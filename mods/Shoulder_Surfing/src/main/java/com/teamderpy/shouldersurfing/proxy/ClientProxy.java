/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.settings.KeyBinding
 *  net.minecraftforge.common.MinecraftForge
 *  net.minecraftforge.common.config.Configuration
 *  net.minecraftforge.fml.client.registry.ClientRegistry
 *  net.minecraftforge.fml.common.event.FMLInitializationEvent
 *  net.minecraftforge.fml.common.event.FMLLoadCompleteEvent
 *  net.minecraftforge.fml.common.event.FMLPreInitializationEvent
 *  net.minecraftforge.fml.relauncher.Side
 *  net.minecraftforge.fml.relauncher.SideOnly
 *  org.apache.commons.io.FileUtils
 */
package com.teamderpy.shouldersurfing.proxy;

import com.teamderpy.shouldersurfing.compatibility.EnumShaderCompatibility;
import com.teamderpy.shouldersurfing.config.Config;
import com.teamderpy.shouldersurfing.event.ClientEventHandler;
import com.teamderpy.shouldersurfing.event.KeyHandler;
import com.teamderpy.shouldersurfing.event.NetworkManager;
import com.teamderpy.shouldersurfing.proxy.CommonProxy;
import com.teamderpy.shouldersurfing.util.ShoulderState;
import com.teamderpy.shouldersurfing.util.ShoulderSurfingHelper;
import java.io.File;
import java.io.IOException;
import net.minecraft.client.settings.KeyBinding;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLLoadCompleteEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.apache.commons.io.FileUtils;

@SideOnly(value=Side.CLIENT)
public class ClientProxy
extends CommonProxy {
    @Override
    public void preInit(FMLPreInitializationEvent event) {
        NetworkManager.init();
        File suggestedConfigurationFile = event.getSuggestedConfigurationFile();
        File suggestedConfigurationFile1 = new File(suggestedConfigurationFile.getParentFile().getParentFile(), "shouldersurfing.cfg");
        if (!suggestedConfigurationFile1.exists()) {
            try {
                FileUtils.copyFile((File)suggestedConfigurationFile, (File)suggestedConfigurationFile1);
            }
            catch (IOException e) {
                e.printStackTrace();
            }
        }
        Config.CLIENT = suggestedConfigurationFile1.exists() ? new Config.ClientConfig(new Configuration(suggestedConfigurationFile1)) : new Config.ClientConfig(new Configuration(suggestedConfigurationFile));
        ShoulderSurfingHelper.setPerspective(Config.CLIENT.getDefaultPerspective());
        MinecraftForge.EVENT_BUS.register((Object)new ClientEventHandler());
        MinecraftForge.EVENT_BUS.register((Object)new KeyHandler());
    }

    @Override
    public void init(FMLInitializationEvent event) {
        ClientRegistry.registerKeyBinding((KeyBinding)KeyHandler.KEYBIND_CAMERA_LEFT);
        ClientRegistry.registerKeyBinding((KeyBinding)KeyHandler.KEYBIND_CAMERA_RIGHT);
        ClientRegistry.registerKeyBinding((KeyBinding)KeyHandler.KEYBIND_CAMERA_IN);
        ClientRegistry.registerKeyBinding((KeyBinding)KeyHandler.KEYBIND_CAMERA_OUT);
        ClientRegistry.registerKeyBinding((KeyBinding)KeyHandler.KEYBIND_CAMERA_UP);
        ClientRegistry.registerKeyBinding((KeyBinding)KeyHandler.KEYBIND_CAMERA_DOWN);
        ClientRegistry.registerKeyBinding((KeyBinding)KeyHandler.KEYBIND_SWAP_SHOULDER);
        ClientRegistry.registerKeyBinding((KeyBinding)KeyHandler.KEYBIND_TOGGLE_SHOULDER_SURFING);
    }

    @Override
    public void loadComplete(FMLLoadCompleteEvent event) {
        if (ClientProxy.isClassLoaded("shadersmod.client.Shaders")) {
            ShoulderState.setShaderType(EnumShaderCompatibility.OLD);
        } else if (ClientProxy.isClassLoaded("net.optifine.shaders.Shaders")) {
            ShoulderState.setShaderType(EnumShaderCompatibility.NEW);
        }
    }

    private static boolean isClassLoaded(String className) {
        try {
            return Class.forName(className) != null;
        }
        catch (ClassNotFoundException e) {
            return false;
        }
    }
}

