/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.objectweb.asm.tree.ClassNode
 */
package eos.moe.dragoncore;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import org.objectweb.asm.tree.ClassNode;
import org.spongepowered.asm.mixin.extensibility.IMixinConfigPlugin;
import org.spongepowered.asm.mixin.extensibility.IMixinInfo;

public class MixinConfigPlugin
implements IMixinConfigPlugin {
    public MixinConfigPlugin() {
        MixinConfigPlugin a2;
    }

    @Override
    public void onLoad(String a2) {
    }

    @Override
    public String getRefMapperConfig() {
        return null;
    }

    @Override
    public boolean shouldApplyMixin(String a2, String a3) {
        return true;
    }

    @Override
    public void acceptTargets(Set<String> a2, Set<String> a3) {
    }

    @Override
    public List<String> getMixins() {
        ArrayList<String> a2 = new ArrayList<String>();
        try {
            Class.forName("optifine.OptiFineForgeTweaker");
            a2.add("MixinFontRendererOptifine");
        }
        catch (Exception exception) {
            // empty catch block
        }
        return !a2.isEmpty() ? a2 : null;
    }

    @Override
    public void preApply(String a2, ClassNode a3, String a4, IMixinInfo a5) {
    }

    @Override
    public void postApply(String a2, ClassNode a3, String a4, IMixinInfo a5) {
    }
}

