/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.inventory.Container
 *  net.minecraft.inventory.IInventory
 *  net.minecraft.inventory.Slot
 */
package eos.moe.armourers;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;

public class qi
extends Container {
    public qi(IInventory a2, IInventory a3) {
        int n2;
        qi a4;
        int n3 = n2 = 0;
        while (n3 < a3.getSizeInventory()) {
            a4.addSlotToContainer(new Slot(a3, n2++, 999, 999));
            n3 = n2;
        }
        int n4 = n2 = 0;
        while (n4 < 3) {
            int n5 = a3 = 0;
            while (n5 < 9) {
                int n6 = a3 + n2 * 9 + 9;
                int n7 = 6 + a3 * 18;
                a4.addSlotToContainer(new Slot(a2, n6, n7, 51 + n2 * 18));
                n5 = ++a3;
            }
            n4 = ++n2;
        }
        int n8 = n2 = 0;
        while (n8 < 9) {
            int n9 = n2++;
            a4.addSlotToContainer(new Slot(a2, n9, 6 + n9 * 18, 109));
            n8 = n2;
        }
    }

    public boolean canInteractWith(EntityPlayer a2) {
        return true;
    }
}

