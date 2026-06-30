/*
 * Decompiled with CFR 0.152.
 */
package info.journeymap.shaded.kotlin.spark.route;

import info.journeymap.shaded.kotlin.spark.route.HttpMethod;
import info.journeymap.shaded.kotlin.spark.utils.SparkUtils;
import java.util.List;

class RouteEntry {
    HttpMethod httpMethod;
    String path;
    String acceptedType;
    Object target;

    RouteEntry() {
    }

    RouteEntry(RouteEntry entry) {
        this.httpMethod = entry.httpMethod;
        this.path = entry.path;
        this.acceptedType = entry.acceptedType;
        this.target = entry.target;
    }

    boolean matches(HttpMethod httpMethod, String path) {
        if ((httpMethod == HttpMethod.before || httpMethod == HttpMethod.after || httpMethod == HttpMethod.afterafter) && this.httpMethod == httpMethod && this.path.equals("+/*paths")) {
            return true;
        }
        boolean match = false;
        if (this.httpMethod == httpMethod) {
            match = this.matchPath(path);
        }
        return match;
    }

    private boolean matchPath(String path) {
        int pathSize;
        if (!this.path.endsWith("*") && (path.endsWith("/") && !this.path.endsWith("/") || this.path.endsWith("/") && !path.endsWith("/"))) {
            return false;
        }
        if (this.path.equals(path)) {
            return true;
        }
        List<String> thisPathList = SparkUtils.convertRouteToList(this.path);
        List<String> pathList = SparkUtils.convertRouteToList(path);
        int thisPathSize = thisPathList.size();
        if (thisPathSize == (pathSize = pathList.size())) {
            for (int i = 0; i < thisPathSize; ++i) {
                String thisPathPart = thisPathList.get(i);
                String pathPart = pathList.get(i);
                if (i == thisPathSize - 1 && thisPathPart.equals("*") && this.path.endsWith("*")) {
                    return true;
                }
                if (thisPathPart.startsWith(":") || thisPathPart.equals(pathPart) || thisPathPart.equals("*")) continue;
                return false;
            }
            return true;
        }
        if (this.path.endsWith("*")) {
            if (pathSize == thisPathSize - 1 && path.endsWith("/")) {
                pathList.add("");
                pathList.add("");
                pathSize += 2;
            }
            if (thisPathSize < pathSize) {
                for (int i = 0; i < thisPathSize; ++i) {
                    String thisPathPart = thisPathList.get(i);
                    String pathPart = pathList.get(i);
                    if (thisPathPart.equals("*") && i == thisPathSize - 1 && this.path.endsWith("*")) {
                        return true;
                    }
                    if (thisPathPart.startsWith(":") || thisPathPart.equals(pathPart) || thisPathPart.equals("*")) continue;
                    return false;
                }
                return true;
            }
        }
        return false;
    }

    public String toString() {
        return this.httpMethod.name() + ", " + this.path + ", " + this.target;
    }
}

