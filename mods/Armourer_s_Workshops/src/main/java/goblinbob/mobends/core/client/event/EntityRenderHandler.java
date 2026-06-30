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
        EntityPlayerSP player = Minecraft.func_71410_x().field_71439_g;
        if (player == null) {
            return;
        }
        PlayerController playerController = CPCManager.get(player.func_110124_au());
        if (playerController == null) {
            return;
        }
        if (playerController.getActiveItem().func_190926_b()) {
            return;
        }
        if (playerController.startTime == -1L) {
            return;
        }
        if (playerController.startTime % 10L == 0L) {
            ItemStack activeItem = playerController.getActiveItem();
            try {
                Random rand = (Random)ReflectionHelper.getPrivateValue(Entity.class, (Object)player, (String[])new String[]{"rand", "field_70146_Z"});
                if (activeItem.func_77978_p() != null && activeItem.func_77978_p().func_74764_b("potion")) {
                    player.func_184185_a(SoundEvents.field_187664_bz, 0.5f, player.field_70170_p.field_73012_v.nextFloat() * 0.1f + 0.9f);
                } else {
                    player.func_184185_a(SoundEvents.field_187537_bA, 0.5f + 0.5f * (float)rand.nextInt(2), (rand.nextFloat() - rand.nextFloat()) * 0.2f + 1.0f);
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
        mainHand.put(player.func_110124_au(), player.func_184614_ca());
        offHand.put(player.func_110124_au(), player.func_184592_cb());
        PlayerController playerController = CPCManager.get(player.func_110124_au());
        if (playerController != null && !playerController.getActiveItem().func_190926_b()) {
            player.field_71071_by.func_70299_a(player.field_71071_by.field_70461_c, playerController.getActiveItem());
            player.field_71071_by.field_184439_c.set(0, (Object)ItemStack.field_190927_a);
        }
        GlStateManager.func_179094_E();
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
        ItemStack hand = mainHand.getOrDefault(player.func_110124_au(), ItemStack.field_190927_a);
        ItemStack off = offHand.getOrDefault(player.func_110124_au(), ItemStack.field_190927_a);
        player.field_71071_by.func_70299_a(player.field_71071_by.field_70461_c, hand);
        player.field_71071_by.field_184439_c.set(0, (Object)off);
        EntityBender<EntityLivingBase> entityBender = EntityBenderRegistry.instance.getForEntity(living);
        if (entityBender == null) {
            return;
        }
        entityBender.afterRender(living, pt);
        AnimationHelper.postRender(living, renderer);
        GlStateManager.func_179121_F();
    }
}

