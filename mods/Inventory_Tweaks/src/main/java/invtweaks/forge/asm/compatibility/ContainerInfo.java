/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.jetbrains.annotations.Nullable
 */
package invtweaks.forge.asm.compatibility;

import invtweaks.forge.asm.ContainerTransformer;
import invtweaks.forge.asm.compatibility.MethodInfo;
import org.jetbrains.annotations.Nullable;

public class ContainerInfo {
    public boolean showButtons = false;
    public boolean validInventory = false;
    public boolean validChest = false;
    public boolean largeChest = false;
    public short rowSize = (short)9;
    public MethodInfo slotMapMethod = ContainerTransformer.getVanillaSlotMapInfo("unknownContainerSlots");
    @Nullable
    public MethodInfo rowSizeMethod = null;
    @Nullable
    public MethodInfo largeChestMethod = null;

    public ContainerInfo() {
    }

    public ContainerInfo(boolean standard, boolean validInv, boolean validCh) {
        this.showButtons = standard;
        this.validInventory = validInv;
        this.validChest = validCh;
    }

    public ContainerInfo(boolean standard, boolean validInv, boolean validCh, boolean largeCh) {
        this.showButtons = standard;
        this.validInventory = validInv;
        this.validChest = validCh;
        this.largeChest = largeCh;
    }

    public ContainerInfo(boolean standard, boolean validInv, boolean validCh, MethodInfo slotMap) {
        this.showButtons = standard;
        this.validInventory = validInv;
        this.validChest = validCh;
        this.slotMapMethod = slotMap;
    }

    public ContainerInfo(boolean standard, boolean validInv, boolean validCh, short rowS) {
        this.showButtons = standard;
        this.validInventory = validInv;
        this.validChest = validCh;
        this.rowSize = rowS;
    }

    public ContainerInfo(boolean standard, boolean validInv, boolean validCh, boolean largeCh, short rowS) {
        this.showButtons = standard;
        this.validInventory = validInv;
        this.validChest = validCh;
        this.largeChest = largeCh;
        this.rowSize = rowS;
    }

    public ContainerInfo(boolean standard, boolean validInv, boolean validCh, short rowS, MethodInfo slotMap) {
        this.showButtons = standard;
        this.validInventory = validInv;
        this.validChest = validCh;
        this.rowSize = rowS;
        this.slotMapMethod = slotMap;
    }
}

