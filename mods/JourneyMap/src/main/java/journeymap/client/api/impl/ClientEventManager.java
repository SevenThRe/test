/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  javax.annotation.ParametersAreNonnullByDefault
 */
package journeymap.client.api.impl;

import java.util.Collection;
import java.util.EnumSet;
import java.util.Iterator;
import javax.annotation.ParametersAreNonnullByDefault;
import journeymap.client.api.event.ClientEvent;
import journeymap.client.api.event.DeathWaypointEvent;
import journeymap.client.api.event.DisplayUpdateEvent;
import journeymap.client.api.impl.ClientAPI;
import journeymap.client.api.impl.DisplayUpdateEventThrottle;
import journeymap.client.api.impl.PluginWrapper;

@ParametersAreNonnullByDefault
public class ClientEventManager {
    private final DisplayUpdateEventThrottle displayUpdateEventThrottle = new DisplayUpdateEventThrottle();
    private final Collection<PluginWrapper> plugins;
    private EnumSet<ClientEvent.Type> subscribedClientEventTypes = EnumSet.noneOf(ClientEvent.Type.class);

    public ClientEventManager(Collection<PluginWrapper> plugins) {
        this.plugins = plugins;
    }

    public void updateSubscribedTypes() {
        this.subscribedClientEventTypes = EnumSet.noneOf(ClientEvent.Type.class);
        for (PluginWrapper wrapper : this.plugins) {
            this.subscribedClientEventTypes.addAll(wrapper.getSubscribedClientEventTypes());
        }
    }

    public boolean canFireClientEvent(ClientEvent.Type type) {
        return this.subscribedClientEventTypes.contains((Object)type);
    }

    public void fireMappingEvent(boolean started, int dimension) {
        ClientEvent.Type type;
        ClientEvent.Type type2 = type = started ? ClientEvent.Type.MAPPING_STARTED : ClientEvent.Type.MAPPING_STOPPED;
        if (this.plugins.isEmpty() || !this.subscribedClientEventTypes.contains((Object)type)) {
            return;
        }
        ClientEvent clientEvent = new ClientEvent(type, dimension);
        for (PluginWrapper wrapper : this.plugins) {
            try {
                wrapper.notify(clientEvent);
            }
            catch (Throwable t) {
                ClientAPI.INSTANCE.logError("Error in fireMappingEvent(): " + clientEvent, t);
            }
        }
    }

    public void fireDeathpointEvent(DeathWaypointEvent clientEvent) {
        if (this.plugins.isEmpty() || !this.subscribedClientEventTypes.contains((Object)ClientEvent.Type.DEATH_WAYPOINT)) {
            return;
        }
        for (PluginWrapper wrapper : this.plugins) {
            try {
                wrapper.notify(clientEvent);
            }
            catch (Throwable t) {
                ClientAPI.INSTANCE.logError("Error in fireDeathpointEvent(): " + clientEvent, t);
            }
        }
    }

    public void fireDisplayUpdateEvent(DisplayUpdateEvent clientEvent) {
        if (this.plugins.size() == 0 || !this.subscribedClientEventTypes.contains((Object)ClientEvent.Type.DISPLAY_UPDATE)) {
            return;
        }
        try {
            this.displayUpdateEventThrottle.add(clientEvent);
        }
        catch (Throwable t) {
            ClientAPI.INSTANCE.logError("Error in fireDisplayUpdateEvent(): " + clientEvent, t);
        }
    }

    public void fireNextClientEvents() {
        if (!this.plugins.isEmpty() && this.displayUpdateEventThrottle.isReady()) {
            Iterator<DisplayUpdateEvent> iterator2 = this.displayUpdateEventThrottle.iterator();
            while (iterator2.hasNext()) {
                DisplayUpdateEvent clientEvent = iterator2.next();
                iterator2.remove();
                for (PluginWrapper wrapper : this.plugins) {
                    try {
                        wrapper.notify(clientEvent);
                    }
                    catch (Throwable t) {
                        ClientAPI.INSTANCE.logError("Error in fireDeathpointEvent(): " + clientEvent, t);
                    }
                }
            }
        }
    }

    void purge() {
        this.plugins.clear();
        this.subscribedClientEventTypes.clear();
    }
}

