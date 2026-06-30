/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  aow$a
 *  bhb
 *  bvp
 *  bvr
 *  bvs
 *  bvt
 *  bvw
 *  bvx
 *  bwa
 *  bwc
 *  cdp
 *  cdq
 *  cfy
 *  cfz
 *  cgc
 *  cgd
 *  cgf
 *  et
 *  fa
 *  org.lwjgl.util.vector.Vector3f
 */
package net.optifine.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import net.optifine.model.ModelUtils;
import org.lwjgl.util.vector.Vector3f;

public class BlockModelUtils {
    private static final float VERTEX_COORD_ACCURACY = 1.0E-6f;

    public static cfy makeModelCube(String spriteName, int tintIndex) {
        cdq sprite = Config.getMinecraft().R().a(spriteName);
        return BlockModelUtils.makeModelCube(sprite, tintIndex);
    }

    public static cfy makeModelCube(cdq sprite, int tintIndex) {
        ArrayList generalQuads = new ArrayList();
        fa[] facings = fa.n;
        HashMap faceQuads = new HashMap();
        for (int i = 0; i < facings.length; ++i) {
            fa facing = facings[i];
            ArrayList<bvp> quads = new ArrayList<bvp>();
            quads.add(BlockModelUtils.makeBakedQuad(facing, sprite, tintIndex));
            faceQuads.put(facing, quads);
        }
        bwa itemOverrideList = new bwa(new ArrayList());
        cgf bakedModel = new cgf(generalQuads, faceQuads, true, true, sprite, bwc.a, itemOverrideList);
        return bakedModel;
    }

    public static cfy joinModelsCube(cfy modelBase, cfy modelAdd) {
        ArrayList generalQuads = new ArrayList();
        generalQuads.addAll(modelBase.a(null, null, 0L));
        generalQuads.addAll(modelAdd.a(null, null, 0L));
        fa[] facings = fa.n;
        HashMap faceQuads = new HashMap();
        for (int i = 0; i < facings.length; ++i) {
            fa facing = facings[i];
            ArrayList quads = new ArrayList();
            quads.addAll(modelBase.a(null, facing, 0L));
            quads.addAll(modelAdd.a(null, facing, 0L));
            faceQuads.put(facing, quads);
        }
        boolean ao = modelBase.a();
        boolean builtIn = modelBase.c();
        cdq sprite = modelBase.d();
        bwc transforms = modelBase.e();
        bwa itemOverrideList = modelBase.f();
        cgf bakedModel = new cgf(generalQuads, faceQuads, ao, builtIn, sprite, transforms, itemOverrideList);
        return bakedModel;
    }

    public static bvp makeBakedQuad(fa facing, cdq sprite, int tintIndex) {
        Vector3f posFrom = new Vector3f(0.0f, 0.0f, 0.0f);
        Vector3f posTo = new Vector3f(16.0f, 16.0f, 16.0f);
        bvt uv = new bvt(new float[]{0.0f, 0.0f, 16.0f, 16.0f}, 0);
        bvr face = new bvr(facing, tintIndex, "#" + facing.m(), uv);
        cfz modelRotation = cfz.a;
        bvs partRotation = null;
        boolean uvLocked = false;
        boolean shade = true;
        bvx faceBakery = new bvx();
        bvp quad = faceBakery.a(posFrom, posTo, face, sprite, facing, modelRotation, partRotation, uvLocked, shade);
        return quad;
    }

    public static cfy makeModel(String modelName, String spriteOldName, String spriteNewName) {
        cdp textureMap = Config.getMinecraft().R();
        cdq spriteOld = textureMap.getSpriteSafe(spriteOldName);
        cdq spriteNew = textureMap.getSpriteSafe(spriteNewName);
        return BlockModelUtils.makeModel(modelName, spriteOld, spriteNew);
    }

    public static cfy makeModel(String modelName, cdq spriteOld, cdq spriteNew) {
        if (spriteOld == null || spriteNew == null) {
            return null;
        }
        cgc modelManager = Config.getModelManager();
        if (modelManager == null) {
            return null;
        }
        cgd mrl = new cgd(modelName, "normal");
        cfy model = modelManager.a(mrl);
        if (model == null || model == modelManager.a()) {
            return null;
        }
        cfy modelNew = ModelUtils.duplicateModel(model);
        fa[] faces = fa.n;
        for (int i = 0; i < faces.length; ++i) {
            fa face = faces[i];
            List quads = modelNew.a(null, face, 0L);
            BlockModelUtils.replaceTexture(quads, spriteOld, spriteNew);
        }
        List quadsGeneral = modelNew.a(null, null, 0L);
        BlockModelUtils.replaceTexture(quadsGeneral, spriteOld, spriteNew);
        return modelNew;
    }

    private static void replaceTexture(List<bvp> quads, cdq spriteOld, cdq spriteNew) {
        ArrayList<bvp> quadsNew = new ArrayList<bvp>();
        for (bvp quad : quads) {
            if (quad.a() == spriteOld) {
                quad = new bvw(quad, spriteNew);
            }
            quadsNew.add(quad);
        }
        quads.clear();
        quads.addAll(quadsNew);
    }

    public static void snapVertexPosition(Vector3f pos) {
        pos.setX(BlockModelUtils.snapVertexCoord(pos.getX()));
        pos.setY(BlockModelUtils.snapVertexCoord(pos.getY()));
        pos.setZ(BlockModelUtils.snapVertexCoord(pos.getZ()));
    }

    private static float snapVertexCoord(float x) {
        if (x > -1.0E-6f && x < 1.0E-6f) {
            return 0.0f;
        }
        if (x > 0.999999f && x < 1.000001f) {
            return 1.0f;
        }
        return x;
    }

    public static bhb getOffsetBoundingBox(bhb aabb, aow.a offsetType, et pos) {
        int x = pos.p();
        int z = pos.r();
        long k = (long)(x * 3129871) ^ (long)z * 116129781L;
        k = k * k * 42317861L + k * 11L;
        double dx = ((double)((float)(k >> 16 & 0xFL) / 15.0f) - 0.5) * 0.5;
        double dz = ((double)((float)(k >> 24 & 0xFL) / 15.0f) - 0.5) * 0.5;
        double dy = 0.0;
        if (offsetType == aow.a.c) {
            dy = ((double)((float)(k >> 20 & 0xFL) / 15.0f) - 1.0) * 0.2;
        }
        return aabb.d(dx, dy, dz);
    }
}

