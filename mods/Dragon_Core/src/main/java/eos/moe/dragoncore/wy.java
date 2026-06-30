/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.gson.Gson
 *  com.google.gson.GsonBuilder
 *  com.google.gson.JsonArray
 *  com.google.gson.JsonElement
 *  com.google.gson.JsonObject
 *  com.google.gson.JsonPrimitive
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.resources.IResource
 *  net.minecraft.util.JsonUtils
 *  net.minecraft.util.ResourceLocation
 *  org.apache.commons.lang3.math.NumberUtils
 */
package eos.moe.dragoncore;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;
import eos.moe.dragoncore.bax;
import eos.moe.dragoncore.ca;
import eos.moe.dragoncore.dg;
import eos.moe.dragoncore.fu;
import eos.moe.dragoncore.hh;
import eos.moe.dragoncore.hk;
import eos.moe.dragoncore.hy;
import eos.moe.dragoncore.kq;
import eos.moe.dragoncore.kw;
import eos.moe.dragoncore.me;
import eos.moe.dragoncore.nd;
import eos.moe.dragoncore.raa;
import eos.moe.dragoncore.to;
import eos.moe.dragoncore.u;
import eos.moe.dragoncore.wa;
import eos.moe.dragoncore.yz;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;
import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.IResource;
import net.minecraft.util.JsonUtils;
import net.minecraft.util.ResourceLocation;
import org.apache.commons.lang3.math.NumberUtils;

/*
 * Duplicate member names - consider using --renamedupmembers true
 */
public class wy {
    private static final String[] k = new String[]{"1.8.0"};
    private static final Gson ALLATORIxDEMO = new GsonBuilder().create();

    public wy() {
        wy a2;
    }

    /*
     * WARNING - void declaration
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    public Map<String, kq> ALLATORIxDEMO(ResourceLocation a2) throws to {
        try {
            Throwable throwable = null;
            try (IResource a3 = Minecraft.getMinecraft().getResourceManager().getResource(a2);){
                wy a4;
                InputStream a5 = a3.getInputStream();
                Map<String, kq> map = a4.ALLATORIxDEMO(a2, a5);
                return map;
            }
            catch (Throwable a5) {
                void var4_6;
                throwable = a5;
                throw var4_6;
            }
        }
        catch (Throwable a6) {
            if (!(a6 instanceof FileNotFoundException)) throw new to(a6);
            throw new to("\u672a\u627e\u5230\u6587\u4ef6", a6);
        }
    }

    public Map<String, kq> ALLATORIxDEMO(ResourceLocation a2, InputStream a3) throws to {
        wy a4;
        BufferedReader a5 = new BufferedReader(new InputStreamReader(a3, StandardCharsets.UTF_8));
        JsonObject a6 = (JsonObject)JsonUtils.gsonDeserialize((Gson)ALLATORIxDEMO, (Reader)a5, JsonObject.class, (boolean)true);
        return a4.ALLATORIxDEMO(a2, a6);
    }

    private /* synthetic */ Map<String, kq> ALLATORIxDEMO(ResourceLocation a3, JsonObject a4) throws to {
        wy a5;
        String a6;
        if (a4.has("format_version")) {
            a6 = a4.get("format_version").getAsString();
            a5.ALLATORIxDEMO(a6);
        }
        a6 = me.ALLATORIxDEMO("animations", a4).getAsJsonObject();
        LinkedHashMap<String, kq> a7 = new LinkedHashMap<String, kq>();
        for (Map.Entry a8 : a6.entrySet()) {
            Object a9;
            Object a102;
            int a11;
            String a12 = (String)a8.getKey();
            JsonElement a13 = (JsonElement)a8.getValue();
            boolean a14 = me.ALLATORIxDEMO("loop", a13, false);
            boolean a15 = me.ALLATORIxDEMO("override_previous_animation", a13, false);
            float a16 = me.ALLATORIxDEMO("blendWeight", a13, 1.0f);
            boolean a17 = false;
            if (!a14) {
                a17 = "hold_on_last_frame".equals(me.ALLATORIxDEMO("loop", a13, ""));
            }
            if ((a11 = (int)(me.ALLATORIxDEMO("animation_length", a13, 0.0f) * 1000.0f)) == 0) continue;
            ArrayList<Object> a18 = new ArrayList<Object>();
            JsonElement a19 = a13.getAsJsonObject().get("bones");
            if (a19 != null) {
                for (Object a102 : me.ALLATORIxDEMO("bones", a13.getAsJsonObject()).getAsJsonObject().entrySet()) {
                    String a20 = (String)a102.getKey();
                    Object a21 = ((JsonElement)a102.getValue()).getAsJsonObject();
                    a9 = a5.ALLATORIxDEMO(a20, (JsonObject)a21);
                    a18.add(a9);
                }
            }
            HashMap<Integer, List<kw>> a22 = new HashMap<Integer, List<kw>>();
            a102 = a13.getAsJsonObject().get("particle_effects");
            if (a102 != null) {
                for (Object a21 : a13.getAsJsonObject().get("particle_effects").getAsJsonObject().entrySet()) {
                    a9 = new ArrayList();
                    a22.put((int)(NumberUtils.toDouble((String)((String)a21.getKey())) * 1000.0), (List<kw>)a9);
                    if (((JsonElement)a21.getValue()).isJsonArray()) {
                        for (JsonElement a23 : ((JsonElement)a21.getValue()).getAsJsonArray()) {
                            a9.add(a5.ALLATORIxDEMO(a23.getAsJsonObject()));
                        }
                        continue;
                    }
                    a9.add(a5.ALLATORIxDEMO(((JsonElement)a21.getValue()).getAsJsonObject()));
                }
            }
            a7.put(a12, new fu(a14, a17, a15, a16, new ResourceLocation(a3.getNamespace(), a3.getPath() + "/" + a12), a12, a11, !a18.isEmpty() ? Collections.unmodifiableMap(a18.stream().collect(Collectors.toMap(hy::ALLATORIxDEMO, a2 -> a2))) : null, a22));
        }
        return a7;
    }

    private /* synthetic */ kw ALLATORIxDEMO(JsonObject a2) throws to {
        String a3 = me.ALLATORIxDEMO("effect", (JsonElement)a2);
        String a4 = me.ALLATORIxDEMO("locator", (JsonElement)a2);
        String a5 = me.ALLATORIxDEMO("pre_effect_script", (JsonElement)a2, null);
        return new kw(a3, a4, a5);
    }

    private /* synthetic */ hy ALLATORIxDEMO(String a3, JsonObject a4) throws to {
        wy a5;
        List<yz> a6 = a5.ALLATORIxDEMO("rotation", a4, a2 -> a2);
        List<yz> a7 = a5.ALLATORIxDEMO("position", a4, a2 -> {
            a2.reverseY();
            return a2;
        });
        List<yz> a8 = a5.ALLATORIxDEMO("scale", a4, a2 -> a2);
        return new hy(a3, a6, a7, a8);
    }

    private /* synthetic */ List<yz> ALLATORIxDEMO(String a2, JsonObject a3, Function<bax, bax> a4) throws to {
        ArrayList<yz> a5 = new ArrayList<yz>();
        if (a3.has(a2)) {
            JsonElement a6 = a3.get(a2);
            if (a6.isJsonArray()) {
                bax a7 = wy.ALLATORIxDEMO(raa.x, a6);
                a7 = a4.apply(a7);
                a5.add(new yz(0, a7));
            } else if (a6.isJsonObject()) {
                Set a8 = a6.getAsJsonObject().entrySet();
                for (Map.Entry a9 : a8) {
                    bax a10;
                    float a11;
                    if (a9.getValue() instanceof JsonObject) {
                        a11 = 0.0f;
                        try {
                            a11 = Float.parseFloat((String)a9.getKey());
                        }
                        catch (NumberFormatException a12) {
                            continue;
                        }
                        a10 = ((JsonElement)a9.getValue()).getAsJsonObject().getAsJsonArray("post");
                        JsonArray a13 = ((JsonElement)a9.getValue()).getAsJsonObject().getAsJsonArray("pre");
                        bax a14 = wy.ALLATORIxDEMO(raa.x, (JsonElement)a10);
                        bax a15 = a13 == null ? a14 : wy.ALLATORIxDEMO(raa.x, (JsonElement)a13);
                        String a16 = ((JsonElement)a9.getValue()).getAsJsonObject().has("lerp_mode") ? me.ALLATORIxDEMO("lerp_mode", (JsonElement)a9.getValue()) : "";
                        a14 = a4.apply(a14);
                        a5.add(new yz(a16, (int)(a11 * 1000.0f), a14, a15));
                        continue;
                    }
                    a11 = 0.0f;
                    try {
                        a11 = Float.parseFloat((String)a9.getKey());
                    }
                    catch (NumberFormatException a17) {
                        continue;
                    }
                    a10 = wy.ALLATORIxDEMO(raa.x, (JsonElement)a9.getValue());
                    a10 = a4.apply(a10);
                    a5.add(new yz((int)(a11 * 1000.0f), a10));
                }
            } else if (a6.isJsonPrimitive()) {
                float a18 = a6.getAsFloat();
                a5.add(new yz(0, new bax(a18, a18, a18)));
            }
        }
        a5.sort(Comparator.comparing(yz::ALLATORIxDEMO));
        if (a5.size() > 1) {
            a5.add(1, ((yz)a5.get(0)).ALLATORIxDEMO());
            a5.add(((yz)a5.get(a5.size() - 1)).ALLATORIxDEMO());
        }
        return !a5.isEmpty() ? Collections.unmodifiableList(a5) : null;
    }

    public static bax ALLATORIxDEMO(nd a2, JsonElement a3) throws to {
        if (a3 instanceof JsonArray) {
            JsonArray a4 = (JsonArray)a3;
            return new dg(wy.ALLATORIxDEMO(a2, a4.get(0)), wy.ALLATORIxDEMO(a2, a4.get(1)), wy.ALLATORIxDEMO(a2, a4.get(2)));
        }
        if (a3 instanceof JsonPrimitive) {
            JsonPrimitive a5 = (JsonPrimitive)a3;
            return new dg(wy.ALLATORIxDEMO(a2, (JsonElement)a5), wy.ALLATORIxDEMO(a2, (JsonElement)a5), wy.ALLATORIxDEMO(a2, (JsonElement)a5));
        }
        throw new to("Json\u65e0\u6cd5\u88ab\u8f6c\u6362\u4e3a\u6570\u7ec4\u6570\u636e: " + a3);
    }

    public static u ALLATORIxDEMO(nd a2, JsonElement a3) throws to {
        if (a3.getAsJsonPrimitive().isString()) {
            try {
                return a2.ALLATORIxDEMO(a3);
            }
            catch (hk a4) {
                throw new to("\u5f02\u5e38\u7684\u5904\u7406\u5f0f" + a3.getAsJsonPrimitive().toString(), a4);
            }
        }
        return hh.ALLATORIxDEMO(a3.getAsDouble());
    }

    private /* synthetic */ void ALLATORIxDEMO(String a2) throws to {
        if (!wa.ALLATORIxDEMO(k, a2)) {
            ca.l.z("[\u9519\u8bef] \u52a8\u753bjson\u7248\u672c: " + a2 + " \u672c\u6a21\u7ec4\u4ec5\u652f\u6301: " + Arrays.toString(k));
            throw new to("[\u9519\u8bef] \u52a8\u753bjson\u7248\u672c: " + a2 + " \u4f46\u672c\u6a21\u7ec4\u4ec5\u652f\u6301: " + Arrays.toString(k));
        }
    }
}

