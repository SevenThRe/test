/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  aag
 *  bib
 *  bqf
 *  bqo
 *  bqp
 *  bzf
 *  bzg
 *  cao
 *  ccg
 *  cch
 */
package net.optifine.entity.model;

import java.util.Iterator;
import java.util.List;
import net.optifine.entity.model.IEntityRenderer;
import net.optifine.entity.model.ModelAdapterQuadruped;

public class ModelAdapterSheepWool
extends ModelAdapterQuadruped {
    public ModelAdapterSheepWool() {
        super(aag.class, "sheep_wool", 0.7f);
    }

    @Override
    public bqf makeModel() {
        return new bqo();
    }

    @Override
    public IEntityRenderer makeEntityRender(bqf modelBase, float shadowSize) {
        bzf renderManager = bib.z().ac();
        bzg render = (bzg)renderManager.getEntityRenderMap().get(aag.class);
        if (!(render instanceof cao)) {
            Config.warn("Not a RenderSheep: " + render);
            return null;
        }
        if (render.getEntityClass() == null) {
            cao rs = new cao(renderManager);
            rs.f = new bqp();
            rs.c = 0.7f;
            render = rs;
        }
        cao renderSheep = (cao)render;
        List list = renderSheep.getLayerRenderers();
        Iterator it = list.iterator();
        while (it.hasNext()) {
            ccg layerRenderer = (ccg)it.next();
            if (!(layerRenderer instanceof cch)) continue;
            it.remove();
        }
        cch layer = new cch(renderSheep);
        layer.c = (bqo)modelBase;
        renderSheep.a((ccg)layer);
        return renderSheep;
    }
}

