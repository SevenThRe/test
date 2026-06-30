/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  javax.annotation.ParametersAreNonnullByDefault
 */
package journeymap.client.api;

import javax.annotation.ParametersAreNonnullByDefault;
import journeymap.client.api.IClientAPI;
import journeymap.client.api.event.ClientEvent;

@ParametersAreNonnullByDefault
public interface IClientPlugin {
    public void initialize(IClientAPI var1);

    public String getModId();

    public void onEvent(ClientEvent var1);
}

