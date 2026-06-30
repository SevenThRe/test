/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.gson.Gson
 *  com.google.gson.GsonBuilder
 *  com.google.gson.JsonDeserializationContext
 *  com.google.gson.JsonDeserializer
 *  com.google.gson.JsonElement
 *  com.google.gson.JsonObject
 *  com.google.gson.JsonSyntaxException
 */
package eos.moe.dragoncore;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSyntaxException;
import eos.moe.dragoncore.as;
import eos.moe.dragoncore.cu;
import eos.moe.dragoncore.gy;
import eos.moe.dragoncore.it;
import eos.moe.dragoncore.qy;
import eos.moe.dragoncore.tp;
import eos.moe.dragoncore.xp;
import eos.moe.dragoncore.zr;
import java.lang.reflect.Type;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

public class uw
implements JsonDeserializer<xp> {
    private Gson y;
    private Map<UUID, as> k;
    private Map<UUID, String> ALLATORIxDEMO;

    public uw() {
        uw a2;
        a2.y = new GsonBuilder().registerTypeAdapter(tp.class, (Object)new zr(a2, null)).registerTypeAdapter(gy.class, (Object)new qy(null)).registerTypeAdapter(cu.class, (Object)new it(a2, null)).create();
        a2.k = new ConcurrentHashMap<UUID, as>();
        a2.ALLATORIxDEMO = new ConcurrentHashMap<UUID, String>();
    }

    public xp deserialize(JsonElement a2, Type a3, JsonDeserializationContext a4) {
        uw a6;
        JsonElement a5;
        xp a8 = new xp();
        JsonObject a9 = a2.getAsJsonObject();
        if (a9.has("meta")) {
            a8.v = a9.get("meta").getAsJsonObject().get("box_uv").getAsBoolean();
        }
        if (a9.has("name")) {
            a8.m = a9.get("name").getAsString();
        }
        if (a9.has("resolution")) {
            JsonObject a7 = a9.get("resolution").getAsJsonObject();
            a8.c = a7.get("width").getAsInt();
            a8.q = a7.get("height").getAsInt();
        }
        if (a9.has("elements") && (a5 = a9.get("elements")).isJsonArray()) {
            for (as as2 : (as[])a6.y.fromJson(a5, as[].class)) {
                a8.b.add(as2);
                a6.k.put(as2.ALLATORIxDEMO, as2);
            }
        }
        if (a9.has("outliner") && (a5 = a9.get("outliner")).isJsonArray()) {
            for (JsonElement a10 : a5.getAsJsonArray()) {
                try {
                    a8.o.add(a6.k.get(a6.y.fromJson(a10, UUID.class)));
                }
                catch (JsonSyntaxException a11) {
                    tp tp2 = (tp)a6.y.fromJson(a10, tp.class);
                    a6.ALLATORIxDEMO.put(tp2.k, tp2.b);
                    a8.o.add(tp2);
                }
            }
        }
        if (a9.has("textures") && (a5 = a9.get("textures")).isJsonArray()) {
            for (JsonElement a12 : a5.getAsJsonArray()) {
                a8.y.add((gy)a6.y.fromJson(a12, gy.class));
            }
        }
        if (a9.has("animations") && (a5 = a9.get("animations")).isJsonArray()) {
            for (JsonElement a13 : a5.getAsJsonArray()) {
                cu a14 = (cu)a6.y.fromJson(a13, cu.class);
                JsonObject jsonObject = a13.getAsJsonObject();
                if (!jsonObject.has("name")) continue;
                a8.k.ALLATORIxDEMO().put(jsonObject.get("name").getAsString(), a14);
            }
        }
        if (a9.has("mcmetas") && (a5 = a9.get("mcmetas")).isJsonObject()) {
            for (Map.Entry a15 : a5.getAsJsonObject().entrySet()) {
                a8.ALLATORIxDEMO.put((String)a15.getKey(), ((JsonElement)a15.getValue()).getAsJsonObject().toString());
            }
        }
        a6.k.clear();
        a6.ALLATORIxDEMO.clear();
        return a8;
    }

    public static /* synthetic */ Gson ALLATORIxDEMO(uw a2) {
        return a2.y;
    }

    public static /* synthetic */ Map c(uw a2) {
        return a2.k;
    }

    public static /* synthetic */ Map ALLATORIxDEMO(uw a2) {
        return a2.ALLATORIxDEMO;
    }
}

