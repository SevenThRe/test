/*
 * Decompiled with CFR 0.152.
 */
package info.journeymap.shaded.kotlin.spark.embeddedserver.jetty;

import org.eclipse.jetty.server.Server;

@FunctionalInterface
public interface JettyServerFactory {
    public Server create(int var1, int var2, int var3);
}

