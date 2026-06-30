/*
 * Decompiled with CFR 0.152.
 */
package org.eclipse.jetty.websocket.servlet;

import info.journeymap.shaded.org.javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import org.eclipse.jetty.websocket.api.UpgradeResponse;
import org.eclipse.jetty.websocket.api.extensions.ExtensionConfig;

public class ServletUpgradeResponse
implements UpgradeResponse {
    private HttpServletResponse response;
    private boolean extensionsNegotiated = false;
    private boolean subprotocolNegotiated = false;
    private Map<String, List<String>> headers = new TreeMap<String, List<String>>(String.CASE_INSENSITIVE_ORDER);
    private List<ExtensionConfig> extensions = new ArrayList<ExtensionConfig>();
    private boolean success = false;
    private int status;

    public ServletUpgradeResponse(HttpServletResponse response) {
        this.response = response;
    }

    @Override
    public void addHeader(String name, String value) {
        if (value != null) {
            List<String> values = this.headers.get(name);
            if (values == null) {
                values = new ArrayList<String>();
                this.headers.put(name, values);
            }
            values.add(value);
        }
    }

    @Override
    public void setHeader(String name, String value) {
        List<String> values;
        if (this.response != null) {
            this.response.setHeader(name, null);
        }
        if ((values = this.headers.get(name)) == null) {
            values = new ArrayList<String>();
            this.headers.put(name, values);
        } else {
            values.clear();
        }
        values.add(value);
    }

    public void complete() {
        if (this.response == null) {
            return;
        }
        TreeMap<String, Collection<String>> real = new TreeMap<String, Collection<String>>(String.CASE_INSENSITIVE_ORDER);
        for (String string : this.response.getHeaderNames()) {
            real.put(string, this.response.getHeaders(string));
        }
        for (Map.Entry entry : this.getHeaders().entrySet()) {
            for (String value : (List)entry.getValue()) {
                this.response.addHeader((String)entry.getKey(), value);
            }
        }
        for (Map.Entry entry : real.entrySet()) {
            String name = (String)entry.getKey();
            Collection prepend = (Collection)entry.getValue();
            List values = this.headers.getOrDefault(name, new ArrayList());
            values.addAll(0, prepend);
            this.headers.put(name, values);
        }
        this.status = this.response.getStatus();
        this.response = null;
    }

    @Override
    public String getAcceptedSubProtocol() {
        return this.getHeader("Sec-WebSocket-Protocol");
    }

    @Override
    public List<ExtensionConfig> getExtensions() {
        return this.extensions;
    }

    @Override
    public String getHeader(String name) {
        String value;
        if (this.response != null && (value = this.response.getHeader(name)) != null) {
            return value;
        }
        List<String> values = this.headers.get(name);
        if (values != null && !values.isEmpty()) {
            return values.get(0);
        }
        return null;
    }

    @Override
    public Set<String> getHeaderNames() {
        if (this.response == null) {
            return this.headers.keySet();
        }
        HashSet<String> h = new HashSet<String>(this.response.getHeaderNames());
        h.addAll(this.headers.keySet());
        return h;
    }

    @Override
    public Map<String, List<String>> getHeaders() {
        return this.headers;
    }

    @Override
    public List<String> getHeaders(String name) {
        if (this.response == null) {
            return this.headers.get(name);
        }
        ArrayList<String> values = new ArrayList<String>(this.response.getHeaders(name));
        values.addAll((Collection<String>)this.headers.get(name));
        return values.isEmpty() ? null : values;
    }

    @Override
    public int getStatusCode() {
        if (this.response != null) {
            return this.response.getStatus();
        }
        return this.status;
    }

    @Override
    public String getStatusReason() {
        throw new UnsupportedOperationException("Servlet's do not support Status Reason");
    }

    public boolean isCommitted() {
        if (this.response != null) {
            return this.response.isCommitted();
        }
        return true;
    }

    public boolean isExtensionsNegotiated() {
        return this.extensionsNegotiated;
    }

    public boolean isSubprotocolNegotiated() {
        return this.subprotocolNegotiated;
    }

    @Override
    public boolean isSuccess() {
        return this.success;
    }

    public void sendError(int statusCode, String message) throws IOException {
        this.setSuccess(false);
        HttpServletResponse r = this.response;
        this.complete();
        r.sendError(statusCode, message);
        r.flushBuffer();
    }

    @Override
    public void sendForbidden(String message) throws IOException {
        this.setSuccess(false);
        HttpServletResponse r = this.response;
        this.complete();
        r.sendError(403, message);
        r.flushBuffer();
    }

    @Override
    public void setAcceptedSubProtocol(String protocol) {
        this.response.setHeader("Sec-WebSocket-Protocol", protocol);
        this.subprotocolNegotiated = true;
    }

    @Override
    public void setExtensions(List<ExtensionConfig> configs) {
        this.extensions.clear();
        this.extensions.addAll(configs);
        String value = ExtensionConfig.toHeaderValue(configs);
        this.response.setHeader("Sec-WebSocket-Extensions", value);
        this.extensionsNegotiated = true;
    }

    @Override
    public void setStatusCode(int statusCode) {
        if (this.response != null) {
            this.response.setStatus(statusCode);
        }
    }

    @Override
    public void setStatusReason(String statusReason) {
        throw new UnsupportedOperationException("Servlet's do not support Status Reason");
    }

    @Override
    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String toString() {
        return String.format("r=%s s=%d h=%s", this.response, this.status, this.headers);
    }
}

