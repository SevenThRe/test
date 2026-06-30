/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.gson.JsonObject
 *  net.minecraft.entity.player.EntityPlayerMP
 */
package journeymap.common.network;

import com.google.gson.JsonObject;
import journeymap.common.Journeymap;
import journeymap.common.network.impl.MessageProcessor;
import journeymap.common.network.impl.Response;
import journeymap.common.util.PlayerConfigController;
import net.minecraft.entity.player.EntityPlayerMP;

public class GetClientConfig
extends MessageProcessor {
    @Override
    protected JsonObject onServer(Response response) {
        EntityPlayerMP player = response.getContext().getServerHandler().field_147369_b;
        return PlayerConfigController.getInstance().getPlayerConfig(player);
    }

    @Override
    public JsonObject onClient(Response response) {
        Journeymap.getClient().setJourneyMapServerConnection(true);
        PlayerConfigController.getInstance().updateClientConfigs(response);
        return null;
    }
}

