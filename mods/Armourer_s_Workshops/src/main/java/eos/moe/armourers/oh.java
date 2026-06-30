/*
 * Decompiled with CFR 0.152.
 */
package eos.moe.armourers;

import eos.moe.armourers.eo;
import eos.moe.armourers.fe;
import java.awt.Color;
import java.util.Arrays;

/*
 * Duplicate member names - consider using --renamedupmembers true
 */
public class oh {
    public static oh l;
    public static Color c;
    public static Color v;
    public int[] s;
    public static Color m;
    public static Color j;

    public boolean equals(Object a2) {
        oh a3;
        if (a3 == a2) {
            return true;
        }
        if (a2 == null) {
            return false;
        }
        if (a3.getClass() != a2.getClass()) {
            return false;
        }
        a2 = (oh)a2;
        return Arrays.equals(a3.s, ((oh)a2).s);
    }

    public oh() {
        oh a2;
        a2.s = new int[eo.values().length];
        oh oh2 = a2;
        oh2.r(eo.s, m.getRGB());
        oh2.r(eo.v, c.getRGB());
        oh2.r(eo.j, v.getRGB());
        oh2.r(eo.c, j.getRGB());
    }

    public int hashCode() {
        oh a2;
        return 193 * Arrays.hashCode(a2.s);
    }

    public int r(eo a2) {
        oh a3;
        return a3.s[a2.ordinal()];
    }

    public byte[] r(eo a2) {
        oh a3;
        return fe.r(a3.s[a2.ordinal()]);
    }

    public void r(eo a2, int a3) {
        a.s[a2.ordinal()] = a3;
    }

    static {
        int n2;
        m = Color.decode("#F9DFD2");
        c = Color.decode("#804020");
        v = Color.decode("#808080");
        j = Color.decode("#808080");
        l = new oh();
        int n3 = n2 = 0;
        while (n3 < eo.values().length) {
            l.r(eo.values()[n2++], 0);
            n3 = n2;
        }
    }

    public oh(oh a2) {
        int n2;
        oh a3;
        a3.s = new int[eo.values().length];
        int n3 = n2 = 0;
        while (n3 < eo.values().length) {
            a3.r(eo.values()[n2], a2.r(eo.values()[n2++]));
            n3 = n2;
        }
    }

    public void r(eo a2, byte[] a3) {
        a.s[a2.ordinal()] = fe.r(a3);
    }
}

