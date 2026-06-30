/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.multiplayer.WorldClient
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraftforge.event.entity.EntityJoinWorldEvent
 *  net.minecraftforge.fml.common.Mod$EventBusSubscriber
 *  net.minecraftforge.fml.common.eventhandler.EventPriority
 *  net.minecraftforge.fml.common.eventhandler.SubscribeEvent
 *  net.minecraftforge.fml.common.gameevent.TickEvent$ClientTickEvent
 */
package eos.moe.dragoncore;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.WorldClient;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;

@Mod.EventBusSubscriber(modid="dragoncore")
public class EntityList {
    public static Map<UUID, Entity> entities = new HashMap<UUID, Entity>();

    public EntityList() {
        EntityList a2;
    }

    @SubscribeEvent(priority=EventPriority.HIGHEST)
    public static void onTick(TickEvent.ClientTickEvent a2) {
        entities.clear();
        WorldClient a3 = Minecraft.getMinecraft().world;
        if (a3 != null) {
            for (Entity a4 : a3.getLoadedEntityList()) {
                entities.put(a4.getUniqueID(), a4);
            }
        }
    }

    @SubscribeEvent
    public static void onJoin(EntityJoinWorldEvent a2) {
        entities.put(a2.getEntity().getUniqueID(), a2.getEntity());
    }

    public static Entity getEntityByUUID(UUID a2) {
        return entities.get(a2);
    }

    public static EntityLivingBase getLivingEntityByUUID(UUID a2) {
        Entity a3 = entities.get(a2);
        if (a3 instanceof EntityLivingBase) {
            return (EntityLivingBase)a3;
        }
        return null;
    }
}

