/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  javax.annotation.Nullable
 *  net.minecraft.block.state.IBlockState
 *  net.minecraft.util.math.BlockPos
 *  net.minecraft.util.math.ChunkPos
 *  net.minecraft.world.EnumSkyBlock
 *  net.minecraft.world.IBlockAccess
 *  net.minecraft.world.World
 *  net.minecraft.world.biome.Biome
 *  net.minecraft.world.chunk.Chunk
 *  net.minecraft.world.chunk.EmptyChunk
 */
package journeymap.client.model;

import java.io.Serializable;
import java.lang.ref.WeakReference;
import java.util.HashMap;
import javax.annotation.Nullable;
import journeymap.client.model.BlockDataArrays;
import journeymap.client.model.BlockFlag;
import journeymap.client.model.BlockMD;
import journeymap.client.model.MapType;
import journeymap.client.world.JmBlockAccess;
import journeymap.common.Journeymap;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.world.EnumSkyBlock;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.chunk.Chunk;
import net.minecraft.world.chunk.EmptyChunk;

public class ChunkMD {
    public static final String PROP_IS_SLIME_CHUNK = "isSlimeChunk";
    public static final String PROP_LOADED = "loaded";
    public static final String PROP_LAST_RENDERED = "lastRendered";
    private final WeakReference<Chunk> chunkReference;
    private final ChunkPos coord;
    private final HashMap<String, Serializable> properties = new HashMap();
    private BlockDataArrays blockDataArrays = new BlockDataArrays();
    private Chunk retainedChunk;

    public ChunkMD(Chunk chunk) {
        this(chunk, false);
    }

    public ChunkMD(Chunk chunk, boolean forceRetain) {
        if (chunk == null) {
            throw new IllegalArgumentException("Chunk can't be null");
        }
        this.coord = new ChunkPos(chunk.x, chunk.z);
        this.setProperty(PROP_LOADED, Long.valueOf(System.currentTimeMillis()));
        this.properties.put(PROP_IS_SLIME_CHUNK, Boolean.valueOf(chunk.getRandomWithSeed(987234911L).nextInt(10) == 0));
        this.chunkReference = new WeakReference<Chunk>(chunk);
        if (forceRetain) {
            this.retainedChunk = chunk;
        }
    }

    public IBlockState getBlockState(int localX, int y, int localZ) {
        if (localX < 0 || localX > 15 || localZ < 0 || localZ > 15) {
            Journeymap.getLogger().warn("Expected local coords, got global coords");
        }
        return this.getBlockState(new BlockPos(this.toWorldX(localX), y, this.toWorldZ(localZ)));
    }

    public IBlockState getBlockState(BlockPos blockPos) {
        return JmBlockAccess.INSTANCE.getBlockState(blockPos);
    }

    public BlockMD getBlockMD(BlockPos blockPos) {
        return BlockMD.getBlockMD(this, blockPos);
    }

    @Nullable
    public Biome getBiome(BlockPos pos) {
        Chunk chunk = this.getChunk();
        byte[] blockBiomeArray = chunk.getBiomeArray();
        int i = pos.getX() & 0xF;
        int j = pos.getZ() & 0xF;
        int k = blockBiomeArray[j << 4 | i] & 0xFF;
        if (k == 255) {
            Biome biome = chunk.getWorld().getBiomeProvider().getBiome(pos, null);
            if (biome == null) {
                return null;
            }
            k = Biome.getIdForBiome((Biome)biome);
            blockBiomeArray[j << 4 | i] = (byte)(k & 0xFF);
        }
        return Biome.getBiome((int)k);
    }

    public int getSavedLightValue(int localX, int y, int localZ) {
        try {
            return this.getChunk().getLightFor(EnumSkyBlock.BLOCK, this.getBlockPos(localX, y, localZ));
        }
        catch (ArrayIndexOutOfBoundsException e) {
            return 1;
        }
    }

    public final BlockMD getBlockMD(int localX, int y, int localZ) {
        return BlockMD.getBlockMD(this, this.getBlockPos(localX, y, localZ));
    }

    public int ceiling(int localX, int localZ) {
        int chunkHeight;
        int y = chunkHeight = this.getPrecipitationHeight(this.getBlockPos(localX, 0, localZ));
        BlockPos blockPos = null;
        try {
            Chunk chunk = this.getChunk();
            while (y >= 0) {
                blockPos = this.getBlockPos(localX, y, localZ);
                BlockMD blockMD = this.getBlockMD(blockPos);
                if (blockMD == null) {
                    --y;
                    continue;
                }
                if (blockMD.isIgnore() || blockMD.hasFlag(BlockFlag.OpenToSky)) {
                    --y;
                    continue;
                }
                if (chunk.canSeeSky(blockPos)) {
                    --y;
                    continue;
                }
                break;
            }
        }
        catch (Exception e) {
            Journeymap.getLogger().warn(e + " at " + blockPos, (Throwable)e);
        }
        return Math.max(0, y);
    }

    public boolean hasChunk() {
        Chunk chunk = (Chunk)this.chunkReference.get();
        boolean result = chunk != null && !(chunk instanceof EmptyChunk) && chunk.isLoaded();
        return result;
    }

    public int getHeight(BlockPos blockPos) {
        return this.getChunk().getHeight(blockPos);
    }

    public int getPrecipitationHeight(int localX, int localZ) {
        return this.getChunk().getPrecipitationHeight(this.getBlockPos(localX, 0, localZ)).getY();
    }

    public int getPrecipitationHeight(BlockPos blockPos) {
        return this.getChunk().getPrecipitationHeight(blockPos).getY();
    }

    public int getLightOpacity(BlockMD blockMD, int localX, int y, int localZ) {
        BlockPos pos = this.getBlockPos(localX, y, localZ);
        return blockMD.getBlockState().getBlock().getLightOpacity(blockMD.getBlockState(), (IBlockAccess)JmBlockAccess.INSTANCE, pos);
    }

    public Serializable getProperty(String name) {
        return this.properties.get(name);
    }

    public Serializable getProperty(String name, Serializable defaultValue) {
        Serializable currentValue = this.getProperty(name);
        if (currentValue == null) {
            this.setProperty(name, defaultValue);
            currentValue = defaultValue;
        }
        return currentValue;
    }

    public Serializable setProperty(String name, Serializable value) {
        return this.properties.put(name, value);
    }

    public int hashCode() {
        return this.getCoord().hashCode();
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (this.getClass() != obj.getClass()) {
            return false;
        }
        ChunkMD other = (ChunkMD)obj;
        return this.getCoord().equals((Object)other.getCoord());
    }

    public Chunk getChunk() {
        Chunk chunk = (Chunk)this.chunkReference.get();
        if (chunk == null) {
            throw new ChunkMissingException(this.getCoord());
        }
        return chunk;
    }

    public World getWorld() {
        return this.getChunk().getWorld();
    }

    public int getWorldActualHeight() {
        return this.getWorld().getActualHeight() + 1;
    }

    public Boolean hasNoSky() {
        return !this.getWorld().provider.isSurfaceWorld();
    }

    public boolean canBlockSeeTheSky(int localX, int y, int localZ) {
        return this.getChunk().canSeeSky(this.getBlockPos(localX, y, localZ));
    }

    public ChunkPos getCoord() {
        return this.coord;
    }

    public boolean isSlimeChunk() {
        return (Boolean)this.getProperty(PROP_IS_SLIME_CHUNK, Boolean.FALSE);
    }

    public long getLoaded() {
        return (Long)this.getProperty(PROP_LOADED, Long.valueOf(0L));
    }

    public void resetRenderTimes() {
        this.getRenderTimes().clear();
    }

    public void resetRenderTime(MapType mapType) {
        this.getRenderTimes().put(mapType, 0L);
    }

    public void resetBlockData(MapType mapType) {
        this.getBlockData().get(mapType).clear();
    }

    protected HashMap<MapType, Long> getRenderTimes() {
        HashMap obj = this.properties.get(PROP_LAST_RENDERED);
        if (!(obj instanceof HashMap)) {
            obj = new HashMap();
            this.properties.put(PROP_LAST_RENDERED, obj);
        }
        return obj;
    }

    public long getLastRendered(MapType mapType) {
        return this.getRenderTimes().getOrDefault(mapType, 0L);
    }

    public long setRendered(MapType mapType) {
        long now = System.currentTimeMillis();
        this.getRenderTimes().put(mapType, now);
        return now;
    }

    public BlockPos getBlockPos(int localX, int y, int localZ) {
        return new BlockPos(this.toWorldX(localX), y, this.toWorldZ(localZ));
    }

    public int toWorldX(int localX) {
        return (this.coord.x << 4) + localX;
    }

    public int toWorldZ(int localZ) {
        return (this.coord.z << 4) + localZ;
    }

    public BlockDataArrays getBlockData() {
        return this.blockDataArrays;
    }

    public BlockDataArrays.DataArray<Integer> getBlockDataInts(MapType mapType) {
        return this.blockDataArrays.get(mapType).ints();
    }

    public BlockDataArrays.DataArray<Float> getBlockDataFloats(MapType mapType) {
        return this.blockDataArrays.get(mapType).floats();
    }

    public BlockDataArrays.DataArray<Boolean> getBlockDataBooleans(MapType mapType) {
        return this.blockDataArrays.get(mapType).booleans();
    }

    public String toString() {
        return "ChunkMD{coord=" + this.coord + ", properties=" + this.properties + '}';
    }

    public int getDimension() {
        return this.getWorld().provider.getDimension();
    }

    public void stopChunkRetention() {
        this.retainedChunk = null;
    }

    public boolean hasRetainedChunk() {
        return this.retainedChunk != null;
    }

    protected void finalize() throws Throwable {
        if (this.retainedChunk != null) {
            super.finalize();
        }
    }

    public static class ChunkMissingException
    extends RuntimeException {
        ChunkMissingException(ChunkPos coord) {
            super("Chunk missing: " + coord);
        }
    }
}

