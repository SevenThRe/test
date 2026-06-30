/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.base.Strings
 */
package eos.moe.dragongps;

import com.google.common.base.Strings;
import java.awt.Color;
import java.util.Random;

/*
 * Duplicate member names - consider using --renamedupmembers true
 * Renamed from eos.moe.dragongps.IiiIiIIiII
 */
public final class iiiiiiiiii_4 {
    public static final int IIiIIIIIIi = 0x808080;
    public static final int IIIIiiiIII = 65535;
    public static final int IIiIIiiIiI = 0;
    public static final int IiIIiiIIiI = 0xFF0000;
    public static final int iIIIiIIIIi = 0xC0C0C0;
    public static final int iIiIIiIiiI = 0xFFFFFF;
    public static final int iiIIIiIiII = -16777216;
    public static final int IIiiIiiIII = -1;
    public static final int iIIiIIiIii = 255;
    public static final int iIIIIiiIII = -16777216;
    public static final int IIiIiIIIiI = 65280;
    public static final int IIiiiiiIIi = 0x404040;

    public static String iIIiiiIIiI(Integer IIiiiiiIIi) {
        int[] IIiiiiiIIi2 = iiiiiiiiii_4.IIIiiiIiii((int)IIiiiiiIIi);
        return String.format("#%02x%02x%02x", IIiiiiiIIi2[0], IIiiiiiIIi2[1], IIiiiiiIIi2[2]);
    }

    public static float[] IIIiiiIiii(int IIiiiiiIIi) {
        return new float[]{(float)(IIiiiiiIIi >> 16 & 0xFF) / 255.0f, (float)(IIiiiiiIIi >> 8 & 0xFF) / 255.0f, (float)(IIiiiiiIIi & 0xFF) / 255.0f};
    }

    public static int IIIiiiIiii(int IIiiiiiIIi, int IIiiiiiIIi2, int IIiiiiiIIi3) {
        return 0xFF000000 | (IIiiiiiIIi & 0xFF) << 16 | (IIiiiiiIIi2 & 0xFF) << 8 | IIiiiiiIIi3 & 0xFF;
    }

    public static float IIIiiiIiii(int IIiiiiiIIi) {
        return (float)iiiiiiiiii_4.IiiiiiIIiI(IIiiiiiIIi) / 255.0f;
    }

    public static Integer IIIiiiIiii(Integer ... IIiiiiiIIi) {
        int[] IIiiiiiIIi2 = new int[]{0, 0, 0};
        int IIiiiiiIIi3 = 0;
        Integer[] IIiiiiiIIi4 = IIiiiiiIIi;
        int IIiiiiiIIi5 = IIiiiiiIIi.length;
        for (int IIiiiiiIIi6 = 0; IIiiiiiIIi6 < IIiiiiiIIi5; ++IIiiiiiIIi6) {
            Integer IIiiiiiIIi7 = IIiiiiiIIi4[IIiiiiiIIi6];
            if (IIiiiiiIIi7 == null) continue;
            int[] IIiiiiiIIi8 = iiiiiiiiii_4.IIIiiiIiii((int)IIiiiiiIIi7);
            IIiiiiiIIi2[0] = Math.max(IIiiiiiIIi2[0], IIiiiiiIIi8[0]);
            IIiiiiiIIi2[1] = Math.max(IIiiiiiIIi2[1], IIiiiiiIIi8[1]);
            IIiiiiiIIi2[2] = Math.max(IIiiiiiIIi2[2], IIiiiiiIIi8[2]);
            ++IIiiiiiIIi3;
        }
        if (IIiiiiiIIi3 == 0) {
            return null;
        }
        return iiiiiiiiii_4.IIIiiiIiii(IIiiiiiIIi2);
    }

    public static int IiiiiiIIiI(int IIiiiiiIIi, float IIiiiiiIIi2) {
        float IIiiiiiIIi3 = IIiiiiiIIi2 < 1.0f ? 0.85f : 1.0f;
        float[] IIiiiiiIIi4 = iiiiiiiiii_4.IIIiiiIiii(IIiiiiiIIi);
        IIiiiiiIIi4[0] = IIiiiiiIIi4[0] * IIiiiiiIIi3 * IIiiiiiIIi2;
        IIiiiiiIIi4[1] = IIiiiiiIIi4[1] * IIiiiiiIIi3 * IIiiiiiIIi2;
        IIiiiiiIIi4[2] = IIiiiiiIIi4[2] * IIiiiiiIIi2;
        return iiiiiiiiii_4.IIIiiiIiii(iiiiiiiiii_4.IIIiiiIiii(IIiiiiiIIi4, 1.0f));
    }

    public static Integer IIIiiiIiii(int IIiiiiiIIi, int IIiiiiiIIi2) {
        int[] IIiiiiiIIi3 = iiiiiiiiii_4.IIIiiiIiii(IIiiiiiIIi);
        int[] IIiiiiiIIi4 = iiiiiiiiii_4.IIIiiiIiii(IIiiiiiIIi2);
        return iiiiiiiiii_4.IIIiiiIiii(IIiiiiiIIi3[0] - IIiiiiiIIi4[0], IIiiiiiIIi3[1] - IIiiiiiIIi4[1], IIiiiiiIIi3[2] - IIiiiiiIIi4[2]);
    }

    public static int[] IIIiiiIiii(int IIiiiiiIIi) {
        return new int[]{IIiiiiiIIi >> 16 & 0xFF, IIiiiiiIIi >> 8 & 0xFF, IIiiiiiIIi & 0xFF};
    }

    public static int IIIiiiIiii(int[] IIiiiiiIIi) {
        return 0xFF000000 | (IIiiiiiIIi[0] & 0xFF) << 16 | (IIiiiiiIIi[1] & 0xFF) << 8 | IIiiiiiIIi[2] & 0xFF;
    }

    public static int IIIiiiIiii(float[] IIiiiiiIIi) {
        return 0xFF000000 | ((int)((double)(IIiiiiiIIi[0] * 255.0f) + 0.5) & 0xFF) << 16 | ((int)((double)(IIiiiiiIIi[1] * 255.0f) + 0.5) & 0xFF) << 8 | (int)((double)(IIiiiiiIIi[2] * 255.0f) + 0.5) & 0xFF;
    }

    public static int iIIiiiIIiI(int IIiiiiiIIi, int IIiiiiiIIi2) {
        int[] IIiiiiiIIi3 = iiiiiiiiii_4.IIIiiiIiii(IIiiiiiIIi2);
        int IIiiiiiIIi4 = IIiiiiiIIi2 >>> 24 & 0xFF;
        int[] IIiiiiiIIi5 = iiiiiiiiii_4.IIIiiiIiii(IIiiiiiIIi);
        int IIiiiiiIIi6 = IIiiiiiIIi5[0] + (IIiiiiiIIi3[0] - IIiiiiiIIi5[0]) * IIiiiiiIIi4;
        int IIiiiiiIIi7 = IIiiiiiIIi5[1] + (IIiiiiiIIi3[1] - IIiiiiiIIi5[1]) * IIiiiiiIIi4;
        int IIiiiiiIIi8 = IIiiiiiIIi5[2] + (IIiiiiiIIi3[2] - IIiiiiiIIi5[2]) * IIiiiiiIIi4;
        return iiiiiiiiii_4.IIIiiiIiii(IIiiiiiIIi6, IIiiiiiIIi7, IIiiiiiIIi8);
    }

    public static int IIIiiiIiii() {
        Random IIiiiiiIIi = new Random();
        int IIiiiiiIIi2 = IIiiiiiIIi.nextInt(255);
        int IIiiiiiIIi3 = IIiiiiiIIi.nextInt(255);
        int IIiiiiiIIi4 = IIiiiiiIIi.nextInt(255);
        int IIiiiiiIIi5 = 100;
        int IIiiiiiIIi6 = Math.max(IIiiiiiIIi2, Math.max(IIiiiiiIIi3, IIiiiiiIIi4));
        if (IIiiiiiIIi6 < IIiiiiiIIi5) {
            if (IIiiiiiIIi2 == IIiiiiiIIi6) {
                IIiiiiiIIi2 = IIiiiiiIIi5;
            } else if (IIiiiiiIIi3 == IIiiiiiIIi6) {
                IIiiiiiIIi3 = IIiiiiiIIi5;
            } else {
                IIiiiiiIIi4 = IIiiiiiIIi5;
            }
        }
        return iiiiiiiiii_4.IIIiiiIiii(IIiiiiiIIi2, IIiiiiiIIi3, IIiiiiiIIi4);
    }

    public static int iIIiiiIIiI(int IIiiiiiIIi, float IIiiiiiIIi2) {
        int[] IIiiiiiIIi3 = iiiiiiiiii_4.IIIiiiIiii(IIiiiiiIIi, IIiiiiiIIi2);
        return (IIiiiiiIIi3[3] & 0xFF) << 24 | (IIiiiiiIIi3[0] & 0xFF) << 16 | (IIiiiiiIIi3[2] & 0xFF) << 8 | IIiiiiiIIi3[1] & 0xFF;
    }

    public static int IiiiiiIIiI(int IIiiiiiIIi) {
        return IIiiiiiIIi < 0 ? 0 : (IIiiiiiIIi > 255 ? 255 : IIiiiiiIIi);
    }

    public static int[] IIIiiiIiii(int IIiiiiiIIi, float IIiiiiiIIi2) {
        return new int[]{IIiiiiiIIi >> 16 & 0xFF, IIiiiiiIIi >> 8 & 0xFF, IIiiiiiIIi & 0xFF, (int)((double)(IIiiiiiIIi2 * 255.0f) + 0.5) & 0xFF};
    }

    public static int IIIiiiIiii(int IIiiiiiIIi, float IIiiiiiIIi2) {
        return IIiiiiiIIi2 == 1.0f ? IIiiiiiIIi : iiiiiiiiii_4.IIIiiiIiii(iiiiiiiiii_4.IIIiiiIiii(iiiiiiiiii_4.IIIiiiIiii(IIiiiiiIIi), IIiiiiiIIi2));
    }

    public static boolean iIIiiiIIiI(int IIiiiiiIIi) {
        return IIiiiiiIIi == -16777216 || IIiiiiiIIi == 0;
    }

    public static int IIIiiiIiii(int IIiiiiiIIi, int IIiiiiiIIi2) {
        float[] IIiiiiiIIi3 = iiiiiiiiii_4.IIIiiiIiii(IIiiiiiIIi);
        float[] IIiiiiiIIi4 = iiiiiiiiii_4.IIIiiiIiii(IIiiiiiIIi2);
        IIiiiiiIIi3[0] = IIiiiiiIIi3[0] * IIiiiiiIIi4[0];
        IIiiiiiIIi3[1] = IIiiiiiIIi3[1] * IIiiiiiIIi4[1];
        IIiiiiiIIi3[2] = IIiiiiiIIi3[2] * IIiiiiiIIi4[2];
        return iiiiiiiiii_4.IIIiiiIiii(IIiiiiiIIi3);
    }

    public static float[] IIIiiiIiii(int IIiiiiiIIi, float IIiiiiiIIi2) {
        return new float[]{(float)(IIiiiiiIIi >> 16 & 0xFF) / 255.0f, (float)(IIiiiiiIIi >> 8 & 0xFF) / 255.0f, (float)(IIiiiiiIIi & 0xFF) / 255.0f, iiiiiiiiii_4.IIIiiiIiii(IIiiiiiIIi2)};
    }

    public static int IIIiiiIiii(int IIiiiiiIIi, float IIiiiiiIIi2, float[] IIiiiiiIIi3) {
        float[] IIiiiiiIIi4 = iiiiiiiiii_4.IIIiiiIiii(IIiiiiiIIi);
        IIiiiiiIIi4[0] = IIiiiiiIIi4[0] * (IIiiiiiIIi2 + IIiiiiiIIi3[0]);
        IIiiiiiIIi4[1] = IIiiiiiIIi4[1] * (IIiiiiiIIi2 + IIiiiiiIIi3[1]);
        IIiiiiiIIi4[2] = IIiiiiiIIi4[2] * (IIiiiiiIIi2 + IIiiiiiIIi3[2]);
        return iiiiiiiiii_4.IIIiiiIiii(iiiiiiiiii_4.IIIiiiIiii(IIiiiiiIIi4, 1.0f));
    }

    public static String IIIiiiIiii(Integer IIiiiiiIIi) {
        if (IIiiiiiIIi == null) {
            return "null";
        }
        int[] IIiiiiiIIi2 = iiiiiiiiii_4.IIIiiiIiii((int)IIiiiiiIIi);
        return String.format("r=%s,g=%s,b=%s", IIiiiiiIIi2[0], IIiiiiiIIi2[1], IIiiiiiIIi2[2]);
    }

    public static int iIIiiiIIiI(int IIiiiiiIIi) {
        return iiiiiiiiii_4.IIIiiiIiii(IIiiiiiIIi & 0xFF, IIiiiiiIIi >>> 8 & 0xFF, IIiiiiiIIi >>> 16 & 0xFF);
    }

    public static Color IIIiiiIiii(Integer IIiiiiiIIi) {
        return IIiiiiiIIi == null ? null : new Color(IIiiiiiIIi);
    }

    private /* synthetic */ iiiiiiiiii_4() {
        iiiiiiiiii_4 IIiiiiiIIi;
    }

    public static float IIIiiiIiii(float IIiiiiiIIi) {
        return IIiiiiiIIi < 0.0f ? 0.0f : (IIiiiiiIIi > 1.0f ? 1.0f : IIiiiiiIIi);
    }

    public static float[] IIIiiiIiii(float[] IIiiiiiIIi, float IIiiiiiIIi2) {
        float IIiiiiiIIi3 = IIiiiiiIIi[0] * IIiiiiiIIi2;
        float IIiiiiiIIi4 = IIiiiiiIIi[1] * IIiiiiiIIi2;
        float IIiiiiiIIi5 = IIiiiiiIIi[2] * IIiiiiiIIi2;
        float f = IIiiiiiIIi3 < 0.0f ? 0.0f : (IIiiiiiIIi[0] = IIiiiiiIIi3 > 1.0f ? 1.0f : IIiiiiiIIi3);
        float f2 = IIiiiiiIIi4 < 0.0f ? 0.0f : (IIiiiiiIIi[1] = IIiiiiiIIi4 > 1.0f ? 1.0f : IIiiiiiIIi4);
        IIiiiiiIIi[2] = IIiiiiiIIi5 < 0.0f ? 0.0f : (IIiiiiiIIi5 > 1.0f ? 1.0f : IIiiiiiIIi5);
        return IIiiiiiIIi;
    }

    public static int IIIiiiIiii(String IIiiiiiIIi) {
        if (!Strings.isNullOrEmpty((String)IIiiiiiIIi)) {
            try {
                return 0xFF000000 | Integer.parseInt(IIiiiiiIIi.replaceFirst("#", ""), 16);
            }
            catch (Exception IIiiiiiIIi2) {
                System.out.println("Invalid color string: " + IIiiiiiIIi);
            }
        }
        return 0;
    }

    public static int IIIiiiIiii(int IIiiiiiIIi) {
        int[] IIiiiiiIIi2 = iiiiiiiiii_4.IIIiiiIiii(IIiiiiiIIi);
        int IIiiiiiIIi3 = iiiiiiiiii_4.IiiiiiIIiI((IIiiiiiIIi2[0] + IIiiiiiIIi2[1] + IIiiiiiIIi2[2]) / 3);
        return iiiiiiiiii_4.IIIiiiIiii(IIiiiiiIIi3, IIiiiiiIIi3, IIiiiiiIIi3);
    }

    public static int[] IIIiiiIiii(int IIiiiiiIIi, int IIiiiiiIIi2) {
        return new int[]{IIiiiiiIIi >> 16 & 0xFF, IIiiiiiIIi >> 8 & 0xFF, IIiiiiiIIi & 0xFF, IIiiiiiIIi2 & 0xFF};
    }

    public static int IIIiiiIiii(float IIiiiiiIIi) {
        return iiiiiiiiii_4.IiiiiiIIiI((int)(IIiiiiiIIi * 255.0f));
    }

    public static boolean IIIiiiIiii(int IIiiiiiIIi) {
        return IIiiiiiIIi == -1 || IIiiiiiIIi == 0xFFFFFF;
    }

    public static int IIIiiiIiii(float IIiiiiiIIi, float IIiiiiiIIi2, float IIiiiiiIIi3) {
        return 0xFF000000 | ((int)((double)(IIiiiiiIIi * 255.0f) + 0.5) & 0xFF) << 16 | ((int)((double)(IIiiiiiIIi2 * 255.0f) + 0.5) & 0xFF) << 8 | (int)((double)(IIiiiiiIIi3 * 255.0f) + 0.5) & 0xFF;
    }

    public static int IIIiiiIiii(int IIiiiiiIIi, int IIiiiiiIIi2, float IIiiiiiIIi3) {
        if (IIiiiiiIIi3 == 1.0f) {
            return IIiiiiiIIi2;
        }
        if (IIiiiiiIIi3 == 0.0f) {
            return IIiiiiiIIi;
        }
        float[] IIiiiiiIIi4 = iiiiiiiiii_4.IIIiiiIiii(IIiiiiiIIi);
        float[] IIiiiiiIIi5 = iiiiiiiiii_4.IIIiiiIiii(IIiiiiiIIi2);
        IIiiiiiIIi4[0] = IIiiiiiIIi5[0] * IIiiiiiIIi3 / 1.0f + IIiiiiiIIi4[0] * (1.0f - IIiiiiiIIi3);
        IIiiiiiIIi4[1] = IIiiiiiIIi5[1] * IIiiiiiIIi3 / 1.0f + IIiiiiiIIi4[1] * (1.0f - IIiiiiiIIi3);
        IIiiiiiIIi4[2] = IIiiiiiIIi5[2] * IIiiiiiIIi3 / 1.0f + IIiiiiiIIi4[2] * (1.0f - IIiiiiiIIi3);
        return iiiiiiiiii_4.IIIiiiIiii(IIiiiiiIIi4);
    }
}

