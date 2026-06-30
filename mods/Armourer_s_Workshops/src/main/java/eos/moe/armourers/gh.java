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
import java.util.ArrayList;
import java.util.List;

/*
 * Duplicate member names - consider using --renamedupmembers true
 */
public class gh
extends cn {
    @SerializedName(value="fillpath")
    private String l;
    @SerializedName(value="emptytip")
    private List<String> c;
    @SerializedName(value="location")
    private String v;
    @SerializedName(value="emptypath")
    private String s;
    private Point m;
    @SerializedName(value="slot")
    private int j;

    @Override
    public void r(Point a2) {
        a.m = a2;
    }

    public void z(String a2) {
        a.v = a2;
    }

    public gh() {
        gh a2;
        gh gh2 = a2;
        a2.v = "0,0";
        gh2.s = "";
        gh gh3 = a2;
        gh2.c = new ArrayList<String>();
        gh2.l = "";
    }

    public String z() {
        gh a2;
        return a2.s;
    }

    public void y(String a2) {
        a.s = a2;
    }

    public int r() {
        gh a2;
        return a2.j;
    }

    public void r(String a2) {
        a.l = a2;
    }

    public List<String> r() {
        gh a2;
        return a2.c;
    }

    @Override
    public String r() {
        gh a2;
        return a2.v;
    }

    public void r(int a2) {
        a.j = a2;
    }

    public String y() {
        gh a2;
        return a2.l;
    }

    public Point r() {
        gh a2;
        return a2.m;
    }

    public boolean r(int a2, int a3) {
        gh a4;
        return a2 >= a4.m.x && a2 <= a4.m.x + 16 && a3 >= a4.m.y && a3 <= a4.m.y + 16;
    }

    public void r(List<String> a2) {
        a.c = a2;
    }
}

