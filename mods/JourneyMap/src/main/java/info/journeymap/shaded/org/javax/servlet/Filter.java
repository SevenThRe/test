/*
 * Decompiled with CFR 0.152.
 */
package info.journeymap.shaded.org.javax.servlet;

import info.journeymap.shaded.org.javax.servlet.FilterChain;
import info.journeymap.shaded.org.javax.servlet.FilterConfig;
import info.journeymap.shaded.org.javax.servlet.ServletException;
import info.journeymap.shaded.org.javax.servlet.ServletRequest;
import info.journeymap.shaded.org.javax.servlet.ServletResponse;
import java.io.IOException;

public interface Filter {
    default public void init(FilterConfig filterConfig) throws ServletException {
    }

    public void doFilter(ServletRequest var1, ServletResponse var2, FilterChain var3) throws IOException, ServletException;

    default public void destroy() {
    }
}

