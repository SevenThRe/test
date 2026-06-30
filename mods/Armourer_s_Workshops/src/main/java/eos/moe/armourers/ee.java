/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.gui.GuiButton
 *  net.minecraft.client.renderer.GlStateManager
 *  net.minecraft.client.renderer.GlStateManager$DestFactor
 *  net.minecraft.client.renderer.GlStateManager$SourceFactor
 */
package eos.moe.armourers;

import java.util.function.Consumer;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.renderer.GlStateManager;

public class ee
extends GuiButton {
    private Consumer<ee> j;

    public ee(int a2, int a3, int a4, int a5, int a6, String a7, Consumer<ee> a8) {
        super(a2, a3, a4, a5, a6, a7);
        ee a9;
        a9.j = a8;
    }

    public ee(int a2, int a3, int a4, String a5, Consumer<ee> a6) {
        super(a2, a3, a4, a5);
        ee a7;
        a7.j = a6;
    }

    /*
     * Unable to fully structure code
     */
    public void drawButton(Minecraft a, int a, int a, float a) {
        block7: {
            if (!a.visible) break block7;
            v0 = a;
            a = v0.fontRenderer;
            v0.getTextureManager().bindTexture(ee.BUTTON_TEXTURES);
            GlStateManager.color((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
            if (a < a.x || a < a.y) ** GOTO lbl-1000
            v1 = a;
            if (a >= v1.x + v1.width) ** GOTO lbl-1000
            v2 = a;
            if (a < v2.y + v2.height) {
                v3 = true;
            } else lbl-1000:
            // 3 sources

            {
                v3 = false;
            }
            a.hovered = v3;
            v4 = a;
            v5 = a;
            a = v5.getHoverState(v5.hovered);
            GlStateManager.enableBlend();
            GlStateManager.tryBlendFuncSeparate((GlStateManager.SourceFactor)GlStateManager.SourceFactor.SRC_ALPHA, (GlStateManager.DestFactor)GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA, (GlStateManager.SourceFactor)GlStateManager.SourceFactor.ONE, (GlStateManager.DestFactor)GlStateManager.DestFactor.ZERO);
            GlStateManager.blendFunc((GlStateManager.SourceFactor)GlStateManager.SourceFactor.SRC_ALPHA, (GlStateManager.DestFactor)GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA);
            v5.drawTexturedModalRect(v5.x, a.y, 0, 46 + a * 20, a.width / 2, a.height);
            v4.drawTexturedModalRect(v5.x + a.width / 2, a.y, 200 - a.width / 2, 46 + a * 20, a.width / 2, a.height);
            v4.mouseDragged(a, a, a);
            a = 0xE0E0E0;
            if (v4.packedFGColour != 0) {
                v6 = a;
                v7 = v6;
                a = v6.packedFGColour;
            } else if (!a.enabled) {
                a = 0xA0A0A0;
                v7 = a;
            } else {
                if (a.hovered) {
                    a = 0xFFFFA0;
                }
                v7 = a;
            }
            v8 = a;
            v9 = a;
            v7.drawCenteredString(a, v8.displayString, v8.x + a.width / 2, v9.y + (v9.height - 8) / 2, a);
        }
    }

    public boolean mousePressed(Minecraft a2, int a3, int a4) {
        ee a5;
        if (super.mousePressed(a2, a3, a4)) {
            a5.j.accept(a5);
        }
        return super.mousePressed(a2, a3, a4);
    }
}

