/*
 * Decompiled with CFR 0.152.
 */
package eos.moe.ancientdream.yaml.snakeyaml.parser;

import eos.moe.ancientdream.yaml.snakeyaml.events.Event;

public interface Parser {
    public boolean checkEvent(Event.ID var1);

    public Event peekEvent();

    public Event getEvent();
}

