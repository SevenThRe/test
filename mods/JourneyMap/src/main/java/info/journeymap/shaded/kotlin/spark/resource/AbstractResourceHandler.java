/*
 * Decompiled with CFR 0.152.
 */
package info.journeymap.shaded.kotlin.spark.resource;

import info.journeymap.shaded.kotlin.spark.resource.AbstractFileResolvingResource;
import info.journeymap.shaded.org.javax.servlet.http.HttpServletRequest;
import java.net.MalformedURLException;

public abstract class AbstractResourceHandler {
    protected static final String SLASH = "/";

    public AbstractFileResolvingResource getResource(HttpServletRequest request) throws MalformedURLException {
        String pathInfo;
        String servletPath;
        boolean included;
        boolean bl = included = request.getAttribute("info.journeymap.shaded.org.javax.servlet.include.request_uri") != null;
        if (included) {
            servletPath = (String)request.getAttribute("info.journeymap.shaded.org.javax.servlet.include.servlet_path");
            pathInfo = (String)request.getAttribute("info.journeymap.shaded.org.javax.servlet.include.path_info");
            if (servletPath == null && pathInfo == null) {
                servletPath = request.getServletPath();
                pathInfo = request.getPathInfo();
            }
        } else {
            servletPath = request.getServletPath();
            pathInfo = request.getPathInfo();
        }
        String pathInContext = AbstractResourceHandler.addPaths(servletPath, pathInfo);
        return this.getResource(pathInContext);
    }

    protected abstract AbstractFileResolvingResource getResource(String var1) throws MalformedURLException;

    public static String addPaths(String segment1, String segment2) {
        if (segment1 == null || segment1.length() == 0) {
            if (segment1 != null && segment2 == null) {
                return segment1;
            }
            return segment2;
        }
        if (segment2 == null || segment2.length() == 0) {
            return segment1;
        }
        int split = segment1.indexOf(59);
        if (split < 0) {
            split = segment1.indexOf(63);
        }
        if (split == 0) {
            return segment2 + segment1;
        }
        if (split < 0) {
            split = segment1.length();
        }
        StringBuilder buf = new StringBuilder(segment1.length() + segment2.length() + 2);
        buf.append(segment1);
        if (buf.charAt(split - 1) == '/') {
            if (segment2.startsWith(SLASH)) {
                buf.deleteCharAt(split - 1);
                buf.insert(split - 1, segment2);
            } else {
                buf.insert(split, segment2);
            }
        } else if (segment2.startsWith(SLASH)) {
            buf.insert(split, segment2);
        } else {
            buf.insert(split, '/');
            buf.insert(split + 1, segment2);
        }
        return buf.toString();
    }
}

