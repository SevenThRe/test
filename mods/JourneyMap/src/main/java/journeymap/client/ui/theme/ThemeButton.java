/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.renderer.GlStateManager
 *  org.lwjgl.opengl.GL11
 */
package journeymap.client.ui.theme;

import java.util.List;
import journeymap.client.Constants;
import journeymap.client.render.draw.DrawUtil;
import journeymap.client.render.texture.TextureCache;
import journeymap.client.render.texture.TextureImpl;
import journeymap.client.ui.component.BooleanPropertyButton;
import journeymap.client.ui.theme.Theme;
import journeymap.common.properties.config.BooleanField;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import org.lwjgl.opengl.GL11;

public class ThemeButton
extends BooleanPropertyButton {
    protected Theme theme;
    protected Theme.Control.ButtonSpec buttonSpec;
    protected TextureImpl textureOn;
    protected TextureImpl textureHover;
    protected TextureImpl textureOff;
    protected TextureImpl textureDisabled;
    protected TextureImpl textureIcon;
    protected String iconName;
    protected List<String> additionalTooltips;
    protected boolean staysOn;
    private boolean displayClickToggle = true;

    public void setDisplayClickToggle(boolean displayClickToggle) {
        this.displayClickToggle = displayClickToggle;
    }

    public ThemeButton(Theme theme, String rawLabel, String iconName) {
        this(theme, Constants.getString(rawLabel), Constants.getString(rawLabel), false, iconName);
    }

    public ThemeButton(Theme theme, String labelOn, String labelOff, boolean toggled, String iconName) {
        super(labelOn, labelOff, null);
        this.iconName = iconName;
        this.setToggled(toggled);
        this.updateTheme(theme);
    }

    protected ThemeButton(Theme theme, String labelOn, String labelOff, String iconName, BooleanField field) {
        super(labelOn, labelOff, field);
        this.iconName = iconName;
        this.updateTheme(theme);
    }

    public boolean isStaysOn() {
        return this.staysOn;
    }

    public void setStaysOn(boolean staysOn) {
        this.staysOn = staysOn;
    }

    public void updateTheme(Theme theme) {
        this.theme = theme;
        this.buttonSpec = this.getButtonSpec(theme);
        if (this.buttonSpec.useThemeImages) {
            String pattern = this.getPathPattern();
            String prefix = this.buttonSpec.prefix;
            this.textureOn = TextureCache.getThemeTexture(theme, String.format(pattern, prefix, "on"));
            this.textureOff = TextureCache.getThemeTexture(theme, String.format(pattern, prefix, "off"));
            this.textureHover = TextureCache.getThemeTexture(theme, String.format(pattern, prefix, "hover"));
            this.textureDisabled = TextureCache.getThemeTexture(theme, String.format(pattern, prefix, "disabled"));
        } else {
            this.textureOn = null;
            this.textureOff = null;
            this.textureHover = null;
            this.textureDisabled = null;
        }
        this.textureIcon = TextureCache.getThemeTexture(theme, String.format("icon/%s.png", this.iconName));
        this.func_175211_a(this.buttonSpec.width);
        this.setHeight(this.buttonSpec.height);
        this.setToggled(false, false);
    }

    public boolean hasValidTextures() {
        if (this.buttonSpec.useThemeImages) {
            return GL11.glIsTexture((int)this.textureOn.getGlTextureId(false)) && GL11.glIsTexture((int)this.textureOff.getGlTextureId(false));
        }
        return true;
    }

    protected String getPathPattern() {
        return "control/%sbutton_%s.png";
    }

    protected Theme.Control.ButtonSpec getButtonSpec(Theme theme) {
        return theme.control.button;
    }

    public Theme.Control.ButtonSpec getButtonSpec() {
        return this.buttonSpec;
    }

    protected TextureImpl getActiveTexture(boolean isMouseOver) {
        if (!this.isEnabled()) {
            return this.textureDisabled;
        }
        return this.toggled != false ? this.textureOn : this.textureOff;
    }

    protected Theme.ColorSpec getIconColor(boolean isMouseOver) {
        if (!this.isEnabled()) {
            return this.buttonSpec.iconDisabled;
        }
        if (isMouseOver) {
            return this.toggled != false ? this.buttonSpec.iconHoverOn : this.buttonSpec.iconHoverOff;
        }
        return this.toggled.booleanValue() ? (this.displayClickToggle ? this.buttonSpec.iconOn : this.buttonSpec.iconOff) : this.buttonSpec.iconOff;
    }

    protected Theme.ColorSpec getButtonColor(boolean isMouseOver) {
        if (!this.isEnabled()) {
            return this.buttonSpec.buttonDisabled;
        }
        if (isMouseOver) {
            return this.toggled.booleanValue() ? (this.displayClickToggle ? this.buttonSpec.buttonHoverOn : this.buttonSpec.buttonHoverOff) : this.buttonSpec.buttonHoverOff;
        }
        return this.toggled.booleanValue() ? (this.displayClickToggle ? this.buttonSpec.buttonOn : this.buttonSpec.buttonOff) : this.buttonSpec.buttonOff;
    }

    @Override
    public void func_191745_a(Minecraft minecraft, int mouseX, int mouseY, float ticks) {
        if (!this.isVisible()) {
            return;
        }
        boolean hover = mouseX >= this.field_146128_h && mouseY >= this.field_146129_i && mouseX < this.field_146128_h + this.field_146120_f && mouseY < this.field_146129_i + this.field_146121_g;
        this.setMouseOver(hover);
        int hoverState = this.func_146114_a(hover);
        boolean isMouseOver = hoverState == 2;
        TextureImpl activeTexture = this.getActiveTexture(isMouseOver);
        Theme.ColorSpec iconColorSpec = this.getIconColor(isMouseOver);
        int drawX = this.getX();
        int drawY = this.getY();
        if (this.buttonSpec.useThemeImages) {
            Theme.ColorSpec buttonColorSpec = this.getButtonColor(isMouseOver);
            float buttonScale = 1.0f;
            if (this.buttonSpec.width != activeTexture.getWidth()) {
                buttonScale = 1.0f * (float)this.buttonSpec.width / (float)activeTexture.getWidth();
            }
            DrawUtil.drawColoredImage(activeTexture, buttonColorSpec.getColor(), buttonColorSpec.alpha, drawX, drawY, buttonScale, 0.0);
        } else {
            this.drawNativeButton(minecraft, mouseX, mouseY);
        }
        float iconScale = 1.0f;
        if (this.theme.icon.width != this.textureIcon.getWidth()) {
            iconScale = 1.0f * (float)this.theme.icon.width / (float)this.textureIcon.getWidth();
        }
        if (!this.buttonSpec.useThemeImages) {
            DrawUtil.drawColoredImage(this.textureIcon, 0, iconColorSpec.alpha, (double)drawX + 0.5, (double)drawY + 0.5, iconScale, 0.0);
        }
        DrawUtil.drawColoredImage(this.textureIcon, iconColorSpec.getColor(), iconColorSpec.alpha, drawX, drawY, iconScale, 0.0);
    }

    public void drawNativeButton(Minecraft minecraft, int mouseX, int mouseY) {
        int magic = 20;
        minecraft.func_110434_K().func_110577_a(field_146122_a);
        GlStateManager.func_179131_c((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
        int k = this.func_146114_a(this.func_146115_a());
        GlStateManager.func_179147_l();
        GlStateManager.func_179112_b((int)770, (int)771);
        this.func_73729_b(this.field_146128_h, this.field_146129_i, 0, 46 + k * magic, this.field_146120_f / 2, this.field_146121_g);
        this.func_73729_b(this.field_146128_h + this.field_146120_f / 2, this.field_146129_i, 200 - this.field_146120_f / 2, 46 + k * magic, this.field_146120_f / 2, this.field_146121_g);
        this.func_146119_b(minecraft, mouseX, mouseY);
        int l = 0xE0E0E0;
    }

    public void setAdditionalTooltips(List<String> additionalTooltips) {
        this.additionalTooltips = additionalTooltips;
    }

    @Override
    public List<String> getTooltip() {
        if (!this.field_146125_m) {
            return null;
        }
        List<String> list = super.getTooltip();
        String style = null;
        style = !this.isEnabled() ? this.buttonSpec.tooltipDisabledStyle : (this.toggled != false ? this.buttonSpec.tooltipOnStyle : this.buttonSpec.tooltipOffStyle);
        list.add(0, style + this.field_146126_j);
        if (this.additionalTooltips != null) {
            list.addAll(this.additionalTooltips);
        }
        return list;
    }
}

