/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.util.EnumFacing
 *  net.minecraft.util.math.BlockPos
 *  net.minecraft.util.math.MathHelper
 *  net.minecraftforge.fml.relauncher.Side
 *  net.minecraftforge.fml.relauncher.SideOnly
 */
package eos.moe.armourers;

import eos.moe.armourers.if;
import eos.moe.armourers.q;
import eos.moe.armourers.qf;
import eos.moe.armourers.ul;
import eos.moe.armourers.vn;
import eos.moe.armourers.we;
import eos.moe.armourers.xf;
import eos.moe.armourers.y;
import java.util.ArrayList;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

/*
 * Duplicate member names - consider using --renamedupmembers true
 */
public class kf
implements q {
    private int r;
    private xf[][][] l;
    private y c;
    private qf v;
    @SideOnly(value=Side.CLIENT)
    private ul s;
    private xf m;
    private ArrayList<we> j;

    public xf y() {
        kf a2;
        return a2.m;
    }

    public xf[][][] r() {
        kf a2;
        return a2.l;
    }

    private /* synthetic */ void r(int a22, int a3, int a4, int a5, int a6, int a7) {
        kf a8;
        BlockPos a22 = new BlockPos(a22 + 1, -a3, a4);
        if (a8.l[a22.func_177958_n()][a22.func_177956_o()][a22.func_177952_p()] == null) {
            a8.l[a22.func_177958_n()][a22.func_177956_o()][a22.func_177952_p()] = new xf(127, 127, 127, -127, -127, -127);
        }
        xf xf2 = a8.l[a22.func_177958_n()][a22.func_177956_o()][a22.func_177952_p()];
        xf2.s(Math.min(xf2.x(), a5));
        xf2.r(Math.min(xf2.s(), a6));
        xf2.y(Math.min(xf2.r(), a7));
        xf2.h(Math.max(xf2.y(), a5));
        xf2.x(Math.max(xf2.h(), a6));
        xf2.z(Math.max(xf2.z(), a7));
    }

    public qf r() {
        kf a2;
        return a2.v;
    }

    @Override
    public if r(int a2) {
        kf a3;
        int n2;
        boolean bl;
        if (a2 >= 0) {
            bl = true;
            n2 = a2;
        } else {
            bl = false;
            n2 = a2;
        }
        if (bl & n2 < a3.j.size()) {
            we we2 = a2 = a3.j.get(a2);
            return new if(we2.m, we2.j, a2.v);
        }
        return null;
    }

    public void y() {
        a.v = null;
    }

    private /* synthetic */ xf r() {
        int n2;
        int n3;
        kf kf2;
        boolean bl;
        kf a2;
        if (a2.c == vn.h.r("armourers:block.base")) {
            bl = true;
            kf2 = a2;
        } else {
            bl = false;
            kf2 = a2;
        }
        if (bl | kf2.c == vn.h.r("armourers:block.multiblock")) {
            a2.r();
        }
        int n4 = 127;
        int n5 = -127;
        int n6 = 127;
        int n7 = -127;
        int n8 = 127;
        int n9 = -127;
        int n10 = n3 = 0;
        while (n10 < a2.v.r()) {
            byte[] byArray = a2.v.h(n3);
            n2 = byArray[0];
            byte by = byArray[1];
            byte by2 = byArray[2];
            n4 = Math.min(n2, n4);
            n5 = Math.max(n2, n5);
            n6 = Math.min(by, n6);
            n7 = Math.max(by, n7);
            n8 = Math.min(by2, n8);
            n9 = Math.max(by2, n9);
            n10 = ++n3;
        }
        n3 = n5 - n4;
        int n11 = n7 - n6;
        n2 = n9 - n8;
        return new xf(n4, n6, n8, n3 + 1, n11 + 1, n2 + 1);
    }

    @Override
    public int r() {
        kf a2;
        return a2.j.size();
    }

    @Override
    public y r() {
        kf a2;
        return a2.c;
    }

    @Override
    public EnumFacing r(int a2) {
        kf a3;
        int n2;
        boolean bl;
        if (a2 >= 0) {
            bl = true;
            n2 = a2;
        } else {
            bl = false;
            n2 = a2;
        }
        if (bl & n2 < a3.j.size()) {
            return EnumFacing.func_82600_a((int)(a3.j.get((int)a2).s - 1));
        }
        return null;
    }

    public String toString() {
        kf a2;
        return new StringBuilder().insert(0, "SkinPart [cubeData=").append(a2.v).append(", markerBlocks=").append(a2.j).append(", skinPart=").append(a2.c.y()).append("]").toString();
    }

    public ArrayList<we> r() {
        kf a2;
        return a2.j;
    }

    @SideOnly(value=Side.CLIENT)
    public void r(ul a2) {
        a.s = a2;
    }

    @SideOnly(value=Side.CLIENT)
    public ul r() {
        kf a2;
        return a2.s;
    }

    @SideOnly(value=Side.CLIENT)
    public int y() {
        kf a2;
        return a2.s.getModelCount();
    }

    private /* synthetic */ void r() {
        int n2;
        kf a2;
        int n3;
        a2.l = new xf[3][3][3];
        int n4 = n3 = 0;
        while (n4 < a2.v.r()) {
            kf kf2 = a2;
            byte[] byArray = kf2.v.h(n3);
            n2 = MathHelper.func_76141_d((float)((float)(byArray[0] + 8) / 16.0f));
            int n5 = MathHelper.func_76141_d((float)((float)(byArray[1] + 8) / 16.0f));
            int n6 = MathHelper.func_76141_d((float)((float)(byArray[2] + 8) / 16.0f));
            kf2.r(n2, n5, n6, byArray[0] - n2 * 16, byArray[1] - n5 * 16, byArray[2] - n6 * 16);
            n4 = ++n3;
        }
        int n7 = n3 = 0;
        while (n7 < 3) {
            int n8;
            int n9 = n8 = 0;
            while (n9 < 3) {
                int n10 = n2 = 0;
                while (n10 < 3) {
                    xf xf2 = a2.l[n3][n8][n2];
                    if (xf2 != null) {
                        xf xf3 = xf2;
                        xf xf4 = xf2;
                        xf3.h(xf3.y() - xf4.x() + 1);
                        xf3.x(xf4.h() - xf2.s() + 1);
                        xf3.z(xf3.z() - xf2.r() + 1);
                    }
                    n10 = ++n2;
                }
                n9 = ++n8;
            }
            n7 = ++n3;
        }
    }

    public xf r(int a2, int a3, int a4) {
        kf a5;
        return a5.l[a2][a3][a4];
    }

    public void r(y a2) {
        kf a3;
        a3.c = a2;
        a3.r();
    }

    public kf(qf a2, y a3, ArrayList<we> a4, int a5) {
        kf a6;
        kf kf2 = a6;
        kf kf3 = a6;
        kf3.r = a5;
        kf3.v = a2;
        a6.c = a3;
        kf2.j = a4;
        kf2.m = a6.r();
    }
}

