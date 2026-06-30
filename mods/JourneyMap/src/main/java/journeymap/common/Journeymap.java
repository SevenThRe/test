/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.command.ICommand
 *  net.minecraftforge.common.MinecraftForge
 *  net.minecraftforge.fml.common.Mod
 *  net.minecraftforge.fml.common.Mod$EventHandler
 *  net.minecraftforge.fml.common.Mod$Instance
 *  net.minecraftforge.fml.common.SidedProxy
 *  net.minecraftforge.fml.common.event.FMLInitializationEvent
 *  net.minecraftforge.fml.common.event.FMLPostInitializationEvent
 *  net.minecraftforge.fml.common.event.FMLPreInitializationEvent
 *  net.minecraftforge.fml.common.event.FMLServerStartedEvent
 *  net.minecraftforge.fml.common.event.FMLServerStartingEvent
 *  net.minecraftforge.fml.common.network.NetworkCheckHandler
 *  net.minecraftforge.fml.relauncher.Side
 *  net.minecraftforge.fml.relauncher.SideOnly
 *  org.apache.logging.log4j.LogManager
 *  org.apache.logging.log4j.Logger
 */
package journeymap.common;

import java.util.Map;
import journeymap.client.JourneymapClient;
import journeymap.common.CommonProxy;
import journeymap.common.command.CommandJTP;
import journeymap.common.network.PacketRegistry;
import journeymap.common.version.Version;
import journeymap.server.JourneymapServer;
import journeymap.server.events.ForgeEvents;
import journeymap.server.nbt.WorldNbtIDSaveHandler;
import journeymap.server.properties.PropertiesManager;
import net.minecraft.command.ICommand;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.event.FMLServerStartedEvent;
import net.minecraftforge.fml.common.event.FMLServerStartingEvent;
import net.minecraftforge.fml.common.network.NetworkCheckHandler;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Mod(modid="journeymap", name="JourneyMap", version="1.12.2-5.7.0", canBeDeactivated=true, guiFactory="journeymap.client.ui.dialog.OptionsGuiFactory", dependencies="required-after:Forge@[${14.23.5.2768},)")
public class Journeymap {
    public static final String MOD_ID = "journeymap";
    public static final String SHORT_MOD_NAME = "JourneyMap";
    public static final Version JM_VERSION = Version.from("5", "7", "0", "", new Version(5, 5, 0, "dev"));
    public static final String FORGE_VERSION = "14.23.5.2768";
    public static final String MC_VERSION = "1.12.2";
    public static final String WEBSITE_URL = "http://journeymap.info/";
    public static final String PATREON_URL = "http://patreon.com/techbrew";
    public static final String DOWNLOAD_URL = "http://minecraft.curseforge.com/projects/journeymap/files/";
    public static final String VERSION_URL = "https://api.cfwidget.com/minecraft/mc-mods/journeymap";
    @Mod.Instance(value="journeymap")
    public static Journeymap instance;
    @SidedProxy(clientSide="journeymap.client.JourneymapClient", serverSide="journeymap.server.JourneymapServer")
    public static CommonProxy proxy;

    public static Logger getLogger() {
        return LogManager.getLogger((String)MOD_ID);
    }

    public static Logger getLogger(String name) {
        return LogManager.getLogger((String)("journeymap/" + name));
    }

    @NetworkCheckHandler
    public boolean checkModLists(Map<String, String> modList, Side side) {
        if (proxy == null) {
            return true;
        }
        return proxy.checkModLists(modList, side);
    }

    @Mod.EventHandler
    public void preInitialize(FMLPreInitializationEvent event) throws Throwable {
        proxy.preInitialize(event);
    }

    @Mod.EventHandler
    public void initialize(FMLInitializationEvent event) throws Throwable {
        MinecraftForge.EVENT_BUS.register((Object)new ForgeEvents());
        PacketRegistry.init();
        proxy.initialize(event);
    }

    @Mod.EventHandler
    public void postInitialize(FMLPostInitializationEvent event) throws Throwable {
        proxy.postInitialize(event);
    }

    @Mod.EventHandler
    public void serverStartingEvent(FMLServerStartingEvent event) {
        PropertiesManager.getInstance();
        event.registerServerCommand((ICommand)new CommandJTP());
    }

    @Mod.EventHandler
    public void serverStartedEvent(FMLServerStartedEvent event) {
        if (event.getSide().isServer()) {
            new WorldNbtIDSaveHandler().getWorldID();
        }
    }

    @SideOnly(value=Side.CLIENT)
    public static JourneymapClient getClient() {
        return (JourneymapClient)proxy;
    }

    @SideOnly(value=Side.SERVER)
    public static JourneymapServer getServer() {
        return (JourneymapServer)proxy;
    }
}

