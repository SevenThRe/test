/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  abx
 *  bib
 *  bqf
 *  brj
 *  brs
 *  bzf
 *  cbj
 */
package net.optifine.entity.model;

import net.optifine.entity.model.IEntityRenderer;
import net.optifine.entity.model.ModelAdapter;
import net.optifine.reflect.Reflector;

public class ModelAdapterWither
extends ModelAdapter {
    public ModelAdapterWither() {
        super(abx.class, "wither", 0.5f);
    }

    @Override
    public bqf makeModel() {
        return new brj(0.0f);
    }

    @Override
    public brs getModelRenderer(bqf model, String modelPart) {
        if (!(model instanceof brj)) {
            return null;
        }
        brj modelWither = (brj)model;
        String PREFIX_BODY = "body";
        if (modelPart.startsWith(PREFIX_BODY)) {
            brs[] bodyParts = (brs[])Reflector.getFieldValue(modelWither, Reflector.ModelWither_bodyParts);
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
        String PREFIX_HEAD = "head";
        if (modelPart.startsWith(PREFIX_HEAD)) {
            brs[] heads = (brs[])Reflector.getFieldValue(modelWither, Reflector.ModelWither_heads);
            if (heads == null) {
                return null;
            }
            String numStr = modelPart.substring(PREFIX_HEAD.length());
            int index = Config.parseInt(numStr, -1);
            if (--index < 0 || index >= heads.length) {
                return null;
            }
            return heads[index];
        }
        return null;
    }

    @Override
    public String[] getModelRendererNames() {
        return new String[]{"body1", "body2", "body3", "head1", "head2", "head3"};
    }

    @Override
    public IEntityRenderer makeEntityRender(bqf modelBase, float shadowSize) {
        bzf renderManager = bib.z().ac();
        cbj render = new cbj(renderManager);
        render.f = modelBase;
        render.c = shadowSize;
        return render;
    }
}

