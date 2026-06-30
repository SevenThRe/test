/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.audio.MusicTicker
 */
package eos.moe.ancientdream.mixins;

import net.minecraft.client.audio.MusicTicker;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(value={MusicTicker.class})
public class MixinMusicTicker {
    @Inject(method={"update"}, at={@At(value="HEAD")}, cancellable=true)
    private void mixin_update(CallbackInfo ci) {
        ci.cancel();
    }
}

