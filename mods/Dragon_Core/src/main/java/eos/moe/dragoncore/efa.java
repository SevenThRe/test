/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.item.ItemStack
 */
package eos.moe.dragoncore;

import eos.moe.dragoncore.iga;
import java.util.Locale;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import net.minecraft.item.ItemStack;
import org.yaml.snakeyamla.configuration.ConfigurationSection;

public class efa {
    public static efa k = new efa();
    public Map<String, iga> ALLATORIxDEMO = new ConcurrentHashMap<String, iga>();

    public efa() {
        efa a2;
    }

    public iga ALLATORIxDEMO(ItemStack a2) {
        efa a3;
        if (a2.isEmpty()) {
            return null;
        }
        if (a3.ALLATORIxDEMO.size() == 0) {
            return null;
        }
        String a4 = a2.getItem().getRegistryName().getPath().toLowerCase(Locale.ROOT);
        int a5 = a2.getMetadata();
        a4 = a4 + ":" + a5;
        return a3.ALLATORIxDEMO.get(a4);
    }

    public void ALLATORIxDEMO(ConfigurationSection a2) {
        efa a3;
        iga a4 = new iga(a2);
        a3.ALLATORIxDEMO.put(a4.ALLATORIxDEMO(), new iga(a2));
    }
}

