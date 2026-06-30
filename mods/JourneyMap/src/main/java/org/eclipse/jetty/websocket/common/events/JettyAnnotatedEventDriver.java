/*
 * Decompiled with CFR 0.152.
 */
package org.eclipse.jetty.websocket.common.events;

import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import java.nio.ByteBuffer;
import org.eclipse.jetty.websocket.api.BatchMode;
import org.eclipse.jetty.websocket.api.WebSocketPolicy;
import org.eclipse.jetty.websocket.api.annotations.WebSocket;
import org.eclipse.jetty.websocket.api.extensions.Frame;
import org.eclipse.jetty.websocket.common.CloseInfo;
import org.eclipse.jetty.websocket.common.events.AbstractEventDriver;
import org.eclipse.jetty.websocket.common.events.JettyAnnotatedMetadata;
import org.eclipse.jetty.websocket.common.message.MessageAppender;
import org.eclipse.jetty.websocket.common.message.MessageInputStream;
import org.eclipse.jetty.websocket.common.message.MessageReader;
import org.eclipse.jetty.websocket.common.message.SimpleBinaryMessage;
import org.eclipse.jetty.websocket.common.message.SimpleTextMessage;

public class JettyAnnotatedEventDriver
extends AbstractEventDriver {
    private final JettyAnnotatedMetadata events;
    private boolean hasCloseBeenCalled = false;
    private BatchMode batchMode;

    public JettyAnnotatedEventDriver(WebSocketPolicy policy, Object websocket, JettyAnnotatedMetadata events) {
        super(policy, websocket);
        this.events = events;
        WebSocket anno = websocket.getClass().getAnnotation(WebSocket.class);
        if (anno.maxTextMessageSize() > 0) {
            this.policy.setMaxTextMessageSize(anno.maxTextMessageSize());
        }
        if (anno.maxBinaryMessageSize() > 0) {
            this.policy.setMaxBinaryMessageSize(anno.maxBinaryMessageSize());
        }
        if (anno.inputBufferSize() > 0) {
            this.policy.setInputBufferSize(anno.inputBufferSize());
        }
        if (anno.maxIdleTime() > 0) {
            this.policy.setIdleTimeout(anno.maxIdleTime());
        }
        this.batchMode = anno.batchMode();
    }

    @Override
    public BatchMode getBatchMode() {
        return this.batchMode;
    }

    @Override
    public void onBinaryFrame(ByteBuffer buffer, boolean fin) throws IOException {
        if (this.events.onBinary == null) {
            return;
        }
        if (this.activeMessage == null) {
            if (this.events.onBinary.isStreaming()) {
                final MessageAppender msg = this.activeMessage = new MessageInputStream();
                this.dispatch(new Runnable(){

                    @Override
                    public void run() {
                        try {
                            ((JettyAnnotatedEventDriver)JettyAnnotatedEventDriver.this).events.onBinary.call(JettyAnnotatedEventDriver.this.websocket, JettyAnnotatedEventDriver.this.session, msg);
                        }
                        catch (Throwable t) {
                            JettyAnnotatedEventDriver.this.onError(t);
                        }
                    }
                });
            } else {
                this.activeMessage = new SimpleBinaryMessage(this);
            }
        }
        this.appendMessage(buffer, fin);
    }

    @Override
    public void onBinaryMessage(byte[] data) {
        if (this.events.onBinary != null) {
            this.events.onBinary.call(this.websocket, this.session, data, 0, data.length);
        }
    }

    @Override
    public void onClose(CloseInfo close) {
        if (this.hasCloseBeenCalled) {
            return;
        }
        this.hasCloseBeenCalled = true;
        if (this.events.onClose != null) {
            this.events.onClose.call(this.websocket, this.session, close.getStatusCode(), close.getReason());
        }
    }

    @Override
    public void onConnect() {
        if (this.events.onConnect != null) {
            this.events.onConnect.call(this.websocket, this.session);
        }
    }

    @Override
    public void onError(Throwable cause) {
        if (this.events.onError != null) {
            this.events.onError.call(this.websocket, this.session, cause);
        }
    }

    @Override
    public void onFrame(Frame frame) {
        if (this.events.onFrame != null) {
            this.events.onFrame.call(this.websocket, this.session, frame);
        }
    }

    @Override
    public void onInputStream(InputStream stream) {
        if (this.events.onBinary != null) {
            this.events.onBinary.call(this.websocket, this.session, stream);
        }
    }

    @Override
    public void onReader(Reader reader) {
        if (this.events.onText != null) {
            this.events.onText.call(this.websocket, this.session, reader);
        }
    }

    @Override
    public void onTextFrame(ByteBuffer buffer, boolean fin) throws IOException {
        if (this.events.onText == null) {
            return;
        }
        if (this.activeMessage == null) {
            if (this.events.onText.isStreaming()) {
                final MessageAppender msg = this.activeMessage = new MessageReader(new MessageInputStream());
                this.dispatch(new Runnable(){

                    @Override
                    public void run() {
                        try {
                            ((JettyAnnotatedEventDriver)JettyAnnotatedEventDriver.this).events.onText.call(JettyAnnotatedEventDriver.this.websocket, JettyAnnotatedEventDriver.this.session, msg);
                        }
                        catch (Throwable t) {
                            JettyAnnotatedEventDriver.this.onError(t);
                        }
                    }
                });
            } else {
                this.activeMessage = new SimpleTextMessage(this);
            }
        }
        this.appendMessage(buffer, fin);
    }

    @Override
    public void onTextMessage(String message) {
        if (this.events.onText != null) {
            this.events.onText.call(this.websocket, this.session, message);
        }
    }

    public String toString() {
        return String.format("%s[%s]", this.getClass().getSimpleName(), this.websocket);
    }
}

