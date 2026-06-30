/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.renderer.GlStateManager
 *  net.minecraft.util.ResourceLocation
 *  org.lwjgl.opengl.GL11
 */
package journeymap.client.model;

import java.awt.Color;
import journeymap.client.Constants;
import journeymap.client.cartography.color.RGB;
import journeymap.client.render.texture.TextureCache;
import journeymap.client.render.texture.TextureImpl;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

public class GridSpec {
    public final Style style;
    public final float red;
    public final float green;
    public final float blue;
    public final float alpha;
    private int colorX = -1;
    private int colorY = -1;
    private transient TextureImpl texture = null;

    public GridSpec(Style style, Color color, float alpha) {
        this.style = style;
        float[] rgb = RGB.floats(color.getRGB());
        this.red = rgb[0];
        this.green = rgb[1];
        this.blue = rgb[2];
        if (alpha < 0.0f) {
            alpha = 0.0f;
        }
        while (alpha > 1.0f) {
            alpha /= 100.0f;
        }
        this.alpha = alpha;
        assert (alpha <= 1.0f);
    }

    public GridSpec(Style style, float red, float green, float blue, float alpha) {
        this.style = style;
        this.red = red;
        this.green = green;
        this.blue = blue;
        this.alpha = alpha;
        assert (alpha <= 1.0f);
    }

    public GridSpec setColorCoords(int x, int y) {
        this.colorX = x;
        this.colorY = y;
        return this;
    }

    public void beginTexture(int textureFilter, int textureWrap, float mapAlpha) {
        GlStateManager.func_179147_l();
        GlStateManager.func_179120_a((int)770, (int)771, (int)1, (int)0);
        GlStateManager.func_179098_w();
        GlStateManager.func_179144_i((int)this.getTexture().func_110552_b());
        GlStateManager.func_179131_c((float)this.red, (float)this.green, (float)this.blue, (float)(this.alpha * mapAlpha));
        GL11.glTexParameteri((int)3553, (int)10241, (int)textureFilter);
        GL11.glTexParameteri((int)3553, (int)10240, (int)textureFilter);
        GL11.glTexParameteri((int)3553, (int)10242, (int)textureWrap);
        GL11.glTexParameteri((int)3553, (int)10243, (int)textureWrap);
    }

    public TextureImpl getTexture() {
        if (this.texture == null || this.texture.isDefunct()) {
            this.texture = TextureCache.getTexture(this.style.textureLocation);
        }
        return this.texture;
    }

    public GridSpec clone() {
        return new GridSpec(this.style, this.red, this.green, this.blue, this.alpha).setColorCoords(this.colorX, this.colorY);
    }

    public void finishTexture() {
        GlStateManager.func_179131_c((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
        GlStateManager.func_179082_a((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
    }

    public Integer getColor() {
        return RGB.toInteger(this.red, this.green, this.blue);
    }

    public int getColorX() {
        return this.colorX;
    }

    public int getColorY() {
        return this.colorY;
    }

    public static enum Style {
        Squares("jm.common.grid_style_squares", TextureCache.GridSquares),
        SquaresWithRegion("jm.common.grid_style_squares_region", TextureCache.GridRegionSquares),
        GridRegion("jm.common.grid_style_region", TextureCache.GridRegion),
        Dots("jm.common.grid_style_dots", TextureCache.GridDots),
        Checkers("jm.common.grid_style_checkers", TextureCache.GridCheckers);

        private final String key;
        private final ResourceLocation textureLocation;

        private Style(String key, ResourceLocation textureLocation) {
            this.key = key;
            this.textureLocation = textureLocation;
        }

        public String displayName() {
            return Constants.getString(this.key);
        }
    }
}

