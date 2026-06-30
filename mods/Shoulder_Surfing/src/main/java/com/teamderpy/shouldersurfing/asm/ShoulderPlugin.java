/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraftforge.fml.relauncher.IFMLLoadingPlugin
 */
package com.teamderpy.shouldersurfing.asm;

import java.util.Map;
import net.minecraftforge.fml.relauncher.IFMLLoadingPlugin;

public class ShoulderPlugin
implements IFMLLoadingPlugin {
    public String[] getASMTransformerClass() {
        return new String[]{"com.teamderpy.shouldersurfing.asm.transformers.EntityPlayerRayTrace", "com.teamderpy.shouldersurfing.asm.transformers.EntityRendererGetMouseOver", "com.teamderpy.shouldersurfing.asm.transformers.EntityRendererGetMouseOver2", "com.teamderpy.shouldersurfing.asm.transformers.EntityRendererOrientCamera", "com.teamderpy.shouldersurfing.asm.transformers.EntityRendererRayTrace", "com.teamderpy.shouldersurfing.asm.transformers.GuiIngameRenderAttackIndicator", "com.teamderpy.shouldersurfing.asm.transformers.GuiCrosshairsBCRenderAttackIndicator"};
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

