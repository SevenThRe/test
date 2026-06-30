/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.renderer.GlStateManager
 *  net.minecraft.entity.Entity
 */
package eos.moe.dragoncore;

import eos.moe.dragoncore.pa;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.Entity;

public class zba
implements pa {
    private String x;
    private String[] v;
    private float m;
    private int c;
    private int q;
    private boolean b;
    private int o;
    private int y;
    private long k;
    private long ALLATORIxDEMO;

    public zba(long a2, String a3) {
        zba a4;
        a4.x = a3;
        String[] a5 = a3.split("#");
        a4.v = a5[0].toLowerCase().split("");
        a4.o = Integer.parseInt(a5[1]);
        a4.m = Float.parseFloat(a5[2]);
        a4.q = Integer.parseInt(a5[3]);
        a4.c = Integer.parseInt(a5[4]);
        a4.b = Boolean.parseBoolean(a5[5]);
        a4.y = Integer.parseInt(a5[6]);
        a4.k = a2 + (long)a4.o;
        if (a4.y > 0) {
            a4.ALLATORIxDEMO = (long)(a4.y + a4.c * a4.q) + a4.k;
        }
    }

    private /* synthetic */ void ALLATORIxDEMO() {
        zba a2;
        a2.k = a2.ALLATORIxDEMO;
        a2.ALLATORIxDEMO = (long)(a2.y + a2.c * a2.q) + a2.k;
    }

    @Override
    public void ALLATORIxDEMO(long a2, Entity a3, float a4) {
        zba a5;
        if (a2 > a5.k && a5.q > 0) {
            long a6;
            if (a5.c != -1 && (a6 = (a2 - a5.k) / (long)a5.q) >= (long)a5.c) {
                if (a5.b) {
                    a5.ALLATORIxDEMO(a5.m);
                }
                if (a5.ALLATORIxDEMO != 0L && a2 > a5.ALLATORIxDEMO) {
                    a5.ALLATORIxDEMO();
                }
                return;
            }
            float a22 = (a2 - a5.k) % (long)a5.q;
            float a7 = a22 / (float)a5.q;
            a5.ALLATORIxDEMO(a5.m * a7);
        }
    }

    private /* synthetic */ void ALLATORIxDEMO(float a2) {
        zba a3;
        String[] stringArray = a3.v;
        int n2 = stringArray.length;
        block10: for (int i2 = 0; i2 < n2; ++i2) {
            String a4;
            switch (a4 = stringArray[i2]) {
                case "x": {
                    GlStateManager.func_179114_b((float)a2, (float)1.0f, (float)0.0f, (float)0.0f);
                    continue block10;
                }
                case "y": {
                    GlStateManager.func_179114_b((float)a2, (float)0.0f, (float)1.0f, (float)0.0f);
                    continue block10;
                }
                case "z": {
                    GlStateManager.func_179114_b((float)a2, (float)0.0f, (float)0.0f, (float)1.0f);
                }
            }
        }
    }
}

