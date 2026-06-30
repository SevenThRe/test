/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.collect.ImmutableList
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.renderer.GlStateManager
 *  net.minecraft.client.renderer.texture.TextureUtil
 *  net.minecraft.client.resources.IResource
 *  net.minecraft.util.ResourceLocation
 *  org.lwjgl.opengl.GL11
 */
package eos.moe.ancientdream.client.gui.guild;

import com.google.common.collect.ImmutableList;
import eos.moe.ancientdream.client.gui.BaseGui;
import eos.moe.ancientdream.client.utils.RenderUtils;
import eos.moe.ancientdream.client.utils.Utils;
import eos.moe.ancientdream.network.send.MessageSender;
import java.awt.Point;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.texture.TextureUtil;
import net.minecraft.client.resources.IResource;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

public class GuildScienceGui
extends BaseGui {
    private static final ResourceLocation BG = new ResourceLocation("ancientdream", "gui/guild/science3.png");
    private static int bgw;
    private static int bgh;
    private Point scrollPoint;
    private float maxScrollDistance;
    private float scrollDistance;
    private ScienceData hoverScience;
    private GuiData data;
    private long clickTime;
    private ScienceData clickScience;

    public GuildScienceGui() {
        this.data = new GuiData();
        this.data.money = "99";
        this.data.points = "999";
        this.data.score = "9999";
        this.data.sciences = new ArrayList<ScienceData>();
        ScienceData scienceData = new ScienceData();
        scienceData.name = "\u6d4b\u8bd5";
        scienceData.x = 1000;
        scienceData.y = -50;
        scienceData.icon = "slot.png";
        scienceData.tip = ImmutableList.of((Object)"\u8bf6\u6211\u53bb\u8fd9\u4e2a\u5389\u5bb3");
        this.data.sciences.add(scienceData);
    }

    public GuildScienceGui(GuiData data) {
        this.data = data;
    }

    public void setGuiData(GuiData data) {
        this.data = data;
    }

    public void initGui() {
        super.initGui();
        double scale = this.height < 400 ? 2.0 : 1.0;
        int maxX = 0;
        for (ScienceData science : this.data.sciences) {
            maxX = Math.max(maxX, science.x);
        }
        this.maxScrollDistance = (float)Math.max(-((double)this.width - (double)(maxX + 180) / scale), 0.0);
    }

    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
        super.drawScreen(mouseX, mouseY, partialTicks);
        this.hoverScience = null;
        double scale = this.height < 400 ? 2.0 : 1.0;
        double height = 400.0 / scale;
        double bgw = (double)GuildScienceGui.bgw / scale;
        double subWidth = 20.0 / scale;
        GlStateManager.pushMatrix();
        GlStateManager.translate((double)0.0, (double)(((double)this.height - height) / 2.0), (double)0.0);
        RenderUtils.drawTexture(0.0, 0.0, subWidth, height, new ResourceLocation("ancientdream", "gui/guild/science1.png"));
        RenderUtils.drawTexture(subWidth, 0.0, (double)(this.scrollDistance * this.maxScrollDistance), 0.0, (double)this.width - subWidth * 2.0, height, bgw, height, BG);
        RenderUtils.drawTexture((double)this.width - subWidth, 0.0, subWidth, height, new ResourceLocation("ancientdream", "gui/guild/science2.png"));
        this.drawHorizontalLine((int)(subWidth - 4.0), (int)((double)this.width - subWidth + 4.0), (int)(height + 9.0), -16777216);
        RenderUtils.drawTexture(subWidth + (double)this.scrollDistance * ((double)(this.width - 37) - subWidth * 2.0), height + 1.0, 37.0, 16.5, new ResourceLocation("ancientdream", "gui/guild/sciencebar.png"));
        GL11.glEnable((int)3089);
        RenderUtils.scissorBox((int)subWidth, 0.0f, (int)((double)this.width - 2.0 * subWidth), this.height);
        for (ScienceData science : this.data.sciences) {
            if (science.x == 0 && science.y == 0) continue;
            double x = (double)science.x / scale - (double)(this.scrollDistance * this.maxScrollDistance);
            double y = height / 2.0 + (double)science.y / scale;
            if (science.type == 1) {
                RenderUtils.drawTexture(x, y, 150.0 / scale, 46.0 / scale, new ResourceLocation("ancientdream", "gui/guild/sciencebtn1.png"));
            } else if (science.type == 2) {
                RenderUtils.drawTexture(x, y, 150.0 / scale, 46.0 / scale, new ResourceLocation("ancientdream", "gui/guild/sciencebtn2.png"));
            } else {
                RenderUtils.drawTexture(x, y, 150.0 / scale, 46.0 / scale, new ResourceLocation("ancientdream", "gui/guild/sciencebtn.png"));
            }
            RenderUtils.drawTexture(x + 13.5 / scale, y + 10.5 / scale, 22.5 / scale, 22.5 / scale, new ResourceLocation("ancientdream", "gui/guild/" + science.icon));
            RenderUtils.drawText(science.name, x + 50.5 / scale + 72.0 / scale / 2.0, y + 23.0 / scale - 2.5, true, true);
            if (!Utils.onArea(x, ((double)this.height - height) / 2.0 + y, 150.0 / scale, 42.0 / scale, (double)mouseX, (double)mouseY)) continue;
            this.hoverScience = science;
        }
        GL11.glDisable((int)3089);
        GlStateManager.popMatrix();
        RenderUtils.drawText("Tip: \u53cc\u51fb\u79d1\u6280\u5373\u53ef\u8fdb\u884c\u7814\u7a76!  \u5f53\u524d\u516c\u4f1a\u8d44\u91d1: \u91d1\u5e01x" + this.data.money + ",\u70b9\u5238x" + this.data.points + ",\u8d21\u732ex" + this.data.score, 5.0, this.height - 12, false, true);
        if (this.hoverScience != null) {
            this.drawHoveringText(this.hoverScience.tip, mouseX, mouseY);
        }
        if (this.scrollPoint != null) {
            int x = mouseX - this.scrollPoint.x;
            this.scrollDistance = (float)((double)x / ((double)(this.width - 37) - subWidth * 2.0));
            this.scrollDistance = Math.min(1.0f, Math.max(0.0f, this.scrollDistance));
        }
    }

    @Override
    public void draw(int mouseX, int mouseY) {
    }

    @Override
    protected void mouseClicked(int mouseX, int mouseY, int mouseButton) throws IOException {
        super.mouseClicked(mouseX, mouseY, mouseButton);
        if (this.hoverScience != null) {
            if (System.currentTimeMillis() - this.clickTime < 200L && this.clickScience == this.hoverScience) {
                Utils.chat("/guild science " + this.hoverScience.name);
                this.clickTime = 0L;
                this.clickScience = null;
            } else {
                this.clickTime = System.currentTimeMillis();
                this.clickScience = this.hoverScience;
            }
        } else {
            this.clickTime = 0L;
            this.clickScience = null;
        }
        double scale = this.height < 400 ? 2.0 : 1.0;
        double subWidth = 20.0 / scale;
        if (Utils.onArea(20.0 / scale + (double)this.scrollDistance * ((double)(this.width - 37) - subWidth * 2.0), ((double)this.height - 400.0 / scale) / 2.0 + 400.0 / scale + 1.0, 37.0, 16.5, (double)mouseX, (double)mouseY)) {
            this.scrollPoint = new Point((int)((double)mouseX - (double)this.scrollDistance * ((double)(this.width - 37) - subWidth * 2.0)), mouseY);
        }
    }

    protected void keyTyped(char typedChar, int keyCode) throws IOException {
        super.keyTyped(typedChar, keyCode);
        if (keyCode == 1) {
            MessageSender.sendOpenManager();
        }
    }

    protected void mouseReleased(int mouseX, int mouseY, int state) {
        super.mouseReleased(mouseX, mouseY, state);
        this.scrollPoint = null;
    }

    static {
        try {
            IResource resource = Minecraft.getMinecraft().getResourceManager().getResource(BG);
            BufferedImage bufferedimage = TextureUtil.readBufferedImage((InputStream)resource.getInputStream());
            bgw = bufferedimage.getWidth();
            bgh = bufferedimage.getHeight();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static class ScienceData {
        private String name;
        private int x;
        private int y;
        private String icon;
        private int type;
        protected List<String> tip;
    }

    public static class GuiData {
        private String money;
        private String points;
        private String score;
        protected List<ScienceData> sciences;
    }
}

