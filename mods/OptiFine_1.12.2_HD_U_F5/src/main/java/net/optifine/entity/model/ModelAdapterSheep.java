/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  aag
 *  bib
 *  bqf
 *  bqp
 *  bzf
 *  cao
 */
package net.optifine.entity.model;

import net.optifine.entity.model.IEntityRenderer;
import net.optifine.entity.model.ModelAdapterQuadruped;

public class ModelAdapterSheep
extends ModelAdapterQuadruped {
    public ModelAdapterSheep() {
        super(aag.class, "sheep", 0.7f);
    }

    @Override
    public bqf makeModel() {
        return new bqp();
    }

    @Override
    public IEntityRenderer makeEntityRender(bqf modelBase, float shadowSize) {
        bzf renderManager = bib.z().ac();
        cao render = new cao(renderManager);
        render.f = modelBase;
        render.c = shadowSize;
        return render;
    }
}

