/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.renderer.GlStateManager
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.util.EnumFacing
 *  org.lwjgl.opengl.GL11
 */
package eos.moe.armourers;

import eos.moe.armourers.bo;
import eos.moe.armourers.dn;
import eos.moe.armourers.if;
import eos.moe.armourers.kf;
import eos.moe.armourers.mk;
import eos.moe.armourers.n;
import eos.moe.armourers.oh;
import eos.moe.armourers.pj;
import eos.moe.armourers.yl;
import java.util.ArrayList;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.EnumFacing;
import org.lwjgl.opengl.GL11;

public class fj
extends pj {
    /*
     * Enabled aggressive block sorting
     */
    private /* synthetic */ void y(mk a2, double a3) {
        fj a4;
        GL11.glPushMatrix();
        if if_ = new if(0, 0, 0);
        EnumFacing enumFacing = EnumFacing.DOWN;
        if (a2.r().r() > 0) {
            mk mk2 = a2;
            if_ = mk2.r().r(0);
            enumFacing = mk2.r().r(0);
        }
        fj fj2 = a4;
        GL11.glRotatef((float)((float)Math.toDegrees(fj2.bipedBody.rotateAngleZ)), (float)0.0f, (float)0.0f, (float)1.0f);
        GL11.glRotatef((float)((float)Math.toDegrees(fj2.bipedBody.rotateAngleY)), (float)0.0f, (float)1.0f, (float)0.0f);
        GL11.glTranslated((double)(v * 0.5f), (double)(v * 0.5f), (double)(v * 0.5f));
        GL11.glTranslated((double)(v * (float)if_.z()), (double)(v * (float)if_.r()), (double)(v * (float)if_.y()));
        switch (enumFacing) {
            case UP: {
                GL11.glRotated((double)a3, (double)0.0, (double)1.0, (double)0.0);
                break;
            }
            case DOWN: {
                GL11.glRotated((double)a3, (double)0.0, (double)-1.0, (double)0.0);
                break;
            }
            case SOUTH: {
                GL11.glRotated((double)a3, (double)0.0, (double)0.0, (double)-1.0);
                break;
            }
            case NORTH: {
                GL11.glRotated((double)a3, (double)0.0, (double)0.0, (double)1.0);
                break;
            }
            case EAST: {
                GL11.glRotated((double)a3, (double)1.0, (double)0.0, (double)0.0);
                break;
            }
            case WEST: {
                GL11.glRotated((double)a3, (double)-1.0, (double)0.0, (double)0.0);
                break;
            }
        }
        GL11.glTranslated((double)(v * (float)(-if_.z())), (double)(v * (float)(-if_.r())), (double)(v * (float)(-if_.y())));
        GL11.glTranslated((double)(v * -0.5f), (double)(v * -0.5f), (double)(v * -0.5f));
        a4.renderPart(a2);
        GL11.glPopMatrix();
    }

    @Override
    public void render(Entity a2, yl a3, dn a4) {
        int n2;
        fj a5;
        if (a3 == null) {
            return;
        }
        ArrayList<kf> arrayList = a3.y();
        if (a2 instanceof EntityPlayer) {
            EntityPlayer entityPlayer = (EntityPlayer)a2;
            fj fj2 = a5;
            fj2.isSneak = entityPlayer.isSneaking();
            fj2.isRiding = entityPlayer.isRiding();
        }
        int n3 = n2 = 0;
        while (n3 < arrayList.size()) {
            kf kf2 = arrayList.get(n2);
            GL11.glPushMatrix();
            if (a5.isSneak) {
                GlStateManager.translate((float)0.0f, (float)0.2f, (float)0.0f);
                GlStateManager.rotate((float)((float)Math.toDegrees(a5.bipedBody.rotateAngleX)), (float)1.0f, (float)0.0f, (float)0.0f);
            }
            GL11.glTranslated((double)0.0, (double)0.0, (double)(v * 2.0f));
            if (a5.isChild) {
                float f2 = 2.0f;
                GL11.glScalef((float)(1.0f / f2), (float)(1.0f / f2), (float)(1.0f / f2));
                GL11.glTranslatef((float)0.0f, (float)(24.0f * v), (float)0.0f);
            }
            double d2 = 45.0;
            d2 = bo.r(a2, a3, n2);
            if (kf2.r().r().equals("leftWing")) {
                a5.y(new mk(kf2, a4), d2);
            }
            if (kf2.r().r().equals("rightWing")) {
                a5.r(new mk(kf2, a4), -d2);
            }
            GL11.glPopMatrix();
            n3 = ++n2;
        }
        GL11.glColor3f((float)1.0f, (float)1.0f, (float)1.0f);
    }

    @Override
    public void render(Entity a2, yl a3, boolean a4, n a5, oh a6, boolean a7, double a8, boolean a9) {
        fj a10;
        a10.render(a2, a3, new dn(v, a5, a6, a8, a9, a4, a7, null));
    }

    public fj() {
        fj a2;
    }

    /*
     * Enabled aggressive block sorting
     */
    private /* synthetic */ void r(mk a2, double a3) {
        fj a4;
        GL11.glPushMatrix();
        if if_ = new if(0, 0, 0);
        EnumFacing enumFacing = EnumFacing.DOWN;
        if (a2.r().r() > 0) {
            mk mk2 = a2;
            if_ = mk2.r().r(0);
            enumFacing = mk2.r().r(0);
        }
        fj fj2 = a4;
        GL11.glRotatef((float)((float)Math.toDegrees(fj2.bipedBody.rotateAngleZ)), (float)0.0f, (float)0.0f, (float)1.0f);
        GL11.glRotatef((float)((float)Math.toDegrees(fj2.bipedBody.rotateAngleY)), (float)0.0f, (float)1.0f, (float)0.0f);
        GL11.glTranslated((double)(v * 0.5f), (double)(v * 0.5f), (double)(v * 0.5f));
        GL11.glTranslated((double)(v * (float)if_.z()), (double)(v * (float)if_.r()), (double)(v * (float)if_.y()));
        switch (enumFacing) {
            case UP: {
                GL11.glRotated((double)a3, (double)0.0, (double)1.0, (double)0.0);
                break;
            }
            case DOWN: {
                GL11.glRotated((double)a3, (double)0.0, (double)-1.0, (double)0.0);
                break;
            }
            case SOUTH: {
                GL11.glRotated((double)a3, (double)0.0, (double)0.0, (double)-1.0);
                break;
            }
            case NORTH: {
                GL11.glRotated((double)a3, (double)0.0, (double)0.0, (double)1.0);
                break;
            }
            case EAST: {
                GL11.glRotated((double)a3, (double)1.0, (double)0.0, (double)0.0);
                break;
            }
            case WEST: {
                GL11.glRotated((double)a3, (double)-1.0, (double)0.0, (double)0.0);
                break;
            }
        }
        GL11.glTranslated((double)(v * (float)(-if_.z())), (double)(v * (float)(-if_.r())), (double)(v * (float)(-if_.y())));
        GL11.glTranslated((double)(v * -0.5f), (double)(v * -0.5f), (double)(v * -0.5f));
        a4.renderPart(a2);
        GL11.glPopMatrix();
    }
}

