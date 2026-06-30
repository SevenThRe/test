/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.audio.SoundHandler
 *  net.minecraft.client.gui.FontRenderer
 *  net.minecraft.client.gui.GuiButton
 *  net.minecraft.client.renderer.GlStateManager
 *  net.minecraft.util.text.TextFormatting
 *  net.minecraftforge.fml.client.FMLClientHandler
 */
package journeymap.client.ui.component;

import java.awt.Color;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import journeymap.client.Constants;
import journeymap.client.render.draw.DrawUtil;
import journeymap.client.ui.component.ButtonList;
import journeymap.client.ui.component.ScrollPane;
import journeymap.common.Journeymap;
import journeymap.common.log.LogFormatter;
import net.minecraft.client.Minecraft;
import net.minecraft.client.audio.SoundHandler;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.util.text.TextFormatting;
import net.minecraftforge.fml.client.FMLClientHandler;

public class Button
extends GuiButton
implements ScrollPane.Scrollable {
    protected Integer customFrameColorLight = new Color(160, 160, 160).getRGB();
    protected Integer customFrameColorDark = new Color(120, 120, 120).getRGB();
    protected Integer customBgColor = new Color(100, 100, 100).getRGB();
    protected Integer customBgHoverColor = new Color(125, 135, 190).getRGB();
    protected Integer customBgHoverColor2 = new Color(100, 100, 100).getRGB();
    protected Integer labelColor;
    protected Integer hoverLabelColor;
    protected Integer disabledLabelColor;
    protected Integer disabledBgColor = Color.darkGray.getRGB();
    protected boolean drawFrame;
    protected boolean drawBackground;
    protected boolean drawLabelShadow = true;
    protected boolean showDisabledHoverText;
    protected boolean defaultStyle = true;
    protected int WIDTH_PAD = 12;
    protected String[] tooltip;
    protected FontRenderer fontRenderer;
    protected Rectangle2D.Double bounds;
    protected ArrayList<Function<Button, Boolean>> clickListeners;
    private int tooltipSize;

    public Button(String label) {
        this(0, 0, label);
        this.resetLabelColors();
    }

    public Button(int width, int height, String label) {
        super(0, 0, 0, width, height, label);
        this.fontRenderer = FMLClientHandler.instance().getClient().fontRenderer;
        this.clickListeners = new ArrayList(0);
        this.tooltipSize = 200;
        this.finishInit();
    }

    public void resetLabelColors() {
        this.labelColor = new Color(0xE0E0E0).getRGB();
        this.hoverLabelColor = new Color(0xFFFFA0).getRGB();
        this.disabledLabelColor = Color.lightGray.getRGB();
    }

    protected void finishInit() {
        this.setEnabled(true);
        this.setDrawButton(true);
        this.setDrawFrame(true);
        this.setDrawBackground(true);
        if (this.height == 0) {
            this.setHeight(20);
        }
        if (this.width == 0) {
            this.setWidth(200);
        }
        this.updateBounds();
    }

    protected void updateLabel() {
    }

    public boolean isActive() {
        return this.isEnabled();
    }

    @Override
    public int getFitWidth(FontRenderer fr) {
        int max = fr.getStringWidth(this.displayString);
        return max + this.WIDTH_PAD + (fr.getBidiFlag() ? (int)Math.ceil((double)max * 0.25) : 0);
    }

    public void fitWidth(FontRenderer fr) {
        this.setWidth(this.getFitWidth(fr));
    }

    @Override
    public void drawPartialScrollable(Minecraft minecraft, int x, int y, int width, int height) {
        minecraft.getTextureManager().bindTexture(BUTTON_TEXTURES);
        int k = 0;
        this.drawTexturedModalRect(x, y, 0, 46 + k * 20, width / 2, height);
        this.drawTexturedModalRect(x + width / 2, y, 200 - width / 2, 46 + k * 20, width / 2, height);
    }

    public void showDisabledOnHover(boolean show) {
        this.showDisabledHoverText = show;
    }

    public boolean isMouseOver() {
        return super.isMouseOver();
    }

    public void setMouseOver(boolean hover) {
        this.setHovered(hover);
    }

    public void playPressSound(SoundHandler soundHandler) {
        if (this.isEnabled()) {
            super.playPressSound(soundHandler);
        }
    }

    public void drawButton(Minecraft minecraft, int mouseX, int mouseY, float partialTicks) {
        if (!this.isVisible()) {
            return;
        }
        if (this.defaultStyle) {
            super.drawButton(minecraft, mouseX, mouseY, partialTicks);
        } else {
            minecraft.getTextureManager().bindTexture(BUTTON_TEXTURES);
            GlStateManager.color((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
            this.setHovered(mouseX >= this.x && mouseY >= this.y && mouseX < this.x + this.width && mouseY < this.y + this.height);
            int hoverState = this.getHoverState(this.isHovered());
            if (this.isDrawFrame()) {
                DrawUtil.drawRectangle(this.x, this.y, this.width, 1.0, this.customFrameColorLight, 1.0f);
                DrawUtil.drawRectangle(this.x, this.y, 1.0, this.height, this.customFrameColorLight, 1.0f);
                DrawUtil.drawRectangle(this.x, this.y + this.height - 1, this.width - 1, 1.0, this.customFrameColorDark, 1.0f);
                DrawUtil.drawRectangle(this.x + this.width - 1, this.y + 1, 1.0, this.height - 1, this.customFrameColorDark, 1.0f);
            }
            if (this.isDrawBackground()) {
                DrawUtil.drawRectangle(this.x + 1, this.y + 1, this.width - 2, this.height - 2, hoverState == 2 ? this.customBgHoverColor : this.customBgColor, 1.0f);
            } else if (this.isEnabled() && this.isHovered()) {
                DrawUtil.drawRectangle(this.x + 1, this.y + 1, this.width - 2, this.height - 2, this.customBgHoverColor2, 0.5f);
            }
            this.mouseDragged(minecraft, mouseX, mouseY);
            Integer varLabelColor = this.labelColor;
            if (!this.isEnabled()) {
                varLabelColor = this.disabledLabelColor;
                if (this.drawBackground) {
                    float alpha = 0.7f;
                    int widthOffset = this.width - (this.height >= 20 ? 3 : 2);
                    DrawUtil.drawRectangle(this.getX() + 1, this.getY() + 1, widthOffset, this.height - 2, this.disabledBgColor, alpha);
                }
            } else if (this.isHovered()) {
                varLabelColor = this.hoverLabelColor;
            } else if (this.labelColor != null) {
                varLabelColor = this.labelColor;
            } else if (this.packedFGColour != 0) {
                varLabelColor = this.packedFGColour;
            }
            DrawUtil.drawCenteredLabel(this.displayString, (double)this.getCenterX(), (double)this.getMiddleY(), null, 0.0f, varLabelColor, 1.0f, 1.0, this.drawLabelShadow);
        }
    }

    public void drawCenteredString(FontRenderer fontRenderer, String text, float x, float y, int color) {
        fontRenderer.drawStringWithShadow(text, x - (float)(fontRenderer.getStringWidth(text) / 2), y, color);
    }

    public void drawUnderline() {
        if (this.isVisible()) {
            DrawUtil.drawRectangle(this.x, this.y + this.height, this.width, 1.0, this.customFrameColorDark, 1.0f);
        }
    }

    public void secondaryDrawButton() {
    }

    public boolean mousePressed(Minecraft minecraft, int mouseX, int mouseY) {
        return this.mousePressed(minecraft, mouseX, mouseY, true);
    }

    public boolean mousePressed(Minecraft minecraft, int mouseX, int mouseY, boolean checkClickListeners) {
        boolean clicked = this.isEnabled() && this.isVisible() && this.mouseOver(mouseX, mouseY);
        return clicked && this.checkClickListeners();
    }

    public boolean checkClickListeners() {
        boolean clicked = true;
        if (!this.clickListeners.isEmpty()) {
            try {
                for (Function<Button, Boolean> listener : this.clickListeners) {
                    if (!listener.apply(this).booleanValue()) break;
                }
            }
            catch (Throwable t) {
                Journeymap.getLogger().error("Error trying to toggle button '" + this.displayString + "': " + LogFormatter.toString(t));
                clicked = false;
            }
        }
        return clicked;
    }

    public String getUnformattedTooltip() {
        if (this.tooltip != null && this.tooltip.length > 0) {
            return this.tooltip[0];
        }
        return null;
    }

    public List<String> getTooltip() {
        ArrayList<String> list = new ArrayList<String>();
        if (this.tooltip != null) {
            for (String line : this.tooltip) {
                list.addAll(this.fontRenderer.listFormattedStringToWidth(line, this.tooltipSize));
            }
            return list;
        }
        if (!this.isEnabled() && this.showDisabledHoverText) {
            list.add(TextFormatting.ITALIC + Constants.getString("jm.common.disabled_feature"));
        }
        return list;
    }

    public void setTooltip(String ... tooltip) {
        this.tooltip = tooltip;
    }

    public void setTooltip(int size, String ... tooltip) {
        this.tooltipSize = size;
        this.tooltip = tooltip;
    }

    public boolean mouseOver(int mouseX, int mouseY) {
        return this.isVisible() && this.getBounds().contains(mouseX, mouseY);
    }

    protected Rectangle2D.Double updateBounds() {
        this.bounds = new Rectangle2D.Double(this.getX(), this.getY(), this.getWidth(), this.getHeight());
        return this.bounds;
    }

    public Rectangle2D.Double getBounds() {
        if (this.bounds == null) {
            return this.updateBounds();
        }
        return this.bounds;
    }

    @Override
    public int getWidth() {
        return this.width;
    }

    public void setWidth(int width) {
        if (this.width != width) {
            this.width = width;
            this.bounds = null;
        }
    }

    @Override
    public void setScrollableWidth(int width) {
        this.setWidth(width);
    }

    @Override
    public int getHeight() {
        return this.height;
    }

    public void setHeight(int height) {
        if (this.height != height) {
            this.height = height;
            this.bounds = null;
            if (height != 20) {
                this.defaultStyle = false;
            }
        }
    }

    public void setTextOnly(FontRenderer fr) {
        this.setHeight(fr.FONT_HEIGHT + 1);
        this.fitWidth(fr);
        this.setDrawBackground(false);
        this.setDrawFrame(false);
    }

    @Override
    public void drawScrollable(Minecraft mc, int mouseX, int mouseY) {
        this.drawButton(mc, mouseX, mouseY, 0.0f);
    }

    @Override
    public void clickScrollable(Minecraft mc, int mouseX, int mouseY) {
    }

    @Override
    public int getX() {
        return this.x;
    }

    public void setX(int x) {
        if (this.x != x) {
            this.x = x;
            this.bounds = null;
        }
    }

    @Override
    public int getY() {
        return this.y;
    }

    public void setY(int y) {
        if (this.y != y) {
            this.y = y;
            this.bounds = null;
        }
    }

    public int getCenterX() {
        return this.x + this.width / 2;
    }

    public int getMiddleY() {
        return this.y + this.height / 2;
    }

    public int getBottomY() {
        return this.y + this.height;
    }

    public int getRightX() {
        return this.x + this.width;
    }

    @Override
    public void setPosition(int x, int y) {
        this.setX(x);
        this.setY(y);
    }

    public Button leftOf(int x) {
        this.setX(x - this.getWidth());
        return this;
    }

    public Button rightOf(int x) {
        this.setX(x);
        return this;
    }

    public Button centerHorizontalOn(int x) {
        this.setX(x - this.width / 2);
        return this;
    }

    public Button centerVerticalOn(int y) {
        this.setY(y - this.height / 2);
        return this;
    }

    public Button leftOf(Button other, int margin) {
        this.setX(other.getX() - this.getWidth() - margin);
        return this;
    }

    public Button rightOf(Button other, int margin) {
        this.setX(other.getX() + other.getWidth() + margin);
        return this;
    }

    public Button above(Button other, int margin) {
        this.setY(other.getY() - this.getHeight() - margin);
        return this;
    }

    public Button above(int y) {
        this.setY(y - this.getHeight());
        return this;
    }

    public Button below(Button other, int margin) {
        this.setY(other.getY() + other.getHeight() + margin);
        return this;
    }

    public Button below(ButtonList list, int margin) {
        this.setY(list.getBottomY() + margin);
        return this;
    }

    public Button below(int y) {
        this.setY(y);
        return this;
    }

    public Button alignTo(Button other, DrawUtil.HAlign hAlign, int hgap, DrawUtil.VAlign vAlign, int vgap) {
        int x = this.getX();
        int y = this.getY();
        switch (hAlign) {
            case Right: {
                x = other.getRightX() + hgap;
                break;
            }
            case Left: {
                x = other.getX() - hgap;
                break;
            }
            case Center: {
                x = other.getCenterX();
            }
        }
        switch (vAlign) {
            case Above: {
                y = other.getY() - vgap - this.getHeight();
                break;
            }
            case Below: {
                y = other.getBottomY() + vgap;
                break;
            }
            case Middle: {
                y = other.getMiddleY() - this.getHeight() / 2;
            }
        }
        this.setX(x);
        this.setY(y);
        return this;
    }

    public boolean isEnabled() {
        return this.enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public boolean isVisible() {
        return this.visible;
    }

    public void setVisible(boolean visible) {
        this.visible = visible;
    }

    public void setDrawButton(boolean drawButton) {
        if (drawButton != this.visible) {
            this.visible = drawButton;
        }
    }

    public boolean isDrawFrame() {
        return this.drawFrame;
    }

    public void setDrawFrame(boolean drawFrame) {
        this.drawFrame = drawFrame;
    }

    public boolean isDrawBackground() {
        return this.drawBackground;
    }

    public void setDrawBackground(boolean drawBackground) {
        this.drawBackground = drawBackground;
    }

    public boolean isDefaultStyle() {
        return this.defaultStyle;
    }

    public void setDefaultStyle(boolean defaultStyle) {
        this.defaultStyle = defaultStyle;
    }

    public boolean keyTyped(char c, int i) {
        return false;
    }

    public void setBackgroundColors(Integer customBgColor, Integer customBgHoverColor, Integer customBgHoverColor2) {
        this.customBgColor = customBgColor;
        this.customBgHoverColor = customBgHoverColor;
        this.customBgHoverColor2 = customBgHoverColor2;
    }

    public void setDrawLabelShadow(boolean draw) {
        this.drawLabelShadow = draw;
    }

    public void setLabelColors(Integer labelColor, Integer hoverLabelColor, Integer disabledLabelColor) {
        this.labelColor = labelColor;
        this.packedFGColour = labelColor;
        if (hoverLabelColor != null) {
            this.hoverLabelColor = hoverLabelColor;
        }
        if (disabledLabelColor != null) {
            this.disabledLabelColor = disabledLabelColor;
        }
    }

    public String getDisplayString() {
        return this.displayString;
    }

    public void refresh() {
    }

    public Integer getLabelColor() {
        return this.labelColor;
    }

    public boolean isHovered() {
        return this.hovered;
    }

    public void setHovered(boolean hovered) {
        this.hovered = hovered;
    }

    public void addClickListener(Function<Button, Boolean> listener) {
        this.clickListeners.add(listener);
    }
}

