/*
 * Decompiled with CFR 0.152.
 */
package eos.moe.dragoncore;

import java.io.IOException;
import java.io.InputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/*
 * Duplicate member names - consider using --renamedupmembers true
 */
public class hn {
    private static final char[] k = new char[]{'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};
    private static final char[] ALLATORIxDEMO = new char[]{'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};

    public hn() {
        hn a2;
    }

    public static String ALLATORIxDEMO(InputStream a2) throws IOException {
        return hn.ALLATORIxDEMO(hn.ALLATORIxDEMO(a2));
    }

    public static byte[] ALLATORIxDEMO(InputStream a2) throws IOException {
        return hn.ALLATORIxDEMO(hn.ALLATORIxDEMO(), a2);
    }

    public static MessageDigest ALLATORIxDEMO() {
        return hn.ALLATORIxDEMO("MD5");
    }

    public static MessageDigest ALLATORIxDEMO(String a2) {
        try {
            return MessageDigest.getInstance(a2);
        }
        catch (NoSuchAlgorithmException a3) {
            throw new IllegalArgumentException(a3);
        }
    }

    private static /* synthetic */ byte[] ALLATORIxDEMO(MessageDigest a2, InputStream a3) throws IOException {
        return hn.ALLATORIxDEMO(a2, a3).digest();
    }

    public static MessageDigest ALLATORIxDEMO(MessageDigest a2, InputStream a3) throws IOException {
        byte[] a4 = new byte[1024];
        int a5 = a3.read(a4, 0, 1024);
        while (a5 > -1) {
            a2.update(a4, 0, a5);
            a5 = a3.read(a4, 0, 1024);
        }
        return a2;
    }

    public static String ALLATORIxDEMO(byte[] a2) {
        return new String(hn.ALLATORIxDEMO(a2));
    }

    public static char[] ALLATORIxDEMO(byte[] a2) {
        return hn.ALLATORIxDEMO(a2, true);
    }

    public static char[] ALLATORIxDEMO(byte[] a2, boolean a3) {
        return hn.ALLATORIxDEMO(a2, a3 ? k : ALLATORIxDEMO);
    }

    public static char[] ALLATORIxDEMO(byte[] a2, char[] a3) {
        int a4 = a2.length;
        char[] a5 = new char[a4 << 1];
        int a6 = 0;
        for (int a7 = 0; a7 < a4; ++a7) {
            a5[a6++] = a3[(0xF0 & a2[a7]) >>> 4];
            a5[a6++] = a3[0xF & a2[a7]];
        }
        return a5;
    }
}

