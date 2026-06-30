/*
 * Decompiled with CFR 0.152.
 */
package journeymap.client.model;

import journeymap.client.api.display.Context;
import journeymap.client.data.DataCache;
import journeymap.client.feature.Feature;
import journeymap.client.feature.FeatureManager;
import journeymap.client.model.EntityDTO;

public class MapType {
    public final Integer vSlice;
    public final Name name;
    public final int dimension;
    public final Context.MapType apiMapType;
    private final int theHashCode;
    private final String theCacheKey;

    public MapType(Name name, Integer vSlice, int dimension) {
        vSlice = name != Name.underground ? null : vSlice;
        this.name = name;
        this.vSlice = vSlice;
        this.dimension = dimension;
        this.apiMapType = this.toApiContextMapType(name);
        this.theCacheKey = MapType.toCacheKey(name, vSlice, dimension);
        this.theHashCode = this.theCacheKey.hashCode();
    }

    public static MapType from(Name name, Integer vSlice, int dimension) {
        return DataCache.INSTANCE.getMapType(name, vSlice, dimension);
    }

    public static MapType from(Integer vSlice, int dimension) {
        return MapType.from(vSlice == null ? Name.surface : Name.underground, vSlice, dimension);
    }

    public static MapType from(Name name, EntityDTO player) {
        return MapType.from(name, player.chunkCoordY, player.dimension);
    }

    public static MapType day(int dimension) {
        return MapType.from(Name.day, null, dimension);
    }

    public static MapType day(EntityDTO player) {
        return MapType.from(Name.day, null, player.dimension);
    }

    public static MapType night(int dimension) {
        return MapType.from(Name.night, null, dimension);
    }

    public static MapType night(EntityDTO player) {
        return MapType.from(Name.night, null, player.dimension);
    }

    public static MapType topo(int dimension) {
        return MapType.from(Name.topo, null, dimension);
    }

    public static MapType topo(EntityDTO player) {
        return MapType.from(Name.topo, null, player.dimension);
    }

    public static MapType underground(EntityDTO player) {
        return MapType.from(Name.underground, player.chunkCoordY, player.dimension);
    }

    public static MapType underground(Integer vSlice, int dimension) {
        return MapType.from(Name.underground, vSlice, dimension);
    }

    public static MapType none() {
        return MapType.from(Name.none, 0, 0);
    }

    public static String toCacheKey(Name name, Integer vSlice, int dimension) {
        return String.format("%s|%s|%s", new Object[]{dimension, name, vSlice == null ? "_" : vSlice});
    }

    private Context.MapType toApiContextMapType(Name name) {
        switch (name) {
            case day: {
                return Context.MapType.Day;
            }
            case topo: {
                return Context.MapType.Topo;
            }
            case night: {
                return Context.MapType.Night;
            }
            case underground: {
                return Context.MapType.Underground;
            }
        }
        return Context.MapType.Any;
    }

    public static MapType fromApiContextMapType(Context.MapType apiMapType, Integer vSlice, int dimension) {
        switch (apiMapType) {
            case Day: {
                return new MapType(Name.day, vSlice, dimension);
            }
            case Night: {
                return new MapType(Name.night, vSlice, dimension);
            }
            case Underground: {
                return new MapType(Name.underground, vSlice, dimension);
            }
            case Topo: {
                return new MapType(Name.topo, vSlice, dimension);
            }
        }
        return new MapType(Name.day, vSlice, dimension);
    }

    public String toCacheKey() {
        return this.theCacheKey;
    }

    public String toString() {
        return this.theCacheKey;
    }

    public String name() {
        return this.name.name();
    }

    public boolean isUnderground() {
        return this.name == Name.underground;
    }

    public boolean isSurface() {
        return this.name == Name.surface;
    }

    public boolean isDay() {
        return this.name == Name.day;
    }

    public boolean isNight() {
        return this.name == Name.night;
    }

    public boolean isTopo() {
        return this.name == Name.topo;
    }

    public boolean isDayOrNight() {
        return this.name == Name.day || this.name == Name.night;
    }

    public boolean isAllowed() {
        if (this.isUnderground()) {
            return FeatureManager.isAllowed(Feature.MapCaves);
        }
        if (this.isTopo()) {
            return FeatureManager.isAllowed(Feature.MapTopo);
        }
        if (this.isDayOrNight() || this.isSurface()) {
            return FeatureManager.isAllowed(Feature.MapSurface);
        }
        return this.name == Name.none;
    }

    public int hashCode() {
        return this.theHashCode;
    }

    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || this.getClass() != o.getClass()) {
            return false;
        }
        MapType mapType = (MapType)o;
        if (this.dimension != mapType.dimension) {
            return false;
        }
        if (this.name != mapType.name) {
            return false;
        }
        return !(this.vSlice != null ? !this.vSlice.equals(mapType.vSlice) : mapType.vSlice != null);
    }

    public static enum Name {
        day,
        night,
        underground,
        surface,
        topo,
        none;

    }
}

