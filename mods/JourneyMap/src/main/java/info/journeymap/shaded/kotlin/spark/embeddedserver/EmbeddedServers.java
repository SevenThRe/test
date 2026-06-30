/*
 * Decompiled with CFR 0.152.
 */
package info.journeymap.shaded.kotlin.spark.embeddedserver;

import info.journeymap.shaded.kotlin.spark.embeddedserver.EmbeddedServer;
import info.journeymap.shaded.kotlin.spark.embeddedserver.EmbeddedServerFactory;
import info.journeymap.shaded.kotlin.spark.embeddedserver.jetty.EmbeddedJettyFactory;
import info.journeymap.shaded.kotlin.spark.route.Routes;
import info.journeymap.shaded.kotlin.spark.staticfiles.StaticFilesConfiguration;
import java.util.HashMap;
import java.util.Map;

public class EmbeddedServers {
    private static Map<Object, EmbeddedServerFactory> factories = new HashMap<Object, EmbeddedServerFactory>();

    public static void initialize() {
        if (!factories.containsKey((Object)Identifiers.JETTY)) {
            EmbeddedServers.add((Object)Identifiers.JETTY, new EmbeddedJettyFactory());
        }
    }

    public static Identifiers defaultIdentifier() {
        return Identifiers.JETTY;
    }

    public static EmbeddedServer create(Object identifier, Routes routeMatcher, StaticFilesConfiguration staticFilesConfiguration, boolean multipleHandlers) {
        EmbeddedServerFactory factory = factories.get(identifier);
        if (factory != null) {
            return factory.create(routeMatcher, staticFilesConfiguration, multipleHandlers);
        }
        throw new RuntimeException("No embedded server matching the identifier");
    }

    public static void add(Object identifier, EmbeddedServerFactory factory) {
        factories.put(identifier, factory);
    }

    public static enum Identifiers {
        JETTY;

    }
}

