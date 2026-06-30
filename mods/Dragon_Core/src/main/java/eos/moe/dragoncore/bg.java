/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.entity.EntityPlayerSP
 *  net.minecraft.util.text.ITextComponent
 *  net.minecraft.util.text.TextComponentString
 */
package eos.moe.dragoncore;

import eos.moe.dragoncore.i;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentString;

public class bg {
    public bg() {
        bg a2;
    }

    @i(f={"\u8c03\u8bd5\u8f93\u51fa", "log"})
    public static void f(String ... a2) {
        for (String a3 : a2) {
            System.out.println(a3);
        }
    }

    @i(f={"\u6d88\u606f", "Message"}, c=true)
    public static void c(String ... a2) {
        EntityPlayerSP a3 = Minecraft.func_71410_x().field_71439_g;
        if (a3 == null) {
            return;
        }
        for (String a4 : a2) {
            a4 = a4.replace("&", "\u00a7");
            a3.func_145747_a((ITextComponent)new TextComponentString(a4));
        }
    }

    @i(f={"\u804a\u5929", "Chat"}, c=true)
    public static void ALLATORIxDEMO(String ... a2) {
        EntityPlayerSP a3 = Minecraft.func_71410_x().field_71439_g;
        if (a3 == null) {
            return;
        }
        for (String a4 : a2) {
            a4 = a4.replace("\u00a7", "&");
            a4 = a4.replace("%p%", a3.func_70005_c_());
            a4 = a4.replace("%player%", a3.func_70005_c_());
            a4 = a4.replace("<player>", a3.func_70005_c_());
            a3.func_71165_d(a4);
        }
    }

    @i(f={"ActionBar"}, c=true)
    public static void ALLATORIxDEMO(String a2) {
        Minecraft.func_71410_x().field_71456_v.func_110326_a(a2, false);
    }

    @i(f={"Title"}, c=true)
    public static void ALLATORIxDEMO(String a2, String a3, int a4, int a5, int a6) {
        a5 = a5 == 0 ? 60 : a5;
        Minecraft.func_71410_x().field_71456_v.func_175178_a(null, null, a4, a5, a6);
        Minecraft.func_71410_x().field_71456_v.func_175178_a(a2, null, a4, a5, a6);
        Minecraft.func_71410_x().field_71456_v.func_175178_a(null, a3, a4, a5, a6);
    }
}

