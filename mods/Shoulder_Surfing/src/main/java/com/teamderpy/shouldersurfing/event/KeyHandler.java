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
        if (Minecraft.getMinecraft() != null && Minecraft.getMinecraft().currentScreen == null) {
            if (KEYBIND_TOGGLE_SHOULDER_SURFING.isKeyDown()) {
                if (ShoulderState.doShoulderSurfing()) {
                    ShoulderSurfingHelper.setPerspective(Perspective.FIRST_PERSON);
                } else if (Minecraft.getMinecraft().gameSettings.thirdPersonView == Perspective.FIRST_PERSON.getPointOfView()) {
                    ShoulderSurfingHelper.setPerspective(Perspective.SHOULDER_SURFING);
                }
            }
            if (ShoulderState.doShoulderSurfing()) {
                if (KEYBIND_CAMERA_LEFT.isKeyDown()) {
                    Config.CLIENT.adjustCameraLeft();
                }
                if (KEYBIND_CAMERA_RIGHT.isKeyDown()) {
                    Config.CLIENT.adjustCameraRight();
                }
                if (KEYBIND_CAMERA_OUT.isKeyDown()) {
                    Config.CLIENT.adjustCameraOut();
                }
                if (KEYBIND_CAMERA_IN.isKeyDown()) {
                    Config.CLIENT.adjustCameraIn();
                }
                if (KEYBIND_CAMERA_UP.isKeyDown()) {
                    Config.CLIENT.adjustCameraUp();
                }
                if (KEYBIND_CAMERA_DOWN.isKeyDown()) {
                    Config.CLIENT.adjustCameraDown();
                }
                if (KEYBIND_SWAP_SHOULDER.isKeyDown()) {
                    Config.CLIENT.swapShoulder();
                }
            }
            if (Minecraft.getMinecraft().gameSettings.keyBindTogglePerspective.isPressed()) {
                Perspective perspective = Perspective.current();
                Perspective next = perspective.next();
                ShoulderSurfingHelper.setPerspective(next);
                if (Minecraft.getMinecraft().gameSettings.thirdPersonView == 0) {
                    Minecraft.getMinecraft().entityRenderer.loadEntityShader(Minecraft.getMinecraft().getRenderViewEntity());
                } else if (Minecraft.getMinecraft().gameSettings.thirdPersonView == 1) {
                    Minecraft.getMinecraft().entityRenderer.loadEntityShader(null);
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

