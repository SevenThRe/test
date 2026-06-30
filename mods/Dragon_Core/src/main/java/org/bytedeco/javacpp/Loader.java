/*
 * Decompiled with CFR 0.152.
 */
package org.bytedeco.javacpp;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.HttpURLConnection;
import java.net.JarURLConnection;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.URLConnection;
import java.nio.channels.FileChannel;
import java.nio.channels.FileLock;
import java.nio.channels.spi.AbstractInterruptibleChannel;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.FileAttribute;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Deque;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.WeakHashMap;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;
import org.bytedeco.javacpp.ClassProperties;
import org.bytedeco.javacpp.Pointer;
import org.bytedeco.javacpp.annotation.Cast;
import org.bytedeco.javacpp.annotation.Name;
import org.bytedeco.javacpp.annotation.Platform;
import org.bytedeco.javacpp.annotation.Properties;
import org.bytedeco.javacpp.annotation.Raw;
import org.bytedeco.javacpp.presets.javacpp;
import org.bytedeco.javacpp.tools.Logger;

@Properties(inherit={javacpp.class})
public class Loader {
    private static final Logger logger;
    private static final String PLATFORM;
    private static java.util.Properties platformProperties;
    private static final ThreadLocal<Deque<Class<?>>> classStack;
    static File cacheDir;
    static File tempDir;
    static Map<String, URL[]> foundLibraries;
    static Map<String, String> loadedLibraries;
    static boolean canCreateSymbolicLink;
    static boolean pathsFirst;
    static WeakHashMap<Class<? extends Pointer>, HashMap<String, Integer>> memberOffsets;

    public static String getPlatform() {
        return PLATFORM;
    }

    public static java.util.Properties loadProperties() {
        String name = Loader.getPlatform();
        if (platformProperties != null && name.equals(platformProperties.getProperty("platform"))) {
            return platformProperties;
        }
        platformProperties = Loader.loadProperties(name, null);
        return platformProperties;
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     * Loose catch block
     */
    public static java.util.Properties loadProperties(String name, String defaults) {
        java.util.Properties p2;
        block31: {
            if (defaults == null) {
                defaults = "generic";
            }
            p2 = new java.util.Properties();
            p2.put("platform", name);
            p2.put("platform.path.separator", File.pathSeparator);
            String s2 = System.mapLibraryName("/");
            int i2 = s2.indexOf(47);
            p2.put("platform.library.prefix", s2.substring(0, i2));
            p2.put("platform.library.suffix", s2.substring(i2 + 1));
            name = "properties/" + name + ".properties";
            InputStream is2 = Loader.class.getResourceAsStream(name);
            try {
                try {
                    p2.load(new InputStreamReader(is2));
                }
                catch (NoSuchMethodError e2) {
                    p2.load(is2);
                }
            }
            catch (Exception e3) {
                name = "properties/" + defaults + ".properties";
                InputStream is22 = Loader.class.getResourceAsStream(name);
                try {
                    p2.load(new InputStreamReader(is22));
                }
                catch (NoSuchMethodError e2) {
                    p2.load(is22);
                }
                try {
                    if (is22 != null) {
                        is22.close();
                    }
                    break block31;
                }
                catch (IOException ex2) {
                    logger.error("Unable to close resource : " + ex2.getMessage());
                }
                break block31;
                catch (Exception ex2) {
                    try {
                        if (is22 != null) {
                            is22.close();
                        }
                        break block31;
                    }
                    catch (IOException ex3) {
                        logger.error("Unable to close resource : " + ex3.getMessage());
                    }
                    break block31;
                    catch (Throwable throwable) {
                        try {
                            if (is22 != null) {
                                is22.close();
                            }
                        }
                        catch (IOException ex4) {
                            logger.error("Unable to close resource : " + ex4.getMessage());
                        }
                        throw throwable;
                    }
                }
            }
            finally {
                try {
                    if (is2 != null) {
                        is2.close();
                    }
                }
                catch (IOException ex5) {
                    logger.error("Unable to close resource : " + ex5.getMessage());
                }
            }
        }
        for (Map.Entry<Object, Object> e4 : System.getProperties().entrySet()) {
            if (!(e4.getKey() instanceof String) || !(e4.getValue() instanceof String)) continue;
            String key = (String)e4.getKey();
            String value = (String)e4.getValue();
            if (key == null || value == null || !key.startsWith("org.bytedeco.javacpp.platform.")) continue;
            p2.put(key.substring(key.indexOf("platform.")), value);
        }
        return p2;
    }

    public static boolean checkVersion(String groupId, String artifactId) {
        return Loader.checkVersion(groupId, artifactId, "-", true, Loader.getCallerClass(2));
    }

    public static boolean checkVersion(String groupId, String artifactId, String separator, boolean logWarnings, Class cls) {
        try {
            int n2;
            String javacppVersion = Loader.getVersion();
            String version = Loader.getVersion(groupId, artifactId, cls);
            if (version == null) {
                if (logWarnings && Loader.isLoadLibraries()) {
                    logger.warn("Version of " + groupId + ":" + artifactId + " could not be found.");
                }
                return false;
            }
            String[] javacppVersions = javacppVersion.split(separator);
            String[] versions = version.split(separator);
            boolean matches = versions[n2 = versions.length - (versions[versions.length - 1].equals("SNAPSHOT") ? 2 : 1)].equals(javacppVersions[0]);
            if (!matches && logWarnings && Loader.isLoadLibraries()) {
                logger.warn("Versions of org.bytedeco:javacpp:" + javacppVersion + " and " + groupId + ":" + artifactId + ":" + version + " do not match.");
            }
            return matches;
        }
        catch (Exception ex2) {
            if (logWarnings && Loader.isLoadLibraries()) {
                logger.warn("Unable to load properties : " + ex2.getMessage());
            }
            return false;
        }
    }

    public static String getVersion() throws IOException {
        return Loader.getVersion("org.bytedeco", "javacpp");
    }

    public static String getVersion(String groupId, String artifactId) throws IOException {
        return Loader.getVersion(groupId, artifactId, Loader.getCallerClass(2));
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public static String getVersion(String groupId, String artifactId, Class cls) throws IOException {
        java.util.Properties p2 = new java.util.Properties();
        InputStream is2 = cls.getClassLoader().getResourceAsStream("META-INF/maven/" + groupId + "/" + artifactId + "/pom.properties");
        if (is2 == null) {
            return null;
        }
        try {
            try {
                p2.load(new InputStreamReader(is2));
            }
            catch (NoSuchMethodError e2) {
                p2.load(is2);
            }
            String string = p2.getProperty("version");
            return string;
        }
        finally {
            try {
                if (is2 != null) {
                    is2.close();
                }
            }
            catch (IOException ex2) {
                logger.error("Unable to close resource : " + ex2.getMessage());
            }
        }
    }

    public static Class getEnclosingClass(Class cls) {
        Platform p2;
        Class<?> c2 = cls;
        while (c2.getEnclosingClass() != null && !c2.isAnnotationPresent(Properties.class) && (!c2.isAnnotationPresent(Platform.class) || (p2 = c2.getAnnotation(Platform.class)).pragma().length <= 0 && p2.define().length <= 0 && p2.exclude().length <= 0 && p2.include().length <= 0 && p2.cinclude().length <= 0 && p2.includepath().length <= 0 && p2.includeresource().length <= 0 && p2.compiler().length <= 0 && p2.linkpath().length <= 0 && p2.linkresource().length <= 0 && p2.link().length <= 0 && p2.frameworkpath().length <= 0 && p2.framework().length <= 0 && p2.preloadresource().length <= 0 && p2.preloadpath().length <= 0 && p2.preload().length <= 0 && p2.resourcepath().length <= 0 && p2.resource().length <= 0 && p2.library().length() <= 0)) {
            c2 = c2.getEnclosingClass();
        }
        return c2;
    }

    public static ClassProperties loadProperties(Class[] cls, java.util.Properties properties, boolean inherit) {
        ClassProperties cp2 = new ClassProperties(properties);
        if (cls != null) {
            for (Class c2 : cls) {
                cp2.load(c2, inherit);
            }
        }
        return cp2;
    }

    public static ClassProperties loadProperties(Class cls, java.util.Properties properties, boolean inherit) {
        ClassProperties cp2 = new ClassProperties(properties);
        if (cls != null) {
            cp2.load(cls, inherit);
        }
        return cp2;
    }

    public static Class getCallerClass(int i2) {
        Class[] classContext = null;
        try {
            classContext = new SecurityManager(){

                public Class[] getClassContext() {
                    return super.getClassContext();
                }
            }.getClassContext();
        }
        catch (NoSuchMethodError | SecurityException e2) {
            logger.warn("Could not create an instance of SecurityManager: " + e2.getMessage());
        }
        if (classContext != null) {
            for (int j2 = 0; j2 < classContext.length; ++j2) {
                if (classContext[j2] != Loader.class) continue;
                return classContext[i2 + j2];
            }
        } else {
            try {
                StackTraceElement[] classNames = Thread.currentThread().getStackTrace();
                for (int j3 = 0; j3 < classNames.length; ++j3) {
                    if (Class.forName(classNames[j3].getClassName()) != Loader.class) continue;
                    return Class.forName(classNames[i2 + j3].getClassName());
                }
            }
            catch (ClassNotFoundException e3) {
                logger.error("No definition for the class found : " + e3.getMessage());
            }
        }
        return null;
    }

    public static File cacheResource(String name) throws IOException {
        Class cls = Loader.getCallerClass(2);
        return Loader.cacheResource(cls, name);
    }

    public static File cacheResource(Class cls, String name) throws IOException {
        URL u2 = Loader.findResource(cls, name);
        return u2 != null ? Loader.cacheResource(u2) : null;
    }

    public static File[] cacheResources(String name) throws IOException {
        Class cls = Loader.getCallerClass(2);
        return Loader.cacheResources(cls, name);
    }

    public static File[] cacheResources(Class cls, String name) throws IOException {
        URL[] urls = Loader.findResources(cls, name);
        File[] files = new File[urls.length];
        for (int i2 = 0; i2 < urls.length; ++i2) {
            files[i2] = Loader.cacheResource(urls[i2]);
        }
        return files;
    }

    public static File cacheResource(URL resourceURL) throws IOException {
        return Loader.cacheResource(resourceURL, null);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    public static File cacheResource(URL resourceURL, String target) throws IOException {
        Path path;
        Runtime runtime;
        File urlFile;
        String[] splitURL = resourceURL.toString().split("#");
        try {
            resourceURL = new URL(splitURL[0]);
            urlFile = new File(new URI(splitURL[0]));
        }
        catch (IllegalArgumentException | URISyntaxException e2) {
            urlFile = new File(resourceURL.getPath());
        }
        String name = urlFile.getName();
        boolean reference = false;
        long size = 0L;
        long timestamp = 0L;
        File cacheDir = Loader.getCacheDir();
        File cacheSubdir = cacheDir.getCanonicalFile();
        String s2 = System.getProperty("org.bytedeco.javacpp.cachedir.nosubdir", "false").toLowerCase();
        boolean noSubdir = s2.equals("true") || s2.equals("t") || s2.equals("");
        URLConnection urlConnection = resourceURL.openConnection();
        if (urlConnection instanceof JarURLConnection) {
            JarFile jarFile = ((JarURLConnection)urlConnection).getJarFile();
            JarEntry jarEntry = ((JarURLConnection)urlConnection).getJarEntry();
            File jarFileFile = new File(jarFile.getName());
            File jarEntryFile = new File(jarEntry.getName());
            size = jarEntry.getSize();
            timestamp = jarEntry.getTime();
            if (!noSubdir) {
                String subdirName = jarFileFile.getName();
                String parentName = jarEntryFile.getParent();
                if (parentName != null) {
                    subdirName = subdirName + File.separator + parentName;
                }
                cacheSubdir = new File(cacheSubdir, subdirName);
            }
        } else if (urlConnection instanceof HttpURLConnection) {
            size = urlConnection.getContentLength();
            timestamp = urlConnection.getLastModified();
            if (!noSubdir) {
                String path2 = resourceURL.getHost() + resourceURL.getPath();
                cacheSubdir = new File(cacheSubdir, path2.substring(0, path2.lastIndexOf(47) + 1));
            }
        } else if (resourceURL.getProtocol().equals("jrt")) {
            String p2 = resourceURL.getPath();
            try {
                Path path3 = Paths.get(new URI("jrt", p2, null));
                try {
                    size = Files.size(path3);
                }
                catch (NoSuchFileException e3) {
                    path3 = Paths.get(new URI("jrt", "/modules" + p2, null));
                    size = Files.size(path3);
                }
                timestamp = Files.getLastModifiedTime(path3, new LinkOption[0]).toMillis();
            }
            catch (URISyntaxException e4) {
                size = 0L;
                timestamp = 0L;
            }
            if (!noSubdir) {
                cacheSubdir = new File(cacheSubdir, urlFile.getParentFile().getName());
            }
        } else {
            if (urlFile.exists()) {
                size = urlFile.length();
                timestamp = urlFile.lastModified();
            } else if (urlConnection != null) {
                size = urlConnection.getContentLengthLong();
                timestamp = urlConnection.getLastModified();
            }
            if (!noSubdir) {
                cacheSubdir = new File(cacheSubdir, urlFile.getParentFile().getName());
            }
        }
        if (splitURL.length > 1 && splitURL[1] != null && splitURL[1].length() > 0) {
            String newName = splitURL[1];
            reference = newName.equals(name);
            name = newName;
        }
        File file = new File(cacheSubdir, name);
        File lockFile = new File(cacheDir, ".lock");
        AbstractInterruptibleChannel lockChannel = null;
        FileLock lock = null;
        if (canCreateSymbolicLink && target != null && target.length() > 0) {
            runtime = Runtime.getRuntime();
            synchronized (runtime) {
                block71: {
                    try {
                        path = file.toPath();
                        Path targetPath = Paths.get(target, new String[0]).normalize();
                        if (!(file.exists() && Files.isSymbolicLink(path) && Files.readSymbolicLink(path).equals(targetPath) || !targetPath.isAbsolute() || targetPath.equals(path) || targetPath.toRealPath(new LinkOption[0]).equals(path))) {
                            if (logger.isDebugEnabled()) {
                                logger.debug("Locking " + cacheDir + " to create symbolic link");
                            }
                            lockChannel = new FileOutputStream(lockFile).getChannel();
                            lock = ((FileChannel)lockChannel).lock();
                            if (!(file.exists() && Files.isSymbolicLink(path) && Files.readSymbolicLink(path).equals(targetPath) || !targetPath.isAbsolute() || targetPath.equals(path) || targetPath.toRealPath(new LinkOption[0]).equals(path))) {
                                if (logger.isDebugEnabled()) {
                                    logger.debug("Creating symbolic link " + path + " to " + targetPath);
                                }
                                try {
                                    file.getParentFile().mkdirs();
                                    Files.createSymbolicLink(path, targetPath, new FileAttribute[0]);
                                }
                                catch (FileAlreadyExistsException e5) {
                                    file.delete();
                                    Files.createSymbolicLink(path, targetPath, new FileAttribute[0]);
                                }
                            }
                        }
                        if (lock == null) break block71;
                    }
                    catch (IOException | RuntimeException e6) {
                        File targetPath;
                        block72: {
                            try {
                                canCreateSymbolicLink = false;
                                if (logger.isDebugEnabled()) {
                                    logger.debug("Failed to create symbolic link " + file + ": " + e6);
                                }
                                targetPath = null;
                                if (lock == null) break block72;
                            }
                            catch (Throwable throwable) {
                                if (lock != null) {
                                    lock.release();
                                }
                                if (lockChannel == null) throw throwable;
                                lockChannel.close();
                                throw throwable;
                            }
                            lock.release();
                        }
                        if (lockChannel == null) return targetPath;
                        lockChannel.close();
                        return targetPath;
                    }
                    lock.release();
                }
                if (lockChannel == null) return file;
                lockChannel.close();
                return file;
            }
        }
        if (canCreateSymbolicLink && urlFile.exists() && reference) {
            runtime = Runtime.getRuntime();
            synchronized (runtime) {
                File file2;
                block73: {
                    try {
                        path = file.toPath();
                        Path urlPath = urlFile.toPath().normalize();
                        if (!(file.exists() && Files.isSymbolicLink(path) && Files.readSymbolicLink(path).equals(urlPath) || !urlPath.isAbsolute() || urlPath.equals(path) || urlPath.toRealPath(new LinkOption[0]).equals(path))) {
                            if (logger.isDebugEnabled()) {
                                logger.debug("Locking " + cacheDir + " to create symbolic link");
                            }
                            lockChannel = new FileOutputStream(lockFile).getChannel();
                            lock = ((FileChannel)lockChannel).lock();
                            if (!(file.exists() && Files.isSymbolicLink(path) && Files.readSymbolicLink(path).equals(urlPath) || !urlPath.isAbsolute() || urlPath.equals(path) || urlPath.toRealPath(new LinkOption[0]).equals(path))) {
                                if (logger.isDebugEnabled()) {
                                    logger.debug("Creating symbolic link " + path + " to " + urlPath);
                                }
                                try {
                                    file.getParentFile().mkdirs();
                                    Files.createSymbolicLink(path, urlPath, new FileAttribute[0]);
                                }
                                catch (FileAlreadyExistsException e7) {
                                    file.delete();
                                    Files.createSymbolicLink(path, urlPath, new FileAttribute[0]);
                                }
                            }
                        }
                        file2 = file;
                        if (lock == null) break block73;
                    }
                    catch (IOException | RuntimeException e8) {
                        try {
                            canCreateSymbolicLink = false;
                            if (logger.isDebugEnabled()) {
                                logger.debug("Could not create symbolic link " + file + ": " + e8);
                            }
                        }
                        catch (Throwable throwable) {
                            throw throwable;
                        }
                        finally {
                            if (lock != null) {
                                lock.release();
                            }
                            if (lockChannel != null) {
                                lockChannel.close();
                            }
                        }
                    }
                    lock.release();
                }
                if (lockChannel == null) return file2;
                lockChannel.close();
                return file2;
            }
        }
        if (file.exists() && file.length() == size && file.lastModified() == timestamp && cacheSubdir.equals(file.getCanonicalFile().getParentFile())) return file;
        runtime = Runtime.getRuntime();
        synchronized (runtime) {
            try {
                if (logger.isDebugEnabled()) {
                    logger.debug("Locking " + cacheDir + " before extracting");
                }
                lockChannel = new FileOutputStream(lockFile).getChannel();
                lock = ((FileChannel)lockChannel).lock();
                if (file.exists() && file.length() == size && file.lastModified() == timestamp && cacheSubdir.equals(file.getCanonicalFile().getParentFile())) return file;
                if (logger.isDebugEnabled()) {
                    logger.debug("Extracting " + resourceURL);
                }
                file.delete();
                Loader.extractResource(resourceURL, file, null, null, true);
                file.setLastModified(timestamp);
            }
            finally {
                if (lock != null) {
                    lock.release();
                }
                if (lockChannel != null) {
                    lockChannel.close();
                }
            }
            return file;
        }
    }

    public static File extractResource(String name, File directory, String prefix, String suffix) throws IOException {
        Class cls = Loader.getCallerClass(2);
        return Loader.extractResource(cls, name, directory, prefix, suffix);
    }

    public static File extractResource(Class cls, String name, File directory, String prefix, String suffix) throws IOException {
        URL u2 = Loader.findResource(cls, name);
        return u2 != null ? Loader.extractResource(u2, directory, prefix, suffix) : null;
    }

    public static File[] extractResources(String name, File directory, String prefix, String suffix) throws IOException {
        Class cls = Loader.getCallerClass(2);
        return Loader.extractResources(cls, name, directory, prefix, suffix);
    }

    public static File[] extractResources(Class cls, String name, File directory, String prefix, String suffix) throws IOException {
        URL[] urls = Loader.findResources(cls, name);
        File[] files = new File[urls.length];
        for (int i2 = 0; i2 < urls.length; ++i2) {
            files[i2] = Loader.extractResource(urls[i2], directory, prefix, suffix);
        }
        return files;
    }

    public static File extractResource(URL resourceURL, File directoryOrFile, String prefix, String suffix) throws IOException {
        return Loader.extractResource(resourceURL, directoryOrFile, prefix, suffix, false);
    }

    public static File extractResource(URL resourceURL, File directoryOrFile, String prefix, String suffix, boolean cacheDirectory) throws IOException {
        URLConnection urlConnection;
        URLConnection uRLConnection = urlConnection = resourceURL != null ? resourceURL.openConnection() : null;
        if (urlConnection instanceof JarURLConnection) {
            JarFile jarFile = ((JarURLConnection)urlConnection).getJarFile();
            JarEntry jarEntry = ((JarURLConnection)urlConnection).getJarEntry();
            String jarFileName = jarFile.getName();
            String jarEntryName = jarEntry.getName();
            if (!jarEntryName.endsWith("/")) {
                jarEntryName = jarEntryName + "/";
            }
            if (jarEntry.isDirectory() || jarFile.getJarEntry(jarEntryName) != null) {
                Enumeration<JarEntry> entries = jarFile.entries();
                while (entries.hasMoreElements()) {
                    JarEntry entry = entries.nextElement();
                    String entryName = entry.getName();
                    long entrySize = entry.getSize();
                    long entryTimestamp = entry.getTime();
                    if (!entryName.startsWith(jarEntryName)) continue;
                    File file = new File(directoryOrFile, entryName.substring(jarEntryName.length()));
                    if (entry.isDirectory()) {
                        file.mkdirs();
                    } else if (!(cacheDirectory && file.exists() && file.length() == entrySize && file.lastModified() == entryTimestamp && file.equals(file.getCanonicalFile()))) {
                        file.delete();
                        String s2 = resourceURL.toString();
                        URL u2 = new URL(s2.substring(0, s2.indexOf("!/") + 2) + entryName);
                        file = Loader.extractResource(u2, file, prefix, suffix);
                    }
                    file.setLastModified(entryTimestamp);
                }
                return directoryOrFile;
            }
        }
        InputStream is2 = urlConnection != null ? urlConnection.getInputStream() : null;
        OutputStream os2 = null;
        if (is2 == null) {
            return null;
        }
        File file = null;
        boolean fileExisted = false;
        try {
            int length;
            if (prefix == null && suffix == null) {
                File directory;
                if (directoryOrFile == null) {
                    directoryOrFile = new File(System.getProperty("java.io.tmpdir"));
                }
                if (directoryOrFile.isDirectory()) {
                    directory = directoryOrFile;
                    try {
                        file = new File(directoryOrFile, new File(new URI(resourceURL.toString().split("#")[0])).getName());
                    }
                    catch (IllegalArgumentException | URISyntaxException ex2) {
                        file = new File(directoryOrFile, new File(resourceURL.getPath()).getName());
                    }
                } else {
                    directory = directoryOrFile.getParentFile();
                    file = directoryOrFile;
                }
                if (directory != null) {
                    directory.mkdirs();
                }
                fileExisted = file.exists();
            } else {
                file = File.createTempFile(prefix, suffix, directoryOrFile);
            }
            file.delete();
            os2 = new FileOutputStream(file);
            byte[] buffer = new byte[65536];
            while ((length = is2.read(buffer)) != -1) {
                os2.write(buffer, 0, length);
            }
        }
        catch (IOException e2) {
            if (file != null && !fileExisted) {
                file.delete();
            }
            throw e2;
        }
        finally {
            is2.close();
            if (os2 != null) {
                os2.close();
            }
        }
        return file;
    }

    public static URL findResource(Class cls, String name) throws IOException {
        URL[] url = Loader.findResources(cls, name, 1);
        return url.length > 0 ? url[0] : null;
    }

    public static URL[] findResources(Class cls, String name) throws IOException {
        return Loader.findResources(cls, name, -1);
    }

    public static URL[] findResources(Class cls, String name, int maxLength) throws IOException {
        if (maxLength == 0) {
            return new URL[0];
        }
        while (name.contains("//")) {
            name = name.replace("//", "/");
        }
        URL url = cls.getResource(name);
        if (url != null && maxLength == 1) {
            return new URL[]{url};
        }
        String path = "";
        if (!name.startsWith("/")) {
            String s2 = cls.getName().replace('.', '/');
            int n2 = s2.lastIndexOf(47);
            if (n2 >= 0) {
                path = s2.substring(0, n2 + 1);
            }
        } else {
            name = name.substring(1);
        }
        ClassLoader classLoader = cls.getClassLoader();
        if (classLoader == null) {
            classLoader = ClassLoader.getSystemClassLoader();
        }
        Enumeration<URL> urls = classLoader.getResources(path + name);
        ArrayList<URL> array = new ArrayList<URL>();
        if (url != null) {
            array.add(url);
        }
        while (url == null && !urls.hasMoreElements() && path.length() > 0) {
            int n3 = path.lastIndexOf(47, path.length() - 2);
            path = n3 >= 0 ? path.substring(0, n3 + 1) : "";
            urls = classLoader.getResources(path + name);
        }
        while (urls.hasMoreElements() && (maxLength < 0 || array.size() < maxLength)) {
            url = urls.nextElement();
            if (array.contains(url)) continue;
            array.add(url);
        }
        return array.toArray(new URL[array.size()]);
    }

    public static File getCacheDir() throws IOException {
        if (cacheDir == null) {
            String[] dirNames;
            for (String dirName : dirNames = new String[]{System.getProperty("org.bytedeco.javacpp.cachedir"), System.getProperty("org.bytedeco.javacpp.cacheDir"), System.getProperty("user.home") + "/.javacpp/cache/", System.getProperty("java.io.tmpdir") + "/.javacpp-" + System.getProperty("user.name") + "/cache/"}) {
                if (dirName == null) continue;
                File f2 = new File(dirName);
                try {
                    if (!f2.exists() && !f2.mkdirs() || !f2.canRead() || !f2.canWrite() || !f2.canExecute()) continue;
                    cacheDir = f2;
                    break;
                }
                catch (SecurityException e2) {
                    logger.warn("Could not access " + f2 + ": " + e2.getMessage());
                }
            }
        }
        if (cacheDir == null) {
            throw new IOException("Could not create the cache: Set the \"org.bytedeco.javacpp.cachedir\" system property.");
        }
        return cacheDir;
    }

    public static File getTempDir() {
        if (tempDir == null) {
            File tmpdir = new File(System.getProperty("java.io.tmpdir"));
            for (int i2 = 0; i2 < 1000; ++i2) {
                File f2 = new File(tmpdir, "javacpp" + System.nanoTime());
                if (!f2.mkdir()) continue;
                tempDir = f2;
                tempDir.deleteOnExit();
                break;
            }
        }
        return tempDir;
    }

    public static synchronized Map<String, String> getLoadedLibraries() {
        return new HashMap<String, String>(loadedLibraries);
    }

    public static boolean isLoadLibraries() {
        String s2 = System.getProperty("org.bytedeco.javacpp.loadlibraries", "true").toLowerCase();
        return (s2 = System.getProperty("org.bytedeco.javacpp.loadLibraries", s2).toLowerCase()).equals("true") || s2.equals("t") || s2.equals("");
    }

    public static boolean checkPlatform(Class<?> cls, java.util.Properties properties) {
        return Loader.checkPlatform(cls, properties, false);
    }

    public static boolean checkPlatform(Class<?> cls, java.util.Properties properties, boolean acceptAllExtensions) {
        boolean supported;
        Class enclosingClass = Loader.getEnclosingClass(cls);
        while (!cls.isAnnotationPresent(Properties.class) && !cls.isAnnotationPresent(Platform.class) && cls.getSuperclass() != null) {
            if (enclosingClass != null && cls.getSuperclass() == Object.class) {
                cls = enclosingClass;
                enclosingClass = null;
                continue;
            }
            cls = cls.getSuperclass();
        }
        Properties classProperties = cls.getAnnotation(Properties.class);
        Platform classPlatform = cls.getAnnotation(Platform.class);
        boolean bl2 = supported = classProperties == null && classPlatform == null;
        if (classProperties != null) {
            Class[] classes = classProperties.inherit();
            String[] defaultNames = classProperties.names();
            ArrayDeque<Class> queue = new ArrayDeque<Class>(Arrays.asList(classes));
            while (queue.size() > 0 && (defaultNames == null || defaultNames.length == 0)) {
                Class c2 = (Class)queue.removeFirst();
                Properties p2 = c2.getAnnotation(Properties.class);
                if (p2 == null) continue;
                defaultNames = p2.names();
                queue.addAll(Arrays.asList(p2.inherit()));
            }
            Platform[] platforms = classProperties.value();
            if (platforms != null && platforms.length > 0) {
                for (Platform p3 : platforms) {
                    if (!Loader.checkPlatform(p3, properties, acceptAllExtensions, defaultNames)) continue;
                    supported = true;
                    break;
                }
            } else if (classes != null && classes.length > 0) {
                for (Class c3 : classes) {
                    if (!Loader.checkPlatform(c3, properties, acceptAllExtensions)) continue;
                    supported = true;
                    break;
                }
            }
        }
        if (classPlatform != null) {
            supported = Loader.checkPlatform(cls.getAnnotation(Platform.class), properties, acceptAllExtensions, new String[0]);
        }
        return supported;
    }

    public static boolean checkPlatform(Platform platform, java.util.Properties properties) {
        return Loader.checkPlatform(platform, properties, false, new String[0]);
    }

    public static boolean checkPlatform(Platform platform, java.util.Properties properties, boolean acceptAllExtensions, String ... defaultNames) {
        if (platform == null) {
            return true;
        }
        if (defaultNames == null) {
            defaultNames = new String[]{};
        }
        String platform2 = properties.getProperty("platform");
        String platformExtension = properties.getProperty("platform.extension");
        String[][] names = new String[][]{platform.value().length > 0 ? platform.value() : defaultNames, platform.not(), platform.pattern()};
        boolean[] matches = new boolean[]{false, false, false};
        block0: for (int i2 = 0; i2 < names.length; ++i2) {
            for (String s2 : names[i2]) {
                if ((i2 >= 2 || !platform2.startsWith(s2)) && (s2.length() <= 0 || !platform2.matches(s2))) continue;
                matches[i2] = true;
                continue block0;
            }
        }
        if (!(names[0].length != 0 && !matches[0] || names[1].length != 0 && matches[1] || names[2].length != 0 && !matches[2])) {
            boolean match = platform.extension().length == 0 || acceptAllExtensions;
            for (String s2 : platform.extension()) {
                if (platformExtension == null || platformExtension.length() <= 0 || !platformExtension.endsWith(s2)) continue;
                match = true;
                break;
            }
            return match;
        }
        return false;
    }

    public static String[] load(Class ... classes) {
        return Loader.load(classes, true);
    }

    public static String[] load(Class[] classes, boolean logMessages) {
        String[] filenames = new String[classes.length];
        java.util.Properties properties = Loader.loadProperties();
        ClassProperties libProperties = null;
        for (int i2 = 0; i2 < classes.length; ++i2) {
            Class c2 = classes[i2];
            if (Loader.getEnclosingClass(c2) != c2 || !(libProperties = Loader.loadProperties(c2, properties, false)).isLoaded()) continue;
            libProperties = Loader.loadProperties(c2, properties, true);
            if (!libProperties.isLoaded()) {
                if (!logMessages) continue;
                logger.warn("Could not load platform properties for " + c2);
                continue;
            }
            try {
                if (logMessages) {
                    logger.info("Loading " + c2);
                }
                filenames[i2] = Loader.load(c2);
                continue;
            }
            catch (NoClassDefFoundError | UnsatisfiedLinkError e2) {
                if (!logMessages) continue;
                logger.warn("Could not load " + c2 + ": " + e2);
            }
        }
        return filenames;
    }

    public static String load() {
        return Loader.load(Loader.getCallerClass(2), Loader.loadProperties(), pathsFirst);
    }

    public static String load(boolean pathsFirst) {
        Class cls = Loader.getCallerClass(2);
        return Loader.load(cls, Loader.loadProperties(), pathsFirst);
    }

    public static String load(Class cls) {
        return Loader.load(cls, Loader.loadProperties(), pathsFirst);
    }

    public static String load(Class cls, String executable) {
        return Loader.load(cls, Loader.loadProperties(), pathsFirst, executable);
    }

    public static String load(Class cls, java.util.Properties properties, boolean pathsFirst) {
        return Loader.load(cls, properties, pathsFirst, null);
    }

    public static String load(Class cls, java.util.Properties properties, boolean pathsFirst, String executable) {
        Class classToLoad = cls;
        if (!Loader.isLoadLibraries() || cls == null) {
            return null;
        }
        if (!Loader.checkPlatform(cls, properties, properties.getProperty("platform.extension") == null)) {
            throw new UnsatisfiedLinkError("Platform \"" + properties.getProperty("platform") + "\" not supported by " + cls);
        }
        ClassProperties p2 = Loader.loadProperties(cls = Loader.getEnclosingClass(cls), properties, true);
        List<String> targets = p2.get("global");
        if (targets.isEmpty()) {
            if (p2.getInheritedClasses() != null) {
                for (Class c2 : p2.getInheritedClasses()) {
                    targets.add(c2.getName());
                }
            }
            targets.add(cls.getName());
        }
        if (!targets.contains(classToLoad.getName())) {
            targets.add(classToLoad.getName());
        }
        for (String s2 : targets) {
            try {
                if (logger.isDebugEnabled()) {
                    logger.debug("Loading class " + s2);
                }
                Class.forName(s2, true, cls.getClassLoader());
            }
            catch (ClassNotFoundException ex2) {
                if (logger.isDebugEnabled()) {
                    logger.debug("Failed to load class " + s2 + ": " + ex2);
                }
                NoClassDefFoundError e2 = new NoClassDefFoundError(ex2.toString());
                e2.initCause(ex2);
                throw e2;
            }
        }
        String cacheDir = null;
        try {
            cacheDir = Loader.getCacheDir().getCanonicalPath();
        }
        catch (IOException s2) {
            // empty catch block
        }
        ArrayList<String> preloads = new ArrayList<String>();
        ArrayList<String> preloaded = new ArrayList<String>();
        ArrayList<String> preloadGlobals = new ArrayList<String>();
        preloads.addAll(p2.get("platform.preload"));
        preloads.addAll(p2.get("platform.link"));
        UnsatisfiedLinkError preloadError = null;
        for (String preload : preloads) {
            try {
                URL[] urls;
                boolean loadGlobally = false;
                if (preload.endsWith("!")) {
                    loadGlobally = true;
                    preload = preload.substring(0, preload.length() - 1);
                }
                URL[] oldUrls = urls = foundLibraries.get(preload);
                if (urls == null) {
                    urls = Loader.findLibrary(cls, p2, preload, pathsFirst);
                    foundLibraries.put(preload, urls);
                }
                String filename = null;
                if (oldUrls == null || urls.length > 0) {
                    filename = Loader.loadLibrary(cls, urls, preload, preloaded.toArray(new String[preloaded.size()]));
                }
                if (filename != null && new File(filename).exists()) {
                    preloaded.add(filename);
                    if (loadGlobally) {
                        preloadGlobals.add(filename);
                    }
                }
                if (cacheDir == null || filename == null || !filename.startsWith(cacheDir)) continue;
                Loader.createLibraryLink(filename, p2, preload, new String[0]);
            }
            catch (UnsatisfiedLinkError e3) {
                preloadError = e3;
            }
        }
        List<String> executables = p2.get("platform.executable");
        LinkedHashMap<String, String> executablePaths = new LinkedHashMap<String, String>();
        if (executables.size() == 0 && executable != null) {
            throw new IllegalArgumentException("executable specified for class which does not have any executables");
        }
        if (executables.size() > 0) {
            String platform = p2.getProperty("platform");
            String[] extensions = p2.get("platform.extension").toArray(new String[0]);
            String prefix = p2.getProperty("platform.executable.prefix", "");
            String suffix = p2.getProperty("platform.executable.suffix", "");
            String filename = prefix + executables.get(0) + suffix;
            String libraryPath = p2.getProperty("platform.library.path", "");
            try {
                if (libraryPath.length() > 0) {
                    block13: for (String preload : preloads) {
                        URL[] urls;
                        for (URL url : urls = Loader.findLibrary(cls, p2, preload, true)) {
                            File f2 = Loader.cacheResource(url);
                            if (f2 == null) continue;
                            f2.setExecutable(true);
                            continue block13;
                        }
                    }
                }
                block15: for (String e4 : executables) {
                    String[] split = e4.split("#");
                    filename = prefix + split[0] + suffix;
                    String filename2 = split.length > 1 ? prefix + split[1] + suffix : null;
                    for (int i2 = extensions.length - 1; i2 >= -1; --i2) {
                        Object f3;
                        String extension;
                        String string = extension = i2 >= 0 ? extensions[i2] : "";
                        String subdir = libraryPath.length() > 0 ? "/" + libraryPath : platform + (extension == null ? "" : extension);
                        URL u2 = Loader.findResource(cls, subdir + "/" + filename);
                        if (u2 == null) continue;
                        if (filename2 != null && !(u2 = new URL(u2 + "#" + filename2)).toString().contains("#")) {
                            f3 = URL.class.getDeclaredField("ref");
                            ((Field)f3).setAccessible(true);
                            ((Field)f3).set(u2, filename2);
                        }
                        if ((f3 = Loader.cacheResource(u2)) == null) continue;
                        ((File)f3).setExecutable(true);
                        executablePaths.put(e4, ((File)f3).getAbsolutePath());
                        continue block15;
                    }
                }
            }
            catch (IOException | IllegalAccessException | NoSuchFieldException e5) {
                logger.error("Could not extract executable " + filename + ": " + e5);
            }
            return executable != null ? (String)executablePaths.get(executable) : (executablePaths.size() > 0 ? (String)executablePaths.values().iterator().next() : null);
        }
        int librarySuffix = -1;
        block17: while (true) {
            try {
                URL[] urls;
                String library = p2.getProperty("platform.library");
                if (librarySuffix >= 0) {
                    library = library + "#" + library + librarySuffix;
                }
                URL[] oldUrls = urls = foundLibraries.get(library);
                if (urls == null) {
                    urls = Loader.findLibrary(cls, p2, library, pathsFirst);
                    foundLibraries.put(library, urls);
                }
                String filename = null;
                if (oldUrls == null || urls.length > 0) {
                    filename = Loader.loadLibrary(cls, urls, library, preloaded.toArray(new String[preloaded.size()]));
                }
                if (cacheDir != null && filename != null && filename.startsWith(cacheDir)) {
                    Loader.createLibraryLink(filename, p2, library, new String[0]);
                }
                for (String preloadGlobal : preloadGlobals) {
                    Loader.loadGlobal(preloadGlobal);
                }
                return filename;
            }
            catch (UnsatisfiedLinkError e6) {
                Throwable t2 = e6;
                while (t2 != null) {
                    if (t2 instanceof UnsatisfiedLinkError && t2.getMessage().contains("already loaded in another classloader")) {
                        ++librarySuffix;
                        continue block17;
                    }
                    t2 = t2.getCause() != t2 ? t2.getCause() : null;
                }
                if (preloadError != null && e6.getCause() == null) {
                    e6.initCause(preloadError);
                }
                if (!Loader.checkPlatform(cls, properties, false)) {
                    return null;
                }
                throw e6;
            }
            break;
        }
    }

    public static URL[] findLibrary(Class cls, ClassProperties properties, String libnameversion) {
        return Loader.findLibrary(cls, properties, libnameversion, pathsFirst);
    }

    public static URL[] findLibrary(Class cls, ClassProperties properties, String libnameversion, boolean pathsFirst) {
        boolean nostyle = false;
        if (libnameversion.startsWith(":")) {
            nostyle = true;
            libnameversion = libnameversion.substring(1);
        } else if (libnameversion.contains(":")) {
            nostyle = true;
            libnameversion = libnameversion.substring(libnameversion.indexOf(":") + 1);
        }
        if (libnameversion.endsWith("!")) {
            libnameversion = libnameversion.substring(0, libnameversion.length() - 1);
        }
        if (libnameversion.trim().endsWith("#") && !libnameversion.trim().endsWith("##")) {
            return new URL[0];
        }
        String[] split = libnameversion.split("#");
        boolean reference = split.length > 1 && split[1].length() > 0;
        String[] s2 = split[0].split("@");
        String[] s22 = (reference ? split[1] : split[0]).split("@");
        String libname = s2[0];
        String libname2 = s22[0];
        String version = s2.length > 1 ? s2[s2.length - 1] : "";
        String version2 = s22.length > 1 ? s22[s22.length - 1] : "";
        String platform = properties.getProperty("platform");
        String[] extensions = properties.get("platform.extension").toArray(new String[0]);
        String prefix = properties.getProperty("platform.library.prefix", "");
        String suffix = properties.getProperty("platform.library.suffix", "");
        String[] styles = new String[]{prefix + libname + suffix + version, prefix + libname + version + suffix, prefix + libname + suffix};
        String[] styles2 = new String[]{prefix + libname2 + suffix + version2, prefix + libname2 + version2 + suffix, prefix + libname2 + suffix};
        String[] suffixes = properties.get("platform.library.suffix").toArray(new String[0]);
        if (suffixes.length > 1) {
            styles = new String[3 * suffixes.length];
            styles2 = new String[3 * suffixes.length];
            for (int i2 = 0; i2 < suffixes.length; ++i2) {
                styles[3 * i2] = prefix + libname + suffixes[i2] + version;
                styles[3 * i2 + 1] = prefix + libname + version + suffixes[i2];
                styles[3 * i2 + 2] = prefix + libname + suffixes[i2];
                styles2[3 * i2] = prefix + libname2 + suffixes[i2] + version2;
                styles2[3 * i2 + 1] = prefix + libname2 + version2 + suffixes[i2];
                styles2[3 * i2 + 2] = prefix + libname2 + suffixes[i2];
            }
        }
        if (nostyle) {
            styles = new String[]{libname};
            styles2 = new String[]{libname2};
        }
        ArrayList<String> paths = new ArrayList<String>();
        paths.addAll(properties.get("platform.linkpath"));
        paths.addAll(properties.get("platform.preloadpath"));
        List<String> resources = properties.get("platform.preloadresource");
        String libraryPath = properties.getProperty("platform.library.path", "");
        if (libraryPath.length() > 0 && pathsFirst) {
            resources.add(0, libraryPath);
        }
        resources.add(null);
        String libpath = System.getProperty("java.library.path", "");
        if (libpath.length() > 0 && (pathsFirst || !Loader.isLoadLibraries() || reference)) {
            paths.addAll(Arrays.asList(libpath.split(File.pathSeparator)));
        }
        ArrayList<URL> urls = new ArrayList<URL>(styles.length * (1 + paths.size()));
        for (int i3 = 0; cls != null && i3 < styles.length; ++i3) {
            for (int j2 = extensions.length - 1; j2 >= -1; --j2) {
                String extension = j2 >= 0 ? extensions[j2] : "";
                for (String resource : resources) {
                    if (resource != null && !resource.endsWith("/")) {
                        resource = resource + "/";
                    }
                    String subdir = libraryPath.length() > 0 && libraryPath.equals(resource) ? "/" + libraryPath : (resource == null ? "" : "/" + resource) + platform + (extension == null ? "" : extension);
                    try {
                        URL u2 = Loader.findResource(cls, subdir + "/" + styles[i3]);
                        if (u2 == null) continue;
                        if (reference && !(u2 = new URL(u2 + "#" + styles2[i3])).toString().contains("#")) {
                            Field f2 = URL.class.getDeclaredField("ref");
                            f2.setAccessible(true);
                            f2.set(u2, styles2[i3]);
                        }
                        if (urls.contains(u2)) continue;
                        urls.add(u2);
                    }
                    catch (IOException | IllegalAccessException | NoSuchFieldException e2) {
                        throw new RuntimeException(e2);
                    }
                }
            }
        }
        int k2 = pathsFirst ? 0 : urls.size();
        for (int i4 = 0; paths.size() > 0 && i4 < styles.length; ++i4) {
            for (String path : paths) {
                File file = new File(path, styles[i4]);
                if (!file.exists()) continue;
                try {
                    URL u3 = file.toURI().toURL();
                    if (reference && !(u3 = new URL(u3 + "#" + styles2[i4])).toString().contains("#")) {
                        Field f3 = URL.class.getDeclaredField("ref");
                        f3.setAccessible(true);
                        f3.set(u3, styles2[i4]);
                    }
                    if (urls.contains(u3)) continue;
                    urls.add(k2++, u3);
                }
                catch (IOException | IllegalAccessException | NoSuchFieldException ex2) {
                    throw new RuntimeException(ex2);
                }
            }
        }
        return urls.toArray(new URL[urls.size()]);
    }

    public static String loadLibrary(String libnameversion, String ... preloaded) {
        return Loader.loadLibrary(Loader.getCallerClass(2), libnameversion, preloaded);
    }

    public static String loadLibrary(Class<?> cls, String libnameversion, String ... preloaded) {
        try {
            return Loader.loadLibrary(Loader.findResources(cls, libnameversion), libnameversion, preloaded);
        }
        catch (IOException e2) {
            throw new RuntimeException(e2);
        }
    }

    public static String loadLibrary(URL[] urls, String libnameversion, String ... preloaded) {
        return Loader.loadLibrary(null, urls, libnameversion, preloaded);
    }

    /*
     * WARNING - void declaration
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    public static synchronized String loadLibrary(Class<?> cls, URL[] urls, String libnameversion, String ... preloaded) {
        if (!Loader.isLoadLibraries()) {
            return null;
        }
        if (libnameversion.startsWith(":")) {
            libnameversion = libnameversion.substring(1);
        } else if (libnameversion.contains(":")) {
            libnameversion = libnameversion.substring(0, libnameversion.indexOf(":"));
        }
        boolean loadGlobally = false;
        if (libnameversion.endsWith("!")) {
            loadGlobally = true;
            libnameversion = libnameversion.substring(0, libnameversion.length() - 1);
        }
        String[] split = libnameversion.split("#");
        String libnameversion2 = split[0];
        if (split.length > 1 && split[1].length() > 0) {
            libnameversion2 = split[1];
        }
        String filename = loadedLibraries.get(libnameversion2);
        UnsatisfiedLinkError loadError = null;
        classStack.get().push(cls);
        try {
            for (URL url : urls) {
                File file;
                block54: {
                    URI uri = url.toURI();
                    file = null;
                    try {
                        file = new File(uri);
                    }
                    catch (Exception exc) {
                        block53: {
                            File f2 = Loader.cacheResource(url, filename);
                            try {
                                if (f2 != null) {
                                    file = f2;
                                } else {
                                    try {
                                        file = new File(new URI(uri.toString().split("#")[0]));
                                    }
                                    catch (IllegalArgumentException | URISyntaxException e2) {
                                        file = new File(uri.getPath());
                                    }
                                }
                            }
                            catch (Exception exc2) {
                                if (!logger.isDebugEnabled()) break block53;
                                logger.debug("Failed to access " + uri + ": " + exc2);
                            }
                        }
                        if (file == null || preloaded == null) break block54;
                        File dir = file.getParentFile();
                        for (String s2 : preloaded) {
                            File file2 = new File(s2);
                            File dir2 = file2.getParentFile();
                            if (!canCreateSymbolicLink || dir2 == null || dir2.equals(dir)) continue;
                            File linkFile = new File(dir, file2.getName());
                            try {
                                Path linkPath = linkFile.toPath().normalize();
                                Path targetPath = file2.toPath().normalize();
                                if (linkFile.exists() && Files.isSymbolicLink(linkPath) && Files.readSymbolicLink(linkPath).equals(targetPath) || !targetPath.isAbsolute() || targetPath.equals(linkPath) || targetPath.toRealPath(new LinkOption[0]).equals(linkPath)) continue;
                                if (logger.isDebugEnabled()) {
                                    logger.debug("Creating symbolic link " + linkPath + " to " + targetPath);
                                }
                                linkFile.delete();
                                Files.createSymbolicLink(linkPath, targetPath, new FileAttribute[0]);
                            }
                            catch (IOException | RuntimeException e3) {
                                canCreateSymbolicLink = false;
                                if (!logger.isDebugEnabled()) continue;
                                logger.debug("Failed to create symbolic link " + linkFile + " to " + file2 + ": " + e3);
                            }
                        }
                    }
                }
                if (filename != null) {
                    String exc = filename;
                    return exc;
                }
                if (file == null || !file.exists()) continue;
                String filename2 = file.getAbsolutePath();
                try {
                    if (logger.isDebugEnabled()) {
                        logger.debug("Loading " + filename2);
                    }
                    loadedLibraries.put(libnameversion2, filename2);
                    boolean loadedByLoad0 = false;
                    if (cls != null && Loader.class.getClassLoader() != cls.getClassLoader()) {
                        try {
                            Method load0 = Runtime.class.getDeclaredMethod("load0", Class.class, String.class);
                            load0.setAccessible(true);
                            load0.invoke((Object)Runtime.getRuntime(), cls, filename2);
                            loadedByLoad0 = true;
                        }
                        catch (IllegalAccessException | IllegalArgumentException | NoSuchMethodException | SecurityException cnfe) {
                            logger.warn("Unable to load the library " + libnameversion2 + " within the ClassLoader scope of " + cls.getName());
                        }
                        catch (InvocationTargetException ite) {
                            Throwable target = ite.getTargetException();
                            if (target instanceof UnsatisfiedLinkError) {
                                throw (UnsatisfiedLinkError)target;
                            }
                            logger.warn("Unable to load the library " + libnameversion2 + " within the ClassLoader scope of " + cls.getName() + " because: " + target.getMessage());
                        }
                    }
                    if (!loadedByLoad0) {
                        System.load(filename2);
                    }
                    if (loadGlobally) {
                        Loader.loadGlobal(filename2);
                    }
                    String string = filename2;
                    return string;
                }
                catch (UnsatisfiedLinkError e4) {
                    loadError = e4;
                    loadedLibraries.remove(libnameversion2);
                    if (!logger.isDebugEnabled()) continue;
                    logger.debug("Failed to load " + filename2 + ": " + e4);
                }
            }
            if (filename != null) {
                String string = filename;
                return string;
            }
            if (!libnameversion.trim().endsWith("#")) {
                void var9_13;
                String string;
                if (loadError == null) {
                    loadError = new UnsatisfiedLinkError("Could not find " + libnameversion + " in class, module, and library paths.");
                }
                if ((string = libnameversion.split("#")[0].split("@")[0]).endsWith("!")) {
                    String string2 = string.substring(0, string.length() - 1);
                }
                if (logger.isDebugEnabled()) {
                    logger.debug("Loading library " + (String)var9_13);
                }
                loadedLibraries.put(libnameversion2, (String)var9_13);
                boolean loadedByLoadLibrary0 = false;
                if (cls != null && Loader.class.getClassLoader() != cls.getClassLoader()) {
                    try {
                        Method load0 = Runtime.class.getDeclaredMethod("loadLibrary0", Class.class, String.class);
                        load0.setAccessible(true);
                        load0.invoke((Object)Runtime.getRuntime(), cls, var9_13);
                        loadedByLoadLibrary0 = true;
                    }
                    catch (IllegalAccessException | IllegalArgumentException | NoSuchMethodException | SecurityException cnfe) {
                        logger.warn("Unable to load the library " + (String)var9_13 + " within the ClassLoader scope of " + cls.getName());
                    }
                    catch (InvocationTargetException ite) {
                        Throwable target = ite.getTargetException();
                        if (target instanceof UnsatisfiedLinkError) {
                            throw (UnsatisfiedLinkError)target;
                        }
                        logger.warn("Unable to load the library " + (String)var9_13 + " within the ClassLoader scope of " + cls.getName() + " because: " + target.getMessage());
                    }
                }
                if (!loadedByLoadLibrary0) {
                    System.loadLibrary((String)var9_13);
                }
                void var11_23 = var9_13;
                return var11_23;
            }
            String string = null;
            return string;
        }
        catch (UnsatisfiedLinkError unsatisfiedLinkError) {
            loadedLibraries.remove(libnameversion2);
            if (loadError != null && unsatisfiedLinkError.getCause() == null) {
                unsatisfiedLinkError.initCause(loadError);
            }
            if (!logger.isDebugEnabled()) throw unsatisfiedLinkError;
            logger.debug("Failed to load for " + libnameversion + ": " + unsatisfiedLinkError);
            throw unsatisfiedLinkError;
        }
        catch (IOException | URISyntaxException exception) {
            loadedLibraries.remove(libnameversion2);
            if (loadError != null && exception.getCause() == null) {
                exception.initCause(loadError);
            }
            UnsatisfiedLinkError e5 = new UnsatisfiedLinkError(exception.toString());
            e5.initCause(exception);
            if (!logger.isDebugEnabled()) throw e5;
            logger.debug("Failed to extract for " + libnameversion + ": " + e5);
            throw e5;
        }
        finally {
            classStack.get().pop();
        }
    }

    public static String createLibraryLink(String filename, ClassProperties properties, String libnameversion, String ... paths) {
        String version;
        String[] stringArray;
        if (libnameversion != null && libnameversion.startsWith(":")) {
            libnameversion = libnameversion.substring(1);
        } else if (libnameversion != null && libnameversion.contains(":")) {
            libnameversion = libnameversion.substring(0, libnameversion.indexOf(":"));
        }
        if (libnameversion != null && libnameversion.endsWith("!")) {
            libnameversion = libnameversion.substring(0, libnameversion.length() - 1);
        }
        File file = new File(filename);
        String parent = file.getParent();
        String name = file.getName();
        String link = null;
        if (libnameversion != null) {
            stringArray = libnameversion.split("#");
        } else {
            String[] stringArray2 = new String[1];
            stringArray = stringArray2;
            stringArray2[0] = "";
        }
        String[] split = stringArray;
        String[] s2 = (split.length > 1 && split[1].length() > 0 ? split[1] : split[0]).split("@");
        String libname = s2[0];
        String string = version = s2.length > 1 ? s2[s2.length - 1] : "";
        if (!name.contains(libname)) {
            return filename;
        }
        for (String suffix : properties.get("platform.library.suffix")) {
            int n2 = name.lastIndexOf(suffix);
            int n22 = version.length() != 0 ? name.lastIndexOf(version) : name.indexOf(".");
            int n3 = name.lastIndexOf(".");
            if (n22 < n2 && n2 < n3) {
                link = name.substring(0, n2) + suffix;
                break;
            }
            if (n2 <= 0 || n22 <= 0) continue;
            link = name.substring(0, n2 < n22 ? n2 : n22) + suffix;
            break;
        }
        if (link == null) {
            for (String suffix : properties.get("platform.library.suffix")) {
                if (!name.endsWith(suffix)) continue;
                link = name;
                break;
            }
        }
        if (canCreateSymbolicLink && link != null && link.length() > 0) {
            File linkFile = new File(parent, link);
            try {
                Path linkPath = linkFile.toPath();
                Path targetPath = Paths.get(name, new String[0]);
                if (!(linkFile.exists() && Files.isSymbolicLink(linkPath) && Files.readSymbolicLink(linkPath).equals(targetPath) || targetPath.isAbsolute() || targetPath.equals(linkPath.getFileName()))) {
                    if (logger.isDebugEnabled()) {
                        logger.debug("Creating symbolic link " + linkPath);
                    }
                    linkFile.delete();
                    Files.createSymbolicLink(linkPath, targetPath, new FileAttribute[0]);
                }
                filename = linkFile.toString();
                for (String parent2 : paths) {
                    if (parent2 == null) continue;
                    for (String link2 : new String[]{link, name}) {
                        File linkFile2 = new File(parent2, link2);
                        Path linkPath2 = linkFile2.toPath();
                        Path relativeTarget = Paths.get(parent2, new String[0]).relativize(Paths.get(parent, new String[0])).resolve(name);
                        if (linkFile2.exists() && Files.isSymbolicLink(linkPath2) && Files.readSymbolicLink(linkPath2).equals(relativeTarget) || relativeTarget.isAbsolute() || relativeTarget.equals(linkPath2.getFileName())) continue;
                        if (logger.isDebugEnabled()) {
                            logger.debug("Creating symbolic link " + linkPath2);
                        }
                        linkFile2.delete();
                        Files.createSymbolicLink(linkPath2, relativeTarget, new FileAttribute[0]);
                    }
                }
            }
            catch (IOException | RuntimeException e2) {
                canCreateSymbolicLink = false;
                if (logger.isDebugEnabled()) {
                    logger.debug("Failed to create symbolic link " + linkFile + ": " + e2);
                }
                return null;
            }
        }
        return filename;
    }

    static Class putMemberOffset(String typeName, String member, int offset) throws ClassNotFoundException {
        try {
            Class<?> context = classStack.get().peek();
            Class<?> c2 = Class.forName(typeName.replace('/', '.'), false, context != null ? context.getClassLoader() : Loader.class.getClassLoader());
            if (member != null) {
                Loader.putMemberOffset(c2.asSubclass(Pointer.class), member, offset);
            }
            return c2;
        }
        catch (ClassNotFoundException e2) {
            logger.warn("Loader.putMemberOffset(): " + e2);
            return null;
        }
    }

    static synchronized void putMemberOffset(Class<? extends Pointer> type, String member, int offset) {
        HashMap<String, Integer> offsets = memberOffsets.get(type);
        if (offsets == null) {
            offsets = new HashMap();
            memberOffsets.put(type, offsets);
        }
        offsets.put(member, offset);
    }

    public static int offsetof(Class<? extends Pointer> type, String member) {
        HashMap<String, Integer> offsets = memberOffsets.get(type);
        while (offsets == null && type.getSuperclass() != null) {
            type = type.getSuperclass().asSubclass(Pointer.class);
            offsets = memberOffsets.get(type);
        }
        return offsets.get(member);
    }

    public static int sizeof(Class<? extends Pointer> type) {
        return Loader.offsetof(type, "sizeof");
    }

    @Name(value={"JavaCPP_totalProcessors"})
    public static native int totalProcessors();

    @Name(value={"JavaCPP_totalCores"})
    public static native int totalCores();

    @Name(value={"JavaCPP_totalChips"})
    public static native int totalChips();

    @Name(value={"JavaCPP_addressof"})
    public static native Pointer addressof(String var0);

    @Name(value={"JavaCPP_loadGlobal"})
    @Raw(withEnv=true)
    public static native void loadGlobal(String var0);

    @Name(value={"JavaCPP_getJavaVM"})
    @Cast(value={"JavaVM*"})
    public static native Pointer getJavaVM();

    static {
        block2: {
            logger = Logger.create(Loader.class);
            PLATFORM = Detector.getPlatform();
            platformProperties = null;
            classStack = new ThreadLocal<Deque<Class<?>>>(){

                @Override
                protected Deque<Class<?>> initialValue() {
                    return new ArrayDeque();
                }
            };
            cacheDir = null;
            tempDir = null;
            foundLibraries = new HashMap<String, URL[]>();
            loadedLibraries = new HashMap<String, String>();
            canCreateSymbolicLink = true;
            pathsFirst = false;
            String s2 = System.getProperty("org.bytedeco.javacpp.pathsfirst", "false").toLowerCase();
            pathsFirst = (s2 = System.getProperty("org.bytedeco.javacpp.pathsFirst", s2).toLowerCase()).equals("true") || s2.equals("t") || s2.equals("");
            memberOffsets = new WeakHashMap();
            try {
                Loader.load();
            }
            catch (Throwable t2) {
                if (!logger.isDebugEnabled()) break block2;
                logger.debug("Could not load Loader: " + t2);
            }
        }
    }

    public static class Detector {
        public static String getPlatform() {
            String jvmName = System.getProperty("java.vm.name", "").toLowerCase();
            String osName = System.getProperty("os.name", "").toLowerCase();
            String osArch = System.getProperty("os.arch", "").toLowerCase();
            String abiType = System.getProperty("sun.arch.abi", "").toLowerCase();
            String libPath = System.getProperty("sun.boot.library.path", "").toLowerCase();
            if (jvmName.startsWith("dalvik") && osName.startsWith("linux")) {
                osName = "android";
            } else if (jvmName.startsWith("robovm") && osName.startsWith("darwin")) {
                osName = "ios";
                osArch = "arm";
            } else if (osName.startsWith("mac os x") || osName.startsWith("darwin")) {
                osName = "macosx";
            } else {
                int spaceIndex = osName.indexOf(32);
                if (spaceIndex > 0) {
                    osName = osName.substring(0, spaceIndex);
                }
            }
            if (osArch.equals("i386") || osArch.equals("i486") || osArch.equals("i586") || osArch.equals("i686")) {
                osArch = "x86";
            } else if (osArch.equals("amd64") || osArch.equals("x86-64") || osArch.equals("x64")) {
                osArch = "x86_64";
            } else if (osArch.startsWith("aarch64") || osArch.startsWith("armv8") || osArch.startsWith("arm64")) {
                osArch = "arm64";
            } else if (osArch.startsWith("arm") && (abiType.equals("gnueabihf") || libPath.contains("openjdk-armhf"))) {
                osArch = "armhf";
            } else if (osArch.startsWith("arm")) {
                osArch = "arm";
            }
            return System.getProperty("org.bytedeco.javacpp.platform", osName + "-" + osArch);
        }
    }
}

