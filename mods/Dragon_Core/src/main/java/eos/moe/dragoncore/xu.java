/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.model.PositionTextureVertex
 *  net.minecraft.client.renderer.BufferBuilder
 */
package eos.moe.dragoncore;

import eos.moe.dragoncore.acf;
import eos.moe.dragoncore.ag;
import eos.moe.dragoncore.bax;
import eos.moe.dragoncore.jq;
import eos.moe.dragoncore.jv;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import net.minecraft.client.model.PositionTextureVertex;
import net.minecraft.client.renderer.BufferBuilder;

public class xu
implements Serializable {
    private static final long q = 1907736523089263403L;
    public final bax b;
    public final bax o;
    private final jq[] y;
    private String k;
    private static String ALLATORIxDEMO;

    public void ALLATORIxDEMO(String a2) {
        a.k = a2;
    }

    public xu(bax a2, bax a3, acf a4, float a5, boolean a6, int a7, int a8) {
        xu a9;
        float a10 = a3.getX();
        float a11 = a3.getY();
        float a12 = a3.getZ();
        a3.set(a3.getX() == 0.0f ? 0.008f : a3.getX(), a3.getY() == 0.0f ? 0.008f : a3.getY(), a3.getZ() == 0.0f ? 0.008f : a3.getZ());
        float a13 = a2.getX();
        float a14 = a2.getY();
        float a15 = a2.getZ();
        float a16 = a4.getX();
        float a17 = a4.getY();
        ArrayList<jq> a18 = new ArrayList<jq>(6);
        a9.b = new bax(a13, a14, a15);
        a9.o = new bax(a13 + a3.getX(), a14 + a3.getY(), a15 + a3.getZ());
        float a19 = a9.o.getX() + a5;
        float a20 = a9.o.getY() + a5;
        float a21 = a9.o.getZ() + a5;
        a13 -= a5;
        a14 -= a5;
        a15 -= a5;
        if (a6) {
            float a22 = a19;
            a19 = a13;
            a13 = a22;
        }
        a10 = a10 < 1.0f ? 0.008f : (float)((int)a10);
        a12 = a12 < 1.0f ? 0.008f : (float)((int)a12);
        a11 = a11 < 1.0f ? 0.008f : (float)((int)a11);
        PositionTextureVertex a23 = new PositionTextureVertex(a13, a14, a15, 0.0f, 0.0f);
        PositionTextureVertex a24 = new PositionTextureVertex(a19, a14, a15, 0.0f, 8.0f);
        PositionTextureVertex a25 = new PositionTextureVertex(a19, a20, a15, 8.0f, 8.0f);
        PositionTextureVertex a26 = new PositionTextureVertex(a13, a20, a15, 8.0f, 0.0f);
        PositionTextureVertex a27 = new PositionTextureVertex(a13, a14, a21, 0.0f, 0.0f);
        PositionTextureVertex a28 = new PositionTextureVertex(a19, a14, a21, 0.0f, 8.0f);
        PositionTextureVertex a29 = new PositionTextureVertex(a19, a20, a21, 8.0f, 8.0f);
        PositionTextureVertex a30 = new PositionTextureVertex(a13, a20, a21, 8.0f, 0.0f);
        if (a12 != 0.0f && a11 != 0.0f) {
            a18.add(new jq(null, new PositionTextureVertex[]{a28, a24, a25, a29}, a16 + a12 + a10, a17 + a12, a16 + a12 + a10 + a12, a17 + a12 + a11, a7, a8));
            a18.add(new jq(null, new PositionTextureVertex[]{a23, a27, a30, a26}, a16, a17 + a12, a16 + a12, a17 + a12 + a11, a7, a8));
        }
        if (a10 != 0.0f && a12 != 0.0f) {
            a18.add(new jq(null, new PositionTextureVertex[]{a28, a27, a23, a24}, a16 + a12, a17, a16 + a12 + a10, a17 + a12, a7, a8));
            a18.add(new jq(null, new PositionTextureVertex[]{a25, a26, a30, a29}, a16 + a12 + a10, a17 + a12, a16 + a12 + a10 + a10, a17, a7, a8));
        }
        if (a10 != 0.0f && a11 != 0.0f) {
            a18.add(new jq(null, new PositionTextureVertex[]{a24, a23, a26, a25}, a16 + a12, a17 + a12, a16 + a12 + a10, a17 + a12 + a11, a7, a8));
            a18.add(new jq(null, new PositionTextureVertex[]{a27, a28, a29, a30}, a16 + a12 + a10 + a12, a17 + a12, a16 + a12 + a10 + a12 + a10, a17 + a12 + a11, a7, a8));
        }
        if (a6) {
            for (jq a31 : a18) {
                a31.func_78235_a();
            }
        }
        a9.y = a18.toArray(new jq[0]);
    }

    public xu(jv a2, bax a3, bax a4, List<ag> a5, float a6, boolean a7, int a8, int a9) {
        xu a10;
        float a11 = a4.getX();
        float a12 = a4.getY();
        float a13 = a4.getZ();
        a4.set(a4.getX() == 0.0f ? 0.008f : a4.getX(), a4.getY() == 0.0f ? 0.008f : a4.getY(), a4.getZ() == 0.0f ? 0.008f : a4.getZ());
        float a14 = a3.getX();
        float a15 = a3.getY();
        float a16 = a3.getZ();
        ArrayList<jq> a17 = new ArrayList<jq>(6);
        a10.b = new bax(a14, a15, a16);
        a10.o = new bax(a14 + a4.getX(), a15 + a4.getY(), a16 + a4.getZ());
        float a18 = a10.o.getX() + a6;
        float a19 = a10.o.getY() + a6;
        float a20 = a10.o.getZ() + a6;
        a14 -= a6;
        a15 -= a6;
        a16 -= a6;
        if (a7) {
            float a21 = a18;
            a18 = a14;
            a14 = a21;
        }
        PositionTextureVertex a22 = new PositionTextureVertex(a14, a15, a16, 0.0f, 0.0f);
        PositionTextureVertex a23 = new PositionTextureVertex(a18, a15, a16, 0.0f, 8.0f);
        PositionTextureVertex a24 = new PositionTextureVertex(a18, a19, a16, 8.0f, 8.0f);
        PositionTextureVertex a25 = new PositionTextureVertex(a14, a19, a16, 8.0f, 0.0f);
        PositionTextureVertex a26 = new PositionTextureVertex(a14, a15, a20, 0.0f, 0.0f);
        PositionTextureVertex a27 = new PositionTextureVertex(a18, a15, a20, 0.0f, 8.0f);
        PositionTextureVertex a28 = new PositionTextureVertex(a18, a19, a20, 8.0f, 8.0f);
        PositionTextureVertex a29 = new PositionTextureVertex(a14, a19, a20, 8.0f, 0.0f);
        if (a5.get((int)3).b().x != 0.0f && a5.get((int)3).b().y != 0.0f) {
            a17.add(new jq(a5.get(3).getTexture(), new PositionTextureVertex[]{a27, a23, a24, a28}, a5.get((int)3).a().x, a5.get((int)3).a().y, a5.get((int)3).a().x + a5.get((int)3).b().x, a5.get((int)3).a().y + a5.get((int)3).b().y, a8, a9));
            if (a5.get(3).getTexture() != null) {
                a2.ALLATORIxDEMO = true;
            }
        }
        if (a5.get((int)1).b().x != 0.0f && a5.get((int)1).b().y != 0.0f) {
            a17.add(new jq(a5.get(1).getTexture(), new PositionTextureVertex[]{a22, a26, a29, a25}, a5.get((int)1).a().x, a5.get((int)1).a().y, a5.get((int)1).a().x + a5.get((int)1).b().x, a5.get((int)1).a().y + a5.get((int)1).b().y, a8, a9));
            if (a5.get(1).getTexture() != null) {
                a2.ALLATORIxDEMO = true;
            }
        }
        if (a5.get((int)4).b().x != 0.0f && a5.get((int)4).b().y != 0.0f) {
            a17.add(new jq(a5.get(4).getTexture(), new PositionTextureVertex[]{a27, a26, a22, a23}, a5.get((int)4).a().x, a5.get((int)4).a().y, a5.get((int)4).a().x + a5.get((int)4).b().x, a5.get((int)4).a().y + a5.get((int)4).b().y, a8, a9));
            if (a5.get(4).getTexture() != null) {
                a2.ALLATORIxDEMO = true;
            }
        }
        if (a5.get((int)5).b().x != 0.0f && a5.get((int)5).b().y != 0.0f) {
            a17.add(new jq(a5.get(5).getTexture(), new PositionTextureVertex[]{a24, a25, a29, a28}, a5.get((int)5).a().x, a5.get((int)5).a().y, a5.get((int)5).a().x + a5.get((int)5).b().x, a5.get((int)5).a().y + a5.get((int)5).b().y, a8, a9));
            if (a5.get(5).getTexture() != null) {
                a2.ALLATORIxDEMO = true;
            }
        }
        if (a5.get((int)0).b().x != 0.0f && a5.get((int)0).b().y != 0.0f) {
            a17.add(new jq(a5.get(0).getTexture(), new PositionTextureVertex[]{a23, a22, a25, a24}, a5.get((int)0).a().x, a5.get((int)0).a().y, a5.get((int)0).a().x + a5.get((int)0).b().x, a5.get((int)0).a().y + a5.get((int)0).b().y, a8, a9));
            if (a5.get(0).getTexture() != null) {
                a2.ALLATORIxDEMO = true;
            }
        }
        if (a5.get((int)2).b().x != 0.0f && a5.get((int)2).b().y != 0.0f) {
            a17.add(new jq(a5.get(2).getTexture(), new PositionTextureVertex[]{a26, a27, a28, a29}, a5.get((int)2).a().x, a5.get((int)2).a().y, a5.get((int)2).a().x + a5.get((int)2).b().x, a5.get((int)2).a().y + a5.get((int)2).b().y, a8, a9));
            if (a5.get(2).getTexture() != null) {
                a2.ALLATORIxDEMO = true;
            }
        }
        if (a7) {
            for (jq a30 : a17) {
                a30.func_78235_a();
            }
        }
        a10.y = a17.toArray(new jq[0]);
    }

    public void ALLATORIxDEMO(BufferBuilder a2, float a3) {
        xu a4;
        for (jq a5 : a4.y) {
            a5.func_178765_a(a2, a3);
        }
    }
}

