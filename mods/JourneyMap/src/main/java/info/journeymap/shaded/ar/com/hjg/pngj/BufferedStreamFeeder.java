/*
 * Decompiled with CFR 0.152.
 */
package info.journeymap.shaded.ar.com.hjg.pngj;

import info.journeymap.shaded.ar.com.hjg.pngj.IBytesConsumer;
import info.journeymap.shaded.ar.com.hjg.pngj.PngjInputException;
import java.io.IOException;
import java.io.InputStream;

public class BufferedStreamFeeder {
    private InputStream stream;
    private byte[] buf;
    private int pendinglen;
    private int offset;
    private boolean eof = false;
    private boolean closeStream = true;
    private boolean failIfNoFeed = false;
    private static final int DEFAULTSIZE = 8192;

    public BufferedStreamFeeder(InputStream is) {
        this(is, 8192);
    }

    public BufferedStreamFeeder(InputStream is, int bufsize) {
        this.stream = is;
        this.buf = new byte[bufsize < 1 ? 8192 : bufsize];
    }

    public InputStream getStream() {
        return this.stream;
    }

    public int feed(IBytesConsumer consumer) {
        return this.feed(consumer, -1);
    }

    public int feed(IBytesConsumer consumer, int maxbytes) {
        int tofeed;
        int n = 0;
        if (this.pendinglen == 0) {
            this.refillBuffer();
        }
        int n2 = tofeed = maxbytes > 0 && maxbytes < this.pendinglen ? maxbytes : this.pendinglen;
        if (tofeed > 0 && (n = consumer.consume(this.buf, this.offset, tofeed)) > 0) {
            this.offset += n;
            this.pendinglen -= n;
        }
        if (n < 1 && this.failIfNoFeed) {
            throw new PngjInputException("failed feed bytes");
        }
        return n;
    }

    public boolean feedFixed(IBytesConsumer consumer, int nbytes) {
        int n;
        for (int remain = nbytes; remain > 0; remain -= n) {
            n = this.feed(consumer, remain);
            if (n >= 1) continue;
            return false;
        }
        return true;
    }

    protected void refillBuffer() {
        if (this.pendinglen > 0 || this.eof) {
            return;
        }
        try {
            this.offset = 0;
            this.pendinglen = this.stream.read(this.buf);
            if (this.pendinglen < 0) {
                this.close();
                return;
            }
            return;
        }
        catch (IOException e) {
            throw new PngjInputException(e);
        }
    }

    public boolean hasMoreToFeed() {
        if (this.eof) {
            return this.pendinglen > 0;
        }
        this.refillBuffer();
        return this.pendinglen > 0;
    }

    public void setCloseStream(boolean closeStream) {
        this.closeStream = closeStream;
    }

    public void close() {
        this.eof = true;
        this.buf = null;
        this.pendinglen = 0;
        this.offset = 0;
        if (this.stream != null && this.closeStream) {
            try {
                this.stream.close();
            }
            catch (Exception exception) {
                // empty catch block
            }
        }
        this.stream = null;
    }

    public void setInputStream(InputStream is) {
        this.stream = is;
        this.eof = false;
    }

    public boolean isEof() {
        return this.eof;
    }

    public void setFailIfNoFeed(boolean failIfNoFeed) {
        this.failIfNoFeed = failIfNoFeed;
    }
}

