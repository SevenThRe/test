/*
 * Decompiled with CFR 0.152.
 */
package org.eclipse.jetty.websocket.common;

import java.io.IOException;
import org.eclipse.jetty.util.Callback;
import org.eclipse.jetty.util.SharedBlockingCallback;
import org.eclipse.jetty.util.thread.Invocable;
import org.eclipse.jetty.websocket.api.WriteCallback;

public class BlockingWriteCallback
extends SharedBlockingCallback {
    public WriteBlocker acquireWriteBlocker() throws IOException {
        return new WriteBlocker(this.acquire());
    }

    public static class WriteBlocker
    implements WriteCallback,
    Callback,
    AutoCloseable {
        private final SharedBlockingCallback.Blocker blocker;

        protected WriteBlocker(SharedBlockingCallback.Blocker blocker) {
            this.blocker = blocker;
        }

        @Override
        public Invocable.InvocationType getInvocationType() {
            return Invocable.InvocationType.NON_BLOCKING;
        }

        @Override
        public void writeFailed(Throwable x) {
            this.blocker.failed(x);
        }

        @Override
        public void writeSuccess() {
            this.blocker.succeeded();
        }

        @Override
        public void succeeded() {
            this.blocker.succeeded();
        }

        @Override
        public void failed(Throwable x) {
            this.blocker.failed(x);
        }

        @Override
        public void close() {
            this.blocker.close();
        }

        public void block() throws IOException {
            this.blocker.block();
        }
    }
}

