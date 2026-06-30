/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.util.ResourceLocation
 */
package eos.moe.dragoncore;

import eos.moe.dragoncore.api.model.AnimationEntityModel;
import eos.moe.dragoncore.api.model.AnimationModel;
import eos.moe.dragoncore.api.model.AnimationModelRenderer;
import eos.moe.dragoncore.at;
import eos.moe.dragoncore.ea;
import eos.moe.dragoncore.hs;
import eos.moe.dragoncore.hy;
import eos.moe.dragoncore.kq;
import eos.moe.dragoncore.kw;
import eos.moe.dragoncore.raa;
import eos.moe.dragoncore.yz;
import java.io.Serializable;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;
import java.util.stream.Collectors;
import net.minecraft.util.ResourceLocation;

/*
 * Duplicate member names - consider using --renamedupmembers true
 */
public class fu
extends kq
implements Serializable {
    private static final long x = 3726656164116961212L;
    private boolean v;
    private final boolean m;
    private boolean c;
    private float q;
    private final String b;
    private final ResourceLocation o;
    private final int y;
    private final Map<String, hy> k;
    private Map<Integer, List<kw>> ALLATORIxDEMO;

    public fu(boolean a2, boolean a3, boolean a4, float a5, ResourceLocation a6, String a7, int a8, Map<String, hy> a9, Map<Integer, List<kw>> a10) {
        fu a11;
        a11.v = a2;
        a11.c = a4;
        a11.q = a5;
        a11.b = a7;
        a11.o = a6;
        a11.y = a8;
        a11.k = a9;
        a11.m = a3;
        a11.ALLATORIxDEMO = a10;
    }

    @Override
    public void ALLATORIxDEMO(AnimationEntityModel a2, ea a3, int a4) {
        fu a5;
        AnimationModel a6 = a2.getBaseModel();
        if (a4 <= a5.y) {
            raa.x.ALLATORIxDEMO("query.anim_time", (float)a4 / 1000.0f);
            if (a5.c()) {
                a6.clear();
            }
            if (a5.k != null) {
                for (Map.Entry<Object, Object> entry : a5.k.entrySet()) {
                    hy a8 = (hy)entry.getValue();
                    AnimationModelRenderer a9 = a6.getPiece(a8.ALLATORIxDEMO());
                    if (a9 == null) continue;
                    a9.setApplyAnimation(true);
                    a8.ALLATORIxDEMO(a5, a3, a9, a4);
                }
            }
            if (a5.ALLATORIxDEMO != null) {
                for (Map.Entry<Object, Object> entry : a5.ALLATORIxDEMO.entrySet()) {
                    if (a4 <= (Integer)entry.getKey()) continue;
                    for (kw a7 : (List)entry.getValue()) {
                        a3.ALLATORIxDEMO(a7);
                    }
                }
            }
        }
    }

    @Override
    public int ALLATORIxDEMO() {
        fu a2;
        return a2.y;
    }

    @Override
    public String ALLATORIxDEMO() {
        fu a2;
        return a2.b;
    }

    @Override
    public ResourceLocation ALLATORIxDEMO() {
        fu a2;
        return a2.o;
    }

    @Override
    public boolean f() {
        fu a2;
        return a2.v;
    }

    public void f() {
        a.v = true;
    }

    @Override
    public boolean c() {
        fu a2;
        return a2.c;
    }

    @Override
    public void ALLATORIxDEMO() {
        a.c = true;
    }

    @Override
    public float ALLATORIxDEMO() {
        fu a2;
        return a2.q;
    }

    @Override
    public void ALLATORIxDEMO(float a2) {
        a.q = a2;
    }

    @Override
    public boolean ALLATORIxDEMO() {
        fu a2;
        return a2.m;
    }

    public Map<String, hy> ALLATORIxDEMO() {
        fu a2;
        return a2.k;
    }

    @Override
    public hs ALLATORIxDEMO() {
        fu a2;
        return new at(a2);
    }

    @Override
    public void ALLATORIxDEMO(Consumer<String> a2) {
        fu a5;
        if (a5.ALLATORIxDEMO() != null) {
            a5.ALLATORIxDEMO().forEach((a3, a4) -> a2.accept((String)a3));
        }
    }

    @Override
    public kq ALLATORIxDEMO() {
        HashMap<String, hy> a2;
        fu a5;
        if (a5.k == null) {
            a2 = null;
        } else {
            a2 = new HashMap<String, hy>();
            a5.k.forEach((a3, a4) -> {
                fu a5;
                List<yz> a6 = null;
                if (a4.f() != null) {
                    a6 = a5.ALLATORIxDEMO(a4.f());
                }
                List<yz> a7 = null;
                if (a4.c() != null) {
                    a7 = a5.ALLATORIxDEMO(a4.c());
                }
                List<yz> a8 = null;
                if (a4.ALLATORIxDEMO() != null) {
                    a8 = a5.ALLATORIxDEMO(a4.ALLATORIxDEMO());
                }
                a2.put(a4.ALLATORIxDEMO(), new hy(a4.ALLATORIxDEMO(), a7, a6, a8));
            });
        }
        return new fu(a5.v, a5.m, a5.c, a5.q, new ResourceLocation(a5.o.getNamespace(), a5.o.getPath() + "-reversed"), a5.b + "-reversed", a5.y, a2, null);
    }

    private /* synthetic */ List<yz> ALLATORIxDEMO(List<yz> a3) {
        fu a4;
        return a3.stream().sorted(Collections.reverseOrder(Comparator.comparingInt(yz::ALLATORIxDEMO))).map(a2 -> {
            fu a3;
            return new yz(a3.y - a2.ALLATORIxDEMO(), a2.c());
        }).collect(Collectors.toList());
    }

    public String toString() {
        fu a2;
        return "BasicAnimation{name=" + a2.b + ", id=" + a2.o + ", looped=" + a2.v + ", length=" + a2.y + '}';
    }
}

