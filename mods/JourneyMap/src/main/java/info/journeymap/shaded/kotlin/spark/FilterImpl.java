/*
 * Decompiled with CFR 0.152.
 */
package info.journeymap.shaded.kotlin.spark;

import info.journeymap.shaded.kotlin.spark.Filter;
import info.journeymap.shaded.kotlin.spark.Request;
import info.journeymap.shaded.kotlin.spark.Response;
import info.journeymap.shaded.kotlin.spark.utils.Wrapper;

public abstract class FilterImpl
implements Filter,
Wrapper {
    static final String DEFAULT_ACCEPT_TYPE = "*/*";
    private String path;
    private String acceptType;
    private Filter delegate;

    public FilterImpl withPrefix(String prefix) {
        this.path = prefix + this.path;
        return this;
    }

    static FilterImpl create(String path, Filter filter2) {
        return FilterImpl.create(path, DEFAULT_ACCEPT_TYPE, filter2);
    }

    static FilterImpl create(String path, String acceptType, final Filter filter2) {
        if (acceptType == null) {
            acceptType = DEFAULT_ACCEPT_TYPE;
        }
        return new FilterImpl(path, acceptType, filter2){

            @Override
            public void handle(Request request, Response response) throws Exception {
                filter2.handle(request, response);
            }
        };
    }

    protected FilterImpl(String path, String acceptType) {
        this.path = path;
        this.acceptType = acceptType;
    }

    protected FilterImpl(String path, String acceptType, Filter filter2) {
        this(path, acceptType);
        this.delegate = filter2;
    }

    @Override
    public abstract void handle(Request var1, Response var2) throws Exception;

    public String getAcceptType() {
        return this.acceptType;
    }

    public String getPath() {
        return this.path;
    }

    @Override
    public Object delegate() {
        return this.delegate;
    }
}

