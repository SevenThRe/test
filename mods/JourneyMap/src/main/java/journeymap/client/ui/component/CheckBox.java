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
        this.setHeight(this.fontRenderer.FONT_HEIGHT + 2);
        this.setWidth(this.getFitWidth(this.fontRenderer));
    }

    @Override
    public int getFitWidth(FontRenderer fr) {
        return super.getFitWidth(fr) + this.boxWidth + 2;
    }

    @Override
    public void drawButton(Minecraft mc, int mouseX, int mouseY, float ticks) {
        if (this.visible) {
            this.setHovered(this.isEnabled() && mouseX >= this.x && mouseY >= this.y && mouseX < this.x + this.width && mouseY < this.y + this.height);
            int yoffset = (this.height - this.boxWidth) / 2;
            GuiUtils.drawContinuousTexturedBox((ResourceLocation)BUTTON_TEXTURES, (int)this.x, (int)(this.y + yoffset), (int)0, (int)46, (int)this.boxWidth, (int)this.boxWidth, (int)200, (int)20, (int)2, (int)3, (int)2, (int)2, (float)this.zLevel);
            this.mouseDragged(mc, mouseX, mouseY);
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
                this.drawCenteredString(this.fontRenderer, this.glyph, this.x + this.boxWidth / 2 + 1, this.y + 1 + yoffset, color);
            }
            this.drawString(this.fontRenderer, this.displayString, this.x + this.boxWidth + labelPad, this.y + 2 + yoffset, color);
        }
    }

    @Override
    public boolean mousePressed(Minecraft p_146116_1_, int mouseX, int mouseY) {
        if (this.isEnabled() && this.visible && mouseX >= this.x && mouseY >= this.y && mouseX < this.x + this.width && mouseY < this.y + this.height) {
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

