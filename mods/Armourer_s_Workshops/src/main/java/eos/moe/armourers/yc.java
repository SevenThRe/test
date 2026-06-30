/*
 * Decompiled with CFR 0.152.
 */
package eos.moe.armourers;

import eos.moe.armourers.pb;
import eos.moe.armourers.rc;
import java.util.ArrayList;
import java.util.List;

public class yc {
    private List<pb> m;
    private rc j;

    public List<pb> r() {
        yc a2;
        return a2.m;
    }

    public void r(rc a2) {
        a.j = a2;
    }

    public rc r() {
        yc a2;
        return a2.j;
    }

    public void r(List<pb> a2) {
        a.m = a2;
    }

    public yc() {
        yc a2;
        yc yc2 = a2;
        a2.m = new ArrayList<pb>();
        yc2.j = new rc();
    }
}

