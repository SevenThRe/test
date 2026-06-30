/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  goblinbob.mobends.standard.data.BipedEntityData
 *  net.minecraft.entity.Entity
 */
package eos.moe.dragoncore;

import eos.moe.dragoncore.api.model.AnimationEntityModel;
import eos.moe.dragoncore.api.model.AnimationModel;
import eos.moe.dragoncore.api.model.AnimationModelRenderer;
import eos.moe.dragoncore.wg;
import goblinbob.mobends.standard.data.BipedEntityData;
import net.minecraft.entity.Entity;

public class gk
implements AnimationEntityModel,
AnimationModel {
    private BipedEntityData<?> ALLATORIxDEMO;

    public gk(BipedEntityData<?> a2) {
        gk a3;
        a3.ALLATORIxDEMO = a2;
    }

    @Override
    public AnimationModel getBaseModel() {
        gk a2;
        return a2;
    }

    @Override
    public Entity getEntity() {
        return null;
    }

    @Override
    public void setEntity(Entity a2) {
    }

    @Override
    public AnimationModelRenderer getPiece(String a2) {
        switch (a2.toLowerCase()) {
            case "head": {
                gk a3;
                return (AnimationModelRenderer)a3.ALLATORIxDEMO.head;
            }
            case "body": {
                gk a3;
                return (AnimationModelRenderer)a3.ALLATORIxDEMO.body;
            }
            case "leftarm": {
                gk a3;
                return (AnimationModelRenderer)a3.ALLATORIxDEMO.leftArm;
            }
            case "leftforearm": {
                gk a3;
                return (AnimationModelRenderer)a3.ALLATORIxDEMO.leftForeArm;
            }
            case "rightarm": {
                gk a3;
                return (AnimationModelRenderer)a3.ALLATORIxDEMO.rightArm;
            }
            case "rightforearm": {
                gk a3;
                return (AnimationModelRenderer)a3.ALLATORIxDEMO.rightForeArm;
            }
            case "leftleg": {
                gk a3;
                return (AnimationModelRenderer)a3.ALLATORIxDEMO.leftLeg;
            }
            case "leftforeleg": {
                gk a3;
                return (AnimationModelRenderer)a3.ALLATORIxDEMO.leftForeLeg;
            }
            case "rightleg": {
                gk a3;
                return (AnimationModelRenderer)a3.ALLATORIxDEMO.rightLeg;
            }
            case "rightforeleg": {
                gk a3;
                return (AnimationModelRenderer)a3.ALLATORIxDEMO.rightForeLeg;
            }
            case "rightitem": {
                gk a3;
                return new wg(a3.ALLATORIxDEMO.renderRightItemRotation);
            }
        }
        return null;
    }

    @Override
    public void clear() {
        gk a2;
        ((AnimationModelRenderer)a2.ALLATORIxDEMO.head).clear();
        ((AnimationModelRenderer)a2.ALLATORIxDEMO.body).clear();
        ((AnimationModelRenderer)a2.ALLATORIxDEMO.leftArm).clear();
        ((AnimationModelRenderer)a2.ALLATORIxDEMO.leftForeArm).clear();
        ((AnimationModelRenderer)a2.ALLATORIxDEMO.rightArm).clear();
        ((AnimationModelRenderer)a2.ALLATORIxDEMO.rightForeArm).clear();
        ((AnimationModelRenderer)a2.ALLATORIxDEMO.leftLeg).clear();
        ((AnimationModelRenderer)a2.ALLATORIxDEMO.leftForeLeg).clear();
        ((AnimationModelRenderer)a2.ALLATORIxDEMO.rightLeg).clear();
        ((AnimationModelRenderer)a2.ALLATORIxDEMO.rightForeLeg).clear();
    }
}

