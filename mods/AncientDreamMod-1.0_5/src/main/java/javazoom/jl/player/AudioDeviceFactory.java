/*
 * Decompiled with CFR 0.152.
 */
package javazoom.jl.player;

import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.AudioDevice;

public abstract class AudioDeviceFactory {
    public abstract AudioDevice createAudioDevice() throws JavaLayerException;

    protected AudioDevice instantiate(ClassLoader classLoader, String string) throws ClassNotFoundException, IllegalAccessException, InstantiationException {
        AudioDevice audioDevice = null;
        Class<?> clazz = null;
        clazz = classLoader == null ? Class.forName(string) : classLoader.loadClass(string);
        Object obj = clazz.newInstance();
        audioDevice = (AudioDevice)obj;
        return audioDevice;
    }
}

