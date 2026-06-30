/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  nf
 *  vi
 */
package net.optifine.config;

import net.optifine.config.IObjectLocator;

public class EntityClassLocator
implements IObjectLocator {
    @Override
    public Object getObject(nf loc) {
        Class cls = vi.a((String)loc.toString());
        return cls;
    }
}

