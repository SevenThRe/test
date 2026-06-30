/*
 * Decompiled with CFR 0.152.
 */
package eos.moe.dragoncore;

import eos.moe.dragoncore.acf;
import eos.moe.dragoncore.ag;
import eos.moe.dragoncore.bax;
import eos.moe.dragoncore.bf;
import eos.moe.dragoncore.jv;
import eos.moe.dragoncore.xu;
import java.io.Serializable;
import java.util.List;

public class gv
implements Serializable {
    private static final long serialVersionUID = -2034548852881688509L;
    private bax o;
    private bax z;
    private acf f;
    private List<ag> c;

    public gv(bax a2, bax a3, acf a4) {
        gv a5;
        a5.o = a2;
        a5.z = a3;
        a5.f = a4;
    }

    public gv(bax a2, bax a3, List<ag> a4) {
        gv a5;
        a5.o = a2;
        a5.z = a3;
        a5.c = a4;
    }

    public xu bake(jv a2, bf a3) {
        gv a4;
        a4.o.set(a4.o.getX() - a3.a.getX(), -(a4.o.getY() + a4.z.getY() - a3.a.getY()), a4.o.getZ() - a3.a.getZ());
        if (a4.c == null) {
            return new xu(a4.o, a4.z, a4.f, a3.f, a3.e, a2.field_78090_t, a2.field_78089_u);
        }
        return new xu(a2, a4.o, a4.z, a4.c, a3.f, a3.e, a2.field_78090_t, a2.field_78089_u);
    }
}

