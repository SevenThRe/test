/*
 * Decompiled with CFR 0.152.
 */
package eos.moe.dragoncore;

import java.util.regex.Pattern;

public class rs {
    public static final Pattern l = Pattern.compile("[\"](.*)[\"]");
    public static final Pattern z = Pattern.compile("\\s");
    public static final Pattern s = Pattern.compile("\\s+");
    public static final Pattern g = Pattern.compile("[^0-9]");
    public static final Pattern t = Pattern.compile(Pattern.quote(" "));
    public static final Pattern r = Pattern.compile(Pattern.quote("-"));
    public static final Pattern x = Pattern.compile(Pattern.quote("."));
    public static final Pattern v = Pattern.compile(Pattern.quote("SET ,"));
    public static final Pattern m = Pattern.compile(Pattern.quote("'"));
    public static final Pattern c = Pattern.compile(Pattern.quote("%"));
    public static final Pattern q = Pattern.compile(Pattern.quote("+"));
    public static final Pattern b = Pattern.compile("\n", 16);
    public static final Pattern o = Pattern.compile("1", 16);
    public static final Pattern y = Pattern.compile("2", 16);
    public static final Pattern k = Pattern.compile("item.", 16);
    public static final Pattern ALLATORIxDEMO = Pattern.compile(" Gem", 16);

    public rs() {
        rs a2;
    }
}

