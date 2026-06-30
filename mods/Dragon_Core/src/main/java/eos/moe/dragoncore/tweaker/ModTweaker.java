/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.launchwrapper.ITweaker
 *  net.minecraft.launchwrapper.Launch
 *  net.minecraft.launchwrapper.LaunchClassLoader
 */
package eos.moe.dragoncore.tweaker;

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
        ModTweaker a2;
        a2.injectMixinTweaker();
    }

    private /* synthetic */ void injectMixinTweaker() {
        System.out.println("[DragonCore] Loading ITWeaker");
        List a2 = (List)Launch.blackboard.get("TweakClasses");
        if (a2.contains(MIXIN_TWEAKER)) {
            return;
        }
        if (GlobalProperties.get(GlobalProperties.Keys.INIT) != null) {
            return;
        }
        System.out.println("Injecting MixinTweaker from DragonCore ModTweaker");
        Launch.classLoader.addClassLoaderExclusion(MIXIN_TWEAKER.substring(0, MIXIN_TWEAKER.lastIndexOf(46)));
        List a3 = (List)Launch.blackboard.get("Tweaks");
        try {
            a3.add((ITweaker)Class.forName(MIXIN_TWEAKER, true, (ClassLoader)Launch.classLoader).newInstance());
        }
        catch (ClassNotFoundException | IllegalAccessException | InstantiationException a4) {
            throw new RuntimeException(a4);
        }
    }

    public void acceptOptions(List<String> a2, File a3, File a4, String a5) {
    }

    public void injectIntoClassLoader(LaunchClassLoader a2) {
        URI a3;
        try {
            ModTweaker a4;
            a3 = a4.getClass().getProtectionDomain().getCodeSource().getLocation().toURI();
        }
        catch (URISyntaxException a5) {
            throw new RuntimeException(a5);
        }
        MixinPlatformManager a6 = MixinBootstrap.getPlatform();
        a6.addContainer(new ContainerHandleURI(a3));
    }

    public String getLaunchTarget() {
        return null;
    }

    public String[] getLaunchArguments() {
        return new String[0];
    }
}

