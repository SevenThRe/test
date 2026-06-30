/*
 * Decompiled with CFR 0.152.
 */
package info.journeymap.shaded.kotlin.spark.kotlin;

import info.journeymap.shaded.kotlin.kotlin.Metadata;
import info.journeymap.shaded.kotlin.kotlin.Unit;
import info.journeymap.shaded.kotlin.kotlin.jvm.functions.Function1;
import info.journeymap.shaded.kotlin.kotlin.jvm.internal.Intrinsics;
import info.journeymap.shaded.kotlin.spark.Filter;
import info.journeymap.shaded.kotlin.spark.ModelAndView;
import info.journeymap.shaded.kotlin.spark.Redirect;
import info.journeymap.shaded.kotlin.spark.Request;
import info.journeymap.shaded.kotlin.spark.Response;
import info.journeymap.shaded.kotlin.spark.ResponseTransformer;
import info.journeymap.shaded.kotlin.spark.Route;
import info.journeymap.shaded.kotlin.spark.Service;
import info.journeymap.shaded.kotlin.spark.TemplateEngine;
import info.journeymap.shaded.kotlin.spark.TemplateViewRoute;
import info.journeymap.shaded.kotlin.spark.kotlin.RouteHandler;
import info.journeymap.shaded.org.jetbrains.annotations.NotNull;
import info.journeymap.shaded.org.jetbrains.annotations.Nullable;

/*
 * Illegal identifiers - consider using --renameillegalidents true
 */
@Metadata(mv={1, 1, 6}, bv={1, 0, 1}, k=1, d1={"\u0000l\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0010\b\n\u0002\b\t\n\u0002\u0010\u000b\n\u0002\b\u0007\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J5\u0010\u0013\u001a\u00020\u00142\n\b\u0002\u0010\u0015\u001a\u0004\u0018\u00010\u00062\b\b\u0002\u0010\u0016\u001a\u00020\u00062\u0017\u0010\u0017\u001a\u0013\u0012\u0004\u0012\u00020\u0019\u0012\u0004\u0012\u00020\u00140\u0018\u00a2\u0006\u0002\b\u001aJ5\u0010\u001b\u001a\u00020\u00142\n\b\u0002\u0010\u0015\u001a\u0004\u0018\u00010\u00062\b\b\u0002\u0010\u0016\u001a\u00020\u00062\u0017\u0010\u0017\u001a\u0013\u0012\u0004\u0012\u00020\u0019\u0012\u0004\u0012\u00020\u00140\u0018\u00a2\u0006\u0002\b\u001aJ\u0018\u0010\u001b\u001a\u00020\u00142\u0006\u0010\u001c\u001a\u00020\u001d2\b\b\u0002\u0010\u0016\u001a\u00020\u0006J9\u0010\u001e\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\u00062\b\b\u0002\u0010\u0016\u001a\u00020\u00062\u0006\u0010\u001f\u001a\u00020 2\u0017\u0010\u0017\u001a\u0013\u0012\u0004\u0012\u00020\u0019\u0012\u0004\u0012\u00020\u00010\u0018\u00a2\u0006\u0002\b\u001aJ9\u0010\u001e\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\u00062\b\b\u0002\u0010\u0016\u001a\u00020\u00062\u0006\u0010!\u001a\u00020\"2\u0017\u0010\u0017\u001a\u0013\u0012\u0004\u0012\u00020\u0019\u0012\u0004\u0012\u00020#0\u0018\u00a2\u0006\u0002\b\u001aJ1\u0010\u001e\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\u00062\b\b\u0002\u0010\u0016\u001a\u00020\u00062\u0017\u0010\u0017\u001a\u0013\u0012\u0004\u0012\u00020\u0019\u0012\u0004\u0012\u00020\u00010\u0018\u00a2\u0006\u0002\b\u001aJ9\u0010$\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\u00062\b\b\u0002\u0010\u0016\u001a\u00020\u00062\u0006\u0010\u001f\u001a\u00020 2\u0017\u0010\u0017\u001a\u0013\u0012\u0004\u0012\u00020\u0019\u0012\u0004\u0012\u00020\u00010\u0018\u00a2\u0006\u0002\b\u001aJ9\u0010$\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\u00062\b\b\u0002\u0010\u0016\u001a\u00020\u00062\u0006\u0010!\u001a\u00020\"2\u0017\u0010\u0017\u001a\u0013\u0012\u0004\u0012\u00020\u0019\u0012\u0004\u0012\u00020#0\u0018\u00a2\u0006\u0002\b\u001aJ1\u0010$\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\u00062\b\b\u0002\u0010\u0016\u001a\u00020\u00062\u0017\u0010\u0017\u001a\u0013\u0012\u0004\u0012\u00020\u0019\u0012\u0004\u0012\u00020\u00010\u0018\u00a2\u0006\u0002\b\u001aJ+\u0010%\u001a\u00020\u00142\n\b\u0002\u0010\u0015\u001a\u0004\u0018\u00010\u00062\u0017\u0010\u0017\u001a\u0013\u0012\u0004\u0012\u00020\u0019\u0012\u0004\u0012\u00020\u00140\u0018\u00a2\u0006\u0002\b\u001aJ9\u0010&\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\u00062\b\b\u0002\u0010\u0016\u001a\u00020\u00062\u0006\u0010\u001f\u001a\u00020 2\u0017\u0010\u0017\u001a\u0013\u0012\u0004\u0012\u00020\u0019\u0012\u0004\u0012\u00020\u00010\u0018\u00a2\u0006\u0002\b\u001aJ9\u0010&\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\u00062\b\b\u0002\u0010\u0016\u001a\u00020\u00062\u0006\u0010!\u001a\u00020\"2\u0017\u0010\u0017\u001a\u0013\u0012\u0004\u0012\u00020\u0019\u0012\u0004\u0012\u00020#0\u0018\u00a2\u0006\u0002\b\u001aJ1\u0010&\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\u00062\b\b\u0002\u0010\u0016\u001a\u00020\u00062\u0017\u0010\u0017\u001a\u0013\u0012\u0004\u0012\u00020\u0019\u0012\u0004\u0012\u00020\u00010\u0018\u00a2\u0006\u0002\b\u001aJ9\u0010'\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\u00062\b\b\u0002\u0010\u0016\u001a\u00020\u00062\u0006\u0010\u001f\u001a\u00020 2\u0017\u0010\u0017\u001a\u0013\u0012\u0004\u0012\u00020\u0019\u0012\u0004\u0012\u00020\u00010\u0018\u00a2\u0006\u0002\b\u001aJ9\u0010'\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\u00062\b\b\u0002\u0010\u0016\u001a\u00020\u00062\u0006\u0010!\u001a\u00020\"2\u0017\u0010\u0017\u001a\u0013\u0012\u0004\u0012\u00020\u0019\u0012\u0004\u0012\u00020#0\u0018\u00a2\u0006\u0002\b\u001aJ1\u0010'\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\u00062\b\b\u0002\u0010\u0016\u001a\u00020\u00062\u0017\u0010\u0017\u001a\u0013\u0012\u0004\u0012\u00020\u0019\u0012\u0004\u0012\u00020\u00010\u0018\u00a2\u0006\u0002\b\u001aJ\u001f\u0010(\u001a\u00020\u00142\u0017\u0010\u0017\u001a\u0013\u0012\u0004\u0012\u00020\u0019\u0012\u0004\u0012\u00020\u00010\u0018\u00a2\u0006\u0002\b\u001aJ\u000e\u0010)\u001a\u00020\u00002\u0006\u0010)\u001a\u00020\u0006J\u001f\u0010*\u001a\u00020\u00142\u0017\u0010\u0017\u001a\u0013\u0012\u0004\u0012\u00020\u0019\u0012\u0004\u0012\u00020\u00010\u0018\u00a2\u0006\u0002\b\u001aJ9\u0010+\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\u00062\b\b\u0002\u0010\u0016\u001a\u00020\u00062\u0006\u0010\u001f\u001a\u00020 2\u0017\u0010\u0017\u001a\u0013\u0012\u0004\u0012\u00020\u0019\u0012\u0004\u0012\u00020\u00010\u0018\u00a2\u0006\u0002\b\u001aJ9\u0010+\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\u00062\b\b\u0002\u0010\u0016\u001a\u00020\u00062\u0006\u0010!\u001a\u00020\"2\u0017\u0010\u0017\u001a\u0013\u0012\u0004\u0012\u00020\u0019\u0012\u0004\u0012\u00020#0\u0018\u00a2\u0006\u0002\b\u001aJ1\u0010+\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\u00062\b\b\u0002\u0010\u0016\u001a\u00020\u00062\u0017\u0010\u0017\u001a\u0013\u0012\u0004\u0012\u00020\u0019\u0012\u0004\u0012\u00020\u00010\u0018\u00a2\u0006\u0002\b\u001aJ9\u0010,\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\u00062\b\b\u0002\u0010\u0016\u001a\u00020\u00062\u0006\u0010\u001f\u001a\u00020 2\u0017\u0010\u0017\u001a\u0013\u0012\u0004\u0012\u00020\u0019\u0012\u0004\u0012\u00020\u00010\u0018\u00a2\u0006\u0002\b\u001aJ9\u0010,\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\u00062\b\b\u0002\u0010\u0016\u001a\u00020\u00062\u0006\u0010!\u001a\u00020\"2\u0017\u0010\u0017\u001a\u0013\u0012\u0004\u0012\u00020\u0019\u0012\u0004\u0012\u00020#0\u0018\u00a2\u0006\u0002\b\u001aJ1\u0010,\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\u00062\b\b\u0002\u0010\u0016\u001a\u00020\u00062\u0017\u0010\u0017\u001a\u0013\u0012\u0004\u0012\u00020\u0019\u0012\u0004\u0012\u00020\u00010\u0018\u00a2\u0006\u0002\b\u001aJ\u0006\u0010-\u001a\u00020.J\u000e\u0010-\u001a\u00020\u00002\u0006\u0010/\u001a\u00020.J9\u00100\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\u00062\b\b\u0002\u0010\u0016\u001a\u00020\u00062\u0006\u0010\u001f\u001a\u00020 2\u0017\u0010\u0017\u001a\u0013\u0012\u0004\u0012\u00020\u0019\u0012\u0004\u0012\u00020\u00010\u0018\u00a2\u0006\u0002\b\u001aJ9\u00100\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\u00062\b\b\u0002\u0010\u0016\u001a\u00020\u00062\u0006\u0010!\u001a\u00020\"2\u0017\u0010\u0017\u001a\u0013\u0012\u0004\u0012\u00020\u0019\u0012\u0004\u0012\u00020#0\u0018\u00a2\u0006\u0002\b\u001aJ1\u00100\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\u00062\b\b\u0002\u0010\u0016\u001a\u00020\u00062\u0017\u0010\u0017\u001a\u0013\u0012\u0004\u0012\u00020\u0019\u0012\u0004\u0012\u00020\u00010\u0018\u00a2\u0006\u0002\b\u001aJ9\u00101\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\u00062\b\b\u0002\u0010\u0016\u001a\u00020\u00062\u0006\u0010\u001f\u001a\u00020 2\u0017\u0010\u0017\u001a\u0013\u0012\u0004\u0012\u00020\u0019\u0012\u0004\u0012\u00020\u00010\u0018\u00a2\u0006\u0002\b\u001aJ9\u00101\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\u00062\b\b\u0002\u0010\u0016\u001a\u00020\u00062\u0006\u0010!\u001a\u00020\"2\u0017\u0010\u0017\u001a\u0013\u0012\u0004\u0012\u00020\u0019\u0012\u0004\u0012\u00020#0\u0018\u00a2\u0006\u0002\b\u001aJ1\u00101\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\u00062\b\b\u0002\u0010\u0016\u001a\u00020\u00062\u0017\u0010\u0017\u001a\u0013\u0012\u0004\u0012\u00020\u0019\u0012\u0004\u0012\u00020\u00010\u0018\u00a2\u0006\u0002\b\u001aJ&\u00102\u001a\u00020\u00002\u0006\u00103\u001a\u00020\u00062\u0006\u00104\u001a\u00020\u00062\u0006\u00105\u001a\u00020\u00062\u0006\u00106\u001a\u00020\u0006J.\u00102\u001a\u00020\u00002\u0006\u00103\u001a\u00020\u00062\u0006\u00104\u001a\u00020\u00062\u0006\u00105\u001a\u00020\u00062\u0006\u00106\u001a\u00020\u00062\u0006\u00107\u001a\u000208J\u0006\u00109\u001a\u00020\u0014J\u000e\u0010:\u001a\u00020\u00002\u0006\u0010;\u001a\u00020.J\u001e\u0010:\u001a\u00020\u00002\u0006\u0010;\u001a\u00020.2\u0006\u0010<\u001a\u00020.2\u0006\u0010=\u001a\u00020.J9\u0010>\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\u00062\b\b\u0002\u0010\u0016\u001a\u00020\u00062\u0006\u0010\u001f\u001a\u00020 2\u0017\u0010\u0017\u001a\u0013\u0012\u0004\u0012\u00020\u0019\u0012\u0004\u0012\u00020\u00010\u0018\u00a2\u0006\u0002\b\u001aJ9\u0010>\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\u00062\b\b\u0002\u0010\u0016\u001a\u00020\u00062\u0006\u0010!\u001a\u00020\"2\u0017\u0010\u0017\u001a\u0013\u0012\u0004\u0012\u00020\u0019\u0012\u0004\u0012\u00020#0\u0018\u00a2\u0006\u0002\b\u001aJ1\u0010>\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\u00062\b\b\u0002\u0010\u0016\u001a\u00020\u00062\u0017\u0010\u0017\u001a\u0013\u0012\u0004\u0012\u00020\u0019\u0012\u0004\u0012\u00020\u00010\u0018\u00a2\u0006\u0002\b\u001aR\u0014\u0010\u0005\u001a\u00020\u0006X\u0086D\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0011\u0010\t\u001a\u00020\n\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR\u0015\u0010\u000f\u001a\u00060\u0010R\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0012\u00a8\u0006?"}, d2={"Linfo/journeymap/shaded/kotlin/spark/kotlin/Http;", "", "service", "Linfo/journeymap/shaded/kotlin/spark/Service;", "(Lspark/Service;)V", "DEFAULT_ACCEPT", "", "getDEFAULT_ACCEPT", "()Ljava/lang/String;", "redirect", "Linfo/journeymap/shaded/kotlin/spark/Redirect;", "getRedirect", "()Lspark/Redirect;", "getService", "()Lspark/Service;", "staticFiles", "Linfo/journeymap/shaded/kotlin/spark/Service$StaticFiles;", "getStaticFiles", "()Lspark/Service$StaticFiles;", "after", "", "path", "accepts", "function", "Linfo/journeymap/shaded/kotlin/kotlin/Function1;", "Linfo/journeymap/shaded/kotlin/spark/kotlin/RouteHandler;", "Linfo/journeymap/shaded/kotlin/kotlin/ExtensionFunctionType;", "before", "filter", "Linfo/journeymap/shaded/kotlin/spark/Filter;", "connect", "responseTransformer", "Linfo/journeymap/shaded/kotlin/spark/ResponseTransformer;", "templateEngine", "Linfo/journeymap/shaded/kotlin/spark/TemplateEngine;", "Linfo/journeymap/shaded/kotlin/spark/ModelAndView;", "delete", "finally", "get", "head", "internalServerError", "ipAddress", "notFound", "options", "patch", "port", "", "number", "post", "put", "secure", "keyStoreFile", "keyStorePassword", "truststoreFile", "truststorePassword", "needsClientCert", "", "stop", "threadPool", "maxSize", "minSize", "idleTimeoutMillis", "trace", "info.journeymap.shaded.kotlin.spark-kotlin"})
public final class Http {
    @NotNull
    private final String DEFAULT_ACCEPT = "*/*";
    @NotNull
    private final Service.StaticFiles staticFiles;
    @NotNull
    private final Redirect redirect;
    @NotNull
    private final Service service;

    @NotNull
    public final String getDEFAULT_ACCEPT() {
        return this.DEFAULT_ACCEPT;
    }

    public final void get(@NotNull String path, @NotNull String accepts, @NotNull Function1<? super RouteHandler, ? extends Object> function) {
        Intrinsics.checkParameterIsNotNull(path, "path");
        Intrinsics.checkParameterIsNotNull(accepts, "accepts");
        Intrinsics.checkParameterIsNotNull(function, "function");
        this.service.get(path, accepts, new Route(function){
            final /* synthetic */ Function1 $function;

            @NotNull
            public final Object handle(Request req, Response res) {
                Request request = req;
                Intrinsics.checkExpressionValueIsNotNull(request, "req");
                Response response = res;
                Intrinsics.checkExpressionValueIsNotNull(response, "res");
                return this.$function.invoke(new RouteHandler(request, response));
            }
            {
                this.$function = function1;
            }
        });
    }

    public static /* bridge */ /* synthetic */ void get$default(Http http, String string, String string2, Function1 function1, int n, Object object) {
        if ((n & 2) != 0) {
            string2 = http.DEFAULT_ACCEPT;
        }
        http.get(string, string2, function1);
    }

    public final void get(@NotNull String path, @NotNull String accepts, @NotNull TemplateEngine templateEngine, @NotNull Function1<? super RouteHandler, ? extends ModelAndView> function) {
        Intrinsics.checkParameterIsNotNull(path, "path");
        Intrinsics.checkParameterIsNotNull(accepts, "accepts");
        Intrinsics.checkParameterIsNotNull(templateEngine, "templateEngine");
        Intrinsics.checkParameterIsNotNull(function, "function");
        this.service.get(path, accepts, new TemplateViewRoute(function){
            final /* synthetic */ Function1 $function;

            @NotNull
            public final ModelAndView invoke(Request req, Response res) {
                Request request = req;
                Intrinsics.checkExpressionValueIsNotNull(request, "req");
                Response response = res;
                Intrinsics.checkExpressionValueIsNotNull(response, "res");
                return (ModelAndView)this.$function.invoke(new RouteHandler(request, response));
            }
            {
                this.$function = function1;
            }
        }, templateEngine);
    }

    public static /* bridge */ /* synthetic */ void get$default(Http http, String string, String string2, TemplateEngine templateEngine, Function1 function1, int n, Object object) {
        if ((n & 2) != 0) {
            string2 = http.DEFAULT_ACCEPT;
        }
        http.get(string, string2, templateEngine, function1);
    }

    public final void get(@NotNull String path, @NotNull String accepts, @NotNull ResponseTransformer responseTransformer, @NotNull Function1<? super RouteHandler, ? extends Object> function) {
        Intrinsics.checkParameterIsNotNull(path, "path");
        Intrinsics.checkParameterIsNotNull(accepts, "accepts");
        Intrinsics.checkParameterIsNotNull(responseTransformer, "responseTransformer");
        Intrinsics.checkParameterIsNotNull(function, "function");
        this.service.get(path, accepts, new Route(function){
            final /* synthetic */ Function1 $function;

            public final void invoke(Request req, Response res) {
                Request request = req;
                Intrinsics.checkExpressionValueIsNotNull(request, "req");
                Response response = res;
                Intrinsics.checkExpressionValueIsNotNull(response, "res");
                this.$function.invoke(new RouteHandler(request, response));
            }
            {
                this.$function = function1;
            }
        }, responseTransformer);
    }

    public static /* bridge */ /* synthetic */ void get$default(Http http, String string, String string2, ResponseTransformer responseTransformer, Function1 function1, int n, Object object) {
        if ((n & 2) != 0) {
            string2 = http.DEFAULT_ACCEPT;
        }
        http.get(string, string2, responseTransformer, function1);
    }

    public final void post(@NotNull String path, @NotNull String accepts, @NotNull Function1<? super RouteHandler, ? extends Object> function) {
        Intrinsics.checkParameterIsNotNull(path, "path");
        Intrinsics.checkParameterIsNotNull(accepts, "accepts");
        Intrinsics.checkParameterIsNotNull(function, "function");
        this.service.post(path, accepts, new Route(function){
            final /* synthetic */ Function1 $function;

            @NotNull
            public final Object handle(Request req, Response res) {
                Request request = req;
                Intrinsics.checkExpressionValueIsNotNull(request, "req");
                Response response = res;
                Intrinsics.checkExpressionValueIsNotNull(response, "res");
                return this.$function.invoke(new RouteHandler(request, response));
            }
            {
                this.$function = function1;
            }
        });
    }

    public static /* bridge */ /* synthetic */ void post$default(Http http, String string, String string2, Function1 function1, int n, Object object) {
        if ((n & 2) != 0) {
            string2 = http.DEFAULT_ACCEPT;
        }
        http.post(string, string2, function1);
    }

    public final void post(@NotNull String path, @NotNull String accepts, @NotNull TemplateEngine templateEngine, @NotNull Function1<? super RouteHandler, ? extends ModelAndView> function) {
        Intrinsics.checkParameterIsNotNull(path, "path");
        Intrinsics.checkParameterIsNotNull(accepts, "accepts");
        Intrinsics.checkParameterIsNotNull(templateEngine, "templateEngine");
        Intrinsics.checkParameterIsNotNull(function, "function");
        this.service.post(path, accepts, new TemplateViewRoute(function){
            final /* synthetic */ Function1 $function;

            @NotNull
            public final ModelAndView invoke(Request req, Response res) {
                Request request = req;
                Intrinsics.checkExpressionValueIsNotNull(request, "req");
                Response response = res;
                Intrinsics.checkExpressionValueIsNotNull(response, "res");
                return (ModelAndView)this.$function.invoke(new RouteHandler(request, response));
            }
            {
                this.$function = function1;
            }
        }, templateEngine);
    }

    public static /* bridge */ /* synthetic */ void post$default(Http http, String string, String string2, TemplateEngine templateEngine, Function1 function1, int n, Object object) {
        if ((n & 2) != 0) {
            string2 = http.DEFAULT_ACCEPT;
        }
        http.post(string, string2, templateEngine, function1);
    }

    public final void post(@NotNull String path, @NotNull String accepts, @NotNull ResponseTransformer responseTransformer, @NotNull Function1<? super RouteHandler, ? extends Object> function) {
        Intrinsics.checkParameterIsNotNull(path, "path");
        Intrinsics.checkParameterIsNotNull(accepts, "accepts");
        Intrinsics.checkParameterIsNotNull(responseTransformer, "responseTransformer");
        Intrinsics.checkParameterIsNotNull(function, "function");
        this.service.post(path, accepts, new Route(function){
            final /* synthetic */ Function1 $function;

            public final void invoke(Request req, Response res) {
                Request request = req;
                Intrinsics.checkExpressionValueIsNotNull(request, "req");
                Response response = res;
                Intrinsics.checkExpressionValueIsNotNull(response, "res");
                this.$function.invoke(new RouteHandler(request, response));
            }
            {
                this.$function = function1;
            }
        }, responseTransformer);
    }

    public static /* bridge */ /* synthetic */ void post$default(Http http, String string, String string2, ResponseTransformer responseTransformer, Function1 function1, int n, Object object) {
        if ((n & 2) != 0) {
            string2 = http.DEFAULT_ACCEPT;
        }
        http.post(string, string2, responseTransformer, function1);
    }

    public final void put(@NotNull String path, @NotNull String accepts, @NotNull Function1<? super RouteHandler, ? extends Object> function) {
        Intrinsics.checkParameterIsNotNull(path, "path");
        Intrinsics.checkParameterIsNotNull(accepts, "accepts");
        Intrinsics.checkParameterIsNotNull(function, "function");
        this.service.put(path, accepts, new Route(function){
            final /* synthetic */ Function1 $function;

            @NotNull
            public final Object handle(Request req, Response res) {
                Request request = req;
                Intrinsics.checkExpressionValueIsNotNull(request, "req");
                Response response = res;
                Intrinsics.checkExpressionValueIsNotNull(response, "res");
                return this.$function.invoke(new RouteHandler(request, response));
            }
            {
                this.$function = function1;
            }
        });
    }

    public static /* bridge */ /* synthetic */ void put$default(Http http, String string, String string2, Function1 function1, int n, Object object) {
        if ((n & 2) != 0) {
            string2 = http.DEFAULT_ACCEPT;
        }
        http.put(string, string2, function1);
    }

    public final void put(@NotNull String path, @NotNull String accepts, @NotNull TemplateEngine templateEngine, @NotNull Function1<? super RouteHandler, ? extends ModelAndView> function) {
        Intrinsics.checkParameterIsNotNull(path, "path");
        Intrinsics.checkParameterIsNotNull(accepts, "accepts");
        Intrinsics.checkParameterIsNotNull(templateEngine, "templateEngine");
        Intrinsics.checkParameterIsNotNull(function, "function");
        this.service.put(path, accepts, new TemplateViewRoute(function){
            final /* synthetic */ Function1 $function;

            @NotNull
            public final ModelAndView invoke(Request req, Response res) {
                Request request = req;
                Intrinsics.checkExpressionValueIsNotNull(request, "req");
                Response response = res;
                Intrinsics.checkExpressionValueIsNotNull(response, "res");
                return (ModelAndView)this.$function.invoke(new RouteHandler(request, response));
            }
            {
                this.$function = function1;
            }
        }, templateEngine);
    }

    public static /* bridge */ /* synthetic */ void put$default(Http http, String string, String string2, TemplateEngine templateEngine, Function1 function1, int n, Object object) {
        if ((n & 2) != 0) {
            string2 = http.DEFAULT_ACCEPT;
        }
        http.put(string, string2, templateEngine, function1);
    }

    public final void put(@NotNull String path, @NotNull String accepts, @NotNull ResponseTransformer responseTransformer, @NotNull Function1<? super RouteHandler, ? extends Object> function) {
        Intrinsics.checkParameterIsNotNull(path, "path");
        Intrinsics.checkParameterIsNotNull(accepts, "accepts");
        Intrinsics.checkParameterIsNotNull(responseTransformer, "responseTransformer");
        Intrinsics.checkParameterIsNotNull(function, "function");
        this.service.post(path, accepts, new Route(function){
            final /* synthetic */ Function1 $function;

            public final void invoke(Request req, Response res) {
                Request request = req;
                Intrinsics.checkExpressionValueIsNotNull(request, "req");
                Response response = res;
                Intrinsics.checkExpressionValueIsNotNull(response, "res");
                this.$function.invoke(new RouteHandler(request, response));
            }
            {
                this.$function = function1;
            }
        }, responseTransformer);
    }

    public static /* bridge */ /* synthetic */ void put$default(Http http, String string, String string2, ResponseTransformer responseTransformer, Function1 function1, int n, Object object) {
        if ((n & 2) != 0) {
            string2 = http.DEFAULT_ACCEPT;
        }
        http.put(string, string2, responseTransformer, function1);
    }

    public final void delete(@NotNull String path, @NotNull String accepts, @NotNull Function1<? super RouteHandler, ? extends Object> function) {
        Intrinsics.checkParameterIsNotNull(path, "path");
        Intrinsics.checkParameterIsNotNull(accepts, "accepts");
        Intrinsics.checkParameterIsNotNull(function, "function");
        this.service.delete(path, accepts, new Route(function){
            final /* synthetic */ Function1 $function;

            @NotNull
            public final Object handle(Request req, Response res) {
                Request request = req;
                Intrinsics.checkExpressionValueIsNotNull(request, "req");
                Response response = res;
                Intrinsics.checkExpressionValueIsNotNull(response, "res");
                return this.$function.invoke(new RouteHandler(request, response));
            }
            {
                this.$function = function1;
            }
        });
    }

    public static /* bridge */ /* synthetic */ void delete$default(Http http, String string, String string2, Function1 function1, int n, Object object) {
        if ((n & 2) != 0) {
            string2 = http.DEFAULT_ACCEPT;
        }
        http.delete(string, string2, function1);
    }

    public final void delete(@NotNull String path, @NotNull String accepts, @NotNull TemplateEngine templateEngine, @NotNull Function1<? super RouteHandler, ? extends ModelAndView> function) {
        Intrinsics.checkParameterIsNotNull(path, "path");
        Intrinsics.checkParameterIsNotNull(accepts, "accepts");
        Intrinsics.checkParameterIsNotNull(templateEngine, "templateEngine");
        Intrinsics.checkParameterIsNotNull(function, "function");
        this.service.delete(path, accepts, new TemplateViewRoute(function){
            final /* synthetic */ Function1 $function;

            @NotNull
            public final ModelAndView invoke(Request req, Response res) {
                Request request = req;
                Intrinsics.checkExpressionValueIsNotNull(request, "req");
                Response response = res;
                Intrinsics.checkExpressionValueIsNotNull(response, "res");
                return (ModelAndView)this.$function.invoke(new RouteHandler(request, response));
            }
            {
                this.$function = function1;
            }
        }, templateEngine);
    }

    public static /* bridge */ /* synthetic */ void delete$default(Http http, String string, String string2, TemplateEngine templateEngine, Function1 function1, int n, Object object) {
        if ((n & 2) != 0) {
            string2 = http.DEFAULT_ACCEPT;
        }
        http.delete(string, string2, templateEngine, function1);
    }

    public final void delete(@NotNull String path, @NotNull String accepts, @NotNull ResponseTransformer responseTransformer, @NotNull Function1<? super RouteHandler, ? extends Object> function) {
        Intrinsics.checkParameterIsNotNull(path, "path");
        Intrinsics.checkParameterIsNotNull(accepts, "accepts");
        Intrinsics.checkParameterIsNotNull(responseTransformer, "responseTransformer");
        Intrinsics.checkParameterIsNotNull(function, "function");
        this.service.delete(path, accepts, new Route(function){
            final /* synthetic */ Function1 $function;

            public final void invoke(Request req, Response res) {
                Request request = req;
                Intrinsics.checkExpressionValueIsNotNull(request, "req");
                Response response = res;
                Intrinsics.checkExpressionValueIsNotNull(response, "res");
                this.$function.invoke(new RouteHandler(request, response));
            }
            {
                this.$function = function1;
            }
        }, responseTransformer);
    }

    public static /* bridge */ /* synthetic */ void delete$default(Http http, String string, String string2, ResponseTransformer responseTransformer, Function1 function1, int n, Object object) {
        if ((n & 2) != 0) {
            string2 = http.DEFAULT_ACCEPT;
        }
        http.delete(string, string2, responseTransformer, function1);
    }

    public final void head(@NotNull String path, @NotNull String accepts, @NotNull Function1<? super RouteHandler, ? extends Object> function) {
        Intrinsics.checkParameterIsNotNull(path, "path");
        Intrinsics.checkParameterIsNotNull(accepts, "accepts");
        Intrinsics.checkParameterIsNotNull(function, "function");
        this.service.head(path, accepts, new Route(function){
            final /* synthetic */ Function1 $function;

            @NotNull
            public final Object handle(Request req, Response res) {
                Request request = req;
                Intrinsics.checkExpressionValueIsNotNull(request, "req");
                Response response = res;
                Intrinsics.checkExpressionValueIsNotNull(response, "res");
                return this.$function.invoke(new RouteHandler(request, response));
            }
            {
                this.$function = function1;
            }
        });
    }

    public static /* bridge */ /* synthetic */ void head$default(Http http, String string, String string2, Function1 function1, int n, Object object) {
        if ((n & 2) != 0) {
            string2 = http.DEFAULT_ACCEPT;
        }
        http.head(string, string2, function1);
    }

    public final void head(@NotNull String path, @NotNull String accepts, @NotNull TemplateEngine templateEngine, @NotNull Function1<? super RouteHandler, ? extends ModelAndView> function) {
        Intrinsics.checkParameterIsNotNull(path, "path");
        Intrinsics.checkParameterIsNotNull(accepts, "accepts");
        Intrinsics.checkParameterIsNotNull(templateEngine, "templateEngine");
        Intrinsics.checkParameterIsNotNull(function, "function");
        this.service.head(path, accepts, new TemplateViewRoute(function){
            final /* synthetic */ Function1 $function;

            @NotNull
            public final ModelAndView invoke(Request req, Response res) {
                Request request = req;
                Intrinsics.checkExpressionValueIsNotNull(request, "req");
                Response response = res;
                Intrinsics.checkExpressionValueIsNotNull(response, "res");
                return (ModelAndView)this.$function.invoke(new RouteHandler(request, response));
            }
            {
                this.$function = function1;
            }
        }, templateEngine);
    }

    public static /* bridge */ /* synthetic */ void head$default(Http http, String string, String string2, TemplateEngine templateEngine, Function1 function1, int n, Object object) {
        if ((n & 2) != 0) {
            string2 = http.DEFAULT_ACCEPT;
        }
        http.head(string, string2, templateEngine, function1);
    }

    public final void head(@NotNull String path, @NotNull String accepts, @NotNull ResponseTransformer responseTransformer, @NotNull Function1<? super RouteHandler, ? extends Object> function) {
        Intrinsics.checkParameterIsNotNull(path, "path");
        Intrinsics.checkParameterIsNotNull(accepts, "accepts");
        Intrinsics.checkParameterIsNotNull(responseTransformer, "responseTransformer");
        Intrinsics.checkParameterIsNotNull(function, "function");
        this.service.head(path, accepts, new Route(function){
            final /* synthetic */ Function1 $function;

            public final void invoke(Request req, Response res) {
                Request request = req;
                Intrinsics.checkExpressionValueIsNotNull(request, "req");
                Response response = res;
                Intrinsics.checkExpressionValueIsNotNull(response, "res");
                this.$function.invoke(new RouteHandler(request, response));
            }
            {
                this.$function = function1;
            }
        }, responseTransformer);
    }

    public static /* bridge */ /* synthetic */ void head$default(Http http, String string, String string2, ResponseTransformer responseTransformer, Function1 function1, int n, Object object) {
        if ((n & 2) != 0) {
            string2 = http.DEFAULT_ACCEPT;
        }
        http.head(string, string2, responseTransformer, function1);
    }

    public final void trace(@NotNull String path, @NotNull String accepts, @NotNull Function1<? super RouteHandler, ? extends Object> function) {
        Intrinsics.checkParameterIsNotNull(path, "path");
        Intrinsics.checkParameterIsNotNull(accepts, "accepts");
        Intrinsics.checkParameterIsNotNull(function, "function");
        this.service.trace(path, accepts, new Route(function){
            final /* synthetic */ Function1 $function;

            @NotNull
            public final Object handle(Request req, Response res) {
                Request request = req;
                Intrinsics.checkExpressionValueIsNotNull(request, "req");
                Response response = res;
                Intrinsics.checkExpressionValueIsNotNull(response, "res");
                return this.$function.invoke(new RouteHandler(request, response));
            }
            {
                this.$function = function1;
            }
        });
    }

    public static /* bridge */ /* synthetic */ void trace$default(Http http, String string, String string2, Function1 function1, int n, Object object) {
        if ((n & 2) != 0) {
            string2 = http.DEFAULT_ACCEPT;
        }
        http.trace(string, string2, function1);
    }

    public final void trace(@NotNull String path, @NotNull String accepts, @NotNull TemplateEngine templateEngine, @NotNull Function1<? super RouteHandler, ? extends ModelAndView> function) {
        Intrinsics.checkParameterIsNotNull(path, "path");
        Intrinsics.checkParameterIsNotNull(accepts, "accepts");
        Intrinsics.checkParameterIsNotNull(templateEngine, "templateEngine");
        Intrinsics.checkParameterIsNotNull(function, "function");
        this.service.trace(path, accepts, new TemplateViewRoute(function){
            final /* synthetic */ Function1 $function;

            @NotNull
            public final ModelAndView invoke(Request req, Response res) {
                Request request = req;
                Intrinsics.checkExpressionValueIsNotNull(request, "req");
                Response response = res;
                Intrinsics.checkExpressionValueIsNotNull(response, "res");
                return (ModelAndView)this.$function.invoke(new RouteHandler(request, response));
            }
            {
                this.$function = function1;
            }
        }, templateEngine);
    }

    public static /* bridge */ /* synthetic */ void trace$default(Http http, String string, String string2, TemplateEngine templateEngine, Function1 function1, int n, Object object) {
        if ((n & 2) != 0) {
            string2 = http.DEFAULT_ACCEPT;
        }
        http.trace(string, string2, templateEngine, function1);
    }

    public final void trace(@NotNull String path, @NotNull String accepts, @NotNull ResponseTransformer responseTransformer, @NotNull Function1<? super RouteHandler, ? extends Object> function) {
        Intrinsics.checkParameterIsNotNull(path, "path");
        Intrinsics.checkParameterIsNotNull(accepts, "accepts");
        Intrinsics.checkParameterIsNotNull(responseTransformer, "responseTransformer");
        Intrinsics.checkParameterIsNotNull(function, "function");
        this.service.trace(path, accepts, new Route(function){
            final /* synthetic */ Function1 $function;

            public final void invoke(Request req, Response res) {
                Request request = req;
                Intrinsics.checkExpressionValueIsNotNull(request, "req");
                Response response = res;
                Intrinsics.checkExpressionValueIsNotNull(response, "res");
                this.$function.invoke(new RouteHandler(request, response));
            }
            {
                this.$function = function1;
            }
        }, responseTransformer);
    }

    public static /* bridge */ /* synthetic */ void trace$default(Http http, String string, String string2, ResponseTransformer responseTransformer, Function1 function1, int n, Object object) {
        if ((n & 2) != 0) {
            string2 = http.DEFAULT_ACCEPT;
        }
        http.trace(string, string2, responseTransformer, function1);
    }

    public final void options(@NotNull String path, @NotNull String accepts, @NotNull Function1<? super RouteHandler, ? extends Object> function) {
        Intrinsics.checkParameterIsNotNull(path, "path");
        Intrinsics.checkParameterIsNotNull(accepts, "accepts");
        Intrinsics.checkParameterIsNotNull(function, "function");
        this.service.options(path, accepts, new Route(function){
            final /* synthetic */ Function1 $function;

            @NotNull
            public final Object handle(Request req, Response res) {
                Request request = req;
                Intrinsics.checkExpressionValueIsNotNull(request, "req");
                Response response = res;
                Intrinsics.checkExpressionValueIsNotNull(response, "res");
                return this.$function.invoke(new RouteHandler(request, response));
            }
            {
                this.$function = function1;
            }
        });
    }

    public static /* bridge */ /* synthetic */ void options$default(Http http, String string, String string2, Function1 function1, int n, Object object) {
        if ((n & 2) != 0) {
            string2 = http.DEFAULT_ACCEPT;
        }
        http.options(string, string2, function1);
    }

    public final void options(@NotNull String path, @NotNull String accepts, @NotNull TemplateEngine templateEngine, @NotNull Function1<? super RouteHandler, ? extends ModelAndView> function) {
        Intrinsics.checkParameterIsNotNull(path, "path");
        Intrinsics.checkParameterIsNotNull(accepts, "accepts");
        Intrinsics.checkParameterIsNotNull(templateEngine, "templateEngine");
        Intrinsics.checkParameterIsNotNull(function, "function");
        this.service.options(path, accepts, new TemplateViewRoute(function){
            final /* synthetic */ Function1 $function;

            @NotNull
            public final ModelAndView invoke(Request req, Response res) {
                Request request = req;
                Intrinsics.checkExpressionValueIsNotNull(request, "req");
                Response response = res;
                Intrinsics.checkExpressionValueIsNotNull(response, "res");
                return (ModelAndView)this.$function.invoke(new RouteHandler(request, response));
            }
            {
                this.$function = function1;
            }
        }, templateEngine);
    }

    public static /* bridge */ /* synthetic */ void options$default(Http http, String string, String string2, TemplateEngine templateEngine, Function1 function1, int n, Object object) {
        if ((n & 2) != 0) {
            string2 = http.DEFAULT_ACCEPT;
        }
        http.options(string, string2, templateEngine, function1);
    }

    public final void options(@NotNull String path, @NotNull String accepts, @NotNull ResponseTransformer responseTransformer, @NotNull Function1<? super RouteHandler, ? extends Object> function) {
        Intrinsics.checkParameterIsNotNull(path, "path");
        Intrinsics.checkParameterIsNotNull(accepts, "accepts");
        Intrinsics.checkParameterIsNotNull(responseTransformer, "responseTransformer");
        Intrinsics.checkParameterIsNotNull(function, "function");
        this.service.options(path, accepts, new Route(function){
            final /* synthetic */ Function1 $function;

            public final void invoke(Request req, Response res) {
                Request request = req;
                Intrinsics.checkExpressionValueIsNotNull(request, "req");
                Response response = res;
                Intrinsics.checkExpressionValueIsNotNull(response, "res");
                this.$function.invoke(new RouteHandler(request, response));
            }
            {
                this.$function = function1;
            }
        }, responseTransformer);
    }

    public static /* bridge */ /* synthetic */ void options$default(Http http, String string, String string2, ResponseTransformer responseTransformer, Function1 function1, int n, Object object) {
        if ((n & 2) != 0) {
            string2 = http.DEFAULT_ACCEPT;
        }
        http.options(string, string2, responseTransformer, function1);
    }

    public final void patch(@NotNull String path, @NotNull String accepts, @NotNull Function1<? super RouteHandler, ? extends Object> function) {
        Intrinsics.checkParameterIsNotNull(path, "path");
        Intrinsics.checkParameterIsNotNull(accepts, "accepts");
        Intrinsics.checkParameterIsNotNull(function, "function");
        this.service.patch(path, accepts, new Route(function){
            final /* synthetic */ Function1 $function;

            @NotNull
            public final Object handle(Request req, Response res) {
                Request request = req;
                Intrinsics.checkExpressionValueIsNotNull(request, "req");
                Response response = res;
                Intrinsics.checkExpressionValueIsNotNull(response, "res");
                return this.$function.invoke(new RouteHandler(request, response));
            }
            {
                this.$function = function1;
            }
        });
    }

    public static /* bridge */ /* synthetic */ void patch$default(Http http, String string, String string2, Function1 function1, int n, Object object) {
        if ((n & 2) != 0) {
            string2 = http.DEFAULT_ACCEPT;
        }
        http.patch(string, string2, function1);
    }

    public final void patch(@NotNull String path, @NotNull String accepts, @NotNull TemplateEngine templateEngine, @NotNull Function1<? super RouteHandler, ? extends ModelAndView> function) {
        Intrinsics.checkParameterIsNotNull(path, "path");
        Intrinsics.checkParameterIsNotNull(accepts, "accepts");
        Intrinsics.checkParameterIsNotNull(templateEngine, "templateEngine");
        Intrinsics.checkParameterIsNotNull(function, "function");
        this.service.patch(path, accepts, new TemplateViewRoute(function){
            final /* synthetic */ Function1 $function;

            @NotNull
            public final ModelAndView invoke(Request req, Response res) {
                Request request = req;
                Intrinsics.checkExpressionValueIsNotNull(request, "req");
                Response response = res;
                Intrinsics.checkExpressionValueIsNotNull(response, "res");
                return (ModelAndView)this.$function.invoke(new RouteHandler(request, response));
            }
            {
                this.$function = function1;
            }
        }, templateEngine);
    }

    public static /* bridge */ /* synthetic */ void patch$default(Http http, String string, String string2, TemplateEngine templateEngine, Function1 function1, int n, Object object) {
        if ((n & 2) != 0) {
            string2 = http.DEFAULT_ACCEPT;
        }
        http.patch(string, string2, templateEngine, function1);
    }

    public final void patch(@NotNull String path, @NotNull String accepts, @NotNull ResponseTransformer responseTransformer, @NotNull Function1<? super RouteHandler, ? extends Object> function) {
        Intrinsics.checkParameterIsNotNull(path, "path");
        Intrinsics.checkParameterIsNotNull(accepts, "accepts");
        Intrinsics.checkParameterIsNotNull(responseTransformer, "responseTransformer");
        Intrinsics.checkParameterIsNotNull(function, "function");
        this.service.patch(path, accepts, new Route(function){
            final /* synthetic */ Function1 $function;

            public final void invoke(Request req, Response res) {
                Request request = req;
                Intrinsics.checkExpressionValueIsNotNull(request, "req");
                Response response = res;
                Intrinsics.checkExpressionValueIsNotNull(response, "res");
                this.$function.invoke(new RouteHandler(request, response));
            }
            {
                this.$function = function1;
            }
        }, responseTransformer);
    }

    public static /* bridge */ /* synthetic */ void patch$default(Http http, String string, String string2, ResponseTransformer responseTransformer, Function1 function1, int n, Object object) {
        if ((n & 2) != 0) {
            string2 = http.DEFAULT_ACCEPT;
        }
        http.patch(string, string2, responseTransformer, function1);
    }

    public final void connect(@NotNull String path, @NotNull String accepts, @NotNull Function1<? super RouteHandler, ? extends Object> function) {
        Intrinsics.checkParameterIsNotNull(path, "path");
        Intrinsics.checkParameterIsNotNull(accepts, "accepts");
        Intrinsics.checkParameterIsNotNull(function, "function");
        this.service.connect(path, accepts, new Route(function){
            final /* synthetic */ Function1 $function;

            @NotNull
            public final Object handle(Request req, Response res) {
                Request request = req;
                Intrinsics.checkExpressionValueIsNotNull(request, "req");
                Response response = res;
                Intrinsics.checkExpressionValueIsNotNull(response, "res");
                return this.$function.invoke(new RouteHandler(request, response));
            }
            {
                this.$function = function1;
            }
        });
    }

    public static /* bridge */ /* synthetic */ void connect$default(Http http, String string, String string2, Function1 function1, int n, Object object) {
        if ((n & 2) != 0) {
            string2 = http.DEFAULT_ACCEPT;
        }
        http.connect(string, string2, function1);
    }

    public final void connect(@NotNull String path, @NotNull String accepts, @NotNull TemplateEngine templateEngine, @NotNull Function1<? super RouteHandler, ? extends ModelAndView> function) {
        Intrinsics.checkParameterIsNotNull(path, "path");
        Intrinsics.checkParameterIsNotNull(accepts, "accepts");
        Intrinsics.checkParameterIsNotNull(templateEngine, "templateEngine");
        Intrinsics.checkParameterIsNotNull(function, "function");
        this.service.connect(path, accepts, new TemplateViewRoute(function){
            final /* synthetic */ Function1 $function;

            @NotNull
            public final ModelAndView invoke(Request req, Response res) {
                Request request = req;
                Intrinsics.checkExpressionValueIsNotNull(request, "req");
                Response response = res;
                Intrinsics.checkExpressionValueIsNotNull(response, "res");
                return (ModelAndView)this.$function.invoke(new RouteHandler(request, response));
            }
            {
                this.$function = function1;
            }
        }, templateEngine);
    }

    public static /* bridge */ /* synthetic */ void connect$default(Http http, String string, String string2, TemplateEngine templateEngine, Function1 function1, int n, Object object) {
        if ((n & 2) != 0) {
            string2 = http.DEFAULT_ACCEPT;
        }
        http.connect(string, string2, templateEngine, function1);
    }

    public final void connect(@NotNull String path, @NotNull String accepts, @NotNull ResponseTransformer responseTransformer, @NotNull Function1<? super RouteHandler, ? extends Object> function) {
        Intrinsics.checkParameterIsNotNull(path, "path");
        Intrinsics.checkParameterIsNotNull(accepts, "accepts");
        Intrinsics.checkParameterIsNotNull(responseTransformer, "responseTransformer");
        Intrinsics.checkParameterIsNotNull(function, "function");
        this.service.connect(path, accepts, new Route(function){
            final /* synthetic */ Function1 $function;

            public final void invoke(Request req, Response res) {
                Request request = req;
                Intrinsics.checkExpressionValueIsNotNull(request, "req");
                Response response = res;
                Intrinsics.checkExpressionValueIsNotNull(response, "res");
                this.$function.invoke(new RouteHandler(request, response));
            }
            {
                this.$function = function1;
            }
        }, responseTransformer);
    }

    public static /* bridge */ /* synthetic */ void connect$default(Http http, String string, String string2, ResponseTransformer responseTransformer, Function1 function1, int n, Object object) {
        if ((n & 2) != 0) {
            string2 = http.DEFAULT_ACCEPT;
        }
        http.connect(string, string2, responseTransformer, function1);
    }

    public final void before(@NotNull Filter filter2, @NotNull String accepts) {
        Intrinsics.checkParameterIsNotNull(filter2, "filter");
        Intrinsics.checkParameterIsNotNull(accepts, "accepts");
        this.service.before(filter2);
    }

    public static /* bridge */ /* synthetic */ void before$default(Http http, Filter filter2, String string, int n, Object object) {
        if ((n & 2) != 0) {
            string = http.DEFAULT_ACCEPT;
        }
        http.before(filter2, string);
    }

    public final void before(@Nullable String path, @NotNull String accepts, @NotNull Function1<? super RouteHandler, Unit> function) {
        Intrinsics.checkParameterIsNotNull(accepts, "accepts");
        Intrinsics.checkParameterIsNotNull(function, "function");
        Filter filter2 = new Filter(function){
            final /* synthetic */ Function1 $function;

            public final void invoke(Request req, Response res) {
                Request request = req;
                Intrinsics.checkExpressionValueIsNotNull(request, "req");
                Response response = res;
                Intrinsics.checkExpressionValueIsNotNull(response, "res");
                this.$function.invoke(new RouteHandler(request, response));
            }
            {
                this.$function = function1;
            }
        };
        if (path == null) {
            this.service.before(filter2);
        } else {
            this.service.before(path, accepts, filter2);
        }
    }

    public static /* bridge */ /* synthetic */ void before$default(Http http, String string, String string2, Function1 function1, int n, Object object) {
        if ((n & 1) != 0) {
            string = null;
        }
        if ((n & 2) != 0) {
            string2 = http.DEFAULT_ACCEPT;
        }
        http.before(string, string2, function1);
    }

    public final void after(@Nullable String path, @NotNull String accepts, @NotNull Function1<? super RouteHandler, Unit> function) {
        Intrinsics.checkParameterIsNotNull(accepts, "accepts");
        Intrinsics.checkParameterIsNotNull(function, "function");
        Filter filter2 = new Filter(function){
            final /* synthetic */ Function1 $function;

            public final void invoke(Request req, Response res) {
                Request request = req;
                Intrinsics.checkExpressionValueIsNotNull(request, "req");
                Response response = res;
                Intrinsics.checkExpressionValueIsNotNull(response, "res");
                this.$function.invoke(new RouteHandler(request, response));
            }
            {
                this.$function = function1;
            }
        };
        if (path == null) {
            this.service.after(filter2);
        } else {
            this.service.after(path, accepts, filter2);
        }
    }

    public static /* bridge */ /* synthetic */ void after$default(Http http, String string, String string2, Function1 function1, int n, Object object) {
        if ((n & 1) != 0) {
            string = null;
        }
        if ((n & 2) != 0) {
            string2 = http.DEFAULT_ACCEPT;
        }
        http.after(string, string2, function1);
    }

    public final void finally(@Nullable String path, @NotNull Function1<? super RouteHandler, Unit> function) {
        Intrinsics.checkParameterIsNotNull(function, "function");
        Filter filter2 = new Filter(function){
            final /* synthetic */ Function1 $function;

            public final void invoke(Request req, Response res) {
                Request request = req;
                Intrinsics.checkExpressionValueIsNotNull(request, "req");
                Response response = res;
                Intrinsics.checkExpressionValueIsNotNull(response, "res");
                this.$function.invoke(new RouteHandler(request, response));
            }
            {
                this.$function = function1;
            }
        };
        if (path == null) {
            this.service.afterAfter(filter2);
        } else {
            this.service.afterAfter(path, filter2);
        }
    }

    public static /* bridge */ /* synthetic */ void finally$default(Http http, String string, Function1 function1, int n, Object object) {
        if ((n & 1) != 0) {
            string = null;
        }
        http.finally(string, function1);
    }

    public final void notFound(@NotNull Function1<? super RouteHandler, ? extends Object> function) {
        Intrinsics.checkParameterIsNotNull(function, "function");
        this.service.notFound(new Route(function){
            final /* synthetic */ Function1 $function;

            @NotNull
            public final Object handle(Request req, Response res) {
                Request request = req;
                Intrinsics.checkExpressionValueIsNotNull(request, "req");
                Response response = res;
                Intrinsics.checkExpressionValueIsNotNull(response, "res");
                return this.$function.invoke(new RouteHandler(request, response));
            }
            {
                this.$function = function1;
            }
        });
    }

    public final void internalServerError(@NotNull Function1<? super RouteHandler, ? extends Object> function) {
        Intrinsics.checkParameterIsNotNull(function, "function");
        this.service.internalServerError(new Route(function){
            final /* synthetic */ Function1 $function;

            @NotNull
            public final Object handle(Request req, Response res) {
                Request request = req;
                Intrinsics.checkExpressionValueIsNotNull(request, "req");
                Response response = res;
                Intrinsics.checkExpressionValueIsNotNull(response, "res");
                return this.$function.invoke(new RouteHandler(request, response));
            }
            {
                this.$function = function1;
            }
        });
    }

    @NotNull
    public final Service.StaticFiles getStaticFiles() {
        return this.staticFiles;
    }

    @NotNull
    public final Redirect getRedirect() {
        return this.redirect;
    }

    public final int port() {
        return this.service.port();
    }

    @NotNull
    public final Http port(int number) {
        this.service.port(number);
        return this;
    }

    @NotNull
    public final Http secure(@NotNull String keyStoreFile, @NotNull String keyStorePassword, @NotNull String truststoreFile, @NotNull String truststorePassword) {
        Intrinsics.checkParameterIsNotNull(keyStoreFile, "keyStoreFile");
        Intrinsics.checkParameterIsNotNull(keyStorePassword, "keyStorePassword");
        Intrinsics.checkParameterIsNotNull(truststoreFile, "truststoreFile");
        Intrinsics.checkParameterIsNotNull(truststorePassword, "truststorePassword");
        this.service.secure(keyStoreFile, keyStorePassword, truststoreFile, truststorePassword);
        return this;
    }

    @NotNull
    public final Http secure(@NotNull String keyStoreFile, @NotNull String keyStorePassword, @NotNull String truststoreFile, @NotNull String truststorePassword, boolean needsClientCert) {
        Intrinsics.checkParameterIsNotNull(keyStoreFile, "keyStoreFile");
        Intrinsics.checkParameterIsNotNull(keyStorePassword, "keyStorePassword");
        Intrinsics.checkParameterIsNotNull(truststoreFile, "truststoreFile");
        Intrinsics.checkParameterIsNotNull(truststorePassword, "truststorePassword");
        this.service.secure(keyStoreFile, keyStorePassword, truststoreFile, truststorePassword, needsClientCert);
        return this;
    }

    @NotNull
    public final Http ipAddress(@NotNull String ipAddress) {
        Intrinsics.checkParameterIsNotNull(ipAddress, "ipAddress");
        this.service.ipAddress(ipAddress);
        return this;
    }

    @NotNull
    public final Http threadPool(int maxSize) {
        this.service.threadPool(maxSize);
        return this;
    }

    @NotNull
    public final Http threadPool(int maxSize, int minSize, int idleTimeoutMillis) {
        this.service.threadPool(maxSize, minSize, idleTimeoutMillis);
        return this;
    }

    public final void stop() {
        this.service.stop();
    }

    @NotNull
    public final Service getService() {
        return this.service;
    }

    public Http(@NotNull Service service) {
        Intrinsics.checkParameterIsNotNull(service, "service");
        this.service = service;
        this.DEFAULT_ACCEPT = "*/*";
        Service.StaticFiles staticFiles = this.service.staticFiles;
        Intrinsics.checkExpressionValueIsNotNull(staticFiles, "service.staticFiles");
        this.staticFiles = staticFiles;
        Redirect redirect = this.service.redirect;
        Intrinsics.checkExpressionValueIsNotNull(redirect, "service.redirect");
        this.redirect = redirect;
    }
}

