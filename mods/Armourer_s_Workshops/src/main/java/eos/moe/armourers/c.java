/*
 * Decompiled with CFR 0.152.
 */
package eos.moe.armourers;

import eos.moe.armourers.cb;
import eos.moe.armourers.nb;
import eos.moe.armourers.ph;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Calendar;

public class c {
    private static final int m = 15;
    private static final long j = 0x210000L;

    public static byte[] r(char[] a2) {
        int n2;
        byte[] byArray = new byte[a2.length];
        int n3 = n2 = 0;
        while (n3 < a2.length) {
            int n4 = n2++;
            byArray[n4] = (byte)a2[n4];
            n3 = n2;
        }
        return byArray;
    }

    public static int r(InputStream a2, byte[] a3, int a4, int a5) throws IOException {
        int n2 = 0;
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
        int n3 = n2;
        while (n3 != a5) {
            int n4 = a2.read(a3, a4 + n2, a5 - n2);
            if (n4 == -1) {
                if (n2 == 0) {
                    return -1;
                }
                return n2;
            }
            n3 = n2 + n4;
        }
        return n2;
    }

    public static boolean r(String a2) {
        return a2 != null && a2.trim().length() > 0;
    }

    private static /* synthetic */ long h(long a2) {
        Calendar calendar;
        int n2 = (int)(a2 << 1 & 0x3EL);
        int n3 = (int)(a2 >> 5 & 0x3FL);
        int n4 = (int)(a2 >> 11 & 0x1FL);
        int n5 = (int)(a2 >> 16 & 0x1FL);
        int n6 = (int)((a2 >> 21 & 0xFL) - 1L);
        int n7 = (int)((a2 >> 25 & 0x7FL) + 1980L);
        Calendar calendar2 = calendar = Calendar.getInstance();
        calendar2.set(n7, n6, n5, n4, n3, n2);
        calendar2.set(14, 0);
        return calendar2.getTime().getTime();
    }

    private static /* synthetic */ int r(InputStream a2, byte[] a3, int a4) throws IOException {
        int n2 = a3.length - a4;
        int n3 = 0;
        int n4 = a4;
        for (int i2 = 1; n4 < a3.length && n3 != -1 && i2 < 15; ++i2) {
            n3 = a2.read(a3, a4, n2);
            if (n3 > 0) {
                a4 += n3;
                n2 -= n3;
            }
            n4 = a4;
        }
        return a4;
    }

    public static cb r(nb a2) {
        if (a2.r() != cb.m) {
            return a2.r();
        }
        if (a2.r() == null) {
            throw new RuntimeException("AesExtraDataRecord not present in local header for aes encrypted data");
        }
        return a2.r().r();
    }

    public c() {
        c a2;
    }

    private static /* synthetic */ long z(long a2) {
        Calendar calendar;
        Calendar calendar2 = calendar = Calendar.getInstance();
        calendar2.setTimeInMillis(a2);
        int n2 = calendar2.get(1);
        if (n2 < 1980) {
            return 0x210000L;
        }
        return n2 - 1980 << 25 | calendar.get(2) + 1 << 21 | calendar.get(5) << 16 | calendar.get(11) << 11 | calendar.get(12) << 5 | calendar.get(13) >> 1;
    }

    public static long y(long a2) {
        if (a2 < 0L) {
            return 0x210000L;
        }
        long l2 = c.z(a2);
        if (l2 != 0x210000L) {
            return l2 + (a2 % 2000L << 32);
        }
        return 0x210000L;
    }

    public static int r(InputStream a2, byte[] a3) throws IOException {
        int n2 = a2.read(a3);
        if (n2 != a3.length && (n2 = c.r(a2, a3, n2)) != a3.length) {
            throw new IOException("Cannot read fully into byte buffer");
        }
        return n2;
    }

    public static long r(long a2) {
        return c.h(a2) + (a2 >> 32);
    }

    public static boolean r(File a2) throws ph {
        if (a2 == null) {
            throw new ph("output path is null");
        }
        if (a2.exists()) {
            if (!a2.isDirectory()) {
                throw new ph("output directory is not valid");
            }
        } else if (!a2.mkdirs()) {
            throw new ph("Cannot create output directories");
        }
        return true;
    }
}

