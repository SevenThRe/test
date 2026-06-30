/*
 * Decompiled with CFR 0.152.
 */
package eos.moe.armourers;

import eos.moe.armourers.cg;
import eos.moe.armourers.ek;
import eos.moe.armourers.y;
import java.util.ArrayList;

public class pl
extends cg {
    private String m;
    private ArrayList<y> j = new ArrayList();

    public pl(String a2) {
        pl a3;
        a3.j.add(new ek(a3));
        a3.m = a2;
    }

    @Override
    public ArrayList<y> r() {
        pl a2;
        return a2.j;
    }

    @Override
    public String y() {
        pl a2;
        return new StringBuilder().insert(0, "armourers:").append(a2.m.toLowerCase()).toString();
    }

    @Override
    public String r() {
        pl a2;
        return a2.m;
    }
}

