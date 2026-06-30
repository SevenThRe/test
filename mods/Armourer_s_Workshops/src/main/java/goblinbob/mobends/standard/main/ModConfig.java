/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityList
 *  net.minecraft.item.Item
 *  net.minecraft.util.ResourceLocation
 *  net.minecraftforge.common.config.Config
 *  net.minecraftforge.common.config.Config$Ignore
 *  net.minecraftforge.common.config.Config$LangKey
 */
package goblinbob.mobends.standard.main;

import goblinbob.mobends.core.util.WildcardPattern;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityList;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.config.Config;

@Config(modid="mobends")
public class ModConfig {
    @Config.LangKey(value="mobends.config.playAttackAnimation")
    public static boolean playAttack = true;
    @Config.LangKey(value="mobends.config.playBowAnimation")
    public static boolean playBow = true;
    @Config.LangKey(value="mobends.config.playHarvestAnimation")
    public static boolean playHarvest = true;
    @Config.LangKey(value="mobends.config.disable_key")
    public static boolean disableKey = false;
    @Config.LangKey(value="mobends.config.show_arrow_trails")
    public static boolean showArrowTrails = true;
    @Config.LangKey(value="mobends.config.show_sword_trails")
    public static boolean showSwordTrail = true;
    @Config.LangKey(value="mobends.config.perform_spin_attack")
    public static boolean performSpinAttack = true;
    @Config.LangKey(value="mobends.config.weapon_items")
    public static String[] weaponItems = new String[0];
    @Config.LangKey(value="mobends.config.tool_items")
    public static String[] toolItems = new String[0];
    @Config.LangKey(value="mobends.config.keep_armor_as_vanilla")
    public static String[] keepArmorAsVanilla = new String[0];
    @Config.LangKey(value="mobends.config.keep_entity_as_vanilla")
    public static String[] keepEntityAsVanilla = new String[0];
    @Config.Ignore
    private static Map<Item, Boolean> keepArmorAsVanillaCache;
    @Config.Ignore
    private static Map<Entity, Boolean> keepEntityAsVanillaCache;
    @Config.Ignore
    private static Map<Item, ItemClassification> itemClassificationCache;
    @Config.Ignore
    private static List<Map<?, ?>> caches;

    private static boolean checkForPatterns(ResourceLocation resourceLocation, String[] patterns) {
        String resourceDomain = resourceLocation.func_110624_b();
        String resourcePath = resourceLocation.func_110623_a();
        for (String pattern : patterns) {
            ResourceLocation patternLocation = new ResourceLocation(pattern);
            if (resourceLocation.equals((Object)patternLocation)) {
                return true;
            }
            WildcardPattern domainPattern = new WildcardPattern(patternLocation.func_110624_b());
            WildcardPattern pathPattern = new WildcardPattern(patternLocation.func_110623_a());
            if (!domainPattern.matches(resourceDomain) || !pathPattern.matches(resourcePath)) continue;
            return true;
        }
        return false;
    }

    public static ItemClassification getItemClassification(Item item) {
        return itemClassificationCache.computeIfAbsent(item, i2 -> {
            ResourceLocation location = item.getRegistryName();
            if (ModConfig.checkForPatterns(location, weaponItems)) {
                return ItemClassification.WEAPON;
            }
            if (ModConfig.checkForPatterns(location, toolItems)) {
                return ItemClassification.TOOL;
            }
            return ItemClassification.UNKNOWN;
        });
    }

    public static boolean shouldKeepArmorAsVanilla(Item item) {
        return keepArmorAsVanillaCache.computeIfAbsent(item, i2 -> ModConfig.checkForPatterns(i2.getRegistryName(), keepArmorAsVanilla));
    }

    public static boolean shouldKeepEntityAsVanilla(Entity entity) {
        return keepEntityAsVanillaCache.computeIfAbsent(entity, e2 -> {
            ResourceLocation location = EntityList.func_191301_a((Entity)entity);
            return location != null && ModConfig.checkForPatterns(location, keepEntityAsVanilla);
        });
    }

    static {
        Map[] mapArray = new Map[3];
        keepArmorAsVanillaCache = new HashMap<Item, Boolean>();
        mapArray[0] = keepArmorAsVanillaCache;
        keepEntityAsVanillaCache = new HashMap<Entity, Boolean>();
        mapArray[1] = keepEntityAsVanillaCache;
        itemClassificationCache = new HashMap<Item, ItemClassification>();
        mapArray[2] = itemClassificationCache;
        caches = Arrays.asList(mapArray);
    }

    public static enum ItemClassification {
        UNKNOWN,
        WEAPON,
        TOOL;

    }
}

