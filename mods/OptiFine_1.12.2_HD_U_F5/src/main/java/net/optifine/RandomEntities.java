/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  aah
 *  aaq
 *  ady
 *  amu
 *  avj
 *  bua
 *  buy
 *  bwx
 *  fy
 *  nb
 *  nf
 *  vg
 *  vp
 */
package net.optifine;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import net.optifine.IRandomEntity;
import net.optifine.RandomEntity;
import net.optifine.RandomEntityProperties;
import net.optifine.RandomTileEntity;
import net.optifine.reflect.Reflector;
import net.optifine.reflect.ReflectorRaw;
import net.optifine.util.IntegratedServerUtils;
import net.optifine.util.PropertiesOrdered;
import net.optifine.util.ResUtils;
import net.optifine.util.StrUtils;

public class RandomEntities {
    private static Map<String, RandomEntityProperties> mapProperties = new HashMap<String, RandomEntityProperties>();
    private static boolean active = false;
    private static buy renderGlobal;
    private static RandomEntity randomEntity;
    private static bwx tileEntityRendererDispatcher;
    private static RandomTileEntity randomTileEntity;
    private static boolean working;
    public static final String SUFFIX_PNG = ".png";
    public static final String SUFFIX_PROPERTIES = ".properties";
    public static final String PREFIX_TEXTURES_ENTITY = "textures/entity/";
    public static final String PREFIX_TEXTURES_PAINTING = "textures/painting/";
    public static final String PREFIX_TEXTURES = "textures/";
    public static final String PREFIX_OPTIFINE_RANDOM = "optifine/random/";
    public static final String PREFIX_MCPATCHER_MOB = "mcpatcher/mob/";
    private static final String[] DEPENDANT_SUFFIXES;
    private static final String PREFIX_DYNAMIC_TEXTURE_HORSE = "horse/";
    private static final String[] HORSE_TEXTURES;
    private static final String[] HORSE_TEXTURES_ABBR;

    public static void entityLoaded(vg entity, amu world) {
        if (world == null) {
            return;
        }
        nb edm = entity.V();
        edm.spawnPosition = entity.c();
        edm.spawnBiome = world.b(edm.spawnPosition);
        if (entity instanceof aah) {
            aah esr = (aah)entity;
            RandomEntities.checkEntityShoulder(esr, false);
        }
        UUID uuid = entity.bm();
        if (entity instanceof ady) {
            RandomEntities.updateEntityVillager(uuid, (ady)entity);
        }
    }

    public static void entityUnloaded(vg entity, amu world) {
        if (entity instanceof aah) {
            aah esr = (aah)entity;
            RandomEntities.checkEntityShoulder(esr, true);
        }
    }

    private static void checkEntityShoulder(aah entity, boolean attach) {
        vp owner = entity.do();
        if (owner == null) {
            owner = Config.getMinecraft().h;
        }
        if (!(owner instanceof bua)) {
            return;
        }
        bua player = (bua)owner;
        UUID entityUuid = entity.bm();
        if (attach) {
            fy nbtRight;
            fy nbtLeft = player.dp();
            if (nbtLeft != null && Config.equals(nbtLeft.a("UUID"), entityUuid)) {
                player.entityShoulderLeft = entity;
            }
            if ((nbtRight = player.dq()) != null && Config.equals(nbtRight.a("UUID"), entityUuid)) {
                player.entityShoulderRight = entity;
            }
        } else {
            nb edm = entity.V();
            if (player.entityShoulderLeft != null && Config.equals(player.entityShoulderLeft.bm(), entityUuid)) {
                nb edmShoulderLeft = player.entityShoulderLeft.V();
                edm.spawnPosition = edmShoulderLeft.spawnPosition;
                edm.spawnBiome = edmShoulderLeft.spawnBiome;
                player.entityShoulderLeft = null;
            }
            if (player.entityShoulderRight != null && Config.equals(player.entityShoulderRight.bm(), entityUuid)) {
                nb edmShoulderRight = player.entityShoulderRight.V();
                edm.spawnPosition = edmShoulderRight.spawnPosition;
                edm.spawnBiome = edmShoulderRight.spawnBiome;
                player.entityShoulderRight = null;
            }
        }
    }

    private static void updateEntityVillager(UUID uuid, ady ev) {
        vg se = IntegratedServerUtils.getEntity(uuid);
        if (!(se instanceof ady)) {
            return;
        }
        ady sev = (ady)se;
        int profSev = sev.dl();
        ev.g(profSev);
        int careerId = Reflector.getFieldValueInt(sev, Reflector.EntityVillager_careerId, 0);
        Reflector.setFieldValueInt(ev, Reflector.EntityVillager_careerId, careerId);
        int careerLevel = Reflector.getFieldValueInt(sev, Reflector.EntityVillager_careerLevel, 0);
        Reflector.setFieldValueInt(ev, Reflector.EntityVillager_careerLevel, careerLevel);
    }

    public static void worldChanged(amu oldWorld, amu newWorld) {
        if (newWorld != null) {
            List entityList = newWorld.L();
            for (int e = 0; e < entityList.size(); ++e) {
                vg entity = (vg)entityList.get(e);
                RandomEntities.entityLoaded(entity, newWorld);
            }
        }
        randomEntity.setEntity(null);
        randomTileEntity.setTileEntity(null);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public static nf getTextureLocation(nf loc) {
        if (!active) {
            return loc;
        }
        if (working) {
            return loc;
        }
        try {
            working = true;
            IRandomEntity re = RandomEntities.getRandomEntityRendered();
            if (re == null) {
                nf nf2 = loc;
                return nf2;
            }
            String name = loc.a();
            if (name.startsWith(PREFIX_DYNAMIC_TEXTURE_HORSE)) {
                name = RandomEntities.getHorseTexturePath(name, PREFIX_DYNAMIC_TEXTURE_HORSE.length());
            }
            if (!name.startsWith(PREFIX_TEXTURES_ENTITY) && !name.startsWith(PREFIX_TEXTURES_PAINTING)) {
                nf nf3 = loc;
                return nf3;
            }
            RandomEntityProperties props = mapProperties.get(name);
            if (props == null) {
                nf nf4 = loc;
                return nf4;
            }
            nf nf5 = props.getTextureLocation(loc, re);
            return nf5;
        }
        finally {
            working = false;
        }
    }

    private static String getHorseTexturePath(String path, int pos) {
        if (HORSE_TEXTURES == null || HORSE_TEXTURES_ABBR == null) {
            return path;
        }
        for (int i = 0; i < HORSE_TEXTURES_ABBR.length; ++i) {
            String abbr = HORSE_TEXTURES_ABBR[i];
            if (!path.startsWith(abbr, pos)) continue;
            return HORSE_TEXTURES[i];
        }
        return path;
    }

    private static IRandomEntity getRandomEntityRendered() {
        avj te;
        if (RandomEntities.renderGlobal.renderedEntity != null) {
            randomEntity.setEntity(RandomEntities.renderGlobal.renderedEntity);
            return randomEntity;
        }
        if (RandomEntities.tileEntityRendererDispatcher.tileEntityRendered != null && (te = RandomEntities.tileEntityRendererDispatcher.tileEntityRendered).D() != null) {
            randomTileEntity.setTileEntity(te);
            return randomTileEntity;
        }
        return null;
    }

    private static RandomEntityProperties makeProperties(nf loc, boolean mcpatcher) {
        RandomEntityProperties props;
        String path = loc.a();
        nf locProps = RandomEntities.getLocationProperties(loc, mcpatcher);
        if (locProps != null && (props = RandomEntities.parseProperties(locProps, loc)) != null) {
            return props;
        }
        nf[] variants = RandomEntities.getLocationsVariants(loc, mcpatcher);
        if (variants == null) {
            return null;
        }
        return new RandomEntityProperties(path, variants);
    }

    private static RandomEntityProperties parseProperties(nf propLoc, nf resLoc) {
        try {
            String path = propLoc.a();
            RandomEntities.dbg(resLoc.a() + ", properties: " + path);
            InputStream in = Config.getResourceStream(propLoc);
            if (in == null) {
                RandomEntities.warn("Properties not found: " + path);
                return null;
            }
            PropertiesOrdered props = new PropertiesOrdered();
            props.load(in);
            in.close();
            RandomEntityProperties rmp = new RandomEntityProperties(props, path, resLoc);
            if (!rmp.isValid(path)) {
                return null;
            }
            return rmp;
        }
        catch (FileNotFoundException e) {
            RandomEntities.warn("File not found: " + resLoc.a());
            return null;
        }
        catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    private static nf getLocationProperties(nf loc, boolean mcpatcher) {
        String path;
        String pathBase;
        String pathProps;
        nf locMcp = RandomEntities.getLocationRandom(loc, mcpatcher);
        if (locMcp == null) {
            return null;
        }
        String domain = locMcp.b();
        nf locProps = new nf(domain, pathProps = (pathBase = StrUtils.removeSuffix(path = locMcp.a(), SUFFIX_PNG)) + SUFFIX_PROPERTIES);
        if (Config.hasResource(locProps)) {
            return locProps;
        }
        String pathParent = RandomEntities.getParentTexturePath(pathBase);
        if (pathParent == null) {
            return null;
        }
        nf locParentProps = new nf(domain, pathParent + SUFFIX_PROPERTIES);
        if (Config.hasResource(locParentProps)) {
            return locParentProps;
        }
        return null;
    }

    protected static nf getLocationRandom(nf loc, boolean mcpatcher) {
        String domain = loc.b();
        String path = loc.a();
        String prefixTextures = PREFIX_TEXTURES;
        String prefixRandom = PREFIX_OPTIFINE_RANDOM;
        if (mcpatcher) {
            prefixTextures = PREFIX_TEXTURES_ENTITY;
            prefixRandom = PREFIX_MCPATCHER_MOB;
        }
        if (!path.startsWith(prefixTextures)) {
            return null;
        }
        String pathRandom = StrUtils.replacePrefix(path, prefixTextures, prefixRandom);
        return new nf(domain, pathRandom);
    }

    private static String getPathBase(String pathRandom) {
        if (pathRandom.startsWith(PREFIX_OPTIFINE_RANDOM)) {
            return StrUtils.replacePrefix(pathRandom, PREFIX_OPTIFINE_RANDOM, PREFIX_TEXTURES);
        }
        if (pathRandom.startsWith(PREFIX_MCPATCHER_MOB)) {
            return StrUtils.replacePrefix(pathRandom, PREFIX_MCPATCHER_MOB, PREFIX_TEXTURES_ENTITY);
        }
        return null;
    }

    protected static nf getLocationIndexed(nf loc, int index) {
        if (loc == null) {
            return null;
        }
        String path = loc.a();
        int pos = path.lastIndexOf(46);
        if (pos < 0) {
            return null;
        }
        String prefix = path.substring(0, pos);
        String suffix = path.substring(pos);
        String pathNew = prefix + index + suffix;
        nf locNew = new nf(loc.b(), pathNew);
        return locNew;
    }

    private static String getParentTexturePath(String path) {
        for (int i = 0; i < DEPENDANT_SUFFIXES.length; ++i) {
            String suffix = DEPENDANT_SUFFIXES[i];
            if (!path.endsWith(suffix)) continue;
            String pathParent = StrUtils.removeSuffix(path, suffix);
            return pathParent;
        }
        return null;
    }

    private static nf[] getLocationsVariants(nf loc, boolean mcpatcher) {
        ArrayList<nf> list = new ArrayList<nf>();
        list.add(loc);
        nf locRandom = RandomEntities.getLocationRandom(loc, mcpatcher);
        if (locRandom == null) {
            return null;
        }
        for (int i = 1; i < list.size() + 10; ++i) {
            int index = i + 1;
            nf locIndex = RandomEntities.getLocationIndexed(locRandom, index);
            if (!Config.hasResource(locIndex)) continue;
            list.add(locIndex);
        }
        if (list.size() <= 1) {
            return null;
        }
        nf[] locs = list.toArray(new nf[list.size()]);
        RandomEntities.dbg(loc.a() + ", variants: " + locs.length);
        return locs;
    }

    public static void update() {
        mapProperties.clear();
        active = false;
        if (!Config.isRandomEntities()) {
            return;
        }
        RandomEntities.initialize();
    }

    private static void initialize() {
        renderGlobal = Config.getRenderGlobal();
        tileEntityRendererDispatcher = bwx.a;
        String[] prefixes = new String[]{PREFIX_OPTIFINE_RANDOM, PREFIX_MCPATCHER_MOB};
        String[] suffixes = new String[]{SUFFIX_PNG, SUFFIX_PROPERTIES};
        String[] pathsRandom = ResUtils.collectFiles(prefixes, suffixes);
        HashSet<String> basePathsChecked = new HashSet<String>();
        for (int i = 0; i < pathsRandom.length; ++i) {
            RandomEntityProperties props;
            String path = pathsRandom[i];
            path = StrUtils.removeSuffix(path, suffixes);
            path = StrUtils.trimTrailing(path, "0123456789");
            String pathBase = RandomEntities.getPathBase(path = path + SUFFIX_PNG);
            if (basePathsChecked.contains(pathBase)) continue;
            basePathsChecked.add(pathBase);
            nf locBase = new nf(pathBase);
            if (!Config.hasResource(locBase) || (props = mapProperties.get(pathBase)) != null) continue;
            props = RandomEntities.makeProperties(locBase, false);
            if (props == null) {
                props = RandomEntities.makeProperties(locBase, true);
            }
            if (props == null) continue;
            mapProperties.put(pathBase, props);
        }
        active = !mapProperties.isEmpty();
    }

    public static void dbg(String str) {
        Config.dbg("RandomEntities: " + str);
    }

    public static void warn(String str) {
        Config.warn("RandomEntities: " + str);
    }

    static {
        randomEntity = new RandomEntity();
        randomTileEntity = new RandomTileEntity();
        working = false;
        DEPENDANT_SUFFIXES = new String[]{"_armor", "_eyes", "_exploding", "_shooting", "_fur", "_eyes", "_invulnerable", "_angry", "_tame", "_collar"};
        HORSE_TEXTURES = (String[])ReflectorRaw.getFieldValue(null, aaq.class, String[].class, 0);
        HORSE_TEXTURES_ABBR = (String[])ReflectorRaw.getFieldValue(null, aaq.class, String[].class, 1);
    }
}

