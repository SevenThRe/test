/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.collect.ImmutableSortedMap
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.entity.EntityPlayerSP
 *  net.minecraft.client.network.NetworkPlayerInfo
 *  net.minecraft.client.renderer.entity.Render
 *  net.minecraft.client.renderer.entity.RenderHorse
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityLiving
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.entity.IEntityOwnable
 *  net.minecraft.entity.INpc
 *  net.minecraft.entity.monster.EntityGolem
 *  net.minecraft.entity.monster.IMob
 *  net.minecraft.entity.passive.EntityAnimal
 *  net.minecraft.entity.passive.EntityHorse
 *  net.minecraft.entity.passive.EntityVillager
 *  net.minecraft.entity.passive.EntityWaterMob
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.util.ResourceLocation
 *  net.minecraft.util.math.AxisAlignedBB
 *  net.minecraftforge.fml.client.FMLClientHandler
 */
package journeymap.client.model;

import com.google.common.collect.ImmutableSortedMap;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import journeymap.client.data.DataCache;
import journeymap.client.log.JMLogger;
import journeymap.client.log.StatTimer;
import journeymap.client.mod.impl.Pixelmon;
import journeymap.client.model.EntityDTO;
import journeymap.common.Journeymap;
import journeymap.common.feature.PlayerRadarManager;
import journeymap.common.log.LogFormatter;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.client.network.NetworkPlayerInfo;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderFacade;
import net.minecraft.client.renderer.entity.RenderHorse;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.IEntityOwnable;
import net.minecraft.entity.INpc;
import net.minecraft.entity.monster.EntityGolem;
import net.minecraft.entity.monster.IMob;
import net.minecraft.entity.passive.EntityAnimal;
import net.minecraft.entity.passive.EntityHorse;
import net.minecraft.entity.passive.EntityVillager;
import net.minecraft.entity.passive.EntityWaterMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraftforge.fml.client.FMLClientHandler;

public class EntityHelper {
    public static EntityDistanceComparator entityDistanceComparator = new EntityDistanceComparator();
    public static EntityDTODistanceComparator entityDTODistanceComparator = new EntityDTODistanceComparator();
    public static EntityMapComparator entityMapComparator = new EntityMapComparator();
    private static final String[] HORSE_TEXTURES = new String[]{"textures/entity/horse/horse_white.png", "textures/entity/horse/horse_creamy.png", "textures/entity/horse/horse_chestnut.png", "textures/entity/horse/horse_brown.png", "textures/entity/horse/horse_black.png", "textures/entity/horse/horse_gray.png", "textures/entity/horse/horse_darkbrown.png"};

    public static List<EntityDTO> getEntitiesNearby(String timerName, int maxEntities, boolean hostile, Class ... entityClasses) {
        StatTimer timer = StatTimer.get("EntityHelper." + timerName);
        timer.start();
        Minecraft mc = FMLClientHandler.instance().getClient();
        List<EntityDTO> list = new ArrayList<EntityDTO>();
        ArrayList allEntities = new ArrayList(mc.field_71441_e.field_72996_f);
        AxisAlignedBB bb = EntityHelper.getBB(mc.field_71439_g);
        try {
            block2: for (Entity entity : allEntities) {
                if (!(entity instanceof EntityLivingBase) || entity.field_70128_L || !entity.field_70175_ag || !bb.func_72326_a(entity.func_174813_aQ())) continue;
                for (Class entityClass : entityClasses) {
                    if (!entityClass.isAssignableFrom(entity.getClass())) continue;
                    EntityLivingBase entityLivingBase = (EntityLivingBase)entity;
                    EntityDTO dto = DataCache.INSTANCE.getEntityDTO(entityLivingBase);
                    dto.update(entityLivingBase, hostile);
                    list.add(dto);
                    continue block2;
                }
            }
            if (list.size() > maxEntities) {
                int before2 = list.size();
                EntityHelper.entityDTODistanceComparator.player = mc.field_71439_g;
                Collections.sort(list, entityDTODistanceComparator);
                list = list.subList(0, maxEntities);
            }
        }
        catch (Throwable t) {
            Journeymap.getLogger().warn("Failed to " + timerName + ": " + LogFormatter.toString(t));
        }
        timer.stop();
        return list;
    }

    public static List<EntityDTO> getMobsNearby() {
        return EntityHelper.getEntitiesNearby("getMobsNearby", Journeymap.getClient().getCoreProperties().maxMobsData.get(), true, IMob.class);
    }

    public static List<EntityDTO> getVillagersNearby() {
        return EntityHelper.getEntitiesNearby("getVillagersNearby", Journeymap.getClient().getCoreProperties().maxVillagersData.get(), false, EntityVillager.class, INpc.class);
    }

    public static List<EntityDTO> getAnimalsNearby() {
        return EntityHelper.getEntitiesNearby("getAnimalsNearby", Journeymap.getClient().getCoreProperties().maxAnimalsData.get(), false, EntityAnimal.class, EntityGolem.class, EntityWaterMob.class);
    }

    public static boolean isPassive(EntityLiving entityLiving) {
        if (entityLiving == null) {
            return false;
        }
        if (entityLiving instanceof IMob) {
            return false;
        }
        EntityLivingBase attackTarget = entityLiving.func_70638_az();
        return attackTarget == null || !(attackTarget instanceof EntityPlayer) && !(attackTarget instanceof IEntityOwnable);
    }

    public static List<EntityDTO> getPlayersNearby() {
        StatTimer timer = StatTimer.get("EntityHelper.getPlayersNearby");
        timer.start();
        Minecraft mc = FMLClientHandler.instance().getClient();
        List<EntityPlayer> allPlayers = new ArrayList();
        if (Journeymap.getClient().isPlayerTrackingEnabled() && !Minecraft.func_71410_x().func_71356_B()) {
            if (mc.func_147114_u().func_175106_d() != null && mc.func_147114_u().func_175106_d().size() > 1) {
                for (NetworkPlayerInfo onlinePlayer : mc.func_147114_u().func_175106_d()) {
                    EntityPlayer networkedPlayer;
                    if (onlinePlayer.func_178845_a().getId().equals(mc.field_71439_g.func_110124_au()) || (networkedPlayer = PlayerRadarManager.getInstance().getPlayers().get(onlinePlayer.func_178845_a().getId())) == null || networkedPlayer.field_71093_bK != Minecraft.func_71410_x().field_71439_g.field_71093_bK) continue;
                    allPlayers.add(networkedPlayer);
                }
            }
        } else {
            allPlayers.addAll(mc.field_71441_e.field_73010_i);
            allPlayers.remove(mc.field_71439_g);
        }
        int max = Journeymap.getClient().getCoreProperties().maxPlayersData.get();
        if (allPlayers.size() > max) {
            EntityHelper.entityDistanceComparator.player = mc.field_71439_g;
            Collections.sort(allPlayers, entityDistanceComparator);
            allPlayers = allPlayers.subList(0, max);
        }
        ArrayList<EntityDTO> playerDTOs = new ArrayList<EntityDTO>(allPlayers.size());
        for (EntityPlayer player : allPlayers) {
            EntityDTO dto = DataCache.INSTANCE.getEntityDTO((EntityLivingBase)player);
            dto.update((EntityLivingBase)player, false);
            playerDTOs.add(dto);
        }
        timer.stop();
        return playerDTOs;
    }

    private static AxisAlignedBB getBB(EntityPlayerSP player) {
        int lateralDistance = Journeymap.getClient().getCoreProperties().radarLateralDistance.get();
        int verticalDistance = Journeymap.getClient().getCoreProperties().radarVerticalDistance.get();
        return EntityHelper.getBoundingBox((EntityPlayer)player, lateralDistance, verticalDistance);
    }

    public static AxisAlignedBB getBoundingBox(EntityPlayer player, double lateralDistance, double verticalDistance) {
        return player.func_174813_aQ().func_72314_b(lateralDistance, verticalDistance, lateralDistance);
    }

    public static Map<String, EntityDTO> buildEntityIdMap(List<? extends EntityDTO> list, boolean sort) {
        if (list == null || list.isEmpty()) {
            return Collections.emptyMap();
        }
        if (sort) {
            Collections.sort(list, new EntityMapComparator());
        }
        LinkedHashMap<String, EntityDTO> idMap = new LinkedHashMap<String, EntityDTO>(list.size());
        for (EntityDTO entityDTO : list) {
            idMap.put("id" + entityDTO.entityId, entityDTO);
        }
        return ImmutableSortedMap.copyOf(idMap);
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    public static ResourceLocation getIconTextureLocation(Entity entity) {
        try {
            Render entityRender = FMLClientHandler.instance().getClient().func_175598_ae().func_78713_a(entity);
            ResourceLocation original = null;
            if (entityRender instanceof RenderHorse) {
                EntityHorse horse = (EntityHorse)entity;
                original = new ResourceLocation("minecraft", horse.func_110212_cp()[0]);
            } else if (Pixelmon.loaded) {
                original = Pixelmon.INSTANCE.getPixelmonResource(entity);
                if (original != null) return original;
                original = RenderFacade.getEntityTexture(entityRender, entity);
            } else {
                original = RenderFacade.getEntityTexture(entityRender, entity);
            }
            if (original == null) {
                JMLogger.logOnce("Can't get entityTexture for " + entity.getClass() + " via " + entityRender.getClass(), null);
                return null;
            }
            if (original.func_110623_a().contains("/entity/")) return new ResourceLocation(original.func_110624_b(), original.func_110623_a().replace("/entity/", "/entity_icon/"));
            return null;
        }
        catch (Throwable t) {
            JMLogger.logOnce("Can't get entityTexture for " + entity.func_70005_c_(), t);
            return null;
        }
    }

    private static class EntityDTODistanceComparator
    implements Comparator<EntityDTO> {
        EntityPlayer player;

        private EntityDTODistanceComparator() {
        }

        @Override
        public int compare(EntityDTO o1, EntityDTO o2) {
            EntityLivingBase e1 = (EntityLivingBase)o1.entityLivingRef.get();
            EntityLivingBase e2 = (EntityLivingBase)o2.entityLivingRef.get();
            if (e1 == null || e2 == null) {
                return 0;
            }
            return Double.compare(e1.func_70068_e((Entity)this.player), e2.func_70068_e((Entity)this.player));
        }
    }

    private static class EntityDistanceComparator
    implements Comparator<Entity> {
        EntityPlayer player;

        private EntityDistanceComparator() {
        }

        @Override
        public int compare(Entity o1, Entity o2) {
            return Double.compare(o1.func_70068_e((Entity)this.player), o2.func_70068_e((Entity)this.player));
        }
    }

    private static class EntityMapComparator
    implements Comparator<EntityDTO> {
        private EntityMapComparator() {
        }

        @Override
        public int compare(EntityDTO o1, EntityDTO o2) {
            Integer n;
            Integer n2;
            Integer o1rank = 0;
            Integer o2rank = 0;
            if (o1.customName != null) {
                n2 = o1rank;
                n = o1rank = Integer.valueOf(o1rank + 1);
            } else if (o1.username != null) {
                o1rank = o1rank + 2;
            }
            if (o2.customName != null) {
                n2 = o2rank;
                n = o2rank = Integer.valueOf(o2rank + 1);
            } else if (o2.username != null) {
                o2rank = o2rank + 2;
            }
            return o1rank.compareTo(o2rank);
        }
    }
}

