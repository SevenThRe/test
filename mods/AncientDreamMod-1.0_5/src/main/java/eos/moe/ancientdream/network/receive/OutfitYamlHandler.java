/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.network.PacketBuffer
 */
package eos.moe.ancientdream.network.receive;

import eos.moe.ancientdream.data.DataManager;
import eos.moe.ancientdream.network.receive.MessageHandler;
import eos.moe.ancientdream.yaml.snakeyaml.configuration.file.YamlConfiguration;
import net.minecraft.network.PacketBuffer;

public class OutfitYamlHandler
implements MessageHandler {
    @Override
    public void readBuffer(PacketBuffer buffer) throws Exception {
        String string = buffer.readString(32768);
        YamlConfiguration yaml = new YamlConfiguration();
        yaml.loadFromString(string);
        DataManager.outfits.clear();
        for (String key : yaml.getKeys(false)) {
            DataManager.outfits.put(key, yaml.getStringList(key));
        }
    }
}

