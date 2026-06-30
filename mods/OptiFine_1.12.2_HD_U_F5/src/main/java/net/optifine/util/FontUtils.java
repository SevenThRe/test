/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  nf
 */
package net.optifine.util;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.Set;
import net.optifine.util.PropertiesOrdered;

public class FontUtils {
    public static Properties readFontProperties(nf locationFontTexture) {
        String fontFileName = locationFontTexture.a();
        PropertiesOrdered props = new PropertiesOrdered();
        String suffix = ".png";
        if (!fontFileName.endsWith(suffix)) {
            return props;
        }
        String fileName = fontFileName.substring(0, fontFileName.length() - suffix.length()) + ".properties";
        try {
            nf locProp = new nf(locationFontTexture.b(), fileName);
            InputStream in = Config.getResourceStream(Config.getResourceManager(), locProp);
            if (in == null) {
                return props;
            }
            Config.log("Loading " + fileName);
            props.load(in);
        }
        catch (FileNotFoundException locProp) {
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        return props;
    }

    public static void readCustomCharWidths(Properties props, float[] charWidth) {
        Set<Object> keySet = props.keySet();
        for (String string : keySet) {
            String value;
            float width;
            String numStr;
            int num;
            String prefix;
            if (!string.startsWith(prefix = "width.") || (num = Config.parseInt(numStr = string.substring(prefix.length()), -1)) < 0 || num >= charWidth.length || !((width = Config.parseFloat(value = props.getProperty(string), -1.0f)) >= 0.0f)) continue;
            charWidth[num] = width;
        }
    }

    public static float readFloat(Properties props, String key, float defOffset) {
        String str = props.getProperty(key);
        if (str == null) {
            return defOffset;
        }
        float offset = Config.parseFloat(str, Float.MIN_VALUE);
        if (offset == Float.MIN_VALUE) {
            Config.warn("Invalid value for " + key + ": " + str);
            return defOffset;
        }
        return offset;
    }

    public static boolean readBoolean(Properties props, String key, boolean defVal) {
        String str = props.getProperty(key);
        if (str == null) {
            return defVal;
        }
        String strLow = str.toLowerCase().trim();
        if (strLow.equals("true") || strLow.equals("on")) {
            return true;
        }
        if (strLow.equals("false") || strLow.equals("off")) {
            return false;
        }
        Config.warn("Invalid value for " + key + ": " + str);
        return defVal;
    }

    public static nf getHdFontLocation(nf fontLoc) {
        if (!Config.isCustomFonts()) {
            return fontLoc;
        }
        if (fontLoc == null) {
            return fontLoc;
        }
        if (!Config.isMinecraftThread()) {
            return fontLoc;
        }
        String fontName = fontLoc.a();
        String texturesStr = "textures/";
        String mcpatcherStr = "mcpatcher/";
        if (!fontName.startsWith(texturesStr)) {
            return fontLoc;
        }
        fontName = fontName.substring(texturesStr.length());
        fontName = mcpatcherStr + fontName;
        nf fontLocHD = new nf(fontLoc.b(), fontName);
        if (Config.hasResource(Config.getResourceManager(), fontLocHD)) {
            return fontLocHD;
        }
        return fontLoc;
    }
}

