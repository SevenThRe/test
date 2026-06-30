/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.collect.ImmutableSet
 *  com.google.common.collect.Lists
 *  net.minecraft.client.Minecraft
 *  net.minecraft.entity.Entity
 */
package eos.moe.armourers;

import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Lists;
import eos.moe.armourers.ModelData;
import eos.moe.armourers.cj;
import eos.moe.armourers.fk;
import eos.moe.armourers.hd;
import eos.moe.armourers.on;
import eos.moe.armourers.pk;
import eos.moe.armourers.r;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;

public class zg {
    public static List<fk> r = Lists.newArrayList();
    private static Minecraft l;
    public static HashMap<String, List<fk>> c;
    public static Map<String, ModelData> v;
    private static final Set<String> s;
    public static ConcurrentHashMap<UUID, List<fk>> m;
    public static HashMap<String, String> j;

    public static List<ModelData> r(Entity a3) {
        return zg.r(a3, "any").stream().filter(a2 -> v.containsKey(a2.j)).filter(a2 -> !s.contains(v.get(a2.j).getSkinType())).map(a2 -> v.get(a2.j)).collect(Collectors.toList());
    }

    public static List<fk> r(Entity a4, String a5) {
        ArrayList<fk> arrayList;
        Object object;
        ArrayList<fk> arrayList2;
        ArrayList<fk> arrayList3 = new ArrayList<fk>();
        if (a4 == zg.l.player && pk.m.size() > 0) {
            arrayList3.addAll(pk.m.stream().map(a2 -> new fk((String)a2.getFirst())).collect(Collectors.toList()));
            arrayList2 = arrayList3;
        } else if (cj.m == a4) {
            arrayList3.addAll(cj.c.stream().map(fk::new).collect(Collectors.toList()));
            arrayList2 = arrayList3;
        } else {
            ArrayList<fk> arrayList4;
            if (m.containsKey(a4.getUniqueID())) {
                object = m.get(a4.getUniqueID());
                ArrayList<fk> arrayList5 = arrayList3;
                arrayList4 = arrayList5;
                arrayList5.addAll((Collection<fk>)object);
            } else {
                object = hd.r(a4.getDisplayName().getUnformattedText().replace(" ", ""));
                if (c.containsKey(object)) {
                    arrayList = c.get(object);
                    arrayList3.addAll(arrayList);
                }
                arrayList4 = arrayList3;
            }
            arrayList4.removeIf(Objects::isNull);
            object = on.r(a4.getUniqueID());
            if (object != null && arrayList3.stream().noneMatch(a2 -> "outfit".equalsIgnoreCase(a2.r()))) {
                arrayList = new ArrayList<fk>();
                boolean bl = arrayList.add(fk.r(((on)object).r()[0]));
                ArrayList<fk> arrayList6 = arrayList;
                arrayList.add(fk.r(((on)object).r()[1]));
                arrayList6.add(fk.r(((on)object).r()[2]));
                arrayList.add(fk.r(((on)object).r()[3]));
                arrayList6.removeIf(Objects::isNull);
                if (!arrayList.isEmpty()) {
                    if (arrayList3.stream().anyMatch(a2 -> "head".equalsIgnoreCase(a2.r()))) {
                        arrayList.removeIf(a2 -> "head".equalsIgnoreCase(a2.r()));
                    }
                    if (arrayList3.stream().anyMatch(a2 -> "chest".equalsIgnoreCase(a2.r()))) {
                        arrayList.removeIf(a2 -> "chest".equalsIgnoreCase(a2.r()));
                    }
                    if (arrayList3.stream().anyMatch(a2 -> "legs".equalsIgnoreCase(a2.r()))) {
                        arrayList.removeIf(a2 -> "legs".equalsIgnoreCase(a2.r()));
                    }
                    if (arrayList3.stream().anyMatch(a2 -> "feet".equalsIgnoreCase(a2.r()))) {
                        arrayList.removeIf(a2 -> "feet".equalsIgnoreCase(a2.r()));
                    }
                }
                arrayList3.addAll(arrayList);
            }
            arrayList2 = arrayList3;
        }
        arrayList2.removeIf(Objects::isNull);
        if (!a5.equalsIgnoreCase("any")) {
            arrayList3.removeIf(a3 -> !a5.equalsIgnoreCase(a3.r()));
        }
        arrayList3.removeIf(a2 -> a2.j.contains("\u9690\u85cf") || a2.j.equals("\u9690\u8eab"));
        object = arrayList3.iterator();
        Iterator iterator = object;
        while (iterator.hasNext()) {
            arrayList = (fk)object.next();
            iterator = object;
            ((fk)((Object)arrayList)).r();
        }
        return arrayList3;
    }

    public static boolean r(Entity a3) {
        if (cj.m == a3) {
            return cj.c.contains("\u9690\u8eab");
        }
        String string = hd.r(a3.getDisplayName().getUnformattedText().replace(" ", ""));
        if (m.containsKey(a3.getUniqueID())) {
            return ((List)m.getOrDefault(a3.getUniqueID(), new ArrayList())).stream().anyMatch(a2 -> a2.j.equalsIgnoreCase("\u9690\u8eab"));
        }
        if (c.containsKey(string)) {
            return ((List)c.getOrDefault(string, new ArrayList())).stream().anyMatch(a2 -> a2.j.equalsIgnoreCase("\u9690\u8eab"));
        }
        return false;
    }

    private static /* synthetic */ List<fk> r(on a2) {
        int n2;
        ArrayList<fk> arrayList = new ArrayList<fk>();
        int n3 = n2 = 0;
        while (n3 < 4) {
            fk fk2 = fk.r(a2.r()[n2]);
            if (fk2 != null && fk2.r() != null) {
                arrayList.add(fk2);
            }
            n3 = ++n2;
        }
        return arrayList;
    }

    static {
        m = new ConcurrentHashMap();
        c = new HashMap();
        j = new HashMap();
        v = new ConcurrentHashMap<String, ModelData>();
        l = Minecraft.getMinecraft();
        Object[] objectArray = new String[2];
        objectArray[0] = "shield";
        objectArray[1] = "item";
        s = ImmutableSet.of((Object)"sword", (Object)"shovel", (Object)"axe", (Object)"pickaxe", (Object)"hoe", (Object)"bow", (Object[])objectArray);
    }

    public static List<fk> r(Entity a2, r a3) {
        return zg.r(a2, a3.r().toLowerCase());
    }

    public zg() {
        zg a2;
    }
}

