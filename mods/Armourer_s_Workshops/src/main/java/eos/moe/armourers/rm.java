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
public class rm
extends pj {
    public rm() {
        rm a2;
    }

    private /* synthetic */ void z(mk a2) {
        rm a3;
        GL11.glPushMatrix();
        GL11.glColor3f((float)1.0f, (float)1.0f, (float)1.0f);
        GL11.glTranslated((double)0.0, (double)(12.0f * a2.r()), (double)0.0);
        GL11.glTranslated((double)(-2.0f * a2.r()), (double)0.0, (double)0.0);
        rm rm2 = a3;
        GL11.glRotatef((float)((float)Math.toDegrees(a3.field_178721_j.field_78808_h)), (float)0.0f, (float)0.0f, (float)1.0f);
        GL11.glRotatef((float)((float)Math.toDegrees(rm2.field_178721_j.field_78796_g)), (float)0.0f, (float)1.0f, (float)0.0f);
        GL11.glRotatef((float)((float)Math.toDegrees(rm2.field_178721_j.field_78795_f)), (float)1.0f, (float)0.0f, (float)0.0f);
        rm2.renderPart(a2);
        GL11.glPopMatrix();
    }

    private /* synthetic */ void y(mk a2) {
        rm a3;
        GL11.glPushMatrix();
        GL11.glColor3f((float)1.0f, (float)1.0f, (float)1.0f);
        GL11.glTranslated((double)0.0, (double)(12.0f * a2.r()), (double)0.0);
        if (a3.field_78093_q) {
            GL11.glRotated((double)-70.0, (double)1.0, (double)0.0, (double)0.0);
        }
        a3.renderPart(a2);
        GL11.glPopMatrix();
    }

    private /* synthetic */ void r(mk a2) {
        rm a3;
        GL11.glPushMatrix();
        GL11.glColor3f((float)1.0f, (float)1.0f, (float)1.0f);
        GL11.glTranslated((double)0.0, (double)(12.0f * a2.r()), (double)0.0);
        GL11.glTranslated((double)(2.0f * a2.r()), (double)0.0, (double)0.0);
        rm rm2 = a3;
        GL11.glRotatef((float)((float)Math.toDegrees(a3.field_178722_k.field_78808_h)), (float)0.0f, (float)0.0f, (float)1.0f);
        GL11.glRotatef((float)((float)Math.toDegrees(rm2.field_178722_k.field_78796_g)), (float)0.0f, (float)1.0f, (float)0.0f);
        GL11.glRotatef((float)((float)Math.toDegrees(rm2.field_178722_k.field_78795_f)), (float)1.0f, (float)0.0f, (float)0.0f);
        rm2.renderPart(a2);
        GL11.glPopMatrix();
    }

    @Override
    public void render(Entity a2, yl a32, dn a4) {
        int n2;
        rm a5;
        Object object;
        if (a32 == null) {
            return;
        }
        ArrayList<kf> arrayList = a32.y();
        if (a2 instanceof EntityPlayer) {
            object = (EntityPlayer)a2;
            rm rm2 = a5;
            rm2.field_78117_n = object.func_70093_af();
            rm2.field_78093_q = object.func_184218_aH();
        }
        RenderHelper.func_74520_c();
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
            rm rm3 = a5;
            rm3.field_178722_k.func_78785_a(v);
            rm3.field_178721_j.func_78785_a(v);
            GL11.glPopAttrib();
            GL11.glPopMatrix();
        }
        int n3 = n2 = 0;
        while (n3 < arrayList.size()) {
            a2 = arrayList.get(n2);
            GL11.glPushMatrix();
            if (a5.field_78091_s) {
                float a32 = 2.0f;
                GL11.glScalef((float)(1.0f / a32), (float)(1.0f / a32), (float)(1.0f / a32));
                GL11.glTranslatef((float)0.0f, (float)(24.0f * v), (float)0.0f);
            }
            if (a5.field_78117_n) {
                GlStateManager.func_179109_b((float)0.0f, (float)0.2f, (float)0.0f);
                GL11.glTranslated((double)0.0, (double)(-3.0f * v), (double)(4.0f * v));
            }
            if (((kf)a2).r().r().equals("leftLeg")) {
                a5.r(new mk((kf)a2, a4));
            } else if (((kf)a2).r().r().equals("rightLeg")) {
                a5.z(new mk((kf)a2, a4));
            } else if (((kf)a2).r().r().equals("skirt")) {
                a5.y(new mk((kf)a2, a4));
            }
            GL11.glPopMatrix();
            n3 = ++n2;
        }
        GL11.glColor3f((float)1.0f, (float)1.0f, (float)1.0f);
    }

    @Override
    public void render(Entity a2, yl a3, boolean a4, n a5, oh a6, boolean a7, double a8, boolean a9) {
        rm a10;
        a10.render(a2, a3, new dn(v, a5, a6, a8, a9, a4, a7, null));
    }
}

