/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.Entity
 */
package eos.moe.armourers;

import eos.moe.armourers.ModelData;
import eos.moe.armourers.bj;
import eos.moe.armourers.zg;
import java.util.List;
import net.minecraft.entity.Entity;

public class DragonArmourers {
    public static boolean renderSkin = true;

    public static void renderHead(Entity a2) {
        bj.z(a2);
    }

    public static void renderSkirt(Entity a2) {
        bj.x(a2);
    }

    public static void renderRightForeArm(Entity a2) {
        bj.h(a2);
    }

    public static void renderRightFeet(Entity a2) {
        bj.l(a2);
    }

    public static void renderRightForeLeg(Entity a2) {
        bj.a(a2);
    }

    public static void renderLeftForeLeg(Entity a2) {
        bj.v(a2);
    }

    public static void renderChest(Entity a2) {
        bj.s(a2);
    }

    public static void renderRightLeg(Entity a2) {
        bj.r(a2);
    }

    public static void renderLeftArm(Entity a2) {
        bj.f(a2);
    }

    public static void renderRightArm(Entity a2) {
        bj.k(a2);
    }

    public DragonArmourers() {
        DragonArmourers a2;
    }

    public static void renderLeftLeg(Entity a2) {
        bj.y(a2);
    }

    public static void renderWings(Entity a2) {
        bj.p(a2);
    }

    public static void renderLeftForeArm(Entity a2) {
        bj.w(a2);
    }

    public static void renderLeftFeet(Entity a2) {
        bj.n(a2);
    }

    public static List<ModelData> getEntityModelSkins(Entity a2) {
        return zg.r(a2);
    }
}

