/*
 * Decompiled with CFR 0.152.
 */
package info.journeymap.shaded.kotlin.spark;

import info.journeymap.shaded.kotlin.spark.Request;
import info.journeymap.shaded.kotlin.spark.Response;
import info.journeymap.shaded.kotlin.spark.ResponseTransformer;
import info.journeymap.shaded.kotlin.spark.Route;
import info.journeymap.shaded.kotlin.spark.RouteImpl;

public abstract class ResponseTransformerRouteImpl
extends RouteImpl {
    public static ResponseTransformerRouteImpl create(String path, Route route, ResponseTransformer transformer) {
        return ResponseTransformerRouteImpl.create(path, "*/*", route, transformer);
    }

    public static ResponseTransformerRouteImpl create(String path, String acceptType, final Route route, final ResponseTransformer transformer) {
        return new ResponseTransformerRouteImpl(path, acceptType, route){

            @Override
            public Object render(Object model) throws Exception {
                return transformer.render(model);
            }

            @Override
            public Object handle(Request request, Response response) throws Exception {
                return route.handle(request, response);
            }
        };
    }

    protected ResponseTransformerRouteImpl(String path, String acceptType, Route route) {
        super(path, acceptType, route);
    }

    @Override
    public abstract Object render(Object var1) throws Exception;
}

