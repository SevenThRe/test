/*
 * Decompiled with CFR 0.152.
 */
package eos.moe.ancientdream.yaml.snakeyaml.events;

import eos.moe.ancientdream.yaml.snakeyaml.error.Mark;
import eos.moe.ancientdream.yaml.snakeyaml.events.Event;

public final class DocumentEndEvent
extends Event {
    private final boolean explicit;

    public DocumentEndEvent(Mark startMark, Mark endMark, boolean explicit) {
        super(startMark, endMark);
        this.explicit = explicit;
    }

    public boolean getExplicit() {
        return this.explicit;
    }

    @Override
    public Event.ID getEventId() {
        return Event.ID.DocumentEnd;
    }
}

