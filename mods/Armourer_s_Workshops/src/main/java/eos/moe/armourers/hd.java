/*
 * Decompiled with CFR 0.152.
 */
package eos.moe.armourers;

import java.util.regex.Pattern;

public class hd {
    private static Pattern j = Pattern.compile(new StringBuilder().insert(0, "(?i)").append(String.valueOf('\u00a7')).append(".").toString());

    public hd() {
        hd a2;
    }

    public static String r(String a2) {
        if (a2 == null) {
            return null;
        }
        return j.matcher(a2).replaceAll("");
    }
}

