/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  adp
 *  bib
 *  bqf
 *  bre
 *  brs
 *  bzf
 *  cbe
 */
package net.optifine.entity.model;

import net.optifine.entity.model.IEntityRenderer;
import net.optifine.entity.model.ModelAdapterBiped;
import net.optifine.reflect.Reflector;

public class ModelAdapterVex
extends ModelAdapterBiped {
    public ModelAdapterVex() {
        super(adp.class, "vex", 0.3f);
    }

    @Override
    public brs getModelRenderer(bqf model, String modelPart) {
        if (!(model instanceof bre)) {
            return null;
        }
        brs modelRenderer = super.getModelRenderer(model, modelPart);
        if (modelRenderer != null) {
            return modelRenderer;
        }
        bre modelVex = (bre)model;
        if (modelPart.equals("left_wing")) {
            return (brs)Reflector.getFieldValue(modelVex, Reflector.ModelVex_leftWing);
        }
        if (modelPart.equals("right_wing")) {
            return (brs)Reflector.getFieldValue(modelVex, Reflector.ModelVex_rightWing);
        }
        return null;
    }

    @Override
    public String[] getModelRendererNames() {
        Object[] names = super.getModelRendererNames();
        names = (String[])Config.addObjectsToArray(names, new String[]{"left_wing", "right_wing"});
        return names;
    }

    @Override
    public bqf makeModel() {
        return new bre();
    }

    @Override
    public IEntityRenderer makeEntityRender(bqf modelBase, float shadowSize) {
        bzf renderManager = bib.z().ac();
        cbe render = new cbe(renderManager);
        render.f = modelBase;
        render.c = shadowSize;
        return render;
    }
}

