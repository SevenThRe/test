/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.item.ItemStack
 *  net.minecraft.nbt.NBTTagCompound
 */
package eos.moe.dragoncore;

import eos.moe.dragoncore.v;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;

/*
 * Duplicate member names - consider using --renamedupmembers true
 */
public class xk
implements v {
    private ItemStack ALLATORIxDEMO;

    public xk(ItemStack a2) {
        xk a3;
        a3.ALLATORIxDEMO = a2;
    }

    @Override
    public Object ALLATORIxDEMO() {
        xk a2;
        return a2.ALLATORIxDEMO;
    }

    @Override
    public String c() {
        xk a2;
        NBTTagCompound a3 = new NBTTagCompound();
        a2.ALLATORIxDEMO.func_77955_b(a3);
        return a3.toString();
    }

    @Override
    public ItemStack ALLATORIxDEMO() {
        xk a2;
        return a2.ALLATORIxDEMO;
    }

    @Override
    public String ALLATORIxDEMO() {
        return "itemstack";
    }
}

