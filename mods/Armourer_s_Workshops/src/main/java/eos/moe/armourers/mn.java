/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  io.netty.buffer.ByteBuf
 *  net.minecraft.nbt.NBTBase
 *  net.minecraft.nbt.NBTTagCompound
 *  net.minecraft.util.StringUtils
 *  net.minecraftforge.fml.common.network.ByteBufUtils
 *  org.apache.logging.log4j.Level
 */
package eos.moe.armourers;

import eos.moe.armourers.bm;
import eos.moe.armourers.n;
import io.netty.buffer.ByteBuf;
import java.util.Arrays;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.StringUtils;
import net.minecraftforge.fml.common.network.ByteBufUtils;
import org.apache.logging.log4j.Level;

/*
 * Duplicate member names - consider using --renamedupmembers true
 */
public class mn
implements n {
    private static String g;
    public static int z;
    private static String t;
    private static String w;
    private static String r;
    private String[] l;
    private boolean[] c;
    private static String v;
    private byte[][] s = new byte[z][4];
    private static String m;
    private static String j;

    public boolean equals(Object a2) {
        mn a3;
        if (a3 == a2) {
            return true;
        }
        if (a2 == null) {
            return false;
        }
        if (a3.getClass() != a2.getClass()) {
            return false;
        }
        a2 = (mn)a2;
        if (!Arrays.deepEquals((Object[])a3.s, (Object[])((mn)a2).s)) {
            return false;
        }
        if (!Arrays.equals(a3.c, ((mn)a2).c)) {
            return false;
        }
        return Arrays.equals(a3.l, ((mn)a2).l);
    }

    @Override
    public void r(int a2) {
        mn a3;
        mn mn2 = a3;
        byte[] byArray = new byte[4];
        byArray[0] = 0;
        byArray[1] = 0;
        byArray[2] = 0;
        byArray[3] = -1;
        mn2.s[a2] = byArray;
        mn2.c[a2] = false;
        mn2.l[a2] = null;
    }

    public mn(n a2) {
        a3();
        int n2;
        mn a3;
        int n3 = n2 = 0;
        while (n3 < z) {
            if (a2.r(n2)) {
                int n4 = n2;
                a3.r(n4, a2.r(n4));
            }
            n3 = ++n2;
        }
    }

    @Override
    public void r(byte[] a2, String a3) {
        mn a4;
        int n2;
        if (a2.length != 4) {
            bm.r(Level.WARN, "Something tried to set an invalid dye colour.");
            Thread.dumpStack();
            return;
        }
        int n3 = n2 = 0;
        while (n3 < a4.c.length) {
            if (!a4.c[n2]) {
                mn mn2 = a4;
                mn2.s[n2] = a2;
                mn2.c[n2] = true;
                mn2.l[n2] = a3;
                return;
            }
            n3 = ++n2;
        }
    }

    @Override
    public int r() {
        int n2;
        int n3 = 0;
        int n4 = n2 = 0;
        while (n4 < z) {
            mn a2;
            if (a2.c[n2]) {
                ++n3;
            }
            n4 = ++n2;
        }
        return n3;
    }

    @Override
    public void r(int a2, byte[] a3, String a4) {
        mn a5;
        if (a3.length != 4) {
            bm.r(Level.WARN, "Something tried to set an invalid dye colour.");
            Thread.dumpStack();
            return;
        }
        mn mn2 = a5;
        mn2.s[a2] = a3;
        mn2.c[a2] = true;
        mn2.l[a2] = a4;
    }

    public mn() {
        mn a2;
        mn mn2 = a2;
        mn2.c = new boolean[z];
        mn2.l = new String[z];
    }

    public void r(NBTTagCompound a2) {
        int n2;
        a2 = a2.getCompoundTag(j);
        int n3 = n2 = 0;
        while (n3 < z) {
            mn a3;
            if (a2.hasKey(v + n2, 7)) {
                NBTTagCompound nBTTagCompound;
                mn mn2 = a3;
                int n4 = n2;
                mn2.s[n4] = a2.getByteArray(new StringBuilder().insert(0, v).append(n4).toString());
                if (mn2.s[n2].length == 4) {
                    nBTTagCompound = a2;
                    a3.c[n2] = true;
                } else {
                    byte[] byArray = new byte[4];
                    byArray[0] = 0;
                    byArray[1] = 0;
                    byArray[2] = 0;
                    byArray[3] = 0;
                    a3.s[n2] = byArray;
                    nBTTagCompound = a2;
                }
                if (nBTTagCompound.hasKey(new StringBuilder().insert(0, w).append(n2).toString(), 8)) {
                    int n5 = n2;
                    a3.l[n5] = a2.getString(new StringBuilder().insert(0, w).append(n5).toString());
                }
            }
            if (a2.hasKey(new StringBuilder().insert(0, v).append(n2).append(m).toString(), 1) && a2.hasKey(new StringBuilder().insert(0, v).append(n2).append(t).toString(), 1) && a2.hasKey(new StringBuilder().insert(0, v).append(n2).append(r).toString(), 1) && a2.hasKey(new StringBuilder().insert(0, v).append(n2).append(g).toString(), 1)) {
                mn mn3 = a3;
                byte[] byArray = new byte[4];
                byArray[0] = 0;
                byArray[1] = 0;
                byArray[2] = 0;
                byArray[3] = 0;
                mn3.s[n2] = byArray;
                mn3.c[n2] = true;
                mn3.s[n2][0] = a2.getByte(new StringBuilder().insert(0, v).append(n2).append(m).toString());
                mn3.s[n2][1] = a2.getByte(new StringBuilder().insert(0, v).append(n2).append(t).toString());
                mn3.s[n2][2] = a2.getByte(new StringBuilder().insert(0, v).append(n2).append(r).toString());
                NBTTagCompound nBTTagCompound = a2;
                mn3.s[n2][3] = nBTTagCompound.getByte(new StringBuilder().insert(0, v).append(n2).append(g).toString());
                if (nBTTagCompound.hasKey(new StringBuilder().insert(0, w).append(n2).toString(), 8)) {
                    int n6 = n2;
                    a3.l[n6] = a2.getString(new StringBuilder().insert(0, w).append(n6).toString());
                }
            }
            n3 = ++n2;
        }
    }

    public NBTTagCompound r(NBTTagCompound a2) {
        int n2;
        NBTTagCompound nBTTagCompound = new NBTTagCompound();
        int n3 = n2 = 0;
        while (n3 < z) {
            mn a3;
            if (a3.c[n2]) {
                NBTTagCompound nBTTagCompound2 = nBTTagCompound;
                nBTTagCompound2.setByte(v + n2 + m, a3.s[n2][0]);
                nBTTagCompound.setByte(new StringBuilder().insert(0, v).append(n2).append(t).toString(), a3.s[n2][1]);
                nBTTagCompound2.setByte(new StringBuilder().insert(0, v).append(n2).append(r).toString(), a3.s[n2][2]);
                mn mn2 = a3;
                nBTTagCompound2.setByte(new StringBuilder().insert(0, v).append(n2).append(g).toString(), mn2.s[n2][3]);
                if (!StringUtils.isNullOrEmpty((String)mn2.l[n2])) {
                    nBTTagCompound.setString(new StringBuilder().insert(0, w).append(n2).toString(), a3.l[n2]);
                }
            }
            n3 = ++n2;
        }
        NBTTagCompound nBTTagCompound3 = a2;
        nBTTagCompound3.setTag(j, (NBTBase)nBTTagCompound);
        return nBTTagCompound3;
    }

    @Override
    public void r(ByteBuf a2) {
        int n2;
        int n3 = n2 = 0;
        while (n3 < z) {
            mn a3;
            mn mn2 = a3;
            mn2.c[n2] = a2.readBoolean();
            if (mn2.c[n2]) {
                ByteBuf byteBuf = a2;
                byteBuf.readBytes(a3.s[n2]);
                if (byteBuf.readBoolean()) {
                    a3.l[n2] = ByteBufUtils.readUTF8String((ByteBuf)a2);
                }
            }
            n3 = ++n2;
        }
    }

    @Override
    public void y(ByteBuf a2) {
        int n2;
        int n3 = n2 = 0;
        while (n3 < z) {
            mn a3;
            mn mn2 = a3;
            a2.writeBoolean(mn2.c[n2]);
            if (mn2.c[n2]) {
                mn mn3 = a3;
                a2.writeBytes(mn3.s[n2]);
                if (!StringUtils.isNullOrEmpty((String)mn3.l[n2])) {
                    a2.writeBoolean(true);
                    ByteBufUtils.writeUTF8String((ByteBuf)a2, (String)a3.l[n2]);
                } else {
                    a2.writeBoolean(false);
                }
            }
            n3 = ++n2;
        }
    }

    @Override
    public String r(int a2) {
        mn a3;
        return a3.l[a2];
    }

    public String toString() {
        mn a2;
        return new StringBuilder().insert(0, "SkinDye [dyes=").append(Arrays.toString((Object[])a2.s)).append(", hasDye=").append(Arrays.toString(a2.c)).append(", names=").append(Arrays.toString(a2.l)).append("]").toString();
    }

    static {
        z = 8;
        j = "dyeData";
        v = "dye";
        w = "name";
        m = "r";
        t = "g";
        r = "b";
        g = "t";
    }

    public int hashCode() {
        mn a2;
        int n2 = 31;
        int n3 = 1;
        n3 = n2 * n3 + Arrays.deepHashCode((Object[])a2.s);
        n3 = n2 * n3 + Arrays.hashCode(a2.c);
        n3 = n2 * n3 + Arrays.hashCode(a2.l);
        return n3;
    }

    @Override
    public boolean r(int a2) {
        mn a3;
        return a3.c[a2];
    }

    @Override
    public void r(int a2, byte[] a3) {
        mn a4;
        a4.r(a2, a3, null);
    }

    @Override
    public boolean y(int a2) {
        mn a3;
        return !StringUtils.isNullOrEmpty((String)a3.l[a2]);
    }

    @Override
    public byte[] r(int a2) {
        mn a3;
        return a3.s[a2];
    }

    @Override
    public void r(byte[] a2) {
        mn a3;
        a3.r(a2, null);
    }
}

