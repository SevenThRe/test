/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  ads
 *  bib
 *  bqf
 *  bqw
 *  bzf
 *  cbk
 */
package net.optifine.entity.model;

import net.optifine.entity.model.IEntityRenderer;
import net.optifine.entity.model.ModelAdapterBiped;

public class ModelAdapterWitherSkeleton
extends ModelAdapterBiped {
    public ModelAdapterWitherSkeleton() {
        super(ads.class, "wither_skeleton", 0.7f);
    }

    @Override
    public bqf makeModel() {
        return new bqw();
    }

    @Override
    public IEntityRenderer makeEntityRender(bqf modelBase, float shadowSize) {
        bzf renderManager = bib.z().ac();
        cbk render = new cbk(renderManager);
        render.f = modelBase;
        render.c = shadowSize;
        return render;
    }
}

