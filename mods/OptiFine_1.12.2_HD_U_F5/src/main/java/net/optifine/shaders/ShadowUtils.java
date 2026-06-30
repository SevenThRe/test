/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  bsb
 *  bvh
 *  bxr
 *  et
 *  rk
 *  vg
 */
package net.optifine.shaders;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import net.optifine.shaders.IteratorRenderChunks;
import net.optifine.shaders.Shaders;

public class ShadowUtils {
    public static Iterator<bxr> makeShadowChunkIterator(bsb world, double partialTicks, vg viewEntity, int renderDistanceChunks, bvh viewFrustum) {
        float shadowRenderDistance = Shaders.getShadowRenderDistance();
        if (shadowRenderDistance <= 0.0f || shadowRenderDistance >= (float)((renderDistanceChunks - 1) * 16)) {
            List<bxr> listChunks = Arrays.asList(viewFrustum.f);
            Iterator<bxr> it = listChunks.iterator();
            return it;
        }
        int shadowDistanceChunks = rk.f((float)(shadowRenderDistance / 16.0f)) + 1;
        float car2 = world.d((float)partialTicks);
        float sunTiltRad = Shaders.sunPathRotation * rk.deg2Rad;
        float sar = car2 > rk.PId2 && car2 < 3.0f * rk.PId2 ? car2 + rk.PI : car2;
        float dx = -rk.a((float)sar);
        float dy = rk.b((float)sar) * rk.b((float)sunTiltRad);
        float dz = -rk.b((float)sar) * rk.a((float)sunTiltRad);
        et posEntity = new et(rk.c((double)viewEntity.p) >> 4, rk.c((double)viewEntity.q) >> 4, rk.c((double)viewEntity.r) >> 4);
        et posStart = posEntity.a((double)(-dx * (float)shadowDistanceChunks), (double)(-dy * (float)shadowDistanceChunks), (double)(-dz * (float)shadowDistanceChunks));
        et posEnd = posEntity.a((double)(dx * (float)renderDistanceChunks), (double)(dy * (float)renderDistanceChunks), (double)(dz * (float)renderDistanceChunks));
        IteratorRenderChunks it = new IteratorRenderChunks(viewFrustum, posStart, posEnd, shadowDistanceChunks, shadowDistanceChunks);
        return it;
    }
}

