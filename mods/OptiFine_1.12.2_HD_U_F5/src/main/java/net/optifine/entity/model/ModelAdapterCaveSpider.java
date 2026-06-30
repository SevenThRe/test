/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  acr
 *  bib
 *  bqf
 *  byu
 *  bzf
 */
package net.optifine.entity.model;

import net.optifine.entity.model.IEntityRenderer;
import net.optifine.entity.model.ModelAdapterSpider;

public class ModelAdapterCaveSpider
extends ModelAdapterSpider {
    public ModelAdapterCaveSpider() {
        super(acr.class, "cave_spider", 0.7f);
    }

    @Override
    public IEntityRenderer makeEntityRender(bqf modelBase, float shadowSize) {
        bzf renderManager = bib.z().ac();
        byu render = new byu(renderManager);
        render.f = modelBase;
        render.c = shadowSize;
        return render;
    }
}

