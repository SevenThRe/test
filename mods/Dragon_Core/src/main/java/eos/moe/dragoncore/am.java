/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.audio.ISound$AttenuationType
 *  net.minecraft.client.audio.PositionedSoundRecord
 *  net.minecraft.util.ResourceLocation
 *  net.minecraft.util.SoundCategory
 */
package eos.moe.dragoncore;

import eos.moe.dragoncore.hm;
import net.minecraft.client.audio.ISound;
import net.minecraft.client.audio.PositionedSoundRecord;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundCategory;

public class am
extends PositionedSoundRecord {
    public hm k;
    private String ALLATORIxDEMO;

    public am(hm a2, ResourceLocation a3, SoundCategory a4, float a5, float a6, boolean a7, int a8, ISound.AttenuationType a9, float a10, float a11, float a12) {
        super(a3, a4, a5, a6, a7, a8, a9, a10, a11, a12);
        am a13;
        a13.k = a2;
    }

    public am(hm a2, ResourceLocation a3, SoundCategory a4, float a5, float a6) {
        a7(a2, a3, a4, a5, a6, false, 0, ISound.AttenuationType.NONE, 0.0f, 0.0f, 0.0f);
        am a7;
    }

    public hm getParent() {
        am a2;
        return a2.k;
    }

    public void setVolume(float a2) {
        a.field_147662_b = a2;
    }

    public void setPitch(float a2) {
        a.field_147663_c = a2;
    }

    public void setPosition(float a2, float a3, float a4) {
        a.field_147660_d = a2;
        a.field_147661_e = a3;
        a.field_147658_f = a4;
    }

    public void setKey(String a2) {
        a.ALLATORIxDEMO = a2;
    }

    public String getKey() {
        am a2;
        return a2.ALLATORIxDEMO;
    }

    public void setAttenuation(ISound.AttenuationType a2) {
        a.field_147666_i = a2;
    }
}

