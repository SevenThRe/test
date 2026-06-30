/*
 * Decompiled with CFR 0.152.
 */
package org.eclipse.jetty.websocket.common.extensions;

import org.eclipse.jetty.util.StringUtil;
import org.eclipse.jetty.websocket.api.WebSocketException;
import org.eclipse.jetty.websocket.api.extensions.Extension;
import org.eclipse.jetty.websocket.api.extensions.ExtensionConfig;
import org.eclipse.jetty.websocket.api.extensions.ExtensionFactory;
import org.eclipse.jetty.websocket.common.extensions.AbstractExtension;
import org.eclipse.jetty.websocket.common.scopes.WebSocketContainerScope;

public class WebSocketExtensionFactory
extends ExtensionFactory {
    private WebSocketContainerScope container;

    public WebSocketExtensionFactory(WebSocketContainerScope container) {
        this.container = container;
    }

    @Override
    public Extension newInstance(ExtensionConfig config) {
        if (config == null) {
            return null;
        }
        String name = config.getName();
        if (StringUtil.isBlank(name)) {
            return null;
        }
        Class<? extends Extension> extClass = this.getExtension(name);
        if (extClass == null) {
            return null;
        }
        try {
            Extension ext = this.container.getObjectFactory().createInstance(extClass);
            if (ext instanceof AbstractExtension) {
                AbstractExtension aext = (AbstractExtension)ext;
                aext.init(this.container);
                aext.setConfig(config);
            }
            return ext;
        }
        catch (IllegalAccessException | InstantiationException e) {
            throw new WebSocketException("Cannot instantiate extension: " + extClass, e);
        }
    }
}

