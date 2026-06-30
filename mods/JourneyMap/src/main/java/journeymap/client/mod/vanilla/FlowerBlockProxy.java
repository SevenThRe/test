/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  javax.annotation.Nullable
 *  net.minecraft.block.Block
 *  net.minecraft.block.BlockFlower
 *  net.minecraft.block.BlockFlower$EnumFlowerType
 *  net.minecraft.block.BlockFlowerPot
 *  net.minecraft.block.properties.IProperty
 *  net.minecraft.block.state.IBlockState
 *  net.minecraft.client.renderer.color.BlockColors
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemStack
 *  net.minecraft.util.math.BlockPos
 *  net.minecraftforge.fml.client.FMLClientHandler
 */
package journeymap.client.mod.vanilla;

import javax.annotation.Nullable;
import journeymap.client.mod.IBlockColorProxy;
import journeymap.client.mod.ModBlockDelegate;
import journeymap.client.model.BlockMD;
import journeymap.client.model.ChunkMD;
import journeymap.common.Journeymap;
import journeymap.common.log.LogFormatter;
import net.minecraft.block.Block;
import net.minecraft.block.BlockFlower;
import net.minecraft.block.BlockFlowerPot;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.color.BlockColors;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.fml.client.FMLClientHandler;

public enum FlowerBlockProxy implements IBlockColorProxy
{
    INSTANCE;

    boolean enabled = true;
    private final BlockColors blockColors = FMLClientHandler.instance().getClient().getBlockColors();

    @Override
    public int deriveBlockColor(BlockMD blockMD, @Nullable ChunkMD chunkMD, @Nullable BlockPos blockPos) {
        Integer color;
        if (blockMD.getBlock() instanceof BlockFlower && (color = this.getFlowerColor(blockMD.getBlockState())) != null) {
            return color;
        }
        return ModBlockDelegate.INSTANCE.getDefaultBlockColorProxy().deriveBlockColor(blockMD, chunkMD, blockPos);
    }

    @Override
    public int getBlockColor(ChunkMD chunkMD, BlockMD blockMD, BlockPos blockPos) {
        if (blockMD.getBlock() instanceof BlockFlower) {
            return blockMD.getTextureColor();
        }
        if (blockMD.getBlock() instanceof BlockFlowerPot && Journeymap.getClient().getCoreProperties().mapPlants.get().booleanValue()) {
            try {
                IBlockState blockState = blockMD.getBlockState();
                ItemStack stack = ((BlockFlowerPot)blockState.getBlock()).getItem(chunkMD.getWorld(), blockPos, blockState);
                if (stack != null) {
                    IBlockState contentBlockState = Block.getBlockFromItem((Item)stack.getItem()).getStateFromMeta(stack.getItem().getDamage(stack));
                    return BlockMD.get(contentBlockState).getTextureColor();
                }
            }
            catch (Exception e) {
                Journeymap.getLogger().error("Error checking FlowerPot: " + e, (Object)LogFormatter.toPartialString(e));
                this.enabled = false;
            }
        }
        return ModBlockDelegate.INSTANCE.getDefaultBlockColorProxy().getBlockColor(chunkMD, blockMD, blockPos);
    }

    private Integer getFlowerColor(IBlockState blockState) {
        if (blockState.getBlock() instanceof BlockFlower) {
            IProperty typeProperty = ((BlockFlower)blockState.getBlock()).getTypeProperty();
            BlockFlower.EnumFlowerType flowerType = (BlockFlower.EnumFlowerType)blockState.getProperties().get((Object)typeProperty);
            if (flowerType != null) {
                switch (flowerType) {
                    case POPPY: {
                        return 9962502;
                    }
                    case BLUE_ORCHID: {
                        return 1998518;
                    }
                    case ALLIUM: {
                        return 8735158;
                    }
                    case HOUSTONIA: {
                        return 10330535;
                    }
                    case RED_TULIP: {
                        return 9962502;
                    }
                    case ORANGE_TULIP: {
                        return 10704922;
                    }
                    case WHITE_TULIP: {
                        return 0xB0B0B0;
                    }
                    case PINK_TULIP: {
                        return 11573936;
                    }
                    case OXEYE_DAISY: {
                        return 0xB3B3B3;
                    }
                    case DANDELION: {
                        return 11514881;
                    }
                }
                return 65280;
            }
        }
        return null;
    }
}

