/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.util.ResourceLocation
 */
package eos.moe.armourers;

import eos.moe.armourers.dn;
import eos.moe.armourers.kf;
import eos.moe.armourers.n;
import eos.moe.armourers.oh;
import net.minecraft.util.ResourceLocation;

/*
 * Duplicate member names - consider using --renamedupmembers true
 */
public class mk
extends dn {
    private kf m;
    private String j;

    public kf r() {
        mk a2;
        return a2.m;
    }

    public mk(kf a2, dn a3, String a4) {
        mk a5;
        mk mk2 = a5;
        super(a3.r(), a3.r(), a3.r(), a3.r(), a3.y(), a3.h(), a3.r(), a3.r());
        mk2.m = a2;
        mk2.j = a4;
    }

    public String r() {
        mk a2;
        return a2.j;
    }

    public mk(kf a2, dn a3) {
        super(a3.r(), a3.r(), a3.r(), a3.r(), a3.y(), a3.h(), a3.r(), a3.r());
        mk a4;
        a4.m = a2;
    }

    public mk(kf a2, float a3, n a4, oh a5, double a6, boolean a7, boolean a8, boolean a9, ResourceLocation a10) {
        super(a3, a4, a5, a6, a7, a8, a9, a10);
        mk a11;
        a11.m = a2;
    }
}

