/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.util.concurrent.ListenableFuture
 *  com.google.gson.Gson
 *  com.google.gson.JsonArray
 *  com.google.gson.JsonElement
 *  com.google.gson.JsonObject
 *  com.google.gson.JsonPrimitive
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.renderer.texture.PngSizeInfo
 *  net.minecraft.client.renderer.vertex.DefaultVertexFormats
 *  net.minecraft.client.resources.data.AnimationFrame
 *  net.minecraft.client.resources.data.AnimationMetadataSection
 *  net.minecraft.util.ResourceLocation
 *  net.minecraftforge.client.model.IModel
 *  net.minecraftforge.client.model.ModelLoaderRegistry
 *  org.apache.commons.lang3.math.NumberUtils
 */
package eos.moe.armourers;

import com.google.common.util.concurrent.ListenableFuture;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;
import eos.moe.armourers.gg;
import eos.moe.armourers.uk;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.texture.PngSizeInfo;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.client.resources.data.AnimationFrame;
import net.minecraft.client.resources.data.AnimationMetadataSection;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.IModel;
import net.minecraftforge.client.model.ModelLoaderRegistry;
import org.apache.commons.lang3.math.NumberUtils;

public class ModelData {
    private byte[] animationBytes;
    private double scale;
    private String name;
    private gg transformBakedModel;
    private Texture textureBytes;
    private Map<String, Boolean> setting;
    private byte[] modelBytes;
    private Texture glowTextureBytes;
    private String skinType;
    private static Gson gson = new Gson();
    private double trans;

    public byte[] getModelBytes() {
        ModelData a2;
        return a2.modelBytes;
    }

    public String getName() {
        ModelData a2;
        return a2.name;
    }

    private static /* synthetic */ ListenableFuture<gg> loadModel(String a2) throws Exception {
        return Minecraft.func_71410_x().func_152343_a(() -> {
            IModel iModel;
            IModel iModel2 = iModel = ModelLoaderRegistry.getModel((ResourceLocation)new ResourceLocation("dragonarmourers", a2));
            return new gg(iModel2.bake(iModel2.getDefaultState(), DefaultVertexFormats.field_176599_b, a2 -> null));
        });
    }

    public gg getTransformBakedModel() {
        ModelData a2;
        return a2.transformBakedModel;
    }

    public Map<String, Boolean> getSetting() {
        ModelData a2;
        return a2.setting;
    }

    public String getSkinType() {
        ModelData a2;
        return a2.skinType;
    }

    public double getTrans() {
        ModelData a2;
        return a2.trans;
    }

    public Texture getTexture() {
        ModelData a2;
        return a2.textureBytes;
    }

    public static String serialize(AnimationMetadataSection a2) {
        JsonArray jsonArray;
        JsonObject jsonObject = new JsonObject();
        AnimationMetadataSection animationMetadataSection = a2;
        jsonObject.addProperty("frametime", (Number)animationMetadataSection.func_110469_d());
        if (animationMetadataSection.func_110473_c() > 0) {
            int n2;
            jsonArray = new JsonArray();
            int n3 = n2 = 0;
            while (n3 < a2.func_110473_c()) {
                int n4 = a2.func_110468_c(n2);
                jsonArray.add((JsonElement)new JsonPrimitive((Number)n4));
                n3 = ++n2;
            }
            jsonObject.add("frames", (JsonElement)jsonArray);
        }
        jsonArray = new JsonObject();
        jsonArray.add("animation", (JsonElement)jsonObject);
        return jsonArray.toString();
    }

    public ModelData(String a2, byte[] a3) throws Exception {
        Object object;
        Object object2;
        JsonObject jsonObject;
        ModelData a4;
        block21: {
            ModelData modelData;
            block20: {
                ModelData modelData2 = a4;
                a4.scale = 1.0;
                modelData2.trans = 0.0;
                ModelData modelData3 = a4;
                modelData2.setting = new HashMap<String, Boolean>();
                modelData2.name = a2;
                JsonObject jsonObject2 = (JsonObject)gson.fromJson(new String((byte[])a3, StandardCharsets.UTF_8), JsonObject.class);
                a3 = jsonObject2;
                if (jsonObject2.has("setting")) {
                    JsonObject jsonObject3;
                    block19: {
                        jsonObject = a3.getAsJsonObject("setting");
                        for (Map.Entry entry : jsonObject.entrySet()) {
                            if (!((JsonElement)entry.getValue()).isJsonPrimitive()) continue;
                            a4.setting.put((String)entry.getKey(), ((JsonElement)entry.getValue()).getAsBoolean());
                        }
                        if (jsonObject.has("type")) {
                            a4.skinType = jsonObject.get("type").getAsString();
                        }
                        if (jsonObject.has("scale")) {
                            try {
                                a4.scale = jsonObject.get("scale").getAsDouble();
                                jsonObject3 = jsonObject;
                                break block19;
                            }
                            catch (NumberFormatException numberFormatException) {
                                // empty catch block
                            }
                        }
                        jsonObject3 = jsonObject;
                    }
                    if (jsonObject3.has("offset")) {
                        try {
                            a4.trans = jsonObject.get("offset").getAsDouble();
                            modelData = a4;
                            break block20;
                        }
                        catch (NumberFormatException numberFormatException) {
                            // empty catch block
                        }
                    }
                }
                modelData = a4;
            }
            if (modelData.skinType == null) {
                a4.skinType = "outfit";
            }
            if (a3.has("display")) {
                JsonObject jsonObject4 = jsonObject = new JsonObject();
                jsonObject4.add("parent", (JsonElement)new JsonPrimitive("builtin/entity"));
                jsonObject4.add("display", a3.get("display"));
                uk.j.put(new StringBuilder().insert(0, "models/").append(a2).append(".json").toString(), jsonObject.toString().getBytes(StandardCharsets.UTF_8));
                try {
                    a4.transformBakedModel = (gg)ModelData.loadModel(a2).get();
                    object2 = a3;
                    break block21;
                }
                catch (Exception exception) {
                    exception.printStackTrace();
                }
            }
            object2 = a3;
        }
        ModelData modelData = a4;
        if (object2.has("model")) {
            modelData.modelBytes = Base64.getDecoder().decode(a3.get("model").getAsString());
            object = a3;
        } else {
            modelData.modelBytes = new byte[0];
            object = a3;
        }
        if (object.has("animation")) {
            a4.animationBytes = Base64.getDecoder().decode(a3.get("animation").getAsString());
        }
        if (a3.has("textures")) {
            jsonObject = a3.getAsJsonArray("textures");
            for (Map.Entry entry : jsonObject) {
                if (!(entry instanceof JsonObject)) continue;
                a2 = (JsonObject)entry;
                String string = a2.get("name").getAsString();
                a3 = string;
                if (string.contains("_e") || ((String)a3).contains("glow")) {
                    a4.glowTextureBytes = new Texture((JsonObject)a2);
                    continue;
                }
                a4.textureBytes = new Texture((JsonObject)a2);
            }
        }
    }

    public Texture getGlowTexture() {
        ModelData a2;
        return a2.glowTextureBytes;
    }

    public boolean getSetting(String a2) {
        ModelData a3;
        return a3.setting.getOrDefault(a2, Boolean.FALSE);
    }

    public byte[] getAnimationBytes() {
        ModelData a2;
        return a2.animationBytes;
    }

    public double getScale() {
        ModelData a2;
        return a2.scale;
    }

    public static class Texture {
        private byte[] data;
        private String meta;
        private String name;

        /*
         * Unable to fully structure code
         */
        public Texture(JsonObject a) throws IOException {
            block24: {
                v0 = a;
                super();
                a.name = v0.get("name").getAsString();
                a.data = Base64.getDecoder().decode(a.get("texture").getAsString().split(",")[1]);
                if (v0.has("mcmeta")) {
                    a.meta = a.get("mcmeta").getAsString();
                    return;
                }
                if (!a.has("frame_order_type") || a.get("frame_order_type").isJsonNull()) break block24;
                v1 = new PngSizeInfo((InputStream)new ByteArrayInputStream(a.data));
                var2_2 = v1.field_188534_b / v1.field_188533_a;
                v2 = a;
                var3_4 = v2.get("frame_order_type").getAsString();
                var4_6 = v2.get("frame_time").getAsInt();
                var5_8 = v2.get("frame_interpolate").getAsBoolean();
                var6_9 = new ArrayList<AnimationFrame>();
                var4_7 = new AnimationMetadataSection(var6_9, -1, -1, var4_6, (boolean)var5_8);
                var5_8 = -1;
                switch (var3_4.hashCode()) {
                    case 3327652: {
                        if (var3_4.equals("loop")) {
                            v3 = var5_8 = 0;
                            break;
                        }
                        ** GOTO lbl38
                    }
                    case 1356771568: {
                        if (var3_4.equals("backwards")) {
                            v3 = var5_8 = 1;
                            break;
                        }
                        ** GOTO lbl38
                    }
                    case -710691011: {
                        if (var3_4.equals("back_and_forth")) {
                            v3 = var5_8 = 2;
                            break;
                        }
                        ** GOTO lbl38
                    }
lbl34:
                    // 2 sources

                    case -1349088399: {
                        if (false) ** GOTO lbl34
                        if (var3_4.equals("custom")) {
                            var5_8 = 3;
                        }
                    }
lbl38:
                    // 7 sources

                    default: {
                        v3 = var5_8;
                    }
                }
                switch (v3) lbl-1000:
                // 2 sources

                {
                    case 0: {
                        if (false) ** GOTO lbl-1000
                        a.meta = ModelData.serialize(var4_7);
                        return;
                    }
                    case 1: {
                        v4 = var7_10 = var2_2 - 1;
                        while (v4 >= var2_2) {
                            var6_9.add(new AnimationFrame(var7_10--, -1));
                            v4 = var7_10;
                        }
                        a.meta = ModelData.serialize(var4_7);
                        return;
                    }
                    case 2: {
                        v5 = var7_11 = 0;
                        while (v5 < var2_2) {
                            var6_9.add(new AnimationFrame(var7_11++, -1));
                            v5 = var7_11;
                        }
                        v6 = var7_11 = var2_2 - 2;
                        while (v6 > 0) {
                            var6_9.add(new AnimationFrame(var7_11--, -1));
                            v6 = var7_11;
                        }
                        a.meta = ModelData.serialize(var4_7);
                        return;
                    }
                    case 3: {
                        var7_12 = a.get("frame_order");
                        var2_3 = var7_12.getAsString();
                        if (!var2_3.isEmpty()) {
                            var2_3 = var2_3.split(" ");
                            var3_5 = var2_3.length;
                            v7 = var5_8 = 0;
                            while (v7 < var3_5) {
                                var7_13 = NumberUtils.toInt((String)var2_3[var5_8]);
                                var6_9.add(new AnimationFrame(var7_13, -1));
                                v7 = ++var5_8;
                            }
                        }
                        a.meta = ModelData.serialize(var4_7);
                    }
                }
            }
        }

        public String getMeta() {
            Texture a2;
            return a2.meta;
        }

        public String getName() {
            Texture a2;
            return a2.name;
        }

        public byte[] getData() {
            Texture a2;
            return a2.data;
        }
    }
}

