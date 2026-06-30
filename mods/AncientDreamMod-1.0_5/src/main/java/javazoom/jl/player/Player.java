/*
 * Decompiled with CFR 0.152.
 */
package javazoom.jl.player;

import java.io.InputStream;
import javazoom.jl.decoder.Bitstream;
import javazoom.jl.decoder.BitstreamException;
import javazoom.jl.decoder.Decoder;
import javazoom.jl.decoder.Header;
import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.decoder.SampleBuffer;
import javazoom.jl.player.AudioDevice;
import javazoom.jl.player.FactoryRegistry;

public class Player {
    private int frame = 0;
    private Bitstream bitstream;
    private Decoder decoder;
    private AudioDevice audio;
    private boolean closed = false;
    private boolean complete = false;
    private int lastPosition = 0;

    public Player(InputStream inputStream) throws JavaLayerException {
        this(inputStream, null);
    }

    public Player(InputStream inputStream, AudioDevice audioDevice) throws JavaLayerException {
        this.bitstream = new Bitstream(inputStream);
        this.decoder = new Decoder();
        if (audioDevice != null) {
            this.audio = audioDevice;
        } else {
            FactoryRegistry factoryRegistry = FactoryRegistry.systemRegistry();
            this.audio = factoryRegistry.createAudioDevice();
        }
        this.audio.open(this.decoder);
    }

    public void play() throws JavaLayerException {
        this.play(Integer.MAX_VALUE);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public boolean play(int n) throws JavaLayerException {
        AudioDevice audioDevice;
        boolean bl = true;
        while (n-- > 0 && bl) {
            bl = this.decodeFrame();
        }
        if (!bl && (audioDevice = this.audio) != null) {
            audioDevice.flush();
            Player player = this;
            synchronized (player) {
                this.complete = !this.closed;
                this.close();
            }
        }
        return bl;
    }

    public synchronized void close() {
        AudioDevice audioDevice = this.audio;
        if (audioDevice != null) {
            this.closed = true;
            this.audio = null;
            audioDevice.close();
            this.lastPosition = audioDevice.getPosition();
            try {
                this.bitstream.close();
            }
            catch (BitstreamException bitstreamException) {
                // empty catch block
            }
        }
    }

    public synchronized boolean isComplete() {
        return this.complete;
    }

    public int getPosition() {
        int n = this.lastPosition;
        AudioDevice audioDevice = this.audio;
        if (audioDevice != null) {
            n = audioDevice.getPosition();
        }
        return n;
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    protected boolean decodeFrame() throws JavaLayerException {
        try {
            AudioDevice audioDevice = this.audio;
            if (audioDevice == null) {
                return false;
            }
            Header header = this.bitstream.readFrame();
            if (header == null) {
                return false;
            }
            SampleBuffer sampleBuffer = (SampleBuffer)this.decoder.decodeFrame(header, this.bitstream);
            Player player = this;
            synchronized (player) {
                audioDevice = this.audio;
                if (audioDevice != null) {
                    audioDevice.write(sampleBuffer.getBuffer(), 0, sampleBuffer.getBufferLength());
                }
            }
            this.bitstream.closeFrame();
        }
        catch (RuntimeException runtimeException) {
            throw new JavaLayerException("Exception decoding audio frame", runtimeException);
        }
        return true;
    }
}

