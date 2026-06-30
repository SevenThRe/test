/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.audio.ISound
 *  net.minecraft.client.audio.PositionedSoundRecord
 *  net.minecraft.client.gui.GuiButton
 *  net.minecraft.client.gui.GuiScreen
 *  net.minecraft.client.gui.inventory.GuiContainer
 *  net.minecraft.client.gui.inventory.GuiCrafting
 *  net.minecraft.client.gui.inventory.GuiInventory
 *  net.minecraft.client.resources.I18n
 *  net.minecraft.enchantment.Enchantment
 *  net.minecraft.enchantment.EnchantmentHelper
 *  net.minecraft.init.Items
 *  net.minecraft.init.SoundEvents
 *  net.minecraft.inventory.Container
 *  net.minecraft.inventory.Slot
 *  net.minecraft.item.ItemStack
 *  net.minecraft.util.NonNullList
 *  net.minecraft.util.ResourceLocation
 *  net.minecraft.util.SoundEvent
 *  org.apache.commons.lang3.ObjectUtils
 *  org.apache.logging.log4j.Logger
 *  org.jetbrains.annotations.NotNull
 *  org.jetbrains.annotations.Nullable
 *  org.lwjgl.LWJGLException
 *  org.lwjgl.input.Keyboard
 *  org.lwjgl.input.Mouse
 *  org.lwjgl.opengl.Display
 */
package invtweaks;

import invtweaks.InvTweaksConfig;
import invtweaks.InvTweaksConfigManager;
import invtweaks.InvTweaksGuiSettingsButton;
import invtweaks.InvTweaksGuiSortingButton;
import invtweaks.InvTweaksHandlerSorting;
import invtweaks.InvTweaksItemTree;
import invtweaks.InvTweaksObfuscation;
import invtweaks.InvTweaksShortcutMapping;
import invtweaks.InvTweaksShortcutType;
import invtweaks.api.IItemTreeItem;
import invtweaks.api.SortingMethod;
import invtweaks.api.container.ContainerSection;
import invtweaks.container.ContainerSectionManager;
import invtweaks.container.DirectContainerManager;
import invtweaks.container.IContainerManager;
import invtweaks.forge.InvTweaksMod;
import invtweaks.integration.ItemListChecker;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.logging.Level;
import java.util.stream.Collectors;
import net.minecraft.client.Minecraft;
import net.minecraft.client.audio.ISound;
import net.minecraft.client.audio.PositionedSoundRecord;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.gui.inventory.GuiCrafting;
import net.minecraft.client.gui.inventory.GuiInventory;
import net.minecraft.client.resources.I18n;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.init.Items;
import net.minecraft.init.SoundEvents;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.logging.log4j.Logger;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.lwjgl.LWJGLException;
import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.Display;

public class InvTweaks
extends InvTweaksObfuscation {
    public static Logger log;
    private static InvTweaks instance;
    @Nullable
    private InvTweaksConfigManager cfgManager = null;
    private SortingMethod chestAlgorithm = SortingMethod.DEFAULT;
    private long chestAlgorithmClickTimestamp = 0L;
    private boolean chestAlgorithmButtonDown = false;
    @NotNull
    private ItemStack storedStack = ItemStack.EMPTY;
    @Nullable
    private String storedStackId = null;
    private int storedStackDamage = Short.MAX_VALUE;
    private int storedFocusedSlot = -1;
    @NotNull
    private final ItemStack[] hotbarClone = new ItemStack[9];
    private boolean hadFocus = true;
    private boolean mouseWasDown = false;
    private boolean wasInGUI = false;
    private boolean previousRecipeBookVisibility = false;
    private int tickNumber = 0;
    private int lastPollingTickNumber = -3;
    private long sortingKeyPressedDate = 0L;
    private boolean sortKeyDown = false;
    private boolean sortKeyEnabled = true;
    private boolean textboxMode = false;
    private boolean itemPickupPending = false;
    private int itemPickupTimeout = 0;
    @NotNull
    private final List<String> queuedMessages = new ArrayList<String>();
    private final ItemListChecker itemListChecker = new ItemListChecker();

    public InvTweaks(Minecraft mc_) {
        super(mc_);
        for (int i = 0; i < this.hotbarClone.length; ++i) {
            this.hotbarClone[i] = ItemStack.EMPTY;
        }
        instance = this;
        this.cfgManager = new InvTweaksConfigManager(this.mc);
        if (this.cfgManager.makeSureConfigurationIsLoaded()) {
            log.info("Mod initialized");
        } else {
            log.error("Mod failed to initialize!");
        }
    }

    public static void logInGameStatic(@NotNull String message) {
        InvTweaks.getInstance().logInGame(message);
    }

    public static void logInGameErrorStatic(@NotNull String message, @NotNull Exception e) {
        InvTweaks.getInstance().logInGameError(message, e);
    }

    public static InvTweaks getInstance() {
        return instance;
    }

    public static Minecraft getMinecraftInstance() {
        return InvTweaks.instance.mc;
    }

    @Nullable
    public static InvTweaksConfigManager getConfigManager() {
        return InvTweaks.instance.cfgManager;
    }

    @NotNull
    public static IContainerManager getContainerManager(@NotNull Container container) {
        return new DirectContainerManager(container);
    }

    @NotNull
    public static IContainerManager getCurrentContainerManager() {
        return InvTweaks.getContainerManager(InvTweaksObfuscation.getCurrentContainer());
    }

    private static int getContainerRowSize(@NotNull GuiContainer guiContainer) {
        return InvTweaks.getSpecialChestRowSize(guiContainer.inventorySlots);
    }

    @NotNull
    private static String buildLogString(@NotNull Level level, String message, @Nullable Exception e) {
        if (e != null) {
            StackTraceElement[] trace = e.getStackTrace();
            if (trace.length == 0) {
                return InvTweaks.buildLogString(level, message) + ": " + e.getMessage();
            }
            StackTraceElement exceptionLine = trace[0];
            if (exceptionLine != null && exceptionLine.getFileName() != null) {
                return InvTweaks.buildLogString(level, message) + ": " + e.getMessage() + " (l" + exceptionLine.getLineNumber() + " in " + exceptionLine.getFileName().replace("InvTweaks", "") + ")";
            }
            return InvTweaks.buildLogString(level, message) + ": " + e.getMessage();
        }
        return InvTweaks.buildLogString(level, message);
    }

    @NotNull
    private static String buildLogString(@NotNull Level level, String message) {
        return "InvTweaks: " + (level.equals(Level.SEVERE) ? "[ERROR] " : "") + message;
    }

    public void addScheduledTask(Runnable task) {
        InvTweaksMod.proxy.addClientScheduledTask(task);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public void onTickInGame() {
        InvTweaks invTweaks = this;
        synchronized (invTweaks) {
            if (!this.onTick()) {
                return;
            }
            this.handleAutoRefill();
            if (this.wasInGUI) {
                this.wasInGUI = false;
                this.textboxMode = false;
            }
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public void onTickInGUI(GuiScreen guiScreen) {
        InvTweaks invTweaks = this;
        synchronized (invTweaks) {
            ItemStack currentStack;
            this.handleMiddleClick(guiScreen);
            if (!this.onTick()) {
                return;
            }
            if (this.isTimeForPolling()) {
                this.unlockKeysIfNecessary();
            }
            if (InvTweaks.isGuiContainer(guiScreen)) {
                this.handleGUILayout((GuiContainer)guiScreen);
            }
            if (!this.wasInGUI) {
                this.mouseWasDown = true;
            }
            if (InvTweaks.isGuiContainer(guiScreen)) {
                this.handleShortcuts((GuiContainer)guiScreen);
            }
            this.storedStackId = (currentStack = this.getFocusedStack()).isEmpty() ? null : currentStack.getItem().getRegistryName().toString();
            int n = this.storedStackDamage = currentStack.isEmpty() ? 0 : currentStack.getItemDamage();
            if (!this.wasInGUI) {
                this.wasInGUI = true;
            }
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public final void onSortingKeyPressed() {
        InvTweaks invTweaks = this;
        synchronized (invTweaks) {
            if (!this.cfgManager.makeSureConfigurationIsLoaded()) {
                return;
            }
            GuiScreen guiScreen = this.getCurrentScreen();
            if (guiScreen == null || InvTweaks.isGuiContainer(guiScreen) && (InvTweaks.isValidChest(((GuiContainer)guiScreen).inventorySlots) || InvTweaks.isValidInventory(((GuiContainer)guiScreen).inventorySlots))) {
                this.handleSorting(guiScreen);
            }
        }
    }

    public void onItemPickup() {
        if (!this.cfgManager.makeSureConfigurationIsLoaded()) {
            return;
        }
        InvTweaksConfig config = this.cfgManager.getConfig();
        if (this.cfgManager.getConfig().getProperty("enableSortingOnPickup").equals("false")) {
            this.itemPickupPending = false;
            return;
        }
        try {
            ContainerSectionManager containerMgr = new ContainerSectionManager(ContainerSection.INVENTORY);
            int currentSlot = -1;
            for (int i = 0; i < 9; ++i) {
                ItemStack currentHotbarStack = containerMgr.getItemStack(i + 27);
                if (currentHotbarStack.isEmpty() || currentHotbarStack.getAnimationsToGo() <= 0 || !this.hotbarClone[i].isEmpty()) continue;
                currentSlot = i + 27;
            }
            if (currentSlot != -1) {
                this.itemPickupPending = false;
                InvTweaksItemTree tree = config.getTree();
                ItemStack stack = containerMgr.getItemStack(currentSlot);
                List<IItemTreeItem> items = tree.getItems(stack.getItem().getRegistryName().toString(), stack.getItemDamage());
                List preferredPositions = config.getRules().stream().filter(rule -> tree.matches(items, rule.getKeyword())).flatMapToInt(e -> Arrays.stream(e.getPreferredSlots())).boxed().collect(Collectors.toList());
                boolean hasToBeMoved = true;
                Iterator iterator = preferredPositions.iterator();
                while (iterator.hasNext()) {
                    int newSlot = (Integer)iterator.next();
                    if (newSlot == currentSlot) {
                        hasToBeMoved = false;
                        break;
                    }
                    if (!containerMgr.getItemStack(newSlot).isEmpty() || !containerMgr.move(currentSlot, newSlot)) continue;
                    break;
                }
                if (hasToBeMoved) {
                    for (int i = 0; !(i >= containerMgr.getSize() || containerMgr.getItemStack(i).isEmpty() && containerMgr.move(currentSlot, i)); ++i) {
                    }
                }
                containerMgr.applyChanges();
            } else if (--this.itemPickupTimeout == 0) {
                this.itemPickupPending = false;
            }
        }
        catch (Exception e2) {
            this.logInGameError("Failed to move picked up stack", e2);
            this.itemPickupPending = false;
        }
    }

    public int compareItems(@NotNull ItemStack i, @NotNull ItemStack j) {
        return this.compareItems(i, j, this.getItemOrder(i), this.getItemOrder(j));
    }

    int compareItems(@NotNull ItemStack i, @NotNull ItemStack j, int orderI, int orderJ) {
        if (j.isEmpty()) {
            return -1;
        }
        if (i.isEmpty() || orderI == -1) {
            return 1;
        }
        if (orderI == orderJ) {
            if (i.getItem() == j.getItem()) {
                boolean iHasName = i.hasDisplayName();
                boolean jHasName = j.hasDisplayName();
                if (iHasName || jHasName) {
                    String jDisplayName;
                    if (!iHasName) {
                        return -1;
                    }
                    if (!jHasName) {
                        return 1;
                    }
                    String iDisplayName = i.getDisplayName();
                    if (!iDisplayName.equals(jDisplayName = j.getDisplayName())) {
                        return iDisplayName.compareTo(jDisplayName);
                    }
                }
                Map iEnchs = EnchantmentHelper.getEnchantments((ItemStack)i);
                Map jEnchs = EnchantmentHelper.getEnchantments((ItemStack)j);
                if (iEnchs.size() == jEnchs.size()) {
                    int enchId;
                    int iEnchMaxId = 0;
                    int iEnchMaxLvl = 0;
                    int jEnchMaxId = 0;
                    int jEnchMaxLvl = 0;
                    for (Map.Entry ench : iEnchs.entrySet()) {
                        enchId = Enchantment.getEnchantmentID((Enchantment)((Enchantment)ench.getKey()));
                        if ((Integer)ench.getValue() > iEnchMaxLvl) {
                            iEnchMaxId = enchId;
                            iEnchMaxLvl = (Integer)ench.getValue();
                            continue;
                        }
                        if ((Integer)ench.getValue() != iEnchMaxLvl || enchId <= iEnchMaxId) continue;
                        iEnchMaxId = enchId;
                    }
                    for (Map.Entry ench : jEnchs.entrySet()) {
                        enchId = Enchantment.getEnchantmentID((Enchantment)((Enchantment)ench.getKey()));
                        if ((Integer)ench.getValue() > jEnchMaxLvl) {
                            jEnchMaxId = enchId;
                            jEnchMaxLvl = (Integer)ench.getValue();
                            continue;
                        }
                        if ((Integer)ench.getValue() != jEnchMaxLvl || enchId <= jEnchMaxId) continue;
                        jEnchMaxId = enchId;
                    }
                    if (iEnchMaxId == jEnchMaxId) {
                        if (iEnchMaxLvl == jEnchMaxLvl) {
                            if (i.getItemDamage() != j.getItemDamage()) {
                                if (i.isItemStackDamageable() && !InvTweaks.getConfigManager().getConfig().getProperty("invertToolDamageSorting").equals("true")) {
                                    return j.getItemDamage() - i.getItemDamage();
                                }
                                return i.getItemDamage() - j.getItemDamage();
                            }
                            return j.getCount() - i.getCount();
                        }
                        return jEnchMaxLvl - iEnchMaxLvl;
                    }
                    return jEnchMaxId - iEnchMaxId;
                }
                return jEnchs.size() - iEnchs.size();
            }
            return ObjectUtils.compare((Comparable)((Object)i.getItem().getRegistryName().toString()), (Comparable)((Object)j.getItem().getRegistryName().toString()));
        }
        return orderI - orderJ;
    }

    public void setItemPickupPending(boolean value) {
        this.itemPickupPending = value;
        this.itemPickupTimeout = 5;
    }

    public void setSortKeyEnabled(boolean enabled) {
        this.sortKeyEnabled = enabled;
    }

    public void setTextboxMode(boolean enabled) {
        this.textboxMode = enabled;
    }

    public void logInGame(@NotNull String message) {
        this.logInGame(message, false);
    }

    public void printQueuedMessages() {
        if (this.mc.ingameGUI != null && !this.queuedMessages.isEmpty()) {
            this.queuedMessages.forEach(this::addChatMessage);
            this.queuedMessages.clear();
        }
    }

    public void logInGame(@NotNull String message, boolean alreadyTranslated) {
        String formattedMsg = InvTweaks.buildLogString(Level.INFO, alreadyTranslated ? message : I18n.format((String)message, (Object[])new Object[0]));
        if (this.mc.ingameGUI == null) {
            this.queuedMessages.add(formattedMsg);
        } else {
            this.addChatMessage(formattedMsg);
        }
        log.info(formattedMsg);
    }

    public void logInGameError(@NotNull String message, @NotNull Exception e) {
        String formattedMsg = InvTweaks.buildLogString(Level.SEVERE, I18n.format((String)message, (Object[])new Object[0]), e);
        log.error(formattedMsg, (Throwable)e);
        if (this.mc.ingameGUI == null) {
            this.queuedMessages.add(formattedMsg);
        } else {
            this.addChatMessage(formattedMsg);
        }
    }

    private boolean onTick() {
        GuiScreen currentScreen;
        this.printQueuedMessages();
        ++this.tickNumber;
        InvTweaksConfig config = this.cfgManager.getConfig();
        if (config == null) {
            return false;
        }
        if (this.itemPickupPending) {
            this.onItemPickup();
        }
        if ((currentScreen = this.getCurrentScreen()) == null || InvTweaks.isGuiInventory(currentScreen)) {
            this.cloneHotbar();
        }
        if (this.isSortingShortcutDown()) {
            if (!this.sortKeyDown) {
                this.sortKeyDown = true;
                this.onSortingKeyPressed();
            }
        } else {
            this.sortKeyDown = false;
        }
        this.handleConfigSwitch();
        return true;
    }

    private void handleConfigSwitch() {
        InvTweaksConfig config = this.cfgManager.getConfig();
        GuiScreen currentScreen = this.getCurrentScreen();
        this.cfgManager.getShortcutsHandler().updatePressedKeys();
        InvTweaksShortcutMapping switchMapping = this.cfgManager.getShortcutsHandler().isShortcutDown(InvTweaksShortcutType.MOVE_TO_SPECIFIC_HOTBAR_SLOT);
        if (this.isSortingShortcutDown() && switchMapping != null) {
            String newRuleset = null;
            int pressedKey = switchMapping.getKeyCodes().get(0);
            if (pressedKey >= 2 && pressedKey <= 10) {
                newRuleset = config.switchConfig(pressedKey - 2);
            } else {
                switch (pressedKey) {
                    case 79: {
                        newRuleset = config.switchConfig(0);
                        break;
                    }
                    case 80: {
                        newRuleset = config.switchConfig(1);
                        break;
                    }
                    case 81: {
                        newRuleset = config.switchConfig(2);
                        break;
                    }
                    case 75: {
                        newRuleset = config.switchConfig(3);
                        break;
                    }
                    case 76: {
                        newRuleset = config.switchConfig(4);
                        break;
                    }
                    case 77: {
                        newRuleset = config.switchConfig(5);
                        break;
                    }
                    case 71: {
                        newRuleset = config.switchConfig(6);
                        break;
                    }
                    case 72: {
                        newRuleset = config.switchConfig(7);
                        break;
                    }
                    case 73: {
                        newRuleset = config.switchConfig(8);
                    }
                }
            }
            if (newRuleset != null) {
                this.logInGame(String.format(I18n.format((String)"invtweaks.loadconfig.enabled", (Object[])new Object[0]), newRuleset), true);
                this.sortingKeyPressedDate = Integer.MAX_VALUE;
            }
        }
        if (this.isSortingShortcutDown()) {
            long currentTime = System.currentTimeMillis();
            if (this.sortingKeyPressedDate == 0L) {
                this.sortingKeyPressedDate = currentTime;
            } else if (currentTime - this.sortingKeyPressedDate > 1000L && this.sortingKeyPressedDate != Integer.MAX_VALUE) {
                String previousRuleset = config.getCurrentRulesetName();
                String newRuleset = config.switchConfig();
                if (previousRuleset != null && newRuleset != null && !previousRuleset.equals(newRuleset)) {
                    this.logInGame(String.format(I18n.format((String)"invtweaks.loadconfig.enabled", (Object[])new Object[0]), newRuleset), true);
                    this.handleSorting(currentScreen);
                }
                this.sortingKeyPressedDate = currentTime;
            }
        } else {
            this.sortingKeyPressedDate = 0L;
        }
    }

    private void handleSorting(GuiScreen guiScreen) {
        NonNullList<ItemStack> mainInventory;
        ItemStack selectedItem = ItemStack.EMPTY;
        int focusedSlot = this.getFocusedSlot();
        if (focusedSlot < (mainInventory = this.getMainInventory()).size() && focusedSlot >= 0) {
            selectedItem = (ItemStack)mainInventory.get(focusedSlot);
        }
        try {
            new InvTweaksHandlerSorting(this.mc, this.cfgManager.getConfig(), ContainerSection.INVENTORY, SortingMethod.INVENTORY, 9).sort();
        }
        catch (Exception e) {
            this.logInGameError("invtweaks.sort.inventory.error", e);
            e.printStackTrace();
        }
        this.playClick();
    }

    private void handleAutoRefill() {
        ItemStack currentStack = this.getFocusedStack();
        ItemStack offhandStack = this.getOffhandStack();
        String currentStackId = currentStack.isEmpty() ? null : currentStack.getItem().getRegistryName().toString();
        int currentStackDamage = currentStack.isEmpty() ? 0 : currentStack.getItemDamage();
        int focusedSlot = this.getFocusedSlot() + 27;
        InvTweaksConfig config = this.cfgManager.getConfig();
        if (this.storedFocusedSlot != focusedSlot) {
            this.storedFocusedSlot = focusedSlot;
        } else if (!(ItemStack.areItemsEqual((ItemStack)currentStack, (ItemStack)this.storedStack) || this.storedStackId == null || this.storedStack.isEmpty() || ItemStack.areItemStacksEqual((ItemStack)offhandStack, (ItemStack)this.storedStack))) {
            if (currentStack.isEmpty() || currentStack.getItem() == Items.BOWL && Objects.equals(this.storedStackId, "minecraft:mushroom_stew") && (this.getCurrentScreen() == null || InvTweaks.isGuiEditSign(this.getCurrentScreen()))) {
                if (config.isAutoRefillEnabled(this.storedStackId, this.storedStackDamage)) {
                    try {
                        this.cfgManager.getAutoRefillHandler().autoRefillSlot(focusedSlot, this.storedStackId, this.storedStackDamage);
                    }
                    catch (Exception e) {
                        this.logInGameError("invtweaks.sort.autorefill.error", e);
                    }
                }
            } else {
                int autoRefillThreshhold;
                int itemMaxDamage = currentStack.getMaxDamage();
                if (this.canToolBeReplaced(currentStackDamage, itemMaxDamage, autoRefillThreshhold = config.getIntProperty("autoRefillDamageThreshhold")) && config.getProperty("autoRefillBeforeBreak").equals("true") && config.isAutoRefillEnabled(this.storedStackId, this.storedStackDamage)) {
                    try {
                        this.cfgManager.getAutoRefillHandler().autoRefillSlot(focusedSlot, this.storedStackId, this.storedStackDamage);
                    }
                    catch (Exception e) {
                        this.logInGameError("invtweaks.sort.autorefill.error", e);
                    }
                }
            }
        }
        this.storedStack = currentStack.copy();
        this.storedStackId = currentStackId;
        this.storedStackDamage = currentStackDamage;
    }

    private boolean canToolBeReplaced(int currentStackDamage, int itemMaxDamage, int autoRefillThreshhold) {
        return itemMaxDamage != 0 && itemMaxDamage - currentStackDamage < autoRefillThreshhold && itemMaxDamage - this.storedStackDamage >= autoRefillThreshhold;
    }

    private void handleMiddleClick(GuiScreen guiScreen) {
        if (Mouse.isButtonDown((int)2)) {
            if (!this.cfgManager.makeSureConfigurationIsLoaded()) {
                return;
            }
            InvTweaksConfig config = this.cfgManager.getConfig();
            if (config.getProperty("enableMiddleClick").equals("true") && InvTweaks.isGuiContainer(guiScreen)) {
                GuiContainer guiContainer = (GuiContainer)guiScreen;
                Container container = guiContainer.inventorySlots;
                if (!this.chestAlgorithmButtonDown) {
                    this.chestAlgorithmButtonDown = true;
                    IContainerManager containerMgr = InvTweaks.getContainerManager(container);
                    Slot slotAtMousePosition = InvTweaksObfuscation.getSlotAtMousePosition((GuiContainer)this.getCurrentScreen());
                    ContainerSection target = null;
                    if (slotAtMousePosition != null) {
                        target = containerMgr.getSlotSection(InvTweaks.getSlotNumber(slotAtMousePosition));
                    }
                    if (InvTweaks.isValidChest(container)) {
                        if (ContainerSection.CHEST.equals((Object)target)) {
                            this.playClick();
                            long timestamp = System.currentTimeMillis();
                            if (timestamp - this.chestAlgorithmClickTimestamp > 2000L || InvTweaks.getContainerRowSize(guiContainer) > 9) {
                                this.chestAlgorithm = SortingMethod.DEFAULT;
                            }
                            try {
                                new InvTweaksHandlerSorting(this.mc, this.cfgManager.getConfig(), ContainerSection.CHEST, this.chestAlgorithm, InvTweaks.getContainerRowSize(guiContainer)).sort();
                            }
                            catch (Exception e) {
                                this.logInGameError("invtweaks.sort.chest.error", e);
                                e.printStackTrace();
                            }
                            this.chestAlgorithm = SortingMethod.values()[(this.chestAlgorithm.ordinal() + 1) % 3];
                            this.chestAlgorithmClickTimestamp = timestamp;
                        } else if (ContainerSection.CRAFTING_IN.equals((Object)target) || ContainerSection.CRAFTING_IN_PERSISTENT.equals((Object)target)) {
                            try {
                                new InvTweaksHandlerSorting(this.mc, this.cfgManager.getConfig(), target, SortingMethod.EVEN_STACKS, containerMgr.getSize(target) == 9 ? 3 : 2).sort();
                            }
                            catch (Exception e) {
                                this.logInGameError("invtweaks.sort.crafting.error", e);
                                e.printStackTrace();
                            }
                        } else if (ContainerSection.INVENTORY_HOTBAR.equals((Object)target) || ContainerSection.INVENTORY_NOT_HOTBAR.equals((Object)target)) {
                            this.handleSorting(guiScreen);
                        }
                    } else if (InvTweaks.isValidInventory(container)) {
                        if (ContainerSection.CRAFTING_IN.equals((Object)target) || ContainerSection.CRAFTING_IN_PERSISTENT.equals((Object)target)) {
                            try {
                                new InvTweaksHandlerSorting(this.mc, this.cfgManager.getConfig(), target, SortingMethod.EVEN_STACKS, containerMgr.getSize(target) == 9 ? 3 : 2).sort();
                            }
                            catch (Exception e) {
                                this.logInGameError("invtweaks.sort.crafting.error", e);
                                e.printStackTrace();
                            }
                        } else {
                            this.handleSorting(guiScreen);
                        }
                    }
                }
            }
        } else {
            this.chestAlgorithmButtonDown = false;
        }
    }

    private boolean hasRecipeButton(@NotNull GuiContainer guiContainer) {
        if (guiContainer instanceof GuiInventory) {
            return true;
        }
        return guiContainer instanceof GuiCrafting;
    }

    private boolean isRecipeBookVisible(@NotNull GuiContainer guiContainer) {
        if (guiContainer instanceof GuiInventory) {
            return ((GuiInventory)guiContainer).recipeBookGui.isVisible();
        }
        if (guiContainer instanceof GuiCrafting) {
            return ((GuiCrafting)guiContainer).recipeBookGui.isVisible();
        }
        return false;
    }

    private void handleGUILayout(@NotNull GuiContainer guiContainer) {
        InvTweaksConfig config = this.cfgManager.getConfig();
        Container container = guiContainer.inventorySlots;
        boolean isValidChest = InvTweaks.isValidChest(container);
        if (InvTweaks.showButtons(container)) {
            int w = 10;
            int h = 10;
            boolean isItemListVisible = this.itemListChecker.isVisible();
            boolean wasItemListVisible = this.itemListChecker.wasVisible();
            boolean isRecipeBookVisible = this.isRecipeBookVisible(guiContainer);
            boolean wasRecipeBookVisible = this.previousRecipeBookVisibility;
            boolean relayout = isItemListVisible != wasItemListVisible || isRecipeBookVisible != wasRecipeBookVisible;
            this.previousRecipeBookVisibility = isRecipeBookVisible;
            boolean customButtonsAdded = false;
            List controlList = guiContainer.buttonList;
            ArrayList<GuiButton> toRemove = new ArrayList<GuiButton>();
            for (GuiButton button : controlList) {
                if (button.id < 54696386 || button.id >= 54696390) continue;
                if (relayout) {
                    toRemove.add(button);
                    continue;
                }
                customButtonsAdded = true;
                break;
            }
            controlList.removeAll(toRemove);
            guiContainer.buttonList = controlList;
            if (!customButtonsAdded) {
                boolean customTextureAvailable = this.hasTexture(new ResourceLocation("inventorytweaks", "textures/gui/button10px.png"));
                int id = 54696386;
                int x = guiContainer.guiLeft + guiContainer.xSize - 16;
                int y = guiContainer.guiTop + 5;
                if (!isValidChest) {
                    controlList.add(new InvTweaksGuiSettingsButton(this.cfgManager, id, x, y, w, h, "...", I18n.format((String)"invtweaks.button.settings.tooltip", (Object[])new Object[0]), customTextureAvailable));
                } else {
                    this.chestAlgorithmClickTimestamp = 0L;
                    boolean isChestWayTooBig = InvTweaks.isLargeChest(guiContainer.inventorySlots);
                    if (isChestWayTooBig && isItemListVisible) {
                        x -= 20;
                        y += 50;
                    }
                    controlList.add(new InvTweaksGuiSettingsButton(this.cfgManager, id++, isChestWayTooBig ? x + 22 : x - 1, isChestWayTooBig ? y - 3 : y, w, h, "...", I18n.format((String)"invtweaks.button.settings.tooltip", (Object[])new Object[0]), customTextureAvailable));
                    if (!config.getProperty("showChestButtons").equals("false")) {
                        int rowSize = InvTweaks.getContainerRowSize(guiContainer);
                        InvTweaksGuiSortingButton button = new InvTweaksGuiSortingButton(this.cfgManager, id++, isChestWayTooBig ? x + 22 : x - 37, isChestWayTooBig ? y + 38 : y, w, h, "s", I18n.format((String)"invtweaks.button.chest1.tooltip", (Object[])new Object[0]), SortingMethod.DEFAULT, rowSize, customTextureAvailable);
                        controlList.add(button);
                        if (rowSize <= 9) {
                            button = new InvTweaksGuiSortingButton(this.cfgManager, id++, isChestWayTooBig ? x + 22 : x - 13, isChestWayTooBig ? y + 12 : y, w, h, "h", I18n.format((String)"invtweaks.button.chest3.tooltip", (Object[])new Object[0]), SortingMethod.HORIZONTAL, rowSize, customTextureAvailable);
                            controlList.add(button);
                            button = new InvTweaksGuiSortingButton(this.cfgManager, id++, isChestWayTooBig ? x + 22 : x - 25, isChestWayTooBig ? y + 25 : y, w, h, "v", I18n.format((String)"invtweaks.button.chest2.tooltip", (Object[])new Object[0]), SortingMethod.VERTICAL, rowSize, customTextureAvailable);
                            controlList.add(button);
                        }
                    }
                }
            }
        } else if (InvTweaks.isGuiInventoryCreative(guiContainer)) {
            List controlList = guiContainer.buttonList;
            GuiButton buttonToRemove = null;
            for (GuiButton o : controlList) {
                if (o.id != 54696386) continue;
                buttonToRemove = o;
                break;
            }
            if (buttonToRemove != null) {
                controlList.remove(buttonToRemove);
            }
        }
    }

    private void handleShortcuts(@NotNull GuiContainer guiScreen) {
        if (!InvTweaks.isValidChest(guiScreen.inventorySlots) && !InvTweaks.isValidInventory(guiScreen.inventorySlots)) {
            return;
        }
        if (Mouse.isButtonDown((int)0) || Mouse.isButtonDown((int)1)) {
            if (!this.mouseWasDown) {
                this.mouseWasDown = true;
                if (this.cfgManager.getConfig().getProperty("enableShortcuts").equals("true")) {
                    this.cfgManager.getShortcutsHandler().handleShortcut();
                }
            }
        } else {
            this.mouseWasDown = false;
        }
    }

    private int getItemOrder(@NotNull ItemStack itemStack) {
        List<IItemTreeItem> items = this.cfgManager.getConfig().getTree().getItems(itemStack.getItem().getRegistryName().toString(), itemStack.getItemDamage(), itemStack.getTagCompound());
        return items.size() > 0 ? items.get(0).getOrder() : Integer.MAX_VALUE;
    }

    private boolean isSortingShortcutDown() {
        if (this.sortKeyEnabled && !this.textboxMode) {
            int keyCode = this.cfgManager.getConfig().getSortKeyCode();
            if (keyCode > 0) {
                return Keyboard.isKeyDown((int)keyCode);
            }
            return Mouse.isButtonDown((int)(100 + keyCode));
        }
        return false;
    }

    private boolean isTimeForPolling() {
        if (this.tickNumber - this.lastPollingTickNumber >= 3) {
            this.lastPollingTickNumber = this.tickNumber;
        }
        return this.tickNumber - this.lastPollingTickNumber == 0;
    }

    private void unlockKeysIfNecessary() {
        boolean hasFocus = Display.isActive();
        if (!this.hadFocus && hasFocus) {
            Keyboard.destroy();
            boolean firstTry = true;
            while (!Keyboard.isCreated()) {
                try {
                    Keyboard.create();
                }
                catch (LWJGLException e) {
                    if (!firstTry) continue;
                    this.logInGameError("invtweaks.keyboardfix.error", (Exception)((Object)e));
                    firstTry = false;
                }
            }
            if (!firstTry) {
                this.logInGame("invtweaks.keyboardfix.recover");
            }
        }
        this.hadFocus = hasFocus;
    }

    private void cloneHotbar() {
        NonNullList<ItemStack> mainInventory = this.getMainInventory();
        for (int i = 0; i < 9; ++i) {
            this.hotbarClone[i] = ((ItemStack)mainInventory.get(i)).copy();
        }
    }

    private void playClick() {
        if (!this.cfgManager.getConfig().getProperty("enableSounds").equals("false")) {
            this.mc.getSoundHandler().playSound((ISound)PositionedSoundRecord.getMasterRecord((SoundEvent)SoundEvents.UI_BUTTON_CLICK, (float)1.0f));
        }
    }
}

