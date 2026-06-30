/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraftforge.fml.relauncher.IFMLLoadingPlugin
 *  net.minecraftforge.fml.relauncher.IFMLLoadingPlugin$MCVersion
 *  net.minecraftforge.fml.relauncher.IFMLLoadingPlugin$Name
 *  net.minecraftforge.fml.relauncher.IFMLLoadingPlugin$SortingIndex
 *  net.minecraftforge.fml.relauncher.IFMLLoadingPlugin$TransformerExclusions
 *  org.jetbrains.annotations.NotNull
 *  org.jetbrains.annotations.Nullable
 */
package invtweaks.forge.asm;

import java.util.Map;
import net.minecraftforge.fml.relauncher.IFMLLoadingPlugin;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@IFMLLoadingPlugin.TransformerExclusions(value={"invtweaks.forge.asm", "invtweaks.forge.asm.compatibility"})
@IFMLLoadingPlugin.SortingIndex(value=1001)
@IFMLLoadingPlugin.Name(value="Inventory Tweaks Coremod")
@IFMLLoadingPlugin.MCVersion(value="")
public class FMLPlugin
implements IFMLLoadingPlugin {
    public static boolean runtimeDeobfEnabled = false;

    @NotNull
    public String[] getASMTransformerClass() {
        return new String[]{"invtweaks.forge.asm.ContainerTransformer"};
    }

    @NotNull
    public String getAccessTransformerClass() {
        return "invtweaks.forge.asm.ITAccessTransformer";
    }

    @Nullable
    public String getModContainerClass() {
        return null;
    }

    @Nullable
    public String getSetupClass() {
        return null;
    }

    public void injectData(@NotNull Map<String, Object> data) {
        runtimeDeobfEnabled = (Boolean)data.get("runtimeDeobfuscationEnabled");
    }
}

