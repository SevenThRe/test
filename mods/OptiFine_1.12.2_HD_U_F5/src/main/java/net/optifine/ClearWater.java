/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  amu
 *  aom
 *  aow
 *  aox
 *  awt
 *  axr
 *  axt
 *  axw
 *  bcz
 *  bid
 *  et
 *  vg
 */
package net.optifine;

import net.optifine.BlockPosM;

public class ClearWater {
    public static void updateWaterOpacity(bid settings, amu world) {
        if (settings != null) {
            int opacity = 3;
            if (settings.ofClearWater) {
                opacity = 1;
            }
            aom.setLightOpacity((aow)aox.j, (int)opacity);
            aom.setLightOpacity((aow)aox.i, (int)opacity);
        }
        if (world == null) {
            return;
        }
        axr cp = world.B();
        if (cp == null) {
            return;
        }
        vg rve = Config.getMinecraft().aa();
        if (rve == null) {
            return;
        }
        int cViewX = (int)rve.p / 16;
        int cViewZ = (int)rve.r / 16;
        int cXMin = cViewX - 512;
        int cXMax = cViewX + 512;
        int cZMin = cViewZ - 512;
        int cZMax = cViewZ + 512;
        int countUpdated = 0;
        for (int cx = cXMin; cx < cXMax; ++cx) {
            for (int cz = cZMin; cz < cZMax; ++cz) {
                axw c2 = cp.a(cx, cz);
                if (c2 == null || c2 instanceof axt) continue;
                int x0 = cx << 4;
                int z0 = cz << 4;
                int x1 = x0 + 16;
                int z1 = z0 + 16;
                BlockPosM posXZ = new BlockPosM(0, 0, 0);
                BlockPosM posXYZ = new BlockPosM(0, 0, 0);
                for (int x = x0; x < x1; ++x) {
                    block3: for (int z = z0; z < z1; ++z) {
                        posXZ.setXyz(x, 0, z);
                        et posH = world.p((et)posXZ);
                        for (int y = 0; y < posH.q(); ++y) {
                            posXYZ.setXyz(x, y, z);
                            awt bs = world.o((et)posXYZ);
                            if (bs.a() != bcz.h) continue;
                            world.a(x, z, posXYZ.q(), posH.q());
                            ++countUpdated;
                            continue block3;
                        }
                    }
                }
            }
        }
        if (countUpdated > 0) {
            String threadName = "server";
            if (Config.isMinecraftThread()) {
                threadName = "client";
            }
            Config.dbg("ClearWater (" + threadName + ") relighted " + countUpdated + " chunks");
        }
    }
}

