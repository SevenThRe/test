/*
 * Decompiled with CFR 0.152.
 */
package info.journeymap.shaded.org.slf4j.helpers;

import info.journeymap.shaded.org.slf4j.ILoggerFactory;
import info.journeymap.shaded.org.slf4j.Logger;
import info.journeymap.shaded.org.slf4j.helpers.NOPLogger;

public class NOPLoggerFactory
implements ILoggerFactory {
    public Logger getLogger(String name) {
        return NOPLogger.NOP_LOGGER;
    }
}

