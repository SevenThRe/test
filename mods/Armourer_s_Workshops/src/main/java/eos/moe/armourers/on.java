/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.item.ItemStack
 */
package eos.moe.armourers;

import eos.moe.armourers.ModelData;
import eos.moe.armourers.in;
import eos.moe.armourers.yl;
import java.util.Arrays;
import java.util.HashMap;
import java.util.UUID;
import net.minecraft.item.ItemStack;

/*
 * Duplicate member names - consider using --renamedupmembers true
 */
public class on {
    public boolean h;
    public boolean a;
    public boolean e;
    public boolean b;
    public boolean g;
    public boolean z;
    public boolean t;
    public boolean w;
    public boolean r;
    public boolean l;
    public static HashMap<UUID, on> c = new HashMap();
    public boolean v;
    public boolean s;
    public ItemStack[] m;
    public boolean j;

    public ItemStack[] r() {
        on a2;
        return a2.m;
    }

    public static on y(UUID a2) {
        return c.getOrDefault(a2, new on());
    }

    public on() {
        on a2;
        on on2 = a2;
        on on3 = a2;
        on on4 = a2;
        on on5 = a2;
        on on6 = a2;
        on on7 = a2;
        on on8 = a2;
        on8.s = false;
        on8.b = false;
        on7.g = false;
        on7.e = false;
        on6.w = false;
        on6.v = false;
        on5.h = false;
        on5.z = false;
        on4.l = false;
        on4.r = false;
        on3.j = false;
        on3.t = false;
        on2.a = false;
        on2.m = new ItemStack[4];
        Arrays.fill(a2.m, ItemStack.EMPTY);
    }

    public void r(yl a2) {
        if (a2 == null) {
            return;
        }
        if (in.t.r(a2.r()).booleanValue()) {
            a.s = true;
        }
        if (in.b.r(a2.r()).booleanValue()) {
            a.b = true;
        }
        if (in.f.r(a2.r()).booleanValue()) {
            a.g = true;
        }
        if (in.n.r(a2.r()).booleanValue()) {
            a.e = true;
        }
        if (in.r.r(a2.r()).booleanValue()) {
            a.w = true;
        }
        if (in.o.r(a2.r()).booleanValue()) {
            a.v = true;
        }
        if (in.w.r(a2.r()).booleanValue()) {
            a.h = true;
        }
        if (in.ra.r(a2.r()).booleanValue()) {
            a.z = true;
        }
        if (in.c.r(a2.r()).booleanValue()) {
            a.l = true;
        }
        if (in.d.r(a2.r()).booleanValue()) {
            a.r = true;
        }
        if (in.x.r(a2.r()).booleanValue()) {
            a.j = true;
        }
        if (in.ua.r(a2.r()).booleanValue()) {
            a.t = true;
        }
    }

    public String toString() {
        on a2;
        return new StringBuilder().insert(0, "DragonSkinCap{hideHead=").append(a2.s).append(", hideChest=").append(a2.b).append(", hideArmLeft=").append(a2.g).append(", hideArmRight=").append(a2.e).append(", hideLegLeft=").append(a2.w).append(", hideLegRight=").append(a2.v).append(", hideHeadOverlay=").append(a2.h).append(", hideChestOverlay=").append(a2.z).append(", hideArmLeftOverlay=").append(a2.l).append(", hideArmRightOverlay=").append(a2.r).append(", hideLegLeftOverlay=").append(a2.j).append(", hideLegRightOverlay=").append(a2.t).append('}').toString();
    }

    public void r(boolean a2) {
        a.a = a2;
    }

    public void r(ModelData a2) {
        if (a2 == null) {
            return;
        }
        if (a2.getSetting("hideHead")) {
            a.s = true;
        }
        if (a2.getSetting("hideChest")) {
            a.b = true;
        }
        if (a2.getSetting("hideArmLeft")) {
            a.g = true;
        }
        if (a2.getSetting("hideArmRight")) {
            a.e = true;
        }
        if (a2.getSetting("hideLegLeft")) {
            a.w = true;
        }
        if (a2.getSetting("hideLegRight")) {
            a.v = true;
        }
        if (a2.getSetting("hideHeadOverlay")) {
            a.h = true;
        }
        if (a2.getSetting("hideChestOverlay")) {
            a.z = true;
        }
        if (a2.getSetting("hideArmLeftOverlay")) {
            a.l = true;
        }
        if (a2.getSetting("hideArmRightOverlay")) {
            a.r = true;
        }
        if (a2.getSetting("hideLegLeftOverlay")) {
            a.j = true;
        }
        if (a2.getSetting("hideLegRightOverlay")) {
            a.t = true;
        }
    }

    public static on r(UUID a2) {
        c.putIfAbsent(a2, new on());
        return c.get(a2);
    }

    public void r() {
        on a2;
        on on2 = a2;
        on on3 = a2;
        on on4 = a2;
        on on5 = a2;
        on on6 = a2;
        on on7 = a2;
        a2.s = false;
        on7.b = false;
        on7.g = false;
        on6.e = false;
        on6.w = false;
        on5.v = false;
        on5.h = false;
        on4.z = false;
        on4.l = false;
        on3.r = false;
        on3.j = false;
        on2.t = false;
        on2.a = false;
    }
}

