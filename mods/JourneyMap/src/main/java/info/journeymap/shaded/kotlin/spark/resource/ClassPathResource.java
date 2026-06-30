/*
 * Decompiled with CFR 0.152.
 */
package info.journeymap.shaded.kotlin.spark.resource;

import info.journeymap.shaded.kotlin.spark.resource.AbstractFileResolvingResource;
import info.journeymap.shaded.kotlin.spark.resource.Resource;
import info.journeymap.shaded.kotlin.spark.utils.Assert;
import info.journeymap.shaded.kotlin.spark.utils.ClassUtils;
import info.journeymap.shaded.kotlin.spark.utils.StringUtils;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

public class ClassPathResource
extends AbstractFileResolvingResource {
    private final String path;
    private ClassLoader classLoader;
    private Class<?> clazz;

    public ClassPathResource(String path) {
        this(path, null);
    }

    public ClassPathResource(String path, ClassLoader classLoader) {
        Assert.notNull(path, "Path must not be null");
        String pathToUse = StringUtils.cleanPath(path);
        if (pathToUse.startsWith("/")) {
            pathToUse = pathToUse.substring(1);
        }
        this.path = pathToUse;
        this.classLoader = classLoader != null ? classLoader : ClassUtils.getDefaultClassLoader();
    }

    protected ClassPathResource(String path, ClassLoader classLoader, Class<?> clazz) {
        this.path = StringUtils.cleanPath(path);
        this.classLoader = classLoader;
        this.clazz = clazz;
    }

    public final String getPath() {
        return this.path;
    }

    @Override
    public boolean exists() {
        URL url = this.clazz != null ? this.clazz.getResource(this.path) : this.classLoader.getResource(this.path);
        return url != null;
    }

    @Override
    public InputStream getInputStream() throws IOException {
        InputStream is = this.clazz != null ? this.clazz.getResourceAsStream(this.path) : this.classLoader.getResourceAsStream(this.path);
        if (is == null) {
            throw new FileNotFoundException(this.getDescription() + " cannot be opened because it does not exist");
        }
        return is;
    }

    @Override
    public URL getURL() throws IOException {
        URL url = this.clazz != null ? this.clazz.getResource(this.path) : this.classLoader.getResource(this.path);
        if (url == null) {
            throw new FileNotFoundException(this.getDescription() + " cannot be resolved to URL because it does not exist");
        }
        return url;
    }

    @Override
    public Resource createRelative(String relativePath) {
        String pathToUse = StringUtils.applyRelativePath(this.path, relativePath);
        return new ClassPathResource(pathToUse, this.classLoader, this.clazz);
    }

    @Override
    public String getFilename() {
        return StringUtils.getFilename(this.path);
    }

    @Override
    public String getDescription() {
        StringBuilder builder = new StringBuilder("class path resource [");
        String pathToUse = this.path;
        if (this.clazz != null && !pathToUse.startsWith("/")) {
            builder.append(ClassUtils.classPackageAsResourcePath(this.clazz));
            builder.append('/');
        }
        if (pathToUse.startsWith("/")) {
            pathToUse = pathToUse.substring(1);
        }
        builder.append(pathToUse);
        builder.append(']');
        return builder.toString();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof ClassPathResource) {
            ClassPathResource otherRes = (ClassPathResource)obj;
            ClassLoader thisLoader = this.classLoader;
            ClassLoader otherLoader = otherRes.classLoader;
            return this.path.equals(otherRes.path) && thisLoader.equals(otherLoader) && this.clazz.equals(otherRes.clazz);
        }
        return false;
    }

    @Override
    public int hashCode() {
        return this.path.hashCode();
    }
}

