/*
 * Decompiled with CFR 0.152.
 */
package org.eclipse.jetty.websocket.common.events;

import java.io.InputStream;
import java.io.Reader;
import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import org.eclipse.jetty.util.log.Log;
import org.eclipse.jetty.util.log.Logger;
import org.eclipse.jetty.websocket.api.Session;
import org.eclipse.jetty.websocket.api.annotations.OnWebSocketClose;
import org.eclipse.jetty.websocket.api.annotations.OnWebSocketConnect;
import org.eclipse.jetty.websocket.api.annotations.OnWebSocketError;
import org.eclipse.jetty.websocket.api.annotations.OnWebSocketFrame;
import org.eclipse.jetty.websocket.api.annotations.OnWebSocketMessage;
import org.eclipse.jetty.websocket.api.extensions.Frame;
import org.eclipse.jetty.websocket.common.events.JettyAnnotatedMetadata;
import org.eclipse.jetty.websocket.common.events.ParamList;
import org.eclipse.jetty.websocket.common.events.annotated.AbstractMethodAnnotationScanner;
import org.eclipse.jetty.websocket.common.events.annotated.CallableMethod;
import org.eclipse.jetty.websocket.common.events.annotated.InvalidSignatureException;
import org.eclipse.jetty.websocket.common.events.annotated.OptionalSessionCallableMethod;

public class JettyAnnotatedScanner
extends AbstractMethodAnnotationScanner<JettyAnnotatedMetadata> {
    private static final Logger LOG = Log.getLogger(JettyAnnotatedScanner.class);
    private static final ParamList validBinaryParams;
    private static final ParamList validConnectParams;
    private static final ParamList validCloseParams;
    private static final ParamList validErrorParams;
    private static final ParamList validFrameParams;
    private static final ParamList validTextParams;

    @Override
    public void onMethodAnnotation(JettyAnnotatedMetadata metadata, Class<?> pojo, Method method, Annotation annotation) {
        if (LOG.isDebugEnabled()) {
            LOG.debug("onMethodAnnotation({}, {}, {}, {})", metadata, pojo, method, annotation);
        }
        if (this.isAnnotation(annotation, OnWebSocketConnect.class)) {
            this.assertValidSignature(method, OnWebSocketConnect.class, validConnectParams);
            this.assertUnset(metadata.onConnect, OnWebSocketConnect.class, method);
            metadata.onConnect = new CallableMethod(pojo, method);
            return;
        }
        if (this.isAnnotation(annotation, OnWebSocketMessage.class)) {
            if (this.isSignatureMatch(method, validTextParams)) {
                this.assertUnset(metadata.onText, OnWebSocketMessage.class, method);
                metadata.onText = new OptionalSessionCallableMethod(pojo, method);
                return;
            }
            if (this.isSignatureMatch(method, validBinaryParams)) {
                this.assertUnset(metadata.onBinary, OnWebSocketMessage.class, method);
                metadata.onBinary = new OptionalSessionCallableMethod(pojo, method);
                return;
            }
            throw InvalidSignatureException.build(method, OnWebSocketMessage.class, validTextParams, validBinaryParams);
        }
        if (this.isAnnotation(annotation, OnWebSocketClose.class)) {
            this.assertValidSignature(method, OnWebSocketClose.class, validCloseParams);
            this.assertUnset(metadata.onClose, OnWebSocketClose.class, method);
            metadata.onClose = new OptionalSessionCallableMethod(pojo, method);
            return;
        }
        if (this.isAnnotation(annotation, OnWebSocketError.class)) {
            this.assertValidSignature(method, OnWebSocketError.class, validErrorParams);
            this.assertUnset(metadata.onError, OnWebSocketError.class, method);
            metadata.onError = new OptionalSessionCallableMethod(pojo, method);
            return;
        }
        if (this.isAnnotation(annotation, OnWebSocketFrame.class)) {
            this.assertValidSignature(method, OnWebSocketFrame.class, validFrameParams);
            this.assertUnset(metadata.onFrame, OnWebSocketFrame.class, method);
            metadata.onFrame = new OptionalSessionCallableMethod(pojo, method);
            return;
        }
    }

    public JettyAnnotatedMetadata scan(Class<?> pojo) {
        JettyAnnotatedMetadata metadata = new JettyAnnotatedMetadata();
        this.scanMethodAnnotations(metadata, pojo);
        return metadata;
    }

    static {
        validConnectParams = new ParamList();
        validConnectParams.addParams(Session.class);
        validCloseParams = new ParamList();
        validCloseParams.addParams(Integer.TYPE, String.class);
        validCloseParams.addParams(Session.class, Integer.TYPE, String.class);
        validErrorParams = new ParamList();
        validErrorParams.addParams(Throwable.class);
        validErrorParams.addParams(Session.class, Throwable.class);
        validTextParams = new ParamList();
        validTextParams.addParams(String.class);
        validTextParams.addParams(Session.class, String.class);
        validTextParams.addParams(Reader.class);
        validTextParams.addParams(Session.class, Reader.class);
        validBinaryParams = new ParamList();
        validBinaryParams.addParams(byte[].class, Integer.TYPE, Integer.TYPE);
        validBinaryParams.addParams(Session.class, byte[].class, Integer.TYPE, Integer.TYPE);
        validBinaryParams.addParams(InputStream.class);
        validBinaryParams.addParams(Session.class, InputStream.class);
        validFrameParams = new ParamList();
        validFrameParams.addParams(Frame.class);
        validFrameParams.addParams(Session.class, Frame.class);
    }
}

