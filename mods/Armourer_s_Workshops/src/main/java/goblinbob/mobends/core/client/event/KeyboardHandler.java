/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.renderer.entity.RenderLivingBase
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraftforge.client.event.RenderLivingEvent$Specials$Pre
 *  net.minecraftforge.fml.common.eventhandler.EventPriority
 *  net.minecraftforge.fml.common.eventhandler.SubscribeEvent
 */
package goblinbob.mobends.core.client.event;

import goblinbob.mobends.core.bender.EntityBender;
import goblinbob.mobends.core.bender.EntityBenderRegistry;
import goblinbob.mobends.core.data.EntityData;
import goblinbob.mobends.core.mutators.Mutator;
import net.minecraft.client.renderer.entity.RenderLivingBase;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.client.event.RenderLivingEvent;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class KeyboardHandler {
    private static final String MAIN_CATEGORY = "Mo' Bends";

    public static void initKeyBindings() {
    }

    @SubscribeEvent(priority=EventPriority.HIGHEST)
    public void beforeLivingRenderNameTag(RenderLivingEvent.Specials.Pre<EntityLivingBase> event) {
        if (event.isCanceled()) {
            return;
        }
        EntityLivingBase living = event.getEntity();
        if (living instanceof EntityPlayer) {
            Mutator<?, ?, ?> mutator;
            EntityBender<EntityLivingBase> entityBender = EntityBenderRegistry.instance.getForEntity(living);
            if (entityBender == null) {
                return;
            }
            RenderLivingBase renderer = event.getRenderer();
            float pt = event.getPartialRenderTick();
            if (entityBender.isAnimated() && (mutator = entityBender.getMutator((RenderLivingBase<EntityLivingBase>)renderer)) != null) {
                Object data = mutator.getData(living);
                entityBender.backtrackBeforeRender((EntityData<EntityLivingBase>)data, (EntityPlayer)living, pt);
            }
        }
    }
}

