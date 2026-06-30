/*
 * Decompiled with CFR 0.152.
 */
package org.yaml.snakeyamla.events;

import org.yaml.snakeyamla.error.Mark;
import org.yaml.snakeyamla.events.Event;

public abstract class NodeEvent
extends Event {
    private final String anchor;

    public NodeEvent(String anchor, Mark startMark, Mark endMark) {
        super(startMark, endMark);
        this.anchor = anchor;
    }

    public String getAnchor() {
        return this.anchor;
    }

    @Override
    protected String getArguments() {
        return "anchor=" + this.anchor;
    }
}

