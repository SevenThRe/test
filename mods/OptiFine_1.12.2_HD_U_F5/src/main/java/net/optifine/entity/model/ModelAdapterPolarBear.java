/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  aae
 *  bib
 *  bqf
 *  bqk
 *  bzf
 *  cam
 */
package net.optifine.entity.model;

import net.optifine.entity.model.IEntityRenderer;
import net.optifine.entity.model.ModelAdapterQuadruped;

public class ModelAdapterPolarBear
extends ModelAdapterQuadruped {
    public ModelAdapterPolarBear() {
        super(aae.class, "polar_bear", 0.7f);
    }

    @Override
    public bqf makeModel() {
        return new bqk();
    }

    @Override
    public IEntityRenderer makeEntityRender(bqf modelBase, float shadowSize) {
        bzf renderManager = bib.z().ac();
        cam render = new cam(renderManager);
        render.f = modelBase;
        render.c = shadowSize;
        return render;
    }
}

