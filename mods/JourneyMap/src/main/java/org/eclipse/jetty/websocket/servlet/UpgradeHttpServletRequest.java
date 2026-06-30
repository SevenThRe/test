/*
 * Decompiled with CFR 0.152.
 */
package org.eclipse.jetty.websocket.servlet;

import info.journeymap.shaded.org.javax.servlet.AsyncContext;
import info.journeymap.shaded.org.javax.servlet.DispatcherType;
import info.journeymap.shaded.org.javax.servlet.RequestDispatcher;
import info.journeymap.shaded.org.javax.servlet.ServletContext;
import info.journeymap.shaded.org.javax.servlet.ServletException;
import info.journeymap.shaded.org.javax.servlet.ServletInputStream;
import info.journeymap.shaded.org.javax.servlet.ServletRequest;
import info.journeymap.shaded.org.javax.servlet.ServletResponse;
import info.journeymap.shaded.org.javax.servlet.http.Cookie;
import info.journeymap.shaded.org.javax.servlet.http.HttpServletRequest;
import info.journeymap.shaded.org.javax.servlet.http.HttpServletResponse;
import info.journeymap.shaded.org.javax.servlet.http.HttpSession;
import info.journeymap.shaded.org.javax.servlet.http.HttpUpgradeHandler;
import info.journeymap.shaded.org.javax.servlet.http.Part;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.InetSocketAddress;
import java.security.Principal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.TreeMap;

public class UpgradeHttpServletRequest
implements HttpServletRequest {
    private static final String UNSUPPORTED_WITH_WEBSOCKET_UPGRADE = "Feature unsupported with a Upgraded to WebSocket HttpServletRequest";
    private HttpServletRequest request;
    private final ServletContext context;
    private final DispatcherType dispatcher;
    private final String method;
    private final String protocol;
    private final String scheme;
    private final boolean secure;
    private final String requestURI;
    private final StringBuffer requestURL;
    private final String pathInfo;
    private final String pathTranslated;
    private final String servletPath;
    private final String query;
    private final String authType;
    private final Cookie[] cookies;
    private final String remoteUser;
    private final Principal principal;
    private final Map<String, List<String>> headers = new TreeMap<String, List<String>>(String.CASE_INSENSITIVE_ORDER);
    private final Map<String, String[]> parameters = new TreeMap<String, String[]>(String.CASE_INSENSITIVE_ORDER);
    private final Map<String, Object> attributes = new HashMap<String, Object>(2);
    private final List<Locale> locales = new ArrayList<Locale>(2);
    private HttpSession session;
    private final InetSocketAddress localAddress;
    private final String localName;
    private final InetSocketAddress remoteAddress;
    private final String remoteName;
    private final InetSocketAddress serverAddress;

    public UpgradeHttpServletRequest(HttpServletRequest httpRequest) {
        this.request = httpRequest;
        this.context = httpRequest.getServletContext();
        this.dispatcher = httpRequest.getDispatcherType();
        this.method = httpRequest.getMethod();
        this.protocol = httpRequest.getProtocol();
        this.scheme = httpRequest.getScheme();
        this.secure = httpRequest.isSecure();
        this.requestURI = httpRequest.getRequestURI();
        this.requestURL = httpRequest.getRequestURL();
        this.pathInfo = httpRequest.getPathInfo();
        this.pathTranslated = httpRequest.getPathTranslated();
        this.servletPath = httpRequest.getServletPath();
        this.query = httpRequest.getQueryString();
        this.authType = httpRequest.getAuthType();
        this.cookies = this.request.getCookies();
        this.remoteUser = httpRequest.getRemoteUser();
        this.principal = httpRequest.getUserPrincipal();
        Enumeration<String> headerNames = httpRequest.getHeaderNames();
        while (headerNames.hasMoreElements()) {
            String name = headerNames.nextElement();
            this.headers.put(name, Collections.list(httpRequest.getHeaders(name)));
        }
        this.parameters.putAll(this.request.getParameterMap());
        Enumeration<String> attributeNames = httpRequest.getAttributeNames();
        while (attributeNames.hasMoreElements()) {
            String name = attributeNames.nextElement();
            this.attributes.put(name, httpRequest.getAttribute(name));
        }
        this.localAddress = InetSocketAddress.createUnresolved(httpRequest.getLocalAddr(), httpRequest.getLocalPort());
        this.localName = httpRequest.getLocalName();
        this.remoteAddress = InetSocketAddress.createUnresolved(httpRequest.getRemoteAddr(), httpRequest.getRemotePort());
        this.remoteName = httpRequest.getRemoteHost();
        this.serverAddress = InetSocketAddress.createUnresolved(httpRequest.getServerName(), httpRequest.getServerPort());
    }

    public HttpServletRequest getHttpServletRequest() {
        return this.request;
    }

    @Override
    public String getAuthType() {
        return this.authType;
    }

    @Override
    public Cookie[] getCookies() {
        return this.cookies;
    }

    @Override
    public String getHeader(String name) {
        List<String> values = this.headers.get(name);
        if (values == null || values.isEmpty()) {
            return null;
        }
        return values.get(0);
    }

    @Override
    public Enumeration<String> getHeaders(String name) {
        List<String> values = this.headers.get(name);
        if (values == null) {
            return Collections.emptyEnumeration();
        }
        return Collections.enumeration(values);
    }

    @Override
    public Enumeration<String> getHeaderNames() {
        return Collections.enumeration(this.headers.keySet());
    }

    public Map<String, List<String>> getHeaders() {
        return Collections.unmodifiableMap(this.headers);
    }

    @Override
    public long getDateHeader(String name) {
        throw new UnsupportedOperationException(UNSUPPORTED_WITH_WEBSOCKET_UPGRADE);
    }

    @Override
    public int getIntHeader(String name) {
        throw new UnsupportedOperationException(UNSUPPORTED_WITH_WEBSOCKET_UPGRADE);
    }

    @Override
    public String getMethod() {
        return this.method;
    }

    @Override
    public String getPathInfo() {
        return this.pathInfo;
    }

    @Override
    public String getPathTranslated() {
        return this.pathTranslated;
    }

    @Override
    public String getContextPath() {
        return this.context.getContextPath();
    }

    @Override
    public String getQueryString() {
        return this.query;
    }

    @Override
    public String getRemoteUser() {
        return this.remoteUser;
    }

    @Override
    public boolean isUserInRole(String role) {
        HttpServletRequest request = this.getHttpServletRequest();
        if (request != null) {
            return request.isUserInRole(role);
        }
        throw new UnsupportedOperationException(UNSUPPORTED_WITH_WEBSOCKET_UPGRADE);
    }

    @Override
    public Principal getUserPrincipal() {
        return this.principal;
    }

    @Override
    public String getRequestURI() {
        return this.requestURI;
    }

    @Override
    public StringBuffer getRequestURL() {
        return this.requestURL;
    }

    @Override
    public String getServletPath() {
        return this.servletPath;
    }

    @Override
    public HttpSession getSession(boolean create) {
        HttpServletRequest request = this.getHttpServletRequest();
        if (request != null) {
            this.session = request.getSession(create);
            return this.session;
        }
        return this.session;
    }

    @Override
    public HttpSession getSession() {
        return this.getSession(true);
    }

    @Override
    public String getRequestedSessionId() {
        throw new UnsupportedOperationException(UNSUPPORTED_WITH_WEBSOCKET_UPGRADE);
    }

    @Override
    public boolean isRequestedSessionIdValid() {
        throw new UnsupportedOperationException(UNSUPPORTED_WITH_WEBSOCKET_UPGRADE);
    }

    @Override
    public boolean isRequestedSessionIdFromCookie() {
        throw new UnsupportedOperationException(UNSUPPORTED_WITH_WEBSOCKET_UPGRADE);
    }

    @Override
    public boolean isRequestedSessionIdFromURL() {
        throw new UnsupportedOperationException(UNSUPPORTED_WITH_WEBSOCKET_UPGRADE);
    }

    @Override
    public boolean isRequestedSessionIdFromUrl() {
        throw new UnsupportedOperationException(UNSUPPORTED_WITH_WEBSOCKET_UPGRADE);
    }

    @Override
    public Object getAttribute(String name) {
        return this.attributes.get(name);
    }

    @Override
    public Enumeration<String> getAttributeNames() {
        return Collections.enumeration(this.attributes.keySet());
    }

    public Map<String, Object> getAttributes() {
        return Collections.unmodifiableMap(this.attributes);
    }

    @Override
    public String getParameter(String name) {
        String[] values = this.parameters.get(name);
        if (values == null || values.length == 0) {
            return null;
        }
        return values[0];
    }

    @Override
    public Enumeration<String> getParameterNames() {
        return Collections.enumeration(this.parameters.keySet());
    }

    @Override
    public String[] getParameterValues(String name) {
        return this.parameters.get(name);
    }

    @Override
    public Map<String, String[]> getParameterMap() {
        return this.parameters;
    }

    @Override
    public String getProtocol() {
        return this.protocol;
    }

    @Override
    public String getScheme() {
        return this.scheme;
    }

    @Override
    public String getServerName() {
        return this.serverAddress.getHostString();
    }

    @Override
    public int getServerPort() {
        return this.serverAddress.getPort();
    }

    @Override
    public String getRemoteAddr() {
        return this.remoteAddress.getHostString();
    }

    @Override
    public int getRemotePort() {
        return this.remoteAddress.getPort();
    }

    @Override
    public String getRemoteHost() {
        return this.remoteName;
    }

    @Override
    public void setAttribute(String name, Object value) {
        HttpServletRequest request = this.getHttpServletRequest();
        if (request != null) {
            request.setAttribute(name, value);
        }
        this.attributes.put(name, value);
    }

    @Override
    public void removeAttribute(String name) {
        HttpServletRequest request = this.getHttpServletRequest();
        if (request != null) {
            request.removeAttribute(name);
        }
        this.attributes.remove(name);
    }

    @Override
    public Locale getLocale() {
        if (this.locales.isEmpty()) {
            return Locale.getDefault();
        }
        return this.locales.get(0);
    }

    @Override
    public Enumeration<Locale> getLocales() {
        return Collections.enumeration(this.locales);
    }

    @Override
    public boolean isSecure() {
        return this.secure;
    }

    @Override
    public String getRealPath(String path) {
        return this.context.getRealPath(path);
    }

    @Override
    public String getLocalName() {
        return this.localName;
    }

    @Override
    public String getLocalAddr() {
        return this.localAddress.getHostString();
    }

    @Override
    public int getLocalPort() {
        return this.localAddress.getPort();
    }

    @Override
    public ServletContext getServletContext() {
        return this.context;
    }

    @Override
    public DispatcherType getDispatcherType() {
        return this.dispatcher;
    }

    @Override
    public boolean authenticate(HttpServletResponse response) throws IOException, ServletException {
        throw new UnsupportedOperationException(UNSUPPORTED_WITH_WEBSOCKET_UPGRADE);
    }

    @Override
    public String changeSessionId() {
        throw new UnsupportedOperationException(UNSUPPORTED_WITH_WEBSOCKET_UPGRADE);
    }

    @Override
    public AsyncContext getAsyncContext() {
        throw new UnsupportedOperationException(UNSUPPORTED_WITH_WEBSOCKET_UPGRADE);
    }

    @Override
    public String getCharacterEncoding() {
        throw new UnsupportedOperationException(UNSUPPORTED_WITH_WEBSOCKET_UPGRADE);
    }

    @Override
    public int getContentLength() {
        throw new UnsupportedOperationException(UNSUPPORTED_WITH_WEBSOCKET_UPGRADE);
    }

    @Override
    public long getContentLengthLong() {
        throw new UnsupportedOperationException(UNSUPPORTED_WITH_WEBSOCKET_UPGRADE);
    }

    @Override
    public String getContentType() {
        throw new UnsupportedOperationException(UNSUPPORTED_WITH_WEBSOCKET_UPGRADE);
    }

    @Override
    public ServletInputStream getInputStream() throws IOException {
        throw new UnsupportedOperationException(UNSUPPORTED_WITH_WEBSOCKET_UPGRADE);
    }

    @Override
    public Part getPart(String name) throws IOException, ServletException {
        throw new UnsupportedOperationException(UNSUPPORTED_WITH_WEBSOCKET_UPGRADE);
    }

    @Override
    public Collection<Part> getParts() throws IOException, ServletException {
        throw new UnsupportedOperationException(UNSUPPORTED_WITH_WEBSOCKET_UPGRADE);
    }

    @Override
    public BufferedReader getReader() throws IOException {
        throw new UnsupportedOperationException(UNSUPPORTED_WITH_WEBSOCKET_UPGRADE);
    }

    @Override
    public RequestDispatcher getRequestDispatcher(String path) {
        throw new UnsupportedOperationException(UNSUPPORTED_WITH_WEBSOCKET_UPGRADE);
    }

    @Override
    public boolean isAsyncStarted() {
        return false;
    }

    @Override
    public boolean isAsyncSupported() {
        return false;
    }

    @Override
    public void login(String username, String password) throws ServletException {
        throw new UnsupportedOperationException(UNSUPPORTED_WITH_WEBSOCKET_UPGRADE);
    }

    @Override
    public void logout() throws ServletException {
        throw new UnsupportedOperationException(UNSUPPORTED_WITH_WEBSOCKET_UPGRADE);
    }

    @Override
    public void setCharacterEncoding(String enc) throws UnsupportedEncodingException {
        throw new UnsupportedOperationException(UNSUPPORTED_WITH_WEBSOCKET_UPGRADE);
    }

    @Override
    public AsyncContext startAsync() throws IllegalStateException {
        throw new UnsupportedOperationException(UNSUPPORTED_WITH_WEBSOCKET_UPGRADE);
    }

    @Override
    public AsyncContext startAsync(ServletRequest servletRequest, ServletResponse servletResponse) throws IllegalStateException {
        throw new UnsupportedOperationException(UNSUPPORTED_WITH_WEBSOCKET_UPGRADE);
    }

    @Override
    public <T extends HttpUpgradeHandler> T upgrade(Class<T> handlerClass) throws IOException, ServletException {
        throw new UnsupportedOperationException(UNSUPPORTED_WITH_WEBSOCKET_UPGRADE);
    }

    public void complete() {
        this.request = null;
    }
}

