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
public class td
extends cf
implements o {
    @Override
    public Point r() {
        return new Point(0, 16);
    }

    @Override
    public a z() {
        return new if(4, 12, 4);
    }

    @Override
    public boolean r(in a2) {
        return in.r.r(a2);
    }

    @Override
    public boolean r() {
        return true;
    }

    @Override
    public boolean y(in a2) {
        return in.x.r(a2);
    }

    @Override
    public Point z() {
        return new Point(16, 48);
    }

    @Override
    public Point y() {
        return new Point(0, 48);
    }

    @Override
    @SideOnly(value=Side.CLIENT)
    public a r() {
        return new if(2, 12, 0);
    }

    @Override
    @SideOnly(value=Side.CLIENT)
    public j y() {
        return new xf(0, 12, -2, 4, 12, 4);
    }

    public td(r a2) {
        super(a2);
        td a3;
        td td2 = a3;
        a3.j = new xf(-8, -8, -8, 11, 9, 16);
        td2.m = new xf(-2, -12, -2, 4, 12, 4);
        a3.s = new if(6, -5, 0);
    }

    @Override
    public String r() {
        return "leftLeg";
    }
}

