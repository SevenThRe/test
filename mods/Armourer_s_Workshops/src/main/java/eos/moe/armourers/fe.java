/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.item.ItemStack
 *  net.minecraft.nbt.NBTTagCompound
 *  net.minecraft.util.math.MathHelper
 */
package eos.moe.armourers;

import eos.moe.armourers.te;
import eos.moe.armourers.ym;
import java.awt.Color;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.math.MathHelper;

/*
 * Duplicate member names - consider using --renamedupmembers true
 */
public class fe {
    private static String j = "toolPaint";

    public static Color y(ItemStack a2) {
        byte[] byArray = fe.y(a2);
        return new Color(byArray[0] & 0xFF, byArray[1] & 0xFF, byArray[2] & 0xFF, 255);
    }

    public static byte[] y(ItemStack a2) {
        NBTTagCompound nBTTagCompound = a2.getTagCompound();
        if (nBTTagCompound != null && nBTTagCompound.hasKey(j, 7)) {
            return nBTTagCompound.getByteArray(j);
        }
        return fe.r();
    }

    public static int r(byte[] a2) {
        return (a2[3] & 0xFF) << 24 | (a2[0] & 0xFF) << 16 | (a2[1] & 0xFF) << 8 | a2[2] & 0xFF;
    }

    public static ym r(ItemStack a2) {
        return te.r(fe.y(a2)[3]);
    }

    public fe() {
        fe a2;
    }

    public static byte[] r(ItemStack a2) {
        byte[] byArray = fe.y(a2);
        byte[] byArray2 = new byte[3];
        byArray2[0] = byArray[0];
        byArray2[1] = byArray[1];
        byArray2[2] = byArray[2];
        return byArray2;
    }

    public static Color r(ItemStack a2) {
        byte[] byArray = fe.y(a2);
        ym ym2 = te.r(byArray[3]);
        if (ym2 == te.s) {
            return fe.r();
        }
        if (ym2 == te.p) {
            return fe.r(byArray);
        }
        if (ym2 == te.v) {
            return fe.y(byArray);
        }
        return new Color(byArray[0] & 0xFF, byArray[1] & 0xFF, byArray[2] & 0xFF, 255);
    }

    public static byte[] y() {
        byte[] byArray = new byte[3];
        byArray[0] = -1;
        byArray[1] = -1;
        byArray[2] = -1;
        return byArray;
    }

    public static void r(ItemStack a2, int a32) {
        ItemStack itemStack = a2;
        byte[] byArray = fe.y(itemStack);
        Color a32 = new Color(a32);
        byte[] byArray2 = byArray;
        byArray[0] = (byte)a32.getRed();
        byArray2[1] = (byte)a32.getGreen();
        byArray[2] = (byte)a32.getBlue();
        fe.y(itemStack, byArray2);
    }

    private static /* synthetic */ Color r() {
        float f2 = (float)(System.currentTimeMillis() % 6375L) / 25.0f;
        return new Color(Color.HSBtoRGB(f2 / 255.0f, 1.0f, 1.0f));
    }

    public static void r(NBTTagCompound a2, byte[] a3) {
        a2.setByteArray(j, a3);
    }

    private static /* synthetic */ Color y(byte[] a2) {
        float f2;
        float f3 = (float)((double)System.currentTimeMillis() % 3187.5) / 12.5f;
        f3 *= 2.0f;
        if (f2 > 255.0f) {
            f3 = 255.0f - (f3 - 255.0f);
        }
        f3 = MathHelper.clamp((float)f3, (float)0.0f, (float)255.0f);
        float[] fArray = Color.RGBtoHSB(a2[0] & 0xFF, a2[1] & 0xFF, a2[2] & 0xFF, null);
        return new Color(Color.HSBtoRGB(fArray[0], fArray[1], f3 / 255.0f));
    }

    public static int y(ItemStack a2) {
        return fe.r(a2).getRGB();
    }

    public static boolean r(ItemStack a2) {
        NBTTagCompound nBTTagCompound = a2.getTagCompound();
        return nBTTagCompound != null && nBTTagCompound.hasKey(j);
    }

    public static double r() {
        return Math.round((double)(System.currentTimeMillis() % 6375L) / 25.0);
    }

    public static void y(ItemStack a2, byte[] a3) {
        NBTTagCompound nBTTagCompound = a2.getTagCompound();
        if (nBTTagCompound == null) {
            nBTTagCompound = new NBTTagCompound();
        }
        nBTTagCompound.setByteArray(j, a3);
        a2.setTagCompound(nBTTagCompound);
    }

    private static /* synthetic */ Color r(byte[] a2) {
        float f2;
        float f3 = (float)((double)System.currentTimeMillis() % 6375.0) / 25.0f;
        f3 *= 2.0f;
        if (f2 > 255.0f) {
            f3 = 255.0f - (f3 - 255.0f);
        }
        f3 = MathHelper.clamp((float)f3, (float)0.0f, (float)255.0f);
        float[] fArray = Color.RGBtoHSB(a2[0] & 0xFF, a2[1] & 0xFF, a2[2] & 0xFF, null);
        return new Color(Color.HSBtoRGB(fArray[0], fArray[1], f3 / 255.0f));
    }

    public static int r(ItemStack a2) {
        return fe.y(a2).getRGB();
    }

    public static void r(ItemStack a2, ym a3) {
        ItemStack itemStack = a2;
        byte[] byArray = fe.y(itemStack);
        byArray[3] = (byte)a3.z();
        fe.y(itemStack, byArray);
    }

    public static byte[] r(int a2) {
        int n2 = 0xFF & a2 >> 24;
        int n3 = 0xFF & a2 >> 16;
        int n4 = 0xFF & a2 >> 8;
        int n5 = 0xFF & a2 >> 0;
        byte[] byArray = new byte[4];
        byArray[0] = (byte)n3;
        byArray[1] = (byte)n4;
        byArray[2] = (byte)n5;
        byArray[3] = (byte)n2;
        return byArray;
    }

    public static byte[] r() {
        byte[] byArray = new byte[4];
        byArray[0] = -1;
        byArray[1] = -1;
        byArray[2] = -1;
        byArray[3] = -1;
        return byArray;
    }

    public static void r(ItemStack a2, byte[] a3) {
        byte[] byArray;
        ItemStack itemStack = a2;
        byte[] byArray2 = byArray = fe.y(itemStack);
        byArray[0] = a3[0];
        byArray2[1] = a3[1];
        byArray[2] = a3[2];
        fe.y(itemStack, byArray2);
    }
}

