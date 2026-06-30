/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraftforge.fml.relauncher.Side
 *  net.minecraftforge.fml.relauncher.SideOnly
 */
package eos.moe.armourers;

import eos.moe.armourers.a;
import eos.moe.armourers.cf;
import eos.moe.armourers.if;
import eos.moe.armourers.r;
import eos.moe.armourers.xf;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

/*
 * Duplicate member names - consider using --renamedupmembers true
 */
public class rj
extends cf {
    public rj(r a2) {
        super(a2);
        rj a3;
        rj rj2 = a3;
        a3.j = new xf(-10, -12, -10, 20, 15, 20);
        rj2.m = new xf(-4, -12, -2, 8, 12, 4);
        a3.s = new if(0, -1, 20);
    }

    @Override
    public String r() {
        return "skirt";
    }

    @Override
    @SideOnly(value=Side.CLIENT)
    public a r() {
        return new if(0, 12, 0);
    }
}

