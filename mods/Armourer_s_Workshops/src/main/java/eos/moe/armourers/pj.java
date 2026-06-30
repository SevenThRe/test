/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.model.ModelBiped
 *  net.minecraft.client.model.ModelPlayer
 *  net.minecraft.client.model.ModelRenderer
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraftforge.fml.relauncher.Side
 *  net.minecraftforge.fml.relauncher.SideOnly
 *  org.lwjgl.opengl.GL11
 */
package eos.moe.armourers;

import eos.moe.armourers.dn;
import eos.moe.armourers.mk;
import eos.moe.armourers.n;
import eos.moe.armourers.oh;
import eos.moe.armourers.p;
import eos.moe.armourers.rk;
import eos.moe.armourers.vn;
import eos.moe.armourers.xd;
import eos.moe.armourers.y;
import eos.moe.armourers.yl;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.model.ModelPlayer;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.lwjgl.opengl.GL11;

@SideOnly(value=Side.CLIENT)
public abstract class pj
extends ModelBiped
implements p {
    public static float v = 0.0625f;
    public boolean s;
    public n m;
    public yl j;

    public void setRotation(ModelRenderer a2, float a3, float a4, float a5) {
        ModelRenderer modelRenderer = a2;
        a2.field_78795_f = a3;
        modelRenderer.field_78796_g = a4;
        modelRenderer.field_78808_h = a5;
    }

    public void setRotation(ModelRenderer a2, ModelRenderer a3) {
        ModelRenderer modelRenderer = a2;
        ModelRenderer modelRenderer2 = a3;
        a2.field_78795_f = modelRenderer2.field_78795_f;
        modelRenderer.field_78796_g = modelRenderer2.field_78796_g;
        modelRenderer.field_78808_h = a3.field_78808_h;
    }

    public void setRotationFromModelBiped(ModelBiped a2, boolean a3) {
        pj a4;
        pj pj2 = a4;
        a4.field_78093_q = false;
        pj2.field_78117_n = false;
        pj2.s = false;
        if (a2 == null) {
            pj pj3 = a4;
            pj pj4 = a4;
            pj4.setRotation(pj4.field_78116_c, 0.0f, 0.0f, 0.0f);
            pj4.setRotation(pj4.field_78115_e, 0.0f, 0.0f, 0.0f);
            pj4.setRotation(pj4.field_178724_i, 0.0f, 0.0f, 0.0f);
            pj4.setRotation(pj4.field_178723_h, 0.0f, 0.0f, 0.0f);
            pj4.setRotation(pj4.field_178722_k, 0.0f, 0.0f, 0.0f);
            pj3.setRotation(pj3.field_178721_j, 0.0f, 0.0f, 0.0f);
            pj3.field_78091_s = false;
            return;
        }
        if (a2 instanceof ModelPlayer) {
            ModelPlayer modelPlayer = (ModelPlayer)a2;
            a4.s = modelPlayer.field_178724_i.field_78797_d == 2.5f;
        }
        pj pj5 = a4;
        pj5.setRotation(pj5.field_78116_c, a2.field_78116_c);
        pj5.setRotation(pj5.field_78115_e, a2.field_78115_e);
        pj5.setRotation(pj5.field_178724_i, a2.field_178724_i);
        pj5.setRotation(pj5.field_178723_h, a2.field_178723_h);
        pj5.setRotation(pj5.field_178722_k, a2.field_178722_k);
        ModelBiped modelBiped = a2;
        pj5.setRotation(pj5.field_178721_j, modelBiped.field_178721_j);
        pj5.field_78091_s = modelBiped.field_78091_s;
        if (a3) {
            a4.field_78091_s = false;
        }
    }

    @Override
    public void render(Entity a2, yl a3, ModelBiped a4, dn a5) {
        pj a6;
        pj pj2 = a6;
        pj2.setRotationFromModelBiped(a4, a5.z());
        pj2.render(a2, a3, a5);
    }

    public void func_78088_a(Entity a2, float a3, float a4, float a5, float a6, float a7, float a8) {
        pj a9;
        if (a9.j != null) {
            pj pj2 = a9;
            pj pj3 = a9;
            pj3.field_78093_q = false;
            pj3.field_78117_n = false;
            pj2.field_78091_s = false;
            pj2.s = false;
            if (a2 instanceof EntityLivingBase) {
                if (((EntityLivingBase)a2).func_184218_aH()) {
                    a9.field_78093_q = true;
                }
                if (((EntityLivingBase)a2).func_70093_af()) {
                    a9.field_78117_n = true;
                }
                if (((EntityLivingBase)a2).func_70631_g_()) {
                    a9.field_78091_s = true;
                }
            }
            pj pj4 = a9;
            pj4.field_178722_k.field_78808_h = 0.0f;
            pj4.field_178721_j.field_78808_h = 0.0f;
            a9.field_78116_c.field_78808_h = 0.0f;
            a9.field_178720_f.field_78808_h = 0.0f;
            super.func_78087_a(a3, a4, a5, a6, a7, a8, a2);
            GL11.glPushAttrib((int)8192);
            GL11.glEnable((int)2884);
            xd.z();
            a9.render(a2, a9.j, false, a9.m, null, false, 0.0, true);
            xd.r();
            GL11.glPopAttrib();
            a9.j = null;
            a9.m = null;
        }
    }

    public abstract void render(Entity var1, yl var2, dn var3);

    @Override
    public void render(Entity a2, yl a3, float a4, float a5, float a6, float a7, float a8) {
        pj a9;
        pj pj2 = a9;
        pj2.func_78087_a(a4, a5, a6, a7, a8, v, a2);
        GL11.glPushAttrib((int)8192);
        GL11.glEnable((int)2884);
        xd.z();
        pj2.render(a2, a3, false, null, null, false, 0.0, true);
        xd.r();
        GL11.glPopAttrib();
    }

    public pj() {
        pj a2;
        pj pj2 = a2;
        pj2.j = null;
        pj2.m = null;
    }

    public boolean skinHasHead(yl a2) {
        if (a2.r() == vn.l) {
            return true;
        }
        if (a2.r() == vn.u) {
            int n2;
            y y2 = vn.l.r().get(0);
            int n3 = n2 = 0;
            while (n3 < a2.y()) {
                if (a2.r().get(n2).r() == y2) {
                    return true;
                }
                n3 = ++n2;
            }
        }
        return false;
    }

    public void renderPart(mk a2) {
        rk.j.renderPart(a2);
    }

    public abstract void render(Entity var1, yl var2, boolean var3, n var4, oh var5, boolean var6, double var7, boolean var9);

    public void func_78087_a(float a2, float a3, float a4, float a5, float a6, float a7, Entity a8) {
        pj a9;
        pj pj2 = a9;
        pj pj3 = a9;
        pj pj4 = a9;
        super.func_78087_a(a2, a3, a4, a5, a6, a7, a8);
        pj4.field_78093_q = false;
        pj4.field_78117_n = false;
        pj3.field_78091_s = false;
        pj2.s = false;
        pj3.field_178722_k.field_78808_h = 0.0f;
        pj2.field_178721_j.field_78808_h = 0.0f;
        pj2.field_78116_c.field_78808_h = 0.0f;
        pj2.field_178720_f.field_78808_h = 0.0f;
    }

    @Override
    public void render(Entity a2, yl a3, ModelBiped a4, boolean a5, n a6, oh a7, boolean a8, double a9, boolean a10) {
        pj a11;
        pj pj2 = a11;
        pj2.setRotationFromModelBiped(a4, false);
        pj2.render(a2, a3, a5, a6, a7, a8, a9, a10);
    }
}

