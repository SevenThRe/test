/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  bib
 *  com.google.gson.JsonArray
 *  com.google.gson.JsonElement
 *  com.google.gson.JsonObject
 *  com.google.gson.JsonParseException
 *  com.google.gson.JsonParser
 *  nf
 */
package net.optifine.player;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonParser;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import javax.imageio.ImageIO;
import net.optifine.http.HttpPipeline;
import net.optifine.http.HttpUtils;
import net.optifine.player.PlayerConfiguration;
import net.optifine.player.PlayerItemModel;
import net.optifine.player.PlayerItemParser;
import net.optifine.util.Json;

public class PlayerConfigurationParser {
    private String player = null;
    public static final String CONFIG_ITEMS = "items";
    public static final String ITEM_TYPE = "type";
    public static final String ITEM_ACTIVE = "active";

    public PlayerConfigurationParser(String player) {
        this.player = player;
    }

    public PlayerConfiguration parsePlayerConfiguration(JsonElement je) {
        if (je == null) {
            throw new JsonParseException("JSON object is null, player: " + this.player);
        }
        JsonObject jo = (JsonObject)je;
        PlayerConfiguration pc = new PlayerConfiguration();
        JsonArray items = (JsonArray)jo.get(CONFIG_ITEMS);
        if (items != null) {
            for (int i = 0; i < items.size(); ++i) {
                PlayerItemModel model;
                JsonObject item = (JsonObject)items.get(i);
                boolean active = Json.getBoolean(item, ITEM_ACTIVE, true);
                if (!active) continue;
                String type = Json.getString(item, ITEM_TYPE);
                if (type == null) {
                    Config.warn("Item type is null, player: " + this.player);
                    continue;
                }
                String modelPath = Json.getString(item, "model");
                if (modelPath == null) {
                    modelPath = "items/" + type + "/model.cfg";
                }
                if ((model = this.downloadModel(modelPath)) == null) continue;
                if (!model.isUsePlayerTexture()) {
                    BufferedImage image;
                    String texturePath = Json.getString(item, "texture");
                    if (texturePath == null) {
                        texturePath = "items/" + type + "/users/" + this.player + ".png";
                    }
                    if ((image = this.downloadTextureImage(texturePath)) == null) continue;
                    model.setTextureImage(image);
                    nf loc = new nf("optifine.net", texturePath);
                    model.setTextureLocation(loc);
                }
                pc.addPlayerItemModel(model);
            }
        }
        return pc;
    }

    private BufferedImage downloadTextureImage(String texturePath) {
        String textureUrl = HttpUtils.getPlayerItemsUrl() + "/" + texturePath;
        try {
            byte[] body = HttpPipeline.get(textureUrl, bib.z().M());
            BufferedImage image = ImageIO.read(new ByteArrayInputStream(body));
            return image;
        }
        catch (IOException e) {
            Config.warn("Error loading item texture " + texturePath + ": " + e.getClass().getName() + ": " + e.getMessage());
            return null;
        }
    }

    private PlayerItemModel downloadModel(String modelPath) {
        String modelUrl = HttpUtils.getPlayerItemsUrl() + "/" + modelPath;
        try {
            byte[] bytes = HttpPipeline.get(modelUrl, bib.z().M());
            String jsonStr = new String(bytes, "ASCII");
            JsonParser jp = new JsonParser();
            JsonObject jo = (JsonObject)jp.parse(jsonStr);
            PlayerItemModel pim = PlayerItemParser.parseItemModel(jo);
            return pim;
        }
        catch (Exception e) {
            Config.warn("Error loading item model " + modelPath + ": " + e.getClass().getName() + ": " + e.getMessage());
            return null;
        }
    }
}

