/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  bvp
 *  cdq
 *  fa
 *  rk
 */
package net.optifine;

import java.util.IdentityHashMap;
import java.util.Map;

public class NaturalProperties {
    public int rotation = 1;
    public boolean flip = false;
    private Map[] quadMaps = new Map[8];

    public NaturalProperties(String type) {
        if (type.equals("4")) {
            this.rotation = 4;
            return;
        }
        if (type.equals("2")) {
            this.rotation = 2;
            return;
        }
        if (type.equals("F")) {
            this.flip = true;
            return;
        }
        if (type.equals("4F")) {
            this.rotation = 4;
            this.flip = true;
            return;
        }
        if (type.equals("2F")) {
            this.rotation = 2;
            this.flip = true;
            return;
        }
        Config.warn("NaturalTextures: Unknown type: " + type);
    }

    public boolean isValid() {
        if (this.rotation == 2 || this.rotation == 4) {
            return true;
        }
        return this.flip;
    }

    public synchronized bvp getQuad(bvp quadIn, int rotate, boolean flipU) {
        bvp quad;
        int index = rotate;
        if (flipU) {
            index |= 4;
        }
        if (index <= 0 || index >= this.quadMaps.length) {
            return quadIn;
        }
        IdentityHashMap<bvp, bvp> map = this.quadMaps[index];
        if (map == null) {
            this.quadMaps[index] = map = new IdentityHashMap<bvp, bvp>(1);
        }
        if ((quad = (bvp)map.get(quadIn)) == null) {
            quad = this.makeQuad(quadIn, rotate, flipU);
            map.put(quadIn, quad);
        }
        return quad;
    }

    private bvp makeQuad(bvp quad, int rotate, boolean flipU) {
        int[] vertexData = quad.b();
        int tintIndex = quad.d();
        fa face = quad.e();
        cdq sprite = quad.a();
        if (!this.isFullSprite(quad)) {
            rotate = 0;
        }
        vertexData = this.transformVertexData(vertexData, rotate, flipU);
        bvp bq = new bvp(vertexData, tintIndex, face, sprite);
        return bq;
    }

    private int[] transformVertexData(int[] vertexData, int rotate, boolean flipU) {
        int[] vertexData2 = (int[])vertexData.clone();
        int v2 = 4 - rotate;
        if (flipU) {
            v2 += 3;
        }
        v2 %= 4;
        int step = vertexData2.length / 4;
        for (int v = 0; v < 4; ++v) {
            int pos = v * step;
            int pos2 = v2 * step;
            vertexData2[pos2 + 4] = vertexData[pos + 4];
            vertexData2[pos2 + 4 + 1] = vertexData[pos + 4 + 1];
            if (flipU) {
                if (--v2 >= 0) continue;
                v2 = 3;
                continue;
            }
            if (++v2 <= 3) continue;
            v2 = 0;
        }
        return vertexData2;
    }

    private boolean isFullSprite(bvp quad) {
        cdq sprite = quad.a();
        float uMin = sprite.e();
        float uMax = sprite.f();
        float uSize = uMax - uMin;
        float uDelta = uSize / 256.0f;
        float vMin = sprite.g();
        float vMax = sprite.h();
        float vSize = vMax - vMin;
        float vDelta = vSize / 256.0f;
        int[] vertexData = quad.b();
        int step = vertexData.length / 4;
        for (int i = 0; i < 4; ++i) {
            int pos = i * step;
            float u = Float.intBitsToFloat(vertexData[pos + 4]);
            float v = Float.intBitsToFloat(vertexData[pos + 4 + 1]);
            if (!this.equalsDelta(u, uMin, uDelta) && !this.equalsDelta(u, uMax, uDelta)) {
                return false;
            }
            if (this.equalsDelta(v, vMin, vDelta) || this.equalsDelta(v, vMax, vDelta)) continue;
            return false;
        }
        return true;
    }

    private boolean equalsDelta(float x1, float x2, float deltaMax) {
        float deltaAbs = rk.e((float)(x1 - x2));
        return deltaAbs < deltaMax;
    }
}

