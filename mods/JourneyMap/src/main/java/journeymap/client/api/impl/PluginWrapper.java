/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.base.MoreObjects
 *  com.google.common.base.Objects
 *  com.google.common.collect.HashBasedTable
 *  com.google.common.collect.Table$Cell
 *  javax.annotation.ParametersAreNonnullByDefault
 */
package journeymap.client.api.impl;

import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;
import com.google.common.collect.HashBasedTable;
import com.google.common.collect.Table;
import java.util.ArrayList;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.List;
import javax.annotation.ParametersAreNonnullByDefault;
import journeymap.client.api.IClientPlugin;
import journeymap.client.api.display.DisplayType;
import journeymap.client.api.display.Displayable;
import journeymap.client.api.display.ImageOverlay;
import journeymap.client.api.display.MarkerOverlay;
import journeymap.client.api.display.Overlay;
import journeymap.client.api.display.PolygonOverlay;
import journeymap.client.api.display.Waypoint;
import journeymap.client.api.event.ClientEvent;
import journeymap.client.api.util.UIState;
import journeymap.client.log.StatTimer;
import journeymap.client.render.draw.DrawImageStep;
import journeymap.client.render.draw.DrawMarkerStep;
import journeymap.client.render.draw.DrawPolygonStep;
import journeymap.client.render.draw.OverlayDrawStep;
import journeymap.client.waypoint.WaypointStore;
import journeymap.common.Journeymap;
import journeymap.common.log.LogFormatter;

@ParametersAreNonnullByDefault
class PluginWrapper {
    private final IClientPlugin plugin;
    private final String modId;
    private final StatTimer eventTimer;
    private final HashMap<Integer, HashBasedTable<String, Overlay, OverlayDrawStep>> dimensionOverlays = new HashMap();
    private final HashBasedTable<String, Waypoint, journeymap.client.model.Waypoint> waypoints = HashBasedTable.create();
    private EnumSet<ClientEvent.Type> subscribedClientEventTypes = EnumSet.noneOf(ClientEvent.Type.class);

    public PluginWrapper(IClientPlugin plugin) {
        this.modId = plugin.getModId();
        this.plugin = plugin;
        this.eventTimer = StatTimer.get("pluginClientEvent_" + this.modId, 1, 200);
    }

    private HashBasedTable<String, Overlay, OverlayDrawStep> getOverlays(int dimension) {
        HashBasedTable table = this.dimensionOverlays.get(dimension);
        if (table == null) {
            table = HashBasedTable.create();
            this.dimensionOverlays.put(dimension, (HashBasedTable<String, Overlay, OverlayDrawStep>)table);
        }
        return table;
    }

    public void show(Displayable displayable) throws Exception {
        String displayId = displayable.getId();
        switch (displayable.getDisplayType()) {
            case Polygon: {
                PolygonOverlay polygon = (PolygonOverlay)displayable;
                this.getOverlays(polygon.getDimension()).put((Object)displayId, (Object)polygon, (Object)new DrawPolygonStep(polygon));
                break;
            }
            case Marker: {
                MarkerOverlay marker = (MarkerOverlay)displayable;
                this.getOverlays(marker.getDimension()).put((Object)displayId, (Object)marker, (Object)new DrawMarkerStep(marker));
                break;
            }
            case Image: {
                ImageOverlay imageOverlay = (ImageOverlay)displayable;
                this.getOverlays(imageOverlay.getDimension()).put((Object)displayId, (Object)imageOverlay, (Object)new DrawImageStep(imageOverlay));
                break;
            }
            case Waypoint: {
                Waypoint modWaypoint = (Waypoint)displayable;
                journeymap.client.model.Waypoint waypoint = new journeymap.client.model.Waypoint(modWaypoint);
                WaypointStore.INSTANCE.save(waypoint);
                this.waypoints.put((Object)displayId, (Object)modWaypoint, (Object)waypoint);
                break;
            }
        }
    }

    public void remove(Displayable displayable) {
        String displayId = displayable.getId();
        try {
            switch (displayable.getDisplayType()) {
                case Waypoint: {
                    this.remove((Waypoint)displayable);
                    break;
                }
                default: {
                    Overlay overlay = (Overlay)displayable;
                    OverlayDrawStep drawStep = (OverlayDrawStep)this.getOverlays(overlay.getDimension()).remove((Object)displayId, (Object)displayable);
                    if (drawStep != null) {
                        drawStep.setEnabled(false);
                    }
                    break;
                }
            }
        }
        catch (Throwable t) {
            Journeymap.getLogger().error("Error removing DrawMarkerStep: " + t, (Object)LogFormatter.toString(t));
        }
    }

    public void remove(Waypoint modWaypoint) {
        String displayId = modWaypoint.getId();
        journeymap.client.model.Waypoint waypoint = (journeymap.client.model.Waypoint)this.waypoints.remove((Object)displayId, (Object)modWaypoint);
        if (waypoint == null) {
            waypoint = new journeymap.client.model.Waypoint(modWaypoint);
        }
        WaypointStore.INSTANCE.remove(waypoint);
    }

    public void removeAll(DisplayType displayType) {
        if (displayType == DisplayType.Waypoint) {
            ArrayList list = new ArrayList(this.waypoints.columnKeySet());
            for (Waypoint modWaypoint : list) {
                this.remove(modWaypoint);
            }
        } else {
            for (HashBasedTable<String, Overlay, OverlayDrawStep> overlays : this.dimensionOverlays.values()) {
                ArrayList list = new ArrayList(overlays.columnKeySet());
                for (Displayable displayable : list) {
                    if (displayable.getDisplayType() != displayType) continue;
                    this.remove(displayable);
                }
            }
        }
    }

    public void removeAll() {
        if (!this.waypoints.isEmpty()) {
            ArrayList list = new ArrayList(this.waypoints.columnKeySet());
            for (Waypoint modWaypoint : list) {
                this.remove(modWaypoint);
            }
        }
        if (!this.dimensionOverlays.isEmpty()) {
            this.dimensionOverlays.clear();
        }
    }

    public boolean exists(Displayable displayable) {
        String displayId = displayable.getId();
        switch (displayable.getDisplayType()) {
            case Waypoint: {
                return this.waypoints.containsRow((Object)displayId);
            }
        }
        if (displayable instanceof Overlay) {
            int dimension = ((Overlay)displayable).getDimension();
            return this.getOverlays(dimension).containsRow((Object)displayId);
        }
        return false;
    }

    public void getDrawSteps(List<OverlayDrawStep> list, UIState uiState) {
        HashBasedTable<String, Overlay, OverlayDrawStep> table = this.getOverlays(uiState.dimension);
        for (Table.Cell cell : table.cellSet()) {
            if (!((Overlay)cell.getColumnKey()).isActiveIn(uiState)) continue;
            list.add((OverlayDrawStep)cell.getValue());
        }
    }

    public void subscribe(EnumSet<ClientEvent.Type> enumSet) {
        this.subscribedClientEventTypes = EnumSet.copyOf(enumSet);
    }

    public EnumSet<ClientEvent.Type> getSubscribedClientEventTypes() {
        return this.subscribedClientEventTypes;
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public void notify(ClientEvent clientEvent) {
        block11: {
            if (!this.subscribedClientEventTypes.contains((Object)clientEvent.type)) {
                return;
            }
            try {
                boolean cancelled = clientEvent.isCancelled();
                boolean cancellable = clientEvent.type.cancellable;
                this.eventTimer.start();
                try {
                    this.plugin.onEvent(clientEvent);
                    if (cancellable && !cancelled && clientEvent.isCancelled()) {
                        Journeymap.getLogger().debug(String.format("Plugin %s cancelled event: %s", new Object[]{this, clientEvent.type}));
                    }
                    this.eventTimer.stop();
                }
                catch (Throwable t) {
                    try {
                        Journeymap.getLogger().error(String.format("Plugin %s errored during event: %s", new Object[]{this, clientEvent.type}), t);
                        this.eventTimer.stop();
                    }
                    catch (Throwable throwable) {
                        this.eventTimer.stop();
                        if (this.eventTimer.hasReachedElapsedLimit()) {
                            Journeymap.getLogger().warn(String.format("Plugin %s too slow handling event: %s", new Object[]{this, clientEvent.type}));
                        }
                        throw throwable;
                    }
                    if (this.eventTimer.hasReachedElapsedLimit()) {
                        Journeymap.getLogger().warn(String.format("Plugin %s too slow handling event: %s", new Object[]{this, clientEvent.type}));
                    }
                    break block11;
                }
                if (this.eventTimer.hasReachedElapsedLimit()) {
                    Journeymap.getLogger().warn(String.format("Plugin %s too slow handling event: %s", new Object[]{this, clientEvent.type}));
                }
            }
            catch (Throwable t) {
                Journeymap.getLogger().error(String.format("Plugin %s error during event: %s", new Object[]{this, clientEvent.type}), t);
            }
        }
    }

    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof PluginWrapper)) {
            return false;
        }
        PluginWrapper that = (PluginWrapper)o;
        return Objects.equal((Object)this.modId, (Object)that.modId);
    }

    public int hashCode() {
        return Objects.hashCode((Object[])new Object[]{this.modId});
    }

    public String toString() {
        return MoreObjects.toStringHelper((Object)this.plugin).add("modId", (Object)this.modId).toString();
    }
}

