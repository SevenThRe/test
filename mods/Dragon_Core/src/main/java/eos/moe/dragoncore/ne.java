/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.gui.GuiChat
 *  net.minecraft.client.gui.GuiScreen
 *  net.minecraft.client.gui.GuiTextField
 *  net.minecraft.client.gui.inventory.GuiFurnace
 *  net.minecraft.inventory.IInventory
 *  net.minecraft.tileentity.TileEntityFurnace
 *  net.minecraftforge.fml.relauncher.ReflectionHelper
 */
package eos.moe.dragoncore;

import eos.moe.dragoncore.de;
import eos.moe.dragoncore.i;
import eos.moe.dragoncore.od;
import eos.moe.dragoncore.sj;
import eos.moe.dragoncore.ui;
import eos.moe.dragoncore.v;
import eos.moe.dragoncore.wi;
import java.util.Locale;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiChat;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.GuiTextField;
import net.minecraft.client.gui.inventory.GuiFurnace;
import net.minecraft.inventory.IInventory;
import net.minecraft.tileentity.TileEntityFurnace;
import net.minecraftforge.fml.relauncher.ReflectionHelper;
import org.yaml.snakeyamla.configuration.file.YamlConfiguration;

/*
 * Duplicate member names - consider using --renamedupmembers true
 */
public class ne {
    public ne() {
        ne a2;
    }

    @i(f={"\u53d6\u5c4f\u5e55\u5bbd\u5ea6", "Screen_Get_Width", "w"})
    public static double c() {
        return de.o.func_78327_c();
    }

    @i(f={"\u53d6\u5c4f\u5e55\u9ad8\u5ea6", "Screen_Get_Height", "h"})
    public static double ALLATORIxDEMO() {
        return de.o.func_78324_d();
    }

    @i(f={"\u53d6\u5c4f\u5e55\u5bbd\u5ea6\u6bd4\u4f8b", "Screen_Get_Width_Ratio", "wr"})
    public static String f() {
        return String.format("%.3f", (double)sj.b / de.o.func_78327_c());
    }

    @i(f={"\u53d6\u5c4f\u5e55\u9ad8\u5ea6\u6bd4\u4f8b", "Screen_Get_Height_Ratio", "hr"})
    public static String c() {
        return String.format("%.3f", (double)sj.o / de.o.func_78324_d());
    }

    @i(f={"\u8bbe\u7f6e\u663e\u793a", "\u663e\u793a", "Screen_Set_Show"})
    public static void c(String a2, v a3) {
        GuiScreen a4;
        boolean a5 = false;
        if (a3 != null) {
            boolean bl2 = a5 = a3.ALLATORIxDEMO() == 0.0;
        }
        if ((a4 = Minecraft.func_71410_x().field_71462_r) instanceof ui && (a2.isEmpty() || ((ui)a4).wa.toLowerCase().equals(a2.toLowerCase(Locale.ROOT)))) {
            ((ui)a4).z = a5;
        } else if (de.c.containsKey(a2.toLowerCase(Locale.ROOT))) {
            de.c.get((Object)a2.toLowerCase((Locale)Locale.ROOT)).z = a5;
        }
    }

    @i(f={"\u8bbe\u7f6e\u9690\u85cf", "\u9690\u85cf", "Screen_Set_Hide"})
    public static void ALLATORIxDEMO(String a2, v a3) {
        GuiScreen a4;
        boolean a5 = true;
        if (a3 != null) {
            boolean bl2 = a5 = a3.ALLATORIxDEMO() != 0.0;
        }
        if ((a4 = Minecraft.func_71410_x().field_71462_r) instanceof ui && (a2.isEmpty() || ((ui)a4).wa.toLowerCase().equals(a2.toLowerCase(Locale.ROOT)))) {
            ((ui)a4).z = a5;
        } else if (de.c.containsKey(a2.toLowerCase(Locale.ROOT))) {
            de.c.get((Object)a2.toLowerCase((Locale)Locale.ROOT)).z = a5;
        }
    }

    @i(f={"\u5173\u95ed\u754c\u9762", "\u8fd4\u56de\u6e38\u620f", "Screen_Close"}, c=true)
    public static void x(ui a2) {
        Minecraft a4 = Minecraft.func_71410_x();
        if (a2.ma == od.b) {
            a2.func_146281_b();
            de.c.entrySet().removeIf(a3 -> a3.getValue() == a2);
        } else if (a2.ma == od.y) {
            wi.b.f();
        } else {
            a4.field_71439_g.func_71053_j();
        }
    }

    @i(f={"\u5173\u95ed\u4e3b\u754c\u9762", "Screen_Close_Main"}, c=true)
    public static void ALLATORIxDEMO() {
        Minecraft a2 = Minecraft.func_71410_x();
        a2.field_71439_g.func_71053_j();
    }

    @i(f={"\u91cd\u8f7d\u754c\u9762", "Screen_Reload"}, c=true)
    public static void f(ui a2) {
        a2.s = 0L;
        a2.reloadFromYaml(a2.getYaml());
    }

    @i(f={"\u5237\u65b0\u7f13\u5b58", "Screen_Cache_Update"})
    public static void c(ui a2) {
        a2.s = 0L;
    }

    @i(f={"\u91cd\u7f6e\u754c\u9762\u6253\u5f00\u65f6\u95f4", "Screen_OpenTime_Reset"})
    public static void ALLATORIxDEMO(ui a2) {
        a2.u = System.currentTimeMillis();
    }

    @i(f={"\u53d6\u754c\u9762\u6253\u5f00\u65f6\u95f4", "Screen_OpenTime_Get"})
    public static double x(ui a2) {
        return a2.u;
    }

    @i(f={"\u53d6\u754c\u9762\u5b58\u6d3b\u65f6\u95f4", "Screen_ActiveTime"})
    public static double f(ui a2) {
        return System.currentTimeMillis() - a2.u;
    }

    @i(f={"\u6253\u5f00\u4e8c\u7ea7\u754c\u9762", "\u6253\u5f00\u5b50\u754c\u9762", "Screen_Open_Sub_Gui"}, c=true)
    public static void d(String a2) {
        wi.b.c(a2.toLowerCase(Locale.ROOT));
    }

    @i(f={"\u6253\u5f00GUI", "Screen_Open_Gui"}, c=true)
    public static void x(String a2) {
        wi.b.ALLATORIxDEMO(a2.toLowerCase(Locale.ROOT));
    }

    @i(f={"\u53d6\u5f53\u524d\u754c\u9762\u540d", "Screen_Get_Name"})
    public static String c(ui a2) {
        return a2.fa == null ? "" : a2.fa;
    }

    @i(f={"\u5f53\u524d\u662f\u5426\u6253\u5f00\u804a\u5929\u680f", "Screen_Chat_Opened"})
    public static boolean ALLATORIxDEMO() {
        return Minecraft.func_71410_x().field_71462_r instanceof GuiChat;
    }

    @i(f={"\u6253\u5f00HUD", "Screen_Open_Hud"}, c=true)
    public static void f(String a2) {
        YamlConfiguration a3 = wi.b.ALLATORIxDEMO(a2);
        if (a3 != null) {
            de.ALLATORIxDEMO(a2, a3);
        }
    }

    @i(f={"\u6253\u5f00\u804a\u5929\u680f", "Screen_Open_ChatGui"}, c=true)
    public static void c(String a2) {
        Minecraft.func_71410_x().func_147108_a((GuiScreen)new GuiChat(a2));
    }

    @i(f={"\u8bbe\u7f6e\u804a\u5929\u680f\u5185\u5bb9", "Screen_ChatGui_Set"}, c=true)
    public static void ALLATORIxDEMO(String a2, int a3) {
        if (Minecraft.func_71410_x().field_71462_r instanceof GuiChat) {
            GuiChat a4 = (GuiChat)Minecraft.func_71410_x().field_71462_r;
            GuiTextField a5 = (GuiTextField)ReflectionHelper.getPrivateValue(GuiChat.class, (Object)a4, (String[])new String[]{"inputField", "field_146415_a"});
            if (a3 == 0) {
                a5.func_146180_a(a5.func_146179_b() + a2);
            } else if (a3 == 1) {
                a5.func_146180_a(a2);
            } else if (a3 == 2) {
                a5.func_146191_b(a2);
            }
        } else {
            ne.c(a2);
        }
    }

    @i(f={"\u53d6\u804a\u5929\u680f\u5185\u5bb9", "Screen_ChatGui_Get"})
    public static String ALLATORIxDEMO() {
        if (Minecraft.func_71410_x().field_71462_r instanceof GuiChat) {
            GuiChat a2 = (GuiChat)Minecraft.func_71410_x().field_71462_r;
            GuiTextField a3 = (GuiTextField)ReflectionHelper.getPrivateValue(GuiChat.class, (Object)a2, (String[])new String[]{"inputField", "field_146415_a"});
            return a3.func_146179_b();
        }
        return "";
    }

    @i(f={"\u5173\u95edHUD", "Screen_Close_Hud"})
    public static void ALLATORIxDEMO(String a2) {
        de.ALLATORIxDEMO(a2);
    }

    @i(f={"\u53d6\u539f\u754c\u9762\u540d", "Screen_Original_Name"})
    public static String ALLATORIxDEMO(ui a2) {
        return a2.h;
    }

    @i(f={"\u7194\u7089\u662f\u5426\u7194\u70bc\u4e2d", "Screen_Furnace_IsBurning"})
    public static boolean ALLATORIxDEMO(ui a2) {
        return a2.f instanceof GuiFurnace && TileEntityFurnace.func_174903_a((IInventory)((GuiFurnace)a2.f).field_147086_v);
    }

    @i(f={"\u53d6\u7194\u7089\u71c3\u6599\u503c", "Screen_Furnace_BurnTime"})
    public static double c(ui a2) {
        return a2.f instanceof GuiFurnace ? ne.ALLATORIxDEMO(((GuiFurnace)a2.f).field_147086_v) : 0.0;
    }

    @i(f={"\u53d6\u7194\u7089\u8fdb\u5ea6\u503c", "Screen_Furnace_CookProgress"})
    public static double ALLATORIxDEMO(ui a2) {
        return a2.f instanceof GuiFurnace ? ne.c(((GuiFurnace)a2.f).field_147086_v) : 0.0;
    }

    private static /* synthetic */ double c(IInventory a2) {
        double a3 = a2.func_174887_a_(2);
        double a4 = a2.func_174887_a_(3);
        return a4 != 0.0 && a3 != 0.0 ? a3 / a4 : 0.0;
    }

    private static /* synthetic */ double ALLATORIxDEMO(IInventory a2) {
        double a3 = a2.func_174887_a_(1);
        if (a3 == 0.0) {
            a3 = 200.0;
        }
        return (double)a2.func_174887_a_(0) / a3;
    }
}

