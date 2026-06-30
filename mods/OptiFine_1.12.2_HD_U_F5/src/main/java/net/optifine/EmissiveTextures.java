/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cdm
 *  cdr
 *  cds
 *  cii
 *  nf
 */
package net.optifine;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Map;
import net.optifine.util.PropertiesOrdered;

public class EmissiveTextures {
    private static String suffixEmissive = null;
    private static String suffixEmissivePng = null;
    private static boolean active = false;
    private static boolean render = false;
    private static boolean hasEmissive = false;
    private static boolean renderEmissive = false;
    private static float lightMapX;
    private static float lightMapY;
    private static final String SUFFIX_PNG = ".png";
    private static final nf LOCATION_EMPTY;

    public static boolean isActive() {
        return active;
    }

    public static String getSuffixEmissive() {
        return suffixEmissive;
    }

    public static void beginRender() {
        render = true;
        hasEmissive = false;
    }

    public static cds getEmissiveTexture(cds texture, Map<nf, cds> mapTextures) {
        cds textureEmissive;
        if (!render) {
            return texture;
        }
        if (!(texture instanceof cdm)) {
            return texture;
        }
        cdm simpleTexture = (cdm)texture;
        nf locationEmissive = simpleTexture.locationEmissive;
        if (!renderEmissive) {
            if (locationEmissive != null) {
                hasEmissive = true;
            }
            return texture;
        }
        if (locationEmissive == null) {
            locationEmissive = LOCATION_EMPTY;
        }
        if ((textureEmissive = mapTextures.get(locationEmissive)) == null) {
            textureEmissive = new cdm(locationEmissive);
            cdr textureManager = Config.getTextureManager();
            textureManager.a(locationEmissive, textureEmissive);
        }
        return textureEmissive;
    }

    public static boolean hasEmissive() {
        return hasEmissive;
    }

    public static void beginRenderEmissive() {
        lightMapX = cii.lastBrightnessX;
        lightMapY = cii.lastBrightnessY;
        cii.a((int)cii.r, (float)240.0f, (float)lightMapY);
        renderEmissive = true;
    }

    public static void endRenderEmissive() {
        renderEmissive = false;
        cii.a((int)cii.r, (float)lightMapX, (float)lightMapY);
    }

    public static void endRender() {
        render = false;
        hasEmissive = false;
    }

    public static void update() {
        active = false;
        suffixEmissive = null;
        suffixEmissivePng = null;
        if (!Config.isEmissiveTextures()) {
            return;
        }
        try {
            String fileName = "optifine/emissive.properties";
            nf loc = new nf(fileName);
            InputStream in = Config.getResourceStream(loc);
            if (in == null) {
                return;
            }
            EmissiveTextures.dbg("Loading " + fileName);
            PropertiesOrdered props = new PropertiesOrdered();
            props.load(in);
            in.close();
            suffixEmissive = props.getProperty("suffix.emissive");
            if (suffixEmissive != null) {
                suffixEmissivePng = suffixEmissive + SUFFIX_PNG;
            }
            active = suffixEmissive != null;
        }
        catch (FileNotFoundException e) {
            return;
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void dbg(String str) {
        Config.dbg("EmissiveTextures: " + str);
    }

    private static void warn(String str) {
        Config.warn("EmissiveTextures: " + str);
    }

    public static boolean isEmissive(nf loc) {
        if (suffixEmissivePng == null) {
            return false;
        }
        return loc.a().endsWith(suffixEmissivePng);
    }

    public static void loadTexture(nf loc, cdm tex) {
        if (loc == null || tex == null) {
            return;
        }
        tex.isEmissive = false;
        tex.locationEmissive = null;
        if (suffixEmissivePng == null) {
            return;
        }
        String path = loc.a();
        if (!path.endsWith(SUFFIX_PNG)) {
            return;
        }
        if (path.endsWith(suffixEmissivePng)) {
            tex.isEmissive = true;
            return;
        }
        String pathEmPng = path.substring(0, path.length() - SUFFIX_PNG.length()) + suffixEmissivePng;
        nf locEmPng = new nf(loc.b(), pathEmPng);
        if (!Config.hasResource(locEmPng)) {
            return;
        }
        tex.locationEmissive = locEmPng;
    }

    static {
        LOCATION_EMPTY = new nf("mcpatcher/ctm/default/empty.png");
    }
}

