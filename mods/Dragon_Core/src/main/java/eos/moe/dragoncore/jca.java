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

    public void func_73866_w_() {
        int a2;
        jca a3;
        ScaledResolution a4 = new ScaledResolution(a3.field_146297_k);
        a3.field_146999_f = a4.func_78326_a();
        a3.field_147000_g = a4.func_78328_b();
        super.func_73866_w_();
        int a5 = 18;
        for (a2 = 0; a2 < 3; ++a2) {
            for (int a6 = 0; a6 < 9; ++a6) {
                Slot a7 = (Slot)a3.field_147002_h.field_75151_b.get(a6 + a2 * 9);
                a7.field_75221_f = a3.field_146295_m + 1 - 76 - 5 + a2 * a5;
            }
        }
        for (a2 = 0; a2 < 9; ++a2) {
            Slot a8 = (Slot)a3.field_147002_h.field_75151_b.get(a2 + 27);
            a8.field_75221_f = a3.field_146295_m + 1 - 5 - a5;
        }
        a2 = a3.field_146294_l - 162 - 25;
        int a9 = a3.field_146295_m - 15 - 14 - 15 + 26;
        a2 = MathHelper.func_76125_a((int)a2, (int)0, (int)200);
        a3.q = new gda(a3, a3.field_146294_l - 100, 12, 95, Math.max(0, a9), 14, a3.field_146294_l, a3.field_146295_m, a3);
        a3.field_146292_n.add(new aia(a3, 0, a3.field_146294_l / 2 - 20 - 20, a3.field_146295_m - 50, "\u2190"));
        a3.field_146292_n.add(new aia(a3, 0, a3.field_146294_l / 2 + 20, a3.field_146295_m - 50, "\u2192"));
        a3.field_146292_n.add(new aia(a3, 0, a3.field_146294_l - 120, a3.field_146295_m / 2 - 20 - 20, "\u2191"));
        a3.field_146292_n.add(new aia(a3, 0, a3.field_146294_l - 120, a3.field_146295_m / 2 + 20, "\u2193"));
        a3.field_146292_n.add(new aia(a3, 0, a3.field_146294_l / 2 - 20 - 20, 10, "-"));
        a3.field_146292_n.add(new aia(a3, 0, a3.field_146294_l / 2 + 20, 10, "+"));
        a3.field_146292_n.add(new aia(a3, 0, 0, a3.field_146295_m / 2 - 20, "\u2190\u2190"));
        a3.field_146292_n.add(new aia(a3, 0, 40, a3.field_146295_m / 2 - 20, "\u2192\u2192"));
        a3.field_146292_n.add(new aia(a3, 0, 20, a3.field_146295_m / 2 - 20 - 20, "\u2191\u2191"));
        a3.field_146292_n.add(new aia(a3, 0, 20, a3.field_146295_m / 2, "\u2193\u2193"));
    }

    public void func_146976_a(float a2, int a3, int a4) {
        jca a5;
        jca.func_73734_a((int)0, (int)0, (int)a5.field_146294_l, (int)a5.field_146295_m, (int)0x6C000000);
        float a6 = (float)((double)System.currentTimeMillis() / 10.0 % 360.0);
        GlStateManager.func_179094_E();
        GlStateManager.func_179147_l();
        GlStateManager.func_179126_j();
        GlStateManager.func_179109_b((float)((float)a5.field_146294_l / 2.0f + ((Float)a5.k.k).floatValue()), (float)((float)a5.field_146295_m / 1.2f + ((Float)a5.k.ALLATORIxDEMO).floatValue()), (float)500.0f);
        GlStateManager.func_179114_b((float)((Float)a5.y.ALLATORIxDEMO).floatValue(), (float)1.0f, (float)0.0f, (float)0.0f);
        GlStateManager.func_179114_b((float)((Float)a5.y.k).floatValue(), (float)0.0f, (float)1.0f, (float)0.0f);
        GlStateManager.func_179131_c((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
        a5.b.func_96094_a(a5.o);
        GuiInventory.func_147046_a((int)0, (int)0, (int)((int)a5.ALLATORIxDEMO), (float)0.0f, (float)0.0f, (EntityLivingBase)a5.b);
        GlStateManager.func_179097_i();
        GlStateManager.func_179084_k();
        GlStateManager.func_179121_F();
        a5.q.ALLATORIxDEMO(a3, a4, a2);
    }

    public void func_146979_b(int a2, int a3) {
        jca a4;
        a4.func_191948_b(a2, a3);
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

