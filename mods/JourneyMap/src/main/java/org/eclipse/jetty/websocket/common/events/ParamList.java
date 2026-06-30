/*
 * Decompiled with CFR 0.152.
 */
package org.eclipse.jetty.websocket.common.events;

import java.util.ArrayList;

public class ParamList
extends ArrayList<Class<?>[]> {
    public void addParams(Class<?> ... paramTypes) {
        this.add(paramTypes);
    }
}

