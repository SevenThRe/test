/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.inventory.Container
 *  net.minecraft.inventory.IInventory
 *  net.minecraft.inventory.Slot
 */
package eos.moe.dragoncore;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;

public class nka
extends Container {
    public nka(IInventory a2, IInventory a3) {
        int a4;
        nka a5;
        for (a4 = 0; a4 < a3.getSizeInventory(); ++a4) {
            a5.addSlotToContainer(new Slot(a3, a4, 999, 999));
        }
        for (a4 = 0; a4 < 3; ++a4) {
            for (int a6 = 0; a6 < 9; ++a6) {
                a5.addSlotToContainer(new Slot(a2, a6 + a4 * 9 + 9, 6 + a6 * 18, 51 + a4 * 18));
            }
        }
        for (a4 = 0; a4 < 9; ++a4) {
            a5.addSlotToContainer(new Slot(a2, a4, 6 + a4 * 18, 109));
        }
    }

    public boolean canInteractWith(EntityPlayer a2) {
        return true;
    }
}

