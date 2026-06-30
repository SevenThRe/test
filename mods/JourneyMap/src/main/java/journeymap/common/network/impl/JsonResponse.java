/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.gson.JsonObject
 *  net.minecraftforge.fml.common.network.simpleimpl.MessageContext
 */
package journeymap.common.network.impl;

import com.google.gson.JsonObject;
import journeymap.common.network.impl.Response;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

public class JsonResponse
implements Response<JsonObject> {
    private JsonObject rawResponse;
    private MessageContext messageContext;

    JsonResponse(JsonObject rawResponse, MessageContext messageContext) {
        this.rawResponse = rawResponse;
        this.messageContext = messageContext;
    }

    @Override
    public JsonObject getAsJson() {
        return this.rawResponse.get("data").getAsJsonObject();
    }

    @Override
    public String getAsString() {
        return this.rawResponse.get("data").toString();
    }

    @Override
    public JsonObject getRawResponse() {
        return this.rawResponse;
    }

    @Override
    public MessageContext getContext() {
        return this.messageContext;
    }
}

