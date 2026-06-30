/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.gui.ScaledResolution
 *  net.minecraft.client.gui.inventory.GuiContainer
 *  net.minecraft.client.renderer.GlStateManager
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.inventory.Container
 *  net.minecraft.inventory.IInventory
 *  net.minecraft.util.Tuple
 *  net.minecraft.util.math.MathHelper
 *  net.minecraftforge.fml.client.FMLClientHandler
 */
package eos.moe.armourers;

import eos.moe.armourers.af;
import eos.moe.armourers.am;
import eos.moe.armourers.ee;
import eos.moe.armourers.jd;
import eos.moe.armourers.je;
import eos.moe.armourers.nf;
import eos.moe.armourers.nn;
import eos.moe.armourers.qi;
import eos.moe.armourers.t;
import eos.moe.armourers.th;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.UUID;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.util.Tuple;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.fml.client.FMLClientHandler;

public class cj
extends GuiContainer
implements t {
    private am r;
    private List<Tuple<String, UUID>> l;
    public static List<String> c = new ArrayList<String>();
    public static DecimalFormat v = new DecimalFormat("#.00");
    private jd s;
    public static Entity m;
    private je j;

    public void drawGuiContainerBackgroundLayer(float a2, int a3, int a4) {
    }

    public void initGui() {
        cj a3;
        ScaledResolution scaledResolution = new ScaledResolution(a3.mc);
        a3.xSize = scaledResolution.getScaledWidth();
        a3.ySize = scaledResolution.getScaledHeight();
        super.initGui();
        a3.inventorySlots.inventorySlots.forEach(a2 -> {
            a2.yPos = 999;
        });
        cj cj2 = a3;
        int n2 = cj2.width - 162 - 25;
        int n3 = cj2.height - 25;
        n2 = MathHelper.clamp((int)n2, (int)0, (int)200);
        cj cj3 = a3;
        cj cj4 = a3;
        cj3.j = new je(142, 12, n2, Math.max(0, n3), 14, cj4.width, cj4.height, a3);
        cj cj5 = a3;
        cj2.r = new am(a3, 6, 12, 125, n3 -= 130, 14, cj5.width, cj5.height);
        cj cj6 = a3;
        cj2.s = new jd(a3, 6, 12 + n3 + 4, 125, 103, 14, cj6.width, cj6.height);
        cj2.buttonList.clear();
        cj2.buttonList.add(new ee(0, 70, n3 + 123, 62, 20, "\u4e34\u65f6\u8bbe\u7f6e", a2 -> {
            if (m != null) {
                nf.r(m.getUniqueID(), false, c);
            }
        }));
        a3.buttonList.add(new ee(0, 5, n3 + 123, 62, 20, "\u6c38\u4e45\u4fdd\u5b58", a2 -> {
            if (m != null) {
                nf.r(m.getUniqueID(), true, c);
            }
        }));
        m = null;
        c.clear();
        af.z();
    }

    public void drawScreen(int a2, int a3, float a4) {
        cj a5;
        GlStateManager.disableLighting();
        cj cj2 = a5;
        cj.drawRect((int)0, (int)0, (int)cj2.width, (int)cj2.height, (int)-1946157056);
        cj cj3 = a5;
        cj3.j.r(a2, a3, a4);
        cj3.r.r(a2, a3, a4);
        cj3.s.r(a2, a3, a4);
        GlStateManager.enableLighting();
        if (m != null) {
            GlStateManager.color((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
            th.r(390, a5.height - 15, 50, (EntityLivingBase)m);
        }
        super.drawScreen(a2, a3, a4);
    }

    public static /* synthetic */ List r(cj a2) {
        return a2.l;
    }

    @Override
    public void dragSkin(String a2, int a3, int a4) {
        cj a5;
        if (m != null && a5.s.r(a3, a4) && !c.contains(a2)) {
            c.add(a2);
        }
    }

    public cj(IInventory a3, IInventory a4) {
        super((Container)new qi((IInventory)a3, a4));
        cj a5;
        af.y();
        a5.l = new ArrayList<Tuple<String, UUID>>();
        a3 = new ArrayList();
        FMLClientHandler.instance().getWorldClient().getLoadedEntityList().forEach(arg_0 -> cj.r((List)a3, arg_0));
        a3.sort(Comparator.comparingDouble(a2 -> Minecraft.getMinecraft().player.getDistance(a2)));
        Object object = a3 = a3.iterator();
        while (object.hasNext()) {
            a4 = (Entity)a3.next();
            object = a3;
            a5.l.add((Tuple<String, UUID>)new Tuple((Object)new StringBuilder().insert(0, a4.getDisplayName().getUnformattedText()).append("\u00a7f(").append(v.format(a5.r((Entity)a4))).append("\u7c73)").toString(), (Object)a4.getUniqueID()));
        }
    }

    public void onGuiClosed() {
        cj a2;
        super.onGuiClosed();
        m = null;
        c.clear();
    }

    private static /* synthetic */ void r(List a2, Entity a3) {
        if (a3 instanceof EntityPlayer || nn.j.contains(a3.getClass())) {
            a2.add(a3);
        }
    }
}

