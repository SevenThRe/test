/*
 * Decompiled with CFR 0.152.
 */
package info.journeymap.shaded.org.javax.servlet;

import info.journeymap.shaded.org.javax.servlet.ServletException;
import info.journeymap.shaded.org.javax.servlet.ServletRequest;
import info.journeymap.shaded.org.javax.servlet.ServletResponse;
import java.io.IOException;

public interface RequestDispatcher {
    public static final String FORWARD_REQUEST_URI = "info.journeymap.shaded.org.javax.servlet.forward.request_uri";
    public static final String FORWARD_CONTEXT_PATH = "info.journeymap.shaded.org.javax.servlet.forward.context_path";
    public static final String FORWARD_MAPPING = "info.journeymap.shaded.org.javax.servlet.forward.mapping";
    public static final String FORWARD_PATH_INFO = "info.journeymap.shaded.org.javax.servlet.forward.path_info";
    public static final String FORWARD_SERVLET_PATH = "info.journeymap.shaded.org.javax.servlet.forward.servlet_path";
    public static final String FORWARD_QUERY_STRING = "info.journeymap.shaded.org.javax.servlet.forward.query_string";
    public static final String INCLUDE_REQUEST_URI = "info.journeymap.shaded.org.javax.servlet.include.request_uri";
    public static final String INCLUDE_CONTEXT_PATH = "info.journeymap.shaded.org.javax.servlet.include.context_path";
    public static final String INCLUDE_PATH_INFO = "info.journeymap.shaded.org.javax.servlet.include.path_info";
    public static final String INCLUDE_MAPPING = "info.journeymap.shaded.org.javax.servlet.include.mapping";
    public static final String INCLUDE_SERVLET_PATH = "info.journeymap.shaded.org.javax.servlet.include.servlet_path";
    public static final String INCLUDE_QUERY_STRING = "info.journeymap.shaded.org.javax.servlet.include.query_string";
    public static final String ERROR_EXCEPTION = "info.journeymap.shaded.org.javax.servlet.error.exception";
    public static final String ERROR_EXCEPTION_TYPE = "info.journeymap.shaded.org.javax.servlet.error.exception_type";
    public static final String ERROR_MESSAGE = "info.journeymap.shaded.org.javax.servlet.error.message";
    public static final String ERROR_REQUEST_URI = "info.journeymap.shaded.org.javax.servlet.error.request_uri";
    public static final String ERROR_SERVLET_NAME = "info.journeymap.shaded.org.javax.servlet.error.servlet_name";
    public static final String ERROR_STATUS_CODE = "info.journeymap.shaded.org.javax.servlet.error.status_code";

    public void forward(ServletRequest var1, ServletResponse var2) throws ServletException, IOException;

    public void include(ServletRequest var1, ServletResponse var2) throws ServletException, IOException;
}

