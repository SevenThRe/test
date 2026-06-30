/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.model.ModelBase
 *  net.minecraft.client.renderer.GlStateManager
 *  net.minecraft.entity.Entity
 */
package eos.moe.dragoncore;

import eos.moe.dragoncore.api.model.AnimationEntityModel;
import eos.moe.dragoncore.api.model.AnimationModel;
import eos.moe.dragoncore.ju;
import eos.moe.dragoncore.vy;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.Entity;

public class tt
extends ModelBase
implements AnimationEntityModel {
    private vy k;
    private Entity ALLATORIxDEMO;

    public tt(vy a2) {
        tt a3;
        a3.k = a2;
        for (ju ju2 : a3.k.ALLATORIxDEMO()) {
        }
        a2.ALLATORIxDEMO();
    }

    public void render(Entity a2, float a3, float a4, float a5, float a6, float a7, float a8) {
        tt a9;
        a9.setRotationAngles(a3, a4, a5, a6, a7, a8, a2);
        GlStateManager.pushMatrix();
        a9.k.ALLATORIxDEMO(a8);
        GlStateManager.popMatrix();
    }

    public void setRotationAngles(float a2, float a3, float a4, float a5, float a6, float a7, Entity a8) {
        tt a9;
        super.setRotationAngles(a2, a3, a4, a5, a6, a7, a8);
    }

    @Override
    public AnimationModel getBaseModel() {
        tt a2;
        return a2.k;
    }

    @Override
    public Entity getEntity() {
        tt a2;
        return a2.ALLATORIxDEMO;
    }

    @Override
    public void setEntity(Entity a2) {
        a.ALLATORIxDEMO = a2;
    }
}

