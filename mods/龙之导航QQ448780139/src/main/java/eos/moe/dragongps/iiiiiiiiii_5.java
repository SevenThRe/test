/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraftforge.common.config.Configuration
 *  net.minecraftforge.fml.common.Mod
 *  net.minecraftforge.fml.common.Mod$EventBusSubscriber
 *  net.minecraftforge.fml.common.Mod$EventHandler
 *  net.minecraftforge.fml.common.event.FMLPreInitializationEvent
 *  net.minecraftforge.fml.common.eventhandler.SubscribeEvent
 *  net.minecraftforge.fml.common.network.FMLNetworkEvent$ClientDisconnectionFromServerEvent
 *  net.minecraftforge.fml.common.network.NetworkRegistry
 *  net.minecraftforge.fml.common.network.simpleimpl.SimpleNetworkWrapper
 *  net.minecraftforge.fml.relauncher.Side
 */
package eos.moe.dragongps;

import eos.moe.dragongps.iiiiiiiiii_0;
import eos.moe.dragongps.iiiiiiiiii_17;
import eos.moe.dragongps.iiiiiiiiii_6;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.network.FMLNetworkEvent;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import net.minecraftforge.fml.relauncher.Side;

/*
 * Renamed from eos.moe.dragongps.IiiiIIiiii
 */
@Mod.EventBusSubscriber(modid="dragongps")
@Mod(modid="dragongps", name="\u9f99\u4e4b\u5bfc\u822aQQ448780139", acceptedMinecraftVersions="1.12.2")
public class iiiiiiiiii_5 {
    public static boolean iIIiIIiIii;
    public static Configuration iIIIIiiIII;
    public static int IIiIiIIIiI;
    public static final SimpleNetworkWrapper IIiiiiiIIi;

    private /* synthetic */ void IIIiiiIiii() {
        IIiiiiiIIi.registerMessage(iiiiiiiiii_6.class, iiiiiiiiii_6.class, 0, Side.CLIENT);
        IIiiiiiIIi.registerMessage(iiiiiiiiii_6.class, iiiiiiiiii_6.class, 1, Side.SERVER);
    }

    @SubscribeEvent
    public static void IIIiiiIiii(FMLNetworkEvent.ClientDisconnectionFromServerEvent IIiiiiiIIi) {
        iiiiiiiiii_6.IIiiIiiIII.clear();
        iiiiiiiiii_6.iIIiIIiIii.clear();
    }

    @Mod.EventHandler
    public void IIIiiiIiii(FMLPreInitializationEvent IIiiiiiIIi) {
        iiiiiiiiii_5 IIiiiiiIIi2;
        new iiiiiiiiii_0(IIiiiiiIIi);
        IIiiiiiIIi2.IIIiiiIiii();
    }

    public iiiiiiiiii_5() {
        iiiiiiiiii_5 IIiiiiiIIi;
    }

    static {
        IIiiiiiIIi = NetworkRegistry.INSTANCE.newSimpleChannel(iiiiiiiiii_17.IIiiiiiIIi);
    }
}

