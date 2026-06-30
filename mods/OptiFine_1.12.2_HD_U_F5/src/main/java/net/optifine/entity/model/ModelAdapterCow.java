/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  bib
 *  bpn
 *  bqf
 *  byw
 *  bzf
 *  zx
 */
package net.optifine.entity.model;

import net.optifine.entity.model.IEntityRenderer;
import net.optifine.entity.model.ModelAdapterQuadruped;

public class ModelAdapterCow
extends ModelAdapterQuadruped {
    public ModelAdapterCow() {
        super(zx.class, "cow", 0.7f);
    }

    @Override
    public bqf makeModel() {
        return new bpn();
    }

    @Override
    public IEntityRenderer makeEntityRender(bqf modelBase, float shadowSize) {
        bzf renderManager = bib.z().ac();
        byw render = new byw(renderManager);
        render.f = modelBase;
        render.c = shadowSize;
        return render;
    }
}

