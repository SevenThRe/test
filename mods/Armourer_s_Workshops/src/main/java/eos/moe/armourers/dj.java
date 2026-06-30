/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.inventory.Container
 *  net.minecraft.inventory.IInventory
 *  net.minecraft.inventory.Slot
 *  net.minecraft.item.ItemStack
 */
package eos.moe.armourers;

import eos.moe.armourers.rg;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

public class dj
extends Container {
    private IInventory s;
    private IInventory m;
    private rg j;

    public boolean func_75145_c(EntityPlayer a2) {
        return true;
    }

    public boolean func_94531_b(Slot a2) {
        dj a3;
        return !a3.j.y() || a2.field_75222_d >= a3.s.func_70302_i_();
    }

    public ItemStack func_82846_b(EntityPlayer a2, int a3) {
        dj a4;
        a2 = ItemStack.field_190927_a;
        Slot slot = (Slot)a4.field_75151_b.get(a3);
        if (slot != null && slot.func_75216_d()) {
            ItemStack itemStack = slot.func_75211_c();
            a2 = itemStack.func_77946_l();
            if (a3 < 63) {
                dj dj2 = a4;
                if (!dj2.func_75135_a(itemStack, 63, dj2.field_75151_b.size(), true)) {
                    return ItemStack.field_190927_a;
                }
            } else if (!a4.func_75135_a(itemStack, 0, 63, false)) {
                return ItemStack.field_190927_a;
            }
            Slot slot2 = slot;
            if (itemStack.func_190926_b()) {
                slot2.func_75215_d(ItemStack.field_190927_a);
                return a2;
            }
            slot2.func_75218_e();
        }
        return a2;
    }

    public dj(rg a2, IInventory a3, IInventory a4) {
        dj a5;
        dj dj2 = a5;
        a5.j = a2;
        dj2.m = a3;
        dj2.s = a4;
        int n2 = a2 = 0;
        while (n2 < a4.func_70302_i_()) {
            a5.func_75146_a(new Slot(a4, a2++, 999, 999));
            n2 = a2;
        }
        int n3 = a2 = 9;
        while (n3 < 36) {
            a5.func_75146_a(new Slot(a3, a2++, 999, 999));
            n3 = a2;
        }
        int n4 = a2 = 0;
        while (n4 < 9) {
            a5.func_75146_a(new Slot(a3, a2++, 999, 999));
            n4 = a2;
        }
    }
}

