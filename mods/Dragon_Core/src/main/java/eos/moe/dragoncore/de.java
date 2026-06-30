/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.collect.ImmutableList
 *  mezz.jei.gui.recipes.RecipesGui
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.gui.GuiChat
 *  net.minecraft.client.gui.GuiScreen
 *  net.minecraft.client.gui.GuiSleepMP
 *  net.minecraft.client.gui.ScaledResolution
 *  net.minecraft.client.gui.inventory.GuiChest
 *  net.minecraft.client.gui.inventory.GuiContainer
 *  net.minecraft.client.gui.inventory.GuiInventory
 *  net.minecraft.client.resources.I18n
 *  net.minecraft.client.settings.KeyBinding
 *  net.minecraft.inventory.Container
 *  net.minecraft.inventory.IInventory
 *  net.minecraft.potion.Potion
 *  net.minecraft.potion.PotionEffect
 *  net.minecraft.util.text.ChatType
 *  net.minecraft.util.text.ITextComponent
 *  net.minecraft.util.text.TextComponentString
 *  net.minecraftforge.client.event.ClientChatReceivedEvent
 *  net.minecraftforge.client.event.GuiOpenEvent
 *  net.minecraftforge.client.event.GuiScreenEvent$DrawScreenEvent$Post
 *  net.minecraftforge.client.event.InputUpdateEvent
 *  net.minecraftforge.client.event.RenderGameOverlayEvent$ElementType
 *  net.minecraftforge.client.event.RenderGameOverlayEvent$Pre
 *  net.minecraftforge.fml.common.Mod$EventBusSubscriber
 *  net.minecraftforge.fml.common.eventhandler.EventPriority
 *  net.minecraftforge.fml.common.eventhandler.SubscribeEvent
 *  net.minecraftforge.fml.common.gameevent.InputEvent$KeyInputEvent
 *  net.minecraftforge.fml.common.gameevent.TickEvent$ClientTickEvent
 *  net.minecraftforge.fml.common.gameevent.TickEvent$Phase
 *  net.minecraftforge.fml.common.gameevent.TickEvent$RenderTickEvent
 *  net.minecraftforge.fml.relauncher.ReflectionHelper
 *  org.lwjgl.input.Keyboard
 */
package eos.moe.dragoncore;

import com.google.common.collect.ImmutableList;
import eos.moe.dragoncore.ca;
import eos.moe.dragoncore.hl;
import eos.moe.dragoncore.nj;
import eos.moe.dragoncore.od;
import eos.moe.dragoncore.pf;
import eos.moe.dragoncore.qd;
import eos.moe.dragoncore.qv;
import eos.moe.dragoncore.rj;
import eos.moe.dragoncore.sj;
import eos.moe.dragoncore.tg;
import eos.moe.dragoncore.ui;
import eos.moe.dragoncore.v;
import eos.moe.dragoncore.wi;
import eos.moe.dragoncore.xf;
import java.awt.Point;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import mezz.jei.gui.recipes.RecipesGui;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiChat;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.GuiSleepMP;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.gui.inventory.GuiChest;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.gui.inventory.GuiInventory;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.settings.KeyBinding;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.text.ChatType;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentString;
import net.minecraftforge.client.event.ClientChatReceivedEvent;
import net.minecraftforge.client.event.GuiOpenEvent;
import net.minecraftforge.client.event.GuiScreenEvent;
import net.minecraftforge.client.event.InputUpdateEvent;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.InputEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;
import net.minecraftforge.fml.relauncher.ReflectionHelper;
import org.lwjgl.input.Keyboard;
import org.yaml.snakeyamla.configuration.file.YamlConfiguration;

@Mod.EventBusSubscriber(modid="dragoncore")
public class de {
    public static final Map<String, ui> c = new ConcurrentHashMap<String, ui>();
    public static final Minecraft q = Minecraft.func_71410_x();
    private static final Set<String> b = new HashSet<String>();
    public static ScaledResolution o = new ScaledResolution(q);
    public static ITextComponent y = new TextComponentString("");
    public static ITextComponent k = new TextComponentString("");
    private static final Set<KeyBinding> ALLATORIxDEMO = new HashSet<KeyBinding>();

    public de() {
        de a2;
    }

    @SubscribeEvent
    public static void ALLATORIxDEMO(TickEvent.RenderTickEvent a2) {
        if (a2.phase == TickEvent.Phase.START) {
            Point a3 = sj.ALLATORIxDEMO();
            sj.b = a3.x;
            sj.o = a3.y;
            rj.ALLATORIxDEMO().ALLATORIxDEMO();
        }
        if (a2.phase == TickEvent.Phase.END) {
            sj.y = de.q.field_71417_B.field_74377_a;
            sj.k = de.q.field_71417_B.field_74375_b;
        }
    }

    @SubscribeEvent
    public static void ALLATORIxDEMO(InputUpdateEvent a2) {
        GuiScreen a3 = Minecraft.func_71410_x().field_71462_r;
        if (a3 instanceof ui && ((ui)a3).ta) {
            tg.ALLATORIxDEMO(a2.getMovementInput());
        }
    }

    @SubscribeEvent
    public static void ALLATORIxDEMO(TickEvent.ClientTickEvent a3) {
        o = new ScaledResolution(q);
        if (a3.phase == TickEvent.Phase.END) {
            return;
        }
        if (de.q.field_71439_g == null) {
            return;
        }
        wi.b.ALLATORIxDEMO("tickEnd", new v[0]);
        Map<String, v> a4 = qv.b.ALLATORIxDEMO();
        a4.entrySet().removeIf(a2 -> ((String)a2.getKey()).startsWith("buff_"));
        int a5 = 1;
        for (PotionEffect a6 : de.q.field_71439_g.func_70651_bq()) {
            Potion a7 = a6.func_188419_a();
            String a8 = a6.func_76453_d();
            String a9 = I18n.func_135052_a((String)a7.func_76393_a(), (Object[])new Object[0]);
            if (a6.func_76458_c() == 1) {
                a9 = a9 + " " + I18n.func_135052_a((String)"enchantment.level.2", (Object[])new Object[0]);
            } else if (a6.func_76458_c() == 2) {
                a9 = a9 + " " + I18n.func_135052_a((String)"enchantment.level.3", (Object[])new Object[0]);
            } else if (a6.func_76458_c() == 3) {
                a9 = a9 + " " + I18n.func_135052_a((String)"enchantment.level.4", (Object[])new Object[0]);
            }
            String a10 = Potion.func_188410_a((PotionEffect)a6, (float)1.0f);
            int a11 = (int)((float)a6.func_76459_b() / 20.0f);
            hl a12 = new hl();
            a12.ALLATORIxDEMO().put("name", xf.ALLATORIxDEMO(a8));
            a12.ALLATORIxDEMO().put("displayname", xf.ALLATORIxDEMO(a9));
            a12.ALLATORIxDEMO().put("duration", xf.ALLATORIxDEMO(a10));
            a12.ALLATORIxDEMO().put("second", pf.ALLATORIxDEMO(a11));
            a12.ALLATORIxDEMO().put("level", pf.ALLATORIxDEMO(a6.func_76458_c()));
            a4.put("buff_" + a5, a12);
            ++a5;
        }
    }

    @SubscribeEvent
    public static void ALLATORIxDEMO(ClientChatReceivedEvent a2) {
        if (a2.getType() == ChatType.GAME_INFO) {
            return;
        }
        String a3 = a2.getMessage().func_150254_d();
        if (!a3.isEmpty()) {
            boolean a4 = false;
            for (ui a5 : wi.b.ALLATORIxDEMO()) {
                y = a2.getMessage();
                k = a2.getMessage();
                a4 = a4 || a5.runGuiAction(nj.w);
                y = new TextComponentString("");
            }
            if (a4) {
                a2.setCanceled(true);
            }
        }
    }

    @SubscribeEvent
    public static void ALLATORIxDEMO(GuiScreenEvent.DrawScreenEvent.Post a2) {
        if (Minecraft.func_71410_x().field_71439_g == null) {
            return;
        }
        for (int a3 = 0; a3 < 5; ++a3) {
            for (Map.Entry<String, ui> a4 : c.entrySet()) {
                if (b.contains(a4.getKey()) || a4.getValue().e != a3 || !a4.getValue().l.equalsIgnoreCase("hud_post")) continue;
                a4.getValue().func_73863_a(a2.getMouseX(), a2.getMouseY(), q.func_193989_ak());
            }
        }
    }

    @SubscribeEvent
    public static void ALLATORIxDEMO(RenderGameOverlayEvent.Pre a2) {
        if (ca.b && a2.getType() == RenderGameOverlayEvent.ElementType.HEALTH) {
            a2.setCanceled(true);
            return;
        }
        if (a2.getType() == RenderGameOverlayEvent.ElementType.ALL) {
            b.clear();
            Point a3 = new Point(sj.b, sj.o);
            for (ui a4 : c.values()) {
                b.addAll(a4.aa);
            }
            if (de.q.field_71462_r instanceof ui) {
                b.addAll(((ui)de.q.field_71462_r).aa);
            }
            if (!(de.q.field_71462_r instanceof GuiChat || de.q.field_71462_r instanceof ui && ((ui)de.q.field_71462_r).j)) {
                a3.x = -9999;
            }
            for (int a5 = 0; a5 < 5; ++a5) {
                for (Map.Entry<String, ui> a6 : c.entrySet()) {
                    String a7;
                    if (b.contains(a6.getKey()) || a6.getValue().e != a5 || (a7 = a6.getValue().l).equalsIgnoreCase("hud_post") && de.q.field_71462_r != null) continue;
                    a6.getValue().func_73863_a(a3.x, a3.y, q.func_193989_ak());
                }
            }
        } else if (b.contains(a2.getType().name())) {
            a2.setCanceled(true);
        }
    }

    @SubscribeEvent
    public static void ALLATORIxDEMO(InputEvent.KeyInputEvent a2) {
        String a3;
        if (Minecraft.func_71410_x().field_71462_r != null) {
            return;
        }
        for (KeyBinding a4 : ca.ALLATORIxDEMO) {
            if (a4.func_151470_d()) {
                if (ALLATORIxDEMO.contains(a4)) continue;
                ALLATORIxDEMO.add(a4);
                for (ui a5 : c.values()) {
                    a5.ba = a4.func_151464_g();
                    a5.runGuiAction(nj.d);
                    a5.ba = "";
                }
                continue;
            }
            if (!ALLATORIxDEMO.contains(a4)) continue;
            ALLATORIxDEMO.remove(a4);
            for (ui a5 : c.values()) {
                a5.ba = a4.func_151464_g();
                a5.runGuiAction(nj.p);
                a5.ba = "";
            }
        }
        if (Keyboard.getEventKeyState()) {
            a3 = Keyboard.getKeyName((int)Keyboard.getEventKey());
            for (ui a6 : c.values()) {
                a6.ba = a3;
                a6.runGuiAction(nj.d);
                a6.ba = "";
            }
        } else {
            a3 = Keyboard.getKeyName((int)Keyboard.getEventKey());
            for (ui a6 : c.values()) {
                a6.ba = a3;
                a6.runGuiAction(nj.p);
                a6.ba = "";
            }
        }
    }

    @SubscribeEvent(priority=EventPriority.LOW)
    public static void ALLATORIxDEMO(GuiOpenEvent a2) {
        Minecraft a3 = Minecraft.func_71410_x();
        if (a2.getGui() != null) {
            Object a4;
            Container a5;
            if (a2.getGui().getClass().getSimpleName().equals("RecipesGui")) {
                RecipesGui a6 = (RecipesGui)a2.getGui();
                if (a6.getParentScreen() instanceof ui) {
                    ((ui)a6.getParentScreen()).ignoreNextClose();
                }
                return;
            }
            if (a2.getGui() instanceof ui) {
                return;
            }
            String a7 = null;
            Container container = a5 = a3.field_71439_g == null ? null : Minecraft.func_71410_x().field_71439_g.field_71069_bz;
            if (a2.getGui() instanceof GuiChat && !(a2.getGui() instanceof GuiSleepMP)) {
                if (ca.x && a2.getGui().getClass() == GuiChat.class) {
                    return;
                }
                a4 = (GuiChat)a2.getGui();
                qv.b.ALLATORIxDEMO("guichatopen", new xf(((GuiChat)a4).field_146409_v));
                Minecraft.func_71410_x().func_152344_a(() -> {
                    for (ui a2 : c.values()) {
                        a2.runGuiAction(nj.a);
                    }
                });
            }
            if (a2.getGui().getClass() == GuiInventory.class) {
                if (!de.q.field_71442_b.func_78758_h()) {
                    a7 = "GuiInventory";
                }
                a5 = ((GuiInventory)a2.getGui()).field_147002_h;
            } else if (a2.getGui() instanceof GuiChest) {
                a4 = (IInventory)ReflectionHelper.getPrivateValue(GuiChest.class, (Object)((GuiChest)a2.getGui()), (String[])new String[]{"field_147015_w", "lowerChestInventory"});
                a7 = a4 != null && a4.func_145748_c_() != null ? a4.func_145748_c_().func_150260_c() : null;
                a5 = ((GuiChest)a2.getGui()).field_147002_h;
            } else {
                if (a2.getGui() instanceof GuiContainer) {
                    a5 = ((GuiContainer)a2.getGui()).field_147002_h;
                }
                a7 = a2.getGui().getClass().getSimpleName();
            }
            if (a7 != null && a5 != null && !a7.isEmpty()) {
                a4 = de.ALLATORIxDEMO(a7);
                System.out.println("DragonCore GuiOpen->" + a7);
                if (a4 != null) {
                    System.out.println("  - match success");
                    ui a8 = new ui((String)((qd)a4).c(), (YamlConfiguration)((qd)a4).ALLATORIxDEMO(), wi.b.ALLATORIxDEMO(), a5, od.q);
                    a8.setOriginalName(a7);
                    a8.setOriginalGui(a2.getGui());
                    a8.open();
                    a2.setGui((GuiScreen)a8);
                }
            }
        }
    }

    public static void c(String a2, YamlConfiguration a3) {
        ui a4 = new ui(a2, a3, wi.b.ALLATORIxDEMO(), Minecraft.func_71410_x().field_71439_g.field_71069_bz, od.q);
        a4.open();
        Minecraft.func_71410_x().func_147108_a((GuiScreen)a4);
    }

    public static void ALLATORIxDEMO(String a2, YamlConfiguration a3) {
        ui a4 = c.remove(a2.toLowerCase(Locale.ROOT));
        if (a4 != null) {
            a4.func_146281_b();
        }
        ui a5 = new ui(a2, a3, wi.b.ALLATORIxDEMO(), Minecraft.func_71410_x().field_71439_g.field_71069_bz, od.b);
        a5.initGui_();
        a5.open();
        c.put(a2.toLowerCase(Locale.ROOT), a5);
    }

    public static void f() {
        for (Map.Entry<String, YamlConfiguration> a2 : wi.b.ALLATORIxDEMO().entrySet()) {
            String a3 = a2.getValue().getString("match");
            if (a3 == null || !a3.toLowerCase(Locale.ROOT).equalsIgnoreCase("hud") && !a3.toLowerCase(Locale.ROOT).equalsIgnoreCase("hud_post")) continue;
            de.ALLATORIxDEMO(a2.getKey(), a2.getValue());
        }
    }

    public static void c() {
        ArrayList<ui> a2 = new ArrayList<ui>(c.values());
        c.clear();
        for (ui a3 : a2) {
            a3.func_146281_b();
        }
    }

    public static void ALLATORIxDEMO(String a2) {
        ui a3 = c.remove(a2.toLowerCase(Locale.ROOT));
        if (a3 != null) {
            a3.func_146281_b();
        }
    }

    public static qd<String, YamlConfiguration> ALLATORIxDEMO(String a2) {
        for (Map.Entry<String, YamlConfiguration> a3 : wi.b.ALLATORIxDEMO().entrySet()) {
            YamlConfiguration a4 = a3.getValue();
            ImmutableList a5 = null;
            if (a4.isList("match")) {
                a5 = a4.getStringList("match");
            } else {
                String a6 = a4.getString("match");
                if (a6 != null && !a6.isEmpty()) {
                    a5 = ImmutableList.of((Object)a6);
                }
            }
            if (a5 == null) continue;
            for (String a7 : a5) {
                if (a7 == null || a2 == null || !a2.equals(a7) && !a2.matches(a7)) continue;
                return qd.ALLATORIxDEMO(a3.getKey(), a3.getValue());
            }
        }
        return null;
    }
}

