/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.init.Items
 *  net.minecraft.util.EnumHand
 */
package eos.moe.dragoncore;

import eos.moe.dragoncore.ls;
import eos.moe.dragoncore.yu;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.util.EnumHand;

/*
 * Duplicate member names - consider using --renamedupmembers true
 */
public class oz
extends yu<EntityPlayer> {
    public boolean t = false;
    public boolean r = false;
    public boolean x = false;
    public int v = 0;
    public float m = 0.0f;
    public float k = 0.0f;
    private Boolean ALLATORIxDEMO = null;

    public oz(EntityPlayer a2) {
        super(a2);
        oz a3;
    }

    public void c(float a2) {
        a.k = a2;
    }

    public float h() {
        oz a2;
        return a2.m;
    }

    public void x(boolean a2) {
        a.ALLATORIxDEMO = a2;
    }

    public void z() {
        a.ALLATORIxDEMO = null;
    }

    @Override
    public void ALLATORIxDEMO(float a2) {
        oz a3;
        super.ALLATORIxDEMO(a2);
        if (a3.k() > 20.0f) {
            a3.v = 0;
        }
        if (a3.q < 0.0) {
            a3.r = false;
        }
        if (!a3.r && a3.q > 0.0) {
            a3.t = !a3.t;
            a3.r = true;
        }
        a3.m += a3.k * ls.ALLATORIxDEMO;
        if (a3.m > 380.0f) {
            a3.m -= 380.0f;
        }
    }

    @Override
    public void d() {
        oz a2;
        super.d();
        if (!a2.r) {
            a2.t = !a2.t;
            a2.r = true;
        }
    }

    @Override
    public void x() {
        oz a2;
        if (((EntityPlayer)a2.s).getHeldItem(EnumHand.MAIN_HAND).getItem() == Items.AIR) {
            a2.x = !a2.x;
            a2.b = 0.0f;
            return;
        }
        if (a2.b <= 6.0f) {
            return;
        }
        switch (a2.v) {
            case 1: {
                a2.v = 2;
                break;
            }
            case 2: {
                a2.v = 3;
                break;
            }
            case 3: {
                a2.v = 4;
                break;
            }
            case 4: {
                a2.v = ((EntityPlayer)a2.ALLATORIxDEMO()).isRiding() ? 1 : 5;
                break;
            }
            default: {
                a2.v = 1;
            }
        }
        a2.b = 0.0f;
    }

    @Override
    public int ALLATORIxDEMO() {
        oz a2;
        return a2.v;
    }

    public boolean n() {
        oz a2;
        return a2.x;
    }

    public boolean y() {
        oz a2;
        return a2.t;
    }

    public boolean h() {
        oz a2;
        return a2.ALLATORIxDEMO != null ? a2.ALLATORIxDEMO : ((EntityPlayer)a2.s).capabilities.isFlying;
    }
}

