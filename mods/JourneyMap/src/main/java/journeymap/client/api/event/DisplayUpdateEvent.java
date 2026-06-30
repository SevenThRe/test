/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.base.MoreObjects
 */
package journeymap.client.api.event;

import com.google.common.base.MoreObjects;
import journeymap.client.api.event.ClientEvent;
import journeymap.client.api.util.UIState;

public class DisplayUpdateEvent
extends ClientEvent {
    public final UIState uiState;

    public DisplayUpdateEvent(UIState uiState) {
        super(ClientEvent.Type.DISPLAY_UPDATE, uiState.dimension);
        this.uiState = uiState;
    }

    public String toString() {
        return MoreObjects.toStringHelper((Object)this).add("uiState", (Object)this.uiState).toString();
    }
}

