/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.gui.GuiScreen
 *  net.minecraft.network.PacketBuffer
 */
package eos.moe.ancientdream.network.receive;

import eos.moe.ancientdream.client.gui.SkillMenuGui;
import eos.moe.ancientdream.network.receive.MessageHandler;
import eos.moe.ancientdream.yaml.snakeyaml.configuration.ConfigurationSection;
import eos.moe.ancientdream.yaml.snakeyaml.configuration.InvalidConfigurationException;
import eos.moe.ancientdream.yaml.snakeyaml.configuration.file.YamlConfiguration;
import java.util.ArrayList;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.network.PacketBuffer;

public class SkillMenuHandler
implements MessageHandler {
    @Override
    public void readBuffer(PacketBuffer buffer) {
        String yamlStr = buffer.readString(32768);
        YamlConfiguration yaml = new YamlConfiguration();
        try {
            yaml.loadFromString(yamlStr);
        }
        catch (InvalidConfigurationException e) {
            e.printStackTrace();
        }
        ArrayList<SkillMenuGui.SkillMenuData> list = new ArrayList<SkillMenuGui.SkillMenuData>();
        ConfigurationSection section = yaml.getConfigurationSection("Skills");
        if (section != null) {
            for (String string : section.getKeys(false)) {
                list.add(new SkillMenuGui.SkillMenuData(section.getConfigurationSection(string)));
            }
        }
        ArrayList<SkillMenuGui.SkillMenuData.TextData> list1 = new ArrayList<SkillMenuGui.SkillMenuData.TextData>();
        section = yaml.getConfigurationSection("Texts");
        if (section != null) {
            for (String key : section.getKeys(false)) {
                list1.add(new SkillMenuGui.SkillMenuData.TextData(section.getConfigurationSection(key)));
            }
        }
        double d = yaml.getDouble("x1", 314.0);
        double x2 = yaml.getDouble("x2", 314.0);
        double y1 = yaml.getDouble("y1", 215.0);
        double y2 = yaml.getDouble("y2", 234.0);
        int maxDistance = yaml.getInt("maxDistance", 500);
        this.run(() -> {
            Minecraft minecraft = Minecraft.getMinecraft();
            if (minecraft.currentScreen instanceof SkillMenuGui) {
                ((SkillMenuGui)minecraft.currentScreen).setSkills(list);
                ((SkillMenuGui)minecraft.currentScreen).setTexts(list1);
            } else {
                minecraft.displayGuiScreen((GuiScreen)new SkillMenuGui(maxDistance, list1, list, new SkillMenuGui.SkillMenuData.ButtonData(x1, y1), new SkillMenuGui.SkillMenuData.ButtonData(x2, y2)));
            }
        });
    }
}

