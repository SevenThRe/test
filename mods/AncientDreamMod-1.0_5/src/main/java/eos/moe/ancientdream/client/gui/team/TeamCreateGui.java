/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.gui.GuiScreen
 *  net.minecraft.client.gui.GuiTextField
 *  net.minecraft.client.renderer.GlStateManager
 *  net.minecraft.util.ResourceLocation
 */
package eos.moe.ancientdream.client.gui.team;

import eos.moe.ancientdream.client.gui.team.TeamManagerGui;
import eos.moe.ancientdream.client.utils.RenderUtils;
import eos.moe.ancientdream.network.send.MessageSender;
import java.awt.Color;
import java.awt.Rectangle;
import java.io.IOException;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.GuiTextField;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.util.ResourceLocation;

public class TeamCreateGui
extends GuiScreen {
    private static ResourceLocation BACKGROUND = new ResourceLocation("ancientdream", "gui/team/create.png");
    private static ResourceLocation BTN = new ResourceLocation("ancientdream", "gui/team/btn.png");
    private static ResourceLocation BTN1 = new ResourceLocation("ancientdream", "gui/team/btn1.png");
    private static ResourceLocation CLOSTBTN = new ResourceLocation("ancientdream", "gui/team/closebutton.png");
    private float offsetX;
    private float offsetY;
    private float xSize;
    private float ySize;
    private GuiTextField field;
    private int selectedTexture;
    private boolean isCreate;

    public TeamCreateGui(boolean isCreate) {
        this.isCreate = isCreate;
    }

    public void initGui() {
        super.initGui();
        this.xSize = 342.0f;
        this.ySize = 264.0f;
        this.offsetX = ((float)this.width - this.xSize) / 2.0f;
        this.offsetY = ((float)this.height - this.ySize) / 2.0f;
        this.field = new GuiTextField(1, this.fontRenderer, 71, 165, 200, 30);
        this.field.setFocused(true);
        this.field.setMaxStringLength(35);
    }

    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
        super.drawScreen(mouseX, mouseY, partialTicks);
        GlStateManager.pushMatrix();
        GlStateManager.translate((float)this.offsetX, (float)this.offsetY, (float)0.0f);
        RenderUtils.drawTexture(5.0, 0.0, this.xSize, this.ySize, BACKGROUND);
        RenderUtils.drawTexture(90.0, 228.0, 70.0, 25.0, (float)mouseX - this.offsetX, (float)mouseY - this.offsetY, BTN, BTN1);
        if (this.isCreate) {
            RenderUtils.drawText("\u00a7e\u00a7l\u786e\u5b9a\u521b\u5efa", 125.0, 237.0, true, true);
        } else {
            RenderUtils.drawText("\u00a7e\u00a7l\u786e\u5b9a\u4fee\u6539", 125.0, 237.0, true, true);
        }
        RenderUtils.drawTexture(185.0, 228.0, 70.0, 25.0, (float)mouseX - this.offsetX, (float)mouseY - this.offsetY, BTN, BTN1);
        if (this.isCreate) {
            RenderUtils.drawText("\u00a7e\u00a7l\u53d6\u6d88\u521b\u5efa", 220.0, 237.0, true, true);
        } else {
            RenderUtils.drawText("\u00a7e\u00a7l\u53d6\u6d88\u4fee\u6539", 220.0, 237.0, true, true);
        }
        for (int i = 0; i < 10; ++i) {
            int x = 42 + (i >= 5 ? i - 5 : i) * 55;
            int y = 53 + (i >= 5 ? 55 : 0);
            if (i == this.selectedTexture) {
                RenderUtils.drawColor((double)(x - 3), (double)(y - 3), 46.0, 46.0, new Color(100, 100, 100, 180));
            }
            RenderUtils.drawTexture(x, y, 40.0, 40.0, new ResourceLocation("ancientdream", "gui/team/head" + i + ".png"));
            if (!new Rectangle(x, y, 40, 40).contains((float)mouseX - this.offsetX, (float)mouseY - this.offsetY)) continue;
            RenderUtils.drawColor((double)x, (double)y, 40.0, 40.0, new Color(255, 255, 255, 127));
        }
        this.field.drawTextBox();
        GlStateManager.popMatrix();
    }

    protected void mouseClicked(int mouseX, int mouseY, int mouseButton) throws IOException {
        super.mouseClicked(mouseX, mouseY, mouseButton);
        if (new Rectangle(90, 228, 70, 25).contains((float)mouseX - this.offsetX, (float)mouseY - this.offsetY)) {
            MessageSender.sendCreateTeam(this.field.getText(), "head" + this.selectedTexture + ".png");
            this.mc.displayGuiScreen((GuiScreen)new TeamManagerGui());
        } else if (new Rectangle(185, 228, 70, 25).contains((float)mouseX - this.offsetX, (float)mouseY - this.offsetY)) {
            this.mc.displayGuiScreen(null);
        } else {
            for (int i = 0; i < 10; ++i) {
                int y;
                int x = 42 + (i >= 5 ? i - 5 : i) * 55;
                if (!new Rectangle(x, y = 53 + (i >= 5 ? 55 : 0), 40, 40).contains((float)mouseX - this.offsetX, (float)mouseY - this.offsetY)) continue;
                this.selectedTexture = i;
            }
        }
    }

    protected void keyTyped(char typedChar, int keyCode) throws IOException {
        super.keyTyped(typedChar, keyCode);
        this.field.textboxKeyTyped(typedChar, keyCode);
    }
}

