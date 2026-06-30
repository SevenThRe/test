/*
 * Decompiled with CFR 0.152.
 */
package eos.moe.dragoncore;

import eos.moe.dragoncore.gc;
import eos.moe.dragoncore.hc;
import eos.moe.dragoncore.yk;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Calendar;

public class ta {
    private static final long k = 0x210000L;
    private static final int ALLATORIxDEMO = 15;

    public ta() {
        ta a2;
    }

    public static boolean ALLATORIxDEMO(String a2) {
        return a2 != null && a2.trim().length() > 0;
    }

    public static boolean ALLATORIxDEMO(File a2) throws yk {
        if (a2 == null) {
            throw new yk("output path is null");
        }
        if (a2.exists()) {
            if (!a2.isDirectory()) {
                throw new yk("output directory is not valid");
            }
        } else if (!a2.mkdirs()) {
            throw new yk("Cannot create output directories");
        }
        return true;
    }

    public static long x(long a2) {
        if (a2 < 0L) {
            return 0x210000L;
        }
        long a3 = ta.f(a2);
        return a3 != 0x210000L ? a3 + (a2 % 2000L << 32) : 0x210000L;
    }

    private static /* synthetic */ long f(long a2) {
        Calendar a3 = Calendar.getInstance();
        a3.setTimeInMillis(a2);
        int a4 = a3.get(1);
        if (a4 < 1980) {
            return 0x210000L;
        }
        return a4 - 1980 << 25 | a3.get(2) + 1 << 21 | a3.get(5) << 16 | a3.get(11) << 11 | a3.get(12) << 5 | a3.get(13) >> 1;
    }

    public static long c(long a2) {
        long a3 = ta.ALLATORIxDEMO(a2);
        return a3 + (a2 >> 32);
    }

    private static /* synthetic */ long ALLATORIxDEMO(long a2) {
        int a3 = (int)(a2 << 1 & 0x3EL);
        int a4 = (int)(a2 >> 5 & 0x3FL);
        int a5 = (int)(a2 >> 11 & 0x1FL);
        int a6 = (int)(a2 >> 16 & 0x1FL);
        int a7 = (int)((a2 >> 21 & 0xFL) - 1L);
        int a8 = (int)((a2 >> 25 & 0x7FL) + 1980L);
        Calendar a9 = Calendar.getInstance();
        a9.set(a8, a7, a6, a5, a4, a3);
        a9.set(14, 0);
        return a9.getTime().getTime();
    }

    public static byte[] ALLATORIxDEMO(char[] a2) {
        byte[] a3 = new byte[a2.length];
        for (int a4 = 0; a4 < a2.length; ++a4) {
            a3[a4] = (byte)a2[a4];
        }
        return a3;
    }

    public static gc ALLATORIxDEMO(hc a2) {
        if (a2.ALLATORIxDEMO() != gc.y) {
            return a2.ALLATORIxDEMO();
        }
        if (a2.ALLATORIxDEMO() == null) {
            throw new RuntimeException("AesExtraDataRecord not present in local header for aes encrypted data");
        }
        return a2.ALLATORIxDEMO().ALLATORIxDEMO();
    }

    public static int ALLATORIxDEMO(InputStream a2, byte[] a3) throws IOException {
        int a4 = a2.read(a3);
        if (a4 != a3.length && (a4 = ta.ALLATORIxDEMO(a2, a3, a4)) != a3.length) {
            throw new IOException("Cannot read fully into byte buffer");
        }
        return a4;
    }

    public static int ALLATORIxDEMO(InputStream a2, byte[] a3, int a4, int a5) throws IOException {
        int a6;
        int a7;
        if (a4 < 0) {
            throw new IllegalArgumentException("Negative offset");
        }
        if (a5 < 0) {
            throw new IllegalArgumentException("Negative length");
        }
        if (a5 == 0) {
            return 0;
        }
        if (a4 + a5 > a3.length) {
            throw new IllegalArgumentException("Length greater than buffer size");
        }
        for (a6 = 0; a6 != a5; a6 += a7) {
            a7 = a2.read(a3, a4 + a6, a5 - a6);
            if (a7 != -1) continue;
            if (a6 == 0) {
                return -1;
            }
            return a6;
        }
        return a6;
    }

    private static /* synthetic */ int ALLATORIxDEMO(InputStream a2, byte[] a3, int a4) throws IOException {
        int a5 = a3.length - a4;
        int a6 = 0;
        for (int a7 = 1; a4 < a3.length && a6 != -1 && a7 < 15; ++a7) {
            a6 = a2.read(a3, a4, a5);
            if (a6 <= 0) continue;
            a4 += a6;
            a5 -= a6;
        }
        return a4;
    }
}

