/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  bqf
 *  brs
 *  com.google.gson.JsonArray
 *  com.google.gson.JsonElement
 *  com.google.gson.JsonObject
 *  com.google.gson.JsonParseException
 *  com.google.gson.JsonParser
 *  nf
 *  rk
 */
package net.optifine.player;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonParser;
import java.awt.Dimension;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import net.optifine.entity.model.CustomEntityModelParser;
import net.optifine.player.ModelPlayerItem;
import net.optifine.player.PlayerItemModel;
import net.optifine.player.PlayerItemRenderer;
import net.optifine.util.Json;

public class PlayerItemParser {
    private static JsonParser jsonParser = new JsonParser();
    public static final String ITEM_TYPE = "type";
    public static final String ITEM_TEXTURE_SIZE = "textureSize";
    public static final String ITEM_USE_PLAYER_TEXTURE = "usePlayerTexture";
    public static final String ITEM_MODELS = "models";
    public static final String MODEL_ID = "id";
    public static final String MODEL_BASE_ID = "baseId";
    public static final String MODEL_TYPE = "type";
    public static final String MODEL_TEXTURE = "texture";
    public static final String MODEL_TEXTURE_SIZE = "textureSize";
    public static final String MODEL_ATTACH_TO = "attachTo";
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
    public static final String BOX_UV_DOWN = "uvDown";
    public static final String BOX_UV_UP = "uvUp";
    public static final String BOX_UV_NORTH = "uvNorth";
    public static final String BOX_UV_SOUTH = "uvSouth";
    public static final String BOX_UV_WEST = "uvWest";
    public static final String BOX_UV_EAST = "uvEast";
    public static final String BOX_UV_FRONT = "uvFront";
    public static final String BOX_UV_BACK = "uvBack";
    public static final String BOX_UV_LEFT = "uvLeft";
    public static final String BOX_UV_RIGHT = "uvRight";
    public static final String ITEM_TYPE_MODEL = "PlayerItem";
    public static final String MODEL_TYPE_BOX = "ModelBox";

    private PlayerItemParser() {
    }

    public static PlayerItemModel parseItemModel(JsonObject obj) {
        String type = Json.getString(obj, "type");
        if (!Config.equals(type, ITEM_TYPE_MODEL)) {
            throw new JsonParseException("Unknown model type: " + type);
        }
        int[] textureSize = Json.parseIntArray(obj.get("textureSize"), 2);
        PlayerItemParser.checkNull(textureSize, "Missing texture size");
        Dimension textureDim = new Dimension(textureSize[0], textureSize[1]);
        boolean usePlayerTexture = Json.getBoolean(obj, ITEM_USE_PLAYER_TEXTURE, false);
        JsonArray models = (JsonArray)obj.get(ITEM_MODELS);
        PlayerItemParser.checkNull(models, "Missing elements");
        HashMap<String, JsonObject> mapModelJsons = new HashMap<String, JsonObject>();
        ArrayList<PlayerItemRenderer> listModels = new ArrayList<PlayerItemRenderer>();
        ArrayList listAttachTos = new ArrayList();
        for (int i = 0; i < models.size(); ++i) {
            PlayerItemRenderer mr;
            String id;
            JsonObject elem = (JsonObject)models.get(i);
            String baseId = Json.getString(elem, MODEL_BASE_ID);
            if (baseId != null) {
                JsonObject baseObj = (JsonObject)mapModelJsons.get(baseId);
                if (baseObj == null) {
                    Config.warn("BaseID not found: " + baseId);
                    continue;
                }
                Set setEntries = baseObj.entrySet();
                for (Map.Entry entry : setEntries) {
                    if (elem.has((String)entry.getKey())) continue;
                    elem.add((String)entry.getKey(), (JsonElement)entry.getValue());
                }
            }
            if ((id = Json.getString(elem, MODEL_ID)) != null) {
                if (!mapModelJsons.containsKey(id)) {
                    mapModelJsons.put(id, elem);
                } else {
                    Config.warn("Duplicate model ID: " + id);
                }
            }
            if ((mr = PlayerItemParser.parseItemRenderer(elem, textureDim)) == null) continue;
            listModels.add(mr);
        }
        PlayerItemRenderer[] modelRenderers = listModels.toArray(new PlayerItemRenderer[listModels.size()]);
        return new PlayerItemModel(textureDim, usePlayerTexture, modelRenderers);
    }

    private static void checkNull(Object obj, String msg) {
        if (obj == null) {
            throw new JsonParseException(msg);
        }
    }

    private static nf makeResourceLocation(String texture) {
        int pos = texture.indexOf(58);
        if (pos < 0) {
            return new nf(texture);
        }
        String domain = texture.substring(0, pos);
        String path = texture.substring(pos + 1);
        return new nf(domain, path);
    }

    private static int parseAttachModel(String attachModelStr) {
        String str = attachModelStr;
        if (str == null) {
            return 0;
        }
        if (str.equals("body")) {
            return 0;
        }
        if (str.equals("head")) {
            return 1;
        }
        if (str.equals("leftArm")) {
            return 2;
        }
        if (str.equals("rightArm")) {
            return 3;
        }
        if (str.equals("leftLeg")) {
            return 4;
        }
        if (str.equals("rightLeg")) {
            return 5;
        }
        if (str.equals("cape")) {
            return 6;
        }
        Config.warn("Unknown attachModel: " + str);
        return 0;
    }

    public static PlayerItemRenderer parseItemRenderer(JsonObject elem, Dimension textureDim) {
        String type = Json.getString(elem, "type");
        if (!Config.equals(type, MODEL_TYPE_BOX)) {
            Config.warn("Unknown model type: " + type);
            return null;
        }
        String attachToStr = Json.getString(elem, MODEL_ATTACH_TO);
        int attachTo = PlayerItemParser.parseAttachModel(attachToStr);
        ModelPlayerItem modelBase = new ModelPlayerItem();
        modelBase.s = textureDim.width;
        modelBase.t = textureDim.height;
        brs mr = PlayerItemParser.parseModelRenderer(elem, modelBase, null, null);
        PlayerItemRenderer pir = new PlayerItemRenderer(attachTo, mr);
        return pir;
    }

    public static brs parseModelRenderer(JsonObject elem, bqf modelBase, int[] parentTextureSize, String basePath) {
        JsonArray submodels;
        JsonObject submodel;
        JsonArray sprites;
        float sizeAdd;
        float[] coordinates;
        JsonArray boxes;
        int[] textureSize;
        float scale;
        brs mr = new brs(modelBase);
        String id = Json.getString(elem, MODEL_ID);
        mr.setId(id);
        mr.scaleX = scale = Json.getFloat(elem, MODEL_SCALE, 1.0f);
        mr.scaleY = scale;
        mr.scaleZ = scale;
        String texture = Json.getString(elem, MODEL_TEXTURE);
        if (texture != null) {
            mr.setTextureLocation(CustomEntityModelParser.getResourceLocation(basePath, texture, ".png"));
        }
        if ((textureSize = Json.parseIntArray(elem.get("textureSize"), 2)) == null) {
            textureSize = parentTextureSize;
        }
        if (textureSize != null) {
            mr.b(textureSize[0], textureSize[1]);
        }
        String invertAxis = Json.getString(elem, MODEL_INVERT_AXIS, "").toLowerCase();
        boolean invertX = invertAxis.contains("x");
        boolean invertY = invertAxis.contains("y");
        boolean invertZ = invertAxis.contains("z");
        float[] translate = Json.parseFloatArray(elem.get(MODEL_TRANSLATE), 3, new float[3]);
        if (invertX) {
            translate[0] = -translate[0];
        }
        if (invertY) {
            translate[1] = -translate[1];
        }
        if (invertZ) {
            translate[2] = -translate[2];
        }
        float[] rotateAngles = Json.parseFloatArray(elem.get(MODEL_ROTATE), 3, new float[3]);
        for (int i = 0; i < rotateAngles.length; ++i) {
            rotateAngles[i] = rotateAngles[i] / 180.0f * rk.PI;
        }
        if (invertX) {
            rotateAngles[0] = -rotateAngles[0];
        }
        if (invertY) {
            rotateAngles[1] = -rotateAngles[1];
        }
        if (invertZ) {
            rotateAngles[2] = -rotateAngles[2];
        }
        mr.a(translate[0], translate[1], translate[2]);
        mr.f = rotateAngles[0];
        mr.g = rotateAngles[1];
        mr.h = rotateAngles[2];
        String mirrorTexture = Json.getString(elem, MODEL_MIRROR_TEXTURE, "").toLowerCase();
        boolean invertU = mirrorTexture.contains("u");
        boolean invertV = mirrorTexture.contains("v");
        if (invertU) {
            mr.i = true;
        }
        if (invertV) {
            mr.mirrorV = true;
        }
        if ((boxes = elem.getAsJsonArray(MODEL_BOXES)) != null) {
            for (int i = 0; i < boxes.size(); ++i) {
                JsonObject box = boxes.get(i).getAsJsonObject();
                int[] textureOffset = Json.parseIntArray(box.get(BOX_TEXTURE_OFFSET), 2);
                int[][] faceUvs = PlayerItemParser.parseFaceUvs(box);
                if (textureOffset == null && faceUvs == null) {
                    throw new JsonParseException("Texture offset not specified");
                }
                coordinates = Json.parseFloatArray(box.get(BOX_COORDINATES), 6);
                if (coordinates == null) {
                    throw new JsonParseException("Coordinates not specified");
                }
                if (invertX) {
                    coordinates[0] = -coordinates[0] - coordinates[3];
                }
                if (invertY) {
                    coordinates[1] = -coordinates[1] - coordinates[4];
                }
                if (invertZ) {
                    coordinates[2] = -coordinates[2] - coordinates[5];
                }
                sizeAdd = Json.getFloat(box, BOX_SIZE_ADD, 0.0f);
                if (faceUvs != null) {
                    mr.addBox(faceUvs, coordinates[0], coordinates[1], coordinates[2], coordinates[3], coordinates[4], coordinates[5], sizeAdd);
                    continue;
                }
                mr.a(textureOffset[0], textureOffset[1]);
                mr.a(coordinates[0], coordinates[1], coordinates[2], (int)coordinates[3], (int)coordinates[4], (int)coordinates[5], sizeAdd);
            }
        }
        if ((sprites = elem.getAsJsonArray(MODEL_SPRITES)) != null) {
            for (int i = 0; i < sprites.size(); ++i) {
                JsonObject sprite = sprites.get(i).getAsJsonObject();
                int[] textureOffset = Json.parseIntArray(sprite.get(BOX_TEXTURE_OFFSET), 2);
                if (textureOffset == null) {
                    throw new JsonParseException("Texture offset not specified");
                }
                coordinates = Json.parseFloatArray(sprite.get(BOX_COORDINATES), 6);
                if (coordinates == null) {
                    throw new JsonParseException("Coordinates not specified");
                }
                if (invertX) {
                    coordinates[0] = -coordinates[0] - coordinates[3];
                }
                if (invertY) {
                    coordinates[1] = -coordinates[1] - coordinates[4];
                }
                if (invertZ) {
                    coordinates[2] = -coordinates[2] - coordinates[5];
                }
                sizeAdd = Json.getFloat(sprite, BOX_SIZE_ADD, 0.0f);
                mr.a(textureOffset[0], textureOffset[1]);
                mr.addSprite(coordinates[0], coordinates[1], coordinates[2], (int)coordinates[3], (int)coordinates[4], (int)coordinates[5], sizeAdd);
            }
        }
        if ((submodel = (JsonObject)elem.get(MODEL_SUBMODEL)) != null) {
            brs subMr = PlayerItemParser.parseModelRenderer(submodel, modelBase, textureSize, basePath);
            mr.a(subMr);
        }
        if ((submodels = (JsonArray)elem.get(MODEL_SUBMODELS)) != null) {
            for (int i = 0; i < submodels.size(); ++i) {
                brs subMrId;
                JsonObject sm = (JsonObject)submodels.get(i);
                brs subMr = PlayerItemParser.parseModelRenderer(sm, modelBase, textureSize, basePath);
                if (subMr.getId() != null && (subMrId = mr.getChild(subMr.getId())) != null) {
                    Config.warn("Duplicate model ID: " + subMr.getId());
                }
                mr.a(subMr);
            }
        }
        return mr;
    }

    private static int[][] parseFaceUvs(JsonObject box) {
        int[][] uvs = new int[][]{Json.parseIntArray(box.get(BOX_UV_DOWN), 4), Json.parseIntArray(box.get(BOX_UV_UP), 4), Json.parseIntArray(box.get(BOX_UV_NORTH), 4), Json.parseIntArray(box.get(BOX_UV_SOUTH), 4), Json.parseIntArray(box.get(BOX_UV_WEST), 4), Json.parseIntArray(box.get(BOX_UV_EAST), 4)};
        if (uvs[2] == null) {
            uvs[2] = Json.parseIntArray(box.get(BOX_UV_FRONT), 4);
        }
        if (uvs[3] == null) {
            uvs[3] = Json.parseIntArray(box.get(BOX_UV_BACK), 4);
        }
        if (uvs[4] == null) {
            uvs[4] = Json.parseIntArray(box.get(BOX_UV_LEFT), 4);
        }
        if (uvs[5] == null) {
            uvs[5] = Json.parseIntArray(box.get(BOX_UV_RIGHT), 4);
        }
        boolean defined = false;
        for (int i = 0; i < uvs.length; ++i) {
            if (uvs[i] == null) continue;
            defined = true;
        }
        if (!defined) {
            return null;
        }
        return uvs;
    }
}

