/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  eos.moe.dragoncore.api.model.AnimationEntityModel
 *  eos.moe.dragoncore.api.model.AnimationModel
 *  eos.moe.dragoncore.api.model.AnimationModelRenderer
 *  net.minecraft.entity.Entity
 */
package goblinbob.mobends.dragon;

import eos.moe.dragoncore.api.model.AnimationModel;
import eos.moe.dragoncore.api.model.AnimationModelRenderer;
import goblinbob.mobends.dragon.HandItemRotationRenderer;
import goblinbob.mobends.dragon.ItemRotationRenderer;
import goblinbob.mobends.dragon.PlayerRenderer;
import goblinbob.mobends.standard.data.BipedEntityData;
import net.minecraft.entity.Entity;

public class AnimationEntityModel
implements eos.moe.dragoncore.api.model.AnimationEntityModel,
AnimationModel {
    private final BipedEntityData<?> modelPlayer;

    public AnimationEntityModel(BipedEntityData<?> dataBiped) {
        this.modelPlayer = dataBiped;
    }

    public AnimationModel getBaseModel() {
        return this;
    }

    public Entity getEntity() {
        return null;
    }

    public void setEntity(Entity e2) {
    }

    public AnimationModelRenderer getPiece(String pieceName) {
        switch (pieceName.toLowerCase()) {
            case "root": {
                return new PlayerRenderer(this.modelPlayer.centerRotation, this.modelPlayer.globalOffset, this.modelPlayer.scale);
            }
            case "head": {
                return this.modelPlayer.head;
            }
            case "body": {
                return this.modelPlayer.body;
            }
            case "leftarm": {
                return this.modelPlayer.leftArm;
            }
            case "leftforearm": {
                return this.modelPlayer.leftForeArm;
            }
            case "rightarm": {
                return this.modelPlayer.rightArm;
            }
            case "rightforearm": {
                return this.modelPlayer.rightForeArm;
            }
            case "leftleg": {
                return this.modelPlayer.leftLeg;
            }
            case "leftforeleg": {
                return this.modelPlayer.leftForeLeg;
            }
            case "rightleg": {
                return this.modelPlayer.rightLeg;
            }
            case "rightforeleg": {
                return this.modelPlayer.rightForeLeg;
            }
            case "rightitem": {
                return new HandItemRotationRenderer(this.modelPlayer.renderRightItemRotation);
            }
            case "leftitem": {
                return new HandItemRotationRenderer(this.modelPlayer.renderLeftItemRotation);
            }
            case "itemright": {
                return new ItemRotationRenderer(this.modelPlayer.rightItemOffset, this.modelPlayer.rightItemRotation, this.modelPlayer.rightItemScale);
            }
            case "itemleft": {
                return new ItemRotationRenderer(this.modelPlayer.leftItemOffset, this.modelPlayer.leftItemRotation, this.modelPlayer.leftItemScale);
            }
        }
        return null;
    }

    public void clear() {
        this.modelPlayer.head.clear();
        this.modelPlayer.body.clear();
        this.modelPlayer.leftArm.clear();
        this.modelPlayer.leftForeArm.clear();
        this.modelPlayer.rightArm.clear();
        this.modelPlayer.rightForeArm.clear();
        this.modelPlayer.leftLeg.clear();
        this.modelPlayer.leftForeLeg.clear();
        this.modelPlayer.rightLeg.clear();
        this.modelPlayer.rightForeLeg.clear();
        this.modelPlayer.rightItemOffset.set(0.0, 0.0, 0.0);
        this.modelPlayer.rightItemRotation.set(0.0, 0.0, 0.0);
        this.modelPlayer.rightItemScale.set(0.0, 0.0, 0.0);
        this.modelPlayer.leftItemOffset.set(0.0, 0.0, 0.0);
        this.modelPlayer.leftItemRotation.set(0.0, 0.0, 0.0);
        this.modelPlayer.leftItemScale.set(0.0, 0.0, 0.0);
    }
}

