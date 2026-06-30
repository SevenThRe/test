/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.settings.KeyBinding
 */
package com.rejahtavi.rfp2;

import net.minecraft.client.settings.KeyBinding;

public class RFP2Keybind {
    public KeyBinding keyBindingInstance;
    private boolean wasPressed = false;

    public RFP2Keybind(String description, int keyCode, String category) {
        this.keyBindingInstance = new KeyBinding(description, keyCode, category);
    }

    public boolean checkForNewPress() {
        boolean currentlyPressed = this.keyBindingInstance.isPressed();
        if (!this.wasPressed && currentlyPressed) {
            this.wasPressed = true;
            return true;
        }
        this.wasPressed = currentlyPressed;
        return false;
    }
}

