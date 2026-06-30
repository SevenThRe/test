/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.Minecraft
 *  net.minecraftforge.fml.common.eventhandler.SubscribeEvent
 *  net.minecraftforge.fml.common.gameevent.TickEvent$ClientTickEvent
 *  net.minecraftforge.fml.common.gameevent.TickEvent$Phase
 *  net.minecraftforge.fml.common.gameevent.TickEvent$RenderTickEvent
 */
package goblinbob.mobends.core.client.event;

import goblinbob.mobends.core.addon.Addons;
import goblinbob.mobends.core.data.EntityDatabase;
import net.minecraft.client.Minecraft;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;

public class DataUpdateHandler {
    public static float partialTicks = 0.0f;
    protected static float ticks = 0.0f;
    public static float ticksPerFrame = 0.0f;

    public static float getTicks() {
        return ticks;
    }

    @SubscribeEvent
    public void updateAnimations(TickEvent.RenderTickEvent event) {
        float newTicks;
        if (event.phase == TickEvent.Phase.END) {
            return;
        }
        if (Minecraft.func_71410_x().field_71441_e == null || Minecraft.func_71410_x().field_71439_g == null) {
            return;
        }
        if (!Minecraft.func_71410_x().func_147113_T()) {
            partialTicks = event.renderTickTime;
        }
        if (ticks > (newTicks = (float)Minecraft.func_71410_x().field_71439_g.field_70173_aa + event.renderTickTime)) {
            DataUpdateHandler.onTicksRestart();
        }
        if (!Minecraft.func_71410_x().field_71441_e.field_72995_K || !Minecraft.func_71410_x().func_147113_T()) {
            ticksPerFrame = Math.min(Math.max(0.0f, newTicks - ticks), 1.0f);
            ticks = newTicks;
            EntityDatabase.instance.updateRender(event.renderTickTime);
            Addons.onRenderTick(event.renderTickTime);
        } else {
            ticksPerFrame = 0.0f;
        }
    }

    public static void onTicksRestart() {
        EntityDatabase.instance.onTicksRestart();
    }

    @SubscribeEvent
    public void onClientTick(TickEvent.ClientTickEvent event) {
        if (event.phase == TickEvent.Phase.END || Minecraft.func_71410_x().field_71439_g == null || Minecraft.func_71410_x().func_147113_T()) {
            return;
        }
        EntityDatabase.instance.updateClient();
        Addons.onClientTick();
    }
}

