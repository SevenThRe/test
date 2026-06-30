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
public class pm
extends pj {
    @Override
    public void render(Entity a2, yl a3, dn a4) {
        int n2;
        pm a5;
        if (a3 == null) {
            return;
        }
        a3 = ((yl)a3).y();
        if (a2 instanceof EntityPlayer) {
            EntityPlayer entityPlayer = (EntityPlayer)a2;
            pm pm2 = a5;
            pm2.field_78117_n = entityPlayer.func_70093_af();
            pm2.field_78093_q = entityPlayer.func_184218_aH();
        }
        int n3 = n2 = 0;
        while (n3 < ((ArrayList)a3).size()) {
            a2 = (kf)((ArrayList)a3).get(n2);
            GL11.glPushMatrix();
            if (a5.field_78091_s) {
                float f2 = 2.0f;
                GL11.glScalef((float)(1.0f / f2), (float)(1.0f / f2), (float)(1.0f / f2));
                GL11.glTranslatef((float)0.0f, (float)(24.0f * v), (float)0.0f);
            }
            if (((kf)a2).r().r().equals("base")) {
                a5.r(new mk((kf)a2, a4));
            }
            GL11.glPopMatrix();
            n3 = ++n2;
        }
        GL11.glColor3f((float)1.0f, (float)1.0f, (float)1.0f);
    }

    public pm() {
        pm a2;
    }

    @Override
    public void render(Entity a2, yl a3, boolean a4, n a5, oh a6, boolean a7, double a8, boolean a9) {
        pm a10;
        a10.render(a2, a3, new dn(v, a5, a6, a8, a9, a4, a7, null));
    }

    private /* synthetic */ void r(mk a2) {
        pm a3;
        GL11.glPushMatrix();
        pm pm2 = a3;
        pm pm3 = a3;
        GL11.glRotatef((float)((float)Math.toDegrees(pm3.field_78115_e.field_78808_h)), (float)0.0f, (float)0.0f, (float)1.0f);
        GL11.glRotatef((float)((float)Math.toDegrees(pm3.field_78115_e.field_78796_g)), (float)0.0f, (float)1.0f, (float)0.0f);
        GL11.glRotatef((float)((float)Math.toDegrees(pm3.field_178723_h.field_78808_h)), (float)0.0f, (float)0.0f, (float)1.0f);
        GL11.glRotatef((float)((float)Math.toDegrees(pm2.field_178723_h.field_78796_g)), (float)0.0f, (float)1.0f, (float)0.0f);
        GL11.glRotatef((float)((float)Math.toDegrees(pm2.field_178723_h.field_78795_f)), (float)1.0f, (float)0.0f, (float)0.0f);
        pm2.renderPart(a2);
        GL11.glPopMatrix();
    }
}

