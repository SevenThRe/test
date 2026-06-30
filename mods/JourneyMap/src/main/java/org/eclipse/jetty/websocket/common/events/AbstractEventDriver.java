/*
 * Decompiled with CFR 0.152.
 */
package org.eclipse.jetty.websocket.common.events;

import java.io.IOException;
import java.nio.ByteBuffer;
import org.eclipse.jetty.util.BufferUtil;
import org.eclipse.jetty.util.Utf8Appendable;
import org.eclipse.jetty.util.component.AbstractLifeCycle;
import org.eclipse.jetty.util.log.Log;
import org.eclipse.jetty.util.log.Logger;
import org.eclipse.jetty.websocket.api.BatchMode;
import org.eclipse.jetty.websocket.api.CloseException;
import org.eclipse.jetty.websocket.api.WebSocketPolicy;
import org.eclipse.jetty.websocket.api.extensions.Frame;
import org.eclipse.jetty.websocket.api.extensions.IncomingFrames;
import org.eclipse.jetty.websocket.common.CloseInfo;
import org.eclipse.jetty.websocket.common.WebSocketSession;
import org.eclipse.jetty.websocket.common.events.EventDriver;
import org.eclipse.jetty.websocket.common.frames.CloseFrame;
import org.eclipse.jetty.websocket.common.message.MessageAppender;

public abstract class AbstractEventDriver
extends AbstractLifeCycle
implements IncomingFrames,
EventDriver {
    private static final Logger LOG = Log.getLogger(AbstractEventDriver.class);
    protected final Logger TARGET_LOG;
    protected WebSocketPolicy policy;
    protected final Object websocket;
    protected WebSocketSession session;
    protected MessageAppender activeMessage;

    public AbstractEventDriver(WebSocketPolicy policy, Object websocket) {
        this.policy = policy;
        this.websocket = websocket;
        this.TARGET_LOG = Log.getLogger(websocket.getClass());
    }

    protected void appendMessage(ByteBuffer buffer, boolean fin) throws IOException {
        this.activeMessage.appendFrame(buffer, fin);
        if (fin) {
            this.activeMessage.messageComplete();
            this.activeMessage = null;
        }
    }

    protected void dispatch(Runnable runnable) {
        this.session.dispatch(runnable);
    }

    @Override
    public WebSocketPolicy getPolicy() {
        return this.policy;
    }

    @Override
    public WebSocketSession getSession() {
        return this.session;
    }

    @Override
    public final void incomingError(Throwable e) {
        if (LOG.isDebugEnabled()) {
            LOG.debug("incomingError(" + e.getClass().getName() + ")", e);
        }
        this.onError(e);
    }

    @Override
    public void incomingFrame(Frame frame) {
        if (LOG.isDebugEnabled()) {
            LOG.debug("incomingFrame({})", frame);
        }
        try {
            this.onFrame(frame);
            byte opcode = frame.getOpCode();
            switch (opcode) {
                case 8: {
                    boolean validate = true;
                    CloseFrame closeframe = (CloseFrame)frame;
                    CloseInfo close = new CloseInfo(closeframe, validate);
                    this.session.getConnection().getIOState().onCloseRemote(close);
                    return;
                }
                case 9: {
                    ByteBuffer pongBuf;
                    if (LOG.isDebugEnabled()) {
                        LOG.debug("PING: {}", BufferUtil.toDetailString(frame.getPayload()));
                    }
                    if (frame.hasPayload()) {
                        pongBuf = ByteBuffer.allocate(frame.getPayload().remaining());
                        BufferUtil.put(frame.getPayload().slice(), pongBuf);
                        BufferUtil.flipToFlush(pongBuf, 0);
                    } else {
                        pongBuf = ByteBuffer.allocate(0);
                    }
                    this.onPing(frame.getPayload());
                    this.session.getRemote().sendPong(pongBuf);
                    break;
                }
                case 10: {
                    if (LOG.isDebugEnabled()) {
                        LOG.debug("PONG: {}", BufferUtil.toDetailString(frame.getPayload()));
                    }
                    this.onPong(frame.getPayload());
                    break;
                }
                case 2: {
                    this.onBinaryFrame(frame.getPayload(), frame.isFin());
                    return;
                }
                case 1: {
                    this.onTextFrame(frame.getPayload(), frame.isFin());
                    return;
                }
                case 0: {
                    this.onContinuationFrame(frame.getPayload(), frame.isFin());
                    return;
                }
                default: {
                    if (LOG.isDebugEnabled()) {
                        LOG.debug("Unhandled OpCode: {}", opcode);
                    }
                    break;
                }
            }
        }
        catch (Utf8Appendable.NotUtf8Exception e) {
            this.terminateConnection(1007, e.getMessage());
        }
        catch (CloseException e) {
            this.terminateConnection(e.getStatusCode(), e.getMessage());
        }
        catch (Throwable t) {
            this.unhandled(t);
        }
    }

    @Override
    public void onContinuationFrame(ByteBuffer buffer, boolean fin) throws IOException {
        if (this.activeMessage == null) {
            throw new IOException("Out of order Continuation frame encountered");
        }
        this.appendMessage(buffer, fin);
    }

    @Override
    public void onPong(ByteBuffer buffer) {
    }

    @Override
    public void onPing(ByteBuffer buffer) {
    }

    @Override
    public BatchMode getBatchMode() {
        return null;
    }

    @Override
    public void openSession(WebSocketSession session) {
        if (LOG.isDebugEnabled()) {
            LOG.debug("openSession({})", session);
            LOG.debug("objectFactory={}", session.getContainerScope().getObjectFactory());
        }
        this.session = session;
        this.session.getContainerScope().getObjectFactory().decorate(this.websocket);
        try {
            this.onConnect();
        }
        catch (Throwable t) {
            this.session.notifyError(t);
            throw t;
        }
    }

    protected void terminateConnection(int statusCode, String rawreason) {
        if (LOG.isDebugEnabled()) {
            LOG.debug("terminateConnection({},{})", statusCode, rawreason);
        }
        this.session.close(statusCode, CloseFrame.truncate(rawreason));
    }

    private void unhandled(Throwable t) {
        this.TARGET_LOG.warn("Unhandled Error (closing connection)", t);
        this.onError(t);
        if (t instanceof CloseException) {
            this.terminateConnection(((CloseException)t).getStatusCode(), t.getClass().getSimpleName());
            return;
        }
        switch (this.policy.getBehavior()) {
            case SERVER: {
                this.terminateConnection(1011, t.getClass().getSimpleName());
                break;
            }
            case CLIENT: {
                this.terminateConnection(1008, t.getClass().getSimpleName());
            }
        }
    }
}

