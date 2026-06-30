/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.renderer.GlStateManager
 */
package eos.moe.dragoncore;

import eos.moe.dragoncore.mk;
import eos.moe.dragoncore.uha;
import eos.moe.dragoncore.ww;
import net.minecraft.client.renderer.GlStateManager;
import org.yaml.snakeyamla.configuration.ConfigurationSection;

public class vda
extends uha {
    private String y;
    private String k;
    private String ALLATORIxDEMO;

    public vda(ConfigurationSection a2) {
        super(a2);
        vda a3;
        a3.ALLATORIxDEMO = a2.getString("path", "unknow.png");
        a3.y = String.valueOf(a2.get("width", "0"));
        a3.k = String.valueOf(a2.get("height", "0"));
    }

    public double x() {
        vda a2;
        return a2.ALLATORIxDEMO().ALLATORIxDEMO(a2.y);
    }

    public double f() {
        vda a2;
        return a2.ALLATORIxDEMO().ALLATORIxDEMO(a2.k);
    }

    @Override
    public void ALLATORIxDEMO() {
        vda a2;
        GlStateManager.func_179094_E();
        GlStateManager.func_179137_b((double)a2.c(), (double)a2.ALLATORIxDEMO(), (double)0.0);
        GlStateManager.func_179131_c((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
        ww.ALLATORIxDEMO(a2.ALLATORIxDEMO);
        float a3 = (float)a2.x();
        float a4 = (float)a2.f();
        GlStateManager.func_179118_c();
        mk.ALLATORIxDEMO(0.0f, 0.0f, 0.0f, 0.0f, a3, a4, a3, a4);
        GlStateManager.func_179101_C();
        GlStateManager.func_179140_f();
        GlStateManager.func_179121_F();
    }
}

