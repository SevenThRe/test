/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.settings.GameSettings
 *  net.minecraft.client.settings.KeyBinding
 *  net.minecraft.util.MovementInput
 *  net.minecraftforge.client.settings.KeyModifier
 *  net.minecraftforge.fml.client.registry.ClientRegistry
 *  org.lwjgl.input.Keyboard
 */
package eos.moe.dragoncore;

import eos.moe.dragoncore.ca;
import eos.moe.dragoncore.i;
import java.util.Locale;
import java.util.Map;
import net.minecraft.client.Minecraft;
import net.minecraft.client.settings.GameSettings;
import net.minecraft.client.settings.KeyBinding;
import net.minecraft.util.MovementInput;
import net.minecraftforge.client.settings.KeyModifier;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import org.lwjgl.input.Keyboard;

/*
 * Duplicate member names - consider using --renamedupmembers true
 */
public class tg {
    public tg() {
        tg a2;
    }

    @i(f={"\u83b7\u53d6\u6309\u952e\u540d", "\u83b7\u53d6\u63a7\u5236\u6309\u952e\u540d", "ControlKey_Get_Name"})
    public static String c(String a2) {
        Map a3 = KeyBinding.KEYBIND_ARRAY;
        KeyBinding a4 = (KeyBinding)a3.get(a2);
        if (a4 != null) {
            return Keyboard.getKeyName((int)a4.getKeyCode());
        }
        return "";
    }

    @i(f={"\u83b7\u53d6\u6309\u952e\u989d\u5916", "\u83b7\u53d6\u63a7\u5236\u6309\u952e\u989d\u5916", "ControlKey_Get_Modifier"})
    public static String ALLATORIxDEMO(String a2) {
        Map a3 = KeyBinding.KEYBIND_ARRAY;
        KeyBinding a4 = (KeyBinding)a3.get(a2);
        if (a4 != null) {
            return a4.getKeyModifier().name().toUpperCase(Locale.ROOT);
        }
        return "NONE";
    }

    @i(f={"\u8bbe\u7f6e\u6309\u952e", "\u8bbe\u7f6e\u63a7\u5236\u6309\u952e", "ControlKey_Set_Key"})
    public static boolean ALLATORIxDEMO(String a2, String a3, String a4) {
        GameSettings a5 = Minecraft.getMinecraft().gameSettings;
        Map a6 = KeyBinding.KEYBIND_ARRAY;
        KeyBinding a7 = (KeyBinding)a6.get(a2);
        if (a7 != null) {
            int a8 = Keyboard.getKeyIndex((String)a4);
            if (a4.equals("MOUSE_LEFT")) {
                a8 = -100;
            }
            if (a4.equals("MOUSE_RIGHT")) {
                a8 = -99;
            }
            if (a4.equals("MOUSE_MIDDLE")) {
                a8 = -98;
            }
            if (a8 != 0 || a4.equals("NONE")) {
                try {
                    KeyModifier a9 = KeyModifier.valueOf((String)a3.toUpperCase(Locale.ROOT));
                    a7.setKeyModifierAndCode(a9, a8);
                    a5.setOptionKeyBinding(a7, a8);
                    KeyBinding.resetKeyBindingArrayAndHash();
                    return true;
                }
                catch (Exception a9) {
                    // empty catch block
                }
            }
        }
        return false;
    }

    @i(f={"\u63a7\u5236\u6309\u952e\u662f\u5426\u6309\u4e0b", "ControlKey_Is_Press"})
    public static boolean ALLATORIxDEMO(String a2) {
        Map a3 = KeyBinding.KEYBIND_ARRAY;
        return a3.containsKey(a2) && ((KeyBinding)a3.get(a2)).isKeyDown();
    }

    @i(f={"\u521b\u5efa\u63a7\u5236\u6309\u952e", "ControlKey_Create"}, c=true)
    public static void ALLATORIxDEMO(String a2, String a3, String a4) {
        Map a5 = KeyBinding.KEYBIND_ARRAY;
        if (a5.containsKey(a3)) {
            return;
        }
        int a6 = Keyboard.getKeyIndex((String)a4);
        if (a6 != 0) {
            KeyBinding a7 = new KeyBinding(a3, a6, a2);
            String a8 = ca.r.getString(a2 + "." + a3);
            if (a8 != null) {
                if (a8.indexOf(58) != -1) {
                    String[] a9 = a8.split(":");
                    a7.setKeyModifierAndCode(KeyModifier.valueFromString((String)a9[1]), Integer.parseInt(a9[0]));
                } else {
                    a7.setKeyModifierAndCode(KeyModifier.NONE, Integer.parseInt(a8));
                }
            }
            ca.ALLATORIxDEMO.add(a7);
            ClientRegistry.registerKeyBinding((KeyBinding)a7);
        }
    }

    @i(f={"\u6a21\u62df\u63a7\u5236\u6309\u952e", "ControlKey_Test"})
    public static boolean ALLATORIxDEMO(String a2, boolean a3) {
        Map a4 = KeyBinding.KEYBIND_ARRAY;
        KeyBinding a5 = (KeyBinding)a4.get(a2);
        if (a5 != null) {
            KeyBinding.setKeyBindState((int)a5.getKeyCode(), (boolean)a3);
            if (a3) {
                KeyBinding.onTick((int)a5.getKeyCode());
            }
            return true;
        }
        return false;
    }

    public static void ALLATORIxDEMO(MovementInput a2) {
        GameSettings a3 = Minecraft.getMinecraft().gameSettings;
        a2.moveStrafe = 0.0f;
        a2.moveForward = 0.0f;
        if (Keyboard.isKeyDown((int)a3.keyBindForward.getKeyCode())) {
            a2.moveForward += 1.0f;
            a2.forwardKeyDown = true;
        } else {
            a2.forwardKeyDown = false;
        }
        if (Keyboard.isKeyDown((int)a3.keyBindBack.getKeyCode())) {
            a2.moveForward -= 1.0f;
            a2.backKeyDown = true;
        } else {
            a2.backKeyDown = false;
        }
        if (Keyboard.isKeyDown((int)a3.keyBindLeft.getKeyCode())) {
            a2.moveStrafe += 1.0f;
            a2.leftKeyDown = true;
        } else {
            a2.leftKeyDown = false;
        }
        if (Keyboard.isKeyDown((int)a3.keyBindRight.getKeyCode())) {
            a2.moveStrafe -= 1.0f;
            a2.rightKeyDown = true;
        } else {
            a2.rightKeyDown = false;
        }
        a2.jump = Keyboard.isKeyDown((int)a3.keyBindJump.getKeyCode());
        a2.sneak = Keyboard.isKeyDown((int)a3.keyBindSneak.getKeyCode());
        if (a2.sneak) {
            a2.moveStrafe = (float)((double)a2.moveStrafe * 0.3);
            a2.moveForward = (float)((double)a2.moveForward * 0.3);
        }
    }
}

