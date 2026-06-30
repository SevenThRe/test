/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.gson.JsonArray
 *  com.google.gson.JsonObject
 */
package journeymap.common.network;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import journeymap.common.Journeymap;
import journeymap.common.feature.PlayerRadarManager;
import journeymap.common.network.impl.MessageProcessor;
import journeymap.common.network.impl.Response;

public class GetPlayerLocations
extends MessageProcessor {
    @Override
    protected JsonObject onServer(Response response) {
        return null;
    }

    @Override
    protected JsonObject onClient(Response result) {
        Journeymap.getClient().setPlayerTrackingEnabled(true);
        if (result.getAsJson().get("players") != null) {
            JsonArray playerList = result.getAsJson().get("players").getAsJsonArray();
            PlayerRadarManager.getInstance().updatePlayers(playerList);
        }
        return null;
    }
}

