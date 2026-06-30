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
        return FMLClientHandler.instance().getClient().field_71443_c;
    }

    public static int getDisplayHeight() {
        return FMLClientHandler.instance().getClient().field_71440_d;
    }

    public static boolean areItemStacksEqual(@NotNull ItemStack itemStack1, @NotNull ItemStack itemStack2) {
        return itemStack1.func_77969_a(itemStack2) && itemStack1.func_190916_E() == itemStack2.func_190916_E();
    }

    @NotNull
    public static ItemStack getSlotStack(@NotNull Container container, int i) {
        Slot slot = (Slot)container.field_75151_b.get(i);
        return slot == null ? ItemStack.field_190927_a : slot.func_75211_c();
    }

    public static int getSlotNumber(Slot slot) {
        try {
            if (slot instanceof GuiContainerCreative.CreativeSlot) {
                Slot underlyingSlot = ((GuiContainerCreative.CreativeSlot)slot).field_148332_b;
                if (underlyingSlot != null) {
                    return underlyingSlot.field_75222_d;
                }
                log.warn("Creative inventory: Failed to get real slot");
            }
        }
        catch (Exception e) {
            log.warn("Failed to access creative slot number");
        }
        return slot.field_75222_d;
    }

    @SideOnly(value=Side.CLIENT)
    @Nullable
    public static Slot getSlotAtMousePosition(@Nullable GuiContainer guiContainer) {
        if (guiContainer != null) {
            Container container = guiContainer.field_147002_h;
            int x = InvTweaksObfuscation.getMouseX(guiContainer);
            int y = InvTweaksObfuscation.getMouseY(guiContainer);
            for (int k = 0; k < container.field_75151_b.size(); ++k) {
                Slot slot = (Slot)container.field_75151_b.get(k);
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
            return (x -= guiContainer.field_147003_i) >= slot.field_75223_e - 1 && x < slot.field_75223_e + 16 + 1 && (y -= guiContainer.field_147009_r) >= slot.field_75221_f - 1 && y < slot.field_75221_f + 16 + 1;
        }
        return false;
    }

    @SideOnly(value=Side.CLIENT)
    private static int getMouseX(@NotNull GuiContainer guiContainer) {
        return Mouse.getEventX() * guiContainer.field_146294_l / InvTweaksObfuscation.getDisplayWidth();
    }

    @SideOnly(value=Side.CLIENT)
    private static int getMouseY(@NotNull GuiContainer guiContainer) {
        return guiContainer.field_146295_m - Mouse.getEventY() * guiContainer.field_146295_m / InvTweaksObfuscation.getDisplayHeight() - 1;
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
        Container currentContainer = mc.field_71439_g.field_71069_bz;
        if (InvTweaksObfuscation.isGuiContainer(mc.field_71462_r)) {
            currentContainer = ((GuiContainer)mc.field_71462_r).field_147002_h;
        }
        return currentContainer;
    }

    public static boolean areSameItemType(@NotNull ItemStack itemStack1, @NotNull ItemStack itemStack2) {
        return !itemStack1.func_190926_b() && !itemStack2.func_190926_b() && (itemStack1.func_77969_a(itemStack2) || itemStack1.func_77984_f() && itemStack1.func_77973_b() == itemStack2.func_77973_b());
    }

    public static boolean areItemsStackable(@NotNull ItemStack itemStack1, @NotNull ItemStack itemStack2) {
        return !itemStack1.func_190926_b() && !itemStack2.func_190926_b() && itemStack1.func_77969_a(itemStack2) && itemStack1.func_77985_e() && (!itemStack1.func_77981_g() || itemStack1.func_77952_i() == itemStack2.func_77952_i()) && ItemStack.func_77970_a((ItemStack)itemStack1, (ItemStack)itemStack2);
    }

    public void addChatMessage(@NotNull String message) {
        if (this.mc.field_71456_v != null) {
            this.mc.field_71456_v.func_146158_b().func_146227_a((ITextComponent)new TextComponentString(message));
        }
    }

    public EntityPlayer getThePlayer() {
        return this.mc.field_71439_g;
    }

    public PlayerControllerMP getPlayerController() {
        return this.mc.field_71442_b;
    }

    @Nullable
    public GuiScreen getCurrentScreen() {
        return this.mc.field_71462_r;
    }

    public FontRenderer getFontRenderer() {
        return this.mc.field_71466_p;
    }

    public void displayGuiScreen(GuiScreen parentScreen) {
        this.mc.func_147108_a(parentScreen);
    }

    public GameSettings getGameSettings() {
        return this.mc.field_71474_y;
    }

    public int getKeyBindingForwardKeyCode() {
        return this.getGameSettings().field_74351_w.field_74512_d;
    }

    public int getKeyBindingBackKeyCode() {
        return this.getGameSettings().field_74368_y.field_74512_d;
    }

    public InventoryPlayer getInventoryPlayer() {
        return this.getThePlayer().field_71071_by;
    }

    public NonNullList<ItemStack> getMainInventory() {
        return this.getInventoryPlayer().field_70462_a;
    }

    @NotNull
    public ItemStack getHeldStack() {
        return this.getInventoryPlayer().func_70445_o();
    }

    @NotNull
    public ItemStack getFocusedStack() {
        return this.getInventoryPlayer().func_70448_g();
    }

    public int getFocusedSlot() {
        return this.getInventoryPlayer().field_70461_c;
    }

    public boolean hasTexture(@NotNull ResourceLocation texture) {
        try {
            this.mc.func_110442_L().func_110536_a(texture);
        }
        catch (IOException e) {
            return false;
        }
        return true;
    }

    @NotNull
    public ItemStack getOffhandStack() {
        return (ItemStack)this.getInventoryPlayer().field_184439_c.get(0);
    }
}

