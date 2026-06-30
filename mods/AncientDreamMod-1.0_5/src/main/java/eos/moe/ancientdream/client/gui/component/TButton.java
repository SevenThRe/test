/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.util.ResourceLocation
 */
package eos.moe.ancientdream.client.gui.component;

import eos.moe.ancientdream.client.gui.component.Component;
import eos.moe.ancientdream.client.utils.RenderUtils;
import eos.moe.ancientdream.client.utils.Utils;
import java.util.function.Consumer;
import net.minecraft.util.ResourceLocation;

public class TButton
implements Component {
    private ResourceLocation texture;
    private ResourceLocation hoveredTexture;
    private Consumer<TButton> consumer;
    private final float x;
    private final float y;
    private final float w;
    private final float h;
    private final float u;
    private final float v;
    private float u1;
    private float v1;
    private boolean useU1V1;
    private float textureWidth;
    private float textureHeight;
    private boolean enable = true;

    public TButton(float x, float y, float u, float v, float u1, float v1, float w, float h, float textureWidth, float textureHeight, Consumer<TButton> consumer) {
        this(x, y, u, v, w, h, textureWidth, textureHeight, consumer);
        this.u1 = u1;
        this.v1 = v1;
        this.useU1V1 = true;
    }

    public TButton(float x, float y, float u, float v, float w, float h, float textureWidth, float textureHeight, Consumer<TButton> consumer) {
        this.x = x;
        this.y = y;
        this.u = u;
        this.v = v;
        this.w = w;
        this.h = h;
        this.textureWidth = textureWidth;
        this.textureHeight = textureHeight;
        this.consumer = consumer;
    }

    public TButton(float x, float y, float w, float h, Consumer<TButton> consumer) {
        this(x, y, 0.0f, 0.0f, w, h, w, h, consumer);
    }

    @Override
    public void draw(float mouseX, float mouseY) {
        if (!this.enable) {
            return;
        }
        if ((this.hoveredTexture != null || this.useU1V1) && this.isHovered(mouseX, mouseY)) {
            if (this.useU1V1) {
                RenderUtils.drawTexture((double)this.x, (double)this.y, (double)this.u1, (double)this.v1, (double)this.w, (double)this.h, (double)this.textureWidth, (double)this.textureHeight, this.texture);
            } else {
                RenderUtils.drawTexture((double)this.x, (double)this.y, (double)this.u, (double)this.v, (double)this.w, (double)this.h, (double)this.textureWidth, (double)this.textureHeight, this.hoveredTexture);
            }
        } else {
            RenderUtils.drawTexture((double)this.x, (double)this.y, (double)this.u, (double)this.v, (double)this.w, (double)this.h, (double)this.textureWidth, (double)this.textureHeight, this.texture);
        }
    }

    @Override
    public void onClick(float mouseX, float mouseY) {
        if (this.enable && this.isHovered(mouseX, mouseY)) {
            this.consumer.accept(this);
        }
    }

    @Override
    public boolean isHovered(float mouseX, float mouseY) {
        return Utils.onArea(this.x, this.y, this.w, this.h, mouseX, mouseY);
    }

    @Override
    public void setEnable(boolean bool) {
        this.enable = bool;
    }

    public TButton setTexture(ResourceLocation texture) {
        this.texture = texture;
        return this;
    }

    public TButton setHoveredTexture(ResourceLocation hoveredTexture) {
        this.hoveredTexture = hoveredTexture;
        return this;
    }

    public TButton setTexture(String texture) {
        this.texture = new ResourceLocation("ancientdream", texture);
        return this;
    }

    public TButton setHoveredTexture(String hoveredTexture) {
        this.hoveredTexture = new ResourceLocation("ancientdream", hoveredTexture);
        return this;
    }

    public String toString() {
        return "TButton{x=" + this.x + ", y=" + this.y + ", w=" + this.w + ", h=" + this.h + '}';
    }
}

