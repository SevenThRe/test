/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.settings.KeyBinding
 *  net.minecraftforge.fml.client.registry.ClientRegistry
 *  net.minecraftforge.fml.common.Mod
 *  net.minecraftforge.fml.common.Mod$EventHandler
 *  net.minecraftforge.fml.common.Mod$Instance
 *  net.minecraftforge.fml.common.event.FMLInitializationEvent
 *  net.minecraftforge.fml.common.event.FMLPostInitializationEvent
 *  net.minecraftforge.fml.common.event.FMLPreInitializationEvent
 *  org.apache.logging.log4j.Logger
 */
package eos.moe.armourers;

import eos.moe.armourers.jg;
import eos.moe.armourers.zh;
import java.io.File;
import net.minecraft.client.settings.KeyBinding;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import org.apache.logging.log4j.Logger;

/*
 * Duplicate member names - consider using --renamedupmembers true
 */
@Mod(modid="armourers_workshops", name="Armourer's Workshops", version="1.12.2-0.49.1", dependencies="required-after:forge@[14.23.3.2694,);after:galacticraftcore;", acceptedMinecraftVersions="1.12.2")
public class sg {
    public static File c;
    public static KeyBinding v;
    @Mod.Instance(value="armourers_workshops")
    private static sg s;
    private static Logger m;
    private static jg j;

    @Mod.EventHandler
    public void r(FMLInitializationEvent a2) {
        j.r(a2);
        j.r();
    }

    public sg() {
        sg a2;
    }

    @Mod.EventHandler
    public void r(FMLPostInitializationEvent a2) {
        j.r(a2);
    }

    @Mod.EventHandler
    public void r(FMLPreInitializationEvent a2) {
        FMLPreInitializationEvent fMLPreInitializationEvent = a2;
        c = fMLPreInitializationEvent.getModConfigurationDirectory().getParentFile();
        m = fMLPreInitializationEvent.getModLog();
        j = new jg();
        new zh(a2);
        j.r(a2);
        v = new KeyBinding("\u663e\u793a\u65f6\u88c5", 207, "\u65f6\u88c5");
        ClientRegistry.registerKeyBinding((KeyBinding)v);
    }

    public static Logger r() {
        return m;
    }

    public static sg r() {
        return s;
    }
}

