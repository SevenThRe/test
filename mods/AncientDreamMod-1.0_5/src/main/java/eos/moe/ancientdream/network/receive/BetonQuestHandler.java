/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.network.PacketBuffer
 */
package eos.moe.ancientdream.network.receive;

import eos.moe.ancientdream.client.gui.betonquest.BetonQuestGui;
import eos.moe.ancientdream.client.utils.Utils;
import eos.moe.ancientdream.network.receive.MessageHandler;
import eos.moe.ancientdream.yaml.snakeyaml.configuration.file.YamlConfiguration;
import net.minecraft.network.PacketBuffer;

public class BetonQuestHandler
implements MessageHandler {
    @Override
    public void readBuffer(PacketBuffer buffer) throws Exception {
        String string = buffer.func_150789_c(32768);
        if (string.isEmpty()) {
            Utils.runSync(() -> {
                BetonQuestGui screen = Utils.getScreen(BetonQuestGui.class);
                if (screen != null) {
                    Utils.openGui(null);
                }
            });
            return;
        }
        YamlConfiguration yamlConfiguration = new YamlConfiguration();
        yamlConfiguration.loadFromString(string);
        BetonQuestGui screen = Utils.getScreen(BetonQuestGui.class);
        BetonQuestGui.Data data = new BetonQuestGui.Data(yamlConfiguration.getStringList("response"), yamlConfiguration.getStringList("options"));
        if (screen != null) {
            Utils.runSync(() -> screen.setData(data));
        } else {
            Utils.openGuiSync(new BetonQuestGui(data));
        }
    }
}

