/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.base.Predicate
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.entity.EntityPlayerSP
 *  net.minecraft.client.settings.KeyBinding
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.util.math.Vec3d
 *  net.minecraftforge.fml.common.eventhandler.SubscribeEvent
 *  net.minecraftforge.fml.common.gameevent.InputEvent$KeyInputEvent
 *  net.minecraftforge.fml.common.gameevent.TickEvent$Phase
 *  net.minecraftforge.fml.common.gameevent.TickEvent$RenderTickEvent
 */
package eos.moe.dragoncore;

import com.google.common.base.Predicate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.client.settings.KeyBinding;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.math.Vec3d;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.InputEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;

public class eja {
    public static KeyBinding m;
    public static KeyBinding c;
    public static List<EntityLivingBase> q;
    private static final Minecraft b;
    public static boolean o;
    private static Entity y;
    private static final Predicate<Entity> k;
    private static int ALLATORIxDEMO;

    public eja() {
        eja a2;
    }

    public static void f() {
    }

    @SubscribeEvent
    public static void ALLATORIxDEMO(InputEvent.KeyInputEvent a2) {
    }

    @SubscribeEvent
    public static void ALLATORIxDEMO(TickEvent.RenderTickEvent a2) {
        EntityPlayerSP a3 = eja.b.player;
        if (a2.phase == TickEvent.Phase.START && eja.b.player != null && !b.isGamePaused()) {
            eja.c();
            if (y != null) {
                float a4;
                Vec3d a5 = y.getPositionVector();
                Vec3d a6 = a5.subtract(eja.b.player.getPositionVector()).normalize();
                double a7 = Math.atan2(-a6.x, a6.z) * 180.0 / Math.PI;
                if (Math.abs(a7 - (double)(a4 = eja.b.player.prevRotationYaw)) > 180.0) {
                    if ((double)a4 > a7) {
                        a7 += 360.0;
                    } else if ((double)a4 < a7) {
                        a7 -= 360.0;
                    }
                }
                double a8 = eja.ALLATORIxDEMO(a4, (float)a7, a2.renderTickTime);
                System.out.println(a8 % 360.0);
                a3.rotationYaw = (float)a8 % 360.0f;
            }
        }
    }

    public static float ALLATORIxDEMO(float a2, float a3, float a4) {
        return a2 + (a3 - a2) * a4;
    }

    private static /* synthetic */ void c(EntityPlayer a2) {
        eja.y = eja.ALLATORIxDEMO(a2);
        if (y != null) {
            o = true;
        }
    }

    private static /* synthetic */ void c() {
        q.removeIf(a2 -> eja.b.player == null || !a2.isEntityAlive());
        if (y != null && !y.isEntityAlive()) {
            y = null;
            o = false;
        }
    }

    public static Entity ALLATORIxDEMO(EntityPlayer a3) {
        List a4 = a3.world.getEntitiesInAABBexcluding((Entity)a3, a3.getEntityBoundingBox().grow(10.0, 2.0, 10.0), k).stream().filter(a2 -> a2 instanceof EntityLivingBase).map(a2 -> (EntityLivingBase)a2).collect(Collectors.toList());
        if (o) {
            ++ALLATORIxDEMO;
            for (EntityLivingBase a5 : a4) {
                if (q.contains(a5)) continue;
                q.add(a5);
                return a5;
            }
            if (ALLATORIxDEMO >= q.size()) {
                ALLATORIxDEMO = 0;
            }
            return (Entity)q.get(ALLATORIxDEMO);
        }
        if (!a4.isEmpty()) {
            EntityLivingBase a6 = (EntityLivingBase)a4.get(0);
            q.add(a6);
            return (Entity)a4.get(0);
        }
        return null;
    }

    private static /* synthetic */ void ALLATORIxDEMO() {
        y = null;
        o = false;
        q.clear();
    }

    static {
        q = new ArrayList<EntityLivingBase>();
        b = Minecraft.getMinecraft();
        k = a2 -> a2 instanceof EntityLivingBase && a2.isEntityAlive() && ((EntityLivingBase)a2).attackable();
        ALLATORIxDEMO = -1;
    }
}

