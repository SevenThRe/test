/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  javax.annotation.Nonnull
 *  net.minecraft.item.ItemStack
 */
package invtweaks.api;

import invtweaks.api.IItemTreeListener;
import invtweaks.api.SortingMethod;
import invtweaks.api.container.ContainerSection;
import javax.annotation.Nonnull;
import net.minecraft.item.ItemStack;

public interface InvTweaksAPI {
    public void addOnLoadListener(IItemTreeListener var1);

    public boolean removeOnLoadListener(IItemTreeListener var1);

    public void setSortKeyEnabled(boolean var1);

    public void setTextboxMode(boolean var1);

    public int compareItems(@Nonnull ItemStack var1, @Nonnull ItemStack var2);

    public void sort(ContainerSection var1, SortingMethod var2);
}

