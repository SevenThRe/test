/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.gui.GuiChat
 *  net.minecraft.client.gui.GuiScreen
 *  net.minecraft.client.renderer.GlStateManager
 *  net.minecraft.util.ResourceLocation
 */
package eos.moe.ancientdream.client.gui.guild;

import eos.moe.ancientdream.client.gui.BaseGui;
import eos.moe.ancientdream.client.gui.component.TButton;
import eos.moe.ancientdream.client.utils.RenderUtils;
import eos.moe.ancientdream.client.utils.Utils;
import java.awt.Color;
import java.io.IOException;
import java.util.List;
import net.minecraft.client.gui.GuiChat;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.util.ResourceLocation;

public class GuildListGui
extends BaseGui {
    private ResourceLocation BG = new ResourceLocation("ancientdream", "gui/guild/guildlist.png");
    private GuildData selectGuild;
    private List<GuildData> list;
    private int page;

    public GuildListGui(boolean hasGuild, List<GuildData> list) {
        this.list = list;
        this.addComponent(new TButton(136.0f, 251.5f, 54.5f, 282.5f, 3.0f, 282.5f, 22.0f, 22.0f, 394.0f, 353.0f, btn -> {
            Utils.chat("/guild list " + (this.page - 1));
            System.out.println("\u4e0a\u4e00\u9875");
            Utils.playSound();
        }).setTexture(this.BG));
        this.addComponent(new TButton(364.5f, 251.5f, 80.5f, 282.5f, 29.0f, 282.5f, 22.0f, 22.0f, 394.0f, 353.0f, btn -> {
            Utils.chat("/guild list " + (this.page + 1));
            System.out.println("\u4e0b\u4e00\u9875");
            Utils.playSound();
        }).setTexture(this.BG));
        if (hasGuild) {
            this.addComponent(new TButton(227.0f, 252.0f, 72.0f, 332.0f, 72.0f, 308.0f, 68.5f, 21.0f, 394.0f, 353.0f, btn -> {
                System.out.println("\u6211\u7684\u516c\u4f1a");
                Utils.chat("/guild manager");
                Utils.playSound();
            }).setTexture(this.BG));
        } else {
            this.addComponent(new TButton(175.0f, 252.0f, 3.0f, 332.0f, 3.0f, 308.0f, 68.5f, 21.0f, 394.0f, 353.0f, btn -> {
                System.out.println("\u521b\u5efa\u516c\u4f1a");
                Utils.playSound();
                Utils.message("\u00a7a\u8bf7\u8f93\u5165\u60a8\u60f3\u8981\u521b\u5efa\u7684\u516c\u4f1a\u540d\u79f0");
                Utils.openGui((GuiScreen)new GuiChat("/guild create "));
            }).setTexture(this.BG));
            this.addComponent(new TButton(279.0f, 252.0f, 141.0f, 332.0f, 141.0f, 308.0f, 68.5f, 21.0f, 394.0f, 353.0f, btn -> {
                System.out.println("\u52a0\u5165\u516c\u4f1a");
                if (this.selectGuild != null) {
                    Utils.chat("/guild join " + this.selectGuild.name);
                }
                Utils.playSound();
            }).setTexture(this.BG));
        }
    }

    public void initGui() {
        this.xSize = 394.0f;
        this.ySize = 278.0f;
        this.offsetX = ((float)this.width - this.xSize) / 2.0f;
        this.offsetY = ((float)this.height - this.ySize) / 2.0f;
    }

    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
        super.drawScreen(mouseX, mouseY, partialTicks);
        GlStateManager.pushMatrix();
        GlStateManager.translate((float)this.offsetX, (float)this.offsetY, (float)0.0f);
        mouseX = (int)((float)mouseX - this.offsetX);
        mouseY = (int)((float)mouseY - this.offsetY);
        if (this.selectGuild != null) {
            RenderUtils.drawTexture(16.0, 41.5, 24.0, 24.0, new ResourceLocation("ancientdream", "gui/guild/" + this.selectGuild.head));
        }
        GlStateManager.enableBlend();
        RenderUtils.drawTexture(0.0, 0.0, 0.0, 0.0, (double)this.xSize, (double)this.ySize, 394.0, 353.0, this.BG);
        GlStateManager.disableBlend();
        if (this.selectGuild != null) {
            int i;
            RenderUtils.drawText(this.selectGuild.leader, 116.0, 45.0, true, true);
            RenderUtils.drawText(this.selectGuild.science, 116.0, 56.0, true, true);
            RenderUtils.drawText(this.selectGuild.points, 228.0, 45.0, true, true);
            RenderUtils.drawText(this.selectGuild.money, 228.0, 56.0, true, true);
            RenderUtils.drawText("0", 339.0, 45.0, true, true);
            RenderUtils.drawText(this.selectGuild.score, 339.0, 56.0, true, true);
            List<String> strings = Utils.splitString(this.selectGuild.sysMessage, 118);
            for (i = 0; i < strings.size(); ++i) {
                RenderUtils.drawText(strings.get(i), 12.0, 91 + i * 12, false, true);
            }
            strings = Utils.splitString(this.selectGuild.message, 118);
            for (i = 0; i < strings.size() && i < 5; ++i) {
                RenderUtils.drawText(strings.get(i), 12.0, 152 + i * 11, false, true);
            }
        }
        this.drawComponents(mouseX, mouseY);
        GlStateManager.translate((float)137.0f, (float)80.0f, (float)0.0f);
        for (int i = 0; i < this.list.size(); ++i) {
            int y = 7 + i * 21;
            GuildData gd = this.list.get(i);
            if (this.selectGuild == gd) {
                RenderUtils.drawColor(0.0, (double)(y - 7), 246.0, 20.0, new Color(255, 255, 255, 100));
            }
            RenderUtils.drawText(gd.displayName, 30.0, y, true, true);
            RenderUtils.drawText(gd.level, 83.0, y, true, true);
            RenderUtils.drawText(gd.memberSize, 130.0, y, true, true);
            RenderUtils.drawText(gd.zdl, 177.0, y, true, true);
            RenderUtils.drawText(gd.zdl1, 224.0, y, true, true);
        }
        GlStateManager.popMatrix();
    }

    @Override
    public void draw(int mouseX, int mouseY) {
    }

    @Override
    protected void mouseClicked(int mouseX, int mouseY, int mouseButton) throws IOException {
        super.mouseClicked(mouseX, mouseY, mouseButton);
        mouseX = (int)((float)mouseX - this.offsetX);
        mouseY = (int)((float)mouseY - this.offsetY);
        mouseX -= 137;
        mouseY -= 80;
        for (int i = 0; i < this.list.size(); ++i) {
            GuildData gd = this.list.get(i);
            Utils.onAreaThenRun(0.0f, i * 21, 180.0f, 21.0f, mouseX, mouseY, () -> {
                this.selectGuild = gd;
                Utils.playSound();
            });
        }
    }

    public void setList(int page, List<GuildData> list) {
        this.page = page;
        this.list = list;
    }

    public static class GuildData {
        private String name;
        private String displayName;
        private String head;
        private String level;
        private String memberSize;
        private String leader;
        private String points;
        private String science;
        private String money;
        private String score;
        private String zdl;
        private String zdl1;
        private String message;
        private String sysMessage;
        private String sb1;
        private String sb2;
        private String sb3;
    }
}

