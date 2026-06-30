/*
 * Decompiled with CFR 0.152.
 */
package eos.moe.armourers;

import java.util.List;
import java.util.regex.Pattern;

public class xn {
    public static boolean r(String a2, List<String> a3, int a4, Pattern a5) {
        a3 = a3.iterator();
        while (a3.hasNext()) {
            String string = (String)a3.next();
            if (!xn.r(a2, string, a4, a5)) continue;
            return true;
        }
        return false;
    }

    public xn() {
        xn a2;
    }

    /*
     * Enabled aggressive block sorting
     */
    public static boolean r(String a2, String a3, int a4, Pattern a5) {
        switch (a4) {
            case 0: {
                return a2.equals(a3);
            }
            case 1: {
                return a3.startsWith(a2);
            }
            case 2: {
                return a3.contains(a2);
            }
            case 3: {
                if (a5 == null) break;
                return a5.matcher(a3).find();
            }
        }
        return false;
    }
}

