/*
 * Decompiled with CFR 0.152.
 */
package eos.moe.armourers;

import eos.moe.armourers.cg;
import eos.moe.armourers.rh;
import eos.moe.armourers.y;
import java.util.ArrayList;

/*
 * Duplicate member names - consider using --renamedupmembers true
 */
public class ke
extends cg {
    private ArrayList<y> m;
    public y j;

    @Override
    public ArrayList<y> r() {
        ke a2;
        return a2.m;
    }

    public ke() {
        ke a2;
        ke ke2 = a2;
        ke ke3 = a2;
        ke2.m = new ArrayList();
        ke3.j = new rh(a2);
        ke2.m.add(a2.j);
    }

    @Override
    public boolean r() {
        return false;
    }

    @Override
    public String r() {
        return "advanced";
    }

    @Override
    public String y() {
        ke a2;
        return new StringBuilder().insert(0, "armourers:").append(a2.r()).toString();
    }
}

