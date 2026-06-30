/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.collect.Lists
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.util.ITooltipFlag
 *  net.minecraft.client.util.ITooltipFlag$TooltipFlags
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.item.ItemStack
 *  net.minecraft.nbt.NBTBase
 *  net.minecraft.nbt.NBTTagCompound
 *  net.minecraft.nbt.NBTTagList
 *  net.minecraft.nbt.NBTTagString
 *  net.minecraft.util.text.TextFormatting
 */
package eos.moe.dragoncore;

import com.google.common.collect.Lists;
import java.util.ArrayList;
import java.util.List;
import net.minecraft.client.Minecraft;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.nbt.NBTTagString;
import net.minecraft.util.text.TextFormatting;

public class dj {
    public dj() {
        dj a2;
    }

    public static String ALLATORIxDEMO(ItemStack a2, boolean a3, boolean a4) {
        NBTTagCompound a5 = new NBTTagCompound();
        a2.writeToNBT(a5);
        return a5.toString();
    }

    public static List<String> f(ItemStack a2) {
        ArrayList a3 = Lists.newArrayList();
        String a4 = a2.getDisplayName();
        if (a2.hasDisplayName()) {
            a4 = TextFormatting.ITALIC + a4;
        }
        a4 = a4 + TextFormatting.RESET;
        a3.add(a4);
        a3.addAll(dj.c(a2));
        return a3;
    }

    public static List<String> c(ItemStack a2) {
        return dj.ALLATORIxDEMO(a2, true);
    }

    public static List<String> ALLATORIxDEMO(ItemStack a2, boolean a3) {
        NBTTagList a4;
        NBTTagCompound a5;
        ArrayList a6 = Lists.newArrayList();
        if (a2.hasTagCompound() && a2.getTagCompound().hasKey("display", 10) && (a5 = a2.getTagCompound().getCompoundTag("display")).getTagId("Lore") == 9 && !(a4 = a5.getTagList("Lore", 8)).isEmpty()) {
            for (int a7 = 0; a7 < a4.tagCount(); ++a7) {
                if (a3) {
                    a6.add(TextFormatting.WHITE + a4.getStringTagAt(a7));
                    continue;
                }
                a6.add(a4.getStringTagAt(a7));
            }
        }
        return a6;
    }

    public static void ALLATORIxDEMO(ItemStack a2, List<String> a3) {
        NBTTagCompound a4 = a2.getOrCreateSubCompound("display");
        NBTTagList a5 = new NBTTagList();
        for (String a6 : a3) {
            a5.appendTag((NBTBase)new NBTTagString(a6));
        }
        a4.setTag("Lore", (NBTBase)a5);
    }

    public boolean ALLATORIxDEMO(ItemStack a2, ItemStack a3) {
        return a2.getItem() == a3.getItem() && a2.getItemDamage() == a3.getItemDamage() && ItemStack.areItemStackShareTagsEqual((ItemStack)a2, (ItemStack)a3);
    }

    public static List<String> ALLATORIxDEMO(ItemStack a2) {
        List a3 = a2.getTooltip((EntityPlayer)Minecraft.getMinecraft().player, (ITooltipFlag)(Minecraft.getMinecraft().gameSettings.advancedItemTooltips ? ITooltipFlag.TooltipFlags.ADVANCED : ITooltipFlag.TooltipFlags.NORMAL));
        for (int a4 = 0; a4 < a3.size(); ++a4) {
            if (a4 == 0) {
                a3.set(a4, a2.getRarity().color + (String)a3.get(a4));
                continue;
            }
            a3.set(a4, TextFormatting.GRAY + (String)a3.get(a4));
        }
        return a3;
    }
}

