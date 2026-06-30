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
        this.fontRenderer = FMLClientHandler.instance().getClient().field_71466_p;
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
        if (this.field_146121_g == 0) {
            this.setHeight(20);
        }
        if (this.field_146120_f == 0) {
            this.func_175211_a(200);
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
        int max = fr.func_78256_a(this.field_146126_j);
        return max + this.WIDTH_PAD + (fr.func_78260_a() ? (int)Math.ceil((double)max * 0.25) : 0);
    }

    public void fitWidth(FontRenderer fr) {
        this.func_175211_a(this.getFitWidth(fr));
    }

    @Override
    public void drawPartialScrollable(Minecraft minecraft, int x, int y, int width, int height) {
        minecraft.func_110434_K().func_110577_a(field_146122_a);
        int k = 0;
        this.func_73729_b(x, y, 0, 46 + k * 20, width / 2, height);
        this.func_73729_b(x + width / 2, y, 200 - width / 2, 46 + k * 20, width / 2, height);
    }

    public void showDisabledOnHover(boolean show) {
        this.showDisabledHoverText = show;
    }

    public boolean func_146115_a() {
        return super.func_146115_a();
    }

    public void setMouseOver(boolean hover) {
        this.setHovered(hover);
    }

    public void func_146113_a(SoundHandler soundHandler) {
        if (this.isEnabled()) {
            super.func_146113_a(soundHandler);
        }
    }

    public void func_191745_a(Minecraft minecraft, int mouseX, int mouseY, float partialTicks) {
        if (!this.isVisible()) {
            return;
        }
        if (this.defaultStyle) {
            super.func_191745_a(minecraft, mouseX, mouseY, partialTicks);
        } else {
            minecraft.func_110434_K().func_110577_a(field_146122_a);
            GlStateManager.func_179131_c((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
            this.setHovered(mouseX >= this.field_146128_h && mouseY >= this.field_146129_i && mouseX < this.field_146128_h + this.field_146120_f && mouseY < this.field_146129_i + this.field_146121_g);
            int hoverState = this.func_146114_a(this.isHovered());
            if (this.isDrawFrame()) {
                DrawUtil.drawRectangle(this.field_146128_h, this.field_146129_i, this.field_146120_f, 1.0, this.customFrameColorLight, 1.0f);
                DrawUtil.drawRectangle(this.field_146128_h, this.field_146129_i, 1.0, this.field_146121_g, this.customFrameColorLight, 1.0f);
                DrawUtil.drawRectangle(this.field_146128_h, this.field_146129_i + this.field_146121_g - 1, this.field_146120_f - 1, 1.0, this.customFrameColorDark, 1.0f);
                DrawUtil.drawRectangle(this.field_146128_h + this.field_146120_f - 1, this.field_146129_i + 1, 1.0, this.field_146121_g - 1, this.customFrameColorDark, 1.0f);
            }
            if (this.isDrawBackground()) {
                DrawUtil.drawRectangle(this.field_146128_h + 1, this.field_146129_i + 1, this.field_146120_f - 2, this.field_146121_g - 2, hoverState == 2 ? this.customBgHoverColor : this.customBgColor, 1.0f);
            } else if (this.isEnabled() && this.isHovered()) {
                DrawUtil.drawRectangle(this.field_146128_h + 1, this.field_146129_i + 1, this.field_146120_f - 2, this.field_146121_g - 2, this.customBgHoverColor2, 0.5f);
            }
            this.func_146119_b(minecraft, mouseX, mouseY);
            Integer varLabelColor = this.labelColor;
            if (!this.isEnabled()) {
                varLabelColor = this.disabledLabelColor;
                if (this.drawBackground) {
                    float alpha = 0.7f;
                    int widthOffset = this.field_146120_f - (this.field_146121_g >= 20 ? 3 : 2);
                    DrawUtil.drawRectangle(this.getX() + 1, this.getY() + 1, widthOffset, this.field_146121_g - 2, this.disabledBgColor, alpha);
                }
            } else if (this.isHovered()) {
                varLabelColor = this.hoverLabelColor;
            } else if (this.labelColor != null) {
                varLabelColor = this.labelColor;
            } else if (this.packedFGColour != 0) {
                varLabelColor = this.packedFGColour;
            }
            DrawUtil.drawCenteredLabel(this.field_146126_j, (double)this.getCenterX(), (double)this.getMiddleY(), null, 0.0f, varLabelColor, 1.0f, 1.0, this.drawLabelShadow);
        }
    }

    public void drawCenteredString(FontRenderer fontRenderer, String text, float x, float y, int color) {
        fontRenderer.func_175063_a(text, x - (float)(fontRenderer.func_78256_a(text) / 2), y, color);
    }

    public void drawUnderline() {
        if (this.isVisible()) {
            DrawUtil.drawRectangle(this.field_146128_h, this.field_146129_i + this.field_146121_g, this.field_146120_f, 1.0, this.customFrameColorDark, 1.0f);
        }
    }

    public void secondaryDrawButton() {
    }

    public boolean func_146116_c(Minecraft minecraft, int mouseX, int mouseY) {
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
                Journeymap.getLogger().error("Error trying to toggle button '" + this.field_146126_j + "': " + LogFormatter.toString(t));
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
                list.addAll(this.fontRenderer.func_78271_c(line, this.tooltipSize));
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
        return this.field_146120_f;
    }

    public void func_175211_a(int width) {
        if (this.field_146120_f != width) {
            this.field_146120_f = width;
            this.bounds = null;
        }
    }

    @Override
    public void setScrollableWidth(int width) {
        this.func_175211_a(width);
    }

    @Override
    public int getHeight() {
        return this.field_146121_g;
    }

    public void setHeight(int height) {
        if (this.field_146121_g != height) {
            this.field_146121_g = height;
            this.bounds = null;
            if (height != 20) {
                this.defaultStyle = false;
            }
        }
    }

    public void setTextOnly(FontRenderer fr) {
        this.setHeight(fr.field_78288_b + 1);
        this.fitWidth(fr);
        this.setDrawBackground(false);
        this.setDrawFrame(false);
    }

    @Override
    public void drawScrollable(Minecraft mc, int mouseX, int mouseY) {
        this.func_191745_a(mc, mouseX, mouseY, 0.0f);
    }

    @Override
    public void clickScrollable(Minecraft mc, int mouseX, int mouseY) {
    }

    @Override
    public int getX() {
        return this.field_146128_h;
    }

    public void setX(int x) {
        if (this.field_146128_h != x) {
            this.field_146128_h = x;
            this.bounds = null;
        }
    }

    @Override
    public int getY() {
        return this.field_146129_i;
    }

    public void setY(int y) {
        if (this.field_146129_i != y) {
            this.field_146129_i = y;
            this.bounds = null;
        }
    }

    public int getCenterX() {
        return this.field_146128_h + this.field_146120_f / 2;
    }

    public int getMiddleY() {
        return this.field_146129_i + this.field_146121_g / 2;
    }

    public int getBottomY() {
        return this.field_146129_i + this.field_146121_g;
    }

    public int getRightX() {
        return this.field_146128_h + this.field_146120_f;
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
        this.setX(x - this.field_146120_f / 2);
        return this;
    }

    public Button centerVerticalOn(int y) {
        this.setY(y - this.field_146121_g / 2);
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
        return this.field_146124_l;
    }

    public void setEnabled(boolean enabled) {
        this.field_146124_l = enabled;
    }

    public boolean isVisible() {
        return this.field_146125_m;
    }

    public void setVisible(boolean visible) {
        this.field_146125_m = visible;
    }

    public void setDrawButton(boolean drawButton) {
        if (drawButton != this.field_146125_m) {
            this.field_146125_m = drawButton;
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
        return this.field_146126_j;
    }

    public void refresh() {
    }

    public Integer getLabelColor() {
        return this.labelColor;
    }

    public boolean isHovered() {
        return this.field_146123_n;
    }

    public void setHovered(boolean hovered) {
        this.field_146123_n = hovered;
    }

    public void addClickListener(Function<Button, Boolean> listener) {
        this.clickListeners.add(listener);
    }
}

