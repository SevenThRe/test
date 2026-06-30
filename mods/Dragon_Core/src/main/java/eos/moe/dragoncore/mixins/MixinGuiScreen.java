/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.gui.GuiChat
 *  net.minecraft.client.gui.GuiScreen
 *  org.lwjgl.input.Keyboard
 *  org.lwjgl.input.Mouse
 */
package eos.moe.dragoncore.mixins;

import eos.moe.dragoncore.de;
import eos.moe.dragoncore.hja;
import eos.moe.dragoncore.nj;
import eos.moe.dragoncore.ui;
import net.minecraft.client.gui.GuiChat;
import net.minecraft.client.gui.GuiScreen;
import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(value={GuiScreen.class})
public class MixinGuiScreen {
    public MixinGuiScreen() {
        MixinGuiScreen a2;
    }

    @Inject(method={"handleMouseInput"}, at={@At(value="HEAD")})
    private /* synthetic */ void mixin_handleMouseInput(CallbackInfo a2) {
        MixinGuiScreen a3;
        if (a3 instanceof GuiChat) {
            return;
        }
        if (a3 instanceof ui) {
            return;
        }
        int a4 = Mouse.getEventDWheel();
        if (a4 != 0) {
            hja.c.ALLATORIxDEMO();
        }
    }

    @Inject(method={"handleKeyboardInput"}, at={@At(value="HEAD")})
    private /* synthetic */ void mixin_keyType(CallbackInfo a2) {
        MixinGuiScreen a3;
        if (a3 instanceof ui) {
            return;
        }
        char a4 = Keyboard.getEventCharacter();
        if (a3 instanceof GuiChat) {
            if (!(Keyboard.getEventKey() == 0 && a4 >= ' ' || Keyboard.getEventKeyState())) {
                String a5 = Keyboard.getKeyName((int)Keyboard.getEventKey());
                for (ui a6 : de.c.values()) {
                    a6.ba = a5;
                    a6.runGuiAction(nj.p);
                    a6.ba = "";
                }
            }
        } else if (Keyboard.getEventKey() == 0 && a4 >= ' ' || Keyboard.getEventKeyState()) {
            hja.c.ALLATORIxDEMO(Keyboard.getKeyName((int)Keyboard.getEventKey()));
        } else {
            hja.c.ALLATORIxDEMO(Keyboard.getKeyName((int)Keyboard.getEventKey()), nj.p);
        }
    }
}

