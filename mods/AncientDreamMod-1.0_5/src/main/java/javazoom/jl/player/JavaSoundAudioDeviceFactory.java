/*
 * Decompiled with CFR 0.152.
 */
package javazoom.jl.player;

import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.AudioDevice;
import javazoom.jl.player.AudioDeviceFactory;
import javazoom.jl.player.JavaSoundAudioDevice;

public class JavaSoundAudioDeviceFactory
extends AudioDeviceFactory {
    private boolean tested = false;
    private static final String DEVICE_CLASS_NAME = "javazoom.jl.player.JavaSoundAudioDevice";

    public synchronized AudioDevice createAudioDevice() throws JavaLayerException {
        if (!this.tested) {
            this.testAudioDevice();
            this.tested = true;
        }
        try {
            return this.createAudioDeviceImpl();
        }
        catch (Exception exception) {
            throw new JavaLayerException("unable to create JavaSound device: " + exception);
        }
        catch (LinkageError linkageError) {
            throw new JavaLayerException("unable to create JavaSound device: " + linkageError);
        }
    }

    protected JavaSoundAudioDevice createAudioDeviceImpl() throws JavaLayerException {
        ClassLoader classLoader = this.getClass().getClassLoader();
        try {
            JavaSoundAudioDevice javaSoundAudioDevice = (JavaSoundAudioDevice)this.instantiate(classLoader, DEVICE_CLASS_NAME);
            return javaSoundAudioDevice;
        }
        catch (Exception exception) {
            throw new JavaLayerException("Cannot create JavaSound device", exception);
        }
        catch (LinkageError linkageError) {
            throw new JavaLayerException("Cannot create JavaSound device", linkageError);
        }
    }

    public void testAudioDevice() throws JavaLayerException {
        JavaSoundAudioDevice javaSoundAudioDevice = this.createAudioDeviceImpl();
        javaSoundAudioDevice.test();
    }
}

