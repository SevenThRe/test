/*
 * Decompiled with CFR 0.152.
 */
package info.journeymap.shaded.org.javax.servlet.http;

import info.journeymap.shaded.org.javax.servlet.http.HttpSessionEvent;
import java.util.EventListener;

public interface HttpSessionActivationListener
extends EventListener {
    default public void sessionWillPassivate(HttpSessionEvent se) {
    }

    default public void sessionDidActivate(HttpSessionEvent se) {
    }
}

