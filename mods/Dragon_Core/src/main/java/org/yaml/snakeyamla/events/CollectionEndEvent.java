/*
 * Decompiled with CFR 0.152.
 */
package org.yaml.snakeyamla.events;

import org.yaml.snakeyamla.error.Mark;
import org.yaml.snakeyamla.events.Event;

public abstract class CollectionEndEvent
extends Event {
    public CollectionEndEvent(Mark startMark, Mark endMark) {
        super(startMark, endMark);
    }
}

