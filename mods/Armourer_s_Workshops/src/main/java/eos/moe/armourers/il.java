/*
 * Decompiled with CFR 0.152.
 */
package eos.moe.armourers;

import eos.moe.armourers.cg;
import eos.moe.armourers.i;
import eos.moe.armourers.in;
import eos.moe.armourers.sm;
import eos.moe.armourers.vj;
import eos.moe.armourers.y;
import java.util.ArrayList;

public class il
extends cg {
    private ArrayList<y> j = new ArrayList();

    @Override
    public String r() {
        return "wings";
    }

    public il() {
        il a2;
        a2.j.add(new vj(a2));
        a2.j.add(new sm(a2));
    }

    @Override
    public String y() {
        return "armourers:wings";
    }

    @Override
    public ArrayList<y> r() {
        il a2;
        return a2.j;
    }

    @Override
    public ArrayList<i<?>> y() {
        il a2;
        ArrayList<i<?>> arrayList = super.y();
        boolean bl = arrayList.add(in.k);
        ArrayList<i<?>> arrayList2 = arrayList;
        arrayList.add(in.u);
        arrayList2.add(in.h);
        arrayList.add(in.oa);
        arrayList.add(in.m);
        return arrayList2;
    }
}

