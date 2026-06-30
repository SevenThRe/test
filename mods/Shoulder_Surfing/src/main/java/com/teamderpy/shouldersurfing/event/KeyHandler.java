/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.settings.KeyBinding
 *  net.minecraftforge.client.event.GuiOpenEvent
 *  net.minecraftforge.fml.common.eventhandler.SubscribeEvent
 *  net.minecraftforge.fml.common.gameevent.InputEvent$KeyInputEvent
 *  net.minecraftforge.fml.relauncher.Side
 *  net.minecraftforge.fml.relauncher.SideOnly
 *  org.lwjgl.input.Keyboard
 */
package com.teamderpy.shouldersurfing.event;

import com.teamderpy.shouldersurfing.config.Config;
import com.teamderpy.shouldersurfing.config.Perspective;
import com.teamderpy.shouldersurfing.util.ShoulderState;
import com.teamderpy.shouldersurfing.util.ShoulderSurfingHelper;
import net.minecraft.client.Minecraft;
import net.minecraft.client.settings.KeyBinding;
import net.minecraftforge.client.event.GuiOpenEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.InputEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.lwjgl.input.Keyboard;

@SideOnly(value=Side.CLIENT)
public class KeyHandler {
    private static final String KEY_CATEGORY = "Shoulder Surfing";
    public static final KeyBinding KEYBIND_CAMERA_LEFT = new KeyBinding("Camera left", 203, "\u6444\u50cf\u673a\u89c6\u89d2\u8bbe\u7f6e");
    public static final KeyBinding KEYBIND_CAMERA_RIGHT = new KeyBinding("Camera right", 205, "\u6444\u50cf\u673a\u89c6\u89d2\u8bbe\u7f6e");
    public static final KeyBinding KEYBIND_CAMERA_IN = new KeyBinding("Camera closer", 200, "\u6444\u50cf\u673a\u89c6\u89d2\u8bbe\u7f6e");
    public static final KeyBinding KEYBIND_CAMERA_OUT = new KeyBinding("Camera farther", 208, "\u6444\u50cf\u673a\u89c6\u89d2\u8bbe\u7f6e");
    public static final KeyBinding KEYBIND_CAMERA_UP = new KeyBinding("Camera up", 201, "\u6444\u50cf\u673a\u89c6\u89d2\u8bbe\u7f6e");
    public static final KeyBinding KEYBIND_CAMERA_DOWN = new KeyBinding("Camera down", 209, "\u6444\u50cf\u673a\u89c6\u89d2\u8bbe\u7f6e");
    public static final KeyBinding KEYBIND_SWAP_SHOULDER = new KeyBinding("Swap shoulder", 24, "\u6444\u50cf\u673a\u89c6\u89d2\u8bbe\u7f6e");
    public static final KeyBinding KEYBIND_TOGGLE_SHOULDER_SURFING = new KeyBinding("Toggle perspective", 0, "\u6444\u50cf\u673a\u89c6\u89d2\u8bbe\u7f6e");

    @SubscribeEvent
    public void keyInputEvent(InputEvent.KeyInputEvent event) {
        if (Minecraft.func_71410_x() != null && Minecraft.func_71410_x().field_71462_r == null) {
            if (KEYBIND_TOGGLE_SHOULDER_SURFING.func_151470_d()) {
                if (ShoulderState.doShoulderSurfing()) {
                    ShoulderSurfingHelper.setPerspective(Perspective.FIRST_PERSON);
                } else if (Minecraft.func_71410_x().field_71474_y.field_74320_O == Perspective.FIRST_PERSON.getPointOfView()) {
                    ShoulderSurfingHelper.setPerspective(Perspective.SHOULDER_SURFING);
                }
            }
            if (ShoulderState.doShoulderSurfing()) {
                if (KEYBIND_CAMERA_LEFT.func_151470_d()) {
                    Config.CLIENT.adjustCameraLeft();
                }
                if (KEYBIND_CAMERA_RIGHT.func_151470_d()) {
                    Config.CLIENT.adjustCameraRight();
                }
                if (KEYBIND_CAMERA_OUT.func_151470_d()) {
                    Config.CLIENT.adjustCameraOut();
                }
                if (KEYBIND_CAMERA_IN.func_151470_d()) {
                    Config.CLIENT.adjustCameraIn();
                }
                if (KEYBIND_CAMERA_UP.func_151470_d()) {
                    Config.CLIENT.adjustCameraUp();
                }
                if (KEYBIND_CAMERA_DOWN.func_151470_d()) {
                    Config.CLIENT.adjustCameraDown();
                }
                if (KEYBIND_SWAP_SHOULDER.func_151470_d()) {
                    Config.CLIENT.swapShoulder();
                }
            }
            if (Minecraft.func_71410_x().field_71474_y.field_151457_aa.func_151468_f()) {
                Perspective perspective = Perspective.current();
                Perspective next = perspective.next();
                ShoulderSurfingHelper.setPerspective(next);
                if (Minecraft.func_71410_x().field_71474_y.field_74320_O == 0) {
                    Minecraft.func_71410_x().field_71460_t.func_175066_a(Minecraft.func_71410_x().func_175606_aa());
                } else if (Minecraft.func_71410_x().field_71474_y.field_74320_O == 1) {
                    Minecraft.func_71410_x().field_71460_t.func_175066_a(null);
                }
                if (Config.CLIENT.doRememberLastPerspective()) {
                    Config.CLIENT.setDefaultPerspective(next);
                }
            }
        }
    }

    @SubscribeEvent
    public void onGuiClosed(GuiOpenEvent event) {
        if (event.getGui() == null) {
            Keyboard.enableRepeatEvents((boolean)true);
        }
    }
}

