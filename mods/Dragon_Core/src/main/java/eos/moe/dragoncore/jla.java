/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.settings.KeyBinding
 *  net.minecraftforge.client.event.GuiOpenEvent
 *  net.minecraftforge.client.event.MouseEvent
 *  net.minecraftforge.fml.common.eventhandler.SubscribeEvent
 *  net.minecraftforge.fml.common.gameevent.InputEvent$KeyInputEvent
 *  org.lwjgl.input.Keyboard
 */
package eos.moe.dragoncore;

import eos.moe.dragoncore.ca;
import eos.moe.dragoncore.nw;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;
import net.minecraft.client.settings.KeyBinding;
import net.minecraftforge.client.event.GuiOpenEvent;
import net.minecraftforge.client.event.MouseEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.InputEvent;
import org.lwjgl.input.Keyboard;

public class jla {
    public static jla y;
    public Set<String> k;
    private final Set<KeyBinding> ALLATORIxDEMO = new HashSet<KeyBinding>();

    public jla() {
        jla a2;
        y = a2;
        a2.k = new HashSet<String>();
    }

    @SubscribeEvent
    public void ALLATORIxDEMO(MouseEvent a3) {
        String a4 = null;
        switch (a3.getButton()) {
            case 0: {
                a4 = "MOUSE_LEFT";
                break;
            }
            case 1: {
                a4 = "MOUSE_RIGHT";
                break;
            }
            case 2: {
                a4 = "MOUSE_MIDDLE";
            }
        }
        if (a4 != null) {
            if (a3.isButtonstate()) {
                jla a5;
                Set<String> a6 = a5.k.stream().map(Keyboard::getKeyIndex).filter(a2 -> a2 != 0).filter(Keyboard::isKeyDown).map(Keyboard::getKeyName).collect(Collectors.toSet());
                nw.ALLATORIxDEMO(a4, a6);
            } else {
                nw.ALLATORIxDEMO(a4);
            }
        }
    }

    @SubscribeEvent
    public void ALLATORIxDEMO(GuiOpenEvent a2) {
        if (a2.getGui() != null) {
            nw.ALLATORIxDEMO("ALL");
        }
    }

    @SubscribeEvent
    public void ALLATORIxDEMO(InputEvent.KeyInputEvent a3) {
        String a4;
        jla a5;
        for (Object a6 : ca.ALLATORIxDEMO) {
            if (a6.func_151470_d()) {
                if (a5.ALLATORIxDEMO.contains(a6)) continue;
                a5.ALLATORIxDEMO.add((KeyBinding)a6);
                nw.ALLATORIxDEMO(a6.func_151464_g(), Collections.emptySet());
                continue;
            }
            if (!a5.ALLATORIxDEMO.contains(a6)) continue;
            a5.ALLATORIxDEMO.remove(a6);
            nw.ALLATORIxDEMO(a6.func_151464_g());
        }
        if (Keyboard.getEventKeyState()) {
            Object a6;
            a4 = Keyboard.getKeyName((int)Keyboard.getEventKey());
            if (!a5.k.contains(a4)) {
                return;
            }
            a6 = a5.k.stream().map(Keyboard::getKeyIndex).filter(a2 -> a2 != 0).filter(Keyboard::isKeyDown).map(Keyboard::getKeyName).collect(Collectors.toSet());
            nw.ALLATORIxDEMO(a4, (Set<String>)a6);
        } else {
            a4 = Keyboard.getKeyName((int)Keyboard.getEventKey());
            if (!a5.k.contains(a4)) {
                return;
            }
            nw.ALLATORIxDEMO(a4);
        }
    }
}

