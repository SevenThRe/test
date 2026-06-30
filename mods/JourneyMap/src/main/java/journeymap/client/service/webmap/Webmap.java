/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.apache.logging.log4j.Logger
 */
package journeymap.client.service.webmap;

import info.journeymap.shaded.kotlin.kotlin.Metadata;
import info.journeymap.shaded.kotlin.kotlin.jvm.internal.Intrinsics;
import info.journeymap.shaded.kotlin.spark.Spark;
import info.journeymap.shaded.kotlin.spark.kotlin.HttpKt;
import info.journeymap.shaded.org.jetbrains.annotations.NotNull;
import java.io.File;
import java.io.IOException;
import java.net.ServerSocket;
import journeymap.client.JourneymapClient;
import journeymap.client.service.webmap.Webmap;
import journeymap.client.service.webmap.kotlin.RoutesKt;
import journeymap.common.Journeymap;
import journeymap.common.properties.config.CustomField;
import org.apache.logging.log4j.Logger;

@Metadata(mv={1, 1, 16}, bv={1, 0, 3}, k=1, d1={"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\b\u0005\b\u00c6\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002J\u0012\u0010\u0013\u001a\u00020\u00142\b\b\u0002\u0010\u0015\u001a\u00020\u000eH\u0002J\b\u0010\u0016\u001a\u00020\u0014H\u0002J\u0006\u0010\u0017\u001a\u00020\u0014J\u0006\u0010\u0018\u001a\u00020\u0014R\u0011\u0010\u0003\u001a\u00020\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006R\u001a\u0010\u0007\u001a\u00020\bX\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\t\u0010\n\"\u0004\b\u000b\u0010\fR\u001a\u0010\r\u001a\u00020\u000eX\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u000f\u0010\u0010\"\u0004\b\u0011\u0010\u0012\u00a8\u0006\u0019"}, d2={"Ljourneymap/client/service/webmap/Webmap;", "", "()V", "logger", "Lorg/apache/logging/log4j/Logger;", "getLogger", "()Lorg/apache/logging/log4j/Logger;", "port", "", "getPort", "()I", "setPort", "(I)V", "started", "", "getStarted", "()Z", "setStarted", "(Z)V", "findPort", "", "tryCurrentPort", "initialise", "start", "stop", "journeymap"})
public final class Webmap {
    @NotNull
    private static final Logger logger;
    private static int port;
    private static boolean started;
    public static final Webmap INSTANCE;

    @NotNull
    public final Logger getLogger() {
        return logger;
    }

    public final int getPort() {
        return port;
    }

    public final void setPort(int n) {
        port = n;
    }

    public final boolean getStarted() {
        return started;
    }

    public final void setStarted(boolean bl) {
        started = bl;
    }

    public final void start() {
        if (!started) {
            Webmap.findPort$default(this, false, 1, null);
            HttpKt.port(port);
            this.initialise();
            started = true;
            logger.info("Webmap is now listening on port " + port + '.');
        }
    }

    private final void initialise() {
        Spark.initExceptionHandler(initialise.1.INSTANCE);
        String assetsRootProperty = System.getProperty("journeymap.webmap.assets_root", null);
        File testFile = new File("../src/main/resources/assets/journeymap/web");
        if (assetsRootProperty != null) {
            logger.info("Detected 'journeymap.webmap.assets_root' property, serving static files from: " + assetsRootProperty);
            HttpKt.getStaticFiles().externalLocation(assetsRootProperty);
        } else if (testFile.exists()) {
            logger.info("Development environment detected, serving static files from the filesystem.");
            HttpKt.getStaticFiles().externalLocation(testFile.getCanonicalPath());
        } else {
            HttpKt.getStaticFiles().location("/assets/journeymap/web");
        }
        HttpKt.before$default(initialise.2.INSTANCE, null, 2, null);
        HttpKt.get$default("/data/:type", null, RoutesKt.wrapForError(initialise.3.INSTANCE), 2, null);
        HttpKt.get$default("/logs", null, RoutesKt.wrapForError(initialise.4.INSTANCE), 2, null);
        HttpKt.get$default("/polygons", null, RoutesKt.wrapForError(initialise.5.INSTANCE), 2, null);
        HttpKt.get$default("/properties", null, RoutesKt.wrapForError(initialise.6.INSTANCE), 2, null);
        HttpKt.get$default("/resources", null, RoutesKt.wrapForError(initialise.7.INSTANCE), 2, null);
        HttpKt.get$default("/skin/:uuid", null, RoutesKt.wrapForError(initialise.8.INSTANCE), 2, null);
        HttpKt.get$default("/status", null, RoutesKt.wrapForError(initialise.9.INSTANCE), 2, null);
        HttpKt.get$default("/tiles/tile.png", null, RoutesKt.wrapForError(initialise.10.INSTANCE), 2, null);
        HttpKt.post$default("/properties", null, RoutesKt.wrapForError(initialise.11.INSTANCE), 2, null);
        Spark.init();
    }

    public final void stop() {
        if (started) {
            HttpKt.stop();
            started = false;
            logger.info("Webmap stopped.");
        }
    }

    private final void findPort(boolean tryCurrentPort) {
        if (port == 0) {
            JourneymapClient journeymapClient = Journeymap.getClient();
            Intrinsics.checkExpressionValueIsNotNull(journeymapClient, "Journeymap.getClient()");
            CustomField customField = journeymapClient.getWebMapProperties().port;
            Intrinsics.checkExpressionValueIsNotNull(customField, "Journeymap.getClient().webMapProperties.port");
            Integer n = customField.getAsInteger();
            int n2 = port = n != null ? n : 0;
        }
        if (tryCurrentPort) {
            try {
                ServerSocket socket = new ServerSocket(port);
                port = socket.getLocalPort();
                socket.close();
            }
            catch (IOException e) {
                logger.warn("Configured port " + port + " could not be bound: " + e);
                this.findPort(false);
            }
            logger.info("Configured port " + port + " is available.");
        } else {
            ServerSocket socket = new ServerSocket(0);
            port = socket.getLocalPort();
            socket.close();
            logger.info("New port " + port + " assigned by ServerSocket.");
        }
    }

    static /* synthetic */ void findPort$default(Webmap webmap, boolean bl, int n, Object object) {
        if ((n & 1) != 0) {
            bl = true;
        }
        webmap.findPort(bl);
    }

    private Webmap() {
    }

    static {
        Webmap webmap;
        INSTANCE = webmap = new Webmap();
        Logger logger = Journeymap.getLogger("webmap");
        Intrinsics.checkExpressionValueIsNotNull(logger, "Journeymap.getLogger(\"webmap\")");
        Webmap.logger = logger;
    }
}

