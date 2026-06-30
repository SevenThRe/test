/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.util.math.BlockPos
 *  net.minecraft.util.math.Vec3d
 */
package eos.moe.dragongps;

import java.util.List;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;

/*
 * Duplicate member names - consider using --renamedupmembers true
 * Renamed from eos.moe.dragongps.iIIiiIiiiI
 */
public class iiiiiiiiii_10 {
    public String iiIIIiIiII;
    public BlockPos IIiiIiiIII;
    public int iIIiIIiIii;
    public String iIIIIiiIII;
    public int IIiIiIIIiI;
    public List<String> IIiiiiiIIi;

    public void IIIiiiIiii(BlockPos IIiiiiiIIi) {
        IIiiiiiIIi.IIiiIiiIII = IIiiiiiIIi;
    }

    public iiiiiiiiii_10(List<String> IIiiiiiIIi2, String IIiiiiiIIi3, String IIiiiiiIIi4, double IIiiiiiIIi5, double IIiiiiiIIi6, double IIiiiiiIIi7, int IIiiiiiIIi8, int IIiiiiiIIi9) {
        iiiiiiiiii_10 IIiiiiiIIi10;
        IIiiiiiIIi10.IIiiIiiIII = new BlockPos(IIiiiiiIIi5, IIiiiiiIIi6, IIiiiiiIIi7);
        if (IIiiiiiIIi3 != null && !"null".equalsIgnoreCase(IIiiiiiIIi3)) {
            IIiiiiiIIi10.iIIIIiiIII = IIiiiiiIIi3;
        }
        IIiiiiiIIi10.IIiiiiiIIi = IIiiiiiIIi2;
        IIiiiiiIIi10.IIiiiiiIIi.replaceAll(IIiiiiiIIi -> IIiiiiiIIi.replace("&", "\u00a7"));
        IIiiiiiIIi10.iiIIIiIiII = IIiiiiiIIi4;
        IIiiiiiIIi10.iIIiIIiIii = IIiiiiiIIi8;
        IIiiiiiIIi10.IIiIiIIIiI = IIiiiiiIIi9;
    }

    public void IIIiiiIiii(String IIiiiiiIIi) {
        IIiiiiiIIi.iIIIIiiIII = IIiiiiiIIi;
    }

    public BlockPos IIIiiiIiii() {
        iiiiiiiiii_10 IIiiiiiIIi;
        return IIiiiiiIIi.IIiiIiiIII;
    }

    public String IIIiiiIiii() {
        iiiiiiiiii_10 IIiiiiiIIi;
        return IIiiiiiIIi.iIIIIiiIII;
    }

    public List<String> IIIiiiIiii() {
        iiiiiiiiii_10 IIiiiiiIIi;
        return IIiiiiiIIi.IIiiiiiIIi;
    }

    public void IIIiiiIiii(List<String> IIiiiiiIIi) {
        IIiiiiiIIi.IIiiiiiIIi = IIiiiiiIIi;
    }

    public Vec3d IIIiiiIiii() {
        iiiiiiiiii_10 IIiiiiiIIi;
        return new Vec3d((double)IIiiiiiIIi.IIiiIiiIII.getX() + 0.5, (double)IIiiiiiIIi.IIiiIiiIII.getY() + 0.5, (double)IIiiiiiIIi.IIiiIiiIII.getZ() + 0.5);
    }
}

