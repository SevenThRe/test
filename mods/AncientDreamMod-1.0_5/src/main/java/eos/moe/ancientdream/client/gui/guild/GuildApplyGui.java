/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.collect.Lists
 *  net.minecraft.client.renderer.GlStateManager
 *  net.minecraft.util.ResourceLocation
 */
package eos.moe.ancientdream.client.gui.guild;

import com.google.common.collect.Lists;
import eos.moe.ancientdream.client.gui.BaseGui;
import eos.moe.ancientdream.client.gui.component.TButton;
import eos.moe.ancientdream.client.utils.RenderUtils;
import eos.moe.ancientdream.client.utils.Utils;
import eos.moe.ancientdream.network.send.MessageSender;
import java.awt.Color;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.util.ResourceLocation;

public class GuildApplyGui
extends BaseGui {
    private GuildData guildData;
    private final ResourceLocation BG = new ResourceLocation("ancientdream", "gui/guild/applylist.png");
    private PlayerData selectPlayer;
    private int page;

    public GuildApplyGui(GuildData guildData) {
        this.guildData = guildData;
        this.addComponent(new TButton(175.0f, 252.0f, 68.0f, 329.0f, 68.0f, 305.5f, 68.5f, 21.0f, 394.0f, 350.0f, btn -> {
            System.out.println("\u901a\u8fc7");
            if (this.selectPlayer != null) {
                Utils.chat("/guild confirm " + this.selectPlayer.name);
                guildData.players.remove(this.selectPlayer);
            }
            Utils.playSound();
        }).setTexture(this.BG));
        this.addComponent(new TButton(279.0f, 252.0f, 0.0f, 329.0f, 0.0f, 305.5f, 68.5f, 21.0f, 394.0f, 350.0f, btn -> {
            System.out.println("\u62d2\u7edd");
            if (this.selectPlayer != null) {
                Utils.chat("/guild refuse " + this.selectPlayer.name);
                guildData.players.remove(this.selectPlayer);
            }
            Utils.playSound();
        }).setTexture(this.BG));
        this.addComponent(new TButton(136.0f, 252.0f, 51.5f, 281.0f, 0.0f, 281.0f, 22.0f, 22.0f, 394.0f, 350.0f, btn -> {
            List partition = Lists.partition(guildData.players, (int)12);
            --this.page;
            if (partition.size() > 0) {
                if (this.page < 0) {
                    this.page = 0;
                }
                if (this.page >= partition.size()) {
                    this.page = partition.size() - 1;
                }
            } else {
                this.page = 0;
            }
            Utils.playSound();
        }).setTexture(this.BG));
        this.addComponent(new TButton(363.0f, 252.0f, 77.5f, 281.0f, 26.0f, 281.0f, 22.0f, 22.0f, 394.0f, 350.0f, btn -> {
            List partition = Lists.partition(guildData.players, (int)12);
            ++this.page;
            if (partition.size() > 0) {
                if (this.page < 0) {
                    this.page = 0;
                }
                if (this.page >= partition.size()) {
                    this.page = partition.size() - 1;
                }
            } else {
                this.page = 0;
            }
            Utils.playSound();
        }).setTexture(this.BG));
    }

    public void initGui() {
        this.setSize(394, 279);
    }

    @Override
    public void draw(int mouseX, int mouseY) {
        RenderUtils.drawTexture(16.0, 41.5, 24.0, 24.0, new ResourceLocation("ancientdream", "gui/guild/" + this.guildData.head));
        GlStateManager.enableBlend();
        RenderUtils.drawTexture(0.0, 0.0, 0.0, 0.0, (double)this.xSize, (double)this.ySize, (double)this.xSize, 350.0, this.BG);
        GlStateManager.disableBlend();
        RenderUtils.drawText(this.guildData.name, 116.0, 45.0, true, true);
        RenderUtils.drawText(this.guildData.level, 116.0, 56.0, true, true);
        RenderUtils.drawText(this.guildData.points, 228.0, 45.0, true, true);
        RenderUtils.drawText(this.guildData.money, 228.0, 56.0, true, true);
        RenderUtils.drawText(this.guildData.playerScore, 339.0, 45.0, true, true);
        RenderUtils.drawText(this.guildData.score, 339.0, 56.0, true, true);
        this.drawComponents((float)mouseX - this.offsetX, (float)mouseY - this.offsetY);
        GlStateManager.translate((float)137.0f, (float)80.0f, (float)0.0f);
        int i = 0;
        List partition = Lists.partition(this.guildData.players, (int)12);
        List list = new ArrayList();
        try {
            list = (List)partition.get(this.page);
        }
        catch (Exception exception) {
            // empty catch block
        }
        for (PlayerData data : list) {
            if (i++ >= 11) break;
            float y = 4.5f + (float)(i - 1) * 15.5f;
            if (data == this.selectPlayer) {
                RenderUtils.drawColor(0.0, (double)y - 4.5, 247.0, 15.0, new Color(255, 255, 255, 100));
            }
            RenderUtils.drawText(data.name, 28.0, y, true, true);
            RenderUtils.drawText(data.money, 84.0, y, true, true);
            RenderUtils.drawText(data.profession, 131.0, y, true, true);
            RenderUtils.drawText(data.pLevel, 177.0, y, true, true);
            RenderUtils.drawText(data.zdl, 225.0, y, true, true);
        }
    }

    @Override
    protected void mouseClicked(int mouseX, int mouseY, int mouseButton) throws IOException {
        super.mouseClicked(mouseX, mouseY, mouseButton);
        int i = 0;
        List partition = Lists.partition(this.guildData.players, (int)12);
        List list = new ArrayList();
        try {
            list = (List)partition.get(this.page);
        }
        catch (Exception exception) {
            // empty catch block
        }
        for (PlayerData data : list) {
            if (i++ >= 11) break;
            if (!Utils.onArea(137.0f, 80.0f + (float)(i - 1) * 15.5f, 250.0f, 15.0f, (float)mouseX - this.offsetX, (float)mouseY - this.offsetY)) continue;
            this.selectPlayer = data;
        }
    }

    protected void keyTyped(char typedChar, int keyCode) throws IOException {
        super.keyTyped(typedChar, keyCode);
        if (keyCode == 1) {
            MessageSender.sendOpenManager();
        }
    }

    public void setGuildData(GuildData guildData) {
        this.guildData = guildData;
    }

    public static class PlayerData {
        private String name;
        private String zdl;
        private String pLevel;
        private String profession;
        private String money;
    }

    public static class GuildData {
        private String head;
        private String name;
        private String level;
        private String memberSize;
        private String leader;
        private String points;
        private String science;
        private String money;
        private String score;
        private String playerScore;
        private String zdl;
        private String sysMessage;
        private String message;
        private String sb1;
        private String sb2;
        private String sb3;
        protected List<PlayerData> players;
    }
}

