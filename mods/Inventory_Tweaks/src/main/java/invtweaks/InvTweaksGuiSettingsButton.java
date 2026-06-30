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
    public void drawButton(@NotNull Minecraft mc, int mouseX, int mouseY, float partialTicks) {
        super.drawButton(mc, mouseX, mouseY, partialTicks);
        InvTweaksObfuscation obf = new InvTweaksObfuscation(mc);
        this.drawCenteredString(obf.getFontRenderer(), this.displayString, this.x + 5, this.y - 1, this.getTextColor(mouseX, mouseY));
    }

    public boolean mousePressed(Minecraft minecraft, int i, int j) {
        InvTweaksObfuscation obf = new InvTweaksObfuscation(minecraft);
        InvTweaksConfig config = this.cfgManager.getConfig();
        if (super.mousePressed(minecraft, i, j)) {
            block4: {
                try {
                    ContainerSectionManager containerMgr = new ContainerSectionManager(ContainerSection.INVENTORY);
                    if (obf.getHeldStack().isEmpty()) break block4;
                    for (int k = containerMgr.getSize() - 1; k >= 0; --k) {
                        if (!containerMgr.getItemStack(k).isEmpty()) continue;
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

