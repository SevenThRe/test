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
public class im
extends cf
implements o {
    @Override
    public boolean r(in a2) {
        return in.o.r(a2);
    }

    @Override
    public boolean r() {
        return false;
    }

    @Override
    @SideOnly(value=Side.CLIENT)
    public j y() {
        return new xf(-4, 12, -2, 4, 12, 4);
    }

    @Override
    @SideOnly(value=Side.CLIENT)
    public a r() {
        return new if(-2, 12, 0);
    }

    @Override
    public boolean y(in a2) {
        return in.ua.r(a2);
    }

    @Override
    public Point r() {
        return new Point(0, 16);
    }

    @Override
    public a z() {
        return new if(4, 12, 4);
    }

    @Override
    public Point z() {
        return new Point(0, 16);
    }

    public im(r a2) {
        super(a2);
        im a3;
        im im2 = a3;
        a3.j = new xf(-3, -8, -8, 11, 9, 16);
        im2.m = new xf(-2, -12, -2, 4, 12, 4);
        a3.s = new if(-6, -5, 0);
    }

    @Override
    public String r() {
        return "rightLeg";
    }

    @Override
    public Point y() {
        return new Point(0, 32);
    }
}

