/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.entity.EntityPlayerSP
 *  net.minecraft.client.renderer.EntityRenderer
 *  net.minecraft.util.ResourceLocation
 *  net.minecraftforge.fml.common.gameevent.TickEvent$ClientTickEvent
 */
package eos.moe.dragoncore;

import eos.moe.dragoncore.i;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.client.renderer.EntityRenderer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.gameevent.TickEvent;

public class nz {
    private static String ALLATORIxDEMO;

    public nz() {
        nz a2;
    }

    @i(f={"\u542f\u7528\u7740\u8272\u5668"}, c=true)
    public static void ALLATORIxDEMO(String a2) {
        nz.ALLATORIxDEMO();
        ALLATORIxDEMO = a2;
    }

    @i(f={"\u5173\u95ed\u7740\u8272\u5668"}, c=true)
    public static void c() {
        nz.ALLATORIxDEMO();
        ALLATORIxDEMO = null;
    }

    public static void ALLATORIxDEMO(TickEvent.ClientTickEvent a2) {
        EntityRenderer a3;
        EntityPlayerSP a4 = Minecraft.getMinecraft().player;
        if (a4 == null && ALLATORIxDEMO != null) {
            nz.c();
        } else if (a4 != null && (a3 = Minecraft.getMinecraft().entityRenderer) != null && ALLATORIxDEMO != null && !a3.isShaderActive()) {
            a3.loadShader(new ResourceLocation("dragoncore", "shaders/post/" + ALLATORIxDEMO + ".json"));
        }
    }

    public static void ALLATORIxDEMO() {
        EntityRenderer a2 = Minecraft.getMinecraft().entityRenderer;
        a2.stopUseShader();
    }
}

