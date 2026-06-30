/*
 * Decompiled with CFR 0.152.
 */
package org.eclipse.jetty.websocket.server;

import info.journeymap.shaded.org.javax.servlet.ServletContainerInitializer;
import info.journeymap.shaded.org.javax.servlet.ServletContext;
import info.journeymap.shaded.org.javax.servlet.ServletException;
import java.util.Set;
import org.eclipse.jetty.websocket.server.NativeWebSocketConfiguration;

public class NativeWebSocketServletContainerInitializer
implements ServletContainerInitializer {
    public static NativeWebSocketConfiguration getDefaultFrom(ServletContext context) {
        String KEY = NativeWebSocketConfiguration.class.getName();
        NativeWebSocketConfiguration configuration = (NativeWebSocketConfiguration)context.getAttribute(KEY);
        if (configuration == null) {
            configuration = new NativeWebSocketConfiguration(context);
            context.setAttribute(KEY, configuration);
        }
        return configuration;
    }

    @Override
    public void onStartup(Set<Class<?>> c, ServletContext ctx) throws ServletException {
        NativeWebSocketServletContainerInitializer.getDefaultFrom(ctx);
    }
}

