/*
 * Decompiled with CFR 0.152.
 */
package javazoom.jl.converter;

import javazoom.jl.converter.WaveFile;
import javazoom.jl.decoder.Obuffer;

public class WaveFileObuffer
extends Obuffer {
    private short[] buffer;
    private short[] bufferp;
    private int channels;
    private WaveFile outWave;
    short[] myBuffer = new short[2];

    public WaveFileObuffer(int n, int n2, String string) {
        int n3;
        if (string == null) {
            throw new NullPointerException("FileName");
        }
        this.buffer = new short[2304];
        this.bufferp = new short[2];
        this.channels = n;
        for (n3 = 0; n3 < n; ++n3) {
            this.bufferp[n3] = (short)n3;
        }
        this.outWave = new WaveFile();
        n3 = this.outWave.OpenForWrite(string, n2, (short)16, (short)this.channels);
    }

    public void append(int n, short s) {
        this.buffer[this.bufferp[n]] = s;
        int n2 = n;
        this.bufferp[n2] = (short)(this.bufferp[n2] + this.channels);
    }

    public void write_buffer(int n) {
        boolean bl = false;
        int n2 = 0;
        n2 = this.outWave.WriteData(this.buffer, this.bufferp[0]);
        for (int i = 0; i < this.channels; ++i) {
            this.bufferp[i] = (short)i;
        }
    }

    public void close() {
        this.outWave.Close();
    }

    public void clear_buffer() {
    }

    public void set_stop_flag() {
    }
}

