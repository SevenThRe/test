/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cea
 *  ceb
 *  ceb$a
 *  ceb$b
 */
package net.optifine.shaders;

public class SVertexFormat {
    public static final int vertexSizeBlock = 14;
    public static final int offsetMidTexCoord = 8;
    public static final int offsetTangent = 10;
    public static final int offsetEntity = 12;
    public static final cea defVertexFormatTextured = SVertexFormat.makeDefVertexFormatTextured();

    public static cea makeDefVertexFormatBlock() {
        cea vf = new cea();
        vf.a(new ceb(0, ceb.a.a, ceb.b.a, 3));
        vf.a(new ceb(0, ceb.a.b, ceb.b.c, 4));
        vf.a(new ceb(0, ceb.a.a, ceb.b.d, 2));
        vf.a(new ceb(1, ceb.a.e, ceb.b.d, 2));
        vf.a(new ceb(0, ceb.a.c, ceb.b.b, 3));
        vf.a(new ceb(0, ceb.a.c, ceb.b.g, 1));
        vf.a(new ceb(0, ceb.a.a, ceb.b.g, 2));
        vf.a(new ceb(0, ceb.a.e, ceb.b.g, 4));
        vf.a(new ceb(0, ceb.a.e, ceb.b.g, 4));
        return vf;
    }

    public static cea makeDefVertexFormatItem() {
        cea vf = new cea();
        vf.a(new ceb(0, ceb.a.a, ceb.b.a, 3));
        vf.a(new ceb(0, ceb.a.b, ceb.b.c, 4));
        vf.a(new ceb(0, ceb.a.a, ceb.b.d, 2));
        vf.a(new ceb(0, ceb.a.e, ceb.b.g, 2));
        vf.a(new ceb(0, ceb.a.c, ceb.b.b, 3));
        vf.a(new ceb(0, ceb.a.c, ceb.b.g, 1));
        vf.a(new ceb(0, ceb.a.a, ceb.b.g, 2));
        vf.a(new ceb(0, ceb.a.e, ceb.b.g, 4));
        vf.a(new ceb(0, ceb.a.e, ceb.b.g, 4));
        return vf;
    }

    public static cea makeDefVertexFormatTextured() {
        cea vf = new cea();
        vf.a(new ceb(0, ceb.a.a, ceb.b.a, 3));
        vf.a(new ceb(0, ceb.a.b, ceb.b.g, 4));
        vf.a(new ceb(0, ceb.a.a, ceb.b.d, 2));
        vf.a(new ceb(0, ceb.a.e, ceb.b.g, 2));
        vf.a(new ceb(0, ceb.a.c, ceb.b.b, 3));
        vf.a(new ceb(0, ceb.a.c, ceb.b.g, 1));
        vf.a(new ceb(0, ceb.a.a, ceb.b.g, 2));
        vf.a(new ceb(0, ceb.a.e, ceb.b.g, 4));
        vf.a(new ceb(0, ceb.a.e, ceb.b.g, 4));
        return vf;
    }

    public static void setDefBakedFormat(cea vf) {
        if (vf == null) {
            return;
        }
        vf.a();
        vf.a(new ceb(0, ceb.a.a, ceb.b.a, 3));
        vf.a(new ceb(0, ceb.a.b, ceb.b.c, 4));
        vf.a(new ceb(0, ceb.a.a, ceb.b.d, 2));
        vf.a(new ceb(0, ceb.a.e, ceb.b.g, 2));
        vf.a(new ceb(0, ceb.a.c, ceb.b.b, 3));
        vf.a(new ceb(0, ceb.a.c, ceb.b.g, 1));
        vf.a(new ceb(0, ceb.a.a, ceb.b.g, 2));
        vf.a(new ceb(0, ceb.a.e, ceb.b.g, 4));
        vf.a(new ceb(0, ceb.a.e, ceb.b.g, 4));
    }

    public static cea duplicate(cea src) {
        if (src == null) {
            return null;
        }
        cea dst = new cea();
        SVertexFormat.copy(src, dst);
        return dst;
    }

    public static void copy(cea src, cea dst) {
        if (src == null || dst == null) {
            return;
        }
        dst.a();
        for (int i = 0; i < src.i(); ++i) {
            dst.a(src.c(i));
        }
    }
}

