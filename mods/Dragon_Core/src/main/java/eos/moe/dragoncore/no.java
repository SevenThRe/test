/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.Minecraft
 *  net.minecraft.tileentity.TileEntity
 *  net.minecraft.tileentity.TileEntitySign
 *  net.minecraft.tileentity.TileEntitySkull
 *  net.minecraft.util.ResourceLocation
 *  net.minecraft.util.math.BlockPos
 *  net.minecraftforge.event.world.WorldEvent$Unload
 *  net.minecraftforge.fml.common.Mod$EventBusSubscriber
 *  net.minecraftforge.fml.common.eventhandler.SubscribeEvent
 */
package eos.moe.dragoncore;

import eos.moe.dragoncore.data.SignData;
import eos.moe.dragoncore.er;
import eos.moe.dragoncore.fq;
import eos.moe.dragoncore.jv;
import eos.moe.dragoncore.kq;
import eos.moe.dragoncore.ms;
import eos.moe.dragoncore.qw;
import eos.moe.dragoncore.renderer.SignRenderer;
import eos.moe.dragoncore.st;
import eos.moe.dragoncore.vu;
import eos.moe.dragoncore.wr;
import eos.moe.dragoncore.ww;
import eos.moe.dragoncore.xz;
import java.util.HashMap;
import java.util.Map;
import net.minecraft.client.Minecraft;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntitySign;
import net.minecraft.tileentity.TileEntitySkull;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.event.world.WorldEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

/*
 * Duplicate member names - consider using --renamedupmembers true
 */
@Mod.EventBusSubscriber(modid="dragoncore")
public class no {
    public static final Map<String, xz> k = new HashMap<String, xz>();
    public static final Map<String, jv> ALLATORIxDEMO = new HashMap<String, jv>();

    public no() {
        no a2;
    }

    @SubscribeEvent
    public static void ALLATORIxDEMO(WorldEvent.Unload a2) {
        k.clear();
    }

    public static jv ALLATORIxDEMO(String a2) {
        if (!ALLATORIxDEMO.containsKey(a2)) {
            jv a3 = ms.ALLATORIxDEMO(new ResourceLocation("dragoncore", "models/blocks/" + a2 + "/model.json"));
            ALLATORIxDEMO.put(a2, a3);
        }
        return ALLATORIxDEMO.get(a2);
    }

    public static void ALLATORIxDEMO(String a2) {
        ww.ALLATORIxDEMO("models/blocks/" + a2 + "/texture.png");
    }

    public static void ALLATORIxDEMO(int a2, int a3, int a4, String a5) {
        TileEntity a6 = Minecraft.func_71410_x().field_71441_e.func_175625_s(new BlockPos(a2, a3, a4));
        if (a6 instanceof TileEntitySkull) {
            TileEntitySkull a7 = (TileEntitySkull)a6;
            String a8 = vu.c((TileEntity)a7);
            if (a8 != null && a8.startsWith("none")) {
                return;
            }
            er a9 = er.ALLATORIxDEMO(a8);
            if (a9 != null) {
                String a10 = a9.b;
                jv a11 = no.ALLATORIxDEMO(a10);
                no.ALLATORIxDEMO(a2, a3, a4, a10, a11, a5);
            }
        } else if (a6 instanceof TileEntitySign) {
            TileEntitySign a12 = (TileEntitySign)a6;
            if (a12.field_145915_a.length <= 1) {
                return;
            }
            if (a12.field_145915_a[1] == null) {
                return;
            }
            String a13 = a12.field_145915_a[1].func_150254_d();
            SignData a14 = SignRenderer.getSignData(a13);
            if (a14 == null) {
                return;
            }
            if (a14.getModel() == null || a14.getCheck() == null) {
                return;
            }
            if (!a14.getCheck().contains("\u00a7")) {
                return;
            }
            String a15 = a14.getModel();
            jv a16 = no.ALLATORIxDEMO(a15);
            no.ALLATORIxDEMO(a2, a3, a4, a15, a16, a5);
        }
    }

    public static void ALLATORIxDEMO(int a2, int a3, int a4, String a5, jv a6, String a7) {
        Map<String, kq> a8;
        Object a9;
        String a10 = a2 + "," + a3 + "," + a4 + "," + a5;
        if (!k.containsKey(a10)) {
            a9 = fq.c();
            ((fq)a9).ALLATORIxDEMO();
            try {
                a8 = wr.ALLATORIxDEMO(new ResourceLocation("dragoncore", "models/blocks/" + a5 + "/animation.json"));
                ((fq)a9).ALLATORIxDEMO(a8);
                a8.forEach((arg_0, arg_1) -> no.ALLATORIxDEMO((fq)a9, arg_0, arg_1));
            }
            catch (Exception a11) {
                a11.printStackTrace();
            }
            a8 = ((fq)a9).ALLATORIxDEMO();
            k.put(a10, (xz)((Object)a8));
        }
        a9 = k.get(a10);
        if ("idle".equals(a7)) {
            if (((qw)a9).ALLATORIxDEMO() != null && !((qw)a9).isOnPlayAnimation()) {
                a8 = ((qw)a9).ALLATORIxDEMO().ALLATORIxDEMO();
                ((qw)a9).ALLATORIxDEMO((st)((Object)a8), "base");
            }
        } else if (((xz)a9).x().containsKey(a7)) {
            a8 = ((xz)a9).x().get(a7);
            st a12 = new st((kq)((Object)a8));
            a12.ALLATORIxDEMO().ALLATORIxDEMO(0);
            ((qw)a9).ALLATORIxDEMO(a12, "base");
        }
        ((qw)a9).ALLATORIxDEMO(a6);
    }

    private static /* synthetic */ void ALLATORIxDEMO(fq a2, String a3, kq a4) {
        if (a3.contains("idle")) {
            a2.ALLATORIxDEMO(new st(a4).ALLATORIxDEMO(1.0f), "base");
        }
    }
}

