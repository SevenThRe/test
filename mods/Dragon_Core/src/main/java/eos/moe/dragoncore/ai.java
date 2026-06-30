/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.Minecraft
 *  org.lwjgl.opengl.Display
 *  org.lwjgl.opengl.DisplayMode
 */
package eos.moe.dragoncore;

import eos.moe.dragoncore.i;
import net.minecraft.client.Minecraft;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;

/*
 * Duplicate member names - consider using --renamedupmembers true
 */
public class ai {
    public ai() {
        ai a2;
    }

    @i(f={"Display_SetResizable"}, c=true)
    public static void c(boolean a2) {
        Display.setResizable((boolean)false);
        Display.setResizable((boolean)a2);
    }

    @i(f={"Display_SetFullScreen"}, c=true)
    public static void ALLATORIxDEMO(boolean a2) {
        Minecraft a3 = Minecraft.func_71410_x();
        if (a2) {
            if (!a3.func_71372_G()) {
                a3.func_71352_k();
            }
        } else if (a3.func_71372_G()) {
            a3.func_71352_k();
        }
    }

    @i(f={"Display_IsFullScreen"})
    public static boolean c() {
        return Minecraft.func_71410_x().func_71372_G();
    }

    @i(f={"Display_IsResizable"})
    public static boolean ALLATORIxDEMO() {
        return Display.isResizable();
    }

    @i(f={"Display_Desktop_Width"})
    public static int k() {
        return Display.getDesktopDisplayMode().getWidth();
    }

    @i(f={"Display_Desktop_Height"})
    public static int d() {
        return Display.getDesktopDisplayMode().getHeight();
    }

    @i(f={"Display_Window_Width"})
    public static int x() {
        return Display.getWidth();
    }

    @i(f={"Display_Window_Height"})
    public static int f() {
        return Display.getHeight();
    }

    @i(f={"Display_Window_X"})
    public static int c() {
        return Display.getX();
    }

    @i(f={"Display_Window_Y"})
    public static int ALLATORIxDEMO() {
        return Display.getY();
    }

    @i(f={"Display_Resize"}, c=true)
    public static void c(int a2, int a3) {
        Minecraft a4 = Minecraft.func_71410_x();
        try {
            boolean a5 = Display.isResizable();
            Display.setFullscreen((boolean)false);
            Display.setResizable((boolean)true);
            Display.setDisplayMode((DisplayMode)new DisplayMode(a2, a3));
            ai.c(a5);
            a4.func_71370_a(a2, a3);
            Display.setVSyncEnabled((boolean)a4.field_71474_y.field_74352_v);
            a4.func_175601_h();
        }
        catch (Exception a6) {
            a6.printStackTrace();
        }
    }

    @i(f={"Display_Location"}, c=true)
    public static void ALLATORIxDEMO(int a2, int a3) {
        Display.setLocation((int)a2, (int)a3);
    }
}

