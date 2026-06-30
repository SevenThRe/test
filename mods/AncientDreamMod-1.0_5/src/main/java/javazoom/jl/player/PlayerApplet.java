/*
 * Decompiled with CFR 0.152.
 */
package javazoom.jl.player;

import java.applet.Applet;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintStream;
import java.net.URL;
import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.AudioDevice;
import javazoom.jl.player.FactoryRegistry;
import javazoom.jl.player.Player;

public class PlayerApplet
extends Applet
implements Runnable {
    public static final String AUDIO_PARAMETER = "audioURL";
    private Player player = null;
    private Thread playerThread = null;
    private String fileName = null;

    protected AudioDevice getAudioDevice() throws JavaLayerException {
        return FactoryRegistry.systemRegistry().createAudioDevice();
    }

    protected InputStream getAudioStream() {
        InputStream inputStream = null;
        try {
            URL uRL = this.getAudioURL();
            if (uRL != null) {
                inputStream = uRL.openStream();
            }
        }
        catch (IOException iOException) {
            System.err.println(iOException);
        }
        return inputStream;
    }

    protected String getAudioFileName() {
        String string = this.fileName;
        if (string == null) {
            string = this.getParameter(AUDIO_PARAMETER);
        }
        return string;
    }

    protected URL getAudioURL() {
        String string = this.getAudioFileName();
        URL uRL = null;
        if (string != null) {
            try {
                uRL = new URL(this.getDocumentBase(), string);
            }
            catch (Exception exception) {
                System.err.println(exception);
            }
        }
        return uRL;
    }

    public void setFileName(String string) {
        this.fileName = string;
    }

    public String getFileName() {
        return this.fileName;
    }

    protected void stopPlayer() throws JavaLayerException {
        if (this.player != null) {
            this.player.close();
            this.player = null;
            this.playerThread = null;
        }
    }

    protected void play(InputStream inputStream, AudioDevice audioDevice) throws JavaLayerException {
        this.stopPlayer();
        if (inputStream != null && audioDevice != null) {
            this.player = new Player(inputStream, audioDevice);
            this.playerThread = this.createPlayerThread();
            this.playerThread.start();
        }
    }

    protected Thread createPlayerThread() {
        return new Thread((Runnable)this, "Audio player thread");
    }

    public void init() {
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public void start() {
        String string = this.getAudioFileName();
        try {
            InputStream inputStream = this.getAudioStream();
            AudioDevice audioDevice = this.getAudioDevice();
            this.play(inputStream, audioDevice);
        }
        catch (JavaLayerException javaLayerException) {
            PrintStream printStream = System.err;
            synchronized (printStream) {
                System.err.println("Unable to play " + string);
                javaLayerException.printStackTrace(System.err);
            }
        }
    }

    public void stop() {
        try {
            this.stopPlayer();
        }
        catch (JavaLayerException javaLayerException) {
            System.err.println(javaLayerException);
        }
    }

    public void destroy() {
    }

    public void run() {
        if (this.player != null) {
            try {
                this.player.play();
            }
            catch (JavaLayerException javaLayerException) {
                System.err.println("Problem playing audio: " + javaLayerException);
            }
        }
    }
}

