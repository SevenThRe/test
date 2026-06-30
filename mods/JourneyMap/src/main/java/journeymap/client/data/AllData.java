/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.cache.CacheLoader
 *  com.google.common.collect.ImmutableMap
 */
package journeymap.client.data;

import com.google.common.cache.CacheLoader;
import com.google.common.collect.ImmutableMap;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import journeymap.client.data.DataCache;
import journeymap.client.data.ImagesData;
import journeymap.client.data.WorldData;
import journeymap.client.model.Waypoint;
import journeymap.common.Journeymap;

public class AllData
extends CacheLoader<Long, Map> {
    public Map load(Long since) throws Exception {
        DataCache cache = DataCache.INSTANCE;
        LinkedHashMap<Key, Object> props = new LinkedHashMap<Key, Object>();
        props.put(Key.world, (Object)cache.getWorld(false));
        props.put(Key.player, cache.getPlayer(false));
        props.put(Key.images, new ImagesData(since));
        if (Journeymap.getClient().getWebMapProperties().showWaypoints.get().booleanValue()) {
            int currentDimension = cache.getPlayer((boolean)false).dimension;
            Collection<Waypoint> waypoints = cache.getWaypoints(false);
            HashMap<String, Waypoint> wpMap = new HashMap<String, Waypoint>();
            for (Waypoint waypoint : waypoints) {
                if (!waypoint.getDimensions().contains(currentDimension)) continue;
                wpMap.put(waypoint.getId(), waypoint);
            }
            props.put(Key.waypoints, wpMap);
        } else {
            props.put(Key.waypoints, Collections.emptyMap());
        }
        if (!WorldData.isHardcoreAndMultiplayer()) {
            props.put(Key.animals, Collections.emptyMap());
            props.put(Key.mobs, Collections.emptyMap());
            props.put(Key.players, Collections.emptyMap());
            props.put(Key.villagers, Collections.emptyMap());
        }
        return ImmutableMap.copyOf(props);
    }

    public long getTTL() {
        return Journeymap.getClient().getCoreProperties().renderDelay.get() * 2000;
    }

    public static enum Key {
        animals,
        images,
        mobs,
        player,
        players,
        villagers,
        waypoints,
        world;

    }
}

