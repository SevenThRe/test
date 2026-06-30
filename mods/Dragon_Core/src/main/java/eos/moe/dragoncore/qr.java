/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.cache.Cache
 *  com.google.common.cache.CacheBuilder
 *  com.google.common.cache.RemovalListener
 *  com.google.common.cache.RemovalNotification
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityList
 */
package eos.moe.dragoncore;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import com.google.common.cache.RemovalListener;
import com.google.common.cache.RemovalNotification;
import eos.moe.dragoncore.en;
import eos.moe.dragoncore.hv;
import eos.moe.dragoncore.kp;
import eos.moe.dragoncore.nj;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityList;
import org.yaml.snakeyamla.configuration.file.YamlConfiguration;

public class qr
implements RemovalListener<UUID, kp> {
    public static qr b = new qr();
    public Map<String, hv> o = new ConcurrentHashMap<String, hv>();
    public Map<UUID, String> y = new ConcurrentHashMap<UUID, String>();
    public Cache<UUID, kp> k;
    private Map<Class<?>, String> ALLATORIxDEMO = new HashMap();

    public qr() {
        qr a2;
        CacheBuilder a3 = CacheBuilder.newBuilder();
        a3.removalListener((RemovalListener)a2);
        a3.expireAfterAccess(1L, TimeUnit.SECONDS);
        a2.k = a3.build();
    }

    public Map<String, hv> getYamls() {
        qr a2;
        return a2.o;
    }

    public String getEntityName(Entity a2) {
        qr a3;
        if (a3.y.containsKey(a2.func_110124_au())) {
            return a3.y.get(a2.func_110124_au());
        }
        return en.ALLATORIxDEMO(a2.func_70005_c_().replace(" ", ""));
    }

    public YamlConfiguration getMatchYaml(Entity a2) {
        qr a3;
        String a4 = a3.getEntityName(a2);
        String a5 = a3.ALLATORIxDEMO.get(a2.getClass());
        if (a5 == null) {
            a5 = EntityList.func_75621_b((Entity)a2);
            a5 = a5 == null ? "unknown" : a5.toLowerCase(Locale.ROOT);
            a3.ALLATORIxDEMO.put(a2.getClass(), a5);
        }
        for (hv a6 : a3.o.values()) {
            if (!a6.ALLATORIxDEMO(a5, a4)) continue;
            return a6.k;
        }
        return null;
    }

    public void onRemoval(RemovalNotification<UUID, kp> a2) {
        ((kp)a2.getValue()).ALLATORIxDEMO(nj.o);
        ((kp)a2.getValue()).t = true;
    }

    public static String ALLATORIxDEMO(String a2) {
        return en.ALLATORIxDEMO(a2.replace(" ", ""));
    }

    public void clear() {
        qr a2;
        a2.o.clear();
        a2.k.invalidateAll();
    }
}

