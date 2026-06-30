/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  aam
 *  bib
 *  bqf
 *  brk
 *  brs
 *  bzf
 *  cbm
 */
package net.optifine.entity.model;

import net.optifine.entity.model.IEntityRenderer;
import net.optifine.entity.model.ModelAdapter;
import net.optifine.reflect.Reflector;

public class ModelAdapterWolf
extends ModelAdapter {
    public ModelAdapterWolf() {
        super(aam.class, "wolf", 0.5f);
    }

    @Override
    public bqf makeModel() {
        return new brk();
    }

    @Override
    public brs getModelRenderer(bqf model, String modelPart) {
        if (!(model instanceof brk)) {
            return null;
        }
        brk modelWolf = (brk)model;
        if (modelPart.equals("head")) {
            return modelWolf.a;
        }
        if (modelPart.equals("body")) {
            return modelWolf.b;
        }
        if (modelPart.equals("leg1")) {
            return modelWolf.c;
        }
        if (modelPart.equals("leg2")) {
            return modelWolf.d;
        }
        if (modelPart.equals("leg3")) {
            return modelWolf.e;
        }
        if (modelPart.equals("leg4")) {
            return modelWolf.f;
        }
        if (modelPart.equals("tail")) {
            return (brs)Reflector.getFieldValue(modelWolf, Reflector.ModelWolf_tail);
        }
        if (modelPart.equals("mane")) {
            return (brs)Reflector.getFieldValue(modelWolf, Reflector.ModelWolf_mane);
        }
        return null;
    }

    @Override
    public String[] getModelRendererNames() {
        return new String[]{"head", "body", "leg1", "leg2", "leg3", "leg4", "tail", "mane"};
    }

    @Override
    public IEntityRenderer makeEntityRender(bqf modelBase, float shadowSize) {
        bzf renderManager = bib.z().ac();
        cbm render = new cbm(renderManager);
        render.f = modelBase;
        render.c = shadowSize;
        return render;
    }
}

