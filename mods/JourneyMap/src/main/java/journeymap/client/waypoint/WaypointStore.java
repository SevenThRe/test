/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.base.Predicate
 *  com.google.common.cache.Cache
 *  com.google.common.cache.CacheBuilder
 *  com.google.common.collect.Maps
 *  com.google.common.io.Files
 *  com.google.gson.Gson
 *  com.google.gson.GsonBuilder
 *  javax.annotation.Nullable
 *  javax.annotation.ParametersAreNonnullByDefault
 */
package journeymap.client.waypoint;

import com.google.common.base.Predicate;
import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import com.google.common.collect.Maps;
import com.google.common.io.Files;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.io.File;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import javax.annotation.Nullable;
import javax.annotation.ParametersAreNonnullByDefault;
import journeymap.client.io.FileHandler;
import journeymap.client.model.Waypoint;
import journeymap.client.model.WaypointGroup;
import journeymap.client.waypoint.JmReader;
import journeymap.common.Journeymap;
import journeymap.common.log.LogFormatter;

@ParametersAreNonnullByDefault
public enum WaypointStore {
    INSTANCE;

    private final Gson gson = new GsonBuilder().setPrettyPrinting().create();
    private final Cache<String, Waypoint> cache = CacheBuilder.newBuilder().build();
    private final Cache<Long, Waypoint> groupCache = CacheBuilder.newBuilder().build();
    private final Set<Integer> dimensions = new HashSet<Integer>();
    private boolean loaded = false;

    private boolean writeToFile(Waypoint waypoint) {
        if (waypoint.isPersistent()) {
            File waypointFile = null;
            try {
                waypointFile = new File(FileHandler.getWaypointDir(), waypoint.getFileName());
                Files.write((CharSequence)this.gson.toJson((Object)waypoint), (File)waypointFile, (Charset)Charset.forName("UTF-8"));
                return true;
            }
            catch (Exception e) {
                Journeymap.getLogger().error(String.format("Can't save waypoint file %s: %s", waypointFile, LogFormatter.toString(e)));
                return false;
            }
        }
        return false;
    }

    public Collection<Waypoint> getAll() {
        return this.cache.asMap().values();
    }

    public Collection<Waypoint> getAll(final WaypointGroup group) {
        return Maps.filterEntries((Map)this.cache.asMap(), (Predicate)new Predicate<Map.Entry<String, Waypoint>>(){

            public boolean apply(@Nullable Map.Entry<String, Waypoint> input) {
                return input != null && Objects.equals(group, input.getValue().getGroup());
            }
        }).values();
    }

    public void add(Waypoint waypoint) {
        if (this.cache.getIfPresent((Object)waypoint.getId()) == null) {
            this.cache.put((Object)waypoint.getId(), (Object)waypoint);
        }
    }

    public void save(Waypoint waypoint) {
        this.cache.put((Object)waypoint.getId(), (Object)waypoint);
        boolean saved = this.writeToFile(waypoint);
        if (saved) {
            waypoint.setDirty(false);
        }
    }

    public void bulkSave() {
        for (Waypoint waypoint : this.cache.asMap().values()) {
            boolean saved;
            if (!waypoint.isDirty() || !(saved = this.writeToFile(waypoint))) continue;
            waypoint.setDirty(false);
        }
    }

    public void remove(Waypoint waypoint) {
        File waypointFile;
        this.cache.invalidate((Object)waypoint.getId());
        if (waypoint.isPersistent() && (waypointFile = new File(FileHandler.getWaypointDir(), waypoint.getFileName())).exists()) {
            this.remove(waypointFile);
        }
    }

    private void remove(File waypointFile) {
        try {
            waypointFile.delete();
        }
        catch (Exception e) {
            Journeymap.getLogger().warn(String.format("Can't delete waypoint file %s: %s", waypointFile, e.getMessage()));
            waypointFile.deleteOnExit();
        }
    }

    public void reset() {
        this.cache.invalidateAll();
        this.dimensions.clear();
        this.loaded = false;
        if (Journeymap.getClient().getWaypointProperties().managerEnabled.get().booleanValue()) {
            this.load();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    private void load() {
        Cache<String, Waypoint> cache = this.cache;
        synchronized (cache) {
            ArrayList<Waypoint> waypoints = new ArrayList<Waypoint>();
            File waypointDir = null;
            try {
                this.cache.invalidateAll();
                waypointDir = FileHandler.getWaypointDir();
                waypoints.addAll(new JmReader().loadWaypoints(waypointDir));
                this.load(waypoints, false);
                Journeymap.getLogger().info(String.format("Loaded %s waypoints from %s", this.cache.size(), waypointDir));
            }
            catch (Exception e) {
                Journeymap.getLogger().error(String.format("Error loading waypoints from %s: %s", waypointDir, LogFormatter.toString(e)));
            }
        }
    }

    public void load(Collection<Waypoint> waypoints, boolean forceSave) {
        for (Waypoint waypoint : waypoints) {
            if (waypoint.isPersistent() && (forceSave || waypoint.isDirty())) {
                this.save(waypoint);
            } else {
                this.cache.put((Object)waypoint.getId(), (Object)waypoint);
            }
            this.dimensions.addAll(waypoint.getDimensions());
        }
        this.loaded = true;
    }

    public boolean hasLoaded() {
        return this.loaded;
    }

    public List<Integer> getLoadedDimensions() {
        return new ArrayList<Integer>(this.dimensions);
    }
}

