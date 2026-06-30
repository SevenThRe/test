/*
 * Decompiled with CFR 0.152.
 */
package net.optifine.util;

import java.util.HashMap;
import java.util.Map;

public class TimedEvent {
    private static Map<String, Long> mapEventTimes = new HashMap<String, Long>();

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public static boolean isActive(String name, long timeIntervalMs) {
        Map<String, Long> map = mapEventTimes;
        synchronized (map) {
            long timeLastMs;
            long timeNowMs = System.currentTimeMillis();
            Long timeLastMsObj = mapEventTimes.get(name);
            if (timeLastMsObj == null) {
                timeLastMsObj = new Long(timeNowMs);
                mapEventTimes.put(name, timeLastMsObj);
            }
            if (timeNowMs < (timeLastMs = timeLastMsObj.longValue()) + timeIntervalMs) {
                return false;
            }
            mapEventTimes.put(name, new Long(timeNowMs));
            return true;
        }
    }
}

