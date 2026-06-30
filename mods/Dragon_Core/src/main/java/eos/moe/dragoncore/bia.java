/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.util.EnumHand
 *  net.minecraftforge.event.entity.ProjectileImpactEvent
 *  net.minecraftforge.event.entity.player.AttackEntityEvent
 *  net.minecraftforge.event.entity.player.PlayerInteractEvent$EntityInteractSpecific
 *  net.minecraftforge.fml.common.Mod$EventBusSubscriber
 *  net.minecraftforge.fml.common.eventhandler.SubscribeEvent
 */
package eos.moe.dragoncore;

import eos.moe.dragoncore.nw;
import eos.moe.dragoncore.raa;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.EnumHand;
import net.minecraftforge.event.entity.ProjectileImpactEvent;
import net.minecraftforge.event.entity.player.AttackEntityEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

@Mod.EventBusSubscriber(modid="dragoncore")
public class bia {
    public bia() {
        bia a2;
    }

    @SubscribeEvent
    public static void ALLATORIxDEMO(AttackEntityEvent a2) {
        Entity a3 = a2.getTarget();
        if (a3 instanceof EntityLivingBase && raa.r.c((EntityLivingBase)a3) != null) {
            nw.ALLATORIxDEMO(a3.getEntityId(), -1, null);
        }
    }

    @SubscribeEvent
    public static void ALLATORIxDEMO(PlayerInteractEvent.EntityInteractSpecific a2) {
        Entity a3 = a2.getTarget();
        if (a3 instanceof EntityLivingBase && raa.r.c((EntityLivingBase)a3) != null) {
            nw.ALLATORIxDEMO(a3.getEntityId(), a2.getHand() == EnumHand.MAIN_HAND ? 0 : 1, a2.getLocalPos());
        }
    }

    @SubscribeEvent
    public static void ALLATORIxDEMO(ProjectileImpactEvent a2) {
        Entity a3;
        if (a2.getRayTraceResult().entityHit != null && (a3 = a2.getRayTraceResult().entityHit) instanceof EntityLivingBase && raa.r.c((EntityLivingBase)a3) != null) {
            nw.ALLATORIxDEMO(a2.getEntity().getEntityId(), a3.getEntityId());
        }
    }
}

