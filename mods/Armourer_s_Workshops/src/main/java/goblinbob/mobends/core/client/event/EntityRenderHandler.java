/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.entity.EntityPlayerSP
 *  net.minecraft.client.renderer.GlStateManager
 *  net.minecraft.client.renderer.entity.RenderLivingBase
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.init.SoundEvents
 *  net.minecraft.item.ItemStack
 *  net.minecraftforge.client.event.RenderPlayerEvent$Post
 *  net.minecraftforge.client.event.RenderPlayerEvent$Pre
 *  net.minecraftforge.fml.common.eventhandler.EventPriority
 *  net.minecraftforge.fml.common.eventhandler.SubscribeEvent
 *  net.minecraftforge.fml.common.gameevent.TickEvent$ClientTickEvent
 *  net.minecraftforge.fml.relauncher.ReflectionHelper
 */
package goblinbob.mobends.core.client.event;

import baka.animation.controller.CPCManager;
import goblinbob.mobends.core.bender.EntityBender;
import goblinbob.mobends.core.bender.EntityBenderRegistry;
import goblinbob.mobends.core.data.EntityData;
import goblinbob.mobends.core.mutators.Mutator;
import goblinbob.mobends.dragon.AnimationHelper;
import goblinbob.mobends.standard.animation.controller.PlayerController;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.UUID;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderLivingBase;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemStack;
import net.minecraftforge.client.event.RenderPlayerEvent;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;
import net.minecraftforge.fml.relauncher.ReflectionHelper;

public class EntityRenderHandler {
    private static Map<UUID, ItemStack> mainHand = new HashMap<UUID, ItemStack>();
    private static Map<UUID, ItemStack> offHand = new HashMap<UUID, ItemStack>();

    @SubscribeEvent
    public void onTick(TickEvent.ClientTickEvent e2) {
        EntityPlayerSP player = Minecraft.getMinecraft().player;
        if (player == null) {
            return;
        }
        PlayerController playerController = CPCManager.get(player.getUniqueID());
        if (playerController == null) {
            return;
        }
        if (playerController.getActiveItem().isEmpty()) {
            return;
        }
        if (playerController.startTime == -1L) {
            return;
        }
        if (playerController.startTime % 10L == 0L) {
            ItemStack activeItem = playerController.getActiveItem();
            try {
                Random rand = (Random)ReflectionHelper.getPrivateValue(Entity.class, (Object)player, (String[])new String[]{"rand", "rand"});
                if (activeItem.getTagCompound() != null && activeItem.getTagCompound().hasKey("potion")) {
                    player.playSound(SoundEvents.ENTITY_GENERIC_DRINK, 0.5f, player.world.rand.nextFloat() * 0.1f + 0.9f);
                } else {
                    player.playSound(SoundEvents.ENTITY_GENERIC_EAT, 0.5f + 0.5f * (float)rand.nextInt(2), (rand.nextFloat() - rand.nextFloat()) * 0.2f + 1.0f);
                }
            }
            catch (Exception ex) {
                ex.printStackTrace();
            }
        }
        ++playerController.startTime;
    }

    @SubscribeEvent(priority=EventPriority.LOWEST)
    public void beforeLivingRender(RenderPlayerEvent.Pre event) {
        EntityRenderHandler.beforeRender(event.getEntityLiving(), (RenderLivingBase)event.getRenderer(), event.getPartialRenderTick());
    }

    @SubscribeEvent
    public void afterLivingRender(RenderPlayerEvent.Post event) {
        EntityRenderHandler.postRender(event.getEntityLiving(), (RenderLivingBase)event.getRenderer(), event.getPartialRenderTick());
    }

    public static void beforeRender(EntityLivingBase living, RenderLivingBase renderer, float pt) {
        EntityBender<EntityLivingBase> entityBender = EntityBenderRegistry.instance.getForEntity(living);
        if (entityBender == null) {
            return;
        }
        EntityPlayer player = (EntityPlayer)living;
        mainHand.put(player.getUniqueID(), player.getHeldItemMainhand());
        offHand.put(player.getUniqueID(), player.getHeldItemOffhand());
        PlayerController playerController = CPCManager.get(player.getUniqueID());
        if (playerController != null && !playerController.getActiveItem().isEmpty()) {
            player.inventory.setInventorySlotContents(player.inventory.currentItem, playerController.getActiveItem());
            player.inventory.offHandInventory.set(0, (Object)ItemStack.EMPTY);
        }
        GlStateManager.pushMatrix();
        if (entityBender.isAnimated()) {
            AnimationHelper.postRender(living, renderer);
            AnimationHelper.preRender(living, renderer);
            if (entityBender.applyMutation((RenderLivingBase<EntityLivingBase>)renderer, living, pt)) {
                Mutator<?, ?, ?> mutator = entityBender.getMutator((RenderLivingBase<EntityLivingBase>)renderer);
                Object data = mutator.getData(living);
                entityBender.beforeRender((EntityData<EntityLivingBase>)data, living, pt);
            }
        } else {
            entityBender.deapplyMutation((RenderLivingBase<EntityLivingBase>)renderer, living);
        }
    }

    public static void postRender(EntityLivingBase living, RenderLivingBase renderer, float pt) {
        EntityPlayer player = (EntityPlayer)living;
        ItemStack hand = mainHand.getOrDefault(player.getUniqueID(), ItemStack.EMPTY);
        ItemStack off = offHand.getOrDefault(player.getUniqueID(), ItemStack.EMPTY);
        player.inventory.setInventorySlotContents(player.inventory.currentItem, hand);
        player.inventory.offHandInventory.set(0, (Object)off);
        EntityBender<EntityLivingBase> entityBender = EntityBenderRegistry.instance.getForEntity(living);
        if (entityBender == null) {
            return;
        }
        entityBender.afterRender(living, pt);
        AnimationHelper.postRender(living, renderer);
        GlStateManager.popMatrix();
    }
}

