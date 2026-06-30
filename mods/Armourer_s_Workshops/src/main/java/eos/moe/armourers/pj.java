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
        a2.rotateAngleX = a3;
        modelRenderer.rotateAngleY = a4;
        modelRenderer.rotateAngleZ = a5;
    }

    public void setRotation(ModelRenderer a2, ModelRenderer a3) {
        ModelRenderer modelRenderer = a2;
        ModelRenderer modelRenderer2 = a3;
        a2.rotateAngleX = modelRenderer2.rotateAngleX;
        modelRenderer.rotateAngleY = modelRenderer2.rotateAngleY;
        modelRenderer.rotateAngleZ = a3.rotateAngleZ;
    }

    public void setRotationFromModelBiped(ModelBiped a2, boolean a3) {
        pj a4;
        pj pj2 = a4;
        a4.isRiding = false;
        pj2.isSneak = false;
        pj2.s = false;
        if (a2 == null) {
            pj pj3 = a4;
            pj pj4 = a4;
            pj4.setRotation(pj4.bipedHead, 0.0f, 0.0f, 0.0f);
            pj4.setRotation(pj4.bipedBody, 0.0f, 0.0f, 0.0f);
            pj4.setRotation(pj4.bipedLeftArm, 0.0f, 0.0f, 0.0f);
            pj4.setRotation(pj4.bipedRightArm, 0.0f, 0.0f, 0.0f);
            pj4.setRotation(pj4.bipedLeftLeg, 0.0f, 0.0f, 0.0f);
            pj3.setRotation(pj3.bipedRightLeg, 0.0f, 0.0f, 0.0f);
            pj3.isChild = false;
            return;
        }
        if (a2 instanceof ModelPlayer) {
            ModelPlayer modelPlayer = (ModelPlayer)a2;
            a4.s = modelPlayer.bipedLeftArm.rotationPointY == 2.5f;
        }
        pj pj5 = a4;
        pj5.setRotation(pj5.bipedHead, a2.bipedHead);
        pj5.setRotation(pj5.bipedBody, a2.bipedBody);
        pj5.setRotation(pj5.bipedLeftArm, a2.bipedLeftArm);
        pj5.setRotation(pj5.bipedRightArm, a2.bipedRightArm);
        pj5.setRotation(pj5.bipedLeftLeg, a2.bipedLeftLeg);
        ModelBiped modelBiped = a2;
        pj5.setRotation(pj5.bipedRightLeg, modelBiped.bipedRightLeg);
        pj5.isChild = modelBiped.isChild;
        if (a3) {
            a4.isChild = false;
        }
    }

    @Override
    public void render(Entity a2, yl a3, ModelBiped a4, dn a5) {
        pj a6;
        pj pj2 = a6;
        pj2.setRotationFromModelBiped(a4, a5.z());
        pj2.render(a2, a3, a5);
    }

    public void render(Entity a2, float a3, float a4, float a5, float a6, float a7, float a8) {
        pj a9;
        if (a9.j != null) {
            pj pj2 = a9;
            pj pj3 = a9;
            pj3.isRiding = false;
            pj3.isSneak = false;
            pj2.isChild = false;
            pj2.s = false;
            if (a2 instanceof EntityLivingBase) {
                if (((EntityLivingBase)a2).isRiding()) {
                    a9.isRiding = true;
                }
                if (((EntityLivingBase)a2).isSneaking()) {
                    a9.isSneak = true;
                }
                if (((EntityLivingBase)a2).isChild()) {
                    a9.isChild = true;
                }
            }
            pj pj4 = a9;
            pj4.bipedLeftLeg.rotateAngleZ = 0.0f;
            pj4.bipedRightLeg.rotateAngleZ = 0.0f;
            a9.bipedHead.rotateAngleZ = 0.0f;
            a9.bipedHeadwear.rotateAngleZ = 0.0f;
            super.setRotationAngles(a3, a4, a5, a6, a7, a8, a2);
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
        pj2.setRotationAngles(a4, a5, a6, a7, a8, v, a2);
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

    public void setRotationAngles(float a2, float a3, float a4, float a5, float a6, float a7, Entity a8) {
        pj a9;
        pj pj2 = a9;
        pj pj3 = a9;
        pj pj4 = a9;
        super.setRotationAngles(a2, a3, a4, a5, a6, a7, a8);
        pj4.isRiding = false;
        pj4.isSneak = false;
        pj3.isChild = false;
        pj2.s = false;
        pj3.bipedLeftLeg.rotateAngleZ = 0.0f;
        pj2.bipedRightLeg.rotateAngleZ = 0.0f;
        pj2.bipedHead.rotateAngleZ = 0.0f;
        pj2.bipedHeadwear.rotateAngleZ = 0.0f;
    }

    @Override
    public void render(Entity a2, yl a3, ModelBiped a4, boolean a5, n a6, oh a7, boolean a8, double a9, boolean a10) {
        pj a11;
        pj pj2 = a11;
        pj2.setRotationFromModelBiped(a4, false);
        pj2.render(a2, a3, a5, a6, a7, a8, a9, a10);
    }
}

