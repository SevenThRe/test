/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.network.PacketBuffer
 */
package eos.moe.ancientdream.network.receive;

import eos.moe.ancientdream.data.DataManager;
import eos.moe.ancientdream.network.receive.MessageHandler;
import net.minecraft.network.PacketBuffer;

public class OutfitHandler
implements MessageHandler {
    @Override
    public void readBuffer(PacketBuffer buffer) throws Exception {
        DataManager.outfitCounts.clear();
        int i = buffer.readInt();
        for (int i1 = 0; i1 < i; ++i1) {
            String key = buffer.func_150789_c(32768);
            int count = buffer.readInt();
            DataManager.outfitCounts.put(key, count);
        }
    }
}

