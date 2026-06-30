/*
 * Decompiled with CFR 0.152.
 */
package info.journeymap.shaded.org.javax.servlet;

import info.journeymap.shaded.org.javax.servlet.ServletRequestAttributeEvent;
import java.util.EventListener;

public interface ServletRequestAttributeListener
extends EventListener {
    default public void attributeAdded(ServletRequestAttributeEvent srae) {
    }

    default public void attributeRemoved(ServletRequestAttributeEvent srae) {
    }

    default public void attributeReplaced(ServletRequestAttributeEvent srae) {
    }
}

