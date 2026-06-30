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
    private String field_149172_a;
    @Shadow
    private PacketBuffer field_149171_b;

    public MixinSPacketCustomPayload() {
        MixinSPacketCustomPayload a2;
    }

    @Overwrite
    public void func_148837_a(PacketBuffer a2) {
        a.field_149172_a = a2.func_150789_c(20);
        int a3 = a2.readableBytes();
        a.field_149171_b = new PacketBuffer(a2.readBytes(a3));
    }
}

