/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.collect.ImmutableMap
 *  net.minecraft.client.gui.GuiScreen
 *  net.minecraft.client.renderer.GlStateManager
 *  net.minecraft.util.ResourceLocation
 *  org.lwjgl.opengl.GL11
 */
package eos.moe.ancientdream.client.gui.guild;

import com.google.common.collect.ImmutableMap;
import eos.moe.ancientdream.client.gui.guild.GuildDonation;
import eos.moe.ancientdream.client.utils.RenderUtils;
import eos.moe.ancientdream.client.utils.Utils;
import java.awt.Color;
import java.awt.Point;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

public class GuildManagerGui
extends GuiScreen {
    private ResourceLocation BG = new ResourceLocation("ancientdream", "gui/guild/manager.png");
    private ResourceLocation BAR = new ResourceLocation("ancientdream", "gui/guild/scroll_bar.png");
    private Point selectPoint;
    private PlayerData selectPlayer;
    private GuildData guildData;
    private float offsetX;
    private float offsetY;
    private float xSize;
    private float ySize;
    private Point scrollPoint;
    private float maxScrollDistance;
    private float scrollDistance;
    private int page;
    private final Map<String, String> zwMap = ImmutableMap.of((Object)"1", (Object)"\u4f1a\u5458", (Object)"2", (Object)"\u7cbe\u82f1", (Object)"3", (Object)"\u957f\u8001", (Object)"4", (Object)"\u526f\u4f1a\u957f", (Object)"5", (Object)"\u4f1a\u957f");

    public GuildManagerGui(GuildData guildData) {
        this.guildData = guildData;
        this.maxScrollDistance = Math.max(0.0f, (float)guildData.players.size() * 15.5f - 169.0f);
    }

    public void initGui() {
        this.xSize = 394.0f;
        this.ySize = 278.0f;
        this.offsetX = ((float)this.width - this.xSize) / 2.0f;
        this.offsetY = ((float)this.height - this.ySize) / 2.0f;
    }

    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
        int i;
        GlStateManager.pushMatrix();
        GlStateManager.translate((float)this.offsetX, (float)this.offsetY, (float)0.0f);
        mouseX = (int)((float)mouseX - this.offsetX);
        mouseY = (int)((float)mouseY - this.offsetY);
        RenderUtils.drawTexture(16.0, 41.5, 24.0, 24.0, new ResourceLocation("ancientdream", "gui/guild/" + this.guildData.head));
        GlStateManager.enableBlend();
        RenderUtils.drawTexture(0.0, 0.0, 0.0, 0.0, (double)this.xSize, (double)this.ySize, 578.0, 354.0, this.BG);
        GlStateManager.disableBlend();
        RenderUtils.drawText(this.guildData.name, 116.0, 45.0, true, true);
        RenderUtils.drawText(this.guildData.level, 116.0, 56.0, true, true);
        RenderUtils.drawText(this.guildData.points, 228.0, 45.0, true, true);
        RenderUtils.drawText(this.guildData.money, 228.0, 56.0, true, true);
        RenderUtils.drawText(this.guildData.playerScore, 339.0, 45.0, true, true);
        RenderUtils.drawText(this.guildData.score, 339.0, 56.0, true, true);
        RenderUtils.drawTexture(375.0, 70.0f + this.scrollDistance * 158.0f, 10.0, 21.0, this.BAR);
        List<String> strings = Utils.splitString(this.guildData.sysMessage, 118);
        for (i = 0; i < strings.size(); ++i) {
            RenderUtils.drawText(strings.get(i), 12.0, 91 + i * 12, false, true);
        }
        strings = Utils.splitString(this.guildData.message, 118);
        for (i = 0; i < strings.size() && i < 5; ++i) {
            RenderUtils.drawText(strings.get(i), 12.0, 152 + i * 11, false, true);
        }
        if (Utils.onArea(136.5f, 252.0f, 22.0f, 22.0f, mouseX, mouseY)) {
            RenderUtils.drawTexture(136.0, 251.5, 3.0, 282.5, 22.0, 22.0, 578.0, 354.0, this.BG);
        }
        if (Utils.onArea(365.0f, 252.0f, 22.0f, 22.0f, mouseX, mouseY)) {
            RenderUtils.drawTexture(364.5, 251.5, 29.0, 282.5, 22.0, 22.0, 578.0, 354.0, this.BG);
        }
        if (this.page == 0) {
            RenderUtils.drawTexture(158.5, 252.0, 287.0, 333.0, 287.0, 309.5, 68.5, 21.0, 578.0, 354.0, mouseX, mouseY, this.BG);
            RenderUtils.drawTexture(226.5, 252.0, 75.0, 333.0, 75.0, 309.5, 68.5, 21.0, 578.0, 354.0, mouseX, mouseY, this.BG);
            RenderUtils.drawTexture(296.0, 252.0, 147.0, 333.0, 147.0, 309.5, 68.5, 21.0, 578.0, 354.0, mouseX, mouseY, this.BG);
        } else if (this.page == 1 && this.guildData.isLeader) {
            RenderUtils.drawTexture(158.5, 252.0, 357.0, 333.0, 357.0, 309.5, 68.5, 21.0, 578.0, 354.0, mouseX, mouseY, this.BG);
            RenderUtils.drawTexture(226.5, 252.0, 2.5, 333.0, 2.5, 309.5, 68.5, 21.0, 578.0, 354.0, mouseX, mouseY, this.BG);
            RenderUtils.drawTexture(296.0, 252.0, 400.0, 173.0, 400.0, 149.5, 68.5, 21.0, 578.0, 354.0, mouseX, mouseY, this.BG);
        } else if (this.page == 1) {
            RenderUtils.drawTexture(158.5, 252.0, 427.0, 333.0, 427.0, 309.5, 68.5, 21.0, 578.0, 354.0, mouseX, mouseY, this.BG);
            RenderUtils.drawTexture(226.5, 252.0, 216.5, 333.0, 216.5, 309.5, 68.5, 21.0, 578.0, 354.0, mouseX, mouseY, this.BG);
        } else if (this.page == 2) {
            RenderUtils.drawTexture(158.5, 252.0, 427.0, 333.0, 427.0, 309.5, 68.5, 21.0, 578.0, 354.0, mouseX, mouseY, this.BG);
            RenderUtils.drawTexture(226.5, 252.0, 498.5, 333.0, 498.5, 309.5, 68.5, 21.0, 578.0, 354.0, mouseX, mouseY, this.BG);
        }
        GlStateManager.pushMatrix();
        GL11.glEnable((int)3089);
        RenderUtils.scissorBox(this.offsetX + 137.0f, this.offsetY + 80.0f, 240.0f, 169.0f);
        GlStateManager.translate((float)137.0f, (float)80.0f, (float)0.0f);
        for (int j = 0; j < this.guildData.players.size(); ++j) {
            PlayerData data = this.guildData.players.get(j);
            float y = 4.5f + (float)j * 15.5f - this.scrollDistance * this.maxScrollDistance;
            RenderUtils.drawTexture(0.0, (double)(y - 4.0f), 219.0, 289.0, 250.0, 15.0, 578.0, 354.0, this.BG);
            if (data == this.selectPlayer) {
                RenderUtils.drawColor(0.0, (double)y - 4.5, 237.0, 15.0, new Color(255, 255, 255, 100));
            }
            RenderUtils.drawText(data.name, 30.0, y, true, true);
            RenderUtils.drawText(this.zwMap.getOrDefault(data.guildLevel, data.guildLevel), 80.0, y, true, true);
            RenderUtils.drawText(data.score, 126.0, y, true, true);
            RenderUtils.drawText(data.level, 170.0, y, true, true);
            RenderUtils.drawText(data.zdl, 216.0, y, true, true);
        }
        GL11.glDisable((int)3089);
        GlStateManager.popMatrix();
        if (this.selectPlayer != null) {
            GlStateManager.pushMatrix();
            GlStateManager.translate((float)this.selectPoint.x, (float)this.selectPoint.y, (float)0.0f);
            RenderUtils.drawTexture(0.0, 0.0, 434.0, 7.0, 91.0, 77.0, 578.0, 354.0, this.BG);
            if (Utils.onArea(10.0f, 18.0f, 70.5f, 10.5f, mouseX - this.selectPoint.x, mouseY - this.selectPoint.y)) {
                RenderUtils.drawTexture(10.0, 18.0, 433.0, 90.5, 70.5, 10.5, 578.0, 354.0, this.BG);
            }
            if (Utils.onArea(10.0f, 29.5f, 70.5f, 10.5f, mouseX - this.selectPoint.x, mouseY - this.selectPoint.y)) {
                RenderUtils.drawTexture(10.0, 29.5, 433.0, 104.0, 70.5, 10.5, 578.0, 354.0, this.BG);
            }
            if (Utils.onArea(10.0f, 41.0f, 70.5f, 10.5f, mouseX - this.selectPoint.x, mouseY - this.selectPoint.y)) {
                RenderUtils.drawTexture(10.0, 41.0, 433.0, 118.0, 70.5, 10.5, 578.0, 354.0, this.BG);
            }
            if (Utils.onArea(10.0f, 52.5f, 70.5f, 10.5f, mouseX - this.selectPoint.x, mouseY - this.selectPoint.y)) {
                RenderUtils.drawTexture(10.0, 52.5, 433.0, 132.5, 70.5, 10.5, 578.0, 354.0, this.BG);
            }
            GlStateManager.popMatrix();
        }
        if (this.scrollPoint != null) {
            int y = (int)((float)mouseY + this.offsetY - (float)this.scrollPoint.y);
            this.scrollDistance = (float)y / 158.0f;
            this.scrollDistance = Math.min(1.0f, Math.max(0.0f, this.scrollDistance));
        }
        GlStateManager.popMatrix();
    }

    protected void mouseClicked(int mouseX, int mouseY, int mouseButton) throws IOException {
        super.mouseClicked(mouseX, mouseY, mouseButton);
        mouseX = (int)((float)mouseX - this.offsetX);
        mouseY = (int)((float)mouseY - this.offsetY);
        if (Utils.onArea(375.0f, 70.0f + this.scrollDistance * 158.0f, 10.0f, 21.0f, mouseX, mouseY)) {
            this.scrollPoint = new Point((int)((float)mouseX + this.offsetX), (int)((float)mouseY + this.offsetY - this.scrollDistance * 158.0f));
            return;
        }
        Utils.onAreaThenRun(136.5f, 252.0f, 22.0f, 22.0f, mouseX, mouseY, () -> {
            --this.page;
            if (this.page < 0) {
                this.page = 0;
            }
            Utils.playSound();
        });
        Utils.onAreaThenRun(365.0f, 252.0f, 22.0f, 22.0f, mouseX, mouseY, () -> {
            ++this.page;
            if (this.page > 2) {
                this.page = 2;
            }
            if (this.page == 2 && !this.guildData.isLeader) {
                this.page = 1;
            }
            Utils.playSound();
        });
        Utils.onAreaThenRun(158.0f, 252.0f, 68.5f, 22.0f, mouseX, mouseY, () -> {
            if (this.page == 0) {
                Utils.chat("/guild shop");
            } else if (this.page == 1 && this.guildData.isLeader) {
                Utils.chat("/guild upgrade");
            } else if (this.page == 1 || this.page == 2) {
                Utils.openGui(new GuildDonation());
            }
            Utils.playSound();
        });
        Utils.onAreaThenRun(227.0f, 252.0f, 68.5f, 22.0f, mouseX, mouseY, () -> {
            if (this.page == 0) {
                if (this.guildData.isLeader && mouseButton == 1) {
                    Utils.chat("/guild setres");
                } else {
                    Utils.chat("/guild teleport");
                }
            } else if (this.page == 1 && this.guildData.isLeader) {
                Utils.chat("/guild setting");
            } else if (this.page == 1 || this.page == 2) {
                Utils.chat("/guild leave");
            }
            Utils.playSound();
        });
        Utils.onAreaThenRun(296.0f, 252.0f, 68.5f, 22.0f, mouseX, mouseY, () -> {
            if (this.page == 0) {
                Utils.chat("/guild sciencegui");
            } else if (this.page == 1) {
                Utils.chat("/guild applylist");
            }
            Utils.playSound();
        });
        if (this.selectPlayer != null) {
            int level;
            if (Utils.onArea(10.0f, 18.0f, 70.5f, 10.5f, mouseX - this.selectPoint.x, mouseY - this.selectPoint.y)) {
                level = Integer.parseInt(this.selectPlayer.guildLevel) + 1;
                Utils.chat("/guild level " + this.selectPlayer.name + " " + level);
                Utils.openGui(null);
            }
            if (Utils.onArea(10.0f, 29.5f, 70.5f, 10.5f, mouseX - this.selectPoint.x, mouseY - this.selectPoint.y)) {
                level = Integer.parseInt(this.selectPlayer.guildLevel) - 1;
                Utils.chat("/guild level " + this.selectPlayer.name + " " + level);
                Utils.openGui(null);
            }
            if (Utils.onArea(10.0f, 41.0f, 70.5f, 10.5f, mouseX - this.selectPoint.x, mouseY - this.selectPoint.y)) {
                Utils.chat("/guild kick " + this.selectPlayer.name);
            }
            if (Utils.onArea(10.0f, 52.5f, 70.5f, 10.5f, mouseX - this.selectPoint.x, mouseY - this.selectPoint.y)) {
                Utils.chat("/guild transLeader " + this.selectPlayer.name);
            }
        }
        this.selectPoint = null;
        this.selectPlayer = null;
        if (mouseButton == 1 && this.guildData.leader.equals(this.mc.player.getName()) && Utils.onArea(137.0f, 80.0f, 240.0f, 169.0f, mouseX, mouseY)) {
            for (int i = 0; i < this.guildData.players.size(); ++i) {
                float y = (float)i * 15.0f - this.scrollDistance * this.maxScrollDistance;
                PlayerData gd = this.guildData.players.get(i);
                if (!Utils.onArea(137.0f, 80.0f + y, 240.0f, 15.0f, mouseX, mouseY)) continue;
                this.selectPlayer = gd;
                this.selectPoint = new Point(mouseX, mouseY);
                Utils.playSound();
                return;
            }
        }
    }

    protected void mouseReleased(int mouseX, int mouseY, int state) {
        super.mouseReleased(mouseX, mouseY, state);
        this.scrollPoint = null;
    }

    public static class PlayerData {
        private String name;
        private String guildLevel;
        private String score;
        private String level;
        private String zdl;
    }

    public static class GuildData {
        private boolean isLeader;
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

