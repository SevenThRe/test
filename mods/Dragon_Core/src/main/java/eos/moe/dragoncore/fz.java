/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.renderer.BufferBuilder
 *  net.minecraft.client.renderer.GlStateManager
 *  net.minecraft.client.renderer.OpenGlHelper
 *  net.minecraft.client.renderer.Tessellator
 *  net.minecraft.client.renderer.vertex.DefaultVertexFormats
 *  net.minecraft.entity.EntityLivingBase
 */
package eos.moe.dragoncore;

import eos.moe.dragoncore.cz;
import eos.moe.dragoncore.kp;
import eos.moe.dragoncore.nh;
import eos.moe.dragoncore.ol;
import eos.moe.dragoncore.sd;
import java.awt.Color;
import java.util.Locale;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.entity.EntityLivingBase;
import org.yaml.snakeyamla.configuration.ConfigurationSection;

public class fz
extends cz {
    private static final Color v = new Color(0, 0, 0, 63);
    private nh m;
    private nh c;
    private nh q;

    public fz(kp a2, ConfigurationSection a3) {
        super(a2, a3);
        fz a4;
        a4.m = a2.ALLATORIxDEMO(a3.getString("text", ""));
        a4.c = a2.ALLATORIxDEMO(a3.getString("background", "0,0,0,63"));
        a4.q = a2.ALLATORIxDEMO(a3.getString("font", ""));
    }

    @Override
    public void ALLATORIxDEMO(EntityLivingBase a2, float a3, boolean a4) {
        fz a5;
        String a6 = a5.m.c();
        String a7 = a5.c.c();
        String a8 = a5.q.c();
        Color a9 = null;
        if (!"hide".equalsIgnoreCase(a7) && (a9 = sd.ALLATORIxDEMO(a7)) == null) {
            a9 = v;
        }
        float a10 = OpenGlHelper.lastBrightnessX;
        float a11 = OpenGlHelper.lastBrightnessY;
        boolean a12 = a5.ALLATORIxDEMO.c();
        if (a12) {
            OpenGlHelper.setLightmapTextureCoords((int)OpenGlHelper.lightmapTexUnit, (float)240.0f, (float)a11);
        }
        a5.ALLATORIxDEMO(a6, a8, a9, a4);
        if (a12) {
            OpenGlHelper.setLightmapTextureCoords((int)OpenGlHelper.lightmapTexUnit, (float)a10, (float)a11);
        }
    }

    private /* synthetic */ void ALLATORIxDEMO(String a2, String a3, Color a4, boolean a5) {
        if (a5 && a4 != null) {
            GlStateManager.disableTexture2D();
            int a6 = ol.ALLATORIxDEMO(a2, a3, true);
            float a7 = (float)a4.getRed() / 255.0f;
            float a8 = (float)a4.getGreen() / 255.0f;
            float a9 = (float)a4.getBlue() / 255.0f;
            float a10 = (float)a4.getAlpha() / 255.0f;
            Tessellator a11 = Tessellator.getInstance();
            BufferBuilder a12 = a11.getBuffer();
            a12.begin(7, DefaultVertexFormats.POSITION_COLOR);
            a12.pos(-1.0, -1.0, 0.0).color(a7, a8, a9, a10).endVertex();
            a12.pos(-1.0, 8.0, 0.0).color(a7, a8, a9, a10).endVertex();
            a12.pos((double)(a6 + 1), 8.0, 0.0).color(a7, a8, a9, a10).endVertex();
            a12.pos((double)(a6 + 1), -1.0, 0.0).color(a7, a8, a9, a10).endVertex();
            a11.draw();
            GlStateManager.enableTexture2D();
        }
        ol.ALLATORIxDEMO(a2, a3, 0.0, 0.0, false, false, true, -1);
    }

    @Override
    public void ALLATORIxDEMO(String a2, Object a3) {
        fz a4;
        switch (a2.toLowerCase(Locale.ROOT)) {
            case "background": {
                a4.c = ((kp)((Object)a4.c)).ALLATORIxDEMO((String)a3);
                return;
            }
            case "text": {
                a4.m = ((kp)((Object)a4.c)).ALLATORIxDEMO((String)a3);
                return;
            }
        }
        super.ALLATORIxDEMO(a2, a3);
    }

    @Override
    public Object ALLATORIxDEMO(String a2) {
        fz a3;
        switch (a2.toLowerCase(Locale.ROOT)) {
            case "height": {
                return "10";
            }
            case "width": {
                return String.valueOf(ol.ALLATORIxDEMO(a3.m.c(), null, true));
            }
            case "background": {
                return a3.c.c();
            }
            case "text": {
                return a3.m.c();
            }
        }
        return super.ALLATORIxDEMO(a2);
    }
}

