/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraftforge.fml.relauncher.Side
 *  net.minecraftforge.fml.relauncher.SideOnly
 *  org.lwjgl.opengl.GL11
 */
package eos.moe.armourers;

import eos.moe.armourers.bm;
import eos.moe.armourers.dn;
import eos.moe.armourers.kf;
import eos.moe.armourers.mk;
import eos.moe.armourers.n;
import eos.moe.armourers.oh;
import eos.moe.armourers.pj;
import eos.moe.armourers.yl;
import java.util.ArrayList;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.lwjgl.opengl.GL11;

@SideOnly(value=Side.CLIENT)
public class ol
extends pj {
    public int j = 0;

    @Override
    public void render(Entity a2, yl a3, boolean a4, n a5, oh a6, boolean a7, double a8, boolean a9) {
        ol a10;
        a10.render(a2, a3, new dn(v, a5, a6, a8, a9, a4, a7, null));
    }

    @Override
    public void render(Entity a22, yl a3, dn a4) {
        ol ol2;
        boolean bl;
        ol a5;
        Object object;
        if (a3 == null) {
            return;
        }
        a3 = ((yl)a3).y();
        if (a22 instanceof EntityPlayer) {
            object = (EntityPlayer)a22;
            ol ol3 = a5;
            EntityPlayer entityPlayer = object;
            a5.field_78117_n = entityPlayer.func_70093_af();
            ol3.field_78093_q = entityPlayer.func_184218_aH();
            ol3.field_78091_s = object.func_70631_g_();
        }
        if (a5.j > ((ArrayList)a3).size() - 1) {
            a5.j = ((ArrayList)a3).size() - 1;
        }
        if (a5.j < 0) {
            bl = true;
            ol2 = a5;
        } else {
            bl = false;
            ol2 = a5;
        }
        if (bl | ol2.j > ((ArrayList)a3).size() - 1) {
            bm.r("wow");
            return;
        }
        object = (kf)((ArrayList)a3).get(a5.j);
        GL11.glPushMatrix();
        if (a5.field_78091_s) {
            float a22 = 2.0f;
            GL11.glScalef((float)(1.0f / a22), (float)(1.0f / a22), (float)(1.0f / a22));
            GL11.glTranslatef((float)0.0f, (float)(24.0f * v), (float)0.0f);
        }
        a5.r(new mk((kf)object, a4));
        GL11.glPopMatrix();
        GL11.glColor3f((float)1.0f, (float)1.0f, (float)1.0f);
        a5.j = 0;
    }

    public ol() {
        ol a2;
    }

    private /* synthetic */ void r(mk a2) {
        ol a3;
        GL11.glPushMatrix();
        ol ol2 = a3;
        ol ol3 = a3;
        GL11.glRotatef((float)((float)Math.toDegrees(ol3.field_78115_e.field_78808_h)), (float)0.0f, (float)0.0f, (float)1.0f);
        GL11.glRotatef((float)((float)Math.toDegrees(ol3.field_78115_e.field_78796_g)), (float)0.0f, (float)1.0f, (float)0.0f);
        GL11.glRotatef((float)((float)Math.toDegrees(ol3.field_178723_h.field_78808_h)), (float)0.0f, (float)0.0f, (float)1.0f);
        GL11.glRotatef((float)((float)Math.toDegrees(ol2.field_178723_h.field_78796_g)), (float)0.0f, (float)1.0f, (float)0.0f);
        GL11.glRotatef((float)((float)Math.toDegrees(ol2.field_178723_h.field_78795_f)), (float)1.0f, (float)0.0f, (float)0.0f);
        ol2.renderPart(a2);
        GL11.glPopMatrix();
    }
}

