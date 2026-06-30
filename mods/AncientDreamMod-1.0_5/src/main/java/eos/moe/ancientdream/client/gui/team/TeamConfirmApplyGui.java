/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.gui.FontRenderer
 *  net.minecraft.client.gui.GuiScreen
 *  net.minecraft.client.renderer.GlStateManager
 *  net.minecraft.util.ResourceLocation
 *  net.minecraftforge.fml.client.config.GuiUtils
 *  org.lwjgl.opengl.GL11
 */
package eos.moe.ancientdream.client.gui.team;

import eos.moe.ancientdream.client.gui.team.TeamManagerGui;
import eos.moe.ancientdream.client.utils.RenderUtils;
import eos.moe.ancientdream.client.utils.Utils;
import eos.moe.ancientdream.client.utils.texture.TextureProvider;
import eos.moe.ancientdream.network.send.MessageSender;
import eos.moe.ancientdream.yaml.snakeyaml.configuration.ConfigurationSection;
import eos.moe.ancientdream.yaml.snakeyaml.configuration.file.YamlConfiguration;
import java.awt.Point;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.client.config.GuiUtils;
import org.lwjgl.opengl.GL11;

public class TeamConfirmApplyGui
extends GuiScreen {
    private static ResourceLocation BACKGROUND = new ResourceLocation("ancientdream", "gui/team/confirm.png");
    private static ResourceLocation BTN = new ResourceLocation("ancientdream", "gui/team/btn.png");
    private static ResourceLocation CLOSTBTN = new ResourceLocation("ancientdream", "gui/team/closebutton.png");
    private static ResourceLocation BTN1 = new ResourceLocation("ancientdream", "gui/team/btn1.png");
    private static ResourceLocation SCROLLBAR = new ResourceLocation("ancientdream", "gui/team/scroll_bar.png");
    private static ResourceLocation GUI = new ResourceLocation("ancientdream", "gui/team/gui1.png");
    private static ResourceLocation GUI2 = new ResourceLocation("ancientdream", "gui/team/gui2.png");
    private static ResourceLocation SUB = new ResourceLocation("ancientdream", "gui/team/sub.png");
    private static ResourceLocation DEFHEAD = new ResourceLocation("ancientdream", "gui/team/head.png");
    private float offsetX;
    private float offsetY;
    private float xSize;
    private float ySize;
    private List<PlayerData> playerList = new ArrayList<PlayerData>();
    private float scrollDistance;
    private Point dragScrollBarPotion;
    private float maxDistance;
    private ScheduledExecutorService timer = Executors.newScheduledThreadPool(Runtime.getRuntime().availableProcessors());
    private PlayerData hoverPlayer;

    public TeamConfirmApplyGui() {
        this.timer.scheduleAtFixedRate(() -> MessageSender.sendUpdateTeamGui(2), 1L, 1000L, TimeUnit.MILLISECONDS);
    }

    public void loadData(YamlConfiguration yaml) {
        ArrayList<PlayerData> teamDataList = new ArrayList<PlayerData>();
        ConfigurationSection teams = yaml.getConfigurationSection("Players");
        if (teams != null) {
            for (String key : teams.getKeys(false)) {
                teamDataList.add(new PlayerData(teams.getConfigurationSection(key)));
            }
        }
        this.maxDistance = teamDataList.size() * 56 - 293;
        if (this.maxDistance < 0.0f) {
            this.maxDistance = 0.0f;
        }
        this.playerList = teamDataList;
    }

    public void func_73866_w_() {
        super.func_73866_w_();
        this.xSize = 252.0f;
        this.ySize = 382.0f;
        this.offsetX = ((float)this.field_146294_l - this.xSize) / 2.0f;
        this.offsetY = ((float)this.field_146295_m - this.ySize) / 2.0f;
    }

    public void func_73863_a(int mouseX, int mouseY, float partialTicks) {
        super.func_73863_a(mouseX, mouseY, partialTicks);
        this.hoverPlayer = null;
        GlStateManager.func_179094_E();
        GlStateManager.func_179109_b((float)this.offsetX, (float)this.offsetY, (float)0.0f);
        RenderUtils.drawTexture(0.0, 0.0, this.xSize, this.ySize, BACKGROUND);
        RenderUtils.drawTexture(243.0, 35.0f + this.scrollDistance * 263.0f, 16.0, 64.0, SCROLLBAR);
        RenderUtils.drawTexture(86.0, 335.0, 80.0, 30.0, (float)mouseX - this.offsetX, (float)mouseY - this.offsetY, BTN, BTN1);
        RenderUtils.drawText("\u00a7e\u8fd4\u56de\u961f\u4f0d\u7ba1\u7406", 126.0, 347.0, true, true);
        GL11.glEnable((int)3089);
        RenderUtils.scissorBox((int)this.offsetX + 8, (int)this.offsetY + 40, 240.0f, 294.0f);
        for (int i = 0; i < this.playerList.size(); ++i) {
            PlayerData data = this.playerList.get(i);
            GlStateManager.func_179094_E();
            float scrollVal = (float)(i * 56) - this.scrollDistance * this.maxDistance;
            GlStateManager.func_179109_b((float)4.0f, (float)scrollVal, (float)0.0f);
            TextureProvider of = TextureProvider.of(data.headLink, DEFHEAD);
            RenderUtils.drawTexture(17.0, 46.0, 38.0, 38.0, of.getTexture(DEFHEAD));
            RenderUtils.drawTexture(12.0, 40.0, 220.0, 48.0, GUI2);
            RenderUtils.drawText(data.name, 65.0, 62.0, false, true);
            if (Utils.onArea(8.0f, 40.0f, 240.0f, 280.0f, (float)mouseX - this.offsetX, (float)mouseY - this.offsetY)) {
                RenderUtils.drawTexture(135.0, 55.5, 40.0, 20.0, (float)mouseX - this.offsetX, (float)mouseY - this.offsetY - scrollVal, BTN, BTN1);
                RenderUtils.drawTexture(180.0, 55.5, 40.0, 20.0, (float)mouseX - this.offsetX, (float)mouseY - this.offsetY - scrollVal, BTN, BTN1);
            } else {
                RenderUtils.drawTexture(135.0, 55.5, 40.0, 20.0, BTN);
                RenderUtils.drawTexture(180.0, 55.5, 40.0, 20.0, BTN);
            }
            RenderUtils.drawText("\u00a7a\u901a\u8fc7", 155.0, 60.5, true, true);
            RenderUtils.drawText("\u00a7c\u62d2\u7edd", 200.0, 60.5, true, true);
            GlStateManager.func_179121_F();
            if (!Utils.onArea(8.0f, 40.0f, 240.0f, 280.0f, (float)mouseX - this.offsetX, (float)mouseY - this.offsetY) || !Utils.onArea(8.0f, 40.0f + scrollVal, 120.0f, 48.0f, (float)mouseX - this.offsetX, (float)mouseY - this.offsetY)) continue;
            this.hoverPlayer = data;
        }
        GL11.glDisable((int)3089);
        GlStateManager.func_179121_F();
        if (this.dragScrollBarPotion != null) {
            int y = mouseY - this.dragScrollBarPotion.y;
            this.scrollDistance = (float)y / 263.0f;
            this.scrollDistance = Math.min(1.0f, Math.max(0.0f, this.scrollDistance));
        }
        if (this.hoverPlayer != null) {
            GuiUtils.drawHoveringText((List)this.hoverPlayer.info, (int)mouseX, (int)mouseY, (int)this.field_146294_l, (int)this.field_146295_m, (int)-1, (FontRenderer)this.field_146289_q);
        }
    }

    protected void func_73864_a(int mouseX, int mouseY, int mouseButton) throws IOException {
        super.func_73864_a(mouseX, mouseY, mouseButton);
        if (Utils.onArea(233.5f, 3.0f, 20.0f, 20.0f, (float)mouseX - this.offsetX, (float)mouseY - this.offsetY)) {
            this.field_146297_k.func_147108_a(null);
        } else if (Utils.onArea(this.offsetX + 243.0f, this.offsetY + 35.0f + this.scrollDistance * 263.0f, 16.0f, 64.0f, mouseX, mouseY)) {
            this.dragScrollBarPotion = new Point(mouseX, (int)((float)mouseY - this.scrollDistance * 263.0f));
        } else if (Utils.onArea(86.0f, 335.0f, 80.0f, 30.0f, (float)mouseX - this.offsetX, (float)mouseY - this.offsetY)) {
            this.field_146297_k.func_147108_a((GuiScreen)new TeamManagerGui());
        } else if (Utils.onArea(8.0f, 50.0f, 240.0f, 280.0f, (float)mouseX - this.offsetX, (float)mouseY - this.offsetY)) {
            for (int i = 0; i < this.playerList.size(); ++i) {
                PlayerData data = this.playerList.get(i);
                float scrollVal = (float)(i * 56) - this.scrollDistance * this.maxDistance;
                if (Utils.onArea(135.0f, 63.5f, 40.0f, 20.0f, (float)mouseX - this.offsetX, (float)mouseY - this.offsetY - scrollVal)) {
                    MessageSender.sendConfirmPlayer(data.uuid);
                    continue;
                }
                if (!Utils.onArea(180.0f, 63.5f, 40.0f, 20.0f, (float)mouseX - this.offsetX, (float)mouseY - this.offsetY - scrollVal)) continue;
                MessageSender.sendRefusePlayer(data.uuid);
            }
        }
    }

    protected void func_146286_b(int mouseX, int mouseY, int state) {
        super.func_146286_b(mouseX, mouseY, state);
        this.dragScrollBarPotion = null;
    }

    public void func_146281_b() {
        super.func_146281_b();
        this.timer.shutdown();
    }

    public class PlayerData {
        private String name;
        private UUID uuid;
        private List<String> info;
        private String headLink;

        public PlayerData(ConfigurationSection section) {
            this.name = section.getString("name", "");
            this.uuid = UUID.fromString(section.getString("uuid"));
            this.info = section.getStringList("info");
            this.headLink = section.getString("link", "");
            if (this.headLink.isEmpty()) {
                this.headLink = "gui/team/head.png";
            }
        }
    }
}

