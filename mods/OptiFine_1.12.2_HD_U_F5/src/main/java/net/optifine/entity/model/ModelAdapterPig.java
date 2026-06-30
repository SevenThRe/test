/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  aad
 *  bib
 *  bqf
 *  bqi
 *  bzf
 *  cak
 */
package net.optifine.entity.model;

import net.optifine.entity.model.IEntityRenderer;
import net.optifine.entity.model.ModelAdapterQuadruped;

public class ModelAdapterPig
extends ModelAdapterQuadruped {
    public ModelAdapterPig() {
        super(aad.class, "pig", 0.7f);
    }

    @Override
    public bqf makeModel() {
        return new bqi();
    }

    @Override
    public IEntityRenderer makeEntityRender(bqf modelBase, float shadowSize) {
        bzf renderManager = bib.z().ac();
        cak render = new cak(renderManager);
        render.f = modelBase;
        render.c = shadowSize;
        return render;
    }
}

