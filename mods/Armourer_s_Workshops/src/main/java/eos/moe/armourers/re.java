/*
 * Decompiled with CFR 0.152.
 */
package eos.moe.armourers;

import eos.moe.armourers.cg;
import eos.moe.armourers.ck;
import eos.moe.armourers.i;
import eos.moe.armourers.in;
import eos.moe.armourers.y;
import java.util.ArrayList;

/*
 * Duplicate member names - consider using --renamedupmembers true
 */
public class re
extends cg {
    private ArrayList<y> j = new ArrayList();

    @Override
    public String y() {
        return "armourers:head";
    }

    @Override
    public String r() {
        return "Head";
    }

    @Override
    public ArrayList<i<?>> y() {
        re a2;
        ArrayList<i<?>> arrayList;
        ArrayList<i<?>> arrayList2 = arrayList = super.y();
        arrayList.add(in.t);
        arrayList2.add(in.w);
        return arrayList2;
    }

    @Override
    public ArrayList<y> r() {
        re a2;
        return a2.j;
    }

    @Override
    public boolean r(in a2, in a3) {
        return in.t.r(a2) != in.t.r(a3);
    }

    @Override
    public int r() {
        return 0;
    }

    public re() {
        re a2;
        a2.j.add(new ck(a2));
    }
}

