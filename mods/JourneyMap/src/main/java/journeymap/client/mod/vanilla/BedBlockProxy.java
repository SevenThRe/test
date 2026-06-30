/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  javax.annotation.Nullable
 *  net.minecraft.block.BlockBed
 *  net.minecraft.block.BlockBed$EnumPartType
 *  net.minecraft.block.properties.IProperty
 *  net.minecraft.tileentity.TileEntity
 *  net.minecraft.tileentity.TileEntityBed
 *  net.minecraft.util.math.BlockPos
 */
package journeymap.client.mod.vanilla;

import javax.annotation.Nullable;
import journeymap.client.cartography.color.RGB;
import journeymap.client.mod.IBlockColorProxy;
import journeymap.client.mod.ModBlockDelegate;
import journeymap.client.model.BlockMD;
import journeymap.client.model.ChunkMD;
import journeymap.client.world.JmBlockAccess;
import net.minecraft.block.BlockBed;
import net.minecraft.block.properties.IProperty;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityBed;
import net.minecraft.util.math.BlockPos;

public enum BedBlockProxy implements IBlockColorProxy
{
    INSTANCE;


    @Override
    public int deriveBlockColor(BlockMD blockMD, @Nullable ChunkMD chunkMD, @Nullable BlockPos blockPos) {
        return ModBlockDelegate.INSTANCE.getDefaultBlockColorProxy().deriveBlockColor(blockMD, chunkMD, blockPos);
    }

    @Override
    public int getBlockColor(ChunkMD chunkMD, BlockMD blockMD, BlockPos blockPos) {
        TileEntity tileentity;
        if (blockMD.getBlock() instanceof BlockBed && (tileentity = JmBlockAccess.INSTANCE.func_175625_s(blockPos)) instanceof TileEntityBed) {
            int bedColor = ((TileEntityBed)tileentity).func_193048_a().func_193350_e();
            if (blockMD.getBlockState().func_177229_b((IProperty)BlockBed.field_176472_a) == BlockBed.EnumPartType.FOOT) {
                return RGB.multiply(0xCCCCCC, bedColor);
            }
            return RGB.multiply(0xFFFFFF, bedColor);
        }
        return ModBlockDelegate.INSTANCE.getDefaultBlockColorProxy().getBlockColor(chunkMD, blockMD, blockPos);
    }
}

