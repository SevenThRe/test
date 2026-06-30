/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  adu
 *  bib
 *  bqf
 *  brh
 *  bzf
 *  cbo
 */
package net.optifine.entity.model;

import net.optifine.entity.model.IEntityRenderer;
import net.optifine.entity.model.ModelAdapterBiped;

public class ModelAdapterZombieVillager
extends ModelAdapterBiped {
    public ModelAdapterZombieVillager() {
        super(adu.class, "zombie_villager", 0.5f);
    }

    @Override
    public bqf makeModel() {
        return new brh();
    }

    @Override
    public IEntityRenderer makeEntityRender(bqf modelBase, float shadowSize) {
        bzf renderManager = bib.z().ac();
        cbo render = new cbo(renderManager);
        render.f = modelBase;
        render.c = shadowSize;
        return render;
    }
}

