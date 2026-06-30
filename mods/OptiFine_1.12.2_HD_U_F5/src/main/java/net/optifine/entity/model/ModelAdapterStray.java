/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  ado
 *  bib
 *  bqf
 *  bqw
 *  bzf
 *  cay
 */
package net.optifine.entity.model;

import net.optifine.entity.model.IEntityRenderer;
import net.optifine.entity.model.ModelAdapterBiped;

public class ModelAdapterStray
extends ModelAdapterBiped {
    public ModelAdapterStray() {
        super(ado.class, "stray", 0.7f);
    }

    @Override
    public bqf makeModel() {
        return new bqw();
    }

    @Override
    public IEntityRenderer makeEntityRender(bqf modelBase, float shadowSize) {
        bzf renderManager = bib.z().ac();
        cay render = new cay(renderManager);
        render.f = modelBase;
        render.c = shadowSize;
        return render;
    }
}

