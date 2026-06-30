/*
 * Decompiled with CFR 0.152.
 */
package eos.moe.dragoncore;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Collection;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.function.Function;

public class ej<K, V>
implements Map<K, V>,
Serializable {
    private static final long k = 1978198479659022715L;
    private final Map<K, V> ALLATORIxDEMO;

    public ej(Map<K, V> a2) {
        ej a3;
        a3.ALLATORIxDEMO = Objects.requireNonNull(a2);
    }

    @Override
    public int size() {
        ej a2;
        return a2.ALLATORIxDEMO.size();
    }

    @Override
    public boolean isEmpty() {
        ej a2;
        return a2.ALLATORIxDEMO.isEmpty();
    }

    @Override
    public boolean containsKey(Object a2) {
        ej a3;
        return a3.ALLATORIxDEMO.containsKey(a2);
    }

    @Override
    public boolean containsValue(Object a2) {
        ej a3;
        return a3.ALLATORIxDEMO.containsValue(a2);
    }

    @Override
    public V get(Object a2) {
        ej a3;
        return a3.ALLATORIxDEMO.get(a2);
    }

    @Override
    public V put(K a2, V a3) {
        ej a4;
        return a4.ALLATORIxDEMO.put(a2, a3);
    }

    @Override
    public V remove(Object a2) {
        ej a3;
        return a3.ALLATORIxDEMO.remove(a2);
    }

    @Override
    public void putAll(Map<? extends K, ? extends V> a2) {
        ej a3;
        a3.ALLATORIxDEMO.putAll(a2);
    }

    @Override
    public void clear() {
        ej a2;
        a2.ALLATORIxDEMO.clear();
    }

    @Override
    public Set<K> keySet() {
        ej a2;
        return a2.ALLATORIxDEMO.keySet();
    }

    @Override
    public Set<Map.Entry<K, V>> entrySet() {
        ej a2;
        return a2.ALLATORIxDEMO.entrySet();
    }

    @Override
    public Collection<V> values() {
        ej a2;
        return a2.ALLATORIxDEMO.values();
    }

    @Override
    public boolean equals(Object a2) {
        ej a3;
        if (a3 == a2) {
            return true;
        }
        return a3.ALLATORIxDEMO.equals(a2);
    }

    @Override
    public int hashCode() {
        ej a2;
        return a2.ALLATORIxDEMO.hashCode();
    }

    public String toString() {
        ej a2;
        return a2.ALLATORIxDEMO.toString();
    }

    @Override
    public V getOrDefault(Object a2, V a3) {
        ej a4;
        return a4.ALLATORIxDEMO.getOrDefault(a2, a3);
    }

    @Override
    public void forEach(BiConsumer<? super K, ? super V> a2) {
        ej a3;
        a3.ALLATORIxDEMO.forEach(a2);
    }

    @Override
    public void replaceAll(BiFunction<? super K, ? super V, ? extends V> a2) {
        ej a3;
        a3.ALLATORIxDEMO.replaceAll(a2);
    }

    @Override
    public V putIfAbsent(K a2, V a3) {
        ej a4;
        return a4.ALLATORIxDEMO.putIfAbsent(a2, a3);
    }

    @Override
    public boolean remove(Object a2, Object a3) {
        ej a4;
        return a4.ALLATORIxDEMO.remove(a2, a3);
    }

    @Override
    public boolean replace(K a2, V a3, V a4) {
        ej a5;
        return a5.ALLATORIxDEMO.replace(a2, a3, a4);
    }

    @Override
    public V replace(K a2, V a3) {
        ej a4;
        return a4.ALLATORIxDEMO.replace(a2, a3);
    }

    @Override
    public V computeIfAbsent(K a2, Function<? super K, ? extends V> a3) {
        ej a4;
        return a4.ALLATORIxDEMO.computeIfAbsent((K)a2, a3);
    }

    @Override
    public V computeIfPresent(K a2, BiFunction<? super K, ? super V, ? extends V> a3) {
        ej a4;
        return a4.ALLATORIxDEMO.computeIfPresent((K)a2, (BiFunction<? super K, ? extends V, ? extends V>)a3);
    }

    @Override
    public V compute(K a2, BiFunction<? super K, ? super V, ? extends V> a3) {
        ej a4;
        return a4.ALLATORIxDEMO.compute((K)a2, (BiFunction<? super K, ? extends V, ? extends V>)a3);
    }

    @Override
    public V merge(K a2, V a3, BiFunction<? super V, ? super V, ? extends V> a4) {
        ej a5;
        return a5.ALLATORIxDEMO.merge(a2, (V)a3, (BiFunction<? extends V, ? extends V, ? extends V>)a4);
    }

    private /* synthetic */ void ALLATORIxDEMO(ObjectOutputStream a2) throws IOException {
        a2.defaultWriteObject();
    }
}

