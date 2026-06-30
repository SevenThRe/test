/*
 * Decompiled with CFR 0.152.
 */
package org.yaml.snakeyamla.extensions.compactnotation;

import org.yaml.snakeyamla.extensions.compactnotation.CompactConstructor;

public class PackageCompactConstructor
extends CompactConstructor {
    private String packageName;

    public PackageCompactConstructor(String packageName) {
        this.packageName = packageName;
    }

    @Override
    protected Class<?> getClassForName(String name) throws ClassNotFoundException {
        if (name.indexOf(46) < 0) {
            try {
                Class<?> clazz = Class.forName(this.packageName + "." + name);
                return clazz;
            }
            catch (ClassNotFoundException classNotFoundException) {
                // empty catch block
            }
        }
        return super.getClassForName(name);
    }
}

