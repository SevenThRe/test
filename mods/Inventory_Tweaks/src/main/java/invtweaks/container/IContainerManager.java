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

import invtweaks.api.container.ContainerSection;
import java.util.List;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public interface IContainerManager {
    public static final int DROP_SLOT = -999;

    public boolean move(ContainerSection var1, int var2, ContainerSection var3, int var4);

    public boolean moveSome(ContainerSection var1, int var2, ContainerSection var3, int var4, int var5);

    default public boolean drop(ContainerSection srcSection, int srcIndex) {
        return this.move(srcSection, srcIndex, null, -999);
    }

    default public boolean dropSome(ContainerSection srcSection, int srcIndex, int amount) {
        return this.moveSome(srcSection, srcIndex, null, -999, amount);
    }

    public boolean putHoldItemDown(ContainerSection var1, int var2);

    default public void leftClick(ContainerSection section, int index) {
        this.click(section, index, false);
    }

    default public void rightClick(ContainerSection section, int index) {
        this.click(section, index, true);
    }

    public void click(ContainerSection var1, int var2, boolean var3);

    public boolean hasSection(ContainerSection var1);

    public List<Slot> getSlots(ContainerSection var1);

    public int getSize();

    public int getSize(ContainerSection var1);

    public int getFirstEmptyIndex(ContainerSection var1);

    public boolean isSlotEmpty(ContainerSection var1, int var2);

    @Nullable
    public Slot getSlot(ContainerSection var1, int var2);

    default public int getSlotIndex(int slotNumber) {
        return this.getSlotIndex(slotNumber, false);
    }

    public int getSlotIndex(int var1, boolean var2);

    @Nullable
    public ContainerSection getSlotSection(int var1);

    @NotNull
    public ItemStack getItemStack(ContainerSection var1, int var2);

    public Container getContainer();

    public void applyChanges();
}

