/*
 * Decompiled with CFR 0.152.
 */
package info.journeymap.shaded.org.javax.servlet;

import info.journeymap.shaded.org.javax.servlet.ServletContextEvent;
import java.util.EventListener;

public interface ServletContextListener
extends EventListener {
    default public void contextInitialized(ServletContextEvent sce) {
    }

    default public void contextDestroyed(ServletContextEvent sce) {
    }
}

