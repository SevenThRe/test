/*
 * Decompiled with CFR 0.152.
 */
package eos.moe.armourers;

import eos.moe.armourers.cg;
import eos.moe.armourers.y;
import eos.moe.armourers.zm;
import java.util.ArrayList;

public class el
extends cg {
    public y m;
    private ArrayList<y> j;

    @Override
    public String y() {
        return "armourers:arrow";
    }

    @Override
    public ArrayList<y> r() {
        el a2;
        return a2.j;
    }

    public el() {
        el a2;
        el el2 = a2;
        el el3 = a2;
        el2.j = new ArrayList();
        el3.m = new zm(a2);
        el2.j.add(a2.m);
    }

    @Override
    public String r() {
        return "arrow";
    }
}

