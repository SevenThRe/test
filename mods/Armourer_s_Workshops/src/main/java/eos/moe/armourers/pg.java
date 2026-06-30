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
public class pg
extends pj {
    @Override
    public void render(Entity a2, yl a3, boolean a4, n a5, oh a6, boolean a7, double a8, boolean a9) {
        pg a10;
        a10.render(a2, a3, new dn(v, a5, a6, a8, a9, a4, a7, null));
    }

    @Override
    public void render(Entity a2, yl a32, dn a4) {
        int n2;
        pg a5;
        Object object;
        if (a32 == null) {
            return;
        }
        ArrayList<kf> arrayList = a32.y();
        if (a2 instanceof EntityPlayer) {
            object = (EntityPlayer)a2;
            pg pg2 = a5;
            pg2.isSneak = object.isSneaking();
            pg2.isRiding = object.isRiding();
        }
        RenderHelper.enableGUIStandardItemLighting();
        if (a32.r() & a4.h()) {
            object = rd.v.getTextureForSkin(a32, a4.r(), a4.r());
            ((ti)((Object)object)).bindTexture();
            GL11.glPushMatrix();
            GL11.glPushAttrib((int)8192);
            GL11.glDisable((int)2884);
            GL11.glEnable((int)3008);
            if (!a4.r()) {
                GL11.glTranslated((double)0.0, (double)(-12.0f * v), (double)0.0);
            }
            pg pg3 = a5;
            pg3.bipedLeftLeg.render(v);
            pg3.bipedRightLeg.render(v);
            GL11.glPopAttrib();
            GL11.glPopMatrix();
        }
        int n3 = n2 = 0;
        while (n3 < arrayList.size()) {
            a2 = arrayList.get(n2);
            GL11.glPushMatrix();
            if (a5.isChild) {
                float a32 = 2.0f;
                GL11.glScalef((float)(1.0f / a32), (float)(1.0f / a32), (float)(1.0f / a32));
                GL11.glTranslatef((float)0.0f, (float)(24.0f * v), (float)0.0f);
            }
            if (a5.isSneak) {
                GlStateManager.translate((float)0.0f, (float)0.2f, (float)0.0f);
                GL11.glTranslated((double)0.0, (double)(-3.0f * v), (double)(4.0f * v));
            }
            if (((kf)a2).r().r().equals("leftFoot")) {
                a5.y(new mk((kf)a2, a4));
            } else if (((kf)a2).r().r().equals("rightFoot")) {
                a5.r(new mk((kf)a2, a4));
            }
            GL11.glPopMatrix();
            n3 = ++n2;
        }
        GL11.glColor3f((float)1.0f, (float)1.0f, (float)1.0f);
    }

    private /* synthetic */ void y(mk a2) {
        pg a3;
        GL11.glPushMatrix();
        GL11.glColor3f((float)1.0f, (float)1.0f, (float)1.0f);
        GL11.glTranslated((double)0.0, (double)(12.0f * a2.r()), (double)0.0);
        GL11.glTranslated((double)(2.0f * a2.r()), (double)0.0, (double)0.0);
        pg pg2 = a3;
        GL11.glRotatef((float)((float)Math.toDegrees(a3.bipedLeftLeg.rotateAngleZ)), (float)0.0f, (float)0.0f, (float)1.0f);
        GL11.glRotatef((float)((float)Math.toDegrees(pg2.bipedLeftLeg.rotateAngleY)), (float)0.0f, (float)1.0f, (float)0.0f);
        GL11.glRotatef((float)((float)Math.toDegrees(pg2.bipedLeftLeg.rotateAngleX)), (float)1.0f, (float)0.0f, (float)0.0f);
        pg2.renderPart(a2);
        GL11.glPopMatrix();
    }

    private /* synthetic */ void r(mk a2) {
        pg a3;
        GL11.glPushMatrix();
        GL11.glColor3f((float)1.0f, (float)1.0f, (float)1.0f);
        GL11.glTranslated((double)0.0, (double)(12.0f * a2.r()), (double)0.0);
        GL11.glTranslated((double)(-2.0f * a2.r()), (double)0.0, (double)0.0);
        pg pg2 = a3;
        GL11.glRotatef((float)((float)Math.toDegrees(a3.bipedRightLeg.rotateAngleZ)), (float)0.0f, (float)0.0f, (float)1.0f);
        GL11.glRotatef((float)((float)Math.toDegrees(pg2.bipedRightLeg.rotateAngleY)), (float)0.0f, (float)1.0f, (float)0.0f);
        GL11.glRotatef((float)((float)Math.toDegrees(pg2.bipedRightLeg.rotateAngleX)), (float)1.0f, (float)0.0f, (float)0.0f);
        pg2.renderPart(a2);
        GL11.glPopMatrix();
    }

    public pg() {
        pg a2;
    }
}

