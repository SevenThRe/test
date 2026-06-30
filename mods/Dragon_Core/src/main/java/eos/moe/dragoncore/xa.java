/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.Minecraft
 *  net.minecraft.launchwrapper.Launch
 *  net.minecraft.util.text.ITextComponent
 *  net.minecraft.util.text.TextComponentString
 *  net.minecraft.util.text.TextFormatting
 *  org.apache.logging.log4j.LogManager
 *  org.apache.logging.log4j.Logger
 */
package eos.moe.dragoncore;

import eos.moe.dragoncore.za;
import net.minecraft.client.Minecraft;
import net.minecraft.launchwrapper.Launch;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextFormatting;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class xa {
    private static final String c = TextFormatting.AQUA + "[DEBUG] " + TextFormatting.RESET;
    private static final String q = TextFormatting.GOLD + "[TRACE] " + TextFormatting.RESET;
    public Logger b;
    public static boolean o = false;
    private boolean y = false;
    private za k = za.y;
    private boolean ALLATORIxDEMO = false;

    public xa(String a2) {
        xa a3;
        a3.b = LogManager.getLogger((String)a2);
        if (((Boolean)Launch.blackboard.get("fml.deobfuscatedEnvironment")).booleanValue()) {
            o = true;
            if (a3.b.getName().equals("dragoncore")) {
                // empty if block
            }
        }
    }

    public void w(String a2) {
        xa a3;
        a3.k(a2, new Object[0]);
    }

    public void z(String a2) {
        Minecraft.getMinecraft().player.sendMessage((ITextComponent)new TextComponentString(a2));
    }

    public void k(String a2, Object ... a3) {
        xa a4;
        a4.b.info(a2, a3);
    }

    public void k(String a2) {
        xa a3;
        a3.d(a2, new Object[0]);
    }

    public void d(String a2, Object ... a3) {
        xa a4;
        a4.b.warn(a2, a3);
    }

    public void d(String a2) {
        xa a3;
        a3.x(a2, new Object[0]);
    }

    public void x(String a2, Object ... a3) {
        xa a4;
        a4.b.error(a2, a3);
    }

    public void ALLATORIxDEMO(Throwable a2) {
        xa a3;
        a3.ALLATORIxDEMO("", a2);
    }

    public void ALLATORIxDEMO(String a2, Throwable a3) {
        xa a4;
        a4.b.error(a2, a3);
    }

    public void x(String a2) {
        xa a3;
        a3.f(a2, new Object[0]);
    }

    public void f(String a2, Object ... a3) {
        xa a4;
        a4.b.fatal(a2, a3);
    }

    public void f(String a2) {
        xa a3;
        a3.c(a2, new Object[0]);
    }

    public void c(String a2, Object ... a3) {
        xa a4;
        if (a4.y && (a4.k == za.y || a4.k == za.k)) {
            if (a4.ALLATORIxDEMO) {
                a4.b.info(c + a2, a3);
            } else {
                a4.b.debug(a2, a3);
            }
        }
    }

    public void c(String a2) {
        xa a3;
        a3.ALLATORIxDEMO(a2, new Object[0]);
    }

    public void ALLATORIxDEMO(String a2, Object ... a3) {
        xa a4;
        if (a4.y && a4.k == za.k) {
            if (a4.ALLATORIxDEMO) {
                a4.b.info(q + a2, a3);
            } else {
                a4.b.debug(a2, a3);
            }
        }
    }

    public void ALLATORIxDEMO(String a2) {
        xa a3;
        if (!o) {
            return;
        }
        a3.k(a2);
        a3.k("This message appears only in dev enviroment.");
    }

    public void c(boolean a2) {
        a.ALLATORIxDEMO = a2;
    }

    public boolean f() {
        xa a2;
        return a2.ALLATORIxDEMO;
    }

    public boolean c() {
        xa a2;
        return a2.y;
    }

    public void ALLATORIxDEMO(boolean a2) {
        a.y = a2;
    }

    public void ALLATORIxDEMO(za a2) {
        a.k = a2;
    }

    public boolean ALLATORIxDEMO() {
        return o;
    }
}

