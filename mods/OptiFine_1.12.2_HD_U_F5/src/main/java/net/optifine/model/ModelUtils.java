/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  bvp
 *  cfy
 *  cgf
 *  fa
 */
package net.optifine.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ModelUtils {
    public static void dbgModel(cfy model) {
        if (model == null) {
            return;
        }
        Config.dbg("Model: " + model + ", ao: " + model.a() + ", gui3d: " + model.b() + ", builtIn: " + model.c() + ", particle: " + model.d());
        fa[] faces = fa.n;
        for (int i = 0; i < faces.length; ++i) {
            fa face = faces[i];
            List faceQuads = model.a(null, face, 0L);
            ModelUtils.dbgQuads(face.m(), faceQuads, "  ");
        }
        List generalQuads = model.a(null, null, 0L);
        ModelUtils.dbgQuads("General", generalQuads, "  ");
    }

    private static void dbgQuads(String name, List quads, String prefix) {
        for (bvp quad : quads) {
            ModelUtils.dbgQuad(name, quad, prefix);
        }
    }

    public static void dbgQuad(String name, bvp quad, String prefix) {
        Config.dbg(prefix + "Quad: " + quad.getClass().getName() + ", type: " + name + ", face: " + quad.e() + ", tint: " + quad.d() + ", sprite: " + quad.a());
        ModelUtils.dbgVertexData(quad.b(), "  " + prefix);
    }

    public static void dbgVertexData(int[] vd, String prefix) {
        int step = vd.length / 4;
        Config.dbg(prefix + "Length: " + vd.length + ", step: " + step);
        for (int i = 0; i < 4; ++i) {
            int pos = i * step;
            float x = Float.intBitsToFloat(vd[pos + 0]);
            float y = Float.intBitsToFloat(vd[pos + 1]);
            float z = Float.intBitsToFloat(vd[pos + 2]);
            int col = vd[pos + 3];
            float u = Float.intBitsToFloat(vd[pos + 4]);
            float v = Float.intBitsToFloat(vd[pos + 5]);
            Config.dbg(prefix + i + " xyz: " + x + "," + y + "," + z + " col: " + col + " u,v: " + u + "," + v);
        }
    }

    public static cfy duplicateModel(cfy model) {
        List generalQuads2 = ModelUtils.duplicateQuadList(model.a(null, null, 0L));
        fa[] faces = fa.n;
        HashMap<fa, List> faceQuads2 = new HashMap<fa, List>();
        for (int i = 0; i < faces.length; ++i) {
            fa face = faces[i];
            List quads = model.a(null, face, 0L);
            List quads2 = ModelUtils.duplicateQuadList(quads);
            faceQuads2.put(face, quads2);
        }
        cgf model2 = new cgf(generalQuads2, faceQuads2, model.a(), model.b(), model.d(), model.e(), model.f());
        return model2;
    }

    public static List duplicateQuadList(List list) {
        ArrayList<bvp> list2 = new ArrayList<bvp>();
        for (bvp quad : list) {
            bvp quad2 = ModelUtils.duplicateQuad(quad);
            list2.add(quad2);
        }
        return list2;
    }

    public static bvp duplicateQuad(bvp quad) {
        bvp quad2 = new bvp((int[])quad.b().clone(), quad.d(), quad.e(), quad.a());
        return quad2;
    }
}

