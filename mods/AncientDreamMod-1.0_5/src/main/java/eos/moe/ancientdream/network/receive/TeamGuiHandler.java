/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.gui.GuiScreen
 *  net.minecraft.network.PacketBuffer
 */
package eos.moe.ancientdream.network.receive;

import eos.moe.ancientdream.client.events.GameOverlyRenderer;
import eos.moe.ancientdream.client.gui.team.TeamApplyGui;
import eos.moe.ancientdream.client.gui.team.TeamConfirmApplyGui;
import eos.moe.ancientdream.client.gui.team.TeamHeadGui;
import eos.moe.ancientdream.client.gui.team.TeamManagerGui;
import eos.moe.ancientdream.network.receive.MessageHandler;
import eos.moe.ancientdream.yaml.snakeyaml.configuration.InvalidConfigurationException;
import eos.moe.ancientdream.yaml.snakeyaml.configuration.file.YamlConfiguration;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.network.PacketBuffer;

public class TeamGuiHandler
implements MessageHandler {
    @Override
    public void readBuffer(PacketBuffer buffer) {
        String string = buffer.readString(32768);
        YamlConfiguration yaml = new YamlConfiguration();
        try {
            yaml.loadFromString(string);
        }
        catch (InvalidConfigurationException e) {
            e.printStackTrace();
        }
        int type = yaml.getInt("type");
        this.run(() -> {
            if (type == 1) {
                Minecraft.getMinecraft().displayGuiScreen((GuiScreen)new TeamApplyGui());
            } else if (type == 0) {
                if (Minecraft.getMinecraft().currentScreen instanceof TeamApplyGui) {
                    ((TeamApplyGui)Minecraft.getMinecraft().currentScreen).loadData(yaml);
                }
            } else if (type == 2) {
                if (Minecraft.getMinecraft().currentScreen instanceof TeamManagerGui) {
                    ((TeamManagerGui)Minecraft.getMinecraft().currentScreen).loadData(yaml);
                }
            } else if (type == 3) {
                if (Minecraft.getMinecraft().currentScreen instanceof TeamConfirmApplyGui) {
                    ((TeamConfirmApplyGui)Minecraft.getMinecraft().currentScreen).loadData(yaml);
                }
            } else if (type == 4) {
                GameOverlyRenderer.loadData(yaml);
            } else if (type == 5) {
                Minecraft.getMinecraft().displayGuiScreen((GuiScreen)new TeamHeadGui((String)yaml.get("link", "")));
            }
        });
    }
}

