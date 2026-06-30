/*
 * Decompiled with CFR 0.152.
 */
package org.eclipse.jetty.websocket.common;

import java.nio.ByteBuffer;
import java.util.List;
import org.eclipse.jetty.io.ByteBufferPool;
import org.eclipse.jetty.util.BufferUtil;
import org.eclipse.jetty.util.log.Log;
import org.eclipse.jetty.util.log.Logger;
import org.eclipse.jetty.websocket.api.MessageTooLargeException;
import org.eclipse.jetty.websocket.api.ProtocolException;
import org.eclipse.jetty.websocket.api.WebSocketBehavior;
import org.eclipse.jetty.websocket.api.WebSocketException;
import org.eclipse.jetty.websocket.api.WebSocketPolicy;
import org.eclipse.jetty.websocket.api.extensions.Extension;
import org.eclipse.jetty.websocket.api.extensions.Frame;
import org.eclipse.jetty.websocket.api.extensions.IncomingFrames;
import org.eclipse.jetty.websocket.common.CloseInfo;
import org.eclipse.jetty.websocket.common.OpCode;
import org.eclipse.jetty.websocket.common.WebSocketFrame;
import org.eclipse.jetty.websocket.common.frames.BinaryFrame;
import org.eclipse.jetty.websocket.common.frames.CloseFrame;
import org.eclipse.jetty.websocket.common.frames.ContinuationFrame;
import org.eclipse.jetty.websocket.common.frames.PingFrame;
import org.eclipse.jetty.websocket.common.frames.PongFrame;
import org.eclipse.jetty.websocket.common.frames.TextFrame;
import org.eclipse.jetty.websocket.common.io.payload.DeMaskProcessor;
import org.eclipse.jetty.websocket.common.io.payload.PayloadProcessor;

public class Parser {
    private static final Logger LOG = Log.getLogger(Parser.class);
    private final WebSocketPolicy policy;
    private final ByteBufferPool bufferPool;
    private State state = State.START;
    private int cursor = 0;
    private WebSocketFrame frame;
    private boolean priorDataFrame;
    private ByteBuffer payload;
    private int payloadLength;
    private PayloadProcessor maskProcessor = new DeMaskProcessor();
    private byte flagsInUse = 0;
    private IncomingFrames incomingFramesHandler;

    public Parser(WebSocketPolicy wspolicy, ByteBufferPool bufferPool) {
        this.bufferPool = bufferPool;
        this.policy = wspolicy;
    }

    private void assertSanePayloadLength(long len) {
        if (LOG.isDebugEnabled()) {
            LOG.debug("{} Payload Length: {} - {}", new Object[]{this.policy.getBehavior(), len, this});
        }
        if (len > Integer.MAX_VALUE) {
            throw new MessageTooLargeException("[int-sane!] cannot handle payload lengths larger than 2147483647");
        }
        switch (this.frame.getOpCode()) {
            case 8: {
                if (len == 1L) {
                    throw new ProtocolException("Invalid close frame payload length, [" + this.payloadLength + "]");
                }
            }
            case 9: 
            case 10: {
                if (len <= 125L) break;
                throw new ProtocolException("Invalid control frame payload length, [" + this.payloadLength + "] cannot exceed [" + 125 + "]");
            }
            case 1: {
                this.policy.assertValidTextMessageSize((int)len);
                break;
            }
            case 2: {
                this.policy.assertValidBinaryMessageSize((int)len);
            }
        }
    }

    public void configureFromExtensions(List<? extends Extension> exts) {
        this.flagsInUse = 0;
        for (Extension extension : exts) {
            if (extension.isRsv1User()) {
                this.flagsInUse = (byte)(this.flagsInUse | 0x40);
            }
            if (extension.isRsv2User()) {
                this.flagsInUse = (byte)(this.flagsInUse | 0x20);
            }
            if (!extension.isRsv3User()) continue;
            this.flagsInUse = (byte)(this.flagsInUse | 0x10);
        }
    }

    public IncomingFrames getIncomingFramesHandler() {
        return this.incomingFramesHandler;
    }

    public WebSocketPolicy getPolicy() {
        return this.policy;
    }

    public boolean isRsv1InUse() {
        return (this.flagsInUse & 0x40) != 0;
    }

    public boolean isRsv2InUse() {
        return (this.flagsInUse & 0x20) != 0;
    }

    public boolean isRsv3InUse() {
        return (this.flagsInUse & 0x10) != 0;
    }

    protected void notifyFrame(Frame f) {
        if (LOG.isDebugEnabled()) {
            LOG.debug("{} Notify {}", new Object[]{this.policy.getBehavior(), this.getIncomingFramesHandler()});
        }
        if (this.policy.getBehavior() == WebSocketBehavior.SERVER) {
            if (!f.isMasked()) {
                throw new ProtocolException("Client MUST mask all frames (RFC-6455: Section 5.1)");
            }
        } else if (this.policy.getBehavior() == WebSocketBehavior.CLIENT && f.isMasked()) {
            throw new ProtocolException("Server MUST NOT mask any frames (RFC-6455: Section 5.1)");
        }
        if (this.incomingFramesHandler == null) {
            return;
        }
        try {
            this.incomingFramesHandler.incomingFrame(f);
        }
        catch (WebSocketException e) {
            this.notifyWebSocketException(e);
        }
        catch (Throwable t) {
            LOG.warn(t);
            this.notifyWebSocketException(new WebSocketException(t));
        }
    }

    protected void notifyWebSocketException(WebSocketException e) {
        LOG.warn(e);
        if (this.incomingFramesHandler == null) {
            return;
        }
        this.incomingFramesHandler.incomingError(e);
    }

    public void parse(ByteBuffer buffer) throws WebSocketException {
        if (buffer.remaining() <= 0) {
            return;
        }
        try {
            while (this.parseFrame(buffer)) {
                if (LOG.isDebugEnabled()) {
                    LOG.debug("{} Parsed Frame: {}", new Object[]{this.policy.getBehavior(), this.frame});
                }
                this.notifyFrame(this.frame);
                if (this.frame.isDataFrame()) {
                    this.priorDataFrame = !this.frame.isFin();
                }
                this.reset();
            }
        }
        catch (WebSocketException e) {
            buffer.position(buffer.limit());
            this.reset();
            this.notifyWebSocketException(e);
            throw e;
        }
        catch (Throwable t) {
            buffer.position(buffer.limit());
            this.reset();
            WebSocketException e = new WebSocketException(t);
            this.notifyWebSocketException(e);
            throw e;
        }
    }

    private void reset() {
        if (this.frame != null) {
            this.frame.reset();
        }
        this.frame = null;
        this.bufferPool.release(this.payload);
        this.payload = null;
    }

    private boolean parseFrame(ByteBuffer buffer) {
        if (LOG.isDebugEnabled()) {
            LOG.debug("{} Parsing {} bytes", new Object[]{this.policy.getBehavior(), buffer.remaining()});
        }
        while (buffer.hasRemaining()) {
            switch (this.state) {
                case START: {
                    byte b = buffer.get();
                    boolean fin = (b & 0x80) != 0;
                    byte opcode = (byte)(b & 0xF);
                    if (!OpCode.isKnown(opcode)) {
                        throw new ProtocolException("Unknown opcode: " + opcode);
                    }
                    if (LOG.isDebugEnabled()) {
                        LOG.debug("{} OpCode {}, fin={} rsv={}{}{}", new Object[]{this.policy.getBehavior(), OpCode.name(opcode), fin, Character.valueOf((b & 0x40) != 0 ? (char)'1' : '.'), Character.valueOf((b & 0x20) != 0 ? (char)'1' : '.'), Character.valueOf((b & 0x10) != 0 ? (char)'1' : '.')});
                    }
                    switch (opcode) {
                        case 1: {
                            this.frame = new TextFrame();
                            if (!this.priorDataFrame) break;
                            throw new ProtocolException("Unexpected " + OpCode.name(opcode) + " frame, was expecting CONTINUATION");
                        }
                        case 2: {
                            this.frame = new BinaryFrame();
                            if (!this.priorDataFrame) break;
                            throw new ProtocolException("Unexpected " + OpCode.name(opcode) + " frame, was expecting CONTINUATION");
                        }
                        case 0: {
                            this.frame = new ContinuationFrame();
                            if (this.priorDataFrame) break;
                            throw new ProtocolException("CONTINUATION frame without prior !FIN");
                        }
                        case 8: {
                            this.frame = new CloseFrame();
                            if (fin) break;
                            throw new ProtocolException("Fragmented Close Frame [" + OpCode.name(opcode) + "]");
                        }
                        case 9: {
                            this.frame = new PingFrame();
                            if (fin) break;
                            throw new ProtocolException("Fragmented Ping Frame [" + OpCode.name(opcode) + "]");
                        }
                        case 10: {
                            this.frame = new PongFrame();
                            if (fin) break;
                            throw new ProtocolException("Fragmented Pong Frame [" + OpCode.name(opcode) + "]");
                        }
                    }
                    this.frame.setFin(fin);
                    if ((b & 0x70) != 0) {
                        if ((b & 0x40) != 0) {
                            if (this.isRsv1InUse()) {
                                this.frame.setRsv1(true);
                            } else {
                                String err = "RSV1 not allowed to be set";
                                if (LOG.isDebugEnabled()) {
                                    LOG.debug(err + ": Remaining buffer: {}", BufferUtil.toDetailString(buffer));
                                }
                                throw new ProtocolException(err);
                            }
                        }
                        if ((b & 0x20) != 0) {
                            if (this.isRsv2InUse()) {
                                this.frame.setRsv2(true);
                            } else {
                                String err = "RSV2 not allowed to be set";
                                if (LOG.isDebugEnabled()) {
                                    LOG.debug(err + ": Remaining buffer: {}", BufferUtil.toDetailString(buffer));
                                }
                                throw new ProtocolException(err);
                            }
                        }
                        if ((b & 0x10) != 0) {
                            if (this.isRsv3InUse()) {
                                this.frame.setRsv3(true);
                            } else {
                                String err = "RSV3 not allowed to be set";
                                if (LOG.isDebugEnabled()) {
                                    LOG.debug(err + ": Remaining buffer: {}", BufferUtil.toDetailString(buffer));
                                }
                                throw new ProtocolException(err);
                            }
                        }
                    }
                    this.state = State.PAYLOAD_LEN;
                    break;
                }
                case PAYLOAD_LEN: {
                    byte b = buffer.get();
                    this.frame.setMasked((b & 0x80) != 0);
                    this.payloadLength = (byte)(0x7F & b);
                    if (this.payloadLength == 127) {
                        this.payloadLength = 0;
                        this.state = State.PAYLOAD_LEN_BYTES;
                        this.cursor = 8;
                        break;
                    }
                    if (this.payloadLength == 126) {
                        this.payloadLength = 0;
                        this.state = State.PAYLOAD_LEN_BYTES;
                        this.cursor = 2;
                        break;
                    }
                    this.assertSanePayloadLength(this.payloadLength);
                    if (this.frame.isMasked()) {
                        this.state = State.MASK;
                        break;
                    }
                    if (this.payloadLength == 0) {
                        this.state = State.START;
                        return true;
                    }
                    this.maskProcessor.reset(this.frame);
                    this.state = State.PAYLOAD;
                    break;
                }
                case PAYLOAD_LEN_BYTES: {
                    byte b = buffer.get();
                    --this.cursor;
                    this.payloadLength |= (b & 0xFF) << 8 * this.cursor;
                    if (this.cursor != 0) break;
                    this.assertSanePayloadLength(this.payloadLength);
                    if (this.frame.isMasked()) {
                        this.state = State.MASK;
                        break;
                    }
                    if (this.payloadLength == 0) {
                        this.state = State.START;
                        return true;
                    }
                    this.maskProcessor.reset(this.frame);
                    this.state = State.PAYLOAD;
                    break;
                }
                case MASK: {
                    byte[] m = new byte[4];
                    this.frame.setMask(m);
                    if (buffer.remaining() >= 4) {
                        buffer.get(m, 0, 4);
                        if (this.payloadLength == 0) {
                            this.state = State.START;
                            return true;
                        }
                        this.maskProcessor.reset(this.frame);
                        this.state = State.PAYLOAD;
                        break;
                    }
                    this.state = State.MASK_BYTES;
                    this.cursor = 4;
                    break;
                }
                case MASK_BYTES: {
                    byte b;
                    this.frame.getMask()[4 - this.cursor] = b = buffer.get();
                    --this.cursor;
                    if (this.cursor != 0) break;
                    if (this.payloadLength == 0) {
                        this.state = State.START;
                        return true;
                    }
                    this.maskProcessor.reset(this.frame);
                    this.state = State.PAYLOAD;
                    break;
                }
                case PAYLOAD: {
                    this.frame.assertValid();
                    if (!this.parsePayload(buffer)) break;
                    if (this.frame.getOpCode() == 8) {
                        new CloseInfo(this.frame);
                    }
                    this.state = State.START;
                    return true;
                }
            }
        }
        return false;
    }

    private boolean parsePayload(ByteBuffer buffer) {
        if (this.payloadLength == 0) {
            return true;
        }
        if (buffer.hasRemaining()) {
            int bytesSoFar = this.payload == null ? 0 : this.payload.position();
            int bytesExpected = this.payloadLength - bytesSoFar;
            int bytesAvailable = buffer.remaining();
            int windowBytes = Math.min(bytesAvailable, bytesExpected);
            int limit = buffer.limit();
            buffer.limit(buffer.position() + windowBytes);
            ByteBuffer window = buffer.slice();
            buffer.limit(limit);
            buffer.position(buffer.position() + window.remaining());
            if (LOG.isDebugEnabled()) {
                LOG.debug("{} Window: {}", new Object[]{this.policy.getBehavior(), BufferUtil.toDetailString(window)});
            }
            this.maskProcessor.process(window);
            if (window.remaining() == this.payloadLength) {
                this.frame.setPayload(window);
                return true;
            }
            if (this.payload == null) {
                this.payload = this.bufferPool.acquire(this.payloadLength, false);
                BufferUtil.clearToFill(this.payload);
            }
            this.payload.put(window);
            if (this.payload.position() == this.payloadLength) {
                BufferUtil.flipToFlush(this.payload, 0);
                this.frame.setPayload(this.payload);
                return true;
            }
        }
        return false;
    }

    public void setIncomingFramesHandler(IncomingFrames incoming) {
        this.incomingFramesHandler = incoming;
    }

    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("Parser@").append(Integer.toHexString(this.hashCode()));
        builder.append("[");
        if (this.incomingFramesHandler == null) {
            builder.append("NO_HANDLER");
        } else {
            builder.append(this.incomingFramesHandler.getClass().getSimpleName());
        }
        builder.append(",s=").append((Object)this.state);
        builder.append(",c=").append(this.cursor);
        builder.append(",len=").append(this.payloadLength);
        builder.append(",f=").append(this.frame);
        builder.append("]");
        return builder.toString();
    }

    private static enum State {
        START,
        PAYLOAD_LEN,
        PAYLOAD_LEN_BYTES,
        MASK,
        MASK_BYTES,
        PAYLOAD;

    }
}

