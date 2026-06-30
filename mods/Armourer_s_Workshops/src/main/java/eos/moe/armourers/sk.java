/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.gson.annotations.SerializedName
 */
package eos.moe.armourers;

import com.google.gson.annotations.SerializedName;
import java.util.ArrayList;
import java.util.List;

public class sk {
    @SerializedName(value="line4")
    private List<String> c;
    @SerializedName(value="line5")
    private List<String> v;
    @SerializedName(value="line3")
    private List<String> s;
    @SerializedName(value="line1")
    private List<String> m;
    @SerializedName(value="line2")
    private List<String> j;

    public sk() {
        sk a2;
        sk sk2 = a2;
        a2.m = new ArrayList<String>();
        sk2.j = new ArrayList<String>();
        a2.s = new ArrayList<String>();
        a2.c = new ArrayList<String>();
        a2.v = new ArrayList<String>();
    }

    public List<String> x() {
        sk a2;
        return a2.m;
    }

    public List<String> h() {
        sk a2;
        return a2.j;
    }

    public void x(List<String> a2) {
        a.m = a2;
    }

    public List<String> z() {
        sk a2;
        return a2.s;
    }

    public List<String> y() {
        sk a2;
        return a2.c;
    }

    public List<String> r() {
        sk a2;
        return a2.v;
    }

    public void h(List<String> a2) {
        a.j = a2;
    }

    public void z(List<String> a2) {
        a.s = a2;
    }

    public void y(List<String> a2) {
        a.v = a2;
    }

    public void r(List<String> a2) {
        a.c = a2;
    }
}

