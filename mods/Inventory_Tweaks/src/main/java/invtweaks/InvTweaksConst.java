/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.Minecraft
 *  net.minecraft.util.ResourceLocation
 *  org.apache.logging.log4j.Level
 */
package invtweaks;

import java.io.File;
import net.minecraft.client.Minecraft;
import net.minecraft.util.ResourceLocation;
import org.apache.logging.log4j.Level;

public class InvTweaksConst {
    public static final String MOD_VERSION = "1.63+release.109.220f184";
    public static final String TREE_VERSION = "1.12.2";
    public static final String INVTWEAKS_CHANNEL = "InventoryTweaks";
    public static final byte PROTOCOL_VERSION = 2;
    public static final byte PACKET_LOGIN = 0;
    public static final byte PACKET_CLICK = 1;
    public static final byte PACKET_SORTCOMPLETE = 2;
    public static final int RULESET_SWAP_DELAY = 1000;
    public static final int POLLING_DELAY = 3;
    public static final int CHEST_ALGORITHM_SWAP_MAX_INTERVAL = 2000;
    public static final int TOOLTIP_DELAY = 800;
    public static final File MINECRAFT_DIR = Minecraft.func_71410_x().field_71412_D;
    public static final File MINECRAFT_CONFIG_DIR = new File(MINECRAFT_DIR, "config/");
    public static final File CONFIG_PROPS_FILE = new File(MINECRAFT_CONFIG_DIR, "InvTweaks.cfg");
    public static final File CONFIG_RULES_FILE = new File(MINECRAFT_CONFIG_DIR, "InvTweaksRules.txt");
    public static final File CONFIG_TREE_FILE = new File(MINECRAFT_CONFIG_DIR, "InvTweaksTree.txt");
    public static final File OLD_CONFIG_TREE_FILE = new File(MINECRAFT_CONFIG_DIR, "InvTweaksTree.xml");
    public static final File OLDER_CONFIG_RULES_FILE = new File(MINECRAFT_DIR, "InvTweaksRules.txt");
    public static final String INVTWEAKS_RESOURCE_DOMAIN = "inventorytweaks";
    public static final ResourceLocation DEFAULT_CONFIG_FILE = new ResourceLocation("inventorytweaks", "defaultconfig.dat");
    public static final ResourceLocation DEFAULT_CONFIG_TREE_FILE = new ResourceLocation("inventorytweaks", "itemtree.xml");
    public static final String HELP_URL = "http://inventory-tweaks.readthedocs.org";
    public static final String INGAME_LOG_PREFIX = "InvTweaks: ";
    public static final Level DEBUG = Level.INFO;
    public static final int JIMEOWAN_ID = 54696386;
    public static final int INVENTORY_SIZE = 36;
    public static final int INVENTORY_ROW_SIZE = 9;
    public static final int HOTBAR_SIZE = 9;
    public static final int INVENTORY_HOTBAR_SIZE = 9;
    public static final int DAMAGE_WILDCARD = Short.MAX_VALUE;
}

