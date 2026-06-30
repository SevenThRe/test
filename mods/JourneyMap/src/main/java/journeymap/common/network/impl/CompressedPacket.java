/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.gson.Gson
 *  com.google.gson.GsonBuilder
 *  com.google.gson.JsonElement
 *  com.google.gson.JsonObject
 *  net.minecraftforge.fml.common.network.simpleimpl.MessageContext
 */
package journeymap.common.network.impl;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import java.io.IOException;
import journeymap.common.network.impl.MessageProcessor;
import journeymap.common.network.impl.NetworkHandler;
import journeymap.common.network.impl.utils.CompressionUtils;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

public abstract class CompressedPacket
extends MessageProcessor {
    private static final Gson GSON = new GsonBuilder().serializeNulls().create();
    private static final String COMPRESSED_DATA = "COMPRESSED_DATA";

    @Override
    protected void buildRequest(JsonObject requestData) {
        if (requestData != null) {
            try {
                String dataAsString = requestData.toString();
                String compressedData = CompressionUtils.compress(dataAsString);
                requestData = new JsonObject();
                requestData.addProperty(COMPRESSED_DATA, compressedData);
            }
            catch (IOException e) {
                NetworkHandler.getLogger().error("ERROR: Unable to compress compressed json packet");
            }
        }
        super.buildRequest(requestData);
    }

    @Override
    protected void handleResponse(JsonObject message, MessageContext ctx) {
        try {
            JsonObject data = message.get("data").getAsJsonObject();
            if (data.get(COMPRESSED_DATA) != null) {
                String dataAsCompressedString = data.get(COMPRESSED_DATA).getAsString();
                String dataString = CompressionUtils.decompress(dataAsCompressedString);
                JsonObject jsonObject = (JsonObject)GSON.fromJson(dataString, JsonObject.class);
                message.add("data", (JsonElement)jsonObject);
                message.remove(COMPRESSED_DATA);
            }
        }
        catch (IOException e) {
            NetworkHandler.getLogger().error("ERROR: Unable to decompress compressed json packet:", (Object)message.get("container_object").getAsString());
        }
        super.handleResponse(message, ctx);
    }
}

