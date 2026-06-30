/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraftforge.fml.relauncher.Side
 *  net.minecraftforge.fml.relauncher.SideOnly
 */
package eos.moe.armourers;

import eos.moe.armourers.n;
import eos.moe.armourers.oh;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

/*
 * Duplicate member names - consider using --renamedupmembers true
 */
@SideOnly(value=Side.CLIENT)
public class yi {
    private oh s;
    private int m;
    private n j;

    public boolean equals(Object a2) {
        yi a3;
        if (a3 == a2) {
            return true;
        }
        if (a2 == null) {
            return false;
        }
        if (a3.getClass() != a2.getClass()) {
            return false;
        }
        a2 = (yi)a2;
        if (a3.s == null ? ((yi)a2).s != null : !a3.s.equals(((yi)a2).s)) {
            return false;
        }
        if (a3.j == null ? ((yi)a2).j != null : !a3.j.equals(((yi)a2).j)) {
            return false;
        }
        return a3.m == ((yi)a2).m;
    }

    public n r() {
        yi a2;
        return a2.j;
    }

    public oh r() {
        yi a2;
        return a2.s;
    }

    public yi(int a2, n a3, oh a4) {
        yi a5;
        yi yi2 = a5;
        a5.m = a2;
        yi2.j = a3;
        yi2.s = a4;
    }

    public int hashCode() {
        yi a2;
        int n2 = 31;
        int n3 = 1;
        n3 = n2 * n3 + (a2.s == null ? 0 : a2.s.hashCode());
        n3 = n2 * n3 + (a2.j == null ? 0 : a2.j.hashCode());
        n3 = n2 * n3 + a2.m;
        return n3;
    }
}

