/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.Minecraft
 */
package eos.moe.dragoncore;

import eos.moe.dragoncore.ca;
import eos.moe.dragoncore.gka;
import eos.moe.dragoncore.laa;
import net.minecraft.client.Minecraft;

/*
 * Duplicate member names - consider using --renamedupmembers true
 */
public enum ala {
    q(0),
    b(1),
    o(2),
    y(1);

    private final int k;

    /*
     * WARNING - Possible parameter corruption
     * WARNING - void declaration
     */
    private /* synthetic */ ala(int n2) {
        void a2;
        ala a3;
        a3.k = a2;
    }

    public int ALLATORIxDEMO() {
        ala a2;
        return a2.k;
    }

    public static ala ALLATORIxDEMO(int a2, boolean a3) {
        switch (a2) {
            case 0: {
                return q;
            }
            case 1: {
                return a3 ? y : b;
            }
            case 2: {
                return o;
            }
        }
        return q;
    }

    public static void ALLATORIxDEMO(ala a2) {
        Minecraft.func_71410_x().field_71474_y.field_74320_O = a2.ALLATORIxDEMO();
        if (ca.y) {
            gka.ALLATORIxDEMO(y.equals((Object)a2));
        } else if (ca.k) {
            laa.ALLATORIxDEMO(y.equals((Object)a2));
        }
    }

    public static ala ALLATORIxDEMO() {
        if (ca.y) {
            return ala.ALLATORIxDEMO(Minecraft.func_71410_x().field_71474_y.field_74320_O, gka.ALLATORIxDEMO());
        }
        if (ca.k) {
            return ala.ALLATORIxDEMO(Minecraft.func_71410_x().field_71474_y.field_74320_O, laa.ALLATORIxDEMO());
        }
        return ala.ALLATORIxDEMO(Minecraft.func_71410_x().field_71474_y.field_74320_O, false);
    }
}

