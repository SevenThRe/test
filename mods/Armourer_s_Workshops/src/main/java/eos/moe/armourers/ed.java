/*
 * Decompiled with CFR 0.152.
 */
package eos.moe.armourers;

import eos.moe.armourers.cg;
import eos.moe.armourers.vh;
import eos.moe.armourers.y;
import java.util.ArrayList;

/*
 * Duplicate member names - consider using --renamedupmembers true
 */
public class ed
extends cg {
    private ArrayList<y> m;
    public y j;

    @Override
    public String y() {
        return "armourers:unknown";
    }

    @Override
    public ArrayList<y> r() {
        ed a2;
        return a2.m;
    }

    public ed() {
        ed a2;
        ed ed2 = a2;
        ed ed3 = a2;
        ed2.m = new ArrayList();
        ed3.j = new vh(a2);
        ed2.m.add(a2.j);
    }

    @Override
    public String r() {
        return "unknown";
    }

    @Override
    public boolean y() {
        return true;
    }
}

