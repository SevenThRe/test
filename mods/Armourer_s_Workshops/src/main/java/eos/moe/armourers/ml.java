/*
 * Decompiled with CFR 0.152.
 */
package eos.moe.armourers;

import eos.moe.armourers.cg;
import eos.moe.armourers.i;
import eos.moe.armourers.in;
import eos.moe.armourers.qn;
import eos.moe.armourers.ug;
import eos.moe.armourers.y;
import java.util.ArrayList;

/*
 * Duplicate member names - consider using --renamedupmembers true
 */
public class ml
extends cg {
    private ArrayList<y> j = new ArrayList();

    @Override
    public int r() {
        return 3;
    }

    @Override
    public String y() {
        return "armourers:feet";
    }

    @Override
    public ArrayList<y> r() {
        ml a2;
        return a2.j;
    }

    public ml() {
        ml a2;
        a2.j.add(new ug(a2));
        a2.j.add(new qn(a2));
    }

    @Override
    public String r() {
        return "Feet";
    }

    @Override
    public ArrayList<i<?>> y() {
        ml a2;
        ArrayList<i<?>> arrayList = super.y();
        boolean bl = arrayList.add(in.r);
        ArrayList<i<?>> arrayList2 = arrayList;
        arrayList.add(in.o);
        arrayList2.add(in.x);
        arrayList.add(in.ua);
        return arrayList2;
    }

    @Override
    public boolean r(in a2, in a3) {
        if (in.r.r(a2) != in.r.r(a3)) {
            return true;
        }
        return in.o.r(a2) != in.o.r(a3);
    }
}

