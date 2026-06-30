/*
 * Decompiled with CFR 0.152.
 */
package org.yaml.snakeyamla.events;

import org.yaml.snakeyamla.error.Mark;
import org.yaml.snakeyamla.events.Event;

public final class StreamStartEvent
extends Event {
    public StreamStartEvent(Mark startMark, Mark endMark) {
        super(startMark, endMark);
    }

    @Override
    public Event.ID getEventId() {
        return Event.ID.StreamStart;
    }
}

