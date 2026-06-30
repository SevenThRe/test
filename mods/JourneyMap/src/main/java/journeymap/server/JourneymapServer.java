/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.mojang.authlib.GameProfile
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.entity.player.EntityPlayerMP
 *  net.minecraft.server.MinecraftServer
 *  net.minecraftforge.common.MinecraftForge
 *  net.minecraftforge.fml.common.FMLCommonHandler
 *  net.minecraftforge.fml.common.Mod$EventHandler
 *  net.minecraftforge.fml.common.event.FMLInitializationEvent
 *  net.minecraftforge.fml.common.event.FMLPostInitializationEvent
 *  net.minecraftforge.fml.common.event.FMLPreInitializationEvent
 *  net.minecraftforge.fml.relauncher.Side
 *  net.minecraftforge.fml.relauncher.SideOnly
 *  net.minecraftforge.fml.server.FMLServerHandler
 *  org.apache.logging.log4j.Logger
 */
package journeymap.server;

import com.mojang.authlib.GameProfile;
import java.util.Map;
import java.util.UUID;
import journeymap.common.CommonProxy;
import journeymap.common.Journeymap;
import journeymap.common.version.Version;
import journeymap.server.Constants;
import journeymap.server.config.ForgeConfig;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.server.MinecraftServer;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraftforge.fml.server.FMLServerHandler;
import org.apache.logging.log4j.Logger;

public class JourneymapServer
implements CommonProxy {
    private Logger logger = Journeymap.getLogger();
    public static boolean DEV_MODE = false;
    private static final Version MINIMUM_ACCEPTABLE_VERSION = new Version(5, 7, 0);

    @Override
    @SideOnly(value=Side.SERVER)
    @Mod.EventHandler
    public void preInitialize(FMLPreInitializationEvent event) {
        MinecraftForge.EVENT_BUS.register((Object)new ForgeConfig());
    }

    @Override
    public void initialize(FMLInitializationEvent event) {
    }

    @Override
    @SideOnly(value=Side.SERVER)
    public void postInitialize(FMLPostInitializationEvent event) {
    }

    @Override
    public boolean checkModLists(Map<String, String> modList, Side side) {
        this.logger.info(side.toString());
        for (String s : modList.keySet()) {
            if (!s.toLowerCase().equals("journeymap") && !s.toLowerCase().equals("journeymap@")) continue;
            if (modList.get(s).contains("@")) {
                this.logger.info("Mod check = dev environment");
                DEV_MODE = true;
                return true;
            }
            String version = modList.get(s).split("-")[1];
            Version userLoggedInVersion = Version.from(version, null);
            if (MINIMUM_ACCEPTABLE_VERSION.isNewerThan(userLoggedInVersion)) {
                this.logger.info("Version Mismatch need " + MINIMUM_ACCEPTABLE_VERSION.toString() + " or higher. Current version attempt -> " + modList.get(s));
                return false;
            }
            return true;
        }
        return true;
    }

    @Override
    public boolean isUpdateCheckEnabled() {
        return false;
    }

    public static boolean isOp(EntityPlayer player) {
        String[] ops;
        if (Side.CLIENT.equals((Object)FMLCommonHandler.instance().getSide())) {
            MinecraftServer mcServer = FMLCommonHandler.instance().getMinecraftServerInstance();
            boolean creative = ((EntityPlayerMP)player).capabilities.isCreativeMode;
            boolean cheatMode = mcServer.getPlayerList().canSendCommands(new GameProfile(player.getUniqueID(), player.getName()));
            return creative || cheatMode;
        }
        for (String opName : ops = FMLServerHandler.instance().getServer().getPlayerList().getOppedPlayerNames()) {
            UUID opId = FMLServerHandler.instance().getServer().getPlayerList().getOppedPlayers().getGameProfileFromName(opName).getId();
            if (!player.getDisplayNameString().equalsIgnoreCase(opName) && !player.getUniqueID().equals(opId) && !Constants.debugOverride((Entity)player)) continue;
            return true;
        }
        return false;
    }
}

