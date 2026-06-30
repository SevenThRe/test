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
import invtweaks.api.container.ContainerSection;
import invtweaks.container.IContainerManager;
import java.util.List;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class ContainerSectionManager {
    private IContainerManager containerMgr;
    private ContainerSection section;

    public ContainerSectionManager(ContainerSection section_) throws Exception {
        this(InvTweaks.getCurrentContainerManager(), section_);
    }

    public ContainerSectionManager(IContainerManager manager, ContainerSection section_) throws Exception {
        this.containerMgr = manager;
        this.section = section_;
        if (!this.containerMgr.hasSection(this.section)) {
            throw new Exception("Section not available");
        }
    }

    public boolean move(int srcIndex, int destIndex) {
        return this.containerMgr.move(this.section, srcIndex, this.section, destIndex);
    }

    public boolean moveSome(int srcIndex, int destIndex, int amount) {
        return this.containerMgr.moveSome(this.section, srcIndex, this.section, destIndex, amount);
    }

    public boolean drop(int srcIndex) {
        return this.containerMgr.drop(this.section, srcIndex);
    }

    public void leftClick(int index) {
        this.containerMgr.leftClick(this.section, index);
    }

    public void click(int index, boolean rightClick) {
        this.containerMgr.click(this.section, index, rightClick);
    }

    public List<Slot> getSlots() {
        return this.containerMgr.getSlots(this.section);
    }

    public int getSize() {
        return this.containerMgr.getSize(this.section);
    }

    public int getFirstEmptyIndex() {
        return this.containerMgr.getFirstEmptyIndex(this.section);
    }

    @Nullable
    public Slot getSlot(int index) {
        return this.containerMgr.getSlot(this.section, index);
    }

    @NotNull
    public ItemStack getItemStack(int index) throws NullPointerException, IndexOutOfBoundsException {
        return this.containerMgr.getItemStack(this.section, index);
    }

    public Container getContainer() {
        return this.containerMgr.getContainer();
    }

    public void applyChanges() {
        this.containerMgr.applyChanges();
    }
}

