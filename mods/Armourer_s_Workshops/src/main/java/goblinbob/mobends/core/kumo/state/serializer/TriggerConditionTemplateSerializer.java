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
import goblinbob.mobends.core.kumo.state.condition.TriggerConditionRegistry;
import goblinbob.mobends.core.kumo.state.template.TriggerConditionTemplate;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;

public class TriggerConditionTemplateSerializer
implements JsonSerializer<TriggerConditionTemplate>,
JsonDeserializer<TriggerConditionTemplate> {
    private final Map<JsonElement, Type> toDeserialize = new HashMap<JsonElement, Type>();

    public JsonElement serialize(TriggerConditionTemplate src, Type typeOfSrc, JsonSerializationContext context) {
        return KumoSerializer.INSTANCE.gson.toJsonTree((Object)src);
    }

    public TriggerConditionTemplate deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        Gson gson = new Gson();
        TriggerConditionTemplate abstractTriggerCondition = (TriggerConditionTemplate)gson.fromJson(json, TriggerConditionTemplate.class);
        String typeName = abstractTriggerCondition.getType();
        if (typeName == null) {
            return null;
        }
        Type templateType = TriggerConditionRegistry.instance.getTemplateClass(typeName);
        if (templateType == null) {
            return null;
        }
        if (templateType.equals(typeOfT)) {
            return abstractTriggerCondition;
        }
        return (TriggerConditionTemplate)KumoSerializer.INSTANCE.gson.fromJson(json, templateType);
    }
}

