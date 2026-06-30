/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.launchwrapper.Launch
 *  net.minecraftforge.fml.relauncher.CoreModManager
 *  net.minecraftforge.fml.relauncher.IFMLLoadingPlugin
 *  org.apache.logging.log4j.LogManager
 */
package eos.moe.ancientdream;

import java.io.File;
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

public class LoadingPlugin
implements IFMLLoadingPlugin {
    public LoadingPlugin() {
        System.out.println("\u6211\u662fLoadingPlugin");
        if (Launch.blackboard.get("fml.deobfuscatedEnvironment") != Boolean.FALSE) {
            MixinBootstrap.init();
        }
        Mixins.addConfiguration("mixins.ancientdream.json");
        CodeSource codeSource = this.getClass().getProtectionDomain().getCodeSource();
        if (codeSource != null) {
            URL location = codeSource.getLocation();
            try {
                File file = new File(location.toURI());
                if (file.isFile()) {
                    CoreModManager.getIgnoredMods().remove(file.getName());
                }
            }
            catch (URISyntaxException e) {
                e.printStackTrace();
            }
        } else {
            LogManager.getLogger().warn("No CodeSource, if this is not a development environment we might run into problems!");
            LogManager.getLogger().warn((Object)this.getClass().getProtectionDomain());
        }
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

    public void injectData(Map<String, Object> data) {
    }

    public String getAccessTransformerClass() {
        return null;
    }
}

