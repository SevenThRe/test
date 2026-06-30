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
public class jl
extends cf
implements o {
    @Override
    public String r() {
        return "leftArm";
    }

    @Override
    public boolean y(in a2) {
        return in.c.r(a2);
    }

    @Override
    public a z() {
        return new if(4, 12, 4);
    }

    @Override
    public Point z() {
        return new Point(32, 48);
    }

    @Override
    public boolean r(in a2) {
        return in.f.r(a2);
    }

    @Override
    public Point y() {
        return new Point(48, 48);
    }

    @Override
    @SideOnly(value=Side.CLIENT)
    public j y() {
        return new xf(-8, 0, -2, 4, 12, 4);
    }

    public jl(r a2) {
        super(a2);
        jl a3;
        jl jl2 = a3;
        a3.j = new xf(-11, -16, -14, 14, 32, 28);
        jl2.m = new xf(-3, -10, -2, 4, 12, 4);
        a3.s = new if(10, -7, 0);
    }

    @Override
    public boolean r() {
        return true;
    }

    @Override
    @SideOnly(value=Side.CLIENT)
    public a r() {
        return new if(5, 2, 0);
    }

    @Override
    public Point r() {
        return new Point(40, 16);
    }
}

