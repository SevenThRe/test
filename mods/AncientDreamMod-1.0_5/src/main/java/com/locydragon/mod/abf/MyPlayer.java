/*
 * Decompiled with CFR 0.152.
 */
package com.locydragon.mod.abf;

import java.io.InputStream;
import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.JavaSoundAudioDevice;
import javazoom.jl.player.Player;

public class MyPlayer
extends Player {
    JavaSoundAudioDevice audioDevice;

    public MyPlayer(InputStream inputStream, JavaSoundAudioDevice audioDevice) throws JavaLayerException {
        super(inputStream, audioDevice);
        this.audioDevice = audioDevice;
    }

    public void setGain(float value) {
        this.audioDevice.setLineGain(-44.0f + value * 50.0f);
    }
}

