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
        Map a3 = KeyBinding.field_74516_a;
        KeyBinding a4 = (KeyBinding)a3.get(a2);
        if (a4 != null) {
            return Keyboard.getKeyName((int)a4.func_151463_i());
        }
        return "";
    }

    @i(f={"\u83b7\u53d6\u6309\u952e\u989d\u5916", "\u83b7\u53d6\u63a7\u5236\u6309\u952e\u989d\u5916", "ControlKey_Get_Modifier"})
    public static String ALLATORIxDEMO(String a2) {
        Map a3 = KeyBinding.field_74516_a;
        KeyBinding a4 = (KeyBinding)a3.get(a2);
        if (a4 != null) {
            return a4.getKeyModifier().name().toUpperCase(Locale.ROOT);
        }
        return "NONE";
    }

    @i(f={"\u8bbe\u7f6e\u6309\u952e", "\u8bbe\u7f6e\u63a7\u5236\u6309\u952e", "ControlKey_Set_Key"})
    public static boolean ALLATORIxDEMO(String a2, String a3, String a4) {
        GameSettings a5 = Minecraft.func_71410_x().field_71474_y;
        Map a6 = KeyBinding.field_74516_a;
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
                    a5.func_151440_a(a7, a8);
                    KeyBinding.func_74508_b();
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
        Map a3 = KeyBinding.field_74516_a;
        return a3.containsKey(a2) && ((KeyBinding)a3.get(a2)).func_151470_d();
    }

    @i(f={"\u521b\u5efa\u63a7\u5236\u6309\u952e", "ControlKey_Create"}, c=true)
    public static void ALLATORIxDEMO(String a2, String a3, String a4) {
        Map a5 = KeyBinding.field_74516_a;
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
        Map a4 = KeyBinding.field_74516_a;
        KeyBinding a5 = (KeyBinding)a4.get(a2);
        if (a5 != null) {
            KeyBinding.func_74510_a((int)a5.func_151463_i(), (boolean)a3);
            if (a3) {
                KeyBinding.func_74507_a((int)a5.func_151463_i());
            }
            return true;
        }
        return false;
    }

    public static void ALLATORIxDEMO(MovementInput a2) {
        GameSettings a3 = Minecraft.func_71410_x().field_71474_y;
        a2.field_78902_a = 0.0f;
        a2.field_192832_b = 0.0f;
        if (Keyboard.isKeyDown((int)a3.field_74351_w.func_151463_i())) {
            a2.field_192832_b += 1.0f;
            a2.field_187255_c = true;
        } else {
            a2.field_187255_c = false;
        }
        if (Keyboard.isKeyDown((int)a3.field_74368_y.func_151463_i())) {
            a2.field_192832_b -= 1.0f;
            a2.field_187256_d = true;
        } else {
            a2.field_187256_d = false;
        }
        if (Keyboard.isKeyDown((int)a3.field_74370_x.func_151463_i())) {
            a2.field_78902_a += 1.0f;
            a2.field_187257_e = true;
        } else {
            a2.field_187257_e = false;
        }
        if (Keyboard.isKeyDown((int)a3.field_74366_z.func_151463_i())) {
            a2.field_78902_a -= 1.0f;
            a2.field_187258_f = true;
        } else {
            a2.field_187258_f = false;
        }
        a2.field_78901_c = Keyboard.isKeyDown((int)a3.field_74314_A.func_151463_i());
        a2.field_78899_d = Keyboard.isKeyDown((int)a3.field_74311_E.func_151463_i());
        if (a2.field_78899_d) {
            a2.field_78902_a = (float)((double)a2.field_78902_a * 0.3);
            a2.field_192832_b = (float)((double)a2.field_192832_b * 0.3);
        }
    }
}

