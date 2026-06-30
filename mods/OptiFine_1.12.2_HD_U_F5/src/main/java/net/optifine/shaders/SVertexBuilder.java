/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  amy
 *  aow
 *  awp
 *  awt
 *  buk
 *  bus
 *  cea
 *  et
 *  org.lwjgl.opengl.GL20
 */
package net.optifine.shaders;

import java.nio.ByteBuffer;
import java.nio.FloatBuffer;
import java.nio.IntBuffer;
import net.optifine.shaders.BlockAliases;
import net.optifine.shaders.Shaders;
import org.lwjgl.opengl.GL20;

public class SVertexBuilder {
    int vertexSize;
    int offsetNormal;
    int offsetUV;
    int offsetUVCenter;
    boolean hasNormal;
    boolean hasTangent;
    boolean hasUV;
    boolean hasUVCenter;
    long[] entityData = new long[10];
    int entityDataIndex = 0;

    public SVertexBuilder() {
        this.entityData[this.entityDataIndex] = 0L;
    }

    public static void initVertexBuilder(buk wrr) {
        wrr.sVertexBuilder = new SVertexBuilder();
    }

    public void pushEntity(long data) {
        ++this.entityDataIndex;
        this.entityData[this.entityDataIndex] = data;
    }

    public void popEntity() {
        this.entityData[this.entityDataIndex] = 0L;
        --this.entityDataIndex;
    }

    public static void pushEntity(awt blockState, et blockPos, amy blockAccess, buk wrr) {
        int metadata;
        int blockId;
        aow block = blockState.u();
        if (blockState instanceof awp) {
            awp bsb2 = (awp)blockState;
            blockId = bsb2.getBlockId();
            metadata = bsb2.getMetadata();
        } else {
            blockId = aow.a((aow)block);
            metadata = block.e(blockState);
        }
        int blockAliasId = BlockAliases.getBlockAliasId(blockId, metadata);
        if (blockAliasId >= 0) {
            blockId = blockAliasId;
        }
        int renderType = block.a(blockState).ordinal();
        int dataLo = ((renderType & 0xFFFF) << 16) + (blockId & 0xFFFF);
        int dataHi = metadata & 0xFFFF;
        wrr.sVertexBuilder.pushEntity(((long)dataHi << 32) + (long)dataLo);
    }

    public static void popEntity(buk wrr) {
        wrr.sVertexBuilder.popEntity();
    }

    public static boolean popEntity(boolean value, buk wrr) {
        wrr.sVertexBuilder.popEntity();
        return value;
    }

    public static void endSetVertexFormat(buk wrr) {
        SVertexBuilder svb = wrr.sVertexBuilder;
        cea vf = wrr.g();
        svb.vertexSize = vf.g() / 4;
        svb.hasTangent = svb.hasNormal = vf.b();
        svb.hasUV = vf.a(0);
        svb.offsetNormal = svb.hasNormal ? vf.c() / 4 : 0;
        svb.offsetUV = svb.hasUV ? vf.b(0) / 4 : 0;
        svb.offsetUVCenter = 8;
    }

    public static void beginAddVertex(buk wrr) {
        if (wrr.f == 0) {
            SVertexBuilder.endSetVertexFormat(wrr);
        }
    }

    public static void endAddVertex(buk wrr) {
        SVertexBuilder svb = wrr.sVertexBuilder;
        if (svb.vertexSize == 14) {
            if (wrr.j == 7 && wrr.f % 4 == 0) {
                svb.calcNormal(wrr, wrr.j() - 4 * svb.vertexSize);
            }
            long eData = svb.entityData[svb.entityDataIndex];
            int pos = wrr.j() - 14 + 12;
            wrr.c.put(pos, (int)eData);
            wrr.c.put(pos + 1, (int)(eData >> 32));
        }
    }

    public static void beginAddVertexData(buk wrr, int[] data) {
        if (wrr.f == 0) {
            SVertexBuilder.endSetVertexFormat(wrr);
        }
        SVertexBuilder svb = wrr.sVertexBuilder;
        if (svb.vertexSize == 14) {
            long eData = svb.entityData[svb.entityDataIndex];
            int pos = 12;
            while (pos + 1 < data.length) {
                data[pos] = (int)eData;
                data[pos + 1] = (int)(eData >> 32);
                pos += 14;
            }
        }
    }

    public static void beginAddVertexData(buk wrr, ByteBuffer byteBuffer) {
        if (wrr.f == 0) {
            SVertexBuilder.endSetVertexFormat(wrr);
        }
        SVertexBuilder svb = wrr.sVertexBuilder;
        if (svb.vertexSize == 14) {
            long eData = svb.entityData[svb.entityDataIndex];
            int dataLengthInt = byteBuffer.limit() / 4;
            int posInt = 12;
            while (posInt + 1 < dataLengthInt) {
                int dataInt0 = (int)eData;
                int dataInt1 = (int)(eData >> 32);
                byteBuffer.putInt(posInt * 4, dataInt0);
                byteBuffer.putInt((posInt + 1) * 4, dataInt1);
                posInt += 14;
            }
        }
    }

    public static void endAddVertexData(buk wrr) {
        SVertexBuilder svb = wrr.sVertexBuilder;
        if (svb.vertexSize == 14 && wrr.j == 7 && wrr.f % 4 == 0) {
            svb.calcNormal(wrr, wrr.j() - 4 * svb.vertexSize);
        }
    }

    public void calcNormal(buk wrr, int baseIndex) {
        SVertexBuilder svb = this;
        FloatBuffer floatBuffer = wrr.e;
        IntBuffer intBuffer = wrr.c;
        int rbi = wrr.j();
        float v0x = floatBuffer.get(baseIndex + 0 * this.vertexSize);
        float v0y = floatBuffer.get(baseIndex + 0 * this.vertexSize + 1);
        float v0z = floatBuffer.get(baseIndex + 0 * this.vertexSize + 2);
        float v0u = floatBuffer.get(baseIndex + 0 * this.vertexSize + this.offsetUV);
        float v0v = floatBuffer.get(baseIndex + 0 * this.vertexSize + this.offsetUV + 1);
        float v1x = floatBuffer.get(baseIndex + 1 * this.vertexSize);
        float v1y = floatBuffer.get(baseIndex + 1 * this.vertexSize + 1);
        float v1z = floatBuffer.get(baseIndex + 1 * this.vertexSize + 2);
        float v1u = floatBuffer.get(baseIndex + 1 * this.vertexSize + this.offsetUV);
        float v1v = floatBuffer.get(baseIndex + 1 * this.vertexSize + this.offsetUV + 1);
        float v2x = floatBuffer.get(baseIndex + 2 * this.vertexSize);
        float v2y = floatBuffer.get(baseIndex + 2 * this.vertexSize + 1);
        float v2z = floatBuffer.get(baseIndex + 2 * this.vertexSize + 2);
        float v2u = floatBuffer.get(baseIndex + 2 * this.vertexSize + this.offsetUV);
        float v2v = floatBuffer.get(baseIndex + 2 * this.vertexSize + this.offsetUV + 1);
        float v3x = floatBuffer.get(baseIndex + 3 * this.vertexSize);
        float v3y = floatBuffer.get(baseIndex + 3 * this.vertexSize + 1);
        float v3z = floatBuffer.get(baseIndex + 3 * this.vertexSize + 2);
        float v3u = floatBuffer.get(baseIndex + 3 * this.vertexSize + this.offsetUV);
        float v3v = floatBuffer.get(baseIndex + 3 * this.vertexSize + this.offsetUV + 1);
        float y1 = v2y - v0y;
        float z2 = v3z - v1z;
        float y2 = v3y - v1y;
        float z1 = v2z - v0z;
        float vnx = y1 * z2 - y2 * z1;
        float x2 = v3x - v1x;
        float x1 = v2x - v0x;
        float vny = z1 * x2 - z2 * x1;
        float vnz = x1 * y2 - x2 * y1;
        float lensq = vnx * vnx + vny * vny + vnz * vnz;
        float mult = (double)lensq != 0.0 ? (float)(1.0 / Math.sqrt(lensq)) : 1.0f;
        vnx *= mult;
        vny *= mult;
        vnz *= mult;
        x1 = v1x - v0x;
        y1 = v1y - v0y;
        z1 = v1z - v0z;
        float u1 = v1u - v0u;
        float v1 = v1v - v0v;
        x2 = v2x - v0x;
        y2 = v2y - v0y;
        z2 = v2z - v0z;
        float u2 = v2u - v0u;
        float v2 = v2v - v0v;
        float d2 = u1 * v2 - u2 * v1;
        float r = d2 != 0.0f ? 1.0f / d2 : 1.0f;
        float tan1x = (v2 * x1 - v1 * x2) * r;
        float tan1y = (v2 * y1 - v1 * y2) * r;
        float tan1z = (v2 * z1 - v1 * z2) * r;
        float tan2x = (u1 * x2 - u2 * x1) * r;
        float tan2y = (u1 * y2 - u2 * y1) * r;
        float tan2z = (u1 * z2 - u2 * z1) * r;
        lensq = tan1x * tan1x + tan1y * tan1y + tan1z * tan1z;
        mult = (double)lensq != 0.0 ? (float)(1.0 / Math.sqrt(lensq)) : 1.0f;
        tan1x *= mult;
        tan1y *= mult;
        tan1z *= mult;
        lensq = tan2x * tan2x + tan2y * tan2y + tan2z * tan2z;
        mult = (double)lensq != 0.0 ? (float)(1.0 / Math.sqrt(lensq)) : 1.0f;
        float tan3x = vnz * tan1y - vny * tan1z;
        float tan3y = vnx * tan1z - vnz * tan1x;
        float tan3z = vny * tan1x - vnx * tan1y;
        float tan1w = (tan2x *= mult) * tan3x + (tan2y *= mult) * tan3y + (tan2z *= mult) * tan3z < 0.0f ? -1.0f : 1.0f;
        int bnx = (int)(vnx * 127.0f) & 0xFF;
        int bny = (int)(vny * 127.0f) & 0xFF;
        int bnz = (int)(vnz * 127.0f) & 0xFF;
        int packedNormal = (bnz << 16) + (bny << 8) + bnx;
        intBuffer.put(baseIndex + 0 * this.vertexSize + this.offsetNormal, packedNormal);
        intBuffer.put(baseIndex + 1 * this.vertexSize + this.offsetNormal, packedNormal);
        intBuffer.put(baseIndex + 2 * this.vertexSize + this.offsetNormal, packedNormal);
        intBuffer.put(baseIndex + 3 * this.vertexSize + this.offsetNormal, packedNormal);
        int packedTan1xy = ((int)(tan1x * 32767.0f) & 0xFFFF) + (((int)(tan1y * 32767.0f) & 0xFFFF) << 16);
        int packedTan1zw = ((int)(tan1z * 32767.0f) & 0xFFFF) + (((int)(tan1w * 32767.0f) & 0xFFFF) << 16);
        intBuffer.put(baseIndex + 0 * this.vertexSize + 10, packedTan1xy);
        intBuffer.put(baseIndex + 0 * this.vertexSize + 10 + 1, packedTan1zw);
        intBuffer.put(baseIndex + 1 * this.vertexSize + 10, packedTan1xy);
        intBuffer.put(baseIndex + 1 * this.vertexSize + 10 + 1, packedTan1zw);
        intBuffer.put(baseIndex + 2 * this.vertexSize + 10, packedTan1xy);
        intBuffer.put(baseIndex + 2 * this.vertexSize + 10 + 1, packedTan1zw);
        intBuffer.put(baseIndex + 3 * this.vertexSize + 10, packedTan1xy);
        intBuffer.put(baseIndex + 3 * this.vertexSize + 10 + 1, packedTan1zw);
        float midU = (v0u + v1u + v2u + v3u) / 4.0f;
        float midV = (v0v + v1v + v2v + v3v) / 4.0f;
        floatBuffer.put(baseIndex + 0 * this.vertexSize + 8, midU);
        floatBuffer.put(baseIndex + 0 * this.vertexSize + 8 + 1, midV);
        floatBuffer.put(baseIndex + 1 * this.vertexSize + 8, midU);
        floatBuffer.put(baseIndex + 1 * this.vertexSize + 8 + 1, midV);
        floatBuffer.put(baseIndex + 2 * this.vertexSize + 8, midU);
        floatBuffer.put(baseIndex + 2 * this.vertexSize + 8 + 1, midV);
        floatBuffer.put(baseIndex + 3 * this.vertexSize + 8, midU);
        floatBuffer.put(baseIndex + 3 * this.vertexSize + 8 + 1, midV);
    }

    public static void calcNormalChunkLayer(buk wrr) {
        if (wrr.g().b() && wrr.j == 7 && wrr.f % 4 == 0) {
            SVertexBuilder svb = wrr.sVertexBuilder;
            SVertexBuilder.endSetVertexFormat(wrr);
            int indexEnd = wrr.f * svb.vertexSize;
            for (int index = 0; index < indexEnd; index += svb.vertexSize * 4) {
                svb.calcNormal(wrr, index);
            }
        }
    }

    public static void drawArrays(int drawMode, int first, int count, buk wrr) {
        if (count != 0) {
            cea vf = wrr.g();
            int vertexSizeByte = vf.g();
            if (vertexSizeByte == 56) {
                ByteBuffer bb = wrr.f();
                bb.position(32);
                GL20.glVertexAttribPointer((int)Shaders.midTexCoordAttrib, (int)2, (int)5126, (boolean)false, (int)vertexSizeByte, (ByteBuffer)bb);
                bb.position(40);
                GL20.glVertexAttribPointer((int)Shaders.tangentAttrib, (int)4, (int)5122, (boolean)false, (int)vertexSizeByte, (ByteBuffer)bb);
                bb.position(48);
                GL20.glVertexAttribPointer((int)Shaders.entityAttrib, (int)3, (int)5122, (boolean)false, (int)vertexSizeByte, (ByteBuffer)bb);
                bb.position(0);
                GL20.glEnableVertexAttribArray((int)Shaders.midTexCoordAttrib);
                GL20.glEnableVertexAttribArray((int)Shaders.tangentAttrib);
                GL20.glEnableVertexAttribArray((int)Shaders.entityAttrib);
                bus.f((int)drawMode, (int)first, (int)count);
                GL20.glDisableVertexAttribArray((int)Shaders.midTexCoordAttrib);
                GL20.glDisableVertexAttribArray((int)Shaders.tangentAttrib);
                GL20.glDisableVertexAttribArray((int)Shaders.entityAttrib);
            } else {
                bus.f((int)drawMode, (int)first, (int)count);
            }
        }
    }
}

