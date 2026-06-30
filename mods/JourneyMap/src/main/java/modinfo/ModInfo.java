/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.resources.Language
 *  net.minecraft.client.resources.Locale
 *  net.minecraft.server.integrated.IntegratedServer
 *  org.apache.logging.log4j.Level
 *  org.apache.logging.log4j.LogManager
 *  org.apache.logging.log4j.Logger
 *  org.lwjgl.opengl.Display
 *  org.lwjgl.opengl.DisplayMode
 */
package modinfo;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import modinfo.Config;
import modinfo.mp.v1.Client;
import modinfo.mp.v1.Message;
import modinfo.mp.v1.Payload;
import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.Language;
import net.minecraft.client.resources.Locale;
import net.minecraft.server.integrated.IntegratedServer;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;

public class ModInfo {
    public static final String VERSION = "0.2";
    public static final Logger LOGGER = LogManager.getLogger((String)"modinfo");
    private final Minecraft minecraft = Minecraft.func_71410_x();
    private final String trackingId;
    private final String modId;
    private final String modName;
    private final String modVersion;
    private Locale reportingLocale;
    private Config config;
    private Client client;

    public ModInfo(String trackingId, String reportingLanguageCode, String modId, String modName, String modVersion, boolean singleUse) {
        this.trackingId = trackingId;
        this.modId = modId;
        this.modName = modName;
        this.modVersion = modVersion;
        try {
            this.reportingLocale = this.getLocale(reportingLanguageCode);
            this.config = Config.getInstance(this.modId);
            this.client = this.createClient();
            if (singleUse) {
                this.singleUse();
            } else if (this.config.isEnabled().booleanValue()) {
                if (Config.generateStatusString(modId, false).equals(this.config.getStatus())) {
                    this.optIn();
                } else {
                    this.config.confirmStatus();
                }
            } else {
                this.optOut();
            }
        }
        catch (Throwable t) {
            LOGGER.log(Level.ERROR, "Unable to configure ModInfo", t);
        }
    }

    static UUID createUUID(String ... parts) {
        int i;
        MessageDigest md;
        try {
            md = MessageDigest.getInstance("MD5");
        }
        catch (NoSuchAlgorithmException nsae) {
            throw new InternalError("MD5 not supported");
        }
        for (String part : parts) {
            md.update(part.getBytes());
        }
        byte[] md5Bytes = md.digest();
        md5Bytes[6] = (byte)(md5Bytes[6] & 0xF);
        md5Bytes[6] = (byte)(md5Bytes[6] | 0x30);
        md5Bytes[8] = (byte)(md5Bytes[8] & 0x3F);
        md5Bytes[8] = (byte)(md5Bytes[8] | 0x80);
        long msb = 0L;
        long lsb = 0L;
        for (i = 0; i < 8; ++i) {
            msb = msb << 8 | (long)(md5Bytes[i] & 0xFF);
        }
        for (i = 8; i < 16; ++i) {
            lsb = lsb << 8 | (long)(md5Bytes[i] & 0xFF);
        }
        return new UUID(msb, lsb);
    }

    public final boolean isEnabled() {
        return this.client != null;
    }

    public void reportAppView() {
        try {
            if (this.isEnabled()) {
                Payload payload = new Payload(Payload.Type.AppView);
                payload.add(this.appViewParams());
                payload.add(this.minecraftParams());
                this.client.send(payload);
            }
        }
        catch (Throwable t) {
            LOGGER.log(Level.ERROR, t.getMessage(), t);
        }
    }

    public void reportException(Throwable e) {
        try {
            if (this.isEnabled()) {
                int byteLength;
                int byteLength2;
                int index;
                int byteLength3;
                String category = "Exception: " + e.toString();
                String lineDelim = " / ";
                int actionMaxBytes = Payload.Parameter.EventAction.getMaxBytes();
                int labelMaxBytes = Payload.Parameter.EventLabel.getMaxBytes();
                int maxBytes = actionMaxBytes + labelMaxBytes;
                StackTraceElement[] stackTrace = e.getStackTrace();
                ArrayList<Integer> byteLengths = new ArrayList<Integer>(stackTrace.length);
                int total = 0;
                for (int i = 0; i < stackTrace.length && total + (byteLength3 = Payload.encode(stackTrace[i].toString() + " / ").getBytes().length) <= maxBytes; ++i) {
                    total += byteLength3;
                    byteLengths.add(i, byteLength3);
                }
                StringBuilder action = new StringBuilder(actionMaxBytes / 11);
                int actionTotal = 0;
                for (index = 0; index < byteLengths.size() && actionTotal + (byteLength2 = ((Integer)byteLengths.get(index)).intValue()) <= actionMaxBytes; ++index) {
                    actionTotal += byteLength2;
                    action.append(stackTrace[index].toString() + " / ");
                }
                StringBuilder label = new StringBuilder(labelMaxBytes / 11);
                int labelTotal = 0;
                while (index < byteLengths.size() && labelTotal + (byteLength = ((Integer)byteLengths.get(index)).intValue()) <= labelMaxBytes) {
                    labelTotal += byteLength;
                    label.append(stackTrace[index].toString() + " / ");
                    ++index;
                }
                this.reportEvent(category, action.toString(), label.toString());
            }
        }
        catch (Throwable t) {
            LOGGER.log(Level.ERROR, t.getMessage(), t);
        }
    }

    public void reportEvent(String category, String action, String label) {
        try {
            if (this.isEnabled()) {
                Payload payload = new Payload(Payload.Type.Event);
                payload.add(this.appViewParams());
                payload.put(Payload.Parameter.EventCategory, category);
                payload.put(Payload.Parameter.EventAction, action);
                payload.put(Payload.Parameter.EventLabel, label);
                this.client.send(payload);
            }
        }
        catch (Throwable t) {
            LOGGER.log(Level.ERROR, t.getMessage(), t);
        }
    }

    public void keepAlive() {
        try {
            if (this.isEnabled()) {
                Payload payload = new Payload(Payload.Type.Event);
                payload.put(Payload.Parameter.EventCategory, "ModInfo");
                payload.put(Payload.Parameter.EventAction, "KeepAlive");
                payload.put(Payload.Parameter.NonInteractionHit, "1");
                this.client.send(payload);
            }
        }
        catch (Throwable t) {
            LOGGER.log(Level.ERROR, t.getMessage(), t);
        }
    }

    private Locale getLocale(String languageCode) {
        String english = "en_US";
        List<String> langs = Arrays.asList(english);
        if (!english.equals(languageCode)) {
            langs.add(languageCode);
        }
        Locale locale = new Locale();
        locale.func_135022_a(this.minecraft.func_110442_L(), langs);
        return locale;
    }

    private String I18n(String translationKey, Object ... parms) {
        return this.reportingLocale.func_135023_a(translationKey, parms);
    }

    private Client createClient() {
        String salt = this.config.getSalt();
        String username = this.minecraft.func_110432_I().func_111285_a();
        UUID clientId = ModInfo.createUUID(salt, username, this.modId);
        return new Client(this.trackingId, clientId, this.config, Minecraft.func_71410_x().func_135016_M().func_135041_c().func_135034_a());
    }

    private Map<Payload.Parameter, String> minecraftParams() {
        HashMap<Payload.Parameter, String> map = new HashMap<Payload.Parameter, String>();
        Language language = this.minecraft.func_135016_M().func_135041_c();
        map.put(Payload.Parameter.UserLanguage, language.func_135034_a());
        DisplayMode displayMode = Display.getDesktopDisplayMode();
        map.put(Payload.Parameter.ScreenResolution, displayMode.getWidth() + "x" + displayMode.getHeight());
        StringBuilder desc = new StringBuilder("1.12.2");
        if (this.minecraft.field_71441_e != null) {
            IntegratedServer server = this.minecraft.func_71401_C();
            boolean multiplayer = server == null || server.func_71344_c();
            desc.append(", ").append(multiplayer ? this.I18n("menu.multiplayer", new Object[0]) : this.I18n("menu.singleplayer", new Object[0]));
        }
        map.put(Payload.Parameter.ContentDescription, desc.toString());
        return map;
    }

    private Map<Payload.Parameter, String> appViewParams() {
        HashMap<Payload.Parameter, String> map = new HashMap<Payload.Parameter, String>();
        map.put(Payload.Parameter.ApplicationName, this.modName);
        map.put(Payload.Parameter.ApplicationVersion, this.modVersion);
        return map;
    }

    private void optIn() {
        Payload payload = new Payload(Payload.Type.Event);
        payload.put(Payload.Parameter.EventCategory, "ModInfo");
        payload.put(Payload.Parameter.EventAction, "Opt In");
        this.createClient().send(payload, new Message.Callback(){

            @Override
            public void onResult(Object result) {
                if (Boolean.TRUE.equals(result) && ModInfo.this.config.isEnabled().booleanValue()) {
                    ModInfo.this.config.confirmStatus();
                    LOGGER.info("ModInfo for " + ModInfo.this.config.getModId() + " has been re-enabled. Thank you!");
                }
            }
        });
    }

    public void singleUse() {
        if (Config.isConfirmedDisabled(this.config)) {
            return;
        }
        this.reportAppView();
        this.config.disable();
    }

    private void optOut() {
        if (Config.isConfirmedDisabled(this.config)) {
            LOGGER.info("ModInfo for " + this.modId + " is disabled");
        } else if (!this.config.isEnabled().booleanValue()) {
            Payload payload = new Payload(Payload.Type.Event);
            payload.put(Payload.Parameter.EventCategory, "ModInfo");
            payload.put(Payload.Parameter.EventAction, "Opt Out");
            this.createClient().send(payload, new Message.Callback(){

                @Override
                public void onResult(Object result) {
                    if (Boolean.TRUE.equals(result) && !ModInfo.this.config.isEnabled().booleanValue()) {
                        ModInfo.this.config.confirmStatus();
                        LOGGER.info("ModInfo for " + ModInfo.this.config.getModId() + " has been disabled");
                    }
                }
            });
        }
    }
}

