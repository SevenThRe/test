/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.player.EntityPlayer
 */
package eos.moe.armourers;

import eos.moe.armourers.in;
import eos.moe.armourers.vn;
import eos.moe.armourers.xl;
import eos.moe.armourers.yl;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;

public class bo {
    private /* synthetic */ bo() {
        bo a2;
    }

    public static double r(Entity a2, yl a3, int a4) {
        int n2;
        String string;
        double d2 = in.h.r(a3.r());
        double d3 = in.oa.r(a3.r());
        double d4 = in.u.r(a3.r());
        double d5 = in.k.r(a3.r());
        xl xl2 = xl.valueOf(in.m.r(a3.r()));
        if (a3.r() == vn.u && !(string = in.z.r(a3.r())).equals("") && (n2 = bo.r(string, a3, a4)) != -1) {
            d2 = in.h.r(a3.r(), n2);
            d3 = in.oa.r(a3.r(), n2);
            d4 = in.u.r(a3.r(), n2);
            d5 = in.k.r(a3.r(), n2);
            xl2 = xl.valueOf(in.m.r(a3.r(), n2));
        }
        double d6 = 0.0;
        if (a2 != null) {
            if (a2.isAirBorne) {
                if (a2 instanceof EntityPlayer) {
                    if (((EntityPlayer)a2).capabilities.isFlying) {
                        d4 = d5;
                    }
                } else {
                    d4 = d5;
                }
            }
            d6 = ((double)System.currentTimeMillis() + (double)a2.getEntityId()) % d4;
            if (xl2 == xl.m) {
                d6 = Math.sin(d6 / d4 * Math.PI * 2.0);
            }
            if (xl2 == xl.j) {
                d6 /= d4;
            }
        }
        d2 -= d3;
        if (xl2 == xl.j) {
            return d2 * d6;
        }
        return -d3 - d2 * ((d6 + 1.0) / 2.0);
    }

    private static /* synthetic */ int r(String a2, yl a3, int a4) {
        int n2;
        a3 = a2.split(":");
        int n3 = n2 = 0;
        while (n3 < a3.length) {
            int n4 = Integer.parseInt(a3[n2]);
            if (a4 < n4) {
                return n2;
            }
            n3 = ++n2;
        }
        return -1;
    }
}

