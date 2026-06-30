/*
 * Decompiled with CFR 0.152.
 */
package info.journeymap.shaded.kotlin.spark.kotlin;

import info.journeymap.shaded.kotlin.kotlin.Metadata;
import info.journeymap.shaded.kotlin.kotlin.Unit;
import info.journeymap.shaded.kotlin.kotlin.jvm.functions.Function1;
import info.journeymap.shaded.kotlin.kotlin.jvm.internal.Intrinsics;
import info.journeymap.shaded.kotlin.spark.Filter;
import info.journeymap.shaded.kotlin.spark.HaltException;
import info.journeymap.shaded.kotlin.spark.ModelAndView;
import info.journeymap.shaded.kotlin.spark.Redirect;
import info.journeymap.shaded.kotlin.spark.Request;
import info.journeymap.shaded.kotlin.spark.Response;
import info.journeymap.shaded.kotlin.spark.ResponseTransformer;
import info.journeymap.shaded.kotlin.spark.Route;
import info.journeymap.shaded.kotlin.spark.Service;
import info.journeymap.shaded.kotlin.spark.Spark;
import info.journeymap.shaded.kotlin.spark.TemplateEngine;
import info.journeymap.shaded.kotlin.spark.TemplateViewRoute;
import info.journeymap.shaded.kotlin.spark.kotlin.Http;
import info.journeymap.shaded.kotlin.spark.kotlin.RouteHandler;
import info.journeymap.shaded.org.jetbrains.annotations.NotNull;
import info.journeymap.shaded.org.jetbrains.annotations.Nullable;

/*
 * Illegal identifiers - consider using --renameillegalidents true
 */
@Metadata(mv={1, 1, 6}, bv={1, 0, 1}, k=2, d1={"\u0000r\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u000f\n\u0002\u0010\u000b\n\u0002\b\u0007\u001a5\u0010\r\u001a\u00020\u000e2\n\b\u0002\u0010\u000f\u001a\u0004\u0018\u00010\u00012\b\b\u0002\u0010\u0010\u001a\u00020\u00012\u0017\u0010\u0011\u001a\u0013\u0012\u0004\u0012\u00020\u0013\u0012\u0004\u0012\u00020\u000e0\u0012\u00a2\u0006\u0002\b\u0014\u001a5\u0010\u0015\u001a\u00020\u000e2\n\b\u0002\u0010\u000f\u001a\u0004\u0018\u00010\u00012\b\b\u0002\u0010\u0010\u001a\u00020\u00012\u0017\u0010\u0011\u001a\u0013\u0012\u0004\u0012\u00020\u0013\u0012\u0004\u0012\u00020\u000e0\u0012\u00a2\u0006\u0002\b\u0014\u001a\u0018\u0010\u0015\u001a\u00020\u000e2\u0006\u0010\u0016\u001a\u00020\u00172\b\b\u0002\u0010\u0010\u001a\u00020\u0001\u001a9\u0010\u0018\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u00012\b\b\u0002\u0010\u0010\u001a\u00020\u00012\u0006\u0010\u0019\u001a\u00020\u001a2\u0017\u0010\u0011\u001a\u0013\u0012\u0004\u0012\u00020\u0013\u0012\u0004\u0012\u00020\u001b0\u0012\u00a2\u0006\u0002\b\u0014\u001a9\u0010\u0018\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u00012\b\b\u0002\u0010\u0010\u001a\u00020\u00012\u0006\u0010\u001c\u001a\u00020\u001d2\u0017\u0010\u0011\u001a\u0013\u0012\u0004\u0012\u00020\u0013\u0012\u0004\u0012\u00020\u001e0\u0012\u00a2\u0006\u0002\b\u0014\u001a1\u0010\u0018\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u00012\b\b\u0002\u0010\u0010\u001a\u00020\u00012\u0017\u0010\u0011\u001a\u0013\u0012\u0004\u0012\u00020\u0013\u0012\u0004\u0012\u00020\u001b0\u0012\u00a2\u0006\u0002\b\u0014\u001a9\u0010\u001f\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u00012\b\b\u0002\u0010\u0010\u001a\u00020\u00012\u0006\u0010\u0019\u001a\u00020\u001a2\u0017\u0010\u0011\u001a\u0013\u0012\u0004\u0012\u00020\u0013\u0012\u0004\u0012\u00020\u001b0\u0012\u00a2\u0006\u0002\b\u0014\u001a9\u0010\u001f\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u00012\b\b\u0002\u0010\u0010\u001a\u00020\u00012\u0006\u0010\u001c\u001a\u00020\u001d2\u0017\u0010\u0011\u001a\u0013\u0012\u0004\u0012\u00020\u0013\u0012\u0004\u0012\u00020\u001e0\u0012\u00a2\u0006\u0002\b\u0014\u001a1\u0010\u001f\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u00012\b\b\u0002\u0010\u0010\u001a\u00020\u00012\u0017\u0010\u0011\u001a\u0013\u0012\u0004\u0012\u00020\u0013\u0012\u0004\u0012\u00020\u001b0\u0012\u00a2\u0006\u0002\b\u0014\u001a+\u0010 \u001a\u00020\u000e2\n\b\u0002\u0010\u000f\u001a\u0004\u0018\u00010\u00012\u0017\u0010\u0011\u001a\u0013\u0012\u0004\u0012\u00020\u0013\u0012\u0004\u0012\u00020\u000e0\u0012\u00a2\u0006\u0002\b\u0014\u001a9\u0010!\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u00012\b\b\u0002\u0010\u0010\u001a\u00020\u00012\u0006\u0010\u0019\u001a\u00020\u001a2\u0017\u0010\u0011\u001a\u0013\u0012\u0004\u0012\u00020\u0013\u0012\u0004\u0012\u00020\u001b0\u0012\u00a2\u0006\u0002\b\u0014\u001a9\u0010!\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u00012\b\b\u0002\u0010\u0010\u001a\u00020\u00012\u0006\u0010\u001c\u001a\u00020\u001d2\u0017\u0010\u0011\u001a\u0013\u0012\u0004\u0012\u00020\u0013\u0012\u0004\u0012\u00020\u001e0\u0012\u00a2\u0006\u0002\b\u0014\u001a1\u0010!\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u00012\b\b\u0002\u0010\u0010\u001a\u00020\u00012\u0017\u0010\u0011\u001a\u0013\u0012\u0004\u0012\u00020\u0013\u0012\u0004\u0012\u00020\u001b0\u0012\u00a2\u0006\u0002\b\u0014\u001a\u0006\u0010\"\u001a\u00020#\u001a\u000e\u0010\"\u001a\u00020#2\u0006\u0010$\u001a\u00020%\u001a\u0016\u0010\"\u001a\u00020#2\u0006\u0010$\u001a\u00020%2\u0006\u0010&\u001a\u00020\u0001\u001a\u000e\u0010\"\u001a\u00020#2\u0006\u0010&\u001a\u00020\u0001\u001a9\u0010'\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u00012\b\b\u0002\u0010\u0010\u001a\u00020\u00012\u0006\u0010\u0019\u001a\u00020\u001a2\u0017\u0010\u0011\u001a\u0013\u0012\u0004\u0012\u00020\u0013\u0012\u0004\u0012\u00020\u001b0\u0012\u00a2\u0006\u0002\b\u0014\u001a9\u0010'\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u00012\b\b\u0002\u0010\u0010\u001a\u00020\u00012\u0006\u0010\u001c\u001a\u00020\u001d2\u0017\u0010\u0011\u001a\u0013\u0012\u0004\u0012\u00020\u0013\u0012\u0004\u0012\u00020\u001e0\u0012\u00a2\u0006\u0002\b\u0014\u001a1\u0010'\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u00012\b\b\u0002\u0010\u0010\u001a\u00020\u00012\u0017\u0010\u0011\u001a\u0013\u0012\u0004\u0012\u00020\u0013\u0012\u0004\u0012\u00020\u001b0\u0012\u00a2\u0006\u0002\b\u0014\u001a\u0006\u0010(\u001a\u00020)\u001a\u001f\u0010*\u001a\u00020\u000e2\u0017\u0010\u0011\u001a\u0013\u0012\u0004\u0012\u00020\u0013\u0012\u0004\u0012\u00020\u001b0\u0012\u00a2\u0006\u0002\b\u0014\u001a\u000e\u0010+\u001a\u00020\u000e2\u0006\u0010+\u001a\u00020\u0001\u001a\u001f\u0010,\u001a\u00020\u000e2\u0017\u0010\u0011\u001a\u0013\u0012\u0004\u0012\u00020\u0013\u0012\u0004\u0012\u00020\u001b0\u0012\u00a2\u0006\u0002\b\u0014\u001a9\u0010-\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u00012\b\b\u0002\u0010\u0010\u001a\u00020\u00012\u0006\u0010\u0019\u001a\u00020\u001a2\u0017\u0010\u0011\u001a\u0013\u0012\u0004\u0012\u00020\u0013\u0012\u0004\u0012\u00020\u001b0\u0012\u00a2\u0006\u0002\b\u0014\u001a9\u0010-\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u00012\b\b\u0002\u0010\u0010\u001a\u00020\u00012\u0006\u0010\u001c\u001a\u00020\u001d2\u0017\u0010\u0011\u001a\u0013\u0012\u0004\u0012\u00020\u0013\u0012\u0004\u0012\u00020\u001e0\u0012\u00a2\u0006\u0002\b\u0014\u001a1\u0010-\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u00012\b\b\u0002\u0010\u0010\u001a\u00020\u00012\u0017\u0010\u0011\u001a\u0013\u0012\u0004\u0012\u00020\u0013\u0012\u0004\u0012\u00020\u001b0\u0012\u00a2\u0006\u0002\b\u0014\u001a9\u0010.\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u00012\b\b\u0002\u0010\u0010\u001a\u00020\u00012\u0006\u0010\u0019\u001a\u00020\u001a2\u0017\u0010\u0011\u001a\u0013\u0012\u0004\u0012\u00020\u0013\u0012\u0004\u0012\u00020\u001b0\u0012\u00a2\u0006\u0002\b\u0014\u001a9\u0010.\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u00012\b\b\u0002\u0010\u0010\u001a\u00020\u00012\u0006\u0010\u001c\u001a\u00020\u001d2\u0017\u0010\u0011\u001a\u0013\u0012\u0004\u0012\u00020\u0013\u0012\u0004\u0012\u00020\u001e0\u0012\u00a2\u0006\u0002\b\u0014\u001a1\u0010.\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u00012\b\b\u0002\u0010\u0010\u001a\u00020\u00012\u0017\u0010\u0011\u001a\u0013\u0012\u0004\u0012\u00020\u0013\u0012\u0004\u0012\u00020\u001b0\u0012\u00a2\u0006\u0002\b\u0014\u001a\u0006\u0010/\u001a\u00020%\u001a\u000e\u0010/\u001a\u00020\u000e2\u0006\u00100\u001a\u00020%\u001a9\u00101\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u00012\b\b\u0002\u0010\u0010\u001a\u00020\u00012\u0006\u0010\u0019\u001a\u00020\u001a2\u0017\u0010\u0011\u001a\u0013\u0012\u0004\u0012\u00020\u0013\u0012\u0004\u0012\u00020\u001b0\u0012\u00a2\u0006\u0002\b\u0014\u001a9\u00101\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u00012\b\b\u0002\u0010\u0010\u001a\u00020\u00012\u0006\u0010\u001c\u001a\u00020\u001d2\u0017\u0010\u0011\u001a\u0013\u0012\u0004\u0012\u00020\u0013\u0012\u0004\u0012\u00020\u001e0\u0012\u00a2\u0006\u0002\b\u0014\u001a1\u00101\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u00012\b\b\u0002\u0010\u0010\u001a\u00020\u00012\u0017\u0010\u0011\u001a\u0013\u0012\u0004\u0012\u00020\u0013\u0012\u0004\u0012\u00020\u001b0\u0012\u00a2\u0006\u0002\b\u0014\u001a9\u00102\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u00012\b\b\u0002\u0010\u0010\u001a\u00020\u00012\u0006\u0010\u0019\u001a\u00020\u001a2\u0017\u0010\u0011\u001a\u0013\u0012\u0004\u0012\u00020\u0013\u0012\u0004\u0012\u00020\u001b0\u0012\u00a2\u0006\u0002\b\u0014\u001a9\u00102\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u00012\b\b\u0002\u0010\u0010\u001a\u00020\u00012\u0006\u0010\u001c\u001a\u00020\u001d2\u0017\u0010\u0011\u001a\u0013\u0012\u0004\u0012\u00020\u0013\u0012\u0004\u0012\u00020\u001e0\u0012\u00a2\u0006\u0002\b\u0014\u001a1\u00102\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u00012\b\b\u0002\u0010\u0010\u001a\u00020\u00012\u0017\u0010\u0011\u001a\u0013\u0012\u0004\u0012\u00020\u0013\u0012\u0004\u0012\u00020\u001b0\u0012\u00a2\u0006\u0002\b\u0014\u001a&\u00103\u001a\u00020\u000e2\u0006\u00104\u001a\u00020\u00012\u0006\u00105\u001a\u00020\u00012\u0006\u00106\u001a\u00020\u00012\u0006\u00107\u001a\u00020\u0001\u001a.\u00103\u001a\u00020\u000e2\u0006\u00104\u001a\u00020\u00012\u0006\u00105\u001a\u00020\u00012\u0006\u00106\u001a\u00020\u00012\u0006\u00107\u001a\u00020\u00012\u0006\u00108\u001a\u000209\u001a\u0006\u0010:\u001a\u00020\u000e\u001a\u000e\u0010;\u001a\u00020\u000e2\u0006\u0010<\u001a\u00020%\u001a\u001e\u0010;\u001a\u00020\u000e2\u0006\u0010<\u001a\u00020%2\u0006\u0010=\u001a\u00020%2\u0006\u0010>\u001a\u00020%\u001a9\u0010?\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u00012\b\b\u0002\u0010\u0010\u001a\u00020\u00012\u0006\u0010\u0019\u001a\u00020\u001a2\u0017\u0010\u0011\u001a\u0013\u0012\u0004\u0012\u00020\u0013\u0012\u0004\u0012\u00020\u001b0\u0012\u00a2\u0006\u0002\b\u0014\u001a9\u0010?\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u00012\b\b\u0002\u0010\u0010\u001a\u00020\u00012\u0006\u0010\u001c\u001a\u00020\u001d2\u0017\u0010\u0011\u001a\u0013\u0012\u0004\u0012\u00020\u0013\u0012\u0004\u0012\u00020\u001e0\u0012\u00a2\u0006\u0002\b\u0014\u001a1\u0010?\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u00012\b\b\u0002\u0010\u0010\u001a\u00020\u00012\u0017\u0010\u0011\u001a\u0013\u0012\u0004\u0012\u00020\u0013\u0012\u0004\u0012\u00020\u001b0\u0012\u00a2\u0006\u0002\b\u0014\"\u0014\u0010\u0000\u001a\u00020\u0001X\u0086D\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0002\u0010\u0003\"\u0011\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007\"\u0015\u0010\b\u001a\u00060\tR\u00020\n\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\f\u00a8\u0006@"}, d2={"DEFAULT_ACCEPT", "", "getDEFAULT_ACCEPT", "()Ljava/lang/String;", "redirect", "Linfo/journeymap/shaded/kotlin/spark/Redirect;", "getRedirect", "()Lspark/Redirect;", "staticFiles", "Linfo/journeymap/shaded/kotlin/spark/Service$StaticFiles;", "Linfo/journeymap/shaded/kotlin/spark/Service;", "getStaticFiles", "()Lspark/Service$StaticFiles;", "after", "", "path", "accepts", "function", "Linfo/journeymap/shaded/kotlin/kotlin/Function1;", "Linfo/journeymap/shaded/kotlin/spark/kotlin/RouteHandler;", "Linfo/journeymap/shaded/kotlin/kotlin/ExtensionFunctionType;", "before", "filter", "Linfo/journeymap/shaded/kotlin/spark/Filter;", "connect", "responseTransformer", "Linfo/journeymap/shaded/kotlin/spark/ResponseTransformer;", "", "templateEngine", "Linfo/journeymap/shaded/kotlin/spark/TemplateEngine;", "Linfo/journeymap/shaded/kotlin/spark/ModelAndView;", "delete", "finally", "get", "halt", "Linfo/journeymap/shaded/kotlin/spark/HaltException;", "code", "", "body", "head", "ignite", "Linfo/journeymap/shaded/kotlin/spark/kotlin/Http;", "internalServerError", "ipAddress", "notFound", "options", "patch", "port", "number", "post", "put", "secure", "keyStoreFile", "keyStorePassword", "truststoreFile", "truststorePassword", "needsClientCert", "", "stop", "threadPool", "maxSize", "minSize", "idleTimeoutMillis", "trace", "info.journeymap.shaded.kotlin.spark-kotlin"})
public final class HttpKt {
    @NotNull
    private static final String DEFAULT_ACCEPT = "*/*";
    @NotNull
    private static final Service.StaticFiles staticFiles;
    @NotNull
    private static final Redirect redirect;

    @NotNull
    public static final String getDEFAULT_ACCEPT() {
        return DEFAULT_ACCEPT;
    }

    @NotNull
    public static final Service.StaticFiles getStaticFiles() {
        return staticFiles;
    }

    @NotNull
    public static final Redirect getRedirect() {
        return redirect;
    }

    public static final int port() {
        return Spark.port();
    }

    public static final void port(int number) {
        Spark.port(number);
    }

    public static final void secure(@NotNull String keyStoreFile, @NotNull String keyStorePassword, @NotNull String truststoreFile, @NotNull String truststorePassword) {
        Intrinsics.checkParameterIsNotNull(keyStoreFile, "keyStoreFile");
        Intrinsics.checkParameterIsNotNull(keyStorePassword, "keyStorePassword");
        Intrinsics.checkParameterIsNotNull(truststoreFile, "truststoreFile");
        Intrinsics.checkParameterIsNotNull(truststorePassword, "truststorePassword");
        Spark.secure(keyStoreFile, keyStorePassword, truststoreFile, truststorePassword);
    }

    public static final void secure(@NotNull String keyStoreFile, @NotNull String keyStorePassword, @NotNull String truststoreFile, @NotNull String truststorePassword, boolean needsClientCert) {
        Intrinsics.checkParameterIsNotNull(keyStoreFile, "keyStoreFile");
        Intrinsics.checkParameterIsNotNull(keyStorePassword, "keyStorePassword");
        Intrinsics.checkParameterIsNotNull(truststoreFile, "truststoreFile");
        Intrinsics.checkParameterIsNotNull(truststorePassword, "truststorePassword");
        Spark.secure(keyStoreFile, keyStorePassword, truststoreFile, truststorePassword, needsClientCert);
    }

    public static final void ipAddress(@NotNull String ipAddress) {
        Intrinsics.checkParameterIsNotNull(ipAddress, "ipAddress");
        Spark.ipAddress(ipAddress);
    }

    public static final void threadPool(int maxSize) {
        Spark.threadPool(maxSize);
    }

    public static final void threadPool(int maxSize, int minSize, int idleTimeoutMillis) {
        Spark.threadPool(maxSize, minSize, idleTimeoutMillis);
    }

    public static final void get(@NotNull String path, @NotNull String accepts, @NotNull Function1<? super RouteHandler, ? extends Object> function) {
        Intrinsics.checkParameterIsNotNull(path, "path");
        Intrinsics.checkParameterIsNotNull(accepts, "accepts");
        Intrinsics.checkParameterIsNotNull(function, "function");
        Spark.get(path, accepts, new Route(function){
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

    public static /* bridge */ /* synthetic */ void get$default(String string, String string2, Function1 function1, int n, Object object) {
        if ((n & 2) != 0) {
            string2 = DEFAULT_ACCEPT;
        }
        HttpKt.get(string, string2, function1);
    }

    public static final void get(@NotNull String path, @NotNull String accepts, @NotNull TemplateEngine templateEngine, @NotNull Function1<? super RouteHandler, ? extends ModelAndView> function) {
        Intrinsics.checkParameterIsNotNull(path, "path");
        Intrinsics.checkParameterIsNotNull(accepts, "accepts");
        Intrinsics.checkParameterIsNotNull(templateEngine, "templateEngine");
        Intrinsics.checkParameterIsNotNull(function, "function");
        Spark.get(path, accepts, new TemplateViewRoute(function){
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

    public static /* bridge */ /* synthetic */ void get$default(String string, String string2, TemplateEngine templateEngine, Function1 function1, int n, Object object) {
        if ((n & 2) != 0) {
            string2 = DEFAULT_ACCEPT;
        }
        HttpKt.get(string, string2, templateEngine, function1);
    }

    public static final void get(@NotNull String path, @NotNull String accepts, @NotNull ResponseTransformer responseTransformer, @NotNull Function1<? super RouteHandler, ? extends Object> function) {
        Intrinsics.checkParameterIsNotNull(path, "path");
        Intrinsics.checkParameterIsNotNull(accepts, "accepts");
        Intrinsics.checkParameterIsNotNull(responseTransformer, "responseTransformer");
        Intrinsics.checkParameterIsNotNull(function, "function");
        Spark.get(path, accepts, new Route(function){
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

    public static /* bridge */ /* synthetic */ void get$default(String string, String string2, ResponseTransformer responseTransformer, Function1 function1, int n, Object object) {
        if ((n & 2) != 0) {
            string2 = DEFAULT_ACCEPT;
        }
        HttpKt.get(string, string2, responseTransformer, function1);
    }

    public static final void post(@NotNull String path, @NotNull String accepts, @NotNull Function1<? super RouteHandler, ? extends Object> function) {
        Intrinsics.checkParameterIsNotNull(path, "path");
        Intrinsics.checkParameterIsNotNull(accepts, "accepts");
        Intrinsics.checkParameterIsNotNull(function, "function");
        Spark.post(path, accepts, new Route(function){
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

    public static /* bridge */ /* synthetic */ void post$default(String string, String string2, Function1 function1, int n, Object object) {
        if ((n & 2) != 0) {
            string2 = DEFAULT_ACCEPT;
        }
        HttpKt.post(string, string2, function1);
    }

    public static final void post(@NotNull String path, @NotNull String accepts, @NotNull TemplateEngine templateEngine, @NotNull Function1<? super RouteHandler, ? extends ModelAndView> function) {
        Intrinsics.checkParameterIsNotNull(path, "path");
        Intrinsics.checkParameterIsNotNull(accepts, "accepts");
        Intrinsics.checkParameterIsNotNull(templateEngine, "templateEngine");
        Intrinsics.checkParameterIsNotNull(function, "function");
        Spark.post(path, accepts, new TemplateViewRoute(function){
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

    public static /* bridge */ /* synthetic */ void post$default(String string, String string2, TemplateEngine templateEngine, Function1 function1, int n, Object object) {
        if ((n & 2) != 0) {
            string2 = DEFAULT_ACCEPT;
        }
        HttpKt.post(string, string2, templateEngine, function1);
    }

    public static final void post(@NotNull String path, @NotNull String accepts, @NotNull ResponseTransformer responseTransformer, @NotNull Function1<? super RouteHandler, ? extends Object> function) {
        Intrinsics.checkParameterIsNotNull(path, "path");
        Intrinsics.checkParameterIsNotNull(accepts, "accepts");
        Intrinsics.checkParameterIsNotNull(responseTransformer, "responseTransformer");
        Intrinsics.checkParameterIsNotNull(function, "function");
        Spark.post(path, accepts, new Route(function){
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

    public static /* bridge */ /* synthetic */ void post$default(String string, String string2, ResponseTransformer responseTransformer, Function1 function1, int n, Object object) {
        if ((n & 2) != 0) {
            string2 = DEFAULT_ACCEPT;
        }
        HttpKt.post(string, string2, responseTransformer, function1);
    }

    public static final void put(@NotNull String path, @NotNull String accepts, @NotNull Function1<? super RouteHandler, ? extends Object> function) {
        Intrinsics.checkParameterIsNotNull(path, "path");
        Intrinsics.checkParameterIsNotNull(accepts, "accepts");
        Intrinsics.checkParameterIsNotNull(function, "function");
        Spark.put(path, accepts, new Route(function){
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

    public static /* bridge */ /* synthetic */ void put$default(String string, String string2, Function1 function1, int n, Object object) {
        if ((n & 2) != 0) {
            string2 = DEFAULT_ACCEPT;
        }
        HttpKt.put(string, string2, function1);
    }

    public static final void put(@NotNull String path, @NotNull String accepts, @NotNull TemplateEngine templateEngine, @NotNull Function1<? super RouteHandler, ? extends ModelAndView> function) {
        Intrinsics.checkParameterIsNotNull(path, "path");
        Intrinsics.checkParameterIsNotNull(accepts, "accepts");
        Intrinsics.checkParameterIsNotNull(templateEngine, "templateEngine");
        Intrinsics.checkParameterIsNotNull(function, "function");
        Spark.put(path, accepts, new TemplateViewRoute(function){
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

    public static /* bridge */ /* synthetic */ void put$default(String string, String string2, TemplateEngine templateEngine, Function1 function1, int n, Object object) {
        if ((n & 2) != 0) {
            string2 = DEFAULT_ACCEPT;
        }
        HttpKt.put(string, string2, templateEngine, function1);
    }

    public static final void put(@NotNull String path, @NotNull String accepts, @NotNull ResponseTransformer responseTransformer, @NotNull Function1<? super RouteHandler, ? extends Object> function) {
        Intrinsics.checkParameterIsNotNull(path, "path");
        Intrinsics.checkParameterIsNotNull(accepts, "accepts");
        Intrinsics.checkParameterIsNotNull(responseTransformer, "responseTransformer");
        Intrinsics.checkParameterIsNotNull(function, "function");
        Spark.post(path, accepts, new Route(function){
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

    public static /* bridge */ /* synthetic */ void put$default(String string, String string2, ResponseTransformer responseTransformer, Function1 function1, int n, Object object) {
        if ((n & 2) != 0) {
            string2 = DEFAULT_ACCEPT;
        }
        HttpKt.put(string, string2, responseTransformer, function1);
    }

    public static final void delete(@NotNull String path, @NotNull String accepts, @NotNull Function1<? super RouteHandler, ? extends Object> function) {
        Intrinsics.checkParameterIsNotNull(path, "path");
        Intrinsics.checkParameterIsNotNull(accepts, "accepts");
        Intrinsics.checkParameterIsNotNull(function, "function");
        Spark.delete(path, accepts, new Route(function){
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

    public static /* bridge */ /* synthetic */ void delete$default(String string, String string2, Function1 function1, int n, Object object) {
        if ((n & 2) != 0) {
            string2 = DEFAULT_ACCEPT;
        }
        HttpKt.delete(string, string2, function1);
    }

    public static final void delete(@NotNull String path, @NotNull String accepts, @NotNull TemplateEngine templateEngine, @NotNull Function1<? super RouteHandler, ? extends ModelAndView> function) {
        Intrinsics.checkParameterIsNotNull(path, "path");
        Intrinsics.checkParameterIsNotNull(accepts, "accepts");
        Intrinsics.checkParameterIsNotNull(templateEngine, "templateEngine");
        Intrinsics.checkParameterIsNotNull(function, "function");
        Spark.delete(path, accepts, new TemplateViewRoute(function){
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

    public static /* bridge */ /* synthetic */ void delete$default(String string, String string2, TemplateEngine templateEngine, Function1 function1, int n, Object object) {
        if ((n & 2) != 0) {
            string2 = DEFAULT_ACCEPT;
        }
        HttpKt.delete(string, string2, templateEngine, function1);
    }

    public static final void delete(@NotNull String path, @NotNull String accepts, @NotNull ResponseTransformer responseTransformer, @NotNull Function1<? super RouteHandler, ? extends Object> function) {
        Intrinsics.checkParameterIsNotNull(path, "path");
        Intrinsics.checkParameterIsNotNull(accepts, "accepts");
        Intrinsics.checkParameterIsNotNull(responseTransformer, "responseTransformer");
        Intrinsics.checkParameterIsNotNull(function, "function");
        Spark.delete(path, accepts, new Route(function){
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

    public static /* bridge */ /* synthetic */ void delete$default(String string, String string2, ResponseTransformer responseTransformer, Function1 function1, int n, Object object) {
        if ((n & 2) != 0) {
            string2 = DEFAULT_ACCEPT;
        }
        HttpKt.delete(string, string2, responseTransformer, function1);
    }

    public static final void head(@NotNull String path, @NotNull String accepts, @NotNull Function1<? super RouteHandler, ? extends Object> function) {
        Intrinsics.checkParameterIsNotNull(path, "path");
        Intrinsics.checkParameterIsNotNull(accepts, "accepts");
        Intrinsics.checkParameterIsNotNull(function, "function");
        Spark.head(path, accepts, new Route(function){
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

    public static /* bridge */ /* synthetic */ void head$default(String string, String string2, Function1 function1, int n, Object object) {
        if ((n & 2) != 0) {
            string2 = DEFAULT_ACCEPT;
        }
        HttpKt.head(string, string2, function1);
    }

    public static final void head(@NotNull String path, @NotNull String accepts, @NotNull TemplateEngine templateEngine, @NotNull Function1<? super RouteHandler, ? extends ModelAndView> function) {
        Intrinsics.checkParameterIsNotNull(path, "path");
        Intrinsics.checkParameterIsNotNull(accepts, "accepts");
        Intrinsics.checkParameterIsNotNull(templateEngine, "templateEngine");
        Intrinsics.checkParameterIsNotNull(function, "function");
        Spark.head(path, accepts, new TemplateViewRoute(function){
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

    public static /* bridge */ /* synthetic */ void head$default(String string, String string2, TemplateEngine templateEngine, Function1 function1, int n, Object object) {
        if ((n & 2) != 0) {
            string2 = DEFAULT_ACCEPT;
        }
        HttpKt.head(string, string2, templateEngine, function1);
    }

    public static final void head(@NotNull String path, @NotNull String accepts, @NotNull ResponseTransformer responseTransformer, @NotNull Function1<? super RouteHandler, ? extends Object> function) {
        Intrinsics.checkParameterIsNotNull(path, "path");
        Intrinsics.checkParameterIsNotNull(accepts, "accepts");
        Intrinsics.checkParameterIsNotNull(responseTransformer, "responseTransformer");
        Intrinsics.checkParameterIsNotNull(function, "function");
        Spark.head(path, accepts, new Route(function){
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

    public static /* bridge */ /* synthetic */ void head$default(String string, String string2, ResponseTransformer responseTransformer, Function1 function1, int n, Object object) {
        if ((n & 2) != 0) {
            string2 = DEFAULT_ACCEPT;
        }
        HttpKt.head(string, string2, responseTransformer, function1);
    }

    public static final void trace(@NotNull String path, @NotNull String accepts, @NotNull Function1<? super RouteHandler, ? extends Object> function) {
        Intrinsics.checkParameterIsNotNull(path, "path");
        Intrinsics.checkParameterIsNotNull(accepts, "accepts");
        Intrinsics.checkParameterIsNotNull(function, "function");
        Spark.trace(path, accepts, new Route(function){
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

    public static /* bridge */ /* synthetic */ void trace$default(String string, String string2, Function1 function1, int n, Object object) {
        if ((n & 2) != 0) {
            string2 = DEFAULT_ACCEPT;
        }
        HttpKt.trace(string, string2, function1);
    }

    public static final void trace(@NotNull String path, @NotNull String accepts, @NotNull TemplateEngine templateEngine, @NotNull Function1<? super RouteHandler, ? extends ModelAndView> function) {
        Intrinsics.checkParameterIsNotNull(path, "path");
        Intrinsics.checkParameterIsNotNull(accepts, "accepts");
        Intrinsics.checkParameterIsNotNull(templateEngine, "templateEngine");
        Intrinsics.checkParameterIsNotNull(function, "function");
        Spark.trace(path, accepts, new TemplateViewRoute(function){
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

    public static /* bridge */ /* synthetic */ void trace$default(String string, String string2, TemplateEngine templateEngine, Function1 function1, int n, Object object) {
        if ((n & 2) != 0) {
            string2 = DEFAULT_ACCEPT;
        }
        HttpKt.trace(string, string2, templateEngine, function1);
    }

    public static final void trace(@NotNull String path, @NotNull String accepts, @NotNull ResponseTransformer responseTransformer, @NotNull Function1<? super RouteHandler, ? extends Object> function) {
        Intrinsics.checkParameterIsNotNull(path, "path");
        Intrinsics.checkParameterIsNotNull(accepts, "accepts");
        Intrinsics.checkParameterIsNotNull(responseTransformer, "responseTransformer");
        Intrinsics.checkParameterIsNotNull(function, "function");
        Spark.trace(path, accepts, new Route(function){
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

    public static /* bridge */ /* synthetic */ void trace$default(String string, String string2, ResponseTransformer responseTransformer, Function1 function1, int n, Object object) {
        if ((n & 2) != 0) {
            string2 = DEFAULT_ACCEPT;
        }
        HttpKt.trace(string, string2, responseTransformer, function1);
    }

    public static final void options(@NotNull String path, @NotNull String accepts, @NotNull Function1<? super RouteHandler, ? extends Object> function) {
        Intrinsics.checkParameterIsNotNull(path, "path");
        Intrinsics.checkParameterIsNotNull(accepts, "accepts");
        Intrinsics.checkParameterIsNotNull(function, "function");
        Spark.options(path, accepts, new Route(function){
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

    public static /* bridge */ /* synthetic */ void options$default(String string, String string2, Function1 function1, int n, Object object) {
        if ((n & 2) != 0) {
            string2 = DEFAULT_ACCEPT;
        }
        HttpKt.options(string, string2, function1);
    }

    public static final void options(@NotNull String path, @NotNull String accepts, @NotNull TemplateEngine templateEngine, @NotNull Function1<? super RouteHandler, ? extends ModelAndView> function) {
        Intrinsics.checkParameterIsNotNull(path, "path");
        Intrinsics.checkParameterIsNotNull(accepts, "accepts");
        Intrinsics.checkParameterIsNotNull(templateEngine, "templateEngine");
        Intrinsics.checkParameterIsNotNull(function, "function");
        Spark.options(path, accepts, new TemplateViewRoute(function){
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

    public static /* bridge */ /* synthetic */ void options$default(String string, String string2, TemplateEngine templateEngine, Function1 function1, int n, Object object) {
        if ((n & 2) != 0) {
            string2 = DEFAULT_ACCEPT;
        }
        HttpKt.options(string, string2, templateEngine, function1);
    }

    public static final void options(@NotNull String path, @NotNull String accepts, @NotNull ResponseTransformer responseTransformer, @NotNull Function1<? super RouteHandler, ? extends Object> function) {
        Intrinsics.checkParameterIsNotNull(path, "path");
        Intrinsics.checkParameterIsNotNull(accepts, "accepts");
        Intrinsics.checkParameterIsNotNull(responseTransformer, "responseTransformer");
        Intrinsics.checkParameterIsNotNull(function, "function");
        Spark.options(path, accepts, new Route(function){
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

    public static /* bridge */ /* synthetic */ void options$default(String string, String string2, ResponseTransformer responseTransformer, Function1 function1, int n, Object object) {
        if ((n & 2) != 0) {
            string2 = DEFAULT_ACCEPT;
        }
        HttpKt.options(string, string2, responseTransformer, function1);
    }

    public static final void patch(@NotNull String path, @NotNull String accepts, @NotNull Function1<? super RouteHandler, ? extends Object> function) {
        Intrinsics.checkParameterIsNotNull(path, "path");
        Intrinsics.checkParameterIsNotNull(accepts, "accepts");
        Intrinsics.checkParameterIsNotNull(function, "function");
        Spark.patch(path, accepts, new Route(function){
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

    public static /* bridge */ /* synthetic */ void patch$default(String string, String string2, Function1 function1, int n, Object object) {
        if ((n & 2) != 0) {
            string2 = DEFAULT_ACCEPT;
        }
        HttpKt.patch(string, string2, function1);
    }

    public static final void patch(@NotNull String path, @NotNull String accepts, @NotNull TemplateEngine templateEngine, @NotNull Function1<? super RouteHandler, ? extends ModelAndView> function) {
        Intrinsics.checkParameterIsNotNull(path, "path");
        Intrinsics.checkParameterIsNotNull(accepts, "accepts");
        Intrinsics.checkParameterIsNotNull(templateEngine, "templateEngine");
        Intrinsics.checkParameterIsNotNull(function, "function");
        Spark.patch(path, accepts, new TemplateViewRoute(function){
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

    public static /* bridge */ /* synthetic */ void patch$default(String string, String string2, TemplateEngine templateEngine, Function1 function1, int n, Object object) {
        if ((n & 2) != 0) {
            string2 = DEFAULT_ACCEPT;
        }
        HttpKt.patch(string, string2, templateEngine, function1);
    }

    public static final void patch(@NotNull String path, @NotNull String accepts, @NotNull ResponseTransformer responseTransformer, @NotNull Function1<? super RouteHandler, ? extends Object> function) {
        Intrinsics.checkParameterIsNotNull(path, "path");
        Intrinsics.checkParameterIsNotNull(accepts, "accepts");
        Intrinsics.checkParameterIsNotNull(responseTransformer, "responseTransformer");
        Intrinsics.checkParameterIsNotNull(function, "function");
        Spark.patch(path, accepts, new Route(function){
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

    public static /* bridge */ /* synthetic */ void patch$default(String string, String string2, ResponseTransformer responseTransformer, Function1 function1, int n, Object object) {
        if ((n & 2) != 0) {
            string2 = DEFAULT_ACCEPT;
        }
        HttpKt.patch(string, string2, responseTransformer, function1);
    }

    public static final void connect(@NotNull String path, @NotNull String accepts, @NotNull Function1<? super RouteHandler, ? extends Object> function) {
        Intrinsics.checkParameterIsNotNull(path, "path");
        Intrinsics.checkParameterIsNotNull(accepts, "accepts");
        Intrinsics.checkParameterIsNotNull(function, "function");
        Spark.connect(path, accepts, new Route(function){
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

    public static /* bridge */ /* synthetic */ void connect$default(String string, String string2, Function1 function1, int n, Object object) {
        if ((n & 2) != 0) {
            string2 = DEFAULT_ACCEPT;
        }
        HttpKt.connect(string, string2, function1);
    }

    public static final void connect(@NotNull String path, @NotNull String accepts, @NotNull TemplateEngine templateEngine, @NotNull Function1<? super RouteHandler, ? extends ModelAndView> function) {
        Intrinsics.checkParameterIsNotNull(path, "path");
        Intrinsics.checkParameterIsNotNull(accepts, "accepts");
        Intrinsics.checkParameterIsNotNull(templateEngine, "templateEngine");
        Intrinsics.checkParameterIsNotNull(function, "function");
        Spark.connect(path, accepts, new TemplateViewRoute(function){
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

    public static /* bridge */ /* synthetic */ void connect$default(String string, String string2, TemplateEngine templateEngine, Function1 function1, int n, Object object) {
        if ((n & 2) != 0) {
            string2 = DEFAULT_ACCEPT;
        }
        HttpKt.connect(string, string2, templateEngine, function1);
    }

    public static final void connect(@NotNull String path, @NotNull String accepts, @NotNull ResponseTransformer responseTransformer, @NotNull Function1<? super RouteHandler, ? extends Object> function) {
        Intrinsics.checkParameterIsNotNull(path, "path");
        Intrinsics.checkParameterIsNotNull(accepts, "accepts");
        Intrinsics.checkParameterIsNotNull(responseTransformer, "responseTransformer");
        Intrinsics.checkParameterIsNotNull(function, "function");
        Spark.connect(path, accepts, new Route(function){
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

    public static /* bridge */ /* synthetic */ void connect$default(String string, String string2, ResponseTransformer responseTransformer, Function1 function1, int n, Object object) {
        if ((n & 2) != 0) {
            string2 = DEFAULT_ACCEPT;
        }
        HttpKt.connect(string, string2, responseTransformer, function1);
    }

    public static final void before(@NotNull Filter filter2, @NotNull String accepts) {
        Intrinsics.checkParameterIsNotNull(filter2, "filter");
        Intrinsics.checkParameterIsNotNull(accepts, "accepts");
        Spark.before(filter2);
    }

    public static /* bridge */ /* synthetic */ void before$default(Filter filter2, String string, int n, Object object) {
        if ((n & 2) != 0) {
            string = DEFAULT_ACCEPT;
        }
        HttpKt.before(filter2, string);
    }

    public static final void before(@Nullable String path, @NotNull String accepts, @NotNull Function1<? super RouteHandler, Unit> function) {
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
            Spark.before(filter2);
        } else {
            Spark.before(path, accepts, filter2);
        }
    }

    public static /* bridge */ /* synthetic */ void before$default(String string, String string2, Function1 function1, int n, Object object) {
        if ((n & 1) != 0) {
            string = null;
        }
        if ((n & 2) != 0) {
            string2 = DEFAULT_ACCEPT;
        }
        HttpKt.before(string, string2, function1);
    }

    public static final void after(@Nullable String path, @NotNull String accepts, @NotNull Function1<? super RouteHandler, Unit> function) {
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
            Spark.after(filter2);
        } else {
            Spark.after(path, accepts, filter2);
        }
    }

    public static /* bridge */ /* synthetic */ void after$default(String string, String string2, Function1 function1, int n, Object object) {
        if ((n & 1) != 0) {
            string = null;
        }
        if ((n & 2) != 0) {
            string2 = DEFAULT_ACCEPT;
        }
        HttpKt.after(string, string2, function1);
    }

    public static final void finally(@Nullable String path, @NotNull Function1<? super RouteHandler, Unit> function) {
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
            Spark.afterAfter(filter2);
        } else {
            Spark.afterAfter(path, filter2);
        }
    }

    public static /* bridge */ /* synthetic */ void finally$default(String string, Function1 function1, int n, Object object) {
        if ((n & 1) != 0) {
            string = null;
        }
        HttpKt.finally(string, function1);
    }

    public static final void notFound(@NotNull Function1<? super RouteHandler, ? extends Object> function) {
        Intrinsics.checkParameterIsNotNull(function, "function");
        Spark.notFound(new Route(function){
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

    public static final void internalServerError(@NotNull Function1<? super RouteHandler, ? extends Object> function) {
        Intrinsics.checkParameterIsNotNull(function, "function");
        Spark.internalServerError(new Route(function){
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
    public static final HaltException halt() {
        HaltException haltException = Spark.halt();
        Intrinsics.checkExpressionValueIsNotNull(haltException, "Spark.halt()");
        return haltException;
    }

    @NotNull
    public static final HaltException halt(@NotNull String body) {
        Intrinsics.checkParameterIsNotNull(body, "body");
        HaltException haltException = Spark.halt(body);
        Intrinsics.checkExpressionValueIsNotNull(haltException, "Spark.halt(body)");
        return haltException;
    }

    @NotNull
    public static final HaltException halt(int code) {
        HaltException haltException = Spark.halt(code);
        Intrinsics.checkExpressionValueIsNotNull(haltException, "Spark.halt(code)");
        return haltException;
    }

    @NotNull
    public static final HaltException halt(int code, @NotNull String body) {
        Intrinsics.checkParameterIsNotNull(body, "body");
        HaltException haltException = Spark.halt(code, body);
        Intrinsics.checkExpressionValueIsNotNull(haltException, "Spark.halt(code, body)");
        return haltException;
    }

    public static final void stop() {
        Spark.stop();
    }

    @NotNull
    public static final Http ignite() {
        Service service = Service.ignite();
        Intrinsics.checkExpressionValueIsNotNull(service, "Service.ignite()");
        return new Http(service);
    }

    static {
        DEFAULT_ACCEPT = DEFAULT_ACCEPT;
        Service.StaticFiles staticFiles = Spark.staticFiles;
        Intrinsics.checkExpressionValueIsNotNull(staticFiles, "Spark.staticFiles");
        HttpKt.staticFiles = staticFiles;
        Redirect redirect = Spark.redirect;
        Intrinsics.checkExpressionValueIsNotNull(redirect, "Spark.redirect");
        HttpKt.redirect = redirect;
    }
}

