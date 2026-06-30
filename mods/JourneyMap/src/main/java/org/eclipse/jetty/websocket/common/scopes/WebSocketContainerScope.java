/*
 * Decompiled with CFR 0.152.
 */
package org.eclipse.jetty.websocket.common.scopes;

import java.util.concurrent.Executor;
import org.eclipse.jetty.io.ByteBufferPool;
import org.eclipse.jetty.util.DecoratedObjectFactory;
import org.eclipse.jetty.util.ssl.SslContextFactory;
import org.eclipse.jetty.websocket.api.WebSocketPolicy;
import org.eclipse.jetty.websocket.common.WebSocketSession;

public interface WebSocketContainerScope {
    public ByteBufferPool getBufferPool();

    public Executor getExecutor();

    public DecoratedObjectFactory getObjectFactory();

    public WebSocketPolicy getPolicy();

    public SslContextFactory getSslContextFactory();

    public boolean isRunning();

    public void onSessionOpened(WebSocketSession var1);

    public void onSessionClosed(WebSocketSession var1);
}

