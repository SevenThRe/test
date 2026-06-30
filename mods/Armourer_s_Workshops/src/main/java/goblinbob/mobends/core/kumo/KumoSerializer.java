/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.gson.Gson
 *  com.google.gson.GsonBuilder
 */
package goblinbob.mobends.core.kumo;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import goblinbob.mobends.core.kumo.state.serializer.KeyframeNodeSerializer;
import goblinbob.mobends.core.kumo.state.serializer.LayerTemplateSerializer;
import goblinbob.mobends.core.kumo.state.serializer.TriggerConditionTemplateSerializer;
import goblinbob.mobends.core.kumo.state.template.LayerTemplate;
import goblinbob.mobends.core.kumo.state.template.TriggerConditionTemplate;
import goblinbob.mobends.core.kumo.state.template.keyframe.KeyframeNodeTemplate;

public class KumoSerializer {
    public static final KumoSerializer INSTANCE = new KumoSerializer();
    public final Gson gson;
    public final Gson layerGson;
    public final Gson keyframeNodeGson;

    private KumoSerializer() {
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.registerTypeAdapter(LayerTemplate.class, (Object)new LayerTemplateSerializer());
        gsonBuilder.registerTypeAdapter(KeyframeNodeTemplate.class, (Object)new KeyframeNodeSerializer());
        gsonBuilder.registerTypeAdapter(TriggerConditionTemplate.class, (Object)new TriggerConditionTemplateSerializer());
        this.gson = gsonBuilder.create();
        gsonBuilder = new GsonBuilder();
        gsonBuilder.registerTypeAdapter(KeyframeNodeTemplate.class, (Object)new KeyframeNodeSerializer());
        gsonBuilder.registerTypeAdapter(TriggerConditionTemplate.class, (Object)new TriggerConditionTemplateSerializer());
        this.layerGson = gsonBuilder.create();
        gsonBuilder = new GsonBuilder();
        gsonBuilder.registerTypeAdapter(TriggerConditionTemplate.class, (Object)new TriggerConditionTemplateSerializer());
        this.keyframeNodeGson = gsonBuilder.create();
    }
}

