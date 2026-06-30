/*
 * Decompiled with CFR 0.152.
 */
package org.eclipse.jetty.websocket.api;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.eclipse.jetty.websocket.api.extensions.ExtensionConfig;

public interface UpgradeResponse {
    public void addHeader(String var1, String var2);

    public String getAcceptedSubProtocol();

    public List<ExtensionConfig> getExtensions();

    public String getHeader(String var1);

    public Set<String> getHeaderNames();

    public Map<String, List<String>> getHeaders();

    public List<String> getHeaders(String var1);

    public int getStatusCode();

    public String getStatusReason();

    public boolean isSuccess();

    public void sendForbidden(String var1) throws IOException;

    public void setAcceptedSubProtocol(String var1);

    public void setExtensions(List<ExtensionConfig> var1);

    public void setHeader(String var1, String var2);

    public void setStatusCode(int var1);

    public void setStatusReason(String var1);

    public void setSuccess(boolean var1);
}

