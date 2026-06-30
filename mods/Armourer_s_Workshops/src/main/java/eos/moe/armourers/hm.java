/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.renderer.GlStateManager
 *  net.minecraft.client.renderer.GlStateManager$DestFactor
 *  net.minecraft.client.renderer.GlStateManager$SourceFactor
 *  net.minecraft.client.renderer.RenderHelper
 *  net.minecraft.entity.Entity
 *  net.minecraftforge.fml.relauncher.Side
 *  net.minecraftforge.fml.relauncher.SideOnly
 *  org.lwjgl.opengl.GL11
 */
package eos.moe.armourers;

import eos.moe.armourers.a;
import eos.moe.armourers.dn;
import eos.moe.armourers.kf;
import eos.moe.armourers.mk;
import eos.moe.armourers.n;
import eos.moe.armourers.oh;
import eos.moe.armourers.pj;
import eos.moe.armourers.rk;
import eos.moe.armourers.yl;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.entity.Entity;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.lwjgl.opengl.GL11;

@SideOnly(value=Side.CLIENT)
public class hm
extends pj {
    public hm() {
        hm a2;
    }

    @Override
    public void render(Entity a2, yl a3, boolean a4, n a5, oh a6, boolean a7, double a8, boolean a9) {
        hm a10;
        a10.render(a2, a3, new dn(v, a5, a6, a8, a9, a4, a7, null));
    }

    @Override
    public void render(Entity a2, yl a3, dn a4) {
        if (a3 == null) {
            return;
        }
        GlStateManager.func_179123_a();
        RenderHelper.func_74520_c();
        GlStateManager.func_179089_o();
        GlStateManager.func_187401_a((GlStateManager.SourceFactor)GlStateManager.SourceFactor.SRC_ALPHA, (GlStateManager.DestFactor)GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA);
        GlStateManager.func_179147_l();
        GlStateManager.func_179091_B();
        int n2 = a2 = 0;
        while (n2 < a3.y().size()) {
            GL11.glPushMatrix();
            kf kf2 = a3.y().get(a2);
            a a5 = kf2.r().y();
            GL11.glTranslated((double)((float)a5.z() * v), (double)((float)(a5.r() + 1) * v), (double)((float)a5.y() * v));
            rk.j.renderPart(new mk(kf2, a4));
            GL11.glPopMatrix();
            n2 = ++a2;
        }
        GlStateManager.func_179101_C();
        GlStateManager.func_179084_k();
        GlStateManager.func_179129_p();
        GlStateManager.func_179099_b();
    }
}

