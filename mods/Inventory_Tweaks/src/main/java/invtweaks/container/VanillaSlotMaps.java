/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.gui.GuiScreen
 *  net.minecraft.client.gui.inventory.GuiContainerCreative
 *  net.minecraft.client.gui.inventory.GuiContainerCreative$ContainerCreative
 *  net.minecraft.creativetab.CreativeTabs
 *  net.minecraft.entity.passive.AbstractChestHorse
 *  net.minecraft.inventory.Container
 *  net.minecraft.inventory.ContainerHorseInventory
 *  net.minecraft.inventory.Slot
 *  net.minecraftforge.fml.client.FMLClientHandler
 *  net.minecraftforge.fml.relauncher.Side
 *  net.minecraftforge.fml.relauncher.SideOnly
 *  org.jetbrains.annotations.NotNull
 */
package invtweaks.container;

import invtweaks.api.container.ContainerSection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.inventory.GuiContainerCreative;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.passive.AbstractChestHorse;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.ContainerHorseInventory;
import net.minecraft.inventory.Slot;
import net.minecraftforge.fml.client.FMLClientHandler;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.jetbrains.annotations.NotNull;

public class VanillaSlotMaps {
    @NotNull
    public static Map<ContainerSection, List<Slot>> containerPlayerSlots(@NotNull Container container) {
        HashMap<ContainerSection, List<Slot>> slotRefs = new HashMap<ContainerSection, List<Slot>>();
        slotRefs.put(ContainerSection.CRAFTING_OUT, container.field_75151_b.subList(0, 1));
        slotRefs.put(ContainerSection.CRAFTING_IN, container.field_75151_b.subList(1, 5));
        slotRefs.put(ContainerSection.ARMOR, container.field_75151_b.subList(5, 9));
        slotRefs.put(ContainerSection.INVENTORY, container.field_75151_b.subList(9, 45));
        slotRefs.put(ContainerSection.INVENTORY_NOT_HOTBAR, container.field_75151_b.subList(9, 36));
        slotRefs.put(ContainerSection.INVENTORY_HOTBAR, container.field_75151_b.subList(36, 45));
        return slotRefs;
    }

    @SideOnly(value=Side.CLIENT)
    public static boolean containerCreativeIsInventory(GuiContainerCreative.ContainerCreative container) {
        GuiScreen currentScreen = FMLClientHandler.instance().getClient().field_71462_r;
        return currentScreen instanceof GuiContainerCreative && ((GuiContainerCreative)currentScreen).func_147056_g() == CreativeTabs.field_78036_m.func_78021_a();
    }

    @SideOnly(value=Side.CLIENT)
    @NotNull
    public static Map<ContainerSection, List<Slot>> containerCreativeSlots(@NotNull GuiContainerCreative.ContainerCreative container) {
        HashMap<ContainerSection, List<Slot>> slotRefs = new HashMap<ContainerSection, List<Slot>>();
        slotRefs.put(ContainerSection.ARMOR, container.field_75151_b.subList(5, 9));
        slotRefs.put(ContainerSection.INVENTORY, container.field_75151_b.subList(9, 45));
        slotRefs.put(ContainerSection.INVENTORY_NOT_HOTBAR, container.field_75151_b.subList(9, 36));
        slotRefs.put(ContainerSection.INVENTORY_HOTBAR, container.field_75151_b.subList(36, 45));
        return slotRefs;
    }

    @NotNull
    public static Map<ContainerSection, List<Slot>> containerChestDispenserSlots(@NotNull Container container) {
        HashMap<ContainerSection, List<Slot>> slotRefs = new HashMap<ContainerSection, List<Slot>>();
        int size = container.field_75151_b.size();
        slotRefs.put(ContainerSection.CHEST, container.field_75151_b.subList(0, size - 36));
        slotRefs.put(ContainerSection.INVENTORY, container.field_75151_b.subList(size - 36, size));
        slotRefs.put(ContainerSection.INVENTORY_NOT_HOTBAR, container.field_75151_b.subList(size - 36, size - 9));
        slotRefs.put(ContainerSection.INVENTORY_HOTBAR, container.field_75151_b.subList(size - 9, size));
        return slotRefs;
    }

    @NotNull
    public static Map<ContainerSection, List<Slot>> containerHorseSlots(@NotNull ContainerHorseInventory container) {
        HashMap<ContainerSection, List<Slot>> slotRefs = new HashMap<ContainerSection, List<Slot>>();
        int size = container.field_75151_b.size();
        if (container.field_111242_f instanceof AbstractChestHorse && ((AbstractChestHorse)container.field_111242_f).func_190695_dh()) {
            slotRefs.put(ContainerSection.CHEST, container.field_75151_b.subList(2, size - 36));
        }
        slotRefs.put(ContainerSection.INVENTORY, container.field_75151_b.subList(size - 36, size));
        slotRefs.put(ContainerSection.INVENTORY_NOT_HOTBAR, container.field_75151_b.subList(size - 36, size - 9));
        slotRefs.put(ContainerSection.INVENTORY_HOTBAR, container.field_75151_b.subList(size - 9, size));
        return slotRefs;
    }

    public static boolean containerHorseIsInventory(@NotNull ContainerHorseInventory container) {
        return container.field_111242_f instanceof AbstractChestHorse && ((AbstractChestHorse)container.field_111242_f).func_190695_dh();
    }

    @NotNull
    public static Map<ContainerSection, List<Slot>> containerFurnaceSlots(@NotNull Container container) {
        HashMap<ContainerSection, List<Slot>> slotRefs = new HashMap<ContainerSection, List<Slot>>();
        slotRefs.put(ContainerSection.FURNACE_IN, container.field_75151_b.subList(0, 1));
        slotRefs.put(ContainerSection.FURNACE_FUEL, container.field_75151_b.subList(1, 2));
        slotRefs.put(ContainerSection.FURNACE_OUT, container.field_75151_b.subList(2, 3));
        slotRefs.put(ContainerSection.INVENTORY, container.field_75151_b.subList(3, 39));
        slotRefs.put(ContainerSection.INVENTORY_NOT_HOTBAR, container.field_75151_b.subList(3, 30));
        slotRefs.put(ContainerSection.INVENTORY_HOTBAR, container.field_75151_b.subList(30, 39));
        return slotRefs;
    }

    @NotNull
    public static Map<ContainerSection, List<Slot>> containerWorkbenchSlots(@NotNull Container container) {
        HashMap<ContainerSection, List<Slot>> slotRefs = new HashMap<ContainerSection, List<Slot>>();
        slotRefs.put(ContainerSection.CRAFTING_OUT, container.field_75151_b.subList(0, 1));
        slotRefs.put(ContainerSection.CRAFTING_IN, container.field_75151_b.subList(1, 10));
        slotRefs.put(ContainerSection.INVENTORY, container.field_75151_b.subList(10, 46));
        slotRefs.put(ContainerSection.INVENTORY_NOT_HOTBAR, container.field_75151_b.subList(10, 37));
        slotRefs.put(ContainerSection.INVENTORY_HOTBAR, container.field_75151_b.subList(37, 46));
        return slotRefs;
    }

    @NotNull
    public static Map<ContainerSection, List<Slot>> containerEnchantmentSlots(@NotNull Container container) {
        HashMap<ContainerSection, List<Slot>> slotRefs = new HashMap<ContainerSection, List<Slot>>();
        slotRefs.put(ContainerSection.ENCHANTMENT, container.field_75151_b.subList(0, 1));
        slotRefs.put(ContainerSection.INVENTORY, container.field_75151_b.subList(2, 38));
        slotRefs.put(ContainerSection.INVENTORY_NOT_HOTBAR, container.field_75151_b.subList(2, 29));
        slotRefs.put(ContainerSection.INVENTORY_HOTBAR, container.field_75151_b.subList(29, 38));
        return slotRefs;
    }

    @NotNull
    public static Map<ContainerSection, List<Slot>> containerBrewingSlots(@NotNull Container container) {
        HashMap<ContainerSection, List<Slot>> slotRefs = new HashMap<ContainerSection, List<Slot>>();
        slotRefs.put(ContainerSection.BREWING_BOTTLES, container.field_75151_b.subList(0, 3));
        slotRefs.put(ContainerSection.BREWING_INGREDIENT, container.field_75151_b.subList(3, 4));
        slotRefs.put(ContainerSection.INVENTORY, container.field_75151_b.subList(4, 40));
        slotRefs.put(ContainerSection.INVENTORY_NOT_HOTBAR, container.field_75151_b.subList(4, 31));
        slotRefs.put(ContainerSection.INVENTORY_HOTBAR, container.field_75151_b.subList(31, 40));
        return slotRefs;
    }

    @NotNull
    public static Map<ContainerSection, List<Slot>> containerRepairSlots(@NotNull Container container) {
        HashMap<ContainerSection, List<Slot>> slotRefs = new HashMap<ContainerSection, List<Slot>>();
        slotRefs.put(ContainerSection.CRAFTING_IN, container.field_75151_b.subList(0, 2));
        slotRefs.put(ContainerSection.CRAFTING_OUT, container.field_75151_b.subList(2, 3));
        slotRefs.put(ContainerSection.INVENTORY, container.field_75151_b.subList(3, 39));
        slotRefs.put(ContainerSection.INVENTORY_NOT_HOTBAR, container.field_75151_b.subList(3, 30));
        slotRefs.put(ContainerSection.INVENTORY_HOTBAR, container.field_75151_b.subList(30, 39));
        return slotRefs;
    }

    @NotNull
    public static Map<ContainerSection, List<Slot>> unknownContainerSlots(@NotNull Container container) {
        HashMap<ContainerSection, List<Slot>> slotRefs = new HashMap<ContainerSection, List<Slot>>();
        int size = container.field_75151_b.size();
        if (size >= 36) {
            slotRefs.put(ContainerSection.CHEST, container.field_75151_b.subList(0, size - 36));
            slotRefs.put(ContainerSection.INVENTORY, container.field_75151_b.subList(size - 36, size));
            slotRefs.put(ContainerSection.INVENTORY_NOT_HOTBAR, container.field_75151_b.subList(size - 36, size - 9));
            slotRefs.put(ContainerSection.INVENTORY_HOTBAR, container.field_75151_b.subList(size - 9, size));
        } else {
            slotRefs.put(ContainerSection.CHEST, container.field_75151_b.subList(0, size));
        }
        return slotRefs;
    }
}

