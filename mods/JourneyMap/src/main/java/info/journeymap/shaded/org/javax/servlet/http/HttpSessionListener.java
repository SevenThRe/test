/*
 * Decompiled with CFR 0.152.
 */
package info.journeymap.shaded.org.javax.servlet.http;

import info.journeymap.shaded.org.javax.servlet.http.HttpSessionEvent;
import java.util.EventListener;

public interface HttpSessionListener
extends EventListener {
    default public void sessionCreated(HttpSessionEvent se) {
    }

    default public void sessionDestroyed(HttpSessionEvent se) {
    }
}

