/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  bja
 *  cey
 */
package net.optifine.shaders.gui;

import net.optifine.shaders.Shaders;
import net.optifine.shaders.config.EnumShaderOption;
import net.optifine.shaders.gui.GuiShaders;

public class GuiButtonEnumShaderOption
extends bja {
    private EnumShaderOption enumShaderOption = null;

    public GuiButtonEnumShaderOption(EnumShaderOption enumShaderOption, int x, int y, int widthIn, int heightIn) {
        super(enumShaderOption.ordinal(), x, y, widthIn, heightIn, GuiButtonEnumShaderOption.getButtonText(enumShaderOption));
        this.enumShaderOption = enumShaderOption;
    }

    public EnumShaderOption getEnumShaderOption() {
        return this.enumShaderOption;
    }

    private static String getButtonText(EnumShaderOption eso) {
        String nameText = cey.a((String)eso.getResourceKey(), (Object[])new Object[0]) + ": ";
        switch (eso) {
            case ANTIALIASING: {
                return nameText + GuiShaders.toStringAa(Shaders.configAntialiasingLevel);
            }
            case NORMAL_MAP: {
                return nameText + GuiShaders.toStringOnOff(Shaders.configNormalMap);
            }
            case SPECULAR_MAP: {
                return nameText + GuiShaders.toStringOnOff(Shaders.configSpecularMap);
            }
            case RENDER_RES_MUL: {
                return nameText + GuiShaders.toStringQuality(Shaders.configRenderResMul);
            }
            case SHADOW_RES_MUL: {
                return nameText + GuiShaders.toStringQuality(Shaders.configShadowResMul);
            }
            case HAND_DEPTH_MUL: {
                return nameText + GuiShaders.toStringHandDepth(Shaders.configHandDepthMul);
            }
            case CLOUD_SHADOW: {
                return nameText + GuiShaders.toStringOnOff(Shaders.configCloudShadow);
            }
            case OLD_HAND_LIGHT: {
                return nameText + Shaders.configOldHandLight.getUserValue();
            }
            case OLD_LIGHTING: {
                return nameText + Shaders.configOldLighting.getUserValue();
            }
            case SHADOW_CLIP_FRUSTRUM: {
                return nameText + GuiShaders.toStringOnOff(Shaders.configShadowClipFrustrum);
            }
            case TWEAK_BLOCK_DAMAGE: {
                return nameText + GuiShaders.toStringOnOff(Shaders.configTweakBlockDamage);
            }
        }
        return nameText + Shaders.getEnumShaderOption(eso);
    }

    public void updateButtonText() {
        this.j = GuiButtonEnumShaderOption.getButtonText(this.enumShaderOption);
    }
}

