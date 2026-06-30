/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.util.math.MathHelper
 *  net.minecraftforge.fml.relauncher.Side
 *  net.minecraftforge.fml.relauncher.SideOnly
 */
package eos.moe.armourers;

import eos.moe.armourers.gl;
import eos.moe.armourers.of;
import eos.moe.armourers.vk;
import java.util.ArrayList;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

/*
 * Duplicate member names - consider using --renamedupmembers true
 */
@SideOnly(value=Side.CLIENT)
public class oj {
    public of[] s;
    public boolean[] m;
    public long j;

    public oj(ArrayList<gl>[] a2) {
        int n2;
        oj a3;
        a3.s = new of[a2.length];
        a3.m = new boolean[a2.length];
        int n3 = n2 = 0;
        while (n3 < a3.s.length) {
            if (a2[n2].size() > 0) {
                oj oj2 = a3;
                oj2.s[n2] = new of();
                oj2.m[n2] = true;
            } else {
                a3.m[n2] = false;
            }
            n3 = ++n2;
        }
    }

    public void y() {
        a.j = System.currentTimeMillis();
    }

    public void r() {
        oj a2;
        int n2;
        int n3 = n2 = 0;
        while (n3 < a2.s.length) {
            if (a2.m[n2]) {
                a2.s[n2].y();
            }
            n3 = ++n2;
        }
    }

    public int r() {
        oj a2;
        long l2 = System.currentTimeMillis();
        if (l2 < a2.j + 500L) {
            return MathHelper.func_76125_a((int)(vk.c + 1 - ((int)((float)(l2 -= a2.j) / 125.0f) + 1)), (int)0, (int)(vk.c + 1));
        }
        return 0;
    }
}

