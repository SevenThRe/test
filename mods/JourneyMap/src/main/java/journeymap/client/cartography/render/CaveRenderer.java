/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.util.math.BlockPos
 *  net.minecraft.world.WorldProviderHell
 */
package journeymap.client.cartography.render;

import java.awt.image.BufferedImage;
import journeymap.client.cartography.IChunkRenderer;
import journeymap.client.cartography.Strata;
import journeymap.client.cartography.Stratum;
import journeymap.client.cartography.color.RGB;
import journeymap.client.cartography.render.BaseRenderer;
import journeymap.client.cartography.render.SurfaceRenderer;
import journeymap.client.log.StatTimer;
import journeymap.client.model.BlockFlag;
import journeymap.client.model.BlockMD;
import journeymap.client.model.ChunkMD;
import journeymap.client.model.MapType;
import journeymap.client.model.RegionImageCache;
import journeymap.client.model.RegionImageSet;
import journeymap.client.render.ComparableBufferedImage;
import journeymap.common.Journeymap;
import journeymap.common.log.LogFormatter;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.WorldProviderHell;

public class CaveRenderer
extends BaseRenderer
implements IChunkRenderer {
    protected SurfaceRenderer surfaceRenderer;
    protected StatTimer renderCaveTimer = StatTimer.get("CaveRenderer.render");
    protected Strata strata = new Strata("Cave", 40, 8, true);
    protected float defaultDim = 0.2f;
    protected boolean mapSurfaceAboveCaves;

    public CaveRenderer(SurfaceRenderer surfaceRenderer) {
        this.surfaceRenderer = surfaceRenderer;
        this.updateOptions(null, null);
        this.shadingSlopeMin = 0.2f;
        this.shadingSlopeMax = 1.1f;
        this.shadingPrimaryDownslopeMultiplier = 0.7f;
        this.shadingPrimaryUpslopeMultiplier = 1.05f;
        this.shadingSecondaryDownslopeMultiplier = 0.99f;
        this.shadingSecondaryUpslopeMultiplier = 1.01f;
    }

    @Override
    protected boolean updateOptions(ChunkMD chunkMd, MapType mapType) {
        if (super.updateOptions(chunkMd, mapType)) {
            this.mapSurfaceAboveCaves = Journeymap.getClient().getCoreProperties().mapSurfaceAboveCaves.get();
            return true;
        }
        return false;
    }

    @Override
    public int getBlockHeight(ChunkMD chunkMd, BlockPos blockPos) {
        Integer vSlice = blockPos.getY() >> 4;
        int[] sliceBounds = this.getVSliceBounds(chunkMd, vSlice);
        int sliceMinY = sliceBounds[0];
        int sliceMaxY = sliceBounds[1];
        Integer y = this.getBlockHeight(chunkMd, blockPos.getX() & 0xF, vSlice, blockPos.getZ() & 0xF, sliceMinY, sliceMaxY);
        return y == null ? blockPos.getY() : y.intValue();
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public synchronized boolean render(ComparableBufferedImage chunkImage, ChunkMD chunkMd, Integer vSlice) {
        if (vSlice == null) {
            Journeymap.getLogger().warn("ChunkOverworldCaveRenderer is for caves. vSlice can't be null");
            return false;
        }
        this.updateOptions(chunkMd, MapType.underground(vSlice, chunkMd.getDimension()));
        this.renderCaveTimer.start();
        try {
            MapType mapType;
            RegionImageSet ris;
            if (!this.hasSlopes(chunkMd, vSlice)) {
                this.populateSlopes(chunkMd, vSlice, this.getSlopes(chunkMd, vSlice));
            }
            ComparableBufferedImage chunkSurfaceImage = null;
            if (this.mapSurfaceAboveCaves && (ris = RegionImageCache.INSTANCE.getRegionImageSet(chunkMd, mapType = MapType.day(chunkMd.getDimension()))) != null && ris.getHolder(mapType).hasTexture()) {
                chunkSurfaceImage = ris.getChunkImage(chunkMd, mapType);
            }
            boolean bl = this.renderUnderground(chunkSurfaceImage, chunkImage, chunkMd, vSlice);
            return bl;
        }
        finally {
            this.renderCaveTimer.stop();
        }
    }

    protected void mask(BufferedImage chunkSurfaceImage, BufferedImage chunkImage, ChunkMD chunkMd, int x, int y, int z) {
        if (chunkSurfaceImage == null || !this.mapSurfaceAboveCaves) {
            this.paintBlackBlock(chunkImage, x, z);
        } else {
            int surfaceY = Math.max(0, chunkMd.getChunk().getHeightValue(x, z));
            int distance = Math.max(0, surfaceY - y);
            if (distance > 16) {
                int minY = this.getBlockHeight(chunkMd, new BlockPos(x, y - 1, z));
                if (y > 0 && minY > 0) {
                    this.paintBlackBlock(chunkImage, x, z);
                } else {
                    this.paintVoidBlock(chunkImage, x, z);
                }
            } else {
                this.paintDimOverlay(chunkSurfaceImage, chunkImage, x, z, this.defaultDim);
            }
        }
    }

    protected boolean renderUnderground(BufferedImage chunkSurfaceImage, BufferedImage chunkSliceImage, ChunkMD chunkMd, int vSlice) {
        int[] sliceBounds = this.getVSliceBounds(chunkMd, vSlice);
        int sliceMinY = sliceBounds[0];
        int sliceMaxY = sliceBounds[1];
        boolean chunkOk = false;
        for (int z = 0; z < 16; ++z) {
            for (int x = 0; x < 16; ++x) {
                this.strata.reset();
                try {
                    int ceiling = this.getBlockHeight(chunkMd, x, vSlice, z, sliceMinY, sliceMaxY);
                    if (ceiling < 0) {
                        this.paintVoidBlock(chunkSliceImage, x, z);
                        chunkOk = true;
                        continue;
                    }
                    int y = Math.min(ceiling, sliceMaxY);
                    this.buildStrata(this.strata, sliceMinY - 1, chunkMd, x, y, z);
                    if (this.strata.isEmpty()) {
                        this.mask(chunkSurfaceImage, chunkSliceImage, chunkMd, x, y, z);
                        chunkOk = true;
                        continue;
                    }
                    chunkOk = this.paintStrata(this.strata, chunkSliceImage, chunkMd, vSlice, x, ceiling, z) || chunkOk;
                    continue;
                }
                catch (Throwable t) {
                    this.paintBadBlock(chunkSliceImage, x, vSlice, z);
                    String error = "CaveRenderer error at x,vSlice,z = " + x + "," + vSlice + "," + z + " : " + LogFormatter.toString(t);
                    Journeymap.getLogger().error(error);
                }
            }
        }
        this.strata.reset();
        return chunkOk;
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    protected void buildStrata(Strata strata, int minY, ChunkMD chunkMd, int x, int topY, int z) {
        BlockMD lavaBlockMD = null;
        try {
            for (int y = this.getBlockHeight(chunkMd, x, topY >> 4, z, minY, topY).intValue(); y >= 0; --y) {
                BlockMD blockMD = BlockMD.getBlockMDFromChunkLocal(chunkMd, x, y, z);
                if (blockMD.isIgnore() || blockMD.hasFlag(BlockFlag.OpenToSky)) continue;
                strata.setBlocksFound(true);
                BlockMD blockAboveMD = BlockMD.getBlockMDFromChunkLocal(chunkMd, x, y + 1, z);
                if (blockMD.isLava() && blockAboveMD.isLava()) {
                    lavaBlockMD = blockMD;
                }
                if (blockAboveMD.isIgnore() || blockAboveMD.hasFlag(BlockFlag.OpenToSky)) {
                    if (!chunkMd.hasNoSky().booleanValue() && chunkMd.canBlockSeeTheSky(x, y + 1, z)) continue;
                    int lightLevel = this.getSliceLightLevel(chunkMd, x, y, z, true);
                    if (lightLevel > 0) {
                        strata.push(chunkMd, blockMD, x, y, z, lightLevel);
                        if (blockMD.hasTransparency()) {
                            if (this.mapTransparency) continue;
                        }
                    } else if (y >= minY) continue;
                } else if (!strata.isEmpty() || y >= minY) continue;
                break;
            }
        }
        finally {
            if (strata.isEmpty() && lavaBlockMD != null && chunkMd.getWorld().provider instanceof WorldProviderHell) {
                strata.push(chunkMd, lavaBlockMD, x, topY, z, 14);
            }
        }
    }

    protected boolean paintStrata(Strata strata, BufferedImage chunkSliceImage, ChunkMD chunkMd, Integer vSlice, int x, int y, int z) {
        if (strata.isEmpty()) {
            this.paintBadBlock(chunkSliceImage, x, y, z);
            return false;
        }
        try {
            float slope;
            Stratum stratum = null;
            BlockMD blockMD = null;
            while (!strata.isEmpty()) {
                stratum = strata.nextUp(this, true);
                if (strata.getRenderCaveColor() == null) {
                    strata.setRenderCaveColor(stratum.getCaveColor());
                } else {
                    strata.setRenderCaveColor(RGB.blendWith(strata.getRenderCaveColor(), stratum.getCaveColor(), stratum.getBlockMD().getAlpha()));
                }
                blockMD = stratum.getBlockMD();
                strata.release(stratum);
            }
            if (strata.getRenderCaveColor() == null) {
                this.paintBadBlock(chunkSliceImage, x, y, z);
                return false;
            }
            if (!blockMD.hasNoShadow() && (slope = this.getSlope(chunkMd, x, vSlice, z)) != 1.0f) {
                strata.setRenderCaveColor(RGB.bevelSlope(strata.getRenderCaveColor(), slope));
            }
            this.paintBlock(chunkSliceImage, x, z, strata.getRenderCaveColor());
        }
        catch (RuntimeException e) {
            this.paintBadBlock(chunkSliceImage, x, y, z);
            throw e;
        }
        return true;
    }

    @Override
    protected Integer getBlockHeight(ChunkMD chunkMd, int x, Integer vSlice, int z, Integer sliceMinY, Integer sliceMaxY) {
        Integer[][] blockSliceHeights = this.getHeights(chunkMd, vSlice);
        if (blockSliceHeights == null) {
            return null;
        }
        Integer y = blockSliceHeights[x][z];
        if (y != null) {
            return y;
        }
        try {
            BlockMD blockMDAbove;
            y = Math.min(chunkMd.getHeight(new BlockPos(x, 0, z)), sliceMaxY) - 1;
            if (y <= sliceMinY) {
                return y;
            }
            if (y + 1 < sliceMaxY) {
                while (y > 0 && y > sliceMinY && ((blockMDAbove = BlockMD.getBlockMDFromChunkLocal(chunkMd, x, y + 1, z)).isIgnore() || blockMDAbove.hasFlag(BlockFlag.OpenToSky))) {
                    Integer n = y;
                    Integer n2 = y = Integer.valueOf(y - 1);
                }
            }
            blockMDAbove = BlockMD.getBlockMDFromChunkLocal(chunkMd, x, y + 1, z);
            BlockMD blockMD = BlockMD.getBlockMDFromChunkLocal(chunkMd, x, y, z);
            boolean inAirPocket = false;
            while (y > 0 && y > sliceMinY) {
                Integer n;
                Integer n3;
                if (this.mapBathymetry && blockMD.isWater()) {
                    n3 = y;
                    n = y = Integer.valueOf(y - 1);
                }
                inAirPocket = blockMD.isIgnore();
                if (!blockMDAbove.isIgnore() && !blockMDAbove.hasTransparency() && !blockMDAbove.hasFlag(BlockFlag.OpenToSky) || blockMD.isIgnore() && blockMD.hasTransparency() && blockMD.hasFlag(BlockFlag.OpenToSky)) {
                    n3 = y;
                    n = y = Integer.valueOf(y - 1);
                    blockMD = BlockMD.getBlockMDFromChunkLocal(chunkMd, x, y, z);
                    blockMDAbove = BlockMD.getBlockMDFromChunkLocal(chunkMd, x, y + 1, z);
                    if (y >= sliceMinY || inAirPocket) continue;
                }
                break;
            }
        }
        catch (Exception e) {
            Journeymap.getLogger().warn("Couldn't get safe slice block height at " + x + "," + z + ": " + e);
            y = sliceMaxY;
        }
        blockSliceHeights[x][z] = y = Integer.valueOf(Math.max(0, y));
        return y;
    }

    protected int getSliceLightLevel(ChunkMD chunkMd, int x, int y, int z, boolean adjusted) {
        return this.mapCaveLighting ? chunkMd.getSavedLightValue(x, y + 1, z) : 15;
    }
}

