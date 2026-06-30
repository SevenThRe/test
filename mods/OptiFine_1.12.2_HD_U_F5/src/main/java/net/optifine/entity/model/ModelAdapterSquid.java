/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  aaj
 *  bib
 *  bqf
 *  brb
 *  brs
 *  bzf
 *  cax
 */
package net.optifine.entity.model;

import net.optifine.entity.model.IEntityRenderer;
import net.optifine.entity.model.ModelAdapter;
import net.optifine.reflect.Reflector;

public class ModelAdapterSquid
extends ModelAdapter {
    public ModelAdapterSquid() {
        super(aaj.class, "squid", 0.7f);
    }

    @Override
    public bqf makeModel() {
        return new brb();
    }

    @Override
    public brs getModelRenderer(bqf model, String modelPart) {
        if (!(model instanceof brb)) {
            return null;
        }
        brb modelSquid = (brb)model;
        if (modelPart.equals("body")) {
            return (brs)Reflector.getFieldValue(modelSquid, Reflector.ModelSquid_body);
        }
        String PREFIX_TENTACLE = "tentacle";
        if (modelPart.startsWith(PREFIX_TENTACLE)) {
            brs[] tentacles = (brs[])Reflector.getFieldValue(modelSquid, Reflector.ModelSquid_tentacles);
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
        return new String[]{"body", "tentacle1", "tentacle2", "tentacle3", "tentacle4", "tentacle5", "tentacle6", "tentacle7", "tentacle8"};
    }

    @Override
    public IEntityRenderer makeEntityRender(bqf modelBase, float shadowSize) {
        bzf renderManager = bib.z().ac();
        cax render = new cax(renderManager);
        render.f = modelBase;
        render.c = shadowSize;
        return render;
    }
}

