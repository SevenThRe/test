/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.launchwrapper.ITweaker
 *  net.minecraft.launchwrapper.LaunchClassLoader
 */
package optifine;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import net.minecraft.launchwrapper.ITweaker;
import net.minecraft.launchwrapper.LaunchClassLoader;

public class OptiFineTweaker
implements ITweaker {
    private List<String> args;

    public void acceptOptions(List<String> args, File gameDir, File assetsDir, String profile) {
        OptiFineTweaker.dbg("OptiFineTweaker: acceptOptions");
        this.args = new ArrayList<String>(args);
        this.args.add("--gameDir");
        this.args.add(gameDir.getAbsolutePath());
        this.args.add("--assetsDir");
        this.args.add(assetsDir.getAbsolutePath());
        this.args.add("--version");
        this.args.add(profile);
    }

    public void injectIntoClassLoader(LaunchClassLoader classLoader) {
        OptiFineTweaker.dbg("OptiFineTweaker: injectIntoClassLoader");
        classLoader.registerTransformer("optifine.OptiFineClassTransformer");
    }

    public String getLaunchTarget() {
        OptiFineTweaker.dbg("OptiFineTweaker: getLaunchTarget");
        return "net.minecraft.client.main.Main";
    }

    public String[] getLaunchArguments() {
        OptiFineTweaker.dbg("OptiFineTweaker: getLaunchArguments");
        return this.args.toArray(new String[this.args.size()]);
    }

    private static void dbg(String str) {
        System.out.println(str);
    }
}

