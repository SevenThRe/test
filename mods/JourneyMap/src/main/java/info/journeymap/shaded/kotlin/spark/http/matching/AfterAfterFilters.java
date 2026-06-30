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

final class AfterAfterFilters {
    AfterAfterFilters() {
    }

    static void execute(RouteContext context) throws Exception {
        Object content = context.body().get();
        List<RouteMatch> matchSet = context.routeMatcher().findMultiple(HttpMethod.afterafter, context.uri(), context.acceptType());
        for (RouteMatch filterMatch : matchSet) {
            Object filterTarget = filterMatch.getTarget();
            if (!(filterTarget instanceof FilterImpl)) continue;
            if (context.requestWrapper().getDelegate() == null) {
                Request request = RequestResponseFactory.create(filterMatch, context.httpRequest());
                context.requestWrapper().setDelegate(request);
            } else {
                context.requestWrapper().changeMatch(filterMatch);
            }
            context.responseWrapper().setDelegate(context.response());
            FilterImpl filter2 = (FilterImpl)filterTarget;
            filter2.handle(context.requestWrapper(), context.responseWrapper());
            String bodyAfterFilter = context.response().body();
            if (bodyAfterFilter == null) continue;
            content = bodyAfterFilter;
        }
        context.body().set(content);
    }
}

