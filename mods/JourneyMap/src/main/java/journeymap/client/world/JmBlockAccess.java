/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  javax.annotation.Nullable
 *  mcp.MethodsReturnNonnullByDefault
 *  net.minecraft.block.state.IBlockState
 *  net.minecraft.init.Biomes
 *  net.minecraft.init.Blocks
 *  net.minecraft.server.integrated.IntegratedServer
 *  net.minecraft.tileentity.TileEntity
 *  net.minecraft.util.EnumFacing
 *  net.minecraft.util.math.BlockPos
 *  net.minecraft.util.math.ChunkPos
 *  net.minecraft.world.IBlockAccess
 *  net.minecraft.world.World
 *  net.minecraft.world.WorldType
 *  net.minecraft.world.biome.Biome
 *  net.minecraftforge.fml.client.FMLClientHandler
 */
package journeymap.client.world;

import javax.annotation.Nullable;
import journeymap.client.data.DataCache;
import journeymap.client.model.ChunkMD;
import mcp.MethodsReturnNonnullByDefault;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Biomes;
import net.minecraft.init.Blocks;
import net.minecraft.server.integrated.IntegratedServer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraft.world.WorldType;
import net.minecraft.world.biome.Biome;
import net.minecraftforge.fml.client.FMLClientHandler;

@MethodsReturnNonnullByDefault
public enum JmBlockAccess implements IBlockAccess
{
    INSTANCE;


    public TileEntity getTileEntity(BlockPos pos) {
        return this.getWorld().getTileEntity(pos);
    }

    public int getCombinedLight(BlockPos pos, int min) {
        return this.getWorld().getCombinedLight(pos, min);
    }

    public IBlockState getBlockState(BlockPos pos) {
        if (!this.isValid(pos)) {
            return Blocks.AIR.getDefaultState();
        }
        ChunkMD chunkMD = this.getChunkMDFromBlockCoords(pos);
        if (chunkMD != null && chunkMD.hasChunk()) {
            return chunkMD.getChunk().getBlockState(pos.getX() & 0xF, pos.getY(), pos.getZ() & 0xF);
        }
        return Blocks.AIR.getDefaultState();
    }

    public boolean isAirBlock(BlockPos pos) {
        return this.getWorld().isAirBlock(pos);
    }

    public Biome getBiome(BlockPos pos) {
        return this.getBiome(pos, Biomes.PLAINS);
    }

    @Nullable
    public Biome getBiome(BlockPos pos, Biome defaultBiome) {
        IntegratedServer server;
        ChunkMD chunkMD = this.getChunkMDFromBlockCoords(pos);
        if (chunkMD != null && chunkMD.hasChunk()) {
            Biome biome = chunkMD.getBiome(pos);
            if (biome != null) {
                return biome;
            }
            biome = defaultBiome;
        }
        if (FMLClientHandler.instance().getClient().isSingleplayer() && (server = FMLClientHandler.instance().getClient().getIntegratedServer()) != null) {
            return server.getEntityWorld().getBiomeProvider().getBiome(pos);
        }
        return defaultBiome;
    }

    public int getStrongPower(BlockPos pos, EnumFacing direction) {
        return this.getWorld().getStrongPower(pos, direction);
    }

    public World getWorld() {
        return FMLClientHandler.instance().getClient().world;
    }

    public WorldType getWorldType() {
        return this.getWorld().getWorldType();
    }

    public boolean isSideSolid(BlockPos pos, EnumFacing side, boolean _default) {
        return this.getWorld().isSideSolid(pos, side, _default);
    }

    private boolean isValid(BlockPos pos) {
        return pos.getX() >= -30000000 && pos.getZ() >= -30000000 && pos.getX() < 30000000 && pos.getZ() < 30000000 && pos.getY() >= 0 && pos.getY() < 256;
    }

    @Nullable
    private ChunkMD getChunkMDFromBlockCoords(BlockPos pos) {
        return DataCache.INSTANCE.getChunkMD(new ChunkPos(pos));
    }
}

