/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.block.Block
 */
package eos.moe.armourers;

import eos.moe.armourers.bm;
import eos.moe.armourers.eg;
import eos.moe.armourers.ne;
import eos.moe.armourers.x;
import eos.moe.armourers.xe;
import eos.moe.armourers.yh;
import java.util.ArrayList;
import net.minecraft.block.Block;

/*
 * Duplicate member names - consider using --renamedupmembers true
 */
public class ve {
    public static ve m;
    private ArrayList<x> j;

    private /* synthetic */ void r(x a2) {
        ve a3;
        a3.j.add(a2);
        bm.r(new StringBuilder().insert(0, "Registering equipment cube - id:").append(a2.r()).append(" name:").append(a2.getClass().getSimpleName()).toString());
    }

    public boolean r(Block a2) {
        ve a3;
        int n2;
        int n3 = n2 = 0;
        while (n3 < a3.j.size()) {
            if (a3.j.get(n2).r() == a2) {
                return true;
            }
            n3 = ++n2;
        }
        return false;
    }

    public ve() {
        ve a2;
        ve ve2 = a2;
        ve2.j = new ArrayList();
    }

    public static void y() {
        m = new ve();
        m.r();
    }

    public x r(byte a2) {
        ve a3;
        if (a2 >= 0 && a2 < a3.j.size()) {
            return a3.j.get(a2);
        }
        return null;
    }

    public x r(Block a2) {
        ve a3;
        int n2;
        int n3 = n2 = 0;
        while (n3 < a3.j.size()) {
            if (a3.j.get(n2).r() == a2) {
                return a3.j.get(n2);
            }
            n3 = ++n2;
        }
        return null;
    }

    public void r() {
        ve a2;
        ve ve2 = a2;
        ve2.r(new eg());
        a2.r(new ne());
        ve2.r(new xe());
        ve2.r(new yh());
    }

    public byte r() {
        ve a2;
        return (byte)a2.j.size();
    }
}

