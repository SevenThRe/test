/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.launchwrapper.ITweaker
 *  net.minecraft.launchwrapper.Launch
 *  net.minecraft.launchwrapper.LaunchClassLoader
 */
package eos.moe.armourers.tweaker;

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

    public void injectIntoClassLoader(LaunchClassLoader a2) {
        try {
            ModTweaker a3;
            a2 = a3.getClass().getProtectionDomain().getCodeSource().getLocation().toURI();
        }
        catch (URISyntaxException uRISyntaxException) {
            throw new RuntimeException(uRISyntaxException);
        }
        MixinPlatformManager mixinPlatformManager = MixinBootstrap.getPlatform();
        mixinPlatformManager.addContainer(new ContainerHandleURI((URI)a2));
    }

    public void acceptOptions(List<String> a2, File a3, File a4, String a5) {
    }

    public String[] getLaunchArguments() {
        return new String[0];
    }

    private /* synthetic */ void injectMixinTweaker() {
        System.out.println("[DragonCore] Loading ITWeaker");
        if (((List)Launch.blackboard.get("TweakClasses")).contains(MIXIN_TWEAKER)) {
            return;
        }
        if (GlobalProperties.get(GlobalProperties.Keys.INIT) != null) {
            return;
        }
        System.out.println("[DragonArmourers] Injecting MixinTweaker from DragonCore ModTweaker");
        Launch.classLoader.addClassLoaderExclusion(MIXIN_TWEAKER.substring(0, MIXIN_TWEAKER.lastIndexOf(46)));
        List list = (List)Launch.blackboard.get("Tweaks");
        try {
            list.add((ITweaker)Class.forName(MIXIN_TWEAKER, true, (ClassLoader)Launch.classLoader).newInstance());
            return;
        }
        catch (ClassNotFoundException | IllegalAccessException | InstantiationException reflectiveOperationException) {
            throw new RuntimeException(reflectiveOperationException);
        }
    }

    public String getLaunchTarget() {
        return null;
    }

    public ModTweaker() {
        ModTweaker a2;
        ModTweaker modTweaker = a2;
        modTweaker.injectMixinTweaker();
    }
}

