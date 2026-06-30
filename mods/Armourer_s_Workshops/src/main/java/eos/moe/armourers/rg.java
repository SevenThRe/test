/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.collect.Lists
 *  com.google.gson.annotations.SerializedName
 */
package eos.moe.armourers;

import com.google.common.collect.Lists;
import com.google.gson.annotations.SerializedName;
import eos.moe.armourers.cn;
import eos.moe.armourers.gh;
import eos.moe.armourers.ko;
import eos.moe.armourers.nm;
import eos.moe.armourers.ri;
import eos.moe.armourers.sd;
import eos.moe.armourers.sk;
import java.awt.Point;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

/*
 * Duplicate member names - consider using --renamedupmembers true
 */
public class rg
extends ko {
    @SerializedName(value="width")
    private int a;
    @SerializedName(value="player")
    private sk e;
    @SerializedName(value="inventory")
    private LinkedHashMap<String, gh> b;
    @SerializedName(value="type")
    private int g;
    private Map<Integer, Point> z;
    @SerializedName(value="blur")
    private boolean t;
    @SerializedName(value="string")
    private LinkedHashMap<String, ri> w;
    @SerializedName(value="model")
    private nm r;
    public Pattern l;
    @SerializedName(value="height")
    private int c;
    @SerializedName(value="background")
    private String v;
    @SerializedName(value="enable")
    private boolean s;
    @SerializedName(value="button")
    private LinkedHashMap<String, sd> m;
    @SerializedName(value="object")
    private String j;

    public void z(int a2) {
        a.c = a2;
    }

    @Override
    public Pattern r() {
        rg a2;
        return a2.l;
    }

    public void z(LinkedHashMap<String, sd> a2) {
        a.m = a2;
    }

    public nm r() {
        rg a2;
        return a2.r;
    }

    public void r(sk a2) {
        a.e = a2;
    }

    public LinkedHashMap<String, sd> z() {
        rg a2;
        return a2.m;
    }

    @Override
    public String r() {
        rg a2;
        return a2.j;
    }

    public void y(int a2) {
        a.a = a2;
    }

    public void y(boolean a2) {
        a.t = a2;
    }

    public void r(int a2) {
        a.g = a2;
    }

    public void r(boolean a2) {
        a.s = a2;
    }

    public void r(Pattern a2) {
        a.l = a2;
    }

    public LinkedHashMap<String, gh> y() {
        rg a2;
        return a2.b;
    }

    public void r(nm a2) {
        a.r = a2;
    }

    @Override
    public int r() {
        rg a2;
        return a2.g;
    }

    public void y(String a2) {
        a.v = a2;
    }

    public LinkedHashMap<String, ri> r() {
        rg a2;
        return a2.w;
    }

    public Map<Integer, Point> r() {
        rg a2;
        return a2.z;
    }

    public void y(LinkedHashMap<String, ri> a2) {
        a.w = a2;
    }

    public String y() {
        rg a2;
        return a2.v;
    }

    public int z() {
        rg a2;
        return a2.a;
    }

    public boolean y() {
        rg a2;
        return a2.s;
    }

    public void r(Map<Integer, Point> a2) {
        a.z = a2;
    }

    public int y() {
        rg a2;
        return a2.c;
    }

    public void r(String a2) {
        a.j = a2;
    }

    public boolean r() {
        rg a2;
        return a2.t;
    }

    public void r(LinkedHashMap<String, gh> a2) {
        a.b = a2;
    }

    public void r() {
        int n2;
        List<Object> list;
        rg a4;
        rg rg2 = a4;
        rg2.j = rg2.j.replace("&", "\u00a7");
        rg2.b.forEach((a2, a3) -> a3.r());
        a4.m.forEach((a2, a3) -> a3.r());
        a4.w.forEach((a2, a3) -> a3.r());
        a4.w.forEach((a2, a3) -> {
            ri ri2 = a3;
            ri2.r(ri2.y().replace("&", "\u00a7"));
        });
        rg rg3 = a4;
        rg3.r.r();
        if (rg3.g == 3) {
            a4.l = Pattern.compile(a4.j);
        }
        if (a4.s) {
            list = Lists.newArrayList(a4.b.values());
            int n3 = n2 = 0;
            while (n3 < list.size()) {
                ((gh)list.get(n2)).r(n2++);
                n3 = n2;
            }
        }
        if (a4.j.equals("E-inventory")) {
            list = a4.e.x();
            int n4 = n2 = 0;
            while (n4 < list.size()) {
                Integer n5 = 9 + n2;
                String string = (String)list.get(n2);
                String string2 = String.valueOf(n2);
                a4.z.put(n5, cn.r(string.replace("i", string2)));
                n4 = ++n2;
            }
            list = a4.e.h();
            int n6 = n2 = 0;
            while (n6 < list.size()) {
                Integer n7 = 18 + n2;
                String string = (String)list.get(n2);
                String string3 = String.valueOf(n2);
                a4.z.put(n7, cn.r(string.replace("i", string3)));
                n6 = ++n2;
            }
            list = a4.e.z();
            int n8 = n2 = 0;
            while (n8 < list.size()) {
                Integer n9 = 27 + n2;
                String string = (String)list.get(n2);
                String string4 = String.valueOf(n2);
                a4.z.put(n9, cn.r(string.replace("i", string4)));
                n8 = ++n2;
            }
            list = a4.e.y();
            int n10 = n2 = 0;
            while (n10 < list.size()) {
                Integer n11 = 36 + n2;
                String string = (String)list.get(n2);
                String string5 = String.valueOf(n2);
                a4.z.put(n11, cn.r(string.replace("i", string5)));
                n10 = ++n2;
            }
            list = a4.e.r();
            int n12 = n2 = 0;
            while (n12 < list.size()) {
                if (n2 == 9) {
                    a4.z.put(45, cn.r(((String)list.get(n2)).replace("i", String.valueOf(n2))));
                } else {
                    a4.z.put(n2, cn.r(((String)list.get(n2)).replace("i", String.valueOf(n2))));
                }
                n12 = ++n2;
            }
        } else {
            list = a4.e.x();
            int n13 = n2 = 0;
            while (n13 < list.size()) {
                Integer n14 = n2;
                String string = (String)list.get(n2);
                String string6 = String.valueOf(n2);
                a4.z.put(n14, cn.r(string.replace("i", string6)));
                n13 = ++n2;
            }
            list = a4.e.h();
            int n15 = n2 = 0;
            while (n15 < list.size()) {
                Integer n16 = 9 + n2;
                String string = (String)list.get(n2);
                String string7 = String.valueOf(n2);
                a4.z.put(n16, cn.r(string.replace("i", string7)));
                n15 = ++n2;
            }
            list = a4.e.z();
            int n17 = n2 = 0;
            while (n17 < list.size()) {
                Integer n18 = 18 + n2;
                String string = (String)list.get(n2);
                String string8 = String.valueOf(n2);
                a4.z.put(n18, cn.r(string.replace("i", string8)));
                n17 = ++n2;
            }
            list = a4.e.y();
            int n19 = n2 = 0;
            while (n19 < list.size()) {
                Integer n20 = 27 + n2;
                String string = (String)list.get(n2);
                String string9 = String.valueOf(n2);
                a4.z.put(n20, cn.r(string.replace("i", string9)));
                n19 = ++n2;
            }
        }
    }

    public sk r() {
        rg a2;
        return a2.e;
    }

    public rg() {
        rg a2;
        rg rg2 = a2;
        rg rg3 = a2;
        rg rg4 = a2;
        rg rg5 = a2;
        a2.t = true;
        rg5.s = true;
        rg5.g = 0;
        rg4.j = "unknow";
        rg4.v = "";
        rg3.a = 0;
        rg3.c = 0;
        rg rg6 = a2;
        rg2.b = new LinkedHashMap();
        rg6.m = new LinkedHashMap();
        rg2.w = new LinkedHashMap();
        rg2.e = new sk();
        rg2.z = new HashMap<Integer, Point>();
        rg2.r = new nm();
    }
}

