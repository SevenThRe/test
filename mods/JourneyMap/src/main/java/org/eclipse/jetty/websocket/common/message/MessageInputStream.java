/*
 * Decompiled with CFR 0.152.
 */
package org.eclipse.jetty.websocket.common.message;

import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.util.concurrent.BlockingDeque;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
import org.eclipse.jetty.util.BufferUtil;
import org.eclipse.jetty.util.log.Log;
import org.eclipse.jetty.util.log.Logger;
import org.eclipse.jetty.websocket.common.message.MessageAppender;

public class MessageInputStream
extends InputStream
implements MessageAppender {
    private static final Logger LOG = Log.getLogger(MessageInputStream.class);
    private static final ByteBuffer EOF = ByteBuffer.allocate(0).asReadOnlyBuffer();
    private final BlockingDeque<ByteBuffer> buffers = new LinkedBlockingDeque<ByteBuffer>();
    private AtomicBoolean closed = new AtomicBoolean(false);
    private final long timeoutMs;
    private ByteBuffer activeBuffer = null;

    public MessageInputStream() {
        this(-1);
    }

    public MessageInputStream(int timeoutMs) {
        this.timeoutMs = timeoutMs;
    }

    @Override
    public void appendFrame(ByteBuffer framePayload, boolean fin) throws IOException {
        if (LOG.isDebugEnabled()) {
            LOG.debug("Appending {} chunk: {}", fin ? "final" : "non-final", BufferUtil.toDetailString(framePayload));
        }
        if (this.closed.get()) {
            return;
        }
        try {
            if (framePayload == null) {
                return;
            }
            int capacity = framePayload.remaining();
            if (capacity <= 0) {
                return;
            }
            ByteBuffer copy = framePayload.isDirect() ? ByteBuffer.allocateDirect(capacity) : ByteBuffer.allocate(capacity);
            copy.put(framePayload).flip();
            this.buffers.put(copy);
        }
        catch (InterruptedException e) {
            throw new IOException(e);
        }
        finally {
            if (fin) {
                this.buffers.offer(EOF);
            }
        }
    }

    @Override
    public void close() throws IOException {
        if (this.closed.compareAndSet(false, true)) {
            this.buffers.offer(EOF);
            super.close();
        }
    }

    @Override
    public void mark(int readlimit) {
    }

    @Override
    public boolean markSupported() {
        return false;
    }

    @Override
    public void messageComplete() {
        if (LOG.isDebugEnabled()) {
            LOG.debug("Message completed", new Object[0]);
        }
        this.buffers.offer(EOF);
    }

    @Override
    public int read() throws IOException {
        try {
            if (this.closed.get()) {
                if (LOG.isDebugEnabled()) {
                    LOG.debug("Stream closed", new Object[0]);
                }
                return -1;
            }
            while (this.activeBuffer == null || !this.activeBuffer.hasRemaining()) {
                if (LOG.isDebugEnabled()) {
                    LOG.debug("Waiting {} ms to read", this.timeoutMs);
                }
                if (this.timeoutMs < 0L) {
                    this.activeBuffer = this.buffers.take();
                } else {
                    this.activeBuffer = this.buffers.poll(this.timeoutMs, TimeUnit.MILLISECONDS);
                    if (this.activeBuffer == null) {
                        throw new IOException(String.format("Read timeout: %,dms expired", this.timeoutMs));
                    }
                }
                if (this.activeBuffer != EOF) continue;
                if (LOG.isDebugEnabled()) {
                    LOG.debug("Reached EOF", new Object[0]);
                }
                this.closed.set(true);
                this.buffers.clear();
                return -1;
            }
            return this.activeBuffer.get() & 0xFF;
        }
        catch (InterruptedException x) {
            if (LOG.isDebugEnabled()) {
                LOG.debug("Interrupted while waiting to read", x);
            }
            this.closed.set(true);
            return -1;
        }
    }

    @Override
    public void reset() throws IOException {
        throw new IOException("reset() not supported");
    }
}

