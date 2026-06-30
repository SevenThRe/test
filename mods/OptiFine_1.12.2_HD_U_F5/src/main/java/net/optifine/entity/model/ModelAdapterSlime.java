/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  adl
 *  bib
 *  bqf
 *  bqy
 *  brs
 *  bzf
 *  cat
 */
package net.optifine.entity.model;

import net.optifine.entity.model.IEntityRenderer;
import net.optifine.entity.model.ModelAdapter;
import net.optifine.reflect.Reflector;

public class ModelAdapterSlime
extends ModelAdapter {
    public ModelAdapterSlime() {
        super(adl.class, "slime", 0.25f);
    }

    @Override
    public bqf makeModel() {
        return new bqy(16);
    }

    @Override
    public brs getModelRenderer(bqf model, String modelPart) {
        if (!(model instanceof bqy)) {
            return null;
        }
        bqy modelSlime = (bqy)model;
        if (modelPart.equals("body")) {
            return (brs)Reflector.getFieldValue(modelSlime, Reflector.ModelSlime_ModelRenderers, 0);
        }
        if (modelPart.equals("left_eye")) {
            return (brs)Reflector.getFieldValue(modelSlime, Reflector.ModelSlime_ModelRenderers, 1);
        }
        if (modelPart.equals("right_eye")) {
            return (brs)Reflector.getFieldValue(modelSlime, Reflector.ModelSlime_ModelRenderers, 2);
        }
        if (modelPart.equals("mouth")) {
            return (brs)Reflector.getFieldValue(modelSlime, Reflector.ModelSlime_ModelRenderers, 3);
        }
        return null;
    }

    @Override
    public String[] getModelRendererNames() {
        return new String[]{"body", "left_eye", "right_eye", "mouth"};
    }

    @Override
    public IEntityRenderer makeEntityRender(bqf modelBase, float shadowSize) {
        bzf renderManager = bib.z().ac();
        cat render = new cat(renderManager);
        render.f = modelBase;
        render.c = shadowSize;
        return render;
    }
}

