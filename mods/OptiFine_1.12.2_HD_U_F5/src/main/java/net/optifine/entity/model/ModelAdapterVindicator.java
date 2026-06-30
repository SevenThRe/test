/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  adq
 *  bib
 *  bpy
 *  bqf
 *  bzf
 *  cbh
 */
package net.optifine.entity.model;

import net.optifine.entity.model.IEntityRenderer;
import net.optifine.entity.model.ModelAdapterIllager;

public class ModelAdapterVindicator
extends ModelAdapterIllager {
    public ModelAdapterVindicator() {
        super(adq.class, "vindication_illager", 0.5f);
    }

    @Override
    public bqf makeModel() {
        return new bpy(0.0f, 0.0f, 64, 64);
    }

    @Override
    public IEntityRenderer makeEntityRender(bqf modelBase, float shadowSize) {
        bzf renderManager = bib.z().ac();
        cbh render = new cbh(renderManager);
        render.f = modelBase;
        render.c = shadowSize;
        return render;
    }
}

