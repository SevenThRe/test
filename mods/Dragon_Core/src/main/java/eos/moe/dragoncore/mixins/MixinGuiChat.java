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

import eos.moe.dragoncore.ca;
import eos.moe.dragoncore.de;
import eos.moe.dragoncore.hja;
import eos.moe.dragoncore.nj;
import eos.moe.dragoncore.ui;
import java.io.IOException;
import net.minecraft.client.gui.GuiChat;
import net.minecraft.client.gui.GuiScreen;
import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(value={GuiChat.class})
public class MixinGuiChat
extends GuiScreen {
    public MixinGuiChat() {
        MixinGuiChat a2;
    }

    @Inject(method={"keyTyped"}, at={@At(value="HEAD")}, cancellable=true)
    private /* synthetic */ void mixin_keyType(char a2, int a3, CallbackInfo a4) {
        try {
            boolean a5 = false;
            for (ui a6 : de.c.values()) {
                a5 = a5 || a6.keyTyped_(a2, a3);
            }
            char a7 = Keyboard.getEventCharacter();
            if (Keyboard.getEventKey() == 0 && a7 >= ' ' || Keyboard.getEventKeyState()) {
                boolean bl2 = a5 = a5 || hja.c.ALLATORIxDEMO(Keyboard.getKeyName((int)Keyboard.getEventKey()));
            }
            if (a5) {
                a4.cancel();
            }
        }
        catch (IOException a8) {
            a8.printStackTrace();
        }
    }

    @Inject(method={"handleMouseInput"}, at={@At(value="HEAD")}, cancellable=true)
    public void handleMouseInput(CallbackInfo a2) throws IOException {
        int a3 = Mouse.getEventDWheel();
        if (a3 != 0 && hja.c.ALLATORIxDEMO()) {
            a2.cancel();
        }
        try {
            for (ui a4 : de.c.values()) {
                if (!a4.handleMouseInput_()) continue;
                a2.cancel();
            }
        }
        catch (IOException a5) {
            a5.printStackTrace();
        }
    }

    @Inject(method={"onGuiClosed"}, at={@At(value="HEAD")})
    private /* synthetic */ void mixin_onGuiClose(CallbackInfo a2) {
        MixinGuiChat a3;
        if (ca.x && ((Object)((Object)a3)).getClass().equals(GuiChat.class)) {
            return;
        }
        for (ui a4 : de.c.values()) {
            a4.runGuiAction(nj.e);
        }
    }
}

