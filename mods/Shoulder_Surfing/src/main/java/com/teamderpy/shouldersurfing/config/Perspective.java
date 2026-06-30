/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.Minecraft
 *  net.minecraftforge.fml.relauncher.Side
 *  net.minecraftforge.fml.relauncher.SideOnly
 */
package com.teamderpy.shouldersurfing.config;

import com.teamderpy.shouldersurfing.config.Config;
import com.teamderpy.shouldersurfing.config.CrosshairVisibility;
import com.teamderpy.shouldersurfing.util.ShoulderState;
import net.minecraft.client.Minecraft;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(value=Side.CLIENT)
public enum Perspective {
    FIRST_PERSON(0, CrosshairVisibility.ALWAYS),
    THIRD_PERSON_FRONT(2, CrosshairVisibility.NEVER),
    SHOULDER_SURFING(1, CrosshairVisibility.ALWAYS);

    private final int pointOfView;
    private final CrosshairVisibility defaultCrosshairVisibility;

    private Perspective(int pointOfView, CrosshairVisibility defaultCrosshairVisibility) {
        this.pointOfView = pointOfView;
        this.defaultCrosshairVisibility = defaultCrosshairVisibility;
    }

    public int getPointOfView() {
        return this.pointOfView;
    }

    public CrosshairVisibility getDefaultCrosshairVisibility() {
        return this.defaultCrosshairVisibility;
    }

    public Perspective next() {
        Perspective next = Perspective.values()[(this.ordinal() + 1) % Perspective.values().length];
        if (Config.CLIENT.replaceDefaultPerspective()) {
            if (this == FIRST_PERSON) {
                return SHOULDER_SURFING;
            }
            if (this == SHOULDER_SURFING) {
                return THIRD_PERSON_FRONT;
            }
            if (this == THIRD_PERSON_FRONT) {
                return FIRST_PERSON;
            }
        }
        return next;
    }

    public static Perspective of(int pointOfView, boolean shoulderSurfing) {
        switch (pointOfView) {
            case 0: {
                return FIRST_PERSON;
            }
            case 1: {
                return SHOULDER_SURFING;
            }
            case 2: {
                return THIRD_PERSON_FRONT;
            }
        }
        return FIRST_PERSON;
    }

    public static Perspective current() {
        return Perspective.of(Minecraft.func_71410_x().field_71474_y.field_74320_O, ShoulderState.doShoulderSurfing());
    }
}

