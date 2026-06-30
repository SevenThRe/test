/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.gui.FontRenderer
 */
package eos.moe.dragoncore.mixins;

import net.minecraft.client.gui.FontRenderer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(value={FontRenderer.class})
public abstract class MixinFontRenderer {
    public MixinFontRenderer() {
        MixinFontRenderer a2;
    }

    @Shadow
    protected abstract void resetStyles();

    @Inject(method={"drawString(Ljava/lang/String;FFIZ)I"}, at={@At(value="INVOKE_ASSIGN", target="Lnet/minecraft/client/gui/FontRenderer;renderString(Ljava/lang/String;FFIZ)I")})
    private /* synthetic */ void mixin_drawString(String a2, float a3, float a4, int a5, boolean a6, CallbackInfoReturnable<Integer> a7) {
        MixinFontRenderer a8;
        a8.resetStyles();
    }
}

