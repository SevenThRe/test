/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  nf
 */
package net.minecraftforge.fml.common.registry;

public interface RegistryDelegate<T> {
    public T get();

    public nf name();

    public Class<T> type();
}

