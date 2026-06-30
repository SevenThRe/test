/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.model.ModelBase
 *  net.minecraft.client.model.ModelBiped
 *  net.minecraft.client.renderer.GlStateManager
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.util.EnumHand
 *  net.minecraft.util.EnumHandSide
 *  net.minecraft.util.math.MathHelper
 */
package eos.moe.dragoncore;

import eos.moe.dragoncore.ju;
import eos.moe.dragoncore.po;
import eos.moe.dragoncore.ss;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.EnumHand;
import net.minecraft.util.EnumHandSide;
import net.minecraft.util.math.MathHelper;

public class rq
extends ss {
    public ju c;
    public ju q;
    public ju b;
    public ju o;
    public ju y;
    public ju k;
    public boolean ALLATORIxDEMO;

    public rq(po a2, String a3, String a4, String a5, String a6, String a7, String a8) {
        super(a2);
        rq a9;
        a9.initParts(a3, a4, a5, a6, a7, a8);
    }

    public void initParts(String a2, String a3, String a4, String a5, String a6, String a7) {
        rq a8;
        for (ju a9 : a8.ALLATORIxDEMO.ALLATORIxDEMO()) {
            if (a9.ALLATORIxDEMO().equals(a2)) {
                a8.c = a9;
                continue;
            }
            if (a9.ALLATORIxDEMO().equals(a3)) {
                a8.q = a9;
                continue;
            }
            if (a9.ALLATORIxDEMO().equals(a4)) {
                a8.o = a9;
                continue;
            }
            if (a9.ALLATORIxDEMO().equals(a5)) {
                a8.b = a9;
                continue;
            }
            if (a9.ALLATORIxDEMO().equals(a6)) {
                a8.k = a9;
                continue;
            }
            if (!a9.ALLATORIxDEMO().equals(a7)) continue;
            a8.y = a9;
        }
    }

    @Override
    public void render(Entity a2, float a3, float a4, float a5, float a6, float a7, float a8) {
        rq a9;
        a9.setRotationAngles(a3, a4, a5, a6, a7, a8, a2);
        GlStateManager.pushMatrix();
        if (a9.isChild) {
            float a10 = 2.0f;
            GlStateManager.scale((float)0.75f, (float)0.75f, (float)0.75f);
            GlStateManager.translate((float)0.0f, (float)(16.0f * a8), (float)0.0f);
            a9.c.d(a8);
            GlStateManager.popMatrix();
            GlStateManager.pushMatrix();
            GlStateManager.scale((float)0.5f, (float)0.5f, (float)0.5f);
            GlStateManager.translate((float)0.0f, (float)(24.0f * a8), (float)0.0f);
            a9.q.d(a8);
            a9.b.d(a8);
            a9.o.d(a8);
            a9.y.d(a8);
            a9.k.d(a8);
        } else {
            if (a2.isSneaking()) {
                GlStateManager.translate((float)0.0f, (float)0.2f, (float)0.0f);
            }
            a9.c.d(a8);
            a9.q.d(a8);
            a9.b.d(a8);
            a9.o.d(a8);
            a9.y.d(a8);
            a9.k.d(a8);
        }
        GlStateManager.popMatrix();
    }

    public void setRotationAngles(float a2, float a3, float a4, float a5, float a6, float a7, Entity a8) {
        rq a9;
        boolean a10 = a8 instanceof EntityLivingBase && ((EntityLivingBase)a8).getTicksElytraFlying() > 4;
        a9.c.g = a5 * ((float)Math.PI / 180);
        a9.c.s = a10 ? -0.7853982f : a6 * ((float)Math.PI / 180);
        a9.q.g = 0.0f;
        float a11 = 1.0f;
        if (a10) {
            a11 = (float)(a8.motionX * a8.motionX + a8.motionY * a8.motionY + a8.motionZ * a8.motionZ);
            a11 /= 0.2f;
            a11 = a11 * a11 * a11;
        }
        if (a11 < 1.0f) {
            a11 = 1.0f;
        }
        a9.b.s = MathHelper.cos((float)(a2 * 0.6662f + (float)Math.PI)) * 2.0f * a3 * 0.5f / a11;
        a9.o.s = MathHelper.cos((float)(a2 * 0.6662f)) * 2.0f * a3 * 0.5f / a11;
        a9.b.t = 0.0f;
        a9.o.t = 0.0f;
        a9.y.s = MathHelper.cos((float)(a2 * 0.6662f)) * 1.4f * a3 / a11;
        a9.k.s = MathHelper.cos((float)(a2 * 0.6662f + (float)Math.PI)) * 1.4f * a3 / a11;
        a9.y.g = 0.0f;
        a9.k.g = 0.0f;
        a9.y.t = 0.0f;
        a9.k.t = 0.0f;
        if (a9.isRiding) {
            a9.b.s += -0.62831855f;
            a9.o.s += -0.62831855f;
            a9.y.s = -1.4137167f;
            a9.y.g = 0.31415927f;
            a9.y.t = 0.07853982f;
            a9.k.s = -1.4137167f;
            a9.k.g = -0.31415927f;
            a9.k.t = -0.07853982f;
        }
        a9.b.g = 0.0f;
        a9.b.t = 0.0f;
        a9.b.t += MathHelper.cos((float)(a4 * 0.09f)) * 0.05f + 0.05f;
        a9.o.t -= MathHelper.cos((float)(a4 * 0.09f)) * 0.05f + 0.05f;
        a9.b.s += MathHelper.sin((float)(a4 * 0.067f)) * 0.05f;
        a9.o.s -= MathHelper.sin((float)(a4 * 0.067f)) * 0.05f;
    }

    public void setModelAttributes(ModelBase a2) {
        rq a3;
        super.setModelAttributes(a2);
        if (a2 instanceof ModelBiped) {
            ModelBiped a4 = (ModelBiped)a2;
            a3.ALLATORIxDEMO = a4.isSneak;
        }
    }

    public void setVisible(boolean a2) {
        a.c.q = !a2;
        a.q.q = !a2;
        a.b.q = !a2;
        a.o.q = !a2;
        a.y.q = !a2;
        a.k.q = !a2;
    }

    public ju getArmForSide(EnumHandSide a2) {
        rq a3;
        return a2 == EnumHandSide.LEFT ? a3.o : a3.b;
    }

    public EnumHandSide getMainHand(Entity a2) {
        if (a2 instanceof EntityLivingBase) {
            EntityLivingBase a3 = (EntityLivingBase)a2;
            EnumHandSide a4 = a3.getPrimaryHand();
            return a3.swingingHand == EnumHand.MAIN_HAND ? a4 : a4.opposite();
        }
        return EnumHandSide.RIGHT;
    }
}

