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
    public void func_191745_a(@NotNull Minecraft mc, int mouseX, int mouseY, float partialTicks) {
        super.func_191745_a(mc, mouseX, mouseY, partialTicks);
        int textColor = this.getTextColor(mouseX, mouseY);
        switch (this.field_146126_j) {
            case "h": {
                InvTweaksGuiSortingButton.func_73734_a((int)(this.field_146128_h + 3), (int)(this.field_146129_i + 3), (int)(this.field_146128_h + this.field_146120_f - 3), (int)(this.field_146129_i + 4), (int)textColor);
                InvTweaksGuiSortingButton.func_73734_a((int)(this.field_146128_h + 3), (int)(this.field_146129_i + 6), (int)(this.field_146128_h + this.field_146120_f - 3), (int)(this.field_146129_i + 7), (int)textColor);
                break;
            }
            case "v": {
                InvTweaksGuiSortingButton.func_73734_a((int)(this.field_146128_h + 3), (int)(this.field_146129_i + 3), (int)(this.field_146128_h + 4), (int)(this.field_146129_i + this.field_146121_g - 3), (int)textColor);
                InvTweaksGuiSortingButton.func_73734_a((int)(this.field_146128_h + 6), (int)(this.field_146129_i + 3), (int)(this.field_146128_h + 7), (int)(this.field_146129_i + this.field_146121_g - 3), (int)textColor);
                break;
            }
            default: {
                InvTweaksGuiSortingButton.func_73734_a((int)(this.field_146128_h + 3), (int)(this.field_146129_i + 3), (int)(this.field_146128_h + this.field_146120_f - 3), (int)(this.field_146129_i + 4), (int)textColor);
                InvTweaksGuiSortingButton.func_73734_a((int)(this.field_146128_h + 5), (int)(this.field_146129_i + 4), (int)(this.field_146128_h + 6), (int)(this.field_146129_i + 5), (int)textColor);
                InvTweaksGuiSortingButton.func_73734_a((int)(this.field_146128_h + 4), (int)(this.field_146129_i + 5), (int)(this.field_146128_h + 5), (int)(this.field_146129_i + 6), (int)textColor);
                InvTweaksGuiSortingButton.func_73734_a((int)(this.field_146128_h + 3), (int)(this.field_146129_i + 6), (int)(this.field_146128_h + this.field_146120_f - 3), (int)(this.field_146129_i + 7), (int)textColor);
            }
        }
    }

    public boolean func_146116_c(Minecraft minecraft, int i, int j) {
        if (super.func_146116_c(minecraft, i, j)) {
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

