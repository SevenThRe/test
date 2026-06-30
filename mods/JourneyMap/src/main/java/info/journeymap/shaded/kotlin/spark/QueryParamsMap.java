/*
 * Decompiled with CFR 0.152.
 */
package info.journeymap.shaded.kotlin.spark;

import info.journeymap.shaded.org.javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class QueryParamsMap {
    private static final QueryParamsMap NULL = new NullQueryParamsMap();
    private static final Pattern KEY_PATTERN = Pattern.compile("\\A[\\[\\]]*([^\\[\\]]+)\\]*");
    private Map<String, QueryParamsMap> queryMap = new HashMap<String, QueryParamsMap>();
    private String[] values;

    public QueryParamsMap(HttpServletRequest request) {
        if (request == null) {
            throw new IllegalArgumentException("HttpServletRequest cannot be null.");
        }
        this.loadQueryString(request.getParameterMap());
    }

    protected QueryParamsMap() {
    }

    protected QueryParamsMap(String key, String ... values) {
        this.loadKeys(key, values);
    }

    protected QueryParamsMap(Map<String, String[]> params) {
        this.loadQueryString(params);
    }

    protected final void loadQueryString(Map<String, String[]> params) {
        for (Map.Entry<String, String[]> param : params.entrySet()) {
            this.loadKeys(param.getKey(), param.getValue());
        }
    }

    protected final void loadKeys(String key, String[] value) {
        String[] parsed = this.parseKey(key);
        if (parsed == null) {
            return;
        }
        if (!this.queryMap.containsKey(parsed[0])) {
            this.queryMap.put(parsed[0], new QueryParamsMap());
        }
        if (!parsed[1].isEmpty()) {
            this.queryMap.get(parsed[0]).loadKeys(parsed[1], value);
        } else {
            this.queryMap.get((Object)parsed[0]).values = (String[])value.clone();
        }
    }

    protected final String[] parseKey(String key) {
        Matcher m = KEY_PATTERN.matcher(key);
        if (m.find()) {
            return new String[]{QueryParamsMap.cleanKey(m.group()), key.substring(m.end())};
        }
        return null;
    }

    protected static final String cleanKey(String group) {
        if (group.startsWith("[")) {
            return group.substring(1, group.length() - 1);
        }
        return group;
    }

    public QueryParamsMap get(String ... keys) {
        QueryParamsMap ret = this;
        for (String key : keys) {
            ret = ret.queryMap.containsKey(key) ? ret.queryMap.get(key) : NULL;
        }
        return ret;
    }

    public String value() {
        if (this.hasValue()) {
            return this.values[0];
        }
        return null;
    }

    public String value(String ... keys) {
        return this.get(keys).value();
    }

    public boolean hasKeys() {
        return !this.queryMap.isEmpty();
    }

    public boolean hasKey(String key) {
        return this.queryMap.containsKey(key);
    }

    public boolean hasValue() {
        return this.values != null && this.values.length > 0;
    }

    public Boolean booleanValue() {
        return this.hasValue() ? Boolean.valueOf(this.value()) : null;
    }

    public Integer integerValue() {
        return this.hasValue() ? Integer.valueOf(this.value()) : null;
    }

    public Long longValue() {
        return this.hasValue() ? Long.valueOf(this.value()) : null;
    }

    public Float floatValue() {
        return this.hasValue() ? Float.valueOf(this.value()) : null;
    }

    public Double doubleValue() {
        return this.hasValue() ? Double.valueOf(this.value()) : null;
    }

    public String[] values() {
        return (String[])this.values.clone();
    }

    Map<String, QueryParamsMap> getQueryMap() {
        return this.queryMap;
    }

    String[] getValues() {
        return this.values;
    }

    public Map<String, String[]> toMap() {
        HashMap<String, String[]> map = new HashMap<String, String[]>();
        for (Map.Entry<String, QueryParamsMap> key : this.queryMap.entrySet()) {
            map.put(key.getKey(), key.getValue().values);
        }
        return map;
    }

    private static class NullQueryParamsMap
    extends QueryParamsMap {
    }
}

