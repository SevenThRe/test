/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraftforge.common.MinecraftForge
 *  net.minecraftforge.common.config.Config
 *  net.minecraftforge.common.config.Config$Comment
 *  net.minecraftforge.common.config.Config$Name
 *  net.minecraftforge.common.config.Config$RangeDouble
 *  net.minecraftforge.common.config.Config$Type
 *  net.minecraftforge.common.config.ConfigManager
 *  net.minecraftforge.fml.client.event.ConfigChangedEvent$OnConfigChangedEvent
 *  net.minecraftforge.fml.common.Mod$EventBusSubscriber
 *  net.minecraftforge.fml.common.eventhandler.SubscribeEvent
 *  net.minecraftforge.fml.relauncher.Side
 */
package com.rejahtavi.rfp2;

import com.rejahtavi.rfp2.RFP2;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.config.Config;
import net.minecraftforge.common.config.ConfigManager;
import net.minecraftforge.fml.client.event.ConfigChangedEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;

@Config(modid="rfp2", type=Config.Type.INSTANCE, name="rfp2", category="")
@Mod.EventBusSubscriber(value={Side.CLIENT})
public class RFP2Config {
    @Config.Comment(value={"Personal preferences for Real First Person 2"})
    @Config.Name(value="Preferences")
    public static final Preferences preferences = new Preferences();
    @Config.Comment(value={"Item and Mount compatability lists for Real First Person 2"})
    @Config.Name(value="Compatability")
    public static final Compatibility compatibility = new Compatibility();

    public RFP2Config() {
        MinecraftForge.EVENT_BUS.register((Object)this);
    }

    @SubscribeEvent
    public static void onConfigChanged(ConfigChangedEvent.OnConfigChangedEvent event) {
        if (event.getModID().contentEquals("rfp2")) {
            RFP2.logger.log(RFP2.LOGGING_LEVEL_LOW, "synchronizing config file.");
            RFP2Config.compatibility.heldItemConflictList = RFP2Config.lowerCaseArray(RFP2Config.compatibility.heldItemConflictList);
            RFP2Config.compatibility.mountConflictList = RFP2Config.lowerCaseArray(RFP2Config.compatibility.mountConflictList);
            ConfigManager.sync((String)"rfp2", (Config.Type)Config.Type.INSTANCE);
            RFP2.state.enableMod = RFP2Config.preferences.enableMod;
            RFP2.state.enableRealArms = RFP2Config.preferences.enableRealArms;
            RFP2.state.enableHeadTurning = RFP2Config.preferences.enableHeadTurning;
            RFP2.state.enableStatusMessages = RFP2Config.preferences.enableStatusMessages;
        }
    }

    private static String[] lowerCaseArray(String[] array) {
        for (int i2 = 0; i2 < array.length; ++i2) {
            array[i2] = array[i2].toLowerCase();
        }
        return array;
    }

    public static class Compatibility {
        @Config.Comment(value={"Vanilla arms are used when holding one of these items.", "Needed for compasses and maps, stops big items blocking the view.", "Note: Not case sensitive, accepts simple item names and regex patterns:", ".* = wildcard, ^ = match beginning of name, $ = match end of name."})
        @Config.Name(value="Held Item Conflicts")
        public String[] heldItemConflictList = new String[]{"minecraft:filled_map", "minecraft:clock", "minecraft:shield", "minecraft:bow", "slashblade:.*", ".*compass$", "tconstruct:.*bow", "tconstruct:battlesign", "thermalfoundation:shield_.*"};
        @Config.Comment(value={"Mod temporarily disables when riding one of these mounts.", "Stops legs clipping through minecarts.", "Note: Not case sensitive, accepts simple item names and regex patterns.", ".* = wildcard, ^ = match beginning of name, $ = match end of name."})
        @Config.Name(value="Mount Conflicts")
        public String[] mountConflictList = new String[]{".*minecart.*"};
        @Config.Comment(value={"Disables the mod when swimming."})
        @Config.Name(value="Disable when swimming")
        public boolean disableWhenSwimming = false;
        @Config.Comment(value={"Enforces a more aggressive version of the swimming checks."})
        @Config.Name(value="Use aggressive swimming checks")
        public boolean useAggressiveSwimmingCheck = false;
        @Config.Comment(value={"Disables the mod when sneaking."})
        @Config.Name(value="Disable when sneaking")
        public boolean disableWhenSneaking = false;
        @Config.Comment(value={"Switches to vanilla arms when *any* item is held, not just conflict items."})
        @Config.Name(value="Use vanilla arms when holding any item")
        public boolean disableArmsWhenAnyItemHeld = false;
        @Config.Comment(value={"Disables rendering safety checks. May enable compatibility with mods that cause rendering exceptions, but cannot guarantee that the game will be stable."})
        @Config.Name(value="Ignore rendering errors (not recommended).")
        public boolean disableRenderErrorCatching = false;
        @Config.Comment(value={"Suppresses alerts about incompatible mods in chat on startup."})
        @Config.Name(value="Suppress startup compatibility alert (not recommended).")
        public boolean disableModCompatibilityAlerts = false;
    }

    public static class Preferences {
        @Config.Comment(value={"Enables/disables mod at startup.", "Default: true"})
        @Config.Name(value="Enable Mod")
        public boolean enableMod = true;
        @Config.Comment(value={"Enables/disables real arms at startup", "Default: true"})
        @Config.Name(value="Enable Real Arm Rendering")
        public boolean enableRealArms = true;
        @Config.Comment(value={"Enables/disables head turning at startup", "Default: false"})
        @Config.Name(value="Enable Head Turning")
        public boolean enableHeadTurning = false;
        @Config.Comment(value={"Enables/disables status messages when a keybind is pressed.", "Default: false"})
        @Config.Name(value="Enable Status Messages")
        public boolean enableStatusMessages = true;
        @Config.Comment(value={"How far behind the camera to put the first person player model", "Default: 0.35"})
        @Config.Name(value="Player Model Offset")
        @Config.RangeDouble(min=0.0, max=2.0)
        public double playerModelOffset = 0.35f;
    }
}

