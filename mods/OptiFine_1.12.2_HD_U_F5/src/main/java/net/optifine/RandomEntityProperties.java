/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  nf
 */
package net.optifine;

import java.util.ArrayList;
import java.util.Properties;
import net.optifine.IRandomEntity;
import net.optifine.RandomEntityRule;
import net.optifine.config.ConnectedParser;

public class RandomEntityProperties {
    public String name = null;
    public String basePath = null;
    public nf[] resourceLocations = null;
    public RandomEntityRule[] rules = null;

    public RandomEntityProperties(String path, nf[] variants) {
        ConnectedParser cp = new ConnectedParser("RandomEntities");
        this.name = cp.parseName(path);
        this.basePath = cp.parseBasePath(path);
        this.resourceLocations = variants;
    }

    public RandomEntityProperties(Properties props, String path, nf baseResLoc) {
        ConnectedParser cp = new ConnectedParser("RandomEntities");
        this.name = cp.parseName(path);
        this.basePath = cp.parseBasePath(path);
        this.rules = this.parseRules(props, path, baseResLoc, cp);
    }

    public nf getTextureLocation(nf loc, IRandomEntity randomEntity) {
        if (this.rules != null) {
            for (int i = 0; i < this.rules.length; ++i) {
                RandomEntityRule rule = this.rules[i];
                if (!rule.matches(randomEntity)) continue;
                return rule.getTextureLocation(loc, randomEntity.getId());
            }
        }
        if (this.resourceLocations != null) {
            int randomId = randomEntity.getId();
            int index = randomId % this.resourceLocations.length;
            return this.resourceLocations[index];
        }
        return loc;
    }

    private RandomEntityRule[] parseRules(Properties props, String pathProps, nf baseResLoc, ConnectedParser cp) {
        ArrayList<RandomEntityRule> list = new ArrayList<RandomEntityRule>();
        int count = props.size();
        for (int i = 0; i < count; ++i) {
            RandomEntityRule rule;
            int index = i + 1;
            String valTextures = props.getProperty("textures." + index);
            if (valTextures == null) {
                valTextures = props.getProperty("skins." + index);
            }
            if (valTextures == null || !(rule = new RandomEntityRule(props, pathProps, baseResLoc, index, valTextures, cp)).isValid(pathProps)) continue;
            list.add(rule);
        }
        RandomEntityRule[] rules = list.toArray(new RandomEntityRule[list.size()]);
        return rules;
    }

    public boolean isValid(String path) {
        int i;
        if (this.resourceLocations == null && this.rules == null) {
            Config.warn("No skins specified: " + path);
            return false;
        }
        if (this.rules != null) {
            for (i = 0; i < this.rules.length; ++i) {
                RandomEntityRule rule = this.rules[i];
                if (rule.isValid(path)) continue;
                return false;
            }
        }
        if (this.resourceLocations != null) {
            for (i = 0; i < this.resourceLocations.length; ++i) {
                nf loc = this.resourceLocations[i];
                if (Config.hasResource(loc)) continue;
                Config.warn("Texture not found: " + loc.a());
                return false;
            }
        }
        return true;
    }

    public boolean isDefault() {
        if (this.rules != null) {
            return false;
        }
        return this.resourceLocations == null;
    }
}

