/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  ahs
 *  ain
 *  amm
 *  anh
 *  anm
 *  aow
 *  aox
 *  aqb
 *  asl
 *  awt
 *  axj
 *  com.google.common.collect.Lists
 *  fa
 *  it.unimi.dsi.fastutil.ints.IntArraySet
 *  nf
 *  ro
 *  vi
 */
package net.optifine.config;

import com.google.common.collect.Lists;
import it.unimi.dsi.fastutil.ints.IntArraySet;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;
import net.optifine.ConnectedProperties;
import net.optifine.config.INameGetter;
import net.optifine.config.MatchBlock;
import net.optifine.config.NbtTagValue;
import net.optifine.config.RangeInt;
import net.optifine.config.RangeListInt;
import net.optifine.config.VillagerProfession;
import net.optifine.config.Weather;

public class ConnectedParser {
    private String context = null;
    public static final VillagerProfession[] PROFESSIONS_INVALID = new VillagerProfession[0];
    public static final ahs[] DYE_COLORS_INVALID = new ahs[0];
    private static final INameGetter<Enum> NAME_GETTER_ENUM = new INameGetter<Enum>(){

        @Override
        public String getName(Enum en) {
            return en.name();
        }
    };
    private static final INameGetter<ahs> NAME_GETTER_DYE_COLOR = new INameGetter<ahs>(){

        @Override
        public String getName(ahs col) {
            return col.m();
        }
    };

    public ConnectedParser(String context) {
        this.context = context;
    }

    public String parseName(String path) {
        int pos2;
        String str = path;
        int pos = str.lastIndexOf(47);
        if (pos >= 0) {
            str = str.substring(pos + 1);
        }
        if ((pos2 = str.lastIndexOf(46)) >= 0) {
            str = str.substring(0, pos2);
        }
        return str;
    }

    public String parseBasePath(String path) {
        int pos = path.lastIndexOf(47);
        if (pos < 0) {
            return "";
        }
        return path.substring(0, pos);
    }

    public MatchBlock[] parseMatchBlocks(String propMatchBlocks) {
        if (propMatchBlocks == null) {
            return null;
        }
        ArrayList<MatchBlock> list = new ArrayList<MatchBlock>();
        String[] blockStrs = Config.tokenize(propMatchBlocks, " ");
        for (int i = 0; i < blockStrs.length; ++i) {
            String blockStr = blockStrs[i];
            MatchBlock[] mbs = this.parseMatchBlock(blockStr);
            if (mbs == null) continue;
            list.addAll(Arrays.asList(mbs));
        }
        MatchBlock[] mbs = list.toArray(new MatchBlock[list.size()]);
        return mbs;
    }

    public awt parseBlockState(String str, awt def) {
        MatchBlock[] mbs = this.parseMatchBlock(str);
        if (mbs == null) {
            return def;
        }
        if (mbs.length != 1) {
            return def;
        }
        MatchBlock mb = mbs[0];
        int blockId = mb.getBlockId();
        aow block = aow.c((int)blockId);
        return block.t();
    }

    public MatchBlock[] parseMatchBlock(String blockStr) {
        if (blockStr == null) {
            return null;
        }
        if ((blockStr = blockStr.trim()).length() <= 0) {
            return null;
        }
        String[] parts = Config.tokenize(blockStr, ":");
        String domain = "minecraft";
        int blockIndex = 0;
        if (parts.length > 1 && this.isFullBlockName(parts)) {
            domain = parts[0];
            blockIndex = 1;
        } else {
            domain = "minecraft";
            blockIndex = 0;
        }
        String blockPart = parts[blockIndex];
        String[] params = Arrays.copyOfRange(parts, blockIndex + 1, parts.length);
        aow[] blocks = this.parseBlockPart(domain, blockPart);
        if (blocks == null) {
            return null;
        }
        MatchBlock[] datas = new MatchBlock[blocks.length];
        for (int i = 0; i < blocks.length; ++i) {
            MatchBlock bd;
            aow block = blocks[i];
            int blockId = aow.a((aow)block);
            int[] metadatas = null;
            if (params.length > 0 && (metadatas = this.parseBlockMetadatas(block, params)) == null) {
                return null;
            }
            datas[i] = bd = new MatchBlock(blockId, metadatas);
        }
        return datas;
    }

    public boolean isFullBlockName(String[] parts) {
        if (parts.length < 2) {
            return false;
        }
        String part1 = parts[1];
        if (part1.length() < 1) {
            return false;
        }
        if (this.startsWithDigit(part1)) {
            return false;
        }
        return !part1.contains("=");
    }

    public boolean startsWithDigit(String str) {
        if (str == null) {
            return false;
        }
        if (str.length() < 1) {
            return false;
        }
        char ch = str.charAt(0);
        return Character.isDigit(ch);
    }

    public aow[] parseBlockPart(String domain, String blockPart) {
        if (this.startsWithDigit(blockPart)) {
            int[] ids = this.parseIntList(blockPart);
            if (ids == null) {
                return null;
            }
            aow[] blocks = new aow[ids.length];
            for (int i = 0; i < ids.length; ++i) {
                int id = ids[i];
                aow block = aow.c((int)id);
                if (block == null) {
                    this.warn("Block not found for id: " + id);
                    return null;
                }
                blocks[i] = block;
            }
            return blocks;
        }
        String fullName = domain + ":" + blockPart;
        aow block = aow.b((String)fullName);
        if (block == null) {
            this.warn("Block not found for name: " + fullName);
            return null;
        }
        aow[] blocks = new aow[]{block};
        return blocks;
    }

    public int[] parseBlockMetadatas(aow block, String[] params) {
        if (params.length <= 0) {
            return null;
        }
        String param0 = params[0];
        if (this.startsWithDigit(param0)) {
            int[] mds = this.parseIntList(param0);
            return mds;
        }
        awt stateDefault = block.t();
        Collection properties = stateDefault.s();
        HashMap<axj, List<Comparable>> mapPropValues = new HashMap<axj, List<Comparable>>();
        for (int i = 0; i < params.length; ++i) {
            String param = params[i];
            if (param.length() <= 0) continue;
            String[] parts = Config.tokenize(param, "=");
            if (parts.length != 2) {
                this.warn("Invalid block property: " + param);
                return null;
            }
            String key = parts[0];
            String valStr = parts[1];
            axj prop = ConnectedProperties.getProperty(key, properties);
            if (prop == null) {
                this.warn("Property not found: " + key + ", block: " + block);
                return null;
            }
            ArrayList<Comparable> list = (ArrayList<Comparable>)mapPropValues.get(key);
            if (list == null) {
                list = new ArrayList<Comparable>();
                mapPropValues.put(prop, list);
            }
            String[] vals = Config.tokenize(valStr, ",");
            for (int v = 0; v < vals.length; ++v) {
                String val = vals[v];
                Comparable propVal = ConnectedParser.parsePropertyValue(prop, val);
                if (propVal == null) {
                    this.warn("Property value not found: " + val + ", property: " + key + ", block: " + block);
                    return null;
                }
                list.add(propVal);
            }
        }
        if (mapPropValues.isEmpty()) {
            return null;
        }
        ArrayList<Integer> listMetadatas = new ArrayList<Integer>();
        for (int i = 0; i < 16; ++i) {
            int md = i;
            try {
                awt bs = this.getStateFromMeta(block, md);
                if (!this.matchState(bs, mapPropValues)) continue;
                listMetadatas.add(md);
                continue;
            }
            catch (IllegalArgumentException illegalArgumentException) {
                // empty catch block
            }
        }
        if (listMetadatas.size() == 16) {
            return null;
        }
        int[] metadatas = new int[listMetadatas.size()];
        for (int i = 0; i < metadatas.length; ++i) {
            metadatas[i] = (Integer)listMetadatas.get(i);
        }
        return metadatas;
    }

    private awt getStateFromMeta(aow block, int md) {
        try {
            awt bs = block.a(md);
            if (block == aox.cF && md > 7) {
                awt bsLow = block.a(md & 7);
                bs = bs.a((axj)aqb.a, bsLow.c((axj)aqb.a));
            }
            if (block == aox.dk && (md & 8) != 0) {
                bs = bs.a((axj)asl.a, (Comparable)Boolean.valueOf(true));
            }
            return bs;
        }
        catch (IllegalArgumentException e) {
            return block.t();
        }
    }

    public static Comparable parsePropertyValue(axj prop, String valStr) {
        Class valueClass = prop.b();
        Comparable valueObj = ConnectedParser.parseValue(valStr, valueClass);
        if (valueObj == null) {
            Collection propertyValues = prop.c();
            valueObj = ConnectedParser.getPropertyValue(valStr, propertyValues);
        }
        return valueObj;
    }

    public static Comparable getPropertyValue(String value, Collection propertyValues) {
        for (Comparable obj : propertyValues) {
            if (!ConnectedParser.getValueName(obj).equals(value)) continue;
            return obj;
        }
        return null;
    }

    private static Object getValueName(Comparable obj) {
        if (obj instanceof ro) {
            ro iss = (ro)obj;
            return iss.m();
        }
        return obj.toString();
    }

    public static Comparable parseValue(String str, Class cls) {
        if (cls == String.class) {
            return str;
        }
        if (cls == Boolean.class) {
            return Boolean.valueOf(str);
        }
        if (cls == Float.class) {
            return Float.valueOf(str);
        }
        if (cls == Double.class) {
            return Double.valueOf(str);
        }
        if (cls == Integer.class) {
            return Integer.valueOf(str);
        }
        if (cls == Long.class) {
            return Long.valueOf(str);
        }
        return null;
    }

    public boolean matchState(awt bs, Map<axj, List<Comparable>> mapPropValues) {
        Set<axj> keys = mapPropValues.keySet();
        for (axj prop : keys) {
            List<Comparable> vals = mapPropValues.get(prop);
            Comparable bsVal = bs.c(prop);
            if (bsVal == null) {
                return false;
            }
            if (vals.contains(bsVal)) continue;
            return false;
        }
        return true;
    }

    public anh[] parseBiomes(String str) {
        if (str == null) {
            return null;
        }
        str = str.trim();
        boolean negative = false;
        if (str.startsWith("!")) {
            negative = true;
            str = str.substring(1);
        }
        String[] biomeNames = Config.tokenize(str, " ");
        ArrayList list = new ArrayList();
        for (int i = 0; i < biomeNames.length; ++i) {
            String biomeName = biomeNames[i];
            anh biome = this.findBiome(biomeName);
            if (biome == null) {
                this.warn("Biome not found: " + biomeName);
                continue;
            }
            list.add(biome);
        }
        if (negative) {
            ArrayList listAllBiomes = Lists.newArrayList((Iterator)anh.p.iterator());
            listAllBiomes.removeAll(list);
            list = listAllBiomes;
        }
        anh[] biomeArr = list.toArray(new anh[list.size()]);
        return biomeArr;
    }

    public anh findBiome(String biomeName) {
        if ((biomeName = biomeName.toLowerCase()).equals("nether")) {
            return anm.j;
        }
        Set biomeIds = anh.p.c();
        for (nf loc : biomeIds) {
            String name;
            anh biome = (anh)anh.p.c((Object)loc);
            if (biome == null || !(name = biome.l().replace(" ", "").toLowerCase()).equals(biomeName)) continue;
            return biome;
        }
        return null;
    }

    public int parseInt(String str, int defVal) {
        if (str == null) {
            return defVal;
        }
        int num = Config.parseInt(str = str.trim(), -1);
        if (num < 0) {
            this.warn("Invalid number: " + str);
            return defVal;
        }
        return num;
    }

    public int[] parseIntList(String str) {
        if (str == null) {
            return null;
        }
        ArrayList<Integer> list = new ArrayList<Integer>();
        String[] intStrs = Config.tokenize(str, " ,");
        for (int i = 0; i < intStrs.length; ++i) {
            String intStr = intStrs[i];
            if (intStr.contains("-")) {
                String[] subStrs = Config.tokenize(intStr, "-");
                if (subStrs.length != 2) {
                    this.warn("Invalid interval: " + intStr + ", when parsing: " + str);
                    continue;
                }
                int min = Config.parseInt(subStrs[0], -1);
                int max = Config.parseInt(subStrs[1], -1);
                if (min < 0 || max < 0 || min > max) {
                    this.warn("Invalid interval: " + intStr + ", when parsing: " + str);
                    continue;
                }
                for (int n = min; n <= max; ++n) {
                    list.add(n);
                }
                continue;
            }
            int val = Config.parseInt(intStr, -1);
            if (val < 0) {
                this.warn("Invalid number: " + intStr + ", when parsing: " + str);
                continue;
            }
            list.add(val);
        }
        int[] ints = new int[list.size()];
        for (int i = 0; i < ints.length; ++i) {
            ints[i] = (Integer)list.get(i);
        }
        return ints;
    }

    public boolean[] parseFaces(String str, boolean[] defVal) {
        if (str == null) {
            return defVal;
        }
        EnumSet<fa> setFaces = EnumSet.allOf(fa.class);
        String[] faceStrs = Config.tokenize(str, " ,");
        for (int i = 0; i < faceStrs.length; ++i) {
            String faceStr = faceStrs[i];
            if (faceStr.equals("sides")) {
                setFaces.add(fa.c);
                setFaces.add(fa.d);
                setFaces.add(fa.e);
                setFaces.add(fa.f);
                continue;
            }
            if (faceStr.equals("all")) {
                setFaces.addAll(Arrays.asList(fa.n));
                continue;
            }
            fa face = this.parseFace(faceStr);
            if (face == null) continue;
            setFaces.add(face);
        }
        boolean[] faces = new boolean[fa.n.length];
        for (int i = 0; i < faces.length; ++i) {
            faces[i] = setFaces.contains(fa.n[i]);
        }
        return faces;
    }

    public fa parseFace(String str) {
        if ((str = str.toLowerCase()).equals("bottom") || str.equals("down")) {
            return fa.a;
        }
        if (str.equals("top") || str.equals("up")) {
            return fa.b;
        }
        if (str.equals("north")) {
            return fa.c;
        }
        if (str.equals("south")) {
            return fa.d;
        }
        if (str.equals("east")) {
            return fa.f;
        }
        if (str.equals("west")) {
            return fa.e;
        }
        Config.warn("Unknown face: " + str);
        return null;
    }

    public void dbg(String str) {
        Config.dbg("" + this.context + ": " + str);
    }

    public void warn(String str) {
        Config.warn("" + this.context + ": " + str);
    }

    public RangeListInt parseRangeListInt(String str) {
        if (str == null) {
            return null;
        }
        RangeListInt list = new RangeListInt();
        String[] parts = Config.tokenize(str, " ,");
        for (int i = 0; i < parts.length; ++i) {
            String part = parts[i];
            RangeInt ri = this.parseRangeInt(part);
            if (ri == null) {
                return null;
            }
            list.addRange(ri);
        }
        return list;
    }

    private RangeInt parseRangeInt(String str) {
        if (str == null) {
            return null;
        }
        if (str.indexOf(45) >= 0) {
            String[] parts = Config.tokenize(str, "-");
            if (parts.length != 2) {
                this.warn("Invalid range: " + str);
                return null;
            }
            int min = Config.parseInt(parts[0], -1);
            int max = Config.parseInt(parts[1], -1);
            if (min < 0 || max < 0) {
                this.warn("Invalid range: " + str);
                return null;
            }
            return new RangeInt(min, max);
        }
        int val = Config.parseInt(str, -1);
        if (val < 0) {
            this.warn("Invalid integer: " + str);
            return null;
        }
        return new RangeInt(val, val);
    }

    public boolean parseBoolean(String str, boolean defVal) {
        if (str == null) {
            return defVal;
        }
        String strLower = str.toLowerCase().trim();
        if (strLower.equals("true")) {
            return true;
        }
        if (strLower.equals("false")) {
            return false;
        }
        this.warn("Invalid boolean: " + str);
        return defVal;
    }

    public Boolean parseBooleanObject(String str) {
        if (str == null) {
            return null;
        }
        String strLower = str.toLowerCase().trim();
        if (strLower.equals("true")) {
            return Boolean.TRUE;
        }
        if (strLower.equals("false")) {
            return Boolean.FALSE;
        }
        this.warn("Invalid boolean: " + str);
        return null;
    }

    public static int parseColor(String str, int defVal) {
        if (str == null) {
            return defVal;
        }
        str = str.trim();
        try {
            int val = Integer.parseInt(str, 16) & 0xFFFFFF;
            return val;
        }
        catch (NumberFormatException e) {
            return defVal;
        }
    }

    public static int parseColor4(String str, int defVal) {
        if (str == null) {
            return defVal;
        }
        str = str.trim();
        try {
            int val = (int)(Long.parseLong(str, 16) & 0xFFFFFFFFFFFFFFFFL);
            return val;
        }
        catch (NumberFormatException e) {
            return defVal;
        }
    }

    public amm parseBlockRenderLayer(String str, amm def) {
        if (str == null) {
            return def;
        }
        str = str.toLowerCase().trim();
        amm[] layers = amm.values();
        for (int i = 0; i < layers.length; ++i) {
            amm layer = layers[i];
            if (!str.equals(layer.name().toLowerCase())) continue;
            return layer;
        }
        return def;
    }

    public <T> T parseObject(String str, T[] objs, INameGetter nameGetter, String property) {
        if (str == null) {
            return null;
        }
        String strLower = str.toLowerCase().trim();
        for (int i = 0; i < objs.length; ++i) {
            T obj = objs[i];
            String name = nameGetter.getName(obj);
            if (name == null || !name.toLowerCase().equals(strLower)) continue;
            return obj;
        }
        this.warn("Invalid " + property + ": " + str);
        return null;
    }

    public <T> T[] parseObjects(String str, T[] objs, INameGetter nameGetter, String property, T[] errValue) {
        if (str == null) {
            return null;
        }
        str = str.toLowerCase().trim();
        String[] parts = Config.tokenize(str, " ");
        Object[] arr = (Object[])Array.newInstance(objs.getClass().getComponentType(), parts.length);
        for (int i = 0; i < parts.length; ++i) {
            String part = parts[i];
            T obj = this.parseObject(part, objs, nameGetter, property);
            if (obj == null) {
                return errValue;
            }
            arr[i] = obj;
        }
        return arr;
    }

    public Enum parseEnum(String str, Enum[] enums, String property) {
        return this.parseObject(str, enums, NAME_GETTER_ENUM, property);
    }

    public Enum[] parseEnums(String str, Enum[] enums, String property, Enum[] errValue) {
        return this.parseObjects(str, enums, NAME_GETTER_ENUM, property, errValue);
    }

    public ahs[] parseDyeColors(String str, String property, ahs[] errValue) {
        return this.parseObjects(str, ahs.values(), NAME_GETTER_DYE_COLOR, property, errValue);
    }

    public Weather[] parseWeather(String str, String property, Weather[] errValue) {
        return this.parseObjects(str, Weather.values(), NAME_GETTER_ENUM, property, errValue);
    }

    public NbtTagValue parseNbtTagValue(String path, String value) {
        if (path == null || value == null) {
            return null;
        }
        return new NbtTagValue(path, value);
    }

    public VillagerProfession[] parseProfessions(String profStr) {
        if (profStr == null) {
            return null;
        }
        ArrayList<VillagerProfession> list = new ArrayList<VillagerProfession>();
        String[] tokens = Config.tokenize(profStr, " ");
        for (int i = 0; i < tokens.length; ++i) {
            String str = tokens[i];
            VillagerProfession prof = this.parseProfession(str);
            if (prof == null) {
                this.warn("Invalid profession: " + str);
                return PROFESSIONS_INVALID;
            }
            list.add(prof);
        }
        if (list.isEmpty()) {
            return null;
        }
        VillagerProfession[] arr = list.toArray(new VillagerProfession[list.size()]);
        return arr;
    }

    private VillagerProfession parseProfession(String str) {
        int prof;
        String[] parts = Config.tokenize(str = str.toLowerCase(), ":");
        if (parts.length > 2) {
            return null;
        }
        String profStr = parts[0];
        String carStr = null;
        if (parts.length > 1) {
            carStr = parts[1];
        }
        if ((prof = ConnectedParser.parseProfessionId(profStr)) < 0) {
            return null;
        }
        int[] cars = null;
        if (carStr != null && (cars = ConnectedParser.parseCareerIds(prof, carStr)) == null) {
            return null;
        }
        return new VillagerProfession(prof, cars);
    }

    private static int parseProfessionId(String str) {
        int id = Config.parseInt(str, -1);
        if (id >= 0) {
            return id;
        }
        if (str.equals("farmer")) {
            return 0;
        }
        if (str.equals("librarian")) {
            return 1;
        }
        if (str.equals("priest")) {
            return 2;
        }
        if (str.equals("blacksmith")) {
            return 3;
        }
        if (str.equals("butcher")) {
            return 4;
        }
        if (str.equals("nitwit")) {
            return 5;
        }
        return -1;
    }

    private static int[] parseCareerIds(int prof, String str) {
        IntArraySet set = new IntArraySet();
        String[] parts = Config.tokenize(str, ",");
        for (int i = 0; i < parts.length; ++i) {
            String part = parts[i];
            int id = ConnectedParser.parseCareerId(prof, part);
            if (id < 0) {
                return null;
            }
            set.add(id);
        }
        int[] arr = set.toIntArray();
        return arr;
    }

    private static int parseCareerId(int prof, String str) {
        int id = Config.parseInt(str, -1);
        if (id >= 0) {
            return id;
        }
        if (prof == 0) {
            if (str.equals("farmer")) {
                return 1;
            }
            if (str.equals("fisherman")) {
                return 2;
            }
            if (str.equals("shepherd")) {
                return 3;
            }
            if (str.equals("fletcher")) {
                return 4;
            }
        }
        if (prof == 1) {
            if (str.equals("librarian")) {
                return 1;
            }
            if (str.equals("cartographer")) {
                return 2;
            }
        }
        if (prof == 2 && str.equals("cleric")) {
            return 1;
        }
        if (prof == 3) {
            if (str.equals("armor")) {
                return 1;
            }
            if (str.equals("weapon")) {
                return 2;
            }
            if (str.equals("tool")) {
                return 3;
            }
        }
        if (prof == 4) {
            if (str.equals("butcher")) {
                return 1;
            }
            if (str.equals("leather")) {
                return 2;
            }
        }
        if (prof == 5 && str.equals("nitwit")) {
            return 1;
        }
        return -1;
    }

    public int[] parseItems(String str) {
        str = str.trim();
        TreeSet<Integer> setIds = new TreeSet<Integer>();
        String[] tokens = Config.tokenize(str, " ");
        for (int i = 0; i < tokens.length; ++i) {
            String token = tokens[i];
            nf loc = new nf(token);
            ain item = (ain)ain.g.c((Object)loc);
            if (item == null) {
                this.warn("Item not found: " + token);
                continue;
            }
            int id = ain.a((ain)item);
            if (id < 0) {
                this.warn("Item has no ID: " + item + ", name: " + token);
                continue;
            }
            setIds.add(new Integer(id));
        }
        Integer[] integers = setIds.toArray(new Integer[setIds.size()]);
        int[] ints = Config.toPrimitive(integers);
        return ints;
    }

    public int[] parseEntities(String str) {
        str = str.trim();
        TreeSet<Integer> setIds = new TreeSet<Integer>();
        String[] tokens = Config.tokenize(str, " ");
        for (int i = 0; i < tokens.length; ++i) {
            String token = tokens[i];
            nf loc = new nf(token);
            Class type = (Class)vi.b.c((Object)loc);
            if (type == null) {
                this.warn("Entity not found: " + token);
                continue;
            }
            int id = vi.b.a((Object)type);
            if (id < 0) {
                this.warn("Entity has no ID: " + type + ", name: " + token);
                continue;
            }
            setIds.add(new Integer(id));
        }
        Integer[] integers = setIds.toArray(new Integer[setIds.size()]);
        int[] ints = Config.toPrimitive(integers);
        return ints;
    }
}

