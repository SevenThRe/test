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

    public boolean canInteractWith(EntityPlayer a2) {
        return true;
    }

    public boolean canDragIntoSlot(Slot a2) {
        dj a3;
        return !a3.j.y() || a2.slotNumber >= a3.s.getSizeInventory();
    }

    public ItemStack transferStackInSlot(EntityPlayer a2, int a3) {
        dj a4;
        a2 = ItemStack.EMPTY;
        Slot slot = (Slot)a4.inventorySlots.get(a3);
        if (slot != null && slot.getHasStack()) {
            ItemStack itemStack = slot.getStack();
            a2 = itemStack.copy();
            if (a3 < 63) {
                dj dj2 = a4;
                if (!dj2.mergeItemStack(itemStack, 63, dj2.inventorySlots.size(), true)) {
                    return ItemStack.EMPTY;
                }
            } else if (!a4.mergeItemStack(itemStack, 0, 63, false)) {
                return ItemStack.EMPTY;
            }
            Slot slot2 = slot;
            if (itemStack.isEmpty()) {
                slot2.putStack(ItemStack.EMPTY);
                return a2;
            }
            slot2.onSlotChanged();
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
        while (n2 < a4.getSizeInventory()) {
            a5.addSlotToContainer(new Slot(a4, a2++, 999, 999));
            n2 = a2;
        }
        int n3 = a2 = 9;
        while (n3 < 36) {
            a5.addSlotToContainer(new Slot(a3, a2++, 999, 999));
            n3 = a2;
        }
        int n4 = a2 = 0;
        while (n4 < 9) {
            a5.addSlotToContainer(new Slot(a3, a2++, 999, 999));
            n4 = a2;
        }
    }
}

