/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  ain
 *  nf
 */
package net.optifine.config;

import net.optifine.config.IObjectLocator;

public class ItemLocator
implements IObjectLocator {
    @Override
    public Object getObject(nf loc) {
        ain item = ain.b((String)loc.toString());
        return item;
    }
}

