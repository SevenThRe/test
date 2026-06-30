/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  aas
 *  bib
 *  bqc
 *  bqf
 *  bzf
 *  cab
 */
package net.optifine.entity.model;

import net.optifine.entity.model.IEntityRenderer;
import net.optifine.entity.model.ModelAdapterQuadruped;

public class ModelAdapterLlama
extends ModelAdapterQuadruped {
    public ModelAdapterLlama() {
        super(aas.class, "llama", 0.7f);
    }

    @Override
    public bqf makeModel() {
        return new bqc(0.0f);
    }

    @Override
    public IEntityRenderer makeEntityRender(bqf modelBase, float shadowSize) {
        bzf renderManager = bib.z().ac();
        cab render = new cab(renderManager);
        render.f = modelBase;
        render.c = shadowSize;
        return render;
    }
}

