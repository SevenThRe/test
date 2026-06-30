/*
 * Decompiled with CFR 0.152.
 */
package eos.moe.armourers;

import eos.moe.armourers.cg;
import eos.moe.armourers.r;
import eos.moe.armourers.y;
import java.util.ArrayList;

/*
 * Duplicate member names - consider using --renamedupmembers true
 */
public class wk
extends cg {
    private ArrayList<y> m;
    private r[] j;

    @Override
    public String r() {
        return "Outfit";
    }

    @Override
    public boolean y() {
        return false;
    }

    @Override
    public String y() {
        return "armourers:outfit";
    }

    public wk(r ... a2) {
        int n2;
        wk a3;
        a3.j = a2;
        wk wk2 = a3;
        a3.m = new ArrayList();
        int n3 = n2 = 0;
        while (n3 < a2.length) {
            a3.m.addAll(a2[n2++].r());
            n3 = n2;
        }
    }

    @Override
    public ArrayList<y> r() {
        wk a2;
        return a2.m;
    }
}

