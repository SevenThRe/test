/*
 * Decompiled with CFR 0.152.
 */
package info.journeymap.shaded.kotlin.spark;

import info.journeymap.shaded.kotlin.spark.ModelAndView;
import info.journeymap.shaded.kotlin.spark.Request;
import info.journeymap.shaded.kotlin.spark.Response;

@FunctionalInterface
public interface TemplateViewRoute {
    public ModelAndView handle(Request var1, Response var2) throws Exception;
}

