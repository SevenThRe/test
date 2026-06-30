/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.base.Strings
 *  com.google.common.cache.CacheLoader
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.entity.EntityPlayerSP
 *  net.minecraft.client.network.NetHandlerPlayClient
 *  net.minecraft.client.network.NetworkPlayerInfo
 *  net.minecraft.client.resources.DefaultPlayerSkin
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityLiving
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.entity.IEntityOwnable
 *  net.minecraft.entity.INpc
 *  net.minecraft.entity.passive.EntityHorse
 *  net.minecraft.entity.passive.EntityTameable
 *  net.minecraft.entity.passive.EntityVillager
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.scoreboard.ScorePlayerTeam
 *  net.minecraft.util.ResourceLocation
 *  net.minecraft.util.StringUtils
 *  net.minecraftforge.fml.client.FMLClientHandler
 */
package journeymap.client.model;

import com.google.common.base.Strings;
import com.google.common.cache.CacheLoader;
import java.io.Serializable;
import java.lang.ref.WeakReference;
import java.util.UUID;
import journeymap.client.model.EntityHelper;
import journeymap.client.properties.CoreProperties;
import journeymap.common.Journeymap;
import journeymap.common.log.LogFormatter;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.client.network.NetHandlerPlayClient;
import net.minecraft.client.network.NetworkPlayerInfo;
import net.minecraft.client.resources.DefaultPlayerSkin;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.IEntityOwnable;
import net.minecraft.entity.INpc;
import net.minecraft.entity.passive.EntityHorse;
import net.minecraft.entity.passive.EntityTameable;
import net.minecraft.entity.passive.EntityVillager;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.scoreboard.ScorePlayerTeam;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.StringUtils;
import net.minecraftforge.fml.client.FMLClientHandler;

public class EntityDTO
implements Serializable {
    public final String entityId;
    public transient WeakReference<EntityLivingBase> entityLivingRef;
    public transient ResourceLocation entityIconLocation;
    public String iconLocation;
    public Boolean hostile;
    public double posX;
    public double posY;
    public double posZ;
    public int chunkCoordX;
    public int chunkCoordY;
    public int chunkCoordZ;
    public double heading;
    public String customName;
    public String owner;
    public String profession;
    public String username;
    public String biome;
    public int dimension;
    public Boolean underground;
    public boolean invisible;
    public boolean sneaking;
    public boolean passiveAnimal;
    public boolean npc;
    public int color;

    private EntityDTO(EntityLivingBase entity) {
        this.entityLivingRef = new WeakReference<EntityLivingBase>(entity);
        this.entityId = entity.getUniqueID().toString();
    }

    public void update(EntityLivingBase entity, boolean hostile) {
        EntityLivingBase ownerEntity;
        Minecraft mc = Minecraft.getMinecraft();
        EntityPlayerSP currentPlayer = FMLClientHandler.instance().getClient().player;
        this.dimension = entity.dimension;
        this.posX = entity.posX;
        this.posY = entity.posY;
        this.posZ = entity.posZ;
        this.chunkCoordX = entity.chunkCoordX;
        this.chunkCoordY = entity.chunkCoordY;
        this.chunkCoordZ = entity.chunkCoordZ;
        this.heading = Math.round(entity.rotationYawHead % 360.0f);
        this.invisible = currentPlayer != null ? entity.isInvisibleToPlayer((EntityPlayer)currentPlayer) : false;
        this.sneaking = entity.isSneaking();
        CoreProperties coreProperties = Journeymap.getClient().getCoreProperties();
        ResourceLocation entityIcon = null;
        int playerColor = coreProperties.getColor(coreProperties.colorPlayer);
        ScorePlayerTeam team = null;
        try {
            team = mc.world.getScoreboard().getPlayersTeam(entity.getCachedUniqueIdString());
        }
        catch (Throwable throwable) {
            // empty catch block
        }
        if (entity instanceof EntityPlayer) {
            String name;
            this.username = name = StringUtils.stripControlCodes((String)entity.getName());
            try {
                playerColor = team != null ? team.getColor().getColorIndex() : (currentPlayer.equals((Object)entity) ? coreProperties.getColor(coreProperties.colorSelf) : coreProperties.getColor(coreProperties.colorPlayer));
            }
            catch (Throwable throwable) {
                // empty catch block
            }
            entityIcon = DefaultPlayerSkin.getDefaultSkinLegacy();
            try {
                NetHandlerPlayClient client = Minecraft.getMinecraft().getConnection();
                NetworkPlayerInfo info = client.getPlayerInfo(entity.getUniqueID());
                if (info != null) {
                    entityIcon = info.getLocationSkin();
                }
            }
            catch (Throwable t) {
                Journeymap.getLogger().error("Error looking up player skin: " + LogFormatter.toPartialString(t));
            }
        } else {
            this.username = null;
            entityIcon = EntityHelper.getIconTextureLocation((Entity)entity);
        }
        if (entityIcon != null) {
            this.entityIconLocation = entityIcon;
            this.iconLocation = entityIcon.toString();
        }
        String owner = null;
        if (entity instanceof EntityTameable) {
            ownerEntity = ((EntityTameable)entity).getOwner();
            if (ownerEntity != null) {
                owner = ownerEntity.getName();
            }
        } else if (entity instanceof IEntityOwnable) {
            ownerEntity = ((IEntityOwnable)entity).getOwner();
            if (ownerEntity != null) {
                owner = ownerEntity.getName();
            }
        } else if (entity instanceof EntityHorse) {
            UUID ownerUuid = ((EntityHorse)entity).getOwnerUniqueId();
            if (currentPlayer != null && ownerUuid != null) {
                try {
                    String playerUuid = currentPlayer.getUniqueID().toString();
                    if (playerUuid.equals(ownerUuid)) {
                        owner = currentPlayer.getName();
                    }
                }
                catch (Throwable t) {
                    t.printStackTrace();
                }
            }
        }
        this.owner = owner;
        String customName = null;
        boolean passive = false;
        if (entity instanceof EntityLiving) {
            EntityLivingBase attackTarget;
            EntityLiving entityLiving = (EntityLiving)entity;
            if (entity.hasCustomName() && entityLiving.getAlwaysRenderNameTag()) {
                customName = StringUtils.stripControlCodes((String)((EntityLiving)entity).getCustomNameTag());
            }
            if (!hostile && currentPlayer != null && (attackTarget = ((EntityLiving)entity).getAttackTarget()) != null && attackTarget.getUniqueID().equals(currentPlayer.getUniqueID())) {
                hostile = true;
            }
            if (EntityHelper.isPassive((EntityLiving)entity)) {
                passive = true;
            }
        }
        if (entity instanceof EntityVillager) {
            EntityVillager villager = (EntityVillager)entity;
            this.profession = villager.getProfessionForge().getCareer(villager.careerId).getName();
        } else if (entity instanceof INpc) {
            this.npc = true;
            this.profession = null;
            this.passiveAnimal = false;
        } else {
            this.profession = null;
            this.passiveAnimal = passive;
        }
        this.customName = customName;
        this.hostile = hostile;
        this.color = entity instanceof EntityPlayer ? playerColor : (team != null ? team.getColor().getColorIndex() : (!Strings.isNullOrEmpty((String)owner) ? coreProperties.getColor(coreProperties.colorPet) : (this.profession != null || this.npc ? coreProperties.getColor(coreProperties.colorVillager) : (hostile ? coreProperties.getColor(coreProperties.colorHostile) : coreProperties.getColor(coreProperties.colorPassive)))));
    }

    public static class SimpleCacheLoader
    extends CacheLoader<EntityLivingBase, EntityDTO> {
        public EntityDTO load(EntityLivingBase entity) throws Exception {
            return new EntityDTO(entity);
        }
    }
}

