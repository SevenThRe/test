/*
 * Decompiled with CFR 0.152.
 */
package goblinbob.mobends.core.flux;

import goblinbob.mobends.core.flux.ComputedDependencyHelper;
import goblinbob.mobends.core.flux.IObservable;
import goblinbob.mobends.core.flux.IObserver;
import java.util.HashSet;
import java.util.Set;

public class Observable<T>
implements IObservable<T> {
    private T value;
    private Set<IObserver<T>> observers = new HashSet<IObserver<T>>();

    public Observable() {
    }

    public Observable(T value) {
        this();
        this.value = value;
    }

    public void next(T newValue) {
        for (IObserver<T> observer : this.observers) {
            observer.onChanged(newValue);
        }
        this.value = newValue;
    }

    @Override
    public T getValue() {
        ComputedDependencyHelper.linkDependency(this);
        return this.value;
    }

    @Override
    public Set<IObserver<T>> getObservers() {
        return this.observers;
    }
}

