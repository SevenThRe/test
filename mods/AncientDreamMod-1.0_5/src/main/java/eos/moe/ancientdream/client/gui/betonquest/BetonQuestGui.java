/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.gui.inventory.GuiInventory
 *  net.minecraft.client.renderer.GlStateManager
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.util.ResourceLocation
 */
package eos.moe.ancientdream.client.gui.betonquest;

import eos.moe.ancientdream.client.gui.BaseGui;
import eos.moe.ancientdream.client.utils.RenderUtils;
import eos.moe.ancientdream.client.utils.Utils;
import eos.moe.ancientdream.network.send.MessageSender;
import java.awt.Color;
import java.io.IOException;
import java.util.List;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.inventory.GuiInventory;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.ResourceLocation;

public class BetonQuestGui
extends BaseGui {
    private final ResourceLocation BTN1 = new ResourceLocation("ancientdream", "gui/betonquest/widgets.png");
    private final ResourceLocation BTN = new ResourceLocation("ancientdream", "gui/betonquest/widget1s.png");
    private int lastHovered = -1;
    private Data data;
    protected boolean finished;
    protected EntityLivingBase entity;

    public BetonQuestGui(Data data) {
        this.data = data;
        this.entity = (EntityLivingBase)Minecraft.func_71410_x().field_71476_x.field_72308_g;
    }

    public void setData(Data data) {
        this.data = data;
        this.finished = false;
        this.lastHovered = -1;
    }

    @Override
    public void draw(int mouseX, int mouseY) {
        double x = (double)this.field_146294_l * 0.15;
        double y = (double)this.field_146295_m * 0.6;
        double scale = (double)this.field_146295_m * 0.0035;
        RenderUtils.drawColor(0.0, y, (double)this.field_146294_l, (double)this.field_146295_m * 0.27, new Color(0, 0, 0, 200));
        GlStateManager.func_179094_E();
        GlStateManager.func_179137_b((double)x, (double)(y + (double)this.field_146295_m * 0.02), (double)0.0);
        GlStateManager.func_179139_a((double)scale, (double)scale, (double)scale);
        for (int i = 0; i < this.data.message.size(); ++i) {
            RenderUtils.drawText((String)this.data.message.get(i), 0.0, i * 10, false, true);
        }
        GlStateManager.func_179121_F();
        boolean hov = false;
        for (int i = 0; i < this.data.replys.size(); ++i) {
            String reply = (String)this.data.replys.get(i);
            x = (double)this.field_146294_l * 0.7;
            y = (double)this.field_146295_m * 0.6 - (double)((this.data.replys.size() - i) * 22);
            RenderUtils.drawTexture(x - 100.0, y, 200.0, 18.0, mouseX, mouseY, this.BTN1, this.BTN);
            RenderUtils.drawText(reply, x, y + 5.0, true, true);
            if (!Utils.onArea(x - 100.0, y, 200.0, 18.0, (double)mouseX, (double)mouseY)) continue;
            hov = true;
            if (this.lastHovered == i) continue;
            this.lastHovered = i;
            Utils.playSound();
        }
        if (!hov) {
            this.lastHovered = -1;
        }
        int entityX = (int)((double)this.field_146294_l * 0.075);
        int entityY = (int)((double)this.field_146295_m * 0.82);
        int entityScale = (int)((double)this.field_146294_l * 0.07);
        if (this.entity != null) {
            GlStateManager.func_179131_c((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
            GuiInventory.func_147046_a((int)entityX, (int)entityY, (int)entityScale, (float)(entityX - mouseX), (float)((float)entityY - (float)entityScale * 1.7f - (float)mouseY), (EntityLivingBase)this.entity);
        }
    }

    @Override
    protected void func_73864_a(int mouseX, int mouseY, int mouseButton) throws IOException {
        if (this.lastHovered != -1 && !this.finished) {
            Utils.playSound();
            this.finished = true;
            MessageSender.sendBetonQuest(this.lastHovered);
        }
    }

    public void func_146281_b() {
        super.func_146281_b();
        MessageSender.sendBetonQuest(-1);
    }

    public static class Data {
        private List<String> message;
        private List<String> replys;

        public Data(List<String> message, List<String> replys) {
            this.message = message;
            this.replys = replys;
        }
    }
}

