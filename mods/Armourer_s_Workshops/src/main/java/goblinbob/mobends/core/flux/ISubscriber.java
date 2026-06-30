/*
 * Decompiled with CFR 0.152.
 */
package goblinbob.mobends.core.flux;

import goblinbob.mobends.core.flux.Subscription;
import java.util.List;

public interface ISubscriber {
    public List<Subscription<?>> getSubscriptions();

    default public void trackSubscription(Subscription<?> observer) {
        this.getSubscriptions().add(observer);
    }

    default public void removeSubscriptions() {
        for (Subscription<?> subscription : this.getSubscriptions()) {
            subscription.unsubscribe();
        }
        this.getSubscriptions().clear();
    }
}

