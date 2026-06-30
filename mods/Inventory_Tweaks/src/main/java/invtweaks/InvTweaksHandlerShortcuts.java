/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.gui.inventory.GuiContainer
 *  net.minecraft.inventory.Slot
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemStack
 *  net.minecraft.util.ResourceLocation
 *  org.jetbrains.annotations.NotNull
 *  org.jetbrains.annotations.Nullable
 *  org.lwjgl.input.Keyboard
 *  org.lwjgl.input.Mouse
 */
package invtweaks;

import invtweaks.InvTweaks;
import invtweaks.InvTweaksConfig;
import invtweaks.InvTweaksObfuscation;
import invtweaks.InvTweaksShortcutMapping;
import invtweaks.InvTweaksShortcutType;
import invtweaks.ShortcutSpecification;
import invtweaks.api.container.ContainerSection;
import invtweaks.container.IContainerManager;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeoutException;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.inventory.Slot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;

public class InvTweaksHandlerShortcuts
extends InvTweaksObfuscation {
    private static final int DROP_SLOT = -999;
    private InvTweaksConfig config;
    private IContainerManager container;
    private Map<Integer, Boolean> pressedKeys;
    private Map<InvTweaksShortcutType, List<InvTweaksShortcutMapping>> shortcuts;

    public InvTweaksHandlerShortcuts(Minecraft mc_, InvTweaksConfig config_) {
        super(mc_);
        this.config = config_;
        this.pressedKeys = new HashMap<Integer, Boolean>();
        this.shortcuts = new HashMap<InvTweaksShortcutType, List<InvTweaksShortcutMapping>>();
    }

    public void loadShortcuts() {
        int[] hotbarKeys;
        this.pressedKeys.clear();
        this.shortcuts.clear();
        Map<String, String> keys = this.config.getProperties("shortcutKey");
        for (String key : keys.keySet()) {
            String[] keyMappings = keys.get(key).split("[ ]*,[ ]*");
            InvTweaksShortcutType shortcutType = InvTweaksShortcutType.fromConfigKey(key);
            if (shortcutType == null) continue;
            String[] stringArray = keyMappings;
            int n = stringArray.length;
            for (int i = 0; i < n; ++i) {
                String keyMapping = stringArray[i];
                String[] keysToHold = keyMapping.split("\\+");
                this.registerShortcutMapping(shortcutType, new InvTweaksShortcutMapping(keysToHold));
            }
        }
        int upKeyCode = this.getKeyBindingForwardKeyCode();
        int downKeyCode = this.getKeyBindingBackKeyCode();
        this.registerShortcutMapping(InvTweaksShortcutType.MOVE_UP, new InvTweaksShortcutMapping(upKeyCode));
        this.registerShortcutMapping(InvTweaksShortcutType.MOVE_DOWN, new InvTweaksShortcutMapping(downKeyCode));
        for (int i : hotbarKeys = new int[]{2, 3, 4, 5, 6, 7, 8, 9, 10, 79, 80, 81, 75, 76, 77, 71, 72, 73}) {
            this.registerShortcutMapping(InvTweaksShortcutType.MOVE_TO_SPECIFIC_HOTBAR_SLOT, new InvTweaksShortcutMapping(i));
        }
        this.pressedKeys.put(42, false);
        this.pressedKeys.put(54, false);
    }

    private void registerShortcutMapping(InvTweaksShortcutType type, @NotNull InvTweaksShortcutMapping mapping) {
        if (this.shortcuts.containsKey((Object)type)) {
            this.shortcuts.get((Object)type).add(mapping);
        } else {
            LinkedList<InvTweaksShortcutMapping> newMappingList = new LinkedList<InvTweaksShortcutMapping>();
            newMappingList.add(mapping);
            this.shortcuts.put(type, newMappingList);
        }
        for (int keyCode : mapping.getKeyCodes()) {
            this.pressedKeys.put(keyCode, false);
        }
    }

    public void handleShortcut() {
        try {
            ShortcutConfig shortcutToTrigger = this.computeShortcutToTrigger();
            if (shortcutToTrigger != null) {
                int ex = Mouse.getEventX();
                int ey = Mouse.getEventY();
                this.runShortcut(shortcutToTrigger);
                Mouse.destroy();
                Mouse.create();
                Mouse.setCursorPosition((int)ex, (int)ey);
            }
        }
        catch (Exception e) {
            InvTweaks.logInGameErrorStatic("invtweaks.shortcut.error", e);
        }
    }

    @Nullable
    public ShortcutSpecification computeCurrentShortcut() {
        ShortcutSpecification.Action action = ShortcutSpecification.Action.MOVE;
        ShortcutSpecification.Target target = ShortcutSpecification.Target.UNSPECIFIED;
        ShortcutSpecification.Scope scope = ShortcutSpecification.Scope.ONE_STACK;
        this.updatePressedKeys();
        boolean validAction = false;
        if (this.isShortcutDown(InvTweaksShortcutType.DROP) != null) {
            action = ShortcutSpecification.Action.DROP;
            validAction = true;
        }
        if (action != ShortcutSpecification.Action.DROP) {
            if (this.isShortcutDown(InvTweaksShortcutType.MOVE_TO_SPECIFIC_HOTBAR_SLOT) != null) {
                target = ShortcutSpecification.Target.HOTBAR_SLOT;
                validAction = true;
            } else if (this.isShortcutDown(InvTweaksShortcutType.MOVE_UP) != null) {
                target = ShortcutSpecification.Target.UP;
                validAction = true;
            } else if (this.isShortcutDown(InvTweaksShortcutType.MOVE_DOWN) != null) {
                target = ShortcutSpecification.Target.DOWN;
                validAction = true;
            }
        }
        if (this.isShortcutDown(InvTweaksShortcutType.MOVE_ALL_ITEMS) != null) {
            scope = ShortcutSpecification.Scope.ALL_ITEMS;
            validAction = true;
        } else if (this.isShortcutDown(InvTweaksShortcutType.MOVE_EVERYTHING) != null) {
            scope = ShortcutSpecification.Scope.EVERYTHING;
            validAction = true;
        } else if (this.isShortcutDown(InvTweaksShortcutType.MOVE_ONE_ITEM) != null) {
            scope = ShortcutSpecification.Scope.ONE_ITEM;
            validAction = true;
        }
        if (validAction) {
            return new ShortcutSpecification(action, target, scope);
        }
        return null;
    }

    private ShortcutConfig computeShortcutToTrigger() {
        ShortcutSpecification shortcut = this.computeCurrentShortcut();
        ShortcutConfig shortcutConfig = new ShortcutConfig();
        this.container = InvTweaks.getCurrentContainerManager();
        Slot slot = InvTweaksObfuscation.getSlotAtMousePosition((GuiContainer)this.getCurrentScreen());
        if (shortcut != null && slot != null && (slot.func_75216_d() || !this.getHeldStack().func_190926_b())) {
            int slotNumber = InvTweaksHandlerShortcuts.getSlotNumber(slot);
            shortcutConfig.fromSection = this.container.getSlotSection(slotNumber);
            shortcutConfig.fromIndex = this.container.getSlotIndex(slotNumber);
            ItemStack itemStack = shortcutConfig.fromStack = slot.func_75216_d() ? slot.func_75211_c().func_77946_l() : this.getHeldStack().func_77946_l();
            if (!slot.func_75214_a(slot.func_75211_c()) && shortcut.getScope() == ShortcutSpecification.Scope.ONE_ITEM) {
                shortcut.setScope(ShortcutSpecification.Scope.ONE_STACK);
            }
            if (shortcutConfig.fromSection != null && shortcutConfig.fromIndex != -1) {
                if (shortcut.getAction() != ShortcutSpecification.Action.DROP) {
                    if (shortcut.getTarget() == ShortcutSpecification.Target.HOTBAR_SLOT) {
                        shortcutConfig.toSection = ContainerSection.INVENTORY_HOTBAR;
                        InvTweaksShortcutMapping hotbarShortcut = this.isShortcutDown(InvTweaksShortcutType.MOVE_TO_SPECIFIC_HOTBAR_SLOT);
                        if (hotbarShortcut != null && !hotbarShortcut.getKeyCodes().isEmpty()) {
                            String keyName = Keyboard.getKeyName((int)hotbarShortcut.getKeyCodes().get(0));
                            shortcutConfig.toIndex = -1 + Integer.parseInt(keyName.replace("NUMPAD", ""));
                        }
                    } else {
                        ArrayList<ContainerSection> orderedSections = new ArrayList<ContainerSection>();
                        if (this.container.hasSection(ContainerSection.CHEST)) {
                            orderedSections.add(ContainerSection.CHEST);
                        } else if (this.container.hasSection(ContainerSection.CRAFTING_IN)) {
                            orderedSections.add(ContainerSection.CRAFTING_IN);
                        } else if (this.container.hasSection(ContainerSection.CRAFTING_IN_PERSISTENT)) {
                            orderedSections.add(ContainerSection.CRAFTING_IN_PERSISTENT);
                        } else if (this.container.hasSection(ContainerSection.FURNACE_IN)) {
                            orderedSections.add(ContainerSection.FURNACE_IN);
                        } else if (this.container.hasSection(ContainerSection.BREWING_INGREDIENT)) {
                            if (!shortcutConfig.fromStack.func_190926_b()) {
                                if (shortcutConfig.fromStack.func_77973_b() == Item.field_150901_e.func_82594_a((Object)new ResourceLocation("potion"))) {
                                    orderedSections.add(ContainerSection.BREWING_BOTTLES);
                                } else {
                                    orderedSections.add(ContainerSection.BREWING_INGREDIENT);
                                }
                            }
                        } else if (this.container.hasSection(ContainerSection.ENCHANTMENT)) {
                            orderedSections.add(ContainerSection.ENCHANTMENT);
                        }
                        orderedSections.add(ContainerSection.INVENTORY_NOT_HOTBAR);
                        orderedSections.add(ContainerSection.INVENTORY_HOTBAR);
                        if (shortcut.getTarget() != ShortcutSpecification.Target.UNSPECIFIED) {
                            int sectionOffset = 0;
                            if (shortcut.getTarget() == ShortcutSpecification.Target.UP) {
                                --sectionOffset;
                            } else if (shortcut.getTarget() == ShortcutSpecification.Target.DOWN) {
                                ++sectionOffset;
                            }
                            int fromSectionIndex = orderedSections.indexOf((Object)shortcutConfig.fromSection);
                            shortcutConfig.toSection = fromSectionIndex != -1 ? (ContainerSection)((Object)orderedSections.get((orderedSections.size() + fromSectionIndex + sectionOffset) % orderedSections.size())) : ContainerSection.INVENTORY;
                        } else {
                            switch (shortcutConfig.fromSection) {
                                case CHEST: {
                                    shortcutConfig.toSection = ContainerSection.INVENTORY;
                                    break;
                                }
                                case INVENTORY_HOTBAR: {
                                    if (orderedSections.contains((Object)ContainerSection.CHEST)) {
                                        shortcutConfig.toSection = ContainerSection.CHEST;
                                        break;
                                    }
                                    shortcutConfig.toSection = ContainerSection.INVENTORY_NOT_HOTBAR;
                                    break;
                                }
                                case CRAFTING_IN: 
                                case CRAFTING_IN_PERSISTENT: 
                                case FURNACE_IN: {
                                    shortcutConfig.toSection = ContainerSection.INVENTORY_NOT_HOTBAR;
                                    break;
                                }
                                default: {
                                    shortcutConfig.toSection = orderedSections.contains((Object)ContainerSection.CHEST) ? ContainerSection.CHEST : ContainerSection.INVENTORY_HOTBAR;
                                }
                            }
                        }
                    }
                }
                shortcutConfig.forceEmptySlot = Mouse.isButtonDown((int)1);
                shortcutConfig.action = shortcut.getAction();
                shortcutConfig.scope = shortcut.getScope();
                return shortcutConfig;
            }
        }
        return null;
    }

    public void updatePressedKeys() {
        if (this.haveControlsChanged()) {
            this.loadShortcuts();
        }
        for (int keyCode : this.pressedKeys.keySet()) {
            if (keyCode > 0 && Keyboard.isKeyDown((int)keyCode)) {
                if (this.pressedKeys.get(keyCode).booleanValue()) continue;
                this.pressedKeys.put(keyCode, true);
                continue;
            }
            this.pressedKeys.put(keyCode, false);
        }
    }

    private boolean haveControlsChanged() {
        return !this.pressedKeys.containsKey(this.getKeyBindingForwardKeyCode()) || !this.pressedKeys.containsKey(this.getKeyBindingBackKeyCode());
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    private void runShortcut(@NotNull ShortcutConfig shortcut) throws TimeoutException {
        if (!this.getHeldStack().func_190926_b()) {
            Slot slot = InvTweaksObfuscation.getSlotAtMousePosition((GuiContainer)this.getCurrentScreen());
            if (slot == null) return;
            int slotNumber = InvTweaksHandlerShortcuts.getSlotNumber(slot);
            this.container.putHoldItemDown(this.container.getSlotSection(slotNumber), this.container.getSlotIndex(slotNumber));
            if (!this.getHeldStack().func_190926_b()) {
                return;
            }
        }
        InvTweaksHandlerShortcuts invTweaksHandlerShortcuts = this;
        synchronized (invTweaksHandlerShortcuts) {
            block29: {
                block28: {
                    if (shortcut.toSection != ContainerSection.INVENTORY_HOTBAR || shortcut.toIndex == -1) break block28;
                    this.container.move(shortcut.fromSection, shortcut.fromIndex, shortcut.toSection, shortcut.toIndex);
                    break block29;
                }
                switch (shortcut.action) {
                    case DROP: {
                        switch (shortcut.scope) {
                            case ONE_ITEM: {
                                this.container.dropSome(shortcut.fromSection, shortcut.fromIndex, 1);
                                break;
                            }
                            case ONE_STACK: {
                                this.container.drop(shortcut.fromSection, shortcut.fromIndex);
                                break;
                            }
                            case ALL_ITEMS: {
                                this.dropAll(shortcut, shortcut.fromStack);
                                break;
                            }
                            case EVERYTHING: {
                                this.dropAll(shortcut, ItemStack.field_190927_a);
                            }
                        }
                    }
                    case MOVE: {
                        switch (shortcut.scope) {
                            case ONE_STACK: {
                                Slot slot = this.container.getSlot(shortcut.fromSection, shortcut.fromIndex);
                                if (!slot.func_75216_d()) return;
                                int toIndex = this.getNextTargetIndex(shortcut, slot.func_75211_c());
                                if (shortcut.fromSection != ContainerSection.CRAFTING_OUT && shortcut.toSection != ContainerSection.ENCHANTMENT) {
                                    while (slot.func_75216_d() && toIndex != -1) {
                                        boolean success = this.container.move(shortcut.fromSection, shortcut.fromIndex, shortcut.toSection, toIndex);
                                        int newIndex = this.getNextTargetIndex(shortcut, slot.func_75211_c());
                                        toIndex = success || shortcut.action == ShortcutSpecification.Action.DROP || newIndex != toIndex ? newIndex : -1;
                                    }
                                } else {
                                    this.container.move(shortcut.fromSection, shortcut.fromIndex, shortcut.toSection, toIndex);
                                }
                                break block29;
                            }
                            case ONE_ITEM: {
                                Slot slot = this.container.getSlot(shortcut.fromSection, shortcut.fromIndex);
                                if (!slot.func_75216_d()) return;
                                int toIndex = this.getNextTargetIndex(shortcut, slot.func_75211_c());
                                this.container.moveSome(shortcut.fromSection, shortcut.fromIndex, shortcut.toSection, toIndex, 1);
                                break block29;
                            }
                            case ALL_ITEMS: {
                                this.moveAll(shortcut, shortcut.fromStack);
                                if (shortcut.fromSection != ContainerSection.INVENTORY_NOT_HOTBAR || shortcut.toSection != ContainerSection.CHEST) return;
                                shortcut.fromSection = ContainerSection.INVENTORY_HOTBAR;
                                this.moveAll(shortcut, shortcut.fromStack);
                                break block29;
                            }
                            case EVERYTHING: {
                                this.moveAll(shortcut, ItemStack.field_190927_a);
                                if (shortcut.fromSection != ContainerSection.INVENTORY_HOTBAR || shortcut.toSection != ContainerSection.CHEST) return;
                                shortcut.fromSection = ContainerSection.INVENTORY_HOTBAR;
                                this.moveAll(shortcut, ItemStack.field_190927_a);
                            }
                        }
                    }
                }
            }
            return;
        }
    }

    private void dropAll(@NotNull ShortcutConfig shortcut, @NotNull ItemStack stackToMatch) {
        this.container.getSlots(shortcut.fromSection).stream().filter(slot -> slot.func_75216_d() && (stackToMatch.func_190926_b() || InvTweaksHandlerShortcuts.areSameItemType(stackToMatch, slot.func_75211_c()))).forEach(slot -> {
            int fromIndex = this.container.getSlotIndex(InvTweaksHandlerShortcuts.getSlotNumber(slot));
            while (slot.func_75216_d()) {
                this.container.drop(shortcut.fromSection, fromIndex);
            }
        });
    }

    private void moveAll(@NotNull ShortcutConfig shortcut, @NotNull ItemStack stackToMatch) {
        int toIndex = Integer.MIN_VALUE;
        for (Slot slot : this.container.getSlots(shortcut.fromSection)) {
            if (slot.func_75216_d() && (stackToMatch.func_190926_b() || InvTweaksHandlerShortcuts.areSameItemType(stackToMatch, slot.func_75211_c()))) {
                int fromIndex = this.container.getSlotIndex(InvTweaksHandlerShortcuts.getSlotNumber(slot));
                toIndex = this.getNextTargetIndex(shortcut, slot.func_75211_c());
                while (slot.func_75216_d() && toIndex != -1 && (shortcut.fromSection != shortcut.toSection || fromIndex != toIndex)) {
                    boolean success = this.container.move(shortcut.fromSection, fromIndex, shortcut.toSection, toIndex);
                    int newIndex = this.getNextTargetIndex(shortcut, slot.func_75211_c());
                    if (success && newIndex == toIndex && slot.func_75216_d()) {
                        throw new RuntimeException("Inventory in invalid sate after move");
                    }
                    if (success || newIndex != toIndex || shortcut.action == ShortcutSpecification.Action.DROP) {
                        toIndex = newIndex;
                        continue;
                    }
                    toIndex = -1;
                }
            }
            if (toIndex != -1) continue;
            break;
        }
    }

    private int getNextTargetIndex(@NotNull ShortcutConfig shortcut, @NotNull ItemStack current) {
        if (shortcut.action == ShortcutSpecification.Action.DROP) {
            return -999;
        }
        int result = -1;
        if (!shortcut.forceEmptySlot) {
            int i = 0;
            for (Slot slot : this.container.getSlots(shortcut.toSection)) {
                ItemStack stack;
                if (slot.func_75216_d() && InvTweaksObfuscation.areItemsStackable(current, stack = slot.func_75211_c()) && stack.func_190916_E() < stack.func_77976_d()) {
                    result = i;
                    break;
                }
                ++i;
            }
        }
        if (result == -1) {
            result = this.container.getFirstEmptyIndex(shortcut.toSection);
        }
        if (result == -1 && shortcut.toSection == ContainerSection.FURNACE_IN) {
            shortcut.toSection = ContainerSection.FURNACE_FUEL;
            result = this.container.getFirstEmptyIndex(shortcut.toSection);
        }
        return result;
    }

    @Nullable
    public InvTweaksShortcutMapping isShortcutDown(InvTweaksShortcutType type) {
        List<InvTweaksShortcutMapping> mappings = this.shortcuts.get((Object)type);
        if (mappings != null) {
            for (InvTweaksShortcutMapping mapping : mappings) {
                if (!mapping.isTriggered(this.pressedKeys)) continue;
                return mapping;
            }
        }
        return null;
    }

    private static class ShortcutConfig {
        @NotNull
        public ShortcutSpecification.Action action = ShortcutSpecification.Action.MOVE;
        @NotNull
        public ShortcutSpecification.Scope scope = ShortcutSpecification.Scope.ONE_STACK;
        @Nullable
        public ContainerSection fromSection = null;
        public int fromIndex = -1;
        @NotNull
        public ItemStack fromStack = ItemStack.field_190927_a;
        @Nullable
        public ContainerSection toSection = null;
        public int toIndex = -1;
        public boolean forceEmptySlot = false;

        private ShortcutConfig() {
        }
    }
}

