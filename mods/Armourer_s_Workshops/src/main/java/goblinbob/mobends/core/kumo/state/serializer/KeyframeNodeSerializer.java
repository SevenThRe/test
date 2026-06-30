/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.gson.Gson
 *  com.google.gson.JsonDeserializationContext
 *  com.google.gson.JsonDeserializer
 *  com.google.gson.JsonElement
 *  com.google.gson.JsonParseException
 *  com.google.gson.JsonSerializationContext
 *  com.google.gson.JsonSerializer
 */
package goblinbob.mobends.core.kumo.state.serializer;

import com.google.gson.Gson;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import goblinbob.mobends.core.kumo.KumoSerializer;
import goblinbob.mobends.core.kumo.state.keyframe.KeyframeNodeRegistry;
import goblinbob.mobends.core.kumo.state.template.keyframe.KeyframeNodeTemplate;
import java.lang.reflect.Type;

public class KeyframeNodeSerializer
implements JsonSerializer<KeyframeNodeTemplate>,
JsonDeserializer<KeyframeNodeTemplate> {
    public JsonElement serialize(KeyframeNodeTemplate src, Type typeOfSrc, JsonSerializationContext context) {
        return new Gson().toJsonTree((Object)src, KeyframeNodeRegistry.INSTANCE.getTemplateClass(src.getType()));
    }

    public KeyframeNodeTemplate deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        Gson gson = new Gson();
        KeyframeNodeTemplate abstractNode = (KeyframeNodeTemplate)gson.fromJson(json, KeyframeNodeTemplate.class);
        return (KeyframeNodeTemplate)KumoSerializer.INSTANCE.keyframeNodeGson.fromJson(json, KeyframeNodeRegistry.INSTANCE.getTemplateClass(abstractNode.getType()));
    }
}

