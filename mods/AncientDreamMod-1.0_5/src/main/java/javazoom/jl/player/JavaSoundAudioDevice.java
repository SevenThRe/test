/*
 * Decompiled with CFR 0.152.
 */
package javazoom.jl.player;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.FloatControl;
import javax.sound.sampled.Line;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.SourceDataLine;
import javazoom.jl.decoder.Decoder;
import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.AudioDeviceBase;

public class JavaSoundAudioDevice
extends AudioDeviceBase {
    private SourceDataLine source = null;
    private AudioFormat fmt = null;
    private byte[] byteBuf = new byte[4096];
    private float defaultVolume = 6.0f;

    protected void setAudioFormat(AudioFormat fmt) {
        this.fmt = fmt;
    }

    protected AudioFormat getAudioFormat() {
        if (this.fmt == null) {
            Decoder decoder = this.getDecoder();
            this.fmt = new AudioFormat(decoder.getOutputFrequency(), 16, decoder.getOutputChannels(), true, false);
        }
        return this.fmt;
    }

    protected DataLine.Info getSourceLineInfo() {
        return new DataLine.Info(SourceDataLine.class, this.getAudioFormat());
    }

    public void open(AudioFormat audioFormat) throws JavaLayerException {
        if (!this.isOpen()) {
            this.setAudioFormat(audioFormat);
            this.openImpl();
            this.setOpen(true);
        }
    }

    public boolean setLineGain(float gain) {
        System.out.println("Vor Source" + gain);
        if (this.source != null) {
            FloatControl volControl = (FloatControl)this.source.getControl(FloatControl.Type.MASTER_GAIN);
            System.out.println("Nach Source" + volControl.getMinimum() + "  " + volControl.getMaximum());
            float newGain = Math.min(Math.max(gain, volControl.getMinimum()), volControl.getMaximum());
            volControl.setValue(newGain);
            return true;
        }
        return false;
    }

    @Override
    protected void openImpl() throws JavaLayerException {
    }

    public void createSource() throws JavaLayerException {
        Throwable t = null;
        try {
            Line line = AudioSystem.getLine(this.getSourceLineInfo());
            if (line instanceof SourceDataLine) {
                this.source = (SourceDataLine)line;
                this.source.open(this.fmt);
                this.source.start();
            }
        }
        catch (RuntimeException ex) {
            t = ex;
        }
        catch (LinkageError linkageError) {
            t = linkageError;
        }
        catch (LineUnavailableException ex2) {
            t = ex2;
        }
        if (this.source == null) {
            throw new JavaLayerException("cannot obtain source audio line", t);
        }
        this.setLineGain(this.defaultVolume);
    }

    public int millisecondsToBytes(AudioFormat audioFormat, int n) {
        return (int)((double)((float)n * (audioFormat.getSampleRate() * (float)audioFormat.getChannels() * (float)audioFormat.getSampleSizeInBits())) / 8000.0);
    }

    @Override
    protected void closeImpl() {
        if (this.source != null) {
            this.source.close();
        }
    }

    @Override
    protected void writeImpl(short[] array, int n, int n2) throws JavaLayerException {
        if (this.source == null) {
            this.createSource();
        }
        this.source.write(this.toByteArray(array, n, n2), 0, n2 * 2);
    }

    protected byte[] getByteArray(int n) {
        if (this.byteBuf.length < n) {
            this.byteBuf = new byte[n + 1024];
        }
        return this.byteBuf;
    }

    protected byte[] toByteArray(short[] array, int n, int n2) {
        byte[] byteArray = this.getByteArray(n2 * 2);
        int n3 = 0;
        while (n2-- > 0) {
            short n4 = array[n++];
            byteArray[n3++] = (byte)n4;
            byteArray[n3++] = (byte)(n4 >>> 8);
        }
        return byteArray;
    }

    @Override
    protected void flushImpl() {
        if (this.source != null) {
            this.source.drain();
        }
    }

    @Override
    public int getPosition() {
        int n = 0;
        if (this.source != null) {
            n = (int)(this.source.getMicrosecondPosition() / 1000L);
        }
        return n;
    }

    public void test() throws JavaLayerException {
        try {
            this.open(new AudioFormat(22050.0f, 16, 1, true, false));
            short[] array = new short[2205];
            this.write(array, 0, array.length);
            this.flush();
            this.close();
        }
        catch (RuntimeException obj) {
            throw new JavaLayerException("Device test failed: " + obj);
        }
    }

    public void setDefaultVolume(float defaultVolume) {
        this.defaultVolume = defaultVolume;
    }
}

