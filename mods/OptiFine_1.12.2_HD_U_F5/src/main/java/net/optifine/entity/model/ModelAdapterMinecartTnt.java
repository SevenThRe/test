/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  afm
 *  bib
 *  bqf
 *  bzf
 *  cbc
 */
package net.optifine.entity.model;

import net.optifine.entity.model.IEntityRenderer;
import net.optifine.entity.model.ModelAdapterMinecart;
import net.optifine.reflect.Reflector;

public class ModelAdapterMinecartTnt
extends ModelAdapterMinecart {
    public ModelAdapterMinecartTnt() {
        super(afm.class, "tnt_minecart", 0.5f);
    }

    @Override
    public IEntityRenderer makeEntityRender(bqf modelBase, float shadowSize) {
        bzf renderManager = bib.z().ac();
        cbc render = new cbc(renderManager);
        if (!Reflector.RenderMinecart_modelMinecart.exists()) {
            Config.warn("Field not found: RenderMinecart.modelMinecart");
            return null;
        }
        Reflector.setFieldValue(render, Reflector.RenderMinecart_modelMinecart, modelBase);
        render.c = shadowSize;
        return render;
    }
}

