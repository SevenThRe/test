/*
 * Decompiled with CFR 0.152.
 */
package org.yaml.snakeyamla.parser;

import org.yaml.snakeyamla.events.Event;

public interface Parser {
    public boolean checkEvent(Event.ID var1);

    public Event peekEvent();

    public Event getEvent();
}

