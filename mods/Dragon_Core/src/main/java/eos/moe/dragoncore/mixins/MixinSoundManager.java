/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.audio.ISound
 *  net.minecraft.client.audio.SoundManager
 */
package eos.moe.dragoncore.mixins;

import eos.moe.dragoncore.am;
import java.util.UUID;
import net.minecraft.client.audio.ISound;
import net.minecraft.client.audio.SoundManager;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(value={SoundManager.class})
public class MixinSoundManager {
    public MixinSoundManager() {
        MixinSoundManager a2;
    }

    @Redirect(method={"playSound"}, at=@At(value="INVOKE", target="Ljava/util/UUID;toString()Ljava/lang/String;"))
    private /* synthetic */ String mixin_getRandomUUID(UUID a2, ISound a3) {
        if (a3 instanceof am) {
            am a4 = (am)a3;
            return a4.getKey();
        }
        return a2.toString();
    }
}

