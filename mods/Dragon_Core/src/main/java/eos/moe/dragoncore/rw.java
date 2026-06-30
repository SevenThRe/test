/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.lwjgl.opengl.GL11
 */
package eos.moe.dragoncore;

import eos.moe.dragoncore.ev;
import eos.moe.dragoncore.lz;
import eos.moe.dragoncore.ow;
import java.nio.FloatBuffer;
import java.util.ArrayList;
import org.lwjgl.opengl.GL11;

public class rw {
    public ev[] y;
    public ow[] k;
    public lz ALLATORIxDEMO;

    public rw(ev[] a2, ow[] a3) {
        rw a4;
        a4.y = a2;
        a4.k = a3;
    }

    public rw(rw a2, ArrayList<ev> a3) {
        rw a4;
        a4.y = new ev[a2.y.length];
        for (int a5 = 0; a5 < a4.y.length; ++a5) {
            a4.y[a5] = a3.get(a2.y[a5].c);
        }
        a4.k = new ow[a2.k.length];
        System.arraycopy(a2.k, 0, a4.k, 0, a4.k.length);
        if (a2.ALLATORIxDEMO != null) {
            a4.ALLATORIxDEMO = a2.ALLATORIxDEMO;
        }
    }

    public void ALLATORIxDEMO(boolean a2) {
        rw a3;
        if (!a2 && a3.ALLATORIxDEMO == null) {
            a3.ALLATORIxDEMO = a3.ALLATORIxDEMO();
        }
        for (int a4 = 0; a4 < 3; ++a4) {
            GL11.glTexCoord2f((float)a3.k[a4].k, (float)a3.k[a4].ALLATORIxDEMO);
            if (!a2) {
                GL11.glNormal3f((float)a3.ALLATORIxDEMO.y, (float)a3.ALLATORIxDEMO.k, (float)a3.ALLATORIxDEMO.ALLATORIxDEMO);
            } else {
                GL11.glNormal3f((float)a3.y[a4].q, (float)a3.y[a4].b, (float)a3.y[a4].o);
            }
            GL11.glVertex3d((double)a3.y[a4].y, (double)a3.y[a4].k, (double)a3.y[a4].ALLATORIxDEMO);
        }
    }

    public lz ALLATORIxDEMO() {
        rw a2;
        float a3 = (a2.y[0].q + a2.y[1].q + a2.y[2].q) / 3.0f;
        float a4 = (a2.y[0].b + a2.y[1].b + a2.y[2].b) / 3.0f;
        float a5 = (a2.y[0].o + a2.y[1].o + a2.y[2].o) / 3.0f;
        return new lz(a3, a4, a5);
    }

    public void ALLATORIxDEMO(FloatBuffer a2, FloatBuffer a3, FloatBuffer a4, boolean a5, float a6) {
        rw a7;
        if (!a5 && a7.ALLATORIxDEMO == null) {
            a7.ALLATORIxDEMO = a7.ALLATORIxDEMO();
        }
        for (int a8 = 0; a8 < 3; ++a8) {
            a3.put(a7.k[a8].k);
            a3.put(a7.k[a8].ALLATORIxDEMO);
            if (!a5) {
                a4.put(a7.ALLATORIxDEMO.y);
                a4.put(a7.ALLATORIxDEMO.k);
                a4.put(a7.ALLATORIxDEMO.ALLATORIxDEMO);
            } else {
                a4.put(a7.y[a8].f(a6));
                a4.put(a7.y[a8].c(a6));
                a4.put(a7.y[a8].ALLATORIxDEMO(a6));
            }
            a2.put(a7.y[a8].k(a6));
            a2.put(a7.y[a8].d(a6));
            a2.put(a7.y[a8].x(a6));
        }
    }

    public void ALLATORIxDEMO(FloatBuffer a2, FloatBuffer a3, boolean a4, float a5) {
        rw a6;
        if (!a4 && a6.ALLATORIxDEMO == null) {
            a6.ALLATORIxDEMO = a6.ALLATORIxDEMO();
        }
        for (int a7 = 0; a7 < 3; ++a7) {
            if (!a4) {
                a3.put(a6.ALLATORIxDEMO.y);
                a3.put(a6.ALLATORIxDEMO.k);
                a3.put(a6.ALLATORIxDEMO.ALLATORIxDEMO);
            } else {
                a3.put(a6.y[a7].f(a5));
                a3.put(a6.y[a7].c(a5));
                a3.put(a6.y[a7].ALLATORIxDEMO(a5));
            }
            a2.put(a6.y[a7].k(a5));
            a2.put(a6.y[a7].d(a5));
            a2.put(a6.y[a7].x(a5));
        }
    }
}

