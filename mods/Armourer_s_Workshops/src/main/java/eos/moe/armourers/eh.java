/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.renderer.GlStateManager
 *  net.minecraft.client.renderer.RenderHelper
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.util.EnumFacing
 *  org.lwjgl.opengl.GL11
 */
package eos.moe.armourers;

import eos.moe.armourers.bo;
import eos.moe.armourers.dn;
import eos.moe.armourers.if;
import eos.moe.armourers.in;
import eos.moe.armourers.kf;
import eos.moe.armourers.mk;
import eos.moe.armourers.n;
import eos.moe.armourers.oh;
import eos.moe.armourers.pj;
import eos.moe.armourers.rd;
import eos.moe.armourers.ti;
import eos.moe.armourers.yl;
import java.util.ArrayList;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.EnumFacing;
import org.lwjgl.opengl.GL11;

public class eh
extends pj {
    private /* synthetic */ void w(mk a2) {
        eh a3;
        GL11.glPushMatrix();
        GL11.glColor3f((float)1.0f, (float)1.0f, (float)1.0f);
        if (a3.isSneak) {
            GlStateManager.translate((float)0.0f, (float)0.2f, (float)0.0f);
            GlStateManager.translate((float)0.0f, (float)(-3.0f * a2.r()), (float)(4.0f * a2.r()));
        }
        GL11.glTranslated((double)0.0, (double)(12.0f * a2.r()), (double)0.0);
        GL11.glTranslated((double)(-2.0f * a2.r()), (double)0.0, (double)0.0);
        eh eh2 = a3;
        GL11.glRotatef((float)((float)Math.toDegrees(a3.bipedRightLeg.rotateAngleZ)), (float)0.0f, (float)0.0f, (float)1.0f);
        GL11.glRotatef((float)((float)Math.toDegrees(eh2.bipedRightLeg.rotateAngleY)), (float)0.0f, (float)1.0f, (float)0.0f);
        GL11.glRotatef((float)((float)Math.toDegrees(eh2.bipedRightLeg.rotateAngleX)), (float)1.0f, (float)0.0f, (float)0.0f);
        eh2.renderPart(a2);
        GL11.glPopMatrix();
    }

    /*
     * Enabled aggressive block sorting
     */
    private /* synthetic */ void y(mk a2, double a3) {
        mk mk2;
        eh a4;
        GL11.glPushMatrix();
        if (a4.isSneak) {
            GlStateManager.translate((float)0.0f, (float)0.2f, (float)0.0f);
            GlStateManager.rotate((float)((float)Math.toDegrees(a4.bipedBody.rotateAngleX)), (float)1.0f, (float)0.0f, (float)0.0f);
        }
        GL11.glTranslated((double)0.0, (double)0.0, (double)(a2.r() * 2.0f));
        if if_ = new if(0, 0, 0);
        EnumFacing enumFacing = EnumFacing.DOWN;
        if (a2.r().r() > 0) {
            mk mk3 = a2;
            if_ = mk3.r().r(0);
            enumFacing = mk3.r().r(0);
        }
        eh eh2 = a4;
        GL11.glRotatef((float)((float)Math.toDegrees(eh2.bipedBody.rotateAngleZ)), (float)0.0f, (float)0.0f, (float)1.0f);
        GL11.glRotatef((float)((float)Math.toDegrees(eh2.bipedBody.rotateAngleY)), (float)0.0f, (float)1.0f, (float)0.0f);
        mk mk4 = a2;
        GL11.glTranslated((double)(a2.r() * 0.5f), (double)(mk4.r() * 0.5f), (double)(a2.r() * 0.5f));
        GL11.glTranslated((double)(mk4.r() * (float)if_.z()), (double)(a2.r() * (float)if_.r()), (double)(a2.r() * (float)if_.y()));
        switch (enumFacing) {
            case UP: {
                GL11.glRotated((double)a3, (double)0.0, (double)1.0, (double)0.0);
                mk2 = a2;
                break;
            }
            case DOWN: {
                GL11.glRotated((double)a3, (double)0.0, (double)-1.0, (double)0.0);
                mk2 = a2;
                break;
            }
            case SOUTH: {
                GL11.glRotated((double)a3, (double)0.0, (double)0.0, (double)-1.0);
                mk2 = a2;
                break;
            }
            case NORTH: {
                GL11.glRotated((double)a3, (double)0.0, (double)0.0, (double)1.0);
                mk2 = a2;
                break;
            }
            case EAST: {
                GL11.glRotated((double)a3, (double)1.0, (double)0.0, (double)0.0);
                mk2 = a2;
                break;
            }
            case WEST: {
                GL11.glRotated((double)a3, (double)-1.0, (double)0.0, (double)0.0);
            }
            default: {
                mk2 = a2;
            }
        }
        GL11.glTranslated((double)(mk2.r() * (float)(-if_.z())), (double)(a2.r() * (float)(-if_.r())), (double)(a2.r() * (float)(-if_.y())));
        mk mk5 = a2;
        GL11.glTranslated((double)(a2.r() * -0.5f), (double)(mk5.r() * -0.5f), (double)(a2.r() * -0.5f));
        a4.renderPart(mk5);
        GL11.glPopMatrix();
    }

    private /* synthetic */ void r(mk a2, boolean a3, boolean a4) {
        eh a5;
        GL11.glPushMatrix();
        if (a5.isSneak && !a4) {
            GlStateManager.translate((float)0.0f, (float)0.2f, (float)0.0f);
        }
        GL11.glTranslatef((float)(-5.0f * a2.r()), (float)0.0f, (float)0.0f);
        GL11.glTranslatef((float)0.0f, (float)(2.0f * a2.r()), (float)0.0f);
        eh eh2 = a5;
        GL11.glRotatef((float)((float)Math.toDegrees(eh2.bipedRightArm.rotateAngleZ)), (float)0.0f, (float)0.0f, (float)1.0f);
        GL11.glRotatef((float)((float)Math.toDegrees(eh2.bipedRightArm.rotateAngleY)), (float)0.0f, (float)1.0f, (float)0.0f);
        GL11.glRotatef((float)((float)Math.toDegrees(eh2.bipedRightArm.rotateAngleX)), (float)1.0f, (float)0.0f, (float)0.0f);
        if (eh2.s & !a3) {
            GL11.glTranslatef((float)(0.25f * a2.r()), (float)0.0f, (float)0.0f);
            GL11.glTranslatef((float)0.0f, (float)(0.5f * a2.r()), (float)0.0f);
            GL11.glScalef((float)0.75f, (float)1.0f, (float)1.0f);
        }
        a5.renderPart(a2);
        GL11.glPopMatrix();
    }

    private /* synthetic */ void s(mk a2) {
        eh a3;
        GL11.glPushMatrix();
        GL11.glColor3f((float)1.0f, (float)1.0f, (float)1.0f);
        if (a3.isSneak) {
            GlStateManager.translate((float)0.0f, (float)0.2f, (float)0.0f);
            GlStateManager.translate((float)0.0f, (float)(-3.0f * a2.r()), (float)(4.0f * a2.r()));
        }
        GL11.glTranslated((double)0.0, (double)(12.0f * a2.r()), (double)0.0);
        GL11.glTranslated((double)(-2.0f * a2.r()), (double)0.0, (double)0.0);
        eh eh2 = a3;
        GL11.glRotatef((float)((float)Math.toDegrees(a3.bipedRightLeg.rotateAngleZ)), (float)0.0f, (float)0.0f, (float)1.0f);
        GL11.glRotatef((float)((float)Math.toDegrees(eh2.bipedRightLeg.rotateAngleY)), (float)0.0f, (float)1.0f, (float)0.0f);
        GL11.glRotatef((float)((float)Math.toDegrees(eh2.bipedRightLeg.rotateAngleX)), (float)1.0f, (float)0.0f, (float)0.0f);
        eh2.renderPart(a2);
        GL11.glPopMatrix();
    }

    public eh() {
        eh a2;
    }

    /*
     * Enabled aggressive block sorting
     */
    private /* synthetic */ void r(mk a2, double a3) {
        mk mk2;
        eh a4;
        GL11.glPushMatrix();
        if (a4.isSneak) {
            GlStateManager.translate((float)0.0f, (float)0.2f, (float)0.0f);
            GlStateManager.rotate((float)((float)Math.toDegrees(a4.bipedBody.rotateAngleX)), (float)1.0f, (float)0.0f, (float)0.0f);
        }
        GL11.glTranslated((double)0.0, (double)0.0, (double)(a2.r() * 2.0f));
        if if_ = new if(0, 0, 0);
        EnumFacing enumFacing = EnumFacing.DOWN;
        if (a2.r().r() > 0) {
            mk mk3 = a2;
            if_ = mk3.r().r(0);
            enumFacing = mk3.r().r(0);
        }
        eh eh2 = a4;
        GL11.glRotatef((float)((float)Math.toDegrees(eh2.bipedBody.rotateAngleZ)), (float)0.0f, (float)0.0f, (float)1.0f);
        GL11.glRotatef((float)((float)Math.toDegrees(eh2.bipedBody.rotateAngleY)), (float)0.0f, (float)1.0f, (float)0.0f);
        mk mk4 = a2;
        GL11.glTranslated((double)(a2.r() * 0.5f), (double)(mk4.r() * 0.5f), (double)(a2.r() * 0.5f));
        GL11.glTranslated((double)(mk4.r() * (float)if_.z()), (double)(a2.r() * (float)if_.r()), (double)(a2.r() * (float)if_.y()));
        switch (enumFacing) {
            case UP: {
                GL11.glRotated((double)a3, (double)0.0, (double)1.0, (double)0.0);
                mk2 = a2;
                break;
            }
            case DOWN: {
                GL11.glRotated((double)a3, (double)0.0, (double)-1.0, (double)0.0);
                mk2 = a2;
                break;
            }
            case SOUTH: {
                GL11.glRotated((double)a3, (double)0.0, (double)0.0, (double)-1.0);
                mk2 = a2;
                break;
            }
            case NORTH: {
                GL11.glRotated((double)a3, (double)0.0, (double)0.0, (double)1.0);
                mk2 = a2;
                break;
            }
            case EAST: {
                GL11.glRotated((double)a3, (double)1.0, (double)0.0, (double)0.0);
                mk2 = a2;
                break;
            }
            case WEST: {
                GL11.glRotated((double)a3, (double)-1.0, (double)0.0, (double)0.0);
            }
            default: {
                mk2 = a2;
            }
        }
        GL11.glTranslated((double)(mk2.r() * (float)(-if_.z())), (double)(a2.r() * (float)(-if_.r())), (double)(a2.r() * (float)(-if_.y())));
        mk mk5 = a2;
        GL11.glTranslated((double)(a2.r() * -0.5f), (double)(mk5.r() * -0.5f), (double)(a2.r() * -0.5f));
        a4.renderPart(mk5);
        GL11.glPopMatrix();
    }

    private /* synthetic */ void r(mk a2, boolean a3) {
        eh a4;
        GL11.glPushMatrix();
        if (a4.isSneak) {
            GlStateManager.translate((float)0.0f, (float)0.2f, (float)0.0f);
        }
        GL11.glTranslatef((float)(5.0f * a2.r()), (float)0.0f, (float)0.0f);
        GL11.glTranslatef((float)0.0f, (float)(2.0f * a2.r()), (float)0.0f);
        eh eh2 = a4;
        GL11.glRotatef((float)((float)Math.toDegrees(eh2.bipedLeftArm.rotateAngleZ)), (float)0.0f, (float)0.0f, (float)1.0f);
        GL11.glRotatef((float)((float)Math.toDegrees(eh2.bipedLeftArm.rotateAngleY)), (float)0.0f, (float)1.0f, (float)0.0f);
        GL11.glRotatef((float)((float)Math.toDegrees(eh2.bipedLeftArm.rotateAngleX)), (float)1.0f, (float)0.0f, (float)0.0f);
        if (eh2.s & !a3) {
            GL11.glTranslatef((float)(-0.25f * a2.r()), (float)0.0f, (float)0.0f);
            GL11.glTranslatef((float)0.0f, (float)(0.5f * a2.r()), (float)0.0f);
            GL11.glScalef((float)0.75f, (float)1.0f, (float)1.0f);
        }
        a4.renderPart(a2);
        GL11.glPopMatrix();
    }

    private /* synthetic */ void x(mk a2) {
        eh a3;
        GL11.glPushMatrix();
        if (a3.isSneak) {
            GlStateManager.translate((float)0.0f, (float)0.2f, (float)0.0f);
            GlStateManager.rotate((float)((float)Math.toDegrees(a3.bipedBody.rotateAngleX)), (float)1.0f, (float)0.0f, (float)0.0f);
        }
        GL11.glColor3f((float)1.0f, (float)1.0f, (float)1.0f);
        a3.renderPart(a2);
        GL11.glPopMatrix();
    }

    private /* synthetic */ void h(mk a2) {
        eh a3;
        GL11.glPushMatrix();
        GL11.glColor3f((float)1.0f, (float)1.0f, (float)1.0f);
        if (a3.isSneak) {
            GlStateManager.translate((float)0.0f, (float)0.2f, (float)0.0f);
            GlStateManager.translate((float)0.0f, (float)(-3.0f * a2.r()), (float)(4.0f * a2.r()));
        }
        GL11.glTranslated((double)0.0, (double)(12.0f * a2.r()), (double)0.0);
        if (a3.isRiding) {
            GL11.glRotated((double)-70.0, (double)1.0, (double)0.0, (double)0.0);
        }
        a3.renderPart(a2);
        GL11.glPopMatrix();
    }

    private /* synthetic */ void z(mk a2) {
        eh a3;
        GL11.glPushMatrix();
        GL11.glColor3f((float)1.0f, (float)1.0f, (float)1.0f);
        if (a3.isSneak) {
            GlStateManager.translate((float)0.0f, (float)0.2f, (float)0.0f);
            GlStateManager.translate((float)0.0f, (float)(-3.0f * a2.r()), (float)(4.0f * a2.r()));
        }
        GL11.glTranslated((double)0.0, (double)(12.0f * a2.r()), (double)0.0);
        GL11.glTranslated((double)(2.0f * a2.r()), (double)0.0, (double)0.0);
        eh eh2 = a3;
        GL11.glRotatef((float)((float)Math.toDegrees(a3.bipedLeftLeg.rotateAngleZ)), (float)0.0f, (float)0.0f, (float)1.0f);
        GL11.glRotatef((float)((float)Math.toDegrees(eh2.bipedLeftLeg.rotateAngleY)), (float)0.0f, (float)1.0f, (float)0.0f);
        GL11.glRotatef((float)((float)Math.toDegrees(eh2.bipedLeftLeg.rotateAngleX)), (float)1.0f, (float)0.0f, (float)0.0f);
        eh2.renderPart(a2);
        GL11.glPopMatrix();
    }

    @Override
    public void render(Entity a2, yl a3, dn a4) {
        int n2;
        eh a5;
        Object object;
        if (a3 == null) {
            return;
        }
        ArrayList<kf> arrayList = a3.y();
        if (a2 instanceof EntityPlayer) {
            object = (EntityPlayer)a2;
            eh eh2 = a5;
            eh2.isSneak = object.isSneaking();
            eh2.isRiding = object.isRiding();
        }
        RenderHelper.enableGUIStandardItemLighting();
        if (a3.r() & a4.h()) {
            object = rd.v.getTextureForSkin(a3, a4.r(), a4.r());
            ((ti)((Object)object)).bindTexture();
            GL11.glPushMatrix();
            GL11.glPushAttrib((int)8192);
            GL11.glDisable((int)2884);
            GL11.glEnable((int)3008);
            if (!a4.r()) {
                GL11.glTranslated((double)0.0, (double)(-12.0f * v), (double)0.0);
            }
            if (!a4.z()) {
                eh eh3 = a5;
                eh3.bipedHead.render(v);
                eh3.bipedBody.render(v);
                eh3.bipedLeftArm.render(v);
                eh3.bipedLeftLeg.render(v);
                eh3.bipedRightLeg.render(v);
            }
            a5.bipedRightArm.render(v);
            GL11.glPopAttrib();
            GL11.glPopMatrix();
        }
        boolean bl = in.b.r(a3.r());
        double d2 = 45.0;
        int n3 = n2 = 0;
        while (n3 < arrayList.size()) {
            kf kf2;
            kf kf3;
            kf kf4;
            kf kf5 = arrayList.get(n2);
            GL11.glPushMatrix();
            if (a5.isChild) {
                float f2 = 2.0f;
                GL11.glScalef((float)(1.0f / f2), (float)(1.0f / f2), (float)(1.0f / f2));
                GL11.glTranslatef((float)0.0f, (float)(24.0f * v), (float)0.0f);
            }
            if (kf5.r().y().equals("armourers:head.base") && !a4.z()) {
                a5.y(new mk(kf5, a4));
            }
            if (kf5.r().y().equals("armourers:chest.base") && !a4.z()) {
                kf4 = kf5;
                a5.x(new mk(kf5, a4));
            } else if (kf5.r().y().equals("armourers:chest.leftArm") && !a4.z()) {
                kf4 = kf5;
                a5.r(new mk(kf5, a4), bl);
            } else {
                if (kf5.r().y().equals("armourers:chest.rightArm")) {
                    a5.r(new mk(kf5, a4), bl, a4.z());
                }
                kf4 = kf5;
            }
            if (kf4.r().y().equals("armourers:legs.leftLeg") && !a4.z()) {
                kf3 = kf5;
                a5.r(new mk(kf5, a4));
            } else if (kf5.r().y().equals("armourers:legs.rightLeg") && !a4.z()) {
                kf3 = kf5;
                a5.w(new mk(kf5, a4));
            } else {
                if (kf5.r().y().equals("armourers:legs.skirt") && !a4.z()) {
                    a5.h(new mk(kf5, a4));
                }
                kf3 = kf5;
            }
            if (kf3.r().y().equals("armourers:feet.leftFoot") && !a4.z()) {
                kf2 = kf5;
                a5.z(new mk(kf5, a4));
            } else {
                if (kf5.r().y().equals("armourers:feet.rightFoot") && !a4.z()) {
                    a5.s(new mk(kf5, a4));
                }
                kf2 = kf5;
            }
            if (kf2.r().y().equals("armourers:wings.leftWing") && !a4.z()) {
                d2 = bo.r(a2, a3, n2);
                a5.r(new mk(kf5, a4), d2);
            }
            if (kf5.r().y().equals("armourers:wings.rightWing") && !a4.z()) {
                d2 = bo.r(a2, a3, n2);
                a5.y(new mk(kf5, a4), -d2);
            }
            GL11.glPopMatrix();
            n3 = ++n2;
        }
        GL11.glColor3f((float)1.0f, (float)1.0f, (float)1.0f);
    }

    private /* synthetic */ void y(mk a2) {
        eh a3;
        GL11.glPushMatrix();
        if (a3.isSneak) {
            GlStateManager.translate((float)0.0f, (float)0.2f, (float)0.0f);
            GlStateManager.translate((float)0.0f, (float)(1.0f * a2.r()), (float)0.0f);
        }
        GL11.glColor3f((float)1.0f, (float)1.0f, (float)1.0f);
        eh eh2 = a3;
        GL11.glRotated((double)Math.toDegrees(a3.bipedHead.rotateAngleZ), (double)0.0, (double)0.0, (double)1.0);
        GL11.glRotated((double)Math.toDegrees(eh2.bipedHead.rotateAngleY), (double)0.0, (double)1.0, (double)0.0);
        GL11.glRotated((double)Math.toDegrees(eh2.bipedHead.rotateAngleX), (double)1.0, (double)0.0, (double)0.0);
        eh2.renderPart(a2);
        GL11.glPopMatrix();
    }

    @Override
    public void render(Entity a2, yl a3, boolean a4, n a5, oh a6, boolean a7, double a8, boolean a9) {
        eh a10;
        a10.render(a2, a3, new dn(v, a5, a6, a8, a9, a4, a7, null));
    }

    private /* synthetic */ void r(mk a2) {
        eh a3;
        GL11.glPushMatrix();
        GL11.glColor3f((float)1.0f, (float)1.0f, (float)1.0f);
        if (a3.isSneak) {
            GlStateManager.translate((float)0.0f, (float)0.2f, (float)0.0f);
            GlStateManager.translate((float)0.0f, (float)(-3.0f * a2.r()), (float)(4.0f * a2.r()));
        }
        GL11.glTranslated((double)0.0, (double)(12.0f * a2.r()), (double)0.0);
        GL11.glTranslated((double)(2.0f * a2.r()), (double)0.0, (double)0.0);
        eh eh2 = a3;
        GL11.glRotatef((float)((float)Math.toDegrees(a3.bipedLeftLeg.rotateAngleZ)), (float)0.0f, (float)0.0f, (float)1.0f);
        GL11.glRotatef((float)((float)Math.toDegrees(eh2.bipedLeftLeg.rotateAngleY)), (float)0.0f, (float)1.0f, (float)0.0f);
        GL11.glRotatef((float)((float)Math.toDegrees(eh2.bipedLeftLeg.rotateAngleX)), (float)1.0f, (float)0.0f, (float)0.0f);
        eh2.renderPart(a2);
        GL11.glPopMatrix();
    }
}

