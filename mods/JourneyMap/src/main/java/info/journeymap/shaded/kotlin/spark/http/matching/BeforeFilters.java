/*
 * Decompiled with CFR 0.152.
 */
package info.journeymap.shaded.kotlin.spark.http.matching;

import info.journeymap.shaded.kotlin.spark.FilterImpl;
import info.journeymap.shaded.kotlin.spark.Request;
import info.journeymap.shaded.kotlin.spark.RequestResponseFactory;
import info.journeymap.shaded.kotlin.spark.http.matching.RouteContext;
import info.journeymap.shaded.kotlin.spark.route.HttpMethod;
import info.journeymap.shaded.kotlin.spark.routematch.RouteMatch;
import java.util.List;

final class BeforeFilters {
    BeforeFilters() {
    }

    static void execute(RouteContext context) throws Exception {
        Object content = context.body().get();
        List<RouteMatch> matchSet = context.routeMatcher().findMultiple(HttpMethod.before, context.uri(), context.acceptType());
        for (RouteMatch filterMatch : matchSet) {
            Object filterTarget = filterMatch.getTarget();
            if (!(filterTarget instanceof FilterImpl)) continue;
            Request request = RequestResponseFactory.create(filterMatch, context.httpRequest());
            FilterImpl filter2 = (FilterImpl)filterTarget;
            context.requestWrapper().setDelegate(request);
            context.responseWrapper().setDelegate(context.response());
            filter2.handle(context.requestWrapper(), context.responseWrapper());
            String bodyAfterFilter = context.response().body();
            if (bodyAfterFilter == null) continue;
            content = bodyAfterFilter;
        }
        context.body().set(content);
    }
}

