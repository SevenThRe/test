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

    public void initGui() {
        super.initGui();
        this.xSize = 460.0f;
        this.ySize = 208.0f;
        this.offsetX = ((float)this.width - this.xSize) / 2.0f;
        this.offsetY = ((float)this.height - this.ySize) / 2.0f;
    }

    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
        this.hoveredMember = -1;
        super.drawScreen(mouseX, mouseY, partialTicks);
        GlStateManager.pushMatrix();
        GlStateManager.translate((float)this.offsetX, (float)this.offsetY, (float)0.0f);
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
        GlStateManager.popMatrix();
        if (this.clickPos != null && this.teamDataList.size() > this.clickedMember) {
            GlStateManager.pushMatrix();
            GlStateManager.translate((float)this.clickPos.x, (float)this.clickPos.y, (float)100.0f);
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
            GlStateManager.popMatrix();
        } else if (this.hoveredMember >= 0 && this.teamDataList.size() > this.hoveredMember) {
            GuiUtils.drawHoveringText((List)this.teamDataList.get(this.hoveredMember).tip, (int)mouseX, (int)mouseY, (int)this.width, (int)this.height, (int)-1, (FontRenderer)this.fontRenderer);
        }
    }

    public EntityLivingBase getEntity(PlayerData playerData) {
        UUID uuid = playerData.uuid;
        for (Entity entity : this.mc.world.getLoadedEntityList()) {
            if (!entity.getUniqueID().equals(uuid)) continue;
            return (EntityLivingBase)entity;
        }
        return new EntityOtherPlayerMP((World)this.mc.world, new GameProfile(playerData.uuid, playerData.name));
    }

    protected void mouseClicked(int mouseX, int mouseY, int mouseButton) throws IOException {
        super.mouseClicked(mouseX, mouseY, mouseButton);
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
                    this.mc.displayGuiScreen((GuiScreen)new TeamConfirmApplyGui());
                } else {
                    MessageSender.sendLeaveTeam();
                    this.mc.displayGuiScreen(null);
                }
            } else if (Utils.onArea(285.0f, 167.0f, 70.0f, 25.0f, (float)mouseX - this.offsetX, (float)mouseY - this.offsetY) && this.isLeader) {
                MessageSender.sendLeaveTeam();
                this.mc.displayGuiScreen(null);
            } else if (Utils.onArea(27.0f, 167.0f, 80.0f, 25.0f, (float)mouseX - this.offsetX, (float)mouseY - this.offsetY)) {
                MessageSender.sendChangeHealth();
            } else if (this.hoveredMember != -1 && this.teamDataList.size() <= this.hoveredMember) {
                this.mc.displayGuiScreen((GuiScreen)new GuiChat("/ad team invite "));
            }
        } else if (mouseButton == 1 && this.hoveredMember != -1 && this.teamDataList.size() > this.hoveredMember) {
            this.clickPos = new Point(mouseX, mouseY);
            this.clickedMember = this.hoveredMember;
            return;
        }
        this.clickPos = null;
        this.clickedMember = -1;
    }

    protected void keyTyped(char typedChar, int keyCode) throws IOException {
        super.keyTyped(typedChar, keyCode);
    }

    public static void drawEntityOnScreen(int posX, int posY, int scale, EntityLivingBase ent) {
        GlStateManager.enableColorMaterial();
        GlStateManager.pushMatrix();
        GlStateManager.translate((float)posX, (float)posY, (float)50.0f);
        GlStateManager.scale((float)(-scale), (float)scale, (float)scale);
        GlStateManager.rotate((float)180.0f, (float)0.0f, (float)0.0f, (float)1.0f);
        float f = ent.renderYawOffset;
        float f1 = ent.rotationYaw;
        float f2 = ent.rotationPitch;
        float f3 = ent.prevRotationYawHead;
        float f4 = ent.rotationYawHead;
        GlStateManager.rotate((float)135.0f, (float)0.0f, (float)1.0f, (float)0.0f);
        RenderHelper.enableStandardItemLighting();
        GlStateManager.rotate((float)-135.0f, (float)0.0f, (float)1.0f, (float)0.0f);
        ent.renderYawOffset = 0.0f;
        ent.rotationYaw = 0.0f;
        ent.rotationPitch = 0.0f;
        ent.rotationYawHead = ent.rotationYaw;
        ent.prevRotationYawHead = ent.rotationYaw;
        GlStateManager.translate((float)0.0f, (float)0.0f, (float)0.0f);
        RenderManager rendermanager = Minecraft.getMinecraft().getRenderManager();
        rendermanager.setPlayerViewY(180.0f);
        rendermanager.setRenderShadow(false);
        GlStateManager.enableDepth();
        rendermanager.renderEntity((Entity)ent, 0.0, 0.0, 0.0, 0.0f, 1.0f, false);
        rendermanager.setRenderShadow(true);
        ent.renderYawOffset = f;
        ent.rotationYaw = f1;
        ent.rotationPitch = f2;
        ent.prevRotationYawHead = f3;
        ent.rotationYawHead = f4;
        GlStateManager.popMatrix();
        RenderHelper.disableStandardItemLighting();
        GlStateManager.disableRescaleNormal();
        GlStateManager.setActiveTexture((int)OpenGlHelper.lightmapTexUnit);
        GlStateManager.disableTexture2D();
        GlStateManager.setActiveTexture((int)OpenGlHelper.defaultTexUnit);
    }

    public void onGuiClosed() {
        this.timer.shutdown();
        super.onGuiClosed();
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

