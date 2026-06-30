/*
 * Decompiled with CFR 0.152.
 */
package org.bytedeco.javacpp.tools;

import org.bytedeco.javacpp.tools.Slf4jLogger;

public class Logger {
    static boolean debug = false;

    public static Logger create(Class cls) {
        String s2 = System.getProperty("org.bytedeco.javacpp.logger", "").toLowerCase();
        if (s2.equals("slf4j") || s2.equals("slf4jlogger")) {
            return new Slf4jLogger(cls);
        }
        return new Logger();
    }

    public boolean isDebugEnabled() {
        return debug;
    }

    public boolean isInfoEnabled() {
        return true;
    }

    public boolean isWarnEnabled() {
        return true;
    }

    public boolean isErrorEnabled() {
        return true;
    }

    public void debug(String s2) {
        System.err.println("Debug: " + s2);
    }

    public void info(String s2) {
        System.err.println("Info: " + s2);
    }

    public void warn(String s2) {
        System.err.println("Warning: " + s2);
    }

    public void error(String s2) {
        System.err.println("Error: " + s2);
    }

    static {
        String s2 = System.getProperty("org.bytedeco.javacpp.logger.debug", "false").toLowerCase();
        debug = s2.equals("true") || s2.equals("t") || s2.equals("");
    }
}

