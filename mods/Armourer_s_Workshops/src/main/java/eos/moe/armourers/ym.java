/*
 * Decompiled with CFR 0.152.
 */
package eos.moe.armourers;

import eos.moe.armourers.ad;
import eos.moe.armourers.eo;

/*
 * Duplicate member names - consider using --renamedupmembers true
 */
public class ym {
    private String w;
    private int r;
    private int l;
    private float c;
    private boolean v;
    private int s;
    private eo m;
    private float j;

    public String y() {
        ym a2;
        return a2.w;
    }

    public float y() {
        ym a2;
        return a2.j;
    }

    public boolean equals(Object a2) {
        ym a3;
        if (a3 == a2) {
            return true;
        }
        if (a2 == null) {
            return false;
        }
        if (a3.getClass() != a2.getClass()) {
            return false;
        }
        a2 = (ym)a2;
        return a3.r == ((ym)a2).r;
    }

    public int hashCode() {
        ym a2;
        int n2 = 31;
        int n3 = 1;
        n3 = n2 * n3 + a2.r;
        return n3;
    }

    public ym r(eo a2) {
        ym a3;
        a3.m = a2;
        return a3;
    }

    public int z() {
        ym a2;
        return a2.r;
    }

    public ym(int a2, int a3, String a4) {
        a5(a2, a3, false, a4);
        ym a5;
    }

    public void r(int a2) {
        a.l = a2;
    }

    public int y() {
        ym a2;
        return a2.s;
    }

    public ym r(float a2, float a3) {
        ym a4;
        a4.c = a2;
        a4.j = a3;
        return a4;
    }

    public eo r() {
        ym a2;
        return a2.m;
    }

    public int r() {
        ym a2;
        return a2.l;
    }

    public boolean r() {
        ym a2;
        return a2.v;
    }

    public String r() {
        ym a2;
        String string = new StringBuilder().insert(0, "paintType.").append("armourers_workshops".toLowerCase()).append(":").toString();
        string = new StringBuilder().insert(0, string).append(a2.w.toLowerCase()).append(".name").toString();
        return ad.r(string);
    }

    public ym(int a2, int a3, boolean a4, String a5) {
        ym a6;
        ym ym2 = a6;
        ym ym3 = a6;
        ym ym4 = a6;
        ym4.r = a2;
        ym4.s = a3;
        ym3.v = a4;
        ym3.w = a5;
        ym2.c = 0.0f;
        ym2.j = 0.0f;
    }

    public float r() {
        ym a2;
        return a2.c;
    }
}

