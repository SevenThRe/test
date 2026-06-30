/*
 * Decompiled with CFR 0.152.
 */
package goblinbob.mobends.core.store;

@FunctionalInterface
public interface IMutation<T, P> {
    default public T mutate(T state) {
        return this.mutate(state, null);
    }

    public T mutate(T var1, P var2);
}

