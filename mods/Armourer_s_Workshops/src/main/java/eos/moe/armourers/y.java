/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraftforge.fml.relauncher.Side
 *  net.minecraftforge.fml.relauncher.SideOnly
 */
package eos.moe.armourers;

import eos.moe.armourers.a;
import eos.moe.armourers.in;
import eos.moe.armourers.j;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

/*
 * Duplicate member names - consider using --renamedupmembers true
 */
public interface y {
    public boolean y(in var1);

    public j z();

    @SideOnly(value=Side.CLIENT)
    public j y();

    public a y();

    public boolean r(in var1);

    public j r();

    public String y();

    @SideOnly(value=Side.CLIENT)
    public a r();

    public String r();
}

