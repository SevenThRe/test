/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.launchwrapper.Launch
 *  net.minecraftforge.fml.relauncher.CoreModManager
 *  net.minecraftforge.fml.relauncher.IFMLLoadingPlugin
 *  org.apache.logging.log4j.LogManager
 */
package eos.moe.armourers.tweaker;

import java.io.File;
import java.io.Serializable;
import java.net.URISyntaxException;
import java.net.URL;
import java.security.CodeSource;
import java.util.Map;
import net.minecraft.launchwrapper.Launch;
import net.minecraftforge.fml.relauncher.CoreModManager;
import net.minecraftforge.fml.relauncher.IFMLLoadingPlugin;
import org.apache.logging.log4j.LogManager;
import org.spongepowered.asm.launch.MixinBootstrap;
import org.spongepowered.asm.mixin.Mixins;

public class ForgePlugin
implements IFMLLoadingPlugin {
    public ForgePlugin() {
        block5: {
            ForgePlugin a2;
            System.out.println("[DragonArmourers] LoadingPlugin");
            if (Launch.blackboard.get("fml.deobfuscatedEnvironment") != Boolean.FALSE) {
                MixinBootstrap.init();
            }
            Mixins.addConfiguration("mixins.dragonarmourers.json");
            Serializable serializable = a2.getClass().getProtectionDomain().getCodeSource();
            if (serializable != null) {
                serializable = ((CodeSource)serializable).getLocation();
                try {
                    serializable = new File(((URL)serializable).toURI());
                    if (((File)serializable).isFile()) {
                        CoreModManager.getIgnoredMods().remove(((File)serializable).getName());
                        return;
                    }
                    break block5;
                }
                catch (URISyntaxException uRISyntaxException) {
                    uRISyntaxException.printStackTrace();
                    return;
                }
            }
            LogManager.getLogger().warn("No CodeSource, if this is not a development environment we might run into problems!");
            LogManager.getLogger().warn((Object)a2.getClass().getProtectionDomain());
        }
    }

    public void injectData(Map<String, Object> a2) {
    }

    public String[] getASMTransformerClass() {
        return new String[0];
    }

    public String getModContainerClass() {
        return null;
    }

    public String getSetupClass() {
        return null;
    }

    public String getAccessTransformerClass() {
        return null;
    }
}

