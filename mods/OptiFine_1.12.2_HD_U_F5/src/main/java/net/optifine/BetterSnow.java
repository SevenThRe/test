/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  amy
 *  aow
 *  aox
 *  apc
 *  aqb
 *  aqo
 *  aqp
 *  aqr
 *  aqs
 *  art
 *  art$a
 *  asb
 *  ath
 *  ati
 *  atp
 *  atw
 *  aun
 *  auo
 *  auq
 *  auv
 *  awt
 *  axj
 *  cfy
 *  et
 *  fa
 */
package net.optifine;

public class BetterSnow {
    private static cfy modelSnowLayer = null;

    public static void update() {
        modelSnowLayer = Config.getMinecraft().ab().a().b(aox.aH.t());
    }

    public static cfy getModelSnowLayer() {
        return modelSnowLayer;
    }

    public static awt getStateSnowLayer() {
        return aox.aH.t();
    }

    public static boolean shouldRender(amy blockAccess, awt blockState, et blockPos) {
        aow block = blockState.u();
        if (!BetterSnow.checkBlock(block, blockState)) {
            return false;
        }
        return BetterSnow.hasSnowNeighbours(blockAccess, blockPos);
    }

    private static boolean hasSnowNeighbours(amy blockAccess, et pos) {
        aow blockSnow = aox.aH;
        if (blockAccess.o(pos.c()).u() == blockSnow || blockAccess.o(pos.d()).u() == blockSnow || blockAccess.o(pos.e()).u() == blockSnow || blockAccess.o(pos.f()).u() == blockSnow) {
            return blockAccess.o(pos.b()).p();
        }
        return false;
    }

    private static boolean checkBlock(aow block, awt blockState) {
        Comparable orient;
        if (blockState.g()) {
            return false;
        }
        if (blockState.p()) {
            return false;
        }
        if (block instanceof atw) {
            return false;
        }
        if (block instanceof apc && (block instanceof aqb || block instanceof aqr || block instanceof asb || block instanceof atp || block instanceof aun)) {
            return true;
        }
        if (block instanceof aqo || block instanceof aqp || block instanceof aqs || block instanceof auo || block instanceof ati || block instanceof auv) {
            return true;
        }
        if (block instanceof ath && blockState.c((axj)auq.a) == fa.b) {
            return true;
        }
        return block instanceof art && ((orient = blockState.c((axj)art.a)) == art.a.g || orient == art.a.f);
    }
}

