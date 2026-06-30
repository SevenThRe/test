/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.util.math.BlockPos
 *  net.minecraftforge.fml.client.FMLClientHandler
 *  org.apache.logging.log4j.Level
 */
package journeymap.client.cartography.render;

import java.awt.image.BufferedImage;
import journeymap.client.cartography.IChunkRenderer;
import journeymap.client.cartography.color.RGB;
import journeymap.client.cartography.render.BaseRenderer;
import journeymap.client.log.StatTimer;
import journeymap.client.model.BlockCoordIntPair;
import journeymap.client.model.BlockFlag;
import journeymap.client.model.BlockMD;
import journeymap.client.model.ChunkMD;
import journeymap.client.model.MapType;
import journeymap.client.properties.TopoProperties;
import journeymap.client.render.ComparableBufferedImage;
import journeymap.common.Journeymap;
import journeymap.common.log.LogFormatter;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.fml.client.FMLClientHandler;
import org.apache.logging.log4j.Level;

public class TopoRenderer
extends BaseRenderer
implements IChunkRenderer {
    private static final String PROP_SHORE = "isShore";
    private Integer[] waterPalette;
    private Integer[] landPalette;
    private int waterPaletteRange;
    private int landPaletteRange;
    private long lastTopoFileUpdate;
    protected StatTimer renderTopoTimer = StatTimer.get("TopoRenderer.renderSurface");
    private Integer landContourColor;
    private Integer waterContourColor;
    private double waterContourInterval;
    private double landContourInterval;
    TopoProperties topoProperties;

    public TopoRenderer() {
        this.primarySlopeOffsets.clear();
        this.secondarySlopeOffsets.clear();
        this.primarySlopeOffsets.add(new BlockCoordIntPair(0, -1));
        this.primarySlopeOffsets.add(new BlockCoordIntPair(-1, 0));
        this.primarySlopeOffsets.add(new BlockCoordIntPair(0, 1));
        this.primarySlopeOffsets.add(new BlockCoordIntPair(1, 0));
    }

    @Override
    protected boolean updateOptions(ChunkMD chunkMd, MapType mapType) {
        boolean needUpdate = false;
        if (super.updateOptions(chunkMd, mapType)) {
            double worldHeight = 256.0;
            if (chunkMd != null) {
                worldHeight = chunkMd.getWorld().getHeight();
            }
            this.topoProperties = Journeymap.getClient().getTopoProperties();
            if (System.currentTimeMillis() - this.lastTopoFileUpdate > 5000L && this.lastTopoFileUpdate < this.topoProperties.lastModified()) {
                needUpdate = true;
                this.topoProperties.load();
                this.lastTopoFileUpdate = this.topoProperties.lastModified();
                this.landContourColor = this.topoProperties.getLandContourColor();
                this.waterContourColor = this.topoProperties.getWaterContourColor();
                this.waterPalette = this.topoProperties.getWaterColors();
                this.waterPaletteRange = this.waterPalette.length - 1;
                this.waterContourInterval = worldHeight / (double)Math.max(1, this.waterPalette.length);
                this.landPalette = this.topoProperties.getLandColors();
                this.landPaletteRange = this.landPalette.length - 1;
                this.landContourInterval = worldHeight / (double)Math.max(1, this.landPalette.length);
            }
            if (chunkMd != null) {
                Long lastUpdate = (Long)chunkMd.getProperty("lastTopoPropFileUpdate", Long.valueOf(this.lastTopoFileUpdate));
                if (needUpdate || lastUpdate < this.lastTopoFileUpdate) {
                    needUpdate = true;
                    chunkMd.resetBlockData(this.getCurrentMapType());
                }
                chunkMd.setProperty("lastTopoPropFileUpdate", Long.valueOf(this.lastTopoFileUpdate));
            }
        }
        return needUpdate;
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean render(ComparableBufferedImage chunkImage, ChunkMD chunkMd, Integer vSlice) {
        StatTimer timer = this.renderTopoTimer;
        if (this.landPalette == null || this.landPalette.length < 1 || this.waterPalette == null || this.waterPalette.length < 1) {
            return false;
        }
        try {
            timer.start();
            this.updateOptions(chunkMd, MapType.from(MapType.Name.topo, null, chunkMd.getDimension()));
            if (!this.hasSlopes(chunkMd, null)) {
                this.populateSlopes(chunkMd);
            }
            boolean bl = this.renderSurface(chunkImage, chunkMd, vSlice, false);
            return bl;
        }
        catch (Throwable e) {
            e.printStackTrace();
            boolean bl = false;
            return bl;
        }
        finally {
            timer.stop();
        }
    }

    protected boolean renderSurface(BufferedImage chunkImage, ChunkMD chunkMd, Integer vSlice, boolean cavePrePass) {
        boolean chunkOk = false;
        try {
            for (int x = 0; x < 16; ++x) {
                for (int z = 0; z < 16; ++z) {
                    Integer[][] waterHeights;
                    BlockMD topBlockMd = null;
                    int y = Math.max(0, this.getBlockHeight(chunkMd, x, null, z, null, null));
                    if (this.mapBathymetry && (waterHeights = this.getFluidHeights(chunkMd, null))[z] != null && waterHeights[z][x] != null) {
                        y = this.getFluidHeights(chunkMd, null)[z][x];
                    }
                    if ((topBlockMd = chunkMd.getBlockMD(x, y, z)) == null) {
                        this.paintBadBlock(chunkImage, x, y, z);
                        continue;
                    }
                    chunkOk = this.paintContour(chunkImage, chunkMd, topBlockMd, x, y, z) || chunkOk;
                }
            }
        }
        catch (Throwable t) {
            Journeymap.getLogger().log(Level.WARN, "Error in renderSurface: " + LogFormatter.toString(t));
        }
        return chunkOk;
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
        try {
            BlockMD blockMD = BlockMD.getBlockMDFromChunkLocal(chunkMd, localX, y, localZ);
            while (y > 0) {
                if (blockMD.isWater() || blockMD.isIce()) {
                    if (!this.mapBathymetry) break;
                    this.getFluidHeights((ChunkMD)chunkMd, null)[localZ][localX] = y;
                } else if (!blockMD.hasAnyFlag(BlockMD.FlagsPlantAndCrop) && !blockMD.isIgnore() && !blockMD.hasFlag(BlockFlag.NoTopo)) break;
                Integer n = y;
                Integer n2 = y = Integer.valueOf(y - 1);
                blockMD = BlockMD.getBlockMDFromChunkLocal(chunkMd, localX, y, localZ);
            }
        }
        catch (Exception e) {
            Journeymap.getLogger().debug("Couldn't get safe surface block height at " + localX + "," + localZ + ": " + e);
        }
        heights[localX][localZ] = y = Integer.valueOf(Math.max(0, y));
        return y;
    }

    protected Float[][] populateSlopes(ChunkMD chunkMd) {
        Float[][] slopes = this.getSlopes(chunkMd, null);
        float nearZero = 1.0E-4f;
        for (int z = 0; z < 16; ++z) {
            for (int x = 0; x < 16; ++x) {
                Float slope;
                double contourInterval;
                float h = this.getBlockHeight(chunkMd, x, null, z, null, null).intValue();
                BlockMD blockMD = BlockMD.getBlockMDFromChunkLocal(chunkMd, x, (int)h, z);
                boolean isWater = false;
                if (blockMD.isWater() || blockMD.isIce() || this.mapBathymetry && this.getFluidHeights(chunkMd, null)[z][x] != null) {
                    isWater = true;
                    contourInterval = this.waterContourInterval;
                } else {
                    contourInterval = this.landContourInterval;
                }
                float[] heights = new float[this.primarySlopeOffsets.size()];
                Float lastOffsetHeight = null;
                boolean flatOffsets = true;
                boolean isShore = false;
                for (int i = 0; i < heights.length; ++i) {
                    BlockCoordIntPair offset = (BlockCoordIntPair)this.primarySlopeOffsets.get(i);
                    float offsetHeight = this.getOffsetBlockHeight(chunkMd, x, null, z, null, null, offset, (int)h);
                    if (isWater && !isShore) {
                        ChunkMD targetChunkMd = this.getOffsetChunk(chunkMd, x, z, offset);
                        int newX = (chunkMd.getCoord().x << 4) + (x + offset.x) & 0xF;
                        int newZ = (chunkMd.getCoord().z << 4) + (z + offset.z) & 0xF;
                        if (targetChunkMd != null) {
                            if (this.mapBathymetry && this.mapBathymetry && this.getFluidHeights(chunkMd, null)[z][x] == null) {
                                isShore = true;
                            } else {
                                int ceiling = targetChunkMd.ceiling(newX, newZ);
                                BlockMD offsetBlock = targetChunkMd.getBlockMD(newX, ceiling, newZ);
                                if (!offsetBlock.isWater() && !offsetBlock.isIce()) {
                                    isShore = true;
                                }
                            }
                        }
                    }
                    heights[i] = offsetHeight = (float)Math.max((double)nearZero, (double)offsetHeight - (double)offsetHeight % contourInterval);
                    if (lastOffsetHeight == null) {
                        lastOffsetHeight = Float.valueOf(offsetHeight);
                        continue;
                    }
                    if (!flatOffsets) continue;
                    flatOffsets = lastOffsetHeight.floatValue() == offsetHeight;
                }
                if (isWater) {
                    this.getShore((ChunkMD)chunkMd)[z][x] = isShore;
                }
                h = (float)Math.max((double)nearZero, (double)h - (double)h % contourInterval);
                if (flatOffsets) {
                    slope = Float.valueOf(1.0f);
                } else {
                    slope = Float.valueOf(0.0f);
                    for (float offsetHeight : heights) {
                        slope = Float.valueOf(slope.floatValue() + h / offsetHeight);
                    }
                    slope = Float.valueOf(slope.floatValue() / (float)heights.length);
                }
                if (slope.isNaN() || slope.isInfinite()) {
                    slope = Float.valueOf(1.0f);
                }
                slopes[x][z] = slope;
            }
        }
        return slopes;
    }

    @Override
    public int getBlockHeight(ChunkMD chunkMd, BlockPos blockPos) {
        return FMLClientHandler.instance().getClient().world.getChunk(blockPos).getPrecipitationHeight(blockPos).getY();
    }

    protected boolean paintContour(BufferedImage chunkImage, ChunkMD chunkMd, BlockMD topBlockMd, int x, int y, int z) {
        if (!chunkMd.hasChunk()) {
            return false;
        }
        try {
            Integer color;
            boolean isWater;
            float slope = this.getSlope(chunkMd, x, null, z);
            boolean bl = isWater = topBlockMd.isWater() || topBlockMd.isIce();
            if (slope > 1.0f && this.waterContourColor != null && this.landContourColor != null) {
                color = isWater ? this.waterContourColor : this.landContourColor;
            } else if (topBlockMd.isLava()) {
                color = topBlockMd.getTextureColor();
            } else if (isWater) {
                if (this.getShore(chunkMd)[z][x] == Boolean.TRUE && this.waterContourColor != null) {
                    color = this.waterContourColor;
                } else {
                    int index = (int)Math.floor(((double)y - (double)y % this.waterContourInterval) / this.waterContourInterval);
                    index = Math.max(0, Math.min(index, this.waterPaletteRange));
                    color = this.waterPalette[index];
                    if (slope < 1.0f) {
                        color = RGB.adjustBrightness(color, 0.9f);
                    }
                }
            } else {
                int index = (int)Math.floor(((double)y - (double)y % this.landContourInterval) / this.landContourInterval);
                index = Math.max(0, Math.min(index, this.landPaletteRange));
                color = this.landPalette[index];
                if (slope < 1.0f && this.waterContourColor != null && this.landContourColor != null) {
                    color = RGB.adjustBrightness(color, 0.85f);
                }
            }
            this.paintBlock(chunkImage, x, z, color);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return true;
    }

    protected final Boolean[][] getShore(ChunkMD chunkMd) {
        return chunkMd.getBlockDataBooleans(this.getCurrentMapType()).get(PROP_SHORE);
    }

    protected final boolean hasShore(ChunkMD chunkMd) {
        return chunkMd.getBlockDataBooleans(this.getCurrentMapType()).has(PROP_SHORE);
    }

    protected final void resetShore(ChunkMD chunkMd) {
        chunkMd.getBlockDataBooleans(this.getCurrentMapType()).clear(PROP_SHORE);
    }
}

