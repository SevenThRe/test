/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.cache.CacheLoader
 */
package journeymap.client.data;

import com.google.common.cache.CacheLoader;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import journeymap.client.model.Waypoint;
import journeymap.client.waypoint.WaypointStore;
import journeymap.common.Journeymap;

public class WaypointsData
extends CacheLoader<Class, Collection<Waypoint>> {
    public static boolean isManagerEnabled() {
        return Journeymap.getClient().getWaypointProperties().managerEnabled.get();
    }

    protected static List<Waypoint> getWaypoints() {
        ArrayList<Waypoint> list = new ArrayList<Waypoint>(0);
        if (WaypointsData.isManagerEnabled()) {
            list.addAll(WaypointStore.INSTANCE.getAll());
        }
        return list;
    }

    private static boolean waypointClassesFound(String ... names) throws Exception {
        boolean loaded = true;
        for (String name : names) {
            if (!loaded) break;
            try {
                loaded = false;
                Class.forName(name);
                loaded = true;
                Journeymap.getLogger().debug("Class found: " + name);
            }
            catch (NoClassDefFoundError e) {
                throw new Exception("Class detected, but is obsolete: " + e.getMessage());
            }
            catch (ClassNotFoundException e) {
                Journeymap.getLogger().debug("Class not found: " + name);
            }
            catch (VerifyError v) {
                throw new Exception("Class detected, but is obsolete: " + v.getMessage());
            }
            catch (Throwable t) {
                throw new Exception("Class detected, but produced errors.", t);
            }
        }
        return loaded;
    }

    public Collection<Waypoint> load(Class aClass) throws Exception {
        return WaypointsData.getWaypoints();
    }

    public long getTTL() {
        return 5000L;
    }
}

