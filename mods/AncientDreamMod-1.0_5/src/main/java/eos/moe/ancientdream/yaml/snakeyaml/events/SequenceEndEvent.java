/*
 * Decompiled with CFR 0.152.
 */
package eos.moe.ancientdream.yaml.snakeyaml.events;

import eos.moe.ancientdream.yaml.snakeyaml.error.Mark;
import eos.moe.ancientdream.yaml.snakeyaml.events.CollectionEndEvent;
import eos.moe.ancientdream.yaml.snakeyaml.events.Event;

public final class SequenceEndEvent
extends CollectionEndEvent {
    public SequenceEndEvent(Mark startMark, Mark endMark) {
        super(startMark, endMark);
    }

    @Override
    public Event.ID getEventId() {
        return Event.ID.SequenceEnd;
    }
}

