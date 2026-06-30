/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.Entity
 *  net.minecraft.item.ItemStack
 *  net.minecraft.util.text.ITextComponent
 */
package eos.moe.dragoncore;

import eos.moe.dragoncore.cz;
import eos.moe.dragoncore.p;
import eos.moe.dragoncore.pf;
import net.minecraft.entity.Entity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.ITextComponent;

/*
 * Duplicate member names - consider using --renamedupmembers true
 */
public interface v {
    public static v ALLATORIxDEMO(Object a2) {
        if (a2 instanceof v) {
            return (v)a2;
        }
        return new pf(a2);
    }

    public Object ALLATORIxDEMO();

    default public String c() {
        v a2;
        return a2.toString();
    }

    default public boolean ALLATORIxDEMO() {
        v a2;
        return a2.ALLATORIxDEMO() != 0.0;
    }

    default public double ALLATORIxDEMO() {
        return 0.0;
    }

    default public ItemStack ALLATORIxDEMO() {
        return ItemStack.EMPTY;
    }

    default public p ALLATORIxDEMO() {
        return null;
    }

    default public cz ALLATORIxDEMO() {
        return null;
    }

    default public ITextComponent ALLATORIxDEMO() {
        return null;
    }

    default public Entity ALLATORIxDEMO() {
        return null;
    }

    public String ALLATORIxDEMO();
}

