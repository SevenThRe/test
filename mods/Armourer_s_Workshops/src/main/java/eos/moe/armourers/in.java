/*
 * Decompiled with CFR 0.152.
 */
package eos.moe.armourers;

import eos.moe.armourers.hh;
import eos.moe.armourers.jm;
import eos.moe.armourers.qj;
import eos.moe.armourers.xl;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;

/*
 * Duplicate member names - consider using --renamedupmembers true
 */
public class in {
    public static hh<Boolean> ta;
    private LinkedHashMap<String, Object> da;
    @Deprecated
    public static hh<Boolean> ca;
    public static hh<String> fa;
    public static hh<String> xa;
    public static hh<Boolean> ga;
    public static hh<Boolean> ua;
    public static hh<Double> oa;
    public static hh<Boolean> za;
    public static hh<Boolean> ra;
    @Deprecated
    public static hh<Boolean> na;
    public static hh<Boolean> o;
    public static hh<Boolean> y;
    public static hh<Boolean> x;
    public static hh<Boolean> i;
    public static hh<Double> k;
    public static hh<Boolean> p;
    public static hh<Boolean> n;
    private static String q;
    public static hh<Boolean> f;
    public static hh<Double> u;
    public static hh<Boolean> d;
    public static hh<Double> h;
    public static hh<Boolean> a;
    public static hh<String> e;
    public static hh<Boolean> b;
    public static hh<Boolean> g;
    public static hh<String> z;
    public static hh<Boolean> t;
    public static hh<Boolean> w;
    public static hh<Boolean> r;
    public static hh<String> l;
    public static hh<Boolean> c;
    public static hh<Integer> v;
    public static hh<Boolean> s;
    public static hh<String> m;
    public static hh<Integer> j;

    public Boolean r(String a2, Boolean a3) {
        in a4;
        if ((a2 = a4.da.get(a2)) != null) {
            return (Boolean)a2;
        }
        return a3;
    }

    public Object r(String a2, Object a3) {
        in a4;
        if ((a2 = a4.da.get(a2)) != null) {
            return a2;
        }
        return a3;
    }

    /*
     * Enabled aggressive block sorting
     */
    public void r(DataInputStream a2, int a3) throws IOException {
        int n2;
        int n3 = a2.readInt();
        int n4 = n2 = 0;
        while (n4 < n3) {
            in in2;
            byte by;
            boolean bl;
            DataInputStream dataInputStream;
            String string = null;
            if (a3 > 12) {
                DataInputStream dataInputStream2 = a2;
                dataInputStream = dataInputStream2;
                string = jm.r(dataInputStream2);
            } else {
                DataInputStream dataInputStream3 = a2;
                dataInputStream = dataInputStream3;
                string = dataInputStream3.readUTF();
            }
            byte by2 = dataInputStream.readByte();
            qj qj2 = qj.s;
            if (by2 >= 0) {
                bl = true;
                by = by2;
            } else {
                bl = false;
                by = by2;
            }
            if (!(bl & by < qj.values().length)) {
                throw new IOException(new StringBuilder().insert(0, "Error loading skin properties ").append(by2).toString());
            }
            qj2 = qj.values()[by2];
            Object object = null;
            switch (qj2) {
                case s: {
                    in a4;
                    if (a3 > 12) {
                        object = jm.r(a2);
                        in2 = a4;
                        break;
                    }
                    object = a2.readUTF();
                    in2 = a4;
                    break;
                }
                case m: {
                    in a4;
                    object = a2.readInt();
                    in2 = a4;
                    break;
                }
                case c: {
                    in a4;
                    object = a2.readDouble();
                    in2 = a4;
                    break;
                }
                case v: {
                    object = a2.readBoolean();
                }
                default: {
                    in a4;
                    in2 = a4;
                }
            }
            in2.da.put(string, object);
            n4 = ++n2;
        }
        return;
    }

    public int hashCode() {
        in a2;
        return a2.toString().hashCode();
    }

    public boolean r(String a2) {
        in a3;
        return a3.da.containsKey(a2);
    }

    static {
        l = new hh<String>("customName", "");
        e = new hh<String>("flavour", "");
        fa = new hh<String>("authorName", "");
        xa = new hh<String>("authorUUID", "");
        na = new hh<Boolean>("armourOverride", false);
        t = new hh<Boolean>("overrideModelHead", false);
        b = new hh<Boolean>("overrideModelChest", false);
        f = new hh<Boolean>("overrideModelArmLeft", false);
        n = new hh<Boolean>("overrideModelArmRight", false);
        r = new hh<Boolean>("overrideModelLegLeft", false);
        o = new hh<Boolean>("overrideModelLegRight", false);
        ca = new hh<Boolean>("armourHideOverlay", false);
        w = new hh<Boolean>("hideOverlayHead", false);
        ra = new hh<Boolean>("hideOverlayChest", false);
        c = new hh<Boolean>("hideOverlayArmLeft", false);
        d = new hh<Boolean>("hideOverlayArmRight", false);
        x = new hh<Boolean>("hideOverlayLegLeft", false);
        ua = new hh<Boolean>("hideOverlayLegRight", false);
        y = new hh<Boolean>("limitLimbs", false);
        z = new hh<String>("partIndexs", "");
        p = new hh<Boolean>("blockGlowing", false);
        g = new hh<Boolean>("blockLadder", false);
        i = new hh<Boolean>("blockNoCollision", false);
        ga = new hh<Boolean>("blockSeat", false);
        a = new hh<Boolean>("blockMultiblock", false);
        s = new hh<Boolean>("blockBed", false);
        ta = new hh<Boolean>("blockInventory", false);
        za = new hh<Boolean>("blockEnderInventory", false);
        v = new hh<Integer>("blockInventoryWidth", 9);
        j = new hh<Integer>("blockInventoryHeight", 4);
        h = new hh<Double>("wingsMaxAngle", 75.0);
        oa = new hh<Double>("wingsMinAngle", 0.0);
        u = new hh<Double>("wingsIdleSpeed", 6000.0);
        k = new hh<Double>("wingsFlyingSpeed", 350.0);
        m = new hh<String>("wingsMovmentType", xl.m.toString());
        q = "skinProps";
    }

    public void r(DataOutputStream a2) throws IOException {
        int n2;
        in a3;
        a2.writeInt(a3.da.size());
        int n3 = n2 = 0;
        while (n3 < a3.da.size()) {
            String string = (String)a3.da.keySet().toArray()[n2];
            Object object = a3.da.get(string);
            jm.r(a2, string);
            if (object instanceof String) {
                DataOutputStream dataOutputStream = a2;
                dataOutputStream.writeByte(qj.s.ordinal());
                jm.r(dataOutputStream, (String)object);
            }
            if (object instanceof Integer) {
                DataOutputStream dataOutputStream = a2;
                dataOutputStream.writeByte(qj.m.ordinal());
                dataOutputStream.writeInt((Integer)object);
            }
            if (object instanceof Double) {
                DataOutputStream dataOutputStream = a2;
                dataOutputStream.writeByte(qj.c.ordinal());
                dataOutputStream.writeDouble((Double)object);
            }
            if (object instanceof Boolean) {
                DataOutputStream dataOutputStream = a2;
                dataOutputStream.writeByte(qj.v.ordinal());
                dataOutputStream.writeBoolean((Boolean)object);
            }
            n3 = ++n2;
        }
    }

    public double r(String a2, double a3) {
        in a4;
        if ((a2 = a4.da.get(a2)) != null) {
            return (Double)a2;
        }
        return a3;
    }

    public String toString() {
        in a2;
        return new StringBuilder().insert(0, "SkinProperties [properties=").append(a2.da).append("]").toString();
    }

    public void r(String a2, Object a3) {
        in a4;
        a4.da.put(a2, a3);
    }

    public ArrayList<String> r() {
        in a2;
        int n2;
        ArrayList<String> arrayList = new ArrayList<String>();
        int n3 = n2 = 0;
        while (n3 < a2.da.size()) {
            String string = (String)a2.da.keySet().toArray()[n2];
            arrayList.add(string + ":" + a2.da.get(string));
            n3 = ++n2;
        }
        return arrayList;
    }

    public String r(String a2, String a3) {
        in a4;
        if ((a2 = a4.da.get(a2)) != null) {
            return (String)a2;
        }
        return a3;
    }

    public in() {
        in a2;
        in in2 = a2;
        in2.da = new LinkedHashMap();
    }

    public boolean equals(Object a2) {
        in a3;
        if (a3 == a2) {
            return true;
        }
        if (a2 == null) {
            return false;
        }
        if (a3.getClass() != a2.getClass()) {
            return false;
        }
        a2 = (in)a2;
        return !(a3.da == null ? ((in)a2).da != null : !a3.da.equals(((in)a2).da));
    }

    public void r(String a2) {
        in a3;
        a3.da.remove(a2);
    }

    public in(in a2) {
        in a3;
        a3.da = (LinkedHashMap)a2.da.clone();
    }

    public int r(String a2, int a3) {
        in a4;
        if ((a2 = a4.da.get(a2)) != null) {
            return (Integer)a2;
        }
        return a3;
    }
}

