/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
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
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class MirroredContainerManager
implements IContainerManager {
    private ItemStack[] slotItems;
    @Nullable
    private ItemStack heldItem;
    @NotNull
    private List<ItemStack> droppedItems = new ArrayList<ItemStack>();
    private Container container;
    private Map<ContainerSection, List<Integer>> itemRefs;
    @Nullable
    private Map<ContainerSection, List<Slot>> slotRefs;

    public MirroredContainerManager(Container cont) {
        this.container = cont;
        this.slotRefs = InvTweaksObfuscation.getContainerSlotMap(this.container);
        if (this.slotRefs == null) {
            this.slotRefs = new HashMap<ContainerSection, List<Slot>>();
        }
        List slots = this.container.field_75151_b;
        int size = slots.size();
        this.itemRefs = new HashMap<ContainerSection, List<Integer>>();
        for (Map.Entry<ContainerSection, List<Slot>> section : this.slotRefs.entrySet()) {
            List slotIndices = section.getValue().stream().map(slots::indexOf).collect(Collectors.toList());
            this.itemRefs.put(section.getKey(), slotIndices);
        }
        this.slotItems = new ItemStack[size];
        for (int i = 0; i < size; ++i) {
            this.slotItems[i] = ((Slot)slots.get(i)).func_75211_c().func_77946_l();
        }
        this.heldItem = InvTweaks.getInstance().getHeldStack();
    }

    @Override
    public boolean move(ContainerSection srcSection, int srcIndex, ContainerSection destSection, int destIndex) {
        int srcSlotIdx = this.slotPositionToIndex(srcSection, srcIndex);
        if (destIndex == -999) {
            this.droppedItems.add(this.slotItems[srcSlotIdx]);
            this.slotItems[srcSlotIdx] = null;
        }
        int destSlotIdx = this.slotPositionToIndex(destSection, destIndex);
        Slot srcSlot = this.getSlot(srcSection, srcIndex);
        Slot destSlot = this.getSlot(destSection, destIndex);
        ItemStack srcItem = this.slotItems[srcSlotIdx];
        ItemStack destItem = this.slotItems[destSlotIdx];
        if (srcItem != null && !destSlot.func_75214_a(srcItem)) {
            return false;
        }
        if (destItem != null && !srcSlot.func_75214_a(destItem)) {
            return false;
        }
        this.slotItems[srcSlotIdx] = destItem;
        this.slotItems[destSlotIdx] = srcItem;
        return true;
    }

    @Override
    public boolean moveSome(ContainerSection srcSection, int srcIndex, ContainerSection destSection, int destIndex, int amount) {
        return false;
    }

    @Override
    public boolean putHoldItemDown(ContainerSection destSection, int destIndex) {
        int destSlotIdx = this.slotPositionToIndex(destSection, destIndex);
        if (this.slotItems[destSlotIdx] != null) {
            return false;
        }
        this.slotItems[destSlotIdx] = this.heldItem;
        this.heldItem = null;
        return true;
    }

    @Override
    public void click(ContainerSection section, int index, boolean rightClick) {
    }

    @Override
    public boolean hasSection(ContainerSection section) {
        return this.itemRefs.containsKey((Object)section);
    }

    @Override
    public List<Slot> getSlots(ContainerSection section) {
        return this.slotRefs.get((Object)section);
    }

    @Override
    public int getSize() {
        return this.slotItems.length;
    }

    @Override
    public int getSize(ContainerSection section) {
        return this.itemRefs.get((Object)section).size();
    }

    @Override
    public int getFirstEmptyIndex(ContainerSection section) {
        int i = 0;
        for (int slot : this.itemRefs.get((Object)section)) {
            if (this.slotItems[slot].func_190926_b()) {
                return i;
            }
            ++i;
        }
        return -1;
    }

    @Override
    public boolean isSlotEmpty(ContainerSection section, int slot) {
        return this.getItemStack(section, slot).func_190926_b();
    }

    @Override
    @NotNull
    public Slot getSlot(ContainerSection section, int index) {
        return this.container.func_75139_a(this.slotPositionToIndex(section, index));
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
        return this.slotItems[this.slotPositionToIndex(section, index)];
    }

    @Override
    public Container getContainer() {
        return this.container;
    }

    @Override
    public void applyChanges() {
        InvTweaksMod.proxy.sortComplete();
    }

    private int slotPositionToIndex(ContainerSection section, int index) {
        if (index == -999) {
            return -999;
        }
        if (index < 0) {
            return -1;
        }
        if (this.hasSection(section)) {
            return this.itemRefs.get((Object)section).get(index);
        }
        return -1;
    }
}

