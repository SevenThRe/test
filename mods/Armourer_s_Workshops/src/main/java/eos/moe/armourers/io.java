/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.renderer.GlStateManager
 *  net.minecraft.client.renderer.RenderHelper
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraftforge.fml.relauncher.Side
 *  net.minecraftforge.fml.relauncher.SideOnly
 *  org.lwjgl.opengl.GL11
 */
package eos.moe.armourers;

import eos.moe.armourers.dn;
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
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.lwjgl.opengl.GL11;

@SideOnly(value=Side.CLIENT)
public class io
extends pj {
    @Override
    public void render(Entity a2, yl a3, boolean a4, n a5, oh a6, boolean a7, double a8, boolean a9) {
        io a10;
        a10.render(a2, a3, new dn(v, a5, a6, a8, a9, a4, a7, null));
    }

    public io() {
        io a2;
    }

    @Override
    public void render(Entity a2, yl a3, dn a4) {
        io a5;
        Object object;
        if (a3 == null) {
            return;
        }
        ArrayList<kf> arrayList = ((yl)a3).y();
        if (a2 instanceof EntityPlayer) {
            object = (EntityPlayer)a2;
            io io2 = a5;
            io2.isSneak = object.isSneaking();
            io2.isRiding = object.isRiding();
        }
        RenderHelper.enableGUIStandardItemLighting();
        if (((yl)a3).r() & a4.h()) {
            object = rd.v.getTextureForSkin((yl)a3, a4.r(), a4.r());
            ((ti)((Object)object)).bindTexture();
            GL11.glPushAttrib((int)8192);
            GL11.glDisable((int)2884);
            GL11.glEnable((int)3008);
            if (!a4.z()) {
                io io3 = a5;
                io3.bipedBody.render(v);
                io3.bipedLeftArm.render(v);
            }
            a5.bipedRightArm.render(v);
            GL11.glPopAttrib();
        }
        boolean bl = in.b.r(((yl)a3).r());
        int n2 = a2 = 0;
        while (n2 < arrayList.size()) {
            a3 = arrayList.get(a2);
            GL11.glPushMatrix();
            if (a5.isChild) {
                float f2 = 2.0f;
                GL11.glScalef((float)(1.0f / f2), (float)(1.0f / f2), (float)(1.0f / f2));
                GL11.glTranslatef((float)0.0f, (float)(24.0f * v), (float)0.0f);
            }
            if (a5.isSneak && !a4.z()) {
                GlStateManager.translate((float)0.0f, (float)0.2f, (float)0.0f);
            }
            if (((kf)a3).r().r().equals("base") && !a4.z()) {
                a5.r(new mk((kf)a3, a4));
            } else if (((kf)a3).r().r().equals("leftArm") && !a4.z()) {
                a5.y(new mk((kf)a3, a4), bl);
            } else if (((kf)a3).r().r().equals("rightArm")) {
                a5.r(new mk((kf)a3, a4), bl);
            }
            GL11.glPopMatrix();
            n2 = ++a2;
        }
        GL11.glColor3f((float)1.0f, (float)1.0f, (float)1.0f);
    }

    private /* synthetic */ void y(mk a2, boolean a3) {
        io a4;
        GL11.glPushMatrix();
        GL11.glTranslatef((float)(5.0f * a2.r()), (float)0.0f, (float)0.0f);
        GL11.glTranslatef((float)0.0f, (float)(2.0f * a2.r()), (float)0.0f);
        io io2 = a4;
        GL11.glRotatef((float)((float)Math.toDegrees(io2.bipedLeftArm.rotateAngleZ)), (float)0.0f, (float)0.0f, (float)1.0f);
        GL11.glRotatef((float)((float)Math.toDegrees(io2.bipedLeftArm.rotateAngleY)), (float)0.0f, (float)1.0f, (float)0.0f);
        GL11.glRotatef((float)((float)Math.toDegrees(io2.bipedLeftArm.rotateAngleX)), (float)1.0f, (float)0.0f, (float)0.0f);
        if (io2.s & !a3) {
            GL11.glTranslatef((float)(-0.25f * a2.r()), (float)0.0f, (float)0.0f);
            GL11.glTranslatef((float)0.0f, (float)(0.5f * a2.r()), (float)0.0f);
            GL11.glScalef((float)0.75f, (float)1.0f, (float)1.0f);
        }
        a4.renderPart(a2);
        GL11.glPopMatrix();
    }

    private /* synthetic */ void r(mk a2, boolean a3) {
        io a4;
        GL11.glPushMatrix();
        GL11.glTranslatef((float)(-5.0f * a2.r()), (float)0.0f, (float)0.0f);
        GL11.glTranslatef((float)0.0f, (float)(2.0f * a2.r()), (float)0.0f);
        io io2 = a4;
        GL11.glRotatef((float)((float)Math.toDegrees(io2.bipedRightArm.rotateAngleZ)), (float)0.0f, (float)0.0f, (float)1.0f);
        GL11.glRotatef((float)((float)Math.toDegrees(io2.bipedRightArm.rotateAngleY)), (float)0.0f, (float)1.0f, (float)0.0f);
        GL11.glRotatef((float)((float)Math.toDegrees(io2.bipedRightArm.rotateAngleX)), (float)1.0f, (float)0.0f, (float)0.0f);
        if (io2.s & !a3) {
            GL11.glTranslatef((float)(0.25f * a2.r()), (float)0.0f, (float)0.0f);
            GL11.glTranslatef((float)0.0f, (float)(0.5f * a2.r()), (float)0.0f);
            GL11.glScalef((float)0.75f, (float)1.0f, (float)1.0f);
        }
        a4.renderPart(a2);
        GL11.glPopMatrix();
    }

    private /* synthetic */ void r(mk a2) {
        io a3;
        GL11.glPushMatrix();
        if (a3.isSneak) {
            GL11.glRotated((double)Math.toDegrees(a3.bipedBody.rotateAngleX), (double)1.0, (double)0.0, (double)0.0);
        }
        GL11.glColor3f((float)1.0f, (float)1.0f, (float)1.0f);
        a3.renderPart(a2);
        GL11.glPopMatrix();
    }
}

