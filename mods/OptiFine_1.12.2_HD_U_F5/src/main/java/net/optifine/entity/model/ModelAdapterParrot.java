/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  aac
 *  bib
 *  bqf
 *  bqh
 *  brs
 *  bzf
 *  caj
 */
package net.optifine.entity.model;

import net.optifine.entity.model.IEntityRenderer;
import net.optifine.entity.model.ModelAdapter;
import net.optifine.reflect.Reflector;

public class ModelAdapterParrot
extends ModelAdapter {
    public ModelAdapterParrot() {
        super(aac.class, "parrot", 0.3f);
    }

    @Override
    public bqf makeModel() {
        return new bqh();
    }

    @Override
    public brs getModelRenderer(bqf model, String modelPart) {
        if (!(model instanceof bqh)) {
            return null;
        }
        bqh modelParrot = (bqh)model;
        if (modelPart.equals("body")) {
            return (brs)Reflector.getFieldValue(modelParrot, Reflector.ModelParrot_ModelRenderers, 0);
        }
        if (modelPart.equals("tail")) {
            return (brs)Reflector.getFieldValue(modelParrot, Reflector.ModelParrot_ModelRenderers, 1);
        }
        if (modelPart.equals("left_wing")) {
            return (brs)Reflector.getFieldValue(modelParrot, Reflector.ModelParrot_ModelRenderers, 2);
        }
        if (modelPart.equals("right_wing")) {
            return (brs)Reflector.getFieldValue(modelParrot, Reflector.ModelParrot_ModelRenderers, 3);
        }
        if (modelPart.equals("head")) {
            return (brs)Reflector.getFieldValue(modelParrot, Reflector.ModelParrot_ModelRenderers, 4);
        }
        if (modelPart.equals("left_leg")) {
            return (brs)Reflector.getFieldValue(modelParrot, Reflector.ModelParrot_ModelRenderers, 9);
        }
        if (modelPart.equals("right_leg")) {
            return (brs)Reflector.getFieldValue(modelParrot, Reflector.ModelParrot_ModelRenderers, 10);
        }
        return null;
    }

    @Override
    public String[] getModelRendererNames() {
        return new String[]{"body", "tail", "left_wing", "right_wing", "head", "left_leg", "right_leg"};
    }

    @Override
    public IEntityRenderer makeEntityRender(bqf modelBase, float shadowSize) {
        bzf renderManager = bib.z().ac();
        caj render = new caj(renderManager);
        render.f = modelBase;
        render.c = shadowSize;
        return render;
    }
}

