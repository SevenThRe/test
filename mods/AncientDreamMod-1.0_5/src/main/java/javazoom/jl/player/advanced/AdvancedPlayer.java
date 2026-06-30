/*
 * Decompiled with CFR 0.152.
 */
package javazoom.jl.player.advanced;

import java.io.InputStream;
import javazoom.jl.decoder.Bitstream;
import javazoom.jl.decoder.BitstreamException;
import javazoom.jl.decoder.Decoder;
import javazoom.jl.decoder.Header;
import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.decoder.SampleBuffer;
import javazoom.jl.player.AudioDevice;
import javazoom.jl.player.FactoryRegistry;
import javazoom.jl.player.advanced.PlaybackEvent;
import javazoom.jl.player.advanced.PlaybackListener;

public class AdvancedPlayer {
    private Bitstream bitstream;
    private Decoder decoder;
    private AudioDevice audio;
    private boolean closed = false;
    private boolean complete = false;
    private int lastPosition = 0;
    private PlaybackListener listener;

    public AdvancedPlayer(InputStream inputStream) throws JavaLayerException {
        this(inputStream, null);
    }

    public AdvancedPlayer(InputStream inputStream, AudioDevice audioDevice) throws JavaLayerException {
        this.bitstream = new Bitstream(inputStream);
        this.audio = audioDevice != null ? audioDevice : FactoryRegistry.systemRegistry().createAudioDevice();
        this.decoder = new Decoder();
        this.audio.open(this.decoder);
    }

    public void play() throws JavaLayerException {
        this.play(Integer.MAX_VALUE);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public boolean play(int n) throws JavaLayerException {
        boolean bl = true;
        if (this.listener != null) {
            this.listener.playbackStarted(this.createEvent(PlaybackEvent.STARTED));
        }
        while (n-- > 0 && bl) {
            bl = this.decodeFrame();
        }
        AudioDevice audioDevice = this.audio;
        if (audioDevice != null) {
            audioDevice.flush();
            AdvancedPlayer advancedPlayer = this;
            synchronized (advancedPlayer) {
                this.complete = !this.closed;
                this.close();
            }
            if (this.listener != null) {
                this.listener.playbackFinished(this.createEvent(audioDevice, PlaybackEvent.STOPPED));
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
            AdvancedPlayer advancedPlayer = this;
            synchronized (advancedPlayer) {
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

    protected boolean skipFrame() throws JavaLayerException {
        Header header = this.bitstream.readFrame();
        if (header == null) {
            return false;
        }
        this.bitstream.closeFrame();
        return true;
    }

    public boolean play(int n, int n2) throws JavaLayerException {
        boolean bl = true;
        int n3 = n;
        while (n3-- > 0 && bl) {
            bl = this.skipFrame();
        }
        return this.play(n2 - n);
    }

    private PlaybackEvent createEvent(int n) {
        return this.createEvent(this.audio, n);
    }

    private PlaybackEvent createEvent(AudioDevice audioDevice, int n) {
        return new PlaybackEvent(this, n, audioDevice.getPosition());
    }

    public void setPlayBackListener(PlaybackListener playbackListener) {
        this.listener = playbackListener;
    }

    public PlaybackListener getPlayBackListener() {
        return this.listener;
    }

    public void stop() {
        this.listener.playbackFinished(this.createEvent(PlaybackEvent.STOPPED));
        this.close();
    }
}

