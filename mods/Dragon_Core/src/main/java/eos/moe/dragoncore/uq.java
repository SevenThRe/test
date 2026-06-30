/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.renderer.GlStateManager
 *  net.minecraft.entity.Entity
 *  net.minecraft.util.math.MathHelper
 */
package eos.moe.dragoncore;

import eos.moe.dragoncore.ju;
import eos.moe.dragoncore.po;
import eos.moe.dragoncore.ss;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.MathHelper;

public class uq
extends ss {
    public ju m;
    public ju c;
    public ju q;
    public ju b;
    public ju o;
    public ju y;
    public float k = 8.0f;
    public float ALLATORIxDEMO = 4.0f;

    public uq(po a2, String a3, String a4, String a5, String a6, String a7, String a8) {
        super(a2);
        uq a9;
        a9.initParts(a3, a4, a5, a6, a7, a8);
    }

    public void initParts(String a2, String a3, String a4, String a5, String a6, String a7) {
        uq a8;
        for (ju a9 : a8.ALLATORIxDEMO.ALLATORIxDEMO()) {
            if (a9.ALLATORIxDEMO().equals(a2)) {
                a8.m = a9;
                continue;
            }
            if (a9.ALLATORIxDEMO().equals(a3)) {
                a8.c = a9;
                continue;
            }
            if (a9.ALLATORIxDEMO().equals(a4)) {
                a8.q = a9;
                continue;
            }
            if (a9.ALLATORIxDEMO().equals(a5)) {
                a8.b = a9;
                continue;
            }
            if (a9.ALLATORIxDEMO().equals(a6)) {
                a8.o = a9;
                continue;
            }
            if (!a9.ALLATORIxDEMO().equals(a7)) continue;
            a8.y = a9;
        }
    }

    @Override
    public void func_78088_a(Entity a2, float a3, float a4, float a5, float a6, float a7, float a8) {
        uq a9;
        a9.func_78087_a(a3, a4, a5, a6, a7, a8, a2);
        if (a9.field_78091_s) {
            float a10 = 2.0f;
            GlStateManager.func_179094_E();
            GlStateManager.func_179109_b((float)0.0f, (float)(a9.k * a8), (float)(a9.ALLATORIxDEMO * a8));
            a9.m.d(a8);
            GlStateManager.func_179121_F();
            GlStateManager.func_179094_E();
            GlStateManager.func_179152_a((float)0.5f, (float)0.5f, (float)0.5f);
            GlStateManager.func_179109_b((float)0.0f, (float)(24.0f * a8), (float)0.0f);
            a9.c.d(a8);
            a9.q.d(a8);
            a9.b.d(a8);
            a9.o.d(a8);
            a9.y.d(a8);
            GlStateManager.func_179121_F();
        } else {
            a9.m.d(a8);
            a9.c.d(a8);
            a9.q.d(a8);
            a9.b.d(a8);
            a9.o.d(a8);
            a9.y.d(a8);
        }
    }

    public void func_78087_a(float a2, float a3, float a4, float a5, float a6, float a7, Entity a8) {
        a.m.s = a6 * ((float)Math.PI / 180);
        a.m.g = a5 * ((float)Math.PI / 180);
        a.c.s = 1.5707964f;
        a.q.s = MathHelper.func_76134_b((float)(a2 * 0.6662f)) * 1.4f * a3;
        a.b.s = MathHelper.func_76134_b((float)(a2 * 0.6662f + (float)Math.PI)) * 1.4f * a3;
        a.o.s = MathHelper.func_76134_b((float)(a2 * 0.6662f + (float)Math.PI)) * 1.4f * a3;
        a.y.s = MathHelper.func_76134_b((float)(a2 * 0.6662f)) * 1.4f * a3;
    }
}

