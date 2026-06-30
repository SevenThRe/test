/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  aak
 *  bib
 *  bqf
 *  brf
 *  brs
 *  bzf
 *  cbf
 */
package net.optifine.entity.model;

import net.optifine.entity.model.IEntityRenderer;
import net.optifine.entity.model.ModelAdapter;

public class ModelAdapterIronGolem
extends ModelAdapter {
    public ModelAdapterIronGolem() {
        super(aak.class, "iron_golem", 0.5f);
    }

    @Override
    public bqf makeModel() {
        return new brf();
    }

    @Override
    public brs getModelRenderer(bqf model, String modelPart) {
        if (!(model instanceof brf)) {
            return null;
        }
        brf modelIronGolem = (brf)model;
        if (modelPart.equals("head")) {
            return modelIronGolem.a;
        }
        if (modelPart.equals("body")) {
            return modelIronGolem.b;
        }
        if (modelPart.equals("left_arm")) {
            return modelIronGolem.d;
        }
        if (modelPart.equals("right_arm")) {
            return modelIronGolem.c;
        }
        if (modelPart.equals("left_leg")) {
            return modelIronGolem.e;
        }
        if (modelPart.equals("right_leg")) {
            return modelIronGolem.f;
        }
        return null;
    }

    @Override
    public String[] getModelRendererNames() {
        return new String[]{"head", "body", "right_arm", "left_arm", "left_leg", "right_leg"};
    }

    @Override
    public IEntityRenderer makeEntityRender(bqf modelBase, float shadowSize) {
        bzf renderManager = bib.z().ac();
        cbf render = new cbf(renderManager);
        render.f = modelBase;
        render.c = shadowSize;
        return render;
    }
}

