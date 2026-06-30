/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraftforge.common.MinecraftForge
 *  org.jetbrains.annotations.NotNull
 *  org.jetbrains.annotations.Nullable
 */
package invtweaks;

import invtweaks.InvTweaks;
import invtweaks.InvTweaksConfigInventoryRuleset;
import invtweaks.InvTweaksConfigProperties;
import invtweaks.InvTweaksConfigSortingRule;
import invtweaks.InvTweaksConst;
import invtweaks.InvTweaksItemTree;
import invtweaks.InvTweaksItemTreeLoader;
import invtweaks.api.IItemTreeItem;
import invtweaks.forge.ClientProxy;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.security.InvalidParameterException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import net.minecraftforge.common.MinecraftForge;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class InvTweaksConfig {
    public static final String PROP_VERSION = "version";
    public static final String PROP_ENABLE_MIDDLE_CLICK = "enableMiddleClick";
    public static final String PROP_SHOW_CHEST_BUTTONS = "showChestButtons";
    public static final String PROP_ENABLE_SORTING_ON_PICKUP = "enableSortingOnPickup";
    public static final String PROP_ENABLE_AUTO_EQUIP_ARMOR = "enableAutoEquipArmor";
    public static final String PROP_ENABLE_AUTO_REFILL = "enableAutoRefill";
    public static final String PROP_AUTO_REFILL_BEFORE_BREAK = "autoRefillBeforeBreak";
    public static final String PROP_AUTO_REFILL_DAMAGE_THRESHHOLD = "autoRefillDamageThreshhold";
    public static final String PROP_INVERT_TOOL_DAMAGE = "invertToolDamageSorting";
    public static final String PROP_ENABLE_SHORTCUTS = "enableShortcuts";
    public static final String PROP_SHORTCUT_PREFIX = "shortcutKey";
    public static final String PROP_SHORTCUT_ONE_ITEM = "shortcutKeyOneItem";
    public static final String PROP_SHORTCUT_ALL_ITEMS = "shortcutKeyAllItems";
    public static final String PROP_SHORTCUT_EVERYTHING = "shortcutKeyEverything";
    public static final String PROP_SHORTCUT_DROP = "shortcutKeyDrop";
    public static final String PROP_SHORTCUT_UP = "shortcutKeyToUpperSection";
    public static final String PROP_SHORTCUT_DOWN = "shortcutKeyToLowerSection";
    public static final String PROP_ENABLE_SOUNDS = "enableSounds";
    public static final String PROP_ENABLE_SERVER_ITEMSWAP = "enableServerItemSwap";
    public static final String PROP_ENABLE_CONFIG_LOADED_MESSAGE = "enableConfigLoadedMesssage";
    public static final String PROP_ENABLE_CONTAINER_MIRRORING = "enableContainerMirroring";
    public static final String VALUE_TRUE = "true";
    public static final String VALUE_FALSE = "false";
    public static final String LOCKED = "locked";
    public static final String FROZEN = "frozen";
    public static final String AUTOREFILL = "autorefill";
    public static final String AUTOREFILL_NOTHING = "nothing";
    public static final String DEBUG = "debug";
    private final File rulesFile;
    private final File treeFile;
    private InvTweaksConfigProperties properties;
    private InvTweaksItemTree tree;
    private List<InvTweaksConfigInventoryRuleset> rulesets;
    private int currentRuleset = 0;
    @Nullable
    private String currentRulesetName = null;
    private List<String> invalidKeywords;
    private long storedConfigLastModified;

    public InvTweaksConfig(File rulesFile_, File treeFile_) {
        this.rulesFile = rulesFile_;
        this.treeFile = treeFile_;
        this.reset();
    }

    private static File getPropertyFile() {
        File configPropsFile = InvTweaksConst.CONFIG_PROPS_FILE;
        if (!configPropsFile.exists()) {
            try {
                configPropsFile.createNewFile();
            }
            catch (IOException e) {
                InvTweaks.logInGameStatic("invtweaks.propsfile.errors");
                return null;
            }
        }
        return configPropsFile;
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public void load() throws Exception {
        InvTweaksConfig invTweaksConfig = this;
        synchronized (invTweaksConfig) {
            this.reset();
            this.loadProperties();
            this.saveProperties();
            if (this.tree != null) {
                MinecraftForge.EVENT_BUS.unregister((Object)this.tree);
            }
            this.tree = InvTweaksItemTreeLoader.load(this.treeFile);
            char[] bytes = new char[(int)this.rulesFile.length()];
            try (FileReader reader = null;){
                reader = new FileReader(this.rulesFile);
                reader.read(bytes);
            }
            String[] configLines = String.valueOf(bytes).replace("\r\n", "\n").replace('\r', '\n').split("\n");
            InvTweaksConfigInventoryRuleset activeRuleset = new InvTweaksConfigInventoryRuleset(this.tree, "Default");
            boolean defaultRuleset = true;
            boolean defaultRulesetEmpty = true;
            for (String line : configLines) {
                String trimmedLine = line.trim();
                if (trimmedLine.isEmpty()) continue;
                if (trimmedLine.matches("^[\\w]*[\\s]*:$")) {
                    if (!defaultRuleset || !defaultRulesetEmpty) {
                        activeRuleset.finalizeRules();
                        this.rulesets.add(activeRuleset);
                    }
                    activeRuleset = new InvTweaksConfigInventoryRuleset(this.tree, trimmedLine.substring(0, trimmedLine.length() - 1));
                    defaultRuleset = false;
                    continue;
                }
                try {
                    String invalidKeyword = activeRuleset.registerLine(trimmedLine);
                    if (defaultRuleset) {
                        defaultRulesetEmpty = false;
                    }
                    if (invalidKeyword == null) continue;
                    this.invalidKeywords.add(invalidKeyword);
                }
                catch (InvalidParameterException invalidParameterException) {
                    // empty catch block
                }
            }
            activeRuleset.finalizeRules();
            this.rulesets.add(activeRuleset);
            this.currentRuleset = 0;
            if (this.currentRulesetName != null) {
                int rulesetIndex = 0;
                for (InvTweaksConfigInventoryRuleset ruleset : this.rulesets) {
                    if (ruleset.getName().equals(this.currentRulesetName)) {
                        this.currentRuleset = rulesetIndex;
                        break;
                    }
                    ++rulesetIndex;
                }
            }
            if (this.currentRuleset == 0) {
                this.currentRulesetName = !this.rulesets.isEmpty() ? this.rulesets.get(this.currentRuleset).getName() : null;
            }
        }
    }

    public boolean refreshProperties() throws IOException {
        long configLastModified = InvTweaksConst.CONFIG_PROPS_FILE.lastModified();
        if (this.storedConfigLastModified != configLastModified) {
            this.storedConfigLastModified = configLastModified;
            this.loadProperties();
            return true;
        }
        return false;
    }

    public void saveProperties() {
        File configPropsFile = InvTweaksConfig.getPropertyFile();
        assert (configPropsFile != null);
        if (configPropsFile.exists()) {
            try {
                FileOutputStream fos = new FileOutputStream(configPropsFile);
                this.properties.store(fos, "Inventory Tweaks Configuration\n(Regarding shortcuts, all key names can be found at: http://legacy.lwjgl.org/javadoc/org/lwjgl/input/Keyboard.html)");
                fos.flush();
                fos.close();
                this.storedConfigLastModified = InvTweaksConst.CONFIG_PROPS_FILE.lastModified();
            }
            catch (IOException e) {
                InvTweaks.logInGameStatic("Failed to save config file " + InvTweaksConst.CONFIG_PROPS_FILE);
            }
        }
    }

    @NotNull
    public Map<String, String> getProperties(@NotNull String prefix) {
        HashMap<String, String> result = new HashMap<String, String>();
        for (Object o : this.properties.keySet()) {
            String key = (String)o;
            if (!key.startsWith(prefix)) continue;
            result.put(key, this.properties.getProperty(key));
        }
        return result;
    }

    @NotNull
    public String getProperty(@NotNull String key) {
        String value = this.properties.getProperty(key);
        return value != null ? value : "";
    }

    public int getIntProperty(@NotNull String key) {
        return Integer.parseInt(this.getProperty(key));
    }

    public void setProperty(String key, String value) {
        this.properties.put(key, (Object)value);
        this.saveProperties();
    }

    public InvTweaksItemTree getTree() {
        return this.tree;
    }

    @Nullable
    public String getCurrentRulesetName() {
        return this.currentRulesetName;
    }

    @Nullable
    public String switchConfig(int i) {
        if (!this.rulesets.isEmpty() && i < this.rulesets.size() && i != this.currentRuleset) {
            this.currentRuleset = i;
            this.currentRulesetName = this.rulesets.get(this.currentRuleset).getName();
            return this.currentRulesetName;
        }
        return null;
    }

    @Nullable
    public String switchConfig() {
        if (this.currentRuleset == -1) {
            return this.switchConfig(0);
        }
        return this.switchConfig((this.currentRuleset + 1) % this.rulesets.size());
    }

    public List<InvTweaksConfigSortingRule> getRules() {
        return this.rulesets.get(this.currentRuleset).getRules();
    }

    public List<String> getInvalidKeywords() {
        return this.invalidKeywords;
    }

    public int[] getLockPriorities() {
        return this.rulesets.get(this.currentRuleset).getLockPriorities();
    }

    public boolean[] getFrozenSlots() {
        return this.rulesets.get(this.currentRuleset).getFrozenSlots();
    }

    public boolean isAutoRefillEnabled(String itemID, int itemDamage) {
        if (!this.getProperty(PROP_ENABLE_AUTO_REFILL).equals(VALUE_FALSE)) {
            List<IItemTreeItem> items = this.tree.getItems(itemID, itemDamage);
            List<String> autoReplaceRules = this.rulesets.get(this.currentRuleset).getAutoReplaceRules();
            boolean found = false;
            for (String keyword : autoReplaceRules) {
                if (keyword.equals(AUTOREFILL_NOTHING)) {
                    return false;
                }
                if (!this.tree.matches(items, keyword)) continue;
                found = true;
            }
            return found || autoReplaceRules.isEmpty();
        }
        return false;
    }

    private void reset() {
        this.rulesets = new ArrayList<InvTweaksConfigInventoryRuleset>();
        this.currentRuleset = -1;
        this.properties = new InvTweaksConfigProperties();
        this.properties.put(PROP_ENABLE_MIDDLE_CLICK, (Object)VALUE_TRUE);
        this.properties.put(PROP_SHOW_CHEST_BUTTONS, (Object)VALUE_TRUE);
        this.properties.put(PROP_ENABLE_SORTING_ON_PICKUP, (Object)VALUE_FALSE);
        this.properties.put(PROP_ENABLE_AUTO_REFILL, (Object)VALUE_TRUE);
        this.properties.put(PROP_AUTO_REFILL_BEFORE_BREAK, (Object)VALUE_FALSE);
        this.properties.put(PROP_AUTO_REFILL_DAMAGE_THRESHHOLD, (Object)"5");
        this.properties.put(PROP_ENABLE_SOUNDS, (Object)VALUE_TRUE);
        this.properties.put(PROP_ENABLE_SHORTCUTS, (Object)VALUE_TRUE);
        this.properties.put(PROP_ENABLE_AUTO_EQUIP_ARMOR, (Object)VALUE_FALSE);
        this.properties.put(PROP_ENABLE_SERVER_ITEMSWAP, (Object)VALUE_TRUE);
        this.properties.put(PROP_ENABLE_CONFIG_LOADED_MESSAGE, (Object)VALUE_FALSE);
        this.properties.put(PROP_INVERT_TOOL_DAMAGE, (Object)VALUE_TRUE);
        this.properties.put(PROP_SHORTCUT_ALL_ITEMS, (Object)"LCONTROL+LSHIFT, RCONTROL+RSHIFT");
        this.properties.put(PROP_SHORTCUT_EVERYTHING, (Object)"SPACE");
        this.properties.put(PROP_SHORTCUT_ONE_ITEM, (Object)"LCONTROL, RCONTROL");
        this.properties.put(PROP_SHORTCUT_UP, (Object)"UP");
        this.properties.put(PROP_SHORTCUT_DOWN, (Object)"DOWN");
        this.properties.put(PROP_SHORTCUT_DROP, (Object)"LALT, RALT");
        this.properties.put(PROP_VERSION, (Object)"1.63+release.109.220f184".split(" ")[0]);
        this.invalidKeywords = new ArrayList<String>();
    }

    private void loadProperties() throws IOException {
        File configPropsFile = InvTweaksConfig.getPropertyFile();
        InvTweaksConfigProperties newProperties = new InvTweaksConfigProperties();
        if (configPropsFile != null) {
            FileInputStream fis = new FileInputStream(configPropsFile);
            newProperties.load(fis);
            fis.close();
        }
        newProperties.sortKeys();
        if (newProperties.get(PROP_VERSION) != null) {
            for (Map.Entry<Object, Object> entry : newProperties.entrySet()) {
                this.properties.put(entry.getKey(), entry.getValue());
            }
        }
        this.properties.put(PROP_VERSION, (Object)"1.63+release.109.220f184".split(" ")[0]);
    }

    public int getSortKeyCode() {
        return ClientProxy.KEYBINDING_SORT.func_151463_i();
    }
}

