/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.network.PacketBuffer
 */
package eos.moe.ancientdream.network.receive;

import eos.moe.ancientdream.client.gui.guild.GuildApplyGui;
import eos.moe.ancientdream.client.gui.guild.GuildListGui;
import eos.moe.ancientdream.client.gui.guild.GuildManagerGui;
import eos.moe.ancientdream.client.gui.guild.GuildScienceGui;
import eos.moe.ancientdream.client.gui.guild.GuildScienceGuiRe;
import eos.moe.ancientdream.client.gui.guild.GuildSetting;
import eos.moe.ancientdream.client.gui.guild.GuildShopGui;
import eos.moe.ancientdream.client.utils.Utils;
import eos.moe.ancientdream.client.utils.yaml.YamlDeserializer;
import eos.moe.ancientdream.client.utils.yaml.YamlHelper;
import eos.moe.ancientdream.network.receive.MessageHandler;
import eos.moe.ancientdream.yaml.snakeyaml.configuration.ConfigurationSection;
import eos.moe.ancientdream.yaml.snakeyaml.configuration.file.YamlConfiguration;
import java.util.ArrayList;
import net.minecraft.network.PacketBuffer;

public class GuildHandler
implements MessageHandler {
    @Override
    public void readBuffer(PacketBuffer buffer) throws Exception {
        int type = buffer.readInt();
        switch (type) {
            case 0: {
                int page = buffer.readInt();
                YamlConfiguration yaml = new YamlConfiguration();
                yaml.loadFromString(buffer.readString(32768));
                boolean hasGuild = yaml.getBoolean("hasGuild");
                ArrayList<GuildListGui.GuildData> list = new ArrayList<GuildListGui.GuildData>();
                for (String key : yaml.getKeys(false)) {
                    if (key.equals("hasGuild")) continue;
                    list.add(YamlHelper.loadFrom(yaml.getConfigurationSection(key), GuildListGui.GuildData.class, null));
                }
                GuildListGui gui = Utils.getScreen(GuildListGui.class);
                if (gui != null) {
                    Utils.runSync(() -> gui.setList(page, list));
                    break;
                }
                Utils.openGuiSync(new GuildListGui(hasGuild, list));
                break;
            }
            case 1: {
                YamlConfiguration yaml = new YamlConfiguration();
                yaml.loadFromString(buffer.readString(32768));
                GuildManagerGui.GuildData guildData = YamlHelper.loadFrom(yaml, GuildManagerGui.GuildData.class, new YamlDeserializer(){

                    @Override
                    public <T> Object deserialize(T inst, String field, Object value) {
                        ConfigurationSection section = (ConfigurationSection)value;
                        ArrayList<GuildManagerGui.PlayerData> list = new ArrayList<GuildManagerGui.PlayerData>();
                        for (String key : section.getKeys(false)) {
                            list.add(YamlHelper.loadFrom(section.getConfigurationSection(key), GuildManagerGui.PlayerData.class, null));
                        }
                        return list;
                    }
                });
                Utils.openGuiSync(new GuildManagerGui(guildData));
                break;
            }
            case 3: {
                GuildShopGui.ShopData shopData = new GuildShopGui.ShopData(buffer);
                GuildShopGui g = Utils.getScreen(GuildShopGui.class);
                if (g != null) {
                    g.setShopData(shopData);
                    break;
                }
                Utils.openGuiSync(new GuildShopGui(shopData));
                break;
            }
            case 4: {
                YamlConfiguration yaml = new YamlConfiguration();
                yaml.loadFromString(buffer.readString(32768));
                final YamlDeserializer deserializer1 = new YamlDeserializer(){

                    @Override
                    public <T> Object deserialize(T inst, String field, Object value) {
                        return value;
                    }
                };
                YamlDeserializer deserializer = new YamlDeserializer(){

                    @Override
                    public <T> Object deserialize(T inst, String field, Object value) {
                        ConfigurationSection section = (ConfigurationSection)value;
                        ArrayList<GuildScienceGui.ScienceData> list = new ArrayList<GuildScienceGui.ScienceData>();
                        for (String key : section.getKeys(false)) {
                            list.add(YamlHelper.loadFrom(section.getConfigurationSection(key), GuildScienceGui.ScienceData.class, deserializer1));
                        }
                        return list;
                    }
                };
                GuildScienceGui.GuiData guidata = YamlHelper.loadFrom(yaml, GuildScienceGui.GuiData.class, deserializer);
                GuildScienceGui gui1 = Utils.getScreen(GuildScienceGui.class);
                if (gui1 != null) {
                    Utils.runSync(() -> gui1.setGuiData(guidata));
                    break;
                }
                Utils.openGuiSync(new GuildScienceGui(guidata));
                break;
            }
            case 5: {
                GuildSetting guildSetting = new GuildSetting(buffer.readString(32768), buffer.readString(32768));
                Utils.openGuiSync(guildSetting);
                break;
            }
            case 6: {
                YamlConfiguration yaml = new YamlConfiguration();
                yaml.loadFromString(buffer.readString(32768));
                GuildApplyGui.GuildData guildData1 = YamlHelper.loadFrom(yaml, GuildApplyGui.GuildData.class, new YamlDeserializer(){

                    @Override
                    public <T> Object deserialize(T inst, String field, Object value) {
                        ConfigurationSection section = (ConfigurationSection)value;
                        ArrayList<GuildApplyGui.PlayerData> list = new ArrayList<GuildApplyGui.PlayerData>();
                        for (String key : section.getKeys(false)) {
                            list.add(YamlHelper.loadFrom(section.getConfigurationSection(key), GuildApplyGui.PlayerData.class, null));
                        }
                        return list;
                    }
                });
                GuildApplyGui gui21 = Utils.getScreen(GuildApplyGui.class);
                if (gui21 != null) {
                    Utils.runSync(() -> gui21.setGuildData(guildData1));
                    break;
                }
                Utils.openGuiSync(new GuildApplyGui(guildData1));
                break;
            }
            case 7: {
                GuildScienceGuiRe.GuiData data = new GuildScienceGuiRe.GuiData(buffer);
                Utils.openGuiSync(new GuildScienceGuiRe(data));
            }
        }
    }
}

