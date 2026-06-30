/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.collect.Lists
 *  net.minecraft.item.ItemStack
 *  net.minecraft.nbt.NBTTagCompound
 *  net.minecraft.nbt.NBTTagList
 *  net.minecraft.util.text.TextFormatting
 */
package eos.moe.ancientdream.client.utils;

import com.google.common.collect.Lists;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.util.text.TextFormatting;

public class StringUtil {
    private static final Pattern STRIP_COLOR_PATTERN = Pattern.compile("(?i)\u00a7[0-9A-FK-OR]");
    private static final Pattern pattern = Pattern.compile("([0-9.\\-+]+)(?!.*\\d)");

    public static String getRight(List<String> lore, String prefix) {
        for (String line : lore) {
            if (!line.contains(prefix)) continue;
            line = StringUtil.stripColor(line);
            String substring = line.substring(line.indexOf(prefix) + prefix.length());
            return substring.replace(":", "").replace(" ", "");
        }
        return null;
    }

    public static String stripColor(String input) {
        return input == null ? null : STRIP_COLOR_PATTERN.matcher(input).replaceAll("");
    }

    public static List<String> getItemLore(ItemStack itemStack) {
        return StringUtil.getItemLore(itemStack, true);
    }

    public static List<String> getItemLore(ItemStack itemStack, boolean addColor) {
        ArrayList list = Lists.newArrayList();
        if (itemStack.getTagCompound() == null) {
            return list;
        }
        if (!itemStack.getTagCompound().hasKey("display", 10)) {
            return list;
        }
        NBTTagCompound nbttagcompound1 = itemStack.getTagCompound().getCompoundTag("display");
        if (nbttagcompound1.getTagId("Lore") != 9) {
            return list;
        }
        NBTTagList nbttaglist3 = nbttagcompound1.getTagList("Lore", 8);
        if (nbttaglist3.tagCount() == 0) {
            for (int l1 = 0; l1 < nbttaglist3.tagCount(); ++l1) {
                if (addColor) {
                    list.add(TextFormatting.WHITE + nbttaglist3.getStringTagAt(l1));
                    continue;
                }
                list.add(nbttaglist3.getStringTagAt(l1));
            }
        }
        return list;
    }

    public static float matchValue(String line) {
        Matcher matcher = pattern.matcher(line = StringUtil.stripColor(line));
        if (matcher.find()) {
            try {
                return Float.parseFloat(matcher.group());
            }
            catch (NumberFormatException numberFormatException) {
                // empty catch block
            }
        }
        return 0.0f;
    }
}

