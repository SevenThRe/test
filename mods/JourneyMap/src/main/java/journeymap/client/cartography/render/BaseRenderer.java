/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.block.Block
 *  net.minecraft.init.Blocks
 *  net.minecraft.util.math.BlockPos
 *  net.minecraft.util.math.ChunkPos
 */
package journeymap.client.cartography.render;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Collection;
import java.util.concurrent.atomic.AtomicLong;
import journeymap.client.cartography.IChunkRenderer;
import journeymap.client.cartography.Stratum;
import journeymap.client.cartography.color.RGB;
import journeymap.client.data.DataCache;
import journeymap.client.model.BlockCoordIntPair;
import journeymap.client.model.ChunkMD;
import journeymap.client.model.MapType;
import journeymap.client.properties.CoreProperties;
import journeymap.common.Journeymap;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkPos;

public abstract class BaseRenderer
implements IChunkRenderer {
    public static final int COLOR_BLACK = Color.black.getRGB();
    public static volatile AtomicLong badBlockCount = new AtomicLong(0L);
    protected static final float[] DEFAULT_FOG = new float[]{0.0f, 0.0f, 0.1f};
    protected final DataCache dataCache = DataCache.INSTANCE;
    protected CoreProperties coreProperties;
    protected boolean mapBathymetry;
    protected boolean mapTransparency;
    protected boolean mapCaveLighting;
    protected boolean mapAntialiasing;
    protected float[] ambientColor;
    protected long lastPropFileUpdate;
    protected ArrayList<BlockCoordIntPair> primarySlopeOffsets = new ArrayList(3);
    protected ArrayList<BlockCoordIntPair> secondarySlopeOffsets = new ArrayList(4);
    protected float shadingSlopeMin;
    protected float shadingSlopeMax;
    protected float shadingPrimaryDownslopeMultiplier;
    protected float shadingPrimaryUpslopeMultiplier;
    protected float shadingSecondaryDownslopeMultiplier;
    protected float shadingSecondaryUpslopeMultiplier;
    protected float tweakMoonlightLevel;
    protected float tweakBrightenDaylightDiff;
    protected float tweakBrightenLightsourceBlock;
    protected float tweakBlendShallowWater;
    protected float tweakMinimumDarkenNightWater;
    protected float tweakWaterColorBlend;
    protected int tweakSurfaceAmbientColor;
    protected int tweakCaveAmbientColor;
    protected int tweakNetherAmbientColor;
    protected int tweakEndAmbientColor;
    private static final String PROP_SLOPES = "slopes";
    private static final String PROP_HEIGHTS = "heights";
    private static final String PROP_WATER_HEIGHTS = "waterHeights";
    private MapType currentMapType;

    public BaseRenderer() {
        this.updateOptions(null, null);
        this.shadingSlopeMin = 0.2f;
        this.shadingSlopeMax = 1.7f;
        this.shadingPrimaryDownslopeMultiplier = 0.65f;
        this.shadingPrimaryUpslopeMultiplier = 1.2f;
        this.shadingSecondaryDownslopeMultiplier = 0.95f;
        this.shadingSecondaryUpslopeMultiplier = 1.05f;
        this.tweakMoonlightLevel = 3.5f;
        this.tweakBrightenDaylightDiff = 0.06f;
        this.tweakBrightenLightsourceBlock = 1.2f;
        this.tweakBlendShallowWater = 0.15f;
        this.tweakMinimumDarkenNightWater = 0.25f;
        this.tweakWaterColorBlend = 0.5f;
        this.tweakSurfaceAmbientColor = 26;
        this.tweakCaveAmbientColor = 0;
        this.tweakNetherAmbientColor = 0x330808;
        this.tweakEndAmbientColor = 26;
        this.primarySlopeOffsets.add(new BlockCoordIntPair(0, -1));
        this.primarySlopeOffsets.add(new BlockCoordIntPair(-1, -1));
        this.primarySlopeOffsets.add(new BlockCoordIntPair(-1, 0));
        this.secondarySlopeOffsets.add(new BlockCoordIntPair(-1, -2));
        this.secondarySlopeOffsets.add(new BlockCoordIntPair(-2, -1));
        this.secondarySlopeOffsets.add(new BlockCoordIntPair(-2, -2));
        this.secondarySlopeOffsets.add(new BlockCoordIntPair(-2, 0));
        this.secondarySlopeOffsets.add(new BlockCoordIntPair(0, -2));
    }

    protected boolean updateOptions(ChunkMD chunkMd, MapType mapType) {
        this.currentMapType = mapType;
        boolean updateNeeded = false;
        this.coreProperties = Journeymap.getClient().getCoreProperties();
        long lastUpdate = Journeymap.getClient().getCoreProperties().lastModified();
        if (lastUpdate == 0L || this.lastPropFileUpdate != lastUpdate) {
            updateNeeded = true;
            this.lastPropFileUpdate = lastUpdate;
            this.mapBathymetry = this.coreProperties.mapBathymetry.get();
            this.mapTransparency = this.coreProperties.mapTransparency.get();
            this.mapAntialiasing = this.coreProperties.mapAntialiasing.get();
            this.mapCaveLighting = this.coreProperties.mapCaveLighting.get();
            this.ambientColor = new float[]{0.0f, 0.0f, 0.0f};
        }
        if (chunkMd != null) {
            Long lastChunkUpdate = (Long)chunkMd.getProperty("lastPropFileUpdate", Long.valueOf(this.lastPropFileUpdate));
            updateNeeded = true;
            chunkMd.resetBlockData(this.getCurrentMapType());
            chunkMd.setProperty("lastPropFileUpdate", lastChunkUpdate);
        }
        return updateNeeded;
    }

    @Override
    public float[] getAmbientColor() {
        return DEFAULT_FOG;
    }

    @Override
    public void setStratumColors(Stratum stratum, int lightAttenuation, Integer waterColor, boolean waterAbove, boolean underground, boolean mapCaveLighting) {
        float nightLightDiff;
        float daylightDiff;
        if (stratum.isUninitialized()) {
            throw new IllegalStateException("Stratum wasn't initialized for setStratumColors");
        }
        float dayAmbient = 15.0f;
        boolean noSky = stratum.getWorldHasNoSky();
        if (noSky) {
            dayAmbient = stratum.getWorldAmbientLight();
            nightLightDiff = daylightDiff = Math.max(1.0f, Math.max((float)stratum.getLightLevel(), dayAmbient - (float)lightAttenuation)) / 15.0f;
        } else {
            daylightDiff = Math.max(1.0f, Math.max((float)stratum.getLightLevel(), dayAmbient - (float)lightAttenuation)) / 15.0f;
            daylightDiff += this.tweakBrightenDaylightDiff;
            nightLightDiff = Math.max(this.tweakMoonlightLevel, Math.max((float)stratum.getLightLevel(), this.tweakMoonlightLevel - (float)lightAttenuation)) / 15.0f;
        }
        int basicColor = stratum.getBlockMD().getBlockColor(stratum.getChunkMd(), stratum.getBlockPos());
        Block block = stratum.getBlockMD().getBlockState().getBlock();
        if (block == Blocks.GLOWSTONE || block == Blocks.LIT_REDSTONE_LAMP) {
            basicColor = RGB.adjustBrightness(basicColor, this.tweakBrightenLightsourceBlock);
        }
        if (waterAbove && waterColor != null) {
            int adjustedWaterColor = waterColor;
            int adjustedBasicColor = RGB.adjustBrightness(basicColor, Math.max(daylightDiff, nightLightDiff));
            stratum.setDayColor(RGB.blendWith(adjustedBasicColor, adjustedWaterColor, this.tweakWaterColorBlend));
            if (noSky) {
                stratum.setNightColor(stratum.getDayColor());
            } else {
                stratum.setNightColor(RGB.adjustBrightness(stratum.getDayColor(), Math.max(nightLightDiff, this.tweakMinimumDarkenNightWater)));
            }
        } else {
            stratum.setDayColor(RGB.adjustBrightness(basicColor, daylightDiff));
            if (noSky) {
                stratum.setNightColor(stratum.getDayColor());
            } else {
                stratum.setNightColor(RGB.darkenAmbient(basicColor, nightLightDiff, this.getAmbientColor()));
            }
        }
        if (underground) {
            stratum.setCaveColor(mapCaveLighting ? stratum.getNightColor() : stratum.getDayColor());
        }
    }

    protected Float[][] populateSlopes(ChunkMD chunkMd, Integer vSlice, Float[][] slopes) {
        boolean isSurface;
        int y = 0;
        int sliceMinY = 0;
        int sliceMaxY = 0;
        boolean bl = isSurface = vSlice == null;
        if (!isSurface) {
            int[] sliceBounds = this.getVSliceBounds(chunkMd, vSlice);
            sliceMinY = sliceBounds[0];
            sliceMaxY = sliceBounds[1];
        }
        for (int z = 0; z < 16; ++z) {
            for (int x = 0; x < 16; ++x) {
                y = this.getBlockHeight(chunkMd, x, vSlice, z, sliceMinY, sliceMaxY);
                Float primarySlope = Float.valueOf(this.calculateSlope(chunkMd, this.primarySlopeOffsets, x, y, z, isSurface, vSlice, sliceMinY, sliceMaxY));
                Float slope = primarySlope;
                if (slope.floatValue() < 1.0f) {
                    slope = Float.valueOf(slope.floatValue() * this.shadingPrimaryDownslopeMultiplier);
                } else if (slope.floatValue() > 1.0f) {
                    slope = Float.valueOf(slope.floatValue() * this.shadingPrimaryUpslopeMultiplier);
                }
                if (this.mapAntialiasing && primarySlope.floatValue() == 1.0f) {
                    Float secondarySlope = Float.valueOf(this.calculateSlope(chunkMd, this.secondarySlopeOffsets, x, y, z, isSurface, vSlice, sliceMinY, sliceMaxY));
                    if (secondarySlope.floatValue() > primarySlope.floatValue()) {
                        slope = Float.valueOf(slope.floatValue() * this.shadingSecondaryUpslopeMultiplier);
                    } else if (secondarySlope.floatValue() < primarySlope.floatValue()) {
                        slope = Float.valueOf(slope.floatValue() * this.shadingSecondaryDownslopeMultiplier);
                    }
                }
                if (slope.isNaN()) {
                    slope = Float.valueOf(1.0f);
                }
                slopes[x][z] = Float.valueOf(Math.min(this.shadingSlopeMax, Math.max(this.shadingSlopeMin, slope.floatValue())));
            }
        }
        return slopes;
    }

    protected MapType getCurrentMapType() {
        return this.currentMapType;
    }

    public abstract int getBlockHeight(ChunkMD var1, BlockPos var2);

    protected abstract Integer getBlockHeight(ChunkMD var1, int var2, Integer var3, int var4, Integer var5, Integer var6);

    protected int getOffsetBlockHeight(ChunkMD chunkMd, int x, Integer vSlice, int z, Integer sliceMinY, Integer sliceMaxY, BlockCoordIntPair offset, int defaultVal) {
        int blockX = (chunkMd.getCoord().x << 4) + (x + offset.x);
        int blockZ = (chunkMd.getCoord().z << 4) + (z + offset.z);
        ChunkPos targetCoord = new ChunkPos(blockX >> 4, blockZ >> 4);
        ChunkMD targetChunkMd = null;
        targetChunkMd = targetCoord.equals((Object)chunkMd.getCoord()) ? chunkMd : this.dataCache.getChunkMD(targetCoord);
        if (targetChunkMd != null) {
            return this.getBlockHeight(targetChunkMd, blockX & 0xF, vSlice, blockZ & 0xF, sliceMinY, sliceMaxY);
        }
        return defaultVal;
    }

    protected float calculateSlope(ChunkMD chunkMd, Collection<BlockCoordIntPair> offsets, int x, int y, int z, boolean isSurface, Integer vSlice, int sliceMinY, int sliceMaxY) {
        if (y <= 0) {
            return 1.0f;
        }
        float slopeSum = 0.0f;
        int defaultHeight = y;
        for (BlockCoordIntPair offset : offsets) {
            float offsetHeight = this.getOffsetBlockHeight(chunkMd, x, vSlice, z, sliceMinY, sliceMaxY, offset, defaultHeight);
            slopeSum += (float)y * 1.0f / offsetHeight;
        }
        Float slope = Float.valueOf(slopeSum / (float)offsets.size());
        if (slope.isNaN()) {
            slope = Float.valueOf(1.0f);
        }
        return slope.floatValue();
    }

    protected int[] getVSliceBounds(ChunkMD chunkMd, Integer vSlice) {
        int hardSliceMaxY;
        int sliceMaxY;
        if (vSlice == null) {
            return null;
        }
        int sliceMinY = Math.max(vSlice << 4, 0);
        if (sliceMinY >= (sliceMaxY = Math.min(hardSliceMaxY = (vSlice + 1 << 4) - 1, chunkMd.getWorld().getActualHeight()))) {
            sliceMaxY = sliceMinY + 2;
        }
        return new int[]{sliceMinY, sliceMaxY};
    }

    protected float getSlope(ChunkMD chunkMd, int x, Integer vSlice, int z) {
        Float[][] slopes = this.getSlopes(chunkMd, vSlice);
        Float slope = slopes[x][z];
        if (slope == null) {
            this.populateSlopes(chunkMd, vSlice, slopes);
            slope = slopes[x][z];
        }
        if (slope == null || slope.isNaN()) {
            Journeymap.getLogger().warn(String.format("Bad slope for %s at %s,%s: %s", chunkMd.getCoord(), x, z, slope));
            slope = Float.valueOf(1.0f);
        }
        return slope.floatValue();
    }

    protected final String getKey(String propName, Integer vSlice) {
        return vSlice == null ? propName : propName + vSlice;
    }

    protected final Integer[][] getHeights(ChunkMD chunkMd, Integer vSlice) {
        return chunkMd.getBlockDataInts(this.getCurrentMapType()).get(this.getKey(PROP_HEIGHTS, vSlice));
    }

    protected final boolean hasHeights(ChunkMD chunkMd, Integer vSlice) {
        return chunkMd.getBlockDataInts(this.getCurrentMapType()).has(this.getKey(PROP_HEIGHTS, vSlice));
    }

    protected final void resetHeights(ChunkMD chunkMd, Integer vSlice) {
        chunkMd.getBlockDataInts(this.getCurrentMapType()).clear(this.getKey(PROP_HEIGHTS, vSlice));
    }

    protected final Float[][] getSlopes(ChunkMD chunkMd, Integer vSlice) {
        return chunkMd.getBlockDataFloats(this.getCurrentMapType()).get(this.getKey(PROP_SLOPES, vSlice));
    }

    protected final boolean hasSlopes(ChunkMD chunkMd, Integer vSlice) {
        return chunkMd.getBlockDataFloats(this.getCurrentMapType()).has(this.getKey(PROP_SLOPES, vSlice));
    }

    protected final void resetSlopes(ChunkMD chunkMd, Integer vSlice) {
        chunkMd.getBlockDataFloats(this.getCurrentMapType()).clear(this.getKey(PROP_SLOPES, vSlice));
    }

    protected final Integer[][] getFluidHeights(ChunkMD chunkMd, Integer vSlice) {
        return chunkMd.getBlockDataInts(this.getCurrentMapType()).get(this.getKey(PROP_WATER_HEIGHTS, vSlice));
    }

    protected final boolean hasWaterHeights(ChunkMD chunkMd, Integer vSlice) {
        return chunkMd.getBlockDataInts(this.getCurrentMapType()).has(this.getKey(PROP_WATER_HEIGHTS, vSlice));
    }

    protected final void resetWaterHeights(ChunkMD chunkMd, Integer vSlice) {
        chunkMd.getBlockDataInts(this.getCurrentMapType()).clear(this.getKey(PROP_WATER_HEIGHTS, vSlice));
    }

    public ChunkMD getOffsetChunk(ChunkMD chunkMd, int x, int z, BlockCoordIntPair offset) {
        int blockX = (chunkMd.getCoord().x << 4) + (x + offset.x);
        int blockZ = (chunkMd.getCoord().z << 4) + (z + offset.z);
        ChunkPos targetCoord = new ChunkPos(blockX >> 4, blockZ >> 4);
        if (targetCoord.equals((Object)chunkMd.getCoord())) {
            return chunkMd;
        }
        return this.dataCache.getChunkMD(targetCoord);
    }

    public void paintDimOverlay(BufferedImage image, int x, int z, float alpha) {
        Integer color = image.getRGB(x, z);
        this.paintBlock(image, x, z, RGB.adjustBrightness(color, alpha));
    }

    public void paintDimOverlay(BufferedImage sourceImage, BufferedImage targetImage, int x, int z, float alpha) {
        Integer color = sourceImage.getRGB(x, z);
        this.paintBlock(targetImage, x, z, RGB.adjustBrightness(color, alpha));
    }

    public void paintBlock(BufferedImage image, int x, int z, int color) {
        image.setRGB(x, z, 0xFF000000 | color);
    }

    public void paintVoidBlock(BufferedImage image, int x, int z) {
        this.paintBlock(image, x, z, RGB.toInteger(this.getAmbientColor()));
    }

    public void paintBlackBlock(BufferedImage image, int x, int z) {
        this.paintBlock(image, x, z, COLOR_BLACK);
    }

    public void paintBadBlock(BufferedImage image, int x, int y, int z) {
        long count = badBlockCount.incrementAndGet();
        if (count == 1L || count % 2046L == 0L) {
            Journeymap.getLogger().warn("Bad block at " + x + "," + y + "," + z + ". Total bad blocks: " + count);
        }
    }
}

