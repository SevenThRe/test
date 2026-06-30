/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraftforge.fml.common.Mod
 *  net.minecraftforge.fml.common.Mod$EventHandler
 *  net.minecraftforge.fml.common.Mod$Instance
 *  net.minecraftforge.fml.common.SidedProxy
 *  net.minecraftforge.fml.common.event.FMLInitializationEvent
 *  net.minecraftforge.fml.common.event.FMLLoadCompleteEvent
 *  net.minecraftforge.fml.common.event.FMLPreInitializationEvent
 *  net.minecraftforge.fml.relauncher.IFMLLoadingPlugin$MCVersion
 *  org.apache.logging.log4j.LogManager
 *  org.apache.logging.log4j.Logger
 */
package com.teamderpy.shouldersurfing;

import com.teamderpy.shouldersurfing.proxy.CommonProxy;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLLoadCompleteEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.relauncher.IFMLLoadingPlugin;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@IFMLLoadingPlugin.MCVersion(value="1.9")
@Mod(modid="shouldersurfing", name="Shoulder Surfing", acceptedMinecraftVersions="[1.9,)", version="2.2.1", canBeDeactivated=false, guiFactory="com.teamderpy.shouldersurfing.gui.GuiShoulderSurfingConfigFactory", certificateFingerprint="d6261bb645f41db84c74f98e512c2bb43f188af2")
public class ShoulderSurfing {
    @SidedProxy(clientSide="com.teamderpy.shouldersurfing.proxy.ClientProxy", serverSide="com.teamderpy.shouldersurfing.proxy.CommonProxy")
    public static CommonProxy proxy;
    public static final String NAME = "Shoulder Surfing";
    public static final String MODID = "shouldersurfing";
    public static final String MC_VERSION = "1.9";
    public static final String VERSION = "2.2.1";
    public static final String DEVELOPERS = "Joshua Powers, Exopandora (for 1.8+)";
    public static final String CERTIFICATE = "d6261bb645f41db84c74f98e512c2bb43f188af2";
    public static final Logger LOGGER;
    @Mod.Instance(value="shouldersurfing")
    private static ShoulderSurfing instance;

    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        proxy.preInit(event);
    }

    @Mod.EventHandler
    public void init(FMLInitializationEvent event) {
        proxy.init(event);
    }

    @Mod.EventHandler
    public void loadComplete(FMLLoadCompleteEvent event) {
        proxy.loadComplete(event);
    }

    public static ShoulderSurfing getInstance() {
        return instance;
    }

    static {
        LOGGER = LogManager.getLogger((String)NAME);
    }
}

