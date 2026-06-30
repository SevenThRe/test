/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.Minecraft
 *  net.minecraft.network.PacketBuffer
 */
package eos.moe.ancientdream.network.receive;

import net.minecraft.client.Minecraft;
import net.minecraft.network.PacketBuffer;

public interface MessageHandler {
    public void readBuffer(PacketBuffer var1) throws Exception;

    default public void run(Runnable runnable) {
        Minecraft.func_71410_x().func_152344_a(runnable);
    }
}

