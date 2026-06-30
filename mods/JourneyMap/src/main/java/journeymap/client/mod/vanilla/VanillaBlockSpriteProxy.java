/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  javax.annotation.Nullable
 *  net.minecraft.block.Block
 *  net.minecraft.block.BlockDoublePlant
 *  net.minecraft.block.BlockDoublePlant$EnumBlockHalf
 *  net.minecraft.block.properties.IProperty
 *  net.minecraft.block.state.IBlockState
 *  net.minecraft.client.renderer.BlockModelShapes
 *  net.minecraft.client.renderer.block.model.BakedQuad
 *  net.minecraft.client.renderer.block.model.IBakedModel
 *  net.minecraft.client.renderer.texture.TextureAtlasSprite
 *  net.minecraft.client.renderer.texture.TextureMap
 *  net.minecraft.util.BlockRenderLayer
 *  net.minecraft.util.EnumFacing
 *  net.minecraft.util.ResourceLocation
 *  net.minecraft.util.math.BlockPos
 *  net.minecraft.world.IBlockAccess
 *  net.minecraftforge.client.ForgeHooksClient
 *  net.minecraftforge.client.MinecraftForgeClient
 *  net.minecraftforge.fluids.IFluidBlock
 *  net.minecraftforge.fml.client.FMLClientHandler
 *  org.apache.logging.log4j.Logger
 */
package journeymap.client.mod.vanilla;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import javax.annotation.Nullable;
import journeymap.client.cartography.color.ColoredSprite;
import journeymap.client.mod.IBlockSpritesProxy;
import journeymap.client.model.BlockMD;
import journeymap.client.model.ChunkMD;
import journeymap.common.Journeymap;
import journeymap.common.log.LogFormatter;
import net.minecraft.block.Block;
import net.minecraft.block.BlockDoublePlant;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.BlockModelShapes;
import net.minecraft.client.renderer.block.model.BakedQuad;
import net.minecraft.client.renderer.block.model.IBakedModel;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraftforge.client.ForgeHooksClient;
import net.minecraftforge.client.MinecraftForgeClient;
import net.minecraftforge.fluids.IFluidBlock;
import net.minecraftforge.fml.client.FMLClientHandler;
import org.apache.logging.log4j.Logger;

public class VanillaBlockSpriteProxy
implements IBlockSpritesProxy {
    private static Logger logger = Journeymap.getLogger();
    BlockModelShapes bms = FMLClientHandler.instance().getClient().func_175602_ab().func_175023_a();

    @Override
    @Nullable
    public Collection<ColoredSprite> getSprites(BlockMD blockMD, @Nullable ChunkMD chunkMD, @Nullable BlockPos blockPos) {
        IBlockState blockState = blockMD.getBlockState();
        Block block = blockState.func_177230_c();
        if (block instanceof IFluidBlock) {
            ResourceLocation loc = ((IFluidBlock)block).getFluid().getStill();
            TextureAtlasSprite tas = FMLClientHandler.instance().getClient().func_147117_R().func_110572_b(loc.toString());
            return Collections.singletonList(new ColoredSprite(tas, null));
        }
        if (blockState.func_177227_a().contains(BlockDoublePlant.field_176492_b)) {
            blockState = blockState.func_177226_a((IProperty)BlockDoublePlant.field_176492_b, (Comparable)BlockDoublePlant.EnumBlockHalf.UPPER);
        }
        HashMap<String, ColoredSprite> map = new HashMap<String, ColoredSprite>();
        try {
            IBakedModel model = this.bms.func_178125_b(blockState);
            block2: for (IBlockState state : new IBlockState[]{blockState, null}) {
                for (EnumFacing facing : new EnumFacing[]{EnumFacing.UP, null}) {
                    if (this.getSprites(blockMD, model, state, facing, map, chunkMD, blockPos)) break block2;
                }
            }
            if (map.isEmpty()) {
                TextureAtlasSprite defaultSprite = this.bms.func_178122_a(blockState);
                if (defaultSprite != null) {
                    map.put(defaultSprite.func_94215_i(), new ColoredSprite(defaultSprite, null));
                    if (!blockMD.isVanillaBlock() && logger.isDebugEnabled()) {
                        logger.debug(String.format("Resorted to using BlockModelStates.getTexture() to use %s as color for %s", defaultSprite.func_94215_i(), blockState));
                    }
                } else {
                    logger.warn(String.format("Unable to get any texture to use as color for %s", blockState));
                }
            }
        }
        catch (Exception e) {
            logger.error("Unexpected error during getSprites(): " + LogFormatter.toPartialString(e));
        }
        return map.values();
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    private boolean getSprites(BlockMD blockMD, IBakedModel model, @Nullable IBlockState blockState, @Nullable EnumFacing facing, HashMap<String, ColoredSprite> map, @Nullable ChunkMD chunkMD, @Nullable BlockPos blockPos) {
        BlockRenderLayer originalLayer = MinecraftForgeClient.getRenderLayer();
        if (blockPos != null && chunkMD != null && chunkMD.getWorld() != null) {
            try {
                blockState = blockMD.getBlock().func_176221_a(blockState, (IBlockAccess)chunkMD.getWorld(), blockPos);
                model = this.bms.func_178125_b(blockState);
                blockState = blockMD.getBlock().getExtendedState(blockState, (IBlockAccess)chunkMD.getWorld(), blockPos);
            }
            catch (Exception exception) {
                // empty catch block
            }
        }
        boolean success = false;
        try {
            for (BlockRenderLayer layer : BlockRenderLayer.values()) {
                if (!blockMD.getBlock().canRenderInLayer(blockState, layer)) continue;
                ForgeHooksClient.setRenderLayer((BlockRenderLayer)layer);
                List quads = model.func_188616_a(blockState, facing, 0L);
                if (!this.addSprites(map, quads)) continue;
                if (!blockMD.isVanillaBlock() && logger.isDebugEnabled()) {
                    logger.debug(String.format("Success during [%s] %s.getQuads(%s, %s, %s)", layer, model.getClass(), blockState, facing, 0));
                }
                success = true;
            }
        }
        catch (Exception e) {
            if (logger.isDebugEnabled()) {
                logger.error(String.format("Error during [%s] %s.getQuads(%s, %s, %s): %s", MinecraftForgeClient.getRenderLayer(), model.getClass(), blockState, facing, 0, LogFormatter.toPartialString(e)));
            }
        }
        finally {
            ForgeHooksClient.setRenderLayer((BlockRenderLayer)originalLayer);
        }
        return success;
    }

    public boolean addSprites(HashMap<String, ColoredSprite> sprites, List<BakedQuad> quads) {
        if (quads == null || quads.isEmpty()) {
            return false;
        }
        if (quads.size() > 1) {
            HashSet<BakedQuad> culled = new HashSet<BakedQuad>(quads.size());
            culled.addAll(quads);
            quads = new ArrayList<BakedQuad>(culled);
        }
        boolean added = false;
        for (BakedQuad quad : quads) {
            ResourceLocation resourceLocation;
            String iconName;
            TextureAtlasSprite sprite = quad.func_187508_a();
            if (sprite == null || sprites.containsKey(iconName = quad.func_187508_a().func_94215_i()) || (resourceLocation = new ResourceLocation(iconName)).equals((Object)TextureMap.field_174945_f)) continue;
            sprites.put(iconName, new ColoredSprite(quad));
            added = true;
        }
        return added;
    }
}

