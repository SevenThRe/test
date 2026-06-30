/*
 * Decompiled with CFR 0.152.
 */
package org.eclipse.jetty.websocket.server.pathmap;

@Deprecated
public abstract class PathSpec {
    private final String spec;

    protected PathSpec(String spec) {
        this.spec = spec;
    }

    public String getSpec() {
        return this.spec;
    }
}

