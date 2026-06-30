/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.entity.AbstractClientPlayer
 *  net.minecraft.client.model.ModelBiped
 *  net.minecraft.client.renderer.GlStateManager
 *  net.minecraft.client.renderer.entity.RenderPlayer
 *  net.minecraft.client.renderer.entity.layers.LayerRenderer
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraftforge.fml.relauncher.Side
 *  net.minecraftforge.fml.relauncher.SideOnly
 */
package eos.moe.armourers;

import eos.moe.armourers.MobendsHelper;
import eos.moe.armourers.dn;
import eos.moe.armourers.fk;
import eos.moe.armourers.km;
import eos.moe.armourers.mn;
import eos.moe.armourers.n;
import eos.moe.armourers.oh;
import eos.moe.armourers.on;
import eos.moe.armourers.r;
import eos.moe.armourers.vk;
import eos.moe.armourers.vn;
import eos.moe.armourers.yl;
import eos.moe.armourers.zg;
import eos.moe.armourers.zh;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.AbstractClientPlayer;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderPlayer;
import net.minecraft.client.renderer.entity.layers.LayerRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(value=Side.CLIENT)
public class qg
implements LayerRenderer<EntityPlayer> {
    private r[] m;
    private RenderPlayer j;

    private /* synthetic */ void r(EntityPlayer a2, yl a3, oh a4, double a5, boolean a6) {
        km km2 = km.t;
        if (a3 != null) {
            qg a7;
            on.y(a2.getUniqueID()).r(a3);
            GlStateManager.pushMatrix();
            km2.r(a3, new dn(0.0625f, (n)new mn(), a4, a5, a6, false, false, ((AbstractClientPlayer)a2).getLocationSkin()), (Entity)a2, (ModelBiped)a7.j.getMainModel());
            GlStateManager.popMatrix();
        }
    }

    public qg(RenderPlayer a2) {
        qg a3;
        qg qg2 = a3;
        qg2.j = a2;
        qg2.m = new r[6];
        qg qg3 = a3;
        qg3.m[0] = vn.l;
        qg3.m[1] = vn.a;
        qg3.m[2] = vn.e;
        qg3.m[3] = vn.w;
        qg3.m[4] = vn.g;
        qg3.m[5] = vn.u;
    }

    public boolean shouldCombineTextures() {
        return false;
    }

    public void doRenderLayer(EntityPlayer a2, float a32, float a42, float a52, float a6, float a72, float a82, float a9) {
        qg a10;
        if (MobendsHelper.isAnimation()) {
            return;
        }
        double d2 = Minecraft.getMinecraft().player.getDistance(a2.posX, a2.posY, a2.posZ);
        if (d2 > (double)vk.n || !zh.g && d2 != 0.0) {
            return;
        }
        qg qg2 = a10;
        qg2.j.getMainModel().bipedLeftArm.isHidden = false;
        qg2.j.getMainModel().bipedRightArm.isHidden = false;
        on.r(a2.getUniqueID()).r();
        oh a32 = oh.l;
        r[] a42 = a10.m;
        int a52 = a10.m.length;
        int n2 = a6 = 0;
        while (n2 < a52) {
            r a72 = a42[a6];
            for (fk a82 : zg.r((Entity)a2, a72)) {
                if (a82 == null) continue;
                a10.r(a2, a82, a32, 0.0, a2 != Minecraft.getMinecraft().player);
            }
            n2 = ++a6;
        }
    }

    private /* synthetic */ void r(EntityPlayer a2, fk a3, oh a4, double a5, boolean a6) {
        qg a7;
        a3 = ((fk)a3).r();
        a7.r(a2, (yl)a3, a4, a5, a6);
    }
}

