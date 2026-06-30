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
    public void func_191745_a(Minecraft a, int a, int a, float a) {
        block7: {
            if (!a.field_146125_m) break block7;
            v0 = a;
            a = v0.field_71466_p;
            v0.func_110434_K().func_110577_a(ee.field_146122_a);
            GlStateManager.func_179131_c((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
            if (a < a.field_146128_h || a < a.field_146129_i) ** GOTO lbl-1000
            v1 = a;
            if (a >= v1.field_146128_h + v1.field_146120_f) ** GOTO lbl-1000
            v2 = a;
            if (a < v2.field_146129_i + v2.field_146121_g) {
                v3 = true;
            } else lbl-1000:
            // 3 sources

            {
                v3 = false;
            }
            a.field_146123_n = v3;
            v4 = a;
            v5 = a;
            a = v5.func_146114_a(v5.field_146123_n);
            GlStateManager.func_179147_l();
            GlStateManager.func_187428_a((GlStateManager.SourceFactor)GlStateManager.SourceFactor.SRC_ALPHA, (GlStateManager.DestFactor)GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA, (GlStateManager.SourceFactor)GlStateManager.SourceFactor.ONE, (GlStateManager.DestFactor)GlStateManager.DestFactor.ZERO);
            GlStateManager.func_187401_a((GlStateManager.SourceFactor)GlStateManager.SourceFactor.SRC_ALPHA, (GlStateManager.DestFactor)GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA);
            v5.func_73729_b(v5.field_146128_h, a.field_146129_i, 0, 46 + a * 20, a.field_146120_f / 2, a.field_146121_g);
            v4.func_73729_b(v5.field_146128_h + a.field_146120_f / 2, a.field_146129_i, 200 - a.field_146120_f / 2, 46 + a * 20, a.field_146120_f / 2, a.field_146121_g);
            v4.func_146119_b(a, a, a);
            a = 0xE0E0E0;
            if (v4.packedFGColour != 0) {
                v6 = a;
                v7 = v6;
                a = v6.packedFGColour;
            } else if (!a.field_146124_l) {
                a = 0xA0A0A0;
                v7 = a;
            } else {
                if (a.field_146123_n) {
                    a = 0xFFFFA0;
                }
                v7 = a;
            }
            v8 = a;
            v9 = a;
            v7.func_73732_a(a, v8.field_146126_j, v8.field_146128_h + a.field_146120_f / 2, v9.field_146129_i + (v9.field_146121_g - 8) / 2, a);
        }
    }

    public boolean func_146116_c(Minecraft a2, int a3, int a4) {
        ee a5;
        if (super.func_146116_c(a2, a3, a4)) {
            a5.j.accept(a5);
        }
        return super.func_146116_c(a2, a3, a4);
    }
}

