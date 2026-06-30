/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.Minecraft
 *  net.minecraft.util.StringUtils
 *  net.minecraft.util.text.ITextComponent
 *  net.minecraft.util.text.TextComponentString
 *  net.minecraft.util.text.TextComponentTranslation
 *  net.minecraft.util.text.event.ClickEvent
 *  net.minecraft.util.text.event.ClickEvent$Action
 *  net.minecraft.util.text.event.HoverEvent
 *  net.minecraft.util.text.event.HoverEvent$Action
 *  org.apache.logging.log4j.Level
 */
package journeymap.client.log;

import java.io.File;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import journeymap.client.Constants;
import journeymap.client.forge.event.KeyEventHandler;
import journeymap.client.service.webmap.Webmap;
import journeymap.common.Journeymap;
import journeymap.common.log.LogFormatter;
import journeymap.common.version.VersionCheck;
import net.minecraft.client.Minecraft;
import net.minecraft.util.StringUtils;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.util.text.event.ClickEvent;
import net.minecraft.util.text.event.HoverEvent;
import org.apache.logging.log4j.Level;

public class ChatLog {
    static final List<TextComponentTranslation> announcements = Collections.synchronizedList(new LinkedList());
    public static boolean enableAnnounceMod = false;
    private static boolean initialized = false;

    public static void queueAnnouncement(ITextComponent chat) {
        TextComponentTranslation wrap = new TextComponentTranslation("jm.common.chat_announcement", new Object[]{chat});
        announcements.add(wrap);
    }

    public static void announceURL(String message, String url) {
        TextComponentString chat = new TextComponentString(message);
        chat.getStyle().setClickEvent(new ClickEvent(ClickEvent.Action.OPEN_URL, url));
        chat.getStyle().setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, (ITextComponent)new TextComponentString(url)));
        ChatLog.queueAnnouncement((ITextComponent)chat);
    }

    public static void announceFile(String message, File file) {
        TextComponentString chat = new TextComponentString(message);
        try {
            chat.getStyle().setClickEvent(new ClickEvent(ClickEvent.Action.OPEN_FILE, file.getCanonicalPath()));
            chat.getStyle().setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, (ITextComponent)new TextComponentString(file.getCanonicalPath())));
        }
        catch (Exception e) {
            Journeymap.getLogger().warn("Couldn't build ClickEvent for file: " + LogFormatter.toString(e));
        }
        ChatLog.queueAnnouncement((ITextComponent)chat);
    }

    public static void announceI18N(String key, Object ... parms) {
        String text = Constants.getString(key, parms);
        TextComponentString chat = new TextComponentString(text);
        ChatLog.queueAnnouncement((ITextComponent)chat);
    }

    public static void announceError(String text) {
        ErrorChat chat = new ErrorChat(text);
        ChatLog.queueAnnouncement((ITextComponent)chat);
    }

    public static void showChatAnnouncements(Minecraft mc) {
        if (!initialized) {
            enableAnnounceMod = Journeymap.getClient().getCoreProperties().announceMod.get();
            ChatLog.announceMod(enableAnnounceMod);
            VersionCheck.getVersionIsCurrent();
            initialized = true;
        }
        while (!announcements.isEmpty()) {
            Level logLevel;
            TextComponentTranslation message = announcements.remove(0);
            if (message == null) continue;
            try {
                mc.ingameGUI.getChatGUI().printChatMessage((ITextComponent)message);
            }
            catch (Exception e) {
                try {
                    Journeymap.getLogger().error("Could not display announcement in chat: " + LogFormatter.toString(e));
                }
                catch (Throwable throwable) {
                    Level logLevel2 = message.getFormatArgs()[0] instanceof ErrorChat ? Level.ERROR : Level.INFO;
                    Journeymap.getLogger().log(logLevel2, StringUtils.stripControlCodes((String)message.getUnformattedComponentText()));
                    throw throwable;
                }
                logLevel = message.getFormatArgs()[0] instanceof ErrorChat ? Level.ERROR : Level.INFO;
                Journeymap.getLogger().log(logLevel, StringUtils.stripControlCodes((String)message.getUnformattedComponentText()));
                continue;
            }
            logLevel = message.getFormatArgs()[0] instanceof ErrorChat ? Level.ERROR : Level.INFO;
            Journeymap.getLogger().log(logLevel, StringUtils.stripControlCodes((String)message.getUnformattedComponentText()));
        }
    }

    public static void announceMod(boolean forced) {
        if (enableAnnounceMod || forced) {
            String keyName = KeyEventHandler.INSTANCE.kbFullscreenToggle.getDisplayName();
            if (Journeymap.getClient().getWebMapProperties().enabled.get().booleanValue()) {
                try {
                    Webmap webServer = Journeymap.getClient().getJmServer();
                    String port = webServer.getPort() == 80 ? "" : ":" + webServer.getPort();
                    String message = Constants.getString("jm.common.webserver_and_mapgui_ready", keyName, port);
                    ChatLog.announceURL(message, "http://localhost" + port);
                }
                catch (Throwable t) {
                    Journeymap.getLogger().error("Couldn't check webserver: " + LogFormatter.toString(t));
                }
            } else {
                ChatLog.announceI18N("jm.common.mapgui_only_ready", keyName);
            }
            if (!Journeymap.getClient().getCoreProperties().mappingEnabled.get().booleanValue()) {
                ChatLog.announceI18N("jm.common.enable_mapping_false_text", new Object[0]);
            }
            enableAnnounceMod = false;
        }
    }

    private static class ErrorChat
    extends TextComponentString {
        public ErrorChat(String text) {
            super(text);
        }
    }
}

