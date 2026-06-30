/*
 * Decompiled with CFR 0.152.
 */
package info.journeymap.shaded.kotlin.spark.embeddedserver;

import info.journeymap.shaded.kotlin.spark.embeddedserver.EmbeddedServer;
import info.journeymap.shaded.kotlin.spark.route.Routes;
import info.journeymap.shaded.kotlin.spark.staticfiles.StaticFilesConfiguration;

public interface EmbeddedServerFactory {
    public EmbeddedServer create(Routes var1, StaticFilesConfiguration var2, boolean var3);
}

