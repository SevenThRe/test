/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  agv
 *  agv$a
 *  ain
 *  air
 *  bus
 *  bvp
 *  bvq
 *  bvr
 *  bvu
 *  bvx
 *  bvy
 *  cdp
 *  cdq
 *  cdr
 *  cds
 *  cfy
 *  cfz
 *  cgb
 *  cgc
 *  cgd
 *  cgf$a
 *  fa
 *  nf
 *  org.lwjgl.opengl.GL11
 *  vl
 */
package net.optifine;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Properties;
import java.util.Set;
import java.util.TreeSet;
import net.optifine.config.IParserInt;
import net.optifine.config.NbtTagValue;
import net.optifine.config.ParserEnchantmentId;
import net.optifine.config.RangeInt;
import net.optifine.config.RangeListInt;
import net.optifine.reflect.Reflector;
import net.optifine.render.Blender;
import net.optifine.util.StrUtils;
import net.optifine.util.TextureUtils;
import org.lwjgl.opengl.GL11;

public class CustomItemProperties {
    public String name = null;
    public String basePath = null;
    public int type = 1;
    public int[] items = null;
    public String texture = null;
    public Map<String, String> mapTextures = null;
    public String model = null;
    public Map<String, String> mapModels = null;
    public RangeListInt damage = null;
    public boolean damagePercent = false;
    public int damageMask = 0;
    public RangeListInt stackSize = null;
    public RangeListInt enchantmentIds = null;
    public RangeListInt enchantmentLevels = null;
    public NbtTagValue[] nbtTagValues = null;
    public int hand = 0;
    public int blend = 1;
    public float speed = 0.0f;
    public float rotation = 0.0f;
    public int layer = 0;
    public float duration = 1.0f;
    public int weight = 0;
    public nf textureLocation = null;
    public Map mapTextureLocations = null;
    public cdq sprite = null;
    public Map mapSprites = null;
    public cfy bakedModelTexture = null;
    public Map<String, cfy> mapBakedModelsTexture = null;
    public cfy bakedModelFull = null;
    public Map<String, cfy> mapBakedModelsFull = null;
    private int textureWidth = 0;
    private int textureHeight = 0;
    public static final int TYPE_UNKNOWN = 0;
    public static final int TYPE_ITEM = 1;
    public static final int TYPE_ENCHANTMENT = 2;
    public static final int TYPE_ARMOR = 3;
    public static final int TYPE_ELYTRA = 4;
    public static final int HAND_ANY = 0;
    public static final int HAND_MAIN = 1;
    public static final int HAND_OFF = 2;
    public static final String INVENTORY = "inventory";

    public CustomItemProperties(Properties props, String path) {
        this.name = CustomItemProperties.parseName(path);
        this.basePath = CustomItemProperties.parseBasePath(path);
        this.type = this.parseType(props.getProperty("type"));
        this.items = this.parseItems(props.getProperty("items"), props.getProperty("matchItems"));
        this.mapModels = CustomItemProperties.parseModels(props, this.basePath);
        this.model = CustomItemProperties.parseModel(props.getProperty("model"), path, this.basePath, this.type, this.mapModels);
        this.mapTextures = CustomItemProperties.parseTextures(props, this.basePath);
        boolean textureFromPath = this.mapModels == null && this.model == null;
        this.texture = CustomItemProperties.parseTexture(props.getProperty("texture"), props.getProperty("tile"), props.getProperty("source"), path, this.basePath, this.type, this.mapTextures, textureFromPath);
        String damageStr = props.getProperty("damage");
        if (damageStr != null) {
            this.damagePercent = damageStr.contains("%");
            damageStr = damageStr.replace("%", "");
            this.damage = this.parseRangeListInt(damageStr);
            this.damageMask = this.parseInt(props.getProperty("damageMask"), 0);
        }
        this.stackSize = this.parseRangeListInt(props.getProperty("stackSize"));
        this.enchantmentIds = this.parseRangeListInt(props.getProperty("enchantmentIDs"), new ParserEnchantmentId());
        this.enchantmentLevels = this.parseRangeListInt(props.getProperty("enchantmentLevels"));
        this.nbtTagValues = this.parseNbtTagValues(props);
        this.hand = this.parseHand(props.getProperty("hand"));
        this.blend = Blender.parseBlend(props.getProperty("blend"));
        this.speed = this.parseFloat(props.getProperty("speed"), 0.0f);
        this.rotation = this.parseFloat(props.getProperty("rotation"), 0.0f);
        this.layer = this.parseInt(props.getProperty("layer"), 0);
        this.weight = this.parseInt(props.getProperty("weight"), 0);
        this.duration = this.parseFloat(props.getProperty("duration"), 1.0f);
    }

    private static String parseName(String path) {
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

    private static String parseBasePath(String path) {
        int pos = path.lastIndexOf(47);
        if (pos < 0) {
            return "";
        }
        return path.substring(0, pos);
    }

    private int parseType(String str) {
        if (str == null) {
            return 1;
        }
        if (str.equals("item")) {
            return 1;
        }
        if (str.equals("enchantment")) {
            return 2;
        }
        if (str.equals("armor")) {
            return 3;
        }
        if (str.equals("elytra")) {
            return 4;
        }
        Config.warn("Unknown method: " + str);
        return 0;
    }

    private int[] parseItems(String str, String str2) {
        if (str == null) {
            str = str2;
        }
        if (str == null) {
            return null;
        }
        str = str.trim();
        TreeSet<Integer> setItemIds = new TreeSet<Integer>();
        String[] tokens = Config.tokenize(str, " ");
        for (int i = 0; i < tokens.length; ++i) {
            ain item;
            String[] parts;
            String token = tokens[i];
            int val = Config.parseInt(token, -1);
            if (val >= 0) {
                setItemIds.add(new Integer(val));
                continue;
            }
            if (token.contains("-") && (parts = Config.tokenize(token, "-")).length == 2) {
                int val1 = Config.parseInt(parts[0], -1);
                int val2 = Config.parseInt(parts[1], -1);
                if (val1 >= 0 && val2 >= 0) {
                    int min = Math.min(val1, val2);
                    int max = Math.max(val1, val2);
                    for (int x = min; x <= max; ++x) {
                        setItemIds.add(new Integer(x));
                    }
                    continue;
                }
            }
            if ((item = ain.b((String)token)) == null) {
                Config.warn("Item not found: " + token);
                continue;
            }
            int id = ain.a((ain)item);
            if (id <= 0) {
                Config.warn("Item not found: " + token);
                continue;
            }
            setItemIds.add(new Integer(id));
        }
        Integer[] integers = setItemIds.toArray(new Integer[setItemIds.size()]);
        int[] ints = new int[integers.length];
        for (int i = 0; i < ints.length; ++i) {
            ints[i] = integers[i];
        }
        return ints;
    }

    private static String parseTexture(String texStr, String texStr2, String texStr3, String path, String basePath, int type, Map<String, String> mapTexs, boolean textureFromPath) {
        int pos2;
        String bowStandbyTex;
        if (texStr == null) {
            texStr = texStr2;
        }
        if (texStr == null) {
            texStr = texStr3;
        }
        if (texStr != null) {
            String png = ".png";
            if (texStr.endsWith(png)) {
                texStr = texStr.substring(0, texStr.length() - png.length());
            }
            texStr = CustomItemProperties.fixTextureName(texStr, basePath);
            return texStr;
        }
        if (type == 3) {
            return null;
        }
        if (mapTexs != null && (bowStandbyTex = mapTexs.get("texture.bow_standby")) != null) {
            return bowStandbyTex;
        }
        if (!textureFromPath) {
            return null;
        }
        String str = path;
        int pos = str.lastIndexOf(47);
        if (pos >= 0) {
            str = str.substring(pos + 1);
        }
        if ((pos2 = str.lastIndexOf(46)) >= 0) {
            str = str.substring(0, pos2);
        }
        str = CustomItemProperties.fixTextureName(str, basePath);
        return str;
    }

    private static Map parseTextures(Properties props, String basePath) {
        String prefix = "texture.";
        Map mapProps = CustomItemProperties.getMatchingProperties(props, prefix);
        if (mapProps.size() <= 0) {
            return null;
        }
        Set keySet = mapProps.keySet();
        LinkedHashMap<String, String> mapTex = new LinkedHashMap<String, String>();
        for (String key : keySet) {
            String val = (String)mapProps.get(key);
            val = CustomItemProperties.fixTextureName(val, basePath);
            mapTex.put(key, val);
        }
        return mapTex;
    }

    private static String fixTextureName(String iconName, String basePath) {
        if (!((iconName = TextureUtils.fixResourcePath(iconName, basePath)).startsWith(basePath) || iconName.startsWith("textures/") || iconName.startsWith("mcpatcher/"))) {
            iconName = basePath + "/" + iconName;
        }
        if (iconName.endsWith(".png")) {
            iconName = iconName.substring(0, iconName.length() - 4);
        }
        if (iconName.startsWith("/")) {
            iconName = iconName.substring(1);
        }
        return iconName;
    }

    private static String parseModel(String modelStr, String path, String basePath, int type, Map<String, String> mapModelNames) {
        String bowStandbyModel;
        if (modelStr != null) {
            String json = ".json";
            if (modelStr.endsWith(json)) {
                modelStr = modelStr.substring(0, modelStr.length() - json.length());
            }
            modelStr = CustomItemProperties.fixModelName(modelStr, basePath);
            return modelStr;
        }
        if (type == 3) {
            return null;
        }
        if (mapModelNames != null && (bowStandbyModel = mapModelNames.get("model.bow_standby")) != null) {
            return bowStandbyModel;
        }
        return modelStr;
    }

    private static Map parseModels(Properties props, String basePath) {
        String prefix = "model.";
        Map mapProps = CustomItemProperties.getMatchingProperties(props, prefix);
        if (mapProps.size() <= 0) {
            return null;
        }
        Set keySet = mapProps.keySet();
        LinkedHashMap<String, String> mapTex = new LinkedHashMap<String, String>();
        for (String key : keySet) {
            String val = (String)mapProps.get(key);
            val = CustomItemProperties.fixModelName(val, basePath);
            mapTex.put(key, val);
        }
        return mapTex;
    }

    private static String fixModelName(String modelName, String basePath) {
        String json;
        boolean isVanilla;
        boolean bl = isVanilla = (modelName = TextureUtils.fixResourcePath(modelName, basePath)).startsWith("block/") || modelName.startsWith("item/");
        if (!(modelName.startsWith(basePath) || isVanilla || modelName.startsWith("mcpatcher/"))) {
            modelName = basePath + "/" + modelName;
        }
        if (modelName.endsWith(json = ".json")) {
            modelName = modelName.substring(0, modelName.length() - json.length());
        }
        if (modelName.startsWith("/")) {
            modelName = modelName.substring(1);
        }
        return modelName;
    }

    private int parseInt(String str, int defVal) {
        if (str == null) {
            return defVal;
        }
        int val = Config.parseInt(str = str.trim(), Integer.MIN_VALUE);
        if (val == Integer.MIN_VALUE) {
            Config.warn("Invalid integer: " + str);
            return defVal;
        }
        return val;
    }

    private float parseFloat(String str, float defVal) {
        if (str == null) {
            return defVal;
        }
        float val = Config.parseFloat(str = str.trim(), Float.MIN_VALUE);
        if (val == Float.MIN_VALUE) {
            Config.warn("Invalid float: " + str);
            return defVal;
        }
        return val;
    }

    private RangeListInt parseRangeListInt(String str) {
        return this.parseRangeListInt(str, null);
    }

    private RangeListInt parseRangeListInt(String str, IParserInt parser) {
        if (str == null) {
            return null;
        }
        String[] tokens = Config.tokenize(str, " ");
        RangeListInt rangeList = new RangeListInt();
        for (int i = 0; i < tokens.length; ++i) {
            int val;
            String token = tokens[i];
            if (parser != null && (val = parser.parse(token, Integer.MIN_VALUE)) != Integer.MIN_VALUE) {
                rangeList.addRange(new RangeInt(val, val));
                continue;
            }
            RangeInt range = this.parseRangeInt(token);
            if (range == null) {
                Config.warn("Invalid range list: " + str);
                return null;
            }
            rangeList.addRange(range);
        }
        return rangeList;
    }

    private RangeInt parseRangeInt(String str) {
        if (str == null) {
            return null;
        }
        int countMinus = (str = str.trim()).length() - str.replace("-", "").length();
        if (countMinus > 1) {
            Config.warn("Invalid range: " + str);
            return null;
        }
        String[] tokens = Config.tokenize(str, "- ");
        int[] vals = new int[tokens.length];
        for (int i = 0; i < tokens.length; ++i) {
            String token = tokens[i];
            int val = Config.parseInt(token, -1);
            if (val < 0) {
                Config.warn("Invalid range: " + str);
                return null;
            }
            vals[i] = val;
        }
        if (vals.length == 1) {
            int val = vals[0];
            if (str.startsWith("-")) {
                return new RangeInt(0, val);
            }
            if (str.endsWith("-")) {
                return new RangeInt(val, 65535);
            }
            return new RangeInt(val, val);
        }
        if (vals.length == 2) {
            int min = Math.min(vals[0], vals[1]);
            int max = Math.max(vals[0], vals[1]);
            return new RangeInt(min, max);
        }
        Config.warn("Invalid range: " + str);
        return null;
    }

    private NbtTagValue[] parseNbtTagValues(Properties props) {
        String PREFIX_NBT = "nbt.";
        Map mapNbt = CustomItemProperties.getMatchingProperties(props, PREFIX_NBT);
        if (mapNbt.size() <= 0) {
            return null;
        }
        ArrayList<NbtTagValue> listNbts = new ArrayList<NbtTagValue>();
        Set keySet = mapNbt.keySet();
        for (String key : keySet) {
            String val = (String)mapNbt.get(key);
            String id = key.substring(PREFIX_NBT.length());
            NbtTagValue nbt = new NbtTagValue(id, val);
            listNbts.add(nbt);
        }
        NbtTagValue[] nbts = listNbts.toArray(new NbtTagValue[listNbts.size()]);
        return nbts;
    }

    private static Map getMatchingProperties(Properties props, String keyPrefix) {
        LinkedHashMap<String, String> map = new LinkedHashMap<String, String>();
        Set<Object> keySet = props.keySet();
        for (String string : keySet) {
            String val = props.getProperty(string);
            if (!string.startsWith(keyPrefix)) continue;
            map.put(string, val);
        }
        return map;
    }

    private int parseHand(String str) {
        if (str == null) {
            return 0;
        }
        if ((str = str.toLowerCase()).equals("any")) {
            return 0;
        }
        if (str.equals("main")) {
            return 1;
        }
        if (str.equals("off")) {
            return 2;
        }
        Config.warn("Invalid hand: " + str);
        return 0;
    }

    public boolean isValid(String path) {
        if (this.name == null || this.name.length() <= 0) {
            Config.warn("No name found: " + path);
            return false;
        }
        if (this.basePath == null) {
            Config.warn("No base path found: " + path);
            return false;
        }
        if (this.type == 0) {
            Config.warn("No type defined: " + path);
            return false;
        }
        if (this.type == 4 && this.items == null) {
            this.items = new int[]{ain.a((ain)air.cS)};
        }
        if (this.type == 1 || this.type == 3 || this.type == 4) {
            if (this.items == null) {
                this.items = this.detectItems();
            }
            if (this.items == null) {
                Config.warn("No items defined: " + path);
                return false;
            }
        }
        if (this.texture == null && this.mapTextures == null && this.model == null && this.mapModels == null) {
            Config.warn("No texture or model specified: " + path);
            return false;
        }
        if (this.type == 2 && this.enchantmentIds == null) {
            Config.warn("No enchantmentIDs specified: " + path);
            return false;
        }
        return true;
    }

    private int[] detectItems() {
        ain item = ain.b((String)this.name);
        if (item == null) {
            return null;
        }
        int id = ain.a((ain)item);
        if (id <= 0) {
            return null;
        }
        return new int[]{id};
    }

    public void updateIcons(cdp textureMap) {
        if (this.texture != null) {
            this.textureLocation = this.getTextureLocation(this.texture);
            if (this.type == 1) {
                nf spriteLocation = this.getSpriteLocation(this.textureLocation);
                this.sprite = textureMap.a(spriteLocation);
            }
        }
        if (this.mapTextures != null) {
            this.mapTextureLocations = new HashMap();
            this.mapSprites = new HashMap();
            Set<String> keySet = this.mapTextures.keySet();
            for (String key : keySet) {
                String val = this.mapTextures.get(key);
                nf locTex = this.getTextureLocation(val);
                this.mapTextureLocations.put(key, locTex);
                if (this.type != 1) continue;
                nf locSprite = this.getSpriteLocation(locTex);
                cdq icon = textureMap.a(locSprite);
                this.mapSprites.put(key, icon);
            }
        }
    }

    private nf getTextureLocation(String texName) {
        String filePath;
        nf locFile;
        boolean exists;
        if (texName == null) {
            return null;
        }
        nf resLoc = new nf(texName);
        String domain = resLoc.b();
        String path = resLoc.a();
        if (!path.contains("/")) {
            path = "textures/items/" + path;
        }
        if (!(exists = Config.hasResource(locFile = new nf(domain, filePath = path + ".png")))) {
            Config.warn("File not found: " + filePath);
        }
        return locFile;
    }

    private nf getSpriteLocation(nf resLoc) {
        String pathTex = resLoc.a();
        pathTex = StrUtils.removePrefix(pathTex, "textures/");
        pathTex = StrUtils.removeSuffix(pathTex, ".png");
        nf locTex = new nf(resLoc.b(), pathTex);
        return locTex;
    }

    public void updateModelTexture(cdp textureMap, bvy itemModelGenerator) {
        if (this.texture == null && this.mapTextures == null) {
            return;
        }
        String[] textures = this.getModelTextures();
        boolean useTint = this.isUseTint();
        this.bakedModelTexture = CustomItemProperties.makeBakedModel(textureMap, itemModelGenerator, textures, useTint);
        if (this.type == 1 && this.mapTextures != null) {
            Set<String> keySet = this.mapTextures.keySet();
            for (String key : keySet) {
                String tex = this.mapTextures.get(key);
                String path = StrUtils.removePrefix(key, "texture.");
                if (!path.startsWith("bow") && !path.startsWith("fishing_rod") && !path.startsWith("shield")) continue;
                String[] texNames = new String[]{tex};
                cfy modelTex = CustomItemProperties.makeBakedModel(textureMap, itemModelGenerator, texNames, useTint);
                if (this.mapBakedModelsTexture == null) {
                    this.mapBakedModelsTexture = new HashMap<String, cfy>();
                }
                String location = "item/" + path;
                this.mapBakedModelsTexture.put(location, modelTex);
            }
        }
    }

    private boolean isUseTint() {
        return true;
    }

    private static cfy makeBakedModel(cdp textureMap, bvy itemModelGenerator, String[] textures, boolean useTint) {
        String[] spriteNames = new String[textures.length];
        for (int i = 0; i < spriteNames.length; ++i) {
            String texture = textures[i];
            spriteNames[i] = StrUtils.removePrefix(texture, "textures/");
        }
        bvu modelBlockBase = CustomItemProperties.makeModelBlock(spriteNames);
        bvu modelBlock = itemModelGenerator.a(textureMap, modelBlockBase);
        cfy model = CustomItemProperties.bakeModel(textureMap, modelBlock, useTint);
        return model;
    }

    private String[] getModelTextures() {
        if (this.type == 1 && this.items.length == 1) {
            agv itemArmor;
            boolean isPotionItem;
            ain item = ain.c((int)this.items[0]);
            boolean bl = isPotionItem = item == air.bH || item == air.bI || item == air.bJ;
            if (isPotionItem && this.damage != null && this.damage.getCountRanges() > 0) {
                RangeInt range = this.damage.getRange(0);
                int valDamage = range.getMin();
                boolean splash = (valDamage & 0x4000) != 0;
                String texOverlay = this.getMapTexture(this.mapTextures, "texture.potion_overlay", "items/potion_overlay");
                String texMain = null;
                texMain = splash ? this.getMapTexture(this.mapTextures, "texture.potion_bottle_splash", "items/potion_bottle_splash") : this.getMapTexture(this.mapTextures, "texture.potion_bottle_drinkable", "items/potion_bottle_drinkable");
                return new String[]{texOverlay, texMain};
            }
            if (item instanceof agv && (itemArmor = (agv)item).d() == agv.a.a) {
                String material = "leather";
                String type = "helmet";
                if (itemArmor.c == vl.f) {
                    type = "helmet";
                }
                if (itemArmor.c == vl.e) {
                    type = "chestplate";
                }
                if (itemArmor.c == vl.d) {
                    type = "leggings";
                }
                if (itemArmor.c == vl.c) {
                    type = "boots";
                }
                String key = material + "_" + type;
                String texMain = this.getMapTexture(this.mapTextures, "texture." + key, "items/" + key);
                String texOverlay = this.getMapTexture(this.mapTextures, "texture." + key + "_overlay", "items/" + key + "_overlay");
                return new String[]{texMain, texOverlay};
            }
        }
        return new String[]{this.texture};
    }

    private String getMapTexture(Map<String, String> map, String key, String def) {
        if (map == null) {
            return def;
        }
        String str = map.get(key);
        if (str == null) {
            return def;
        }
        return str;
    }

    private static bvu makeModelBlock(String[] modelTextures) {
        StringBuffer sb = new StringBuffer();
        sb.append("{\"parent\": \"builtin/generated\",\"textures\": {");
        for (int i = 0; i < modelTextures.length; ++i) {
            String modelTexture = modelTextures[i];
            if (i > 0) {
                sb.append(", ");
            }
            sb.append("\"layer" + i + "\": \"" + modelTexture + "\"");
        }
        sb.append("}}");
        String modelStr = sb.toString();
        bvu model = bvu.a((String)modelStr);
        return model;
    }

    private static cfy bakeModel(cdp textureMap, bvu modelBlockIn, boolean useTint) {
        cfz modelRotationIn = cfz.a;
        boolean uvLocked = false;
        String spriteParticleName = modelBlockIn.c("particle");
        cdq var4 = textureMap.a(new nf(spriteParticleName).toString());
        cgf.a var5 = new cgf.a(modelBlockIn, modelBlockIn.g()).a(var4);
        for (bvq var7 : modelBlockIn.a()) {
            for (fa var9 : var7.c.keySet()) {
                bvr var10 = (bvr)var7.c.get(var9);
                if (!useTint) {
                    var10 = new bvr(var10.b, -1, var10.d, var10.e);
                }
                String spriteName = modelBlockIn.c(var10.d);
                cdq var11 = textureMap.a(new nf(spriteName).toString());
                bvp quad = CustomItemProperties.makeBakedQuad(var7, var10, var11, var9, modelRotationIn, uvLocked);
                if (var10.b == null) {
                    var5.a(quad);
                    continue;
                }
                var5.a(modelRotationIn.a(var10.b), quad);
            }
        }
        return var5.b();
    }

    private static bvp makeBakedQuad(bvq blockPart, bvr blockPartFace, cdq textureAtlasSprite, fa enumFacing, cfz modelRotation, boolean uvLocked) {
        bvx faceBakery = new bvx();
        return faceBakery.a(blockPart.a, blockPart.b, blockPartFace, textureAtlasSprite, enumFacing, modelRotation, blockPart.d, uvLocked, blockPart.e);
    }

    public String toString() {
        return "" + this.basePath + "/" + this.name + ", type: " + this.type + ", items: [" + Config.arrayToString(this.items) + "], textture: " + this.texture;
    }

    public float getTextureWidth(cdr textureManager) {
        if (this.textureWidth <= 0) {
            if (this.textureLocation != null) {
                cds tex = textureManager.b(this.textureLocation);
                int texId = tex.b();
                int prevTexId = bus.getBoundTexture();
                bus.i((int)texId);
                this.textureWidth = GL11.glGetTexLevelParameteri((int)3553, (int)0, (int)4096);
                bus.i((int)prevTexId);
            }
            if (this.textureWidth <= 0) {
                this.textureWidth = 16;
            }
        }
        return this.textureWidth;
    }

    public float getTextureHeight(cdr textureManager) {
        if (this.textureHeight <= 0) {
            if (this.textureLocation != null) {
                cds tex = textureManager.b(this.textureLocation);
                int texId = tex.b();
                int prevTexId = bus.getBoundTexture();
                bus.i((int)texId);
                this.textureHeight = GL11.glGetTexLevelParameteri((int)3553, (int)0, (int)4097);
                bus.i((int)prevTexId);
            }
            if (this.textureHeight <= 0) {
                this.textureHeight = 16;
            }
        }
        return this.textureHeight;
    }

    public cfy getBakedModel(nf modelLocation, boolean fullModel) {
        String modelPath;
        cfy customModel;
        Map<String, cfy> mapBakedModels;
        cfy bakedModel;
        if (fullModel) {
            bakedModel = this.bakedModelFull;
            mapBakedModels = this.mapBakedModelsFull;
        } else {
            bakedModel = this.bakedModelTexture;
            mapBakedModels = this.mapBakedModelsTexture;
        }
        if (modelLocation != null && mapBakedModels != null && (customModel = mapBakedModels.get(modelPath = modelLocation.a())) != null) {
            return customModel;
        }
        return bakedModel;
    }

    public void loadModels(cgb modelBakery) {
        if (this.model != null) {
            CustomItemProperties.loadItemModel(modelBakery, this.model);
        }
        if (this.type == 1 && this.mapModels != null) {
            Set<String> keySet = this.mapModels.keySet();
            for (String key : keySet) {
                String mod = this.mapModels.get(key);
                String path = StrUtils.removePrefix(key, "model.");
                if (!path.startsWith("bow") && !path.startsWith("fishing_rod") && !path.startsWith("shield")) continue;
                CustomItemProperties.loadItemModel(modelBakery, mod);
            }
        }
    }

    public void updateModelsFull() {
        cgc modelManager = Config.getModelManager();
        cfy missingModel = modelManager.a();
        if (this.model != null) {
            nf locItem = CustomItemProperties.getModelLocation(this.model);
            cgd mrl = new cgd(locItem, INVENTORY);
            this.bakedModelFull = modelManager.a(mrl);
            if (this.bakedModelFull == missingModel) {
                Config.warn("Custom Items: Model not found " + mrl.a());
                this.bakedModelFull = null;
            }
        }
        if (this.type == 1 && this.mapModels != null) {
            Set<String> keySet = this.mapModels.keySet();
            for (String key : keySet) {
                String mod = this.mapModels.get(key);
                String path = StrUtils.removePrefix(key, "model.");
                if (!path.startsWith("bow") && !path.startsWith("fishing_rod") && !path.startsWith("shield")) continue;
                nf locItem = CustomItemProperties.getModelLocation(mod);
                cgd mrl = new cgd(locItem, INVENTORY);
                cfy bm = modelManager.a(mrl);
                if (bm == missingModel) {
                    Config.warn("Custom Items: Model not found " + mrl.a());
                    continue;
                }
                if (this.mapBakedModelsFull == null) {
                    this.mapBakedModelsFull = new HashMap<String, cfy>();
                }
                String location = "item/" + path;
                this.mapBakedModelsFull.put(location, bm);
            }
        }
    }

    private static void loadItemModel(cgb modelBakery, String model) {
        nf locItem = CustomItemProperties.getModelLocation(model);
        cgd mrl = new cgd(locItem, INVENTORY);
        if (Reflector.ModelLoader.exists()) {
            try {
                Object vanillaLoader = Reflector.ModelLoader_VanillaLoader_INSTANCE.getValue();
                CustomItemProperties.checkNull(vanillaLoader, "vanillaLoader is null");
                Object iModel = Reflector.call(vanillaLoader, Reflector.ModelLoader_VanillaLoader_loadModel, mrl);
                CustomItemProperties.checkNull(iModel, "iModel is null");
                Map stateModels = (Map)Reflector.getFieldValue(modelBakery, Reflector.ModelLoader_stateModels);
                CustomItemProperties.checkNull(stateModels, "stateModels is null");
                stateModels.put(mrl, iModel);
                Set registryTextures = (Set)Reflector.ModelLoaderRegistry_textures.getValue();
                CustomItemProperties.checkNull(registryTextures, "registryTextures is null");
                Collection modelTextures = (Collection)Reflector.call(iModel, Reflector.IModel_getTextures, new Object[0]);
                CustomItemProperties.checkNull(modelTextures, "modelTextures is null");
                registryTextures.addAll(modelTextures);
            }
            catch (Exception e) {
                Config.warn("Error registering model: " + mrl + ", " + e.getClass().getName() + ": " + e.getMessage());
            }
            return;
        }
        modelBakery.a(locItem.toString(), (nf)mrl, locItem);
    }

    private static void checkNull(Object obj, String msg) throws NullPointerException {
        if (obj == null) {
            throw new NullPointerException(msg);
        }
    }

    private static nf getModelLocation(String modelName) {
        if (Reflector.ModelLoader.exists() && !modelName.startsWith("mcpatcher/") && !modelName.startsWith("optifine/")) {
            return new nf("models/" + modelName);
        }
        return new nf(modelName);
    }
}

