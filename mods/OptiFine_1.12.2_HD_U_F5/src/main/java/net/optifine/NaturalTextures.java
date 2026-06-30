/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  bvp
 *  cdp
 *  cdq
 *  et
 *  nf
 */
package net.optifine;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import net.optifine.ConnectedTextures;
import net.optifine.NaturalProperties;
import net.optifine.util.TextureUtils;

public class NaturalTextures {
    private static NaturalProperties[] propertiesByIndex = new NaturalProperties[0];

    public static void update() {
        propertiesByIndex = new NaturalProperties[0];
        if (!Config.isNaturalTextures()) {
            return;
        }
        String fileName = "optifine/natural.properties";
        try {
            nf loc = new nf(fileName);
            if (!Config.hasResource(loc)) {
                Config.dbg("NaturalTextures: configuration \"" + fileName + "\" not found");
                return;
            }
            boolean defaultConfig = Config.isFromDefaultResourcePack(loc);
            InputStream in = Config.getResourceStream(loc);
            ArrayList<NaturalProperties> list = new ArrayList<NaturalProperties>(256);
            String configStr = Config.readInputStream(in);
            in.close();
            String[] configLines = Config.tokenize(configStr, "\n\r");
            if (defaultConfig) {
                Config.dbg("Natural Textures: Parsing default configuration \"" + fileName + "\"");
                Config.dbg("Natural Textures: Valid only for textures from default resource pack");
            } else {
                Config.dbg("Natural Textures: Parsing configuration \"" + fileName + "\"");
            }
            cdp textureMapBlocks = TextureUtils.getTextureMapBlocks();
            for (int i = 0; i < configLines.length; ++i) {
                String line = configLines[i].trim();
                if (line.startsWith("#")) continue;
                String[] strs = Config.tokenize(line, "=");
                if (strs.length != 2) {
                    Config.warn("Natural Textures: Invalid \"" + fileName + "\" line: " + line);
                    continue;
                }
                String key = strs[0].trim();
                String type = strs[1].trim();
                String texName = key;
                cdq ts = textureMapBlocks.getSpriteSafe("minecraft:blocks/" + texName);
                if (ts == null) {
                    Config.warn("Natural Textures: Texture not found: \"" + fileName + "\" line: " + line);
                    continue;
                }
                int tileNum = ts.getIndexInMap();
                if (tileNum < 0) {
                    Config.warn("Natural Textures: Invalid \"" + fileName + "\" line: " + line);
                    continue;
                }
                if (defaultConfig && !Config.isFromDefaultResourcePack(new nf("textures/blocks/" + texName + ".png"))) {
                    return;
                }
                NaturalProperties props = new NaturalProperties(type);
                if (!props.isValid()) continue;
                while (list.size() <= tileNum) {
                    list.add(null);
                }
                list.set(tileNum, props);
                Config.dbg("NaturalTextures: " + texName + " = " + type);
            }
            propertiesByIndex = list.toArray(new NaturalProperties[list.size()]);
        }
        catch (FileNotFoundException e) {
            Config.warn("NaturalTextures: configuration \"" + fileName + "\" not found");
            return;
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static bvp getNaturalTexture(et blockPosIn, bvp quad) {
        cdq sprite = quad.a();
        if (sprite == null) {
            return quad;
        }
        NaturalProperties nps = NaturalTextures.getNaturalProperties(sprite);
        if (nps == null) {
            return quad;
        }
        int side = ConnectedTextures.getSide(quad.e());
        int rand = Config.getRandom(blockPosIn, side);
        int rotate = 0;
        boolean flipU = false;
        if (nps.rotation > 1) {
            rotate = rand & 3;
        }
        if (nps.rotation == 2) {
            rotate = rotate / 2 * 2;
        }
        if (nps.flip) {
            flipU = (rand & 4) != 0;
        }
        return nps.getQuad(quad, rotate, flipU);
    }

    public static NaturalProperties getNaturalProperties(cdq icon) {
        if (!(icon instanceof cdq)) {
            return null;
        }
        cdq ts = icon;
        int tileNum = ts.getIndexInMap();
        if (tileNum < 0 || tileNum >= propertiesByIndex.length) {
            return null;
        }
        NaturalProperties props = propertiesByIndex[tileNum];
        return props;
    }
}

