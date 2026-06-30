/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  bib
 *  bpm
 *  bqf
 *  brs
 *  byv
 *  bzf
 *  zw
 */
package net.optifine.entity.model;

import net.optifine.entity.model.IEntityRenderer;
import net.optifine.entity.model.ModelAdapter;

public class ModelAdapterChicken
extends ModelAdapter {
    public ModelAdapterChicken() {
        super(zw.class, "chicken", 0.3f);
    }

    @Override
    public bqf makeModel() {
        return new bpm();
    }

    @Override
    public brs getModelRenderer(bqf model, String modelPart) {
        if (!(model instanceof bpm)) {
            return null;
        }
        bpm modelChicken = (bpm)model;
        if (modelPart.equals("head")) {
            return modelChicken.a;
        }
        if (modelPart.equals("body")) {
            return modelChicken.b;
        }
        if (modelPart.equals("right_leg")) {
            return modelChicken.c;
        }
        if (modelPart.equals("left_leg")) {
            return modelChicken.d;
        }
        if (modelPart.equals("right_wing")) {
            return modelChicken.e;
        }
        if (modelPart.equals("left_wing")) {
            return modelChicken.f;
        }
        if (modelPart.equals("bill")) {
            return modelChicken.g;
        }
        if (modelPart.equals("chin")) {
            return modelChicken.h;
        }
        return null;
    }

    @Override
    public String[] getModelRendererNames() {
        return new String[]{"head", "body", "right_leg", "left_leg", "right_wing", "left_wing", "bill", "chin"};
    }

    @Override
    public IEntityRenderer makeEntityRender(bqf modelBase, float shadowSize) {
        bzf renderManager = bib.z().ac();
        byv render = new byv(renderManager);
        render.f = modelBase;
        render.c = shadowSize;
        return render;
    }
}

