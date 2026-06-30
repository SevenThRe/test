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
 *  net.minecraftforge.fml.relauncher.ReflectionHelper
 */
package eos.moe.armourers;

import eos.moe.armourers.af;
import eos.moe.armourers.cj;
import eos.moe.armourers.me;
import eos.moe.armourers.pk;
import eos.moe.armourers.rg;
import eos.moe.armourers.uf;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.inventory.GuiChest;
import net.minecraft.inventory.IInventory;
import net.minecraftforge.client.event.GuiOpenEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.ReflectionHelper;

@Mod.EventBusSubscriber(modid="armourers_workshops")
public class mg {
    public mg() {
        mg a2;
    }

    @SubscribeEvent
    public static void r(GuiOpenEvent a2) {
        if (a2.getGui() instanceof GuiChest) {
            Object object;
            String[] stringArray = new String[2];
            stringArray[0] = "upperChestInventory";
            stringArray[1] = "upperChestInventory";
            IInventory iInventory = (IInventory)ReflectionHelper.getPrivateValue(GuiChest.class, (Object)((GuiChest)a2.getGui()), (String[])stringArray);
            String[] stringArray2 = new String[2];
            stringArray2[0] = "lowerChestInventory";
            stringArray2[1] = "lowerChestInventory";
            IInventory iInventory2 = (IInventory)ReflectionHelper.getPrivateValue(GuiChest.class, (Object)((GuiChest)a2.getGui()), (String[])stringArray2);
            Object object2 = object = iInventory2 != null && iInventory2.getDisplayName() != null ? iInventory2.getDisplayName().getUnformattedText() : "";
            if (((String)object).startsWith("Dragon_Armourers_Manager")) {
                a2.setGui((GuiScreen)new uf(iInventory, iInventory2));
                return;
            }
            if (((String)object).startsWith("Dragon_Armourers_EntityManager")) {
                a2.setGui((GuiScreen)new cj(iInventory, iInventory2));
                return;
            }
            if (((String)object).startsWith("Dragon_Armourers_Shop")) {
                a2.setGui((GuiScreen)new pk(iInventory, iInventory2));
                return;
            }
            if ((object = af.r((String)object)) != null) {
                a2.setGui((GuiScreen)new me((rg)object, iInventory, iInventory2));
            }
        }
    }
}

