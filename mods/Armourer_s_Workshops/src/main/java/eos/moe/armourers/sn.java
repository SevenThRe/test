/*
 * Decompiled with CFR 0.152.
 */
package eos.moe.armourers;

import eos.moe.armourers.kg;
import eos.moe.armourers.vi;

public class sn {
    public boolean r(kg a2, String a3) {
        if (a2 == null || a3 == null) {
            return true;
        }
        if ((a3 = a3.split(":")).length != 3) {
            return true;
        }
        a3 = vi.r(a3[0]);
        int n2 = Integer.parseInt(a3[1]);
        byte[] byArray = vi.r(a3[2]);
        kg kg2 = a2;
        a2.r((byte[])a3);
        kg2.r(n2);
        kg2.y(byArray);
        return false;
    }

    public sn() {
        sn a2;
    }

    public String r(kg a2) {
        return new StringBuilder().insert(0, vi.r(a2.r())).append(":").append(String.valueOf(a2.r())).append(":").append(vi.r(a2.y())).toString();
    }
}

