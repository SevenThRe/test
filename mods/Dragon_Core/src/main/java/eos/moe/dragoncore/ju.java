/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.renderer.BufferBuilder
 *  net.minecraft.client.renderer.GLAllocation
 *  net.minecraft.client.renderer.GlStateManager
 *  net.minecraft.client.renderer.Tessellator
 *  net.minecraftforge.fml.relauncher.Side
 *  net.minecraftforge.fml.relauncher.SideOnly
 */
package eos.moe.dragoncore;

import eos.moe.dragoncore.api.model.AnimationModelRenderer;
import eos.moe.dragoncore.bax;
import eos.moe.dragoncore.hu;
import eos.moe.dragoncore.po;
import eos.moe.dragoncore.sx;
import eos.moe.dragoncore.vy;
import java.util.ArrayList;
import java.util.List;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.GLAllocation;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.Tessellator;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ju
implements AnimationModelRenderer {
    public float i;
    public float l;
    public float z;
    public float s;
    public float g;
    public float t;
    public float r;
    public float x;
    public float v;
    public bax m = new bax(0.0f, 0.0f, 0.0f);
    private bax c = new bax(1.0f, 1.0f, 1.0f);
    public boolean q;
    public List<ju> b = new ArrayList<ju>();
    private sx o;
    private vy y;
    private boolean k;
    private int ALLATORIxDEMO;

    public ju(po a2, sx a3) {
        ju a4;
        a4.o = a3;
        a4.y = a2;
    }

    public String ALLATORIxDEMO() {
        ju a2;
        return a2.o.y;
    }

    public void ALLATORIxDEMO(float a2, float a3, float a4) {
        a.i = a2;
        a.l = a3;
        a.z = a4;
    }

    public void ALLATORIxDEMO(hu a2) {
        a.i = a2.y;
        a.l = a2.k;
        a.z = a2.ALLATORIxDEMO;
    }

    public void ALLATORIxDEMO(ju a2) {
        ju a3;
        a3.b.add(a2);
        a3.y.ALLATORIxDEMO(a2);
    }

    @SideOnly(value=Side.CLIENT)
    public void d(float a2) {
        ju a3;
        if (!a3.q) {
            if (!a3.k) {
                a3.ALLATORIxDEMO(a2);
            }
            GlStateManager.func_179094_E();
            a3.x(a2);
            GlStateManager.func_179148_o((int)a3.ALLATORIxDEMO);
            a3.f(a2);
            GlStateManager.func_179121_F();
        }
        a3.ALLATORIxDEMO();
    }

    private /* synthetic */ void x(float a2) {
        ju a3;
        if (a3.i != 0.0f || a3.l != 0.0f || a3.z != 0.0f) {
            GlStateManager.func_179109_b((float)(a3.i * a2), (float)(a3.l * a2), (float)(a3.z * a2));
        }
        if (a3.t != 0.0f) {
            GlStateManager.func_179114_b((float)a3.t, (float)0.0f, (float)0.0f, (float)1.0f);
        }
        if (a3.g != 0.0f) {
            GlStateManager.func_179114_b((float)(-a3.g), (float)0.0f, (float)1.0f, (float)0.0f);
        }
        if (a3.s != 0.0f) {
            GlStateManager.func_179114_b((float)a3.s, (float)1.0f, (float)0.0f, (float)0.0f);
        }
        if (a3.i != 0.0f || a3.l != 0.0f || a3.z != 0.0f) {
            GlStateManager.func_179109_b((float)(-a3.i * a2), (float)(-a3.l * a2), (float)(-a3.z * a2));
        }
    }

    private /* synthetic */ void f(float a2) {
        ju a3;
        GlStateManager.func_179152_a((float)a3.c.getX(), (float)a3.c.getY(), (float)a3.c.getZ());
        if (a3.b != null) {
            for (ju a4 : a3.b) {
                a4.d(a2);
            }
        }
        GlStateManager.func_179152_a((float)(1.0f / a3.c.getX()), (float)(1.0f / a3.c.getY()), (float)(1.0f / a3.c.getZ()));
    }

    @SideOnly(value=Side.CLIENT)
    public void c(float a2) {
        ju a3;
        if (!a3.q) {
            if (!a3.k) {
                a3.ALLATORIxDEMO(a2);
            }
            a3.x(a2);
        }
    }

    @SideOnly(value=Side.CLIENT)
    private /* synthetic */ void ALLATORIxDEMO(float a2) {
        ju a3;
        a3.ALLATORIxDEMO = GLAllocation.func_74526_a((int)1);
        GlStateManager.func_187423_f((int)a3.ALLATORIxDEMO, (int)4864);
        BufferBuilder a4 = Tessellator.func_178181_a().func_178180_c();
        a3.o.ALLATORIxDEMO(a4, a2);
        GlStateManager.func_187415_K();
        a3.k = true;
    }

    private /* synthetic */ void ALLATORIxDEMO() {
        ju a2;
        a2.s = a2.m.x;
        a2.g = a2.m.y;
        a2.t = a2.m.z;
        a2.r = 0.0f;
        a2.x = 0.0f;
        a2.v = 0.0f;
        a2.c.set(1.0f, 1.0f, 1.0f);
    }

    @Override
    public void setScaleFactor(float a2, float a3, float a4) {
        ju a5;
        a5.c.set(a2, a3, a4);
    }

    @Override
    public void setRotateAngle(float a2, float a3, float a4) {
        a.s = a2;
        a.g = a3;
        a.t = a4;
    }

    @Override
    public void setOffsets(float a2, float a3, float a4) {
        a.r = a2;
        a.x = a3;
        a.v = a4;
    }

    @Override
    public bax getRotateAngle() {
        ju a2;
        return new bax(a2.s, a2.g, a2.t);
    }

    @Override
    public bax getOffsets() {
        ju a2;
        return new bax(a2.r, a2.x, a2.v);
    }

    @Override
    public bax getScaleFactor() {
        ju a2;
        return a2.c;
    }

    @Override
    public bax getStartRotationAngles() {
        ju a2;
        return a2.m;
    }
}

