/*
 * Decompiled with CFR 0.152.
 */
package invtweaks.api;

import invtweaks.api.IItemTreeItem;
import java.util.Collection;
import java.util.List;

public interface IItemTreeCategory {
    public boolean contains(IItemTreeItem var1);

    public void addCategory(IItemTreeCategory var1);

    public void addItem(IItemTreeItem var1);

    public Collection<IItemTreeCategory> getSubCategories();

    public Collection<List<IItemTreeItem>> getItems();

    public String getName();

    public int getCategoryOrder();

    public int findCategoryOrder(String var1);

    public int findKeywordDepth(String var1);
}

