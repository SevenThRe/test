/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.jetbrains.annotations.NotNull
 */
package invtweaks;

import invtweaks.api.IItemTreeCategory;
import invtweaks.api.IItemTreeItem;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.jetbrains.annotations.NotNull;

public class InvTweaksItemTreeCategory
implements IItemTreeCategory {
    private final Map<String, List<IItemTreeItem>> items = new HashMap<String, List<IItemTreeItem>>();
    private final List<String> matchingItems = new ArrayList<String>();
    private final List<IItemTreeCategory> subCategories = new ArrayList<IItemTreeCategory>();
    private String name;
    private int order = -1;

    public InvTweaksItemTreeCategory(String name_) {
        this.name = name_;
    }

    @Override
    public boolean contains(@NotNull IItemTreeItem item) {
        List<IItemTreeItem> storedItems = this.items.get(item.getId());
        if (storedItems != null) {
            for (IItemTreeItem storedItem : storedItems) {
                if (!storedItem.equals(item)) continue;
                return true;
            }
        }
        for (IItemTreeCategory category : this.subCategories) {
            if (!category.contains(item)) continue;
            return true;
        }
        return false;
    }

    @Override
    public void addCategory(IItemTreeCategory category) {
        this.subCategories.add(category);
    }

    @Override
    public void addItem(@NotNull IItemTreeItem item) {
        if (this.items.get(item.getId()) == null) {
            ArrayList<IItemTreeItem> itemList = new ArrayList<IItemTreeItem>();
            itemList.add(item);
            this.items.put(item.getId(), itemList);
        } else {
            this.items.get(item.getId()).add(item);
        }
        this.matchingItems.add(item.getName());
        if (this.order == -1 || this.order > item.getOrder()) {
            this.order = item.getOrder();
        }
    }

    @Override
    public int getCategoryOrder() {
        if (this.order != -1) {
            return this.order;
        }
        for (IItemTreeCategory category : this.subCategories) {
            int catOrder = category.getCategoryOrder();
            if (catOrder == -1) continue;
            return catOrder;
        }
        return -1;
    }

    @Override
    public int findCategoryOrder(@NotNull String keyword) {
        if (keyword.equals(this.name)) {
            return this.getCategoryOrder();
        }
        for (IItemTreeCategory category : this.subCategories) {
            int result = category.findCategoryOrder(keyword);
            if (result == -1) continue;
            return result;
        }
        return -1;
    }

    @Override
    public int findKeywordDepth(String keyword) {
        if (this.name.equals(keyword)) {
            return 0;
        }
        if (this.matchingItems.contains(keyword)) {
            return 1;
        }
        for (IItemTreeCategory category : this.subCategories) {
            int result = category.findKeywordDepth(keyword);
            if (result == -1) continue;
            return result + 1;
        }
        return -1;
    }

    @Override
    @NotNull
    public Collection<IItemTreeCategory> getSubCategories() {
        return this.subCategories;
    }

    @Override
    @NotNull
    public Collection<List<IItemTreeItem>> getItems() {
        return this.items.values();
    }

    @Override
    public String getName() {
        return this.name;
    }

    @NotNull
    public String toString() {
        return this.name + " (" + this.subCategories.size() + " cats, " + this.items.size() + " items)";
    }
}

