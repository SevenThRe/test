/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraftforge.fml.relauncher.Side
 *  net.minecraftforge.fml.relauncher.SideOnly
 */
package eos.moe.armourers;

import eos.moe.armourers.ed;
import eos.moe.armourers.el;
import eos.moe.armourers.gi;
import eos.moe.armourers.il;
import eos.moe.armourers.jj;
import eos.moe.armourers.jk;
import eos.moe.armourers.ke;
import eos.moe.armourers.ml;
import eos.moe.armourers.pl;
import eos.moe.armourers.r;
import eos.moe.armourers.re;
import eos.moe.armourers.ud;
import eos.moe.armourers.w;
import eos.moe.armourers.wk;
import eos.moe.armourers.xh;
import eos.moe.armourers.y;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

/*
 * Duplicate member names - consider using --renamedupmembers true
 */
public class vn
implements w {
    public static r k;
    public static r p;
    public static r n;
    public static r q;
    public static r f;
    public static r u;
    private LinkedHashMap<String, r> d;
    public static vn h;
    public static r a;
    public static r e;
    public static r b;
    public static r g;
    public static r z;
    public static r t;
    public static r w;
    private HashMap<String, y> r;
    public static r l;
    public static r c;
    public static r v;
    public static r s;
    public static r m;
    public static r j;

    public int r() {
        vn a2;
        return a2.d.size();
    }

    private /* synthetic */ void y() {
        vn a2;
        l = new re();
        a = new jj();
        e = new xh();
        w = new ml();
        g = new il();
        z = new pl("Sword");
        q = new pl("Shield");
        c = new gi();
        s = new pl("Pickaxe");
        p = new pl("Axe");
        j = new pl("Shovel");
        b = new pl("Hoe");
        t = new pl("Item");
        m = new jk();
        k = new ke();
        r[] rArray = new r[5];
        rArray[0] = l;
        rArray[1] = a;
        rArray[2] = e;
        rArray[3] = w;
        rArray[4] = g;
        u = new wk(rArray);
        f = new ud();
        n = new el();
        v = new ed();
        a2.r(l);
        a2.r(a);
        a2.r(e);
        a2.r(w);
        a2.r(g);
        a2.r(z);
        a2.r(q);
        a2.r(c);
        a2.r(s);
        a2.r(p);
        a2.r(j);
        a2.r(b);
        a2.r(t);
        a2.r(m);
        a2.r(k);
        a2.r(u);
        a2.r(v);
    }

    public static void r() {
        h = new vn();
    }

    @SideOnly(value=Side.CLIENT)
    public String r(y a2) {
        return new StringBuilder().insert(0, "skinPartType.").append(a2.y()).append(".name").toString();
    }

    public vn() {
        vn a2;
        vn vn2 = a2;
        vn vn3 = a2;
        vn2.d = new LinkedHashMap();
        vn3.r = new HashMap();
        vn2.y();
    }

    @Override
    public ArrayList<r> r() {
        vn a2;
        int n2;
        ArrayList<r> arrayList = new ArrayList<r>();
        int n3 = n2 = 0;
        while (n3 < a2.d.size()) {
            Object object = (String)a2.d.keySet().toArray()[n2];
            if ((object = a2.r((String)object)) != null) {
                arrayList.add((r)object);
            }
            n3 = ++n2;
        }
        return arrayList;
    }

    @Override
    public y r(String a2) {
        vn a3;
        String string;
        boolean bl;
        if (a2 == null) {
            bl = true;
            string = a2;
        } else {
            bl = false;
            string = a2;
        }
        if (bl | string.trim().isEmpty()) {
            return ((ed)vn.v).j;
        }
        return a3.r.get(a2);
    }

    @Override
    public boolean r(r a2) {
        vn a3;
        if (a2 == null) {
            return false;
        }
        if (a2.y() == null || a2.y().trim().isEmpty()) {
            return false;
        }
        if (a3.r.containsKey(a2.y())) {
            return false;
        }
        if (a2.r() == null || a2.r().size() == 0) {
            return false;
        }
        a3.d.put(a2.y(), (r)a2);
        a2 = a2.r();
        int n2 = 0;
        int n3 = n2;
        while (n3 < ((ArrayList)a2).size()) {
            y y2 = (y)((ArrayList)a2).get(n2);
            a3.r.put(y2.y(), y2);
            n3 = ++n2;
        }
        return true;
    }

    /*
     * Enabled aggressive block sorting
     */
    public y r(int a2) {
        switch (a2) {
            case 0: {
                vn a3;
                return a3.r("armourers:head.base");
            }
            case 1: {
                vn a3;
                return a3.r("armourers:chest.base");
            }
            case 2: {
                vn a3;
                return a3.r("armourers:chest.leftArm");
            }
            case 3: {
                vn a3;
                return a3.r("armourers:chest.rightArm");
            }
            case 4: {
                vn a3;
                return a3.r("armourers:legs.leftLeg");
            }
            case 5: {
                vn a3;
                return a3.r("armourers:legs.rightLeg");
            }
            case 6: {
                vn a3;
                return a3.r("armourers:legs.skirt");
            }
            case 7: {
                vn a3;
                return a3.r("armourers:feet.leftFoot");
            }
            case 8: {
                vn a3;
                return a3.r("armourers:feet.rightFoot");
            }
            case 9: {
                vn a3;
                return a3.r("armourers:sword.base");
            }
            case 10: {
                vn a3;
                return a3.r("armourers:bow.base");
            }
        }
        return null;
    }

    /*
     * Enabled aggressive block sorting
     */
    public r r(int a2) {
        switch (a2) {
            case 0: {
                vn a3;
                return a3.r("armourers:head");
            }
            case 1: {
                vn a3;
                return a3.r("armourers:chest");
            }
            case 2: {
                vn a3;
                return a3.r("armourers:legs");
            }
            case 3: {
                vn a3;
                return a3.r("armourers:legs");
            }
            case 4: {
                vn a3;
                return a3.r("armourers:feet");
            }
            case 5: {
                vn a3;
                return a3.r("armourers:sword");
            }
            case 6: {
                vn a3;
                return a3.r("armourers:bow");
            }
            case 7: {
                vn a3;
                return a3.r("armourers:bow");
            }
        }
        return null;
    }

    public String r(r a2) {
        return new StringBuilder().insert(0, "skinType.").append(a2.y()).append(".name").toString();
    }

    @Override
    public r r(String a2) {
        vn a3;
        String string;
        boolean bl;
        if (a2 == null) {
            bl = true;
            string = a2;
        } else {
            bl = false;
            string = a2;
        }
        if (bl | string.trim().isEmpty()) {
            return v;
        }
        if (((String)(a2 = ((String)a2).toLowerCase())).equals(f.y())) {
            return e;
        }
        if (((String)a2).equals(n.y())) {
            return c;
        }
        if ((a2 = a3.d.get(a2)) == null) {
            a2 = v;
        }
        return a2;
    }
}

