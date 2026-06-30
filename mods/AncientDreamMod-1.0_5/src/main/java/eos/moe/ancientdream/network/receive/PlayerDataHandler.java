/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.network.PacketBuffer
 */
package eos.moe.ancientdream.network.receive;

import eos.moe.ancientdream.data.DataManager;
import eos.moe.ancientdream.network.receive.MessageHandler;
import eos.moe.ancientdream.yaml.snakeyaml.configuration.InvalidConfigurationException;
import eos.moe.ancientdream.yaml.snakeyaml.configuration.file.YamlConfiguration;
import net.minecraft.network.PacketBuffer;

public class PlayerDataHandler
implements MessageHandler {
    @Override
    public void readBuffer(PacketBuffer buffer) {
        String key = buffer.readString(32768);
        String yamlStr = buffer.readString(32768);
        YamlConfiguration yaml = new YamlConfiguration();
        try {
            yaml.loadFromString(yamlStr);
            this.run(() -> DataManager.playerData.updateData(key, yaml));
        }
        catch (InvalidConfigurationException e) {
            e.printStackTrace();
        }
    }
}

