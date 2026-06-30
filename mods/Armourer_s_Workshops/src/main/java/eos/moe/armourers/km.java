/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.model.ModelBiped
 *  net.minecraft.client.renderer.GlStateManager
 *  net.minecraft.client.renderer.GlStateManager$DestFactor
 *  net.minecraft.client.renderer.GlStateManager$SourceFactor
 *  net.minecraft.entity.Entity
 *  net.minecraftforge.fml.relauncher.Side
 *  net.minecraftforge.fml.relauncher.SideOnly
 */
package eos.moe.armourers;

import eos.moe.armourers.dn;
import eos.moe.armourers.eh;
import eos.moe.armourers.fj;
import eos.moe.armourers.hm;
import eos.moe.armourers.io;
import eos.moe.armourers.n;
import eos.moe.armourers.oh;
import eos.moe.armourers.ol;
import eos.moe.armourers.pg;
import eos.moe.armourers.pj;
import eos.moe.armourers.pm;
import eos.moe.armourers.r;
import eos.moe.armourers.rm;
import eos.moe.armourers.vn;
import eos.moe.armourers.wi;
import eos.moe.armourers.yl;
import java.util.HashMap;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.Entity;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(value=Side.CLIENT)
public class km {
    public fj g;
    public wi z;
    public static km t;
    public eh w;
    public pm r;
    public io l;
    private HashMap<String, pj> c;
    public pg v;
    public rm s;
    public hm m;
    public ol j;

    public boolean r(Entity a2, ModelBiped a3, yl a4, n a5, oh a6, double a7, boolean a8) {
        km a9;
        if (a4 == null) {
            return false;
        }
        pj pj2 = a9.r(a4.r());
        GlStateManager.func_179123_a();
        GlStateManager.func_179089_o();
        GlStateManager.func_187401_a((GlStateManager.SourceFactor)GlStateManager.SourceFactor.SRC_ALPHA, (GlStateManager.DestFactor)GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA);
        GlStateManager.func_179147_l();
        GlStateManager.func_179091_B();
        pj2.render(a2, a4, a3, false, a5, a6, false, a7, a8);
        GlStateManager.func_179101_C();
        GlStateManager.func_179084_k();
        GlStateManager.func_179129_p();
        GlStateManager.func_179099_b();
        return true;
    }

    public void r(r a2, pj a3) {
        km a4;
        a4.c.put(a2.y(), a3);
    }

    public static void r() {
        t = new km();
    }

    public boolean r(yl a2, dn a3, Entity a4, ModelBiped a5) {
        km a6;
        if (a2 == null) {
            return false;
        }
        pj pj2 = a6.r(a2.r());
        GlStateManager.func_179123_a();
        GlStateManager.func_179089_o();
        GlStateManager.func_187401_a((GlStateManager.SourceFactor)GlStateManager.SourceFactor.SRC_ALPHA, (GlStateManager.DestFactor)GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA);
        GlStateManager.func_179147_l();
        GlStateManager.func_179091_B();
        pj2.render(a4, a2, a5, a3);
        GlStateManager.func_179101_C();
        GlStateManager.func_179084_k();
        GlStateManager.func_179129_p();
        GlStateManager.func_179099_b();
        GlStateManager.func_179147_l();
        GlStateManager.func_179084_k();
        return true;
    }

    private /* synthetic */ km() {
        km a2;
        km km2 = a2;
        km km3 = a2;
        km2.z = new wi();
        km3.l = new io();
        km2.s = new rm();
        km2.v = new pg();
        km2.g = new fj();
        km2.w = new eh();
        km2.r = new pm();
        km2.j = new ol();
        km2.m = new hm();
        km2.c = new HashMap();
        km2.r(vn.l, a2.z);
        km2.r(vn.a, a2.l);
        km2.r(vn.e, a2.s);
        km2.r(vn.w, a2.v);
        km2.r(vn.g, a2.g);
        km2.r(vn.z, a2.r);
        km2.r(vn.q, a2.r);
        km2.r(vn.c, a2.j);
        km2.r(vn.s, a2.r);
        km2.r(vn.p, a2.r);
        km2.r(vn.j, a2.r);
        km2.r(vn.b, a2.r);
        km2.r(vn.t, a2.r);
        km2.r(vn.u, a2.w);
    }

    public pj r(r a2) {
        km a3;
        if ((a2 = a3.c.get(a2.y())) == null) {
            return a3.m;
        }
        return a2;
    }
}

