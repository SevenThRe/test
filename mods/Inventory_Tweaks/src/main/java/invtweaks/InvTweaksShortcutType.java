/*
 * Decompiled with CFR 0.152.
 */
package invtweaks;

public enum InvTweaksShortcutType {
    MOVE_ALL_ITEMS,
    MOVE_EVERYTHING,
    MOVE_ONE_STACK,
    MOVE_ONE_ITEM,
    MOVE_UP,
    MOVE_DOWN,
    MOVE_TO_SPECIFIC_HOTBAR_SLOT,
    DROP;


    public static InvTweaksShortcutType fromConfigKey(String property) {
        if ("shortcutKeyAllItems".equals(property)) {
            return MOVE_ALL_ITEMS;
        }
        if ("shortcutKeyEverything".equals(property)) {
            return MOVE_EVERYTHING;
        }
        if ("shortcutKeyToLowerSection".equals(property)) {
            return MOVE_DOWN;
        }
        if ("shortcutKeyDrop".equals(property)) {
            return DROP;
        }
        if ("shortcutKeyOneItem".equals(property)) {
            return MOVE_ONE_ITEM;
        }
        if ("shortcutKeyToUpperSection".equals(property)) {
            return MOVE_UP;
        }
        return null;
    }
}

