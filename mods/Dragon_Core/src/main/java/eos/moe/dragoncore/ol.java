/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.gui.FontRenderer
 */
package eos.moe.dragoncore;

import eos.moe.dragoncore.fd;
import eos.moe.dragoncore.in;
import eos.moe.dragoncore.sd;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;

public class ol {
    public ol() {
        ol a2;
    }

    public static void ALLATORIxDEMO(String a2, String a3, double a4, double a5, boolean a6, boolean a7, boolean a8, int a9) {
        if (sd.ALLATORIxDEMO() <= 0.05f) {
            return;
        }
        if (a2.isEmpty()) {
            return;
        }
        if (a8) {
            a2 = a2.replace("&", "\u00a7");
        }
        int a10 = a9 >> 16 & 0xFF;
        int a11 = a9 >> 8 & 0xFF;
        int a12 = a9 & 0xFF;
        if (a3 == null || a3.isEmpty()) {
            FontRenderer a13 = Minecraft.func_71410_x().field_71466_p;
            a9 = a10 | a11 << 8 | a12 << 16 | (int)(sd.ALLATORIxDEMO() * 255.0f) << 24;
            a13.func_175065_a(a2, (float)((int)(a4 - (double)(a6 ? ol.ALLATORIxDEMO(a2, null, false) / 2 : 0))), (float)a5, a9, a7);
        } else {
            in a14 = fd.ALLATORIxDEMO(a3);
            a14.ALLATORIxDEMO((float)a10 / 255.0f, (float)a11 / 255.0f, (float)a12 / 255.0f, a14.o);
            a14.c(a2, (int)(a4 - (double)(a6 ? ol.ALLATORIxDEMO(a2, null, false) / 2 : 0)), (int)a5);
        }
    }

    public static String ALLATORIxDEMO(String a2, String a3, int a4, boolean a5) {
        if (a2 == null || a2.isEmpty()) {
            return "";
        }
        if (a5) {
            a2 = a2.replace("&", "\u00a7");
        }
        if (a3 == null || a3.isEmpty()) {
            return Minecraft.func_71410_x().field_71466_p.func_78262_a(a2, a4, false);
        }
        return fd.ALLATORIxDEMO(a3).ALLATORIxDEMO(a2, a4, false);
    }

    public static int ALLATORIxDEMO(String a2, String a3, boolean a4) {
        if (a2 == null || a2.isEmpty()) {
            return 0;
        }
        if (a4) {
            a2 = a2.replace("&", "\u00a7");
        }
        if (a3 == null || a3.isEmpty()) {
            return Minecraft.func_71410_x().field_71466_p.func_78256_a(a2);
        }
        return fd.ALLATORIxDEMO(a3).c(a2);
    }
}

