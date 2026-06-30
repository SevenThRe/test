/*
 * Decompiled with CFR 0.152.
 */
package org.eclipse.jetty.websocket.api;

import java.net.HttpCookie;
import java.net.URI;
import java.security.Principal;
import java.util.List;
import java.util.Map;
import org.eclipse.jetty.websocket.api.extensions.ExtensionConfig;

public interface UpgradeRequest {
    public void addExtensions(ExtensionConfig ... var1);

    public void addExtensions(String ... var1);

    @Deprecated
    public void clearHeaders();

    public List<HttpCookie> getCookies();

    public List<ExtensionConfig> getExtensions();

    public String getHeader(String var1);

    public int getHeaderInt(String var1);

    public Map<String, List<String>> getHeaders();

    public List<String> getHeaders(String var1);

    public String getHost();

    public String getHttpVersion();

    public String getMethod();

    public String getOrigin();

    public Map<String, List<String>> getParameterMap();

    public String getProtocolVersion();

    public String getQueryString();

    public URI getRequestURI();

    public Object getSession();

    public List<String> getSubProtocols();

    public Principal getUserPrincipal();

    public boolean hasSubProtocol(String var1);

    public boolean isOrigin(String var1);

    public boolean isSecure();

    public void setCookies(List<HttpCookie> var1);

    public void setExtensions(List<ExtensionConfig> var1);

    public void setHeader(String var1, List<String> var2);

    public void setHeader(String var1, String var2);

    public void setHeaders(Map<String, List<String>> var1);

    public void setHttpVersion(String var1);

    public void setMethod(String var1);

    public void setRequestURI(URI var1);

    public void setSession(Object var1);

    public void setSubProtocols(List<String> var1);

    public void setSubProtocols(String ... var1);
}

