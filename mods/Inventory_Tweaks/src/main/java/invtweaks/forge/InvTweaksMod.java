/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.item.ItemStack
 *  net.minecraftforge.fml.common.Mod
 *  net.minecraftforge.fml.common.Mod$EventHandler
 *  net.minecraftforge.fml.common.Mod$Instance
 *  net.minecraftforge.fml.common.SidedProxy
 *  net.minecraftforge.fml.common.event.FMLInitializationEvent
 *  net.minecraftforge.fml.common.event.FMLPostInitializationEvent
 *  net.minecraftforge.fml.common.event.FMLPreInitializationEvent
 *  net.minecraftforge.fml.common.event.FMLServerAboutToStartEvent
 *  net.minecraftforge.fml.common.event.FMLServerStoppedEvent
 *  org.jetbrains.annotations.NotNull
 */
package invtweaks.forge;

import invtweaks.api.IItemTreeListener;
import invtweaks.api.InvTweaksAPI;
import invtweaks.api.SortingMethod;
import invtweaks.api.container.ContainerSection;
import invtweaks.forge.CommonProxy;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.event.FMLServerAboutToStartEvent;
import net.minecraftforge.fml.common.event.FMLServerStoppedEvent;
import org.jetbrains.annotations.NotNull;

@Mod(modid="inventorytweaks", dependencies="required-after:forge@[14.21.0,)", acceptableRemoteVersions="*", acceptedMinecraftVersions="", guiFactory="invtweaks.forge.ModGuiFactory", certificateFingerprint="55d2cd4f5f0961410bf7b91ef6c6bf00a766dcbe")
public class InvTweaksMod
implements InvTweaksAPI {
    @Mod.Instance
    public static InvTweaksMod instance;
    @SidedProxy(clientSide="invtweaks.forge.ClientProxy", serverSide="invtweaks.forge.CommonProxy")
    public static CommonProxy proxy;

    public static void setTextboxModeStatic(boolean enabled) {
        instance.setTextboxMode(enabled);
    }

    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent e) {
        proxy.preInit(e);
    }

    @Mod.EventHandler
    public void init(FMLInitializationEvent e) {
        proxy.init(e);
    }

    @Mod.EventHandler
    public void postInit(FMLPostInitializationEvent e) {
        proxy.postInit(e);
    }

    @Mod.EventHandler
    public void serverAboutToStart(@NotNull FMLServerAboutToStartEvent e) {
        proxy.serverAboutToStart(e);
    }

    @Mod.EventHandler
    public void serverStopped(FMLServerStoppedEvent e) {
        proxy.serverStopped(e);
    }

    @Override
    public void addOnLoadListener(IItemTreeListener listener) {
        proxy.addOnLoadListener(listener);
    }

    @Override
    public boolean removeOnLoadListener(IItemTreeListener listener) {
        return proxy.removeOnLoadListener(listener);
    }

    @Override
    public void setSortKeyEnabled(boolean enabled) {
        proxy.setSortKeyEnabled(enabled);
    }

    @Override
    public void setTextboxMode(boolean enabled) {
        proxy.setTextboxMode(enabled);
    }

    @Override
    public int compareItems(@NotNull ItemStack i, @NotNull ItemStack j) {
        return proxy.compareItems(i, j);
    }

    @Override
    public void sort(ContainerSection section, SortingMethod method) {
        proxy.sort(section, method);
    }
}

