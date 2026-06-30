/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.gui.ScaledResolution
 *  net.minecraft.client.renderer.ActiveRenderInfo
 *  net.minecraft.client.renderer.EntityRenderer
 *  net.minecraft.client.renderer.entity.RenderManager
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.util.math.MathHelper
 *  net.minecraft.util.math.Vec3d
 */
package eos.moe.dragoncore;

import eos.moe.dragoncore.gg;
import eos.moe.dragoncore.i;
import eos.moe.dragoncore.ik;
import eos.moe.dragoncore.qg;
import eos.moe.dragoncore.uy;
import java.util.ArrayList;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.renderer.ActiveRenderInfo;
import net.minecraft.client.renderer.EntityRenderer;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;

public class tr {
    public tr() {
        tr a2;
    }

    @i(f={"\u53d6\u4e16\u754c\u5c4f\u5e55\u5750\u6807"})
    public static qg ALLATORIxDEMO(double a2, double a3, double a4) {
        uy a5 = tr.ALLATORIxDEMO(a2, a3, a4, Minecraft.getMinecraft().getRenderPartialTicks());
        ArrayList<String> a6 = new ArrayList<String>();
        a6.add(String.valueOf(uy.c(a5)));
        a6.add(String.valueOf(uy.ALLATORIxDEMO(a5)));
        a6.add(String.valueOf(uy.ALLATORIxDEMO(a5)));
        return new qg(a6, true);
    }

    @i(f={"\u53d6\u4e16\u754c\u5c4f\u5e55\u5750\u6807\u4fee\u6b63"})
    public static qg ALLATORIxDEMO(double a2, double a3, double a4, float a5) {
        double a6;
        uy a7 = tr.ALLATORIxDEMO(a2, a3, a4, Minecraft.getMinecraft().getRenderPartialTicks());
        if (a5 <= 0.0f || a5 > 1.0f) {
            a5 = 0.66f;
        }
        ScaledResolution a8 = new ScaledResolution(Minecraft.getMinecraft());
        double a9 = a8.getScaledWidth_double();
        double a10 = a8.getScaledHeight_double();
        double a11 = 0.0;
        double a12 = 0.0;
        a11 = a9 / 2.0 + (double)uy.c(a7);
        a12 = a10 / 2.0 - (double)uy.ALLATORIxDEMO(a7);
        if (uy.ALLATORIxDEMO(a7)) {
            if (a11 < 0.0 || a11 > a9 || a12 < 0.0) {
                a11 = Math.max(a11, 0.0);
                a11 = Math.min(a11, a9);
                a6 = a11 - a9 / 2.0;
                double a13 = Math.pow(a6, 2.0) / Math.pow(a9 / 2.0, 2.0);
                a13 = (1.0 - a13) * Math.pow(a10 / 2.0, 2.0);
                a12 = Math.sqrt(a13) + a10 / 2.0 + 4.0;
                a12 = a10 - a12;
                a11 = (a11 - a9 / 2.0) * (double)a5 + a9 / 2.0;
                a12 = (a12 - a10 / 2.0) * (double)a5 + a10 / 2.0;
            } else {
                a12 = Math.max(a12, 0.0);
                a12 = Math.min(a12, a10);
            }
        } else {
            a11 = Math.max(a11, 0.0);
            a11 = Math.min(a11, a9);
            a11 = a9 - a11;
            a6 = a11 - a9 / 2.0;
            double a14 = Math.pow(a6, 2.0) / Math.pow(a9 / 2.0, 2.0);
            a14 = (1.0 - a14) * Math.pow(a10 / 2.0, 2.0);
            a12 = Math.sqrt(a14) + a10 / 2.0 - 4.0;
            a11 = (a11 - a9 / 2.0) * (double)a5 + a9 / 2.0;
            a12 = (a12 - a10 / 2.0) * (double)a5 + a10 / 2.0;
        }
        ArrayList<String> a15 = new ArrayList<String>();
        a15.add(String.valueOf(a11));
        a15.add(String.valueOf(a12));
        return new qg(a15, true);
    }

    private static /* synthetic */ uy ALLATORIxDEMO(double a2, double a3, double a4, float a5) {
        float a6;
        float a7;
        float a8;
        EntityRenderer a9;
        Minecraft a10 = Minecraft.getMinecraft();
        RenderManager a11 = Minecraft.getMinecraft().getRenderManager();
        Vec3d a12 = ActiveRenderInfo.projectViewFromEntity((Entity)a11.renderViewEntity, (double)a5);
        ik a13 = new ik(0.0f, 0.0f, 0.0f, 1.0f);
        a13.ALLATORIxDEMO(gg.q.ALLATORIxDEMO(-a11.playerViewY));
        a13.ALLATORIxDEMO(gg.m.ALLATORIxDEMO(a11.playerViewX));
        a13.c();
        gg a14 = new gg((float)(a12.x - a2), (float)(a12.y - a3), (float)(a12.z - a4));
        a14.ALLATORIxDEMO(a13);
        if (a10.gameSettings.viewBobbing && (a9 = a10.getRenderManager().renderViewEntity) instanceof EntityPlayer) {
            EntityPlayer a15 = (EntityPlayer)a9;
            float a16 = a15.distanceWalkedModified;
            a8 = a16 - a15.prevDistanceWalkedModified;
            a7 = -(a16 + a8 * a5);
            a6 = tr.ALLATORIxDEMO(a5, a15.prevCameraYaw, a15.cameraYaw);
            gg a17 = new gg(-(MathHelper.sin((float)(a7 * (float)Math.PI)) * a6 * 0.5f), Math.abs(MathHelper.cos((float)(a7 * (float)Math.PI)) * a6), 0.0f);
            a17.c(-a17.c());
            a14.f(a17);
        }
        a9 = Minecraft.getMinecraft().entityRenderer;
        float a18 = a9.getFOVModifier(a5, true);
        ScaledResolution a19 = new ScaledResolution(a10);
        a8 = (float)a19.getScaledHeight_double() / 2.0f;
        a7 = a8 / (a14.ALLATORIxDEMO() * (float)Math.tan(Math.toRadians(a18 / 2.0f)));
        a6 = -a14.f() * a7;
        float a20 = a14.c() * a7;
        boolean a21 = a14.ALLATORIxDEMO() < 0.0f;
        return new uy(a6, a20, a21);
    }

    public static float ALLATORIxDEMO(float a2, float a3, float a4) {
        return a3 + a2 * (a4 - a3);
    }
}

