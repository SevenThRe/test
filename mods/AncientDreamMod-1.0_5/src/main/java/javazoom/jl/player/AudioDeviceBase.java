/*
 * Decompiled with CFR 0.152.
 */
package javazoom.jl.player;

import javazoom.jl.decoder.Decoder;
import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.AudioDevice;

public abstract class AudioDeviceBase
implements AudioDevice {
    private boolean open = false;
    private Decoder decoder = null;

    public synchronized void open(Decoder decoder) throws JavaLayerException {
        if (!this.isOpen()) {
            this.decoder = decoder;
            this.openImpl();
            this.setOpen(true);
        }
    }

    protected void openImpl() throws JavaLayerException {
    }

    protected void setOpen(boolean bl) {
        this.open = bl;
    }

    public synchronized boolean isOpen() {
        return this.open;
    }

    public synchronized void close() {
        if (this.isOpen()) {
            this.closeImpl();
            this.setOpen(false);
            this.decoder = null;
        }
    }

    protected void closeImpl() {
    }

    public void write(short[] sArray, int n, int n2) throws JavaLayerException {
        if (this.isOpen()) {
            this.writeImpl(sArray, n, n2);
        }
    }

    protected void writeImpl(short[] sArray, int n, int n2) throws JavaLayerException {
    }

    public void flush() {
        if (this.isOpen()) {
            this.flushImpl();
        }
    }

    protected void flushImpl() {
    }

    protected Decoder getDecoder() {
        return this.decoder;
    }
}

