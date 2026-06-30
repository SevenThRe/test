/*
 * Decompiled with CFR 0.152.
 */
package org.yaml.snakeyamla.events;

import org.yaml.snakeyamla.error.Mark;
import org.yaml.snakeyamla.events.Event;
import org.yaml.snakeyamla.events.NodeEvent;

public final class AliasEvent
extends NodeEvent {
    public AliasEvent(String anchor, Mark startMark, Mark endMark) {
        super(anchor, startMark, endMark);
        if (anchor == null) {
            throw new NullPointerException("anchor is not specified for alias");
        }
    }

    @Override
    public Event.ID getEventId() {
        return Event.ID.Alias;
    }
}

