/*
 * Decompiled with CFR 0.152.
 */
package eos.moe.armourers;

import eos.moe.armourers.cg;
import eos.moe.armourers.i;
import eos.moe.armourers.im;
import eos.moe.armourers.in;
import eos.moe.armourers.rj;
import eos.moe.armourers.td;
import eos.moe.armourers.y;
import java.util.ArrayList;

/*
 * Duplicate member names - consider using --renamedupmembers true
 */
public class xh
extends cg {
    private ArrayList<y> j = new ArrayList();

    @Override
    public ArrayList<i<?>> y() {
        xh a2;
        ArrayList<i<?>> arrayList = super.y();
        boolean bl = arrayList.add(in.r);
        ArrayList<i<?>> arrayList2 = arrayList;
        arrayList.add(in.o);
        arrayList2.add(in.x);
        arrayList.add(in.ua);
        arrayList.add(in.y);
        return arrayList2;
    }

    @Override
    public String y() {
        return "armourers:legs";
    }

    @Override
    public boolean r(in a2, in a3) {
        if (in.r.r(a2) != in.r.r(a3)) {
            return true;
        }
        return in.o.r(a2) != in.o.r(a3);
    }

    @Override
    public String r() {
        return "Legs";
    }

    public xh() {
        xh a2;
        a2.j.add(new td(a2));
        a2.j.add(new im(a2));
        a2.j.add(new rj(a2));
    }

    @Override
    public ArrayList<y> r() {
        xh a2;
        return a2.j;
    }

    @Override
    public int r() {
        return 2;
    }
}

