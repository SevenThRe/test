/*
 * Decompiled with CFR 0.152.
 */
package eos.moe.armourers;

import eos.moe.armourers.a;

public class if
implements a {
    private int s;
    private int m;
    private int j;

    @Override
    public int y() {
        if a2;
        return a2.j;
    }

    public String toString() {
        if a2;
        return new StringBuilder().insert(0, "Point3D [x=").append(a2.s).append(", y=").append(a2.m).append(", z=").append(a2.j).append("]").toString();
    }

    @Override
    public int r() {
        if a2;
        return a2.m;
    }

    @Override
    public int z() {
        if a2;
        return a2.s;
    }

    public if(int a2, int a3, int a4) {
        if a5;
        if if_ = a5;
        a5.s = a2;
        if_.m = a3;
        if_.j = a4;
    }
}

