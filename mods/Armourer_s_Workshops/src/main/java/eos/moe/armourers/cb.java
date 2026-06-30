/*
 * Decompiled with CFR 0.152.
 */
package eos.moe.armourers;

import eos.moe.armourers.ph;
import eos.moe.armourers.uh;

public final class cb
extends Enum<cb> {
    private int c;
    private static final /* synthetic */ cb[] v;
    public static final /* enum */ cb s = new cb("STORE", 0, 0);
    public static final /* enum */ cb m;
    public static final /* enum */ cb j;

    public static cb valueOf(String a2) {
        return Enum.valueOf(cb.class, a2);
    }

    /*
     * WARNING - Possible parameter corruption
     * WARNING - void declaration
     */
    private /* synthetic */ cb(int n2) {
        void a2;
        void var2_-1;
        void var1_-1;
        cb a3;
        a3.c = a2;
    }

    public static cb[] values() {
        return (cb[])v.clone();
    }

    static {
        j = new cb("DEFLATE", 1, 8);
        m = new cb("AES_INTERNAL_ONLY", 2, 99);
        cb[] cbArray = new cb[3];
        cbArray[0] = s;
        cbArray[1] = j;
        cbArray[2] = m;
        v = cbArray;
    }

    public static cb r(int a2) throws ph {
        int n2;
        cb[] cbArray = cb.values();
        int n3 = cbArray.length;
        int n4 = n2 = 0;
        while (n4 < n3) {
            cb cb2 = cbArray[n2];
            if (cb2.r() == a2) {
                return cb2;
            }
            n4 = ++n2;
        }
        throw new ph("Unknown compression method", uh.c);
    }

    public int r() {
        cb a2;
        return a2.c;
    }
}

