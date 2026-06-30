/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.model.ModelBase
 *  net.minecraft.client.model.ModelBox
 *  net.minecraft.client.model.ModelRenderer
 *  net.minecraft.client.renderer.BufferBuilder
 *  net.minecraft.client.renderer.GLAllocation
 *  net.minecraft.client.renderer.GlStateManager
 *  net.minecraft.client.renderer.Tessellator
 */
package eos.moe.dragoncore;

import eos.moe.dragoncore.api.model.AnimationModelRenderer;
import eos.moe.dragoncore.api.model.IModelPiece;
import eos.moe.dragoncore.bax;
import eos.moe.dragoncore.gg;
import eos.moe.dragoncore.jv;
import eos.moe.dragoncore.nk;
import eos.moe.dragoncore.xu;
import java.io.Serializable;
import java.util.List;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelBox;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.GLAllocation;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.Tessellator;

public class mt
extends ModelRenderer
implements IModelPiece,
AnimationModelRenderer,
Serializable {
    private static final long v = 7269514370568333722L;
    public mt m;
    public List<xu> c;
    public bax q;
    private bax b = new bax(1.0f, 1.0f, 1.0f);
    private boolean o;
    private boolean y = true;
    private int k;
    public Runnable ALLATORIxDEMO;

    public boolean isRender() {
        mt a2;
        return a2.y;
    }

    public void setRender(boolean a2) {
        a.y = a2;
    }

    public mt(jv a2, bax a3, String a4, List<xu> a5, boolean a6) {
        super((ModelBase)a2, a4);
        mt a7;
        a7.q = a3;
        a7.field_78795_f = a3.getX();
        a7.field_78796_g = a3.getY();
        a7.field_78808_h = a3.getZ();
        a7.field_78806_j = !a6;
        a7.c = a5;
    }

    @Override
    public String getName() {
        mt a2;
        return a2.field_78802_n;
    }

    @Override
    public void setRotateAngle(float a2, float a3, float a4) {
        a.field_78795_f = a2;
        a.field_78796_g = a3;
        a.field_78808_h = a4;
    }

    @Override
    public void setOffsets(float a2, float a3, float a4) {
        a.field_82906_o = a2;
        a.field_82908_p = a3;
        a.field_82907_q = a4;
    }

    @Override
    public bax getRotateAngle() {
        mt a2;
        return new bax(a2.field_78795_f, a2.field_78796_g, a2.field_78808_h);
    }

    @Override
    public bax getOffsets() {
        mt a2;
        return new bax(a2.field_82906_o, a2.field_82908_p, a2.field_82907_q);
    }

    @Override
    public void render(float a2, boolean a3) {
        mt a4;
        if (a3) {
            a4.render2(a2);
        } else {
            a4.func_78785_a(a2);
        }
    }

    public void render2(float a2) {
        mt a3;
        if (!a3.field_78807_k && a3.field_78806_j) {
            GlStateManager.func_179094_E();
            GlStateManager.func_179109_b((float)(a3.field_82906_o * a2), (float)(a3.field_82908_p * a2), (float)(a3.field_82907_q * a2));
            a3.ALLATORIxDEMO(a2);
            GlStateManager.func_179152_a((float)a3.b.getX(), (float)a3.b.getY(), (float)a3.b.getZ());
            if (a3.field_78805_m != null) {
                for (ModelRenderer a4 : a3.field_78805_m) {
                    a4.func_78785_a(a2);
                }
            }
            GlStateManager.func_179152_a((float)(1.0f / a3.b.getX()), (float)(1.0f / a3.b.getY()), (float)(1.0f / a3.b.getZ()));
            GlStateManager.func_179121_F();
        }
    }

    public void func_78785_a(float a2) {
        mt a3;
        if (!a3.field_78807_k && a3.field_78806_j) {
            if (!a3.o) {
                a3.func_78788_d(a2);
            }
            GlStateManager.func_179094_E();
            GlStateManager.func_179109_b((float)(a3.field_82906_o * a2), (float)(a3.field_82908_p * a2), (float)(a3.field_82907_q * a2));
            a3.ALLATORIxDEMO(a2);
            a3.c(a2);
            GlStateManager.func_179121_F();
        }
    }

    public nk position(nk a2, float a3) {
        mt a4;
        if (a4.m != null) {
            a4.m.position(a2, a3);
        }
        a2.ALLATORIxDEMO((double)(a4.field_82906_o * a3), (double)(a4.field_82908_p * a3), (double)(a4.field_82907_q * a3));
        if (a4.field_78800_c != 0.0f || a4.field_78797_d != 0.0f || a4.field_78798_e != 0.0f) {
            a2.ALLATORIxDEMO((double)(a4.field_78800_c * a3), (double)(a4.field_78797_d * a3), (double)(a4.field_78798_e * a3));
        }
        if (a4.field_78808_h != 0.0f) {
            a2.ALLATORIxDEMO(gg.o.ALLATORIxDEMO(a4.field_78808_h));
        }
        if (a4.field_78796_g != 0.0f) {
            a2.ALLATORIxDEMO(gg.q.ALLATORIxDEMO(a4.field_78796_g));
        }
        if (a4.field_78795_f != 0.0f) {
            a2.ALLATORIxDEMO(gg.m.ALLATORIxDEMO(a4.field_78795_f));
        }
        a2.x(nk.c(a4.b.getX(), a4.b.getY(), a4.b.getZ()));
        return a2;
    }

    public nk backPosition(nk a2, float a3) {
        mt a4;
        if (a4.field_78800_c != 0.0f || a4.field_78797_d != 0.0f || a4.field_78798_e != 0.0f) {
            a2.ALLATORIxDEMO((double)(-a4.field_78800_c * a3), (double)(-a4.field_78797_d * a3), (double)(-a4.field_78798_e * a3));
        }
        if (a4.m != null) {
            a4.m.backPosition(a2, a3);
        }
        return a2;
    }

    public void func_78791_b(float a2) {
        mt a3;
        if (!a3.field_78807_k && a3.field_78806_j) {
            if (!a3.o) {
                a3.func_78788_d(a2);
            }
            GlStateManager.func_179094_E();
            a3.ALLATORIxDEMO(a2);
            GlStateManager.func_179148_o((int)a3.k);
            GlStateManager.func_179121_F();
        }
    }

    private /* synthetic */ void c(float a2) {
        mt a3;
        GlStateManager.func_179152_a((float)a3.b.getX(), (float)a3.b.getY(), (float)a3.b.getZ());
        if (a3.y) {
            GlStateManager.func_179148_o((int)a3.k);
        }
        if (a3.field_78805_m != null) {
            for (ModelRenderer a4 : a3.field_78805_m) {
                a4.func_78785_a(a2);
            }
        }
        GlStateManager.func_179152_a((float)(1.0f / a3.b.getX()), (float)(1.0f / a3.b.getY()), (float)(1.0f / a3.b.getZ()));
    }

    public void func_78794_c(float a2) {
        mt a3;
        if (!a3.field_78807_k && a3.field_78806_j) {
            if (!a3.o) {
                a3.func_78788_d(a2);
            }
            a3.ALLATORIxDEMO(a2);
        }
    }

    public void applyTransAndRotations(float a2) {
        mt a3;
        GlStateManager.func_179109_b((float)(a3.field_82906_o * a2), (float)(a3.field_82908_p * a2), (float)(a3.field_82907_q * a2));
        a3.ALLATORIxDEMO(a2);
    }

    private /* synthetic */ void ALLATORIxDEMO(float a2) {
        mt a3;
        if (a3.field_78800_c != 0.0f || a3.field_78797_d != 0.0f || a3.field_78798_e != 0.0f) {
            GlStateManager.func_179109_b((float)(a3.field_78800_c * a2), (float)(a3.field_78797_d * a2), (float)(a3.field_78798_e * a2));
        }
        if (a3.ALLATORIxDEMO != null) {
            a3.ALLATORIxDEMO.run();
        }
        if (a3.field_78808_h != 0.0f) {
            GlStateManager.func_179114_b((float)a3.field_78808_h, (float)0.0f, (float)0.0f, (float)1.0f);
        }
        if (a3.field_78796_g != 0.0f) {
            GlStateManager.func_179114_b((float)a3.field_78796_g, (float)0.0f, (float)1.0f, (float)0.0f);
        }
        if (a3.field_78795_f != 0.0f) {
            GlStateManager.func_179114_b((float)a3.field_78795_f, (float)1.0f, (float)0.0f, (float)0.0f);
        }
    }

    public void resetData() {
        mt a2;
        a2.field_78795_f = a2.q.getX();
        a2.field_78796_g = a2.q.getY();
        a2.field_78808_h = a2.q.getZ();
        a2.field_82906_o = 0.0f;
        a2.field_82908_p = 0.0f;
        a2.field_82907_q = 0.0f;
        a2.b.set(1.0f, 1.0f, 1.0f);
        if (a2.field_78805_m != null) {
            for (ModelRenderer a3 : a2.field_78805_m) {
                if (!(a3 instanceof mt)) continue;
                ((mt)a3).resetData();
            }
        }
    }

    public void func_78788_d(float a2) {
        mt a3;
        a3.k = GLAllocation.func_74526_a((int)1);
        GlStateManager.func_187423_f((int)a3.k, (int)4864);
        BufferBuilder a4 = Tessellator.func_178181_a().func_178180_c();
        for (ModelBox modelBox : a3.field_78804_l) {
            modelBox.func_178780_a(a4, a2);
        }
        for (xu xu2 : a3.c) {
            xu2.ALLATORIxDEMO(a4, a2);
        }
        GlStateManager.func_187415_K();
        a3.o = true;
    }

    @Override
    public void setScaleFactor(float a2, float a3, float a4) {
        mt a5;
        a5.b.set(a2, a3, a4);
    }

    @Override
    public bax getScaleFactor() {
        mt a2;
        return a2.b;
    }

    @Override
    public bax getStartRotationAngles() {
        mt a2;
        return a2.q;
    }

    public mt getPiece(String a2) {
        mt a3;
        for (ModelRenderer a4 : a3.field_78805_m) {
            if (!(a4 instanceof mt) || !a2.equalsIgnoreCase(a4.field_78802_n)) continue;
            return (mt)a4;
        }
        return null;
    }

    public ModelRenderer findPiece(String a2) {
        mt a3;
        for (ModelRenderer a4 : a3.field_78805_m) {
            if (a2.equalsIgnoreCase(a4.field_78802_n)) {
                return a4;
            }
            if (!(a4 instanceof mt)) continue;
            mt a5 = (mt)a4;
            return a5.findPiece(a2);
        }
        return null;
    }

    public String getTopParentName() {
        mt a2;
        if (a2.m != null) {
            return a2.m.field_78802_n;
        }
        return a2.field_78802_n;
    }
}

