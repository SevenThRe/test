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
import eos.moe.armourers.in;
import eos.moe.armourers.j;
import eos.moe.armourers.o;
import eos.moe.armourers.r;
import eos.moe.armourers.xf;
import java.awt.Point;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

/*
 * Duplicate member names - consider using --renamedupmembers true
 */
public class dd
extends cf
implements o {
    @Override
    @SideOnly(value=Side.CLIENT)
    public a r() {
        return new if(-5, 2, 0);
    }

    @Override
    public boolean r() {
        return false;
    }

    @Override
    public a z() {
        return new if(4, 12, 4);
    }

    @Override
    public String r() {
        return "rightArm";
    }

    public dd(r a2) {
        super(a2);
        dd a3;
        dd dd2 = a3;
        a3.j = new xf(-3, -16, -14, 14, 32, 28);
        dd2.m = new xf(-1, -10, -2, 4, 12, 4);
        a3.s = new if(-10, -7, 0);
    }

    @Override
    public Point y() {
        return new Point(40, 32);
    }

    @Override
    @SideOnly(value=Side.CLIENT)
    public j y() {
        return new xf(4, 0, -2, 4, 12, 4);
    }

    @Override
    public boolean r(in a2) {
        return in.n.r(a2);
    }

    @Override
    public Point r() {
        return new Point(40, 16);
    }

    @Override
    public Point z() {
        return new Point(40, 16);
    }

    @Override
    public boolean y(in a2) {
        return in.d.r(a2);
    }
}

