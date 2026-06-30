/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraftforge.fml.relauncher.Side
 *  net.minecraftforge.fml.relauncher.SideOnly
 */
package eos.moe.armourers;

import eos.moe.armourers.in;
import eos.moe.armourers.kf;
import eos.moe.armourers.nl;
import eos.moe.armourers.q;
import eos.moe.armourers.r;
import eos.moe.armourers.s;
import eos.moe.armourers.ti;
import java.util.ArrayList;
import java.util.Arrays;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

/*
 * Duplicate member names - consider using --renamedupmembers true
 */
public class yl
implements s {
    private int[] z;
    private r t;
    @SideOnly(value=Side.CLIENT)
    public ti w;
    public int r;
    private ArrayList<kf> l;
    private int[] c;
    private int[] v;
    private int s;
    private in m;
    private int[] j;

    public void r(int[] a2, int[] a3, int[] a4) {
        yl a5;
        yl yl2 = a5;
        a5.z = a2;
        yl2.c = a3;
        yl2.j = a4;
    }

    public in r() {
        yl a2;
        return a2.m;
    }

    public yl(in a22, r a3, int[] a4, ArrayList<kf> a5) {
        yl a6;
        yl yl2 = a6;
        yl yl3 = a6;
        yl yl4 = a6;
        yl yl5 = a6;
        yl5.s = 0;
        yl5.r = -1;
        yl4.z = new int[10];
        yl4.c = new int[10];
        yl3.j = new int[10];
        yl3.m = a22;
        yl2.t = a3;
        yl2.v = null;
        if (a4 != null) {
            boolean bl;
            block4: {
                boolean a22 = false;
                int n2 = a3 = 0;
                while (n2 < nl.j) {
                    if (a4[a3] >>> 16 != 255) {
                        bl = a22 = true;
                        break block4;
                    }
                    n2 = ++a3;
                }
                bl = a22;
            }
            if (bl) {
                a6.v = a4;
            }
        }
        a6.l = a5;
    }

    public int[] r(int a2) {
        yl a3;
        int[] nArray = new int[3];
        nArray[0] = a3.z[a2];
        nArray[1] = a3.c[a2];
        nArray[2] = a3.j[a2];
        return nArray;
    }

    public String toString() {
        yl a2;
        String string = new StringBuilder().insert(0, "Skin [properties=").append(a2.m).append(", type=").append(a2.t.r().toUpperCase()).toString();
        if (a2.v != null) {
            string = new StringBuilder().insert(0, string).append(", paintData=").append(Arrays.hashCode(a2.v)).toString();
        }
        string = new StringBuilder().insert(0, string).append("]").toString();
        return string;
    }

    public boolean equals(Object a2) {
        yl a3;
        if (a3 == a2) {
            return true;
        }
        if (a2 == null) {
            return false;
        }
        if (a3.getClass() != a2.getClass()) {
            return false;
        }
        return ((yl)a2).s == a3.s;
    }

    public int hashCode() {
        yl a2;
        if (a2.s == 0) {
            int n2;
            String string = a2.toString();
            int n3 = n2 = 0;
            while (n3 < a2.l.size()) {
                String string2 = a2.l.get(n2).toString();
                string = new StringBuilder().insert(0, string).append(string2).toString();
                n3 = ++n2;
            }
            a2.s = string.hashCode();
        }
        return a2.s;
    }

    @Override
    public r r() {
        yl a2;
        return a2.t;
    }

    @Override
    public ArrayList<q> r() {
        yl a2;
        return new ArrayList<q>(a2.l);
    }

    public boolean r() {
        yl a2;
        return a2.v != null;
    }

    public int y() {
        yl a2;
        return a2.l.size();
    }

    public ArrayList<kf> y() {
        yl a2;
        return a2.l;
    }

    public int r() {
        yl a2;
        if (a2.s == 0) {
            a2.s = a2.hashCode();
        }
        return a2.s;
    }

    public int[] r() {
        yl a2;
        return a2.v;
    }

    @SideOnly(value=Side.CLIENT)
    public void r() {
        yl a2;
        int n2;
        int n3 = n2 = 0;
        while (n3 < a2.l.size()) {
            kf kf2 = a2.l.get(n2);
            kf2.r().cleanUpDisplayLists();
            n3 = ++n2;
        }
        if (a2.r()) {
            a2.w.deleteGlTexture();
        }
    }
}

