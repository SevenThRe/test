/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  brs
 *  com.google.gson.JsonArray
 *  com.google.gson.JsonElement
 *  com.google.gson.JsonObject
 *  com.google.gson.JsonParseException
 *  com.google.gson.JsonParser
 *  nf
 */
package net.optifine.entity.model;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonParser;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import net.optifine.config.ConnectedParser;
import net.optifine.entity.model.CustomEntityModel;
import net.optifine.entity.model.CustomEntityRenderer;
import net.optifine.entity.model.CustomModelRenderer;
import net.optifine.entity.model.anim.ModelUpdater;
import net.optifine.entity.model.anim.ModelVariableUpdater;
import net.optifine.player.PlayerItemParser;
import net.optifine.util.Json;

public class CustomEntityModelParser {
    public static final String ENTITY = "entity";
    public static final String TEXTURE = "texture";
    public static final String SHADOW_SIZE = "shadowSize";
    public static final String ITEM_TYPE = "type";
    public static final String ITEM_TEXTURE_SIZE = "textureSize";
    public static final String ITEM_USE_PLAYER_TEXTURE = "usePlayerTexture";
    public static final String ITEM_MODELS = "models";
    public static final String ITEM_ANIMATIONS = "animations";
    public static final String MODEL_ID = "id";
    public static final String MODEL_BASE_ID = "baseId";
    public static final String MODEL_MODEL = "model";
    public static final String MODEL_TYPE = "type";
    public static final String MODEL_PART = "part";
    public static final String MODEL_ATTACH = "attach";
    public static final String MODEL_INVERT_AXIS = "invertAxis";
    public static final String MODEL_MIRROR_TEXTURE = "mirrorTexture";
    public static final String MODEL_TRANSLATE = "translate";
    public static final String MODEL_ROTATE = "rotate";
    public static final String MODEL_SCALE = "scale";
    public static final String MODEL_BOXES = "boxes";
    public static final String MODEL_SPRITES = "sprites";
    public static final String MODEL_SUBMODEL = "submodel";
    public static final String MODEL_SUBMODELS = "submodels";
    public static final String BOX_TEXTURE_OFFSET = "textureOffset";
    public static final String BOX_COORDINATES = "coordinates";
    public static final String BOX_SIZE_ADD = "sizeAdd";
    public static final String ENTITY_MODEL = "EntityModel";
    public static final String ENTITY_MODEL_PART = "EntityModelPart";

    public static CustomEntityRenderer parseEntityRender(JsonObject obj, String path) {
        ConnectedParser cp = new ConnectedParser("CustomEntityModels");
        String name = cp.parseName(path);
        String basePath = cp.parseBasePath(path);
        String texture = Json.getString(obj, TEXTURE);
        int[] textureSize = Json.parseIntArray(obj.get(ITEM_TEXTURE_SIZE), 2);
        float shadowSize = Json.getFloat(obj, SHADOW_SIZE, -1.0f);
        JsonArray models = (JsonArray)obj.get(ITEM_MODELS);
        CustomEntityModelParser.checkNull(models, "Missing models");
        HashMap mapModelJsons = new HashMap();
        ArrayList<CustomModelRenderer> listModels = new ArrayList<CustomModelRenderer>();
        for (int i = 0; i < models.size(); ++i) {
            JsonObject elem = (JsonObject)models.get(i);
            CustomEntityModelParser.processBaseId(elem, mapModelJsons);
            CustomEntityModelParser.processExternalModel(elem, mapModelJsons, basePath);
            CustomEntityModelParser.processId(elem, mapModelJsons);
            CustomModelRenderer mr = CustomEntityModelParser.parseCustomModelRenderer(elem, textureSize, basePath);
            if (mr == null) continue;
            listModels.add(mr);
        }
        CustomModelRenderer[] modelRenderers = listModels.toArray(new CustomModelRenderer[listModels.size()]);
        nf textureLocation = null;
        if (texture != null) {
            textureLocation = CustomEntityModelParser.getResourceLocation(basePath, texture, ".png");
        }
        CustomEntityRenderer cer2 = new CustomEntityRenderer(name, basePath, textureLocation, modelRenderers, shadowSize);
        return cer2;
    }

    private static void processBaseId(JsonObject elem, Map mapModelJsons) {
        String baseId = Json.getString(elem, MODEL_BASE_ID);
        if (baseId == null) {
            return;
        }
        JsonObject baseObj = (JsonObject)mapModelJsons.get(baseId);
        if (baseObj == null) {
            Config.warn("BaseID not found: " + baseId);
            return;
        }
        CustomEntityModelParser.copyJsonElements(baseObj, elem);
    }

    private static void processExternalModel(JsonObject elem, Map mapModelJsons, String basePath) {
        String modelPath = Json.getString(elem, MODEL_MODEL);
        if (modelPath == null) {
            return;
        }
        nf locJson = CustomEntityModelParser.getResourceLocation(basePath, modelPath, ".jpm");
        try {
            JsonObject modelObj = CustomEntityModelParser.loadJson(locJson);
            if (modelObj == null) {
                Config.warn("Model not found: " + locJson);
                return;
            }
            CustomEntityModelParser.copyJsonElements(modelObj, elem);
        }
        catch (IOException e) {
            Config.error("" + e.getClass().getName() + ": " + e.getMessage());
        }
        catch (JsonParseException e) {
            Config.error("" + ((Object)((Object)e)).getClass().getName() + ": " + e.getMessage());
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void copyJsonElements(JsonObject objFrom, JsonObject objTo) {
        Set setEntries = objFrom.entrySet();
        for (Map.Entry entry : setEntries) {
            if (((String)entry.getKey()).equals(MODEL_ID) || objTo.has((String)entry.getKey())) continue;
            objTo.add((String)entry.getKey(), (JsonElement)entry.getValue());
        }
    }

    public static nf getResourceLocation(String basePath, String path, String extension) {
        if (!path.endsWith(extension)) {
            path = path + extension;
        }
        if (!path.contains("/")) {
            path = basePath + "/" + path;
        } else if (path.startsWith("./")) {
            path = basePath + "/" + path.substring(2);
        } else if (path.startsWith("~/")) {
            path = "optifine/" + path.substring(2);
        }
        return new nf(path);
    }

    private static void processId(JsonObject elem, Map mapModelJsons) {
        String id = Json.getString(elem, MODEL_ID);
        if (id == null) {
            return;
        }
        if (id.length() < 1) {
            Config.warn("Empty model ID: " + id);
            return;
        }
        if (mapModelJsons.containsKey(id)) {
            Config.warn("Duplicate model ID: " + id);
            return;
        }
        mapModelJsons.put(id, elem);
    }

    public static CustomModelRenderer parseCustomModelRenderer(JsonObject elem, int[] textureSize, String basePath) {
        String modelPart = Json.getString(elem, MODEL_PART);
        CustomEntityModelParser.checkNull(modelPart, "Model part not specified, missing \"replace\" or \"attachTo\".");
        boolean attach = Json.getBoolean(elem, MODEL_ATTACH, false);
        CustomEntityModel modelBase = new CustomEntityModel();
        if (textureSize != null) {
            modelBase.s = textureSize[0];
            modelBase.t = textureSize[1];
        }
        ModelUpdater mu = null;
        JsonArray animations = (JsonArray)elem.get(ITEM_ANIMATIONS);
        if (animations != null) {
            ArrayList<ModelVariableUpdater> listModelVariableUpdaters = new ArrayList<ModelVariableUpdater>();
            for (int i = 0; i < animations.size(); ++i) {
                JsonObject anim = (JsonObject)animations.get(i);
                Set entries = anim.entrySet();
                for (Map.Entry entry : entries) {
                    String key = (String)entry.getKey();
                    String val = ((JsonElement)entry.getValue()).getAsString();
                    ModelVariableUpdater mvu = new ModelVariableUpdater(key, val);
                    listModelVariableUpdaters.add(mvu);
                }
            }
            if (listModelVariableUpdaters.size() > 0) {
                ModelVariableUpdater[] mvus = listModelVariableUpdaters.toArray(new ModelVariableUpdater[listModelVariableUpdaters.size()]);
                mu = new ModelUpdater(mvus);
            }
        }
        brs mr = PlayerItemParser.parseModelRenderer(elem, modelBase, textureSize, basePath);
        CustomModelRenderer cmr = new CustomModelRenderer(modelPart, attach, mr, mu);
        return cmr;
    }

    private static void checkNull(Object obj, String msg) {
        if (obj == null) {
            throw new JsonParseException(msg);
        }
    }

    public static JsonObject loadJson(nf location) throws IOException, JsonParseException {
        InputStream in = Config.getResourceStream(location);
        if (in == null) {
            return null;
        }
        String jsonStr = Config.readInputStream(in, "ASCII");
        in.close();
        JsonParser jp = new JsonParser();
        JsonObject jo = (JsonObject)jp.parse(jsonStr);
        return jo;
    }
}

