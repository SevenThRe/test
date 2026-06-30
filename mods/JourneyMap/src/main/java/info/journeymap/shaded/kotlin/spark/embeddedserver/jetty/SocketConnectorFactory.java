/*
 * Decompiled with CFR 0.152.
 */
package info.journeymap.shaded.kotlin.spark.embeddedserver.jetty;

import info.journeymap.shaded.kotlin.spark.ssl.SslStores;
import info.journeymap.shaded.kotlin.spark.utils.Assert;
import java.util.concurrent.TimeUnit;
import org.eclipse.jetty.server.ForwardedRequestCustomizer;
import org.eclipse.jetty.server.HttpConfiguration;
import org.eclipse.jetty.server.HttpConnectionFactory;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.ServerConnector;
import org.eclipse.jetty.util.ssl.SslContextFactory;

public class SocketConnectorFactory {
    public static ServerConnector createSocketConnector(Server server, String host, int port) {
        Assert.notNull(server, "'server' must not be null");
        Assert.notNull(host, "'host' must not be null");
        HttpConnectionFactory httpConnectionFactory = SocketConnectorFactory.createHttpConnectionFactory();
        ServerConnector connector = new ServerConnector(server, httpConnectionFactory);
        SocketConnectorFactory.initializeConnector(connector, host, port);
        return connector;
    }

    public static ServerConnector createSecureSocketConnector(Server server, String host, int port, SslStores sslStores) {
        Assert.notNull(server, "'server' must not be null");
        Assert.notNull(host, "'host' must not be null");
        Assert.notNull(sslStores, "'sslStores' must not be null");
        SslContextFactory sslContextFactory = new SslContextFactory(sslStores.keystoreFile());
        if (sslStores.keystorePassword() != null) {
            sslContextFactory.setKeyStorePassword(sslStores.keystorePassword());
        }
        if (sslStores.trustStoreFile() != null) {
            sslContextFactory.setTrustStorePath(sslStores.trustStoreFile());
        }
        if (sslStores.trustStorePassword() != null) {
            sslContextFactory.setTrustStorePassword(sslStores.trustStorePassword());
        }
        if (sslStores.needsClientCert()) {
            sslContextFactory.setNeedClientAuth(true);
            sslContextFactory.setWantClientAuth(true);
        }
        HttpConnectionFactory httpConnectionFactory = SocketConnectorFactory.createHttpConnectionFactory();
        ServerConnector connector = new ServerConnector(server, sslContextFactory, httpConnectionFactory);
        SocketConnectorFactory.initializeConnector(connector, host, port);
        return connector;
    }

    private static void initializeConnector(ServerConnector connector, String host, int port) {
        connector.setIdleTimeout(TimeUnit.HOURS.toMillis(1L));
        connector.setSoLingerTime(-1);
        connector.setHost(host);
        connector.setPort(port);
    }

    private static HttpConnectionFactory createHttpConnectionFactory() {
        HttpConfiguration httpConfig = new HttpConfiguration();
        httpConfig.setSecureScheme("https");
        httpConfig.addCustomizer(new ForwardedRequestCustomizer());
        return new HttpConnectionFactory(httpConfig);
    }
}

