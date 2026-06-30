/*
 * Decompiled with CFR 0.152.
 */
package org.yaml.snakeyamla.events;

import org.yaml.snakeyamla.DumperOptions;
import org.yaml.snakeyamla.error.Mark;
import org.yaml.snakeyamla.events.CollectionStartEvent;
import org.yaml.snakeyamla.events.Event;

public final class MappingStartEvent
extends CollectionStartEvent {
    public MappingStartEvent(String anchor, String tag, boolean implicit, Mark startMark, Mark endMark, DumperOptions.FlowStyle flowStyle) {
        super(anchor, tag, implicit, startMark, endMark, flowStyle);
    }

    @Deprecated
    public MappingStartEvent(String anchor, String tag, boolean implicit, Mark startMark, Mark endMark, Boolean flowStyle) {
        this(anchor, tag, implicit, startMark, endMark, DumperOptions.FlowStyle.fromBoolean(flowStyle));
    }

    @Override
    public Event.ID getEventId() {
        return Event.ID.MappingStart;
    }
}

