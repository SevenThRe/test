/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  nf
 */
package net.minecraftforge.registries;

public interface IRegistryDelegate<T> {
    public T get();

    public nf name();

    public Class<T> type();
}

