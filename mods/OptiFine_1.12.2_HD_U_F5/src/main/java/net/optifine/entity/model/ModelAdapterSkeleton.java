/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  adk
 *  bib
 *  bqf
 *  bqw
 *  bzf
 *  cas
 */
package net.optifine.entity.model;

import net.optifine.entity.model.IEntityRenderer;
import net.optifine.entity.model.ModelAdapterBiped;

public class ModelAdapterSkeleton
extends ModelAdapterBiped {
    public ModelAdapterSkeleton() {
        super(adk.class, "skeleton", 0.7f);
    }

    @Override
    public bqf makeModel() {
        return new bqw();
    }

    @Override
    public IEntityRenderer makeEntityRender(bqf modelBase, float shadowSize) {
        bzf renderManager = bib.z().ac();
        cas render = new cas(renderManager);
        render.f = modelBase;
        render.c = shadowSize;
        return render;
    }
}

