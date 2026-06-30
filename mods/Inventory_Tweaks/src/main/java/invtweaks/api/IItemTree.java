/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.nbt.NBTTagCompound
 */
package invtweaks.api;

import invtweaks.api.IItemTreeCategory;
import invtweaks.api.IItemTreeItem;
import java.util.Collection;
import java.util.List;
import java.util.Random;
import net.minecraft.nbt.NBTTagCompound;

public interface IItemTree {
    public void registerOre(String var1, String var2, String var3, int var4);

    public boolean matches(List<IItemTreeItem> var1, String var2);

    public boolean isKeywordValid(String var1);

    public Collection<IItemTreeCategory> getAllCategories();

    public IItemTreeCategory getRootCategory();

    public void setRootCategory(IItemTreeCategory var1);

    public IItemTreeCategory getCategory(String var1);

    public boolean isItemUnknown(String var1, int var2);

    public List<IItemTreeItem> getItems(String var1, int var2, NBTTagCompound var3);

    public List<IItemTreeItem> getItems(String var1, int var2);

    public List<IItemTreeItem> getItems(String var1);

    public IItemTreeItem getRandomItem(Random var1);

    public boolean containsItem(String var1);

    public boolean containsCategory(String var1);

    public IItemTreeCategory addCategory(String var1, String var2) throws NullPointerException;

    public void addCategory(String var1, IItemTreeCategory var2) throws NullPointerException;

    public IItemTreeItem addItem(String var1, String var2, String var3, int var4, int var5) throws NullPointerException;

    public IItemTreeItem addItem(String var1, String var2, String var3, int var4, NBTTagCompound var5, int var6) throws NullPointerException;

    public void addItem(String var1, IItemTreeItem var2) throws NullPointerException;

    public int getKeywordDepth(String var1);

    public int getKeywordOrder(String var1);
}

