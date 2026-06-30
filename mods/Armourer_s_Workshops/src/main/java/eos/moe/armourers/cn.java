/*
 * Decompiled with CFR 0.152.
 */
package eos.moe.armourers;

import eos.moe.armourers.fm;
import java.awt.Point;

/*
 * Duplicate member names - consider using --renamedupmembers true
 */
public abstract class cn {
    public cn() {
        cn a2;
    }

    public void r() {
        cn a2;
        cn cn2 = a2;
        cn2.r(cn.r(cn2.r()));
    }

    public static Point r(String a2) {
        String string = a2;
        String string2 = string.split(",")[0];
        int n2 = (int)fm.r(string2);
        int n3 = (int)fm.r(string.split(",")[1]);
        return new Point(n2, n3);
    }

    public abstract void r(Point var1);

    public abstract String r();
}

