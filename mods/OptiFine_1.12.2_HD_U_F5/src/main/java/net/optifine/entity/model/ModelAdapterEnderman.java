/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  acu
 *  bib
 *  bpq
 *  bqf
 *  bzd
 *  bzf
 */
package net.optifine.entity.model;

import net.optifine.entity.model.IEntityRenderer;
import net.optifine.entity.model.ModelAdapterBiped;

public class ModelAdapterEnderman
extends ModelAdapterBiped {
    public ModelAdapterEnderman() {
        super(acu.class, "enderman", 0.5f);
    }

    @Override
    public bqf makeModel() {
        return new bpq(0.0f);
    }

    @Override
    public IEntityRenderer makeEntityRender(bqf modelBase, float shadowSize) {
        bzf renderManager = bib.z().ac();
        bzd render = new bzd(renderManager);
        render.f = modelBase;
        render.c = shadowSize;
        return render;
    }
}

