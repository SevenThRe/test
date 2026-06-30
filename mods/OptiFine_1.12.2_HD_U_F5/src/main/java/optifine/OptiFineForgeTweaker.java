/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.launchwrapper.ITweaker
 *  net.minecraft.launchwrapper.LaunchClassLoader
 */
package optifine;

import java.io.File;
import java.util.List;
import net.minecraft.launchwrapper.ITweaker;
import net.minecraft.launchwrapper.LaunchClassLoader;
import optifine.LaunchUtils;

public class OptiFineForgeTweaker
implements ITweaker {
    public void acceptOptions(List<String> args, File gameDir, File assetsDir, String profile) {
        OptiFineForgeTweaker.dbg("OptiFineForgeTweaker: acceptOptions");
    }

    public void injectIntoClassLoader(LaunchClassLoader classLoader) {
        if (LaunchUtils.isForgeServer()) {
            OptiFineForgeTweaker.dbg("OptiFineForgeTweaker: Forge server detected, skipping class transformer");
            return;
        }
        OptiFineForgeTweaker.dbg("OptiFineForgeTweaker: injectIntoClassLoader");
        classLoader.registerTransformer("optifine.OptiFineClassTransformer");
    }

    public String getLaunchTarget() {
        OptiFineForgeTweaker.dbg("OptiFineForgeTweaker: getLaunchTarget");
        return "net.minecraft.client.main.Main";
    }

    public String[] getLaunchArguments() {
        OptiFineForgeTweaker.dbg("OptiFineForgeTweaker: getLaunchArguments");
        return new String[0];
    }

    private static void dbg(String str) {
        System.out.println(str);
    }
}

