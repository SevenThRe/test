/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.renderer.OpenGlHelper
 *  net.minecraft.entity.EntityLivingBase
 */
package eos.moe.dragoncore;

import eos.moe.dragoncore.cz;
import eos.moe.dragoncore.kp;
import eos.moe.dragoncore.nh;
import eos.moe.dragoncore.sd;
import eos.moe.dragoncore.ww;
import java.util.Locale;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.entity.EntityLivingBase;
import org.yaml.snakeyamla.configuration.ConfigurationSection;

public class cw
extends cz {
    private nh t;
    private nh r;
    private nh x;
    private nh v;
    private nh m;
    private nh c;
    private nh q;

    public cw(kp a2, ConfigurationSection a3) {
        super(a2, a3);
        cw a4;
        a4.t = a2.ALLATORIxDEMO(a3.getString("texture", "unknown.png"));
        a4.v = a2.ALLATORIxDEMO(a3.get("u", 0));
        a4.m = a2.ALLATORIxDEMO(a3.get("v", 0));
        a4.r = a2.ALLATORIxDEMO(a3.get("width", 0));
        a4.x = a2.ALLATORIxDEMO(a3.get("height", 0));
        a4.c = a3.contains("textureWidth") ? a2.ALLATORIxDEMO(a3.get("textureWidth", 0)) : a4.r;
        a4.q = a3.contains("textureHeight") ? a2.ALLATORIxDEMO(a3.get("textureHeight", 0)) : a4.x;
    }

    @Override
    public void ALLATORIxDEMO(EntityLivingBase a2, float a3, boolean a4) {
        cw a5;
        ww.ALLATORIxDEMO(a5.t.c());
        if (a5.ALLATORIxDEMO.c()) {
            float a6 = OpenGlHelper.lastBrightnessX;
            float a7 = OpenGlHelper.lastBrightnessY;
            OpenGlHelper.setLightmapTextureCoords((int)OpenGlHelper.lightmapTexUnit, (float)240.0f, (float)a7);
            sd.ALLATORIxDEMO(0.0, 0.0, a5.v.c(), a5.m.c(), a5.r.c(), a5.x.c(), a5.c.c(), a5.q.c());
            OpenGlHelper.setLightmapTextureCoords((int)OpenGlHelper.lightmapTexUnit, (float)a6, (float)a7);
        } else {
            sd.ALLATORIxDEMO(0.0, 0.0, a5.v.c(), a5.m.c(), a5.r.c(), a5.x.c(), a5.c.c(), a5.q.c());
        }
    }

    @Override
    public void ALLATORIxDEMO(String a2, Object a3) {
        cw a4;
        switch (a2.toLowerCase(Locale.ROOT)) {
            case "texture": {
                a4.t = ((kp)((Object)a4.c)).ALLATORIxDEMO((String)a3);
                return;
            }
            case "width": {
                a4.r = ((kp)((Object)a4.c)).ALLATORIxDEMO(a3);
                return;
            }
            case "height": {
                a4.x = ((kp)((Object)a4.c)).ALLATORIxDEMO(a3);
                return;
            }
            case "u": {
                a4.v = ((kp)((Object)a4.c)).ALLATORIxDEMO(a3);
                return;
            }
            case "v": {
                a4.m = ((kp)((Object)a4.c)).ALLATORIxDEMO(a3);
                return;
            }
            case "texturewidth": {
                a4.c = ((kp)((Object)a4.c)).ALLATORIxDEMO(a3);
                return;
            }
            case "textureheight": {
                a4.q = ((kp)((Object)a4.c)).ALLATORIxDEMO(a3);
                return;
            }
        }
        super.ALLATORIxDEMO(a2, a3);
    }

    @Override
    public Object ALLATORIxDEMO(String a2) {
        cw a3;
        switch (a2.toLowerCase(Locale.ROOT)) {
            case "texture": {
                return a3.t.c();
            }
            case "width": {
                return a3.r.c();
            }
            case "height": {
                return a3.x.c();
            }
            case "u": {
                return a3.v.c();
            }
            case "v": {
                return a3.m.c();
            }
            case "texturewidth": {
                return a3.c.c();
            }
            case "textureheight": {
                return a3.q.c();
            }
        }
        return super.ALLATORIxDEMO(a2);
    }
}

