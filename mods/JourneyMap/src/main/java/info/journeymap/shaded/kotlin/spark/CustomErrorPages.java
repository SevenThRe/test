/*
 * Decompiled with CFR 0.152.
 */
package info.journeymap.shaded.kotlin.spark;

import info.journeymap.shaded.kotlin.spark.Request;
import info.journeymap.shaded.kotlin.spark.Response;
import info.journeymap.shaded.kotlin.spark.Route;
import info.journeymap.shaded.org.slf4j.Logger;
import info.journeymap.shaded.org.slf4j.LoggerFactory;
import java.util.HashMap;

public class CustomErrorPages {
    private static final Logger LOG = LoggerFactory.getLogger(CustomErrorPages.class);
    public static final String NOT_FOUND = "<html><body><h2>404 Not found</h2></body></html>";
    public static final String INTERNAL_ERROR = "<html><body><h2>500 Internal Server Error</h2></body></html>";
    private final HashMap<Integer, Object> customPages = new HashMap();
    private final HashMap<Integer, String> defaultPages = new HashMap();

    public static boolean existsFor(int status) {
        return CustomErrorPages.getInstance().customPages.containsKey(status);
    }

    public static Object getFor(int status, Request request, Response response) {
        Object customRenderer = CustomErrorPages.getInstance().customPages.get(status);
        Object customPage = CustomErrorPages.getInstance().getDefaultFor(status);
        if (customRenderer instanceof String) {
            customPage = customRenderer;
        } else if (customRenderer instanceof Route) {
            try {
                customPage = ((Route)customRenderer).handle(request, response);
            }
            catch (Exception e) {
                LOG.warn("Custom error page handler for status code {} has thrown an exception: {}. Using default page instead.", (Object)status, (Object)e.getMessage());
            }
        }
        return customPage;
    }

    public String getDefaultFor(int status) {
        String defaultPage = this.defaultPages.get(status);
        return defaultPage != null ? defaultPage : "<html><body><h2>HTTP Status " + status + "</h2></body></html>";
    }

    static void add(int status, String page) {
        CustomErrorPages.getInstance().customPages.put(status, page);
    }

    static void add(int status, Route route) {
        CustomErrorPages.getInstance().customPages.put(status, route);
    }

    private CustomErrorPages() {
        this.defaultPages.put(404, NOT_FOUND);
        this.defaultPages.put(500, INTERNAL_ERROR);
    }

    private static CustomErrorPages getInstance() {
        return SingletonHolder.INSTANCE;
    }

    private static class SingletonHolder {
        private static final CustomErrorPages INSTANCE = new CustomErrorPages();

        private SingletonHolder() {
        }
    }
}

