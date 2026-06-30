/*
 * Decompiled with CFR 0.152.
 */
package eos.moe.armourers;

public class vi {
    public static final String j = "0123456789ABCDEF";

    public static int r(char a2) {
        if (a2 >= '0' && a2 <= '9') {
            return a2 - 48;
        }
        if (a2 >= 'A' && a2 <= 'F') {
            return a2 - 65 + 10;
        }
        if (a2 >= 'a' && a2 <= 'f') {
            return a2 - 97 + 10;
        }
        throw new IllegalArgumentException(new StringBuilder().insert(0, "Input string may only contain hex digits, but found '").append(a2).append("'").toString());
    }

    public static String r(byte[] a2) {
        int n2;
        if (a2 == null) {
            return "";
        }
        StringBuffer stringBuffer = new StringBuffer(2 * a2.length);
        int n3 = n2 = 0;
        while (n3 < a2.length) {
            int n4 = (256 + a2[n2]) % 256;
            stringBuffer.append(j.charAt(n4 / 16 & 0xF));
            stringBuffer.append(j.charAt(n4 % 16 & 0xF));
            n3 = ++n2;
        }
        return stringBuffer.toString();
    }

    public vi() {
        vi a2;
    }

    public static byte[] r(String a2) {
        String string;
        String string2 = a2;
        if (string2 == null) {
            string = string2 = "";
        } else {
            if (a2.length() % 2 != 0) {
                string2 = new StringBuilder().insert(0, "0").append(a2).toString();
            }
            string = string2;
        }
        byte[] byArray = new byte[string.length() / 2];
        int n2 = 0;
        int n3 = 0;
        int n4 = n2;
        while (n4 < string2.length()) {
            String string3 = string2;
            char c2 = string3.charAt(n2);
            char c3 = string3.charAt(++n2);
            byArray[n3++] = (byte)(vi.r(c2) * 16 + vi.r(c3));
            n4 = ++n2;
        }
        return byArray;
    }
}

