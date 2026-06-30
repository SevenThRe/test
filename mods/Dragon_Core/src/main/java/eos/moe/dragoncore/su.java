/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.base.Strings
 *  net.minecraft.nbt.NBTBase
 *  net.minecraft.nbt.NBTTagByte
 *  net.minecraft.nbt.NBTTagByteArray
 *  net.minecraft.nbt.NBTTagCompound
 *  net.minecraft.nbt.NBTTagDouble
 *  net.minecraft.nbt.NBTTagEnd
 *  net.minecraft.nbt.NBTTagFloat
 *  net.minecraft.nbt.NBTTagInt
 *  net.minecraft.nbt.NBTTagIntArray
 *  net.minecraft.nbt.NBTTagList
 *  net.minecraft.nbt.NBTTagLong
 *  net.minecraft.nbt.NBTTagShort
 *  net.minecraft.nbt.NBTTagString
 */
package eos.moe.dragoncore;

import com.google.common.base.Strings;
import eos.moe.dragoncore.fi;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagByte;
import net.minecraft.nbt.NBTTagByteArray;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagDouble;
import net.minecraft.nbt.NBTTagEnd;
import net.minecraft.nbt.NBTTagFloat;
import net.minecraft.nbt.NBTTagInt;
import net.minecraft.nbt.NBTTagIntArray;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.nbt.NBTTagLong;
import net.minecraft.nbt.NBTTagShort;
import net.minecraft.nbt.NBTTagString;

/*
 * Duplicate member names - consider using --renamedupmembers true
 */
public class su {
    public static final char ALLATORIxDEMO = '\u00a7';

    public su() {
        su a2;
    }

    public static String c(fi a2) {
        String a3 = a2.ALLATORIxDEMO();
        NBTBase a4 = a2.ALLATORIxDEMO();
        String a5 = su.ALLATORIxDEMO(a4);
        return Strings.isNullOrEmpty((String)a3) ? "" + a5 : a3 + ": " + a5;
    }

    public static String ALLATORIxDEMO(fi a2) {
        String a3 = a2.ALLATORIxDEMO();
        NBTBase a4 = a2.ALLATORIxDEMO();
        String a5 = su.ALLATORIxDEMO(a4);
        return Strings.isNullOrEmpty((String)a3) ? "" + a5 : a3 + ": " + a5 + '\u00a7' + 'r';
    }

    public static NBTBase ALLATORIxDEMO(byte a2) {
        switch (a2) {
            case 0: {
                return new NBTTagEnd();
            }
            case 1: {
                return new NBTTagByte(0);
            }
            case 2: {
                return new NBTTagShort();
            }
            case 3: {
                return new NBTTagInt(0);
            }
            case 4: {
                return new NBTTagLong(0L);
            }
            case 5: {
                return new NBTTagFloat(0.0f);
            }
            case 6: {
                return new NBTTagDouble(0.0);
            }
            case 7: {
                return new NBTTagByteArray(new byte[0]);
            }
            case 8: {
                return new NBTTagString("");
            }
            case 9: {
                return new NBTTagList();
            }
            case 10: {
                return new NBTTagCompound();
            }
            case 11: {
                return new NBTTagIntArray(new int[0]);
            }
        }
        return null;
    }

    public static String ALLATORIxDEMO(NBTBase a2) {
        switch (a2.func_74732_a()) {
            case 1: {
                return "" + ((NBTTagByte)a2).func_150290_f();
            }
            case 2: {
                return "" + ((NBTTagShort)a2).func_150289_e();
            }
            case 3: {
                return "" + ((NBTTagInt)a2).func_150287_d();
            }
            case 4: {
                return "" + ((NBTTagLong)a2).func_150291_c();
            }
            case 5: {
                return "" + ((NBTTagFloat)a2).func_150288_h();
            }
            case 6: {
                return "" + ((NBTTagDouble)a2).func_150286_g();
            }
            case 7: {
                return a2.toString();
            }
            case 8: {
                return ((NBTTagString)a2).func_150285_a_();
            }
            case 9: {
                return "(TagList)";
            }
            case 10: {
                return "(TagCompound)";
            }
            case 11: {
                return a2.toString();
            }
        }
        return "?";
    }

    public static String ALLATORIxDEMO(byte a2) {
        switch (a2) {
            case 1: {
                return "Byte";
            }
            case 2: {
                return "Short";
            }
            case 3: {
                return "Int";
            }
            case 4: {
                return "Long";
            }
            case 5: {
                return "Float";
            }
            case 6: {
                return "Double";
            }
            case 7: {
                return "Byte[]";
            }
            case 8: {
                return "String";
            }
            case 9: {
                return "List";
            }
            case 10: {
                return "Compound";
            }
            case 11: {
                return "Int[]";
            }
            case 12: {
                return "\u7f16\u8f91";
            }
            case 13: {
                return "\u5220\u9664";
            }
            case 14: {
                return "\u590d\u5236";
            }
            case 15: {
                return "\u526a\u5207";
            }
            case 16: {
                return "\u7c98\u8d34";
            }
        }
        return "Unknown";
    }
}

