/*
 * Decompiled with CFR 0.152.
 */
package org.eclipse.jetty.websocket.servlet;

import info.journeymap.shaded.org.javax.servlet.http.Cookie;
import info.journeymap.shaded.org.javax.servlet.http.HttpServletRequest;
import info.journeymap.shaded.org.javax.servlet.http.HttpSession;
import java.net.HttpCookie;
import java.net.InetSocketAddress;
import java.net.URI;
import java.net.URISyntaxException;
import java.security.Principal;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import org.eclipse.jetty.websocket.api.UpgradeRequest;
import org.eclipse.jetty.websocket.api.extensions.ExtensionConfig;
import org.eclipse.jetty.websocket.servlet.UpgradeHttpServletRequest;

public class ServletUpgradeRequest
implements UpgradeRequest {
    private static final String CANNOT_MODIFY_SERVLET_REQUEST = "Cannot modify Servlet Request";
    private final URI requestURI;
    private final UpgradeHttpServletRequest request;
    private final boolean secure;
    private List<HttpCookie> cookies;
    private Map<String, List<String>> parameterMap;
    private List<String> subprotocols;

    public ServletUpgradeRequest(HttpServletRequest httpRequest) throws URISyntaxException {
        URI servletURI = URI.create(httpRequest.getRequestURL().toString());
        this.secure = httpRequest.isSecure();
        String scheme = this.secure ? "wss" : "ws";
        String authority = servletURI.getAuthority();
        String path = servletURI.getPath();
        String query = httpRequest.getQueryString();
        String fragment = null;
        this.requestURI = new URI(scheme, authority, path, query, fragment);
        this.request = new UpgradeHttpServletRequest(httpRequest);
    }

    @Override
    public void addExtensions(ExtensionConfig ... configs) {
        throw new UnsupportedOperationException(CANNOT_MODIFY_SERVLET_REQUEST);
    }

    @Override
    public void addExtensions(String ... configs) {
        throw new UnsupportedOperationException(CANNOT_MODIFY_SERVLET_REQUEST);
    }

    @Override
    public void clearHeaders() {
        throw new UnsupportedOperationException(CANNOT_MODIFY_SERVLET_REQUEST);
    }

    public void complete() {
        this.request.complete();
    }

    public X509Certificate[] getCertificates() {
        return (X509Certificate[])this.request.getAttribute("info.journeymap.shaded.org.javax.servlet.request.X509Certificate");
    }

    @Override
    public List<HttpCookie> getCookies() {
        Cookie[] requestCookies;
        if (this.cookies == null && (requestCookies = this.request.getCookies()) != null) {
            this.cookies = new ArrayList<HttpCookie>();
            for (Cookie requestCookie : requestCookies) {
                HttpCookie cookie = new HttpCookie(requestCookie.getName(), requestCookie.getValue());
                this.cookies.add(cookie);
            }
        }
        return this.cookies;
    }

    @Override
    public List<ExtensionConfig> getExtensions() {
        Enumeration<String> e = this.request.getHeaders("Sec-WebSocket-Extensions");
        return ExtensionConfig.parseEnum(e);
    }

    @Override
    public String getHeader(String name) {
        return this.request.getHeader(name);
    }

    @Override
    public int getHeaderInt(String name) {
        String val = this.request.getHeader(name);
        if (val == null) {
            return -1;
        }
        return Integer.parseInt(val);
    }

    @Override
    public Map<String, List<String>> getHeaders() {
        return this.request.getHeaders();
    }

    @Override
    public List<String> getHeaders(String name) {
        return this.request.getHeaders().get(name);
    }

    @Override
    public String getHost() {
        return this.requestURI.getHost();
    }

    public HttpServletRequest getHttpServletRequest() {
        return this.request;
    }

    @Override
    public String getHttpVersion() {
        return this.request.getProtocol();
    }

    public String getLocalAddress() {
        return this.request.getLocalAddr();
    }

    public Locale getLocale() {
        return this.request.getLocale();
    }

    public Enumeration<Locale> getLocales() {
        return this.request.getLocales();
    }

    public String getLocalHostName() {
        return this.request.getLocalName();
    }

    public int getLocalPort() {
        return this.request.getLocalPort();
    }

    public InetSocketAddress getLocalSocketAddress() {
        return new InetSocketAddress(this.getLocalAddress(), this.getLocalPort());
    }

    @Override
    public String getMethod() {
        return this.request.getMethod();
    }

    @Override
    public String getOrigin() {
        return this.getHeader("Origin");
    }

    @Override
    public Map<String, List<String>> getParameterMap() {
        Map<String, String[]> requestParams;
        if (this.parameterMap == null && (requestParams = this.request.getParameterMap()) != null) {
            this.parameterMap = new HashMap<String, List<String>>(requestParams.size());
            for (Map.Entry<String, String[]> entry : requestParams.entrySet()) {
                this.parameterMap.put(entry.getKey(), Arrays.asList((Object[])entry.getValue()));
            }
        }
        return this.parameterMap;
    }

    @Deprecated
    public Principal getPrincipal() {
        return this.getUserPrincipal();
    }

    @Override
    public String getProtocolVersion() {
        String version = this.request.getHeader("Sec-WebSocket-Version");
        if (version == null) {
            return Integer.toString(13);
        }
        return version;
    }

    @Override
    public String getQueryString() {
        return this.requestURI.getQuery();
    }

    public String getRemoteAddress() {
        return this.request.getRemoteAddr();
    }

    public String getRemoteHostName() {
        return this.request.getRemoteHost();
    }

    public int getRemotePort() {
        return this.request.getRemotePort();
    }

    public InetSocketAddress getRemoteSocketAddress() {
        return new InetSocketAddress(this.getRemoteAddress(), this.getRemotePort());
    }

    public String getRequestPath() {
        String contextPath = this.request.getContextPath();
        String requestPath = this.request.getRequestURI();
        if (requestPath.startsWith(contextPath)) {
            requestPath = requestPath.substring(contextPath.length());
        }
        return requestPath;
    }

    @Override
    public URI getRequestURI() {
        return this.requestURI;
    }

    public Object getServletAttribute(String name) {
        return this.request.getAttribute(name);
    }

    public Map<String, Object> getServletAttributes() {
        return this.request.getAttributes();
    }

    public Map<String, List<String>> getServletParameters() {
        return this.getParameterMap();
    }

    @Override
    public HttpSession getSession() {
        return this.request.getSession(false);
    }

    @Override
    public List<String> getSubProtocols() {
        Enumeration<String> requestProtocols;
        if (this.subprotocols == null && (requestProtocols = this.request.getHeaders("Sec-WebSocket-Protocol")) != null) {
            this.subprotocols = new ArrayList<String>(2);
            while (requestProtocols.hasMoreElements()) {
                String candidate = requestProtocols.nextElement();
                Collections.addAll(this.subprotocols, this.parseProtocols(candidate));
            }
        }
        return this.subprotocols;
    }

    @Override
    public Principal getUserPrincipal() {
        return this.request.getUserPrincipal();
    }

    @Override
    public boolean hasSubProtocol(String test) {
        for (String protocol : this.getSubProtocols()) {
            if (!protocol.equalsIgnoreCase(test)) continue;
            return true;
        }
        return false;
    }

    @Override
    public boolean isOrigin(String test) {
        return test.equalsIgnoreCase(this.getOrigin());
    }

    @Override
    public boolean isSecure() {
        return this.secure;
    }

    public boolean isUserInRole(String role) {
        return this.request.isUserInRole(role);
    }

    private String[] parseProtocols(String protocol) {
        if (protocol == null) {
            return new String[0];
        }
        if ((protocol = protocol.trim()).length() == 0) {
            return new String[0];
        }
        return protocol.split("\\s*,\\s*");
    }

    @Override
    public void setCookies(List<HttpCookie> cookies) {
        throw new UnsupportedOperationException(CANNOT_MODIFY_SERVLET_REQUEST);
    }

    @Override
    public void setExtensions(List<ExtensionConfig> configs) {
        throw new UnsupportedOperationException(CANNOT_MODIFY_SERVLET_REQUEST);
    }

    @Override
    public void setHeader(String name, List<String> values) {
        throw new UnsupportedOperationException(CANNOT_MODIFY_SERVLET_REQUEST);
    }

    @Override
    public void setHeader(String name, String value) {
        throw new UnsupportedOperationException(CANNOT_MODIFY_SERVLET_REQUEST);
    }

    @Override
    public void setHeaders(Map<String, List<String>> headers) {
        throw new UnsupportedOperationException(CANNOT_MODIFY_SERVLET_REQUEST);
    }

    @Override
    public void setHttpVersion(String httpVersion) {
        throw new UnsupportedOperationException(CANNOT_MODIFY_SERVLET_REQUEST);
    }

    @Override
    public void setMethod(String method) {
        throw new UnsupportedOperationException(CANNOT_MODIFY_SERVLET_REQUEST);
    }

    @Override
    public void setRequestURI(URI uri) {
        throw new UnsupportedOperationException(CANNOT_MODIFY_SERVLET_REQUEST);
    }

    public void setServletAttribute(String name, Object value) {
        this.request.setAttribute(name, value);
    }

    @Override
    public void setSession(Object session) {
        throw new UnsupportedOperationException(CANNOT_MODIFY_SERVLET_REQUEST);
    }

    @Override
    public void setSubProtocols(List<String> subProtocols) {
        throw new UnsupportedOperationException(CANNOT_MODIFY_SERVLET_REQUEST);
    }

    @Override
    public void setSubProtocols(String ... protocols) {
        throw new UnsupportedOperationException(CANNOT_MODIFY_SERVLET_REQUEST);
    }
}

