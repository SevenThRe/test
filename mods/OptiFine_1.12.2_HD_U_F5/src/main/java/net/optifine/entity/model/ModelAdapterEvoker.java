/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  acx
 *  bib
 *  bpy
 *  bqf
 *  bzf
 *  bzi
 */
package net.optifine.entity.model;

import net.optifine.entity.model.IEntityRenderer;
import net.optifine.entity.model.ModelAdapterIllager;

public class ModelAdapterEvoker
extends ModelAdapterIllager {
    public ModelAdapterEvoker() {
        super(acx.class, "evocation_illager", 0.5f);
    }

    @Override
    public bqf makeModel() {
        return new bpy(0.0f, 0.0f, 64, 64);
    }

    @Override
    public IEntityRenderer makeEntityRender(bqf modelBase, float shadowSize) {
        bzf renderManager = bib.z().ac();
        bzi render = new bzi(renderManager);
        render.f = modelBase;
        render.c = shadowSize;
        return render;
    }
}

