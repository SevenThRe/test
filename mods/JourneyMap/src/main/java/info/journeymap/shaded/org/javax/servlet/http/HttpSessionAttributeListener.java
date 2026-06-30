/*
 * Decompiled with CFR 0.152.
 */
package info.journeymap.shaded.org.javax.servlet.http;

import info.journeymap.shaded.org.javax.servlet.http.HttpSessionBindingEvent;
import java.util.EventListener;

public interface HttpSessionAttributeListener
extends EventListener {
    default public void attributeAdded(HttpSessionBindingEvent event) {
    }

    default public void attributeRemoved(HttpSessionBindingEvent event) {
    }

    default public void attributeReplaced(HttpSessionBindingEvent event) {
    }
}

