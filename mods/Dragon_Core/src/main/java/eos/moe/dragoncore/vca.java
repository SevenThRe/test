/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.renderer.GlStateManager
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityLivingBase
 */
package eos.moe.dragoncore;

import eos.moe.dragoncore.pa;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;

public class vca
implements pa {
    private String r;
    private String[] x;
    private float v;
    private int m;
    private int c;
    private boolean q;
    private int b;
    private int o;
    private boolean y;
    private long k;
    private long ALLATORIxDEMO;

    public vca(long a2, String a3) {
        vca a4;
        a4.r = a3;
        String[] a5 = a3.split("#");
        a4.x = a5[0].toLowerCase().split("");
        a4.b = Integer.parseInt(a5[1]);
        a4.v = Float.parseFloat(a5[2]);
        a4.c = Integer.parseInt(a5[3]);
        a4.m = Integer.parseInt(a5[4]);
        a4.q = Boolean.parseBoolean(a5[5]);
        a4.o = Integer.parseInt(a5[6]);
        a4.y = Boolean.parseBoolean(a5[7]);
        a4.k = a2 + (long)a4.b;
        if (a4.o > 0) {
            a4.ALLATORIxDEMO = (long)(a4.o + a4.m * a4.c) + a4.k;
        }
    }

    private /* synthetic */ void ALLATORIxDEMO() {
        vca a2;
        a2.k = a2.ALLATORIxDEMO;
        a2.ALLATORIxDEMO = (long)(a2.o + a2.m * a2.c) + a2.k;
    }

    @Override
    public void ALLATORIxDEMO(long a2, Entity a3, float a4) {
        vca a5;
        if (a5.y && a3 == null) {
            return;
        }
        if (a2 > a5.k && a5.c > 0) {
            long a6;
            if (a5.m != -1 && (a6 = (a2 - a5.k) / (long)a5.c) >= (long)a5.m) {
                if (a5.q) {
                    a5.ALLATORIxDEMO(a3, a4, a5.v);
                }
                if (a5.ALLATORIxDEMO != 0L && a2 > a5.ALLATORIxDEMO) {
                    a5.ALLATORIxDEMO();
                }
                return;
            }
            float a22 = (a2 - a5.k) % (long)a5.c;
            float a7 = a22 / (float)a5.c;
            a5.ALLATORIxDEMO(a3, a4, a5.v * a7);
        }
    }

    private /* synthetic */ void ALLATORIxDEMO(Entity a2, float a3, float a4) {
        vca a5;
        if (!a5.y) {
            String[] stringArray = a5.x;
            int n2 = stringArray.length;
            block20: for (int i2 = 0; i2 < n2; ++i2) {
                String a6;
                switch (a6 = stringArray[i2]) {
                    case "x": {
                        GlStateManager.translate((float)a4, (float)0.0f, (float)0.0f);
                        continue block20;
                    }
                    case "y": {
                        GlStateManager.translate((float)0.0f, (float)a4, (float)0.0f);
                        continue block20;
                    }
                    case "z": {
                        GlStateManager.translate((float)0.0f, (float)0.0f, (float)a4);
                    }
                }
            }
        } else {
            float a7 = 0.0f;
            if (a2 instanceof EntityLivingBase) {
                EntityLivingBase a8 = (EntityLivingBase)a2;
                a7 = a5.ALLATORIxDEMO(a8.prevRenderYawOffset, a8.renderYawOffset, a3);
            }
            double a9 = Math.toRadians(a7 + 90.0f);
            double a10 = Math.toRadians(90.0);
            double a11 = Math.sin(a10) * Math.cos(a9);
            double a12 = Math.sin(a10) * Math.sin(a9);
            a9 = a4 < 0.0f ? Math.toRadians(a7) : Math.toRadians(a7 + 180.0f);
            double a13 = (double)Math.abs(a4) * Math.sin(a10) * Math.cos(a9) + (double)a4 * a11;
            double a14 = (double)Math.abs(a4) * Math.sin(a10) * Math.sin(a9) + (double)a4 * a12;
            String[] stringArray = a5.x;
            int n3 = stringArray.length;
            block21: for (int i3 = 0; i3 < n3; ++i3) {
                String a15;
                switch (a15 = stringArray[i3]) {
                    case "x": {
                        GlStateManager.translate((double)a13, (double)0.0, (double)0.0);
                        continue block21;
                    }
                    case "y": {
                        GlStateManager.translate((double)0.0, (double)a4, (double)0.0);
                        continue block21;
                    }
                    case "z": {
                        GlStateManager.translate((double)0.0, (double)0.0, (double)a14);
                    }
                }
            }
        }
    }

    public float ALLATORIxDEMO(float a2, float a3, float a4) {
        float a5;
        for (a5 = a3 - a2; a5 < -180.0f; a5 += 360.0f) {
        }
        while (a5 >= 180.0f) {
            a5 -= 360.0f;
        }
        return a2 + a4 * a5;
    }
}

