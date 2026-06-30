/*
 * Decompiled with CFR 0.152.
 */
package net.optifine.shaders.config;

import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import net.optifine.shaders.Shaders;
import net.optifine.shaders.config.ShaderOption;
import net.optifine.util.StrUtils;

public class ShaderOptionVariable
extends ShaderOption {
    private static final Pattern PATTERN_VARIABLE = Pattern.compile("^\\s*#define\\s+(\\w+)\\s+(-?[0-9\\.Ff]+|\\w+)\\s*(//.*)?$");

    public ShaderOptionVariable(String name, String description, String value, String[] values, String path) {
        super(name, description, value, values, value, path);
        this.setVisible(this.getValues().length > 1);
    }

    @Override
    public String getSourceLine() {
        return "#define " + this.getName() + " " + this.getValue() + " // Shader option " + this.getValue();
    }

    @Override
    public String getValueText(String val) {
        String prefix = Shaders.translate("prefix." + this.getName(), "");
        String text = super.getValueText(val);
        String suffix = Shaders.translate("suffix." + this.getName(), "");
        String textFull = prefix + text + suffix;
        return textFull;
    }

    @Override
    public String getValueColor(String val) {
        String valLow = val.toLowerCase();
        if (valLow.equals("false") || valLow.equals("off")) {
            return "\u00a7c";
        }
        return "\u00a7a";
    }

    @Override
    public boolean matchesLine(String line) {
        Matcher m2 = PATTERN_VARIABLE.matcher(line);
        if (!m2.matches()) {
            return false;
        }
        String defName = m2.group(1);
        return defName.matches(this.getName());
    }

    public static ShaderOption parseOption(String line, String path) {
        Matcher m2 = PATTERN_VARIABLE.matcher(line);
        if (!m2.matches()) {
            return null;
        }
        String name = m2.group(1);
        String value = m2.group(2);
        String description = m2.group(3);
        String vals = StrUtils.getSegment(description, "[", "]");
        if (vals != null && vals.length() > 0) {
            description = description.replace(vals, "").trim();
        }
        String[] values = ShaderOptionVariable.parseValues(value, vals);
        if (name == null || name.length() <= 0) {
            return null;
        }
        path = StrUtils.removePrefix(path, "/shaders/");
        ShaderOptionVariable so = new ShaderOptionVariable(name, description, value, values, path);
        return so;
    }

    public static String[] parseValues(String value, String valuesStr) {
        String[] values = new String[]{value};
        if (valuesStr == null) {
            return values;
        }
        valuesStr = valuesStr.trim();
        valuesStr = StrUtils.removePrefix(valuesStr, "[");
        valuesStr = StrUtils.removeSuffix(valuesStr, "]");
        if ((valuesStr = valuesStr.trim()).length() <= 0) {
            return values;
        }
        Object[] parts = Config.tokenize(valuesStr, " ");
        if (parts.length <= 0) {
            return values;
        }
        if (!Arrays.asList(parts).contains(value)) {
            parts = (String[])Config.addObjectToArray(parts, value, 0);
        }
        return parts;
    }
}

