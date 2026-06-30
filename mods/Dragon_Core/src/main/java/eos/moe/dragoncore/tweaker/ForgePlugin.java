/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.launchwrapper.Launch
 *  net.minecraftforge.fml.relauncher.CoreModManager
 *  net.minecraftforge.fml.relauncher.IFMLLoadingPlugin
 *  org.apache.logging.log4j.LogManager
 */
package eos.moe.dragoncore.tweaker;

import eos.moe.dragoncore.tweaker.TransformerManager;
import java.io.File;
import java.io.Serializable;
import java.net.URISyntaxException;
import java.net.URL;
import java.security.CodeSource;
import java.util.Map;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import net.minecraft.launchwrapper.Launch;
import net.minecraftforge.fml.relauncher.CoreModManager;
import net.minecraftforge.fml.relauncher.IFMLLoadingPlugin;
import org.apache.logging.log4j.LogManager;
import org.spongepowered.asm.launch.MixinBootstrap;
import org.spongepowered.asm.mixin.Mixins;

public class ForgePlugin
implements IFMLLoadingPlugin {
    public ForgePlugin() {
        File[] a2;
        Serializable a3;
        ForgePlugin a4;
        System.out.println("[DragonCore] LoadingPlugin");
        if (Launch.blackboard.get("fml.deobfuscatedEnvironment") != Boolean.FALSE) {
            MixinBootstrap.init();
        }
        Mixins.addConfiguration("mixins.dragoncore.json");
        CodeSource a5 = a4.getClass().getProtectionDomain().getCodeSource();
        if (a5 != null) {
            a3 = a5.getLocation();
            try {
                a2 = new File(((URL)a3).toURI());
                if (a2.isFile()) {
                    CoreModManager.getIgnoredMods().remove(a2.getName());
                }
            }
            catch (URISyntaxException a6) {
                a6.printStackTrace();
            }
        } else {
            LogManager.getLogger().warn("No CodeSource, if this is not a development environment we might run into problems!");
            LogManager.getLogger().warn((Object)a4.getClass().getProtectionDomain());
        }
        a3 = new File(Launch.minecraftHome, "mods");
        a2 = ((File)a3).listFiles();
        if (a2 != null) {
            for (File a7 : a2) {
                if (!a7.getName().endsWith(".jar") && !a7.getName().endsWith(".zip")) continue;
                try {
                    ZipFile a8 = new ZipFile(a7);
                    ZipEntry a9 = a8.getEntry("dragonarmourers.json");
                    if (a9 == null) continue;
                    Launch.classLoader.addURL(a7.toURI().toURL());
                }
                catch (Throwable a10) {
                    a10.printStackTrace();
                }
            }
        }
    }

    public String[] getASMTransformerClass() {
        return new String[]{TransformerManager.class.getName()};
    }

    public String getModContainerClass() {
        return null;
    }

    public String getSetupClass() {
        return null;
    }

    public void injectData(Map<String, Object> a2) {
    }

    public String getAccessTransformerClass() {
        return null;
    }
}

