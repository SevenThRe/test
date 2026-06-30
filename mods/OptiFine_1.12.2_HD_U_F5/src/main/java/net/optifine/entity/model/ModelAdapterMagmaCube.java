/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  add
 *  bib
 *  bqa
 *  bqf
 *  brs
 *  bzf
 *  bzx
 */
package net.optifine.entity.model;

import net.optifine.entity.model.IEntityRenderer;
import net.optifine.entity.model.ModelAdapter;
import net.optifine.reflect.Reflector;

public class ModelAdapterMagmaCube
extends ModelAdapter {
    public ModelAdapterMagmaCube() {
        super(add.class, "magma_cube", 0.5f);
    }

    @Override
    public bqf makeModel() {
        return new bqa();
    }

    @Override
    public brs getModelRenderer(bqf model, String modelPart) {
        if (!(model instanceof bqa)) {
            return null;
        }
        bqa modelMagmaCube = (bqa)model;
        if (modelPart.equals("core")) {
            return (brs)Reflector.getFieldValue(modelMagmaCube, Reflector.ModelMagmaCube_core);
        }
        String PREFIX_SEGMENTS = "segment";
        if (modelPart.startsWith(PREFIX_SEGMENTS)) {
            brs[] segments = (brs[])Reflector.getFieldValue(modelMagmaCube, Reflector.ModelMagmaCube_segments);
            if (segments == null) {
                return null;
            }
            String numStr = modelPart.substring(PREFIX_SEGMENTS.length());
            int index = Config.parseInt(numStr, -1);
            if (--index < 0 || index >= segments.length) {
                return null;
            }
            return segments[index];
        }
        return null;
    }

    @Override
    public String[] getModelRendererNames() {
        return new String[]{"core", "segment1", "segment2", "segment3", "segment4", "segment5", "segment6", "segment7", "segment8"};
    }

    @Override
    public IEntityRenderer makeEntityRender(bqf modelBase, float shadowSize) {
        bzf renderManager = bib.z().ac();
        bzx render = new bzx(renderManager);
        render.f = modelBase;
        render.c = shadowSize;
        return render;
    }
}

