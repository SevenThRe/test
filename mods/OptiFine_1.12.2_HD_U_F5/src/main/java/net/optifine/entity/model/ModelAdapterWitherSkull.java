/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  afb
 *  bib
 *  bqf
 *  bqv
 *  brs
 *  bzf
 *  cbl
 */
package net.optifine.entity.model;

import net.optifine.entity.model.IEntityRenderer;
import net.optifine.entity.model.ModelAdapter;
import net.optifine.reflect.Reflector;

public class ModelAdapterWitherSkull
extends ModelAdapter {
    public ModelAdapterWitherSkull() {
        super(afb.class, "wither_skull", 0.0f);
    }

    @Override
    public bqf makeModel() {
        return new bqv();
    }

    @Override
    public brs getModelRenderer(bqf model, String modelPart) {
        if (!(model instanceof bqv)) {
            return null;
        }
        bqv modelSkeletonHead = (bqv)model;
        if (modelPart.equals("head")) {
            return modelSkeletonHead.a;
        }
        return null;
    }

    @Override
    public String[] getModelRendererNames() {
        return new String[]{"head"};
    }

    @Override
    public IEntityRenderer makeEntityRender(bqf modelBase, float shadowSize) {
        bzf renderManager = bib.z().ac();
        cbl render = new cbl(renderManager);
        if (!Reflector.RenderWitherSkull_model.exists()) {
            Config.warn("Field not found: RenderWitherSkull_model");
            return null;
        }
        Reflector.setFieldValue(render, Reflector.RenderWitherSkull_model, modelBase);
        render.c = shadowSize;
        return render;
    }
}

