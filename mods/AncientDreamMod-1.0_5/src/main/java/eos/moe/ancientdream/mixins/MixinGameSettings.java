/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.settings.GameSettings
 *  net.minecraft.util.SoundCategory
 */
package eos.moe.ancientdream.mixins;

import com.locydragon.mod.abf.AudioBuffer;
import net.minecraft.client.settings.GameSettings;
import net.minecraft.util.SoundCategory;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(value={GameSettings.class})
public class MixinGameSettings {
    @Inject(method={"setSoundLevel"}, at={@At(value="RETURN")})
    private void mixin_setSoundLevel(SoundCategory category, float volume, CallbackInfo ci) {
        if (category == SoundCategory.MUSIC) {
            AudioBuffer.Volume(volume);
        }
    }
}

