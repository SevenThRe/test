/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.collect.Lists
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
 */
package eos.moe.dragoncore;

import com.google.common.collect.Lists;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;
import eos.moe.dragoncore.ModelLocator;
import eos.moe.dragoncore.acf;
import eos.moe.dragoncore.ag;
import eos.moe.dragoncore.bax;
import eos.moe.dragoncore.bf;
import eos.moe.dragoncore.ca;
import eos.moe.dragoncore.ce;
import eos.moe.dragoncore.gv;
import eos.moe.dragoncore.jv;
import eos.moe.dragoncore.me;
import eos.moe.dragoncore.to;
import eos.moe.dragoncore.wa;
import eos.moe.dragoncore.wq;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.Reader;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.IResource;
import net.minecraft.util.JsonUtils;
import net.minecraft.util.ResourceLocation;

public class gx {
    private static final String[] y = new String[]{"1.12.0", "1.10.0", "1.8.0"};
    private static final Gson k = new GsonBuilder().create();
    private String ALLATORIxDEMO;

    public gx() {
        gx a2;
    }

    /*
     * WARNING - void declaration
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    public List<jv> ALLATORIxDEMO(ResourceLocation a2) throws to {
        try {
            Throwable throwable = null;
            try (IResource a3 = Minecraft.func_71410_x().func_110442_L().func_110536_a(a2);){
                gx a4;
                InputStream a5 = a3.func_110527_b();
                byte[] a6 = gx.ALLATORIxDEMO(a5);
                List<ce> a7 = null;
                if (ca.m != null && wq.k != null) {
                    a7 = ca.m.ab(a6);
                }
                if (a7 == null || a7.isEmpty()) {
                    a7 = a4.ALLATORIxDEMO(new ByteArrayInputStream(a6));
                }
                List<jv> list = a7.stream().map(a4::ALLATORIxDEMO).collect(Collectors.toList());
                return list;
            }
            catch (Throwable a5) {
                void var4_6;
                throwable = a5;
                throw var4_6;
            }
        }
        catch (Throwable a8) {
            if (!(a8 instanceof FileNotFoundException)) throw new to(a8);
            throw new to("\u672a\u627e\u5230\u6587\u4ef6", a8);
        }
    }

    public List<jv> c(byte[] a2) {
        try {
            gx a3;
            List<ce> a4 = a3.ALLATORIxDEMO(new ByteArrayInputStream(a2));
            return a4.stream().map(a3::ALLATORIxDEMO).collect(Collectors.toList());
        }
        catch (Throwable a5) {
            a5.printStackTrace();
            return null;
        }
    }

    public List<jv> ALLATORIxDEMO(byte[] a2) throws to {
        try {
            gx a3;
            ObjectInputStream a4 = new ObjectInputStream(new ByteArrayInputStream(a2));
            int a5 = a4.readInt();
            ArrayList<ce> a6 = new ArrayList<ce>();
            for (int a7 = 0; a7 < a5; ++a7) {
                a6.add((ce)a4.readObject());
            }
            return a6.stream().map(a3::ALLATORIxDEMO).collect(Collectors.toList());
        }
        catch (Throwable a8) {
            if (a8 instanceof FileNotFoundException) {
                throw new to("\u672a\u627e\u5230\u6587\u4ef6", a8);
            }
            throw new to(a8);
        }
    }

    public static byte[] ALLATORIxDEMO(InputStream a2) {
        try {
            int a3;
            ByteArrayOutputStream a4 = new ByteArrayOutputStream();
            while ((a3 = a2.read()) != -1) {
                a4.write(a3);
            }
            byte[] a5 = a4.toByteArray();
            a4.close();
            a2.close();
            return a5;
        }
        catch (Exception a6) {
            a6.printStackTrace();
            return null;
        }
    }

    public List<ce> ALLATORIxDEMO(InputStream a2) throws to {
        gx a3;
        BufferedReader a4 = new BufferedReader(new InputStreamReader(a2));
        JsonObject a5 = (JsonObject)JsonUtils.func_188173_a((Gson)k, (Reader)a4, JsonObject.class, (boolean)true);
        return a3.ALLATORIxDEMO(a5);
    }

    private /* synthetic */ List<ce> ALLATORIxDEMO(JsonObject a2) throws to {
        ArrayList<ce> a3 = new ArrayList<ce>();
        for (Map.Entry a4 : a2.entrySet()) {
            Object a5;
            gx a6;
            if (((String)a4.getKey()).equals("format_version")) {
                a6.ALLATORIxDEMO = ((JsonElement)a4.getValue()).getAsString();
                a6.ALLATORIxDEMO();
                continue;
            }
            if (a6.ALLATORIxDEMO == null || "1.8.0".equals(a6.ALLATORIxDEMO) || "1.10.0".equals(a6.ALLATORIxDEMO)) {
                a5 = new JsonArray();
                a5.add((JsonElement)a4.getValue());
                ce a7 = a6.ALLATORIxDEMO((String)a4.getKey(), (JsonArray)a5);
                a3.add(a7);
                continue;
            }
            a5 = a6.ALLATORIxDEMO((String)a4.getKey(), ((JsonElement)a4.getValue()).getAsJsonArray());
            a3.add((ce)a5);
        }
        return a3;
    }

    private /* synthetic */ ce ALLATORIxDEMO(String a2, JsonArray a3) throws to {
        int a4;
        int a5;
        Object a6;
        JsonObject a7 = a3.get(0).getAsJsonObject();
        JsonArray a8 = a7.get("bones").getAsJsonArray();
        if (a7.has("description")) {
            a6 = a7.get("description").getAsJsonObject();
            a5 = me.ALLATORIxDEMO("texture_width", (JsonElement)a6);
            a4 = me.ALLATORIxDEMO("texture_height", (JsonElement)a6);
        } else {
            a5 = me.ALLATORIxDEMO("texturewidth", (JsonElement)a7);
            a4 = me.ALLATORIxDEMO("textureheight", (JsonElement)a7);
        }
        a6 = new HashMap();
        HashMap<String, bf> a9 = new HashMap<String, bf>();
        for (JsonElement a10 : a8) {
            gx a11;
            bf a12;
            if (a10 instanceof JsonPrimitive) {
                a12 = (bf)k.fromJson(a10.getAsString(), bf.class);
                a9.put(a12.m, a12);
                if (a12.locatorMap == null) continue;
                ((HashMap)a6).putAll(a12.locatorMap);
                continue;
            }
            a12 = a11.ALLATORIxDEMO(a10);
            a9.put(a12.m, a12);
            if (a12.locatorMap == null) continue;
            ((HashMap)a6).putAll(a12.locatorMap);
        }
        ArrayList<bf> a13 = new ArrayList<bf>();
        for (bf a12 : a9.values()) {
            if (a12.t != null) {
                bf a14 = (bf)a9.get(a12.t);
                if (a14 != null) {
                    if (a14.r == null) {
                        a14.r = new ArrayList<bf>();
                    }
                    a14.r.add(a12);
                    continue;
                }
                throw new to("\u65e0\u6cd5\u627e\u5230\u5728\u5df2\u52a0\u8f7d\u9aa8\u9abc\u5185\u627e\u5230" + a12.m + "\u7684\u7236\u9aa8\u9abc(" + a12.t + ")");
            }
            a13.add(a12);
        }
        return new ce(a2, a5, a4, a13, (Map<String, ModelLocator>)a6);
    }

    public jv ALLATORIxDEMO(ce a2) {
        jv a4 = new jv(a2.n, a2.i, a2.w, a2.locatorMap);
        a4.setPieces(a2.b.stream().map(a3 -> a3.bake(a4, null)).collect(Collectors.toList()));
        return a4;
    }

    private /* synthetic */ bf ALLATORIxDEMO(JsonElement a2) throws to {
        bax a3;
        Object a42;
        JsonObject a5;
        bax a6 = me.ALLATORIxDEMO("pivot", a2, new bax(0.0f, 0.0f, 0.0f));
        bax a7 = me.ALLATORIxDEMO("rotation", a2, new bax(0.0f, 0.0f, 0.0f));
        boolean a8 = me.ALLATORIxDEMO("mirror", a2, false);
        boolean a9 = me.ALLATORIxDEMO("neverrender", a2, false);
        float a10 = me.ALLATORIxDEMO("inflate", a2, 0.0f);
        String a11 = me.ALLATORIxDEMO("name", a2);
        HashMap<String, ModelLocator> a12 = new HashMap<String, ModelLocator>();
        String a13 = me.ALLATORIxDEMO("parent", a2, null);
        if (a2.getAsJsonObject().has("locators")) {
            a5 = a2.getAsJsonObject().get("locators").getAsJsonObject();
            for (Object a42 : a5.entrySet()) {
                if (((JsonElement)a42.getValue()).isJsonArray()) {
                    a12.put((String)a42.getKey(), new ModelLocator(a11, me.ALLATORIxDEMO((JsonElement)a42.getValue())));
                    continue;
                }
                Object a14 = me.ALLATORIxDEMO("offset", (JsonElement)a42.getValue(), new bax(0.0f, 0.0f, 0.0f));
                a3 = me.ALLATORIxDEMO("rotation", (JsonElement)a42.getValue(), new bax(0.0f, 0.0f, 0.0f));
                a12.put((String)a42.getKey(), new ModelLocator(a11, (bax)a14, a3));
            }
        }
        a5 = new ArrayList();
        ArrayList<gv> a15 = new ArrayList<gv>();
        if (a2.getAsJsonObject().has("cubes")) {
            for (Object a14 : a2.getAsJsonObject().get("cubes").getAsJsonArray()) {
                Serializable a16;
                Serializable a17;
                Serializable a18;
                a3 = me.ALLATORIxDEMO("origin", a14, new bax(0.0f, 0.0f, 0.0f));
                bax a19 = me.ALLATORIxDEMO("size", a14, new bax(0.0f, 0.0f, 0.0f));
                JsonElement a20 = me.ALLATORIxDEMO("uv", a14.getAsJsonObject());
                if (!(a20 instanceof JsonArray)) {
                    a18 = new ag(a20, "north");
                    a17 = new ag(a20, "east");
                    a16 = new ag(a20, "south");
                    ag a21 = new ag(a20, "west");
                    ag a22 = new ag(a20, "up");
                    ag a23 = new ag(a20, "down");
                    ArrayList a24 = Lists.newArrayList((Object[])new ag[]{a18, a17, a16, a21, a22, a23});
                    if (a14.getAsJsonObject().has("rotation") || a14.getAsJsonObject().has("inflate") || a14.getAsJsonObject().has("mirror")) {
                        bax a25 = me.ALLATORIxDEMO("rotation", a14, new bax(0.0f, 0.0f, 0.0f));
                        bax a26 = me.ALLATORIxDEMO("pivot", a14, new bax(0.0f, 0.0f, 0.0f));
                        boolean a27 = me.ALLATORIxDEMO("mirror", a14, false);
                        float a28 = me.ALLATORIxDEMO("inflate", a14, 0.0f);
                        a5.add(new bf(Lists.newArrayList((Object[])new gv[]{new gv(a3, a19, a24)}), a26, a25, a27, false, a28, "cube_wrapper_" + a5.size(), a11));
                        continue;
                    }
                    a15.add(new gv(a3, a19, a24));
                    continue;
                }
                a18 = me.ALLATORIxDEMO("uv", a14, new acf());
                if (a14.getAsJsonObject().has("rotation") || a14.getAsJsonObject().has("inflate") || a14.getAsJsonObject().has("mirror")) {
                    a17 = me.ALLATORIxDEMO("rotation", a14, new bax(0.0f, 0.0f, 0.0f));
                    a16 = me.ALLATORIxDEMO("pivot", a14, new bax(0.0f, 0.0f, 0.0f));
                    boolean a29 = me.ALLATORIxDEMO("mirror", a14, false);
                    float a30 = me.ALLATORIxDEMO("inflate", a14, 0.0f);
                    a5.add(new bf(Lists.newArrayList((Object[])new gv[]{new gv(a3, a19, (acf)a18)}), (bax)a16, (bax)a17, a29, false, a30, "cube_wrapper_" + a5.size(), a11));
                    continue;
                }
                a15.add(new gv(a3, a19, (acf)a18));
            }
        }
        a42 = new bf(a15, a6, a7, a8, a9, a10, a11, a13);
        ((bf)a42).r = a5;
        ((bf)a42).locatorMap = a12;
        return a42;
    }

    private /* synthetic */ void ALLATORIxDEMO() throws to {
        gx a2;
        if (!wa.ALLATORIxDEMO(y, a2.ALLATORIxDEMO)) {
            ca.l.z("\u9519\u8bef: \u6a21\u578b\u7684\u7248\u672c" + a2.ALLATORIxDEMO + ",\u672cmod\u4ec5\u652f\u6301" + Arrays.toString(y));
            throw new to("\u9519\u8bef: \u6a21\u578b\u7684\u7248\u672c" + a2.ALLATORIxDEMO + ",\u672cmod\u4ec5\u652f\u6301" + Arrays.toString(y));
        }
    }
}

