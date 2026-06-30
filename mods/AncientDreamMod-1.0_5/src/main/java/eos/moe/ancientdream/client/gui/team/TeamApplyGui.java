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

import eos.moe.ancientdream.client.gui.team.TeamCreateGui;
import eos.moe.ancientdream.client.gui.team.TeamManagerGui;
import eos.moe.ancientdream.client.utils.RenderUtils;
import eos.moe.ancientdream.client.utils.Utils;
import eos.moe.ancientdream.network.send.MessageSender;
import eos.moe.ancientdream.yaml.snakeyaml.configuration.ConfigurationSection;
import eos.moe.ancientdream.yaml.snakeyaml.configuration.file.YamlConfiguration;
import java.awt.Point;
import java.awt.Rectangle;
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

public class TeamApplyGui
extends GuiScreen {
    private static ResourceLocation BACKGROUND = new ResourceLocation("ancientdream", "gui/team/apply.png");
    private static ResourceLocation BTN = new ResourceLocation("ancientdream", "gui/team/btn.png");
    private static ResourceLocation CLOSTBTN = new ResourceLocation("ancientdream", "gui/team/closebutton.png");
    private static ResourceLocation BTN1 = new ResourceLocation("ancientdream", "gui/team/btn1.png");
    private static ResourceLocation SCROLLBAR = new ResourceLocation("ancientdream", "gui/team/scroll_bar.png");
    private static ResourceLocation GUI = new ResourceLocation("ancientdream", "gui/team/gui1.png");
    private static ResourceLocation SUB = new ResourceLocation("ancientdream", "gui/team/sub.png");
    private float offsetX;
    private float offsetY;
    private float xSize;
    private float ySize;
    private List<TeamData> teamDataList = new ArrayList<TeamData>();
    private float scrollDistance;
    private Point dragScrollBarPotion;
    private float maxDistance;
    private ScheduledExecutorService timer = Executors.newScheduledThreadPool(Runtime.getRuntime().availableProcessors());
    private boolean isLeader;
    private boolean hasTeam;
    private TeamData hoverTeamData;
    private TeamData clickedTeam;
    private long clickTime;

    public TeamApplyGui() {
        this.timer.scheduleAtFixedRate(() -> MessageSender.sendUpdateTeamGui(0), 1L, 1000L, TimeUnit.MILLISECONDS);
    }

    public void loadData(YamlConfiguration yaml) {
        this.isLeader = yaml.getBoolean("isLeader");
        this.hasTeam = yaml.getBoolean("hasTeam");
        ArrayList<TeamData> teamDataList = new ArrayList<TeamData>();
        ConfigurationSection teams = yaml.getConfigurationSection("Teams");
        if (teams != null) {
            for (String key : teams.getKeys(false)) {
                teamDataList.add(new TeamData(teams.getConfigurationSection(key)));
            }
        }
        this.maxDistance = teamDataList.size() * 52 - 240;
        if (this.maxDistance < 0.0f) {
            this.maxDistance = 0.0f;
        }
        this.teamDataList = teamDataList;
    }

    public void initGui() {
        super.initGui();
        this.xSize = 252.0f;
        this.ySize = 382.0f;
        this.offsetX = ((float)this.width - this.xSize) / 2.0f;
        this.offsetY = ((float)this.height - this.ySize) / 2.0f;
    }

    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
        super.drawScreen(mouseX, mouseY, partialTicks);
        this.hoverTeamData = null;
        GlStateManager.pushMatrix();
        GlStateManager.translate((float)this.offsetX, (float)this.offsetY, (float)0.0f);
        RenderUtils.drawTexture(0.0, 0.0, this.xSize, this.ySize, BACKGROUND);
        if (this.isLeader) {
            RenderUtils.drawTexture(26.0, 335.0, 88.0, 30.0, (float)mouseX - this.offsetX, (float)mouseY - this.offsetY, BTN, BTN1);
            RenderUtils.drawText("\u00a7e\u4fee\u6539\u4fe1\u606f", 70.0, 345.0, true, true);
            RenderUtils.drawTexture(135.0, 335.0, 88.0, 30.0, (float)mouseX - this.offsetX, (float)mouseY - this.offsetY, BTN, BTN1);
            RenderUtils.drawText("\u00a7e\u7ba1\u7406\u961f\u4f0d", 179.0, 345.0, true, true);
        } else if (!this.hasTeam) {
            RenderUtils.drawTexture(79.0, 335.0, 88.0, 30.0, (float)mouseX - this.offsetX, (float)mouseY - this.offsetY, BTN, BTN1);
            RenderUtils.drawText("\u00a7e\u521b\u5efa\u961f\u4f0d", 125.0, 345.0, true, true);
        } else {
            RenderUtils.drawTexture(79.0, 335.0, 88.0, 30.0, (float)mouseX - this.offsetX, (float)mouseY - this.offsetY, BTN, BTN1);
            RenderUtils.drawText("\u00a7e\u961f\u4f0d\u4fe1\u606f", 125.0, 345.0, true, true);
        }
        GL11.glEnable((int)3089);
        RenderUtils.scissorBox((int)this.offsetX + 8, (int)this.offsetY + 40, 240.0f, 264.0f);
        for (int i = 0; i < this.teamDataList.size(); ++i) {
            TeamData data = this.teamDataList.get(i);
            GlStateManager.pushMatrix();
            GlStateManager.translate((float)2.0f, (float)((float)(i * 54) - this.scrollDistance * this.maxDistance), (float)0.0f);
            RenderUtils.drawTexture(17.0, 46.0, 38.0, 38.0, data.texture);
            RenderUtils.drawTexture(12.0, 40.0, 220.0, 48.0, GUI);
            RenderUtils.drawText(data.desc, 62.0, 50.0, false, true);
            RenderUtils.drawText(data.desc1, 62.0, 60.0, false, true);
            RenderUtils.drawText(data.leaderName, 62.0, 70.0, false, true);
            RenderUtils.drawText(data.teamSize, 224 - this.fontRenderer.getStringWidth(data.teamSize), 70.0, false, true);
            GlStateManager.popMatrix();
            if (!Utils.onArea(12.0f, 40.0f, 243.0f, 264.0f, (float)mouseX - this.offsetX, (float)mouseY - this.offsetY) || !Utils.onArea(8.0f, (float)(40 + i * 54) - this.scrollDistance * this.maxDistance, 226.0f, 48.0f, (float)mouseX - this.offsetX, (float)mouseY - this.offsetY)) continue;
            this.hoverTeamData = data;
        }
        GL11.glDisable((int)3089);
        RenderUtils.drawTexture(245.0, 30.0f + this.scrollDistance * 240.0f, 16.0, 64.0, SCROLLBAR);
        RenderUtils.drawText("\u53cc\u51fb\u961f\u4f0d\u5373\u53ef\u7533\u8bf7\u52a0\u5165,\u518d\u6b21\u53cc\u51fb\u53d6\u6d88\u7533\u8bf7", 125.0, 317.0, true, true);
        GlStateManager.popMatrix();
        if (this.dragScrollBarPotion != null) {
            int y = mouseY - this.dragScrollBarPotion.y;
            this.scrollDistance = (float)y / 240.0f;
            this.scrollDistance = Math.min(1.0f, Math.max(0.0f, this.scrollDistance));
        }
        if (this.hoverTeamData != null) {
            GuiUtils.drawHoveringText((List)this.hoverTeamData.members, (int)mouseX, (int)mouseY, (int)this.width, (int)this.height, (int)-1, (FontRenderer)this.fontRenderer);
        }
    }

    protected void mouseClicked(int mouseX, int mouseY, int mouseButton) throws IOException {
        super.mouseClicked(mouseX, mouseY, mouseButton);
        if (this.hoverTeamData != null && this.clickedTeam == this.hoverTeamData && System.currentTimeMillis() - this.clickTime < 400L) {
            MessageSender.sendApplyTeam(this.clickedTeam.leaderUUID);
            this.clickedTeam = null;
            return;
        }
        if (this.hoverTeamData != null && mouseButton == 0) {
            this.clickedTeam = this.hoverTeamData;
        }
        this.clickTime = System.currentTimeMillis();
        if (Utils.onArea(247.0f, 30.0f + this.scrollDistance * 240.0f, 16.0f, 64.0f, (float)mouseX - this.offsetX, (float)mouseY - this.offsetY)) {
            this.dragScrollBarPotion = new Point(mouseX, (int)((float)mouseY - this.scrollDistance * 240.0f));
            return;
        }
        if (Utils.onArea(233.5f, 3.0f, 20.0f, 20.0f, (float)mouseX - this.offsetX, (float)mouseY - this.offsetY)) {
            this.mc.displayGuiScreen(null);
        }
        if (this.isLeader) {
            if (new Rectangle(17, 335, 100, 30).contains((float)mouseX - this.offsetX, (float)mouseY - this.offsetY)) {
                this.mc.displayGuiScreen((GuiScreen)new TeamCreateGui(false));
            } else if (new Rectangle(135, 335, 100, 30).contains((float)mouseX - this.offsetX, (float)mouseY - this.offsetY)) {
                this.mc.displayGuiScreen((GuiScreen)new TeamManagerGui());
            }
        } else if (!this.hasTeam) {
            if (new Rectangle(77, 335, 100, 30).contains((float)mouseX - this.offsetX, (float)mouseY - this.offsetY)) {
                this.mc.displayGuiScreen((GuiScreen)new TeamCreateGui(true));
            }
        } else if (new Rectangle(77, 335, 100, 30).contains((float)mouseX - this.offsetX, (float)mouseY - this.offsetY)) {
            this.mc.displayGuiScreen((GuiScreen)new TeamManagerGui());
        }
    }

    protected void mouseReleased(int mouseX, int mouseY, int state) {
        super.mouseReleased(mouseX, mouseY, state);
        this.dragScrollBarPotion = null;
    }

    public void onGuiClosed() {
        super.onGuiClosed();
        this.timer.shutdown();
    }

    public class TeamData {
        private String desc;
        private String desc1;
        private String leaderName;
        private UUID leaderUUID;
        private String teamSize;
        private ResourceLocation texture;
        private List<String> members;

        public TeamData(ConfigurationSection section) {
            this.desc = section.getString("desc", "");
            this.desc1 = "";
            if (this.desc.length() > 18) {
                this.desc1 = this.desc.substring(18);
                this.desc = this.desc.substring(0, 18);
            }
            this.leaderName = section.getString("leader", "");
            this.leaderUUID = UUID.fromString(section.getString("uuid"));
            this.teamSize = section.getString("teamSize", "");
            this.texture = new ResourceLocation("ancientdream", "gui/team/" + section.getString("texture", ""));
            this.members = section.getStringList("members");
        }
    }
}

