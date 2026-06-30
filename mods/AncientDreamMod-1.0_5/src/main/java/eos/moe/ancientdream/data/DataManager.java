/*
 * Decompiled with CFR 0.152.
 */
package eos.moe.ancientdream.data;

import eos.moe.ancientdream.data.PlayerData;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class DataManager {
    public static PlayerData playerData = new PlayerData();
    public static Map<String, List<String>> outfits = new ConcurrentHashMap<String, List<String>>();
    public static Map<String, Integer> outfitCounts = new ConcurrentHashMap<String, Integer>();
}

