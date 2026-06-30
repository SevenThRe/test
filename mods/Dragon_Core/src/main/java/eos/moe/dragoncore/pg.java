/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.gui.GuiMainMenu
 *  net.minecraft.client.gui.GuiMultiplayer
 *  net.minecraft.client.gui.GuiOptions
 *  net.minecraft.client.gui.GuiScreen
 *  net.minecraft.client.gui.achievement.GuiStats
 *  net.minecraft.client.gui.advancements.GuiScreenAdvancements
 *  net.minecraft.client.gui.inventory.GuiChest
 *  net.minecraft.inventory.IInventory
 *  net.minecraft.realms.RealmsBridge
 *  net.minecraftforge.fml.relauncher.ReflectionHelper
 */
package eos.moe.dragoncore;

import eos.moe.dragoncore.Base;
import eos.moe.dragoncore.de;
import eos.moe.dragoncore.i;
import eos.moe.dragoncore.pf;
import eos.moe.dragoncore.qg;
import eos.moe.dragoncore.ui;
import eos.moe.dragoncore.v;
import eos.moe.dragoncore.xf;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import javax.script.Invocable;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiMainMenu;
import net.minecraft.client.gui.GuiMultiplayer;
import net.minecraft.client.gui.GuiOptions;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.achievement.GuiStats;
import net.minecraft.client.gui.advancements.GuiScreenAdvancements;
import net.minecraft.client.gui.inventory.GuiChest;
import net.minecraft.inventory.IInventory;
import net.minecraft.realms.RealmsBridge;
import net.minecraftforge.fml.relauncher.ReflectionHelper;

/*
 * Duplicate member names - consider using --renamedupmembers true
 */
public class pg {
    private static ScriptEngine k;
    private static boolean ALLATORIxDEMO;

    public pg() {
        pg a2;
    }

    @i(f={"\u6e38\u620f\u9009\u9879", "Minecraft_Options"}, c=true)
    public static void d() {
        Minecraft a2 = Minecraft.func_71410_x();
        a2.func_147108_a((GuiScreen)new GuiOptions(null, a2.field_71474_y));
    }

    @i(f={"\u9000\u51fa\u6e38\u620f", "Minecraft_Quit"}, c=true)
    public static void x() {
        Minecraft a2 = Minecraft.func_71410_x();
        boolean a3 = a2.func_71387_A();
        boolean a4 = a2.func_181540_al();
        a2.field_71441_e.func_72882_A();
        a2.func_71403_a(null);
        if (a3) {
            a2.func_147108_a((GuiScreen)new GuiMainMenu());
        } else if (a4) {
            RealmsBridge a5 = new RealmsBridge();
            a5.switchToRealms((GuiScreen)new GuiMainMenu());
        } else {
            a2.func_147108_a((GuiScreen)new GuiMultiplayer((GuiScreen)new GuiMainMenu()));
        }
    }

    @i(f={"\u5173\u95ed\u6e38\u620f", "Minecraft_Shutdown"}, c=true)
    public static void f() {
        Minecraft a2 = Minecraft.func_71410_x();
        a2.func_71400_g();
    }

    @i(f={"\u6e38\u620f\u8fdb\u5ea6", "Minecraft_Advancements"}, c=true)
    public static void c() {
        Minecraft a2 = Minecraft.func_71410_x();
        a2.func_147108_a((GuiScreen)new GuiScreenAdvancements(a2.field_71439_g.field_71174_a.func_191982_f()));
    }

    @i(f={"\u6e38\u620f\u7edf\u8ba1", "Minecraft_Stat"}, c=true)
    public static void ALLATORIxDEMO() {
        Minecraft a2 = Minecraft.func_71410_x();
        a2.func_147108_a((GuiScreen)new GuiStats(null, a2.field_71439_g.func_146107_m()));
    }

    @i(f={"\u8bbe\u7f6e\u754c\u9762\u5c3a\u5bf8", "Minecraft_Set_Gui_Scale"}, c=true)
    public static void ALLATORIxDEMO(int a2) {
        Minecraft a3 = Minecraft.func_71410_x();
        a3.field_71474_y.field_74335_Z = a2;
        a3.field_71474_y.func_74303_b();
    }

    @i(f={"\u53d6\u754c\u9762\u5c3a\u5bf8", "Minecraft_Get_Gui_Scale"})
    public static double ALLATORIxDEMO() {
        return de.o.func_78325_e();
    }

    @i(f={"\u53d6\u5f53\u524d\u6e38\u620f\u754c\u9762\u540d", "Minecraft_Get_Screen_Name"})
    public static String ALLATORIxDEMO() {
        GuiScreen a2 = Minecraft.func_71410_x().field_71462_r;
        if (a2 == null) {
            return "none";
        }
        if (a2 instanceof ui) {
            return "dragoncore:" + ((ui)a2).fa;
        }
        if (a2 instanceof GuiChest) {
            IInventory a3 = (IInventory)ReflectionHelper.getPrivateValue(GuiChest.class, (Object)((GuiChest)a2), (String[])new String[]{"field_147015_w", "lowerChestInventory"});
            return "chest:" + (a3 != null && a3.func_145748_c_() != null ? a3.func_145748_c_().func_150260_c() : "");
        }
        return "minecraft:" + a2.getClass().getSimpleName();
    }

    @i(f={"\u53d6FPS", "Minecraft_Get_FPS"})
    public static int ALLATORIxDEMO() {
        return Minecraft.func_175610_ah();
    }

    @i(f={"\u52a0\u8f7dJS", "JS_Load"})
    public static boolean ALLATORIxDEMO() {
        try {
            if (k == null) {
                ScriptEngineManager a2 = new ScriptEngineManager(ClassLoader.getSystemClassLoader());
                k = a2.getEngineByName("nashorn");
            }
            return true;
        }
        catch (Exception a3) {
            return false;
        }
    }

    @i(f={"\u8bbe\u7f6e\u6750\u8d28\u5305"}, c=true)
    public static void ALLATORIxDEMO(String[] a2) {
        Minecraft a3 = Minecraft.func_71410_x();
        a3.field_71474_y.field_151453_l.clear();
        a3.field_71474_y.field_151453_l.addAll(Arrays.asList(a2));
        a3.field_71474_y.func_74303_b();
        a3.func_175603_A();
    }

    @i(f={"\u6267\u884cJS", "JS_EXECUTE"})
    public static v ALLATORIxDEMO(String a3, v ... a4) {
        Object[] a5 = Arrays.stream(a4).map(a2 -> {
            if (a2 instanceof qg) {
                qg a3 = (qg)a2;
                return a3.ALLATORIxDEMO().stream().map(v::c).collect(Collectors.toList());
            }
            return a2.c();
        }).toArray();
        return pg.ALLATORIxDEMO(a3, a5);
    }

    public static v ALLATORIxDEMO(String a2, Object ... a3) {
        if (k == null) {
            return xf.ALLATORIxDEMO("error! engine is null");
        }
        try {
            k.put("base", new Base());
            k.eval(a2);
            Invocable a4 = (Invocable)((Object)k);
            Object a5 = a4.invokeFunction("execute", a3);
            return pg.ALLATORIxDEMO(a5);
        }
        catch (Exception a6) {
            a6.printStackTrace();
            return xf.ALLATORIxDEMO("error! Exception on log");
        }
    }

    public static v ALLATORIxDEMO(Object a2) {
        if (a2 instanceof Boolean) {
            return pf.ALLATORIxDEMO(a2);
        }
        if (a2 instanceof String) {
            return xf.ALLATORIxDEMO((String)a2);
        }
        if (a2 instanceof Number) {
            return pf.ALLATORIxDEMO(a2);
        }
        if (a2 instanceof List) {
            List a3 = (List)a2;
            List<v> a4 = a3.stream().map(pg::ALLATORIxDEMO).collect(Collectors.toList());
            return new qg(a4);
        }
        return xf.ALLATORIxDEMO(a2.toString());
    }
}

