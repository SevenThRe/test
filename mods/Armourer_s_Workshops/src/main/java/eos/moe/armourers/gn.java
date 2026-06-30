/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.renderer.GlStateManager
 *  net.minecraft.client.renderer.entity.RenderLivingBase
 *  net.minecraft.client.renderer.entity.layers.LayerRenderer
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraftforge.fml.relauncher.Side
 *  net.minecraftforge.fml.relauncher.SideOnly
 *  org.lwjgl.opengl.GL11
 */
package eos.moe.armourers;

import eos.moe.armourers.fk;
import eos.moe.armourers.kd;
import eos.moe.armourers.mk;
import eos.moe.armourers.mn;
import eos.moe.armourers.oh;
import eos.moe.armourers.r;
import eos.moe.armourers.rk;
import eos.moe.armourers.vk;
import eos.moe.armourers.vn;
import eos.moe.armourers.yl;
import eos.moe.armourers.zg;
import java.util.Iterator;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderLivingBase;
import net.minecraft.client.renderer.entity.layers.LayerRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.lwjgl.opengl.GL11;

@SideOnly(value=Side.CLIENT)
public abstract class gn<E extends EntityLivingBase, R extends RenderLivingBase>
implements LayerRenderer<E> {
    public r[] s;
    public static float m = 0.0625f;
    public R j;

    public gn(R a2) {
        gn a3;
        gn gn2 = a3;
        gn2.j = a2;
        gn2.s = new r[12];
        gn gn3 = a3;
        gn3.s[0] = vn.l;
        gn3.s[1] = vn.a;
        gn3.s[2] = vn.e;
        gn3.s[3] = vn.w;
        gn3.s[4] = vn.g;
        gn3.s[5] = vn.z;
        gn3.s[6] = vn.q;
        gn3.s[7] = vn.c;
        gn3.s[8] = vn.s;
        gn3.s[9] = vn.p;
        gn3.s[10] = vn.c;
        gn3.s[11] = vn.b;
    }

    public void doRenderLayer(E a2, float a3, float a4, float a5, float a6, float a7, float a8, float a9) {
        gn a10;
        int n2;
        if (Minecraft.getMinecraft().player.getDistance(((EntityLivingBase)a2).posX, ((EntityLivingBase)a2).posY, ((EntityLivingBase)a2).posZ) > (double)vk.n) {
            return;
        }
        int n3 = n2 = 0;
        while (n3 < a10.s.length) {
            GlStateManager.pushMatrix();
            gn gn2 = a10;
            gn2.setRotTranForPartType(a2, a10.s[n2], a3, a4, a5, a6, a7, a8, a9);
            gn2.renderSkinType((EntityLivingBase)a2, gn2.s[n2++]);
            GlStateManager.popMatrix();
            n3 = n2;
        }
    }

    public abstract void setRotTranForPartType(E var1, r var2, float var3, float var4, float var5, float var6, float var7, float var8, float var9);

    public boolean shouldCombineTextures() {
        return false;
    }

    public void renderSkinType(EntityLivingBase a2, r a3) {
        a2 = zg.r((Entity)a2, (r)a3).iterator();
        Iterator<fk> iterator = a2;
        while (iterator.hasNext()) {
            int n2;
            a3 = (fk)a2.next();
            if ((a3 = kd.j.getSkin((fk)a3)) == null) {
                iterator = a2;
                continue;
            }
            oh oh2 = oh.l;
            GL11.glEnable((int)2977);
            int n3 = n2 = 0;
            while (n3 < ((yl)a3).y().size()) {
                rk.j.renderPart(new mk(((yl)a3).y().get(++n2), m, new mn(), oh2, 0.0, false, false, false, null));
                n3 = n2;
            }
            GL11.glDisable((int)2977);
            iterator = a2;
        }
    }
}

