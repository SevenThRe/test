/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.collect.ImmutableList
 *  com.mojang.authlib.minecraft.MinecraftProfileTexture$Type
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.entity.AbstractClientPlayer
 *  net.minecraft.client.network.NetworkPlayerInfo
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.inventory.EntityEquipmentSlot
 *  net.minecraft.item.ItemStack
 *  net.minecraft.profiler.Profiler
 *  net.minecraft.util.ResourceLocation
 *  net.minecraftforge.client.event.RenderPlayerEvent$Post
 *  net.minecraftforge.client.event.RenderPlayerEvent$Pre
 *  net.minecraftforge.common.MinecraftForge
 *  net.minecraftforge.fml.common.eventhandler.EventPriority
 *  net.minecraftforge.fml.common.eventhandler.SubscribeEvent
 *  net.minecraftforge.fml.common.gameevent.TickEvent$ClientTickEvent
 *  net.minecraftforge.fml.common.gameevent.TickEvent$Phase
 *  net.minecraftforge.fml.relauncher.Side
 *  net.minecraftforge.fml.relauncher.SideOnly
 */
package eos.moe.armourers;

import com.google.common.collect.ImmutableList;
import com.mojang.authlib.minecraft.MinecraftProfileTexture;
import eos.moe.armourers.ModelData;
import eos.moe.armourers.ae;
import eos.moe.armourers.fk;
import eos.moe.armourers.jg;
import eos.moe.armourers.oh;
import eos.moe.armourers.on;
import eos.moe.armourers.qd;
import eos.moe.armourers.vn;
import eos.moe.armourers.yl;
import eos.moe.armourers.zg;
import eos.moe.armourers.zh;
import java.util.HashMap;
import java.util.List;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.AbstractClientPlayer;
import net.minecraft.client.network.NetworkPlayerInfo;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemStack;
import net.minecraft.profiler.Profiler;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.event.RenderPlayerEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(value=Side.CLIENT)
public class zl {
    private Profiler v;
    public static zl s;
    public static HashMap<EntityPlayer, ae> m;
    private boolean j;

    @SubscribeEvent(priority=EventPriority.LOW)
    public void r(RenderPlayerEvent.Pre a2) {
        zl a3;
        a3.y((Entity)a2.getEntityPlayer());
    }

    public void y(Entity a2) {
        zl a3;
        if (a2 != Minecraft.getMinecraft().player && !zh.g) {
            return;
        }
        if (jg.r() != qd.c) {
            return;
        }
        if (!(a2 instanceof AbstractClientPlayer)) {
            return;
        }
        a2 = (AbstractClientPlayer)a2;
        a3.v.startSection("textureBuild");
        if (m.containsKey(a2) && !a2.getLocationSkin().getNamespace().equals("armourers_workshops")) {
            ae ae2 = m.get(a2);
            ae2.r(a2.getLocationSkin());
            ae2.r(oh.l);
            yl[] ylArray = ImmutableList.of((Object)vn.l, (Object)vn.a, (Object)vn.e, (Object)vn.w, (Object)vn.u);
            ylArray = (yl[])zg.r((Entity)a2, "any").stream().map(fk::r).filter(arg_0 -> zl.r((List)ylArray, arg_0)).toArray(yl[]::new);
            ae ae3 = ae2;
            ae3.r(ylArray);
            ae2 = ae3.z();
            a2 = Minecraft.getMinecraft().getConnection().getPlayerInfo(a2.getUniqueID());
            if (a2 != null && (a2 = ((NetworkPlayerInfo)a2).playerTextures) != null) {
                a2.put(MinecraftProfileTexture.Type.SKIN, ae2);
            }
        }
        a3.v.endSection();
    }

    public static void r() {
        s = new zl();
    }

    private static /* synthetic */ boolean r(List a2, yl a3) {
        return a3 != null && a2.contains(a3.r());
    }

    @SubscribeEvent(priority=EventPriority.HIGH)
    public void r(RenderPlayerEvent.Post a2) {
        zl a3;
        a3.r((Entity)a2.getEntityPlayer());
    }

    public zl() {
        zl a2;
        MinecraftForge.EVENT_BUS.register((Object)a2);
        a2.v = Minecraft.getMinecraft().profiler;
    }

    public void r(Entity a2) {
        zl a3;
        if (a2 != Minecraft.getMinecraft().player && !zh.g) {
            return;
        }
        if (jg.r() != qd.c) {
            return;
        }
        if (!(a2 instanceof AbstractClientPlayer)) {
            return;
        }
        a2 = (AbstractClientPlayer)a2;
        a3.v.startSection("textureReset");
        if (m.containsKey(a2)) {
            ae ae2 = m.get(a2);
            ResourceLocation resourceLocation = ae2.y();
            ResourceLocation resourceLocation2 = ae2.r();
            Object object = Minecraft.getMinecraft().getConnection().getPlayerInfo(a2.getUniqueID());
            if (object != null && (object = ((NetworkPlayerInfo)object).playerTextures) != null && a2.getLocationSkin() == resourceLocation) {
                object.put(MinecraftProfileTexture.Type.SKIN, resourceLocation2);
            }
        } else {
            m.put((EntityPlayer)a2, new ae());
        }
        a3.v.endSection();
    }

    static {
        m = new HashMap();
    }

    @SubscribeEvent
    public void r(TickEvent.ClientTickEvent a2) {
        if (((TickEvent.ClientTickEvent)a2).phase == TickEvent.Phase.START) {
            int n2;
            a2 = Minecraft.getMinecraft().player;
            if (a2 == null) {
                return;
            }
            Object object = a2;
            on on2 = on.r(object.getUniqueID());
            boolean bl = zg.r((Entity)object);
            on on3 = on2;
            on3.r();
            ItemStack[] itemStackArray = on3.m;
            int n3 = n2 = 0;
            while (n3 < itemStackArray.length) {
                EntityEquipmentSlot object2 = EntityEquipmentSlot.values()[n2 + 2];
                itemStackArray[n2++] = a2.getItemStackFromSlot(object2);
                n3 = n2;
            }
            for (fk fk2 : zg.r((Entity)a2, "any")) {
                a2 = fk2.r();
                on on4 = on2;
                if (a2 != null) {
                    on4.r((ModelData)a2);
                    continue;
                }
                on4.r(fk2.r());
            }
            on2.r(bl);
        }
    }
}

