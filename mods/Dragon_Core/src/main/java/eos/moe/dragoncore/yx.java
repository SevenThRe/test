/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.Minecraft
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.init.Items
 *  net.minecraft.util.EnumHand
 */
package eos.moe.dragoncore;

import eos.moe.dragoncore.i;
import eos.moe.dragoncore.xz;
import eos.moe.dragoncore.yu;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.util.EnumHand;

/*
 * Duplicate member names - consider using --renamedupmembers true
 */
public class yx {
    public yx() {
        yx a2;
    }

    @i(f={"\u662f\u5426\u98de\u884c\u4e2d"})
    public static boolean f(xz a2) {
        EntityLivingBase a3 = a2.ALLATORIxDEMO();
        if (a3 instanceof EntityPlayer) {
            return ((EntityPlayer)a3).field_71075_bZ.field_75100_b;
        }
        return false;
    }

    @i(f={"\u53d6\u653b\u51fb\u540e\u65f6\u95f4\u523b"})
    public static float ALLATORIxDEMO(xz a2) {
        yu a3 = a2.o;
        return a3.k();
    }

    @i(f={"\u662f\u5426\u6301\u6709\u7269\u54c1"})
    public static boolean c(xz a2) {
        return a2.ALLATORIxDEMO().func_184586_b(EnumHand.MAIN_HAND).func_77973_b() != Items.field_190931_a;
    }

    @i(f={"\u662f\u5426\u662f\u81ea\u5df1"})
    public static boolean ALLATORIxDEMO(xz a2) {
        return a2.ALLATORIxDEMO() == Minecraft.func_71410_x().field_71439_g;
    }
}

