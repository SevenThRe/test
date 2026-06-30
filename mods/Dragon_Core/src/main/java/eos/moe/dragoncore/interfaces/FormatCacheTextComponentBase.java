/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.util.text.ITextComponent
 */
package eos.moe.dragoncore.interfaces;

import net.minecraft.util.text.ITextComponent;

public interface FormatCacheTextComponentBase {
    public String toStringCache();

    public static FormatCacheTextComponentBase of(ITextComponent a2) {
        return (FormatCacheTextComponentBase)a2;
    }
}

