/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.entity.player.EntityPlayerMP
 *  net.minecraft.util.math.BlockPos
 *  net.minecraftforge.fml.common.network.NetworkRegistry
 *  net.minecraftforge.fml.common.network.simpleimpl.SimpleNetworkWrapper
 *  net.minecraftforge.fml.relauncher.Side
 */
package eos.moe.dragoncore;

import eos.moe.dragoncore.bi;
import eos.moe.dragoncore.dl;
import eos.moe.dragoncore.fl;
import eos.moe.dragoncore.fn;
import eos.moe.dragoncore.kd;
import eos.moe.dragoncore.kj;
import eos.moe.dragoncore.lh;
import eos.moe.dragoncore.ov;
import eos.moe.dragoncore.pj;
import eos.moe.dragoncore.pn;
import eos.moe.dragoncore.rl;
import eos.moe.dragoncore.si;
import eos.moe.dragoncore.tk;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import net.minecraftforge.fml.relauncher.Side;

public class sf {
    public SimpleNetworkWrapper k;
    private static int ALLATORIxDEMO = 0;

    public sf() {
        sf a2;
    }

    public void c() {
        sf a2;
        a2.k = NetworkRegistry.INSTANCE.newSimpleChannel("nbtedit");
        a2.ALLATORIxDEMO();
    }

    public void ALLATORIxDEMO() {
        sf a2;
        a2.k.registerMessage(dl.class, fn.class, ALLATORIxDEMO++, Side.SERVER);
        a2.k.registerMessage(kd.class, lh.class, ALLATORIxDEMO++, Side.SERVER);
        a2.k.registerMessage(si.class, rl.class, ALLATORIxDEMO++, Side.SERVER);
        a2.k.registerMessage(tk.class, fl.class, ALLATORIxDEMO++, Side.SERVER);
        a2.k.registerMessage(kd.class, lh.class, ALLATORIxDEMO++, Side.CLIENT);
        a2.k.registerMessage(tk.class, fl.class, ALLATORIxDEMO++, Side.CLIENT);
        a2.k.registerMessage(bi.class, pj.class, ALLATORIxDEMO++, Side.CLIENT);
    }

    public void ALLATORIxDEMO(EntityPlayerMP a2, BlockPos a3) {
        if (ov.y.ALLATORIxDEMO((EntityPlayer)a2)) {
            sf a4;
            a2.func_71121_q().func_152344_a((Runnable)new kj(a4, a2, a3));
        }
    }

    public void ALLATORIxDEMO(EntityPlayerMP a2, int a3) {
        if (ov.y.ALLATORIxDEMO((EntityPlayer)a2)) {
            sf a4;
            a2.func_71121_q().func_152344_a((Runnable)new pn(a4, a2, a3));
        }
    }
}

