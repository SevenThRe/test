/*
 * Decompiled with CFR 0.152.
 */
package org.eclipse.jetty.websocket.server;

import info.journeymap.shaded.org.javax.servlet.ServletContext;
import java.io.IOException;
import java.util.Iterator;
import org.eclipse.jetty.http.pathmap.MappedResource;
import org.eclipse.jetty.http.pathmap.PathMappings;
import org.eclipse.jetty.http.pathmap.UriTemplatePathSpec;
import org.eclipse.jetty.util.component.ContainerLifeCycle;
import org.eclipse.jetty.util.component.Dumpable;
import org.eclipse.jetty.websocket.api.WebSocketException;
import org.eclipse.jetty.websocket.api.WebSocketPolicy;
import org.eclipse.jetty.websocket.server.MappedWebSocketCreator;
import org.eclipse.jetty.websocket.server.WebSocketServerFactory;
import org.eclipse.jetty.websocket.server.pathmap.PathSpec;
import org.eclipse.jetty.websocket.server.pathmap.RegexPathSpec;
import org.eclipse.jetty.websocket.server.pathmap.ServletPathSpec;
import org.eclipse.jetty.websocket.servlet.ServletUpgradeRequest;
import org.eclipse.jetty.websocket.servlet.ServletUpgradeResponse;
import org.eclipse.jetty.websocket.servlet.WebSocketCreator;

public class NativeWebSocketConfiguration
extends ContainerLifeCycle
implements MappedWebSocketCreator,
Dumpable {
    private final WebSocketServerFactory factory;
    private final PathMappings<WebSocketCreator> mappings = new PathMappings();

    public NativeWebSocketConfiguration(ServletContext context) {
        this(new WebSocketServerFactory(context));
    }

    public NativeWebSocketConfiguration(WebSocketServerFactory webSocketServerFactory) {
        this.factory = webSocketServerFactory;
        this.addBean(this.factory);
    }

    @Override
    public void doStop() throws Exception {
        this.mappings.removeIf(mapped -> !(mapped.getResource() instanceof PersistedWebSocketCreator));
        super.doStop();
    }

    @Override
    public String dump() {
        return ContainerLifeCycle.dump(this);
    }

    @Override
    public void dump(Appendable out, String indent) throws IOException {
        this.mappings.dump(out, indent);
    }

    public WebSocketServerFactory getFactory() {
        return this.factory;
    }

    public MappedResource<WebSocketCreator> getMatch(String target) {
        return this.mappings.getMatch(target);
    }

    public WebSocketPolicy getPolicy() {
        return this.factory.getPolicy();
    }

    @Override
    public void addMapping(org.eclipse.jetty.http.pathmap.PathSpec pathSpec, WebSocketCreator creator) {
        WebSocketCreator wsCreator = creator;
        if (!this.isRunning()) {
            wsCreator = new PersistedWebSocketCreator(creator);
        }
        this.mappings.put(pathSpec, wsCreator);
    }

    @Override
    @Deprecated
    public void addMapping(PathSpec spec, WebSocketCreator creator) {
        if (spec instanceof ServletPathSpec) {
            this.addMapping((org.eclipse.jetty.http.pathmap.PathSpec)new org.eclipse.jetty.http.pathmap.ServletPathSpec(spec.getSpec()), creator);
        } else if (spec instanceof RegexPathSpec) {
            this.addMapping((org.eclipse.jetty.http.pathmap.PathSpec)new org.eclipse.jetty.http.pathmap.RegexPathSpec(spec.getSpec()), creator);
        } else {
            throw new RuntimeException("Unsupported (Deprecated) PathSpec implementation type: " + spec.getClass().getName());
        }
    }

    public void addMapping(org.eclipse.jetty.http.pathmap.PathSpec pathSpec, Class<?> endpointClass) {
        this.mappings.put(pathSpec, (req, resp) -> {
            try {
                return endpointClass.newInstance();
            }
            catch (IllegalAccessException | InstantiationException e) {
                throw new WebSocketException("Unable to create instance of " + endpointClass.getName(), e);
            }
        });
    }

    @Override
    public void addMapping(String rawspec, WebSocketCreator creator) {
        org.eclipse.jetty.http.pathmap.PathSpec spec = this.toPathSpec(rawspec);
        this.addMapping(spec, creator);
    }

    private org.eclipse.jetty.http.pathmap.PathSpec toPathSpec(String rawspec) {
        if (rawspec.charAt(0) == '/' || rawspec.startsWith("*.") || rawspec.startsWith("servlet|")) {
            return new org.eclipse.jetty.http.pathmap.ServletPathSpec(rawspec);
        }
        if (rawspec.charAt(0) == '^' || rawspec.startsWith("regex|")) {
            return new org.eclipse.jetty.http.pathmap.RegexPathSpec(rawspec);
        }
        if (rawspec.startsWith("uri-template|")) {
            return new UriTemplatePathSpec(rawspec.substring("uri-template|".length()));
        }
        throw new IllegalArgumentException("Unrecognized path spec syntax [" + rawspec + "]");
    }

    @Override
    public WebSocketCreator getMapping(String rawspec) {
        org.eclipse.jetty.http.pathmap.PathSpec pathSpec = this.toPathSpec(rawspec);
        for (MappedResource<WebSocketCreator> mappedResource : this.mappings) {
            if (!mappedResource.getPathSpec().equals(pathSpec)) continue;
            return mappedResource.getResource();
        }
        return null;
    }

    @Override
    public boolean removeMapping(String rawspec) {
        org.eclipse.jetty.http.pathmap.PathSpec pathSpec = this.toPathSpec(rawspec);
        boolean removed = false;
        Iterator<MappedResource<WebSocketCreator>> iterator2 = this.mappings.iterator();
        while (iterator2.hasNext()) {
            MappedResource<WebSocketCreator> mapping = iterator2.next();
            if (!mapping.getPathSpec().equals(pathSpec)) continue;
            iterator2.remove();
            removed = true;
        }
        return removed;
    }

    public void addMapping(String rawspec, Class<?> endpointClass) {
        org.eclipse.jetty.http.pathmap.PathSpec pathSpec = this.toPathSpec(rawspec);
        this.addMapping(pathSpec, endpointClass);
    }

    private class PersistedWebSocketCreator
    implements WebSocketCreator {
        private final WebSocketCreator delegate;

        public PersistedWebSocketCreator(WebSocketCreator delegate) {
            this.delegate = delegate;
        }

        @Override
        public Object createWebSocket(ServletUpgradeRequest req, ServletUpgradeResponse resp) {
            return this.delegate.createWebSocket(req, resp);
        }

        public String toString() {
            return "Persisted[" + super.toString() + "]";
        }
    }
}

