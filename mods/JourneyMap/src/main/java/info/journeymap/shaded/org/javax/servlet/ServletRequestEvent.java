/*
 * Decompiled with CFR 0.152.
 */
package info.journeymap.shaded.org.javax.servlet;

import info.journeymap.shaded.org.javax.servlet.ServletContext;
import info.journeymap.shaded.org.javax.servlet.ServletRequest;
import java.util.EventObject;

public class ServletRequestEvent
extends EventObject {
    private static final long serialVersionUID = -7467864054698729101L;
    private final transient ServletRequest request;

    public ServletRequestEvent(ServletContext sc, ServletRequest request) {
        super(sc);
        this.request = request;
    }

    public ServletRequest getServletRequest() {
        return this.request;
    }

    public ServletContext getServletContext() {
        return (ServletContext)super.getSource();
    }
}

