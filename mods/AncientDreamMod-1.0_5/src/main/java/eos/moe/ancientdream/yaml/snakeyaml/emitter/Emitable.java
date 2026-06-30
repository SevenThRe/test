/*
 * Decompiled with CFR 0.152.
 */
package eos.moe.ancientdream.yaml.snakeyaml.emitter;

import eos.moe.ancientdream.yaml.snakeyaml.events.Event;
import java.io.IOException;

public interface Emitable {
    public void emit(Event var1) throws IOException;
}

