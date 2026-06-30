/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.apache.logging.log4j.Logger
 */
package journeymap.client.service.webmap.kotlin;

import info.journeymap.shaded.kotlin.kotlin.Metadata;
import info.journeymap.shaded.kotlin.kotlin.jvm.functions.Function1;
import info.journeymap.shaded.kotlin.kotlin.jvm.internal.Intrinsics;
import info.journeymap.shaded.kotlin.kotlin.reflect.KDeclarationContainer;
import info.journeymap.shaded.kotlin.spark.kotlin.RouteHandler;
import info.journeymap.shaded.org.jetbrains.annotations.NotNull;
import journeymap.client.service.webmap.kotlin.RoutesKt;
import journeymap.common.Journeymap;
import journeymap.common.log.LogFormatter;
import org.apache.logging.log4j.Logger;

@Metadata(mv={1, 1, 16}, bv={1, 0, 3}, k=2, d1={"\u0000\u0018\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\u001a(\u0010\u0002\u001a\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00050\u00032\u0012\u0010\u0006\u001a\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00050\u0003H\u0000\"\u000e\u0010\u0000\u001a\u00020\u0001X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0007"}, d2={"logger", "Lorg/apache/logging/log4j/Logger;", "wrapForError", "Linfo/journeymap/shaded/kotlin/kotlin/Function1;", "Linfo/journeymap/shaded/kotlin/spark/kotlin/RouteHandler;", "", "function", "journeymap"})
public final class RoutesKt {
    private static final Logger logger;

    @NotNull
    public static final Function1<RouteHandler, Object> wrapForError(@NotNull Function1<? super RouteHandler, ? extends Object> function) {
        Intrinsics.checkParameterIsNotNull(function, "function");
        Function1<RouteHandler, Object> $fun$wrapper$1 = new Function1<RouteHandler, Object>(function){
            final /* synthetic */ Function1 $function;

            @NotNull
            public final Object invoke(@NotNull RouteHandler handler) {
                Object object;
                Intrinsics.checkParameterIsNotNull(handler, "handler");
                try {
                    object = this.$function.invoke(handler);
                }
                catch (Throwable t) {
                    RoutesKt.access$getLogger$p().error(LogFormatter.toString(t));
                    handler.getResponse().status(500);
                    String string = t.getLocalizedMessage();
                    Intrinsics.checkExpressionValueIsNotNull(string, "t.localizedMessage");
                    object = string;
                }
                return object;
            }
            {
                this.$function = function1;
                super(1);
            }
        };
        return new Function1<RouteHandler, Object>($fun$wrapper$1){
            final /* synthetic */ wrapForError.1 $wrapper$1;

            @NotNull
            public final Object invoke(@NotNull RouteHandler p1) {
                Intrinsics.checkParameterIsNotNull(p1, "p1");
                return this.$wrapper$1.invoke(p1);
            }

            public final KDeclarationContainer getOwner() {
                return null;
            }

            public final String getName() {
                return "wrapper";
            }

            public final String getSignature() {
                return "invoke(Lspark/kotlin/RouteHandler;)Ljava/lang/Object;";
            }
            {
                this.$wrapper$1 = var1_1;
                super(1);
            }
        };
    }

    static {
        Logger logger = Journeymap.getLogger("webmap/routes");
        Intrinsics.checkExpressionValueIsNotNull(logger, "Journeymap.getLogger(\"webmap/routes\")");
        RoutesKt.logger = logger;
    }

    public static final /* synthetic */ Logger access$getLogger$p() {
        return logger;
    }
}

