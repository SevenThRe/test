/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  aaa
 *  bib
 *  bpn
 *  bqf
 *  bzf
 *  cag
 */
package net.optifine.entity.model;

import net.optifine.entity.model.IEntityRenderer;
import net.optifine.entity.model.ModelAdapterQuadruped;

public class ModelAdapterMooshroom
extends ModelAdapterQuadruped {
    public ModelAdapterMooshroom() {
        super(aaa.class, "mooshroom", 0.7f);
    }

    @Override
    public bqf makeModel() {
        return new bpn();
    }

    @Override
    public IEntityRenderer makeEntityRender(bqf modelBase, float shadowSize) {
        bzf renderManager = bib.z().ac();
        cag render = new cag(renderManager);
        render.f = modelBase;
        render.c = shadowSize;
        return render;
    }
}

