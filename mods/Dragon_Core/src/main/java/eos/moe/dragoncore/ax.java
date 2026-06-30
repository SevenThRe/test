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
 */
package eos.moe.dragoncore;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;
import eos.moe.dragoncore.as;
import eos.moe.dragoncore.cq;
import eos.moe.dragoncore.cu;
import eos.moe.dragoncore.dx;
import eos.moe.dragoncore.gy;
import eos.moe.dragoncore.ix;
import eos.moe.dragoncore.ma;
import eos.moe.dragoncore.mq;
import eos.moe.dragoncore.tp;
import eos.moe.dragoncore.uw;
import eos.moe.dragoncore.xp;
import eos.moe.dragoncore.zv;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class ax {
    public static Gson ALLATORIxDEMO = new GsonBuilder().registerTypeAdapter(xp.class, (Object)new uw()).setPrettyPrinting().create();

    public ax() {
        ax a2;
    }

    public static dx ALLATORIxDEMO(InputStream a2) throws cq {
        xp a3 = (xp)ALLATORIxDEMO.fromJson((Reader)new InputStreamReader(a2), xp.class);
        LinkedHashMap<String, String> a4 = new LinkedHashMap<String, String>();
        for (gy a5 : a3.y) {
            a4.put(a5.ALLATORIxDEMO, a5.y);
        }
        return new dx(ax.ALLATORIxDEMO(a3, a4), ax.ALLATORIxDEMO(a3), a3.y, a3.ALLATORIxDEMO);
    }

    private static /* synthetic */ String ALLATORIxDEMO(xp a2, Map<String, String> a3) throws cq {
        ma a42;
        JsonArray a5 = new JsonArray();
        ArrayList<as> a6 = new ArrayList<as>();
        for (ma a42 : a2.o) {
            if (!(a42 instanceof as)) continue;
            a6.add((as)a42);
        }
        if (a6.size() > 0) {
            a5.add(ax.ALLATORIxDEMO(new tp("bb_main"), null, a6, a3));
        }
        for (ma a42 : a2.o) {
            if (!(a42 instanceof tp)) continue;
            ax.ALLATORIxDEMO(a5, (tp)a42, null, a3);
        }
        JsonObject a7 = new JsonObject();
        a42 = new JsonArray();
        JsonObject a8 = new JsonObject();
        JsonObject a9 = new JsonObject();
        a42.add((JsonElement)a8);
        a7.addProperty("format_version", "1.12.0");
        a7.add("minecraft:geometry", (JsonElement)a42);
        a8.add("description", (JsonElement)a9);
        a9.addProperty("texture_width", (Number)a2.c);
        a9.addProperty("texture_height", (Number)a2.q);
        a9.addProperty("identifier", "geometry.unknown");
        a8.add("bones", (JsonElement)a5);
        return a7.toString();
    }

    private static /* synthetic */ String ALLATORIxDEMO(xp a2) {
        JsonObject a3 = new JsonObject();
        for (Map.Entry<String, cu> a4 : a2.k.ALLATORIxDEMO().entrySet()) {
            JsonObject a5 = new JsonObject();
            JsonObject a6 = new JsonObject();
            a3.add(a4.getKey(), (JsonElement)a5);
            a5.add("bones", (JsonElement)a6);
            a5.addProperty("loop", a4.getValue().ALLATORIxDEMO());
            a5.addProperty("animation_length", (Number)Float.valueOf(a4.getValue().ALLATORIxDEMO()));
            for (Map.Entry<String, mq> a7 : a4.getValue().ALLATORIxDEMO().entrySet()) {
                JsonArray a8;
                JsonArray a9;
                JsonObject a10 = new JsonObject();
                a6.add(a7.getKey(), (JsonElement)a10);
                mq a11 = a7.getValue();
                JsonObject a12 = new JsonObject();
                JsonObject a13 = new JsonObject();
                JsonObject a14 = new JsonObject();
                for (Map.Entry<Float, ix> a15 : a11.f().entrySet()) {
                    if (a15.getValue().ALLATORIxDEMO().equals("linear")) {
                        a9 = new JsonArray();
                        a9.add(a15.getValue().ALLATORIxDEMO()[0]);
                        a9.add(a15.getValue().ALLATORIxDEMO()[1]);
                        a9.add(a15.getValue().ALLATORIxDEMO()[2]);
                        a12.add(String.valueOf(a15.getKey()), (JsonElement)a9);
                        continue;
                    }
                    a9 = new JsonObject();
                    a8 = new JsonArray();
                    a8.add(a15.getValue().ALLATORIxDEMO()[0]);
                    a8.add(a15.getValue().ALLATORIxDEMO()[1]);
                    a8.add(a15.getValue().ALLATORIxDEMO()[2]);
                    a9.add("post", (JsonElement)a8);
                    a9.addProperty("lerp_mode", a15.getValue().ALLATORIxDEMO());
                    a12.add(String.valueOf(a15.getKey()), (JsonElement)a9);
                }
                for (Map.Entry<Float, ix> a15 : a11.ALLATORIxDEMO().entrySet()) {
                    if (a15.getValue().ALLATORIxDEMO().equals("linear")) {
                        a9 = new JsonArray();
                        a9.add(a15.getValue().ALLATORIxDEMO()[0]);
                        a9.add(a15.getValue().ALLATORIxDEMO()[1]);
                        a9.add(a15.getValue().ALLATORIxDEMO()[2]);
                        a13.add(String.valueOf(a15.getKey()), (JsonElement)a9);
                        continue;
                    }
                    a9 = new JsonObject();
                    a8 = new JsonArray();
                    a8.add(a15.getValue().ALLATORIxDEMO()[0]);
                    a8.add(a15.getValue().ALLATORIxDEMO()[1]);
                    a8.add(a15.getValue().ALLATORIxDEMO()[2]);
                    a9.add("post", (JsonElement)a8);
                    a9.addProperty("lerp_mode", a15.getValue().ALLATORIxDEMO());
                    a13.add(String.valueOf(a15.getKey()), (JsonElement)a9);
                }
                for (Map.Entry<Float, ix> a15 : a11.c().entrySet()) {
                    if (a15.getValue().ALLATORIxDEMO().equals("linear")) {
                        a9 = new JsonArray();
                        a9.add(a15.getValue().ALLATORIxDEMO()[0]);
                        a9.add(a15.getValue().ALLATORIxDEMO()[1]);
                        a9.add(a15.getValue().ALLATORIxDEMO()[2]);
                        a14.add(String.valueOf(a15.getKey()), (JsonElement)a9);
                        continue;
                    }
                    a9 = new JsonObject();
                    a8 = new JsonArray();
                    a8.add(a15.getValue().ALLATORIxDEMO()[0]);
                    a8.add(a15.getValue().ALLATORIxDEMO()[1]);
                    a8.add(a15.getValue().ALLATORIxDEMO()[2]);
                    a9.add("post", (JsonElement)a8);
                    a9.addProperty("lerp_mode", a15.getValue().ALLATORIxDEMO());
                    a14.add(String.valueOf(a15.getKey()), (JsonElement)a9);
                }
                if (a12.size() > 0) {
                    a10.add("rotation", (JsonElement)a12);
                }
                if (a13.size() > 0) {
                    a10.add("position", (JsonElement)a13);
                }
                if (a14.size() <= 0) continue;
                a10.add("scale", (JsonElement)a14);
            }
        }
        JsonObject a16 = new JsonObject();
        a16.addProperty("format_version", "1.8.0");
        a16.add("animations", (JsonElement)a3);
        return a16.toString();
    }

    private static /* synthetic */ void ALLATORIxDEMO(JsonArray a2, tp a3, tp a4, Map<String, String> a5) throws cq {
        ArrayList<as> a6 = new ArrayList<as>();
        for (ma a7 : a3.ALLATORIxDEMO) {
            if (a7 instanceof tp) {
                ax.ALLATORIxDEMO(a2, (tp)a7, a3, a5);
                continue;
            }
            as a8 = (as)a7;
            a6.add(a8);
        }
        a2.add(ax.ALLATORIxDEMO(a3, a4, a6, a5));
    }

    private static /* synthetic */ JsonElement ALLATORIxDEMO(tp a2, tp a3, List<as> a4, Map<String, String> a5) throws cq {
        JsonArray a6;
        JsonObject a7 = new JsonObject();
        a7.addProperty("name", a2.b);
        if (a3 != null) {
            a7.addProperty("parent", a3.b);
        }
        JsonArray a8 = new JsonArray();
        a8.add((Number)Float.valueOf(-a2.o[0]));
        a8.add((Number)Float.valueOf(a2.o[1]));
        a8.add((Number)Float.valueOf(a2.o[2]));
        a7.add("pivot", (JsonElement)a8);
        if (!ax.ALLATORIxDEMO(a2.y)) {
            a6 = new JsonArray();
            a6.add((Number)Float.valueOf(-a2.y[0]));
            a6.add((Number)Float.valueOf(-a2.y[1]));
            a6.add((Number)Float.valueOf(a2.y[2]));
            a7.add("rotation", (JsonElement)a6);
        }
        a6 = new JsonArray();
        a7.add("cubes", (JsonElement)a6);
        for (as a9 : a4) {
            JsonArray a10;
            JsonObject a11 = new JsonObject();
            a6.add((JsonElement)a11);
            JsonArray a12 = new JsonArray();
            a12.add((Number)Float.valueOf(-Math.max(a9.c[0], a9.q[0])));
            a12.add((Number)Float.valueOf(Math.min(a9.c[1], a9.q[1])));
            a12.add((Number)Float.valueOf(Math.min(a9.c[2], a9.q[2])));
            a11.add("origin", (JsonElement)a12);
            JsonArray a13 = new JsonArray();
            a13.add((Number)Float.valueOf(Math.max(a9.c[0], a9.q[0]) - Math.min(a9.c[0], a9.q[0])));
            a13.add((Number)Float.valueOf(Math.max(a9.c[1], a9.q[1]) - Math.min(a9.c[1], a9.q[1])));
            a13.add((Number)Float.valueOf(Math.max(a9.c[2], a9.q[2]) - Math.min(a9.c[2], a9.q[2])));
            a11.add("size", (JsonElement)a13);
            JsonArray a14 = new JsonArray();
            a14.add((Number)Float.valueOf(-a9.o[0]));
            a14.add((Number)Float.valueOf(a9.o[1]));
            a14.add((Number)Float.valueOf(a9.o[2]));
            a11.add("pivot", (JsonElement)a14);
            if (!ax.ALLATORIxDEMO(a9.b)) {
                a10 = new JsonArray();
                a10.add((Number)Float.valueOf(-a9.b[0]));
                a10.add((Number)Float.valueOf(-a9.b[1]));
                a10.add((Number)Float.valueOf(a9.b[2]));
                a11.add("rotation", (JsonElement)a10);
            }
            a10 = new JsonObject();
            a11.add("uv", (JsonElement)a10);
            for (Map.Entry<String, zv> a15 : a9.k.entrySet()) {
                JsonObject a16 = new JsonObject();
                a10.add(a15.getKey(), (JsonElement)a16);
                JsonArray a17 = new JsonArray();
                a17.add((Number)Float.valueOf(Math.min(a15.getValue().y[0], a15.getValue().y[2])));
                a17.add((Number)Float.valueOf(Math.min(a15.getValue().y[1], a15.getValue().y[3])));
                a16.add("uv", (JsonElement)a17);
                JsonArray a18 = new JsonArray();
                a18.add((Number)Float.valueOf(Math.max(a15.getValue().y[0], a15.getValue().y[2]) - Math.min(a15.getValue().y[0], a15.getValue().y[2])));
                a18.add((Number)Float.valueOf(Math.max(a15.getValue().y[1], a15.getValue().y[3]) - Math.min(a15.getValue().y[1], a15.getValue().y[3])));
                a16.add("uv_size", (JsonElement)a18);
                if (!a5.containsKey(String.valueOf(a15.getValue().ALLATORIxDEMO))) continue;
                String a19 = a5.get(String.valueOf(a15.getValue().ALLATORIxDEMO));
                a16.add("texture", (JsonElement)new JsonPrimitive(a19));
            }
        }
        return a7;
    }

    private static /* synthetic */ boolean ALLATORIxDEMO(float[] a2) {
        return a2[0] == 0.0f && a2[1] == 0.0f && a2[2] == 0.0f;
    }
}

