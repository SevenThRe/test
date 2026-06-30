/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  acc
 *  bib
 *  bqb
 *  bqf
 *  brs
 *  bzf
 *  bzy
 */
package net.optifine.entity.model;

import net.optifine.entity.model.IEntityRenderer;
import net.optifine.entity.model.ModelAdapter;
import net.optifine.reflect.Reflector;

public class ModelAdapterLeadKnot
extends ModelAdapter {
    public ModelAdapterLeadKnot() {
        super(acc.class, "lead_knot", 0.0f);
    }

    @Override
    public bqf makeModel() {
        return new bqb();
    }

    @Override
    public brs getModelRenderer(bqf model, String modelPart) {
        if (!(model instanceof bqb)) {
            return null;
        }
        bqb modelLeashKnot = (bqb)model;
        if (modelPart.equals("knot")) {
            return modelLeashKnot.a;
        }
        return null;
    }

    @Override
    public String[] getModelRendererNames() {
        return new String[]{"knot"};
    }

    @Override
    public IEntityRenderer makeEntityRender(bqf modelBase, float shadowSize) {
        bzf renderManager = bib.z().ac();
        bzy render = new bzy(renderManager);
        if (!Reflector.RenderLeashKnot_leashKnotModel.exists()) {
            Config.warn("Field not found: RenderLeashKnot.leashKnotModel");
            return null;
        }
        Reflector.setFieldValue(render, Reflector.RenderLeashKnot_leashKnotModel, modelBase);
        render.c = shadowSize;
        return render;
    }
}

