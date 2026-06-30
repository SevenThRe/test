/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemArmor
 *  net.minecraft.item.ItemStack
 *  net.minecraft.nbt.JsonToNBT
 *  net.minecraft.nbt.NBTBase
 *  net.minecraft.nbt.NBTException
 *  net.minecraft.nbt.NBTTagCompound
 *  net.minecraft.nbt.NBTTagString
 */
package eos.moe.dragoncore;

import eos.moe.dragoncore.al;
import eos.moe.dragoncore.dj;
import eos.moe.dragoncore.i;
import eos.moe.dragoncore.qg;
import eos.moe.dragoncore.v;
import eos.moe.dragoncore.wk;
import eos.moe.dragoncore.xk;
import java.util.List;
import java.util.regex.Pattern;
import net.minecraft.item.Item;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.JsonToNBT;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTException;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagString;

/*
 * Duplicate member names - consider using --renamedupmembers true
 */
public class tm {
    public tm() {
        tm a2;
    }

    @i(f={"\u5e8f\u5217\u5316\u7269\u54c1", "ItemStack_Serialize"})
    public static String c(ItemStack a2) {
        NBTTagCompound a3 = new NBTTagCompound();
        a2.writeToNBT(a3);
        return a3.toString();
    }

    @i(f={"\u53cd\u5e8f\u5217\u5316\u7269\u54c1", "ItemStack_DeSerialize"})
    public static xk ALLATORIxDEMO(String a2) {
        try {
            NBTTagCompound a3 = JsonToNBT.getTagFromJson((String)a2);
            return new xk(new ItemStack(a3));
        }
        catch (NBTException a4) {
            return new xk(ItemStack.EMPTY);
        }
    }

    @i(f={"\u53d6\u7269\u54c1\u6570", "ItemStack_Get_Count"})
    public static int x(ItemStack a2) {
        return a2.isEmpty() ? 0 : a2.getCount();
    }

    @i(f={"\u53d6\u7269\u54c1\u540d", "ItemStack_Get_Name"})
    public static String ALLATORIxDEMO(ItemStack a2) {
        return a2.isEmpty() ? "" : a2.getDisplayName();
    }

    @i(f={"\u53d6\u7269\u54c1Lore\u6570", "ItemStack_Get_Lore_Size"})
    public static int f(ItemStack a2) {
        List<String> a3 = dj.c(a2);
        return a3.size();
    }

    @i(f={"\u53d6\u7269\u54c1Lore", "ItemStack_Get_Lore"})
    public static String ALLATORIxDEMO(ItemStack a2, int a3, boolean a4) {
        List<String> a5 = dj.ALLATORIxDEMO(a2, a4);
        if (a3 < 0) {
            a3 = a5.size() + a3;
        }
        return a5.size() >= a3 + 1 && a3 >= 0 ? a5.get(a3) : "";
    }

    @i(f={"\u53d6\u7269\u54c1\u6240\u6709Lore", "ItemStack_Get_All_Lore"})
    public static qg ALLATORIxDEMO(ItemStack a2, boolean a3) {
        List<String> a4 = dj.ALLATORIxDEMO(a2, a3);
        return new qg(a4, false);
    }

    @i(f={"\u53d6\u7269\u54c1\u4fe1\u606f\u6570", "ItemStack_Get_Info_Size"})
    public static int c(ItemStack a2) {
        List<String> a3 = dj.ALLATORIxDEMO(a2);
        return a3.size();
    }

    @i(f={"\u53d6\u7269\u54c1\u4fe1\u606f", "ItemStack_Get_Info"})
    public static String ALLATORIxDEMO(ItemStack a2, int a3) {
        List<String> a4 = dj.ALLATORIxDEMO(a2);
        if (a3 < 0) {
            a3 = a4.size() + a3;
        }
        return a4.size() >= a3 + 1 ? a4.get(a3) : "";
    }

    @i(f={"\u53d6\u7269\u54c1\u6240\u6709\u4fe1\u606f", "ItemStack_Get_All_Info"})
    public static qg ALLATORIxDEMO(ItemStack a2) {
        List<String> a3 = dj.ALLATORIxDEMO(a2);
        return new qg(a3, false);
    }

    @i(f={"\u53d6\u7269\u54c1NBT", "ItemStack_Get_NBT"})
    public static String ALLATORIxDEMO(ItemStack a2, String a3) {
        NBTTagCompound a4 = a2.getTagCompound();
        if (a4 != null) {
            NBTBase a5;
            if (a3.isEmpty()) {
                return a4.toString();
            }
            String[] a6 = a3.split("\\.");
            for (int a7 = 0; a7 < a6.length - 1; ++a7) {
                a4 = a4.getCompoundTag(a6[a7]);
            }
            if (a6.length > 0 && (a5 = a4.getTag(a6[a6.length - 1])) != null) {
                if (a5.getId() == 8) {
                    return ((NBTTagString)a5).getString();
                }
                return a5.toString();
            }
        }
        return "";
    }

    @i(f={"\u5339\u914d\u7269\u54c1", "ItemStack_Match"})
    public static boolean ALLATORIxDEMO(ItemStack a2, String a3, v a4) {
        if (a2.isEmpty()) {
            return false;
        }
        if (a2.getItem().getRegistryName() != null && a2.getItem().getRegistryName().getPath().contains("shulker_box")) {
            return false;
        }
        Pattern a5 = Pattern.compile(a3);
        String a6 = dj.ALLATORIxDEMO(a2, false, false);
        int a7 = Item.getIdFromItem((Item)a2.getItem());
        return (a4 instanceof wk || a7 == (int)a4.ALLATORIxDEMO()) && al.ALLATORIxDEMO(a5, a6);
    }

    @i(f={"\u53d6\u7269\u54c1\u62a4\u7532\u503c", "ItemStack_Get_Armor"})
    public static int ALLATORIxDEMO(ItemStack a2) {
        return a2.getItem() instanceof ItemArmor ? ((ItemArmor)a2.getItem()).damageReduceAmount : 0;
    }
}

