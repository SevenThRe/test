/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.gui.FontRenderer
 */
package journeymap.client.ui.component;

import journeymap.client.ui.component.Button;
import journeymap.client.ui.component.TextBox;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;

public class TextBoxButton
extends Button {
    protected TextBox textBox;

    public TextBoxButton(String text) {
        super(text);
    }

    public TextBoxButton(Object text, FontRenderer fontRenderer, int width, int height) {
        this(text, fontRenderer, width, height, false, false);
    }

    public TextBoxButton(Object text, FontRenderer fontRenderer, int width, int height, boolean isNumeric, boolean negative) {
        super(text.toString());
        this.textBox = new TextBox(text, fontRenderer, width, height - 4, isNumeric, negative);
    }

    public String getText() {
        return this.textBox.getText();
    }

    public String getSelectedText() {
        return this.textBox.getSelectedText();
    }

    @Override
    public void drawButton(Minecraft minecraft, int mouseX, int mouseY, float partialTicks) {
        this.textBox.setMinLength(1);
        this.textBox.setX(this.getX());
        this.textBox.setY(this.getY());
        this.textBox.drawTextBox();
    }

    @Override
    public boolean mousePressed(Minecraft minecraft, int mouseX, int mouseY) {
        this.textBox.setFocused(true);
        return this.textBox.mouseClicked(mouseX, mouseY, 0);
    }

    @Override
    public boolean keyTyped(char typedChar, int keyCode) {
        return this.textBox.textboxKeyTyped(typedChar, keyCode);
    }

    @Override
    public boolean isMouseOver() {
        return this.textBox.isFocused();
    }

    @Override
    public void setVisible(boolean visible) {
        this.textBox.setVisible(visible);
        super.setVisible(visible);
    }

    @Override
    public int getCenterX() {
        return this.textBox.getCenterX();
    }

    @Override
    public int getRightX() {
        return this.textBox.getRightX();
    }

    @Override
    public int getBottomY() {
        return this.textBox.getBottomY();
    }

    @Override
    public int getMiddleY() {
        return this.textBox.getMiddleY();
    }

    @Override
    public int getWidth() {
        if (this.textBox != null) {
            return this.textBox.getWidth();
        }
        return this.width;
    }

    @Override
    public int getHeight() {
        if (this.textBox != null) {
            return this.textBox.getHeight();
        }
        return this.height;
    }

    public void setText(String text) {
        this.textBox.setText(text);
    }
}

