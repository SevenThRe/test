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
public class ri
extends cn {
    private Point c;
    @SerializedName(value="line")
    private String v;
    @SerializedName(value="location")
    private String s;
    @SerializedName(value="center")
    private boolean m;
    @SerializedName(value="scale")
    private float j;

    public void y(String a2) {
        a.s = a2;
    }

    public boolean r() {
        ri a2;
        return a2.m;
    }

    @Override
    public String r() {
        ri a2;
        return a2.s;
    }

    @Override
    public void r(Point a2) {
        a.c = a2;
    }

    public Point r() {
        ri a2;
        return a2.c;
    }

    public void r(String a2) {
        a.v = a2;
    }

    public String y() {
        ri a2;
        return a2.v;
    }

    public void r(boolean a2) {
        a.m = a2;
    }

    public float r() {
        ri a2;
        return a2.j;
    }

    public void r(float a2) {
        a.j = a2;
    }

    public ri() {
        ri a2;
        ri ri2 = a2;
        ri ri3 = a2;
        ri3.v = "";
        ri3.s = "0,0";
        ri2.j = 1.0f;
        ri2.m = false;
    }
}

