/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  amn
 *  rk
 */
package net.optifine;

import java.util.Comparator;

public class ChunkPosComparator
implements Comparator<amn> {
    private int chunkPosX;
    private int chunkPosZ;
    private double yawRad;
    private double pitchNorm;

    public ChunkPosComparator(int chunkPosX, int chunkPosZ, double yawRad, double pitchRad) {
        this.chunkPosX = chunkPosX;
        this.chunkPosZ = chunkPosZ;
        this.yawRad = yawRad;
        this.pitchNorm = 1.0 - rk.a((double)(Math.abs(pitchRad) / 1.5707963267948966), (double)0.0, (double)1.0);
    }

    @Override
    public int compare(amn cp1, amn cp2) {
        int distSq1 = this.getDistSq(cp1);
        int distSq2 = this.getDistSq(cp2);
        return distSq1 - distSq2;
    }

    private int getDistSq(amn cp) {
        int dx = cp.a - this.chunkPosX;
        int dz = cp.b - this.chunkPosZ;
        int distSq = dx * dx + dz * dz;
        double yaw = rk.c((double)dz, (double)dx);
        double dYaw = Math.abs(yaw - this.yawRad);
        if (dYaw > Math.PI) {
            dYaw = Math.PI * 2 - dYaw;
        }
        distSq = (int)((double)distSq * (1000.0 * (this.pitchNorm * dYaw * dYaw)));
        return distSq;
    }
}

