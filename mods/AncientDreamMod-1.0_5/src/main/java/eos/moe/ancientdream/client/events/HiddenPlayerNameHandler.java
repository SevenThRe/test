/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.entity.EntityOtherPlayerMP
 *  net.minecraftforge.client.event.RenderLivingEvent$Specials$Pre
 *  net.minecraftforge.fml.common.Mod$EventBusSubscriber
 *  net.minecraftforge.fml.common.eventhandler.SubscribeEvent
 */
package eos.moe.ancientdream.client.events;

import net.minecraft.client.entity.EntityOtherPlayerMP;
import net.minecraftforge.client.event.RenderLivingEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

@Mod.EventBusSubscriber(modid="ancientdream")
public class HiddenPlayerNameHandler {
    @SubscribeEvent
    public static void onRenderLivingEventSpecialsPre(RenderLivingEvent.Specials.Pre<?> e) {
        if (e.getEntity() instanceof EntityOtherPlayerMP) {
            EntityOtherPlayerMP entity = (EntityOtherPlayerMP)e.getEntity();
            if (entity.func_70005_c_().contains("\u00a7")) {
                return;
            }
            e.setCanceled(true);
        }
    }
}

