/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.util.ResourceLocation
 *  net.minecraftforge.client.event.RenderGameOverlayEvent
 *  net.minecraftforge.fml.common.Mod$EventBusSubscriber
 *  net.minecraftforge.fml.common.eventhandler.SubscribeEvent
 */
package eos.moe.ancientdream.client.events;

import eos.moe.ancientdream.yaml.snakeyaml.configuration.ConfigurationSection;
import eos.moe.ancientdream.yaml.snakeyaml.configuration.file.YamlConfiguration;
import java.util.ArrayList;
import java.util.concurrent.CopyOnWriteArrayList;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

@Mod.EventBusSubscriber(modid="ancientdream")
public class GameOverlyRenderer {
    private static final ResourceLocation LEADER = new ResourceLocation("ancientdream", "gui/team/hud_leader.png");
    private static final ResourceLocation MEMBER = new ResourceLocation("ancientdream", "gui/team/hud_member.png");
    private static final ResourceLocation HEALTH = new ResourceLocation("ancientdream", "gui/team/health.png");
    private static final ResourceLocation MANA = new ResourceLocation("ancientdream", "gui/team/mana.png");
    private static final ResourceLocation HEAD = new ResourceLocation("ancientdream", "gui/team/head.png");
    public static CopyOnWriteArrayList<PlayerData> list = new CopyOnWriteArrayList();

    @SubscribeEvent
    public static void on(RenderGameOverlayEvent e) {
    }

    public static void loadData(YamlConfiguration yaml) {
        ConfigurationSection p = yaml.getConfigurationSection("p");
        ArrayList<PlayerData> list = new ArrayList<PlayerData>();
        if (p != null) {
            for (String key : p.getKeys(false)) {
                list.add(new PlayerData(p.getConfigurationSection(key)));
            }
        }
        GameOverlyRenderer.list.clear();
        GameOverlyRenderer.list.addAll(list);
    }

    public static class PlayerData {
        private String name;
        private float health;
        private float maxHealth;
        private float mana;
        private float maxMana;
        private float h;
        private float m;
        private String head;

        public PlayerData(ConfigurationSection section) {
            this.name = section.getString("n", "");
            this.health = (float)section.getDouble("h");
            this.maxHealth = (float)section.getDouble("mh");
            this.mana = (float)section.getDouble("m");
            this.maxMana = (float)section.getDouble("mm");
            this.head = section.getString("d", "").replace("\u00a7", "&");
            if (this.head.isEmpty()) {
                this.head = "gui/team/head.png";
            }
            this.h = this.health / this.maxHealth;
            this.m = this.mana / this.maxMana;
        }
    }
}

