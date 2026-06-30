/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.Minecraft
 *  net.minecraft.util.ResourceLocation
 *  org.jetbrains.annotations.NotNull
 *  org.lwjgl.opengl.GL11
 */
package invtweaks;

import invtweaks.InvTweaksConfigManager;
import invtweaks.InvTweaksGuiTooltipButton;
import net.minecraft.client.Minecraft;
import net.minecraft.util.ResourceLocation;
import org.jetbrains.annotations.NotNull;
import org.lwjgl.opengl.GL11;

public class InvTweaksGuiIconButton
extends InvTweaksGuiTooltipButton {
    @NotNull
    private static ResourceLocation resourceButtonCustom = new ResourceLocation("inventorytweaks", "textures/gui/button10px.png");
    @NotNull
    private static ResourceLocation resourceButtonDefault = new ResourceLocation("textures/gui/widgets.png");
    protected InvTweaksConfigManager cfgManager;
    private boolean useCustomTexture;

    public InvTweaksGuiIconButton(InvTweaksConfigManager cfgManager_, int id_, int x, int y, int w, int h, String displayString_, String tooltip, boolean useCustomTexture_) {
        super(id_, x, y, w, h, displayString_, tooltip);
        this.cfgManager = cfgManager_;
        this.useCustomTexture = useCustomTexture_;
    }

    @Override
    public void func_191745_a(@NotNull Minecraft mc, int mouseX, int mouseY, float partialTicks) {
        super.func_191745_a(mc, mouseX, mouseY, partialTicks);
        int k = this.func_146114_a(this.isMouseOverButton(mouseX, mouseY));
        GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
        if (this.useCustomTexture) {
            mc.func_110434_K().func_110577_a(resourceButtonCustom);
            this.func_73729_b(this.field_146128_h, this.field_146129_i, (k - 1) * 10, 0, this.field_146120_f, this.field_146121_g);
        } else {
            mc.func_110434_K().func_110577_a(resourceButtonDefault);
            this.func_73729_b(this.field_146128_h, this.field_146129_i, 1, 46 + k * 20 + 1, this.field_146120_f / 2, this.field_146121_g / 2);
            this.func_73729_b(this.field_146128_h, this.field_146129_i + this.field_146121_g / 2, 1, 46 + k * 20 + 20 - this.field_146121_g / 2 - 1, this.field_146120_f / 2, this.field_146121_g / 2);
            this.func_73729_b(this.field_146128_h + this.field_146120_f / 2, this.field_146129_i, 200 - this.field_146120_f / 2 - 1, 46 + k * 20 + 1, this.field_146120_f / 2, this.field_146121_g / 2);
            this.func_73729_b(this.field_146128_h + this.field_146120_f / 2, this.field_146129_i + this.field_146121_g / 2, 200 - this.field_146120_f / 2 - 1, 46 + k * 20 + 19 - this.field_146121_g / 2, this.field_146120_f / 2, this.field_146121_g / 2);
        }
    }
}

