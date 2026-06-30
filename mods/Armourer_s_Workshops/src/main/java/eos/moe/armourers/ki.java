/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.model.ModelBiped
 *  net.minecraft.client.renderer.GlStateManager
 *  net.minecraft.client.renderer.entity.RenderLivingBase
 *  net.minecraft.client.renderer.entity.layers.LayerRenderer
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.util.EnumHandSide
 */
package eos.moe.armourers;

import eos.moe.armourers.fk;
import eos.moe.armourers.in;
import eos.moe.armourers.kd;
import eos.moe.armourers.km;
import eos.moe.armourers.mn;
import eos.moe.armourers.oh;
import eos.moe.armourers.on;
import eos.moe.armourers.r;
import eos.moe.armourers.vk;
import eos.moe.armourers.vn;
import eos.moe.armourers.yl;
import eos.moe.armourers.zg;
import eos.moe.armourers.zh;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderLivingBase;
import net.minecraft.client.renderer.entity.layers.LayerRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.EnumHandSide;

public class ki<E extends EntityLivingBase, R extends RenderLivingBase>
implements LayerRenderer<E> {
    public R s;
    public r[] m;
    public static float j = 0.0625f;

    public void translateToHand(EnumHandSide a2) {
        ki a3;
        ((ModelBiped)a3.s.getMainModel()).postRenderArm(0.0625f, a2);
    }

    public boolean shouldCombineTextures() {
        return false;
    }

    public ki(R a2) {
        ki a3;
        ki ki2 = a3;
        ki2.s = a2;
        ki2.m = new r[6];
        ki ki3 = a3;
        ki3.m[0] = vn.l;
        ki3.m[1] = vn.a;
        ki3.m[2] = vn.e;
        ki3.m[3] = vn.w;
        ki3.m[4] = vn.g;
        ki3.m[5] = vn.u;
    }

    public void doRenderLayer(E a2, float a3, float a42, float a5, float a62, float a7, float a8, float a9) {
        ki a10;
        double d2 = Minecraft.getMinecraft().player.getDistance(((EntityLivingBase)a2).posX, ((EntityLivingBase)a2).posY, ((EntityLivingBase)a2).posZ);
        if (d2 > (double)vk.n || !zh.g && d2 != 0.0) {
            return;
        }
        if (zg.r(a2, "any").size() == 0) {
            return;
        }
        on.r(a2.getUniqueID()).r();
        int n2 = a3 = 0;
        while (n2 < a10.m.length) {
            Object a42 = a10.m[a3];
            a42 = zg.r(a2, (r)a42);
            int n3 = a5 = 0;
            while (n3 < a42.size()) {
                fk a62 = (fk)a42.get(a5);
                if (a62 != null) {
                    E e2 = a2;
                    a10.r((Entity)e2, a62, e2 != Minecraft.getMinecraft().player);
                }
                n3 = ++a5;
            }
            n2 = ++a3;
        }
    }

    private /* synthetic */ int r(int a2) {
        if (a2 >= 18) {
            return 2;
        }
        if (a2 > 13) {
            return 1;
        }
        return 0;
    }

    private /* synthetic */ void r(Entity a2, fk a3, boolean a4) {
        km km2 = km.t;
        if ((a3 = kd.j.getSkin((fk)a3)) != null) {
            ki a5;
            on on2 = on.r(a2.getUniqueID());
            if (in.t.r(((yl)a3).r()).booleanValue()) {
                on2.s = true;
            }
            if (in.b.r(((yl)a3).r()).booleanValue()) {
                on2.b = true;
            }
            if (in.f.r(((yl)a3).r()).booleanValue()) {
                on2.g = true;
            }
            if (in.n.r(((yl)a3).r()).booleanValue()) {
                on2.e = true;
            }
            if (in.r.r(((yl)a3).r()).booleanValue()) {
                on2.w = true;
            }
            if (in.o.r(((yl)a3).r()).booleanValue()) {
                on2.v = true;
            }
            if (in.w.r(((yl)a3).r()).booleanValue()) {
                on2.h = true;
            }
            if (in.ra.r(((yl)a3).r()).booleanValue()) {
                on2.z = true;
            }
            if (in.c.r(((yl)a3).r()).booleanValue()) {
                on2.l = true;
            }
            if (in.d.r(((yl)a3).r()).booleanValue()) {
                on2.r = true;
            }
            if (in.x.r(((yl)a3).r()).booleanValue()) {
                on2.j = true;
            }
            if (in.ua.r(((yl)a3).r()).booleanValue()) {
                on2.t = true;
            }
            GlStateManager.pushMatrix();
            km2.r(a2, (ModelBiped)a5.s.getMainModel(), (yl)a3, new mn(), oh.l, 0.0, a4);
            GlStateManager.popMatrix();
        }
    }
}

