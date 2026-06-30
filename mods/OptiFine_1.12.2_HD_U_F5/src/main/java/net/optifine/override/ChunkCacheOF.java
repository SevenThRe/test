/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  amy
 *  amz
 *  and
 *  anh
 *  avj
 *  awt
 *  axw$a
 *  et
 *  fa
 */
package net.optifine.override;

import java.util.Arrays;
import net.optifine.DynamicLights;
import net.optifine.reflect.Reflector;
import net.optifine.util.ArrayCache;

public class ChunkCacheOF
implements amy {
    private final and chunkCache;
    private final int posX;
    private final int posY;
    private final int posZ;
    private final int sizeX;
    private final int sizeY;
    private final int sizeZ;
    private final int sizeXY;
    private int[] combinedLights;
    private awt[] blockStates;
    private final int arraySize;
    private final boolean dynamicLights = Config.isDynamicLights();
    private static final ArrayCache cacheCombinedLights = new ArrayCache(Integer.TYPE, 16);
    private static final ArrayCache cacheBlockStates = new ArrayCache(awt.class, 16);

    public ChunkCacheOF(and chunkCache, et posFromIn, et posToIn, int subIn) {
        this.chunkCache = chunkCache;
        int minChunkX = posFromIn.p() - subIn >> 4;
        int minChunkY = posFromIn.q() - subIn >> 4;
        int minChunkZ = posFromIn.r() - subIn >> 4;
        int maxChunkX = posToIn.p() + subIn >> 4;
        int maxChunkY = posToIn.q() + subIn >> 4;
        int maxChunkZ = posToIn.r() + subIn >> 4;
        this.sizeX = maxChunkX - minChunkX + 1 << 4;
        this.sizeY = maxChunkY - minChunkY + 1 << 4;
        this.sizeZ = maxChunkZ - minChunkZ + 1 << 4;
        this.sizeXY = this.sizeX * this.sizeY;
        this.arraySize = this.sizeX * this.sizeY * this.sizeZ;
        this.posX = minChunkX << 4;
        this.posY = minChunkY << 4;
        this.posZ = minChunkZ << 4;
    }

    private int getPositionIndex(et pos) {
        int dx = pos.p() - this.posX;
        if (dx < 0 || dx >= this.sizeX) {
            return -1;
        }
        int dy = pos.q() - this.posY;
        if (dy < 0 || dy >= this.sizeY) {
            return -1;
        }
        int dz = pos.r() - this.posZ;
        if (dz < 0 || dz >= this.sizeZ) {
            return -1;
        }
        return dz * this.sizeXY + dy * this.sizeX + dx;
    }

    public int b(et pos, int lightValue) {
        int index = this.getPositionIndex(pos);
        if (index < 0 || index >= this.arraySize || this.combinedLights == null) {
            return this.getCombinedLightRaw(pos, lightValue);
        }
        int light = this.combinedLights[index];
        if (light == -1) {
            this.combinedLights[index] = light = this.getCombinedLightRaw(pos, lightValue);
        }
        return light;
    }

    private int getCombinedLightRaw(et pos, int lightValue) {
        int light = this.chunkCache.b(pos, lightValue);
        if (this.dynamicLights && !this.o(pos).p()) {
            light = DynamicLights.getCombinedLight(pos, light);
        }
        return light;
    }

    public awt o(et pos) {
        int index = this.getPositionIndex(pos);
        if (index < 0 || index >= this.arraySize || this.blockStates == null) {
            return this.chunkCache.o(pos);
        }
        awt iblockstate = this.blockStates[index];
        if (iblockstate == null) {
            this.blockStates[index] = iblockstate = this.chunkCache.o(pos);
        }
        return iblockstate;
    }

    public void renderStart() {
        if (this.combinedLights == null) {
            this.combinedLights = (int[])cacheCombinedLights.allocate(this.arraySize);
        }
        Arrays.fill(this.combinedLights, -1);
        if (this.blockStates == null) {
            this.blockStates = (awt[])cacheBlockStates.allocate(this.arraySize);
        }
        Arrays.fill(this.blockStates, null);
    }

    public void renderFinish() {
        cacheCombinedLights.free(this.combinedLights);
        this.combinedLights = null;
        cacheBlockStates.free(this.blockStates);
        this.blockStates = null;
    }

    public boolean isEmpty() {
        return this.chunkCache.ac();
    }

    public anh b(et pos) {
        return this.chunkCache.b(pos);
    }

    public int a(et pos, fa direction) {
        return this.chunkCache.a(pos, direction);
    }

    public avj r(et pos) {
        return this.chunkCache.a(pos, axw.a.c);
    }

    public avj getTileEntity(et pos, axw.a type) {
        return this.chunkCache.a(pos, type);
    }

    public amz N() {
        return this.chunkCache.N();
    }

    public boolean d(et pos) {
        return this.chunkCache.d(pos);
    }

    public boolean isSideSolid(et pos, fa side, boolean _default) {
        return Reflector.callBoolean(this.chunkCache, Reflector.ForgeChunkCache_isSideSolid, pos, side, _default);
    }
}

