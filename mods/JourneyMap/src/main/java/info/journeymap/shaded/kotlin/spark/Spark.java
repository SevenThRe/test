/*
 * Decompiled with CFR 0.152.
 */
package info.journeymap.shaded.kotlin.spark;

import info.journeymap.shaded.kotlin.spark.ExceptionHandler;
import info.journeymap.shaded.kotlin.spark.Filter;
import info.journeymap.shaded.kotlin.spark.HaltException;
import info.journeymap.shaded.kotlin.spark.ModelAndView;
import info.journeymap.shaded.kotlin.spark.Redirect;
import info.journeymap.shaded.kotlin.spark.ResponseTransformer;
import info.journeymap.shaded.kotlin.spark.Route;
import info.journeymap.shaded.kotlin.spark.RouteGroup;
import info.journeymap.shaded.kotlin.spark.Service;
import info.journeymap.shaded.kotlin.spark.TemplateEngine;
import info.journeymap.shaded.kotlin.spark.TemplateViewRoute;
import java.util.function.Consumer;

public class Spark {
    public static final Redirect redirect = Spark.getInstance().redirect;
    public static final Service.StaticFiles staticFiles = Spark.getInstance().staticFiles;

    protected Spark() {
    }

    private static Service getInstance() {
        return SingletonHolder.INSTANCE;
    }

    public static void path(String path, RouteGroup routeGroup) {
        Spark.getInstance().path(path, routeGroup);
    }

    public static void get(String path, Route route) {
        Spark.getInstance().get(path, route);
    }

    public static void post(String path, Route route) {
        Spark.getInstance().post(path, route);
    }

    public static void put(String path, Route route) {
        Spark.getInstance().put(path, route);
    }

    public static void patch(String path, Route route) {
        Spark.getInstance().patch(path, route);
    }

    public static void delete(String path, Route route) {
        Spark.getInstance().delete(path, route);
    }

    public static void head(String path, Route route) {
        Spark.getInstance().head(path, route);
    }

    public static void trace(String path, Route route) {
        Spark.getInstance().trace(path, route);
    }

    public static void connect(String path, Route route) {
        Spark.getInstance().connect(path, route);
    }

    public static void options(String path, Route route) {
        Spark.getInstance().options(path, route);
    }

    public static void before(String path, Filter filter2) {
        Spark.getInstance().before(path, filter2);
    }

    public static void before(String path, Filter ... filters) {
        for (Filter filter2 : filters) {
            Spark.getInstance().before(path, filter2);
        }
    }

    public static void after(String path, Filter filter2) {
        Spark.getInstance().after(path, filter2);
    }

    public static void after(String path, Filter ... filters) {
        for (Filter filter2 : filters) {
            Spark.getInstance().after(path, filter2);
        }
    }

    public static void get(String path, String acceptType, Route route) {
        Spark.getInstance().get(path, acceptType, route);
    }

    public static void post(String path, String acceptType, Route route) {
        Spark.getInstance().post(path, acceptType, route);
    }

    public static void put(String path, String acceptType, Route route) {
        Spark.getInstance().put(path, acceptType, route);
    }

    public static void patch(String path, String acceptType, Route route) {
        Spark.getInstance().patch(path, acceptType, route);
    }

    public static void delete(String path, String acceptType, Route route) {
        Spark.getInstance().delete(path, acceptType, route);
    }

    public static void head(String path, String acceptType, Route route) {
        Spark.getInstance().head(path, acceptType, route);
    }

    public static void trace(String path, String acceptType, Route route) {
        Spark.getInstance().trace(path, acceptType, route);
    }

    public static void connect(String path, String acceptType, Route route) {
        Spark.getInstance().connect(path, acceptType, route);
    }

    public static void options(String path, String acceptType, Route route) {
        Spark.getInstance().options(path, acceptType, route);
    }

    public static void before(Filter ... filters) {
        for (Filter filter2 : filters) {
            Spark.getInstance().before(filter2);
        }
    }

    public static void after(Filter ... filters) {
        for (Filter filter2 : filters) {
            Spark.getInstance().after(filter2);
        }
    }

    public static void before(String path, String acceptType, Filter ... filters) {
        for (Filter filter2 : filters) {
            Spark.getInstance().before(path, acceptType, filter2);
        }
    }

    public static void after(String path, String acceptType, Filter ... filters) {
        for (Filter filter2 : filters) {
            Spark.getInstance().after(path, acceptType, filter2);
        }
    }

    public static void afterAfter(String path, Filter filter2) {
        Spark.getInstance().afterAfter(path, filter2);
    }

    public static void afterAfter(Filter filter2) {
        Spark.getInstance().afterAfter(filter2);
    }

    public static void get(String path, TemplateViewRoute route, TemplateEngine engine) {
        Spark.getInstance().get(path, route, engine);
    }

    public static void get(String path, String acceptType, TemplateViewRoute route, TemplateEngine engine) {
        Spark.getInstance().get(path, acceptType, route, engine);
    }

    public static void post(String path, TemplateViewRoute route, TemplateEngine engine) {
        Spark.getInstance().post(path, route, engine);
    }

    public static void post(String path, String acceptType, TemplateViewRoute route, TemplateEngine engine) {
        Spark.getInstance().post(path, acceptType, route, engine);
    }

    public static void put(String path, TemplateViewRoute route, TemplateEngine engine) {
        Spark.getInstance().put(path, route, engine);
    }

    public static void put(String path, String acceptType, TemplateViewRoute route, TemplateEngine engine) {
        Spark.getInstance().put(path, acceptType, route, engine);
    }

    public static void delete(String path, TemplateViewRoute route, TemplateEngine engine) {
        Spark.getInstance().delete(path, route, engine);
    }

    public static void delete(String path, String acceptType, TemplateViewRoute route, TemplateEngine engine) {
        Spark.getInstance().delete(path, acceptType, route, engine);
    }

    public static void patch(String path, TemplateViewRoute route, TemplateEngine engine) {
        Spark.getInstance().patch(path, route, engine);
    }

    public static void patch(String path, String acceptType, TemplateViewRoute route, TemplateEngine engine) {
        Spark.getInstance().patch(path, acceptType, route, engine);
    }

    public static void head(String path, TemplateViewRoute route, TemplateEngine engine) {
        Spark.getInstance().head(path, route, engine);
    }

    public static void head(String path, String acceptType, TemplateViewRoute route, TemplateEngine engine) {
        Spark.getInstance().head(path, acceptType, route, engine);
    }

    public static void trace(String path, TemplateViewRoute route, TemplateEngine engine) {
        Spark.getInstance().trace(path, route, engine);
    }

    public static void trace(String path, String acceptType, TemplateViewRoute route, TemplateEngine engine) {
        Spark.getInstance().trace(path, acceptType, route, engine);
    }

    public static void connect(String path, TemplateViewRoute route, TemplateEngine engine) {
        Spark.getInstance().connect(path, route, engine);
    }

    public static void connect(String path, String acceptType, TemplateViewRoute route, TemplateEngine engine) {
        Spark.getInstance().connect(path, acceptType, route, engine);
    }

    public static void options(String path, TemplateViewRoute route, TemplateEngine engine) {
        Spark.getInstance().options(path, route, engine);
    }

    public static void options(String path, String acceptType, TemplateViewRoute route, TemplateEngine engine) {
        Spark.getInstance().options(path, acceptType, route, engine);
    }

    public static void get(String path, Route route, ResponseTransformer transformer) {
        Spark.getInstance().get(path, route, transformer);
    }

    public static void get(String path, String acceptType, Route route, ResponseTransformer transformer) {
        Spark.getInstance().get(path, acceptType, route, transformer);
    }

    public static void post(String path, Route route, ResponseTransformer transformer) {
        Spark.getInstance().post(path, route, transformer);
    }

    public static void post(String path, String acceptType, Route route, ResponseTransformer transformer) {
        Spark.getInstance().post(path, acceptType, route, transformer);
    }

    public static void put(String path, Route route, ResponseTransformer transformer) {
        Spark.getInstance().put(path, route, transformer);
    }

    public static void put(String path, String acceptType, Route route, ResponseTransformer transformer) {
        Spark.getInstance().put(path, acceptType, route, transformer);
    }

    public static void delete(String path, Route route, ResponseTransformer transformer) {
        Spark.getInstance().delete(path, route, transformer);
    }

    public static void delete(String path, String acceptType, Route route, ResponseTransformer transformer) {
        Spark.getInstance().delete(path, acceptType, route, transformer);
    }

    public static void head(String path, Route route, ResponseTransformer transformer) {
        Spark.getInstance().head(path, route, transformer);
    }

    public static void head(String path, String acceptType, Route route, ResponseTransformer transformer) {
        Spark.getInstance().head(path, acceptType, route, transformer);
    }

    public static void connect(String path, Route route, ResponseTransformer transformer) {
        Spark.getInstance().connect(path, route, transformer);
    }

    public static void connect(String path, String acceptType, Route route, ResponseTransformer transformer) {
        Spark.getInstance().connect(path, acceptType, route, transformer);
    }

    public static void trace(String path, Route route, ResponseTransformer transformer) {
        Spark.getInstance().trace(path, route, transformer);
    }

    public static void trace(String path, String acceptType, Route route, ResponseTransformer transformer) {
        Spark.getInstance().trace(path, acceptType, route, transformer);
    }

    public static void options(String path, Route route, ResponseTransformer transformer) {
        Spark.getInstance().options(path, route, transformer);
    }

    public static void options(String path, String acceptType, Route route, ResponseTransformer transformer) {
        Spark.getInstance().options(path, acceptType, route, transformer);
    }

    public static void patch(String path, Route route, ResponseTransformer transformer) {
        Spark.getInstance().patch(path, route, transformer);
    }

    public static void patch(String path, String acceptType, Route route, ResponseTransformer transformer) {
        Spark.getInstance().patch(path, acceptType, route, transformer);
    }

    public static <T extends Exception> void exception(Class<T> exceptionClass, ExceptionHandler<? super T> handler) {
        Spark.getInstance().exception(exceptionClass, handler);
    }

    public static HaltException halt() {
        throw Spark.getInstance().halt();
    }

    public static HaltException halt(int status) {
        throw Spark.getInstance().halt(status);
    }

    public static HaltException halt(String body) {
        throw Spark.getInstance().halt(body);
    }

    public static HaltException halt(int status, String body) {
        throw Spark.getInstance().halt(status, body);
    }

    public static void setIpAddress(String ipAddress) {
        Spark.getInstance().ipAddress(ipAddress);
    }

    public static void ipAddress(String ipAddress) {
        Spark.getInstance().ipAddress(ipAddress);
    }

    public static void setPort(int port) {
        Spark.getInstance().port(port);
    }

    public static void port(int port) {
        Spark.getInstance().port(port);
    }

    public static int port() {
        return Spark.getInstance().port();
    }

    public static void setSecure(String keystoreFile, String keystorePassword, String truststoreFile, String truststorePassword) {
        Spark.getInstance().secure(keystoreFile, keystorePassword, truststoreFile, truststorePassword);
    }

    public static void secure(String keystoreFile, String keystorePassword, String truststoreFile, String truststorePassword) {
        Spark.getInstance().secure(keystoreFile, keystorePassword, truststoreFile, truststorePassword);
    }

    public static void initExceptionHandler(Consumer<Exception> initExceptionHandler) {
        Spark.getInstance().initExceptionHandler(initExceptionHandler);
    }

    public static void secure(String keystoreFile, String keystorePassword, String truststoreFile, String truststorePassword, boolean needsClientCert) {
        Spark.getInstance().secure(keystoreFile, keystorePassword, truststoreFile, truststorePassword, needsClientCert);
    }

    public static void threadPool(int maxThreads) {
        Spark.getInstance().threadPool(maxThreads);
    }

    public static void threadPool(int maxThreads, int minThreads, int idleTimeoutMillis) {
        Spark.getInstance().threadPool(maxThreads, minThreads, idleTimeoutMillis);
    }

    public static void staticFileLocation(String folder) {
        Spark.getInstance().staticFileLocation(folder);
    }

    public static void externalStaticFileLocation(String externalFolder) {
        Spark.getInstance().externalStaticFileLocation(externalFolder);
    }

    public static void awaitInitialization() {
        Spark.getInstance().awaitInitialization();
    }

    public static void stop() {
        Spark.getInstance().stop();
    }

    public static void webSocket(String path, Class<?> handler) {
        Spark.getInstance().webSocket(path, handler);
    }

    public static void webSocket(String path, Object handler) {
        Spark.getInstance().webSocket(path, handler);
    }

    public static void webSocketIdleTimeoutMillis(int timeoutMillis) {
        Spark.getInstance().webSocketIdleTimeoutMillis(timeoutMillis);
    }

    public static void notFound(String page) {
        Spark.getInstance().notFound(page);
    }

    public static void internalServerError(String page) {
        Spark.getInstance().internalServerError(page);
    }

    public static void notFound(Route route) {
        Spark.getInstance().notFound(route);
    }

    public static void internalServerError(Route route) {
        Spark.getInstance().internalServerError(route);
    }

    public static void init() {
        Spark.getInstance().init();
    }

    public static ModelAndView modelAndView(Object model, String viewName) {
        return new ModelAndView(model, viewName);
    }

    private static class SingletonHolder {
        private static final Service INSTANCE = Service.ignite();

        private SingletonHolder() {
        }
    }
}

