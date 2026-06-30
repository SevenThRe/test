/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.gui.GuiControls
 *  net.minecraft.client.gui.GuiScreen
 *  org.apache.logging.log4j.Level
 */
package journeymap.client.ui.dialog;

import java.awt.Desktop;
import java.io.IOException;
import java.net.URI;
import java.net.URLEncoder;
import journeymap.client.ui.UIManager;
import journeymap.client.ui.fullscreen.Fullscreen;
import journeymap.common.Journeymap;
import journeymap.common.log.LogFormatter;
import journeymap.common.version.VersionCheck;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiControls;
import net.minecraft.client.gui.GuiScreen;
import org.apache.logging.log4j.Level;

public class FullscreenActions {
    public static void open() {
        UIManager.INSTANCE.openFullscreenMap();
    }

    public static void showCaveLayers() {
        UIManager.INSTANCE.openFullscreenMap().showCaveLayers();
    }

    public static void launchLocalhost() {
        String url = "http://localhost:" + Journeymap.getClient().getWebMapProperties().port.get();
        try {
            Desktop.getDesktop().browse(URI.create(url));
        }
        catch (IOException e) {
            Journeymap.getLogger().log(Level.ERROR, "Could not launch browser with URL: " + url + ": " + LogFormatter.toString(e));
        }
    }

    public static void launchPatreon() {
        String url = "http://patreon.com/techbrew";
        try {
            Desktop.getDesktop().browse(URI.create(url));
        }
        catch (IOException e) {
            Journeymap.getLogger().log(Level.ERROR, "Could not launch browser with URL: " + url + ": " + LogFormatter.toString(e));
        }
    }

    public static void launchWebsite(String path) {
        String url = "http://journeymap.info/" + path;
        try {
            Desktop.getDesktop().browse(URI.create(url));
        }
        catch (Throwable e) {
            Journeymap.getLogger().error("Could not launch browser with URL: " + url, (Object)LogFormatter.toString(e));
        }
    }

    public static void openKeybindings() {
        UIManager.INSTANCE.closeAll();
        Fullscreen fullscreen = UIManager.INSTANCE.openFullscreenMap();
        Minecraft mc = Minecraft.func_71410_x();
        mc.func_147108_a((GuiScreen)new GuiControls((GuiScreen)fullscreen, mc.field_71474_y));
    }

    public static void tweet(String message) {
        String path = null;
        try {
            path = "http://twitter.com/home/?status=@JourneyMapMod+" + URLEncoder.encode(message, "UTF-8");
            Desktop.getDesktop().browse(URI.create(path));
        }
        catch (Throwable e) {
            e.printStackTrace();
        }
    }

    public static void discord() {
        String path = null;
        try {
            path = "https://discord.gg/eP8gE69";
            Desktop.getDesktop().browse(URI.create(path));
        }
        catch (Throwable e) {
            e.printStackTrace();
        }
    }

    public static void launchDownloadWebsite() {
        String url = VersionCheck.getDownloadUrl();
        try {
            Desktop.getDesktop().browse(URI.create(url));
        }
        catch (Throwable e) {
            Journeymap.getLogger().error("Could not launch browser with URL: " + url, (Object)LogFormatter.toString(e));
        }
    }
}

