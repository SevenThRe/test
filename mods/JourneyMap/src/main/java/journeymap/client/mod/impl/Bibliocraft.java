/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.base.Strings
 *  javax.annotation.Nullable
 *  net.minecraft.block.state.IBlockState
 *  net.minecraft.client.renderer.texture.TextureAtlasSprite
 *  net.minecraft.util.ResourceLocation
 *  net.minecraft.util.math.BlockPos
 *  net.minecraftforge.fml.client.FMLClientHandler
 */
package journeymap.client.mod.impl;

import com.google.common.base.Strings;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import javax.annotation.Nullable;
import journeymap.client.cartography.color.ColoredSprite;
import journeymap.client.mod.IBlockSpritesProxy;
import journeymap.client.mod.IModBlockHandler;
import journeymap.client.mod.ModBlockDelegate;
import journeymap.client.mod.ModPropertyEnum;
import journeymap.client.model.BlockMD;
import journeymap.client.model.ChunkMD;
import journeymap.common.Journeymap;
import journeymap.common.log.LogFormatter;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.fml.client.FMLClientHandler;

public class Bibliocraft
implements IModBlockHandler,
IBlockSpritesProxy {
    List<ModPropertyEnum<String>> colorProperties = new ArrayList<ModPropertyEnum<String>>(2);

    public Bibliocraft() {
        this.colorProperties.add(new ModPropertyEnum<String>("jds.bibliocraft.blocks.BiblioColorBlock", "COLOR", "getWoolTextureString", String.class));
        this.colorProperties.add(new ModPropertyEnum<String>("jds.bibliocraft.blocks.BiblioWoodBlock", "WOOD_TYPE", "getTextureString", String.class));
    }

    @Override
    public void initialize(BlockMD blockMD) {
        blockMD.setBlockSpritesProxy(this);
    }

    @Override
    @Nullable
    public Collection<ColoredSprite> getSprites(BlockMD blockMD, @Nullable ChunkMD chunkMD, @Nullable BlockPos blockPos) {
        IBlockState blockState = blockMD.getBlockState();
        String textureString = (String)ModPropertyEnum.getFirstValue(this.colorProperties, blockState, new Object[0]);
        if (!Strings.isNullOrEmpty((String)textureString)) {
            try {
                ResourceLocation loc = new ResourceLocation(textureString);
                TextureAtlasSprite tas = FMLClientHandler.instance().getClient().func_147117_R().func_110572_b(loc.toString());
                return Collections.singletonList(new ColoredSprite(tas, null));
            }
            catch (Exception e) {
                Journeymap.getLogger().error(String.format("Error getting sprite from %s: %s", textureString, LogFormatter.toPartialString(e)));
            }
        }
        return ModBlockDelegate.INSTANCE.getDefaultBlockSpritesProxy().getSprites(blockMD, chunkMD, blockPos);
    }
}

