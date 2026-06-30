/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  bqf
 *  bqm
 *  brs
 */
package net.optifine.entity.model;

import net.optifine.entity.model.ModelAdapter;

public abstract class ModelAdapterQuadruped
extends ModelAdapter {
    public ModelAdapterQuadruped(Class entityClass, String name, float shadowSize) {
        super(entityClass, name, shadowSize);
    }

    @Override
    public brs getModelRenderer(bqf model, String modelPart) {
        if (!(model instanceof bqm)) {
            return null;
        }
        bqm modelQuadruped = (bqm)model;
        if (modelPart.equals("head")) {
            return modelQuadruped.a;
        }
        if (modelPart.equals("body")) {
            return modelQuadruped.b;
        }
        if (modelPart.equals("leg1")) {
            return modelQuadruped.c;
        }
        if (modelPart.equals("leg2")) {
            return modelQuadruped.d;
        }
        if (modelPart.equals("leg3")) {
            return modelQuadruped.e;
        }
        if (modelPart.equals("leg4")) {
            return modelQuadruped.f;
        }
        return null;
    }

    @Override
    public String[] getModelRendererNames() {
        return new String[]{"head", "body", "leg1", "leg2", "leg3", "leg4"};
    }
}

