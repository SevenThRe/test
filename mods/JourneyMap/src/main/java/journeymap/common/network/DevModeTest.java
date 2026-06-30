/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.gson.JsonObject
 *  net.minecraft.entity.Entity
 */
package journeymap.common.network;

import com.google.gson.JsonObject;
import journeymap.common.network.impl.MessageProcessor;
import journeymap.common.network.impl.Response;
import journeymap.server.Constants;
import journeymap.server.JourneymapServer;
import net.minecraft.entity.Entity;

public class DevModeTest
extends MessageProcessor {
    @Override
    protected JsonObject onServer(Response response) {
        if (Constants.isDev((Entity)response.getContext().getServerHandler().field_147369_b)) {
            JourneymapServer.DEV_MODE = response.getAsJson().get("value").getAsBoolean();
        }
        return null;
    }

    @Override
    protected JsonObject onClient(Response response) {
        return null;
    }
}

