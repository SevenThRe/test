/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.network.PacketBuffer
 *  net.minecraft.network.play.server.SPacketCustomSound
 */
package eos.moe.dragoncore.mixins;

import net.minecraft.network.PacketBuffer;
import net.minecraft.network.play.server.SPacketCustomSound;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(value={SPacketCustomSound.class})
public class MixinSPacketCustomSound {
    @Shadow
    private String soundName;

    public MixinSPacketCustomSound() {
        MixinSPacketCustomSound a2;
    }

    @Inject(method={"readPacketData"}, at={@At(value="RETURN")})
    private /* synthetic */ void mixin_readPacketData(PacketBuffer a2, CallbackInfo a3) {
        MixinSPacketCustomSound a4;
        if (a4.soundName.startsWith("https:") || a4.soundName.startsWith("http:")) {
            a4.soundName = "minecraft:" + a4.soundName;
        }
    }
}

