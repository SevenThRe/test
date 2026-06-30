/*
 * Decompiled with CFR 0.152.
 */
package info.journeymap.shaded.kotlin.spark.route;

import java.util.HashMap;

public enum HttpMethod {
    get,
    post,
    put,
    patch,
    delete,
    head,
    trace,
    connect,
    options,
    before,
    after,
    afterafter,
    unsupported;

    private static HashMap<String, HttpMethod> methods;

    public static HttpMethod get(String methodStr) {
        HttpMethod method = methods.get(methodStr);
        return method != null ? method : unsupported;
    }

    static {
        methods = new HashMap();
        for (HttpMethod method : HttpMethod.values()) {
            methods.put(method.toString(), method);
        }
    }
}

