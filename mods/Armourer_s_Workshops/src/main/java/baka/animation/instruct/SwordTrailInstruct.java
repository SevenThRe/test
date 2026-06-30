/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.util.EnumHandSide
 */
package baka.animation.instruct;

import baka.animation.instruct.InstructBase;
import goblinbob.mobends.standard.data.PlayerData;
import net.minecraft.util.EnumHandSide;

public class SwordTrailInstruct
extends InstructBase {
    protected final boolean enable;
    protected final EnumHandSide handSide;
    protected final float length;
    protected float swordTrailRed;
    protected float swordTrailGreen;
    protected float swordTrailBlue;

    @Override
    public void perform(PlayerData data) {
        super.perform(data);
    }

    public boolean isLeft() {
        return this.handSide == EnumHandSide.LEFT;
    }

    public SwordTrailInstruct(boolean enable, EnumHandSide handSide, float length, float swordTrailRed, float swordTrailGreen, float swordTrailBlue) {
        this.enable = enable;
        this.handSide = handSide;
        this.length = length;
        this.swordTrailRed = swordTrailRed;
        this.swordTrailGreen = swordTrailGreen;
        this.swordTrailBlue = swordTrailBlue;
    }

    public boolean isEnable() {
        return this.enable;
    }

    public EnumHandSide getHandSide() {
        return this.handSide;
    }

    public float getLength() {
        return this.length;
    }

    public float getSwordTrailRed() {
        return this.swordTrailRed;
    }

    public float getSwordTrailGreen() {
        return this.swordTrailGreen;
    }

    public float getSwordTrailBlue() {
        return this.swordTrailBlue;
    }
}

