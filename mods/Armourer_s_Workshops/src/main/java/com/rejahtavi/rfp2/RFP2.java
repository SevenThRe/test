/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.entity.EntityPlayerSP
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.util.text.ITextComponent
 *  net.minecraft.util.text.TextComponentString
 *  net.minecraft.util.text.TextFormatting
 *  net.minecraftforge.fml.common.Mod
 *  net.minecraftforge.fml.common.Mod$EventHandler
 *  net.minecraftforge.fml.common.Mod$Instance
 *  net.minecraftforge.fml.common.event.FMLInitializationEvent
 *  net.minecraftforge.fml.common.event.FMLPostInitializationEvent
 *  net.minecraftforge.fml.common.event.FMLPreInitializationEvent
 *  org.apache.logging.log4j.Level
 *  org.apache.logging.log4j.Logger
 */
package com.rejahtavi.rfp2;

import com.rejahtavi.rfp2.ClientProxy;
import com.rejahtavi.rfp2.IProxy;
import com.rejahtavi.rfp2.RFP2Config;
import com.rejahtavi.rfp2.RFP2State;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextFormatting;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.Logger;

@Mod(modid="rfp2", name="Real First Person 2", version="1.12.2-1.3.2", dependencies="after:cosmeticarmorreworked;after:ido;after:morph;", clientSideOnly=true, acceptedMinecraftVersions="1.12.2", acceptableRemoteVersions="*")
public class RFP2 {
    public static String[] CONFLICT_MODIDS = new String[]{"obfuscate", "moreplayermodels", "playerformlittlemaid"};
    public static String MODID = "rfp2";
    public static String MODNAME = "Real First Person 2";
    public static String MODVER = "1.12.2-1.3.2";
    public static String MODDEPS = "after:cosmeticarmorreworked;after:ido;after:morph;";
    @Mod.Instance(value="rfp2")
    public static RFP2 INSTANCE;
    public static IProxy PROXY;
    public static RFP2Config config;
    public static RFP2State state;
    public static Logger logger;
    public static long lastLoggedTimestamp;
    public static long ignoredErrorCount;
    public static Level LOGGING_LEVEL_DEBUG;
    public static Level LOGGING_LEVEL_LOW;
    public static Level LOGGING_LEVEL_MED;
    public static Level LOGGING_LEVEL_HIGH;

    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        PROXY.preInit(event);
    }

    @Mod.EventHandler
    public void init(FMLInitializationEvent event) {
        PROXY.init(event);
    }

    @Mod.EventHandler
    public void postInit(FMLPostInitializationEvent event) {
        PROXY.postInit(event);
    }

    public static void logToChat(String message) {
        EntityPlayerSP player = Minecraft.getMinecraft().player;
        if (player != null) {
            TextComponentString textToSend = new TextComponentString(message);
            player.sendMessage((ITextComponent)textToSend);
        }
    }

    public static void logToChatByPlayer(String message, EntityPlayer player) {
        if (player != null) {
            TextComponentString textToSend = new TextComponentString(message);
            player.sendMessage((ITextComponent)textToSend);
        }
    }

    public static void errorDisableMod(String sourceMethod, Exception e2) {
        if (RFP2Config.compatibility.disableRenderErrorCatching) {
            long epoch = System.currentTimeMillis() / 1000L;
            if (epoch >= lastLoggedTimestamp + 60L) {
                logger.log(LOGGING_LEVEL_MED, ": " + sourceMethod + " **IGNORING** exception:" + e2.getMessage());
                if (ignoredErrorCount > 0L) {
                    logger.log(LOGGING_LEVEL_MED, ": (" + ignoredErrorCount + " errors ignored in last " + 60 + "s.)");
                }
                ignoredErrorCount = 0L;
                lastLoggedTimestamp = epoch;
            } else {
                ++ignoredErrorCount;
            }
        } else {
            RFP2.state.enableMod = false;
            logger.log(LOGGING_LEVEL_HIGH, ": first person rendering deactivated.");
            logger.log(LOGGING_LEVEL_HIGH, ": " + sourceMethod + " encountered an exception:" + e2.getMessage());
            e2.printStackTrace();
            RFP2.logToChat("Real First Person 2 mod " + TextFormatting.RED + " disabled");
            RFP2.logToChat(sourceMethod + " encountered an exception:");
            RFP2.logToChat(TextFormatting.RED + e2.getMessage());
            RFP2.logToChat(TextFormatting.DARK_RED + e2.getStackTrace().toString());
            RFP2.logToChat(TextFormatting.GOLD + "Please check your minecraft log file for more details.");
        }
    }

    static {
        PROXY = new ClientProxy();
        lastLoggedTimestamp = 0L;
        ignoredErrorCount = 0L;
        LOGGING_LEVEL_DEBUG = Level.DEBUG;
        LOGGING_LEVEL_LOW = Level.INFO;
        LOGGING_LEVEL_MED = Level.WARN;
        LOGGING_LEVEL_HIGH = Level.FATAL;
    }
}

