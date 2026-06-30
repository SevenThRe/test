/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.gui.GuiScreen
 *  net.minecraft.client.gui.GuiTextField
 *  net.minecraft.client.renderer.GlStateManager
 *  net.minecraft.util.ResourceLocation
 */
package eos.moe.ancientdream.client.gui.team;

import eos.moe.ancientdream.client.utils.RenderUtils;
import eos.moe.ancientdream.client.utils.texture.TextureProvider;
import eos.moe.ancientdream.network.send.MessageSender;
import java.awt.Rectangle;
import java.io.IOException;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.GuiTextField;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.util.ResourceLocation;

public class TeamHeadGui
extends GuiScreen {
    private static ResourceLocation BACKGROUND = new ResourceLocation("ancientdream", "gui/team/create.png");
    private static ResourceLocation BTN = new ResourceLocation("ancientdream", "gui/team/btn.png");
    private static ResourceLocation BTN1 = new ResourceLocation("ancientdream", "gui/team/btn1.png");
    private static ResourceLocation DEFHEAD = new ResourceLocation("ancientdream", "gui/team/head.png");
    private float offsetX;
    private float offsetY;
    private float xSize;
    private float ySize;
    private GuiTextField field;
    private String link;

    public TeamHeadGui(String link) {
        this.link = link;
    }

    public void initGui() {
        super.initGui();
        this.xSize = 342.0f;
        this.ySize = 264.0f;
        this.offsetX = ((float)this.width - this.xSize) / 2.0f;
        this.offsetY = ((float)this.height - this.ySize) / 2.0f;
        this.field = new GuiTextField(1, this.fontRenderer, 71, 160, 200, 30);
        this.field.setFocused(true);
        this.field.setMaxStringLength(999);
        this.field.setText(this.link);
    }

    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
        super.drawScreen(mouseX, mouseY, partialTicks);
        GlStateManager.pushMatrix();
        GlStateManager.translate((float)this.offsetX, (float)this.offsetY, (float)0.0f);
        RenderUtils.drawTexture(0.0, 0.0, this.xSize, this.ySize, BACKGROUND);
        RenderUtils.drawTexture(90.0, 228.0, 70.0, 25.0, (float)mouseX - this.offsetX, (float)mouseY - this.offsetY, BTN, BTN1);
        RenderUtils.drawText("\u00a7e\u00a7l\u786e\u5b9a\u5934\u50cf", 125.0, 237.0, true, true);
        RenderUtils.drawTexture(178.0, 228.0, 70.0, 25.0, (float)mouseX - this.offsetX, (float)mouseY - this.offsetY, BTN, BTN1);
        RenderUtils.drawText("\u00a7e\u00a7l\u53d6\u6d88\u4fee\u6539", 214.0, 237.0, true, true);
        if (this.field.getText().endsWith(".png") || this.field.getText().endsWith(".jpg")) {
            TextureProvider of = TextureProvider.of(this.field.getText(), DEFHEAD);
            RenderUtils.drawTexture(121.0, 43.0, 100.0, 100.0, of.getTexture(DEFHEAD));
        } else {
            TextureProvider of = TextureProvider.of(this.link, DEFHEAD);
            RenderUtils.drawTexture(121.0, 43.0, 100.0, 100.0, of.getTexture(DEFHEAD));
        }
        RenderUtils.drawText("\u8bf7\u8f93\u5165\u56fe\u7247\u4ee5png\u6216jpg\u7ed3\u5c3e\u7684\u7f51\u9875\u94fe\u63a5", 171.0, 195.0, true, true);
        this.field.drawTextBox();
        GlStateManager.popMatrix();
    }

    protected void mouseClicked(int mouseX, int mouseY, int mouseButton) throws IOException {
        super.mouseClicked(mouseX, mouseY, mouseButton);
        if (new Rectangle(90, 228, 70, 25).contains((float)mouseX - this.offsetX, (float)mouseY - this.offsetY)) {
            TextureProvider of = TextureProvider.of(this.field.getText(), DEFHEAD);
            if (!this.field.getText().endsWith(".png") && !this.field.getText().endsWith(".jpg") || of.getTexture() == null || of.getTexture() == DEFHEAD) {
                this.field.setText("\u8be5\u56fe\u7247\u65e0\u6cd5\u52a0\u8f7d\uff0c\u60a8\u65e0\u6cd5\u4f7f\u7528\u672c\u56fe\u7247\u4f5c\u4e3a\u5934\u50cf");
                this.field.setSelectionPos(0);
            } else {
                MessageSender.sendHead(this.field.getText());
                Minecraft.getMinecraft().displayGuiScreen(null);
            }
        } else if (new Rectangle(178, 228, 70, 25).contains((float)mouseX - this.offsetX, (float)mouseY - this.offsetY)) {
            Minecraft.getMinecraft().displayGuiScreen(null);
        }
    }

    protected void keyTyped(char typedChar, int keyCode) throws IOException {
        super.keyTyped(typedChar, keyCode);
        this.field.textboxKeyTyped(typedChar, keyCode);
    }
}

