/*
 * Decompiled with CFR 0.152.
 */
package eos.moe.armourers;

import eos.moe.armourers.aj;
import eos.moe.armourers.cg;
import eos.moe.armourers.i;
import eos.moe.armourers.in;
import eos.moe.armourers.y;
import eos.moe.armourers.ze;
import java.util.ArrayList;

public class jk
extends cg {
    public y s;
    public y m;
    private ArrayList<y> j;

    @Override
    public String y() {
        return "armourers:block";
    }

    @Override
    public ArrayList<i<?>> y() {
        jk a2;
        ArrayList<i<?>> arrayList = super.y();
        boolean bl = arrayList.add(in.s);
        ArrayList<i<?>> arrayList2 = arrayList;
        arrayList.add(in.p);
        arrayList2.add(in.g);
        arrayList.add(in.a);
        arrayList.add(in.i);
        arrayList.add(in.ga);
        arrayList.add(in.ta);
        arrayList.add(in.v);
        arrayList.add(in.j);
        arrayList.add(in.za);
        return arrayList2;
    }

    @Override
    public ArrayList<y> r() {
        jk a2;
        return a2.j;
    }

    public jk() {
        jk a2;
        jk jk2 = a2;
        jk jk3 = a2;
        jk2.j = new ArrayList();
        jk3.s = new aj(a2);
        jk2.m = new ze(a2);
        jk2.j.add(a2.s);
        jk jk4 = a2;
        jk4.j.add(jk4.m);
    }

    @Override
    public String r() {
        return "block";
    }
}

