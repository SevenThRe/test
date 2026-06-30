/*
 * Decompiled with CFR 0.152.
 */
package org.eclipse.jetty.websocket.common.io;

import java.io.EOFException;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.atomic.AtomicReference;
import org.eclipse.jetty.util.StringUtil;
import org.eclipse.jetty.util.log.Log;
import org.eclipse.jetty.util.log.Logger;
import org.eclipse.jetty.websocket.common.CloseInfo;
import org.eclipse.jetty.websocket.common.ConnectionState;

public class IOState {
    private static final Logger LOG = Log.getLogger(IOState.class);
    private ConnectionState state;
    private final List<ConnectionStateListener> listeners = new CopyOnWriteArrayList<ConnectionStateListener>();
    private boolean inputAvailable = false;
    private boolean outputAvailable = false;
    private CloseHandshakeSource closeHandshakeSource;
    private CloseInfo closeInfo = null;
    private AtomicReference<CloseInfo> finalClose = new AtomicReference();
    private boolean cleanClose = false;

    public IOState() {
        this.state = ConnectionState.CONNECTING;
        this.closeHandshakeSource = CloseHandshakeSource.NONE;
    }

    public void addListener(ConnectionStateListener listener) {
        this.listeners.add(listener);
    }

    public void assertInputOpen() throws IOException {
        if (!this.isInputAvailable()) {
            throw new IOException("Connection input is closed");
        }
    }

    public void assertOutputOpen() throws IOException {
        if (!this.isOutputAvailable()) {
            throw new IOException("Connection output is closed");
        }
    }

    public CloseInfo getCloseInfo() {
        CloseInfo ci = this.finalClose.get();
        if (ci != null) {
            return ci;
        }
        return this.closeInfo;
    }

    public ConnectionState getConnectionState() {
        return this.state;
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public boolean isClosed() {
        IOState iOState = this;
        synchronized (iOState) {
            return this.state == ConnectionState.CLOSED;
        }
    }

    public boolean isInputAvailable() {
        return this.inputAvailable;
    }

    public boolean isOpen() {
        return !this.isClosed();
    }

    public boolean isOutputAvailable() {
        return this.outputAvailable;
    }

    private void notifyStateListeners(ConnectionState state) {
        if (LOG.isDebugEnabled()) {
            LOG.debug("Notify State Listeners: {}", new Object[]{state});
        }
        for (ConnectionStateListener listener : this.listeners) {
            if (LOG.isDebugEnabled()) {
                LOG.debug("{}.onConnectionStateChange({})", listener.getClass().getSimpleName(), state.name());
            }
            listener.onConnectionStateChange(state);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public void onAbnormalClose(CloseInfo close) {
        if (LOG.isDebugEnabled()) {
            LOG.debug("onAbnormalClose({})", close);
        }
        ConnectionState event = null;
        IOState iOState = this;
        synchronized (iOState) {
            if (this.state == ConnectionState.CLOSED) {
                return;
            }
            if (this.state == ConnectionState.OPEN) {
                this.cleanClose = false;
            }
            this.state = ConnectionState.CLOSED;
            this.finalClose.compareAndSet(null, close);
            this.inputAvailable = false;
            this.outputAvailable = false;
            this.closeHandshakeSource = CloseHandshakeSource.ABNORMAL;
            event = this.state;
        }
        this.notifyStateListeners(event);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public void onCloseLocal(CloseInfo closeInfo) {
        boolean open = false;
        IOState iOState = this;
        synchronized (iOState) {
            ConnectionState initialState = this.state;
            if (LOG.isDebugEnabled()) {
                LOG.debug("onCloseLocal({}) : {}", new Object[]{closeInfo, initialState});
            }
            if (initialState == ConnectionState.CLOSED) {
                if (LOG.isDebugEnabled()) {
                    LOG.debug("already closed", new Object[0]);
                }
                return;
            }
            if (initialState == ConnectionState.CONNECTED) {
                if (LOG.isDebugEnabled()) {
                    LOG.debug("FastClose in CONNECTED detected", new Object[0]);
                }
                open = true;
            }
        }
        if (open) {
            this.openAndCloseLocal(closeInfo);
        } else {
            this.closeLocal(closeInfo);
        }
    }

    private void openAndCloseLocal(CloseInfo closeInfo) {
        this.onOpened();
        if (LOG.isDebugEnabled()) {
            LOG.debug("FastClose continuing with Closure", new Object[0]);
        }
        this.closeLocal(closeInfo);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    private void closeLocal(CloseInfo closeInfo) {
        ConnectionState event = null;
        ConnectionState abnormalEvent = null;
        IOState iOState = this;
        synchronized (iOState) {
            if (LOG.isDebugEnabled()) {
                LOG.debug("onCloseLocal(), input={}, output={}", this.inputAvailable, this.outputAvailable);
            }
            this.closeInfo = closeInfo;
            this.outputAvailable = false;
            if (this.closeHandshakeSource == CloseHandshakeSource.NONE) {
                this.closeHandshakeSource = CloseHandshakeSource.LOCAL;
            }
            if (!this.inputAvailable) {
                if (LOG.isDebugEnabled()) {
                    LOG.debug("Close Handshake satisfied, disconnecting", new Object[0]);
                }
                this.cleanClose = true;
                this.state = ConnectionState.CLOSED;
                this.finalClose.compareAndSet(null, closeInfo);
                event = this.state;
            } else if (this.state == ConnectionState.OPEN) {
                event = this.state = ConnectionState.CLOSING;
                if (closeInfo.isAbnormal()) {
                    abnormalEvent = ConnectionState.CLOSED;
                    this.finalClose.compareAndSet(null, closeInfo);
                    this.cleanClose = false;
                    this.outputAvailable = false;
                    this.inputAvailable = false;
                    this.closeHandshakeSource = CloseHandshakeSource.ABNORMAL;
                }
            }
        }
        if (event != null) {
            this.notifyStateListeners(event);
            if (abnormalEvent != null) {
                this.notifyStateListeners(abnormalEvent);
            }
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public void onCloseRemote(CloseInfo closeInfo) {
        if (LOG.isDebugEnabled()) {
            LOG.debug("onCloseRemote({})", closeInfo);
        }
        ConnectionState event = null;
        IOState iOState = this;
        synchronized (iOState) {
            if (this.state == ConnectionState.CLOSED) {
                return;
            }
            if (LOG.isDebugEnabled()) {
                LOG.debug("onCloseRemote(), input={}, output={}", this.inputAvailable, this.outputAvailable);
            }
            this.closeInfo = closeInfo;
            this.inputAvailable = false;
            if (this.closeHandshakeSource == CloseHandshakeSource.NONE) {
                this.closeHandshakeSource = CloseHandshakeSource.REMOTE;
            }
            if (!this.outputAvailable) {
                LOG.debug("Close Handshake satisfied, disconnecting", new Object[0]);
                this.cleanClose = true;
                this.state = ConnectionState.CLOSED;
                this.finalClose.compareAndSet(null, closeInfo);
                event = this.state;
            } else if (this.state == ConnectionState.OPEN) {
                event = this.state = ConnectionState.CLOSING;
            }
        }
        if (event != null) {
            this.notifyStateListeners(event);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public void onConnected() {
        ConnectionState event = null;
        IOState iOState = this;
        synchronized (iOState) {
            if (this.state != ConnectionState.CONNECTING) {
                LOG.debug("Unable to set to connected, not in CONNECTING state: {}", new Object[]{this.state});
                return;
            }
            this.state = ConnectionState.CONNECTED;
            this.inputAvailable = false;
            this.outputAvailable = true;
            event = this.state;
        }
        this.notifyStateListeners(event);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public void onFailedUpgrade() {
        assert (this.state == ConnectionState.CONNECTING);
        ConnectionState event = null;
        IOState iOState = this;
        synchronized (iOState) {
            this.state = ConnectionState.CLOSED;
            this.cleanClose = false;
            this.inputAvailable = false;
            this.outputAvailable = false;
            event = this.state;
        }
        this.notifyStateListeners(event);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public void onOpened() {
        if (LOG.isDebugEnabled()) {
            LOG.debug("onOpened()", new Object[0]);
        }
        ConnectionState event = null;
        IOState iOState = this;
        synchronized (iOState) {
            if (this.state == ConnectionState.OPEN) {
                return;
            }
            if (this.state != ConnectionState.CONNECTED) {
                LOG.debug("Unable to open, not in CONNECTED state: {}", new Object[]{this.state});
                return;
            }
            this.state = ConnectionState.OPEN;
            this.inputAvailable = true;
            this.outputAvailable = true;
            event = this.state;
        }
        this.notifyStateListeners(event);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public void onReadFailure(Throwable t) {
        ConnectionState event = null;
        IOState iOState = this;
        synchronized (iOState) {
            if (this.state == ConnectionState.CLOSED) {
                return;
            }
            String reason = "WebSocket Read Failure";
            if (t instanceof EOFException) {
                reason = "WebSocket Read EOF";
                Throwable cause = t.getCause();
                if (cause != null && StringUtil.isNotBlank(cause.getMessage())) {
                    reason = "EOF: " + cause.getMessage();
                }
            } else if (StringUtil.isNotBlank(t.getMessage())) {
                reason = t.getMessage();
            }
            CloseInfo close = new CloseInfo(1006, reason);
            this.finalClose.compareAndSet(null, close);
            this.cleanClose = false;
            this.state = ConnectionState.CLOSED;
            this.closeInfo = close;
            this.inputAvailable = false;
            this.outputAvailable = false;
            this.closeHandshakeSource = CloseHandshakeSource.ABNORMAL;
            event = this.state;
        }
        this.notifyStateListeners(event);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public void onWriteFailure(Throwable t) {
        ConnectionState event = null;
        IOState iOState = this;
        synchronized (iOState) {
            if (this.state == ConnectionState.CLOSED) {
                return;
            }
            String reason = "WebSocket Write Failure";
            if (t instanceof EOFException) {
                reason = "WebSocket Write EOF";
                Throwable cause = t.getCause();
                if (cause != null && StringUtil.isNotBlank(cause.getMessage())) {
                    reason = "EOF: " + cause.getMessage();
                }
            } else if (StringUtil.isNotBlank(t.getMessage())) {
                reason = t.getMessage();
            }
            CloseInfo close = new CloseInfo(1006, reason);
            this.finalClose.compareAndSet(null, close);
            this.cleanClose = false;
            this.state = ConnectionState.CLOSED;
            this.inputAvailable = false;
            this.outputAvailable = false;
            this.closeHandshakeSource = CloseHandshakeSource.ABNORMAL;
            event = this.state;
        }
        this.notifyStateListeners(event);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public void onDisconnected() {
        ConnectionState event = null;
        IOState iOState = this;
        synchronized (iOState) {
            if (this.state == ConnectionState.CLOSED) {
                return;
            }
            CloseInfo close = new CloseInfo(1006, "Disconnected");
            this.cleanClose = false;
            this.state = ConnectionState.CLOSED;
            this.closeInfo = close;
            this.inputAvailable = false;
            this.outputAvailable = false;
            this.closeHandshakeSource = CloseHandshakeSource.ABNORMAL;
            event = this.state;
        }
        this.notifyStateListeners(event);
    }

    public String toString() {
        StringBuilder str = new StringBuilder();
        str.append(this.getClass().getSimpleName());
        str.append("@").append(Integer.toHexString(this.hashCode()));
        str.append("[").append((Object)this.state);
        str.append(',');
        if (!this.inputAvailable) {
            str.append('!');
        }
        str.append("in,");
        if (!this.outputAvailable) {
            str.append('!');
        }
        str.append("out");
        if (this.state == ConnectionState.CLOSED || this.state == ConnectionState.CLOSING) {
            CloseInfo ci = this.finalClose.get();
            if (ci != null) {
                str.append(",finalClose=").append(ci);
            } else {
                str.append(",close=").append(this.closeInfo);
            }
            str.append(",clean=").append(this.cleanClose);
            str.append(",closeSource=").append((Object)this.closeHandshakeSource);
        }
        str.append(']');
        return str.toString();
    }

    public boolean wasAbnormalClose() {
        return this.closeHandshakeSource == CloseHandshakeSource.ABNORMAL;
    }

    public boolean wasCleanClose() {
        return this.cleanClose;
    }

    public boolean wasLocalCloseInitiated() {
        return this.closeHandshakeSource == CloseHandshakeSource.LOCAL;
    }

    public boolean wasRemoteCloseInitiated() {
        return this.closeHandshakeSource == CloseHandshakeSource.REMOTE;
    }

    public static interface ConnectionStateListener {
        public void onConnectionStateChange(ConnectionState var1);
    }

    private static enum CloseHandshakeSource {
        NONE,
        LOCAL,
        REMOTE,
        ABNORMAL;

    }
}

