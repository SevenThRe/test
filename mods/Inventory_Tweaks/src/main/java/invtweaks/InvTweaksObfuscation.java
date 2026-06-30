/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.gui.FontRenderer
 *  net.minecraft.client.gui.GuiButton
 *  net.minecraft.client.gui.GuiScreen
 *  net.minecraft.client.gui.inventory.GuiContainer
 *  net.minecraft.client.gui.inventory.GuiContainerCreative
 *  net.minecraft.client.gui.inventory.GuiContainerCreative$CreativeSlot
 *  net.minecraft.client.gui.inventory.GuiEditSign
 *  net.minecraft.client.gui.inventory.GuiInventory
 *  net.minecraft.client.multiplayer.PlayerControllerMP
 *  net.minecraft.client.settings.GameSettings
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.entity.player.InventoryPlayer
 *  net.minecraft.inventory.Container
 *  net.minecraft.inventory.Slot
 *  net.minecraft.item.ItemArmor
 *  net.minecraft.item.ItemStack
 *  net.minecraft.util.NonNullList
 *  net.minecraft.util.ResourceLocation
 *  net.minecraft.util.text.ITextComponent
 *  net.minecraft.util.text.TextComponentString
 *  net.minecraftforge.fml.client.FMLClientHandler
 *  net.minecraftforge.fml.relauncher.Side
 *  net.minecraftforge.fml.relauncher.SideOnly
 *  org.apache.logging.log4j.Logger
 *  org.jetbrains.annotations.Contract
 *  org.jetbrains.annotations.NotNull
 *  org.jetbrains.annotations.Nullable
 *  org.lwjgl.input.Mouse
 */
package invtweaks;

import invtweaks.InvTweaks;
import invtweaks.api.container.ContainerSection;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.gui.inventory.GuiContainerCreative;
import net.minecraft.client.gui.inventory.GuiEditSign;
import net.minecraft.client.gui.inventory.GuiInventory;
import net.minecraft.client.multiplayer.PlayerControllerMP;
import net.minecraft.client.settings.GameSettings;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentString;
import net.minecraftforge.fml.client.FMLClientHandler;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.apache.logging.log4j.Logger;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.lwjgl.input.Mouse;

public class InvTweaksObfuscation {
    private static final Logger log = InvTweaks.log;
    public Minecraft mc;

    public InvTweaksObfuscation(Minecraft mc_) {
        this.mc = mc_;
    }

    @Nullable
    public static String getNamespacedID(@Nullable String id) {
        if (id == null) {
            return null;
        }
        if (id.indexOf(58) == -1) {
            return "minecraft:" + id;
        }
        return id;
    }

    public static int getDisplayWidth() {
        return FMLClientHandler.instance().getClient().displayWidth;
    }

    public static int getDisplayHeight() {
        return FMLClientHandler.instance().getClient().displayHeight;
    }

    public static boolean areItemStacksEqual(@NotNull ItemStack itemStack1, @NotNull ItemStack itemStack2) {
        return itemStack1.isItemEqual(itemStack2) && itemStack1.getCount() == itemStack2.getCount();
    }

    @NotNull
    public static ItemStack getSlotStack(@NotNull Container container, int i) {
        Slot slot = (Slot)container.inventorySlots.get(i);
        return slot == null ? ItemStack.EMPTY : slot.getStack();
    }

    public static int getSlotNumber(Slot slot) {
        try {
            if (slot instanceof GuiContainerCreative.CreativeSlot) {
                Slot underlyingSlot = ((GuiContainerCreative.CreativeSlot)slot).slot;
                if (underlyingSlot != null) {
                    return underlyingSlot.slotNumber;
                }
                log.warn("Creative inventory: Failed to get real slot");
            }
        }
        catch (Exception e) {
            log.warn("Failed to access creative slot number");
        }
        return slot.slotNumber;
    }

    @SideOnly(value=Side.CLIENT)
    @Nullable
    public static Slot getSlotAtMousePosition(@Nullable GuiContainer guiContainer) {
        if (guiContainer != null) {
            Container container = guiContainer.inventorySlots;
            int x = InvTweaksObfuscation.getMouseX(guiContainer);
            int y = InvTweaksObfuscation.getMouseY(guiContainer);
            for (int k = 0; k < container.inventorySlots.size(); ++k) {
                Slot slot = (Slot)container.inventorySlots.get(k);
                if (!InvTweaksObfuscation.getIsMouseOverSlot(guiContainer, slot, x, y)) continue;
                return slot;
            }
            return null;
        }
        return null;
    }

    @SideOnly(value=Side.CLIENT)
    private static boolean getIsMouseOverSlot(@Nullable GuiContainer guiContainer, @NotNull Slot slot, int x, int y) {
        if (guiContainer != null) {
            return (x -= guiContainer.guiLeft) >= slot.xPos - 1 && x < slot.xPos + 16 + 1 && (y -= guiContainer.guiTop) >= slot.yPos - 1 && y < slot.yPos + 16 + 1;
        }
        return false;
    }

    @SideOnly(value=Side.CLIENT)
    private static int getMouseX(@NotNull GuiContainer guiContainer) {
        return Mouse.getEventX() * guiContainer.width / InvTweaksObfuscation.getDisplayWidth();
    }

    @SideOnly(value=Side.CLIENT)
    private static int getMouseY(@NotNull GuiContainer guiContainer) {
        return guiContainer.height - Mouse.getEventY() * guiContainer.height / InvTweaksObfuscation.getDisplayHeight() - 1;
    }

    @Contract(value="!null->_")
    public static int getSpecialChestRowSize(Container container) {
        return 0;
    }

    @Contract(value="!null->_")
    public static boolean isValidChest(Container container) {
        return false;
    }

    @Contract(value="!null->_")
    public static boolean isLargeChest(Container container) {
        return false;
    }

    @Contract(value="!null->_")
    public static boolean isValidInventory(Container container) {
        return false;
    }

    @Contract(value="!null->_")
    public static boolean showButtons(Container container) {
        return false;
    }

    @Contract(value="!null->_")
    public static Map<ContainerSection, List<Slot>> getContainerSlotMap(Container container) {
        return null;
    }

    public static boolean isGuiContainer(@Nullable Object o) {
        return o != null && o instanceof GuiContainer;
    }

    public static boolean isGuiInventoryCreative(@Nullable Object o) {
        return o != null && o.getClass().equals(GuiContainerCreative.class);
    }

    public static boolean isGuiInventory(@Nullable Object o) {
        return o != null && o.getClass().equals(GuiInventory.class);
    }

    public static boolean isGuiButton(@Nullable Object o) {
        return o != null && o instanceof GuiButton;
    }

    public static boolean isGuiEditSign(@Nullable Object o) {
        return o != null && o.getClass().equals(GuiEditSign.class);
    }

    public static boolean isItemArmor(@Nullable Object o) {
        return o != null && o instanceof ItemArmor;
    }

    public static boolean isBasicSlot(@Nullable Object o) {
        return o != null && (o.getClass().equals(Slot.class) || o.getClass().equals(GuiContainerCreative.CreativeSlot.class));
    }

    public static Container getCurrentContainer() {
        Minecraft mc = FMLClientHandler.instance().getClient();
        Container currentContainer = mc.player.inventoryContainer;
        if (InvTweaksObfuscation.isGuiContainer(mc.currentScreen)) {
            currentContainer = ((GuiContainer)mc.currentScreen).inventorySlots;
        }
        return currentContainer;
    }

    public static boolean areSameItemType(@NotNull ItemStack itemStack1, @NotNull ItemStack itemStack2) {
        return !itemStack1.isEmpty() && !itemStack2.isEmpty() && (itemStack1.isItemEqual(itemStack2) || itemStack1.isItemStackDamageable() && itemStack1.getItem() == itemStack2.getItem());
    }

    public static boolean areItemsStackable(@NotNull ItemStack itemStack1, @NotNull ItemStack itemStack2) {
        return !itemStack1.isEmpty() && !itemStack2.isEmpty() && itemStack1.isItemEqual(itemStack2) && itemStack1.isStackable() && (!itemStack1.getHasSubtypes() || itemStack1.getItemDamage() == itemStack2.getItemDamage()) && ItemStack.areItemStackTagsEqual((ItemStack)itemStack1, (ItemStack)itemStack2);
    }

    public void addChatMessage(@NotNull String message) {
        if (this.mc.ingameGUI != null) {
            this.mc.ingameGUI.getChatGUI().printChatMessage((ITextComponent)new TextComponentString(message));
        }
    }

    public EntityPlayer getThePlayer() {
        return this.mc.player;
    }

    public PlayerControllerMP getPlayerController() {
        return this.mc.playerController;
    }

    @Nullable
    public GuiScreen getCurrentScreen() {
        return this.mc.currentScreen;
    }

    public FontRenderer getFontRenderer() {
        return this.mc.fontRenderer;
    }

    public void displayGuiScreen(GuiScreen parentScreen) {
        this.mc.displayGuiScreen(parentScreen);
    }

    public GameSettings getGameSettings() {
        return this.mc.gameSettings;
    }

    public int getKeyBindingForwardKeyCode() {
        return this.getGameSettings().keyBindForward.keyCode;
    }

    public int getKeyBindingBackKeyCode() {
        return this.getGameSettings().keyBindBack.keyCode;
    }

    public InventoryPlayer getInventoryPlayer() {
        return this.getThePlayer().inventory;
    }

    public NonNullList<ItemStack> getMainInventory() {
        return this.getInventoryPlayer().mainInventory;
    }

    @NotNull
    public ItemStack getHeldStack() {
        return this.getInventoryPlayer().getItemStack();
    }

    @NotNull
    public ItemStack getFocusedStack() {
        return this.getInventoryPlayer().getCurrentItem();
    }

    public int getFocusedSlot() {
        return this.getInventoryPlayer().currentItem;
    }

    public boolean hasTexture(@NotNull ResourceLocation texture) {
        try {
            this.mc.getResourceManager().getResource(texture);
        }
        catch (IOException e) {
            return false;
        }
        return true;
    }

    @NotNull
    public ItemStack getOffhandStack() {
        return (ItemStack)this.getInventoryPlayer().offHandInventory.get(0);
    }
}

