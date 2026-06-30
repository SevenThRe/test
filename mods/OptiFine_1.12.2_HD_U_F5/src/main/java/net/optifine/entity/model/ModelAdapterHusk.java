/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  adb
 *  bib
 *  bqf
 *  brl
 *  bzf
 *  bzs
 */
package net.optifine.entity.model;

import net.optifine.entity.model.IEntityRenderer;
import net.optifine.entity.model.ModelAdapterBiped;

public class ModelAdapterHusk
extends ModelAdapterBiped {
    public ModelAdapterHusk() {
        super(adb.class, "husk", 0.5f);
    }

    @Override
    public bqf makeModel() {
        return new brl();
    }

    @Override
    public IEntityRenderer makeEntityRender(bqf modelBase, float shadowSize) {
        bzf renderManager = bib.z().ac();
        bzs render = new bzs(renderManager);
        render.f = modelBase;
        render.c = shadowSize;
        return render;
    }
}

