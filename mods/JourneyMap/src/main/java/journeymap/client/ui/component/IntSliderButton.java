/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.gui.FontRenderer
 *  net.minecraft.client.renderer.GlStateManager
 *  net.minecraft.util.ResourceLocation
 *  net.minecraftforge.fml.client.config.GuiUtils
 */
package journeymap.client.ui.component;

import journeymap.client.ui.component.Button;
import journeymap.client.ui.component.IConfigFieldHolder;
import journeymap.common.properties.config.IntegerField;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.client.config.GuiUtils;

public class IntSliderButton
extends Button
implements IConfigFieldHolder<IntegerField> {
    public String prefix = "";
    public boolean dragging = false;
    public int minValue = 0;
    public int maxValue = 0;
    public String suffix = "";
    public boolean drawString = true;
    IntegerField field;

    public IntSliderButton(IntegerField field, String prefix, String suf) {
        this(field, prefix, suf, field.getMinValue(), field.getMaxValue(), true);
    }

    public IntSliderButton(IntegerField field, String prefix, String suf, int minVal, int maxVal, boolean drawStr) {
        super(prefix);
        this.minValue = minVal;
        this.maxValue = maxVal;
        this.prefix = prefix;
        this.suffix = suf;
        this.field = field;
        this.setValue(field.get());
        this.disabledLabelColor = 0x404040;
    }

    public int getHoverState(boolean par1) {
        return 0;
    }

    protected void mouseDragged(Minecraft par1Minecraft, int par2, int par3) {
        if (this.visible && this.isEnabled()) {
            if (this.dragging) {
                this.setSliderValue((float)(par2 - (this.x + 4)) / (float)(this.width - 8));
            }
            int k = this.getHoverState(this.isEnabled());
            if (this.isEnabled() || this.dragging) {
                GlStateManager.color((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
                double sliderValue = this.getSliderValue();
                GuiUtils.drawContinuousTexturedBox((ResourceLocation)BUTTON_TEXTURES, (int)(this.x + 1 + (int)(sliderValue * (double)(this.width - 10))), (int)(this.y + 1), (int)0, (int)66, (int)8, (int)(this.height - 2), (int)200, (int)20, (int)2, (int)3, (int)2, (int)2, (float)this.zLevel);
            }
        }
    }

    @Override
    public boolean mousePressed(Minecraft mc, int mouseX, int mouseY) {
        if (super.mousePressed(mc, mouseX, mouseY, false)) {
            this.setSliderValue((float)(mouseX - (this.x + 4)) / (float)(this.width - 8));
            this.dragging = true;
            return this.checkClickListeners();
        }
        return false;
    }

    public double getSliderValue() {
        return ((double)this.field.get().intValue() - (double)this.minValue * 1.0) / (double)(this.maxValue - this.minValue);
    }

    public void setSliderValue(double sliderValue) {
        if (sliderValue < 0.0) {
            sliderValue = 0.0;
        }
        if (sliderValue > 1.0) {
            sliderValue = 1.0;
        }
        int intVal = (int)Math.round(sliderValue * (double)(this.maxValue - this.minValue) + (double)this.minValue);
        this.setValue(intVal);
    }

    @Override
    public void updateLabel() {
        if (this.drawString) {
            this.displayString = this.prefix + this.field.get() + this.suffix;
        }
    }

    public void mouseReleased(int par1, int par2) {
        if (this.dragging) {
            this.dragging = false;
            this.field.save();
            this.checkClickListeners();
        }
    }

    @Override
    public int getFitWidth(FontRenderer fr) {
        int max = fr.getStringWidth(this.prefix + this.minValue + this.suffix);
        max = Math.max(max, fr.getStringWidth(this.prefix + this.maxValue + this.suffix));
        return max + this.WIDTH_PAD;
    }

    @Override
    public boolean keyTyped(char c, int i) {
        if (this.isEnabled()) {
            if (i == 203 || i == 208 || i == 74) {
                this.setValue(Math.max(this.minValue, this.getValue() - 1));
                return true;
            }
            if (i == 205 || i == 200 || i == 78) {
                this.setValue(Math.min(this.maxValue, this.getValue() + 1));
                return true;
            }
        }
        return false;
    }

    public int getValue() {
        return this.field.get();
    }

    public void setValue(int value) {
        value = Math.min(value, this.maxValue);
        value = Math.max(value, this.minValue);
        if (this.field.get() != value) {
            this.field.set(value);
            if (!this.dragging) {
                this.field.save();
            }
        }
        this.updateLabel();
    }

    @Override
    public void refresh() {
        this.setValue(this.field.get());
    }

    @Override
    public IntegerField getConfigField() {
        return this.field;
    }
}

