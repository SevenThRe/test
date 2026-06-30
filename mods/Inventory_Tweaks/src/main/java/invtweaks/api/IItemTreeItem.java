/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.nbt.NBTTagCompound
 */
package invtweaks.api;

import net.minecraft.nbt.NBTTagCompound;

public interface IItemTreeItem
extends Comparable<IItemTreeItem> {
    public String getName();

    public String getId();

    public int getDamage();

    public NBTTagCompound getExtraData();

    public int getOrder();
}

