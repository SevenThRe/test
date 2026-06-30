/*
 * Decompiled with CFR 0.152.
 */
package eos.moe.dragoncore;

import eos.moe.dragoncore.ad;
import eos.moe.dragoncore.ak;
import eos.moe.dragoncore.dd;
import eos.moe.dragoncore.un;
import java.io.Serializable;
import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import java.util.function.BiConsumer;
import java.util.function.Function;

public class jk<V>
implements Map<String, V>,
Serializable,
Cloneable {
    private final LinkedHashMap<String, V> q;
    private final HashMap<String, String> b;
    private final Locale o;
    private volatile transient Set<String> y;
    private volatile transient Collection<V> k;
    private volatile transient Set<Map.Entry<String, V>> ALLATORIxDEMO;

    public jk() {
        a2((Locale)null);
        jk<V> a2;
    }

    public jk(Locale a2) {
        a3(12, a2);
        jk<V> a3;
    }

    public jk(int a2) {
        a3(a2, null);
        jk<V> a3;
    }

    public jk(int a2, Locale a3) {
        jk a4;
        a4.q = new ak(a4, (int)((float)a2 / 0.75f), 0.75f);
        a4.b = jk.ALLATORIxDEMO(a2);
        a4.o = a3 != null ? a3 : Locale.getDefault();
    }

    public static <K, V> HashMap<K, V> ALLATORIxDEMO(int a2) {
        return new HashMap(jk.ALLATORIxDEMO(a2), 0.75f);
    }

    private static /* synthetic */ int ALLATORIxDEMO(int a2) {
        return (int)Math.ceil((double)a2 / 0.75);
    }

    private /* synthetic */ jk(jk<V> a2) {
        jk a3;
        a3.q = (LinkedHashMap)a2.q.clone();
        a3.b = (HashMap)a2.b.clone();
        a3.o = a2.o;
    }

    @Override
    public int size() {
        jk a2;
        return a2.q.size();
    }

    @Override
    public boolean isEmpty() {
        jk a2;
        return a2.q.isEmpty();
    }

    @Override
    public boolean containsKey(Object a2) {
        jk a3;
        if (a2 instanceof String && a3.b.containsKey(a3.c((String)a2))) {
            boolean a4 = true;
            return a4;
        }
        boolean a5 = false;
        return a5;
    }

    @Override
    public boolean containsValue(Object a2) {
        jk a3;
        return a3.q.containsValue(a2);
    }

    @Override
    public V get(Object a2) {
        jk a3;
        String a4;
        if (a2 instanceof String && (a4 = a3.b.get(a3.c((String)a2))) != null) {
            return a3.q.get(a4);
        }
        return null;
    }

    @Override
    public V getOrDefault(Object a2, V a3) {
        jk a4;
        String a5;
        if (a2 instanceof String && (a5 = a4.b.get(a4.c((String)a2))) != null) {
            return a4.q.get(a5);
        }
        return a3;
    }

    public V c(String a2, V a3) {
        jk a4;
        String a5 = a4.b.put(a4.c(a2), a2);
        V a6 = null;
        if (a5 != null && !a5.equals(a2)) {
            a6 = a4.q.remove(a5);
        }
        V a7 = a4.q.put(a2, a3);
        return a6 != null ? a6 : (V)a7;
    }

    @Override
    public void putAll(Map<? extends String, ? extends V> a2) {
        if (!a2.isEmpty()) {
            jk a3;
            a2.forEach(a3::c);
        }
    }

    public V ALLATORIxDEMO(String a2, V a3) {
        jk a4;
        String a5 = a4.b.putIfAbsent(a4.c(a2), a2);
        if (a5 != null) {
            V a6 = a4.q.get(a5);
            if (a6 != null) {
                return a6;
            }
            a2 = a5;
        }
        return a4.q.putIfAbsent(a2, a3);
    }

    public V ALLATORIxDEMO(String a2, Function<? super String, ? extends V> a3) {
        jk a4;
        String a5 = a4.b.putIfAbsent(a4.c(a2), a2);
        if (a5 != null) {
            V a6 = a4.q.get(a5);
            if (a6 != null) {
                return a6;
            }
            a2 = a5;
        }
        return a4.q.computeIfAbsent(a2, a3);
    }

    @Override
    public V remove(Object a2) {
        jk a3;
        String a4;
        if (a2 instanceof String && (a4 = a3.ALLATORIxDEMO((String)a2)) != null) {
            return a3.q.remove(a4);
        }
        return null;
    }

    @Override
    public void clear() {
        jk a2;
        a2.b.clear();
        a2.q.clear();
    }

    @Override
    public Set<String> keySet() {
        jk a2;
        dd a3 = a2.y;
        if (a3 == null) {
            a2.y = a3 = new dd(a2, a2.q.keySet());
        }
        return a3;
    }

    @Override
    public Collection<V> values() {
        jk a2;
        ad a3 = a2.k;
        if (a3 == null) {
            a2.k = a3 = new ad(a2, a2.q.values());
        }
        return a3;
    }

    @Override
    public Set<Map.Entry<String, V>> entrySet() {
        jk a2;
        un a3 = a2.ALLATORIxDEMO;
        if (a3 == null) {
            a2.ALLATORIxDEMO = a3 = new un(a2, a2.q.entrySet());
        }
        return a3;
    }

    @Override
    public void forEach(BiConsumer<? super String, ? super V> a2) {
        jk a3;
        a3.q.forEach(a2);
    }

    public jk<V> ALLATORIxDEMO() {
        jk a2;
        return new jk<V>(a2);
    }

    @Override
    public boolean equals(Object a2) {
        jk a3;
        return a3 == a2 || a3.q.equals(a2);
    }

    @Override
    public int hashCode() {
        jk a2;
        return a2.q.hashCode();
    }

    public String toString() {
        jk a2;
        return a2.q.toString();
    }

    public Locale ALLATORIxDEMO() {
        jk a2;
        return a2.o;
    }

    public String c(String a2) {
        jk a3;
        return a2.toLowerCase(a3.ALLATORIxDEMO());
    }

    public boolean ALLATORIxDEMO(Map.Entry<String, V> a2) {
        return false;
    }

    private /* synthetic */ String ALLATORIxDEMO(String a2) {
        jk a3;
        return a3.b.remove(a3.c(a2));
    }

    public static /* synthetic */ String ALLATORIxDEMO(jk a2, String a3) {
        return a2.ALLATORIxDEMO(a3);
    }

    public static /* synthetic */ HashMap ALLATORIxDEMO(jk a2) {
        return a2.b;
    }

    public static /* synthetic */ LinkedHashMap ALLATORIxDEMO(jk a2) {
        return a2.q;
    }
}

