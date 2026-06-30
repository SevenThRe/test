/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.cache.CacheBuilder
 *  com.google.common.cache.CacheLoader
 *  com.google.common.cache.LoadingCache
 *  com.google.common.cache.RemovalListener
 *  com.google.common.cache.RemovalNotification
 *  com.google.common.io.Files
 *  javax.annotation.ParametersAreNonnullByDefault
 */
package journeymap.client.waypoint;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import com.google.common.cache.RemovalListener;
import com.google.common.cache.RemovalNotification;
import com.google.common.io.Files;
import java.io.File;
import java.nio.charset.Charset;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;
import javax.annotation.ParametersAreNonnullByDefault;
import journeymap.client.io.FileHandler;
import journeymap.client.model.Waypoint;
import journeymap.client.model.WaypointGroup;
import journeymap.client.waypoint.WaypointStore;
import journeymap.common.Journeymap;
import journeymap.common.log.LogFormatter;

@ParametersAreNonnullByDefault
public enum WaypointGroupStore {
    INSTANCE;

    public static final String KEY_PATTERN = "%s:%s";
    public static final String FILENAME = "waypoint_groups.json";
    public final LoadingCache<String, WaypointGroup> cache = this.createCache();

    public WaypointGroup get(String name) {
        return this.get("journeymap", name);
    }

    public WaypointGroup get(String origin, String name) {
        this.ensureLoaded();
        return (WaypointGroup)this.cache.getUnchecked((Object)String.format(KEY_PATTERN, origin, name));
    }

    public boolean exists(WaypointGroup waypointGroup) {
        this.ensureLoaded();
        return this.cache.getIfPresent((Object)waypointGroup.getKey()) != null;
    }

    public void put(WaypointGroup waypointGroup) {
        this.ensureLoaded();
        this.cache.put((Object)waypointGroup.getKey(), (Object)waypointGroup);
        this.save(true);
    }

    public boolean putIfNew(WaypointGroup waypointGroup) {
        if (this.exists(waypointGroup)) {
            return false;
        }
        this.put(waypointGroup);
        return true;
    }

    public void remove(WaypointGroup waypointGroup) {
        this.ensureLoaded();
        this.cache.invalidate((Object)waypointGroup.getKey());
        waypointGroup.setDirty(false);
        this.save();
    }

    private void ensureLoaded() {
        if (this.cache.size() == 0L) {
            this.load();
        }
    }

    private void load() {
        File groupFile = new File(FileHandler.getWaypointDir(), FILENAME);
        if (groupFile.exists()) {
            HashMap map = new HashMap(0);
            try {
                String groupsString = Files.toString((File)groupFile, (Charset)Charset.forName("UTF-8"));
                map = (HashMap)WaypointGroup.GSON.fromJson(groupsString, map.getClass());
            }
            catch (Exception e) {
                Journeymap.getLogger().error(String.format("Error reading WaypointGroups file %s: %s", groupFile, LogFormatter.toPartialString(e)));
                try {
                    groupFile.renameTo(new File(groupFile.getParentFile(), groupFile.getName() + ".bad"));
                }
                catch (Exception e2) {
                    Journeymap.getLogger().error(String.format("Error renaming bad WaypointGroups file %s: %s", groupFile, LogFormatter.toPartialString(e)));
                }
            }
            if (!map.isEmpty()) {
                this.cache.invalidateAll();
                this.cache.putAll((Map)map);
                Journeymap.getLogger().info(String.format("Loaded WaypointGroups file %s", groupFile));
                this.cache.put((Object)WaypointGroup.DEFAULT.getKey(), (Object)WaypointGroup.DEFAULT);
                return;
            }
        }
        this.cache.put((Object)WaypointGroup.DEFAULT.getKey(), (Object)WaypointGroup.DEFAULT);
        this.save(true);
    }

    public void save() {
        this.save(true);
    }

    public void save(boolean force) {
        boolean doWrite = force;
        if (!force) {
            for (WaypointGroup group : this.cache.asMap().values()) {
                if (!group.isDirty()) continue;
                doWrite = true;
                break;
            }
        }
        if (doWrite) {
            TreeMap map = null;
            try {
                map = new TreeMap(new Comparator<String>(){
                    final String defaultKey = WaypointGroup.DEFAULT.getKey();

                    @Override
                    public int compare(String o1, String o2) {
                        if (o1.equals(this.defaultKey)) {
                            return -1;
                        }
                        if (o2.equals(this.defaultKey)) {
                            return 1;
                        }
                        return o1.compareTo(o2);
                    }
                });
                map.putAll(this.cache.asMap());
            }
            catch (Exception e) {
                Journeymap.getLogger().error(String.format("Error preparing WaypointGroups: %s", LogFormatter.toPartialString(e)));
                return;
            }
            File groupFile = null;
            try {
                File waypointDir = FileHandler.getWaypointDir();
                if (!waypointDir.exists()) {
                    waypointDir.mkdirs();
                }
                groupFile = new File(waypointDir, FILENAME);
                boolean isNew = groupFile.exists();
                Files.write((CharSequence)WaypointGroup.GSON.toJson(map), (File)groupFile, (Charset)Charset.forName("UTF-8"));
                for (WaypointGroup group : this.cache.asMap().values()) {
                    group.setDirty(false);
                }
                if (isNew) {
                    Journeymap.getLogger().info("Created WaypointGroups file: " + groupFile);
                }
            }
            catch (Exception e) {
                Journeymap.getLogger().error(String.format("Error writing WaypointGroups file %s: %s", groupFile, LogFormatter.toPartialString(e)));
            }
        }
    }

    private LoadingCache<String, WaypointGroup> createCache() {
        LoadingCache cache = CacheBuilder.newBuilder().concurrencyLevel(1).removalListener((RemovalListener)new RemovalListener<String, WaypointGroup>(){

            @ParametersAreNonnullByDefault
            public void onRemoval(RemovalNotification<String, WaypointGroup> notification) {
                for (Waypoint orphan : WaypointStore.INSTANCE.getAll((WaypointGroup)notification.getValue())) {
                    orphan.setGroupName(WaypointGroup.DEFAULT.getName());
                    orphan.setGroup(WaypointGroup.DEFAULT);
                }
                WaypointGroupStore.this.save();
            }
        }).build((CacheLoader)new CacheLoader<String, WaypointGroup>(){

            @ParametersAreNonnullByDefault
            public WaypointGroup load(String key) throws Exception {
                String name;
                String origin;
                int index = key.indexOf(":");
                if (index < 1) {
                    origin = "Unknown";
                    name = key;
                    Journeymap.getLogger().warn("Problematic waypoint group key: " + key);
                } else {
                    origin = key.substring(0, index);
                    name = key.substring(index, key.length());
                }
                return new WaypointGroup(origin, name);
            }
        });
        return cache;
    }
}

