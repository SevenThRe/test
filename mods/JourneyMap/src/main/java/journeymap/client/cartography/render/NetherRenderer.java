/*
 * Decompiled with CFR 0.152.
 */
package journeymap.client.cartography.render;

import journeymap.client.cartography.IChunkRenderer;
import journeymap.client.cartography.color.RGB;
import journeymap.client.cartography.render.CaveRenderer;
import journeymap.client.model.BlockFlag;
import journeymap.client.model.BlockMD;
import journeymap.client.model.ChunkMD;
import journeymap.client.model.MapType;
import journeymap.common.Journeymap;

public class NetherRenderer
extends CaveRenderer
implements IChunkRenderer {
    public NetherRenderer() {
        super(null);
    }

    @Override
    protected boolean updateOptions(ChunkMD chunkMd, MapType mapType) {
        if (super.updateOptions(chunkMd, mapType)) {
            this.ambientColor = RGB.floats(this.tweakNetherAmbientColor);
            this.mapSurfaceAboveCaves = false;
            return true;
        }
        return false;
    }

    @Override
    protected Integer getBlockHeight(ChunkMD chunkMd, int x, Integer vSlice, int z, Integer sliceMinY, Integer sliceMaxY) {
        int y;
        Integer[][] blockSliceHeights = this.getHeights(chunkMd, vSlice);
        if (blockSliceHeights == null) {
            return null;
        }
        Integer intY = blockSliceHeights[x][z];
        if (intY != null) {
            return intY;
        }
        try {
            y = sliceMaxY;
            BlockMD blockMD = BlockMD.getBlockMDFromChunkLocal(chunkMd, x, y, z);
            BlockMD blockMDAbove = BlockMD.getBlockMDFromChunkLocal(chunkMd, x, Math.min(y + 1, sliceMaxY), z);
            while (y > 0 && !blockMD.isLava()) {
                if (blockMDAbove.isIgnore() || blockMDAbove.hasTransparency() || blockMDAbove.hasFlag(BlockFlag.OpenToSky)) {
                    if (!(blockMD.isIgnore() || blockMD.hasTransparency() || blockMD.hasFlag(BlockFlag.OpenToSky))) {
                        break;
                    }
                } else if (y == sliceMinY) {
                    y = sliceMaxY;
                    break;
                }
                blockMD = BlockMD.getBlockMDFromChunkLocal(chunkMd, x, --y, z);
                blockMDAbove = BlockMD.getBlockMDFromChunkLocal(chunkMd, x, y + 1, z);
            }
        }
        catch (Exception e) {
            Journeymap.getLogger().warn("Couldn't get safe slice block height at " + x + "," + z + ": " + e);
            y = sliceMaxY;
        }
        y = Math.max(0, y);
        blockSliceHeights[x][z] = y;
        return y;
    }

    @Override
    protected int getSliceLightLevel(ChunkMD chunkMd, int x, int y, int z, boolean adjusted) {
        if (y + 1 >= chunkMd.getWorldActualHeight()) {
            return 0;
        }
        return this.mapCaveLighting ? Math.max(adjusted ? 2 : 0, chunkMd.getSavedLightValue(x, y + 1, z)) : 15;
    }

    @Override
    public float[] getAmbientColor() {
        return this.ambientColor;
    }
}

