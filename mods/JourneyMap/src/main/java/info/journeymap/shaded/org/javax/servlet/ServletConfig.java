/*
 * Decompiled with CFR 0.152.
 */
package info.journeymap.shaded.org.javax.servlet;

import info.journeymap.shaded.org.javax.servlet.ServletContext;
import java.util.Enumeration;

public interface ServletConfig {
    public String getServletName();

    public ServletContext getServletContext();

    public String getInitParameter(String var1);

    public Enumeration<String> getInitParameterNames();
}

