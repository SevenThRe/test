/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  javax.annotation.Nullable
 *  net.minecraft.block.state.IBlockState
 *  net.minecraft.util.math.BlockPos
 *  net.minecraft.world.IBlockAccess
 *  net.minecraftforge.fml.client.FMLClientHandler
 */
package journeymap.client.mod.impl;

import javax.annotation.Nullable;
import journeymap.client.mod.IBlockColorProxy;
import journeymap.client.mod.IModBlockHandler;
import journeymap.client.mod.ModBlockDelegate;
import journeymap.client.model.BlockMD;
import journeymap.client.model.ChunkMD;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraftforge.fml.client.FMLClientHandler;

@Deprecated
public class CodeChickenLibMod
implements IModBlockHandler,
IBlockColorProxy {
    @Override
    public void initialize(BlockMD blockMD) {
        blockMD.setBlockColorProxy(this);
    }

    @Override
    public int deriveBlockColor(BlockMD blockMD, @Nullable ChunkMD chunkMD, @Nullable BlockPos blockPos) {
        return ModBlockDelegate.INSTANCE.getDefaultBlockColorProxy().deriveBlockColor(blockMD, chunkMD, blockPos);
    }

    @Override
    public int getBlockColor(ChunkMD chunkMD, BlockMD blockMD, BlockPos blockPos) {
        IBlockState blockState = blockMD.getBlockState();
        try {
            blockState = blockMD.getBlock().getActualState(blockState, (IBlockAccess)chunkMD.getWorld(), blockPos);
            blockState = blockMD.getBlock().getExtendedState(blockState, (IBlockAccess)chunkMD.getWorld(), blockPos);
        }
        catch (Exception exception) {
            // empty catch block
        }
        return FMLClientHandler.instance().getClient().getBlockColors().colorMultiplier(blockState, (IBlockAccess)chunkMD.getWorld(), blockPos, blockMD.getBlock().getRenderLayer().ordinal());
    }
}

