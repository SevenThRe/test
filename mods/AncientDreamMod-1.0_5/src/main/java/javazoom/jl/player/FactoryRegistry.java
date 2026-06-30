/*
 * Decompiled with CFR 0.152.
 */
package javazoom.jl.player;

import java.util.Enumeration;
import java.util.Hashtable;
import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.AudioDevice;
import javazoom.jl.player.AudioDeviceFactory;
import javazoom.jl.player.JavaSoundAudioDeviceFactory;

public class FactoryRegistry
extends AudioDeviceFactory {
    private static FactoryRegistry instance = null;
    protected Hashtable factories = new Hashtable();

    public static synchronized FactoryRegistry systemRegistry() {
        if (instance == null) {
            instance = new FactoryRegistry();
            instance.registerDefaultFactories();
        }
        return instance;
    }

    public void addFactory(AudioDeviceFactory audioDeviceFactory) {
        this.factories.put(audioDeviceFactory.getClass(), audioDeviceFactory);
    }

    public void removeFactoryType(Class clazz) {
        this.factories.remove(clazz);
    }

    public void removeFactory(AudioDeviceFactory audioDeviceFactory) {
        this.factories.remove(audioDeviceFactory.getClass());
    }

    public AudioDevice createAudioDevice() throws JavaLayerException {
        AudioDevice audioDevice = null;
        AudioDeviceFactory[] audioDeviceFactoryArray = this.getFactoriesPriority();
        if (audioDeviceFactoryArray == null) {
            throw new JavaLayerException(this + ": no factories registered");
        }
        JavaLayerException javaLayerException = null;
        for (int i = 0; audioDevice == null && i < audioDeviceFactoryArray.length; ++i) {
            try {
                audioDevice = audioDeviceFactoryArray[i].createAudioDevice();
                continue;
            }
            catch (JavaLayerException javaLayerException2) {
                javaLayerException = javaLayerException2;
            }
        }
        if (audioDevice == null && javaLayerException != null) {
            throw new JavaLayerException("Cannot create AudioDevice", javaLayerException);
        }
        return audioDevice;
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    protected AudioDeviceFactory[] getFactoriesPriority() {
        AudioDeviceFactory[] audioDeviceFactoryArray = null;
        Hashtable hashtable = this.factories;
        synchronized (hashtable) {
            int n = this.factories.size();
            if (n != 0) {
                audioDeviceFactoryArray = new AudioDeviceFactory[n];
                int n2 = 0;
                Enumeration enumeration = this.factories.elements();
                while (enumeration.hasMoreElements()) {
                    AudioDeviceFactory audioDeviceFactory = (AudioDeviceFactory)enumeration.nextElement();
                    audioDeviceFactoryArray[n2++] = audioDeviceFactory;
                }
            }
        }
        return audioDeviceFactoryArray;
    }

    protected void registerDefaultFactories() {
        this.addFactory(new JavaSoundAudioDeviceFactory());
    }
}

