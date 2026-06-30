/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  aao
 *  aap
 *  aaq
 *  aas
 *  aat
 *  ady
 *  ahs
 *  amy
 *  anh
 *  api$a
 *  avh
 *  avj
 *  avl
 *  avp
 *  avq
 *  avs
 *  awb
 *  blk
 *  bmi
 *  bmk
 *  bmm
 *  bmq
 *  bmt
 *  bmu
 *  bmv
 *  bna
 *  et
 *  nf
 *  ui
 *  vg
 */
package net.optifine;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.Set;
import net.optifine.CustomGuis;
import net.optifine.config.ConnectedParser;
import net.optifine.config.Matches;
import net.optifine.config.NbtTagValue;
import net.optifine.config.RangeListInt;
import net.optifine.config.VillagerProfession;
import net.optifine.reflect.Reflector;
import net.optifine.reflect.ReflectorField;
import net.optifine.util.StrUtils;
import net.optifine.util.TextureUtils;

public class CustomGuiProperties {
    private String fileName = null;
    private String basePath = null;
    private EnumContainer container = null;
    private Map<nf, nf> textureLocations = null;
    private NbtTagValue nbtName = null;
    private anh[] biomes = null;
    private RangeListInt heights = null;
    private Boolean large = null;
    private Boolean trapped = null;
    private Boolean christmas = null;
    private Boolean ender = null;
    private RangeListInt levels = null;
    private VillagerProfession[] professions = null;
    private EnumVariant[] variants = null;
    private ahs[] colors = null;
    private static final EnumVariant[] VARIANTS_HORSE = new EnumVariant[]{EnumVariant.HORSE, EnumVariant.DONKEY, EnumVariant.MULE, EnumVariant.LLAMA};
    private static final EnumVariant[] VARIANTS_DISPENSER = new EnumVariant[]{EnumVariant.DISPENSER, EnumVariant.DROPPER};
    private static final EnumVariant[] VARIANTS_INVALID = new EnumVariant[0];
    private static final ahs[] COLORS_INVALID = new ahs[0];
    private static final nf ANVIL_GUI_TEXTURE = new nf("textures/gui/container/anvil.png");
    private static final nf BEACON_GUI_TEXTURE = new nf("textures/gui/container/beacon.png");
    private static final nf BREWING_STAND_GUI_TEXTURE = new nf("textures/gui/container/brewing_stand.png");
    private static final nf CHEST_GUI_TEXTURE = new nf("textures/gui/container/generic_54.png");
    private static final nf CRAFTING_TABLE_GUI_TEXTURE = new nf("textures/gui/container/crafting_table.png");
    private static final nf HORSE_GUI_TEXTURE = new nf("textures/gui/container/horse.png");
    private static final nf DISPENSER_GUI_TEXTURE = new nf("textures/gui/container/dispenser.png");
    private static final nf ENCHANTMENT_TABLE_GUI_TEXTURE = new nf("textures/gui/container/enchanting_table.png");
    private static final nf FURNACE_GUI_TEXTURE = new nf("textures/gui/container/furnace.png");
    private static final nf HOPPER_GUI_TEXTURE = new nf("textures/gui/container/hopper.png");
    private static final nf INVENTORY_GUI_TEXTURE = new nf("textures/gui/container/inventory.png");
    private static final nf SHULKER_BOX_GUI_TEXTURE = new nf("textures/gui/container/shulker_box.png");
    private static final nf VILLAGER_GUI_TEXTURE = new nf("textures/gui/container/villager.png");

    public CustomGuiProperties(Properties props, String path) {
        ConnectedParser cp = new ConnectedParser("CustomGuis");
        this.fileName = cp.parseName(path);
        this.basePath = cp.parseBasePath(path);
        this.container = (EnumContainer)cp.parseEnum(props.getProperty("container"), EnumContainer.values(), "container");
        this.textureLocations = CustomGuiProperties.parseTextureLocations(props, "texture", this.container, "textures/gui/", this.basePath);
        this.nbtName = cp.parseNbtTagValue("name", props.getProperty("name"));
        this.biomes = cp.parseBiomes(props.getProperty("biomes"));
        this.heights = cp.parseRangeListInt(props.getProperty("heights"));
        this.large = cp.parseBooleanObject(props.getProperty("large"));
        this.trapped = cp.parseBooleanObject(props.getProperty("trapped"));
        this.christmas = cp.parseBooleanObject(props.getProperty("christmas"));
        this.ender = cp.parseBooleanObject(props.getProperty("ender"));
        this.levels = cp.parseRangeListInt(props.getProperty("levels"));
        this.professions = cp.parseProfessions(props.getProperty("professions"));
        Enum[] vars = CustomGuiProperties.getContainerVariants(this.container);
        this.variants = (EnumVariant[])cp.parseEnums(props.getProperty("variants"), vars, "variants", VARIANTS_INVALID);
        this.colors = CustomGuiProperties.parseEnumDyeColors(props.getProperty("colors"));
    }

    private static EnumVariant[] getContainerVariants(EnumContainer cont) {
        if (cont == EnumContainer.HORSE) {
            return VARIANTS_HORSE;
        }
        if (cont == EnumContainer.DISPENSER) {
            return VARIANTS_DISPENSER;
        }
        return new EnumVariant[0];
    }

    private static ahs[] parseEnumDyeColors(String str) {
        if (str == null) {
            return null;
        }
        str = str.toLowerCase();
        String[] tokens = Config.tokenize(str, " ");
        ahs[] cols = new ahs[tokens.length];
        for (int i = 0; i < tokens.length; ++i) {
            String token = tokens[i];
            ahs col = CustomGuiProperties.parseEnumDyeColor(token);
            if (col == null) {
                CustomGuiProperties.warn("Invalid color: " + token);
                return COLORS_INVALID;
            }
            cols[i] = col;
        }
        return cols;
    }

    private static ahs parseEnumDyeColor(String str) {
        if (str == null) {
            return null;
        }
        ahs[] colors = ahs.values();
        for (int i = 0; i < colors.length; ++i) {
            ahs enumDyeColor = colors[i];
            if (enumDyeColor.m().equals(str)) {
                return enumDyeColor;
            }
            if (!enumDyeColor.d().equals(str)) continue;
            return enumDyeColor;
        }
        return null;
    }

    private static nf parseTextureLocation(String str, String basePath) {
        if (str == null) {
            return null;
        }
        String tex = TextureUtils.fixResourcePath(str = str.trim(), basePath);
        if (!tex.endsWith(".png")) {
            tex = tex + ".png";
        }
        return new nf(basePath + "/" + tex);
    }

    private static Map<nf, nf> parseTextureLocations(Properties props, String property, EnumContainer container, String pathPrefix, String basePath) {
        HashMap<nf, nf> map = new HashMap<nf, nf>();
        String propVal = props.getProperty(property);
        if (propVal != null) {
            nf locKey = CustomGuiProperties.getGuiTextureLocation(container);
            nf locVal = CustomGuiProperties.parseTextureLocation(propVal, basePath);
            if (locKey != null && locVal != null) {
                map.put(locKey, locVal);
            }
        }
        String keyPrefix = property + ".";
        Set<Object> keys = props.keySet();
        for (String string : keys) {
            if (!string.startsWith(keyPrefix)) continue;
            String pathRel = string.substring(keyPrefix.length());
            pathRel = pathRel.replace('\\', '/');
            pathRel = StrUtils.removePrefixSuffix(pathRel, "/", ".png");
            String path = pathPrefix + pathRel + ".png";
            String val = props.getProperty(string);
            nf locKey = new nf(path);
            nf locVal = CustomGuiProperties.parseTextureLocation(val, basePath);
            map.put(locKey, locVal);
        }
        return map;
    }

    private static nf getGuiTextureLocation(EnumContainer container) {
        switch (container) {
            case ANVIL: {
                return ANVIL_GUI_TEXTURE;
            }
            case BEACON: {
                return BEACON_GUI_TEXTURE;
            }
            case BREWING_STAND: {
                return BREWING_STAND_GUI_TEXTURE;
            }
            case CHEST: {
                return CHEST_GUI_TEXTURE;
            }
            case CRAFTING: {
                return CRAFTING_TABLE_GUI_TEXTURE;
            }
            case CREATIVE: {
                return null;
            }
            case DISPENSER: {
                return DISPENSER_GUI_TEXTURE;
            }
            case ENCHANTMENT: {
                return ENCHANTMENT_TABLE_GUI_TEXTURE;
            }
            case FURNACE: {
                return FURNACE_GUI_TEXTURE;
            }
            case HOPPER: {
                return HOPPER_GUI_TEXTURE;
            }
            case HORSE: {
                return HORSE_GUI_TEXTURE;
            }
            case INVENTORY: {
                return INVENTORY_GUI_TEXTURE;
            }
            case SHULKER_BOX: {
                return SHULKER_BOX_GUI_TEXTURE;
            }
            case VILLAGER: {
                return VILLAGER_GUI_TEXTURE;
            }
        }
        return null;
    }

    public boolean isValid(String path) {
        if (this.fileName == null || this.fileName.length() <= 0) {
            CustomGuiProperties.warn("No name found: " + path);
            return false;
        }
        if (this.basePath == null) {
            CustomGuiProperties.warn("No base path found: " + path);
            return false;
        }
        if (this.container == null) {
            CustomGuiProperties.warn("No container found: " + path);
            return false;
        }
        if (this.textureLocations.isEmpty()) {
            CustomGuiProperties.warn("No texture found: " + path);
            return false;
        }
        if (this.professions == ConnectedParser.PROFESSIONS_INVALID) {
            CustomGuiProperties.warn("Invalid professions or careers: " + path);
            return false;
        }
        if (this.variants == VARIANTS_INVALID) {
            CustomGuiProperties.warn("Invalid variants: " + path);
            return false;
        }
        if (this.colors == COLORS_INVALID) {
            CustomGuiProperties.warn("Invalid colors: " + path);
            return false;
        }
        return true;
    }

    private static void warn(String str) {
        Config.warn("[CustomGuis] " + str);
    }

    private boolean matchesGeneral(EnumContainer ec, et pos, amy blockAccess) {
        anh biome;
        if (this.container != ec) {
            return false;
        }
        if (this.biomes != null && !Matches.biome(biome = blockAccess.b(pos), this.biomes)) {
            return false;
        }
        return this.heights == null || this.heights.isInRange(pos.q());
    }

    public boolean matchesPos(EnumContainer ec, et pos, amy blockAccess, blk screen) {
        String name;
        if (!this.matchesGeneral(ec, pos, blockAccess)) {
            return false;
        }
        if (this.nbtName != null && !this.nbtName.matchesValue(name = CustomGuiProperties.getName(screen))) {
            return false;
        }
        switch (ec) {
            case BEACON: {
                return this.matchesBeacon(pos, blockAccess);
            }
            case CHEST: {
                return this.matchesChest(pos, blockAccess);
            }
            case DISPENSER: {
                return this.matchesDispenser(pos, blockAccess);
            }
            case SHULKER_BOX: {
                return this.matchesShulker(pos, blockAccess);
            }
        }
        return true;
    }

    public static String getName(blk screen) {
        ui nameable = CustomGuiProperties.getWorldNameable(screen);
        if (nameable == null) {
            return null;
        }
        return nameable.i_().c();
    }

    private static ui getWorldNameable(blk screen) {
        if (screen instanceof bmi) {
            return CustomGuiProperties.getWorldNameable(screen, Reflector.GuiBeacon_tileBeacon);
        }
        if (screen instanceof bmk) {
            return CustomGuiProperties.getWorldNameable(screen, Reflector.GuiBrewingStand_tileBrewingStand);
        }
        if (screen instanceof bmm) {
            return CustomGuiProperties.getWorldNameable(screen, Reflector.GuiChest_lowerChestInventory);
        }
        if (screen instanceof bmq) {
            return ((bmq)screen).v;
        }
        if (screen instanceof bmt) {
            return CustomGuiProperties.getWorldNameable(screen, Reflector.GuiEnchantment_nameable);
        }
        if (screen instanceof bmu) {
            return CustomGuiProperties.getWorldNameable(screen, Reflector.GuiFurnace_tileFurnace);
        }
        if (screen instanceof bmv) {
            return CustomGuiProperties.getWorldNameable(screen, Reflector.GuiHopper_hopperInventory);
        }
        if (screen instanceof bna) {
            return CustomGuiProperties.getWorldNameable(screen, Reflector.GuiShulkerBox_inventory);
        }
        return null;
    }

    private static ui getWorldNameable(blk screen, ReflectorField fieldInventory) {
        Object obj = Reflector.getFieldValue(screen, fieldInventory);
        if (!(obj instanceof ui)) {
            return null;
        }
        return (ui)obj;
    }

    private boolean matchesBeacon(et pos, amy blockAccess) {
        int l;
        avj te = blockAccess.r(pos);
        if (!(te instanceof avh)) {
            return false;
        }
        avh teb = (avh)te;
        return this.levels == null || this.levels.isInRange(l = teb.s());
    }

    private boolean matchesChest(et pos, amy blockAccess) {
        avj te = blockAccess.r(pos);
        if (te instanceof avl) {
            avl tec = (avl)te;
            return this.matchesChest(tec, pos, blockAccess);
        }
        if (te instanceof avs) {
            avs teec = (avs)te;
            return this.matchesEnderChest(teec, pos, blockAccess);
        }
        return false;
    }

    private boolean matchesChest(avl tec, et pos, amy blockAccess) {
        boolean isLarge = tec.h != null || tec.g != null || tec.f != null || tec.i != null;
        boolean isTrapped = tec.p() == api.a.b;
        boolean isChristmas = CustomGuis.isChristmas;
        boolean isEnder = false;
        return this.matchesChest(isLarge, isTrapped, isChristmas, isEnder);
    }

    private boolean matchesEnderChest(avs teec, et pos, amy blockAccess) {
        return this.matchesChest(false, false, false, true);
    }

    private boolean matchesChest(boolean isLarge, boolean isTrapped, boolean isChristmas, boolean isEnder) {
        if (this.large != null && this.large != isLarge) {
            return false;
        }
        if (this.trapped != null && this.trapped != isTrapped) {
            return false;
        }
        if (this.christmas != null && this.christmas != isChristmas) {
            return false;
        }
        return this.ender == null || this.ender == isEnder;
    }

    private boolean matchesDispenser(et pos, amy blockAccess) {
        EnumVariant var;
        avj te = blockAccess.r(pos);
        if (!(te instanceof avp)) {
            return false;
        }
        avp ted = (avp)te;
        return this.variants == null || Config.equalsOne((Object)(var = this.getDispenserVariant(ted)), (Object[])this.variants);
    }

    private EnumVariant getDispenserVariant(avp ted) {
        if (ted instanceof avq) {
            return EnumVariant.DROPPER;
        }
        return EnumVariant.DISPENSER;
    }

    private boolean matchesShulker(et pos, amy blockAccess) {
        ahs col;
        avj te = blockAccess.r(pos);
        if (!(te instanceof awb)) {
            return false;
        }
        awb tesb = (awb)te;
        return this.colors == null || Config.equalsOne(col = tesb.s(), this.colors);
    }

    public boolean matchesEntity(EnumContainer ec, vg entity, amy blockAccess) {
        String entityName;
        if (!this.matchesGeneral(ec, entity.c(), blockAccess)) {
            return false;
        }
        if (this.nbtName != null && !this.nbtName.matchesValue(entityName = entity.h_())) {
            return false;
        }
        switch (ec) {
            case VILLAGER: {
                return this.matchesVillager(entity, blockAccess);
            }
            case HORSE: {
                return this.matchesHorse(entity, blockAccess);
            }
        }
        return true;
    }

    private boolean matchesVillager(vg entity, amy blockAccess) {
        if (!(entity instanceof ady)) {
            return false;
        }
        ady entityVillager = (ady)entity;
        if (this.professions != null) {
            int profInt = entityVillager.dl();
            int careerInt = Reflector.getFieldValueInt(entityVillager, Reflector.EntityVillager_careerId, -1);
            if (careerInt < 0) {
                return false;
            }
            boolean matchProfession = false;
            for (int i = 0; i < this.professions.length; ++i) {
                VillagerProfession prof = this.professions[i];
                if (!prof.matches(profInt, careerInt)) continue;
                matchProfession = true;
                break;
            }
            if (!matchProfession) {
                return false;
            }
        }
        return true;
    }

    private boolean matchesHorse(vg entity, amy blockAccess) {
        aas el;
        ahs col;
        EnumVariant var;
        if (!(entity instanceof aao)) {
            return false;
        }
        aao ah = (aao)entity;
        if (this.variants != null && !Config.equalsOne((Object)(var = this.getHorseVariant(ah)), (Object[])this.variants)) {
            return false;
        }
        return this.colors == null || !(ah instanceof aas) || Config.equalsOne(col = (el = (aas)ah).dT(), this.colors);
    }

    private EnumVariant getHorseVariant(aao entity) {
        if (entity instanceof aaq) {
            return EnumVariant.HORSE;
        }
        if (entity instanceof aap) {
            return EnumVariant.DONKEY;
        }
        if (entity instanceof aat) {
            return EnumVariant.MULE;
        }
        if (entity instanceof aas) {
            return EnumVariant.LLAMA;
        }
        return null;
    }

    public EnumContainer getContainer() {
        return this.container;
    }

    public nf getTextureLocation(nf loc) {
        nf locNew = this.textureLocations.get(loc);
        if (locNew == null) {
            return loc;
        }
        return locNew;
    }

    public String toString() {
        return "name: " + this.fileName + ", container: " + (Object)((Object)this.container) + ", textures: " + this.textureLocations;
    }

    private static enum EnumVariant {
        HORSE,
        DONKEY,
        MULE,
        LLAMA,
        DISPENSER,
        DROPPER;

    }

    public static enum EnumContainer {
        ANVIL,
        BEACON,
        BREWING_STAND,
        CHEST,
        CRAFTING,
        DISPENSER,
        ENCHANTMENT,
        FURNACE,
        HOPPER,
        HORSE,
        VILLAGER,
        SHULKER_BOX,
        CREATIVE,
        INVENTORY;

    }
}

