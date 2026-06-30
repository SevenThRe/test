/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.mojang.authlib.GameProfile
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.entity.EntityOtherPlayerMP
 *  net.minecraft.client.gui.FontRenderer
 *  net.minecraft.client.gui.GuiChat
 *  net.minecraft.client.gui.GuiScreen
 *  net.minecraft.client.renderer.GlStateManager
 *  net.minecraft.client.renderer.OpenGlHelper
 *  net.minecraft.client.renderer.RenderHelper
 *  net.minecraft.client.renderer.entity.RenderManager
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.util.ResourceLocation
 *  net.minecraft.world.World
 *  net.minecraftforge.fml.client.config.GuiUtils
 */
package eos.moe.ancientdream.client.gui.team;

import com.mojang.authlib.GameProfile;
import eos.moe.ancientdream.client.gui.team.TeamConfirmApplyGui;
import eos.moe.ancientdream.client.utils.RenderUtils;
import eos.moe.ancientdream.client.utils.Utils;
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
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityOtherPlayerMP;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.GuiChat;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraftforge.fml.client.config.GuiUtils;

public class TeamManagerGui
extends GuiScreen {
    private static final ResourceLocation BACKGROUND = new ResourceLocation("ancientdream", "gui/team/main-gui.png");
    private static final ResourceLocation FLAG = new ResourceLocation("ancientdream", "gui/team/flag.png");
    private static final ResourceLocation ADD = new ResourceLocation("ancientdream", "gui/team/add.png");
    private static final ResourceLocation ADD1 = new ResourceLocation("ancientdream", "gui/team/add1.png");
    private static final ResourceLocation BTN = new ResourceLocation("ancientdream", "gui/team/btn.png");
    private static final ResourceLocation BTN1 = new ResourceLocation("ancientdream", "gui/team/btn1.png");
    private static final ResourceLocation SUB = new ResourceLocation("ancientdream", "gui/team/sub.png");
    private float offsetX;
    private float offsetY;
    private float xSize;
    private float ySize;
    private List<PlayerData> teamDataList = new ArrayList<PlayerData>();
    private int hoveredMember = -1;
    private final ScheduledExecutorService timer = Executors.newScheduledThreadPool(Runtime.getRuntime().availableProcessors());
    private boolean isLeader;
    private boolean showHealth;
    private int clickedMember;
    private Point clickPos;

    public TeamManagerGui() {
        this.timer.scheduleAtFixedRate(() -> MessageSender.sendUpdateTeamGui(1), 1L, 1000L, TimeUnit.MILLISECONDS);
    }

    public void loadData(YamlConfiguration yaml) {
        ArrayList<PlayerData> teamDataList = new ArrayList<PlayerData>();
        ConfigurationSection teams = yaml.getConfigurationSection("Teams");
        if (teams != null) {
            for (String key : teams.getKeys(false)) {
                teamDataList.add(new PlayerData(teams.getConfigurationSection(key)));
            }
        }
        this.isLeader = yaml.getBoolean("leader");
        this.showHealth = yaml.getBoolean("showHealth");
        this.teamDataList = teamDataList;
    }

    public void func_73866_w_() {
        super.func_73866_w_();
        this.xSize = 460.0f;
        this.ySize = 208.0f;
        this.offsetX = ((float)this.field_146294_l - this.xSize) / 2.0f;
        this.offsetY = ((float)this.field_146295_m - this.ySize) / 2.0f;
    }

    public void func_73863_a(int mouseX, int mouseY, float partialTicks) {
        this.hoveredMember = -1;
        super.func_73863_a(mouseX, mouseY, partialTicks);
        GlStateManager.func_179094_E();
        GlStateManager.func_179109_b((float)this.offsetX, (float)this.offsetY, (float)0.0f);
        RenderUtils.drawTexture(0.0, 0.0, this.xSize, this.ySize, BACKGROUND);
        for (int i = 0; i < 4; ++i) {
            if (this.teamDataList.size() <= i) {
                RenderUtils.drawTexture(68 + 105 * i - 15, 80.0, 30.0, 30.0, (float)mouseX - this.offsetX, (float)mouseY - this.offsetY, ADD, ADD1);
            } else {
                PlayerData playerData = this.teamDataList.get(i);
                TeamManagerGui.drawEntityOnScreen(68 + 105 * i, 130, 40, this.getEntity(playerData));
                RenderUtils.drawText(playerData.name, 68 + 105 * i, 132.0, true, true);
                if (playerData.isLeader) {
                    RenderUtils.drawTexture(37 + 105 * i, 42.0, 15.0, 15.0, FLAG);
                }
            }
            if (!Utils.onArea(30 + 105 * i, 42.0f, 78.0f, 118.0f, (float)mouseX - this.offsetX, (float)mouseY - this.offsetY)) continue;
            this.hoveredMember = i;
        }
        if (this.isLeader) {
            RenderUtils.drawTexture(285.0, 167.0, 70.0, 25.0, (float)mouseX - this.offsetX, (float)mouseY - this.offsetY, BTN, BTN1);
            RenderUtils.drawText("\u00a7e\u9000\u51fa\u961f\u4f0d", 320.0, 175.0, true, true);
            RenderUtils.drawTexture(360.0, 167.0, 70.0, 25.0, (float)mouseX - this.offsetX, (float)mouseY - this.offsetY, BTN, BTN1);
            RenderUtils.drawText("\u00a7e\u67e5\u770b\u7533\u8bf7\u5217\u8868", 395.0, 175.0, true, true);
        } else {
            RenderUtils.drawTexture(360.0, 167.0, 70.0, 25.0, (float)mouseX - this.offsetX, (float)mouseY - this.offsetY, BTN, BTN1);
            RenderUtils.drawText("\u00a7e\u9000\u51fa\u961f\u4f0d", 395.0, 175.0, true, true);
        }
        RenderUtils.drawTexture(27.0, 167.0, 80.0, 25.0, (float)mouseX - this.offsetX, (float)mouseY - this.offsetY, BTN, BTN1);
        if (this.showHealth) {
            RenderUtils.drawText("\u00a7e\u5173\u95ed\u8840\u91cf\u663e\u793a", 67.0, 175.0, true, true);
        } else {
            RenderUtils.drawText("\u00a7e\u5f00\u542f\u8840\u91cf\u663e\u793a", 67.0, 175.0, true, true);
        }
        GlStateManager.func_179121_F();
        if (this.clickPos != null && this.teamDataList.size() > this.clickedMember) {
            GlStateManager.func_179094_E();
            GlStateManager.func_179109_b((float)this.clickPos.x, (float)this.clickPos.y, (float)100.0f);
            RenderUtils.drawTexture(0.0, 0.0, 80.0, 100.0, SUB);
            if (this.isLeader) {
                RenderUtils.drawTexture(10.0, 12.0, 60.0, 15.0, mouseX - this.clickPos.x, mouseY - this.clickPos.y, BTN, BTN1);
                RenderUtils.drawTexture(10.0, 33.0, 60.0, 15.0, mouseX - this.clickPos.x, mouseY - this.clickPos.y, BTN, BTN1);
                RenderUtils.drawTexture(10.0, 54.0, 60.0, 15.0, mouseX - this.clickPos.x, mouseY - this.clickPos.y, BTN, BTN1);
                RenderUtils.drawTexture(10.0, 75.0, 60.0, 15.0, mouseX - this.clickPos.x, mouseY - this.clickPos.y, BTN, BTN1);
                RenderUtils.drawText("\u00a7a\u53d1\u9001\u4f20\u9001\u8bf7\u6c42", 40.0, 15.0, true, true);
                RenderUtils.drawText("\u00a7e\u53d1\u9001\u9080\u8bf7\u8bf7\u6c42", 40.0, 36.0, true, true);
                RenderUtils.drawText("\u00a7b\u8f6c\u8ba9\u961f\u957f", 40.0, 57.0, true, true);
                RenderUtils.drawText("\u00a7c\u8e22\u51fa\u961f\u4f0d", 40.0, 78.0, true, true);
            } else {
                RenderUtils.drawTexture(10.0, 33.0, 70.0, 15.0, mouseX - this.clickPos.x, mouseY - this.clickPos.y, BTN, BTN1);
                RenderUtils.drawTexture(10.0, 54.0, 70.0, 15.0, mouseX - this.clickPos.x, mouseY - this.clickPos.y, BTN, BTN1);
                RenderUtils.drawText("\u00a7a\u53d1\u9001\u4f20\u9001\u8bf7\u6c42", 40.0, 36.0, true, true);
                RenderUtils.drawText("\u00a7e\u53d1\u9001\u9080\u8bf7\u8bf7\u6c42", 40.0, 57.0, true, true);
            }
            GlStateManager.func_179121_F();
        } else if (this.hoveredMember >= 0 && this.teamDataList.size() > this.hoveredMember) {
            GuiUtils.drawHoveringText((List)this.teamDataList.get(this.hoveredMember).tip, (int)mouseX, (int)mouseY, (int)this.field_146294_l, (int)this.field_146295_m, (int)-1, (FontRenderer)this.field_146289_q);
        }
    }

    public EntityLivingBase getEntity(PlayerData playerData) {
        UUID uuid = playerData.uuid;
        for (Entity entity : this.field_146297_k.field_71441_e.func_72910_y()) {
            if (!entity.func_110124_au().equals(uuid)) continue;
            return (EntityLivingBase)entity;
        }
        return new EntityOtherPlayerMP((World)this.field_146297_k.field_71441_e, new GameProfile(playerData.uuid, playerData.name));
    }

    protected void func_73864_a(int mouseX, int mouseY, int mouseButton) throws IOException {
        super.func_73864_a(mouseX, mouseY, mouseButton);
        if (this.clickPos != null && this.clickedMember != -1 && Utils.onArea(this.clickPos.x, this.clickPos.y, 80.0f, 100.0f, mouseX, mouseY)) {
            if (this.teamDataList.size() > this.clickedMember) {
                if (this.isLeader) {
                    Utils.onAreaThenRun(10.0f, 12.0f, 60.0f, 15.0f, mouseX - this.clickPos.x, mouseY - this.clickPos.y, () -> MessageSender.sendTpPlayer(this.teamDataList.get(this.clickedMember).uuid));
                    Utils.onAreaThenRun(10.0f, 33.0f, 60.0f, 15.0f, mouseX - this.clickPos.x, mouseY - this.clickPos.y, () -> MessageSender.sendInviteTpPlayer(this.teamDataList.get(this.clickedMember).uuid));
                    Utils.onAreaThenRun(10.0f, 54.0f, 60.0f, 15.0f, mouseX - this.clickPos.x, mouseY - this.clickPos.y, () -> MessageSender.sendChangeLeader(this.teamDataList.get(this.clickedMember).uuid));
                    Utils.onAreaThenRun(10.0f, 75.0f, 60.0f, 15.0f, mouseX - this.clickPos.x, mouseY - this.clickPos.y, () -> MessageSender.sendKickPlayer(this.teamDataList.get(this.clickedMember).uuid));
                } else {
                    Utils.onAreaThenRun(10.0f, 33.0f, 60.0f, 15.0f, mouseX - this.clickPos.x, mouseY - this.clickPos.y, () -> MessageSender.sendTpPlayer(this.teamDataList.get(this.clickedMember).uuid));
                    Utils.onAreaThenRun(10.0f, 54.0f, 60.0f, 15.0f, mouseX - this.clickPos.x, mouseY - this.clickPos.y, () -> MessageSender.sendInviteTpPlayer(this.teamDataList.get(this.clickedMember).uuid));
                }
            }
        } else if (mouseButton == 0) {
            if (Utils.onArea(360.0f, 167.0f, 70.0f, 25.0f, (float)mouseX - this.offsetX, (float)mouseY - this.offsetY)) {
                if (this.isLeader) {
                    this.field_146297_k.func_147108_a((GuiScreen)new TeamConfirmApplyGui());
                } else {
                    MessageSender.sendLeaveTeam();
                    this.field_146297_k.func_147108_a(null);
                }
            } else if (Utils.onArea(285.0f, 167.0f, 70.0f, 25.0f, (float)mouseX - this.offsetX, (float)mouseY - this.offsetY) && this.isLeader) {
                MessageSender.sendLeaveTeam();
                this.field_146297_k.func_147108_a(null);
            } else if (Utils.onArea(27.0f, 167.0f, 80.0f, 25.0f, (float)mouseX - this.offsetX, (float)mouseY - this.offsetY)) {
                MessageSender.sendChangeHealth();
            } else if (this.hoveredMember != -1 && this.teamDataList.size() <= this.hoveredMember) {
                this.field_146297_k.func_147108_a((GuiScreen)new GuiChat("/ad team invite "));
            }
        } else if (mouseButton == 1 && this.hoveredMember != -1 && this.teamDataList.size() > this.hoveredMember) {
            this.clickPos = new Point(mouseX, mouseY);
            this.clickedMember = this.hoveredMember;
            return;
        }
        this.clickPos = null;
        this.clickedMember = -1;
    }

    protected void func_73869_a(char typedChar, int keyCode) throws IOException {
        super.func_73869_a(typedChar, keyCode);
    }

    public static void drawEntityOnScreen(int posX, int posY, int scale, EntityLivingBase ent) {
        GlStateManager.func_179142_g();
        GlStateManager.func_179094_E();
        GlStateManager.func_179109_b((float)posX, (float)posY, (float)50.0f);
        GlStateManager.func_179152_a((float)(-scale), (float)scale, (float)scale);
        GlStateManager.func_179114_b((float)180.0f, (float)0.0f, (float)0.0f, (float)1.0f);
        float f = ent.field_70761_aq;
        float f1 = ent.field_70177_z;
        float f2 = ent.field_70125_A;
        float f3 = ent.field_70758_at;
        float f4 = ent.field_70759_as;
        GlStateManager.func_179114_b((float)135.0f, (float)0.0f, (float)1.0f, (float)0.0f);
        RenderHelper.func_74519_b();
        GlStateManager.func_179114_b((float)-135.0f, (float)0.0f, (float)1.0f, (float)0.0f);
        ent.field_70761_aq = 0.0f;
        ent.field_70177_z = 0.0f;
        ent.field_70125_A = 0.0f;
        ent.field_70759_as = ent.field_70177_z;
        ent.field_70758_at = ent.field_70177_z;
        GlStateManager.func_179109_b((float)0.0f, (float)0.0f, (float)0.0f);
        RenderManager rendermanager = Minecraft.func_71410_x().func_175598_ae();
        rendermanager.func_178631_a(180.0f);
        rendermanager.func_178633_a(false);
        GlStateManager.func_179126_j();
        rendermanager.func_188391_a((Entity)ent, 0.0, 0.0, 0.0, 0.0f, 1.0f, false);
        rendermanager.func_178633_a(true);
        ent.field_70761_aq = f;
        ent.field_70177_z = f1;
        ent.field_70125_A = f2;
        ent.field_70758_at = f3;
        ent.field_70759_as = f4;
        GlStateManager.func_179121_F();
        RenderHelper.func_74518_a();
        GlStateManager.func_179101_C();
        GlStateManager.func_179138_g((int)OpenGlHelper.field_77476_b);
        GlStateManager.func_179090_x();
        GlStateManager.func_179138_g((int)OpenGlHelper.field_77478_a);
    }

    public void func_146281_b() {
        this.timer.shutdown();
        super.func_146281_b();
    }

    public class PlayerData {
        private final String name;
        private final UUID uuid;
        private final String level;
        private final String info;
        private final List<String> tip;
        private final boolean isLeader;

        public PlayerData(ConfigurationSection section) {
            this.name = section.getString("name", "");
            this.uuid = UUID.fromString(section.getString("uuid"));
            this.level = section.getString("level", "");
            this.info = section.getString("info", "");
            this.tip = section.getStringList("tip");
            this.isLeader = section.getBoolean("isLeader");
        }
    }
}

