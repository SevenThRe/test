/*
 * Decompiled with CFR 0.152.
 */
package info.journeymap.shaded.kotlin.spark.kotlin;

import info.journeymap.shaded.kotlin.kotlin.Metadata;
import info.journeymap.shaded.kotlin.kotlin.jvm.internal.Intrinsics;
import info.journeymap.shaded.kotlin.spark.QueryParamsMap;
import info.journeymap.shaded.kotlin.spark.Request;
import info.journeymap.shaded.kotlin.spark.Response;
import info.journeymap.shaded.kotlin.spark.Session;
import info.journeymap.shaded.org.jetbrains.annotations.NotNull;
import info.journeymap.shaded.org.jetbrains.annotations.Nullable;
import java.util.Map;
import java.util.Set;

@Metadata(mv={1, 1, 6}, bv={1, 0, 1}, k=1, d1={"\u0000Z\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010#\n\u0002\b\u0004\n\u0002\u0010%\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0011\n\u0002\b\u0007\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\u0002\u0010\u0006J\u000e\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\fJ\u0016\u0010\u000b\u001a\u00020\u000e2\u0006\u0010\r\u001a\u00020\f2\u0006\u0010\u000f\u001a\u00020\fJ\u000e\u0010\u0010\u001a\n\u0012\u0004\u0012\u00020\f\u0018\u00010\u0011J\u0006\u0010\u0012\u001a\u00020\fJ\u0006\u0010\u0013\u001a\u00020\fJ\u0006\u0010\u0014\u001a\u00020\fJ\u0014\u0010\u0015\u001a\u0010\u0012\u0004\u0012\u00020\f\u0012\u0004\u0012\u00020\f\u0018\u00010\u0016J\u000e\u0010\u0015\u001a\u00020\f2\u0006\u0010\u0017\u001a\u00020\fJ\u0006\u0010\u0018\u001a\u00020\fJ\u0006\u0010\u0019\u001a\u00020\u001aJ\u0006\u0010\u001b\u001a\u00020\fJ\u0006\u0010\u001c\u001a\u00020\u001dJ\u000e\u0010\u001c\u001a\u00020\u001d2\u0006\u0010\r\u001a\u00020\fJ\u000e\u0010\u001e\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\fJ\u000e\u0010\u001f\u001a\u00020\u000e2\u0006\u0010 \u001a\u00020\fJ\u0016\u0010\u001f\u001a\u00020\u000e2\u0006\u0010 \u001a\u00020\f2\u0006\u0010!\u001a\u00020\u001aJ\u0006\u0010\"\u001a\u00020\fJ\u0006\u0010#\u001a\u00020\fJ\u0006\u0010$\u001a\u00020\fJ\u0006\u0010%\u001a\u00020&J\u0010\u0010%\u001a\u0004\u0018\u00010&2\u0006\u0010'\u001a\u00020(J\u0015\u0010)\u001a\f\u0012\u0006\b\u0001\u0012\u00020\f\u0018\u00010*\u00a2\u0006\u0002\u0010+J\u0006\u0010,\u001a\u00020\u001aJ\u000e\u0010,\u001a\u00020\u000e2\u0006\u0010-\u001a\u00020\u001aJ\u0006\u0010.\u001a\u00020\fJ\u000e\u0010.\u001a\u00020\u000e2\u0006\u0010\u0012\u001a\u00020\fJ\u0006\u0010/\u001a\u00020\fJ\u0006\u00100\u001a\u00020\fR\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0011\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\b\n\u0000\u001a\u0004\b\t\u0010\n\u00a8\u00061"}, d2={"Linfo/journeymap/shaded/kotlin/spark/kotlin/RouteHandler;", "", "request", "Linfo/journeymap/shaded/kotlin/spark/Request;", "response", "Linfo/journeymap/shaded/kotlin/spark/Response;", "(Lspark/Request;Lspark/Response;)V", "getRequest", "()Lspark/Request;", "getResponse", "()Lspark/Response;", "attribute", "", "key", "", "value", "attributes", "", "contentType", "contextPath", "host", "params", "", "name", "pathInfo", "port", "", "protocol", "queryMap", "Linfo/journeymap/shaded/kotlin/spark/QueryParamsMap;", "queryParams", "redirect", "location", "statusCode", "requestMethod", "scheme", "servletPath", "session", "Linfo/journeymap/shaded/kotlin/spark/Session;", "create", "", "splat", "", "()[Ljava/lang/String;", "status", "code", "type", "uri", "userAgent", "info.journeymap.shaded.kotlin.spark-kotlin"})
public final class RouteHandler {
    @NotNull
    private final Request request;
    @NotNull
    private final Response response;

    @NotNull
    public final String params(@NotNull String name) {
        Intrinsics.checkParameterIsNotNull(name, "name");
        String string = this.request.params(name);
        Intrinsics.checkExpressionValueIsNotNull(string, "request.params(name)");
        return string;
    }

    @Nullable
    public final Map<String, String> params() {
        return this.request.params();
    }

    @Nullable
    public final String[] splat() {
        return this.request.splat();
    }

    @NotNull
    public final String contentType() {
        String string = this.request.contentType();
        Intrinsics.checkExpressionValueIsNotNull(string, "request.contentType()");
        return string;
    }

    @NotNull
    public final String queryParams(@NotNull String key) {
        Intrinsics.checkParameterIsNotNull(key, "key");
        String string = this.request.queryParams(key);
        Intrinsics.checkExpressionValueIsNotNull(string, "request.queryParams(key)");
        return string;
    }

    @NotNull
    public final QueryParamsMap queryMap() {
        QueryParamsMap queryParamsMap = this.request.queryMap();
        Intrinsics.checkExpressionValueIsNotNull(queryParamsMap, "request.queryMap()");
        return queryParamsMap;
    }

    @NotNull
    public final QueryParamsMap queryMap(@NotNull String key) {
        Intrinsics.checkParameterIsNotNull(key, "key");
        QueryParamsMap queryParamsMap = this.request.queryMap(key);
        Intrinsics.checkExpressionValueIsNotNull(queryParamsMap, "request.queryMap(key)");
        return queryParamsMap;
    }

    @NotNull
    public final String attribute(@NotNull String key) {
        Intrinsics.checkParameterIsNotNull(key, "key");
        Object t = this.request.attribute(key);
        Intrinsics.checkExpressionValueIsNotNull(t, "request.attribute(key)");
        return (String)t;
    }

    public final void attribute(@NotNull String key, @NotNull String value) {
        Intrinsics.checkParameterIsNotNull(key, "key");
        Intrinsics.checkParameterIsNotNull(value, "value");
        this.request.attribute(key, value);
    }

    @Nullable
    public final Set<String> attributes() {
        return this.request.attributes();
    }

    @NotNull
    public final Session session() {
        Session session = this.request.session();
        Intrinsics.checkExpressionValueIsNotNull(session, "request.session()");
        return session;
    }

    @Nullable
    public final Session session(boolean create) {
        return this.request.session(create);
    }

    @NotNull
    public final String uri() {
        String string = this.request.uri();
        Intrinsics.checkExpressionValueIsNotNull(string, "request.uri()");
        return string;
    }

    @NotNull
    public final String protocol() {
        String string = this.request.protocol();
        Intrinsics.checkExpressionValueIsNotNull(string, "request.protocol()");
        return string;
    }

    @NotNull
    public final String scheme() {
        String string = this.request.scheme();
        Intrinsics.checkExpressionValueIsNotNull(string, "request.scheme()");
        return string;
    }

    @NotNull
    public final String host() {
        String string = this.request.host();
        Intrinsics.checkExpressionValueIsNotNull(string, "request.host()");
        return string;
    }

    public final int port() {
        return this.request.port();
    }

    @NotNull
    public final String pathInfo() {
        String string = this.request.pathInfo();
        Intrinsics.checkExpressionValueIsNotNull(string, "request.pathInfo()");
        return string;
    }

    @NotNull
    public final String servletPath() {
        String string = this.request.servletPath();
        Intrinsics.checkExpressionValueIsNotNull(string, "request.servletPath()");
        return string;
    }

    @NotNull
    public final String contextPath() {
        String string = this.request.contextPath();
        Intrinsics.checkExpressionValueIsNotNull(string, "request.contextPath()");
        return string;
    }

    @NotNull
    public final String userAgent() {
        String string = this.request.userAgent();
        Intrinsics.checkExpressionValueIsNotNull(string, "request.userAgent()");
        return string;
    }

    @NotNull
    public final String requestMethod() {
        String string = this.request.requestMethod();
        Intrinsics.checkExpressionValueIsNotNull(string, "request.requestMethod()");
        return string;
    }

    public final int status() {
        return this.response.status();
    }

    public final void status(int code) {
        this.response.status(code);
    }

    @NotNull
    public final String type() {
        String string = this.response.type();
        Intrinsics.checkExpressionValueIsNotNull(string, "response.type()");
        return string;
    }

    public final void type(@NotNull String contentType) {
        Intrinsics.checkParameterIsNotNull(contentType, "contentType");
        this.response.type(contentType);
    }

    public final void redirect(@NotNull String location) {
        Intrinsics.checkParameterIsNotNull(location, "location");
        this.response.redirect(location);
    }

    public final void redirect(@NotNull String location, int statusCode) {
        Intrinsics.checkParameterIsNotNull(location, "location");
        this.response.redirect(location, statusCode);
    }

    @NotNull
    public final Request getRequest() {
        return this.request;
    }

    @NotNull
    public final Response getResponse() {
        return this.response;
    }

    public RouteHandler(@NotNull Request request, @NotNull Response response) {
        Intrinsics.checkParameterIsNotNull(request, "request");
        Intrinsics.checkParameterIsNotNull(response, "response");
        this.request = request;
        this.response = response;
    }
}

