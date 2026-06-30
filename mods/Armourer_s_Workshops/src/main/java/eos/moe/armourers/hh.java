/*
 * Decompiled with CFR 0.152.
 */
package eos.moe.armourers;

import eos.moe.armourers.i;
import eos.moe.armourers.in;

public class hh<T>
implements i<T> {
    private String m;
    private T j;

    public void r(in a2, T a3, int a4) {
        hh a5;
        a2.r(a5.m + String.valueOf(a4), a3);
    }

    public void r(in a2, T a3) {
        hh a4;
        a2.r(a4.m, a3);
    }

    public void r(in a2, int a3) {
        hh a4;
        a2.r(a4.m + String.valueOf(a3));
    }

    public T r(in a2, int a3) {
        hh a4;
        if (a2.r(a4.m + String.valueOf(a3))) {
            return (T)a2.r(new StringBuilder().insert(0, a4.m).append(String.valueOf(a3)).toString(), a4.j);
        }
        if (a2.r(a4.m)) {
            hh hh2 = a4;
            return (T)a2.r(hh2.m, hh2.j);
        }
        return a4.j;
    }

    public T r() {
        hh a2;
        return a2.j;
    }

    public void r(in a2) {
        hh a3;
        a2.r(a3.m);
    }

    public String r() {
        hh a2;
        return a2.m;
    }

    public hh(String a2, T a3) {
        hh a4;
        hh hh2 = a4;
        hh2.m = a2;
        hh2.j = a3;
    }

    public T r(in a2) {
        hh a3;
        hh hh2 = a3;
        return (T)a2.r(hh2.m, hh2.j);
    }
}

