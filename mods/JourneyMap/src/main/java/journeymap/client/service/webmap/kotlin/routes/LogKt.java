/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.apache.logging.log4j.Logger
 */
package journeymap.client.service.webmap.kotlin.routes;

import info.journeymap.shaded.kotlin.kotlin.Metadata;
import info.journeymap.shaded.kotlin.kotlin.io.FilesKt;
import info.journeymap.shaded.kotlin.kotlin.jvm.internal.Intrinsics;
import info.journeymap.shaded.kotlin.spark.kotlin.RouteHandler;
import info.journeymap.shaded.org.javax.servlet.http.HttpServletResponse;
import info.journeymap.shaded.org.jetbrains.annotations.NotNull;
import java.io.File;
import journeymap.client.log.JMLogger;
import journeymap.common.Journeymap;
import org.apache.logging.log4j.Logger;

@Metadata(mv={1, 1, 16}, bv={1, 0, 3}, k=2, d1={"\u0000\u0014\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\u001a\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\u0000\"\u000e\u0010\u0000\u001a\u00020\u0001X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0006"}, d2={"logger", "Lorg/apache/logging/log4j/Logger;", "logGet", "", "handler", "Linfo/journeymap/shaded/kotlin/spark/kotlin/RouteHandler;", "journeymap"})
public final class LogKt {
    private static final Logger logger;

    @NotNull
    public static final Object logGet(@NotNull RouteHandler handler) {
        Object object;
        Intrinsics.checkParameterIsNotNull(handler, "handler");
        File file = JMLogger.getLogFile();
        Intrinsics.checkExpressionValueIsNotNull(file, "JMLogger.getLogFile()");
        File logFile = file;
        if (logFile.exists()) {
            handler.getResponse().raw().addHeader("Content-Disposition", "inline; filename=\"journeymap.log\"");
            HttpServletResponse httpServletResponse = handler.getResponse().raw();
            Intrinsics.checkExpressionValueIsNotNull(httpServletResponse, "handler.response.raw()");
            httpServletResponse.getOutputStream().write(FilesKt.readBytes(logFile));
            HttpServletResponse httpServletResponse2 = handler.getResponse().raw();
            Intrinsics.checkExpressionValueIsNotNull(httpServletResponse2, "handler.response.raw()");
            httpServletResponse2.getOutputStream().flush();
            object = handler.getResponse();
        } else {
            logger.warn("Unable to find JourneyMap logfile");
            handler.status(404);
            object = "Not found: " + logFile.getPath();
        }
        return object;
    }

    static {
        Logger logger = Journeymap.getLogger("webmap/routes/log");
        Intrinsics.checkExpressionValueIsNotNull(logger, "Journeymap.getLogger(\"webmap/routes/log\")");
        LogKt.logger = logger;
    }
}

