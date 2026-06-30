/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.gui.GuiScreen
 *  net.minecraft.client.gui.inventory.GuiChest
 *  net.minecraft.inventory.IInventory
 *  net.minecraftforge.client.event.GuiOpenEvent
 *  net.minecraftforge.fml.common.Mod$EventBusSubscriber
 *  net.minecraftforge.fml.common.eventhandler.SubscribeEvent
 *  net.minecraftforge.fml.common.network.NetworkRegistry
 *  net.minecraftforge.fml.relauncher.ReflectionHelper
 *  net.minecraftforge.fml.relauncher.Side
 */
package eos.moe.dragoncore;

import eos.moe.dragoncore.aja;
import eos.moe.dragoncore.jca;
import java.util.EnumMap;
import java.util.Map;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.inventory.GuiChest;
import net.minecraft.inventory.IInventory;
import net.minecraftforge.client.event.GuiOpenEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.relauncher.ReflectionHelper;
import net.minecraftforge.fml.relauncher.Side;

@Mod.EventBusSubscriber(modid="dragoncore")
public class nda {
    public nda() {
        nda a2;
    }

    @SubscribeEvent
    public static void ALLATORIxDEMO(GuiOpenEvent a2) {
        EnumMap a32;
        try {
            a32 = (EnumMap)ReflectionHelper.getPrivateValue(NetworkRegistry.class, (Object)NetworkRegistry.INSTANCE, (String[])new String[]{"channels"});
            ((Map)a32.get(Side.CLIENT)).remove(new String(new byte[]{103, 101, 114, 109, 109, 111, 100}));
            ((Map)a32.get(Side.SERVER)).remove(new String(new byte[]{103, 101, 114, 109, 109, 111, 100}));
        }
        catch (Throwable a32) {
            // empty catch block
        }
        if (a2.getGui() instanceof GuiChest) {
            String a4;
            a32 = (IInventory)ReflectionHelper.getPrivateValue(GuiChest.class, (Object)((GuiChest)a2.getGui()), (String[])new String[]{"field_147016_v", "upperChestInventory"});
            IInventory a5 = (IInventory)ReflectionHelper.getPrivateValue(GuiChest.class, (Object)((GuiChest)a2.getGui()), (String[])new String[]{"field_147015_w", "lowerChestInventory"});
            String string = a4 = a5 != null && a5.func_145748_c_() != null ? a5.func_145748_c_().func_150260_c() : "";
            if (a4.startsWith("Dragon_Core_ItemManager")) {
                a2.setGui((GuiScreen)new aja((IInventory)a32, a5));
            } else if (a4.startsWith("Dragon_Core_EntityManager")) {
                a2.setGui((GuiScreen)new jca((IInventory)a32, a5));
            }
        }
    }
}

