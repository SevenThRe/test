/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.renderer.GlStateManager
 *  net.minecraft.client.renderer.RenderHelper
 *  net.minecraft.item.ItemStack
 *  net.minecraft.network.PacketBuffer
 *  net.minecraft.util.ResourceLocation
 *  org.lwjgl.opengl.GL11
 */
package eos.moe.ancientdream.client.gui.guild;

import eos.moe.ancientdream.client.gui.BaseGui;
import eos.moe.ancientdream.client.utils.RenderUtils;
import eos.moe.ancientdream.client.utils.Utils;
import eos.moe.ancientdream.network.send.MessageSender;
import java.awt.Point;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.item.ItemStack;
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

public class GuildScienceGuiRe
extends BaseGui {
    private static final ResourceLocation BG = new ResourceLocation("ancientdream", "gui/guild/science01.png");
    private static final ResourceLocation SUB = new ResourceLocation("ancientdream", "gui/guild/science03.png");
    private int type = 3;
    private float confirmY;
    private String hoverScienceName;
    private ItemStack hoverItem;
    private final GuiData guiData;
    private ScienceData selectScience;
    private ScienceData hoverScience;
    private Point scrollPoint;
    private float scrollDistance;
    private Point scrollPoint2;
    private float scrollDistance2;

    public GuildScienceGuiRe(GuiData guiData) {
        this.guiData = guiData;
    }

    public void func_73866_w_() {
        this.setSize(400, 250);
    }

    @Override
    public void draw(int mouseX, int mouseY) {
        int y;
        this.hoverScience = null;
        this.hoverItem = null;
        this.hoverScienceName = null;
        RenderUtils.drawTexture(0.0, 0.0, 0.0, 0.0, (double)this.xSize, (double)this.ySize, 400.0, 400.0, BG);
        if (this.type == 3) {
            RenderUtils.drawTexture(7.0, 5.0, 18.0, 250.0, 18.0, 266.0, 16.0, 16.0, 400.0, 400.0, (float)mouseX - this.offsetX, (float)mouseY - this.offsetY, BG);
            RenderUtils.drawText("\u6240\u6709\u79d1\u6280", 26.0, 9.0, false, true);
        } else if (this.type == 2) {
            RenderUtils.drawTexture(7.0, 5.0, 50.0, 250.0, 50.0, 266.0, 16.0, 16.0, 400.0, 400.0, (float)mouseX - this.offsetX, (float)mouseY - this.offsetY, BG);
            RenderUtils.drawText("\u5df2\u7814\u7a76\u79d1\u6280", 26.0, 9.0, false, true);
        } else if (this.type == 1) {
            RenderUtils.drawTexture(7.0, 5.0, 34.0, 250.0, 34.0, 266.0, 16.0, 16.0, 400.0, 400.0, (float)mouseX - this.offsetX, (float)mouseY - this.offsetY, BG);
            RenderUtils.drawText("\u672a\u7814\u7a76\u79d1\u6280", 26.0, 9.0, false, true);
        }
        RenderUtils.drawTexture(190.0, (double)(26.0f + this.scrollDistance * 195.0f), 0.0, 268.0, 10.0, 28.0, 400.0, 400.0, BG);
        RenderUtils.drawTexture(389.0, (double)(26.0f + this.scrollDistance2 * 195.0f), 0.0, 268.0, 10.0, 28.0, 400.0, 400.0, BG);
        List collect = this.guiData.sciences.stream().filter(scienceData -> this.type == 3 || this.type == 2 && ((ScienceData)scienceData).type == 1 || this.type == 1 && (((ScienceData)scienceData).type == 0 || ((ScienceData)scienceData).type >= 2)).collect(Collectors.toList());
        GL11.glEnable((int)3089);
        RenderUtils.scissorBox(16.0f + this.offsetX, 32.0f + this.offsetY, 999.0f, 205.0f);
        int maxScroll = Math.max(0, collect.size() * 15 - 200);
        for (int i = 0; i < collect.size(); ++i) {
            ScienceData scienceData2 = (ScienceData)collect.get(i);
            RenderUtils.drawTexture(16.0, (double)((float)(36 + i * 15) - this.scrollDistance * (float)maxScroll), 0.0, 296.0, 0.0, 309.0, 166.0, 13.0, 400.0, 400.0, (float)mouseX - this.offsetX, (float)mouseY - this.offsetY, BG);
            RenderUtils.drawText(scienceData2.name, 99.0, (float)(36 + i * 15) - this.scrollDistance * (float)maxScroll + 2.5f, true, true);
            if (!Utils.onArea(16.0f, 32.0f, 999.0f, 205.0f, (float)mouseX - this.offsetX, (float)mouseY - this.offsetY) || !Utils.onArea(16.0f, (float)(36 + i * 15) - this.scrollDistance * (float)maxScroll, 166.0f, 13.0f, (float)mouseX - this.offsetX, (float)mouseY - this.offsetY)) continue;
            this.hoverScience = scienceData2;
        }
        if (this.selectScience != null) {
            int i;
            float addY = -this.scrollDistance2 * 500.0f;
            RenderUtils.drawTexture(212.0, (double)(32.0f + addY), 0.0, 296.0, 0.0, 309.0, 166.0, 13.0, 400.0, 400.0, (float)mouseX - this.offsetX, (float)mouseY - this.offsetY, BG);
            RenderUtils.drawText("\u00a7e\u00a7l" + this.selectScience.name, 300.0, 35.0f + addY, true, true);
            if (this.selectScience.itemStacks.size() > 0) {
                RenderUtils.drawText("\u00a7l\u7814\u7a76\u6750\u6599", 210.0, 50.0f + addY, false, true);
            }
            float y2 = 65.0f + addY;
            float x = 210.0f;
            if (this.selectScience.itemStacks.size() == 0) {
                y2 -= 30.0f;
            }
            RenderHelper.func_74520_c();
            for (i = 0; i < this.selectScience.itemStacks.size(); ++i) {
                ItemStack itemStack = (ItemStack)this.selectScience.itemStacks.get(i);
                RenderUtils.drawTexture((double)x, (double)y2, 0.0, 250.0, 18.0, 18.0, 400.0, 400.0, BG);
                GlStateManager.func_179094_E();
                GlStateManager.func_179109_b((float)(x + 1.0f), (float)(y2 + 1.0f), (float)0.0f);
                this.field_146296_j.func_180450_b(itemStack, 0, 0);
                this.field_146296_j.func_180453_a(this.field_146289_q, itemStack, 0, 0, null);
                GlStateManager.func_179121_F();
                if (Utils.onArea(x + 1.0f, y2 + 1.0f, 16.0f, 16.0f, (float)mouseX - this.offsetX, (float)mouseY - this.offsetY)) {
                    this.hoverItem = itemStack;
                }
                if (i % 9 != 0) {
                    x += 18.0f;
                }
                if (i == 0 || i % 9 != 0) continue;
                x = 210.0f;
                y2 += 18.0f;
            }
            RenderHelper.func_74518_a();
            RenderUtils.drawText("\u00a7l\u7814\u7a76\u9700\u6c42", 210.0, y2 + 23.0f, false, true);
            for (i = 0; i < this.selectScience.info.size(); ++i) {
                String info = this.selectScience.info.get(i);
                if (info.startsWith("science|")) {
                    RenderUtils.drawTexture(212.0, (double)(y2 + 35.0f), 0.0, 296.0, 0.0, 309.0, 166.0, 13.0, 400.0, 400.0, (float)mouseX - this.offsetX, (float)mouseY - this.offsetY, BG);
                    RenderUtils.drawText(info.substring(8), 295.0, y2 + 38.0f, true, true);
                    if (Utils.onArea(0.0f, y2 + 32.0f, 999.0f, 205.0f, (float)mouseX - this.offsetX, (float)mouseY - this.offsetY) && Utils.onArea(212.0f, y2 + 35.0f, 166.0f, 13.0f, (float)mouseX - this.offsetX, (float)mouseY - this.offsetY)) {
                        this.hoverScienceName = info.substring(8);
                    }
                    y2 += 15.0f;
                    continue;
                }
                RenderUtils.drawText(info, 210.0, y2 + 35.0f, false, true);
                y2 += 13.0f;
            }
            this.confirmY = y2 + 45.0f;
            if (this.selectScience.type == 0) {
                RenderUtils.drawTexture(247.0, (double)(y2 + 45.0f), 0.0, 353.0, 0.0, 366.0, 89.0, 13.0, 400.0, 400.0, (float)mouseX - this.offsetX, (float)mouseY - this.offsetY, BG);
            } else if (this.selectScience.type == 1) {
                RenderUtils.drawTexture(247.0, (double)(y2 + 45.0f), 0.0, 379.0, 89.0, 13.0, 400.0, 400.0, BG);
            } else if (this.selectScience.type == 2) {
                RenderUtils.drawTexture(247.0, (double)(y2 + 45.0f), 90.0, 353.0, 90.0, 366.0, 89.0, 13.0, 400.0, 400.0, (float)mouseX - this.offsetX, (float)mouseY - this.offsetY, BG);
            } else {
                RenderUtils.drawTexture(247.0, (double)(y2 + 45.0f), 166.0, 296.0, 166.0, 309.0, 89.0, 13.0, 400.0, 400.0, (float)mouseX - this.offsetX, (float)mouseY - this.offsetY, BG);
                RenderUtils.drawText("\u5269\u4f59: " + Utils.secondToTime(this.selectScience.type), 291.0, y2 + 48.0f, true, true);
            }
        }
        GL11.glDisable((int)3089);
        if (this.hoverItem != null) {
            this.func_146285_a(this.hoverItem, (int)((float)mouseX - this.offsetX), (int)((float)mouseY - this.offsetY));
        }
        if (this.scrollPoint != null) {
            y = mouseY - this.scrollPoint.y;
            this.scrollDistance = (float)y / 195.0f;
            this.scrollDistance = Math.min(1.0f, Math.max(0.0f, this.scrollDistance));
        }
        if (this.scrollPoint2 != null) {
            y = mouseY - this.scrollPoint2.y;
            this.scrollDistance2 = (float)y / 195.0f;
            this.scrollDistance2 = Math.min(1.0f, Math.max(0.0f, this.scrollDistance2));
        }
    }

    @Override
    protected void func_73864_a(int mouseX, int mouseY, int mouseButton) throws IOException {
        super.func_73864_a(mouseX, mouseY, mouseButton);
        mouseX = (int)((float)mouseX - this.offsetX);
        mouseY = (int)((float)mouseY - this.offsetY);
        if (Utils.onArea(190.0f, 26.0f + this.scrollDistance * 195.0f, 10.0f, 28.0f, mouseX, mouseY)) {
            this.scrollPoint = new Point((int)((float)mouseX + this.offsetX), (int)((float)mouseY + this.offsetY - this.scrollDistance * 195.0f));
            Utils.playSound();
        } else if (Utils.onArea(389.0f, 26.0f + this.scrollDistance2 * 195.0f, 10.0f, 28.0f, mouseX, mouseY)) {
            this.scrollPoint2 = new Point((int)((float)mouseX + this.offsetX), (int)((float)mouseY + this.offsetY - this.scrollDistance2 * 195.0f));
            Utils.playSound();
        } else if (this.hoverScienceName != null) {
            this.guiData.sciences.stream().filter(a -> ((ScienceData)a).name.equalsIgnoreCase(this.hoverScienceName)).findFirst().ifPresent(scienceData -> {
                this.selectScience = scienceData;
            });
            Utils.playSound();
        } else if (this.selectScience != null && Utils.onArea(247.0f, this.confirmY, 89.0f, 13.0f, mouseX, mouseY)) {
            Utils.chat("/guild science " + this.selectScience.name + " 1");
            Utils.playSound();
        } else if (Utils.onArea(7.0f, 5.0f, 16.0f, 16.0f, mouseX, mouseY)) {
            if (this.type == 2) {
                this.type = 3;
            } else if (this.type == 3) {
                this.type = 1;
            } else if (this.type == 1) {
                this.type = 2;
            }
            Utils.playSound();
        } else if (this.hoverScience != null) {
            this.selectScience = this.hoverScience;
            Utils.playSound();
        }
    }

    protected void func_146286_b(int mouseX, int mouseY, int state) {
        super.func_146286_b(mouseX, mouseY, state);
        this.scrollPoint = null;
        this.scrollPoint2 = null;
    }

    protected void func_73869_a(char typedChar, int keyCode) throws IOException {
        super.func_73869_a(typedChar, keyCode);
        if (keyCode == 1) {
            MessageSender.sendOpenManager();
        }
    }

    public static class ScienceData {
        private String name;
        private List<ItemStack> itemStacks;
        private int type;
        protected List<String> info;

        public ScienceData(PacketBuffer buffer) throws IOException {
            int i;
            this.name = buffer.func_150789_c(32768);
            this.itemStacks = new ArrayList<ItemStack>();
            this.info = new ArrayList<String>();
            int size = buffer.readInt();
            for (i = 0; i < size; ++i) {
                this.itemStacks.add(buffer.func_150791_c());
            }
            this.type = buffer.readInt();
            size = buffer.readInt();
            for (i = 0; i < size; ++i) {
                this.info.add(buffer.func_150789_c(32768));
            }
        }

        public ScienceData() {
        }
    }

    public static class GuiData {
        private String money;
        private String points;
        private String score;
        protected List<ScienceData> sciences;

        public GuiData(PacketBuffer buffer) throws IOException {
            this.money = buffer.func_150789_c(32768);
            this.points = buffer.func_150789_c(32768);
            this.score = buffer.func_150789_c(32768);
            this.sciences = new ArrayList<ScienceData>();
            int size = buffer.readInt();
            for (int i = 0; i < size; ++i) {
                this.sciences.add(new ScienceData(buffer));
            }
        }

        public GuiData() {
        }
    }
}

