/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.Minecraft
 *  net.minecraftforge.common.ForgeVersion
 *  org.apache.logging.log4j.Level
 *  org.apache.logging.log4j.LogManager
 *  org.apache.logging.log4j.Logger
 *  org.apache.logging.log4j.MarkerManager
 *  org.apache.logging.log4j.core.Appender
 *  org.apache.logging.log4j.core.Layout
 *  org.apache.logging.log4j.core.LogEvent
 *  org.apache.logging.log4j.core.Logger
 *  org.apache.logging.log4j.core.appender.RandomAccessFileAppender
 *  org.apache.logging.log4j.core.impl.Log4jLogEvent
 *  org.apache.logging.log4j.core.layout.PatternLayout
 *  org.apache.logging.log4j.message.Message
 *  org.apache.logging.log4j.message.SimpleMessage
 */
package journeymap.client.log;

import java.io.File;
import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;
import journeymap.client.Constants;
import journeymap.client.JourneymapClient;
import journeymap.client.feature.FeatureManager;
import journeymap.client.io.FileHandler;
import journeymap.client.properties.ClientPropertiesBase;
import journeymap.common.Journeymap;
import journeymap.common.log.LogFormatter;
import journeymap.common.properties.PropertiesBase;
import journeymap.common.properties.config.StringField;
import net.minecraft.client.Minecraft;
import net.minecraftforge.common.ForgeVersion;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.MarkerManager;
import org.apache.logging.log4j.core.Appender;
import org.apache.logging.log4j.core.Layout;
import org.apache.logging.log4j.core.LogEvent;
import org.apache.logging.log4j.core.appender.RandomAccessFileAppender;
import org.apache.logging.log4j.core.impl.Log4jLogEvent;
import org.apache.logging.log4j.core.layout.PatternLayout;
import org.apache.logging.log4j.message.Message;
import org.apache.logging.log4j.message.SimpleMessage;

public class JMLogger {
    public static final String DEPRECATED_LOG_FILE = "journeyMap.log";
    public static final String LOG_FILE = "journeymap.log";
    private static final HashSet<Integer> singletonErrors = new HashSet();
    private static final AtomicInteger singletonErrorsCounter = new AtomicInteger(0);
    private static RandomAccessFileAppender fileAppender;

    public static Logger init() {
        Logger logger = LogManager.getLogger((String)"journeymap");
        if (!logger.isInfoEnabled()) {
            logger.warn("Forge is surpressing INFO-level logging. If you need technical support for JourneyMap, you must return logging to INFO.");
        }
        try {
            File deprecatedLog = new File(FileHandler.getJourneyMapDir(), DEPRECATED_LOG_FILE);
            if (deprecatedLog.exists()) {
                deprecatedLog.delete();
            }
        }
        catch (Exception e) {
            logger.error("Error removing deprecated logfile: " + e.getMessage());
        }
        try {
            File logFile = JMLogger.getLogFile();
            if (logFile.exists()) {
                logFile.delete();
            } else {
                logFile.getParentFile().mkdirs();
            }
            PatternLayout layout = PatternLayout.createLayout((String)"[%d{HH:mm:ss}] [%t/%level] [%C{1}] %msg%n", null, null, null, null, (boolean)true, (boolean)false, null, null);
            fileAppender = RandomAccessFileAppender.createAppender((String)logFile.getAbsolutePath(), (String)"treu", (String)"journeymap-logfile", (String)"true", null, (String)"true", (Layout)layout, null, (String)"false", null, null);
            ((org.apache.logging.log4j.core.Logger)logger).addAppender((Appender)fileAppender);
            if (!fileAppender.isStarted()) {
                fileAppender.start();
            }
            logger.info("JourneyMap log initialized.");
        }
        catch (SecurityException e) {
            logger.error("Error adding file handler: " + LogFormatter.toString(e));
        }
        catch (Throwable e) {
            logger.error("Error adding file handler: " + LogFormatter.toString(e));
        }
        return logger;
    }

    public static void setLevelFromProperties() {
        try {
            Logger logger = LogManager.getLogger((String)"journeymap");
            ((org.apache.logging.log4j.core.Logger)logger).setLevel(Level.toLevel((String)Journeymap.getClient().getCoreProperties().logLevel.get(), (Level)Level.INFO));
        }
        catch (Throwable t) {
            t.printStackTrace();
        }
    }

    public static void logProperties() {
        Log4jLogEvent record = new Log4jLogEvent(JourneymapClient.MOD_NAME, MarkerManager.getMarker((String)JourneymapClient.MOD_NAME), null, Level.INFO, (Message)new SimpleMessage(JMLogger.getPropertiesSummary()), null);
        if (fileAppender != null) {
            fileAppender.append((LogEvent)record);
        }
    }

    public static String getPropertiesSummary() {
        LinkedHashMap<String, String> props = new LinkedHashMap<String, String>();
        props.put("Version", JourneymapClient.MOD_NAME + ", built with Forge " + "14.23.5.2768");
        props.put("Forge", ForgeVersion.getVersion());
        List<String> envProps = Arrays.asList("os.name, os.arch, java.version, user.country, user.language");
        StringBuilder sb = new StringBuilder();
        for (String string : envProps) {
            sb.append(string).append("=").append(System.getProperty(string)).append(", ");
        }
        sb.append("game language=").append(Minecraft.getMinecraft().gameSettings.language).append(", ");
        sb.append("locale=").append(Constants.getLocale());
        props.put("Environment", sb.toString());
        sb = new StringBuilder();
        for (Map.Entry entry : props.entrySet()) {
            if (sb.length() > 0) {
                sb.append(LogFormatter.LINEBREAK);
            }
            sb.append((String)entry.getKey()).append(": ").append((String)entry.getValue());
        }
        sb.append(LogFormatter.LINEBREAK).append(FeatureManager.getPolicyDetails());
        JourneymapClient jm = Journeymap.getClient();
        List<ClientPropertiesBase> list = Arrays.asList(Journeymap.getClient().getMiniMapProperties1(), Journeymap.getClient().getMiniMapProperties2(), Journeymap.getClient().getFullMapProperties(), Journeymap.getClient().getWaypointProperties(), Journeymap.getClient().getWebMapProperties(), Journeymap.getClient().getCoreProperties());
        for (PropertiesBase propertiesBase : list) {
            sb.append(LogFormatter.LINEBREAK).append(propertiesBase);
        }
        return sb.toString();
    }

    public static File getLogFile() {
        return new File(FileHandler.getJourneyMapDir(), LOG_FILE);
    }

    public static void logOnce(String text, Throwable throwable) {
        if (!singletonErrors.contains(text.hashCode())) {
            singletonErrors.add(text.hashCode());
            Journeymap.getLogger().error(text + " (SUPPRESSED)");
            if (throwable != null) {
                Journeymap.getLogger().error(LogFormatter.toString(throwable));
            }
        } else {
            int count = singletonErrorsCounter.incrementAndGet();
            if (count > 1000) {
                singletonErrors.clear();
                singletonErrorsCounter.set(0);
            }
        }
    }

    public static class LogLevelStringProvider
    implements StringField.ValuesProvider {
        @Override
        public List<String> getStrings() {
            Level[] levels = Level.values();
            String[] levelStrings = new String[levels.length];
            for (int i = 0; i < levels.length; ++i) {
                levelStrings[i] = levels[i].toString();
            }
            return Arrays.asList(levelStrings);
        }

        @Override
        public String getDefaultString() {
            return Level.INFO.toString();
        }
    }
}

