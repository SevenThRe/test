/*
 * Decompiled with CFR 0.152.
 */
package goblinbob.mobends.core.flux;

import goblinbob.mobends.core.flux.ComputedDependencyHelper;
import goblinbob.mobends.core.flux.IComputedExpression;
import goblinbob.mobends.core.flux.IObservable;
import goblinbob.mobends.core.flux.IObserver;
import java.util.HashSet;
import java.util.Set;

public class Computed<T>
implements IObservable<T>,
IObserver {
    private boolean dirty = true;
    private T value;
    private IComputedExpression<T> expression;
    private Set<IObserver<T>> observers;

    public Computed(IComputedExpression<T> expression) {
        this.expression = expression;
        this.observers = new HashSet<IObserver<T>>();
        ComputedDependencyHelper.dirtyComputedSet.add(this);
    }

    @Override
    public T getValue() {
        ComputedDependencyHelper.linkDependency(this);
        if (this.dirty) {
            ComputedDependencyHelper.evaluatedStack.push(this);
            T newValue = this.expression.compute();
            for (IObserver<T> observer : this.observers) {
                observer.onChanged(newValue);
            }
            this.value = newValue;
            this.dirty = false;
            ComputedDependencyHelper.evaluatedStack.pop();
        }
        return this.value;
    }

    @Override
    public Set<IObserver<T>> getObservers() {
        return this.observers;
    }

    public void onChanged(Object newValue) {
        this.dirty = true;
        ComputedDependencyHelper.dirtyComputedSet.add(this);
    }
}

