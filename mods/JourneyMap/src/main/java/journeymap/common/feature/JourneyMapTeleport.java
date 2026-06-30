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
            creative = ((EntityPlayerMP)entity).field_71075_bZ.field_75098_d;
            cheatMode = mcServer.func_184103_al().func_152596_g(new GameProfile(entity.func_110124_au(), entity.func_70005_c_()));
            if (this.isTeleportAvailable(entity, location) || creative || cheatMode || JourneymapServer.isOp((EntityPlayer)((EntityPlayerMP)entity))) {
                if (mcServer == null) {
                    entity.func_145747_a((ITextComponent)new TextComponentString("Cannot Find World"));
                    return false;
                }
                if (!entity.func_70089_S()) {
                    entity.func_145747_a((ITextComponent)new TextComponentString("Cannot teleport when dead."));
                    return false;
                }
                WorldServer destinationWorld = mcServer.func_71218_a(location.getDim());
                if (destinationWorld == null) {
                    entity.func_145747_a((ITextComponent)new TextComponentString("Could not get world for Dimension " + location.getDim()));
                    return false;
                }
                return this.teleportEntity(mcServer, (World)destinationWorld, entity, location, entity.field_70177_z);
            }
            entity.func_145747_a((ITextComponent)new TextComponentString("Server has disabled JourneyMap teleport usage for your current or destination dimension."));
            return false;
        }
        return false;
    }

    private boolean isTeleportAvailable(Entity entity, Location location) {
        DimensionProperties destinationProperty = PropertiesManager.getInstance().getDimProperties(location.getDim());
        DimensionProperties entityLocationProperty = PropertiesManager.getInstance().getDimProperties(entity.field_71093_bK);
        return this.canDimTeleport(destinationProperty) && this.canDimTeleport(entityLocationProperty);
    }

    private boolean canDimTeleport(DimensionProperties properties) {
        if (properties.enabled.get().booleanValue()) {
            return properties.teleportEnabled.get();
        }
        return PropertiesManager.getInstance().getGlobalProperties().teleportEnabled.get();
    }

    private boolean teleportEntity(MinecraftServer server, World destinationWorld, Entity entity, Location location, float yaw) {
        World startWorld = entity.field_70170_p;
        boolean changedWorld = startWorld != destinationWorld;
        PlayerList playerList = server.func_184103_al();
        if (entity instanceof EntityPlayerMP) {
            EntityPlayerMP player = (EntityPlayerMP)entity;
            player.func_184210_p();
            if (changedWorld) {
                player.field_71093_bK = location.getDim();
                player.field_71135_a.func_147359_a((Packet)new SPacketRespawn(player.field_71093_bK, player.field_70170_p.func_175659_aa(), destinationWorld.func_72912_H().func_76067_t(), player.field_71134_c.func_73081_b()));
                playerList.func_187243_f(player);
                startWorld.func_72973_f((Entity)player);
                player.field_70128_L = false;
                this.transferPlayerToWorld((Entity)player, (WorldServer)destinationWorld);
                playerList.func_72375_a(player, (WorldServer)startWorld);
                player.field_71135_a.func_147364_a(location.getX() + 0.5, location.getY(), location.getZ() + 0.5, yaw, entity.field_70125_A);
                player.field_71134_c.func_73080_a((WorldServer)destinationWorld);
                player.field_71135_a.func_147359_a((Packet)new SPacketPlayerAbilities(player.field_71075_bZ));
                playerList.func_72354_b(player, (WorldServer)destinationWorld);
                playerList.func_72385_f(player);
                for (PotionEffect potioneffect : player.func_70651_bq()) {
                    player.field_71135_a.func_147359_a((Packet)new SPacketEntityEffect(player.func_145782_y(), potioneffect));
                }
                FMLCommonHandler.instance().firePlayerChangedDimensionEvent((EntityPlayer)player, player.field_71093_bK, location.getDim());
                return true;
            }
            player.field_71135_a.func_147364_a(location.getX() + 0.5, location.getY(), location.getZ() + 0.5, yaw, entity.field_70125_A);
            ((WorldServer)destinationWorld).func_72863_F().func_186028_c((int)location.getX() >> 4, (int)location.getZ() >> 4);
            return true;
        }
        return false;
    }

    private void transferPlayerToWorld(Entity entity, WorldServer toWorldIn) {
        entity.func_70012_b(entity.field_70165_t + 0.5, entity.field_70163_u, entity.field_70161_v + 0.5, entity.field_70177_z, entity.field_70125_A);
        toWorldIn.func_72838_d(entity);
        toWorldIn.func_72866_a(entity, false);
        entity.func_70029_a((World)toWorldIn);
    }
}

