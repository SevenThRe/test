/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.Entity
 *  net.minecraft.util.math.BlockPos
 *  net.minecraft.util.math.MathHelper
 *  net.minecraft.world.EnumSkyBlock
 */
package eos.moe.dragoncore;

import eos.moe.dragoncore.ha;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.EnumSkyBlock;

/*
 * Duplicate member names - consider using --renamedupmembers true
 */
public class eka {
    private final ha c;
    private int q;
    private int b;
    private int o;
    private int y;
    private int k;
    private int ALLATORIxDEMO;

    public eka(ha a2) {
        eka a3;
        a3.c = a2;
        a3.o = 0;
        a3.b = 0;
        a3.q = 0;
        a3.ALLATORIxDEMO = 0;
        a3.k = 0;
        a3.y = 0;
    }

    public boolean ALLATORIxDEMO() {
        eka a2;
        Entity a3 = a2.c.ALLATORIxDEMO();
        if (!a3.isEntityAlive()) {
            return true;
        }
        if (a2.ALLATORIxDEMO(a3)) {
            a3.world.checkLightFor(EnumSkyBlock.BLOCK, new BlockPos(a2.y, a2.k, a2.ALLATORIxDEMO));
            a3.world.checkLightFor(EnumSkyBlock.BLOCK, new BlockPos(a2.q, a2.b, a2.o));
        }
        return false;
    }

    public int f() {
        eka a2;
        return a2.y;
    }

    public int c() {
        eka a2;
        return a2.k;
    }

    public int ALLATORIxDEMO() {
        eka a2;
        return a2.ALLATORIxDEMO;
    }

    public ha ALLATORIxDEMO() {
        eka a2;
        return a2.c;
    }

    private /* synthetic */ boolean ALLATORIxDEMO(Entity a2) {
        eka a3;
        int a4 = MathHelper.floor((double)a2.posX);
        int a5 = MathHelper.floor((double)a2.posY);
        int a6 = MathHelper.floor((double)a2.posZ);
        if (a4 != a3.y || a5 != a3.k || a6 != a3.ALLATORIxDEMO) {
            a3.q = a3.y;
            a3.b = a3.k;
            a3.o = a3.ALLATORIxDEMO;
            a3.y = a4;
            a3.k = a5;
            a3.ALLATORIxDEMO = a6;
            return true;
        }
        return false;
    }

    public boolean equals(Object a2) {
        if (a2 instanceof eka) {
            eka a3;
            eka a4 = (eka)a2;
            if (a4.c == a3.c) {
                return true;
            }
        }
        return false;
    }

    public int hashCode() {
        eka a2;
        return a2.c.ALLATORIxDEMO().getEntityId();
    }
}

