/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.gui.FontRenderer
 *  net.minecraft.client.gui.GuiButton
 */
package journeymap.client.ui.theme;

import java.util.ArrayList;
import java.util.Arrays;
import journeymap.client.render.draw.DrawUtil;
import journeymap.client.render.texture.TextureCache;
import journeymap.client.render.texture.TextureImpl;
import journeymap.client.ui.component.Button;
import journeymap.client.ui.component.ButtonList;
import journeymap.client.ui.component.JmUI;
import journeymap.client.ui.theme.Theme;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.GuiButton;

public class ThemeToolbar
extends Button {
    private final ButtonList buttonList;
    private Theme theme;
    private Theme.Container.Toolbar.ToolbarSpec toolbarSpec;
    private TextureImpl textureBegin;
    private TextureImpl textureInner;
    private TextureImpl textureEnd;

    public ThemeToolbar(Theme theme, Button ... buttons) {
        this(theme, new ButtonList(buttons));
    }

    public ThemeToolbar(Theme theme, ButtonList buttonList) {
        super(buttonList.getWidth(), buttonList.getHeight(), "");
        this.buttonList = buttonList;
        this.updateTheme(theme);
    }

    public void updateTheme(Theme theme) {
        this.theme = theme;
        this.updateTextures();
    }

    public Theme.Container.Toolbar.ToolbarSpec updateTextures() {
        Theme.Container.Toolbar.ToolbarSpec toolbarSpec = this.buttonList.isHorizontal() ? this.theme.container.toolbar.horizontal : this.theme.container.toolbar.vertical;
        this.func_175211_a(this.buttonList.getWidth());
        this.setHeight(this.buttonList.getHeight());
        if (this.toolbarSpec == null || toolbarSpec != this.toolbarSpec) {
            this.toolbarSpec = toolbarSpec;
            if (toolbarSpec.useThemeImages) {
                String pathPattern = "container/" + toolbarSpec.prefix + "toolbar_%s.png";
                this.textureBegin = TextureCache.getThemeTexture(this.theme, String.format(pathPattern, "begin"));
                this.textureInner = TextureCache.getThemeTexture(this.theme, String.format(pathPattern, "inner"));
                this.textureEnd = TextureCache.getThemeTexture(this.theme, String.format(pathPattern, "end"));
            }
        }
        return this.toolbarSpec;
    }

    public void updateLayout() {
        this.updateTextures();
        int drawX = this.buttonList.getLeftX() - 1;
        int drawY = this.buttonList.getTopY() - 1;
        this.setPosition(drawX, drawY);
    }

    public Theme.Container.Toolbar.ToolbarSpec getToolbarSpec() {
        return this.toolbarSpec;
    }

    private ButtonList getButtonList() {
        return this.buttonList;
    }

    public boolean contains(GuiButton button) {
        return this.buttonList.contains(button);
    }

    public <B extends Button> void add(B ... buttons) {
        this.buttonList.addAll(Arrays.asList(buttons));
    }

    public int getVMargin() {
        if (this.buttonList.isHorizontal()) {
            int heightDiff = (this.toolbarSpec.inner.height - this.theme.control.button.height) / 2;
            return heightDiff + this.toolbarSpec.margin;
        }
        return this.toolbarSpec.margin;
    }

    public int getHMargin() {
        if (this.buttonList.isHorizontal()) {
            return this.toolbarSpec.begin.width + this.toolbarSpec.margin;
        }
        int widthDiff = (this.toolbarSpec.inner.width - this.theme.control.button.width) / 2;
        return widthDiff + this.toolbarSpec.margin;
    }

    public void setDrawToolbar(boolean draw) {
        super.setDrawButton(draw);
        for (Button button : this.buttonList) {
            button.setDrawButton(draw);
        }
    }

    @Override
    public void func_191745_a(Minecraft minecraft, int mouseX, int mouseY, float f) {
        if (!this.field_146125_m) {
            return;
        }
        double drawX = this.getX();
        double drawY = this.getY();
        if (!this.toolbarSpec.useThemeImages) {
            return;
        }
        if (this.field_146125_m) {
            DrawUtil.drawQuad(this.textureBegin, this.toolbarSpec.begin.getColor(), this.toolbarSpec.begin.alpha, drawX, drawY, this.buttonList.getWidth() + 1, this.buttonList.getHeight() + 1, false, 0.0);
        }
    }

    @Override
    public int getCenterX() {
        return this.field_146128_h + this.field_146120_f / 2;
    }

    @Override
    public int getMiddleY() {
        return this.field_146129_i + this.field_146121_g / 2;
    }

    @Override
    public int getBottomY() {
        return this.field_146129_i + this.field_146121_g;
    }

    @Override
    public int getRightX() {
        return this.field_146128_h + this.field_146120_f;
    }

    public ArrayList<String> getTooltip() {
        return null;
    }

    public void equalizeWidths(FontRenderer fr) {
        this.buttonList.equalizeWidths(fr);
    }

    public void equalizeWidths(FontRenderer fr, int hgap, int maxTotalWidth) {
        this.buttonList.equalizeWidths(fr, hgap, maxTotalWidth);
    }

    public ButtonList layoutHorizontal(int startX, int y, boolean leftToRight, int hgap) {
        return this.layoutHorizontal(startX, y, leftToRight, hgap, false);
    }

    public ButtonList layoutHorizontal(int startX, int y, boolean leftToRight, int hgap, boolean alignCenter) {
        this.buttonList.layoutHorizontal(startX, y, leftToRight, hgap, alignCenter);
        this.updateLayout();
        return this.buttonList;
    }

    public ButtonList layoutCenteredVertical(int x, int centerY, boolean leftToRight, int vgap) {
        this.buttonList.layoutCenteredVertical(x, centerY, leftToRight, vgap);
        this.updateLayout();
        return this.buttonList;
    }

    public ButtonList layoutVertical(int x, int startY, boolean leftToRight, int vgap) {
        this.buttonList.layoutVertical(x, startY, leftToRight, vgap);
        this.updateLayout();
        return this.buttonList;
    }

    public ButtonList layoutCenteredHorizontal(int centerX, int y, boolean leftToRight, int hgap) {
        this.buttonList.layoutCenteredHorizontal(centerX, y, leftToRight, hgap);
        this.updateLayout();
        return this.buttonList;
    }

    public ButtonList layoutDistributedHorizontal(int leftX, int y, int rightX, boolean leftToRight) {
        this.buttonList.layoutDistributedHorizontal(leftX, y, rightX, leftToRight);
        this.updateLayout();
        return this.buttonList;
    }

    public ButtonList layoutFilledHorizontal(FontRenderer fr, int leftX, int y, int rightX, int hgap, boolean leftToRight) {
        this.buttonList.layoutFilledHorizontal(fr, leftX, y, rightX, hgap, leftToRight);
        this.updateLayout();
        return this.buttonList;
    }

    public void setLayout(ButtonList.Layout layout, ButtonList.Direction direction) {
        this.buttonList.setLayout(layout, direction);
        this.updateLayout();
    }

    public ButtonList reverse() {
        this.buttonList.reverse();
        this.updateLayout();
        return this.buttonList;
    }

    public void addAllButtons(JmUI gui) {
        gui.getButtonList().add(this);
        gui.getButtonList().addAll(this.buttonList);
    }
}

