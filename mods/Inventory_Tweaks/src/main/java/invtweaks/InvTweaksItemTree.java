/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.item.ItemStack
 *  net.minecraft.nbt.NBTBase
 *  net.minecraft.nbt.NBTTagCompound
 *  net.minecraft.nbt.NBTUtil
 *  net.minecraftforge.fml.common.eventhandler.SubscribeEvent
 *  net.minecraftforge.oredict.OreDictionary
 *  net.minecraftforge.oredict.OreDictionary$OreRegisterEvent
 *  org.apache.logging.log4j.Logger
 *  org.jetbrains.annotations.NotNull
 *  org.jetbrains.annotations.Nullable
 */
package invtweaks;

import invtweaks.InvTweaks;
import invtweaks.InvTweaksItemTreeCategory;
import invtweaks.InvTweaksItemTreeItem;
import invtweaks.api.IItemTree;
import invtweaks.api.IItemTreeCategory;
import invtweaks.api.IItemTreeItem;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Random;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTUtil;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.oredict.OreDictionary;
import org.apache.logging.log4j.Logger;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class InvTweaksItemTree
implements IItemTree {
    public static final String UNKNOWN_ITEM = "unknown";
    private static final Logger log = InvTweaks.log;
    @Nullable
    private static List<IItemTreeItem> defaultItems = null;
    @NotNull
    private Map<String, IItemTreeCategory> categories = new HashMap<String, IItemTreeCategory>();
    @NotNull
    private Map<String, List<IItemTreeItem>> itemsById = new HashMap<String, List<IItemTreeItem>>(500);
    @NotNull
    private Map<String, List<IItemTreeItem>> itemsByName = new HashMap<String, List<IItemTreeItem>>(500);
    private String rootCategory;
    @NotNull
    private List<OreDictInfo> oresRegistered = new ArrayList<OreDictInfo>();
    private int highestOrder = 0;

    public InvTweaksItemTree() {
        this.reset();
    }

    public void reset() {
        if (defaultItems == null) {
            defaultItems = new ArrayList<IItemTreeItem>();
            defaultItems.add(new InvTweaksItemTreeItem(UNKNOWN_ITEM, null, Short.MAX_VALUE, null, Integer.MAX_VALUE));
        }
        this.categories.clear();
        this.itemsByName.clear();
        this.itemsById.clear();
    }

    @Override
    public boolean matches(@Nullable List<IItemTreeItem> items, @NotNull String keyword) {
        if (items == null) {
            return false;
        }
        for (IItemTreeItem item : items) {
            if (item.getName() == null || !item.getName().equals(keyword)) continue;
            return true;
        }
        IItemTreeCategory category = this.getCategory(keyword);
        if (category != null) {
            for (IItemTreeItem item : items) {
                if (!category.contains(item)) continue;
                return true;
            }
        }
        return keyword.equals(this.rootCategory);
    }

    @Override
    public int getKeywordDepth(String keyword) {
        try {
            return this.getRootCategory().findKeywordDepth(keyword);
        }
        catch (NullPointerException e) {
            log.error("The root category is missing: " + e.getMessage());
            return 0;
        }
    }

    @Override
    public int getKeywordOrder(String keyword) {
        List<IItemTreeItem> items = this.getItems(keyword);
        if (items != null && items.size() != 0) {
            return items.get(0).getOrder();
        }
        try {
            return this.getRootCategory().findCategoryOrder(keyword);
        }
        catch (NullPointerException e) {
            log.error("The root category is missing: " + e.getMessage());
            return -1;
        }
    }

    @Override
    public boolean isKeywordValid(String keyword) {
        if (this.containsItem(keyword)) {
            return true;
        }
        IItemTreeCategory category = this.getCategory(keyword);
        return category != null;
    }

    @Override
    @NotNull
    public Collection<IItemTreeCategory> getAllCategories() {
        return this.categories.values();
    }

    @Override
    public IItemTreeCategory getRootCategory() {
        return this.categories.get(this.rootCategory);
    }

    @Override
    public void setRootCategory(@NotNull IItemTreeCategory category) {
        this.rootCategory = category.getName();
        this.categories.put(this.rootCategory, category);
    }

    @Override
    public IItemTreeCategory getCategory(String keyword) {
        return this.categories.get(keyword);
    }

    @Override
    public boolean isItemUnknown(String id, int damage) {
        return this.itemsById.get(id) == null;
    }

    @Override
    @NotNull
    public List<IItemTreeItem> getItems(@Nullable String id, int damage, @Nullable NBTTagCompound extra) {
        if (id == null) {
            return new ArrayList<IItemTreeItem>();
        }
        List<IItemTreeItem> items = this.itemsById.get(id);
        ArrayList<IItemTreeItem> filteredItems = new ArrayList<IItemTreeItem>();
        if (items != null) {
            filteredItems.addAll(items);
        }
        if (items != null && !items.isEmpty()) {
            items.stream().filter(item -> item.getDamage() != Short.MAX_VALUE && item.getDamage() != damage).forEach(filteredItems::remove);
        }
        items = filteredItems;
        filteredItems = new ArrayList<IItemTreeItem>(items);
        if (extra != null && !items.isEmpty()) {
            items.stream().filter(item -> !NBTUtil.func_181123_a((NBTBase)item.getExtraData(), (NBTBase)extra, (boolean)true)).forEach(filteredItems::remove);
        }
        if (filteredItems.isEmpty()) {
            int newItemOrder = this.highestOrder + 1;
            InvTweaksItemTreeItem newItemId = new InvTweaksItemTreeItem(String.format("%s-%d", id, damage), id, damage, null, newItemOrder);
            InvTweaksItemTreeItem newItemDamage = new InvTweaksItemTreeItem(id, id, Short.MAX_VALUE, null, newItemOrder);
            this.addItem(this.getRootCategory().getName(), newItemId);
            this.addItem(this.getRootCategory().getName(), newItemDamage);
            filteredItems.add(newItemId);
            filteredItems.add(newItemDamage);
        }
        filteredItems.removeIf(Objects::isNull);
        return filteredItems;
    }

    @Override
    @NotNull
    public List<IItemTreeItem> getItems(String id, int damage) {
        return this.getItems(id, damage, null);
    }

    @Override
    public List<IItemTreeItem> getItems(String name) {
        return this.itemsByName.get(name);
    }

    @Override
    @NotNull
    public IItemTreeItem getRandomItem(@NotNull Random r) {
        return (IItemTreeItem)this.itemsByName.values().toArray()[r.nextInt(this.itemsByName.size())];
    }

    @Override
    public boolean containsItem(String name) {
        return this.itemsByName.containsKey(name);
    }

    @Override
    public boolean containsCategory(String name) {
        return this.categories.containsKey(name);
    }

    @Override
    @NotNull
    public IItemTreeCategory addCategory(String parentCategory, String newCategory) throws NullPointerException {
        InvTweaksItemTreeCategory addedCategory = new InvTweaksItemTreeCategory(newCategory);
        this.addCategory(parentCategory, addedCategory);
        return addedCategory;
    }

    @Override
    @NotNull
    public IItemTreeItem addItem(String parentCategory, String name, String id, int damage, int order) throws NullPointerException {
        return this.addItem(parentCategory, name, id, damage, null, order);
    }

    @Override
    @NotNull
    public IItemTreeItem addItem(String parentCategory, String name, String id, int damage, NBTTagCompound extra, int order) throws NullPointerException {
        InvTweaksItemTreeItem addedItem = new InvTweaksItemTreeItem(name, id, damage, extra, order);
        this.addItem(parentCategory, addedItem);
        return addedItem;
    }

    @Override
    public void addCategory(String parentCategory, @NotNull IItemTreeCategory newCategory) throws NullPointerException {
        this.categories.get(parentCategory).addCategory(newCategory);
        this.categories.put(newCategory.getName(), newCategory);
    }

    @Override
    public void addItem(String parentCategory, @NotNull IItemTreeItem newItem) throws NullPointerException {
        ArrayList<IItemTreeItem> list;
        this.highestOrder = Math.max(this.highestOrder, newItem.getOrder());
        this.categories.get(parentCategory).addItem(newItem);
        if (this.itemsByName.containsKey(newItem.getName())) {
            this.itemsByName.get(newItem.getName()).add(newItem);
        } else {
            list = new ArrayList<IItemTreeItem>();
            list.add(newItem);
            this.itemsByName.put(newItem.getName(), list);
        }
        if (this.itemsById.containsKey(newItem.getId())) {
            this.itemsById.get(newItem.getId()).add(newItem);
        } else {
            list = new ArrayList();
            list.add(newItem);
            this.itemsById.put(newItem.getId(), list);
        }
    }

    public int getHighestOrder() {
        return this.highestOrder;
    }

    @Override
    public void registerOre(String category, String name, String oreName, int order) {
        for (ItemStack i : OreDictionary.getOres((String)oreName)) {
            if (i != null) {
                this.addItem(category, new InvTweaksItemTreeItem(name, i.func_77973_b().getRegistryName().toString(), i.func_77952_i(), null, order));
                continue;
            }
            log.warn(String.format("An OreDictionary entry for %s is null", oreName));
        }
        this.oresRegistered.add(new OreDictInfo(category, name, oreName, order));
    }

    @SubscribeEvent
    public void oreRegistered(@NotNull OreDictionary.OreRegisterEvent ev) {
        this.oresRegistered.stream().filter(ore -> ore.oreName.equals(ev.getName())).forEach(ore -> {
            ItemStack evOre = ev.getOre();
            if (!evOre.func_190926_b()) {
                this.addItem(ore.category, new InvTweaksItemTreeItem(ore.name, evOre.func_77973_b().getRegistryName().toString(), evOre.func_77952_i(), null, ore.order));
            } else {
                log.warn(String.format("An OreDictionary entry for %s is null", ev.getName()));
            }
        });
    }

    private static class OreDictInfo {
        String category;
        String name;
        String oreName;
        int order;

        OreDictInfo(String category_, String name_, String oreName_, int order_) {
            this.category = category_;
            this.name = name_;
            this.oreName = oreName_;
            this.order = order_;
        }
    }
}

