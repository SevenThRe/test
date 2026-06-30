/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.network.PacketBuffer
 *  net.minecraft.network.play.server.SPacketCustomPayload
 */
package eos.moe.dragoncore.mixins;

import net.minecraft.network.PacketBuffer;
import net.minecraft.network.play.server.SPacketCustomPayload;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(value={SPacketCustomPayload.class})
public class MixinSPacketCustomPayload {
    @Shadow
    private String channel;
    @Shadow
    private PacketBuffer data;

    public MixinSPacketCustomPayload() {
        MixinSPacketCustomPayload a2;
    }

    @Overwrite
    public void readPacketData(PacketBuffer a2) {
        a.channel = a2.readString(20);
        int a3 = a2.readableBytes();
        a.data = new PacketBuffer(a2.readBytes(a3));
    }
}

