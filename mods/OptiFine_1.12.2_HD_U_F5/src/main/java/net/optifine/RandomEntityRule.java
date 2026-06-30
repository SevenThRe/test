/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  aam
 *  ady
 *  ahs
 *  amu
 *  anh
 *  bsb
 *  et
 *  nf
 *  vg
 *  vq
 */
package net.optifine;

import java.util.Properties;
import net.optifine.IRandomEntity;
import net.optifine.RandomEntities;
import net.optifine.RandomEntity;
import net.optifine.config.ConnectedParser;
import net.optifine.config.Matches;
import net.optifine.config.NbtTagValue;
import net.optifine.config.RangeInt;
import net.optifine.config.RangeListInt;
import net.optifine.config.VillagerProfession;
import net.optifine.config.Weather;
import net.optifine.reflect.Reflector;
import net.optifine.util.ArrayUtils;
import net.optifine.util.MathUtils;

public class RandomEntityRule {
    private String pathProps = null;
    private nf baseResLoc = null;
    private int index;
    private int[] textures = null;
    private nf[] resourceLocations = null;
    private int[] weights = null;
    private anh[] biomes = null;
    private RangeListInt heights = null;
    private RangeListInt healthRange = null;
    private boolean healthPercent = false;
    private NbtTagValue nbtName = null;
    public int[] sumWeights = null;
    public int sumAllWeights = 1;
    private VillagerProfession[] professions = null;
    private ahs[] collarColors = null;
    private Boolean baby = null;
    private RangeListInt moonPhases = null;
    private RangeListInt dayTimes = null;
    private Weather[] weatherList = null;

    public RandomEntityRule(Properties props, String pathProps, nf baseResLoc, int index, String valTextures, ConnectedParser cp) {
        String healthStr;
        this.pathProps = pathProps;
        this.baseResLoc = baseResLoc;
        this.index = index;
        this.textures = cp.parseIntList(valTextures);
        this.weights = cp.parseIntList(props.getProperty("weights." + index));
        this.biomes = cp.parseBiomes(props.getProperty("biomes." + index));
        this.heights = cp.parseRangeListInt(props.getProperty("heights." + index));
        if (this.heights == null) {
            this.heights = this.parseMinMaxHeight(props, index);
        }
        if ((healthStr = props.getProperty("health." + index)) != null) {
            this.healthPercent = healthStr.contains("%");
            healthStr = healthStr.replace("%", "");
            this.healthRange = cp.parseRangeListInt(healthStr);
        }
        this.nbtName = cp.parseNbtTagValue("name", props.getProperty("name." + index));
        this.professions = cp.parseProfessions(props.getProperty("professions." + index));
        this.collarColors = cp.parseDyeColors(props.getProperty("collarColors." + index), "collar color", ConnectedParser.DYE_COLORS_INVALID);
        this.baby = cp.parseBooleanObject(props.getProperty("baby." + index));
        this.moonPhases = cp.parseRangeListInt(props.getProperty("moonPhase." + index));
        this.dayTimes = cp.parseRangeListInt(props.getProperty("dayTime." + index));
        this.weatherList = cp.parseWeather(props.getProperty("weather." + index), "weather." + index, null);
    }

    private RangeListInt parseMinMaxHeight(Properties props, int index) {
        String minHeightStr = props.getProperty("minHeight." + index);
        String maxHeightStr = props.getProperty("maxHeight." + index);
        if (minHeightStr == null && maxHeightStr == null) {
            return null;
        }
        int minHeight = 0;
        if (minHeightStr != null && (minHeight = Config.parseInt(minHeightStr, -1)) < 0) {
            Config.warn("Invalid minHeight: " + minHeightStr);
            return null;
        }
        int maxHeight = 256;
        if (maxHeightStr != null && (maxHeight = Config.parseInt(maxHeightStr, -1)) < 0) {
            Config.warn("Invalid maxHeight: " + maxHeightStr);
            return null;
        }
        if (maxHeight < 0) {
            Config.warn("Invalid minHeight, maxHeight: " + minHeightStr + ", " + maxHeightStr);
            return null;
        }
        RangeListInt list = new RangeListInt();
        list.addRange(new RangeInt(minHeight, maxHeight));
        return list;
    }

    public boolean isValid(String path) {
        if (this.textures == null || this.textures.length == 0) {
            Config.warn("Invalid skins for rule: " + this.index);
            return false;
        }
        if (this.resourceLocations != null) {
            return true;
        }
        this.resourceLocations = new nf[this.textures.length];
        boolean mcpatcher = this.pathProps.startsWith("mcpatcher/mob/");
        nf locMcp = RandomEntities.getLocationRandom(this.baseResLoc, mcpatcher);
        if (locMcp == null) {
            Config.warn("Invalid path: " + this.baseResLoc.a());
            return false;
        }
        for (int i = 0; i < this.resourceLocations.length; ++i) {
            int index = this.textures[i];
            if (index <= 1) {
                this.resourceLocations[i] = this.baseResLoc;
                continue;
            }
            nf locNew = RandomEntities.getLocationIndexed(locMcp, index);
            if (locNew == null) {
                Config.warn("Invalid path: " + this.baseResLoc.a());
                return false;
            }
            if (!Config.hasResource(locNew)) {
                Config.warn("Texture not found: " + locNew.a());
                return false;
            }
            this.resourceLocations[i] = locNew;
        }
        if (this.weights != null) {
            if (this.weights.length > this.resourceLocations.length) {
                Config.warn("More weights defined than skins, trimming weights: " + path);
                int[] weights2 = new int[this.resourceLocations.length];
                System.arraycopy(this.weights, 0, weights2, 0, weights2.length);
                this.weights = weights2;
            }
            if (this.weights.length < this.resourceLocations.length) {
                Config.warn("Less weights defined than skins, expanding weights: " + path);
                int[] weights2 = new int[this.resourceLocations.length];
                System.arraycopy(this.weights, 0, weights2, 0, this.weights.length);
                int avgWeight = MathUtils.getAverage(this.weights);
                for (int i = this.weights.length; i < weights2.length; ++i) {
                    weights2[i] = avgWeight;
                }
                this.weights = weights2;
            }
            this.sumWeights = new int[this.weights.length];
            int sum = 0;
            for (int i = 0; i < this.weights.length; ++i) {
                if (this.weights[i] < 0) {
                    Config.warn("Invalid weight: " + this.weights[i]);
                    return false;
                }
                this.sumWeights[i] = sum += this.weights[i];
            }
            this.sumAllWeights = sum;
            if (this.sumAllWeights <= 0) {
                Config.warn("Invalid sum of all weights: " + sum);
                this.sumAllWeights = 1;
            }
        }
        if (this.professions == ConnectedParser.PROFESSIONS_INVALID) {
            Config.warn("Invalid professions or careers: " + path);
            return false;
        }
        if (this.collarColors == ConnectedParser.DYE_COLORS_INVALID) {
            Config.warn("Invalid collar colors: " + path);
            return false;
        }
        return true;
    }

    public boolean matches(IRandomEntity randomEntity) {
        Weather weather;
        int dayTime;
        int moonPhase;
        bsb world;
        vq livingEntity;
        RandomEntity rme;
        vg entity;
        String name;
        et pos;
        if (this.biomes != null && !Matches.biome(randomEntity.getSpawnBiome(), this.biomes)) {
            return false;
        }
        if (this.heights != null && (pos = randomEntity.getSpawnPosition()) != null && !this.heights.isInRange(pos.q())) {
            return false;
        }
        if (this.healthRange != null) {
            int healthMax;
            int health = randomEntity.getHealth();
            if (this.healthPercent && (healthMax = randomEntity.getMaxHealth()) > 0) {
                health = (int)((double)(health * 100) / (double)healthMax);
            }
            if (!this.healthRange.isInRange(health)) {
                return false;
            }
        }
        if (this.nbtName != null && !this.nbtName.matchesValue(name = randomEntity.getName())) {
            return false;
        }
        if (this.professions != null && randomEntity instanceof RandomEntity && (entity = (rme = (RandomEntity)randomEntity).getEntity()) instanceof ady) {
            ady entityVillager = (ady)entity;
            int profInt = entityVillager.dl();
            int careerInt = Reflector.getFieldValueInt(entityVillager, Reflector.EntityVillager_careerId, -1);
            if (profInt < 0 || careerInt < 0) {
                return false;
            }
            boolean matchProfession = false;
            for (int i = 0; i < this.professions.length; ++i) {
                VillagerProfession prof = this.professions[i];
                if (!prof.matches(profInt, careerInt)) continue;
                matchProfession = true;
                break;
            }
            if (!matchProfession) {
                return false;
            }
        }
        if (this.collarColors != null && randomEntity instanceof RandomEntity && (entity = (rme = (RandomEntity)randomEntity).getEntity()) instanceof aam) {
            aam entityWolf = (aam)entity;
            if (!entityWolf.dl()) {
                return false;
            }
            ahs col = entityWolf.dw();
            if (!Config.equalsOne(col, this.collarColors)) {
                return false;
            }
        }
        if (this.baby != null && randomEntity instanceof RandomEntity && (entity = (rme = (RandomEntity)randomEntity).getEntity()) instanceof vq && (livingEntity = (vq)entity).l_() != this.baby.booleanValue()) {
            return false;
        }
        if (this.moonPhases != null && (world = Config.getMinecraft().f) != null && !this.moonPhases.isInRange(moonPhase = world.F())) {
            return false;
        }
        if (this.dayTimes != null && (world = Config.getMinecraft().f) != null && !this.dayTimes.isInRange(dayTime = (int)world.V().f())) {
            return false;
        }
        return this.weatherList == null || (world = Config.getMinecraft().f) == null || ArrayUtils.contains((Object[])this.weatherList, (Object)(weather = Weather.getWeather((amu)world, 0.0f)));
    }

    public nf getTextureLocation(nf loc, int randomId) {
        if (this.resourceLocations == null || this.resourceLocations.length == 0) {
            return loc;
        }
        int index = 0;
        if (this.weights == null) {
            index = randomId % this.resourceLocations.length;
        } else {
            int randWeight = randomId % this.sumAllWeights;
            for (int i = 0; i < this.sumWeights.length; ++i) {
                if (this.sumWeights[i] <= randWeight) continue;
                index = i;
                break;
            }
        }
        return this.resourceLocations[index];
    }
}

