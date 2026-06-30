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
public class fh
extends cf
implements o {
    @Override
    @SideOnly(value=Side.CLIENT)
    public a r() {
        return new if(0, 0, 0);
    }

    @Override
    @SideOnly(value=Side.CLIENT)
    public j y() {
        return new xf(-4, 0, -2, 8, 12, 4);
    }

    @Override
    public Point y() {
        return new Point(16, 32);
    }

    @Override
    public a z() {
        return new if(8, 12, 4);
    }

    @Override
    public boolean r() {
        return false;
    }

    @Override
    public boolean y(in a2) {
        return in.ra.r(a2);
    }

    @Override
    public Point z() {
        return new Point(16, 16);
    }

    @Override
    public Point r() {
        return new Point(16, 16);
    }

    @Override
    public String r() {
        return "base";
    }

    public fh(r a2) {
        super(a2);
        fh a3;
        fh fh2 = a3;
        a3.j = new xf(-6, -24, -30, 12, 38, 60);
        fh2.m = new xf(-4, -12, -2, 8, 12, 4);
        a3.s = new if(0, -1, 0);
    }

    @Override
    public boolean r(in a2) {
        return in.b.r(a2);
    }
}

