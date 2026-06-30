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


    public TileEntity func_175625_s(BlockPos pos) {
        return this.getWorld().func_175625_s(pos);
    }

    public int func_175626_b(BlockPos pos, int min) {
        return this.getWorld().func_175626_b(pos, min);
    }

    public IBlockState func_180495_p(BlockPos pos) {
        if (!this.isValid(pos)) {
            return Blocks.field_150350_a.func_176223_P();
        }
        ChunkMD chunkMD = this.getChunkMDFromBlockCoords(pos);
        if (chunkMD != null && chunkMD.hasChunk()) {
            return chunkMD.getChunk().func_186032_a(pos.func_177958_n() & 0xF, pos.func_177956_o(), pos.func_177952_p() & 0xF);
        }
        return Blocks.field_150350_a.func_176223_P();
    }

    public boolean func_175623_d(BlockPos pos) {
        return this.getWorld().func_175623_d(pos);
    }

    public Biome func_180494_b(BlockPos pos) {
        return this.getBiome(pos, Biomes.field_76772_c);
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
        if (FMLClientHandler.instance().getClient().func_71356_B() && (server = FMLClientHandler.instance().getClient().func_71401_C()) != null) {
            return server.func_130014_f_().func_72959_q().func_180631_a(pos);
        }
        return defaultBiome;
    }

    public int func_175627_a(BlockPos pos, EnumFacing direction) {
        return this.getWorld().func_175627_a(pos, direction);
    }

    public World getWorld() {
        return FMLClientHandler.instance().getClient().field_71441_e;
    }

    public WorldType func_175624_G() {
        return this.getWorld().func_175624_G();
    }

    public boolean isSideSolid(BlockPos pos, EnumFacing side, boolean _default) {
        return this.getWorld().isSideSolid(pos, side, _default);
    }

    private boolean isValid(BlockPos pos) {
        return pos.func_177958_n() >= -30000000 && pos.func_177952_p() >= -30000000 && pos.func_177958_n() < 30000000 && pos.func_177952_p() < 30000000 && pos.func_177956_o() >= 0 && pos.func_177956_o() < 256;
    }

    @Nullable
    private ChunkMD getChunkMDFromBlockCoords(BlockPos pos) {
        return DataCache.INSTANCE.getChunkMD(new ChunkPos(pos));
    }
}

