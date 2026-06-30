/*
 * Decompiled with CFR 0.152.
 */
package goblinbob.mobends.core.flux;

import goblinbob.mobends.core.flux.Computed;
import goblinbob.mobends.core.flux.IObservable;
import java.util.EmptyStackException;
import java.util.HashSet;
import java.util.Set;
import java.util.Stack;

public class ComputedDependencyHelper {
    public static final Stack<Computed<?>> evaluatedStack = new Stack();
    public static final Set<Computed<?>> dirtyComputedSet = new HashSet();

    public static void linkDependency(IObservable<?> observable) {
        try {
            Computed<?> top = evaluatedStack.peek();
            observable.subscribe(top);
        }
        catch (EmptyStackException emptyStackException) {
            // empty catch block
        }
    }

    public static void reevaluateDirty() {
        for (Computed<?> dirty : dirtyComputedSet) {
            dirty.getValue();
        }
        dirtyComputedSet.clear();
    }
}

