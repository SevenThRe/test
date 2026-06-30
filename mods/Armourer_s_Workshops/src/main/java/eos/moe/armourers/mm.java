/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.renderer.entity.RenderLivingBase
 *  net.minecraft.client.renderer.entity.RenderPlayer
 *  net.minecraft.client.renderer.entity.layers.LayerRenderer
 *  net.minecraftforge.common.MinecraftForge
 *  net.minecraftforge.fml.common.Mod
 *  net.minecraftforge.fml.common.Mod$EventHandler
 *  net.minecraftforge.fml.common.event.FMLInitializationEvent
 *  net.minecraftforge.fml.common.event.FMLPostInitializationEvent
 *  net.minecraftforge.fml.common.event.FMLPreInitializationEvent
 *  net.minecraftforge.fml.common.eventhandler.SubscribeEvent
 *  net.minecraftforge.fml.common.network.FMLNetworkEvent$ClientConnectedToServerEvent
 *  net.minecraftforge.fml.relauncher.ReflectionHelper
 */
package eos.moe.armourers;

import eos.moe.armourers.ng;
import eos.moe.armourers.tg;
import java.util.Iterator;
import java.util.List;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.entity.RenderLivingBase;
import net.minecraft.client.renderer.entity.RenderPlayer;
import net.minecraft.client.renderer.entity.layers.LayerRenderer;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.network.FMLNetworkEvent;
import net.minecraftforge.fml.relauncher.ReflectionHelper;

@Mod(modid="dragonarmourers", acceptedMinecraftVersions="[1.12.2]")
public class mm {
    public static String j = "dragonarmourers";

    @SubscribeEvent
    public void r(FMLNetworkEvent.ClientConnectedToServerEvent a2) {
        System.out.println("\u517c\u5bb9\u6a21\u578b\u65f6\u88c5");
        mm.r();
    }

    public static void r() {
        Iterator iterator;
        Iterator iterator2 = iterator = Minecraft.getMinecraft().getRenderManager().getSkinMap().values().iterator();
        while (iterator2.hasNext()) {
            RenderPlayer renderPlayer = (RenderPlayer)iterator.next();
            String[] stringArray = new String[2];
            stringArray[0] = "layerRenderers";
            stringArray[1] = "layerRenderers";
            Iterator iterator3 = ((List)ReflectionHelper.getPrivateValue(RenderLivingBase.class, (Object)renderPlayer, (String[])stringArray)).iterator();
            while (iterator3.hasNext()) {
                if (!((LayerRenderer)iterator3.next() instanceof tg)) continue;
                return;
            }
            renderPlayer.addLayer((LayerRenderer)new tg(renderPlayer));
            iterator2 = iterator;
        }
    }

    public mm() {
        mm a2;
    }

    @Mod.EventHandler
    public void r(FMLPostInitializationEvent a2) {
    }

    @Mod.EventHandler
    public void r(FMLInitializationEvent a2) {
    }

    @Mod.EventHandler
    public void r(FMLPreInitializationEvent a2) {
        mm a3;
        MinecraftForge.EVENT_BUS.register((Object)a3);
        new ng();
    }
}

