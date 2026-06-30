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
        if (((AbstractClientPlayer)data.getEntity()).func_110124_au().equals(Minecraft.func_71410_x().field_71439_g.func_110124_au())) {
            if (Minecraft.func_71410_x().func_152345_ab()) {
                List<EntityLivingBase> entityLivingBases = this.collectEntities(Minecraft.func_71410_x().func_175606_aa(), this.distance, this.arc);
                entityLivingBases = this.filterWall(entityLivingBases);
                List<UUID> collect = entityLivingBases.stream().map(Entity::func_110124_au).collect(Collectors.toList());
                NetworkHandler.damageEvent(this.actionName, collect);
            } else {
                Minecraft.func_71410_x().func_152344_a(() -> {
                    List<EntityLivingBase> entityLivingBases = this.collectEntities(Minecraft.func_71410_x().func_175606_aa(), this.distance, this.arc);
                    entityLivingBases = this.filterWall(entityLivingBases);
                    List<UUID> collect = entityLivingBases.stream().map(Entity::func_110124_au).collect(Collectors.toList());
                    NetworkHandler.damageEvent(this.actionName, collect);
                });
            }
        }
    }

    protected final Vec3d getVectorForRotation(float pitch, float yaw) {
        float f2 = MathHelper.func_76134_b((float)(-yaw * ((float)Math.PI / 180) - (float)Math.PI));
        float f1 = MathHelper.func_76126_a((float)(-yaw * ((float)Math.PI / 180) - (float)Math.PI));
        float f22 = -MathHelper.func_76134_b((float)(-pitch * ((float)Math.PI / 180)));
        float f3 = MathHelper.func_76126_a((float)(-pitch * ((float)Math.PI / 180)));
        return new Vec3d((double)(f1 * f22), (double)f3, (double)(f2 * f22));
    }

    protected List<EntityLivingBase> filterWall(List<EntityLivingBase> entities) {
        ArrayList<EntityLivingBase> list = new ArrayList<EntityLivingBase>();
        Entity player = Minecraft.func_71410_x().func_175606_aa();
        Vec3d vec3d = new Vec3d(player.field_70165_t, player.field_70163_u + (double)(player.func_70047_e() * 0.7f), player.field_70161_v);
        for (EntityLivingBase entity : entities) {
            Vec3d vec3d1 = new Vec3d(entity.field_70165_t, player.field_70163_u + (double)(player.func_70047_e() * 0.7f), entity.field_70161_v);
            RayTraceResult raytraceresult = player.field_70170_p.func_147447_a(vec3d, vec3d1, false, true, false);
            if (raytraceresult != null && raytraceresult.field_72313_a == RayTraceResult.Type.BLOCK || player.field_70163_u - (entity.field_70163_u + (double)entity.field_70131_O) > 1.0) continue;
            if (entity.field_70163_u - (player.field_70163_u + (double)player.field_70131_O) > 1.0) {
                // empty if block
            }
            list.add(entity);
        }
        return list;
    }

    protected List<EntityLivingBase> collectEntities(Entity player, float range, float arc) {
        ArrayList<EntityLivingBase> list = new ArrayList<EntityLivingBase>();
        if (player != null) {
            List entitiesWithinAABBExcludingEntity = player.field_70170_p.func_72839_b(player, player.func_174813_aQ().func_186662_g((double)range));
            Vec3d dir = this.getVectorForRotation(0.0f, player.field_70177_z);
            dir = new Vec3d(dir.field_72450_a, 0.0, dir.field_72449_c);
            double cos = Math.cos((double)arc * Math.PI / 180.0);
            double cosSq = cos * cos;
            for (Entity entity : entitiesWithinAABBExcludingEntity) {
                if (!(entity instanceof EntityLivingBase)) continue;
                EntityLivingBase entityLivingBase = (EntityLivingBase)entity;
                if (arc >= 360.0f) {
                    list.add(entityLivingBase);
                    continue;
                }
                Vec3d relative = entityLivingBase.func_174791_d().func_178788_d(player.func_174791_d());
                relative = new Vec3d(relative.field_72450_a, 0.0, relative.field_72449_c);
                double dot = relative.func_72430_b(dir);
                double value = dot * dot / relative.func_189985_c();
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

