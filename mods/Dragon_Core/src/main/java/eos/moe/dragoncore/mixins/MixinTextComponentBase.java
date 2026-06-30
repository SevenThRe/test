/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.util.text.TextComponentBase
 */
package eos.moe.dragoncore.mixins;

import eos.moe.dragoncore.interfaces.FormatCacheTextComponentBase;
import net.minecraft.util.text.TextComponentBase;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(value={TextComponentBase.class})
public abstract class MixinTextComponentBase
implements FormatCacheTextComponentBase {
    private String formatCache;

    public MixinTextComponentBase() {
        MixinTextComponentBase a2;
    }

    @Shadow
    public abstract String getFormattedText();

    @Override
    public String toStringCache() {
        MixinTextComponentBase a2;
        if (a2.formatCache == null) {
            a2.formatCache = a2.getFormattedText();
        }
        return a2.formatCache;
    }
}

