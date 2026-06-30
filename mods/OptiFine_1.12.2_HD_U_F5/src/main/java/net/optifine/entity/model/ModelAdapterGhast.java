/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  acy
 *  bib
 *  bpt
 *  bqf
 *  brs
 *  bzf
 *  bzn
 */
package net.optifine.entity.model;

import net.optifine.entity.model.IEntityRenderer;
import net.optifine.entity.model.ModelAdapter;
import net.optifine.reflect.Reflector;

public class ModelAdapterGhast
extends ModelAdapter {
    public ModelAdapterGhast() {
        super(acy.class, "ghast", 0.5f);
    }

    @Override
    public bqf makeModel() {
        return new bpt();
    }

    @Override
    public brs getModelRenderer(bqf model, String modelPart) {
        if (!(model instanceof bpt)) {
            return null;
        }
        bpt modelGhast = (bpt)model;
        if (modelPart.equals("body")) {
            return (brs)Reflector.getFieldValue(modelGhast, Reflector.ModelGhast_body);
        }
        String PREFIX_TENTACLE = "tentacle";
        if (modelPart.startsWith(PREFIX_TENTACLE)) {
            brs[] tentacles = (brs[])Reflector.getFieldValue(modelGhast, Reflector.ModelGhast_tentacles);
            if (tentacles == null) {
                return null;
            }
            String numStr = modelPart.substring(PREFIX_TENTACLE.length());
            int index = Config.parseInt(numStr, -1);
            if (--index < 0 || index >= tentacles.length) {
                return null;
            }
            return tentacles[index];
        }
        return null;
    }

    @Override
    public String[] getModelRendererNames() {
        return new String[]{"body", "tentacle1", "tentacle2", "tentacle3", "tentacle4", "tentacle5", "tentacle6", "tentacle7", "tentacle8", "tentacle9"};
    }

    @Override
    public IEntityRenderer makeEntityRender(bqf modelBase, float shadowSize) {
        bzf renderManager = bib.z().ac();
        bzn render = new bzn(renderManager);
        render.f = modelBase;
        render.c = shadowSize;
        return render;
    }
}

