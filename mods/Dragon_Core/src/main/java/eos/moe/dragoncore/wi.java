/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.entity.EntityPlayerSP
 *  net.minecraft.client.gui.GuiScreen
 *  net.minecraft.inventory.Container
 *  net.minecraft.inventory.Slot
 *  net.minecraft.item.ItemStack
 *  net.minecraftforge.fml.common.Mod$EventBusSubscriber
 */
package eos.moe.dragoncore;

import eos.moe.dragoncore.af;
import eos.moe.dragoncore.de;
import eos.moe.dragoncore.gi;
import eos.moe.dragoncore.hja;
import eos.moe.dragoncore.nw;
import eos.moe.dragoncore.od;
import eos.moe.dragoncore.qd;
import eos.moe.dragoncore.tm;
import eos.moe.dragoncore.ui;
import eos.moe.dragoncore.v;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.Mod;
import org.yaml.snakeyamla.configuration.file.YamlConfiguration;

/*
 * Duplicate member names - consider using --renamedupmembers true
 */
@Mod.EventBusSubscriber(modid="dragoncore")
public class wi {
    public static final wi b = new wi();
    private final ConcurrentHashMap<String, ItemStack> o = new ConcurrentHashMap();
    private final ConcurrentHashMap<String, YamlConfiguration> y = new ConcurrentHashMap();
    private Minecraft k = Minecraft.func_71410_x();
    private ui ALLATORIxDEMO;

    public wi() {
        wi a2;
    }

    public void x() {
        wi a2;
        a2.o.clear();
        a2.y.clear();
    }

    public ui ALLATORIxDEMO() {
        wi a2;
        return a2.ALLATORIxDEMO;
    }

    public ui c(String a2) {
        wi a3;
        a3.f();
        if (!a3.ALLATORIxDEMO(a2)) {
            return null;
        }
        GuiScreen a4 = Minecraft.func_71410_x().field_71462_r;
        if (a4 instanceof ui) {
            ui a5 = (ui)a4;
            ui a6 = new ui(a5.getMoLangRuntime().ALLATORIxDEMO, a2, a3.ALLATORIxDEMO(a2), a3.y, a3.k.field_71439_g.field_71069_bz, od.y);
            a6.initGui_();
            a6.open();
            a3.ALLATORIxDEMO = a6;
            return a5;
        }
        return null;
    }

    public void f() {
        wi a2;
        if (a2.ALLATORIxDEMO != null) {
            a2.ALLATORIxDEMO.func_146281_b();
            a2.ALLATORIxDEMO = null;
            GuiScreen a3 = Minecraft.func_71410_x().field_71462_r;
            if (a3 instanceof ui) {
                ui a4 = (ui)a3;
                a4.runGuiAction("sub_close");
            }
        }
    }

    public boolean c(String a2) {
        return false;
    }

    public ui ALLATORIxDEMO(String a2) {
        wi a3;
        if (!a3.ALLATORIxDEMO(a2)) {
            return null;
        }
        ui a4 = new ui(a2, a3.ALLATORIxDEMO(a2), a3.y, a3.k.field_71439_g.field_71069_bz, od.q);
        a4.open();
        Minecraft.func_71410_x().func_147108_a((GuiScreen)a4);
        return a4;
    }

    public void c(String a2) {
    }

    public void c() {
    }

    public void ALLATORIxDEMO(String a2) {
    }

    public void ALLATORIxDEMO() {
    }

    public List<ui> ALLATORIxDEMO() {
        wi a2;
        ArrayList<ui> a3 = new ArrayList<ui>();
        GuiScreen a4 = Minecraft.func_71410_x().field_71462_r;
        if (a2.ALLATORIxDEMO != null) {
            a3.add(a2.ALLATORIxDEMO);
            a3.addAll(a2.ALLATORIxDEMO.getGuiManagerComponents());
        }
        if (a4 instanceof ui) {
            a3.add((ui)a4);
            a3.addAll(((ui)a4).getGuiManagerComponents());
        }
        if (hja.c.k != null) {
            a3.add(hja.c.k);
            a3.addAll(hja.c.k.getGuiManagerComponents());
        }
        for (ui a5 : de.c.values()) {
            a3.add(a5);
            a3.addAll(a5.getGuiManagerComponents());
        }
        return a3;
    }

    public void ALLATORIxDEMO(String a2, v ... a3) {
        wi a4;
        for (ui a5 : a4.ALLATORIxDEMO()) {
            gi.c(a5, a2, a3);
        }
    }

    public void ALLATORIxDEMO(String a2, boolean a4) {
        wi a5;
        if (a4) {
            a5.o.entrySet().removeIf(a3 -> ((String)a3.getKey()).startsWith(a2));
        } else {
            a5.o.remove(a2);
        }
    }

    public void ALLATORIxDEMO(String a2, ItemStack a3) {
        wi a4;
        a4.o.put(a2, a3);
    }

    public qd<ItemStack, String> ALLATORIxDEMO(String a2, boolean a3) {
        wi a4;
        if (a2.startsWith("player_")) {
            return qd.ALLATORIxDEMO(a4.ALLATORIxDEMO(a2.substring(7)), null);
        }
        if (a2.startsWith("container_")) {
            Slot a5 = a4.ALLATORIxDEMO(a2.substring(10), a3 ? a4.k.field_71439_g.field_71069_bz : a4.k.field_71439_g.field_71070_bA);
            if (a5 == null) {
                return qd.ALLATORIxDEMO(ItemStack.field_190927_a, null);
            }
            if (a4.k.field_71462_r instanceof af && !a3) {
                return ((af)a4.k.field_71462_r).getSlotItemStack(a5);
            }
            return qd.ALLATORIxDEMO(a5.func_75211_c(), null);
        }
        if (a2.startsWith("{") && a2.endsWith("}")) {
            ItemStack a6 = a4.o.get(a2);
            if (a6 == null) {
                a6 = tm.ALLATORIxDEMO(a2).ALLATORIxDEMO();
                a4.o.put(a2, a6);
            }
            return qd.ALLATORIxDEMO(a6, null);
        }
        if (!a4.o.containsKey(a2)) {
            nw.ALLATORIxDEMO("DragonCore_RetrieveSlot", new String[]{a2});
            a4.o.put(a2, ItemStack.field_190927_a);
        }
        return qd.ALLATORIxDEMO(a4.o.get(a2), null);
    }

    public Slot ALLATORIxDEMO(String a2, Container a3) {
        wi a4;
        EntityPlayerSP a5 = a4.k.field_71439_g;
        if (a2.startsWith("container_")) {
            a2 = a2.substring(10);
        }
        switch (a2.toLowerCase()) {
            case "craft": {
                a2 = "0";
                break;
            }
            case "craft1": {
                a2 = "1";
                break;
            }
            case "craft2": {
                a2 = "2";
                break;
            }
            case "craft3": {
                a2 = "3";
                break;
            }
            case "craft4": {
                a2 = "4";
                break;
            }
            case "head": {
                a2 = "5";
                break;
            }
            case "chest": {
                a2 = "6";
                break;
            }
            case "leg": {
                a2 = "7";
                break;
            }
            case "feet": {
                a2 = "8";
                break;
            }
            case "mainhand": {
                a2 = String.valueOf(a5.field_71071_by.field_70461_c + 36);
                break;
            }
            case "offhand": {
                a2 = "45";
            }
        }
        int a6 = -1;
        String[] a7 = a2.split(",", 2);
        if (a7.length == 2) {
            try {
                a6 = ((int)Double.parseDouble(a7[1]) - 1) * 9 + (int)Double.parseDouble(a7[0]) - 1;
            }
            catch (NumberFormatException numberFormatException) {}
        } else {
            try {
                a6 = (int)Double.parseDouble(a2);
            }
            catch (NumberFormatException numberFormatException) {
                // empty catch block
            }
        }
        if (a6 < 0) {
            return null;
        }
        if (a3 == null) {
            return null;
        }
        if (a3.field_75151_b.size() < a6 + 1) {
            return null;
        }
        return a3.func_75139_a(a6);
    }

    public ItemStack ALLATORIxDEMO(String a2) {
        wi a3;
        EntityPlayerSP a4 = a3.k.field_71439_g;
        Container a5 = a4.field_71069_bz;
        switch (a2.toLowerCase()) {
            case "craft": {
                return (ItemStack)a5.func_75138_a().get(0);
            }
            case "craft1": {
                return (ItemStack)a5.func_75138_a().get(1);
            }
            case "craft2": {
                return (ItemStack)a5.func_75138_a().get(2);
            }
            case "craft3": {
                return (ItemStack)a5.func_75138_a().get(3);
            }
            case "craft4": {
                return (ItemStack)a5.func_75138_a().get(4);
            }
            case "head": {
                return (ItemStack)a5.func_75138_a().get(5);
            }
            case "chest": {
                return (ItemStack)a5.func_75138_a().get(6);
            }
            case "leg": {
                return (ItemStack)a5.func_75138_a().get(7);
            }
            case "feet": {
                return (ItemStack)a5.func_75138_a().get(8);
            }
            case "mainhand": {
                return a4.func_184614_ca();
            }
            case "offhand": {
                return a4.func_184592_cb();
            }
            case "current": {
                return a4.field_71071_by.func_70448_g();
            }
            case "shield": {
                return (ItemStack)a5.func_75138_a().get(45);
            }
        }
        try {
            return (ItemStack)a5.func_75138_a().get((int)Double.parseDouble(a2));
        }
        catch (Exception a6) {
            return ItemStack.field_190927_a;
        }
    }

    public boolean ALLATORIxDEMO(String a2) {
        wi a3;
        return a3.y.containsKey(a2.toLowerCase(Locale.ROOT));
    }

    public YamlConfiguration ALLATORIxDEMO(String a2) {
        wi a3;
        return a3.y.get(a2.toLowerCase(Locale.ROOT));
    }

    public void ALLATORIxDEMO(String a2, YamlConfiguration a3) {
        wi a4;
        a4.y.put(a2.toLowerCase(Locale.ROOT), a3);
    }

    public ConcurrentHashMap<String, ItemStack> ALLATORIxDEMO() {
        wi a2;
        return a2.o;
    }

    public Map<String, YamlConfiguration> ALLATORIxDEMO() {
        wi a2;
        return a2.y;
    }
}

