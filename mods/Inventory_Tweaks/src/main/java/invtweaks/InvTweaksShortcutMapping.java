/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.jetbrains.annotations.NotNull
 *  org.lwjgl.input.Keyboard
 */
package invtweaks;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.jetbrains.annotations.NotNull;
import org.lwjgl.input.Keyboard;

public class InvTweaksShortcutMapping {
    @NotNull
    private List<Integer> keysToHold = new ArrayList<Integer>();

    public InvTweaksShortcutMapping(int keyCode) {
        this.keysToHold.add(keyCode);
    }

    public InvTweaksShortcutMapping(String ... keyNames) {
        for (String keyName : keyNames) {
            keyName = keyName.trim().replace("KEY_", "").replace("ALT", "MENU");
            this.keysToHold.add(Keyboard.getKeyIndex((String)keyName));
        }
    }

    public boolean isTriggered(@NotNull Map<Integer, Boolean> pressedKeys) {
        for (Integer keyToHold : this.keysToHold) {
            if (!(keyToHold != 29 ? pressedKeys.get(keyToHold) == false : pressedKeys.get(keyToHold) == false || Keyboard.isKeyDown((int)184))) continue;
            return false;
        }
        return true;
    }

    @NotNull
    public List<Integer> getKeyCodes() {
        return this.keysToHold;
    }
}

