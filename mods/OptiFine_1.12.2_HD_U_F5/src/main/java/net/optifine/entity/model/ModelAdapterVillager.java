/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  ady
 *  bib
 *  bqf
 *  brg
 *  brs
 *  bzf
 *  cbg
 */
package net.optifine.entity.model;

import net.optifine.entity.model.IEntityRenderer;
import net.optifine.entity.model.ModelAdapter;

public class ModelAdapterVillager
extends ModelAdapter {
    public ModelAdapterVillager() {
        super(ady.class, "villager", 0.5f);
    }

    @Override
    public bqf makeModel() {
        return new brg(0.0f);
    }

    @Override
    public brs getModelRenderer(bqf model, String modelPart) {
        if (!(model instanceof brg)) {
            return null;
        }
        brg modelVillager = (brg)model;
        if (modelPart.equals("head")) {
            return modelVillager.a;
        }
        if (modelPart.equals("body")) {
            return modelVillager.b;
        }
        if (modelPart.equals("arms")) {
            return modelVillager.c;
        }
        if (modelPart.equals("left_leg")) {
            return modelVillager.e;
        }
        if (modelPart.equals("right_leg")) {
            return modelVillager.d;
        }
        if (modelPart.equals("nose")) {
            return modelVillager.f;
        }
        return null;
    }

    @Override
    public String[] getModelRendererNames() {
        return new String[]{"head", "body", "arms", "right_leg", "left_leg", "nose"};
    }

    @Override
    public IEntityRenderer makeEntityRender(bqf modelBase, float shadowSize) {
        bzf renderManager = bib.z().ac();
        cbg render = new cbg(renderManager);
        render.f = modelBase;
        render.c = shadowSize;
        return render;
    }
}

