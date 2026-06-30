/*
 * Decompiled with CFR 0.152.
 */
package net.optifine.shaders.config;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import net.optifine.shaders.config.ShaderLine;

public class ShaderParser {
    public static Pattern PATTERN_UNIFORM = Pattern.compile("\\s*uniform\\s+\\w+\\s+(\\w+).*");
    public static Pattern PATTERN_ATTRIBUTE = Pattern.compile("\\s*attribute\\s+\\w+\\s+(\\w+).*");
    public static Pattern PATTERN_CONST_INT = Pattern.compile("\\s*const\\s+int\\s+(\\w+)\\s*=\\s*([-+.\\w]+)\\s*;.*");
    public static Pattern PATTERN_CONST_FLOAT = Pattern.compile("\\s*const\\s+float\\s+(\\w+)\\s*=\\s*([-+.\\w]+)\\s*;.*");
    public static Pattern PATTERN_CONST_VEC4 = Pattern.compile("\\s*const\\s+vec4\\s+(\\w+)\\s*=\\s*(.+)\\s*;.*");
    public static Pattern PATTERN_CONST_BOOL = Pattern.compile("\\s*const\\s+bool\\s+(\\w+)\\s*=\\s*(\\w+)\\s*;.*");
    public static Pattern PATTERN_PROPERTY = Pattern.compile("\\s*(/\\*|//)?\\s*([A-Z]+):\\s*(\\w+)\\s*(\\*/.*|\\s*)");
    public static Pattern PATTERN_EXTENSION = Pattern.compile("\\s*#\\s*extension\\s+(\\w+)\\s*:\\s*(\\w+).*");
    public static Pattern PATTERN_DEFERRED_FSH = Pattern.compile(".*deferred[0-9]*\\.fsh");
    public static Pattern PATTERN_COMPOSITE_FSH = Pattern.compile(".*composite[0-9]*\\.fsh");
    public static Pattern PATTERN_FINAL_FSH = Pattern.compile(".*final\\.fsh");
    public static Pattern PATTERN_DRAW_BUFFERS = Pattern.compile("[0-7N]*");

    public static ShaderLine parseLine(String line) {
        Matcher mu = PATTERN_UNIFORM.matcher(line);
        if (mu.matches()) {
            return new ShaderLine(1, mu.group(1), "", line);
        }
        Matcher ma = PATTERN_ATTRIBUTE.matcher(line);
        if (ma.matches()) {
            return new ShaderLine(2, ma.group(1), "", line);
        }
        Matcher mc = PATTERN_PROPERTY.matcher(line);
        if (mc.matches()) {
            return new ShaderLine(6, mc.group(2), mc.group(3), line);
        }
        Matcher mci = PATTERN_CONST_INT.matcher(line);
        if (mci.matches()) {
            return new ShaderLine(3, mci.group(1), mci.group(2), line);
        }
        Matcher mcf = PATTERN_CONST_FLOAT.matcher(line);
        if (mcf.matches()) {
            return new ShaderLine(4, mcf.group(1), mcf.group(2), line);
        }
        Matcher mcb = PATTERN_CONST_BOOL.matcher(line);
        if (mcb.matches()) {
            return new ShaderLine(5, mcb.group(1), mcb.group(2), line);
        }
        Matcher mce = PATTERN_EXTENSION.matcher(line);
        if (mce.matches()) {
            return new ShaderLine(7, mce.group(1), mce.group(2), line);
        }
        Matcher mcv4 = PATTERN_CONST_VEC4.matcher(line);
        if (mcv4.matches()) {
            return new ShaderLine(8, mcv4.group(1), mcv4.group(2), line);
        }
        return null;
    }

    public static int getIndex(String uniform, String prefix, int minIndex, int maxIndex) {
        if (uniform.length() != prefix.length() + 1) {
            return -1;
        }
        if (!uniform.startsWith(prefix)) {
            return -1;
        }
        int index = uniform.charAt(prefix.length()) - 48;
        if (index < minIndex || index > maxIndex) {
            return -1;
        }
        return index;
    }

    public static int getShadowDepthIndex(String uniform) {
        switch (uniform) {
            case "shadow": {
                return 0;
            }
            case "watershadow": {
                return 1;
            }
        }
        return ShaderParser.getIndex(uniform, "shadowtex", 0, 1);
    }

    public static int getShadowColorIndex(String uniform) {
        switch (uniform) {
            case "shadowcolor": {
                return 0;
            }
        }
        return ShaderParser.getIndex(uniform, "shadowcolor", 0, 1);
    }

    public static int getDepthIndex(String uniform) {
        return ShaderParser.getIndex(uniform, "depthtex", 0, 2);
    }

    public static int getColorIndex(String uniform) {
        int gauxIndex = ShaderParser.getIndex(uniform, "gaux", 1, 4);
        if (gauxIndex > 0) {
            return gauxIndex + 3;
        }
        return ShaderParser.getIndex(uniform, "colortex", 4, 7);
    }

    public static boolean isDeferred(String filename) {
        return PATTERN_DEFERRED_FSH.matcher(filename).matches();
    }

    public static boolean isComposite(String filename) {
        return PATTERN_COMPOSITE_FSH.matcher(filename).matches();
    }

    public static boolean isFinal(String filename) {
        return PATTERN_FINAL_FSH.matcher(filename).matches();
    }

    public static boolean isValidDrawBuffers(String str) {
        return PATTERN_DRAW_BUFFERS.matcher(str).matches();
    }
}

