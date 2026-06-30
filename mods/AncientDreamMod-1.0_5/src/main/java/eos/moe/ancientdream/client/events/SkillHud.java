/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.util.ResourceLocation
 *  net.minecraftforge.client.event.RenderGameOverlayEvent
 *  net.minecraftforge.fml.common.Mod$EventBusSubscriber
 *  net.minecraftforge.fml.common.eventhandler.SubscribeEvent
 */
package eos.moe.ancientdream.client.events;

import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

@Mod.EventBusSubscriber(modid="ancientdream")
public class SkillHud {
    private static ResourceLocation HUD = new ResourceLocation("ancientdream", "gui/skill/hud.png");
    private static ResourceLocation CD = new ResourceLocation("ancientdream", "gui/skill/hud_cd.png");

    @SubscribeEvent
    public static void onr(RenderGameOverlayEvent e) {
    }
}

