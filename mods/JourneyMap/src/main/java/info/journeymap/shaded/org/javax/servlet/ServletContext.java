/*
 * Decompiled with CFR 0.152.
 */
package info.journeymap.shaded.org.javax.servlet;

import info.journeymap.shaded.org.javax.servlet.Filter;
import info.journeymap.shaded.org.javax.servlet.FilterRegistration;
import info.journeymap.shaded.org.javax.servlet.RequestDispatcher;
import info.journeymap.shaded.org.javax.servlet.Servlet;
import info.journeymap.shaded.org.javax.servlet.ServletException;
import info.journeymap.shaded.org.javax.servlet.ServletRegistration;
import info.journeymap.shaded.org.javax.servlet.SessionCookieConfig;
import info.journeymap.shaded.org.javax.servlet.SessionTrackingMode;
import info.journeymap.shaded.org.javax.servlet.descriptor.JspConfigDescriptor;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Enumeration;
import java.util.EventListener;
import java.util.Map;
import java.util.Set;

public interface ServletContext {
    public static final String TEMPDIR = "info.journeymap.shaded.org.javax.servlet.context.tempdir";
    public static final String ORDERED_LIBS = "info.journeymap.shaded.org.javax.servlet.context.orderedLibs";

    public String getContextPath();

    public ServletContext getContext(String var1);

    public int getMajorVersion();

    public int getMinorVersion();

    public int getEffectiveMajorVersion();

    public int getEffectiveMinorVersion();

    public String getMimeType(String var1);

    public Set<String> getResourcePaths(String var1);

    public URL getResource(String var1) throws MalformedURLException;

    public InputStream getResourceAsStream(String var1);

    public RequestDispatcher getRequestDispatcher(String var1);

    public RequestDispatcher getNamedDispatcher(String var1);

    @Deprecated
    public Servlet getServlet(String var1) throws ServletException;

    @Deprecated
    public Enumeration<Servlet> getServlets();

    @Deprecated
    public Enumeration<String> getServletNames();

    public void log(String var1);

    @Deprecated
    public void log(Exception var1, String var2);

    public void log(String var1, Throwable var2);

    public String getRealPath(String var1);

    public String getServerInfo();

    public String getInitParameter(String var1);

    public Enumeration<String> getInitParameterNames();

    public boolean setInitParameter(String var1, String var2);

    public Object getAttribute(String var1);

    public Enumeration<String> getAttributeNames();

    public void setAttribute(String var1, Object var2);

    public void removeAttribute(String var1);

    public String getServletContextName();

    public ServletRegistration.Dynamic addServlet(String var1, String var2);

    public ServletRegistration.Dynamic addServlet(String var1, Servlet var2);

    public ServletRegistration.Dynamic addServlet(String var1, Class<? extends Servlet> var2);

    public ServletRegistration.Dynamic addJspFile(String var1, String var2);

    public <T extends Servlet> T createServlet(Class<T> var1) throws ServletException;

    public ServletRegistration getServletRegistration(String var1);

    public Map<String, ? extends ServletRegistration> getServletRegistrations();

    public FilterRegistration.Dynamic addFilter(String var1, String var2);

    public FilterRegistration.Dynamic addFilter(String var1, Filter var2);

    public FilterRegistration.Dynamic addFilter(String var1, Class<? extends Filter> var2);

    public <T extends Filter> T createFilter(Class<T> var1) throws ServletException;

    public FilterRegistration getFilterRegistration(String var1);

    public Map<String, ? extends FilterRegistration> getFilterRegistrations();

    public SessionCookieConfig getSessionCookieConfig();

    public void setSessionTrackingModes(Set<SessionTrackingMode> var1);

    public Set<SessionTrackingMode> getDefaultSessionTrackingModes();

    public Set<SessionTrackingMode> getEffectiveSessionTrackingModes();

    public void addListener(String var1);

    public <T extends EventListener> void addListener(T var1);

    public void addListener(Class<? extends EventListener> var1);

    public <T extends EventListener> T createListener(Class<T> var1) throws ServletException;

    public JspConfigDescriptor getJspConfigDescriptor();

    public ClassLoader getClassLoader();

    public void declareRoles(String ... var1);

    public String getVirtualServerName();

    public int getSessionTimeout();

    public void setSessionTimeout(int var1);

    public String getRequestCharacterEncoding();

    public void setRequestCharacterEncoding(String var1);

    public String getResponseCharacterEncoding();

    public void setResponseCharacterEncoding(String var1);
}

