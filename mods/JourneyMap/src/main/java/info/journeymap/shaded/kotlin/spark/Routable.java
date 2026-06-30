/*
 * Decompiled with CFR 0.152.
 */
package info.journeymap.shaded.kotlin.spark;

import info.journeymap.shaded.kotlin.spark.Filter;
import info.journeymap.shaded.kotlin.spark.FilterImpl;
import info.journeymap.shaded.kotlin.spark.ResponseTransformer;
import info.journeymap.shaded.kotlin.spark.ResponseTransformerRouteImpl;
import info.journeymap.shaded.kotlin.spark.Route;
import info.journeymap.shaded.kotlin.spark.RouteImpl;
import info.journeymap.shaded.kotlin.spark.TemplateEngine;
import info.journeymap.shaded.kotlin.spark.TemplateViewRoute;
import info.journeymap.shaded.kotlin.spark.TemplateViewRouteImpl;
import info.journeymap.shaded.kotlin.spark.route.HttpMethod;

abstract class Routable {
    Routable() {
    }

    protected abstract void addRoute(HttpMethod var1, RouteImpl var2);

    @Deprecated
    protected abstract void addRoute(String var1, RouteImpl var2);

    protected abstract void addFilter(HttpMethod var1, FilterImpl var2);

    @Deprecated
    protected abstract void addFilter(String var1, FilterImpl var2);

    public void get(String path, Route route) {
        this.addRoute(HttpMethod.get, RouteImpl.create(path, route));
    }

    public void post(String path, Route route) {
        this.addRoute(HttpMethod.post, RouteImpl.create(path, route));
    }

    public void put(String path, Route route) {
        this.addRoute(HttpMethod.put, RouteImpl.create(path, route));
    }

    public void patch(String path, Route route) {
        this.addRoute(HttpMethod.patch, RouteImpl.create(path, route));
    }

    public void delete(String path, Route route) {
        this.addRoute(HttpMethod.delete, RouteImpl.create(path, route));
    }

    public void head(String path, Route route) {
        this.addRoute(HttpMethod.head, RouteImpl.create(path, route));
    }

    public void trace(String path, Route route) {
        this.addRoute(HttpMethod.trace, RouteImpl.create(path, route));
    }

    public void connect(String path, Route route) {
        this.addRoute(HttpMethod.connect, RouteImpl.create(path, route));
    }

    public void options(String path, Route route) {
        this.addRoute(HttpMethod.options, RouteImpl.create(path, route));
    }

    public void before(String path, Filter filter2) {
        this.addFilter(HttpMethod.before, FilterImpl.create(path, filter2));
    }

    public void after(String path, Filter filter2) {
        this.addFilter(HttpMethod.after, FilterImpl.create(path, filter2));
    }

    public void get(String path, String acceptType, Route route) {
        this.addRoute(HttpMethod.get, RouteImpl.create(path, acceptType, route));
    }

    public void post(String path, String acceptType, Route route) {
        this.addRoute(HttpMethod.post, RouteImpl.create(path, acceptType, route));
    }

    public void put(String path, String acceptType, Route route) {
        this.addRoute(HttpMethod.put, RouteImpl.create(path, acceptType, route));
    }

    public void patch(String path, String acceptType, Route route) {
        this.addRoute(HttpMethod.patch, RouteImpl.create(path, acceptType, route));
    }

    public void delete(String path, String acceptType, Route route) {
        this.addRoute(HttpMethod.delete, RouteImpl.create(path, acceptType, route));
    }

    public void head(String path, String acceptType, Route route) {
        this.addRoute(HttpMethod.head, RouteImpl.create(path, acceptType, route));
    }

    public void trace(String path, String acceptType, Route route) {
        this.addRoute(HttpMethod.trace, RouteImpl.create(path, acceptType, route));
    }

    public void connect(String path, String acceptType, Route route) {
        this.addRoute(HttpMethod.connect, RouteImpl.create(path, acceptType, route));
    }

    public void options(String path, String acceptType, Route route) {
        this.addRoute(HttpMethod.options, RouteImpl.create(path, acceptType, route));
    }

    public void before(Filter filter2) {
        this.addFilter(HttpMethod.before, FilterImpl.create("+/*paths", filter2));
    }

    public void after(Filter filter2) {
        this.addFilter(HttpMethod.after, FilterImpl.create("+/*paths", filter2));
    }

    public void before(String path, String acceptType, Filter filter2) {
        this.addFilter(HttpMethod.before, FilterImpl.create(path, acceptType, filter2));
    }

    public void after(String path, String acceptType, Filter filter2) {
        this.addFilter(HttpMethod.after, FilterImpl.create(path, acceptType, filter2));
    }

    public void afterAfter(Filter filter2) {
        this.addFilter(HttpMethod.afterafter, FilterImpl.create("+/*paths", filter2));
    }

    public void afterAfter(String path, Filter filter2) {
        this.addFilter(HttpMethod.afterafter, FilterImpl.create(path, filter2));
    }

    public void get(String path, TemplateViewRoute route, TemplateEngine engine) {
        this.addRoute(HttpMethod.get, (RouteImpl)TemplateViewRouteImpl.create(path, route, engine));
    }

    public void get(String path, String acceptType, TemplateViewRoute route, TemplateEngine engine) {
        this.addRoute(HttpMethod.get, (RouteImpl)TemplateViewRouteImpl.create(path, acceptType, route, engine));
    }

    public void post(String path, TemplateViewRoute route, TemplateEngine engine) {
        this.addRoute(HttpMethod.post, (RouteImpl)TemplateViewRouteImpl.create(path, route, engine));
    }

    public void post(String path, String acceptType, TemplateViewRoute route, TemplateEngine engine) {
        this.addRoute(HttpMethod.post, (RouteImpl)TemplateViewRouteImpl.create(path, acceptType, route, engine));
    }

    public void put(String path, TemplateViewRoute route, TemplateEngine engine) {
        this.addRoute(HttpMethod.put, (RouteImpl)TemplateViewRouteImpl.create(path, route, engine));
    }

    public void put(String path, String acceptType, TemplateViewRoute route, TemplateEngine engine) {
        this.addRoute(HttpMethod.put, (RouteImpl)TemplateViewRouteImpl.create(path, acceptType, route, engine));
    }

    public void delete(String path, TemplateViewRoute route, TemplateEngine engine) {
        this.addRoute(HttpMethod.delete, (RouteImpl)TemplateViewRouteImpl.create(path, route, engine));
    }

    public void delete(String path, String acceptType, TemplateViewRoute route, TemplateEngine engine) {
        this.addRoute(HttpMethod.delete, (RouteImpl)TemplateViewRouteImpl.create(path, acceptType, route, engine));
    }

    public void patch(String path, TemplateViewRoute route, TemplateEngine engine) {
        this.addRoute(HttpMethod.patch, (RouteImpl)TemplateViewRouteImpl.create(path, route, engine));
    }

    public void patch(String path, String acceptType, TemplateViewRoute route, TemplateEngine engine) {
        this.addRoute(HttpMethod.patch, (RouteImpl)TemplateViewRouteImpl.create(path, acceptType, route, engine));
    }

    public void head(String path, TemplateViewRoute route, TemplateEngine engine) {
        this.addRoute(HttpMethod.head, (RouteImpl)TemplateViewRouteImpl.create(path, route, engine));
    }

    public void head(String path, String acceptType, TemplateViewRoute route, TemplateEngine engine) {
        this.addRoute(HttpMethod.head, (RouteImpl)TemplateViewRouteImpl.create(path, acceptType, route, engine));
    }

    public void trace(String path, TemplateViewRoute route, TemplateEngine engine) {
        this.addRoute(HttpMethod.trace, (RouteImpl)TemplateViewRouteImpl.create(path, route, engine));
    }

    public void trace(String path, String acceptType, TemplateViewRoute route, TemplateEngine engine) {
        this.addRoute(HttpMethod.trace, (RouteImpl)TemplateViewRouteImpl.create(path, acceptType, route, engine));
    }

    public void connect(String path, TemplateViewRoute route, TemplateEngine engine) {
        this.addRoute(HttpMethod.connect, (RouteImpl)TemplateViewRouteImpl.create(path, route, engine));
    }

    public void connect(String path, String acceptType, TemplateViewRoute route, TemplateEngine engine) {
        this.addRoute(HttpMethod.connect, (RouteImpl)TemplateViewRouteImpl.create(path, acceptType, route, engine));
    }

    public void options(String path, TemplateViewRoute route, TemplateEngine engine) {
        this.addRoute(HttpMethod.options, (RouteImpl)TemplateViewRouteImpl.create(path, route, engine));
    }

    public void options(String path, String acceptType, TemplateViewRoute route, TemplateEngine engine) {
        this.addRoute(HttpMethod.options, (RouteImpl)TemplateViewRouteImpl.create(path, acceptType, route, engine));
    }

    public void get(String path, Route route, ResponseTransformer transformer) {
        this.addRoute(HttpMethod.get, (RouteImpl)ResponseTransformerRouteImpl.create(path, route, transformer));
    }

    public void get(String path, String acceptType, Route route, ResponseTransformer transformer) {
        this.addRoute(HttpMethod.get, (RouteImpl)ResponseTransformerRouteImpl.create(path, acceptType, route, transformer));
    }

    public void post(String path, Route route, ResponseTransformer transformer) {
        this.addRoute(HttpMethod.post, (RouteImpl)ResponseTransformerRouteImpl.create(path, route, transformer));
    }

    public void post(String path, String acceptType, Route route, ResponseTransformer transformer) {
        this.addRoute(HttpMethod.post, (RouteImpl)ResponseTransformerRouteImpl.create(path, acceptType, route, transformer));
    }

    public void put(String path, Route route, ResponseTransformer transformer) {
        this.addRoute(HttpMethod.put, (RouteImpl)ResponseTransformerRouteImpl.create(path, route, transformer));
    }

    public void put(String path, String acceptType, Route route, ResponseTransformer transformer) {
        this.addRoute(HttpMethod.put, (RouteImpl)ResponseTransformerRouteImpl.create(path, acceptType, route, transformer));
    }

    public void delete(String path, Route route, ResponseTransformer transformer) {
        this.addRoute(HttpMethod.delete, (RouteImpl)ResponseTransformerRouteImpl.create(path, route, transformer));
    }

    public void delete(String path, String acceptType, Route route, ResponseTransformer transformer) {
        this.addRoute(HttpMethod.delete, (RouteImpl)ResponseTransformerRouteImpl.create(path, acceptType, route, transformer));
    }

    public void head(String path, Route route, ResponseTransformer transformer) {
        this.addRoute(HttpMethod.head, (RouteImpl)ResponseTransformerRouteImpl.create(path, route, transformer));
    }

    public void head(String path, String acceptType, Route route, ResponseTransformer transformer) {
        this.addRoute(HttpMethod.head, (RouteImpl)ResponseTransformerRouteImpl.create(path, acceptType, route, transformer));
    }

    public void connect(String path, Route route, ResponseTransformer transformer) {
        this.addRoute(HttpMethod.connect, (RouteImpl)ResponseTransformerRouteImpl.create(path, route, transformer));
    }

    public void connect(String path, String acceptType, Route route, ResponseTransformer transformer) {
        this.addRoute(HttpMethod.connect, (RouteImpl)ResponseTransformerRouteImpl.create(path, acceptType, route, transformer));
    }

    public void trace(String path, Route route, ResponseTransformer transformer) {
        this.addRoute(HttpMethod.trace, (RouteImpl)ResponseTransformerRouteImpl.create(path, route, transformer));
    }

    public void trace(String path, String acceptType, Route route, ResponseTransformer transformer) {
        this.addRoute(HttpMethod.trace, (RouteImpl)ResponseTransformerRouteImpl.create(path, acceptType, route, transformer));
    }

    public void options(String path, Route route, ResponseTransformer transformer) {
        this.addRoute(HttpMethod.options, (RouteImpl)ResponseTransformerRouteImpl.create(path, route, transformer));
    }

    public void options(String path, String acceptType, Route route, ResponseTransformer transformer) {
        this.addRoute(HttpMethod.options, (RouteImpl)ResponseTransformerRouteImpl.create(path, acceptType, route, transformer));
    }

    public void patch(String path, Route route, ResponseTransformer transformer) {
        this.addRoute(HttpMethod.patch, (RouteImpl)ResponseTransformerRouteImpl.create(path, route, transformer));
    }

    public void patch(String path, String acceptType, Route route, ResponseTransformer transformer) {
        this.addRoute(HttpMethod.patch, (RouteImpl)ResponseTransformerRouteImpl.create(path, acceptType, route, transformer));
    }
}

