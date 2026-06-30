/*
 * Decompiled with CFR 0.152.
 */
package eos.moe.armourers;

import eos.moe.armourers.cg;
import eos.moe.armourers.dd;
import eos.moe.armourers.fh;
import eos.moe.armourers.i;
import eos.moe.armourers.in;
import eos.moe.armourers.jl;
import eos.moe.armourers.y;
import java.util.ArrayList;

/*
 * Duplicate member names - consider using --renamedupmembers true
 */
public class jj
extends cg {
    private ArrayList<y> j = new ArrayList();

    @Override
    public ArrayList<y> r() {
        jj a2;
        return a2.j;
    }

    @Override
    public String y() {
        return "armourers:chest";
    }

    public jj() {
        jj a2;
        a2.j.add(new fh(a2));
        a2.j.add(new jl(a2));
        a2.j.add(new dd(a2));
    }

    @Override
    public ArrayList<i<?>> y() {
        jj a2;
        ArrayList<i<?>> arrayList = super.y();
        boolean bl = arrayList.add(in.b);
        ArrayList<i<?>> arrayList2 = arrayList;
        arrayList.add(in.f);
        arrayList2.add(in.n);
        arrayList.add(in.ra);
        arrayList.add(in.c);
        arrayList.add(in.d);
        return arrayList2;
    }

    @Override
    public int r() {
        return 1;
    }

    @Override
    public boolean r(in a2, in a3) {
        if (in.b.r(a2) != in.b.r(a3)) {
            return true;
        }
        if (in.f.r(a2) != in.f.r(a3)) {
            return true;
        }
        return in.n.r(a2) != in.n.r(a3);
    }

    @Override
    public String r() {
        return "Chest";
    }
}

