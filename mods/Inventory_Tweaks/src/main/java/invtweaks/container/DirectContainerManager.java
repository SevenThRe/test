/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.inventory.ClickType
 *  net.minecraft.inventory.Container
 *  net.minecraft.inventory.Slot
 *  net.minecraft.item.ItemStack
 *  org.jetbrains.annotations.NotNull
 *  org.jetbrains.annotations.Nullable
 */
package invtweaks.container;

import invtweaks.InvTweaks;
import invtweaks.InvTweaksObfuscation;
import invtweaks.api.container.ContainerSection;
import invtweaks.container.IContainerManager;
import invtweaks.forge.InvTweaksMod;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import net.minecraft.inventory.ClickType;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class DirectContainerManager
implements IContainerManager {
    @NotNull
    private final Container container;
    @NotNull
    private Map<ContainerSection, List<Slot>> slotRefs = new HashMap<ContainerSection, List<Slot>>();

    public DirectContainerManager(@NotNull Container cont) {
        this.container = cont;
        this.initSlots();
    }

    private void initSlots() {
        Map<ContainerSection, List<Slot>> refs = InvTweaksObfuscation.getContainerSlotMap(this.container);
        this.slotRefs = refs == null ? new HashMap<ContainerSection, List<Slot>>() : refs;
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    @Override
    public boolean move(ContainerSection srcSection, int srcIndex, ContainerSection destSection, int destIndex) {
        Slot destSlot;
        ItemStack srcStack = this.getItemStack(srcSection, srcIndex);
        ItemStack destStack = this.getItemStack(destSection, destIndex);
        if (srcStack.func_190926_b() && destIndex != -999) {
            return false;
        }
        if (srcSection == destSection && srcIndex == destIndex) {
            return true;
        }
        if (destIndex != -999 && !(destSlot = this.getSlot(destSection, destIndex)).func_75214_a(srcStack)) {
            return false;
        }
        if (!InvTweaks.getInstance().getHeldStack().func_190926_b()) {
            int firstEmptyIndex = this.getFirstEmptyIndex(ContainerSection.INVENTORY);
            if (firstEmptyIndex == -1) return false;
            this.leftClick(ContainerSection.INVENTORY, firstEmptyIndex);
        }
        assert (!srcStack.func_190926_b());
        if (!destStack.func_190926_b() && !InvTweaksObfuscation.areItemsStackable(srcStack, destStack)) {
            int intermediateSlot = this.getFirstEmptyUsableSlotNumber();
            ContainerSection intermediateSection = this.getSlotSection(intermediateSlot);
            int intermediateIndex = this.getSlotIndex(intermediateSlot);
            if (intermediateIndex == -1) return false;
            Slot interSlot = this.getSlot(intermediateSection, intermediateIndex);
            if (!interSlot.func_75214_a(destStack)) {
                return false;
            }
            Slot srcSlot = this.getSlot(srcSection, srcIndex);
            if (!srcSlot.func_75214_a(destStack)) {
                return false;
            }
            this.leftClick(destSection, destIndex);
            this.leftClick(intermediateSection, intermediateIndex);
            this.leftClick(srcSection, srcIndex);
            this.leftClick(destSection, destIndex);
            this.leftClick(intermediateSection, intermediateIndex);
            this.leftClick(srcSection, srcIndex);
            return true;
        } else {
            this.leftClick(srcSection, srcIndex);
            this.leftClick(destSection, destIndex);
            if (InvTweaks.getInstance().getHeldStack().func_190926_b()) return true;
            Slot srcSlot = this.getSlot(srcSection, srcIndex);
            if (srcSlot.func_75214_a(InvTweaks.getInstance().getHeldStack())) {
                this.leftClick(srcSection, srcIndex);
                return true;
            } else {
                int firstEmptyIndex = this.getFirstEmptyIndex(ContainerSection.INVENTORY);
                if (firstEmptyIndex == -1) return true;
                this.leftClick(ContainerSection.INVENTORY, firstEmptyIndex);
            }
        }
        return true;
    }

    @Override
    public boolean moveSome(ContainerSection srcSection, int srcIndex, ContainerSection destSection, int destIndex, int amount) {
        ItemStack source = this.getItemStack(srcSection, srcIndex);
        if (source.func_190926_b() || srcSection == destSection && srcIndex == destIndex) {
            return true;
        }
        ItemStack destination = this.getItemStack(srcSection, srcIndex);
        int sourceSize = source.func_190916_E();
        int movedAmount = Math.min(amount, sourceSize);
        if (destination.func_190926_b() || InvTweaksObfuscation.areItemStacksEqual(source, destination)) {
            this.leftClick(srcSection, srcIndex);
            for (int i = 0; i < movedAmount; ++i) {
                this.rightClick(destSection, destIndex);
            }
            if (movedAmount < sourceSize) {
                this.leftClick(srcSection, srcIndex);
            }
            return true;
        }
        return false;
    }

    @Override
    public boolean putHoldItemDown(ContainerSection destSection, int destIndex) {
        ItemStack heldStack = InvTweaks.getInstance().getHeldStack();
        if (!heldStack.func_190926_b()) {
            if (this.getItemStack(destSection, destIndex).func_190926_b()) {
                this.click(destSection, destIndex, false);
                return true;
            }
            return false;
        }
        return true;
    }

    @Override
    public void click(ContainerSection section, int index, boolean rightClick) {
        int slot = this.indexToSlot(section, index);
        if (slot != -1) {
            int data = rightClick ? 1 : 0;
            InvTweaksMod.proxy.slotClick(InvTweaks.getInstance().getPlayerController(), this.container.field_75152_c, slot, data, ClickType.PICKUP, InvTweaks.getInstance().getThePlayer());
        }
    }

    @Override
    public boolean hasSection(ContainerSection section) {
        return this.slotRefs.containsKey((Object)section);
    }

    @Override
    public List<Slot> getSlots(ContainerSection section) {
        return this.slotRefs.get((Object)section);
    }

    @Override
    public int getSize() {
        int result = 0;
        for (List<Slot> slots : this.slotRefs.values()) {
            result += slots.size();
        }
        return result;
    }

    @Override
    public int getSize(ContainerSection section) {
        if (this.hasSection(section)) {
            return this.slotRefs.get((Object)section).size();
        }
        return 0;
    }

    @Override
    public int getFirstEmptyIndex(ContainerSection section) {
        int i = 0;
        for (Slot slot : this.slotRefs.get((Object)section)) {
            if (!slot.func_75216_d()) {
                return i;
            }
            ++i;
        }
        return -1;
    }

    @Override
    public boolean isSlotEmpty(ContainerSection section, int slot) {
        return this.hasSection(section) && this.getItemStack(section, slot).func_190926_b();
    }

    @Override
    @Nullable
    public Slot getSlot(ContainerSection section, int index) {
        List<Slot> slots = this.slotRefs.get((Object)section);
        if (slots != null) {
            return slots.get(index);
        }
        return null;
    }

    @Override
    public int getSlotIndex(int slotNumber, boolean preferInventory) {
        for (ContainerSection section : this.slotRefs.keySet()) {
            if ((preferInventory || section == ContainerSection.INVENTORY) && (!preferInventory || section == ContainerSection.INVENTORY_NOT_HOTBAR || section == ContainerSection.INVENTORY_HOTBAR)) continue;
            int i = 0;
            for (Slot slot : this.slotRefs.get((Object)section)) {
                if (InvTweaksObfuscation.getSlotNumber(slot) == slotNumber) {
                    return i;
                }
                ++i;
            }
        }
        return -1;
    }

    @Override
    @Nullable
    public ContainerSection getSlotSection(int slotNumber) {
        for (ContainerSection section : this.slotRefs.keySet()) {
            if (section == ContainerSection.INVENTORY) continue;
            for (Slot slot : this.slotRefs.get((Object)section)) {
                if (InvTweaksObfuscation.getSlotNumber(slot) != slotNumber) continue;
                return section;
            }
        }
        return null;
    }

    @Override
    @NotNull
    public ItemStack getItemStack(ContainerSection section, int index) {
        int slot = this.indexToSlot(section, index);
        if (slot >= 0 && slot < this.container.field_75151_b.size()) {
            return InvTweaksObfuscation.getSlotStack(this.container, slot);
        }
        return ItemStack.field_190927_a;
    }

    @Override
    @NotNull
    public Container getContainer() {
        return this.container;
    }

    private int getFirstEmptyUsableSlotNumber() {
        for (ContainerSection section : this.slotRefs.keySet()) {
            for (Slot slot : this.slotRefs.get((Object)section)) {
                if (!InvTweaksObfuscation.isBasicSlot(slot) || slot.func_75216_d()) continue;
                return InvTweaksObfuscation.getSlotNumber(slot);
            }
        }
        return -1;
    }

    private int indexToSlot(ContainerSection section, int index) {
        if (index == -999) {
            return -999;
        }
        if (index < 0) {
            return -1;
        }
        if (this.hasSection(section)) {
            Slot slot = this.slotRefs.get((Object)section).get(index);
            if (slot != null) {
                return InvTweaksObfuscation.getSlotNumber(slot);
            }
            return -1;
        }
        return -1;
    }

    @Override
    public void applyChanges() {
        InvTweaksMod.proxy.sortComplete();
    }
}

