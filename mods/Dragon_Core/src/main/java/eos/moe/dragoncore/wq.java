/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.collect.Sets
 *  net.minecraft.client.resources.AbstractResourcePack
 *  org.apache.logging.log4j.LogManager
 *  org.apache.logging.log4j.Logger
 */
package eos.moe.dragoncore;

import com.google.common.collect.Sets;
import eos.moe.dragoncore.ca;
import eos.moe.dragoncore.kr;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import net.minecraft.client.resources.AbstractResourcePack;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class wq
extends AbstractResourcePack {
    public static wq b = null;
    private final List<kr> o = new ArrayList<kr>();
    private static final Logger y = LogManager.getLogger();
    public static byte[] k;
    public static Set<String> ALLATORIxDEMO;

    public wq() {
        super(null);
        wq a2;
        b = a2;
        a2.reload();
    }

    public void reload() {
        wq a2;
        a2.o.clear();
        File[] a3 = ca.s.listFiles();
        if (a3 == null) {
            return;
        }
        for (File a4 : a3) {
            if (!a4.getName().startsWith("Resource") || !a4.getName().endsWith(".zip")) continue;
            a2.o.add(new kr(a4));
        }
    }

    public InputStream func_110591_a(String a2) throws IOException {
        wq a3;
        a2 = a2.replace(".png.png", ".png");
        for (kr a4 : a3.o) {
            if (a4.ALLATORIxDEMO() == null || !a4.ALLATORIxDEMO(a2)) continue;
            return a4.ALLATORIxDEMO(a2, new String(k), ALLATORIxDEMO.toArray(new String[0]));
        }
        throw new FileNotFoundException(a2);
    }

    public boolean func_110593_b(String a2) {
        wq a3;
        if (k == null) {
            return false;
        }
        a2 = a2.replace(".png.png", ".png");
        for (kr a4 : a3.o) {
            if (a4.ALLATORIxDEMO() == null || !a4.ALLATORIxDEMO(a2)) continue;
            return true;
        }
        return false;
    }

    public Set<String> func_110587_b() {
        return Sets.newHashSet((Object[])new String[]{"dragoncore"});
    }

    public String func_130077_b() {
        return "dragoncore";
    }

    public void func_110594_c(String a2) {
        y.warn("ResourcePack: ignored non-lowercase namespace: {} in {}", (Object)a2, (Object)"dragoncore");
    }

    static {
        ALLATORIxDEMO = new HashSet<String>();
        ALLATORIxDEMO.add("minecraf");
    }
}

