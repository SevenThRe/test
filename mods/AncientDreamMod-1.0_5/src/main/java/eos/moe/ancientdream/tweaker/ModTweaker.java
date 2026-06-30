/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.launchwrapper.ITweaker
 *  net.minecraft.launchwrapper.Launch
 *  net.minecraft.launchwrapper.LaunchClassLoader
 */
package eos.moe.ancientdream.tweaker;

import java.io.File;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import net.minecraft.launchwrapper.ITweaker;
import net.minecraft.launchwrapper.Launch;
import net.minecraft.launchwrapper.LaunchClassLoader;
import org.spongepowered.asm.launch.GlobalProperties;
import org.spongepowered.asm.launch.MixinBootstrap;
import org.spongepowered.asm.launch.platform.MixinPlatformManager;
import org.spongepowered.asm.launch.platform.container.ContainerHandleURI;

public class ModTweaker
implements ITweaker {
    private static final String MIXIN_TWEAKER = "org.spongepowered.asm.launch.MixinTweaker";

    public ModTweaker() {
        this.injectMixinTweaker();
    }

    private void injectMixinTweaker() {
        System.out.println("\u6211\u662fITWeaker");
        List tweakClasses = (List)Launch.blackboard.get("TweakClasses");
        if (tweakClasses.contains(MIXIN_TWEAKER)) {
            return;
        }
        if (GlobalProperties.get(GlobalProperties.Keys.INIT) != null) {
            return;
        }
        System.out.println("Injecting MixinTweaker from ReplayModTweaker");
        Launch.classLoader.addClassLoaderExclusion(MIXIN_TWEAKER.substring(0, MIXIN_TWEAKER.lastIndexOf(46)));
        List tweaks = (List)Launch.blackboard.get("Tweaks");
        try {
            tweaks.add((ITweaker)Class.forName(MIXIN_TWEAKER, true, (ClassLoader)Launch.classLoader).newInstance());
        }
        catch (ClassNotFoundException | IllegalAccessException | InstantiationException ex2) {
            throw new RuntimeException(ex2);
        }
    }

    public void acceptOptions(List<String> args, File gameDir, File assetsDir, String profile) {
    }

    public void injectIntoClassLoader(LaunchClassLoader classLoader) {
        URI uri;
        try {
            uri = this.getClass().getProtectionDomain().getCodeSource().getLocation().toURI();
        }
        catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }
        MixinPlatformManager platform = MixinBootstrap.getPlatform();
        platform.addContainer(new ContainerHandleURI(uri));
    }

    public String getLaunchTarget() {
        return null;
    }

    public String[] getLaunchArguments() {
        return new String[0];
    }
}

