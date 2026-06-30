/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraftforge.fml.common.event.FMLPreInitializationEvent
 */
package goblinbob.mobends.core.module;

import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

public interface IModule {
    public void preInit(FMLPreInitializationEvent var1);

    public void onRefresh();
}

