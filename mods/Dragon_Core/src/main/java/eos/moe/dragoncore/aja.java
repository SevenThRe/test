/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.gui.ScaledResolution
 *  net.minecraft.client.gui.inventory.GuiContainer
 *  net.minecraft.client.renderer.GlStateManager
 *  net.minecraft.client.renderer.OpenGlHelper
 *  net.minecraft.client.renderer.RenderHelper
 *  net.minecraft.init.Items
 *  net.minecraft.inventory.Container
 *  net.minecraft.inventory.IInventory
 *  net.minecraft.inventory.Slot
 *  net.minecraft.item.ItemStack
 *  net.minecraft.nbt.NBTTagCompound
 *  net.minecraft.util.math.MathHelper
 */
package eos.moe.dragoncore;

import eos.moe.dragoncore.dca;
import eos.moe.dragoncore.dh;
import eos.moe.dragoncore.fp;
import eos.moe.dragoncore.nka;
import eos.moe.dragoncore.nw;
import eos.moe.dragoncore.sa;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.init.Items;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.math.MathHelper;

public class aja
extends GuiContainer
implements sa {
    private dca b;
    private String o = "";
    private dh<Float, Float> y = new dh<Float, Float>(Float.valueOf(0.0f), Float.valueOf(0.0f));
    private dh<Float, Float> k = new dh<Float, Float>(Float.valueOf(0.0f), Float.valueOf(0.0f));
    private float ALLATORIxDEMO = 10.0f;

    public aja(IInventory a2, IInventory a3) {
        super((Container)new nka(a2, a3));
        aja a4;
    }

    public void initGui() {
        int a2;
        aja a3;
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
        a3.b = new dca(a3, a3.width - 100, 12, 95, Math.max(0, a9), 14, a3.width, a3.height, a3);
        a3.buttonList.add(new fp(a3, 0, a3.width / 2 - 20 - 20, a3.height - 50, "\u2190"));
        a3.buttonList.add(new fp(a3, 0, a3.width / 2 + 20, a3.height - 50, "\u2192"));
        a3.buttonList.add(new fp(a3, 0, a3.width - 120, a3.height / 2 - 20 - 20, "\u2191"));
        a3.buttonList.add(new fp(a3, 0, a3.width - 120, a3.height / 2 + 20, "\u2193"));
        a3.buttonList.add(new fp(a3, 0, a3.width / 2 - 20 - 20, 10, "-"));
        a3.buttonList.add(new fp(a3, 0, a3.width / 2 + 20, 10, "+"));
        a3.buttonList.add(new fp(a3, 0, 0, a3.height / 2 - 20, "\u2190\u2190"));
        a3.buttonList.add(new fp(a3, 0, 40, a3.height / 2 - 20, "\u2192\u2192"));
        a3.buttonList.add(new fp(a3, 0, 20, a3.height / 2 - 20 - 20, "\u2191\u2191"));
        a3.buttonList.add(new fp(a3, 0, 20, a3.height / 2, "\u2193\u2193"));
    }

    public void drawScreen(int a2, int a3, float a4) {
        aja a5;
        super.drawScreen(a2, a3, a4);
        GlStateManager.pushMatrix();
        GlStateManager.enableRescaleNormal();
        GlStateManager.disableLighting();
        GlStateManager.disableDepth();
        OpenGlHelper.setLightmapTextureCoords((int)OpenGlHelper.lightmapTexUnit, (float)240.0f, (float)240.0f);
        RenderHelper.enableGUIStandardItemLighting();
        GlStateManager.translate((float)((float)a5.width / 3.0f + ((Float)a5.k.k).floatValue()), (float)((float)a5.height / 3.0f + ((Float)a5.k.ALLATORIxDEMO).floatValue()), (float)0.0f);
        GlStateManager.translate((float)8.0f, (float)8.0f, (float)0.0f);
        GlStateManager.rotate((float)((Float)a5.y.ALLATORIxDEMO).floatValue(), (float)1.0f, (float)0.0f, (float)0.0f);
        GlStateManager.rotate((float)((Float)a5.y.k).floatValue(), (float)0.0f, (float)1.0f, (float)0.0f);
        GlStateManager.translate((float)-8.0f, (float)-8.0f, (float)0.0f);
        GlStateManager.color((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
        GlStateManager.scale((float)a5.ALLATORIxDEMO, (float)a5.ALLATORIxDEMO, (float)0.0f);
        if (a5.o != null) {
            a5.mc.getRenderItem().renderItemIntoGUI(a5.createItemStack(), 0, 0);
        }
        GlStateManager.popMatrix();
        a5.b.ALLATORIxDEMO(a2, a3, a4);
    }

    public ItemStack createItemStack() {
        aja a2;
        ItemStack a3 = new ItemStack(Items.APPLE);
        NBTTagCompound a4 = a3.getTagCompound() == null ? new NBTTagCompound() : a3.getTagCompound();
        a4.setString("itemmodel", a2.o);
        a3.setTagCompound(a4);
        return a3;
    }

    public void drawGuiContainerBackgroundLayer(float a2, int a3, int a4) {
    }

    public void drawGuiContainerForegroundLayer(int a2, int a3) {
        aja a4;
        a4.renderHoveredToolTip(a2, a3);
    }

    @Override
    public void drag(String a2, int a3, int a4) {
        aja a5;
        Slot a6 = a5.getSlotUnderMouse();
        if (a6 != null && a5.o != null) {
            nw.ALLATORIxDEMO(a6.getSlotIndex(), a5.o);
        }
    }

    public static /* synthetic */ String ALLATORIxDEMO(aja a2, String a3) {
        a2.o = a3;
        return a2.o;
    }

    public static /* synthetic */ dh c(aja a2) {
        return a2.y;
    }

    public static /* synthetic */ float ALLATORIxDEMO(aja a2) {
        return a2.ALLATORIxDEMO;
    }

    public static /* synthetic */ float ALLATORIxDEMO(aja a2, float a3) {
        a2.ALLATORIxDEMO = a3;
        return a2.ALLATORIxDEMO;
    }

    public static /* synthetic */ dh ALLATORIxDEMO(aja a2) {
        return a2.k;
    }
}

