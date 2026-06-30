/*
 * Decompiled with CFR 0.152.
 */
package eos.moe.armourers;

import eos.moe.armourers.qb;
import eos.moe.armourers.wm;

public class nb
extends qb {
    private byte[] s;
    private long m;
    private boolean j;

    public void x(boolean a2) {
        a.j = a2;
    }

    public void x(long a2) {
        a.m = a2;
    }

    public byte[] z() {
        nb a2;
        return a2.s;
    }

    public boolean x() {
        nb a2;
        return a2.j;
    }

    public void z(byte[] a2) {
        a.s = a2;
    }

    public nb() {
        nb a2;
        nb nb2 = a2;
        nb2.r(wm.c);
    }

    public long s() {
        nb a2;
        return a2.m;
    }
}

