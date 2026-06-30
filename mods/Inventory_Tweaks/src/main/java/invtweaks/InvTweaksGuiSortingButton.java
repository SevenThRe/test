/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.Minecraft
 *  org.jetbrains.annotations.NotNull
 */
package invtweaks;

import invtweaks.InvTweaks;
import invtweaks.InvTweaksConfigManager;
import invtweaks.InvTweaksGuiIconButton;
import invtweaks.InvTweaksHandlerSorting;
import invtweaks.api.SortingMethod;
import invtweaks.api.container.ContainerSection;
import net.minecraft.client.Minecraft;
import org.jetbrains.annotations.NotNull;

public class InvTweaksGuiSortingButton
extends InvTweaksGuiIconButton {
    private final ContainerSection section = ContainerSection.CHEST;
    private SortingMethod algorithm;
    private int rowSize;

    public InvTweaksGuiSortingButton(InvTweaksConfigManager cfgManager_, int id_, int x, int y, int w, int h, String displayString_, String tooltip, SortingMethod algorithm_, int rowSize_, boolean useCustomTexture) {
        super(cfgManager_, id_, x, y, w, h, displayString_, tooltip, useCustomTexture);
        this.algorithm = algorithm_;
        this.rowSize = rowSize_;
    }

    @Override
    public void drawButton(@NotNull Minecraft mc, int mouseX, int mouseY, float partialTicks) {
        super.drawButton(mc, mouseX, mouseY, partialTicks);
        int textColor = this.getTextColor(mouseX, mouseY);
        switch (this.displayString) {
            case "h": {
                InvTweaksGuiSortingButton.drawRect((int)(this.x + 3), (int)(this.y + 3), (int)(this.x + this.width - 3), (int)(this.y + 4), (int)textColor);
                InvTweaksGuiSortingButton.drawRect((int)(this.x + 3), (int)(this.y + 6), (int)(this.x + this.width - 3), (int)(this.y + 7), (int)textColor);
                break;
            }
            case "v": {
                InvTweaksGuiSortingButton.drawRect((int)(this.x + 3), (int)(this.y + 3), (int)(this.x + 4), (int)(this.y + this.height - 3), (int)textColor);
                InvTweaksGuiSortingButton.drawRect((int)(this.x + 6), (int)(this.y + 3), (int)(this.x + 7), (int)(this.y + this.height - 3), (int)textColor);
                break;
            }
            default: {
                InvTweaksGuiSortingButton.drawRect((int)(this.x + 3), (int)(this.y + 3), (int)(this.x + this.width - 3), (int)(this.y + 4), (int)textColor);
                InvTweaksGuiSortingButton.drawRect((int)(this.x + 5), (int)(this.y + 4), (int)(this.x + 6), (int)(this.y + 5), (int)textColor);
                InvTweaksGuiSortingButton.drawRect((int)(this.x + 4), (int)(this.y + 5), (int)(this.x + 5), (int)(this.y + 6), (int)textColor);
                InvTweaksGuiSortingButton.drawRect((int)(this.x + 3), (int)(this.y + 6), (int)(this.x + this.width - 3), (int)(this.y + 7), (int)textColor);
            }
        }
    }

    public boolean mousePressed(Minecraft minecraft, int i, int j) {
        if (super.mousePressed(minecraft, i, j)) {
            try {
                new InvTweaksHandlerSorting(minecraft, this.cfgManager.getConfig(), this.section, this.algorithm, this.rowSize).sort();
            }
            catch (Exception e) {
                InvTweaks.logInGameErrorStatic("invtweaks.sort.chest.error", e);
                e.printStackTrace();
            }
            return true;
        }
        return false;
    }
}

