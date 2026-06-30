/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  bib
 *  blk
 *  bus
 *  rk
 */
package net.optifine.shaders.gui;

import net.optifine.shaders.config.ShaderOption;
import net.optifine.shaders.gui.GuiButtonShaderOption;
import net.optifine.shaders.gui.GuiShaderOptions;

public class GuiSliderShaderOption
extends GuiButtonShaderOption {
    private float sliderValue = 1.0f;
    public boolean dragging;
    private ShaderOption shaderOption = null;

    public GuiSliderShaderOption(int buttonId, int x, int y, int w, int h2, ShaderOption shaderOption, String text) {
        super(buttonId, x, y, w, h2, shaderOption, text);
        this.shaderOption = shaderOption;
        this.sliderValue = shaderOption.getIndexNormalized();
        this.j = GuiShaderOptions.getButtonText(shaderOption, this.f);
    }

    protected int a(boolean mouseOver) {
        return 0;
    }

    protected void a(bib mc, int mouseX, int mouseY) {
        if (this.m) {
            if (this.dragging && !blk.s()) {
                this.sliderValue = (float)(mouseX - (this.h + 4)) / (float)(this.f - 8);
                this.sliderValue = rk.a((float)this.sliderValue, (float)0.0f, (float)1.0f);
                this.shaderOption.setIndexNormalized(this.sliderValue);
                this.sliderValue = this.shaderOption.getIndexNormalized();
                this.j = GuiShaderOptions.getButtonText(this.shaderOption, this.f);
            }
            mc.N().a(a);
            bus.c((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
            this.b(this.h + (int)(this.sliderValue * (float)(this.f - 8)), this.i, 0, 66, 4, 20);
            this.b(this.h + (int)(this.sliderValue * (float)(this.f - 8)) + 4, this.i, 196, 66, 4, 20);
        }
    }

    public boolean b(bib mc, int mouseX, int mouseY) {
        if (super.b(mc, mouseX, mouseY)) {
            this.sliderValue = (float)(mouseX - (this.h + 4)) / (float)(this.f - 8);
            this.sliderValue = rk.a((float)this.sliderValue, (float)0.0f, (float)1.0f);
            this.shaderOption.setIndexNormalized(this.sliderValue);
            this.j = GuiShaderOptions.getButtonText(this.shaderOption, this.f);
            this.dragging = true;
            return true;
        }
        return false;
    }

    public void a(int mouseX, int mouseY) {
        this.dragging = false;
    }

    @Override
    public void valueChanged() {
        this.sliderValue = this.shaderOption.getIndexNormalized();
    }

    @Override
    public boolean isSwitchable() {
        return false;
    }
}

