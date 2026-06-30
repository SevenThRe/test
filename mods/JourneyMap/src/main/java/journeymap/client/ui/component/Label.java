/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.gui.FontRenderer
 */
package journeymap.client.ui.component;

import journeymap.client.Constants;
import journeymap.client.render.draw.DrawUtil;
import journeymap.client.ui.component.Button;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;

public class Label
extends Button {
    private DrawUtil.HAlign hAlign = DrawUtil.HAlign.Left;

    public Label(int width, String key, Object ... labelArgs) {
        super(Constants.getString(key, labelArgs));
        this.setDrawBackground(false);
        this.setDrawFrame(false);
        this.setEnabled(false);
        this.setLabelColors(0xC0C0C0, 0xC0C0C0, 0xC0C0C0);
        this.setWidth(width);
    }

    @Override
    public int getFitWidth(FontRenderer fr) {
        return this.fontRenderer.getStringWidth(this.displayString);
    }

    @Override
    public void fitWidth(FontRenderer fr) {
    }

    public void setHAlign(DrawUtil.HAlign hAlign) {
        this.hAlign = hAlign;
    }

    @Override
    public void drawButton(Minecraft minecraft, int mouseX, int mouseY, float ticks) {
        int labelX;
        switch (this.hAlign) {
            case Left: {
                labelX = this.getRightX();
                break;
            }
            case Right: {
                labelX = this.getX();
                break;
            }
            default: {
                labelX = this.getCenterX();
            }
        }
        DrawUtil.drawLabel(this.displayString, labelX, this.getMiddleY(), this.hAlign, DrawUtil.VAlign.Middle, null, 0.0f, this.labelColor, 1.0f, 1.0, this.drawLabelShadow);
    }
}

