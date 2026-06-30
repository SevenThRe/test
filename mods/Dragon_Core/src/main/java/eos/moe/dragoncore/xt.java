/*
 * Decompiled with CFR 0.152.
 */
package eos.moe.dragoncore;

import eos.moe.dragoncore.ev;

public class xt
extends ev {
    private boolean g = false;
    private float t;
    private float r;
    private float x;
    private float v;
    private float m;
    private float c;

    public xt(float a2, float a3, float a4, float a5, float a6, float a7, int a8) {
        super(a2, a3, a4, a5, a6, a7, a8);
        xt a9;
    }

    @Override
    public void ALLATORIxDEMO() {
        xt a2;
        if (a2.g) {
            a2.y = a2.t;
            a2.k = a2.r;
            a2.ALLATORIxDEMO = a2.x;
        }
        if (a2.x == null) {
            a2.t = a2.r.x;
            a2.r = a2.r.y;
            a2.x = a2.r.z;
        } else {
            a2.t = a2.x.x;
            a2.r = a2.x.y;
            a2.x = a2.x.z;
        }
        if (a2.m == null) {
            a2.v = a2.v.x;
            a2.m = a2.v.y;
            a2.c = a2.v.z;
        } else {
            a2.v = a2.m.x;
            a2.m = a2.m.y;
            a2.c = a2.m.z;
        }
        if (!a2.g) {
            a2.y = a2.t;
            a2.k = a2.r;
            a2.ALLATORIxDEMO = a2.x;
            a2.g = true;
        }
    }

    @Override
    public float k(float a2) {
        xt a3;
        return a3.y * (1.0f - a2) + a3.t * a2;
    }

    @Override
    public float d(float a2) {
        xt a3;
        return a3.k * (1.0f - a2) + a3.r * a2;
    }

    @Override
    public float x(float a2) {
        xt a3;
        return a3.ALLATORIxDEMO * (1.0f - a2) + a3.x * a2;
    }

    @Override
    public float f(float a2) {
        xt a3;
        return a3.q;
    }

    @Override
    public float c(float a2) {
        xt a3;
        return a3.b;
    }

    @Override
    public float ALLATORIxDEMO(float a2) {
        xt a3;
        return a3.o;
    }
}

