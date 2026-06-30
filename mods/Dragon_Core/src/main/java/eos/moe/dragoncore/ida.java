/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.renderer.BufferBuilder
 *  net.minecraft.client.renderer.GlStateManager
 *  net.minecraft.client.renderer.GlStateManager$DestFactor
 *  net.minecraft.client.renderer.GlStateManager$SourceFactor
 *  net.minecraft.client.renderer.Tessellator
 *  net.minecraft.client.renderer.vertex.DefaultVertexFormats
 */
package eos.moe.dragoncore;

import eos.moe.dragoncore.uha;
import java.awt.Color;
import java.util.ArrayList;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import org.yaml.snakeyamla.configuration.ConfigurationSection;

public class ida
extends uha {
    private String b;
    private String o;
    private int y;
    private int k;
    private int ALLATORIxDEMO;

    public ida(ConfigurationSection a2) {
        super(a2);
        ida a3;
        a3.y = ida.ALLATORIxDEMO(a2.getString("startcolor", "#ffffffff"));
        a3.k = ida.ALLATORIxDEMO(a2.getString("endcolor", "#ffffffff"));
        a3.b = String.valueOf(a2.get("width", "0"));
        a3.o = String.valueOf(a2.get("height", "0"));
    }

    public double x() {
        ida a2;
        return a2.ALLATORIxDEMO().ALLATORIxDEMO(a2.b);
    }

    public double f() {
        ida a2;
        return a2.ALLATORIxDEMO().ALLATORIxDEMO(a2.o);
    }

    @Override
    public void ALLATORIxDEMO() {
        ida a2;
        GlStateManager.pushMatrix();
        GlStateManager.translate((double)a2.c(), (double)a2.ALLATORIxDEMO(), (double)0.0);
        a2.c(0, 0.0, 0.0, a2.x(), a2.f(), a2.y, a2.k);
        GlStateManager.popMatrix();
    }

    public static int ALLATORIxDEMO(String a2) {
        try {
            if (a2.length() == 7 || a2.length() == 9 && a2.startsWith("#")) {
                ArrayList<Integer> a3 = new ArrayList<Integer>();
                for (int a4 = 1; a4 < a2.length(); a4 += 2) {
                    String a5 = a2.substring(a4, a4 + 2);
                    a3.add(Integer.parseInt(a5, 16));
                }
                if (a3.size() == 3) {
                    return ida.ALLATORIxDEMO(new Color((Integer)a3.get(0), (Integer)a3.get(1), (Integer)a3.get(2)));
                }
                return ida.ALLATORIxDEMO(new Color((Integer)a3.get(1), (Integer)a3.get(2), (Integer)a3.get(3), (Integer)a3.get(0)));
            }
        }
        catch (Exception a6) {
            a6.printStackTrace();
        }
        return ida.ALLATORIxDEMO(Color.WHITE);
    }

    public static int ALLATORIxDEMO(Color a2) {
        return a2.getAlpha() << 24 | a2.getRed() << 16 | a2.getGreen() << 8 | a2.getBlue();
    }

    public void c(int a2, double a3, double a4, double a5, double a6, int a7, int a8) {
        ida a9;
        float a10 = (float)(a7 >> 24 & 0xFF) / 255.0f;
        float a11 = (float)(a7 >> 16 & 0xFF) / 255.0f;
        float a12 = (float)(a7 >> 8 & 0xFF) / 255.0f;
        float a13 = (float)(a7 & 0xFF) / 255.0f;
        float a14 = (float)(a8 >> 24 & 0xFF) / 255.0f;
        float a15 = (float)(a8 >> 16 & 0xFF) / 255.0f;
        float a16 = (float)(a8 >> 8 & 0xFF) / 255.0f;
        float a17 = (float)(a8 & 0xFF) / 255.0f;
        GlStateManager.disableTexture2D();
        GlStateManager.enableBlend();
        GlStateManager.disableAlpha();
        GlStateManager.tryBlendFuncSeparate((GlStateManager.SourceFactor)GlStateManager.SourceFactor.SRC_ALPHA, (GlStateManager.DestFactor)GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA, (GlStateManager.SourceFactor)GlStateManager.SourceFactor.ONE, (GlStateManager.DestFactor)GlStateManager.DestFactor.ZERO);
        GlStateManager.shadeModel((int)7425);
        Tessellator a18 = Tessellator.getInstance();
        BufferBuilder a19 = a18.getBuffer();
        a19.begin(7, DefaultVertexFormats.POSITION_COLOR);
        if (a9.ALLATORIxDEMO == 0) {
            a19.pos(a5, a4, (double)a2).color(a11, a12, a13, a10).endVertex();
            a19.pos(a3, a4, (double)a2).color(a11, a12, a13, a10).endVertex();
            a19.pos(a3, a6, (double)a2).color(a15, a16, a17, a14).endVertex();
            a19.pos(a5, a6, (double)a2).color(a15, a16, a17, a14).endVertex();
        } else if (a9.ALLATORIxDEMO == 1) {
            a19.pos(a3, a4, (double)a2).color(a11, a12, a13, a10).endVertex();
            a19.pos(a3, a6, (double)a2).color(a11, a12, a13, a10).endVertex();
            a19.pos(a5, a6, (double)a2).color(a15, a16, a17, a14).endVertex();
            a19.pos(a5, a4, (double)a2).color(a15, a16, a17, a14).endVertex();
        } else if (a9.ALLATORIxDEMO == 2) {
            a19.pos(a5, a4, (double)a2).color(a11, a12, a13, a10).endVertex();
            a19.pos(a3, a4, (double)a2).color(a15, a16, a17, a14).endVertex();
            a19.pos(a3, a6, (double)a2).color(a11, a12, a13, a10).endVertex();
            a19.pos(a5, a6, (double)a2).color(a15, a16, a17, a14).endVertex();
        } else if (a9.ALLATORIxDEMO == 3) {
            a19.pos(a5, a4, (double)a2).color(a15, a16, a17, a14).endVertex();
            a19.pos(a3, a4, (double)a2).color(a11, a12, a13, a10).endVertex();
            a19.pos(a3, a6, (double)a2).color(a15, a16, a17, a14).endVertex();
            a19.pos(a5, a6, (double)a2).color(a11, a12, a13, a10).endVertex();
        }
        a18.draw();
        GlStateManager.shadeModel((int)7424);
        GlStateManager.disableBlend();
        GlStateManager.enableAlpha();
        GlStateManager.enableTexture2D();
    }

    public static void ALLATORIxDEMO(int a2, double a3, double a4, double a5, double a6, int a7, int a8) {
        float a9 = (float)(a7 >> 24 & 0xFF) / 255.0f;
        float a10 = (float)(a7 >> 16 & 0xFF) / 255.0f;
        float a11 = (float)(a7 >> 8 & 0xFF) / 255.0f;
        float a12 = (float)(a7 & 0xFF) / 255.0f;
        float a13 = (float)(a8 >> 24 & 0xFF) / 255.0f;
        float a14 = (float)(a8 >> 16 & 0xFF) / 255.0f;
        float a15 = (float)(a8 >> 8 & 0xFF) / 255.0f;
        float a16 = (float)(a8 & 0xFF) / 255.0f;
        GlStateManager.disableTexture2D();
        GlStateManager.enableBlend();
        GlStateManager.disableAlpha();
        GlStateManager.tryBlendFuncSeparate((GlStateManager.SourceFactor)GlStateManager.SourceFactor.SRC_ALPHA, (GlStateManager.DestFactor)GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA, (GlStateManager.SourceFactor)GlStateManager.SourceFactor.ONE, (GlStateManager.DestFactor)GlStateManager.DestFactor.ZERO);
        GlStateManager.shadeModel((int)7425);
        Tessellator a17 = Tessellator.getInstance();
        BufferBuilder a18 = a17.getBuffer();
        a18.begin(7, DefaultVertexFormats.POSITION_COLOR);
        a18.pos(a5, a4, (double)a2).color(a10, a11, a12, a9).endVertex();
        a18.pos(a3, a4, (double)a2).color(a10, a11, a12, a9).endVertex();
        a18.pos(a3, a6, (double)a2).color(a14, a15, a16, a13).endVertex();
        a18.pos(a5, a6, (double)a2).color(a14, a15, a16, a13).endVertex();
        a17.draw();
        GlStateManager.shadeModel((int)7424);
        GlStateManager.disableBlend();
        GlStateManager.enableAlpha();
        GlStateManager.enableTexture2D();
    }
}

