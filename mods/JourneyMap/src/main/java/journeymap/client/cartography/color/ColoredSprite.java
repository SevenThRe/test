/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  javax.annotation.Nullable
 *  javax.annotation.ParametersAreNonnullByDefault
 *  net.minecraft.client.renderer.block.model.BakedQuad
 *  net.minecraft.client.renderer.texture.TextureAtlasSprite
 *  net.minecraft.client.renderer.texture.TextureMap
 *  net.minecraft.util.ResourceLocation
 *  org.apache.logging.log4j.Logger
 */
package journeymap.client.cartography.color;

import java.awt.image.BufferedImage;
import javax.annotation.Nullable;
import javax.annotation.ParametersAreNonnullByDefault;
import journeymap.client.render.texture.TextureCache;
import journeymap.common.Journeymap;
import journeymap.common.log.LogFormatter;
import net.minecraft.client.renderer.block.model.BakedQuad;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.util.ResourceLocation;
import org.apache.logging.log4j.Logger;

@ParametersAreNonnullByDefault
public class ColoredSprite {
    private static Logger logger = Journeymap.getLogger();
    private final Integer color;
    private final TextureAtlasSprite sprite;

    public ColoredSprite(TextureAtlasSprite sprite, @Nullable Integer color) {
        this.sprite = sprite;
        this.color = null;
    }

    public ColoredSprite(BakedQuad quad) {
        this.sprite = quad.func_187508_a();
        this.color = null;
    }

    public String getIconName() {
        return this.sprite.func_94215_i();
    }

    @Nullable
    public Integer getColor() {
        return this.color;
    }

    public boolean hasColor() {
        return this.color != null;
    }

    @Nullable
    public BufferedImage getColoredImage() {
        try {
            ResourceLocation resourceLocation = new ResourceLocation(this.getIconName());
            if (resourceLocation.equals((Object)TextureMap.field_174945_f)) {
                return null;
            }
            BufferedImage image = this.getFrameTextureData(this.sprite);
            if (image == null || image.getWidth() == 0) {
                image = this.getImageResource(this.sprite);
            }
            if (image == null || image.getWidth() == 0) {
                return null;
            }
            return this.applyColor(image);
        }
        catch (Throwable e1) {
            if (logger.isDebugEnabled()) {
                logger.error("ColoredSprite: Error getting image for " + this.getIconName() + ": " + LogFormatter.toString(e1));
            }
            return null;
        }
    }

    private BufferedImage getFrameTextureData(TextureAtlasSprite tas) {
        try {
            int[] rgb;
            if (tas.func_110970_k() > 0 && (rgb = tas.func_147965_a(0)[0]).length > 0) {
                int width = tas.func_94211_a();
                int height = tas.func_94216_b();
                BufferedImage textureImg = new BufferedImage(width, height, 2);
                textureImg.setRGB(0, 0, width, height, rgb, 0, width);
                return textureImg;
            }
        }
        catch (Throwable t) {
            logger.error(String.format("ColoredSprite: Unable to use frame data for %s: %s", tas.func_94215_i(), t.getMessage()));
        }
        return null;
    }

    private BufferedImage getImageResource(TextureAtlasSprite tas) {
        try {
            ResourceLocation iconNameLoc = new ResourceLocation(tas.func_94215_i());
            ResourceLocation fileLoc = new ResourceLocation(iconNameLoc.func_110624_b(), "textures/" + iconNameLoc.func_110623_a() + ".png");
            return TextureCache.resolveImage(fileLoc);
        }
        catch (Throwable t) {
            logger.error(String.format("ColoredSprite: Unable to use texture file for %s: %s", tas.func_94215_i(), t.getMessage()));
            return null;
        }
    }

    private BufferedImage applyColor(BufferedImage original) {
        return original;
    }
}

