/*
 * Decompiled with CFR 0.152.
 */
package goblinbob.mobends.core.store;

import goblinbob.mobends.core.store.IMutation;

public class Store<T> {
    protected T state;

    public Store(T initialState) {
        this.state = initialState;
    }

    public void commit(IMutation<T, ?> mutation) {
        this.state = mutation.mutate(this.state);
    }

    public <P> void commit(IMutation<T, P> mutation, P payload) {
        this.state = mutation.mutate(this.state, payload);
    }
}

