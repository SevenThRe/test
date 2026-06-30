/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.entity.AbstractClientPlayer
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.util.math.MathHelper
 *  net.minecraft.util.math.RayTraceResult
 *  net.minecraft.util.math.RayTraceResult$Type
 *  net.minecraft.util.math.Vec3d
 */
package baka.animation.instruct;

import baka.animation.instruct.InstructBase;
import baka.client.network.NetworkHandler;
import goblinbob.mobends.standard.data.PlayerData;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.AbstractClientPlayer;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.Vec3d;

public class DamageInstruct
extends InstructBase {
    protected String actionName;
    protected float distance;
    protected float arc;

    public DamageInstruct(String actionName, float distance, float arc) {
        this.actionName = actionName;
        this.distance = distance;
        this.arc = arc;
    }

    @Override
    public void perform(PlayerData data) {
        super.perform(data);
        if (((AbstractClientPlayer)data.getEntity()).getUniqueID().equals(Minecraft.getMinecraft().player.getUniqueID())) {
            if (Minecraft.getMinecraft().isCallingFromMinecraftThread()) {
                List<EntityLivingBase> entityLivingBases = this.collectEntities(Minecraft.getMinecraft().getRenderViewEntity(), this.distance, this.arc);
                entityLivingBases = this.filterWall(entityLivingBases);
                List<UUID> collect = entityLivingBases.stream().map(Entity::getUniqueID).collect(Collectors.toList());
                NetworkHandler.damageEvent(this.actionName, collect);
            } else {
                Minecraft.getMinecraft().addScheduledTask(() -> {
                    List<EntityLivingBase> entityLivingBases = this.collectEntities(Minecraft.getMinecraft().getRenderViewEntity(), this.distance, this.arc);
                    entityLivingBases = this.filterWall(entityLivingBases);
                    List<UUID> collect = entityLivingBases.stream().map(Entity::getUniqueID).collect(Collectors.toList());
                    NetworkHandler.damageEvent(this.actionName, collect);
                });
            }
        }
    }

    protected final Vec3d getVectorForRotation(float pitch, float yaw) {
        float f2 = MathHelper.cos((float)(-yaw * ((float)Math.PI / 180) - (float)Math.PI));
        float f1 = MathHelper.sin((float)(-yaw * ((float)Math.PI / 180) - (float)Math.PI));
        float f22 = -MathHelper.cos((float)(-pitch * ((float)Math.PI / 180)));
        float f3 = MathHelper.sin((float)(-pitch * ((float)Math.PI / 180)));
        return new Vec3d((double)(f1 * f22), (double)f3, (double)(f2 * f22));
    }

    protected List<EntityLivingBase> filterWall(List<EntityLivingBase> entities) {
        ArrayList<EntityLivingBase> list = new ArrayList<EntityLivingBase>();
        Entity player = Minecraft.getMinecraft().getRenderViewEntity();
        Vec3d vec3d = new Vec3d(player.posX, player.posY + (double)(player.getEyeHeight() * 0.7f), player.posZ);
        for (EntityLivingBase entity : entities) {
            Vec3d vec3d1 = new Vec3d(entity.posX, player.posY + (double)(player.getEyeHeight() * 0.7f), entity.posZ);
            RayTraceResult raytraceresult = player.world.rayTraceBlocks(vec3d, vec3d1, false, true, false);
            if (raytraceresult != null && raytraceresult.typeOfHit == RayTraceResult.Type.BLOCK || player.posY - (entity.posY + (double)entity.height) > 1.0) continue;
            if (entity.posY - (player.posY + (double)player.height) > 1.0) {
                // empty if block
            }
            list.add(entity);
        }
        return list;
    }

    protected List<EntityLivingBase> collectEntities(Entity player, float range, float arc) {
        ArrayList<EntityLivingBase> list = new ArrayList<EntityLivingBase>();
        if (player != null) {
            List entitiesWithinAABBExcludingEntity = player.world.getEntitiesWithinAABBExcludingEntity(player, player.getEntityBoundingBox().grow((double)range));
            Vec3d dir = this.getVectorForRotation(0.0f, player.rotationYaw);
            dir = new Vec3d(dir.x, 0.0, dir.z);
            double cos = Math.cos((double)arc * Math.PI / 180.0);
            double cosSq = cos * cos;
            for (Entity entity : entitiesWithinAABBExcludingEntity) {
                if (!(entity instanceof EntityLivingBase)) continue;
                EntityLivingBase entityLivingBase = (EntityLivingBase)entity;
                if (arc >= 360.0f) {
                    list.add(entityLivingBase);
                    continue;
                }
                Vec3d relative = entityLivingBase.getPositionVector().subtract(player.getPositionVector());
                relative = new Vec3d(relative.x, 0.0, relative.z);
                double dot = relative.dotProduct(dir);
                double value = dot * dot / relative.lengthSquared();
                if ((double)arc < 180.0 && dot > 0.0 && value >= cosSq) {
                    list.add(entityLivingBase);
                    continue;
                }
                if (arc < 180.0f || dot <= 0.0 && dot > cosSq) continue;
                list.add(entityLivingBase);
            }
        }
        return list;
    }
}

