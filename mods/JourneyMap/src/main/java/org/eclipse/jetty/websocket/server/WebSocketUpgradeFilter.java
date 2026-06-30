/*
 * Decompiled with CFR 0.152.
 */
package org.eclipse.jetty.websocket.server;

import info.journeymap.shaded.org.javax.servlet.DispatcherType;
import info.journeymap.shaded.org.javax.servlet.Filter;
import info.journeymap.shaded.org.javax.servlet.FilterChain;
import info.journeymap.shaded.org.javax.servlet.FilterConfig;
import info.journeymap.shaded.org.javax.servlet.ServletContext;
import info.journeymap.shaded.org.javax.servlet.ServletException;
import info.journeymap.shaded.org.javax.servlet.ServletRequest;
import info.journeymap.shaded.org.javax.servlet.ServletResponse;
import info.journeymap.shaded.org.javax.servlet.http.HttpServletRequest;
import info.journeymap.shaded.org.javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.EnumSet;
import org.eclipse.jetty.http.pathmap.MappedResource;
import org.eclipse.jetty.server.handler.ContextHandler;
import org.eclipse.jetty.servlet.FilterHolder;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.util.annotation.ManagedAttribute;
import org.eclipse.jetty.util.annotation.ManagedObject;
import org.eclipse.jetty.util.component.ContainerLifeCycle;
import org.eclipse.jetty.util.component.Dumpable;
import org.eclipse.jetty.util.log.Log;
import org.eclipse.jetty.util.log.Logger;
import org.eclipse.jetty.websocket.server.MappedWebSocketCreator;
import org.eclipse.jetty.websocket.server.NativeWebSocketConfiguration;
import org.eclipse.jetty.websocket.server.NativeWebSocketServletContainerInitializer;
import org.eclipse.jetty.websocket.server.WebSocketServerFactory;
import org.eclipse.jetty.websocket.server.pathmap.PathSpec;
import org.eclipse.jetty.websocket.servlet.WebSocketCreator;
import org.eclipse.jetty.websocket.servlet.WebSocketServletFactory;

@ManagedObject(value="WebSocket Upgrade Filter")
public class WebSocketUpgradeFilter
implements Filter,
MappedWebSocketCreator,
Dumpable {
    private static final Logger LOG = Log.getLogger(WebSocketUpgradeFilter.class);
    public static final String CONTEXT_ATTRIBUTE_KEY = "contextAttributeKey";
    public static final String CONFIG_ATTRIBUTE_KEY = "configAttributeKey";
    private NativeWebSocketConfiguration configuration;
    private String instanceKey;
    private boolean localConfiguration = false;
    private boolean alreadySetToAttribute = false;

    public static WebSocketUpgradeFilter configureContext(ServletContextHandler context) throws ServletException {
        WebSocketUpgradeFilter filter2 = (WebSocketUpgradeFilter)context.getAttribute(WebSocketUpgradeFilter.class.getName());
        if (filter2 != null) {
            return filter2;
        }
        NativeWebSocketConfiguration configuration = NativeWebSocketServletContainerInitializer.getDefaultFrom(context.getServletContext());
        filter2 = new WebSocketUpgradeFilter(configuration);
        filter2.setToAttribute(context, WebSocketUpgradeFilter.class.getName());
        String name = "Jetty_WebSocketUpgradeFilter";
        String pathSpec = "/*";
        EnumSet<DispatcherType> dispatcherTypes = EnumSet.of(DispatcherType.REQUEST);
        FilterHolder fholder = new FilterHolder(filter2);
        fholder.setName(name);
        fholder.setAsyncSupported(true);
        fholder.setInitParameter(CONTEXT_ATTRIBUTE_KEY, WebSocketUpgradeFilter.class.getName());
        context.addFilter(fholder, pathSpec, dispatcherTypes);
        if (LOG.isDebugEnabled()) {
            LOG.debug("Adding [{}] {} mapped to {} to {}", name, filter2, pathSpec, context);
        }
        return filter2;
    }

    @Deprecated
    public static WebSocketUpgradeFilter configureContext(ServletContext context) throws ServletException {
        ContextHandler handler = ContextHandler.getContextHandler(context);
        if (handler == null) {
            throw new ServletException("Not running on Jetty, WebSocket support unavailable");
        }
        if (!(handler instanceof ServletContextHandler)) {
            throw new ServletException("Not running in Jetty ServletContextHandler, WebSocket support via " + WebSocketUpgradeFilter.class.getName() + " unavailable");
        }
        return WebSocketUpgradeFilter.configureContext((ServletContextHandler)handler);
    }

    public WebSocketUpgradeFilter() {
    }

    public WebSocketUpgradeFilter(WebSocketServerFactory factory) {
        this(new NativeWebSocketConfiguration(factory));
    }

    public WebSocketUpgradeFilter(NativeWebSocketConfiguration configuration) {
        this.configuration = configuration;
    }

    @Override
    public void addMapping(org.eclipse.jetty.http.pathmap.PathSpec spec, WebSocketCreator creator) {
        this.configuration.addMapping(spec, creator);
    }

    @Override
    @Deprecated
    public void addMapping(PathSpec spec, WebSocketCreator creator) {
        this.configuration.addMapping(spec, creator);
    }

    @Override
    public void addMapping(String spec, WebSocketCreator creator) {
        this.configuration.addMapping(spec, creator);
    }

    @Override
    public boolean removeMapping(String spec) {
        return this.configuration.removeMapping(spec);
    }

    @Override
    public void destroy() {
        try {
            this.alreadySetToAttribute = false;
            if (this.localConfiguration) {
                this.configuration.stop();
            }
        }
        catch (Exception e) {
            LOG.ignore(e);
        }
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        block10: {
            if (this.configuration == null) {
                LOG.debug("WebSocketUpgradeFilter is not operational - missing " + NativeWebSocketConfiguration.class.getName(), new Object[0]);
                chain.doFilter(request, response);
                return;
            }
            WebSocketServerFactory factory = this.configuration.getFactory();
            if (factory == null) {
                LOG.debug("WebSocketUpgradeFilter is not operational - no WebSocketServletFactory configured", new Object[0]);
                chain.doFilter(request, response);
                return;
            }
            try {
                MappedResource<WebSocketCreator> resource;
                HttpServletRequest httpreq = (HttpServletRequest)request;
                HttpServletResponse httpresp = (HttpServletResponse)response;
                if (!factory.isUpgradeRequest(httpreq, httpresp)) {
                    chain.doFilter(request, response);
                    return;
                }
                String contextPath = httpreq.getContextPath();
                String target = httpreq.getRequestURI();
                if (target.startsWith(contextPath)) {
                    target = target.substring(contextPath.length());
                }
                if ((resource = this.configuration.getMatch(target)) == null) {
                    chain.doFilter(request, response);
                    return;
                }
                if (LOG.isDebugEnabled()) {
                    LOG.debug("WebSocket Upgrade detected on {} for endpoint {}", target, resource);
                }
                WebSocketCreator creator = resource.getResource();
                httpreq.setAttribute(org.eclipse.jetty.http.pathmap.PathSpec.class.getName(), resource.getPathSpec());
                if (factory.acceptWebSocket(creator, httpreq, httpresp)) {
                    return;
                }
                if (response.isCommitted()) {
                    return;
                }
            }
            catch (ClassCastException e) {
                if (!LOG.isDebugEnabled()) break block10;
                LOG.debug("Not a HttpServletRequest, skipping WebSocketUpgradeFilter", new Object[0]);
            }
        }
        chain.doFilter(request, response);
    }

    @Override
    public String dump() {
        return ContainerLifeCycle.dump(this);
    }

    @Override
    public void dump(Appendable out, String indent) throws IOException {
        out.append(indent).append(" +- configuration=").append(this.configuration.toString()).append("\n");
        this.configuration.dump(out, indent);
    }

    public WebSocketServletFactory getFactory() {
        return this.configuration.getFactory();
    }

    @ManagedAttribute(value="configuration", readonly=true)
    public NativeWebSocketConfiguration getConfiguration() {
        if (this.configuration == null) {
            throw new IllegalStateException(this.getClass().getName() + " not initialized yet");
        }
        return this.configuration;
    }

    @Override
    public WebSocketCreator getMapping(String target) {
        return this.getConfiguration().getMapping(target);
    }

    @Override
    public void init(FilterConfig config) throws ServletException {
        try {
            String max;
            String configurationKey = config.getInitParameter(CONFIG_ATTRIBUTE_KEY);
            if (configurationKey == null) {
                configurationKey = NativeWebSocketConfiguration.class.getName();
            }
            if (this.configuration == null) {
                this.configuration = (NativeWebSocketConfiguration)config.getServletContext().getAttribute(configurationKey);
                if (this.configuration == null) {
                    throw new ServletException("Unable to find required instance of " + NativeWebSocketConfiguration.class.getName() + " at ServletContext attribute '" + configurationKey + "'");
                }
            } else if (config.getServletContext().getAttribute(configurationKey) == null) {
                config.getServletContext().setAttribute(configurationKey, this.configuration);
            }
            if (!this.configuration.isRunning()) {
                this.localConfiguration = true;
                this.configuration.start();
            }
            if ((max = config.getInitParameter("maxIdleTime")) != null) {
                this.getFactory().getPolicy().setIdleTimeout(Long.parseLong(max));
            }
            if ((max = config.getInitParameter("maxTextMessageSize")) != null) {
                this.getFactory().getPolicy().setMaxTextMessageSize(Integer.parseInt(max));
            }
            if ((max = config.getInitParameter("maxBinaryMessageSize")) != null) {
                this.getFactory().getPolicy().setMaxBinaryMessageSize(Integer.parseInt(max));
            }
            if ((max = config.getInitParameter("inputBufferSize")) != null) {
                this.getFactory().getPolicy().setInputBufferSize(Integer.parseInt(max));
            }
            this.instanceKey = config.getInitParameter(CONTEXT_ATTRIBUTE_KEY);
            if (this.instanceKey == null) {
                this.instanceKey = WebSocketUpgradeFilter.class.getName();
            }
            this.setToAttribute(config.getServletContext(), this.instanceKey);
        }
        catch (ServletException e) {
            throw e;
        }
        catch (Throwable t) {
            throw new ServletException(t);
        }
    }

    private void setToAttribute(ServletContextHandler context, String key) throws ServletException {
        this.setToAttribute(context.getServletContext(), key);
    }

    public void setToAttribute(ServletContext context, String key) throws ServletException {
        if (this.alreadySetToAttribute) {
            return;
        }
        if (context.getAttribute(key) != null) {
            throw new ServletException(WebSocketUpgradeFilter.class.getName() + " is defined twice for the same context attribute key '" + key + "'.  Make sure you have different init-param '" + CONTEXT_ATTRIBUTE_KEY + "' values set");
        }
        context.setAttribute(key, this);
        this.alreadySetToAttribute = true;
    }

    public String toString() {
        return String.format("%s[configuration=%s]", this.getClass().getSimpleName(), this.configuration);
    }
}

