/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.item.ItemStack
 *  net.minecraft.nbt.NBTTagCompound
 *  net.minecraft.util.Tuple
 */
package eos.moe.armourers;

import eos.moe.armourers.nf;
import eos.moe.armourers.rg;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.Tuple;

/*
 * Duplicate member names - consider using --renamedupmembers true
 */
public class af {
    public static String r;
    public static final List<String> l;
    public static String c;
    public static String v;
    public static final List<Tuple<String, Integer>> s;
    public static ConcurrentHashMap<String, rg> m;
    public static String j;

    public static List<String> r(ItemStack a2) {
        if (a2 == null || a2.func_190926_b() || !a2.func_77942_o()) {
            return new ArrayList<String>();
        }
        ArrayList<String> arrayList = new ArrayList<String>();
        NBTTagCompound nBTTagCompound = a2.func_77978_p();
        if (nBTTagCompound.func_150297_b("display", 10) && (nBTTagCompound = nBTTagCompound.func_74775_l("display")).func_150299_b("Lore") == 9 && !(nBTTagCompound = nBTTagCompound.func_150295_c("Lore", 8)).func_82582_d()) {
            int n2;
            int n3 = n2 = 0;
            while (n3 < nBTTagCompound.func_74745_c()) {
                String string = nBTTagCompound.func_150307_f(n2);
                arrayList.add(string);
                n3 = ++n2;
            }
        }
        return arrayList;
    }

    public static int r(String a2) {
        try {
            return Integer.parseInt(a2);
        }
        catch (Exception exception) {
            return 0;
        }
    }

    public static void z() {
        nf.y(r, j, v);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public static boolean r(int a2) {
        List<String> list = l;
        synchronized (list) {
            return l.get(a2).startsWith("DIR-");
        }
    }

    public static void y() {
        l.clear();
        s.clear();
        r = "";
        v = "*";
        j = "";
    }

    public af() {
        af a2;
    }

    public static String r(ItemStack a2, String a3) {
        if (a2 == null || a2.func_190926_b() || a2.func_77978_p() == null) {
            return null;
        }
        return a2.func_77978_p().func_74779_i(a3);
    }

    public static void r() {
        nf.r(r, j, v);
    }

    public static rg r(String a2) {
        for (rg rg2 : m.values()) {
            if (!rg2.r(a2)) continue;
            return rg2;
        }
        return null;
    }

    static {
        m = new ConcurrentHashMap();
        c = "";
        s = new ArrayList<Tuple<String, Integer>>();
        l = new ArrayList<String>();
    }
}

