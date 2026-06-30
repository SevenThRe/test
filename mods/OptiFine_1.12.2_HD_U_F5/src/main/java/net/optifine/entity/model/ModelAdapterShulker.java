/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  adi
 *  bib
 *  bqf
 *  bqs
 *  brs
 *  bzf
 *  caq
 */
package net.optifine.entity.model;

import net.optifine.entity.model.IEntityRenderer;
import net.optifine.entity.model.ModelAdapter;

public class ModelAdapterShulker
extends ModelAdapter {
    public ModelAdapterShulker() {
        super(adi.class, "shulker", 0.0f);
    }

    @Override
    public bqf makeModel() {
        return new bqs();
    }

    @Override
    public brs getModelRenderer(bqf model, String modelPart) {
        if (!(model instanceof bqs)) {
            return null;
        }
        bqs modelShulker = (bqs)model;
        if (modelPart.equals("head")) {
            return modelShulker.c;
        }
        if (modelPart.equals("base")) {
            return modelShulker.a;
        }
        if (modelPart.equals("lid")) {
            return modelShulker.b;
        }
        return null;
    }

    @Override
    public String[] getModelRendererNames() {
        return new String[]{"base", "lid", "head"};
    }

    @Override
    public IEntityRenderer makeEntityRender(bqf modelBase, float shadowSize) {
        bzf renderManager = bib.z().ac();
        caq render = new caq(renderManager);
        render.f = modelBase;
        render.c = shadowSize;
        return render;
    }
}

