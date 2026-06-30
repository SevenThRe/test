/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  adf
 *  bib
 *  bqf
 *  brl
 *  bzf
 *  cal
 */
package net.optifine.entity.model;

import net.optifine.entity.model.IEntityRenderer;
import net.optifine.entity.model.ModelAdapterBiped;

public class ModelAdapterPigZombie
extends ModelAdapterBiped {
    public ModelAdapterPigZombie() {
        super(adf.class, "zombie_pigman", 0.5f);
    }

    @Override
    public bqf makeModel() {
        return new brl();
    }

    @Override
    public IEntityRenderer makeEntityRender(bqf modelBase, float shadowSize) {
        bzf renderManager = bib.z().ac();
        cal render = new cal(renderManager);
        render.f = modelBase;
        render.c = shadowSize;
        return render;
    }
}

