/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.Minecraft
 *  net.minecraft.item.ItemStack
 */
package eos.moe.dragoncore;

import eos.moe.dragoncore.al;
import eos.moe.dragoncore.baa;
import eos.moe.dragoncore.dj;
import eos.moe.dragoncore.eba;
import eos.moe.dragoncore.nj;
import eos.moe.dragoncore.od;
import eos.moe.dragoncore.ui;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;
import net.minecraft.client.Minecraft;
import net.minecraft.item.ItemStack;
import org.yaml.snakeyamla.configuration.file.YamlConfiguration;

/*
 * Duplicate member names - consider using --renamedupmembers true
 */
public class hja {
    public static hja c = new hja();
    private CopyOnWriteArrayList<eba> q = new CopyOnWriteArrayList();
    private CopyOnWriteArrayList<baa> b = new CopyOnWriteArrayList();
    private Map<String, YamlConfiguration> o = new ConcurrentHashMap<String, YamlConfiguration>();
    private ItemStack y = null;
    public ui k = null;
    private baa ALLATORIxDEMO = null;

    public hja() {
        hja a2;
    }

    public eba ALLATORIxDEMO(ItemStack a2) {
        hja a3;
        if (a2.func_190926_b()) {
            return null;
        }
        String a4 = dj.ALLATORIxDEMO(a2, false, false);
        for (eba a5 : a3.q) {
            if (!a5.ALLATORIxDEMO() || !al.ALLATORIxDEMO(a5.k, a4)) continue;
            return a5;
        }
        return null;
    }

    public boolean ALLATORIxDEMO(String a2) {
        hja a3;
        return a3.ALLATORIxDEMO(a2, nj.d);
    }

    public boolean ALLATORIxDEMO(String a2, nj a3) {
        hja a4;
        if (a4.k != null) {
            a4.k.ba = a2;
            boolean a5 = a4.k.runGuiAction(a3);
            a4.k.ba = "";
            return a5;
        }
        return false;
    }

    public boolean ALLATORIxDEMO() {
        hja a2;
        if (a2.k != null) {
            return a2.k.runGuiAction(nj.u);
        }
        return false;
    }

    public ui ALLATORIxDEMO(ItemStack a2) {
        hja a3;
        if (a2.func_190926_b() || !ItemStack.func_77989_b((ItemStack)a2, (ItemStack)a3.y) || a3.ALLATORIxDEMO != null && !a3.ALLATORIxDEMO.ALLATORIxDEMO()) {
            if (a3.k != null) {
                a3.k.onGuiClosed1();
            }
            a3.k = null;
            a3.y = a2;
            a3.ALLATORIxDEMO = null;
        }
        if (a3.k != null) {
            return a3.k;
        }
        if (a3.y.func_190926_b()) {
            return null;
        }
        String a4 = dj.ALLATORIxDEMO(a2, false, false);
        for (baa a5 : a3.b) {
            String a6 = baa.ALLATORIxDEMO(a5);
            if (a6 == null || !a4.contains(a6) && !al.ALLATORIxDEMO(baa.ALLATORIxDEMO(a5), a4) || !a5.ALLATORIxDEMO()) continue;
            a3.ALLATORIxDEMO = a5;
            a3.k = new ui(a4, baa.ALLATORIxDEMO(a5), a3.o, Minecraft.func_71410_x().field_71439_g.field_71069_bz, od.o);
            a3.k.initGui_();
            return a3.k;
        }
        return null;
    }

    public void ALLATORIxDEMO() {
        hja a2;
        if (a2.k != null) {
            a2.k.onGuiClosed1();
        }
        a2.k = null;
        a2.ALLATORIxDEMO = null;
    }

    public CopyOnWriteArrayList<eba> c() {
        hja a2;
        return a2.q;
    }

    public CopyOnWriteArrayList<baa> ALLATORIxDEMO() {
        hja a2;
        return a2.b;
    }

    public Map<String, YamlConfiguration> ALLATORIxDEMO() {
        hja a2;
        return a2.o;
    }
}

