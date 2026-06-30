/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.network.PacketBuffer
 *  net.minecraft.network.play.client.CPacketCustomPayload
 */
package eos.moe.dragoncore.mixins;

import net.minecraft.network.PacketBuffer;
import net.minecraft.network.play.client.CPacketCustomPayload;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(value={CPacketCustomPayload.class})
public class MixinCPacketCustomPayload {
    public MixinCPacketCustomPayload() {
        MixinCPacketCustomPayload a2;
    }

    @Redirect(method={"<init>(Ljava/lang/String;Lnet/minecraft/network/PacketBuffer;)V"}, at=@At(value="INVOKE", target="Lnet/minecraft/network/PacketBuffer;writerIndex()I"))
    private /* synthetic */ int ss(PacketBuffer a2) {
        return 0;
    }
}

