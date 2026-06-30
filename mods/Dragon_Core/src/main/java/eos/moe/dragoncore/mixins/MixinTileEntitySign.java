/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.tileentity.TileEntity
 *  net.minecraft.tileentity.TileEntitySign
 *  net.minecraft.util.text.ITextComponent
 *  net.minecraft.util.text.TextComponentBase
 */
package eos.moe.dragoncore.mixins;

import eos.moe.dragoncore.interfaces.FormatCacheTextComponentBase;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntitySign;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentBase;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(value={TileEntitySign.class})
public class MixinTileEntitySign
extends TileEntity {
    @Shadow
    @Final
    public ITextComponent[] field_145915_a;

    public MixinTileEntitySign() {
        MixinTileEntitySign a2;
    }

    public double func_145833_n() {
        String a2;
        MixinTileEntitySign a3;
        if (a3.field_145915_a.length > 1 && a3.field_145915_a[1] != null && a3.field_145915_a[1] instanceof TextComponentBase && (a2 = FormatCacheTextComponentBase.of(a3.field_145915_a[1]).toStringCache()).contains("{") && a2.contains("\u00a7")) {
            return 900.0;
        }
        return super.func_145833_n();
    }
}

