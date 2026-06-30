/*
 * Decompiled with CFR 0.152.
 */
package info.journeymap.shaded.org.javax.servlet.http;

import info.journeymap.shaded.org.javax.servlet.http.HttpSessionBindingEvent;
import java.util.EventListener;

public interface HttpSessionBindingListener
extends EventListener {
    default public void valueBound(HttpSessionBindingEvent event) {
    }

    default public void valueUnbound(HttpSessionBindingEvent event) {
    }
}

