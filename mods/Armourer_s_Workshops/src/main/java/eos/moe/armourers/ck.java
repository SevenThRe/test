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
public class ck
extends cf
implements o {
    @Override
    @SideOnly(value=Side.CLIENT)
    public a r() {
        return new if(0, 0, 0);
    }

    @Override
    public boolean y(in a2) {
        return in.w.r(a2);
    }

    public ck(r a2) {
        super(a2);
        ck a3;
        ck ck2 = a3;
        a3.j = new xf(-32, -24, -32, 64, 56, 64);
        ck2.m = new xf(-4, 0, -4, 8, 8, 8);
        a3.s = new if(0, 0, 0);
    }

    @Override
    public Point z() {
        return new Point(0, 0);
    }

    @Override
    public Point y() {
        return new Point(32, 0);
    }

    @Override
    public boolean r() {
        return false;
    }

    @Override
    public String r() {
        return "base";
    }

    @Override
    @SideOnly(value=Side.CLIENT)
    public j y() {
        return new xf(-4, -8, -4, 8, 8, 8);
    }

    @Override
    public boolean r(in a2) {
        return in.t.r(a2);
    }

    @Override
    public Point r() {
        return new Point(0, 0);
    }

    @Override
    public a z() {
        return new if(8, 8, 8);
    }
}

