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
public class sd
extends cn {
    @SerializedName(value="hoverpath")
    private String g;
    @SerializedName(value="text")
    private String z;
    @SerializedName(value="presspath")
    private String t;
    @SerializedName(value="defpath")
    private String w;
    @SerializedName(value="hovertip")
    private List<String> r;
    @SerializedName(value="location")
    private String l;
    @SerializedName(value="command")
    private String c;
    @SerializedName(value="height")
    private int v;
    @SerializedName(value="width")
    private int s;
    private Point m;
    @SerializedName(value="sound")
    private String j;

    @Override
    public String r() {
        sd a2;
        return a2.l;
    }

    public int y() {
        sd a2;
        return a2.s;
    }

    public void y(int a2) {
        a.s = a2;
    }

    public String w() {
        sd a2;
        return a2.z;
    }

    public void w(String a2) {
        a.z = a2;
    }

    public void s(String a2) {
        a.c = a2;
    }

    public void x(String a2) {
        a.w = a2;
    }

    public Point r() {
        sd a2;
        return a2.m;
    }

    @Override
    public void r(Point a2) {
        a.m = a2;
    }

    public String s() {
        sd a2;
        return a2.t;
    }

    public void h(String a2) {
        a.j = a2;
    }

    public void r(int a2) {
        a.v = a2;
    }

    public boolean r(int a2, int a3) {
        sd a4;
        return a2 >= a4.m.x && a2 <= a4.m.x + a4.s && a3 >= a4.m.y && a3 <= a4.m.y + a4.v;
    }

    public void z(String a2) {
        a.l = a2;
    }

    public String x() {
        sd a2;
        return a2.w;
    }

    public List<String> r() {
        sd a2;
        return a2.r;
    }

    public String h() {
        sd a2;
        return a2.j;
    }

    public String z() {
        sd a2;
        return a2.c;
    }

    public String y() {
        sd a2;
        return a2.g;
    }

    public void r(List<String> a2) {
        a.r = a2;
    }

    public void y(String a2) {
        a.g = a2;
    }

    public void r(String a2) {
        a.t = a2;
    }

    public sd() {
        sd a2;
        sd sd2 = a2;
        sd sd3 = a2;
        sd sd4 = a2;
        sd sd5 = a2;
        a2.l = "0,0";
        sd5.s = 0;
        sd5.v = 0;
        sd4.w = "";
        sd4.g = "";
        sd3.t = "";
        sd sd6 = a2;
        sd3.r = new ArrayList<String>();
        sd3.c = "";
        sd2.j = "";
        sd2.z = "";
    }

    public int r() {
        sd a2;
        return a2.v;
    }
}

