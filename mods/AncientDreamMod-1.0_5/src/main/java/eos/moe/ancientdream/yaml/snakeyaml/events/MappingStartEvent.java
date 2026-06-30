/*
 * Decompiled with CFR 0.152.
 */
package eos.moe.ancientdream.yaml.snakeyaml.events;

import eos.moe.ancientdream.yaml.snakeyaml.DumperOptions;
import eos.moe.ancientdream.yaml.snakeyaml.error.Mark;
import eos.moe.ancientdream.yaml.snakeyaml.events.CollectionStartEvent;
import eos.moe.ancientdream.yaml.snakeyaml.events.Event;

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

