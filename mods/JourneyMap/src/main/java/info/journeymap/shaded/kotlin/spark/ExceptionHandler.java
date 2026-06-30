/*
 * Decompiled with CFR 0.152.
 */
package info.journeymap.shaded.kotlin.spark;

import info.journeymap.shaded.kotlin.spark.Request;
import info.journeymap.shaded.kotlin.spark.Response;

@FunctionalInterface
public interface ExceptionHandler<T extends Exception> {
    public void handle(T var1, Request var2, Response var3);
}

