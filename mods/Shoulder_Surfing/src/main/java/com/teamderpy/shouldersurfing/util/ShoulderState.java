/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.Minecraft
 *  net.minecraftforge.fml.relauncher.Side
 *  net.minecraftforge.fml.relauncher.SideOnly
 */
package com.teamderpy.shouldersurfing.util;

import com.teamderpy.shouldersurfing.compatibility.EnumShaderCompatibility;
import com.teamderpy.shouldersurfing.math.Vec2f;
import net.minecraft.client.Minecraft;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(value=Side.CLIENT)
public class ShoulderState {
    private static boolean enabled;
    private static boolean switchPerspective;
    private static boolean isAiming;
    private static Vec2f lastTranslation;
    private static Vec2f translation;
    private static Vec2f projected;
    private static double cameraDistance;
    private static EnumShaderCompatibility shaders;

    public static boolean doSwitchPerspective() {
        return switchPerspective;
    }

    public static void setSwitchPerspective(boolean switchPerspective) {
        ShoulderState.switchPerspective = switchPerspective;
    }

    public static Vec2f getLastTranslation() {
        return lastTranslation;
    }

    public static void setLastTranslation(Vec2f lastTranslation) {
        ShoulderState.lastTranslation = lastTranslation;
    }

    public static Vec2f getTranslation() {
        return translation;
    }

    public static void setTranslation(Vec2f translation) {
        ShoulderState.translation = translation;
    }

    public static Vec2f getProjected() {
        return projected;
    }

    public static void setProjected(Vec2f projected) {
        ShoulderState.projected = projected;
    }

    public static boolean isAiming() {
        return isAiming;
    }

    public static void setAiming(boolean isAiming) {
        ShoulderState.isAiming = isAiming;
    }

    public static double getCameraDistance() {
        return cameraDistance;
    }

    public static void setCameraDistance(double cameraDistance) {
        ShoulderState.cameraDistance = cameraDistance;
    }

    public static boolean isEnabled() {
        return enabled;
    }

    public static void setEnabled(boolean enabled) {
        ShoulderState.enabled = enabled;
    }

    public static boolean doShoulderSurfing() {
        return enabled && Minecraft.getMinecraft().gameSettings.thirdPersonView == 1;
    }

    public static EnumShaderCompatibility getShaderType() {
        return shaders;
    }

    public static void setShaderType(EnumShaderCompatibility type) {
        shaders = type;
    }

    static {
        lastTranslation = Vec2f.ZERO;
        translation = Vec2f.ZERO;
        projected = null;
        shaders = EnumShaderCompatibility.NONE;
    }
}

