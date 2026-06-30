/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.model.ModelBase
 *  net.minecraft.client.renderer.GlStateManager
 *  net.minecraft.client.renderer.vertex.DefaultVertexFormats
 *  net.minecraftforge.fml.relauncher.Side
 *  net.minecraftforge.fml.relauncher.SideOnly
 *  org.lwjgl.opengl.GL11
 */
package eos.moe.armourers;

import eos.moe.armourers.cm;
import eos.moe.armourers.fe;
import eos.moe.armourers.g;
import eos.moe.armourers.gk;
import eos.moe.armourers.gl;
import eos.moe.armourers.jg;
import eos.moe.armourers.mk;
import eos.moe.armourers.ql;
import eos.moe.armourers.ul;
import eos.moe.armourers.vk;
import eos.moe.armourers.xd;
import java.util.ArrayList;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.lwjgl.opengl.GL11;

@SideOnly(value=Side.CLIENT)
public class rk
extends ModelBase {
    private Minecraft m = Minecraft.func_71410_x();
    public static rk j = new rk();

    public rk() {
        rk a2;
    }

    /*
     * Unable to fully structure code
     */
    public void renderPart(mk a) {
        ++cm.j;
        var2_3 = a.r().r();
        var3_5 = var2_3.getModelForDye(a);
        var4_6 = jg.r();
        v0 = var5_7 = 0;
        while (v0 < var3_5.s.length) {
            if (var3_5.m[var5_7] && !var3_5.s[var5_7].r()) {
                v1 = var3_5;
                v1.s[var5_7].z();
                a.r(var2_3.l[var5_7], a, var2_3);
                v1.s[var5_7].r();
                v1.y();
            }
            v0 = ++var5_7;
        }
        ql.r("cube.png");
        var5_7 = 0;
        var2_4 = 0;
        var6_8 = var3_5.r();
        a = a.r();
        if (!a.y()) {
            var6_8 = 0;
        }
        if (var6_8 > a) {
            a = var6_8;
        }
        if (a == 0) ** GOTO lbl30
        if (var4_6) {
            var5_7 = a * 4;
            v2 = var4_6;
        } else {
            var5_7 = a * 2;
lbl30:
            // 2 sources

            v2 = var4_6;
        }
        var2_4 = v2 != false ? var5_7 + 4 : var5_7 + 2;
        GlStateManager.func_179123_a();
        GL11.glEnable((int)2884);
        GlStateManager.func_179098_w();
        GlStateManager.func_179128_n((int)5890);
        GlStateManager.func_179094_E();
        GlStateManager.func_179137_b((double)0.0, (double)(fe.r() / 256.0), (double)0.0);
        a = var3_5.s.length;
        v3 = a = var5_7;
        while (v3 < var2_4) {
            if (a >= var5_7) {
                v4 = true;
                v5 = a;
            } else {
                v4 = false;
                v5 = a;
            }
            if (v4 & v5 < var2_4) {
                var4_6 = false;
                if (a % 2 == 1) {
                    var4_6 = true;
                }
                if (a >= 0) {
                    v6 = true;
                    v7 = a;
                } else {
                    v6 = false;
                    v7 = a;
                }
                if (v6 & v7 < var3_5.s.length && var3_5.m[a] && var3_5.s[a].r()) {
                    if (var4_6) {
                        GlStateManager.func_179123_a();
                        GlStateManager.func_179140_f();
                        xd.x();
                    }
                    if (vk.p) {
                        GL11.glPolygonMode((int)1032, (int)6913);
                        GL11.glLineWidth((float)1.0f);
                    }
                    var3_5.s[a].h();
                    if (vk.p) {
                        GL11.glPolygonMode((int)1032, (int)6914);
                    }
                    if (var4_6) {
                        xd.h();
                        GlStateManager.func_179145_e();
                        GlStateManager.func_179099_b();
                    }
                }
            }
            v3 = ++a;
        }
        GlStateManager.func_179121_F();
        GlStateManager.func_179128_n((int)5888);
        GlStateManager.func_179117_G();
        GlStateManager.func_179131_c((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
        GlStateManager.func_179099_b();
    }

    private /* synthetic */ void r(ArrayList<gl> a2, mk a3, ul a4) {
        int n2;
        g g2 = gk.j;
        g2.r(DefaultVertexFormats.field_181712_l);
        int n3 = n2 = 0;
        while (n3 < a2.size()) {
            a2.get(++n2).r(g2, a3, a4);
            n3 = n2;
        }
        g2.y();
    }
}

