/*
 * Decompiled with CFR 0.152.
 */
package org.yaml.snakeyamla.emitter;

import java.io.IOException;
import org.yaml.snakeyamla.events.Event;

public interface Emitable {
    public void emit(Event var1) throws IOException;
}

