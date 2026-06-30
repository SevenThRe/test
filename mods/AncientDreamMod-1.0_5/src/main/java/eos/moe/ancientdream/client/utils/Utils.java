/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.collect.ImmutableList
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.gui.GuiScreen
 *  net.minecraft.init.SoundEvents
 *  net.minecraft.util.text.ITextComponent
 *  net.minecraft.util.text.TextComponentString
 */
package eos.moe.ancientdream.client.utils;

import com.google.common.collect.ImmutableList;
import eos.moe.ancientdream.client.utils.RenderUtils;
import java.util.ArrayList;
import java.util.List;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.init.SoundEvents;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentString;

public class Utils {
    public static boolean onArea(float x, float y, float w, float h, float mx, float my) {
        return mx >= x && mx <= x + w && my >= y && my <= y + h;
    }

    public static boolean onArea(double x, double y, double w, double h, double mx, double my) {
        return mx >= x && mx <= x + w && my >= y && my <= y + h;
    }

    public static void onAreaThenRun(float x, float y, float w, float h, float mx, float my, Runnable runnable) {
        if (Utils.onArea(x, y, w, h, mx, my)) {
            runnable.run();
        }
    }

    public static List<String> splitString(String text, int length) {
        ArrayList<String> list = new ArrayList<String>();
        if (RenderUtils.getTextWidth(null, text) < length) {
            return ImmutableList.of((Object)text);
        }
        String tx = "";
        for (String s : text.split("")) {
            if (RenderUtils.getTextWidth(null, tx + s) >= length) {
                list.add(tx);
                tx = s;
                continue;
            }
            tx = tx + s;
        }
        list.add(tx);
        return list;
    }

    public static void playSound() {
        Minecraft.getMinecraft().player.playSound(SoundEvents.UI_BUTTON_CLICK, 1.0f, 1.0f);
    }

    public static <T> T getScreen(Class<T> class_) {
        GuiScreen currentScreen = Minecraft.getMinecraft().currentScreen;
        if (currentScreen != null && class_ == currentScreen.getClass()) {
            return (T)currentScreen;
        }
        return null;
    }

    public static void runSync(Runnable runnable) {
        Minecraft.getMinecraft().addScheduledTask(runnable);
    }

    public static void message(String message) {
        Minecraft.getMinecraft().player.sendMessage((ITextComponent)new TextComponentString(message));
    }

    public static void chat(String message) {
        Minecraft.getMinecraft().player.sendChatMessage(message);
    }

    public static void openGui(GuiScreen guiScreen) {
        Minecraft.getMinecraft().displayGuiScreen(guiScreen);
    }

    public static void openGuiSync(GuiScreen guiScreen) {
        Minecraft.getMinecraft().addScheduledTask(() -> Utils.openGui(guiScreen));
    }

    public static String secondToTime(long second) {
        long days = second / 86400L;
        long hours = (second %= 86400L) / 3600L;
        long minutes = (second %= 3600L) / 60L;
        second %= 60L;
        if (0L < days) {
            return days + "\u5929" + hours + "\u65f6" + minutes + "\u5206" + second + "\u79d2";
        }
        if (0L < hours) {
            return hours + "\u65f6" + minutes + "\u5206" + second + "\u79d2";
        }
        return minutes + "\u5206" + second + "\u79d2";
    }
}

