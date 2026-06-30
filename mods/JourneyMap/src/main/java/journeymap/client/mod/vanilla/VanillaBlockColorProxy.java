/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  javax.annotation.Nonnull
 *  javax.annotation.Nullable
 *  net.minecraft.block.state.IBlockState
 *  net.minecraft.client.renderer.color.BlockColors
 *  net.minecraft.util.math.BlockPos
 *  net.minecraft.world.IBlockAccess
 *  net.minecraftforge.fluids.IFluidBlock
 *  net.minecraftforge.fml.client.FMLClientHandler
 *  org.apache.logging.log4j.Logger
 */
package journeymap.client.mod.vanilla;

import java.util.Collection;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import journeymap.client.cartography.color.ColorManager;
import journeymap.client.cartography.color.ColoredSprite;
import journeymap.client.cartography.color.RGB;
import journeymap.client.mod.IBlockColorProxy;
import journeymap.client.model.BlockFlag;
import journeymap.client.model.BlockMD;
import journeymap.client.model.ChunkMD;
import journeymap.client.properties.CoreProperties;
import journeymap.client.world.JmBlockAccess;
import journeymap.common.Journeymap;
import journeymap.common.log.LogFormatter;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.color.BlockColors;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraftforge.fluids.IFluidBlock;
import net.minecraftforge.fml.client.FMLClientHandler;
import org.apache.logging.log4j.Logger;

public class VanillaBlockColorProxy
implements IBlockColorProxy {
    static Logger logger = Journeymap.getLogger();
    private final BlockColors blockColors = FMLClientHandler.instance().getClient().getBlockColors();
    private boolean blendFoliage;
    private boolean blendGrass;
    private boolean blendWater;

    public VanillaBlockColorProxy() {
        CoreProperties coreProperties = Journeymap.getClient().getCoreProperties();
        this.blendFoliage = coreProperties.mapBlendFoliage.get();
        this.blendGrass = coreProperties.mapBlendGrass.get();
        this.blendWater = coreProperties.mapBlendWater.get();
    }

    @Override
    public int deriveBlockColor(BlockMD blockMD, @Nullable ChunkMD chunkMD, @Nullable BlockPos blockPos) {
        IBlockState blockState = blockMD.getBlockState();
        try {
            if (blockState.getBlock() instanceof IFluidBlock) {
                return VanillaBlockColorProxy.getSpriteColor(blockMD, 0xBCBCBC, chunkMD, blockPos);
            }
            Integer color = VanillaBlockColorProxy.getSpriteColor(blockMD, null, chunkMD, blockPos);
            if (color == null) {
                color = VanillaBlockColorProxy.setBlockColorToMaterial(blockMD);
            }
            return color;
        }
        catch (Throwable e) {
            logger.error("Error deriving color for " + blockMD + ": " + LogFormatter.toPartialString(e));
            blockMD.addFlags(BlockFlag.Error);
            return VanillaBlockColorProxy.setBlockColorToMaterial(blockMD);
        }
    }

    @Override
    public int getBlockColor(ChunkMD chunkMD, BlockMD blockMD, BlockPos blockPos) {
        int result = blockMD.getTextureColor(chunkMD, blockPos);
        if (blockMD.isFoliage()) {
            result = RGB.adjustBrightness(result, 0.8f);
        } else if (blockMD.isFluid()) {
            return RGB.multiply(result, ((IFluidBlock)blockMD.getBlock()).getFluid().getColor());
        }
        return RGB.multiply(result, this.getColorMultiplier(chunkMD, blockMD, blockPos, blockMD.getBlock().getRenderLayer().ordinal()));
    }

    public int getColorMultiplier(ChunkMD chunkMD, BlockMD blockMD, BlockPos blockPos, int tintIndex) {
        if (!this.blendGrass && blockMD.isGrass()) {
            return chunkMD.getBiome(blockPos).getGrassColorAtPos(blockPos);
        }
        if (!this.blendFoliage && blockMD.isFoliage()) {
            return chunkMD.getBiome(blockPos).getFoliageColorAtPos(blockPos);
        }
        if (!this.blendWater && blockMD.isWater()) {
            return chunkMD.getBiome(blockPos).getWaterColorMultiplier();
        }
        return this.blockColors.colorMultiplier(blockMD.getBlockState(), (IBlockAccess)JmBlockAccess.INSTANCE, blockPos, tintIndex);
    }

    public static Integer getSpriteColor(@Nonnull BlockMD blockMD, @Nullable Integer defaultColor, @Nullable ChunkMD chunkMD, @Nullable BlockPos blockPos) {
        Collection<ColoredSprite> sprites = blockMD.getBlockSpritesProxy().getSprites(blockMD, chunkMD, blockPos);
        float[] rgba = ColorManager.INSTANCE.getAverageColor(sprites);
        if (rgba != null) {
            return RGB.toInteger(rgba);
        }
        return defaultColor;
    }

    public static int setBlockColorToError(BlockMD blockMD) {
        blockMD.setAlpha(0.0f);
        blockMD.addFlags(BlockFlag.Ignore, BlockFlag.Error);
        blockMD.setColor(-1);
        return -1;
    }

    public static int setBlockColorToMaterial(BlockMD blockMD) {
        try {
            blockMD.setAlpha(1.0f);
            blockMD.addFlags(BlockFlag.Ignore);
            return blockMD.setColor(blockMD.getBlockState().getMaterial().getMaterialMapColor().colorValue);
        }
        catch (Exception e) {
            logger.warn(String.format("Failed to use MaterialMapColor, marking as error: %s", blockMD));
            return VanillaBlockColorProxy.setBlockColorToError(blockMD);
        }
    }
}

