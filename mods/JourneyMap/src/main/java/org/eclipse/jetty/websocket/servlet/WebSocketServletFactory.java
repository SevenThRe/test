/*
 * Decompiled with CFR 0.152.
 */
package org.eclipse.jetty.websocket.servlet;

import info.journeymap.shaded.org.javax.servlet.ServletContext;
import info.journeymap.shaded.org.javax.servlet.http.HttpServletRequest;
import info.journeymap.shaded.org.javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import org.eclipse.jetty.websocket.api.WebSocketPolicy;
import org.eclipse.jetty.websocket.api.extensions.ExtensionFactory;
import org.eclipse.jetty.websocket.servlet.WebSocketCreator;

public interface WebSocketServletFactory {
    public boolean acceptWebSocket(HttpServletRequest var1, HttpServletResponse var2) throws IOException;

    public boolean acceptWebSocket(WebSocketCreator var1, HttpServletRequest var2, HttpServletResponse var3) throws IOException;

    public void start() throws Exception;

    public void stop() throws Exception;

    public WebSocketCreator getCreator();

    public ExtensionFactory getExtensionFactory();

    public WebSocketPolicy getPolicy();

    public boolean isUpgradeRequest(HttpServletRequest var1, HttpServletResponse var2);

    public void register(Class<?> var1);

    public void setCreator(WebSocketCreator var1);

    public static class Loader {
        static final String DEFAULT_IMPL = "org.eclipse.jetty.websocket.server.WebSocketServerFactory";

        public static WebSocketServletFactory load(ServletContext ctx, WebSocketPolicy policy) {
            try {
                Class<?> wsClazz = Class.forName(DEFAULT_IMPL, true, Thread.currentThread().getContextClassLoader());
                Constructor<?> ctor = wsClazz.getDeclaredConstructor(ServletContext.class, WebSocketPolicy.class);
                return (WebSocketServletFactory)ctor.newInstance(ctx, policy);
            }
            catch (ClassNotFoundException e) {
                throw new RuntimeException("Unable to load org.eclipse.jetty.websocket.server.WebSocketServerFactory", e);
            }
            catch (IllegalAccessException | InstantiationException | NoSuchMethodException | InvocationTargetException e) {
                throw new RuntimeException("Unable to instantiate org.eclipse.jetty.websocket.server.WebSocketServerFactory", e);
            }
        }
    }
}

