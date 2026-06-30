/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.gui.ScaledResolution
 *  net.minecraft.client.gui.inventory.GuiContainer
 *  net.minecraft.client.gui.inventory.GuiInventory
 *  net.minecraft.client.renderer.GlStateManager
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.entity.monster.EntityZombie
 *  net.minecraft.inventory.Container
 *  net.minecraft.inventory.IInventory
 *  net.minecraft.inventory.Slot
 *  net.minecraft.util.math.MathHelper
 *  net.minecraft.world.World
 *  net.minecraftforge.fml.client.FMLClientHandler
 */
package eos.moe.dragoncore;

import eos.moe.dragoncore.aia;
import eos.moe.dragoncore.dh;
import eos.moe.dragoncore.gda;
import eos.moe.dragoncore.nka;
import eos.moe.dragoncore.raa;
import eos.moe.dragoncore.rda;
import eos.moe.dragoncore.sa;
import java.util.ArrayList;
import java.util.List;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.gui.inventory.GuiInventory;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.monster.EntityZombie;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import net.minecraftforge.fml.client.FMLClientHandler;

public class jca
extends GuiContainer
implements sa {
    private List<dh<String, rda>> c;
    private gda q;
    private EntityZombie b;
    private String o = "";
    private dh<Float, Float> y = new dh<Float, Float>(Float.valueOf(27.0f), Float.valueOf(700.0f));
    private dh<Float, Float> k = new dh<Float, Float>(Float.valueOf(-20.0f), Float.valueOf(-50.0f));
    private float ALLATORIxDEMO = 30.0f;

    public jca(IInventory a4, IInventory a5) {
        super((Container)new nka(a4, a5));
        jca a6;
        a6.c = new ArrayList<dh<String, rda>>();
        raa.r.m.forEach((a2, a3) -> {
            jca a4;
            a4.c.add(new dh<String, rda>((String)a2, (rda)a3));
        });
        a6.b = new EntityZombie((World)FMLClientHandler.instance().getWorldClient());
    }

    public void initGui() {
        int a2;
        jca a3;
        ScaledResolution a4 = new ScaledResolution(a3.mc);
        a3.xSize = a4.getScaledWidth();
        a3.ySize = a4.getScaledHeight();
        super.initGui();
        int a5 = 18;
        for (a2 = 0; a2 < 3; ++a2) {
            for (int a6 = 0; a6 < 9; ++a6) {
                Slot a7 = (Slot)a3.inventorySlots.inventorySlots.get(a6 + a2 * 9);
                a7.yPos = a3.height + 1 - 76 - 5 + a2 * a5;
            }
        }
        for (a2 = 0; a2 < 9; ++a2) {
            Slot a8 = (Slot)a3.inventorySlots.inventorySlots.get(a2 + 27);
            a8.yPos = a3.height + 1 - 5 - a5;
        }
        a2 = a3.width - 162 - 25;
        int a9 = a3.height - 15 - 14 - 15 + 26;
        a2 = MathHelper.clamp((int)a2, (int)0, (int)200);
        a3.q = new gda(a3, a3.width - 100, 12, 95, Math.max(0, a9), 14, a3.width, a3.height, a3);
        a3.buttonList.add(new aia(a3, 0, a3.width / 2 - 20 - 20, a3.height - 50, "\u2190"));
        a3.buttonList.add(new aia(a3, 0, a3.width / 2 + 20, a3.height - 50, "\u2192"));
        a3.buttonList.add(new aia(a3, 0, a3.width - 120, a3.height / 2 - 20 - 20, "\u2191"));
        a3.buttonList.add(new aia(a3, 0, a3.width - 120, a3.height / 2 + 20, "\u2193"));
        a3.buttonList.add(new aia(a3, 0, a3.width / 2 - 20 - 20, 10, "-"));
        a3.buttonList.add(new aia(a3, 0, a3.width / 2 + 20, 10, "+"));
        a3.buttonList.add(new aia(a3, 0, 0, a3.height / 2 - 20, "\u2190\u2190"));
        a3.buttonList.add(new aia(a3, 0, 40, a3.height / 2 - 20, "\u2192\u2192"));
        a3.buttonList.add(new aia(a3, 0, 20, a3.height / 2 - 20 - 20, "\u2191\u2191"));
        a3.buttonList.add(new aia(a3, 0, 20, a3.height / 2, "\u2193\u2193"));
    }

    public void drawGuiContainerBackgroundLayer(float a2, int a3, int a4) {
        jca a5;
        jca.drawRect((int)0, (int)0, (int)a5.width, (int)a5.height, (int)0x6C000000);
        float a6 = (float)((double)System.currentTimeMillis() / 10.0 % 360.0);
        GlStateManager.pushMatrix();
        GlStateManager.enableBlend();
        GlStateManager.enableDepth();
        GlStateManager.translate((float)((float)a5.width / 2.0f + ((Float)a5.k.k).floatValue()), (float)((float)a5.height / 1.2f + ((Float)a5.k.ALLATORIxDEMO).floatValue()), (float)500.0f);
        GlStateManager.rotate((float)((Float)a5.y.ALLATORIxDEMO).floatValue(), (float)1.0f, (float)0.0f, (float)0.0f);
        GlStateManager.rotate((float)((Float)a5.y.k).floatValue(), (float)0.0f, (float)1.0f, (float)0.0f);
        GlStateManager.color((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
        a5.b.setCustomNameTag(a5.o);
        GuiInventory.drawEntityOnScreen((int)0, (int)0, (int)((int)a5.ALLATORIxDEMO), (float)0.0f, (float)0.0f, (EntityLivingBase)a5.b);
        GlStateManager.disableDepth();
        GlStateManager.disableBlend();
        GlStateManager.popMatrix();
        a5.q.ALLATORIxDEMO(a3, a4, a2);
    }

    public void drawGuiContainerForegroundLayer(int a2, int a3) {
        jca a4;
        a4.renderHoveredToolTip(a2, a3);
    }

    @Override
    public void drag(String a2, int a3, int a4) {
    }

    public static /* synthetic */ List ALLATORIxDEMO(jca a2) {
        return a2.c;
    }

    public static /* synthetic */ String ALLATORIxDEMO(jca a2, String a3) {
        a2.o = a3;
        return a2.o;
    }

    public static /* synthetic */ dh c(jca a2) {
        return a2.y;
    }

    public static /* synthetic */ float ALLATORIxDEMO(jca a2) {
        return a2.ALLATORIxDEMO;
    }

    public static /* synthetic */ float ALLATORIxDEMO(jca a2, float a3) {
        a2.ALLATORIxDEMO = a3;
        return a2.ALLATORIxDEMO;
    }

    public static /* synthetic */ dh ALLATORIxDEMO(jca a2) {
        return a2.k;
    }
}

