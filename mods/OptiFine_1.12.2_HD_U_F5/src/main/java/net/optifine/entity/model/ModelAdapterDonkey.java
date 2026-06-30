/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  aap
 *  bib
 *  bqf
 *  byn
 *  bzf
 */
package net.optifine.entity.model;

import net.optifine.entity.model.IEntityRenderer;
import net.optifine.entity.model.ModelAdapterHorse;

public class ModelAdapterDonkey
extends ModelAdapterHorse {
    public ModelAdapterDonkey() {
        super(aap.class, "donkey", 0.75f);
    }

    @Override
    public IEntityRenderer makeEntityRender(bqf modelBase, float shadowSize) {
        bzf renderManager = bib.z().ac();
        byn render = new byn(renderManager);
        render.f = modelBase;
        render.c = shadowSize;
        return render;
    }
}

