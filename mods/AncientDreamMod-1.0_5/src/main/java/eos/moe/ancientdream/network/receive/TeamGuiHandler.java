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
        String string = buffer.func_150789_c(32768);
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
                Minecraft.func_71410_x().func_147108_a((GuiScreen)new TeamApplyGui());
            } else if (type == 0) {
                if (Minecraft.func_71410_x().field_71462_r instanceof TeamApplyGui) {
                    ((TeamApplyGui)Minecraft.func_71410_x().field_71462_r).loadData(yaml);
                }
            } else if (type == 2) {
                if (Minecraft.func_71410_x().field_71462_r instanceof TeamManagerGui) {
                    ((TeamManagerGui)Minecraft.func_71410_x().field_71462_r).loadData(yaml);
                }
            } else if (type == 3) {
                if (Minecraft.func_71410_x().field_71462_r instanceof TeamConfirmApplyGui) {
                    ((TeamConfirmApplyGui)Minecraft.func_71410_x().field_71462_r).loadData(yaml);
                }
            } else if (type == 4) {
                GameOverlyRenderer.loadData(yaml);
            } else if (type == 5) {
                Minecraft.func_71410_x().func_147108_a((GuiScreen)new TeamHeadGui((String)yaml.get("link", "")));
            }
        });
    }
}

