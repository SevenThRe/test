/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  adt
 *  bib
 *  bqf
 *  brl
 *  bzf
 *  cbn
 */
package net.optifine.entity.model;

import net.optifine.entity.model.IEntityRenderer;
import net.optifine.entity.model.ModelAdapterBiped;

public class ModelAdapterZombie
extends ModelAdapterBiped {
    public ModelAdapterZombie() {
        super(adt.class, "zombie", 0.5f);
    }

    @Override
    public bqf makeModel() {
        return new brl();
    }

    @Override
    public IEntityRenderer makeEntityRender(bqf modelBase, float shadowSize) {
        bzf renderManager = bib.z().ac();
        cbn render = new cbn(renderManager);
        render.f = modelBase;
        render.c = shadowSize;
        return render;
    }
}

