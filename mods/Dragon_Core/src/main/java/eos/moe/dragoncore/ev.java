/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.lwjgl.util.vector.Matrix4f
 *  org.lwjgl.util.vector.ReadableVector4f
 *  org.lwjgl.util.vector.Vector4f
 */
package eos.moe.dragoncore;

import eos.moe.dragoncore.lz;
import eos.moe.dragoncore.vr;
import org.lwjgl.util.vector.Matrix4f;
import org.lwjgl.util.vector.ReadableVector4f;
import org.lwjgl.util.vector.Vector4f;

public class ev
extends lz {
    public ev t;
    public final Vector4f r;
    public Vector4f x;
    public final Vector4f v;
    public Vector4f m;
    public final int c;
    public float q;
    public float b;
    public float o;

    public ev(ev a2) {
        super(a2.y, a2.k, a2.ALLATORIxDEMO);
        ev a3;
        a3.t = null;
        a3.x = new Vector4f();
        a3.m = new Vector4f();
        a3.q = a2.q;
        a3.b = a2.b;
        a3.o = a2.o;
        a3.r = new Vector4f((ReadableVector4f)a2.r);
        a3.v = new Vector4f((ReadableVector4f)a2.v);
        a3.c = a2.c;
        a2.t = a3;
        a3.x = a2.x;
        a3.m = a2.m;
    }

    public ev(float a2, float a3, float a4, float a5, float a6, float a7, int a8) {
        super(a2, a3, a4);
        ev a9;
        a9.t = null;
        a9.x = new Vector4f();
        a9.m = new Vector4f();
        a9.q = a5;
        a9.b = a6;
        a9.o = a7;
        a9.r = new Vector4f(a2, a3, a4, 1.0f);
        a9.v = new Vector4f(a5, a6, a7, 0.0f);
        a9.c = a8;
    }

    public void f() {
        a.x = null;
        a.m = null;
    }

    public void c() {
        ev a2;
        if (a2.x == null) {
            a2.x = new Vector4f();
        }
        if (a2.m == null) {
            a2.m = new Vector4f();
        }
    }

    public void ALLATORIxDEMO(vr a2, float a3) {
        Matrix4f a4 = a2.q;
        if (a4 != null) {
            ev a5;
            a5.c();
            Vector4f a6 = Matrix4f.transform((Matrix4f)a4, (Vector4f)a5.r, (Vector4f)null);
            Vector4f a7 = Matrix4f.transform((Matrix4f)a4, (Vector4f)a5.v, (Vector4f)null);
            a6.scale(a3);
            a7.scale(a3);
            Vector4f.add((Vector4f)a6, (Vector4f)a5.x, (Vector4f)a5.x);
            Vector4f.add((Vector4f)a7, (Vector4f)a5.m, (Vector4f)a5.m);
        }
    }

    public void ALLATORIxDEMO() {
        ev a2;
        if (a2.x == null) {
            a2.y = a2.r.x;
            a2.k = a2.r.y;
            a2.ALLATORIxDEMO = a2.r.z;
        } else {
            a2.y = a2.x.x;
            a2.k = a2.x.y;
            a2.ALLATORIxDEMO = a2.x.z;
        }
        if (a2.m == null) {
            a2.q = a2.v.x;
            a2.b = a2.v.y;
            a2.o = a2.v.z;
        } else {
            a2.q = a2.m.x;
            a2.b = a2.m.y;
            a2.o = a2.m.z;
        }
    }

    public boolean ALLATORIxDEMO(float a2, float a3, float a4) {
        ev a5;
        return a5.y == a2 && a5.k == a3 && a5.ALLATORIxDEMO == a4;
    }

    public float k(float a2) {
        ev a3;
        return a3.y;
    }

    public float d(float a2) {
        ev a3;
        return a3.k;
    }

    public float x(float a2) {
        ev a3;
        return a3.ALLATORIxDEMO;
    }

    public float f(float a2) {
        ev a3;
        return a3.q;
    }

    public float c(float a2) {
        ev a3;
        return a3.b;
    }

    public float ALLATORIxDEMO(float a2) {
        ev a3;
        return a3.o;
    }
}

