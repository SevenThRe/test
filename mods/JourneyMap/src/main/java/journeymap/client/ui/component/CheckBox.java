/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.gui.FontRenderer
 *  net.minecraft.util.ResourceLocation
 *  net.minecraftforge.fml.client.config.GuiUtils
 */
package journeymap.client.ui.component;

import journeymap.client.ui.component.BooleanPropertyButton;
import journeymap.common.properties.config.BooleanField;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.client.config.GuiUtils;

public class CheckBox
extends BooleanPropertyButton {
    public int boxWidth = 11;
    String glyph = "\u2714";

    public CheckBox(String displayString, boolean checked) {
        this(displayString, null);
        this.toggled = checked;
    }

    public CheckBox(String displayString, BooleanField field) {
        super(displayString, displayString, field);
        this.setHeight(this.fontRenderer.field_78288_b + 2);
        this.func_175211_a(this.getFitWidth(this.fontRenderer));
    }

    @Override
    public int getFitWidth(FontRenderer fr) {
        return super.getFitWidth(fr) + this.boxWidth + 2;
    }

    @Override
    public void func_191745_a(Minecraft mc, int mouseX, int mouseY, float ticks) {
        if (this.field_146125_m) {
            this.setHovered(this.isEnabled() && mouseX >= this.field_146128_h && mouseY >= this.field_146129_i && mouseX < this.field_146128_h + this.field_146120_f && mouseY < this.field_146129_i + this.field_146121_g);
            int yoffset = (this.field_146121_g - this.boxWidth) / 2;
            GuiUtils.drawContinuousTexturedBox((ResourceLocation)field_146122_a, (int)this.field_146128_h, (int)(this.field_146129_i + yoffset), (int)0, (int)46, (int)this.boxWidth, (int)this.boxWidth, (int)200, (int)20, (int)2, (int)3, (int)2, (int)2, (float)this.field_73735_i);
            this.func_146119_b(mc, mouseX, mouseY);
            int color = 0xE0E0E0;
            if (this.isHovered()) {
                color = 0xFFFFA0;
            } else if (!this.isEnabled()) {
                color = 0x404040;
            } else if (this.labelColor != null) {
                color = this.labelColor;
            } else if (this.packedFGColour != 0) {
                color = this.packedFGColour;
            }
            int labelPad = 4;
            if (this.toggled.booleanValue()) {
                this.func_73732_a(this.fontRenderer, this.glyph, this.field_146128_h + this.boxWidth / 2 + 1, this.field_146129_i + 1 + yoffset, color);
            }
            this.func_73731_b(this.fontRenderer, this.field_146126_j, this.field_146128_h + this.boxWidth + labelPad, this.field_146129_i + 2 + yoffset, color);
        }
    }

    @Override
    public boolean func_146116_c(Minecraft p_146116_1_, int mouseX, int mouseY) {
        if (this.isEnabled() && this.field_146125_m && mouseX >= this.field_146128_h && mouseY >= this.field_146129_i && mouseX < this.field_146128_h + this.field_146120_f && mouseY < this.field_146129_i + this.field_146121_g) {
            this.toggle();
            return this.checkClickListeners();
        }
        return false;
    }

    @Override
    public boolean keyTyped(char c, int i) {
        if (this.isEnabled() && i == 57) {
            this.toggle();
            return true;
        }
        return false;
    }
}

