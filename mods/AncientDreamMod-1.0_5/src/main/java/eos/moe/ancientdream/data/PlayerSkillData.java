/*
 * Decompiled with CFR 0.152.
 */
package eos.moe.ancientdream.data;

import eos.moe.ancientdream.yaml.snakeyaml.configuration.ConfigurationSection;

public class PlayerSkillData {
    private String key;
    private String texture;
    private long startTime;
    private long endTime;

    public PlayerSkillData(ConfigurationSection section) {
        this.key = section.getString("key");
        this.texture = "gui/skill/" + section.getString("texture", "default.png");
        int cd = section.getInt("endTime") * 1000;
        if (cd > 0) {
            this.startTime = System.currentTimeMillis();
            this.endTime = System.currentTimeMillis() + (long)cd;
        }
    }

    public String getKey() {
        return this.key;
    }

    public String getTexture() {
        return this.texture;
    }

    public long getEndTime() {
        return this.endTime;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public long getStartTime() {
        return this.startTime;
    }

    public void setTexture(String texture) {
        this.texture = texture;
    }

    public void setStartTime(long startTime) {
        this.startTime = startTime;
    }

    public void setEndTime(long endTime) {
        this.endTime = endTime;
    }
}

