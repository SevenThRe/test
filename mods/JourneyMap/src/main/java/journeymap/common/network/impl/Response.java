/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.gson.JsonObject
 *  net.minecraftforge.fml.common.network.simpleimpl.MessageContext
 */
package journeymap.common.network.impl;

import com.google.gson.JsonObject;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

public interface Response<T> {
    public JsonObject getAsJson();

    public String getAsString();

    public T getRawResponse();

    public MessageContext getContext();
}

