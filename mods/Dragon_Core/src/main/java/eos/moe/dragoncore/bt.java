/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.item.ItemStack
 */
package eos.moe.dragoncore;

import eos.moe.dragoncore.m;
import eos.moe.dragoncore.p;
import eos.moe.dragoncore.v;
import eos.moe.dragoncore.wk;
import eos.moe.dragoncore.xn;
import java.util.ArrayList;
import java.util.List;
import net.minecraft.item.ItemStack;

/*
 * Duplicate member names - consider using --renamedupmembers true
 */
public class bt {
    public static final bt k = new bt(new ArrayList<v>());
    private final List<v> ALLATORIxDEMO;

    public bt(List<v> a2) {
        bt a3;
        a3.ALLATORIxDEMO = a2;
    }

    public v ALLATORIxDEMO(int a2) {
        bt a3;
        return a3.ALLATORIxDEMO(a2) ? a3.ALLATORIxDEMO.get(a2) : wk.k;
    }

    private /* synthetic */ <T> T ALLATORIxDEMO(int a2, Class<T> a3) {
        bt a4;
        v a5 = a4.ALLATORIxDEMO.get(a2);
        if (a5.getClass().equals(a3)) {
            return (T)a5;
        }
        throw new RuntimeException("MoParams: Expected parameter type of " + a3.getName() + ", " + a5.getClass().getName() + " given.");
    }

    public boolean ALLATORIxDEMO(int a2) {
        bt a3;
        return a3.ALLATORIxDEMO.size() >= a2 + 1;
    }

    public int ALLATORIxDEMO(int a2) {
        bt a3;
        return (int)a3.ALLATORIxDEMO(a2);
    }

    public int ALLATORIxDEMO(int a2, int a3) {
        bt a4;
        return a4.ALLATORIxDEMO(a2) ? (int)a4.ALLATORIxDEMO(a2) : a3;
    }

    public double ALLATORIxDEMO(int a2) {
        bt a3;
        return a3.ALLATORIxDEMO(a2).ALLATORIxDEMO();
    }

    public double ALLATORIxDEMO(int a2, double a3) {
        bt a4;
        return a4.ALLATORIxDEMO(a2) ? a4.ALLATORIxDEMO(a2).ALLATORIxDEMO() : a3;
    }

    public String ALLATORIxDEMO(int a2) {
        bt a3;
        return a3.ALLATORIxDEMO(a2).c();
    }

    public String ALLATORIxDEMO(int a2, String a3) {
        bt a4;
        return a4.ALLATORIxDEMO(a2) ? a4.ALLATORIxDEMO(a2).c() : a3;
    }

    public ItemStack ALLATORIxDEMO(int a2) {
        bt a3;
        return a3.ALLATORIxDEMO(a2).ALLATORIxDEMO();
    }

    public ItemStack ALLATORIxDEMO(int a2, ItemStack a3) {
        bt a4;
        return a4.ALLATORIxDEMO(a2) ? a4.ALLATORIxDEMO(a2).ALLATORIxDEMO() : a3;
    }

    public p ALLATORIxDEMO(int a2) {
        bt a3;
        return a3.ALLATORIxDEMO(a2) ? a3.ALLATORIxDEMO(a2).ALLATORIxDEMO() : null;
    }

    public List<v> ALLATORIxDEMO() {
        bt a2;
        return a2.ALLATORIxDEMO;
    }

    public int ALLATORIxDEMO() {
        bt a2;
        return a2.ALLATORIxDEMO.size();
    }

    public xn ALLATORIxDEMO(int a2) {
        bt a3;
        return a3.ALLATORIxDEMO(a2, xn.class);
    }

    public m ALLATORIxDEMO(int a2) {
        bt a3;
        return a3.ALLATORIxDEMO(a2, m.class);
    }
}

