/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  nf
 *  vg
 *  vi
 */
package net.optifine.util;

import java.util.HashMap;
import java.util.Map;

public class EntityUtils {
    private static final Map<Class, Integer> mapIdByClass = new HashMap<Class, Integer>();
    private static final Map<String, Integer> mapIdByLocation = new HashMap<String, Integer>();
    private static final Map<String, Integer> mapIdByName = new HashMap<String, Integer>();

    public static int getEntityIdByClass(vg entity) {
        if (entity == null) {
            return -1;
        }
        return EntityUtils.getEntityIdByClass(entity.getClass());
    }

    public static int getEntityIdByClass(Class cls) {
        Integer id = mapIdByClass.get(cls);
        if (id == null) {
            return -1;
        }
        return id;
    }

    public static int getEntityIdByLocation(String locStr) {
        Integer id = mapIdByLocation.get(locStr);
        if (id == null) {
            return -1;
        }
        return id;
    }

    public static int getEntityIdByName(String name) {
        Integer id = mapIdByName.get(name);
        if (id == null) {
            return -1;
        }
        return id;
    }

    static {
        for (int i = 0; i < 1000; ++i) {
            nf loc;
            Class cls = vi.a((int)i);
            if (cls == null || (loc = vi.a((Class)cls)) == null) continue;
            String locStr = loc.toString();
            String name = vi.a((nf)loc);
            if (name == null) continue;
            if (mapIdByClass.containsKey(cls)) {
                Config.warn("Duplicate entity class: " + cls + ", id1: " + mapIdByClass.get(cls) + ", id2: " + i);
            }
            if (mapIdByLocation.containsKey(locStr)) {
                Config.warn("Duplicate entity location: " + locStr + ", id1: " + mapIdByLocation.get(locStr) + ", id2: " + i);
            }
            if (mapIdByName.containsKey(locStr)) {
                Config.warn("Duplicate entity name: " + name + ", id1: " + mapIdByName.get(name) + ", id2: " + i);
            }
            mapIdByClass.put(cls, i);
            mapIdByLocation.put(locStr, i);
            mapIdByName.put(name, i);
        }
    }
}

