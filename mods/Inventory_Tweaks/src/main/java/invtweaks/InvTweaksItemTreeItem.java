/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.nbt.NBTBase
 *  net.minecraft.nbt.NBTTagCompound
 *  net.minecraft.nbt.NBTUtil
 *  org.jetbrains.annotations.NotNull
 *  org.jetbrains.annotations.Nullable
 */
package invtweaks;

import invtweaks.InvTweaksObfuscation;
import invtweaks.api.IItemTreeItem;
import java.util.Objects;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTUtil;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class InvTweaksItemTreeItem
implements IItemTreeItem {
    private String name;
    @Nullable
    private String id;
    private int damage;
    private NBTTagCompound extraData;
    private int order;

    public InvTweaksItemTreeItem(String name_, String id_, int damage_, NBTTagCompound extraData_, int order_) {
        this.name = name_;
        this.id = InvTweaksObfuscation.getNamespacedID(id_);
        this.damage = damage_;
        this.extraData = extraData_;
        this.order = order_;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    @Nullable
    public String getId() {
        return this.id;
    }

    @Override
    public int getDamage() {
        return this.damage;
    }

    @Override
    public NBTTagCompound getExtraData() {
        return this.extraData;
    }

    @Override
    public int getOrder() {
        return this.order;
    }

    public boolean equals(@Nullable Object o) {
        if (o == null || !(o instanceof IItemTreeItem)) {
            return false;
        }
        IItemTreeItem item = (IItemTreeItem)o;
        return Objects.equals(this.id, item.getId()) && NBTUtil.func_181123_a((NBTBase)this.extraData, (NBTBase)item.getExtraData(), (boolean)true) && (this.damage == Short.MAX_VALUE || this.damage == item.getDamage());
    }

    public String toString() {
        return this.name;
    }

    @Override
    public int compareTo(@NotNull IItemTreeItem item) {
        return item.getOrder() - this.order;
    }
}

