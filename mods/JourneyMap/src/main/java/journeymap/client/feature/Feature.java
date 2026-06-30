/*
 * Decompiled with CFR 0.152.
 */
package journeymap.client.feature;

import java.util.EnumSet;

public enum Feature {
    RadarPlayers,
    RadarAnimals,
    RadarMobs,
    RadarVillagers,
    MapTopo,
    MapSurface,
    MapCaves;


    public static EnumSet<Feature> radar() {
        return EnumSet.of(RadarPlayers, RadarAnimals, RadarMobs, RadarVillagers);
    }

    public static EnumSet<Feature> all() {
        return EnumSet.allOf(Feature.class);
    }
}

