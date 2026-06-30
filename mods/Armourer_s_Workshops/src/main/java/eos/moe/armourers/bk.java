/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.item.Item
 */
package eos.moe.armourers;

import eos.moe.armourers.um;
import java.util.HashSet;
import net.minecraft.item.Item;

public class bk {
    private static HashSet<String> j = new HashSet();

    private /* synthetic */ bk() {
        bk a2;
    }

    public static boolean r(um a2, Item a3) {
        a3 = new StringBuilder().insert(0, a2.toString().toLowerCase()).append(":").append(a3.getRegistryName().toString()).toString();
        return j.contains(a3);
    }

    public static void r(um a2, String a3) {
        j.add(new StringBuilder().insert(0, a2.toString().toLowerCase()).append(":minecraft:").append(a3).toString());
    }

    public static void r() {
        bk.r(um.r, "wooden_sword");
        bk.r(um.r, "stone_sword");
        bk.r(um.r, "iron_sword");
        bk.r(um.r, "golden_sword");
        bk.r(um.r, "diamond_sword");
        bk.r(um.c, "shield");
        bk.r(um.m, "bow");
        bk.r(um.j, "wooden_pickaxe");
        bk.r(um.j, "stone_pickaxe");
        bk.r(um.j, "iron_pickaxe");
        bk.r(um.j, "golden_pickaxe");
        bk.r(um.j, "diamond_pickaxe");
        bk.r(um.v, "wooden_axe");
        bk.r(um.v, "stone_axe");
        bk.r(um.v, "iron_axe");
        bk.r(um.v, "golden_axe");
        bk.r(um.v, "diamond_axe");
        bk.r(um.w, "wooden_shovel");
        bk.r(um.w, "stone_shovel");
        bk.r(um.w, "iron_shovel");
        bk.r(um.w, "golden_shovel");
        bk.r(um.w, "diamond_shovel");
        bk.r(um.l, "wooden_hoe");
        bk.r(um.l, "stone_hoe");
        bk.r(um.l, "iron_hoe");
        bk.r(um.l, "golden_hoe");
        bk.r(um.l, "diamond_hoe");
    }

    public static HashSet<String> r() {
        return j;
    }
}

