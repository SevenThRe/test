/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.util.ResourceLocation
 */
package eos.moe.dragoncore;

import eos.moe.dragoncore.api.model.AnimationEntityModel;
import eos.moe.dragoncore.ea;
import eos.moe.dragoncore.hs;
import java.util.function.Consumer;
import net.minecraft.util.ResourceLocation;

/*
 * Duplicate member names - consider using --renamedupmembers true
 */
public abstract class kq {
    public kq() {
        kq a2;
    }

    public abstract void ALLATORIxDEMO(AnimationEntityModel var1, ea var2, int var3);

    public abstract String ALLATORIxDEMO();

    public abstract ResourceLocation ALLATORIxDEMO();

    public abstract int ALLATORIxDEMO();

    public abstract boolean f();

    public void c() {
    }

    public abstract boolean c();

    public void ALLATORIxDEMO() {
    }

    public abstract float ALLATORIxDEMO();

    public void ALLATORIxDEMO(float a2) {
    }

    public abstract boolean ALLATORIxDEMO();

    public abstract hs ALLATORIxDEMO();

    public abstract void ALLATORIxDEMO(Consumer<String> var1);

    public abstract kq ALLATORIxDEMO();

    public boolean equals(Object a2) {
        kq a3;
        if (a3 == a2) {
            return true;
        }
        if (!(a2 instanceof kq)) {
            return false;
        }
        kq a4 = (kq)a2;
        return a3.ALLATORIxDEMO().equals((Object)a4.ALLATORIxDEMO());
    }

    public int hashCode() {
        kq a2;
        return a2.ALLATORIxDEMO().hashCode();
    }
}

