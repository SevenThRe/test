/*
 * Decompiled with CFR 0.152.
 */
package javazoom.jl.decoder;

import javazoom.jl.decoder.Obuffer;

public class SampleBuffer
extends Obuffer {
    private short[] buffer = new short[2304];
    private int[] bufferp = new int[2];
    private int channels;
    private int frequency;

    public SampleBuffer(int n, int n2) {
        this.channels = n2;
        this.frequency = n;
        for (int i = 0; i < n2; ++i) {
            this.bufferp[i] = (short)i;
        }
    }

    public int getChannelCount() {
        return this.channels;
    }

    public int getSampleFrequency() {
        return this.frequency;
    }

    public short[] getBuffer() {
        return this.buffer;
    }

    public int getBufferLength() {
        return this.bufferp[0];
    }

    public void append(int n, short s) {
        this.buffer[this.bufferp[n]] = s;
        int n2 = n;
        this.bufferp[n2] = this.bufferp[n2] + this.channels;
    }

    public void appendSamples(int n, float[] fArray) {
        int n2 = this.bufferp[n];
        int n3 = 0;
        while (n3 < 32) {
            short s;
            float f;
            f = (f = fArray[n3++]) > 32767.0f ? 32767.0f : (f < -32767.0f ? -32767.0f : f);
            this.buffer[n2] = s = (short)f;
            n2 += this.channels;
        }
        this.bufferp[n] = n2;
    }

    public void write_buffer(int n) {
    }

    public void close() {
    }

    public void clear_buffer() {
        for (int i = 0; i < this.channels; ++i) {
            this.bufferp[i] = (short)i;
        }
    }

    public void set_stop_flag() {
    }
}

