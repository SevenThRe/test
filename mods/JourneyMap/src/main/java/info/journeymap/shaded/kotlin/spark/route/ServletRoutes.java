/*
 * Decompiled with CFR 0.152.
 */
package info.journeymap.shaded.kotlin.spark.route;

import info.journeymap.shaded.kotlin.spark.route.Routes;

public final class ServletRoutes {
    private static Routes routes = null;

    private ServletRoutes() {
    }

    public static synchronized Routes get() {
        if (routes == null) {
            routes = new Routes();
        }
        return routes;
    }
}

