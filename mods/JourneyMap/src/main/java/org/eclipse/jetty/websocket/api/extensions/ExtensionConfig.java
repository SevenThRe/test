/*
 * Decompiled with CFR 0.152.
 */
package org.eclipse.jetty.websocket.api.extensions;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.eclipse.jetty.websocket.api.util.QuoteUtil;

public class ExtensionConfig {
    private final String name;
    private final Map<String, String> parameters;

    public static ExtensionConfig parse(String parameterizedName) {
        return new ExtensionConfig(parameterizedName);
    }

    public static List<ExtensionConfig> parseEnum(Enumeration<String> valuesEnum) {
        ArrayList<ExtensionConfig> configs = new ArrayList<ExtensionConfig>();
        if (valuesEnum != null) {
            while (valuesEnum.hasMoreElements()) {
                Iterator<String> extTokenIter = QuoteUtil.splitAt(valuesEnum.nextElement(), ",");
                while (extTokenIter.hasNext()) {
                    String extToken = extTokenIter.next();
                    configs.add(ExtensionConfig.parse(extToken));
                }
            }
        }
        return configs;
    }

    public static List<ExtensionConfig> parseList(String ... rawSecWebSocketExtensions) {
        ArrayList<ExtensionConfig> configs = new ArrayList<ExtensionConfig>();
        for (String rawValue : rawSecWebSocketExtensions) {
            Iterator<String> extTokenIter = QuoteUtil.splitAt(rawValue, ",");
            while (extTokenIter.hasNext()) {
                String extToken = extTokenIter.next();
                configs.add(ExtensionConfig.parse(extToken));
            }
        }
        return configs;
    }

    public static String toHeaderValue(List<ExtensionConfig> configs) {
        if (configs == null || configs.isEmpty()) {
            return null;
        }
        StringBuilder parameters = new StringBuilder();
        boolean needsDelim = false;
        for (ExtensionConfig ext : configs) {
            if (needsDelim) {
                parameters.append(", ");
            }
            parameters.append(ext.getParameterizedName());
            needsDelim = true;
        }
        return parameters.toString();
    }

    public ExtensionConfig(ExtensionConfig copy) {
        this.name = copy.name;
        this.parameters = new HashMap<String, String>();
        this.parameters.putAll(copy.parameters);
    }

    public ExtensionConfig(String parameterizedName) {
        Iterator<String> extListIter = QuoteUtil.splitAt(parameterizedName, ";");
        this.name = extListIter.next();
        this.parameters = new HashMap<String, String>();
        while (extListIter.hasNext()) {
            String extParam = extListIter.next();
            Iterator<String> extParamIter = QuoteUtil.splitAt(extParam, "=");
            String key = extParamIter.next().trim();
            String value = null;
            if (extParamIter.hasNext()) {
                value = extParamIter.next();
            }
            this.parameters.put(key, value);
        }
    }

    public String getName() {
        return this.name;
    }

    public final int getParameter(String key, int defValue) {
        String val = this.parameters.get(key);
        if (val == null) {
            return defValue;
        }
        return Integer.valueOf(val);
    }

    public final String getParameter(String key, String defValue) {
        String val = this.parameters.get(key);
        if (val == null) {
            return defValue;
        }
        return val;
    }

    public final String getParameterizedName() {
        StringBuilder str = new StringBuilder();
        str.append(this.name);
        for (String param : this.parameters.keySet()) {
            str.append(';');
            str.append(param);
            String value = this.parameters.get(param);
            if (value == null) continue;
            str.append('=');
            QuoteUtil.quoteIfNeeded(str, value, ";=");
        }
        return str.toString();
    }

    public final Set<String> getParameterKeys() {
        return this.parameters.keySet();
    }

    public final Map<String, String> getParameters() {
        return this.parameters;
    }

    public final void init(ExtensionConfig other) {
        this.parameters.clear();
        this.parameters.putAll(other.parameters);
    }

    public final void setParameter(String key) {
        this.parameters.put(key, null);
    }

    public final void setParameter(String key, int value) {
        this.parameters.put(key, Integer.toString(value));
    }

    public final void setParameter(String key, String value) {
        this.parameters.put(key, value);
    }

    public String toString() {
        return this.getParameterizedName();
    }
}

