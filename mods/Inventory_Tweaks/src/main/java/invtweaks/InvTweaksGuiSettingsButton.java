/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.Minecraft
 *  org.apache.logging.log4j.Logger
 *  org.jetbrains.annotations.NotNull
 */
package invtweaks;

import invtweaks.InvTweaks;
import invtweaks.InvTweaksConfig;
import invtweaks.InvTweaksConfigManager;
import invtweaks.InvTweaksGuiIconButton;
import invtweaks.InvTweaksGuiSettings;
import invtweaks.InvTweaksObfuscation;
import invtweaks.api.container.ContainerSection;
import invtweaks.container.ContainerSectionManager;
import net.minecraft.client.Minecraft;
import org.apache.logging.log4j.Logger;
import org.jetbrains.annotations.NotNull;

public class InvTweaksGuiSettingsButton
extends InvTweaksGuiIconButton {
    private static final Logger log = InvTweaks.log;

    public InvTweaksGuiSettingsButton(InvTweaksConfigManager cfgManager_, int id_, int x, int y, int w, int h, String displayString_, String tooltip, boolean useCustomTexture) {
        super(cfgManager_, id_, x, y, w, h, displayString_, tooltip, useCustomTexture);
    }

    @Override
    public void func_191745_a(@NotNull Minecraft mc, int mouseX, int mouseY, float partialTicks) {
        super.func_191745_a(mc, mouseX, mouseY, partialTicks);
        InvTweaksObfuscation obf = new InvTweaksObfuscation(mc);
        this.func_73732_a(obf.getFontRenderer(), this.field_146126_j, this.field_146128_h + 5, this.field_146129_i - 1, this.getTextColor(mouseX, mouseY));
    }

    public boolean func_146116_c(Minecraft minecraft, int i, int j) {
        InvTweaksObfuscation obf = new InvTweaksObfuscation(minecraft);
        InvTweaksConfig config = this.cfgManager.getConfig();
        if (super.func_146116_c(minecraft, i, j)) {
            block4: {
                try {
                    ContainerSectionManager containerMgr = new ContainerSectionManager(ContainerSection.INVENTORY);
                    if (obf.getHeldStack().func_190926_b()) break block4;
                    for (int k = containerMgr.getSize() - 1; k >= 0; --k) {
                        if (!containerMgr.getItemStack(k).func_190926_b()) continue;
                        containerMgr.leftClick(k);
                        break;
                    }
                }
                catch (Exception e) {
                    log.error("mousePressed", (Throwable)e);
                }
            }
            this.cfgManager.makeSureConfigurationIsLoaded();
            obf.displayGuiScreen(new InvTweaksGuiSettings(minecraft, obf.getCurrentScreen(), config));
            return true;
        }
        return false;
    }
}

