/*
 * Decompiled with CFR 0.152.
 */
package org.bytedeco.javacv;

import java.io.ByteArrayOutputStream;
import org.bytedeco.javacv.Seekable;

public class SeekableByteArrayOutputStream
extends ByteArrayOutputStream
implements Seekable {
    long position;

    @Override
    public void seek(long position, int whence) {
        if (position < 0L || position > (long)this.count || whence != 0) {
            throw new IllegalArgumentException();
        }
        this.position = position;
    }

    @Override
    public synchronized void write(int b2) {
        if (this.position < (long)this.count) {
            this.buf[(int)this.position] = (byte)b2;
        } else {
            super.write(b2);
        }
        ++this.position;
    }

    @Override
    public synchronized void write(byte[] b2, int off, int len) {
        if (this.position < (long)this.count) {
            for (int i2 = 0; i2 < len; ++i2) {
                this.write(b2[off + i2]);
            }
        } else {
            super.write(b2, off, len);
            this.position = this.count;
        }
    }
}

