/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.primitives.Primitives
 *  net.minecraft.client.Minecraft
 *  net.minecraft.nbt.NBTBase
 *  net.minecraft.nbt.NBTTagCompound
 *  net.minecraft.nbt.NBTTagList
 *  net.minecraft.util.ResourceLocation
 *  net.minecraft.util.registry.RegistryNamespaced
 *  net.minecraftforge.fml.relauncher.Side
 *  net.minecraftforge.fml.relauncher.SideOnly
 */
package eos.moe.dragoncore;

import com.google.common.primitives.Primitives;
import eos.moe.dragoncore.rs;
import java.io.BufferedInputStream;
import java.io.IOException;
import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import net.minecraft.client.Minecraft;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.registry.RegistryNamespaced;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

/*
 * Duplicate member names - consider using --renamedupmembers true
 */
public class fr {
    private static Field o;
    private static Field y;
    private static Field k;
    public static int[] ALLATORIxDEMO;

    public fr() {
        fr a2;
    }

    public static void ALLATORIxDEMO(ArrayList<?> a2, int a3) {
        while (a2.size() <= a3) {
            a2.add(null);
        }
    }

    public static <T> T ALLATORIxDEMO(ArrayList<T> a2, T a3, int a4) {
        if (a2.size() <= a4) {
            a2.add(a4, a3);
            return null;
        }
        return a2.set(a4, a3);
    }

    public static <T> void ALLATORIxDEMO(ArrayList<T> a2, T a3) {
        int a4 = a2.indexOf(null);
        if (a4 == -1) {
            a2.add(a3);
        } else {
            a2.set(a4, a3);
        }
    }

    public static <E> Map<String, E> ALLATORIxDEMO(NBTTagCompound a2) {
        try {
            return (Map)o.get(a2);
        }
        catch (Exception a3) {
            System.err.println(a2);
            a3.printStackTrace();
            return null;
        }
    }

    public static ArrayList<NBTBase> ALLATORIxDEMO(NBTTagList a2) {
        try {
            return (ArrayList)y.get(a2);
        }
        catch (Exception a3) {
            System.err.println(a2);
            System.err.println(y);
            a3.printStackTrace();
            return null;
        }
    }

    public static int ALLATORIxDEMO(Object a2) throws ClassCastException {
        Number a3;
        int a4;
        int a5 = -1;
        int a6 = 0;
        Double a7 = null;
        for (a4 = 0; a4 < Array.getLength(a2); ++a4) {
            a3 = (Number)Array.get(a2, a4);
            if (a7 != null && !(a3.doubleValue() > a7)) continue;
            a7 = a3.doubleValue();
        }
        for (a4 = 0; a4 < Array.getLength(a2); ++a4) {
            a3 = (Number)Array.get(a2, a4);
            if (a3.doubleValue() == a7.doubleValue()) {
                ++a6;
                a5 = a4;
            }
            if (a6 <= 1) continue;
            return -1;
        }
        return a5;
    }

    public static Object[] ALLATORIxDEMO(Object a2) {
        if (!a2.getClass().isArray()) {
            throw new IllegalArgumentException("The variable 'primitiveArray' must ACTUALLY BE AN ARRAY!");
        }
        Class a3 = a2.getClass().getComponentType();
        if (!a3.isPrimitive()) {
            return (Object[])a2;
        }
        a3 = Primitives.wrap(a3);
        Object a4 = Array.newInstance(a3, Array.getLength(a2));
        for (int a5 = 0; a5 < Array.getLength(a2); ++a5) {
            Array.set(a4, a5, Array.get(a2, a5));
        }
        return (Object[])a4;
    }

    public static <K, V> Map<K, V> ALLATORIxDEMO(Map<K, V> a2, K[] a3, V[] a4) {
        for (int a5 = 0; a5 < a3.length; ++a5) {
            a2.put(a3[a5], a4[a5]);
        }
        return a2;
    }

    public static <K, V> Map<K, V> ALLATORIxDEMO(Map<K, V> a2, Object[] a3) {
        for (int a4 = 0; a4 < a3.length / 2; ++a4) {
            a2.put(a3[a4 * 2], a3[a4 * 2 + 1]);
        }
        return a2;
    }

    public static <V> V ALLATORIxDEMO(Map<String, V> a2, String a3) {
        if (a3 == null) {
            return a2.get(null);
        }
        for (Map.Entry<String, V> a4 : a2.entrySet()) {
            if (!a3.equalsIgnoreCase(a4.getKey())) continue;
            return a4.getValue();
        }
        return null;
    }

    public static String ALLATORIxDEMO(String a2) {
        return rs.l.matcher(a2).replaceAll("$1").trim();
    }

    public static byte[] ALLATORIxDEMO(int a2, int a3) {
        if (a3 < 1 || a3 > 8) {
            throw new IllegalArgumentException("Invalid value for size; must be between 1 and 8 inclusive");
        }
        byte[] a4 = new byte[32 / a3];
        for (int a5 = 0; a5 < 32 / a3; ++a5) {
            a4[a5] = (byte)(a2 & ALLATORIxDEMO[a3 - 1]);
            a2 >>= a3;
        }
        return a4;
    }

    public static int ALLATORIxDEMO(byte[] a2, int a3) {
        if (a3 < 1 || a3 > 8) {
            throw new IllegalArgumentException("Invalid value for size; must be between 1 and 8 inclusive");
        }
        int a4 = 0;
        for (int a5 = 0; a5 < 32 / a3 && a5 < a2.length; ++a5) {
            a4 += (a2[a5] & ALLATORIxDEMO[a3 - 1]) << a3 * a5;
        }
        return a4;
    }

    @SideOnly(value=Side.CLIENT)
    public static BufferedInputStream ALLATORIxDEMO(ResourceLocation a2) {
        try {
            return new BufferedInputStream(Minecraft.func_71410_x().func_110442_L().func_110536_a(a2).func_110527_b());
        }
        catch (IOException a3) {
            a3.printStackTrace();
            return null;
        }
    }

    static {
        Field[] a2;
        Field[] a3;
        Field[] a4;
        for (Field a5 : a4 = NBTTagCompound.class.getDeclaredFields()) {
            if (!Map.class.isAssignableFrom(a5.getType())) continue;
            o = a5;
            o.setAccessible(true);
            break;
        }
        for (Field a6 : a3 = NBTTagList.class.getDeclaredFields()) {
            if (!List.class.isAssignableFrom(a6.getType())) continue;
            y = a6;
            y.setAccessible(true);
            break;
        }
        for (Field a7 : a2 = RegistryNamespaced.class.getDeclaredFields()) {
            if (!Map.class.isAssignableFrom(a7.getType())) continue;
            k = a7;
            k.setAccessible(true);
            break;
        }
        ALLATORIxDEMO = new int[]{1, 3, 7, 15, 31, 63, 127, 255};
    }
}

