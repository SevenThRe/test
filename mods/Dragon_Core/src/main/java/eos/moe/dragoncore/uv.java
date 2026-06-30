/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.renderer.GlStateManager
 *  net.minecraft.client.renderer.texture.SimpleTexture
 *  net.minecraft.util.ResourceLocation
 *  org.lwjgl.opengl.GL11
 */
package eos.moe.dragoncore;

import eos.moe.dragoncore.mp;
import eos.moe.dragoncore.rs;
import eos.moe.dragoncore.yq;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.texture.SimpleTexture;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

public class uv {
    private boolean v = false;
    private boolean m = false;
    private boolean c = false;
    private boolean q = false;
    private boolean b = true;
    private SimpleTexture o;
    private mp y;
    private final File k;
    private final String ALLATORIxDEMO;

    public uv(File a2) throws IOException {
        uv a3;
        a3.k = a2.getParentFile();
        String a4 = a3.k.getAbsolutePath();
        a3.ALLATORIxDEMO = a4.substring(a4.indexOf("dragoncore\\") + 9);
        BufferedReader a5 = new BufferedReader(new FileReader(a2));
        String a6 = null;
        while ((a6 = a5.readLine()) != null) {
            ResourceLocation a7;
            String a8;
            String[] a9 = rs.s.split(a6);
            if (a9[0].equalsIgnoreCase("$texture")) {
                a8 = a3.ALLATORIxDEMO.endsWith("/") ? a3.ALLATORIxDEMO + a9[1] : a3.ALLATORIxDEMO + "/" + a9[1];
                a7 = new ResourceLocation("dragoncore", a8);
                Minecraft.func_71410_x().func_110434_K().func_110577_a(a7);
                a3.o = (SimpleTexture)Minecraft.func_71410_x().func_110434_K().func_110581_b(a7);
                continue;
            }
            if (a9[0].equalsIgnoreCase("$cubemap")) {
                a8 = a4.endsWith("/") ? a4 + a9[1] : a4 + "/" + a9[1];
                a7 = new ResourceLocation(a8);
                a3.y = new mp(a7);
                continue;
            }
            if (a9[0].equalsIgnoreCase("$nocull")) {
                a3.q = Boolean.parseBoolean(a9[1]);
                continue;
            }
            if (a9[0].equalsIgnoreCase("$translucent")) {
                a3.v = Boolean.parseBoolean(a9[1]);
                continue;
            }
            if (a9[0].equalsIgnoreCase("$wireframe")) {
                a3.m = Boolean.parseBoolean(a9[1]);
                continue;
            }
            if (a9[0].equalsIgnoreCase("$unlit")) {
                a3.c = Boolean.parseBoolean(a9[1]);
                continue;
            }
            if (!a9[0].equalsIgnoreCase("$depthmask")) continue;
            a3.b = Boolean.parseBoolean(a9[1]);
        }
        a5.close();
    }

    public void c() {
        uv a2;
        GL11.glBindTexture((int)3553, (int)a2.o.func_110552_b());
        if (a2.v) {
            yq.o.c(new Object[0]);
        }
        if (a2.m) {
            yq.b.c(new Object[0]);
        }
        if (a2.c) {
            yq.q.c(new Object[0]);
        }
        if (a2.q) {
            yq.y.c(new Object[0]);
        } else {
            yq.y.ALLATORIxDEMO(new Object[0]);
        }
        if (!a2.b) {
            GlStateManager.func_179132_a((boolean)false);
        }
        if (a2.y != null) {
            a2.y.start();
        }
    }

    public void ALLATORIxDEMO() {
        uv a2;
        if (a2.v) {
            yq.o.ALLATORIxDEMO(new Object[0]);
        }
        if (a2.m) {
            yq.b.ALLATORIxDEMO(new Object[0]);
        }
        if (a2.c) {
            yq.q.ALLATORIxDEMO(new Object[0]);
        }
        if (!a2.q) {
            yq.y.ALLATORIxDEMO(new Object[0]);
        }
        if (!a2.b) {
            GlStateManager.func_179132_a((boolean)true);
        }
        if (a2.y != null) {
            a2.y.end();
        }
    }
}

