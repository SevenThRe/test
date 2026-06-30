/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.Minecraft
 *  net.minecraft.inventory.Slot
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemArmor
 *  net.minecraft.item.ItemStack
 *  net.minecraft.util.ResourceLocation
 *  org.apache.commons.lang3.tuple.Pair
 *  org.apache.logging.log4j.Logger
 *  org.jetbrains.annotations.NotNull
 *  org.jetbrains.annotations.Nullable
 */
package invtweaks;

import invtweaks.InvTweaks;
import invtweaks.InvTweaksConfig;
import invtweaks.InvTweaksConfigSortingRule;
import invtweaks.InvTweaksConst;
import invtweaks.InvTweaksItemTree;
import invtweaks.InvTweaksObfuscation;
import invtweaks.api.IItemTreeItem;
import invtweaks.api.SortingMethod;
import invtweaks.api.container.ContainerSection;
import invtweaks.container.ContainerSectionManager;
import invtweaks.container.IContainerManager;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import net.minecraft.client.Minecraft;
import net.minecraft.inventory.Slot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import org.apache.commons.lang3.tuple.Pair;
import org.apache.logging.log4j.Logger;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class InvTweaksHandlerSorting
extends InvTweaksObfuscation {
    private static final Logger log = InvTweaks.log;
    private static final int MAX_CONTAINER_SIZE = 999;
    @Nullable
    private static int[] DEFAULT_LOCK_PRIORITIES = null;
    @Nullable
    private static boolean[] DEFAULT_FROZEN_SLOTS = null;
    private ContainerSectionManager containerMgr;
    private SortingMethod algorithm;
    private int size;
    private boolean sortArmorParts;
    private InvTweaksItemTree tree;
    private List<InvTweaksConfigSortingRule> rules;
    private int[] rulePriority;
    private int[] keywordOrder;
    @Nullable
    private int[] lockPriorities;
    @Nullable
    private boolean[] frozenSlots;

    public InvTweaksHandlerSorting(Minecraft mc_, @NotNull InvTweaksConfig config, ContainerSection section, SortingMethod algorithm_, int rowSize) throws Exception {
        super(mc_);
        int i;
        if (DEFAULT_LOCK_PRIORITIES == null) {
            DEFAULT_LOCK_PRIORITIES = new int[999];
            for (i = 0; i < 999; ++i) {
                InvTweaksHandlerSorting.DEFAULT_LOCK_PRIORITIES[i] = 0;
            }
        }
        if (DEFAULT_FROZEN_SLOTS == null) {
            DEFAULT_FROZEN_SLOTS = new boolean[999];
            for (i = 0; i < 999; ++i) {
                InvTweaksHandlerSorting.DEFAULT_FROZEN_SLOTS[i] = false;
            }
        }
        this.containerMgr = new ContainerSectionManager(section);
        this.size = this.containerMgr.getSize();
        this.sortArmorParts = config.getProperty("enableAutoEquipArmor").equals("true") && !InvTweaksHandlerSorting.isGuiInventoryCreative(this.getCurrentScreen());
        this.rules = config.getRules();
        this.tree = config.getTree();
        if (section == ContainerSection.INVENTORY) {
            this.lockPriorities = config.getLockPriorities();
            this.frozenSlots = config.getFrozenSlots();
            this.algorithm = SortingMethod.INVENTORY;
        } else {
            this.lockPriorities = DEFAULT_LOCK_PRIORITIES;
            this.frozenSlots = DEFAULT_FROZEN_SLOTS;
            this.algorithm = algorithm_;
            if (this.algorithm != SortingMethod.DEFAULT) {
                this.computeLineSortingRules(rowSize, this.algorithm == SortingMethod.HORIZONTAL);
            }
        }
        this.rulePriority = new int[this.size];
        this.keywordOrder = new int[this.size];
        for (i = 0; i < this.size; ++i) {
            this.rulePriority[i] = -1;
            ItemStack stack = this.containerMgr.getItemStack(i);
            this.keywordOrder[i] = !stack.func_190926_b() ? this.getItemOrder(stack) : -1;
        }
        this.rules.stream().filter(rule -> rule.getContainerSize() == this.size && rule.getPreferredSlots() != null).forEach(rule -> {
            int priority = rule.getPriority();
            for (int slot : rule.getPreferredSlots()) {
                ItemStack stack = this.containerMgr.getItemStack(slot);
                if (stack.func_190926_b()) continue;
                List<IItemTreeItem> items = this.tree.getItems(stack.func_77973_b().getRegistryName().toString(), stack.func_77952_i(), stack.func_77978_p());
                if (this.rulePriority[slot] >= priority || !this.tree.matches(items, rule.getKeyword())) continue;
                this.rulePriority[slot] = priority;
            }
        });
    }

    private static boolean canMergeStacks(@NotNull ItemStack from, @NotNull ItemStack to) {
        if (InvTweaksHandlerSorting.areItemsStackable(from, to)) {
            if (from.func_190916_E() > from.func_77976_d()) {
                return false;
            }
            if (to.func_190916_E() < to.func_77976_d()) {
                return true;
            }
        }
        return false;
    }

    public void sort() {
        int emptySlot;
        long timer = System.nanoTime();
        IContainerManager globalContainer = InvTweaks.getCurrentContainerManager();
        if (!this.getHeldStack().func_190926_b()) {
            emptySlot = globalContainer.getFirstEmptyIndex(ContainerSection.INVENTORY);
            if (emptySlot != -1) {
                globalContainer.putHoldItemDown(ContainerSection.INVENTORY, emptySlot);
            } else {
                return;
            }
        }
        if (this.algorithm != SortingMethod.DEFAULT) {
            if (this.algorithm == SortingMethod.EVEN_STACKS) {
                this.sortEvenStacks();
            } else if (this.algorithm == SortingMethod.INVENTORY) {
                this.sortInventory(globalContainer);
            }
            this.sortWithRules();
        }
        this.defaultSorting();
        if (log.isEnabled(InvTweaksConst.DEBUG)) {
            timer = System.nanoTime() - timer;
            log.info("Sorting done in " + timer + "ns");
        }
        if (!this.getHeldStack().func_190926_b() && (emptySlot = globalContainer.getFirstEmptyIndex(ContainerSection.INVENTORY)) != -1) {
            globalContainer.putHoldItemDown(ContainerSection.INVENTORY, emptySlot);
        }
        globalContainer.applyChanges();
    }

    private void sortWithRules() {
        log.info("Applying rules.");
        for (InvTweaksConfigSortingRule rule : this.rules) {
            int priority = rule.getPriority();
            if (log.isEnabled(InvTweaksConst.DEBUG)) {
                log.info("Rule : " + rule.getKeyword() + "(" + priority + ")");
            }
            block1: for (int i = 0; i < this.size; ++i) {
                List<IItemTreeItem> fromItems;
                ItemStack from = this.containerMgr.getItemStack(i);
                if (!this.hasToBeMoved(i, priority) || this.lockPriorities[i] >= priority || !this.tree.matches(fromItems = this.tree.getItems(from.func_77973_b().getRegistryName().toString(), from.func_77952_i(), from.func_77978_p()), rule.getKeyword())) continue;
                int[] preferredSlots = rule.getPreferredSlots();
                int stackToMove = i;
                for (int k : preferredSlots) {
                    int moveResult = this.move(stackToMove, k, priority);
                    if (moveResult == -1) continue;
                    if (moveResult == k || !this.tree.matches(fromItems = this.tree.getItems((from = this.containerMgr.getItemStack(moveResult)).func_77973_b().getRegistryName().toString(), from.func_77952_i(), from.func_77978_p()), rule.getKeyword()) || i < moveResult) continue block1;
                    stackToMove = moveResult;
                }
            }
        }
        log.info("Locking stacks.");
        for (int i = 0; i < this.size; ++i) {
            if (!this.hasToBeMoved(i, 1) || this.lockPriorities[i] <= 0) continue;
            this.markAsMoved(i, 1);
        }
    }

    private void sortInventory(@NotNull IContainerManager globalContainer) {
        log.info("Handling crafting slots.");
        if (globalContainer.hasSection(ContainerSection.CRAFTING_IN)) {
            List<Slot> craftingSlots = globalContainer.getSlots(ContainerSection.CRAFTING_IN);
            int emptyIndex = globalContainer.getFirstEmptyIndex(ContainerSection.INVENTORY);
            if (emptyIndex != -1) {
                for (Slot craftingSlot : craftingSlots) {
                    if (!craftingSlot.func_75216_d()) continue;
                    globalContainer.move(ContainerSection.CRAFTING_IN, globalContainer.getSlotIndex(InvTweaksHandlerSorting.getSlotNumber(craftingSlot)), ContainerSection.INVENTORY, emptyIndex);
                    emptyIndex = globalContainer.getFirstEmptyIndex(ContainerSection.INVENTORY);
                    if (emptyIndex != -1) continue;
                    break;
                }
            }
        }
        this.sortMergeArmor(globalContainer);
    }

    private void sortMergeArmor(@NotNull IContainerManager globalContainer) {
        log.info("Merging stacks.");
        for (int i = this.size - 1; i >= 0; --i) {
            ItemStack from = this.containerMgr.getItemStack(i);
            if (from.func_190926_b()) continue;
            Item fromItem = from.func_77973_b();
            if (fromItem.func_77645_m()) {
                this.moveArmor(globalContainer, i, from, fromItem);
                continue;
            }
            this.mergeItem(i, from);
        }
    }

    private void mergeItem(int i, @NotNull ItemStack from) {
        int j = 0;
        int[] nArray = this.lockPriorities;
        int n = nArray.length;
        for (int k = 0; k < n; ++k) {
            ItemStack to;
            Integer lockPriority = nArray[k];
            if (lockPriority > 0 && !(to = this.containerMgr.getItemStack(j)).func_190926_b() && InvTweaksHandlerSorting.areItemsStackable(from, to)) {
                this.move(i, j, Integer.MAX_VALUE);
                this.markAsNotMoved(j);
                if (this.containerMgr.getItemStack(i).func_190926_b()) break;
            }
            ++j;
        }
    }

    private void moveArmor(@NotNull IContainerManager globalContainer, int i, @NotNull ItemStack from, Item fromItem) {
        if (this.sortArmorParts && InvTweaksHandlerSorting.isItemArmor(fromItem)) {
            ItemArmor fromItemArmor = (ItemArmor)fromItem;
            if (globalContainer.hasSection(ContainerSection.ARMOR)) {
                List<Slot> armorSlots = globalContainer.getSlots(ContainerSection.ARMOR);
                for (Slot slot : armorSlots) {
                    boolean move = false;
                    if (!slot.func_75216_d()) {
                        move = true;
                    } else {
                        Item currentArmor = slot.func_75211_c().func_77973_b();
                        if (InvTweaksHandlerSorting.isItemArmor(currentArmor)) {
                            int armorLevel = ((ItemArmor)currentArmor).field_77879_b;
                            if (armorLevel < fromItemArmor.field_77879_b || armorLevel == fromItemArmor.field_77879_b && slot.func_75211_c().func_77952_i() < from.func_77952_i()) {
                                move = true;
                            }
                        } else {
                            move = true;
                        }
                    }
                    if (!slot.func_75214_a(from) || !move) continue;
                    globalContainer.move(ContainerSection.INVENTORY, i, ContainerSection.ARMOR, globalContainer.getSlotIndex(InvTweaksHandlerSorting.getSlotNumber(slot)));
                }
            }
        }
    }

    private void sortEvenStacks() {
        int[] count;
        Pair item;
        log.info("Distributing items.");
        HashMap<Pair, int[]> itemCounts = new HashMap<Pair, int[]>();
        for (int i = 0; i < this.size; ++i) {
            ItemStack stack = this.containerMgr.getItemStack(i);
            if (stack.func_190926_b()) continue;
            item = Pair.of((Object)stack.func_77973_b().getRegistryName().toString(), (Object)stack.func_77952_i());
            count = (int[])itemCounts.get(item);
            if (count == null) {
                int[] newCount = new int[]{stack.func_190916_E(), 1};
                itemCounts.put(item, newCount);
                continue;
            }
            count[0] = count[0] + stack.func_190916_E();
            count[1] = count[1] + 1;
        }
        for (Map.Entry entry : itemCounts.entrySet()) {
            int largeIndex;
            item = (Pair)entry.getKey();
            count = (int[])entry.getValue();
            int numPerSlot = count[0] / count[1];
            if (numPerSlot > new ItemStack((Item)Item.field_150901_e.func_82594_a((Object)new ResourceLocation((String)item.getLeft())), 1, 0).func_77976_d()) continue;
            LinkedList<Integer> smallStacks = new LinkedList<Integer>();
            LinkedList<Integer> largeStacks = new LinkedList<Integer>();
            for (int i = 0; i < this.size; ++i) {
                ItemStack stack = this.containerMgr.getItemStack(i);
                if (stack.func_190926_b() || !Pair.of((Object)stack.func_77973_b().getRegistryName().toString(), (Object)stack.func_77952_i()).equals((Object)item)) continue;
                int stackSize = stack.func_190916_E();
                if (stackSize > numPerSlot) {
                    largeStacks.offer(i);
                    continue;
                }
                if (stackSize >= numPerSlot) continue;
                smallStacks.offer(i);
            }
            while (!smallStacks.isEmpty()) {
                largeIndex = (Integer)largeStacks.peek();
                int largeSize = this.containerMgr.getItemStack(largeIndex).func_190916_E();
                int smallIndex = (Integer)smallStacks.peek();
                int smallSize = this.containerMgr.getItemStack(smallIndex).func_190916_E();
                this.containerMgr.moveSome(largeIndex, smallIndex, Math.min(numPerSlot - smallSize, largeSize - numPerSlot));
                largeSize = this.containerMgr.getItemStack(largeIndex).func_190916_E();
                smallSize = this.containerMgr.getItemStack(smallIndex).func_190916_E();
                if (largeSize == numPerSlot) {
                    largeStacks.remove();
                }
                if (smallSize != numPerSlot) continue;
                smallStacks.remove();
            }
            while (largeStacks.size() > 1) {
                largeIndex = (Integer)largeStacks.poll();
                int largeSize = this.containerMgr.getItemStack(largeIndex).func_190916_E();
                this.containerMgr.moveSome(largeIndex, (Integer)largeStacks.peek(), largeSize - numPerSlot);
            }
        }
        for (int i = 0; i < this.size; ++i) {
            this.markAsMoved(i, 1);
        }
    }

    private void defaultSorting() {
        log.info("Default sorting.");
        ArrayList<Integer> remaining = new ArrayList<Integer>();
        ArrayList<Integer> nextRemaining = new ArrayList<Integer>();
        for (int i = 0; i < this.size; ++i) {
            if (!this.hasToBeMoved(i, 1)) continue;
            remaining.add(i);
            nextRemaining.add(i);
        }
        int iterations = 0;
        while (remaining.size() > 0 && iterations++ < 50) {
            Iterator iterator = remaining.iterator();
            block2: while (iterator.hasNext()) {
                int i = (Integer)iterator.next();
                if (this.hasToBeMoved(i, 1)) {
                    for (int j = 0; j < this.size; ++j) {
                        if (this.move(i, j, 1) == -1) continue;
                        nextRemaining.remove((Object)j);
                        continue block2;
                    }
                    continue;
                }
                nextRemaining.remove((Object)i);
            }
            remaining.clear();
            remaining.addAll(nextRemaining);
        }
        if (iterations == 100) {
            log.warn("Sorting takes too long, aborting.");
        }
    }

    private boolean canSwapSlots(int i, int j, int priority) {
        return this.lockPriorities[j] <= priority && (this.rulePriority[j] < priority || this.rulePriority[j] == priority && this.isOrderedBefore(i, j));
    }

    private int move(int i, int j, int priority) {
        ItemStack from = this.containerMgr.getItemStack(i);
        ItemStack to = this.containerMgr.getItemStack(j);
        if (from.func_190926_b() || this.frozenSlots[j] || this.frozenSlots[i]) {
            return -1;
        }
        if (this.lockPriorities[i] <= priority) {
            if (i == j) {
                this.markAsMoved(i, priority);
                return j;
            }
            if (to.func_190926_b() && this.lockPriorities[j] <= priority && !this.frozenSlots[j]) {
                this.rulePriority[i] = -1;
                this.keywordOrder[i] = -1;
                this.rulePriority[j] = priority;
                this.keywordOrder[j] = this.getItemOrder(from);
                if (this.containerMgr.move(i, j)) {
                    return j;
                }
                return -1;
            }
            if (!to.func_190926_b() && (this.canSwapSlots(i, j, priority) || InvTweaksHandlerSorting.canMergeStacks(from, to))) {
                this.keywordOrder[j] = this.keywordOrder[i];
                this.rulePriority[j] = priority;
                this.rulePriority[i] = -1;
                boolean success = this.containerMgr.move(i, j);
                if (success) {
                    ItemStack remains = this.containerMgr.getItemStack(i);
                    if (!remains.func_190926_b()) {
                        int dropSlot = i;
                        if (this.lockPriorities[j] > this.lockPriorities[i]) {
                            for (int k = 0; k < this.size; ++k) {
                                if (!this.containerMgr.getItemStack(k).func_190926_b() || this.lockPriorities[k] != 0) continue;
                                dropSlot = k;
                                break;
                            }
                        }
                        if (dropSlot != i && !this.containerMgr.move(i, dropSlot)) {
                            return -1;
                        }
                        this.rulePriority[dropSlot] = -1;
                        this.keywordOrder[dropSlot] = this.getItemOrder(remains);
                        return dropSlot;
                    }
                    return j;
                }
                return -1;
            }
        }
        return -1;
    }

    private void markAsMoved(int i, int priority) {
        this.rulePriority[i] = priority;
    }

    private void markAsNotMoved(int i) {
        this.rulePriority[i] = -1;
    }

    private boolean hasToBeMoved(int slot, int priority) {
        return !this.containerMgr.getItemStack(slot).func_190926_b() && this.rulePriority[slot] <= priority;
    }

    private boolean isOrderedBefore(int i, int j) {
        ItemStack iStack = this.containerMgr.getItemStack(i);
        ItemStack jStack = this.containerMgr.getItemStack(j);
        return InvTweaks.getInstance().compareItems(iStack, jStack, this.keywordOrder[i], this.keywordOrder[j]) < 0;
    }

    private int getItemOrder(@NotNull ItemStack itemStack) {
        List<IItemTreeItem> items = this.tree.getItems(itemStack.func_77973_b().getRegistryName().toString(), itemStack.func_77952_i(), itemStack.func_77978_p());
        return items.size() > 0 ? items.get(0).getOrder() : Integer.MAX_VALUE;
    }

    private void computeLineSortingRules(int rowSize, boolean horizontal) {
        int spaceWidth;
        int spaceHeight;
        if (rowSize > 9) {
            return;
        }
        this.rules = new ArrayList<InvTweaksConfigSortingRule>();
        Map<IItemTreeItem, Integer> stats = this.computeContainerStats();
        ArrayList<IItemTreeItem> itemOrder = new ArrayList<IItemTreeItem>();
        int distinctItems = stats.size();
        int columnSize = this.getContainerColumnSize(rowSize);
        int availableSlots = this.size;
        int remainingStacks = 0;
        for (Integer stacks : stats.values()) {
            remainingStacks += stacks.intValue();
        }
        if (distinctItems == 0) {
            return;
        }
        ArrayList<IItemTreeItem> unorderedItems = new ArrayList<IItemTreeItem>(stats.keySet());
        boolean hasStacksToOrderFirst = true;
        block1: while (hasStacksToOrderFirst) {
            hasStacksToOrderFirst = false;
            for (IItemTreeItem item : unorderedItems) {
                Integer value = stats.get(item);
                if (value <= (horizontal ? rowSize : columnSize) || itemOrder.contains(item)) continue;
                hasStacksToOrderFirst = true;
                itemOrder.add(item);
                unorderedItems.remove(item);
                continue block1;
            }
        }
        unorderedItems.sort(Collections.reverseOrder());
        itemOrder.addAll(unorderedItems);
        if (horizontal) {
            spaceHeight = 1;
            spaceWidth = rowSize / ((distinctItems + columnSize - 1) / columnSize);
        } else {
            spaceWidth = 1;
            spaceHeight = columnSize / ((distinctItems + rowSize - 1) / rowSize);
        }
        char row = 'a';
        char maxRow = (char)(row - 1 + columnSize);
        char column = '1';
        char maxColumn = (char)(column - 1 + rowSize);
        for (IItemTreeItem item : itemOrder) {
            int thisSpaceWidth = spaceWidth;
            int thisSpaceHeight = spaceHeight;
            while (stats.get(item) > thisSpaceHeight * thisSpaceWidth) {
                if (horizontal) {
                    if (column + thisSpaceWidth < maxColumn) {
                        thisSpaceWidth = maxColumn - column + 1;
                        continue;
                    }
                    if (row + thisSpaceHeight >= maxRow) break;
                    ++thisSpaceHeight;
                    continue;
                }
                if (row + thisSpaceHeight < maxRow) {
                    thisSpaceHeight = maxRow - row + 1;
                    continue;
                }
                if (column + thisSpaceWidth >= maxColumn) break;
                ++thisSpaceWidth;
            }
            if (horizontal && column + thisSpaceWidth == maxColumn) {
                ++thisSpaceWidth;
            } else if (!horizontal && row + thisSpaceHeight == maxRow) {
                ++thisSpaceHeight;
            }
            String constraint = String.format("%c%c-%c%c", Character.valueOf(row), Character.valueOf(column), Character.valueOf((char)(row - '\u0001' + thisSpaceHeight)), Character.valueOf((char)(column - '\u0001' + thisSpaceWidth)));
            if (!horizontal) {
                constraint = constraint + 'v';
            }
            this.rules.add(new InvTweaksConfigSortingRule(this.tree, constraint, item.getName(), this.size, rowSize));
            if ((availableSlots -= thisSpaceHeight * thisSpaceWidth) < (remainingStacks -= stats.get(item).intValue())) break;
            if (horizontal) {
                if (column + thisSpaceWidth + spaceWidth <= maxColumn + '\u0001') {
                    column = (char)(column + thisSpaceWidth);
                } else {
                    column = '1';
                    row = (char)(row + thisSpaceHeight);
                }
            } else if (row + thisSpaceHeight + spaceHeight <= maxRow + '\u0001') {
                row = (char)(row + thisSpaceHeight);
            } else {
                row = 'a';
                column = (char)(column + thisSpaceWidth);
            }
            if (row <= maxRow && column <= maxColumn) continue;
            break;
        }
        String defaultRule = horizontal ? maxRow + "1-a" + maxColumn : "a" + maxColumn + "-" + maxRow + "1v";
        this.rules.add(new InvTweaksConfigSortingRule(this.tree, defaultRule, this.tree.getRootCategory().getName(), this.size, rowSize));
    }

    @NotNull
    private Map<IItemTreeItem, Integer> computeContainerStats() {
        HashMap<IItemTreeItem, Integer> stats = new HashMap<IItemTreeItem, Integer>();
        HashMap<Integer, IItemTreeItem> itemSearch = new HashMap<Integer, IItemTreeItem>();
        for (int i = 0; i < this.size; ++i) {
            ItemStack stack = this.containerMgr.getItemStack(i);
            if (stack.func_190926_b()) continue;
            int itemSearchKey = Item.func_150891_b((Item)stack.func_77973_b()) * 100000 + (stack.func_77976_d() != 1 ? stack.func_77952_i() : 0);
            IItemTreeItem item = (IItemTreeItem)itemSearch.get(itemSearchKey);
            if (item == null) {
                item = this.tree.getItems(stack.func_77973_b().getRegistryName().toString(), stack.func_77952_i(), stack.func_77978_p()).get(0);
                itemSearch.put(itemSearchKey, item);
                stats.put(item, 1);
                continue;
            }
            stats.put(item, (Integer)stats.get(item) + 1);
        }
        return stats;
    }

    private int getContainerColumnSize(int rowSize) {
        return this.size / rowSize;
    }
}

