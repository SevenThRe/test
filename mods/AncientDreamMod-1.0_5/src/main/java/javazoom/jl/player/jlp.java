/*
 * Decompiled with CFR 0.152.
 */
package javazoom.jl.player;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.AudioDevice;
import javazoom.jl.player.FactoryRegistry;
import javazoom.jl.player.Player;

public class jlp {
    private String fFilename = null;
    private boolean remote = false;

    public static void main(String[] stringArray) {
        int n = 0;
        try {
            jlp jlp2 = jlp.createInstance(stringArray);
            if (jlp2 != null) {
                jlp2.play();
            }
        }
        catch (Exception exception) {
            System.err.println(exception);
            exception.printStackTrace(System.err);
            n = 1;
        }
        System.exit(n);
    }

    public static jlp createInstance(String[] stringArray) {
        jlp jlp2 = new jlp();
        if (!jlp2.parseArgs(stringArray)) {
            jlp2 = null;
        }
        return jlp2;
    }

    private jlp() {
    }

    public jlp(String string) {
        this.init(string);
    }

    protected void init(String string) {
        this.fFilename = string;
    }

    protected boolean parseArgs(String[] stringArray) {
        boolean bl = false;
        if (stringArray.length == 1) {
            this.init(stringArray[0]);
            bl = true;
            this.remote = false;
        } else if (stringArray.length == 2) {
            if (!stringArray[0].equals("-url")) {
                this.showUsage();
            } else {
                this.init(stringArray[1]);
                bl = true;
                this.remote = true;
            }
        } else {
            this.showUsage();
        }
        return bl;
    }

    public void showUsage() {
        System.out.println("Usage: jlp [-url] <filename>");
        System.out.println("");
        System.out.println(" e.g. : java javazoom.jl.player.jlp localfile.mp3");
        System.out.println("        java javazoom.jl.player.jlp -url http://www.server.com/remotefile.mp3");
        System.out.println("        java javazoom.jl.player.jlp -url http://www.shoutcastserver.com:8000");
    }

    public void play() throws JavaLayerException {
        try {
            System.out.println("playing " + this.fFilename + "...");
            InputStream inputStream = null;
            inputStream = this.remote ? this.getURLInputStream() : this.getInputStream();
            AudioDevice audioDevice = this.getAudioDevice();
            Player player = new Player(inputStream, audioDevice);
            player.play();
        }
        catch (IOException iOException) {
            throw new JavaLayerException("Problem playing file " + this.fFilename, iOException);
        }
        catch (Exception exception) {
            throw new JavaLayerException("Problem playing file " + this.fFilename, exception);
        }
    }

    protected InputStream getURLInputStream() throws Exception {
        URL uRL = new URL(this.fFilename);
        InputStream inputStream = uRL.openStream();
        BufferedInputStream bufferedInputStream = new BufferedInputStream(inputStream);
        return bufferedInputStream;
    }

    protected InputStream getInputStream() throws IOException {
        FileInputStream fileInputStream = new FileInputStream(this.fFilename);
        BufferedInputStream bufferedInputStream = new BufferedInputStream(fileInputStream);
        return bufferedInputStream;
    }

    protected AudioDevice getAudioDevice() throws JavaLayerException {
        return FactoryRegistry.systemRegistry().createAudioDevice();
    }
}

