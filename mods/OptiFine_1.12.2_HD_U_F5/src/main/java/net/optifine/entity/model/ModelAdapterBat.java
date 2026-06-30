/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  bib
 *  bpg
 *  bqf
 *  brs
 *  byr
 *  bzf
 *  zt
 */
package net.optifine.entity.model;

import net.optifine.entity.model.IEntityRenderer;
import net.optifine.entity.model.ModelAdapter;
import net.optifine.reflect.Reflector;

public class ModelAdapterBat
extends ModelAdapter {
    public ModelAdapterBat() {
        super(zt.class, "bat", 0.25f);
    }

    @Override
    public bqf makeModel() {
        return new bpg();
    }

    @Override
    public brs getModelRenderer(bqf model, String modelPart) {
        if (!(model instanceof bpg)) {
            return null;
        }
        bpg modelBat = (bpg)model;
        if (modelPart.equals("head")) {
            return (brs)Reflector.getFieldValue(modelBat, Reflector.ModelBat_ModelRenderers, 0);
        }
        if (modelPart.equals("body")) {
            return (brs)Reflector.getFieldValue(modelBat, Reflector.ModelBat_ModelRenderers, 1);
        }
        if (modelPart.equals("right_wing")) {
            return (brs)Reflector.getFieldValue(modelBat, Reflector.ModelBat_ModelRenderers, 2);
        }
        if (modelPart.equals("left_wing")) {
            return (brs)Reflector.getFieldValue(modelBat, Reflector.ModelBat_ModelRenderers, 3);
        }
        if (modelPart.equals("outer_right_wing")) {
            return (brs)Reflector.getFieldValue(modelBat, Reflector.ModelBat_ModelRenderers, 4);
        }
        if (modelPart.equals("outer_left_wing")) {
            return (brs)Reflector.getFieldValue(modelBat, Reflector.ModelBat_ModelRenderers, 5);
        }
        return null;
    }

    @Override
    public String[] getModelRendererNames() {
        return new String[]{"head", "body", "right_wing", "left_wing", "outer_right_wing", "outer_left_wing"};
    }

    @Override
    public IEntityRenderer makeEntityRender(bqf modelBase, float shadowSize) {
        bzf renderManager = bib.z().ac();
        byr render = new byr(renderManager);
        render.f = modelBase;
        render.c = shadowSize;
        return render;
    }
}

