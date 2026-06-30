/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.lwjgl.util.vector.Matrix4f
 */
package eos.moe.dragoncore;

import eos.moe.dragoncore.eu;
import eos.moe.dragoncore.iq;
import eos.moe.dragoncore.vr;
import java.util.ArrayList;
import org.lwjgl.util.vector.Matrix4f;

public class mz {
    public ArrayList<Matrix4f> o;
    public ArrayList<Matrix4f> y;
    public eu k;
    public int ALLATORIxDEMO;

    public mz(mz a2, eu a3) {
        mz a4;
        a4.o = new ArrayList();
        a4.y = new ArrayList();
        a4.k = a3;
        a4.ALLATORIxDEMO = a2.ALLATORIxDEMO;
        a4.o = a2.o;
        a4.y = a2.y;
    }

    public mz(eu a2) {
        mz a3;
        a3.o = new ArrayList();
        a3.y = new ArrayList();
        a3.k = a2;
        a3.ALLATORIxDEMO = a2.c();
    }

    public void ALLATORIxDEMO(int a2, Matrix4f a3) {
        mz a4;
        a4.o.add(a2, a3);
        a4.y.add(a2, Matrix4f.invert((Matrix4f)a3, null));
    }

    public void ALLATORIxDEMO(int a2, float a3) {
        mz a4;
        float a5 = (float)Math.toRadians(a3);
        Matrix4f a6 = iq.ALLATORIxDEMO(0.0f, 0.0f, 0.0f, a5, 0.0f, 0.0f);
        Matrix4f.mul((Matrix4f)a6, (Matrix4f)a4.o.get(a2), (Matrix4f)a4.o.get(a2));
        Matrix4f.mul((Matrix4f)Matrix4f.invert((Matrix4f)a6, null), (Matrix4f)a4.y.get(a2), (Matrix4f)a4.y.get(a2));
    }

    public void ALLATORIxDEMO() {
        mz a2;
        for (int a3 = 0; a3 < a2.o.size(); ++a3) {
            vr a4 = a2.k.c.get(a3);
            if (a4.r == null) continue;
            Matrix4f a5 = Matrix4f.mul((Matrix4f)a2.o.get(a4.r.t), (Matrix4f)a2.o.get(a3), null);
            a2.o.set(a3, a5);
            a2.y.set(a3, Matrix4f.invert((Matrix4f)a5, null));
        }
    }
}

