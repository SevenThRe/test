/*
 * Decompiled with CFR 0.152.
 */
package net.optifine.shaders.uniform;

import java.util.HashMap;
import java.util.Map;
import net.optifine.util.CounterInt;
import net.optifine.util.SmoothFloat;

public class Smoother {
    private static Map<Integer, SmoothFloat> mapSmoothValues = new HashMap<Integer, SmoothFloat>();
    private static CounterInt counterIds = new CounterInt(1);

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public static float getSmoothValue(int id, float value, float timeFadeUpSec, float timeFadeDownSec) {
        Map<Integer, SmoothFloat> map = mapSmoothValues;
        synchronized (map) {
            Integer key = id;
            SmoothFloat sf = mapSmoothValues.get(key);
            if (sf == null) {
                sf = new SmoothFloat(value, timeFadeUpSec, timeFadeDownSec);
                mapSmoothValues.put(key, sf);
            }
            float valueSmooth = sf.getSmoothValue(value, timeFadeUpSec, timeFadeDownSec);
            return valueSmooth;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public static int getNextId() {
        CounterInt counterInt = counterIds;
        synchronized (counterInt) {
            return counterIds.nextValue();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public static void resetValues() {
        Map<Integer, SmoothFloat> map = mapSmoothValues;
        synchronized (map) {
            mapSmoothValues.clear();
        }
    }
}

