/*
 * Decompiled with CFR 0.152.
 */
package info.journeymap.shaded.kotlin.spark.resource;

import info.journeymap.shaded.kotlin.spark.resource.AbstractFileResolvingResource;
import info.journeymap.shaded.kotlin.spark.resource.AbstractResourceHandler;
import info.journeymap.shaded.kotlin.spark.resource.ClassPathResource;
import info.journeymap.shaded.kotlin.spark.resource.UriPath;
import info.journeymap.shaded.kotlin.spark.staticfiles.DirectoryTraversal;
import info.journeymap.shaded.kotlin.spark.utils.Assert;
import info.journeymap.shaded.org.slf4j.Logger;
import info.journeymap.shaded.org.slf4j.LoggerFactory;
import java.net.MalformedURLException;

public class ClassPathResourceHandler
extends AbstractResourceHandler {
    private static final Logger LOG = LoggerFactory.getLogger(ClassPathResourceHandler.class);
    private final String baseResource;
    private String welcomeFile;

    public ClassPathResourceHandler(String baseResource) {
        this(baseResource, null);
    }

    public ClassPathResourceHandler(String baseResource, String welcomeFile) {
        Assert.notNull(baseResource);
        this.baseResource = baseResource;
        this.welcomeFile = welcomeFile;
    }

    @Override
    protected AbstractFileResolvingResource getResource(String path) throws MalformedURLException {
        if (path == null || !path.startsWith("/")) {
            throw new MalformedURLException(path);
        }
        try {
            path = UriPath.canonical(path);
            String addedPath = ClassPathResourceHandler.addPaths(this.baseResource, path);
            ClassPathResource resource = new ClassPathResource(addedPath);
            if (resource.exists() && path.endsWith("/")) {
                resource = this.welcomeFile != null ? new ClassPathResource(ClassPathResourceHandler.addPaths(resource.getPath(), this.welcomeFile)) : null;
            }
            if (resource != null && resource.exists()) {
                DirectoryTraversal.protectAgainstInClassPath(resource.getPath());
                return resource;
            }
            return null;
        }
        catch (DirectoryTraversal.DirectoryTraversalDetection directoryTraversalDetection) {
            throw directoryTraversalDetection;
        }
        catch (Exception e) {
            if (LOG.isDebugEnabled()) {
                LOG.debug(e.getClass().getSimpleName() + " when trying to get resource. " + e.getMessage());
            }
            return null;
        }
    }
}

