/*
 * Decompiled with CFR 0.152.
 */
package info.journeymap.shaded.kotlin.spark.embeddedserver;

import info.journeymap.shaded.kotlin.spark.embeddedserver.NotSupportedException;
import info.journeymap.shaded.kotlin.spark.embeddedserver.jetty.websocket.WebSocketHandlerWrapper;
import info.journeymap.shaded.kotlin.spark.ssl.SslStores;
import java.util.Map;
import java.util.Optional;

public interface EmbeddedServer {
    public int ignite(String var1, int var2, SslStores var3, int var4, int var5, int var6) throws Exception;

    default public void configureWebSockets(Map<String, WebSocketHandlerWrapper> webSocketHandlers, Optional<Integer> webSocketIdleTimeoutMillis) {
        NotSupportedException.raise(this.getClass().getSimpleName(), "Web Sockets");
    }

    public void join() throws InterruptedException;

    public void extinguish();

    public int activeThreadCount();
}

