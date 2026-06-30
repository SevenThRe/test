/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.multiplayer.WorldClient
 *  net.minecraft.client.network.NetHandlerPlayClient
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.network.INetHandler
 *  net.minecraft.network.Packet
 *  net.minecraft.network.PacketThreadUtil
 *  net.minecraft.network.play.server.SPacketDestroyEntities
 *  net.minecraft.network.play.server.SPacketJoinGame
 *  net.minecraft.network.play.server.SPacketSpawnMob
 *  net.minecraft.util.IThreadListener
 *  org.apache.logging.log4j.Logger
 */
package eos.moe.dragoncore.mixins;

import eos.moe.dragoncore.dt;
import eos.moe.dragoncore.nw;
import eos.moe.dragoncore.xz;
import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.WorldClient;
import net.minecraft.client.network.NetHandlerPlayClient;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.network.INetHandler;
import net.minecraft.network.Packet;
import net.minecraft.network.PacketThreadUtil;
import net.minecraft.network.play.server.SPacketDestroyEntities;
import net.minecraft.network.play.server.SPacketJoinGame;
import net.minecraft.network.play.server.SPacketSpawnMob;
import net.minecraft.util.IThreadListener;
import org.apache.logging.log4j.Logger;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(value={NetHandlerPlayClient.class})
public class MixinNetHandlerPlayClient {
    @Shadow
    private Minecraft field_147299_f;
    @Shadow
    private WorldClient field_147300_g;

    public MixinNetHandlerPlayClient() {
        MixinNetHandlerPlayClient a2;
    }

    @Inject(method={"handleDestroyEntities"}, at={@At(value="HEAD")}, cancellable=true)
    private /* synthetic */ void overwrite_handleDestroyEntities(SPacketDestroyEntities a2, CallbackInfo a3) {
        MixinNetHandlerPlayClient a4;
        a3.cancel();
        PacketThreadUtil.func_180031_a((Packet)a2, (INetHandler)((NetHandlerPlayClient)a4), (IThreadListener)a4.field_147299_f);
        for (int a5 = 0; a5 < a2.func_149098_c().length; ++a5) {
            xz a6;
            Entity a7 = a4.field_147300_g.func_73045_a(a2.func_149098_c()[a5]);
            if (a7 instanceof EntityLivingBase && (a6 = dt.k.getEntityManager(a7.func_110124_au())) != null && dt.k.isPlayingAnimation(a6, "death")) continue;
            a4.field_147300_g.func_73028_b(a2.func_149098_c()[a5]);
        }
    }

    @Inject(method={"handleJoinGame"}, at={@At(value="RETURN")})
    private /* synthetic */ void inject_handleJoinGame(SPacketJoinGame a2, CallbackInfo a3) {
        nw.ALLATORIxDEMO("DragonCore", "version", "2613");
    }

    @Inject(method={"handleSpawnMob"}, at={@At(value="RETURN")})
    private /* synthetic */ void mixin_handleSpawnMob(SPacketSpawnMob a2, CallbackInfo a3) {
        try {
            Entity a4 = Minecraft.func_71410_x().field_71441_e.func_73045_a(a2.func_149024_d());
            if (a4 instanceof EntityLivingBase) {
                dt.k.init((EntityLivingBase)a4, true);
            }
        }
        catch (Exception exception) {
            // empty catch block
        }
    }

    @Redirect(method={"handleUpdateTileEntity"}, at=@At(value="INVOKE", target="Lorg/apache/logging/log4j/Logger;error(Ljava/lang/String;Ljava/lang/Object;Ljava/lang/Object;)V", remap=false))
    private /* synthetic */ void mixin_handleUpdateTileEntity(Logger a2, String a3, Object a4, Object a5) {
        if (a5 != null && a5.toString().contains("structural")) {
            return;
        }
        a2.error("Received invalid update packet for null tile entity at {} with data: {}", a4, a5);
    }
}

