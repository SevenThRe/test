/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.gui.FontRenderer
 *  net.minecraft.client.gui.GuiButton
 *  net.minecraft.client.gui.GuiScreen
 *  net.minecraft.client.gui.ScaledResolution
 *  net.minecraft.client.renderer.GlStateManager
 *  net.minecraft.client.renderer.RenderHelper
 *  org.apache.logging.log4j.Logger
 *  org.lwjgl.opengl.GL11
 */
package journeymap.client.ui.component;

import java.awt.Color;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import journeymap.client.render.draw.DrawUtil;
import journeymap.client.render.texture.TextureCache;
import journeymap.client.render.texture.TextureImpl;
import journeymap.client.ui.UIManager;
import journeymap.client.ui.component.Button;
import journeymap.common.Journeymap;
import journeymap.common.log.LogFormatter;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.RenderHelper;
import org.apache.logging.log4j.Logger;
import org.lwjgl.opengl.GL11;

public abstract class JmUI
extends GuiScreen {
    protected final String title;
    protected final int headerHeight = 35;
    protected final Logger logger = Journeymap.getLogger();
    protected GuiScreen returnDisplay;
    protected int scaleFactor = 1;
    protected TextureImpl logo = TextureCache.getTexture(TextureCache.Logo);

    public JmUI(String title) {
        this(title, null);
    }

    public JmUI(String title, GuiScreen returnDisplay) {
        this.title = title;
        this.returnDisplay = returnDisplay;
        if (this.returnDisplay != null && this.returnDisplay instanceof JmUI) {
            JmUI jmReturnDisplay = (JmUI)this.returnDisplay;
            if (jmReturnDisplay.returnDisplay instanceof JmUI) {
                jmReturnDisplay.returnDisplay = null;
            }
        }
    }

    public Minecraft getMinecraft() {
        return this.mc;
    }

    public void setWorldAndResolution(Minecraft minecraft, int width, int height) {
        super.setWorldAndResolution(minecraft, width, height);
        this.scaleFactor = new ScaledResolution(minecraft).getScaleFactor();
    }

    public boolean doesGuiPauseGame() {
        return true;
    }

    public FontRenderer getFontRenderer() {
        return this.fontRenderer;
    }

    public void sizeDisplay(boolean scaled) {
        int glwidth = scaled ? this.width : this.mc.displayWidth;
        int glheight = scaled ? this.height : this.mc.displayHeight;
        DrawUtil.sizeDisplay(glwidth, glheight);
    }

    protected boolean isMouseOverButton(int mouseX, int mouseY) {
        for (int k = 0; k < this.buttonList.size(); ++k) {
            Button button;
            GuiButton guibutton = (GuiButton)this.buttonList.get(k);
            if (!(guibutton instanceof Button) || !(button = (Button)guibutton).mouseOver(mouseX, mouseY)) continue;
            return true;
        }
        return false;
    }

    protected void mouseReleased(int mouseX, int mouseY, int mouseEvent) {
        super.mouseReleased(mouseX, mouseY, mouseEvent);
    }

    protected void drawLogo() {
        if (this.logo.isDefunct()) {
            this.logo = TextureCache.getTexture(TextureCache.Logo);
        }
        DrawUtil.sizeDisplay(this.mc.displayWidth, this.mc.displayHeight);
        DrawUtil.drawImage(this.logo, 8.0, 8.0, false, 0.5f, 0.0);
        DrawUtil.sizeDisplay(this.width, this.height);
    }

    protected void drawTitle() {
        DrawUtil.drawRectangle(0.0, 0.0, this.width, 35.0, 0, 0.4f);
        DrawUtil.drawLabel(this.title, this.width / 2, 17.0, DrawUtil.HAlign.Center, DrawUtil.VAlign.Middle, 0, 0.0f, Color.CYAN.getRGB(), 1.0f, 1.0, true, 0.0);
        String apiVersion = "API v1.4";
        DrawUtil.drawLabel("API v1.4", this.width - 10, 17.0, DrawUtil.HAlign.Left, DrawUtil.VAlign.Middle, 0, 0.0f, 0xCCCCCC, 1.0f, 0.5, true, 0.0);
    }

    public void initGui() {
        this.buttonList.clear();
    }

    public void drawBackground(int tint) {
        if (this.mc.world == null) {
            this.drawGradientRect(0, 0, this.width, this.height, -1072689136, -804253680);
        } else {
            this.drawDefaultBackground();
        }
    }

    protected abstract void layoutButtons();

    public List getButtonList() {
        return this.buttonList;
    }

    public void drawScreen(int x, int y, float par3) {
        try {
            this.drawBackground(0);
            this.layoutButtons();
            this.drawTitle();
            this.drawLogo();
            List<String> tooltip = null;
            for (int k = 0; k < this.buttonList.size(); ++k) {
                Button button;
                GuiButton guibutton = (GuiButton)this.buttonList.get(k);
                guibutton.drawButton(this.mc, x, y, 0.0f);
                if (tooltip != null || !(guibutton instanceof Button) || !(button = (Button)guibutton).mouseOver(x, y)) continue;
                tooltip = button.getTooltip();
            }
            if (tooltip != null && !tooltip.isEmpty()) {
                this.drawHoveringText(tooltip, x, y, this.getFontRenderer());
                RenderHelper.disableStandardItemLighting();
            }
        }
        catch (Throwable t) {
            Journeymap.getLogger().error("Error in UI: " + LogFormatter.toString(t));
            this.closeAndReturn();
        }
    }

    public void drawGradientRect(int left, int top, int right, int bottom, int startColor, int endColor) {
        super.drawGradientRect(left, top, right, bottom, startColor, endColor);
    }

    public void close() {
    }

    protected void closeAndReturn() {
        if (this.returnDisplay == null) {
            if (this.mc.world != null) {
                UIManager.INSTANCE.openFullscreenMap();
            } else {
                UIManager.INSTANCE.closeAll();
            }
        } else {
            if (this.returnDisplay instanceof JmUI) {
                ((JmUI)this.returnDisplay).returnDisplay = null;
            }
            UIManager.INSTANCE.open(this.returnDisplay);
        }
    }

    protected void keyTyped(char c, int i) throws IOException {
        switch (i) {
            case 1: {
                this.closeAndReturn();
            }
        }
    }

    public void drawHoveringText(String[] tooltip, int mouseX, int mouseY) {
        this.drawHoveringText(Arrays.asList(tooltip), mouseX, mouseY, this.getFontRenderer());
    }

    public GuiScreen getReturnDisplay() {
        return this.returnDisplay;
    }

    public void drawHoveringText(List<String> tooltip, int mouseX, int mouseY, FontRenderer fontRenderer) {
        if (!tooltip.isEmpty()) {
            GL11.glDisable((int)32826);
            RenderHelper.disableStandardItemLighting();
            GlStateManager.disableLighting();
            GlStateManager.disableDepth();
            int maxLineWidth = 0;
            for (String line : tooltip) {
                int lineWidth = fontRenderer.getStringWidth(line);
                if (fontRenderer.getBidiFlag()) {
                    lineWidth = (int)Math.ceil((double)lineWidth * 1.25);
                }
                if (lineWidth <= maxLineWidth) continue;
                maxLineWidth = lineWidth;
            }
            int drawX = mouseX + 12;
            int drawY = mouseY - 12;
            int boxHeight = 8;
            if (tooltip.size() > 1) {
                boxHeight += 2 + (tooltip.size() - 1) * 10;
            }
            if (drawX + maxLineWidth > this.width) {
                drawX -= 28 + maxLineWidth;
            }
            if (drawY + boxHeight + 6 > this.height) {
                drawY = this.height - boxHeight - 6;
            }
            this.zLevel = 300.0f;
            this.itemRender.zLevel = 300.0f;
            int j1 = -267386864;
            this.drawGradientRect(drawX - 3, drawY - 4, drawX + maxLineWidth + 3, drawY - 3, -267386864, -267386864);
            this.drawGradientRect(drawX - 3, drawY + boxHeight + 3, drawX + maxLineWidth + 3, drawY + boxHeight + 4, -267386864, -267386864);
            this.drawGradientRect(drawX - 3, drawY - 3, drawX + maxLineWidth + 3, drawY + boxHeight + 3, -267386864, -267386864);
            this.drawGradientRect(drawX - 4, drawY - 3, drawX - 3, drawY + boxHeight + 3, -267386864, -267386864);
            this.drawGradientRect(drawX + maxLineWidth + 3, drawY - 3, drawX + maxLineWidth + 4, drawY + boxHeight + 3, -267386864, -267386864);
            int k1 = 0x505000FF;
            int l1 = 1344798847;
            this.drawGradientRect(drawX - 3, drawY - 3 + 1, drawX - 3 + 1, drawY + boxHeight + 3 - 1, 0x505000FF, 1344798847);
            this.drawGradientRect(drawX + maxLineWidth + 2, drawY - 3 + 1, drawX + maxLineWidth + 3, drawY + boxHeight + 3 - 1, 0x505000FF, 1344798847);
            this.drawGradientRect(drawX - 3, drawY - 3, drawX + maxLineWidth + 3, drawY - 3 + 1, 0x505000FF, 0x505000FF);
            this.drawGradientRect(drawX - 3, drawY + boxHeight + 2, drawX + maxLineWidth + 3, drawY + boxHeight + 3, 1344798847, 1344798847);
            for (int i2 = 0; i2 < tooltip.size(); ++i2) {
                String line2 = tooltip.get(i2);
                if (fontRenderer.getBidiFlag()) {
                    int lineWidth2 = (int)Math.ceil((double)fontRenderer.getStringWidth(line2) * 1.1);
                    fontRenderer.drawStringWithShadow(line2, (float)(drawX + maxLineWidth - lineWidth2), (float)drawY, -1);
                } else {
                    fontRenderer.drawStringWithShadow(line2, (float)drawX, (float)drawY, -1);
                }
                if (i2 == 0) {
                    drawY += 2;
                }
                drawY += 10;
            }
            this.zLevel = 0.0f;
            this.itemRender.zLevel = 0.0f;
            GlStateManager.enableLighting();
            GlStateManager.enableDepth();
            RenderHelper.enableStandardItemLighting();
            GL11.glEnable((int)32826);
        }
    }
}

