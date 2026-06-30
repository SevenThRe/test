/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.renderer.GlStateManager
 *  net.minecraft.entity.EntityLivingBase
 */
package eos.moe.dragoncore;

import eos.moe.dragoncore.kp;
import eos.moe.dragoncore.nh;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.EntityLivingBase;
import org.yaml.snakeyamla.configuration.ConfigurationSection;

public abstract class cz {
    public kp c;
    public Map<String, String> q;
    public nh b;
    public nh o;
    public nh y;
    public nh k;
    public nh ALLATORIxDEMO;

    public cz(kp a2, ConfigurationSection a3) {
        cz a4;
        a4.c = a2;
        a4.b = a2.ALLATORIxDEMO(a3.get("x", 0));
        a4.o = a2.ALLATORIxDEMO(a3.get("y", 0));
        a4.y = a2.ALLATORIxDEMO(a3.get("visible", true));
        a4.k = a2.ALLATORIxDEMO(a3.get("scale", 1));
        a4.ALLATORIxDEMO = a2.ALLATORIxDEMO(a3.get("glow", false));
        a4.q = new HashMap<String, String>();
        for (String a5 : a3.getKeys(false)) {
            a4.q.put(a5, String.valueOf(a3.get(a5)));
        }
    }

    public final void c(EntityLivingBase a2, float a3, boolean a4) {
        cz a5;
        if (!a5.y.c()) {
            return;
        }
        double a6 = a5.b.c();
        double a7 = a5.o.c();
        double a8 = a5.k.c();
        GlStateManager.func_179094_E();
        GlStateManager.func_179137_b((double)a6, (double)a7, (double)0.0);
        GlStateManager.func_179139_a((double)a8, (double)a8, (double)1.0);
        a5.ALLATORIxDEMO(a2, a3, a4);
        GlStateManager.func_179121_F();
    }

    public abstract void ALLATORIxDEMO(EntityLivingBase var1, float var2, boolean var3);

    public void ALLATORIxDEMO(String a2, Object a3) {
        cz a4;
        switch (a2 = a2.toLowerCase(Locale.ROOT)) {
            case "x": {
                a4.b = a4.c.ALLATORIxDEMO(a3);
                return;
            }
            case "y": {
                a4.o = a4.c.ALLATORIxDEMO(a3);
                return;
            }
            case "visible": {
                a4.y = a4.c.ALLATORIxDEMO(a3);
                return;
            }
            case "scale": {
                a4.k = a4.c.ALLATORIxDEMO(a3);
                return;
            }
        }
        a4.q.put(a2, String.valueOf(a3));
    }

    public Object ALLATORIxDEMO(String a2) {
        cz a3;
        switch (a2 = a2.toLowerCase(Locale.ROOT)) {
            case "x": {
                return a3.b.c();
            }
            case "y": {
                return a3.o.c();
            }
            case "visible": {
                return a3.y.c() ? "true" : "false";
            }
            case "scale": {
                return a3.k.c();
            }
        }
        return a3.q.getOrDefault(a2, "");
    }
}

