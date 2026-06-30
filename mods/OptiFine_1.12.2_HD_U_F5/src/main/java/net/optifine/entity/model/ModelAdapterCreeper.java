/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  acs
 *  bib
 *  bpo
 *  bqf
 *  brs
 *  byx
 *  bzf
 */
package net.optifine.entity.model;

import net.optifine.entity.model.IEntityRenderer;
import net.optifine.entity.model.ModelAdapter;

public class ModelAdapterCreeper
extends ModelAdapter {
    public ModelAdapterCreeper() {
        super(acs.class, "creeper", 0.5f);
    }

    @Override
    public bqf makeModel() {
        return new bpo();
    }

    @Override
    public brs getModelRenderer(bqf model, String modelPart) {
        if (!(model instanceof bpo)) {
            return null;
        }
        bpo modelCreeper = (bpo)model;
        if (modelPart.equals("head")) {
            return modelCreeper.a;
        }
        if (modelPart.equals("armor")) {
            return modelCreeper.b;
        }
        if (modelPart.equals("body")) {
            return modelCreeper.c;
        }
        if (modelPart.equals("leg1")) {
            return modelCreeper.d;
        }
        if (modelPart.equals("leg2")) {
            return modelCreeper.e;
        }
        if (modelPart.equals("leg3")) {
            return modelCreeper.f;
        }
        if (modelPart.equals("leg4")) {
            return modelCreeper.g;
        }
        return null;
    }

    @Override
    public String[] getModelRendererNames() {
        return new String[]{"head", "armor", "body", "leg1", "leg2", "leg3", "leg4"};
    }

    @Override
    public IEntityRenderer makeEntityRender(bqf modelBase, float shadowSize) {
        bzf renderManager = bib.z().ac();
        byx render = new byx(renderManager);
        render.f = modelBase;
        render.c = shadowSize;
        return render;
    }
}

