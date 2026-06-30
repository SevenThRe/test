/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  aej
 *  bib
 *  bps
 *  bqf
 *  brs
 *  bzf
 *  bzh
 */
package net.optifine.entity.model;

import net.optifine.entity.model.IEntityRenderer;
import net.optifine.entity.model.ModelAdapter;
import net.optifine.reflect.Reflector;

public class ModelAdapterEvokerFangs
extends ModelAdapter {
    public ModelAdapterEvokerFangs() {
        super(aej.class, "evocation_fangs", 0.0f);
    }

    @Override
    public bqf makeModel() {
        return new bps();
    }

    @Override
    public brs getModelRenderer(bqf model, String modelPart) {
        if (!(model instanceof bps)) {
            return null;
        }
        bps modelEvokerFangs = (bps)model;
        if (modelPart.equals("base")) {
            return (brs)Reflector.getFieldValue(modelEvokerFangs, Reflector.ModelEvokerFangs_ModelRenderers, 0);
        }
        if (modelPart.equals("upper_jaw")) {
            return (brs)Reflector.getFieldValue(modelEvokerFangs, Reflector.ModelEvokerFangs_ModelRenderers, 1);
        }
        if (modelPart.equals("lower_jaw")) {
            return (brs)Reflector.getFieldValue(modelEvokerFangs, Reflector.ModelEvokerFangs_ModelRenderers, 2);
        }
        return null;
    }

    @Override
    public String[] getModelRendererNames() {
        return new String[]{"base", "upper_jaw", "lower_jaw"};
    }

    @Override
    public IEntityRenderer makeEntityRender(bqf modelBase, float shadowSize) {
        bzf renderManager = bib.z().ac();
        bzh render = new bzh(renderManager);
        if (!Reflector.RenderEvokerFangs_model.exists()) {
            Config.warn("Field not found: RenderEvokerFangs.model");
            return null;
        }
        Reflector.setFieldValue(render, Reflector.RenderEvokerFangs_model, modelBase);
        render.c = shadowSize;
        return render;
    }
}

