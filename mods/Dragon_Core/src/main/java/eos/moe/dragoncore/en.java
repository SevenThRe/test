/*
 * Decompiled with CFR 0.152.
 */
package eos.moe.dragoncore;

import java.util.regex.Pattern;

public class en {
    private static Pattern ALLATORIxDEMO = Pattern.compile("(?i)" + String.valueOf('\u00a7') + ".");

    public en() {
        en a2;
    }

    public static String ALLATORIxDEMO(String a2) {
        if (a2 != null) {
            String a3 = "";
            char[] a4 = a2.toCharArray();
            for (int a5 = 0; a5 < a4.length; ++a5) {
                if (a4[a5] == '\u00a7') {
                    if (a4.length > a5 + 1 && a4[a5 + 1] == '#') {
                        a5 += 6;
                    }
                    ++a5;
                    continue;
                }
                a3 = a3 + a4[a5];
            }
            return a3;
        }
        return null;
    }
}

