/*
 * Decompiled with CFR 0.152.
 */
package info.journeymap.shaded.kotlin.spark.http.matching;

import info.journeymap.shaded.kotlin.spark.CustomErrorPages;
import info.journeymap.shaded.kotlin.spark.HaltException;
import info.journeymap.shaded.kotlin.spark.RequestResponseFactory;
import info.journeymap.shaded.kotlin.spark.Response;
import info.journeymap.shaded.kotlin.spark.embeddedserver.jetty.HttpRequestWrapper;
import info.journeymap.shaded.kotlin.spark.http.matching.AfterAfterFilters;
import info.journeymap.shaded.kotlin.spark.http.matching.AfterFilters;
import info.journeymap.shaded.kotlin.spark.http.matching.BeforeFilters;
import info.journeymap.shaded.kotlin.spark.http.matching.Body;
import info.journeymap.shaded.kotlin.spark.http.matching.GeneralError;
import info.journeymap.shaded.kotlin.spark.http.matching.Halt;
import info.journeymap.shaded.kotlin.spark.http.matching.RequestWrapper;
import info.journeymap.shaded.kotlin.spark.http.matching.ResponseWrapper;
import info.journeymap.shaded.kotlin.spark.http.matching.RouteContext;
import info.journeymap.shaded.kotlin.spark.http.matching.Routes;
import info.journeymap.shaded.kotlin.spark.route.HttpMethod;
import info.journeymap.shaded.kotlin.spark.serialization.SerializerChain;
import info.journeymap.shaded.kotlin.spark.staticfiles.StaticFilesConfiguration;
import info.journeymap.shaded.org.javax.servlet.Filter;
import info.journeymap.shaded.org.javax.servlet.FilterChain;
import info.journeymap.shaded.org.javax.servlet.FilterConfig;
import info.journeymap.shaded.org.javax.servlet.ServletException;
import info.journeymap.shaded.org.javax.servlet.ServletRequest;
import info.journeymap.shaded.org.javax.servlet.ServletResponse;
import info.journeymap.shaded.org.javax.servlet.http.HttpServletRequest;
import info.journeymap.shaded.org.javax.servlet.http.HttpServletResponse;
import info.journeymap.shaded.org.slf4j.Logger;
import info.journeymap.shaded.org.slf4j.LoggerFactory;
import java.io.IOException;

public class MatcherFilter
implements Filter {
    private static final Logger LOG = LoggerFactory.getLogger(MatcherFilter.class);
    private static final String ACCEPT_TYPE_REQUEST_MIME_HEADER = "Accept";
    private static final String HTTP_METHOD_OVERRIDE_HEADER = "X-HTTP-Method-Override";
    private final StaticFilesConfiguration staticFiles;
    private info.journeymap.shaded.kotlin.spark.route.Routes routeMatcher;
    private SerializerChain serializerChain;
    private boolean externalContainer;
    private boolean hasOtherHandlers;

    public MatcherFilter(info.journeymap.shaded.kotlin.spark.route.Routes routeMatcher, StaticFilesConfiguration staticFiles, boolean externalContainer, boolean hasOtherHandlers) {
        this.routeMatcher = routeMatcher;
        this.staticFiles = staticFiles;
        this.externalContainer = externalContainer;
        this.hasOtherHandlers = hasOtherHandlers;
        this.serializerChain = new SerializerChain();
    }

    @Override
    public void init(FilterConfig config) {
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest)servletRequest;
        HttpServletResponse httpResponse = (HttpServletResponse)servletResponse;
        boolean consumedByStaticFile = this.staticFiles.consume(httpRequest, httpResponse);
        if (consumedByStaticFile) {
            return;
        }
        String method = this.getHttpMethodFrom(httpRequest);
        String httpMethodStr = method.toLowerCase();
        String uri = httpRequest.getRequestURI();
        String acceptType = httpRequest.getHeader(ACCEPT_TYPE_REQUEST_MIME_HEADER);
        Body body = Body.create();
        RequestWrapper requestWrapper = RequestWrapper.create();
        ResponseWrapper responseWrapper = ResponseWrapper.create();
        Response response = RequestResponseFactory.create(httpResponse);
        HttpMethod httpMethod = HttpMethod.get(httpMethodStr);
        RouteContext context = RouteContext.create().withMatcher(this.routeMatcher).withHttpRequest(httpRequest).withUri(uri).withAcceptType(acceptType).withBody(body).withRequestWrapper(requestWrapper).withResponseWrapper(responseWrapper).withResponse(response).withHttpMethod(httpMethod);
        try {
            try {
                BeforeFilters.execute(context);
                Routes.execute(context);
                AfterFilters.execute(context);
            }
            catch (HaltException halt) {
                Halt.modify(httpResponse, body, halt);
            }
            catch (Exception generalException) {
                GeneralError.modify(httpRequest, httpResponse, body, requestWrapper, responseWrapper, generalException);
            }
            if (body.notSet() && responseWrapper.isRedirected()) {
                body.set("");
            }
            if (body.notSet() && this.hasOtherHandlers && servletRequest instanceof HttpRequestWrapper) {
                ((HttpRequestWrapper)servletRequest).notConsumed(true);
                return;
            }
            if (body.notSet() && !this.externalContainer) {
                LOG.info("The requested route [{}] has not been mapped in Spark for {}: [{}]", uri, ACCEPT_TYPE_REQUEST_MIME_HEADER, acceptType);
                httpResponse.setStatus(404);
                if (CustomErrorPages.existsFor(404)) {
                    requestWrapper.setDelegate(RequestResponseFactory.create(httpRequest));
                    responseWrapper.setDelegate(RequestResponseFactory.create(httpResponse));
                    body.set(CustomErrorPages.getFor(404, requestWrapper, responseWrapper));
                } else {
                    body.set(String.format("<html><body><h2>404 Not found</h2></body></html>", new Object[0]));
                }
            }
        }
        finally {
            try {
                AfterAfterFilters.execute(context);
            }
            catch (Exception generalException) {
                GeneralError.modify(httpRequest, httpResponse, body, requestWrapper, responseWrapper, generalException);
            }
        }
        if (body.isSet()) {
            body.serializeTo(httpResponse, this.serializerChain, httpRequest);
        } else if (chain != null) {
            chain.doFilter(httpRequest, httpResponse);
        }
    }

    private String getHttpMethodFrom(HttpServletRequest httpRequest) {
        String method = httpRequest.getHeader(HTTP_METHOD_OVERRIDE_HEADER);
        if (method == null) {
            method = httpRequest.getMethod();
        }
        return method;
    }

    @Override
    public void destroy() {
    }
}

