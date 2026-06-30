/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.model.ModelBase
 *  net.minecraft.client.model.ModelRenderer
 *  net.minecraft.entity.Entity
 */
package eos.moe.dragoncore;

import eos.moe.dragoncore.ModelLocator;
import eos.moe.dragoncore.api.model.AnimationEntityModel;
import eos.moe.dragoncore.api.model.AnimationModel;
import eos.moe.dragoncore.api.model.IModel;
import eos.moe.dragoncore.api.model.IModelPiece;
import eos.moe.dragoncore.mt;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class jv
extends ModelBase
implements IModel,
AnimationModel,
AnimationEntityModel,
Serializable {
    private static final long q = -803001720143499167L;
    private String b;
    private Map<String, ModelLocator> o;
    private List<mt> y;
    private Map<String, mt> k;
    public boolean ALLATORIxDEMO = false;

    public jv(String a2, int a3, int a4, Map<String, ModelLocator> a5) {
        jv a6;
        a6.b = a2;
        a6.o = a5;
        a6.field_78090_t = a3;
        a6.field_78089_u = a4;
    }

    public void func_78088_a(Entity a2, float a3, float a4, float a5, float a6, float a7, float a8) {
        jv a9;
        a9.render(a8);
    }

    public String getName() {
        jv a2;
        return a2.b;
    }

    public void setName(String a2) {
        a.b = a2;
    }

    @Override
    public void render(float a2) {
        jv a3;
        if (a3.y != null) {
            for (mt a4 : a3.y) {
                a4.func_78785_a(a2);
            }
        }
    }

    public Map<String, ModelLocator> getLocatorMap() {
        jv a2;
        return a2.o;
    }

    @Override
    public void clear() {
        jv a2;
        a2.clearData();
    }

    @Override
    public void clearData() {
        jv a2;
        if (a2.y != null) {
            for (mt a3 : a2.y) {
                a3.resetData();
            }
        }
    }

    @Override
    public List<IModelPiece> getModelPieces() {
        jv a2;
        return new ArrayList<IModelPiece>(a2.y);
    }

    public List<mt> getPieces() {
        jv a2;
        return a2.y;
    }

    public void setPieces(List<mt> a2) {
        a4.y = a2;
        a4.k = new HashMap<String, mt>();
        for (mt a3 : a2) {
            jv a4;
            a4.ALLATORIxDEMO(a3);
        }
    }

    private /* synthetic */ void ALLATORIxDEMO(mt a2) {
        jv a3;
        a3.k.put(a2.field_78802_n, a2);
        List a4 = a2.field_78805_m;
        if (a4 != null) {
            for (ModelRenderer a5 : a4) {
                if (!(a5 instanceof mt)) continue;
                a3.ALLATORIxDEMO((mt)a5);
            }
        }
    }

    @Override
    public mt getPiece(String a2) {
        jv a3;
        return a3.k.get(a2);
    }

    public Map<String, mt> getPieceMap() {
        jv a2;
        return a2.k;
    }

    @Override
    public ModelRenderer findPiece(String a2) {
        jv a3;
        if (a3.k.containsKey(a2)) {
            return a3.k.get(a2);
        }
        for (mt a4 : a3.y) {
            ModelRenderer a5 = a4.findPiece(a2);
            if (a5 == null) continue;
            return a5;
        }
        return null;
    }

    @Override
    public AnimationModel getBaseModel() {
        jv a2;
        return a2;
    }

    @Override
    public Entity getEntity() {
        return null;
    }

    @Override
    public void setEntity(Entity a2) {
    }
}

