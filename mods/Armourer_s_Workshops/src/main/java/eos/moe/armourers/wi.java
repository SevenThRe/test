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
import eos.moe.armourers.mk;
import eos.moe.armourers.n;
import eos.moe.armourers.oh;
import eos.moe.armourers.pj;
import eos.moe.armourers.rd;
import eos.moe.armourers.ti;
import eos.moe.armourers.yl;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.lwjgl.opengl.GL11;

@SideOnly(value=Side.CLIENT)
public class wi
extends pj {
    @Override
    public void render(Entity a2, yl a3, boolean a4, n a5, oh a6, boolean a7, double a8, boolean a9) {
        wi a10;
        a10.render(a2, a3, new dn(v, a5, a6, a8, a9, a4, a7, null));
    }

    private /* synthetic */ void r(mk a2) {
        wi a3;
        GL11.glPushMatrix();
        GL11.glColor3f((float)1.0f, (float)1.0f, (float)1.0f);
        a3.renderPart(a2);
        GL11.glPopMatrix();
    }

    @Override
    public void render(Entity a2, yl a3, dn a4) {
        wi a5;
        Object object;
        if (a3 == null) {
            return;
        }
        if (a2 instanceof EntityPlayer) {
            object = (EntityPlayer)a2;
            wi wi2 = a5;
            wi2.isSneak = object.isSneaking();
            wi2.isRiding = object.isRiding();
        }
        RenderHelper.enableGUIStandardItemLighting();
        if (a3.r() & a4.h()) {
            object = rd.v.getTextureForSkin(a3, a4.r(), a4.r());
            ((ti)((Object)object)).bindTexture();
            GL11.glPushAttrib((int)8192);
            GL11.glDisable((int)2884);
            GL11.glEnable((int)3008);
            a5.bipedHead.render(v);
            GL11.glPopAttrib();
        }
        if (a3.y().size() > 0) {
            GL11.glPushMatrix();
            if (a5.isChild) {
                float f2 = 2.0f;
                GL11.glScalef((float)(1.5f / f2), (float)(1.5f / f2), (float)(1.5f / f2));
                GL11.glTranslatef((float)0.0f, (float)(16.0f * v), (float)0.0f);
            }
            if (a5.isSneak) {
                GlStateManager.translate((float)0.0f, (float)0.2f, (float)0.0f);
                GlStateManager.translate((float)0.0f, (float)(1.0f * v), (float)0.0f);
            }
            GL11.glColor3f((float)1.0f, (float)1.0f, (float)1.0f);
            wi wi3 = a5;
            GL11.glRotated((double)Math.toDegrees(wi3.bipedHead.rotateAngleZ), (double)0.0, (double)0.0, (double)1.0);
            GL11.glRotated((double)Math.toDegrees(wi3.bipedHead.rotateAngleY), (double)0.0, (double)1.0, (double)0.0);
            GL11.glRotated((double)Math.toDegrees(wi3.bipedHead.rotateAngleX), (double)1.0, (double)0.0, (double)0.0);
            wi wi4 = a5;
            wi3.r(new mk(a3.y().get(0), a4));
            GL11.glPopMatrix();
        }
        GL11.glColor3f((float)1.0f, (float)1.0f, (float)1.0f);
    }

    public wi() {
        wi a2;
    }
}

