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
    public void drawButton(@NotNull Minecraft mc, int mouseX, int mouseY, float partialTicks) {
        super.drawButton(mc, mouseX, mouseY, partialTicks);
        int k = this.getHoverState(this.isMouseOverButton(mouseX, mouseY));
        GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
        if (this.useCustomTexture) {
            mc.getTextureManager().bindTexture(resourceButtonCustom);
            this.drawTexturedModalRect(this.x, this.y, (k - 1) * 10, 0, this.width, this.height);
        } else {
            mc.getTextureManager().bindTexture(resourceButtonDefault);
            this.drawTexturedModalRect(this.x, this.y, 1, 46 + k * 20 + 1, this.width / 2, this.height / 2);
            this.drawTexturedModalRect(this.x, this.y + this.height / 2, 1, 46 + k * 20 + 20 - this.height / 2 - 1, this.width / 2, this.height / 2);
            this.drawTexturedModalRect(this.x + this.width / 2, this.y, 200 - this.width / 2 - 1, 46 + k * 20 + 1, this.width / 2, this.height / 2);
            this.drawTexturedModalRect(this.x + this.width / 2, this.y + this.height / 2, 200 - this.width / 2 - 1, 46 + k * 20 + 19 - this.height / 2, this.width / 2, this.height / 2);
        }
    }
}

