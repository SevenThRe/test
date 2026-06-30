/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.renderer.GlStateManager
 *  net.minecraft.client.renderer.RenderHelper
 */
package eos.moe.dragoncore;

import eos.moe.dragoncore.uha;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.RenderHelper;
import org.yaml.snakeyamla.configuration.ConfigurationSection;

public class mha
extends uha {
    private float ALLATORIxDEMO;

    public mha(ConfigurationSection a2) {
        super(a2);
        mha a3;
        a3.ALLATORIxDEMO = (float)a2.getInt("size", 16) / 16.0f;
    }

    @Override
    public void ALLATORIxDEMO() {
        mha a2;
        GlStateManager.pushMatrix();
        RenderHelper.disableStandardItemLighting();
        RenderHelper.enableGUIStandardItemLighting();
        GlStateManager.enableDepth();
        GlStateManager.translate((double)a2.c(), (double)a2.ALLATORIxDEMO(), (double)250.0);
        GlStateManager.scale((float)a2.ALLATORIxDEMO, (float)a2.ALLATORIxDEMO, (float)a2.ALLATORIxDEMO);
        Minecraft.getMinecraft().getRenderItem().renderItemAndEffectIntoGUI(a2.ALLATORIxDEMO().v, 0, 0);
        GlStateManager.popMatrix();
        GlStateManager.disableDepth();
    }
}

