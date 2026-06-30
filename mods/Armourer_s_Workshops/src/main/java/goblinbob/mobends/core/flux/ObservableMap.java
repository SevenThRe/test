/*
 * Decompiled with CFR 0.152.
 */
package goblinbob.mobends.core.flux;

import goblinbob.mobends.core.flux.ComputedDependencyHelper;
import goblinbob.mobends.core.flux.IObservable;
import goblinbob.mobends.core.flux.IObserver;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class ObservableMap<K, V>
implements IObservable<Map<K, V>> {
    private Map<K, V> map = new HashMap();
    private Set<IObserver<Map<K, V>>> observers = new HashSet<IObserver<Map<K, V>>>();

    public void put(K key, V value) {
        this.map.put(key, value);
        this.notifyObservers();
    }

    public V get(K key) {
        ComputedDependencyHelper.linkDependency(this);
        return this.map.get(key);
    }

    public Collection<V> values() {
        ComputedDependencyHelper.linkDependency(this);
        return this.map.values();
    }

    public void clear() {
        this.map.clear();
        this.notifyObservers();
    }

    private void notifyObservers() {
        for (IObserver<Map<K, V>> observer : this.observers) {
            observer.onChanged(this.map);
        }
    }

    @Override
    public Map<K, V> getValue() {
        ComputedDependencyHelper.linkDependency(this);
        return this.map;
    }

    @Override
    public Set<IObserver<Map<K, V>>> getObservers() {
        return this.observers;
    }
}

