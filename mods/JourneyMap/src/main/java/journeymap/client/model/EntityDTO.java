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
        this.entityId = entity.func_110124_au().toString();
    }

    public void update(EntityLivingBase entity, boolean hostile) {
        EntityLivingBase ownerEntity;
        Minecraft mc = Minecraft.func_71410_x();
        EntityPlayerSP currentPlayer = FMLClientHandler.instance().getClient().field_71439_g;
        this.dimension = entity.field_71093_bK;
        this.posX = entity.field_70165_t;
        this.posY = entity.field_70163_u;
        this.posZ = entity.field_70161_v;
        this.chunkCoordX = entity.field_70176_ah;
        this.chunkCoordY = entity.field_70162_ai;
        this.chunkCoordZ = entity.field_70164_aj;
        this.heading = Math.round(entity.field_70759_as % 360.0f);
        this.invisible = currentPlayer != null ? entity.func_98034_c((EntityPlayer)currentPlayer) : false;
        this.sneaking = entity.func_70093_af();
        CoreProperties coreProperties = Journeymap.getClient().getCoreProperties();
        ResourceLocation entityIcon = null;
        int playerColor = coreProperties.getColor(coreProperties.colorPlayer);
        ScorePlayerTeam team = null;
        try {
            team = mc.field_71441_e.func_96441_U().func_96509_i(entity.func_189512_bd());
        }
        catch (Throwable throwable) {
            // empty catch block
        }
        if (entity instanceof EntityPlayer) {
            String name;
            this.username = name = StringUtils.func_76338_a((String)entity.func_70005_c_());
            try {
                playerColor = team != null ? team.func_178775_l().func_175746_b() : (currentPlayer.equals((Object)entity) ? coreProperties.getColor(coreProperties.colorSelf) : coreProperties.getColor(coreProperties.colorPlayer));
            }
            catch (Throwable throwable) {
                // empty catch block
            }
            entityIcon = DefaultPlayerSkin.func_177335_a();
            try {
                NetHandlerPlayClient client = Minecraft.func_71410_x().func_147114_u();
                NetworkPlayerInfo info = client.func_175102_a(entity.func_110124_au());
                if (info != null) {
                    entityIcon = info.func_178837_g();
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
            ownerEntity = ((EntityTameable)entity).func_70902_q();
            if (ownerEntity != null) {
                owner = ownerEntity.func_70005_c_();
            }
        } else if (entity instanceof IEntityOwnable) {
            ownerEntity = ((IEntityOwnable)entity).func_70902_q();
            if (ownerEntity != null) {
                owner = ownerEntity.func_70005_c_();
            }
        } else if (entity instanceof EntityHorse) {
            UUID ownerUuid = ((EntityHorse)entity).func_184780_dh();
            if (currentPlayer != null && ownerUuid != null) {
                try {
                    String playerUuid = currentPlayer.func_110124_au().toString();
                    if (playerUuid.equals(ownerUuid)) {
                        owner = currentPlayer.func_70005_c_();
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
            if (entity.func_145818_k_() && entityLiving.func_174833_aM()) {
                customName = StringUtils.func_76338_a((String)((EntityLiving)entity).func_95999_t());
            }
            if (!hostile && currentPlayer != null && (attackTarget = ((EntityLiving)entity).func_70638_az()) != null && attackTarget.func_110124_au().equals(currentPlayer.func_110124_au())) {
                hostile = true;
            }
            if (EntityHelper.isPassive((EntityLiving)entity)) {
                passive = true;
            }
        }
        if (entity instanceof EntityVillager) {
            EntityVillager villager = (EntityVillager)entity;
            this.profession = villager.getProfessionForge().getCareer(villager.field_175563_bv).getName();
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
        this.color = entity instanceof EntityPlayer ? playerColor : (team != null ? team.func_178775_l().func_175746_b() : (!Strings.isNullOrEmpty((String)owner) ? coreProperties.getColor(coreProperties.colorPet) : (this.profession != null || this.npc ? coreProperties.getColor(coreProperties.colorVillager) : (hostile ? coreProperties.getColor(coreProperties.colorHostile) : coreProperties.getColor(coreProperties.colorPassive)))));
    }

    public static class SimpleCacheLoader
    extends CacheLoader<EntityLivingBase, EntityDTO> {
        public EntityDTO load(EntityLivingBase entity) throws Exception {
            return new EntityDTO(entity);
        }
    }
}

