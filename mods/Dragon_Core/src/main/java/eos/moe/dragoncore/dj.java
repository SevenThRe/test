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
        a2.func_77955_b(a5);
        return a5.toString();
    }

    public static List<String> f(ItemStack a2) {
        ArrayList a3 = Lists.newArrayList();
        String a4 = a2.func_82833_r();
        if (a2.func_82837_s()) {
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
        if (a2.func_77942_o() && a2.func_77978_p().func_150297_b("display", 10) && (a5 = a2.func_77978_p().func_74775_l("display")).func_150299_b("Lore") == 9 && !(a4 = a5.func_150295_c("Lore", 8)).func_82582_d()) {
            for (int a7 = 0; a7 < a4.func_74745_c(); ++a7) {
                if (a3) {
                    a6.add(TextFormatting.WHITE + a4.func_150307_f(a7));
                    continue;
                }
                a6.add(a4.func_150307_f(a7));
            }
        }
        return a6;
    }

    public static void ALLATORIxDEMO(ItemStack a2, List<String> a3) {
        NBTTagCompound a4 = a2.func_190925_c("display");
        NBTTagList a5 = new NBTTagList();
        for (String a6 : a3) {
            a5.func_74742_a((NBTBase)new NBTTagString(a6));
        }
        a4.func_74782_a("Lore", (NBTBase)a5);
    }

    public boolean ALLATORIxDEMO(ItemStack a2, ItemStack a3) {
        return a2.func_77973_b() == a3.func_77973_b() && a2.func_77952_i() == a3.func_77952_i() && ItemStack.areItemStackShareTagsEqual((ItemStack)a2, (ItemStack)a3);
    }

    public static List<String> ALLATORIxDEMO(ItemStack a2) {
        List a3 = a2.func_82840_a((EntityPlayer)Minecraft.func_71410_x().field_71439_g, (ITooltipFlag)(Minecraft.func_71410_x().field_71474_y.field_82882_x ? ITooltipFlag.TooltipFlags.ADVANCED : ITooltipFlag.TooltipFlags.NORMAL));
        for (int a4 = 0; a4 < a3.size(); ++a4) {
            if (a4 == 0) {
                a3.set(a4, a2.func_77953_t().field_77937_e + (String)a3.get(a4));
                continue;
            }
            a3.set(a4, TextFormatting.GRAY + (String)a3.get(a4));
        }
        return a3;
    }
}

