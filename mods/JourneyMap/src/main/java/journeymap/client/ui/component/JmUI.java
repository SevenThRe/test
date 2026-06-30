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
        return this.field_146297_k;
    }

    public void func_146280_a(Minecraft minecraft, int width, int height) {
        super.func_146280_a(minecraft, width, height);
        this.scaleFactor = new ScaledResolution(minecraft).func_78325_e();
    }

    public boolean func_73868_f() {
        return true;
    }

    public FontRenderer getFontRenderer() {
        return this.field_146289_q;
    }

    public void sizeDisplay(boolean scaled) {
        int glwidth = scaled ? this.field_146294_l : this.field_146297_k.field_71443_c;
        int glheight = scaled ? this.field_146295_m : this.field_146297_k.field_71440_d;
        DrawUtil.sizeDisplay(glwidth, glheight);
    }

    protected boolean isMouseOverButton(int mouseX, int mouseY) {
        for (int k = 0; k < this.field_146292_n.size(); ++k) {
            Button button;
            GuiButton guibutton = (GuiButton)this.field_146292_n.get(k);
            if (!(guibutton instanceof Button) || !(button = (Button)guibutton).mouseOver(mouseX, mouseY)) continue;
            return true;
        }
        return false;
    }

    protected void func_146286_b(int mouseX, int mouseY, int mouseEvent) {
        super.func_146286_b(mouseX, mouseY, mouseEvent);
    }

    protected void drawLogo() {
        if (this.logo.isDefunct()) {
            this.logo = TextureCache.getTexture(TextureCache.Logo);
        }
        DrawUtil.sizeDisplay(this.field_146297_k.field_71443_c, this.field_146297_k.field_71440_d);
        DrawUtil.drawImage(this.logo, 8.0, 8.0, false, 0.5f, 0.0);
        DrawUtil.sizeDisplay(this.field_146294_l, this.field_146295_m);
    }

    protected void drawTitle() {
        DrawUtil.drawRectangle(0.0, 0.0, this.field_146294_l, 35.0, 0, 0.4f);
        DrawUtil.drawLabel(this.title, this.field_146294_l / 2, 17.0, DrawUtil.HAlign.Center, DrawUtil.VAlign.Middle, 0, 0.0f, Color.CYAN.getRGB(), 1.0f, 1.0, true, 0.0);
        String apiVersion = "API v1.4";
        DrawUtil.drawLabel("API v1.4", this.field_146294_l - 10, 17.0, DrawUtil.HAlign.Left, DrawUtil.VAlign.Middle, 0, 0.0f, 0xCCCCCC, 1.0f, 0.5, true, 0.0);
    }

    public void func_73866_w_() {
        this.field_146292_n.clear();
    }

    public void func_146278_c(int tint) {
        if (this.field_146297_k.field_71441_e == null) {
            this.func_73733_a(0, 0, this.field_146294_l, this.field_146295_m, -1072689136, -804253680);
        } else {
            this.func_146276_q_();
        }
    }

    protected abstract void layoutButtons();

    public List getButtonList() {
        return this.field_146292_n;
    }

    public void func_73863_a(int x, int y, float par3) {
        try {
            this.func_146278_c(0);
            this.layoutButtons();
            this.drawTitle();
            this.drawLogo();
            List<String> tooltip = null;
            for (int k = 0; k < this.field_146292_n.size(); ++k) {
                Button button;
                GuiButton guibutton = (GuiButton)this.field_146292_n.get(k);
                guibutton.func_191745_a(this.field_146297_k, x, y, 0.0f);
                if (tooltip != null || !(guibutton instanceof Button) || !(button = (Button)guibutton).mouseOver(x, y)) continue;
                tooltip = button.getTooltip();
            }
            if (tooltip != null && !tooltip.isEmpty()) {
                this.drawHoveringText(tooltip, x, y, this.getFontRenderer());
                RenderHelper.func_74518_a();
            }
        }
        catch (Throwable t) {
            Journeymap.getLogger().error("Error in UI: " + LogFormatter.toString(t));
            this.closeAndReturn();
        }
    }

    public void func_73733_a(int left, int top, int right, int bottom, int startColor, int endColor) {
        super.func_73733_a(left, top, right, bottom, startColor, endColor);
    }

    public void close() {
    }

    protected void closeAndReturn() {
        if (this.returnDisplay == null) {
            if (this.field_146297_k.field_71441_e != null) {
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

    protected void func_73869_a(char c, int i) throws IOException {
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
            RenderHelper.func_74518_a();
            GlStateManager.func_179140_f();
            GlStateManager.func_179097_i();
            int maxLineWidth = 0;
            for (String line : tooltip) {
                int lineWidth = fontRenderer.func_78256_a(line);
                if (fontRenderer.func_78260_a()) {
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
            if (drawX + maxLineWidth > this.field_146294_l) {
                drawX -= 28 + maxLineWidth;
            }
            if (drawY + boxHeight + 6 > this.field_146295_m) {
                drawY = this.field_146295_m - boxHeight - 6;
            }
            this.field_73735_i = 300.0f;
            this.field_146296_j.field_77023_b = 300.0f;
            int j1 = -267386864;
            this.func_73733_a(drawX - 3, drawY - 4, drawX + maxLineWidth + 3, drawY - 3, -267386864, -267386864);
            this.func_73733_a(drawX - 3, drawY + boxHeight + 3, drawX + maxLineWidth + 3, drawY + boxHeight + 4, -267386864, -267386864);
            this.func_73733_a(drawX - 3, drawY - 3, drawX + maxLineWidth + 3, drawY + boxHeight + 3, -267386864, -267386864);
            this.func_73733_a(drawX - 4, drawY - 3, drawX - 3, drawY + boxHeight + 3, -267386864, -267386864);
            this.func_73733_a(drawX + maxLineWidth + 3, drawY - 3, drawX + maxLineWidth + 4, drawY + boxHeight + 3, -267386864, -267386864);
            int k1 = 0x505000FF;
            int l1 = 1344798847;
            this.func_73733_a(drawX - 3, drawY - 3 + 1, drawX - 3 + 1, drawY + boxHeight + 3 - 1, 0x505000FF, 1344798847);
            this.func_73733_a(drawX + maxLineWidth + 2, drawY - 3 + 1, drawX + maxLineWidth + 3, drawY + boxHeight + 3 - 1, 0x505000FF, 1344798847);
            this.func_73733_a(drawX - 3, drawY - 3, drawX + maxLineWidth + 3, drawY - 3 + 1, 0x505000FF, 0x505000FF);
            this.func_73733_a(drawX - 3, drawY + boxHeight + 2, drawX + maxLineWidth + 3, drawY + boxHeight + 3, 1344798847, 1344798847);
            for (int i2 = 0; i2 < tooltip.size(); ++i2) {
                String line2 = tooltip.get(i2);
                if (fontRenderer.func_78260_a()) {
                    int lineWidth2 = (int)Math.ceil((double)fontRenderer.func_78256_a(line2) * 1.1);
                    fontRenderer.func_175063_a(line2, (float)(drawX + maxLineWidth - lineWidth2), (float)drawY, -1);
                } else {
                    fontRenderer.func_175063_a(line2, (float)drawX, (float)drawY, -1);
                }
                if (i2 == 0) {
                    drawY += 2;
                }
                drawY += 10;
            }
            this.field_73735_i = 0.0f;
            this.field_146296_j.field_77023_b = 0.0f;
            GlStateManager.func_179145_e();
            GlStateManager.func_179126_j();
            RenderHelper.func_74519_b();
            GL11.glEnable((int)32826);
        }
    }
}

