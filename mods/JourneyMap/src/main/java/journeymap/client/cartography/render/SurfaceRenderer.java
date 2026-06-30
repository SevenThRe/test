/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.util.math.BlockPos
 *  org.apache.logging.log4j.Level
 */
package journeymap.client.cartography.render;

import java.awt.image.BufferedImage;
import journeymap.client.cartography.IChunkRenderer;
import journeymap.client.cartography.Strata;
import journeymap.client.cartography.Stratum;
import journeymap.client.cartography.color.RGB;
import journeymap.client.cartography.render.BaseRenderer;
import journeymap.client.log.StatTimer;
import journeymap.client.model.BlockCoordIntPair;
import journeymap.client.model.BlockMD;
import journeymap.client.model.ChunkMD;
import journeymap.client.model.MapType;
import journeymap.client.render.ComparableBufferedImage;
import journeymap.common.Journeymap;
import journeymap.common.log.LogFormatter;
import net.minecraft.util.math.BlockPos;
import org.apache.logging.log4j.Level;

public class SurfaceRenderer
extends BaseRenderer
implements IChunkRenderer {
    protected StatTimer renderSurfaceTimer = StatTimer.get("SurfaceRenderer.renderSurface");
    protected StatTimer renderSurfacePrepassTimer = StatTimer.get("SurfaceRenderer.renderSurface.CavePrepass");
    protected Strata strata = new Strata("Surface", 40, 8, false);
    protected float maxDepth = 8.0f;

    public SurfaceRenderer() {
        this.updateOptions(null, null);
    }

    @Override
    protected boolean updateOptions(ChunkMD chunkMd, MapType mapType) {
        if (super.updateOptions(chunkMd, mapType)) {
            this.ambientColor = RGB.floats(this.tweakSurfaceAmbientColor);
            return true;
        }
        return false;
    }

    @Override
    public int getBlockHeight(ChunkMD chunkMd, BlockPos blockPos) {
        Integer y = this.getBlockHeight(chunkMd, blockPos.func_177958_n() & 0xF, null, blockPos.func_177952_p() & 0xF, null, null);
        return y == null ? blockPos.func_177956_o() : y.intValue();
    }

    @Override
    public boolean render(ComparableBufferedImage dayChunkImage, ChunkMD chunkMd, Integer ignored) {
        return this.render(dayChunkImage, null, chunkMd, null, false);
    }

    public boolean render(ComparableBufferedImage dayChunkImage, BufferedImage nightChunkImage, ChunkMD chunkMd) {
        return this.render(dayChunkImage, nightChunkImage, chunkMd, null, false);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public synchronized boolean render(ComparableBufferedImage dayChunkImage, BufferedImage nightChunkImage, ChunkMD chunkMd, Integer vSlice, boolean cavePrePass) {
        StatTimer timer = cavePrePass ? this.renderSurfacePrepassTimer : this.renderSurfaceTimer;
        try {
            timer.start();
            this.updateOptions(chunkMd, MapType.from(MapType.Name.surface, null, chunkMd.getDimension()));
            if (!this.hasSlopes(chunkMd, vSlice)) {
                this.populateSlopes(chunkMd, vSlice, this.getSlopes(chunkMd, vSlice));
            }
            boolean bl = this.renderSurface(dayChunkImage, nightChunkImage, chunkMd, vSlice, cavePrePass);
            return bl;
        }
        catch (Throwable e) {
            e.printStackTrace();
            boolean bl = false;
            return bl;
        }
        finally {
            this.strata.reset();
            timer.stop();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    protected boolean renderSurface(BufferedImage dayChunkImage, BufferedImage nightChunkImage, ChunkMD chunkMd, Integer vSlice, boolean cavePrePass) {
        boolean chunkOk = false;
        try {
            int sliceMaxY = 0;
            if (cavePrePass) {
                int[] sliceBounds = this.getVSliceBounds(chunkMd, vSlice);
                sliceMaxY = sliceBounds[1];
            }
            for (int x = 0; x < 16; ++x) {
                for (int z = 0; z < 16; ++z) {
                    Integer[][] waterHeights;
                    Integer waterHeight;
                    boolean showSlope;
                    this.strata.reset();
                    int upperY = Math.max(0, chunkMd.getPrecipitationHeight(x, z));
                    int lowerY = Math.max(0, this.getBlockHeight(chunkMd, x, null, z, null, null));
                    if (upperY == 0 || lowerY == 0) {
                        this.paintVoidBlock(dayChunkImage, x, z);
                        if (!cavePrePass && nightChunkImage != null) {
                            this.paintVoidBlock(nightChunkImage, x, z);
                        }
                        chunkOk = true;
                        continue;
                    }
                    if (cavePrePass && upperY > sliceMaxY && (float)(upperY - sliceMaxY) > this.maxDepth) {
                        chunkOk = true;
                        this.paintBlackBlock(dayChunkImage, x, z);
                        continue;
                    }
                    boolean bl = showSlope = !chunkMd.getBlockMD(x, lowerY, z).hasNoShadow();
                    if (this.mapBathymetry && (waterHeight = (waterHeights = this.getFluidHeights(chunkMd, null))[z][x]) != null) {
                        upperY = waterHeight;
                    }
                    this.buildStrata(this.strata, upperY, chunkMd, x, lowerY, z);
                    chunkOk = this.paintStrata(this.strata, dayChunkImage, nightChunkImage, chunkMd, x, z, showSlope, cavePrePass) || chunkOk;
                }
            }
        }
        catch (Throwable t) {
            Journeymap.getLogger().log(Level.WARN, LogFormatter.toString(t));
        }
        finally {
            this.strata.reset();
        }
        return chunkOk;
    }

    public int getSurfaceBlockHeight(ChunkMD chunkMd, int x, int z, BlockCoordIntPair offset, int defaultVal) {
        ChunkMD targetChunkMd = this.getOffsetChunk(chunkMd, x, z, offset);
        int newX = (chunkMd.getCoord().field_77276_a << 4) + (x + offset.x) & 0xF;
        int newZ = (chunkMd.getCoord().field_77275_b << 4) + (z + offset.z) & 0xF;
        if (targetChunkMd != null) {
            Integer height = this.getBlockHeight(targetChunkMd, newX, null, newZ, null, null);
            if (height == null) {
                return defaultVal;
            }
            return height;
        }
        return defaultVal;
    }

    @Override
    public Integer getBlockHeight(ChunkMD chunkMd, int localX, Integer vSlice, int localZ, Integer sliceMinY, Integer sliceMaxY) {
        Integer[][] heights = this.getHeights(chunkMd, null);
        if (heights == null) {
            return null;
        }
        Integer y = heights[localX][localZ];
        if (y != null) {
            return y;
        }
        y = Math.max(0, chunkMd.getPrecipitationHeight(localX, localZ));
        if (y == 0) {
            return 0;
        }
        boolean setFluidHeight = true;
        try {
            while (y > 0) {
                Integer n;
                Integer n2;
                BlockMD blockMD = BlockMD.getBlockMDFromChunkLocal(chunkMd, localX, y, localZ);
                if (blockMD.isIgnore()) {
                    n2 = y;
                    n = y = Integer.valueOf(y - 1);
                    continue;
                }
                if (blockMD.isWater() || blockMD.isFluid()) {
                    if (this.mapBathymetry) {
                        if (setFluidHeight) {
                            this.getFluidHeights((ChunkMD)chunkMd, null)[localZ][localX] = y;
                            setFluidHeight = false;
                        }
                        n2 = y;
                        n = y = Integer.valueOf(y - 1);
                        continue;
                    }
                } else {
                    if (blockMD.hasTransparency() && this.mapTransparency) {
                        n2 = y;
                        n = y = Integer.valueOf(y - 1);
                        continue;
                    }
                    if (!blockMD.isLava() && blockMD.hasNoShadow()) {
                        n2 = y;
                        n = y = Integer.valueOf(y - 1);
                    }
                }
                break;
            }
        }
        catch (Exception e) {
            Journeymap.getLogger().warn(String.format("Couldn't get safe surface block height for %s coords %s,%s: %s", chunkMd, localX, localZ, LogFormatter.toString(e)));
        }
        heights[localX][localZ] = y = Integer.valueOf(Math.max(0, y));
        return y;
    }

    protected void buildStrata(Strata strata, int upperY, ChunkMD chunkMd, int x, int lowerY, int z) {
        BlockMD blockMD;
        while (upperY > lowerY) {
            blockMD = BlockMD.getBlockMDFromChunkLocal(chunkMd, x, upperY, z);
            if (!blockMD.isIgnore()) {
                if (blockMD.hasTransparency()) {
                    strata.push(chunkMd, blockMD, x, upperY, z);
                    if (!this.mapTransparency) break;
                }
                if (blockMD.hasNoShadow()) {
                    lowerY = upperY;
                    break;
                }
            }
            --upperY;
        }
        if (this.mapTransparency || strata.isEmpty()) {
            while (lowerY >= 0 && !((float)(upperY - lowerY) >= this.maxDepth)) {
                blockMD = BlockMD.getBlockMDFromChunkLocal(chunkMd, x, lowerY, z);
                if (!blockMD.isIgnore()) {
                    strata.push(chunkMd, blockMD, x, lowerY, z);
                    if (!blockMD.hasTransparency() || !this.mapTransparency) break;
                }
                --lowerY;
            }
        }
    }

    protected boolean paintStrata(Strata strata, BufferedImage dayChunkImage, BufferedImage nightChunkImage, ChunkMD chunkMd, int x, int z, boolean showSlope, boolean cavePrePass) {
        float slope;
        int y = strata.getTopY();
        if (strata.isEmpty()) {
            if (dayChunkImage != null) {
                this.paintBadBlock(dayChunkImage, x, y, z);
            }
            if (nightChunkImage != null) {
                this.paintBadBlock(nightChunkImage, x, y, z);
            }
            return false;
        }
        while (!strata.isEmpty()) {
            Stratum stratum = strata.nextUp(this, true);
            if (strata.getRenderDayColor() == null || strata.getRenderNightColor() == null) {
                strata.setRenderDayColor(stratum.getDayColor());
                if (!cavePrePass) {
                    strata.setRenderNightColor(stratum.getNightColor());
                }
            } else {
                strata.setRenderDayColor(RGB.blendWith(strata.getRenderDayColor(), stratum.getDayColor(), stratum.getBlockMD().getAlpha()));
                if (!cavePrePass) {
                    strata.setRenderNightColor(RGB.blendWith(strata.getRenderNightColor(), stratum.getNightColor(), stratum.getBlockMD().getAlpha()));
                }
            }
            strata.release(stratum);
        }
        if (strata.getRenderDayColor() == null) {
            this.paintBadBlock(dayChunkImage, x, y, z);
            this.paintBadBlock(nightChunkImage, x, y, z);
            return false;
        }
        if (nightChunkImage != null && strata.getRenderNightColor() == null) {
            this.paintBadBlock(nightChunkImage, x, y, z);
            return false;
        }
        if (showSlope && (slope = this.getSlope(chunkMd, x, null, z)) != 1.0f) {
            strata.setRenderDayColor(RGB.bevelSlope(strata.getRenderDayColor(), slope));
            if (!cavePrePass) {
                strata.setRenderNightColor(RGB.bevelSlope(strata.getRenderNightColor(), slope));
            }
        }
        this.paintBlock(dayChunkImage, x, z, strata.getRenderDayColor());
        if (nightChunkImage != null) {
            this.paintBlock(nightChunkImage, x, z, strata.getRenderNightColor());
        }
        return true;
    }
}

