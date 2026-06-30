/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraftforge.common.MinecraftForge
 *  net.minecraftforge.fml.common.Mod
 *  net.minecraftforge.fml.common.Mod$EventHandler
 *  net.minecraftforge.fml.common.event.FMLInitializationEvent
 *  net.minecraftforge.fml.common.event.FMLPreInitializationEvent
 *  org.apache.logging.log4j.Logger
 */
package eos.moe.ancientdream;

import eos.moe.ancientdream.client.utils.LocalCache;
import eos.moe.ancientdream.network.NetworkMessage;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import org.apache.logging.log4j.Logger;

@Mod(modid="ancientdream", useMetadata=true, version="1.0.5", acceptedMinecraftVersions="[1.12.2]", acceptableRemoteVersions="*", clientSideOnly=true)
public class AncientDream {
    public static final String MODID = "ancientdream";
    private static Logger logger;

    @Mod.EventHandler
    public void pre(FMLPreInitializationEvent event) {
        logger = event.getModLog();
    }

    @Mod.EventHandler
    public void init(FMLInitializationEvent event) {
        MinecraftForge.EVENT_BUS.register((Object)this);
        LocalCache.init();
        NetworkMessage.init();
    }

    public static Logger getLogger() {
        return logger;
    }
}

