/*
 * Decompiled with CFR 0.152.
 */
package info.journeymap.shaded.kotlin.spark;

import info.journeymap.shaded.kotlin.spark.Request;
import info.journeymap.shaded.kotlin.spark.Response;

@FunctionalInterface
public interface Route {
    public Object handle(Request var1, Response var2) throws Exception;
}

