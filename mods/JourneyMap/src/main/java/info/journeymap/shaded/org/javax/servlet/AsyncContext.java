/*
 * Decompiled with CFR 0.152.
 */
package info.journeymap.shaded.org.javax.servlet;

import info.journeymap.shaded.org.javax.servlet.AsyncListener;
import info.journeymap.shaded.org.javax.servlet.ServletContext;
import info.journeymap.shaded.org.javax.servlet.ServletException;
import info.journeymap.shaded.org.javax.servlet.ServletRequest;
import info.journeymap.shaded.org.javax.servlet.ServletResponse;

public interface AsyncContext {
    public static final String ASYNC_REQUEST_URI = "info.journeymap.shaded.org.javax.servlet.async.request_uri";
    public static final String ASYNC_CONTEXT_PATH = "info.journeymap.shaded.org.javax.servlet.async.context_path";
    public static final String ASYNC_MAPPING = "info.journeymap.shaded.org.javax.servlet.async.mapping";
    public static final String ASYNC_PATH_INFO = "info.journeymap.shaded.org.javax.servlet.async.path_info";
    public static final String ASYNC_SERVLET_PATH = "info.journeymap.shaded.org.javax.servlet.async.servlet_path";
    public static final String ASYNC_QUERY_STRING = "info.journeymap.shaded.org.javax.servlet.async.query_string";

    public ServletRequest getRequest();

    public ServletResponse getResponse();

    public boolean hasOriginalRequestAndResponse();

    public void dispatch();

    public void dispatch(String var1);

    public void dispatch(ServletContext var1, String var2);

    public void complete();

    public void start(Runnable var1);

    public void addListener(AsyncListener var1);

    public void addListener(AsyncListener var1, ServletRequest var2, ServletResponse var3);

    public <T extends AsyncListener> T createListener(Class<T> var1) throws ServletException;

    public void setTimeout(long var1);

    public long getTimeout();
}

