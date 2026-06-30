/*
 * Decompiled with CFR 0.152.
 */
package info.journeymap.shaded.org.javax.servlet;

import info.journeymap.shaded.org.javax.servlet.ServletContextAttributeEvent;
import java.util.EventListener;

public interface ServletContextAttributeListener
extends EventListener {
    default public void attributeAdded(ServletContextAttributeEvent event) {
    }

    default public void attributeRemoved(ServletContextAttributeEvent event) {
    }

    default public void attributeReplaced(ServletContextAttributeEvent event) {
    }
}

