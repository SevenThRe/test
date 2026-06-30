/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  acv
 *  bib
 *  bpr
 *  bqf
 *  brs
 *  bze
 *  bzf
 */
package net.optifine.entity.model;

import net.optifine.entity.model.IEntityRenderer;
import net.optifine.entity.model.ModelAdapter;
import net.optifine.reflect.Reflector;

public class ModelAdapterEndermite
extends ModelAdapter {
    public ModelAdapterEndermite() {
        super(acv.class, "endermite", 0.3f);
    }

    @Override
    public bqf makeModel() {
        return new bpr();
    }

    @Override
    public brs getModelRenderer(bqf model, String modelPart) {
        if (!(model instanceof bpr)) {
            return null;
        }
        bpr modelEnderMite = (bpr)model;
        String PREFIX_BODY = "body";
        if (modelPart.startsWith(PREFIX_BODY)) {
            brs[] bodyParts = (brs[])Reflector.getFieldValue(modelEnderMite, Reflector.ModelEnderMite_bodyParts);
            if (bodyParts == null) {
                return null;
            }
            String numStr = modelPart.substring(PREFIX_BODY.length());
            int index = Config.parseInt(numStr, -1);
            if (--index < 0 || index >= bodyParts.length) {
                return null;
            }
            return bodyParts[index];
        }
        return null;
    }

    @Override
    public String[] getModelRendererNames() {
        return new String[]{"body1", "body2", "body3", "body4"};
    }

    @Override
    public IEntityRenderer makeEntityRender(bqf modelBase, float shadowSize) {
        bzf renderManager = bib.z().ac();
        bze render = new bze(renderManager);
        render.f = modelBase;
        render.c = shadowSize;
        return render;
    }
}

