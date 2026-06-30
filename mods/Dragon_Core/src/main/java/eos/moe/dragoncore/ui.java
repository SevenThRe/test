/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.gui.GuiScreen
 *  net.minecraft.client.renderer.GlStateManager
 *  net.minecraft.inventory.ClickType
 *  net.minecraft.inventory.Container
 *  net.minecraft.inventory.Slot
 *  net.minecraft.util.text.ITextComponent
 *  org.lwjgl.input.Keyboard
 *  org.lwjgl.input.Mouse
 */
package eos.moe.dragoncore;

import eos.moe.dragoncore.af;
import eos.moe.dragoncore.an;
import eos.moe.dragoncore.de;
import eos.moe.dragoncore.ee;
import eos.moe.dragoncore.ff;
import eos.moe.dragoncore.hja;
import eos.moe.dragoncore.hl;
import eos.moe.dragoncore.hw;
import eos.moe.dragoncore.ie;
import eos.moe.dragoncore.jj;
import eos.moe.dragoncore.jk;
import eos.moe.dragoncore.ke;
import eos.moe.dragoncore.kh;
import eos.moe.dragoncore.nh;
import eos.moe.dragoncore.nj;
import eos.moe.dragoncore.nw;
import eos.moe.dragoncore.od;
import eos.moe.dragoncore.p;
import eos.moe.dragoncore.qh;
import eos.moe.dragoncore.qv;
import eos.moe.dragoncore.r;
import eos.moe.dragoncore.sj;
import eos.moe.dragoncore.v;
import eos.moe.dragoncore.wi;
import eos.moe.dragoncore.wq;
import eos.moe.dragoncore.zg;
import java.awt.Point;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.Future;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.inventory.ClickType;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.Slot;
import net.minecraft.util.text.ITextComponent;
import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.yaml.snakeyamla.configuration.ConfigurationSection;
import org.yaml.snakeyamla.configuration.MemorySection;
import org.yaml.snakeyamla.configuration.file.YamlConfiguration;

public class ui
extends af
implements r {
    private static final Pattern pa = Pattern.compile("[']([^']+)[']");
    private static final Pattern da = Pattern.compile("[%]([^%]+)[%]");
    public static final ui qa = new ui("default", new YamlConfiguration("DragonCore_Default"), new HashMap<String, YamlConfiguration>(), null, od.k);
    public final String wa;
    public final String fa;
    private final qv oa;
    public Map<String, v> ka = new HashMap<String, v>();
    public boolean ua;
    public boolean xa;
    public boolean ta;
    public Set<String> aa;
    public String ba = "";
    public od ma;
    public String h = "";
    public GuiScreen f;
    public jj d;
    public List<jj> p;
    public long u;
    public boolean w;
    public Map<String, nh> a;
    public int e;
    public List<String> n = new ArrayList<String>();
    public boolean j;
    public List<Future<v>> i;
    public String l;
    public boolean z;
    public long s;
    public int g;
    private nh t;
    private Map<String, jj> r;
    private nh x;
    private YamlConfiguration v;
    private boolean m;
    private boolean c;
    private qh q;
    private qh b;
    private qh o;
    private qh y;
    private qh k;
    private qh ALLATORIxDEMO;

    public ui(String a2, YamlConfiguration a3, Map<String, YamlConfiguration> a4, Container a5, od a6) {
        a7(null, a2, a3, a4, a5, a6);
        ui a7;
    }

    public ui(hl a4, String a5, YamlConfiguration a6, Map<String, YamlConfiguration> a7, Container a8, od a9) {
        super(a8);
        ui a10;
        a10.l = a6.getString("match", "");
        a10.wa = a5;
        a10.fa = a6.getFileName();
        a10.ma = a9;
        a10.eventButton = -1;
        a10.u = System.currentTimeMillis();
        a10.oa = new qv(a10, a4);
        a10.oa.ALLATORIxDEMO("\u65b9\u6cd5", "func", ie.ALLATORIxDEMO(a10));
        a10.p = new ArrayList<jj>();
        a10.a = new ConcurrentHashMap<String, nh>();
        a10.i = new CopyOnWriteArrayList<Future<v>>();
        a10.r = Collections.synchronizedMap(new jk());
        a10.width = de.o.getScaledWidth();
        a10.height = de.o.getScaledWidth();
        LinkedHashMap<String, YamlConfiguration> a11 = new LinkedHashMap<String, YamlConfiguration>();
        a11.put(a6.getFileName(), a6.reloadFromString());
        a11.putAll(a6.getStringList("import").stream().map(a3 -> (YamlConfiguration)a7.get(a3.toLowerCase(Locale.ROOT))).filter(Objects::nonNull).collect(Collectors.toMap(MemorySection::getFileName, a2 -> a2)));
        a10.v = sj.ALLATORIxDEMO(a11.values());
        a10.reloadFromYaml(a10.v);
    }

    @Override
    public void setAnimationValue(String a2, v a3) {
        ui a4;
        if (a3 == null) {
            a4.ka.remove(a2);
        } else {
            a4.ka.put(a2, a3);
        }
    }

    @Override
    public v getAnimationValue(String a2) {
        ui a3;
        return a3.ka.get(a2);
    }

    @Override
    public boolean isClosed() {
        ui a2;
        return a2.w;
    }

    public void setWorldAndResolution(Minecraft a2, int a3, int a4) {
        ui a5;
        super.setWorldAndResolution(a2, a3, a4);
        if (!a5.v.getBoolean("enableJei", false)) {
            an.c();
        }
    }

    public void open() {
        ui a2;
        a2.runGuiAction(nj.n);
        a2.m = true;
        if (a2.ma == od.q || a2.ma == od.b) {
            nw.ALLATORIxDEMO("DragonCore", "screen_open", a2.fa);
        }
    }

    public void reloadFunctions(String a2, int a3) {
        ui a4;
        if (a3 > 0) {
            for (Future<v> a5 : a4.i) {
                if (a5.isCancelled()) continue;
                a5.cancel(a3 == 2);
            }
            a4.i.clear();
        }
        a4.a.clear();
        ConfigurationSection a6 = a4.v.getConfigurationSection(a2);
        if (a6 != null) {
            for (String a7 : a6.getKeys(false)) {
                Object a8 = a6.get(a7);
                if (a8 == null) continue;
                a4.a.put(a7, a4.parseExpression(a6.get(a7)));
            }
        }
    }

    public void reloadFromYaml(YamlConfiguration a2) {
        ui a3;
        a3.v = a2;
        a3.reloadFunctions("Functions", 0);
        a3.g = a2.getInt("updateInterval");
        a3.z = a3.parseExpression(a2.get("hide", false)).c();
        a3.ua = a3.parseExpression(a2.get("debug", false)).c();
        a3.xa = a3.parseExpression(a2.get("through", false)).c();
        a3.ta = a3.parseExpression(a2.get("allowMove", false)).c();
        a3.aa = new HashSet<String>(a2.getStringList("hideHud"));
        a3.e = a2.getInt("priority");
        a3.j = a2.getBoolean("interactHud") && a3.ma == od.q;
        a3.q = new qh(a3, "scale", a3.parseExpression(a2.get("scale", 1)));
        a3.b = new qh(a3, "x", a3.parseExpression(a2.get("x", 0)));
        a3.o = new qh(a3, "y", a3.parseExpression(a2.get("y", 0)));
        a3.y = new qh(a3, "rotatez", a3.parseExpression(a2.get("rotatez", 0)));
        a3.k = new qh(a3, "visible", a3.parseExpression(a2.get("visible", true)));
        a3.ALLATORIxDEMO = new qh(a3, "enable", a3.parseExpression(a2.get("enable", true)));
        Map<String, jj> a4 = kh.ALLATORIxDEMO(a3, a2);
        if (a3.r != null) {
            for (Map.Entry<String, jj> a5 : new ArrayList<Map.Entry<String, jj>>(a3.r.entrySet())) {
                jj a6 = a4.get(a5.getKey());
                if (a6 != null) {
                    a6.s = a5.getValue().s.ALLATORIxDEMO(a6);
                    a6.g = a5.getValue().g.ALLATORIxDEMO(a6);
                    if (a6 instanceof ee && a5.getValue() instanceof ee) {
                        ((ee)a6).setFocused(((ee)a5.getValue()).isFocused());
                        ((ee)a6).setText(((ee)a5.getValue()).getText());
                    }
                    if (a6 instanceof ff && a5.getValue() instanceof ff) {
                        ((ff)a6).setText(((ff)a5.getValue()).getText());
                    }
                    a6.ka.putAll(a5.getValue().ka);
                    a6.ua.putAll(a5.getValue().ua);
                    continue;
                }
                a5.getValue().runAction("remove");
            }
            a3.r.clear();
            a3.r = a4;
        } else {
            a3.r = a4;
            for (jj a7 : a4.values()) {
                a7.runAction("create");
            }
        }
        a3.x = a3.parseExpression(a2.get("currentItemSize", 16));
        a3.t = a3.parseExpression(a2.get("allowEscClose", true));
        if (a3.m) {
            a3.runGuiAction(nj.j);
        }
    }

    public void setOriginalName(String a2) {
        a.h = a2;
    }

    public void setOriginalGui(GuiScreen a2) {
        a.f = a2;
    }

    @Override
    public void drawScreen(int a3, int a4, float a5) {
        ui a6;
        GuiScreen a7 = Minecraft.getMinecraft().currentScreen;
        a6.width = de.o.getScaledWidth();
        a6.height = de.o.getScaledHeight();
        if (a6.z || wq.k == null) {
            a6.p.clear();
            a6.d = null;
            return;
        }
        a6.runGuiAction(nj.i);
        boolean a8 = a6.k.c();
        a6.p.clear();
        a6.d = null;
        ArrayList<jj> a9 = new ArrayList<jj>(a6.r.values());
        a9.sort(Comparator.comparingDouble(a2 -> a2.d.c()));
        if (a8 && a6.g > 0 && System.currentTimeMillis() - a6.s > (long)a6.g) {
            for (jj a10 : a9) {
                a10.ta.c();
                if (!a10.ta.ALLATORIxDEMO()) continue;
                a10.cache(a3, a4);
            }
            a6.runGuiAction("cache");
            a6.s = System.currentTimeMillis();
        }
        if (a7 != null && a8) {
            for (jj a10 : a9) {
                if (!a10.isHovered(a3, a4)) continue;
                a6.d = a10;
                a6.p.add(a10);
            }
        }
        if (a8) {
            boolean a11;
            double a12 = a6.q.c();
            double a13 = a6.b.c();
            double a14 = a6.o.c();
            int a15 = (int)a6.y.c();
            boolean bl2 = a11 = a12 != 1.0 || a13 != 0.0 || a14 != 0.0 || a15 != 0;
            if (a11) {
                GlStateManager.pushMatrix();
                GlStateManager.translate((double)a13, (double)a14, (double)0.0);
                if (a12 != 1.0 || a15 != 0) {
                    GlStateManager.translate((float)((float)a6.width / 2.0f), (float)((float)a6.height / 2.0f), (float)0.0f);
                    if (a12 != 1.0) {
                        GlStateManager.scale((double)a12, (double)a12, (double)1.0);
                    }
                    if (a15 != 0) {
                        GlStateManager.rotate((float)a15, (float)0.0f, (float)0.0f, (float)1.0f);
                    }
                    GlStateManager.translate((float)(-((float)a6.width) / 2.0f), (float)(-((float)a6.height) / 2.0f), (float)0.0f);
                }
            }
            for (jj a16 : a9) {
                a16.draw(a3, a4, a6.ua);
            }
            if (a11) {
                GlStateManager.popMatrix();
            }
        }
        GlStateManager.enableDepth();
        GlStateManager.disableBlend();
        GlStateManager.disableLighting();
        ui a17 = wi.b.ALLATORIxDEMO();
        if (a17 != null && a6.ma == od.q) {
            a17.drawScreen(a3, a4, a5);
        }
        if ((a17 == null && a6.ma == od.q || a6.ma == od.y) && a8) {
            super.drawScreen(a3, a4, a5);
        }
        if (a7 != null && a17 == null && a8) {
            super.drawHoverTip(a5, a3, a4);
            a6.drawHoverITextComponent(a3, a4);
        }
        if (a6.ma == od.y && a8) {
            super.drawHoverTip(a5, a3, a4);
            a6.drawHoverITextComponent(a3, a4);
        }
        GlStateManager.disableLighting();
        a6.runGuiAction(nj.l);
    }

    public void drawHoverITextComponent(int a2, int a3) {
        ke a4;
        ITextComponent a5;
        ui a6;
        if (a6.d != null && a6.d instanceof ke && (a5 = (a4 = (ke)a6.d).getTextComponent(a2, a3)) != null && a5.getStyle().getHoverEvent() != null) {
            a6.handleComponentHover(a5, a2, a3);
        }
    }

    @Override
    public void mouseClicked(int a3, int a4, int a5) throws IOException {
        ui a6;
        if (a6.z) {
            return;
        }
        switch (a5) {
            case 0: {
                a6.ba = "MOUSE_LEFT";
                break;
            }
            case 1: {
                a6.ba = "MOUSE_RIGHT";
                break;
            }
            case 2: {
                a6.ba = "MOUSE_MIDDLE";
            }
        }
        boolean a7 = a6.runGuiAction(nj.z);
        switch (a5) {
            case 0: {
                a7 = a7 || a6.runGuiAction(nj.s);
                break;
            }
            case 1: {
                a7 = a7 || a6.runGuiAction(nj.g);
                break;
            }
            case 2: {
                a7 = a7 || a6.runGuiAction(nj.t);
            }
        }
        a6.ba = "";
        if (a7) {
            return;
        }
        super.mouseClicked(a3, a4, a5);
        ArrayList<jj> a8 = new ArrayList<jj>(a6.r.values());
        a8.sort(Comparator.comparingDouble(a2 -> a2.d.c()));
        if (a6.isThrough()) {
            for (jj jj2 : a6.p) {
                if (!jj2.xa.c()) continue;
                jj2.ALLATORIxDEMO.put(a5, new Point((int)((double)a3 - jj2.s.ALLATORIxDEMO()), (int)((double)a4 - jj2.g.ALLATORIxDEMO())));
                jj2.runClick(a3, a4, a5);
            }
            for (jj jj3 : a8) {
                if (a6.p.contains(jj3)) continue;
                jj3.runUnClick(a3, a4, a5);
            }
        } else {
            jj a10 = null;
            jj jj4 = a6.getHoveredComponent();
            if (jj4 != null && jj4.xa.c()) {
                a10 = jj4;
                jj4.ALLATORIxDEMO.put(a5, new Point((int)((double)a3 - jj4.s.ALLATORIxDEMO()), (int)((double)a4 - jj4.g.ALLATORIxDEMO())));
                jj4.runClick(a3, a4, a5);
            }
            for (jj a11 : a8) {
                if (a11 == a10) continue;
                a11.runUnClick(a3, a4, a5);
            }
        }
        if (a6.j) {
            for (ui ui2 : de.c.values()) {
                ui2.mouseClicked(a3, a4, a5);
            }
        }
    }

    @Override
    public void mouseReleased(int a2, int a3, int a4) {
        ui a5;
        if (a5.z) {
            return;
        }
        boolean a6 = a5.runGuiAction(nj.r);
        switch (a4) {
            case 0: {
                a6 = a6 || a5.runGuiAction(nj.x);
                break;
            }
            case 1: {
                a6 = a6 || a5.runGuiAction(nj.v);
                break;
            }
            case 2: {
                boolean bl2 = a6 = a6 || a5.runGuiAction(nj.m);
            }
        }
        if (a6) {
            return;
        }
        super.mouseReleased(a2, a3, a4);
        ArrayList<jj> a7 = new ArrayList<jj>(a5.r.values());
        for (jj jj2 : a7) {
            if (jj2.ALLATORIxDEMO.remove(a4) == null) continue;
            jj2.runRelease(a2, a3, a4);
        }
        if (a5.j) {
            for (ui ui2 : de.c.values()) {
                ui2.mouseReleased(a2, a3, a4);
            }
        }
    }

    public void handleKeyboardInput() throws IOException {
        ui a3;
        ui a4 = wi.b.ALLATORIxDEMO();
        if (a4 != null && a3.ma == od.q) {
            a4.handleKeyboardInput();
            return;
        }
        boolean a5 = a3.k.c();
        boolean a6 = a3.ALLATORIxDEMO.c();
        if (!a5 || !a6) {
            return;
        }
        new ArrayList<jj>(a3.r.values()).stream().filter(a2 -> a2 instanceof zg).map(a2 -> (zg)a2).filter(a2 -> a2.k != null).filter(a2 -> !a2.k.w).filter(a2 -> a2.isVisible() && a2.isEnable()).forEach(a2 -> {
            try {
                a2.k.handleMouseInput();
            }
            catch (IOException a3) {
                throw new RuntimeException(a3);
            }
        });
        char a7 = Keyboard.getEventCharacter();
        if (Keyboard.getEventKey() == 0 && a7 >= ' ' || Keyboard.getEventKeyState()) {
            a3.keyTyped(a7, Keyboard.getEventKey());
        } else {
            if (a3.z) {
                return;
            }
            int a8 = Keyboard.getEventKey();
            String a9 = Keyboard.getKeyName((int)a8);
            if (a3.ma == od.q || a3.ma == od.y) {
                hja.c.ALLATORIxDEMO(a9);
            }
            a3.ba = a9;
            boolean a10 = a3.runGuiAction(nj.p);
            a3.ba = "";
            if (!a10 && a3.j) {
                for (ui a11 : de.c.values()) {
                    a11.ba = a9;
                    a11.runGuiAction(nj.p);
                    a11.ba = "";
                }
            }
        }
        a3.mc.dispatchKeypresses();
    }

    @Override
    public void mouseClickMove(int a2, int a3, int a4, long a5) {
        ui a6;
        if (a6.z) {
            return;
        }
        super.mouseClickMove(a2, a3, a4, a5);
    }

    @Override
    public void keyTyped(char a2, int a3) throws IOException {
        ui a4;
        a4.keyTyped_(a2, a3);
    }

    public boolean keyTyped_(char a2, int a3) throws IOException {
        ui a4;
        if (a4.z) {
            return false;
        }
        boolean a5 = false;
        boolean a6 = false;
        if (a4.ma == od.q || a4.ma == od.y) {
            a5 = hja.c.ALLATORIxDEMO(Keyboard.getKeyName((int)a3));
        }
        a4.ba = Keyboard.getKeyName((int)a3);
        a6 = a4.runGuiAction(nj.d);
        a4.ba = "";
        if (a6) {
            return true;
        }
        if (a3 == 1 && a4.t.c()) {
            if (a4.ma == od.q) {
                a4.mc.player.closeScreen();
            } else if (a4.ma == od.y) {
                wi.b.f();
            }
        }
        ArrayList<jj> a7 = new ArrayList<jj>(a4.r.values());
        super.keyTyped(a2, a3);
        for (jj jj2 : a7) {
            if (!jj2.ta.c() || !jj2.xa.c()) continue;
            a5 = a5 || jj2.keyTyped(a2, a3);
        }
        if (a4.j) {
            for (ui ui2 : de.c.values()) {
                ui2.keyTyped(a2, a3);
            }
        }
        return a5;
    }

    public void handleMouseInput() throws IOException {
        ui a3;
        ui a4 = wi.b.ALLATORIxDEMO();
        if (a4 != null && a3.ma == od.q) {
            a4.handleMouseInput();
            return;
        }
        boolean a5 = a3.k.c();
        boolean a6 = a3.ALLATORIxDEMO.c();
        if (!a5 || !a6) {
            return;
        }
        new ArrayList<jj>(a3.r.values()).stream().filter(a2 -> a2 instanceof zg).map(a2 -> (zg)a2).filter(a2 -> a2.k != null).filter(a2 -> !a2.k.w).filter(a2 -> a2.isVisible() && a2.isEnable()).forEach(a2 -> {
            try {
                a2.k.handleMouseInput();
            }
            catch (IOException a3) {
                throw new RuntimeException(a3);
            }
        });
        a3.handleMouseInput_();
    }

    public boolean handleMouseInput_() throws IOException {
        ui a2;
        if (a2.z) {
            return false;
        }
        int a3 = Mouse.getEventX() * a2.width / a2.mc.displayWidth;
        int a4 = a2.height - Mouse.getEventY() * a2.height / a2.mc.displayHeight - 1;
        int a5 = Mouse.getEventButton();
        if (Mouse.getEventButtonState()) {
            if (a2.mc.gameSettings.touchscreen && a2.touchValue++ > 0) {
                return false;
            }
            a2.eventButton = a5;
            a2.lastMouseEvent = Minecraft.getSystemTime();
            a2.mouseClicked(a3, a4, a2.eventButton);
        } else if (a5 != -1) {
            if (a2.mc.gameSettings.touchscreen && --a2.touchValue > 0) {
                return false;
            }
            a2.eventButton = -1;
            a2.mouseReleased(a3, a4, a5);
        } else {
            a2.runGuiAction(nj.c);
            a2.runGuiAction(nj.q);
            if (a2.eventButton != -1 && a2.lastMouseEvent > 0L) {
                long a6 = Minecraft.getSystemTime() - a2.lastMouseEvent;
                a2.mouseClickMove(a3, a4, a2.eventButton, a6);
            }
        }
        if (Mouse.getEventDWheel() != 0) {
            if ((a2.ma == od.q || a2.ma == od.y) && hja.c.ALLATORIxDEMO()) {
                return true;
            }
            return a2.runWheel();
        }
        return false;
    }

    public boolean runWheel() {
        ui a2;
        if (a2.z) {
            return false;
        }
        boolean a3 = a2.runGuiAction(nj.u);
        for (jj jj2 : a2.p) {
            a3 = a3 || jj2.runAction("wheel").ALLATORIxDEMO();
        }
        if (a2.j) {
            for (ui ui2 : de.c.values()) {
                ui2.runWheel();
            }
        }
        return a3;
    }

    @Override
    public Map<String, jj> getComponents() {
        ui a2;
        return a2.r;
    }

    public jj findComponent(String a2) {
        ui a3;
        return a3.r.get(a2);
    }

    public void removeComponent(p a2) {
        ui a4;
        if (a2 instanceof jj && a4.r.containsValue(a2)) {
            jj a5 = (jj)a2;
            a5.onRemove();
            a4.r.entrySet().removeIf(a3 -> a3.getValue() == a5);
        }
    }

    public void removeComponent(String a2, boolean a4) {
        ui a5;
        a2 = a2.toLowerCase(Locale.ROOT);
        if (a4) {
            String a6 = a2;
            a5.r.entrySet().removeIf(a3 -> {
                boolean a4 = ((String)a3.getKey()).toLowerCase(Locale.ROOT).startsWith(a6);
                if (a4) {
                    ((jj)a3.getValue()).onRemove();
                }
                return a4;
            });
        } else if (a5.r.containsKey(a2)) {
            jj a7 = a5.findComponent(a2);
            a7.onRemove();
            a5.r.remove(a2);
        }
    }

    @Override
    public jj getHoveredComponent() {
        ui a2;
        return a2.d;
    }

    @Override
    public List<jj> getHoveredComponents() {
        ui a2;
        return a2.p;
    }

    public boolean runGuiAction(nj a2) {
        ui a3;
        return a3.runGuiAction(a2.ALLATORIxDEMO());
    }

    public boolean runGuiAction(String a2) {
        ui a3;
        nh a4 = a3.a.get(a2);
        if (a4 != null) {
            return a4.c();
        }
        return false;
    }

    public nh parseExpression(Object a2) {
        ui a3;
        String a4 = String.valueOf(a2);
        if (a4.startsWith("co|") || a4.startsWith("mo|")) {
            a4 = a4.substring(3);
        }
        a4 = a3.ALLATORIxDEMO(a4);
        return hw.ALLATORIxDEMO(a3.oa, a4);
    }

    public nh parseExpression(String a2) {
        ui a3;
        if (!a2.startsWith("co|") && !a2.startsWith("mo|")) {
            if (a3.ALLATORIxDEMO(a2)) {
                a2 = a3.ALLATORIxDEMO(a2);
                return hw.ALLATORIxDEMO(a3.oa, a2);
            }
            if (!a2.startsWith("'")) {
                a2 = "'" + a2 + "'";
            }
        } else {
            a2 = a2.substring(3);
        }
        a2 = a3.ALLATORIxDEMO(a2);
        return hw.ALLATORIxDEMO(a3.oa, a2);
    }

    private /* synthetic */ boolean ALLATORIxDEMO(String a2) {
        ui a3;
        for (String a4 : a3.oa.ALLATORIxDEMO().ALLATORIxDEMO().keySet()) {
            if (!a2.contains(a4 + ".")) continue;
            return true;
        }
        return a2.contains("return ");
    }

    private /* synthetic */ String ALLATORIxDEMO(String a2) {
        Matcher a3 = pa.matcher(a2);
        while (a3.find()) {
            String a4 = a3.group();
            Matcher a5 = da.matcher(a4);
            boolean a6 = false;
            while (a5.find() && a5.group().contains("_")) {
                a6 = true;
                String a7 = a5.group();
                a4 = a4.replace(a7, "',\u65b9\u6cd5.\u53d6\u53d8\u91cf('" + a5.group(1) + "'),'");
            }
            if (!a6) continue;
            a4 = "\u65b9\u6cd5.\u5408\u5e76\u6587\u672c(" + a4 + ")";
            a2 = a2.replace(a3.group(), a4);
        }
        return a2;
    }

    @Override
    public nh getCurrentItemScale() {
        ui a2;
        return a2.x;
    }

    @Override
    public od getGuiType() {
        ui a2;
        return a2.ma;
    }

    public YamlConfiguration getYaml() {
        ui a2;
        return a2.v;
    }

    public qv getMoLangRuntime() {
        ui a2;
        return a2.oa;
    }

    public void onScreenResize() {
        ui a2;
        a2.runGuiAction(nj.b);
    }

    public void onGuiClosed1() {
        ui a3;
        if (a3.c) {
            a3.c = false;
            return;
        }
        if (a3.ma == od.q && wi.b.ALLATORIxDEMO() != null) {
            wi.b.f();
        }
        new ArrayList<jj>(a3.r.values()).stream().filter(a2 -> a2 instanceof zg).map(a2 -> (zg)a2).forEach(zg::onClose);
        a3.runGuiAction(nj.o);
        a3.w = true;
        for (jj a4 : new ArrayList<jj>(a3.r.values())) {
            a4.onClose();
        }
    }

    @Override
    public void onGuiClosed() {
        ui a2;
        an.ALLATORIxDEMO();
        a2.onGuiClosed1();
        super.onGuiClosed();
    }

    public void ignoreNextClose() {
        a.c = true;
    }

    @Override
    public boolean isThrough() {
        ui a2;
        return a2.xa;
    }

    @Override
    public String getMatch() {
        ui a2;
        return a2.l;
    }

    public boolean isLoaded() {
        ui a2;
        return a2.m;
    }

    public void initGui_() {
        ui a2;
        a2.mc = Minecraft.getMinecraft();
        Keyboard.enableRepeatEvents((boolean)true);
        a2.fontRenderer = a2.mc.fontRenderer;
        a2.itemRender = a2.mc.getRenderItem();
    }

    @Override
    public void handleMouseClick(Slot a2, int a3, int a4, ClickType a5) {
        ui a6;
        if (a2 == null && a3 == -999 && a6.runGuiAction(nj.y)) {
            return;
        }
        super.handleMouseClick(a2, a3, a4, a5);
    }

    public List<ui> getGuiManagerComponents() {
        ui a3;
        List a4 = new ArrayList<jj>(a3.r.values()).stream().filter(a2 -> a2 instanceof zg).map(a2 -> (zg)a2).filter(a2 -> a2.k != null).map(a2 -> a2.k).collect(Collectors.toList());
        return a4.stream().map(ui::getGuiManagerComponents).flatMap(Collection::stream).collect(Collectors.toList());
    }

    static {
        qa.open();
    }
}

