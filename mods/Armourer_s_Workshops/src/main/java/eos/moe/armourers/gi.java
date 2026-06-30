/*
 * Decompiled with CFR 0.152.
 */
package eos.moe.armourers;

import eos.moe.armourers.cg;
import eos.moe.armourers.ej;
import eos.moe.armourers.og;
import eos.moe.armourers.vf;
import eos.moe.armourers.wh;
import eos.moe.armourers.y;
import java.util.ArrayList;

public class gi
extends cg {
    private ArrayList<y> j = new ArrayList();

    public gi() {
        gi a2;
        a2.j.add(new og(a2));
        a2.j.add(new vf(a2));
        a2.j.add(new ej(a2));
        a2.j.add(new wh(a2));
    }

    @Override
    public String r() {
        return "bow";
    }

    @Override
    public ArrayList<y> r() {
        gi a2;
        return a2.j;
    }

    @Override
    public String y() {
        return "armourers:bow";
    }
}

