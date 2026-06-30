/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.gui.FontRenderer
 *  net.minecraft.client.gui.ScaledResolution
 *  net.minecraft.client.gui.inventory.GuiContainer
 *  net.minecraft.client.renderer.GlStateManager
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.inventory.Container
 *  net.minecraft.inventory.IInventory
 *  net.minecraft.util.Tuple
 *  org.lwjgl.input.Keyboard
 */
package eos.moe.armourers;

import eos.moe.armourers.af;
import eos.moe.armourers.ee;
import eos.moe.armourers.nf;
import eos.moe.armourers.qi;
import eos.moe.armourers.ql;
import eos.moe.armourers.sf;
import eos.moe.armourers.th;
import eos.moe.armourers.yk;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.util.Tuple;
import org.lwjgl.input.Keyboard;

public class pk
extends GuiContainer {
    private sf s;
    public static List<Tuple<String, Integer>> m = new ArrayList<Tuple<String, Integer>>();
    private yk j;

    public static /* synthetic */ FontRenderer v(pk a2) {
        return a2.field_146289_q;
    }

    public static /* synthetic */ FontRenderer w(pk a2) {
        return a2.field_146289_q;
    }

    public pk(IInventory a2, IInventory a3) {
        super((Container)new qi(a2, a3));
        pk a4;
        af.y();
    }

    public static /* synthetic */ FontRenderer s(pk a2) {
        return a2.field_146289_q;
    }

    public static /* synthetic */ sf r(pk a2) {
        return a2.s;
    }

    public static /* synthetic */ FontRenderer x(pk a2) {
        return a2.field_146289_q;
    }

    public static /* synthetic */ FontRenderer h(pk a2) {
        return a2.field_146289_q;
    }

    public static /* synthetic */ FontRenderer z(pk a2) {
        return a2.field_146289_q;
    }

    public static /* synthetic */ FontRenderer y(pk a2) {
        return a2.field_146289_q;
    }

    public static /* synthetic */ FontRenderer r(pk a2) {
        return a2.field_146289_q;
    }

    public void func_146976_a(float a2, int a3, int a4) {
    }

    public void func_73866_w_() {
        pk a3;
        ScaledResolution scaledResolution = new ScaledResolution(a3.field_146297_k);
        pk pk2 = a3;
        ScaledResolution scaledResolution2 = scaledResolution;
        a3.field_146999_f = scaledResolution2.func_78326_a();
        pk2.field_147000_g = scaledResolution2.func_78328_b();
        super.func_73866_w_();
        Keyboard.enableRepeatEvents((boolean)true);
        pk2.field_147002_h.field_75151_b.forEach(a2 -> {
            a2.field_75221_f = 999;
        });
        pk pk3 = a3;
        pk3.field_146292_n.clear();
        pk pk4 = a3;
        pk pk5 = a3;
        pk pk6 = a3;
        pk3.j = new yk(pk5, (a3.field_146294_l - 245) / 2, (pk5.field_146295_m - 200) / 2, 120, 200, 25, pk6.field_146294_l, pk6.field_146295_m);
        pk pk7 = a3;
        pk pk8 = a3;
        pk4.s = new sf(pk7, (a3.field_146294_l - 245) / 2 + 125, (pk7.field_146295_m - 200) / 2 + 50, 120, 115, 14, pk8.field_146294_l, pk8.field_146295_m);
        af.r();
        pk3.field_146292_n.clear();
        pk3.field_146292_n.add(new ee(0, a3.s.f, a3.s.g + 15, 120, 20, "\u8d2d\u4e70", a2 -> {
            pk a3;
            if ("\u8d2d\u4e70".equals(a2.field_146126_j)) {
                int n2 = m.stream().mapToInt(Tuple::func_76340_b).sum();
                ee ee2 = a2;
                ee2.field_146126_j = "\u786e\u5b9a\u8d2d\u4e70(\u82b1\u8d39" + n2 + af.c + ")";
                return;
            }
            List<String> list = m.stream().map(Tuple::func_76341_a).collect(Collectors.toList());
            nf.r(list);
            a3.field_146297_k.func_147108_a(null);
        }));
    }

    public void func_146281_b() {
        pk a2;
        super.func_146281_b();
        m.clear();
    }

    public void func_73863_a(int a2, int a3, float a4) {
        pk a5;
        GlStateManager.func_179140_f();
        pk pk2 = a5;
        pk.func_73734_a((int)0, (int)0, (int)pk2.field_146294_l, (int)pk2.field_146295_m, (int)-1946157056);
        ql.r("shop.png");
        GlStateManager.func_179131_c((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
        pk pk3 = a5;
        pk pk4 = a5;
        pk.func_146110_a((int)((pk3.field_146294_l - 280) / 2), (int)((pk4.field_146295_m - 250) / 2), (float)0.0f, (float)0.0f, (int)280, (int)250, (float)280.0f, (float)250.0f);
        pk3.j.r(a2, a3, a4);
        pk4.s.r(a2, a3, a4);
        int n2 = m.stream().mapToInt(Tuple::func_76340_b).sum();
        GlStateManager.func_179094_E();
        pk pk5 = a5;
        GlStateManager.func_179109_b((float)(a5.s.f + 33), (float)(pk5.s.u + 5 - 55), (float)0.0f);
        GlStateManager.func_179152_a((float)2.0f, (float)2.0f, (float)0.0f);
        pk5.field_146289_q.func_175065_a("\u00a76\u8d2d\u7269\u8f66", 0.0f, 0.0f, -1, false);
        GlStateManager.func_179121_F();
        GlStateManager.func_179131_c((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
        pk pk6 = a5;
        th.r(a5.field_146294_l - 80, pk6.field_146295_m - 20, 30, (EntityLivingBase)a5.field_146297_k.field_71439_g);
        String string = new StringBuilder().insert(0, "\u00a76\u603b\u8ba1: \u00a7a").append(m.size()).append(" \u00a76\u4ef6\u65f6\u88c5 \u5171 \u00a7a").append(n2).append(" \u00a76").append(af.c).toString();
        pk6.field_146289_q.func_175065_a("\u00a76\u5c06\u5de6\u4fa7\u65f6\u88c5\u62d6\u5165\u4e0b\u65b9\u65b9\u6846\u5185", (float)(a5.s.f + 6), (float)(a5.s.u + 30 - 55), -1, false);
        a5.field_146289_q.func_175065_a("\u00a76\u53cc\u51fb\u53ef\u79fb\u9664\u5546\u54c1", (float)(a5.s.f + 28), (float)(a5.s.u + 45 - 60), -1, false);
        pk pk7 = a5;
        String string2 = string;
        pk7.field_146289_q.func_78276_b(string2, pk7.s.f + 60 - a5.field_146289_q.func_78256_a(string2) / 2, a5.s.g + 4, -1);
        GlStateManager.func_179145_e();
        super.func_73863_a(a2, a3, a4);
    }
}

