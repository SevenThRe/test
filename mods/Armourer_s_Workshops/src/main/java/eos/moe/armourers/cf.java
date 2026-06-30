/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraftforge.fml.relauncher.Side
 *  net.minecraftforge.fml.relauncher.SideOnly
 */
package eos.moe.armourers;

import eos.moe.armourers.a;
import eos.moe.armourers.if;
import eos.moe.armourers.in;
import eos.moe.armourers.j;
import eos.moe.armourers.r;
import eos.moe.armourers.y;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

/*
 * Duplicate member names - consider using --renamedupmembers true
 */
public abstract class cf
implements y {
    private r v;
    public a s;
    public j m;
    public j j;

    @Override
    public j z() {
        cf a2;
        return a2.j;
    }

    @Override
    @SideOnly(value=Side.CLIENT)
    public j y() {
        return null;
    }

    @Override
    public boolean y(in a2) {
        return false;
    }

    @Override
    @SideOnly(value=Side.CLIENT)
    public a r() {
        return new if(0, 0, 0);
    }

    public cf(r a2) {
        cf a3;
        a3.v = a2;
    }

    @Override
    public a y() {
        cf a2;
        return a2.s;
    }

    @Override
    public boolean r(in a2) {
        return false;
    }

    @Override
    public String y() {
        cf a2;
        return new StringBuilder().insert(0, a2.v.y()).append(".").append(a2.r()).toString();
    }

    @Override
    public j r() {
        cf a2;
        return a2.m;
    }
}

