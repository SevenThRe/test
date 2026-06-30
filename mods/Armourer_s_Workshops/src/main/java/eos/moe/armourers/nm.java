/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.gson.annotations.SerializedName
 */
package eos.moe.armourers;

import com.google.gson.annotations.SerializedName;
import eos.moe.armourers.cn;
import java.awt.Point;

/*
 * Duplicate member names - consider using --renamedupmembers true
 */
public class nm
extends cn {
    private Point s;
    @SerializedName(value="scale")
    private int m;
    @SerializedName(value="location")
    private String j;

    public Point r() {
        nm a2;
        return a2.s;
    }

    public nm() {
        nm a2;
        nm nm2 = a2;
        nm2.j = "0,0";
        nm2.m = 1;
    }

    public void r(int a2) {
        a.m = a2;
    }

    @Override
    public String r() {
        nm a2;
        return a2.j;
    }

    public void r(String a2) {
        a.j = a2;
    }

    public int r() {
        nm a2;
        return a2.m;
    }

    @Override
    public void r(Point a2) {
        a.s = a2;
    }
}

