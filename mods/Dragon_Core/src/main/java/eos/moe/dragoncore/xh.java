/*
 * Decompiled with CFR 0.152.
 */
package eos.moe.dragoncore;

import eos.moe.dragoncore.yk;

public class xh {
    private int v;
    private int[][] m = null;
    private int c;
    private int q;
    private int b;
    private int o;
    private static final byte[] y = new byte[]{99, 124, 119, 123, -14, 107, 111, -59, 48, 1, 103, 43, -2, -41, -85, 118, -54, -126, -55, 125, -6, 89, 71, -16, -83, -44, -94, -81, -100, -92, 114, -64, -73, -3, -109, 38, 54, 63, -9, -52, 52, -91, -27, -15, 113, -40, 49, 21, 4, -57, 35, -61, 24, -106, 5, -102, 7, 18, -128, -30, -21, 39, -78, 117, 9, -125, 44, 26, 27, 110, 90, -96, 82, 59, -42, -77, 41, -29, 47, -124, 83, -47, 0, -19, 32, -4, -79, 91, 106, -53, -66, 57, 74, 76, 88, -49, -48, -17, -86, -5, 67, 77, 51, -123, 69, -7, 2, 127, 80, 60, -97, -88, 81, -93, 64, -113, -110, -99, 56, -11, -68, -74, -38, 33, 16, -1, -13, -46, -51, 12, 19, -20, 95, -105, 68, 23, -60, -89, 126, 61, 100, 93, 25, 115, 96, -127, 79, -36, 34, 42, -112, -120, 70, -18, -72, 20, -34, 94, 11, -37, -32, 50, 58, 10, 73, 6, 36, 92, -62, -45, -84, 98, -111, -107, -28, 121, -25, -56, 55, 109, -115, -43, 78, -87, 108, 86, -12, -22, 101, 122, -82, 8, -70, 120, 37, 46, 28, -90, -76, -58, -24, -35, 116, 31, 75, -67, -117, -118, 112, 62, -75, 102, 72, 3, -10, 14, 97, 53, 87, -71, -122, -63, 29, -98, -31, -8, -104, 17, 105, -39, -114, -108, -101, 30, -121, -23, -50, 85, 40, -33, -116, -95, -119, 13, -65, -26, 66, 104, 65, -103, 45, 15, -80, 84, -69, 22};
    private static final int[] k = new int[]{1, 2, 4, 8, 16, 32, 64, 128, 27, 54, 108, 216, 171, 77, 154, 47, 94, 188, 99, 198, 151, 53, 106, 212, 179, 125, 250, 239, 197, 145};
    private static final int[] ALLATORIxDEMO = new int[]{-1520213050, -2072216328, -1720223762, -1921287178, 0xDF2F2FF, -1117033514, -1318096930, 1422247313, 1345335392, 50397442, -1452841010, 2099981142, 436141799, 1658312629, -424957107, -1703512340, 1170918031, -1652391393, 1086966153, -2021818886, 368769775, -346465870, -918075506, 0xBF0F0FB, -324162239, 1742001331, -39673249, -357585083, -1080255453, -140204973, -1770884380, 1539358875, -1028147339, 486407649, -1366060227, 1780885068, 1513502316, 1094664062, 49805301, 1338821763, 1546925160, -190470831, 887481809, 150073849, -1821281822, 1943591083, 1395732834, 1058346282, 201589768, 1388824469, 1696801606, 1589887901, 672667696, -1583966665, 251987210, -1248159185, 151455502, 907153956, -1686077413, 1038279391, 652995533, 1764173646, -843926913, -1619692054, 453576978, -1635548387, 1949051992, 773462580, 756751158, -1301385508, -296068428, -73359269, -162377052, 1295727478, 1641469623, -827083907, 2066295122, 0x3EE3E3DD, 1898917726, -1752923117, -179088474, 1758581177, 0, 753790401, 1612718144, 536673507, -927878791, -312779850, -1100322092, 1187761037, -641810841, 1262041458, -565556588, -733197160, -396863312, 1255133061, 1808847035, 720367557, -441800113, 385612781, -985447546, -682799718, 0x55333366, -1803188975, -817543798, 284817897, 100794884, -2122350594, -263171936, 1144798328, -1163944155, -475486133, -212774494, -22830243, -1069531008, -1970303227, -1382903233, -1130521311, 1211644016, 83228145, -541279133, -1044990345, 1977277103, 1663115586, 806359072, 452984805, 250868733, 1842533055, 1288555905, 336333848, 890442534, 804056259, -513843266, -1567123659, -867941240, 957814574, 1472513171, -223893675, -2105639172, 1195195770, -1402706744, -413311558, 723065138, -1787595802, -1604296512, -1736343271, -783331426, 2145180835, 0x66222244, 2116692564, -1416589253, -2088204277, -901364084, 703524551, -742868885, 1007948840, 2044649127, -497131844, 487262998, 1994120109, 1004593371, 1446130276, 1312438900, 503974420, -615954030, 168166924, 1814307912, -463709000, 1573044895, 1859376061, -273896381, -1503501628, -1466855111, -1533700815, 937747667, -1954973198, 854058965, 1137232011, 1496790894, -1217565222, -1936880383, 1691735473, -766620004, -525751991, -1267962664, -95005012, 133494003, 636152527, -1352309302, -1904575756, -374428089, 0x18080810, -709182865, -2005370640, 1864705354, 1915629148, 605822008, -240736681, -944458637, 1371981463, 602466507, 2094914977, -1670089496, 555687742, -582268010, -591544991, -2037675251, -2054518257, -1871679264, 1111375484, -994724495, -1436129588, -666351472, 84083462, 32962295, 302911004, -1553899070, 1597322602, -111716434, -793134743, -1853454825, 1489093017, 656219450, -1180787161, 954327513, 335083755, -1281845205, 0x33111122, -1150719534, 1893325225, -1987146233, -1483434957, -1231316179, 572399164, -1836611819, 552200649, 1238290055, -11184726, 2015897680, 2061492133, -1886614525, -123625127, -2138470135, 386731290, -624967835, 837215959, -968736124, -1201116976, -1019133566, -1332111063, 1999449434, 286199582, -877612933, -61582168, -692339859, 974525996};

    public xh(byte[] a2) throws yk {
        xh a3;
        a3.ALLATORIxDEMO(a2);
    }

    private /* synthetic */ void ALLATORIxDEMO(byte[] a2) throws yk {
        xh a3;
        a3.m = a3.ALLATORIxDEMO(a2);
    }

    private /* synthetic */ int[][] ALLATORIxDEMO(byte[] a2) throws yk {
        xh a3;
        int a4 = a2.length / 4;
        if (a4 != 4 && a4 != 6 && a4 != 8 || a4 * 4 != a2.length) {
            throw new yk("invalid key length (not 128/192/256)");
        }
        a3.v = a4 + 6;
        int[][] a5 = new int[a3.v + 1][4];
        int a6 = 0;
        int a7 = 0;
        while (a7 < a2.length) {
            a5[a6 >> 2][a6 & 3] = a2[a7] & 0xFF | (a2[a7 + 1] & 0xFF) << 8 | (a2[a7 + 2] & 0xFF) << 16 | a2[a7 + 3] << 24;
            a7 += 4;
            ++a6;
        }
        int a8 = a3.v + 1 << 2;
        for (a7 = a4; a7 < a8; ++a7) {
            int a9 = a5[a7 - 1 >> 2][a7 - 1 & 3];
            if (a7 % a4 == 0) {
                a9 = a3.ALLATORIxDEMO(a3.ALLATORIxDEMO(a9, 8)) ^ k[a7 / a4 - 1];
            } else if (a4 > 6 && a7 % a4 == 4) {
                a9 = a3.ALLATORIxDEMO(a9);
            }
            a5[a7 >> 2][a7 & 3] = a5[a7 - a4 >> 2][a7 - a4 & 3] ^ a9;
        }
        return a5;
    }

    public int ALLATORIxDEMO(byte[] a2, byte[] a3) throws yk {
        xh a4;
        return a4.ALLATORIxDEMO(a2, 0, a3, 0);
    }

    public int ALLATORIxDEMO(byte[] a2, int a3, byte[] a4, int a5) throws yk {
        xh a6;
        if (a6.m == null) {
            throw new yk("AES engine not initialised");
        }
        if (a3 + 16 > a2.length) {
            throw new yk("input buffer too short");
        }
        if (a5 + 16 > a4.length) {
            throw new yk("output buffer too short");
        }
        a6.c(a2, a3);
        a6.ALLATORIxDEMO(a6.m);
        a6.ALLATORIxDEMO(a4, a5);
        return 16;
    }

    private /* synthetic */ void c(byte[] a2, int a3) {
        int a4 = a3;
        a.c = a2[a4++] & 0xFF;
        a.c |= (a2[a4++] & 0xFF) << 8;
        a.c |= (a2[a4++] & 0xFF) << 16;
        a.c |= a2[a4++] << 24;
        a.q = a2[a4++] & 0xFF;
        a.q |= (a2[a4++] & 0xFF) << 8;
        a.q |= (a2[a4++] & 0xFF) << 16;
        a.q |= a2[a4++] << 24;
        a.b = a2[a4++] & 0xFF;
        a.b |= (a2[a4++] & 0xFF) << 8;
        a.b |= (a2[a4++] & 0xFF) << 16;
        a.b |= a2[a4++] << 24;
        a.o = a2[a4++] & 0xFF;
        a.o |= (a2[a4++] & 0xFF) << 8;
        a.o |= (a2[a4++] & 0xFF) << 16;
        a.o |= a2[a4++] << 24;
    }

    private /* synthetic */ void ALLATORIxDEMO(byte[] a2, int a3) {
        xh a4;
        int a5 = a3;
        a2[a5++] = (byte)a4.c;
        a2[a5++] = (byte)(a4.c >> 8);
        a2[a5++] = (byte)(a4.c >> 16);
        a2[a5++] = (byte)(a4.c >> 24);
        a2[a5++] = (byte)a4.q;
        a2[a5++] = (byte)(a4.q >> 8);
        a2[a5++] = (byte)(a4.q >> 16);
        a2[a5++] = (byte)(a4.q >> 24);
        a2[a5++] = (byte)a4.b;
        a2[a5++] = (byte)(a4.b >> 8);
        a2[a5++] = (byte)(a4.b >> 16);
        a2[a5++] = (byte)(a4.b >> 24);
        a2[a5++] = (byte)a4.o;
        a2[a5++] = (byte)(a4.o >> 8);
        a2[a5++] = (byte)(a4.o >> 16);
        a2[a5++] = (byte)(a4.o >> 24);
    }

    private /* synthetic */ void ALLATORIxDEMO(int[][] a2) {
        int a3;
        int a4;
        int a5;
        int a6;
        xh a7;
        a7.c ^= a2[0][0];
        a7.q ^= a2[0][1];
        a7.b ^= a2[0][2];
        a7.o ^= a2[0][3];
        int a8 = 1;
        while (a8 < a7.v - 1) {
            a6 = ALLATORIxDEMO[a7.c & 0xFF] ^ a7.ALLATORIxDEMO(ALLATORIxDEMO[a7.q >> 8 & 0xFF], 24) ^ a7.ALLATORIxDEMO(ALLATORIxDEMO[a7.b >> 16 & 0xFF], 16) ^ a7.ALLATORIxDEMO(ALLATORIxDEMO[a7.o >> 24 & 0xFF], 8) ^ a2[a8][0];
            a5 = ALLATORIxDEMO[a7.q & 0xFF] ^ a7.ALLATORIxDEMO(ALLATORIxDEMO[a7.b >> 8 & 0xFF], 24) ^ a7.ALLATORIxDEMO(ALLATORIxDEMO[a7.o >> 16 & 0xFF], 16) ^ a7.ALLATORIxDEMO(ALLATORIxDEMO[a7.c >> 24 & 0xFF], 8) ^ a2[a8][1];
            a4 = ALLATORIxDEMO[a7.b & 0xFF] ^ a7.ALLATORIxDEMO(ALLATORIxDEMO[a7.o >> 8 & 0xFF], 24) ^ a7.ALLATORIxDEMO(ALLATORIxDEMO[a7.c >> 16 & 0xFF], 16) ^ a7.ALLATORIxDEMO(ALLATORIxDEMO[a7.q >> 24 & 0xFF], 8) ^ a2[a8][2];
            a3 = ALLATORIxDEMO[a7.o & 0xFF] ^ a7.ALLATORIxDEMO(ALLATORIxDEMO[a7.c >> 8 & 0xFF], 24) ^ a7.ALLATORIxDEMO(ALLATORIxDEMO[a7.q >> 16 & 0xFF], 16) ^ a7.ALLATORIxDEMO(ALLATORIxDEMO[a7.b >> 24 & 0xFF], 8) ^ a2[a8++][3];
            a7.c = ALLATORIxDEMO[a6 & 0xFF] ^ a7.ALLATORIxDEMO(ALLATORIxDEMO[a5 >> 8 & 0xFF], 24) ^ a7.ALLATORIxDEMO(ALLATORIxDEMO[a4 >> 16 & 0xFF], 16) ^ a7.ALLATORIxDEMO(ALLATORIxDEMO[a3 >> 24 & 0xFF], 8) ^ a2[a8][0];
            a7.q = ALLATORIxDEMO[a5 & 0xFF] ^ a7.ALLATORIxDEMO(ALLATORIxDEMO[a4 >> 8 & 0xFF], 24) ^ a7.ALLATORIxDEMO(ALLATORIxDEMO[a3 >> 16 & 0xFF], 16) ^ a7.ALLATORIxDEMO(ALLATORIxDEMO[a6 >> 24 & 0xFF], 8) ^ a2[a8][1];
            a7.b = ALLATORIxDEMO[a4 & 0xFF] ^ a7.ALLATORIxDEMO(ALLATORIxDEMO[a3 >> 8 & 0xFF], 24) ^ a7.ALLATORIxDEMO(ALLATORIxDEMO[a6 >> 16 & 0xFF], 16) ^ a7.ALLATORIxDEMO(ALLATORIxDEMO[a5 >> 24 & 0xFF], 8) ^ a2[a8][2];
            a7.o = ALLATORIxDEMO[a3 & 0xFF] ^ a7.ALLATORIxDEMO(ALLATORIxDEMO[a6 >> 8 & 0xFF], 24) ^ a7.ALLATORIxDEMO(ALLATORIxDEMO[a5 >> 16 & 0xFF], 16) ^ a7.ALLATORIxDEMO(ALLATORIxDEMO[a4 >> 24 & 0xFF], 8) ^ a2[a8++][3];
        }
        a6 = ALLATORIxDEMO[a7.c & 0xFF] ^ a7.ALLATORIxDEMO(ALLATORIxDEMO[a7.q >> 8 & 0xFF], 24) ^ a7.ALLATORIxDEMO(ALLATORIxDEMO[a7.b >> 16 & 0xFF], 16) ^ a7.ALLATORIxDEMO(ALLATORIxDEMO[a7.o >> 24 & 0xFF], 8) ^ a2[a8][0];
        a5 = ALLATORIxDEMO[a7.q & 0xFF] ^ a7.ALLATORIxDEMO(ALLATORIxDEMO[a7.b >> 8 & 0xFF], 24) ^ a7.ALLATORIxDEMO(ALLATORIxDEMO[a7.o >> 16 & 0xFF], 16) ^ a7.ALLATORIxDEMO(ALLATORIxDEMO[a7.c >> 24 & 0xFF], 8) ^ a2[a8][1];
        a4 = ALLATORIxDEMO[a7.b & 0xFF] ^ a7.ALLATORIxDEMO(ALLATORIxDEMO[a7.o >> 8 & 0xFF], 24) ^ a7.ALLATORIxDEMO(ALLATORIxDEMO[a7.c >> 16 & 0xFF], 16) ^ a7.ALLATORIxDEMO(ALLATORIxDEMO[a7.q >> 24 & 0xFF], 8) ^ a2[a8][2];
        a3 = ALLATORIxDEMO[a7.o & 0xFF] ^ a7.ALLATORIxDEMO(ALLATORIxDEMO[a7.c >> 8 & 0xFF], 24) ^ a7.ALLATORIxDEMO(ALLATORIxDEMO[a7.q >> 16 & 0xFF], 16) ^ a7.ALLATORIxDEMO(ALLATORIxDEMO[a7.b >> 24 & 0xFF], 8) ^ a2[a8++][3];
        a7.c = y[a6 & 0xFF] & 0xFF ^ (y[a5 >> 8 & 0xFF] & 0xFF) << 8 ^ (y[a4 >> 16 & 0xFF] & 0xFF) << 16 ^ y[a3 >> 24 & 0xFF] << 24 ^ a2[a8][0];
        a7.q = y[a5 & 0xFF] & 0xFF ^ (y[a4 >> 8 & 0xFF] & 0xFF) << 8 ^ (y[a3 >> 16 & 0xFF] & 0xFF) << 16 ^ y[a6 >> 24 & 0xFF] << 24 ^ a2[a8][1];
        a7.b = y[a4 & 0xFF] & 0xFF ^ (y[a3 >> 8 & 0xFF] & 0xFF) << 8 ^ (y[a6 >> 16 & 0xFF] & 0xFF) << 16 ^ y[a5 >> 24 & 0xFF] << 24 ^ a2[a8][2];
        a7.o = y[a3 & 0xFF] & 0xFF ^ (y[a6 >> 8 & 0xFF] & 0xFF) << 8 ^ (y[a5 >> 16 & 0xFF] & 0xFF) << 16 ^ y[a4 >> 24 & 0xFF] << 24 ^ a2[a8][3];
    }

    private /* synthetic */ int ALLATORIxDEMO(int a2, int a3) {
        return a2 >>> a3 | a2 << -a3;
    }

    private /* synthetic */ int ALLATORIxDEMO(int a2) {
        return y[a2 & 0xFF] & 0xFF | (y[a2 >> 8 & 0xFF] & 0xFF) << 8 | (y[a2 >> 16 & 0xFF] & 0xFF) << 16 | y[a2 >> 24 & 0xFF] << 24;
    }
}

