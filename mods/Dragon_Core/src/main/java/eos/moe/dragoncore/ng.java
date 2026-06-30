/*
 * Decompiled with CFR 0.152.
 */
package eos.moe.dragoncore;

public class ng {
    public static final String ALLATORIxDEMO = "0123456789ABCDEF";

    public ng() {
        ng a2;
    }

    public static String ALLATORIxDEMO(byte[] a2) {
        if (a2 == null) {
            return "";
        }
        StringBuffer a3 = new StringBuffer(2 * a2.length);
        for (int a4 = 0; a4 < a2.length; ++a4) {
            int a5 = (256 + a2[a4]) % 256;
            a3.append(ALLATORIxDEMO.charAt(a5 / 16 & 0xF));
            a3.append(ALLATORIxDEMO.charAt(a5 % 16 & 0xF));
        }
        return a3.toString();
    }

    public static byte[] ALLATORIxDEMO(String a2) {
        String a3 = a2;
        if (a2 == null) {
            a3 = "";
        } else if (a2.length() % 2 != 0) {
            a3 = "0" + a2;
        }
        byte[] a4 = new byte[a3.length() / 2];
        int a5 = 0;
        int a6 = 0;
        while (a5 < a3.length()) {
            char a7 = a3.charAt(a5++);
            char a8 = a3.charAt(a5++);
            a4[a6] = (byte)(ng.ALLATORIxDEMO(a7) * 16 + ng.ALLATORIxDEMO(a8));
            ++a6;
        }
        return a4;
    }

    public static int ALLATORIxDEMO(char a2) {
        if (a2 >= '0' && a2 <= '9') {
            return a2 - 48;
        }
        if (a2 >= 'A' && a2 <= 'F') {
            return a2 - 65 + 10;
        }
        if (a2 >= 'a' && a2 <= 'f') {
            return a2 - 97 + 10;
        }
        throw new IllegalArgumentException("Input string may only contain hex digits, but found '" + a2 + "'");
    }
}

