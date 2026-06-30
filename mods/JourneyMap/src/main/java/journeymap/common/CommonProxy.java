/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraftforge.fml.common.event.FMLInitializationEvent
 *  net.minecraftforge.fml.common.event.FMLPostInitializationEvent
 *  net.minecraftforge.fml.common.event.FMLPreInitializationEvent
 *  net.minecraftforge.fml.relauncher.Side
 */
package journeymap.common;

import java.util.Map;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.relauncher.Side;

public interface CommonProxy {
    public void preInitialize(FMLPreInitializationEvent var1) throws Throwable;

    public void initialize(FMLInitializationEvent var1) throws Throwable;

    public void postInitialize(FMLPostInitializationEvent var1) throws Throwable;

    public boolean checkModLists(Map<String, String> var1, Side var2);

    public boolean isUpdateCheckEnabled();
}

