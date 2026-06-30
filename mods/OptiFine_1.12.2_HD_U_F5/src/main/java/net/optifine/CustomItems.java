/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  agv
 *  ahy
 *  ain
 *  aip
 *  air
 *  ajd
 *  bib
 *  bqf
 *  bus
 *  bvy
 *  bzw
 *  cdp
 *  cdr
 *  cer
 *  cfy
 *  cgb
 *  fy
 *  ge
 *  nf
 *  uz
 *  vg
 *  vl
 *  vp
 */
package net.optifine;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;
import net.optifine.CustomItemProperties;
import net.optifine.CustomItemsComparator;
import net.optifine.config.NbtTagValue;
import net.optifine.render.Blender;
import net.optifine.shaders.Shaders;
import net.optifine.shaders.ShadersRender;
import net.optifine.util.PropertiesOrdered;
import net.optifine.util.ResUtils;
import net.optifine.util.StrUtils;

public class CustomItems {
    private static CustomItemProperties[][] itemProperties = null;
    private static CustomItemProperties[][] enchantmentProperties = null;
    private static Map mapPotionIds = null;
    private static bvy itemModelGenerator = new bvy();
    private static boolean useGlint = true;
    private static boolean renderOffHand = false;
    public static final int MASK_POTION_SPLASH = 16384;
    public static final int MASK_POTION_NAME = 63;
    public static final int MASK_POTION_EXTENDED = 64;
    public static final String KEY_TEXTURE_OVERLAY = "texture.potion_overlay";
    public static final String KEY_TEXTURE_SPLASH = "texture.potion_bottle_splash";
    public static final String KEY_TEXTURE_DRINKABLE = "texture.potion_bottle_drinkable";
    public static final String DEFAULT_TEXTURE_OVERLAY = "items/potion_overlay";
    public static final String DEFAULT_TEXTURE_SPLASH = "items/potion_bottle_splash";
    public static final String DEFAULT_TEXTURE_DRINKABLE = "items/potion_bottle_drinkable";
    private static final int[][] EMPTY_INT2_ARRAY = new int[0][];
    private static final Map<String, Integer> mapPotionDamages = CustomItems.makeMapPotionDamages();
    private static final String TYPE_POTION_NORMAL = "normal";
    private static final String TYPE_POTION_SPLASH = "splash";
    private static final String TYPE_POTION_LINGER = "linger";

    public static void update() {
        itemProperties = null;
        enchantmentProperties = null;
        useGlint = true;
        if (!Config.isCustomItems()) {
            return;
        }
        CustomItems.readCitProperties("mcpatcher/cit.properties");
        cer[] rps = Config.getResourcePacks();
        for (int i = rps.length - 1; i >= 0; --i) {
            cer rp = rps[i];
            CustomItems.update(rp);
        }
        CustomItems.update((cer)Config.getDefaultResourcePack());
        if (itemProperties.length <= 0) {
            itemProperties = null;
        }
        if (enchantmentProperties.length <= 0) {
            enchantmentProperties = null;
        }
    }

    private static void readCitProperties(String fileName) {
        try {
            nf loc = new nf(fileName);
            InputStream in = Config.getResourceStream(loc);
            if (in == null) {
                return;
            }
            Config.dbg("CustomItems: Loading " + fileName);
            PropertiesOrdered props = new PropertiesOrdered();
            props.load(in);
            in.close();
            useGlint = Config.parseBoolean(props.getProperty("useGlint"), true);
        }
        catch (FileNotFoundException e) {
            return;
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void update(cer rp) {
        CustomItemProperties[] cips;
        int i;
        Object[] names = ResUtils.collectFiles(rp, "mcpatcher/cit/", ".properties", null);
        Map mapAutoProperties = CustomItems.makeAutoImageProperties(rp);
        if (mapAutoProperties.size() > 0) {
            Set keySetAuto = mapAutoProperties.keySet();
            Object[] keysAuto = keySetAuto.toArray(new String[keySetAuto.size()]);
            names = (String[])Config.addObjectsToArray(names, keysAuto);
        }
        Arrays.sort(names);
        List itemList = CustomItems.makePropertyList(itemProperties);
        List enchantmentList = CustomItems.makePropertyList(enchantmentProperties);
        for (int i2 = 0; i2 < names.length; ++i2) {
            Object name = names[i2];
            Config.dbg("CustomItems: " + (String)name);
            try {
                CustomItemProperties cip = null;
                if (mapAutoProperties.containsKey(name)) {
                    cip = (CustomItemProperties)mapAutoProperties.get(name);
                }
                if (cip == null) {
                    nf locFile = new nf((String)name);
                    InputStream in = rp.a(locFile);
                    if (in == null) {
                        Config.warn("CustomItems file not found: " + (String)name);
                        continue;
                    }
                    PropertiesOrdered props = new PropertiesOrdered();
                    props.load(in);
                    cip = new CustomItemProperties(props, (String)name);
                }
                if (!cip.isValid((String)name)) continue;
                CustomItems.addToItemList(cip, itemList);
                CustomItems.addToEnchantmentList(cip, enchantmentList);
                continue;
            }
            catch (FileNotFoundException e) {
                Config.warn("CustomItems file not found: " + (String)name);
                continue;
            }
            catch (Exception e) {
                e.printStackTrace();
            }
        }
        itemProperties = CustomItems.propertyListToArray(itemList);
        enchantmentProperties = CustomItems.propertyListToArray(enchantmentList);
        Comparator comp = CustomItems.getPropertiesComparator();
        for (i = 0; i < itemProperties.length; ++i) {
            cips = itemProperties[i];
            if (cips == null) continue;
            Arrays.sort(cips, comp);
        }
        for (i = 0; i < enchantmentProperties.length; ++i) {
            cips = enchantmentProperties[i];
            if (cips == null) continue;
            Arrays.sort(cips, comp);
        }
    }

    private static Comparator getPropertiesComparator() {
        Comparator comp = new Comparator(){

            public int compare(Object o1, Object o2) {
                CustomItemProperties cip1 = (CustomItemProperties)o1;
                CustomItemProperties cip2 = (CustomItemProperties)o2;
                if (cip1.layer != cip2.layer) {
                    return cip1.layer - cip2.layer;
                }
                if (cip1.weight != cip2.weight) {
                    return cip2.weight - cip1.weight;
                }
                if (!cip1.basePath.equals(cip2.basePath)) {
                    return cip1.basePath.compareTo(cip2.basePath);
                }
                return cip1.name.compareTo(cip2.name);
            }
        };
        return comp;
    }

    public static void updateIcons(cdp textureMap) {
        List<CustomItemProperties> cips = CustomItems.getAllProperties();
        for (CustomItemProperties cip : cips) {
            cip.updateIcons(textureMap);
        }
    }

    public static void loadModels(cgb modelBakery) {
        List<CustomItemProperties> cips = CustomItems.getAllProperties();
        for (CustomItemProperties cip : cips) {
            cip.loadModels(modelBakery);
        }
    }

    public static void updateModels() {
        List<CustomItemProperties> cips = CustomItems.getAllProperties();
        for (CustomItemProperties cip : cips) {
            if (cip.type != 1) continue;
            cdp textureMap = bib.z().R();
            cip.updateModelTexture(textureMap, itemModelGenerator);
            cip.updateModelsFull();
        }
    }

    private static List<CustomItemProperties> getAllProperties() {
        ArrayList<CustomItemProperties> list = new ArrayList<CustomItemProperties>();
        CustomItems.addAll(itemProperties, list);
        CustomItems.addAll(enchantmentProperties, list);
        return list;
    }

    private static void addAll(CustomItemProperties[][] cipsArr, List<CustomItemProperties> list) {
        if (cipsArr == null) {
            return;
        }
        for (int i = 0; i < cipsArr.length; ++i) {
            CustomItemProperties[] cips = cipsArr[i];
            if (cips == null) continue;
            for (int k = 0; k < cips.length; ++k) {
                CustomItemProperties cip = cips[k];
                if (cip == null) continue;
                list.add(cip);
            }
        }
    }

    private static Map makeAutoImageProperties(cer rp) {
        HashMap map = new HashMap();
        map.putAll(CustomItems.makePotionImageProperties(rp, TYPE_POTION_NORMAL, ain.a((ain)air.bH)));
        map.putAll(CustomItems.makePotionImageProperties(rp, TYPE_POTION_SPLASH, ain.a((ain)air.bI)));
        map.putAll(CustomItems.makePotionImageProperties(rp, TYPE_POTION_LINGER, ain.a((ain)air.bJ)));
        return map;
    }

    private static Map makePotionImageProperties(cer rp, String type, int itemId) {
        HashMap<String, CustomItemProperties> map = new HashMap<String, CustomItemProperties>();
        String typePrefix = type + "/";
        String[] prefixes = new String[]{"mcpatcher/cit/potion/" + typePrefix, "mcpatcher/cit/Potion/" + typePrefix};
        String[] suffixes = new String[]{".png"};
        String[] names = ResUtils.collectFiles(rp, prefixes, suffixes);
        for (int i = 0; i < names.length; ++i) {
            String path;
            String name = path = names[i];
            Properties props = CustomItems.makePotionProperties(name = StrUtils.removePrefixSuffix(name, prefixes, suffixes), type, itemId, path);
            if (props == null) continue;
            String pathProp = StrUtils.removeSuffix(path, suffixes) + ".properties";
            CustomItemProperties cip = new CustomItemProperties(props, pathProp);
            map.put(pathProp, cip);
        }
        return map;
    }

    private static Properties makePotionProperties(String name, String type, int itemId, String path) {
        if (StrUtils.endsWith(name, new String[]{"_n", "_s"})) {
            return null;
        }
        if (name.equals("empty") && type.equals(TYPE_POTION_NORMAL)) {
            itemId = ain.a((ain)air.bK);
            PropertiesOrdered props = new PropertiesOrdered();
            ((Properties)props).put("type", "item");
            ((Properties)props).put("items", "" + itemId);
            return props;
        }
        int potionItemId = itemId;
        int[] damages = (int[])CustomItems.getMapPotionIds().get(name);
        if (damages == null) {
            Config.warn("Potion not found for image: " + path);
            return null;
        }
        StringBuffer bufDamage = new StringBuffer();
        for (int i = 0; i < damages.length; ++i) {
            int damage = damages[i];
            if (type.equals(TYPE_POTION_SPLASH)) {
                damage |= 0x4000;
            }
            if (i > 0) {
                bufDamage.append(" ");
            }
            bufDamage.append(damage);
        }
        int damageMask = 16447;
        if (name.equals("water") || name.equals("mundane")) {
            damageMask |= 0x40;
        }
        PropertiesOrdered props = new PropertiesOrdered();
        ((Properties)props).put("type", "item");
        ((Properties)props).put("items", "" + potionItemId);
        ((Properties)props).put("damage", "" + bufDamage.toString());
        ((Properties)props).put("damageMask", "" + damageMask);
        if (type.equals(TYPE_POTION_SPLASH)) {
            ((Properties)props).put(KEY_TEXTURE_SPLASH, name);
        } else {
            ((Properties)props).put(KEY_TEXTURE_DRINKABLE, name);
        }
        return props;
    }

    private static Map getMapPotionIds() {
        if (mapPotionIds == null) {
            mapPotionIds = new LinkedHashMap();
            mapPotionIds.put("water", CustomItems.getPotionId(0, 0));
            mapPotionIds.put("awkward", CustomItems.getPotionId(0, 1));
            mapPotionIds.put("thick", CustomItems.getPotionId(0, 2));
            mapPotionIds.put("potent", CustomItems.getPotionId(0, 3));
            mapPotionIds.put("regeneration", CustomItems.getPotionIds(1));
            mapPotionIds.put("movespeed", CustomItems.getPotionIds(2));
            mapPotionIds.put("fireresistance", CustomItems.getPotionIds(3));
            mapPotionIds.put("poison", CustomItems.getPotionIds(4));
            mapPotionIds.put("heal", CustomItems.getPotionIds(5));
            mapPotionIds.put("nightvision", CustomItems.getPotionIds(6));
            mapPotionIds.put("clear", CustomItems.getPotionId(7, 0));
            mapPotionIds.put("bungling", CustomItems.getPotionId(7, 1));
            mapPotionIds.put("charming", CustomItems.getPotionId(7, 2));
            mapPotionIds.put("rank", CustomItems.getPotionId(7, 3));
            mapPotionIds.put("weakness", CustomItems.getPotionIds(8));
            mapPotionIds.put("damageboost", CustomItems.getPotionIds(9));
            mapPotionIds.put("moveslowdown", CustomItems.getPotionIds(10));
            mapPotionIds.put("leaping", CustomItems.getPotionIds(11));
            mapPotionIds.put("harm", CustomItems.getPotionIds(12));
            mapPotionIds.put("waterbreathing", CustomItems.getPotionIds(13));
            mapPotionIds.put("invisibility", CustomItems.getPotionIds(14));
            mapPotionIds.put("thin", CustomItems.getPotionId(15, 0));
            mapPotionIds.put("debonair", CustomItems.getPotionId(15, 1));
            mapPotionIds.put("sparkling", CustomItems.getPotionId(15, 2));
            mapPotionIds.put("stinky", CustomItems.getPotionId(15, 3));
            mapPotionIds.put("mundane", CustomItems.getPotionId(0, 4));
            mapPotionIds.put("speed", mapPotionIds.get("movespeed"));
            mapPotionIds.put("fire_resistance", mapPotionIds.get("fireresistance"));
            mapPotionIds.put("instant_health", mapPotionIds.get("heal"));
            mapPotionIds.put("night_vision", mapPotionIds.get("nightvision"));
            mapPotionIds.put("strength", mapPotionIds.get("damageboost"));
            mapPotionIds.put("slowness", mapPotionIds.get("moveslowdown"));
            mapPotionIds.put("instant_damage", mapPotionIds.get("harm"));
            mapPotionIds.put("water_breathing", mapPotionIds.get("waterbreathing"));
        }
        return mapPotionIds;
    }

    private static int[] getPotionIds(int baseId) {
        return new int[]{baseId, baseId + 16, baseId + 32, baseId + 48};
    }

    private static int[] getPotionId(int baseId, int subId) {
        return new int[]{baseId + subId * 16};
    }

    private static int getPotionNameDamage(String name) {
        String fullName = "effect." + name;
        Set keys = uz.b.c();
        for (nf rl : keys) {
            uz potion = (uz)uz.b.c((Object)rl);
            String potionName = potion.a();
            if (!fullName.equals(potionName)) continue;
            return uz.a((uz)potion);
        }
        return -1;
    }

    private static List makePropertyList(CustomItemProperties[][] propsArr) {
        ArrayList<ArrayList<CustomItemProperties>> list = new ArrayList<ArrayList<CustomItemProperties>>();
        if (propsArr != null) {
            for (int i = 0; i < propsArr.length; ++i) {
                CustomItemProperties[] props = propsArr[i];
                ArrayList<CustomItemProperties> propList = null;
                if (props != null) {
                    propList = new ArrayList<CustomItemProperties>(Arrays.asList(props));
                }
                list.add(propList);
            }
        }
        return list;
    }

    private static CustomItemProperties[][] propertyListToArray(List list) {
        CustomItemProperties[][] propArr = new CustomItemProperties[list.size()][];
        for (int i = 0; i < list.size(); ++i) {
            List subList = (List)list.get(i);
            if (subList == null) continue;
            CustomItemProperties[] subArr = subList.toArray(new CustomItemProperties[subList.size()]);
            Arrays.sort(subArr, new CustomItemsComparator());
            propArr[i] = subArr;
        }
        return propArr;
    }

    private static void addToItemList(CustomItemProperties cp, List itemList) {
        if (cp.items == null) {
            return;
        }
        for (int i = 0; i < cp.items.length; ++i) {
            int itemId = cp.items[i];
            if (itemId <= 0) {
                Config.warn("Invalid item ID: " + itemId);
                continue;
            }
            CustomItems.addToList(cp, itemList, itemId);
        }
    }

    private static void addToEnchantmentList(CustomItemProperties cp, List enchantmentList) {
        if (cp.type != 2) {
            return;
        }
        if (cp.enchantmentIds == null) {
            return;
        }
        for (int i = 0; i < 256; ++i) {
            int id = i;
            if (!cp.enchantmentIds.isInRange(id)) continue;
            CustomItems.addToList(cp, enchantmentList, id);
        }
    }

    private static void addToList(CustomItemProperties cp, List list, int id) {
        while (id >= list.size()) {
            list.add(null);
        }
        ArrayList<CustomItemProperties> subList = (ArrayList<CustomItemProperties>)list.get(id);
        if (subList == null) {
            subList = new ArrayList<CustomItemProperties>();
            list.set(id, subList);
        }
        subList.add(cp);
    }

    public static cfy getCustomItemModel(aip itemStack, cfy model, nf modelLocation, boolean fullModel) {
        if (!fullModel && model.b()) {
            return model;
        }
        if (itemProperties == null) {
            return model;
        }
        CustomItemProperties props = CustomItems.getCustomItemProperties(itemStack, 1);
        if (props == null) {
            return model;
        }
        cfy customModel = props.getBakedModel(modelLocation, fullModel);
        if (customModel != null) {
            return customModel;
        }
        return model;
    }

    public static boolean bindCustomArmorTexture(aip itemStack, vl slot, String overlay) {
        if (itemProperties == null) {
            return false;
        }
        nf loc = CustomItems.getCustomArmorLocation(itemStack, slot, overlay);
        if (loc == null) {
            return false;
        }
        Config.getTextureManager().a(loc);
        return true;
    }

    private static nf getCustomArmorLocation(aip itemStack, vl slot, String overlay) {
        String key;
        nf loc;
        CustomItemProperties props = CustomItems.getCustomItemProperties(itemStack, 3);
        if (props == null) {
            return null;
        }
        if (props.mapTextureLocations == null) {
            return props.textureLocation;
        }
        ain item = itemStack.c();
        if (!(item instanceof agv)) {
            return null;
        }
        agv itemArmor = (agv)item;
        String material = itemArmor.d().d();
        int layer = slot == vl.d ? 2 : 1;
        StringBuffer sb = new StringBuffer();
        sb.append("texture.");
        sb.append(material);
        sb.append("_layer_");
        sb.append(layer);
        if (overlay != null) {
            sb.append("_");
            sb.append(overlay);
        }
        if ((loc = (nf)props.mapTextureLocations.get(key = sb.toString())) == null) {
            return props.textureLocation;
        }
        return loc;
    }

    public static nf getCustomElytraTexture(aip itemStack, nf locElytra) {
        if (itemProperties == null) {
            return locElytra;
        }
        CustomItemProperties props = CustomItems.getCustomItemProperties(itemStack, 4);
        if (props == null) {
            return locElytra;
        }
        if (props.textureLocation == null) {
            return locElytra;
        }
        return props.textureLocation;
    }

    private static CustomItemProperties getCustomItemProperties(aip itemStack, int type) {
        CustomItemProperties[] cips;
        if (itemProperties == null) {
            return null;
        }
        if (itemStack == null) {
            return null;
        }
        ain item = itemStack.c();
        int itemId = ain.a((ain)item);
        if (itemId >= 0 && itemId < itemProperties.length && (cips = itemProperties[itemId]) != null) {
            for (int i = 0; i < cips.length; ++i) {
                CustomItemProperties cip = cips[i];
                if (cip.type != type || !CustomItems.matchesProperties(cip, itemStack, null)) continue;
                return cip;
            }
        }
        return null;
    }

    private static boolean matchesProperties(CustomItemProperties cip, aip itemStack, int[][] enchantmentIdLevels) {
        int i;
        ain item = itemStack.c();
        if (cip.damage != null) {
            int damage = CustomItems.getItemStackDamage(itemStack);
            if (damage < 0) {
                return false;
            }
            if (cip.damageMask != 0) {
                damage &= cip.damageMask;
            }
            if (cip.damagePercent) {
                int damageMax = item.l();
                damage = (int)((double)(damage * 100) / (double)damageMax);
            }
            if (!cip.damage.isInRange(damage)) {
                return false;
            }
        }
        if (cip.stackSize != null && !cip.stackSize.isInRange(itemStack.E())) {
            return false;
        }
        int[][] idLevels = enchantmentIdLevels;
        if (cip.enchantmentIds != null) {
            if (idLevels == null) {
                idLevels = CustomItems.getEnchantmentIdLevels(itemStack);
            }
            boolean idMatch = false;
            for (i = 0; i < idLevels.length; ++i) {
                int id = idLevels[i][0];
                if (!cip.enchantmentIds.isInRange(id)) continue;
                idMatch = true;
                break;
            }
            if (!idMatch) {
                return false;
            }
        }
        if (cip.enchantmentLevels != null) {
            if (idLevels == null) {
                idLevels = CustomItems.getEnchantmentIdLevels(itemStack);
            }
            boolean levelMatch = false;
            for (i = 0; i < idLevels.length; ++i) {
                int level = idLevels[i][1];
                if (!cip.enchantmentLevels.isInRange(level)) continue;
                levelMatch = true;
                break;
            }
            if (!levelMatch) {
                return false;
            }
        }
        if (cip.nbtTagValues != null) {
            fy nbt = itemStack.p();
            for (i = 0; i < cip.nbtTagValues.length; ++i) {
                NbtTagValue ntv = cip.nbtTagValues[i];
                if (ntv.matches(nbt)) continue;
                return false;
            }
        }
        if (cip.hand != 0) {
            if (cip.hand == 1 && renderOffHand) {
                return false;
            }
            if (cip.hand == 2 && !renderOffHand) {
                return false;
            }
        }
        return true;
    }

    private static int getItemStackDamage(aip itemStack) {
        ain item = itemStack.c();
        if (item instanceof ajd) {
            return CustomItems.getPotionDamage(itemStack);
        }
        return itemStack.i();
    }

    private static int getPotionDamage(aip itemStack) {
        fy nbt = itemStack.p();
        if (nbt == null) {
            return 0;
        }
        String name = nbt.l("Potion");
        if (name == null || name.equals("")) {
            return 0;
        }
        Integer value = mapPotionDamages.get(name);
        if (value == null) {
            return -1;
        }
        int val = value;
        if (itemStack.c() == air.bI) {
            val |= 0x4000;
        }
        return val;
    }

    private static Map<String, Integer> makeMapPotionDamages() {
        HashMap<String, Integer> map = new HashMap<String, Integer>();
        CustomItems.addPotion("water", 0, false, map);
        CustomItems.addPotion("awkward", 16, false, map);
        CustomItems.addPotion("thick", 32, false, map);
        CustomItems.addPotion("mundane", 64, false, map);
        CustomItems.addPotion("regeneration", 1, true, map);
        CustomItems.addPotion("swiftness", 2, true, map);
        CustomItems.addPotion("fire_resistance", 3, true, map);
        CustomItems.addPotion("poison", 4, true, map);
        CustomItems.addPotion("healing", 5, true, map);
        CustomItems.addPotion("night_vision", 6, true, map);
        CustomItems.addPotion("weakness", 8, true, map);
        CustomItems.addPotion("strength", 9, true, map);
        CustomItems.addPotion("slowness", 10, true, map);
        CustomItems.addPotion("leaping", 11, true, map);
        CustomItems.addPotion("harming", 12, true, map);
        CustomItems.addPotion("water_breathing", 13, true, map);
        CustomItems.addPotion("invisibility", 14, true, map);
        return map;
    }

    private static void addPotion(String name, int value, boolean extended, Map<String, Integer> map) {
        if (extended) {
            value |= 0x2000;
        }
        map.put("minecraft:" + name, value);
        if (extended) {
            int valueStrong = value | 0x20;
            map.put("minecraft:strong_" + name, valueStrong);
            int valueLong = value | 0x40;
            map.put("minecraft:long_" + name, valueLong);
        }
    }

    private static int[][] getEnchantmentIdLevels(aip itemStack) {
        ge nbt;
        ge ge2;
        ain item = itemStack.c();
        if (item == air.co) {
            ahy cfr_ignored_0 = (ahy)air.co;
            ge2 = ahy.h((aip)itemStack);
        } else {
            ge2 = nbt = itemStack.q();
        }
        if (nbt == null || nbt.c() <= 0) {
            return EMPTY_INT2_ARRAY;
        }
        int[][] arr2 = new int[nbt.c()][2];
        for (int i = 0; i < nbt.c(); ++i) {
            fy tag = nbt.b(i);
            short id = tag.g("id");
            short lvl = tag.g("lvl");
            arr2[i][0] = id;
            arr2[i][1] = lvl;
        }
        return arr2;
    }

    public static boolean renderCustomEffect(bzw renderItem, aip itemStack, cfy model) {
        if (enchantmentProperties == null) {
            return false;
        }
        if (itemStack == null) {
            return false;
        }
        int[][] idLevels = CustomItems.getEnchantmentIdLevels(itemStack);
        if (idLevels.length <= 0) {
            return false;
        }
        HashSet<Integer> layersRendered = null;
        boolean rendered = false;
        cdr textureManager = Config.getTextureManager();
        for (int i = 0; i < idLevels.length; ++i) {
            CustomItemProperties[] cips;
            int id = idLevels[i][0];
            if (id < 0 || id >= enchantmentProperties.length || (cips = enchantmentProperties[id]) == null) continue;
            for (int p = 0; p < cips.length; ++p) {
                CustomItemProperties cip = cips[p];
                if (layersRendered == null) {
                    layersRendered = new HashSet<Integer>();
                }
                if (!layersRendered.add(id) || !CustomItems.matchesProperties(cip, itemStack, idLevels) || cip.textureLocation == null) continue;
                textureManager.a(cip.textureLocation);
                float width = cip.getTextureWidth(textureManager);
                if (!rendered) {
                    rendered = true;
                    bus.a((boolean)false);
                    bus.c((int)514);
                    bus.g();
                    bus.n((int)5890);
                }
                Blender.setupBlend(cip.blend, 1.0f);
                bus.G();
                bus.b((float)(width / 2.0f), (float)(width / 2.0f), (float)(width / 2.0f));
                float offset = cip.speed * (float)(bib.I() % 3000L) / 3000.0f / 8.0f;
                bus.c((float)offset, (float)0.0f, (float)0.0f);
                bus.b((float)cip.rotation, (float)0.0f, (float)0.0f, (float)1.0f);
                renderItem.a(model, -1);
                bus.H();
            }
        }
        if (rendered) {
            bus.e();
            bus.m();
            bus.b((int)770, (int)771);
            bus.c((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
            bus.n((int)5888);
            bus.f();
            bus.c((int)515);
            bus.a((boolean)true);
            textureManager.a(cdp.g);
        }
        return rendered;
    }

    public static boolean renderCustomArmorEffect(vp entity, aip itemStack, bqf model, float limbSwing, float prevLimbSwing, float partialTicks, float timeLimbSwing, float yaw, float pitch, float scale) {
        if (enchantmentProperties == null) {
            return false;
        }
        if (Config.isShaders() && Shaders.isShadowPass) {
            return false;
        }
        if (itemStack == null) {
            return false;
        }
        int[][] idLevels = CustomItems.getEnchantmentIdLevels(itemStack);
        if (idLevels.length <= 0) {
            return false;
        }
        HashSet<Integer> layersRendered = null;
        boolean rendered = false;
        cdr textureManager = Config.getTextureManager();
        for (int i = 0; i < idLevels.length; ++i) {
            CustomItemProperties[] cips;
            int id = idLevels[i][0];
            if (id < 0 || id >= enchantmentProperties.length || (cips = enchantmentProperties[id]) == null) continue;
            for (int p = 0; p < cips.length; ++p) {
                CustomItemProperties cip = cips[p];
                if (layersRendered == null) {
                    layersRendered = new HashSet<Integer>();
                }
                if (!layersRendered.add(id) || !CustomItems.matchesProperties(cip, itemStack, idLevels) || cip.textureLocation == null) continue;
                textureManager.a(cip.textureLocation);
                float width = cip.getTextureWidth(textureManager);
                if (!rendered) {
                    rendered = true;
                    if (Config.isShaders()) {
                        ShadersRender.renderEnchantedGlintBegin();
                    }
                    bus.m();
                    bus.c((int)514);
                    bus.a((boolean)false);
                }
                Blender.setupBlend(cip.blend, 1.0f);
                bus.g();
                bus.n((int)5890);
                bus.F();
                bus.b((float)cip.rotation, (float)0.0f, (float)0.0f, (float)1.0f);
                float texScale = width / 8.0f;
                bus.b((float)texScale, (float)(texScale / 2.0f), (float)texScale);
                float offset = cip.speed * (float)(bib.I() % 3000L) / 3000.0f / 8.0f;
                bus.c((float)0.0f, (float)offset, (float)0.0f);
                bus.n((int)5888);
                model.a((vg)entity, limbSwing, prevLimbSwing, timeLimbSwing, yaw, pitch, scale);
            }
        }
        if (rendered) {
            bus.e();
            bus.m();
            bus.b((int)770, (int)771);
            bus.c((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
            bus.n((int)5890);
            bus.F();
            bus.n((int)5888);
            bus.f();
            bus.a((boolean)true);
            bus.c((int)515);
            bus.l();
            if (Config.isShaders()) {
                ShadersRender.renderEnchantedGlintEnd();
            }
        }
        return rendered;
    }

    public static boolean isUseGlint() {
        return useGlint;
    }

    public static void setRenderOffHand(boolean renderOffHand) {
        CustomItems.renderOffHand = renderOffHand;
    }
}

