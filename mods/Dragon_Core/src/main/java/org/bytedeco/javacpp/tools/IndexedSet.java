/*
 * Decompiled with CFR 0.152.
 */
package org.bytedeco.javacpp.tools;

import java.util.Iterator;
import java.util.LinkedHashMap;

class IndexedSet<E>
extends LinkedHashMap<E, Integer>
implements Iterable<E> {
    IndexedSet() {
    }

    public int index(E e2) {
        Integer i2 = (Integer)this.get(e2);
        if (i2 == null) {
            i2 = this.size();
            this.put(e2, i2);
        }
        return i2;
    }

    @Override
    public Iterator<E> iterator() {
        return this.keySet().iterator();
    }
}

