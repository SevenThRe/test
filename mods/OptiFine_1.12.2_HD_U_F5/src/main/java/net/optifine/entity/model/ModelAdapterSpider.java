/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  adn
 *  bib
 *  bqf
 *  bra
 *  brs
 *  bzf
 *  caw
 */
package net.optifine.entity.model;

import net.optifine.entity.model.IEntityRenderer;
import net.optifine.entity.model.ModelAdapter;

public class ModelAdapterSpider
extends ModelAdapter {
    public ModelAdapterSpider() {
        super(adn.class, "spider", 1.0f);
    }

    protected ModelAdapterSpider(Class entityClass, String name, float shadowSize) {
        super(entityClass, name, shadowSize);
    }

    @Override
    public bqf makeModel() {
        return new bra();
    }

    @Override
    public brs getModelRenderer(bqf model, String modelPart) {
        if (!(model instanceof bra)) {
            return null;
        }
        bra modelSpider = (bra)model;
        if (modelPart.equals("head")) {
            return modelSpider.a;
        }
        if (modelPart.equals("neck")) {
            return modelSpider.b;
        }
        if (modelPart.equals("body")) {
            return modelSpider.c;
        }
        if (modelPart.equals("leg1")) {
            return modelSpider.d;
        }
        if (modelPart.equals("leg2")) {
            return modelSpider.e;
        }
        if (modelPart.equals("leg3")) {
            return modelSpider.f;
        }
        if (modelPart.equals("leg4")) {
            return modelSpider.g;
        }
        if (modelPart.equals("leg5")) {
            return modelSpider.h;
        }
        if (modelPart.equals("leg6")) {
            return modelSpider.i;
        }
        if (modelPart.equals("leg7")) {
            return modelSpider.j;
        }
        if (modelPart.equals("leg8")) {
            return modelSpider.k;
        }
        return null;
    }

    @Override
    public String[] getModelRendererNames() {
        return new String[]{"head", "neck", "body", "leg1", "leg2", "leg3", "leg4", "leg5", "leg6", "leg7", "leg8"};
    }

    @Override
    public IEntityRenderer makeEntityRender(bqf modelBase, float shadowSize) {
        bzf renderManager = bib.z().ac();
        caw render = new caw(renderManager);
        render.f = modelBase;
        render.c = shadowSize;
        return render;
    }
}

