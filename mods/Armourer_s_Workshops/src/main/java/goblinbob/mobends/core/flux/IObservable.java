/*
 * Decompiled with CFR 0.152.
 */
package goblinbob.mobends.core.flux;

import goblinbob.mobends.core.flux.IObserver;
import goblinbob.mobends.core.flux.Subscription;
import java.util.Set;

public interface IObservable<T> {
    public T getValue();

    public Set<IObserver<T>> getObservers();

    default public Subscription<T> subscribe(IObserver<T> observer) {
        this.getObservers().add(observer);
        return new Subscription(this, observer);
    }

    default public void unsubscribe(IObserver<T> observer) {
        this.getObservers().remove(observer);
    }
}

