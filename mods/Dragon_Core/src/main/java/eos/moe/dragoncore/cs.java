/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.Entity
 *  net.minecraft.util.ResourceLocation
 */
package eos.moe.dragoncore;

import eos.moe.dragoncore.kq;
import eos.moe.dragoncore.st;
import eos.moe.dragoncore.ux;
import eos.moe.dragoncore.xr;
import java.util.Objects;
import java.util.function.BiConsumer;
import java.util.function.Predicate;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;

/*
 * Duplicate member names - consider using --renamedupmembers true
 */
public class cs<T extends Entity, EXTRA_DATA> {
    private final st b;
    private final String o;
    private final ResourceLocation y;
    private Predicate<xr> k = ux.c();
    private BiConsumer<? super T, ? super EXTRA_DATA> ALLATORIxDEMO = (a2, a3) -> {};

    public cs(ResourceLocation a4, st a5, String a6) {
        cs a7;
        a7.y = a4;
        a7.b = a5;
        a7.o = a6;
    }

    public cs<T, EXTRA_DATA> ALLATORIxDEMO(BiConsumer<? super T, ? super EXTRA_DATA> a2) {
        cs a3;
        a3.ALLATORIxDEMO = a2;
        return a3;
    }

    public cs<T, EXTRA_DATA> ALLATORIxDEMO(Predicate<xr> a2) {
        cs a3;
        a3.k = a2;
        return a3;
    }

    public BiConsumer<? super T, ? super EXTRA_DATA> ALLATORIxDEMO() {
        cs a2;
        return a2.ALLATORIxDEMO;
    }

    public st ALLATORIxDEMO() {
        cs a2;
        return a2.b;
    }

    public Predicate<xr> ALLATORIxDEMO() {
        cs a2;
        return a2.k;
    }

    public String ALLATORIxDEMO() {
        cs a2;
        return a2.o;
    }

    public boolean equals(Object a2) {
        cs a3;
        if (a3 == a2) {
            return true;
        }
        if (!(a2 instanceof cs)) {
            return false;
        }
        cs a4 = (cs)a2;
        return a3.y.equals((Object)a4.y);
    }

    public boolean ALLATORIxDEMO(kq a2) {
        cs a3;
        return a3.b.ALLATORIxDEMO().ALLATORIxDEMO().equals(a2);
    }

    public int hashCode() {
        cs a2;
        return Objects.hash(a2.y);
    }
}

