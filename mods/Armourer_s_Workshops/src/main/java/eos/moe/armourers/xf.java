/*
 * Decompiled with CFR 0.152.
 */
package eos.moe.armourers;

import eos.moe.armourers.j;

public class xf
implements j {
    private int l;
    private int c;
    private int v;
    private int s;
    private int m;
    private int j;

    public String toString() {
        xf a2;
        return new StringBuilder().insert(0, "Rectangle3D [x=").append(a2.v).append(", y=").append(a2.s).append(", z=").append(a2.c).append(", width=").append(a2.l).append(", height=").append(a2.j).append(", depth=").append(a2.m).append("]").toString();
    }

    @Override
    public int y() {
        xf a2;
        return a2.l;
    }

    @Override
    public int z() {
        xf a2;
        return a2.m;
    }

    @Override
    public int s() {
        xf a2;
        return a2.s;
    }

    @Override
    public int h() {
        xf a2;
        return a2.j;
    }

    public xf(int a2, int a3, int a4, int a5, int a6, int a7) {
        xf a8;
        xf xf2 = a8;
        xf xf3 = a8;
        xf xf4 = a8;
        xf4.v = a2;
        xf4.s = a3;
        xf3.c = a4;
        xf3.l = a5;
        xf2.j = a6;
        xf2.m = a7;
    }

    public void s(int a2) {
        a.v = a2;
    }

    public void x(int a2) {
        a.j = a2;
    }

    public void h(int a2) {
        a.l = a2;
    }

    public void z(int a2) {
        a.m = a2;
    }

    @Override
    public int r() {
        xf a2;
        return a2.c;
    }

    public void y(int a2) {
        a.c = a2;
    }

    @Override
    public int x() {
        xf a2;
        return a2.v;
    }

    public void r(int a2) {
        a.s = a2;
    }
}

