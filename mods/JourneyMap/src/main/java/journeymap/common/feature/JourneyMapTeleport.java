/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.mojang.authlib.GameProfile
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.entity.player.EntityPlayerMP
 *  net.minecraft.network.Packet
 *  net.minecraft.network.play.server.SPacketEntityEffect
 *  net.minecraft.network.play.server.SPacketPlayerAbilities
 *  net.minecraft.network.play.server.SPacketRespawn
 *  net.minecraft.potion.PotionEffect
 *  net.minecraft.server.MinecraftServer
 *  net.minecraft.server.management.PlayerList
 *  net.minecraft.util.text.ITextComponent
 *  net.minecraft.util.text.TextComponentString
 *  net.minecraft.world.World
 *  net.minecraft.world.WorldServer
 *  net.minecraftforge.fml.common.FMLCommonHandler
 */
package journeymap.common.feature;

import com.mojang.authlib.GameProfile;
import journeymap.common.Journeymap;
import journeymap.common.feature.Location;
import journeymap.server.JourneymapServer;
import journeymap.server.properties.DimensionProperties;
import journeymap.server.properties.PropertiesManager;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.SPacketEntityEffect;
import net.minecraft.network.play.server.SPacketPlayerAbilities;
import net.minecraft.network.play.server.SPacketRespawn;
import net.minecraft.potion.PotionEffect;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.management.PlayerList;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;
import net.minecraftforge.fml.common.FMLCommonHandler;

public class JourneyMapTeleport {
    private static final JourneyMapTeleport INSTANCE = new JourneyMapTeleport();

    private JourneyMapTeleport() {
    }

    public static JourneyMapTeleport instance() {
        return INSTANCE;
    }

    public boolean attemptTeleport(Entity entity, Location location) {
        MinecraftServer mcServer = FMLCommonHandler.instance().getMinecraftServerInstance();
        boolean creative = false;
        boolean cheatMode = false;
        if (entity == null) {
            Journeymap.getLogger().error("Attempted to teleport null entity.");
            return false;
        }
        if (entity instanceof EntityPlayerMP) {
            creative = ((EntityPlayerMP)entity).capabilities.isCreativeMode;
            cheatMode = mcServer.getPlayerList().canSendCommands(new GameProfile(entity.getUniqueID(), entity.getName()));
            if (this.isTeleportAvailable(entity, location) || creative || cheatMode || JourneymapServer.isOp((EntityPlayer)((EntityPlayerMP)entity))) {
                if (mcServer == null) {
                    entity.sendMessage((ITextComponent)new TextComponentString("Cannot Find World"));
                    return false;
                }
                if (!entity.isEntityAlive()) {
                    entity.sendMessage((ITextComponent)new TextComponentString("Cannot teleport when dead."));
                    return false;
                }
                WorldServer destinationWorld = mcServer.getWorld(location.getDim());
                if (destinationWorld == null) {
                    entity.sendMessage((ITextComponent)new TextComponentString("Could not get world for Dimension " + location.getDim()));
                    return false;
                }
                return this.teleportEntity(mcServer, (World)destinationWorld, entity, location, entity.rotationYaw);
            }
            entity.sendMessage((ITextComponent)new TextComponentString("Server has disabled JourneyMap teleport usage for your current or destination dimension."));
            return false;
        }
        return false;
    }

    private boolean isTeleportAvailable(Entity entity, Location location) {
        DimensionProperties destinationProperty = PropertiesManager.getInstance().getDimProperties(location.getDim());
        DimensionProperties entityLocationProperty = PropertiesManager.getInstance().getDimProperties(entity.dimension);
        return this.canDimTeleport(destinationProperty) && this.canDimTeleport(entityLocationProperty);
    }

    private boolean canDimTeleport(DimensionProperties properties) {
        if (properties.enabled.get().booleanValue()) {
            return properties.teleportEnabled.get();
        }
        return PropertiesManager.getInstance().getGlobalProperties().teleportEnabled.get();
    }

    private boolean teleportEntity(MinecraftServer server, World destinationWorld, Entity entity, Location location, float yaw) {
        World startWorld = entity.world;
        boolean changedWorld = startWorld != destinationWorld;
        PlayerList playerList = server.getPlayerList();
        if (entity instanceof EntityPlayerMP) {
            EntityPlayerMP player = (EntityPlayerMP)entity;
            player.dismountRidingEntity();
            if (changedWorld) {
                player.dimension = location.getDim();
                player.connection.sendPacket((Packet)new SPacketRespawn(player.dimension, player.world.getDifficulty(), destinationWorld.getWorldInfo().getTerrainType(), player.interactionManager.getGameType()));
                playerList.updatePermissionLevel(player);
                startWorld.removeEntityDangerously((Entity)player);
                player.isDead = false;
                this.transferPlayerToWorld((Entity)player, (WorldServer)destinationWorld);
                playerList.preparePlayer(player, (WorldServer)startWorld);
                player.connection.setPlayerLocation(location.getX() + 0.5, location.getY(), location.getZ() + 0.5, yaw, entity.rotationPitch);
                player.interactionManager.setWorld((WorldServer)destinationWorld);
                player.connection.sendPacket((Packet)new SPacketPlayerAbilities(player.capabilities));
                playerList.updateTimeAndWeatherForPlayer(player, (WorldServer)destinationWorld);
                playerList.syncPlayerInventory(player);
                for (PotionEffect potioneffect : player.getActivePotionEffects()) {
                    player.connection.sendPacket((Packet)new SPacketEntityEffect(player.getEntityId(), potioneffect));
                }
                FMLCommonHandler.instance().firePlayerChangedDimensionEvent((EntityPlayer)player, player.dimension, location.getDim());
                return true;
            }
            player.connection.setPlayerLocation(location.getX() + 0.5, location.getY(), location.getZ() + 0.5, yaw, entity.rotationPitch);
            ((WorldServer)destinationWorld).getChunkProvider().loadChunk((int)location.getX() >> 4, (int)location.getZ() >> 4);
            return true;
        }
        return false;
    }

    private void transferPlayerToWorld(Entity entity, WorldServer toWorldIn) {
        entity.setLocationAndAngles(entity.posX + 0.5, entity.posY, entity.posZ + 0.5, entity.rotationYaw, entity.rotationPitch);
        toWorldIn.spawnEntity(entity);
        toWorldIn.updateEntityWithOptionalForce(entity, false);
        entity.setWorld((World)toWorldIn);
    }
}

