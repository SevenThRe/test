/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  acl
 *  acm
 *  acq
 *  acs
 *  add
 *  aed
 *  ael
 *  ahb
 *  ain
 *  aip
 *  air
 *  aow
 *  aox
 *  bsb
 *  buy
 *  et
 *  my
 *  na
 *  nf
 *  vg
 *  vl
 *  vp
 */
package net.optifine;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import net.optifine.DynamicLight;
import net.optifine.DynamicLightsMap;
import net.optifine.config.ConnectedParser;
import net.optifine.config.EntityClassLocator;
import net.optifine.config.IObjectLocator;
import net.optifine.config.ItemLocator;
import net.optifine.reflect.ReflectorForge;
import net.optifine.util.PropertiesOrdered;

public class DynamicLights {
    private static DynamicLightsMap mapDynamicLights = new DynamicLightsMap();
    private static Map<Class, Integer> mapEntityLightLevels = new HashMap<Class, Integer>();
    private static Map<ain, Integer> mapItemLightLevels = new HashMap<ain, Integer>();
    private static long timeUpdateMs = 0L;
    private static final double MAX_DIST = 7.5;
    private static final double MAX_DIST_SQ = 56.25;
    private static final int LIGHT_LEVEL_MAX = 15;
    private static final int LIGHT_LEVEL_FIRE = 15;
    private static final int LIGHT_LEVEL_BLAZE = 10;
    private static final int LIGHT_LEVEL_MAGMA_CUBE = 8;
    private static final int LIGHT_LEVEL_MAGMA_CUBE_CORE = 13;
    private static final int LIGHT_LEVEL_GLOWSTONE_DUST = 8;
    private static final int LIGHT_LEVEL_PRISMARINE_CRYSTALS = 8;
    private static final my<aip> PARAMETER_ITEM_STACK = new my(6, na.f);
    private static boolean initialized;

    public static void entityAdded(vg entityIn, buy renderGlobal) {
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public static void entityRemoved(vg entityIn, buy renderGlobal) {
        DynamicLightsMap dynamicLightsMap = mapDynamicLights;
        synchronized (dynamicLightsMap) {
            DynamicLight dynamicLight = mapDynamicLights.remove(entityIn.S());
            if (dynamicLight != null) {
                dynamicLight.updateLitChunks(renderGlobal);
            }
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public static void update(buy renderGlobal) {
        long timeNowMs = System.currentTimeMillis();
        if (timeNowMs < timeUpdateMs + 50L) {
            return;
        }
        timeUpdateMs = timeNowMs;
        if (!initialized) {
            DynamicLights.initialize();
        }
        DynamicLightsMap dynamicLightsMap = mapDynamicLights;
        synchronized (dynamicLightsMap) {
            DynamicLights.updateMapDynamicLights(renderGlobal);
            if (mapDynamicLights.size() <= 0) {
                return;
            }
            List<DynamicLight> dynamicLights = mapDynamicLights.valueList();
            for (int i = 0; i < dynamicLights.size(); ++i) {
                DynamicLight dynamicLight = dynamicLights.get(i);
                dynamicLight.update(renderGlobal);
            }
        }
    }

    private static void initialize() {
        initialized = true;
        mapEntityLightLevels.clear();
        mapItemLightLevels.clear();
        String[] modIds = ReflectorForge.getForgeModIds();
        for (int i = 0; i < modIds.length; ++i) {
            String modId = modIds[i];
            try {
                nf loc = new nf(modId, "optifine/dynamic_lights.properties");
                InputStream in = Config.getResourceStream(loc);
                DynamicLights.loadModConfiguration(in, loc.toString(), modId);
                continue;
            }
            catch (IOException iOException) {
                // empty catch block
            }
        }
        if (mapEntityLightLevels.size() > 0) {
            Config.dbg("DynamicLights entities: " + mapEntityLightLevels.size());
        }
        if (mapItemLightLevels.size() > 0) {
            Config.dbg("DynamicLights items: " + mapItemLightLevels.size());
        }
    }

    private static void loadModConfiguration(InputStream in, String path, String modId) {
        if (in == null) {
            return;
        }
        try {
            PropertiesOrdered props = new PropertiesOrdered();
            props.load(in);
            in.close();
            Config.dbg("DynamicLights: Parsing " + path);
            ConnectedParser cp = new ConnectedParser("DynamicLights");
            DynamicLights.loadModLightLevels(props.getProperty("entities"), mapEntityLightLevels, new EntityClassLocator(), cp, path, modId);
            DynamicLights.loadModLightLevels(props.getProperty("items"), mapItemLightLevels, new ItemLocator(), cp, path, modId);
        }
        catch (IOException e) {
            Config.warn("DynamicLights: Error reading " + path);
        }
    }

    private static void loadModLightLevels(String prop, Map mapLightLevels, IObjectLocator ol, ConnectedParser cp, String path, String modId) {
        if (prop == null) {
            return;
        }
        String[] parts = Config.tokenize(prop, " ");
        for (int i = 0; i < parts.length; ++i) {
            String part = parts[i];
            String[] tokens = Config.tokenize(part, ":");
            if (tokens.length != 2) {
                cp.warn("Invalid entry: " + part + ", in:" + path);
                continue;
            }
            String name = tokens[0];
            String light = tokens[1];
            String nameFull = modId + ":" + name;
            nf loc = new nf(nameFull);
            Object obj = ol.getObject(loc);
            if (obj == null) {
                cp.warn("Object not found: " + nameFull);
                continue;
            }
            int lightLevel = cp.parseInt(light, -1);
            if (lightLevel < 0 || lightLevel > 15) {
                cp.warn("Invalid light level: " + part);
                continue;
            }
            mapLightLevels.put(obj, new Integer(lightLevel));
        }
    }

    private static void updateMapDynamicLights(buy renderGlobal) {
        bsb world = renderGlobal.getWorld();
        if (world == null) {
            return;
        }
        List entities = world.L();
        for (vg entity : entities) {
            DynamicLight dynamicLight;
            int key;
            int lightLevel = DynamicLights.getLightLevel(entity);
            if (lightLevel > 0) {
                key = entity.S();
                dynamicLight = mapDynamicLights.get(key);
                if (dynamicLight != null) continue;
                dynamicLight = new DynamicLight(entity);
                mapDynamicLights.put(key, dynamicLight);
                continue;
            }
            key = entity.S();
            dynamicLight = mapDynamicLights.remove(key);
            if (dynamicLight == null) continue;
            dynamicLight.updateLitChunks(renderGlobal);
        }
    }

    public static int getCombinedLight(et pos, int combinedLight) {
        double lightPlayer = DynamicLights.getLightLevel(pos);
        combinedLight = DynamicLights.getCombinedLight(lightPlayer, combinedLight);
        return combinedLight;
    }

    public static int getCombinedLight(vg entity, int combinedLight) {
        double lightPlayer = DynamicLights.getLightLevel(entity);
        combinedLight = DynamicLights.getCombinedLight(lightPlayer, combinedLight);
        return combinedLight;
    }

    public static int getCombinedLight(double lightPlayer, int combinedLight) {
        int lightBlockFF;
        int lightPlayerFF;
        if (lightPlayer > 0.0 && (lightPlayerFF = (int)(lightPlayer * 16.0)) > (lightBlockFF = combinedLight & 0xFF)) {
            combinedLight &= 0xFFFFFF00;
            combinedLight |= lightPlayerFF;
        }
        return combinedLight;
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public static double getLightLevel(et pos) {
        double lightLevelMax = 0.0;
        DynamicLightsMap dynamicLightsMap = mapDynamicLights;
        synchronized (dynamicLightsMap) {
            List<DynamicLight> dynamicLights = mapDynamicLights.valueList();
            for (int i = 0; i < dynamicLights.size(); ++i) {
                double dist;
                double light;
                double lightLevel;
                DynamicLight dynamicLight = dynamicLights.get(i);
                int dynamicLightLevel = dynamicLight.getLastLightLevel();
                if (dynamicLightLevel <= 0) continue;
                double px = dynamicLight.getLastPosX();
                double py = dynamicLight.getLastPosY();
                double pz = dynamicLight.getLastPosZ();
                double dx = (double)pos.p() - px;
                double dy = (double)pos.q() - py;
                double dz = (double)pos.r() - pz;
                double distSq = dx * dx + dy * dy + dz * dz;
                if (dynamicLight.isUnderwater() && !Config.isClearWater()) {
                    dynamicLightLevel = Config.limit(dynamicLightLevel - 2, 0, 15);
                    distSq *= 2.0;
                }
                if (distSq > 56.25 || !((lightLevel = (light = 1.0 - (dist = Math.sqrt(distSq)) / 7.5) * (double)dynamicLightLevel) > lightLevelMax)) continue;
                lightLevelMax = lightLevel;
            }
        }
        double lightPlayer = Config.limit(lightLevelMax, 0.0, 15.0);
        return lightPlayer;
    }

    public static int getLightLevel(aip itemStack) {
        Integer level;
        ahb itemBlock;
        aow block;
        if (itemStack == null) {
            return 0;
        }
        ain item = itemStack.c();
        if (item instanceof ahb && (block = (itemBlock = (ahb)item).d()) != null) {
            return block.o(block.t());
        }
        if (item == air.aB) {
            return aox.l.o(aox.l.t());
        }
        if (item == air.bD || item == air.bO) {
            return 10;
        }
        if (item == air.bb) {
            return 8;
        }
        if (item == air.cO) {
            return 8;
        }
        if (item == air.bP) {
            return 8;
        }
        if (item == air.ck) {
            return aox.bY.o(aox.bY.t()) / 2;
        }
        if (!mapItemLightLevels.isEmpty() && (level = mapItemLightLevels.get(item)) != null) {
            return level;
        }
        return 0;
    }

    public static int getLightLevel(vg entity) {
        acs entityCreeper;
        Integer level;
        aed player;
        if (entity == Config.getMinecraft().aa() && !Config.isDynamicHandLight()) {
            return 0;
        }
        if (entity instanceof aed && (player = (aed)entity).y()) {
            return 0;
        }
        if (entity.aR()) {
            return 15;
        }
        if (!mapEntityLightLevels.isEmpty() && (level = mapEntityLightLevels.get(entity.getClass())) != null) {
            return level;
        }
        if (entity instanceof ael) {
            return 15;
        }
        if (entity instanceof acm) {
            return 15;
        }
        if (entity instanceof acq) {
            acq entityBlaze = (acq)entity;
            if (entityBlaze.p()) {
                return 15;
            }
            return 10;
        }
        if (entity instanceof add) {
            add emc = (add)entity;
            if ((double)emc.b > 0.6) {
                return 13;
            }
            return 8;
        }
        if (entity instanceof acs && (double)(entityCreeper = (acs)entity).a(0.0f) > 0.001) {
            return 15;
        }
        if (entity instanceof vp) {
            player = (vp)entity;
            aip stackMain = player.co();
            int levelMain = DynamicLights.getLightLevel(stackMain);
            aip stackOff = player.cp();
            int levelOff = DynamicLights.getLightLevel(stackOff);
            aip stackHead = player.b(vl.f);
            int levelHead = DynamicLights.getLightLevel(stackHead);
            int levelHandMax = Math.max(levelMain, levelOff);
            return Math.max(levelHandMax, levelHead);
        }
        if (entity instanceof acl) {
            acl entityItem = (acl)entity;
            aip itemStack = DynamicLights.getItemStack(entityItem);
            return DynamicLights.getLightLevel(itemStack);
        }
        return 0;
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public static void removeLights(buy renderGlobal) {
        DynamicLightsMap dynamicLightsMap = mapDynamicLights;
        synchronized (dynamicLightsMap) {
            List<DynamicLight> dynamicLights = mapDynamicLights.valueList();
            for (int i = 0; i < dynamicLights.size(); ++i) {
                DynamicLight dynamicLight = dynamicLights.get(i);
                dynamicLight.updateLitChunks(renderGlobal);
            }
            mapDynamicLights.clear();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public static void clear() {
        DynamicLightsMap dynamicLightsMap = mapDynamicLights;
        synchronized (dynamicLightsMap) {
            mapDynamicLights.clear();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public static int getCount() {
        DynamicLightsMap dynamicLightsMap = mapDynamicLights;
        synchronized (dynamicLightsMap) {
            return mapDynamicLights.size();
        }
    }

    public static aip getItemStack(acl entityItem) {
        aip itemstack = (aip)entityItem.V().a(PARAMETER_ITEM_STACK);
        return itemstack;
    }
}

