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

    public void func_146976_a(float a2, int a3, int a4) {
    }

    public void func_73866_w_() {
        cj a3;
        ScaledResolution scaledResolution = new ScaledResolution(a3.field_146297_k);
        a3.field_146999_f = scaledResolution.func_78326_a();
        a3.field_147000_g = scaledResolution.func_78328_b();
        super.func_73866_w_();
        a3.field_147002_h.field_75151_b.forEach(a2 -> {
            a2.field_75221_f = 999;
        });
        cj cj2 = a3;
        int n2 = cj2.field_146294_l - 162 - 25;
        int n3 = cj2.field_146295_m - 25;
        n2 = MathHelper.func_76125_a((int)n2, (int)0, (int)200);
        cj cj3 = a3;
        cj cj4 = a3;
        cj3.j = new je(142, 12, n2, Math.max(0, n3), 14, cj4.field_146294_l, cj4.field_146295_m, a3);
        cj cj5 = a3;
        cj2.r = new am(a3, 6, 12, 125, n3 -= 130, 14, cj5.field_146294_l, cj5.field_146295_m);
        cj cj6 = a3;
        cj2.s = new jd(a3, 6, 12 + n3 + 4, 125, 103, 14, cj6.field_146294_l, cj6.field_146295_m);
        cj2.field_146292_n.clear();
        cj2.field_146292_n.add(new ee(0, 70, n3 + 123, 62, 20, "\u4e34\u65f6\u8bbe\u7f6e", a2 -> {
            if (m != null) {
                nf.r(m.func_110124_au(), false, c);
            }
        }));
        a3.field_146292_n.add(new ee(0, 5, n3 + 123, 62, 20, "\u6c38\u4e45\u4fdd\u5b58", a2 -> {
            if (m != null) {
                nf.r(m.func_110124_au(), true, c);
            }
        }));
        m = null;
        c.clear();
        af.z();
    }

    public void func_73863_a(int a2, int a3, float a4) {
        cj a5;
        GlStateManager.func_179140_f();
        cj cj2 = a5;
        cj.func_73734_a((int)0, (int)0, (int)cj2.field_146294_l, (int)cj2.field_146295_m, (int)-1946157056);
        cj cj3 = a5;
        cj3.j.r(a2, a3, a4);
        cj3.r.r(a2, a3, a4);
        cj3.s.r(a2, a3, a4);
        GlStateManager.func_179145_e();
        if (m != null) {
            GlStateManager.func_179131_c((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
            th.r(390, a5.field_146295_m - 15, 50, (EntityLivingBase)m);
        }
        super.func_73863_a(a2, a3, a4);
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
        FMLClientHandler.instance().getWorldClient().func_72910_y().forEach(arg_0 -> cj.r((List)a3, arg_0));
        a3.sort(Comparator.comparingDouble(a2 -> Minecraft.func_71410_x().field_71439_g.func_70032_d(a2)));
        Object object = a3 = a3.iterator();
        while (object.hasNext()) {
            a4 = (Entity)a3.next();
            object = a3;
            a5.l.add((Tuple<String, UUID>)new Tuple((Object)new StringBuilder().insert(0, a4.func_145748_c_().func_150260_c()).append("\u00a7f(").append(v.format(a5.r((Entity)a4))).append("\u7c73)").toString(), (Object)a4.func_110124_au()));
        }
    }

    public void func_146281_b() {
        cj a2;
        super.func_146281_b();
        m = null;
        c.clear();
    }

    private static /* synthetic */ void r(List a2, Entity a3) {
        if (a3 instanceof EntityPlayer || nn.j.contains(a3.getClass())) {
            a2.add(a3);
        }
    }
}

