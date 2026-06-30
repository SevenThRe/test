/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  eos.moe.dragoncore.api.CoreAPI
 *  eos.moe.dragoncore.api.model.AnimationEntityModel
 *  eos.moe.dragoncore.api.model.AnimationManager
 *  net.minecraft.client.model.ModelPlayer
 *  net.minecraft.client.renderer.entity.RenderLivingBase
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemAxe
 *  net.minecraft.item.ItemHoe
 *  net.minecraft.item.ItemPickaxe
 *  net.minecraft.item.ItemSpade
 *  net.minecraft.item.ItemSword
 *  net.minecraft.util.EnumHand
 */
package goblinbob.mobends.dragon;

import eos.moe.dragoncore.api.CoreAPI;
import eos.moe.dragoncore.api.model.AnimationManager;
import goblinbob.mobends.core.bender.EntityBender;
import goblinbob.mobends.core.bender.EntityBenderRegistry;
import goblinbob.mobends.core.client.model.ModelPart;
import goblinbob.mobends.core.data.EntityDatabase;
import goblinbob.mobends.dragon.AnimationEntityModel;
import goblinbob.mobends.standard.data.BipedEntityData;
import java.util.UUID;
import net.minecraft.client.model.ModelPlayer;
import net.minecraft.client.renderer.entity.RenderLivingBase;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemAxe;
import net.minecraft.item.ItemHoe;
import net.minecraft.item.ItemPickaxe;
import net.minecraft.item.ItemSpade;
import net.minecraft.item.ItemSword;
import net.minecraft.util.EnumHand;

public class AnimationHelper {
    private static int needPlaySwordTrailMethod = 0;

    public static boolean isOnPlayAnimation(Entity entity) {
        if (entity instanceof EntityPlayer) {
            AnimationManager animationManager = CoreAPI.getAnimationManager((UUID)entity.getUniqueID());
            return animationManager != null && animationManager.isOnPlayAnimation();
        }
        return false;
    }

    public static void preRender(EntityLivingBase living, RenderLivingBase<?> renderer) {
        AnimationHelper.preRender(living, renderer, false);
    }

    public static void preRender(EntityLivingBase living, RenderLivingBase<?> renderer, boolean onlyAnimation) {
        EntityBender<EntityLivingBase> entityBender = EntityBenderRegistry.instance.getForEntity(living);
        if (entityBender == null) {
            return;
        }
        if (!(renderer.getMainModel() instanceof ModelPlayer)) {
            return;
        }
        AnimationManager manager = CoreAPI.getAnimationManager((UUID)living.getUniqueID());
        if (manager == null) {
            return;
        }
        ModelPlayer mainModel = (ModelPlayer)renderer.getMainModel();
        if (mainModel.bipedBody instanceof ModelPart) {
            Item item;
            Object entityData = EntityDatabase.instance.get(living);
            if (entityData == null) {
                return;
            }
            BipedEntityData bipedData = (BipedEntityData)entityData;
            AnimationEntityModel animationEntityModel = new AnimationEntityModel(bipedData);
            if (manager.isOnPlayAnimation() && !onlyAnimation) {
                bipedData.localOffset.lock(0.0f, 0.0f, 0.0f);
                bipedData.globalOffset.lock(0.0f, 0.0f, 0.0f);
                bipedData.centerRotation.lock(0.0f, 0.0f, 0.0f);
                bipedData.renderRotation.lock(0.0f, 0.0f, 0.0f);
                bipedData.scale.set(1.0, 1.0, 1.0);
            }
            manager.applyAnimation((eos.moe.dragoncore.api.model.AnimationEntityModel)animationEntityModel);
            boolean needPlaySwordTrail = false;
            if (needPlaySwordTrailMethod == 0) {
                try {
                    manager.getClass().getDeclaredMethod("needPlaySwordTrail", new Class[0]);
                    needPlaySwordTrailMethod = 1;
                }
                catch (Exception ex) {
                    needPlaySwordTrailMethod = -1;
                }
            } else if (needPlaySwordTrailMethod == -1) {
                needPlaySwordTrail = manager.isOnPlayAnimation();
            } else if (needPlaySwordTrailMethod == 1) {
                needPlaySwordTrail = manager.needPlaySwordTrail();
            }
            if (needPlaySwordTrail && !onlyAnimation && ((item = living.getHeldItem(EnumHand.MAIN_HAND).getItem()) instanceof ItemSword || item instanceof ItemAxe || item instanceof ItemHoe || item instanceof ItemSpade || item instanceof ItemPickaxe)) {
                bipedData.swordTrail.add(bipedData);
            }
        }
    }

    public static void postRender(EntityLivingBase living, RenderLivingBase<?> renderer) {
        EntityBender<EntityLivingBase> entityBender = EntityBenderRegistry.instance.getForEntity(living);
        if (entityBender == null) {
            return;
        }
        if (!(renderer.getMainModel() instanceof ModelPlayer)) {
            return;
        }
        ModelPlayer mainModel = (ModelPlayer)renderer.getMainModel();
        if (mainModel.bipedBody instanceof ModelPart) {
            Object entityData = EntityDatabase.instance.get(living);
            if (entityData == null) {
                return;
            }
            AnimationManager manager = CoreAPI.getAnimationManager((UUID)living.getUniqueID());
            if (manager == null) {
                return;
            }
            BipedEntityData bipedData = (BipedEntityData)entityData;
            bipedData.localOffset.unlock();
            bipedData.globalOffset.unlock();
            bipedData.centerRotation.unlock();
            bipedData.renderRotation.unlock();
            bipedData.renderRightItemRotation.unlock();
            bipedData.renderLeftItemRotation.unlock();
            bipedData.scale.set(1.0, 1.0, 1.0);
            new AnimationEntityModel(bipedData).clear();
        }
    }
}

