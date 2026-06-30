/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  afe
 *  bib
 *  bqe
 *  bqf
 *  brs
 *  bzf
 *  cad
 */
package net.optifine.entity.model;

import net.optifine.entity.model.IEntityRenderer;
import net.optifine.entity.model.ModelAdapter;
import net.optifine.reflect.Reflector;

public class ModelAdapterMinecart
extends ModelAdapter {
    public ModelAdapterMinecart() {
        super(afe.class, "minecart", 0.5f);
    }

    protected ModelAdapterMinecart(Class entityClass, String name, float shadow) {
        super(entityClass, name, shadow);
    }

    @Override
    public bqf makeModel() {
        return new bqe();
    }

    @Override
    public brs getModelRenderer(bqf model, String modelPart) {
        if (!(model instanceof bqe)) {
            return null;
        }
        bqe modelMinecart = (bqe)model;
        if (modelPart.equals("bottom")) {
            return modelMinecart.a[0];
        }
        if (modelPart.equals("back")) {
            return modelMinecart.a[1];
        }
        if (modelPart.equals("front")) {
            return modelMinecart.a[2];
        }
        if (modelPart.equals("right")) {
            return modelMinecart.a[3];
        }
        if (modelPart.equals("left")) {
            return modelMinecart.a[4];
        }
        if (modelPart.equals("dirt")) {
            return modelMinecart.a[5];
        }
        return null;
    }

    @Override
    public String[] getModelRendererNames() {
        return new String[]{"bottom", "back", "front", "right", "left", "dirt"};
    }

    @Override
    public IEntityRenderer makeEntityRender(bqf modelBase, float shadowSize) {
        bzf renderManager = bib.z().ac();
        cad render = new cad(renderManager);
        if (!Reflector.RenderMinecart_modelMinecart.exists()) {
            Config.warn("Field not found: RenderMinecart.modelMinecart");
            return null;
        }
        Reflector.setFieldValue(render, Reflector.RenderMinecart_modelMinecart, modelBase);
        render.c = shadowSize;
        return render;
    }
}

