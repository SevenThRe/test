/*
 * Decompiled with CFR 0.152.
 */
package org.bytedeco.javacpp.tools;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.util.Arrays;
import java.util.Collection;
import java.util.Enumeration;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;
import org.bytedeco.javacpp.tools.ClassFilter;
import org.bytedeco.javacpp.tools.Logger;
import org.bytedeco.javacpp.tools.UserClassLoader;

class ClassScanner {
    final Logger logger;
    final Collection<Class> classes;
    final UserClassLoader loader;
    final ClassFilter classFilter;

    ClassScanner(Logger logger, Collection<Class> classes, UserClassLoader loader) {
        this(logger, classes, loader, null);
    }

    ClassScanner(Logger logger, Collection<Class> classes, UserClassLoader loader, ClassFilter classFilter) {
        this.logger = logger;
        this.classes = classes;
        this.loader = loader;
        this.classFilter = classFilter;
    }

    public Collection<Class> getClasses() {
        return this.classes;
    }

    public UserClassLoader getClassLoader() {
        return this.loader;
    }

    public void addClass(String className) throws ClassNotFoundException, NoClassDefFoundError {
        if (className == null) {
            return;
        }
        if (className.endsWith(".class")) {
            className = className.substring(0, className.length() - 6);
        }
        Class<?> c2 = Class.forName(className, false, this.loader);
        this.addClass(c2);
    }

    public void addClass(Class c2) {
        if (!this.classes.contains(c2)) {
            this.classes.add(c2);
        }
    }

    public void addMatchingFile(String filename, String packagePath, boolean recursive, byte ... data) throws ClassNotFoundException, NoClassDefFoundError {
        if (filename != null && filename.endsWith(".class") && !filename.contains("-") && (this.classFilter == null || this.classFilter.keep(filename, data)) && (packagePath == null || recursive && filename.startsWith(packagePath) || filename.regionMatches(0, packagePath, 0, Math.max(filename.lastIndexOf(47), packagePath.lastIndexOf(47))))) {
            this.addClass(filename.replace('/', '.'));
        }
    }

    public void addMatchingDir(String parentName, File dir, String packagePath, boolean recursive) throws ClassNotFoundException, IOException, NoClassDefFoundError {
        Object[] files = dir.listFiles();
        Arrays.sort(files);
        for (Object f2 : files) {
            String pathName;
            String string = pathName = parentName == null ? ((File)f2).getName() : parentName + ((File)f2).getName();
            if (((File)f2).isDirectory()) {
                this.addMatchingDir(pathName + "/", (File)f2, packagePath, recursive);
                continue;
            }
            byte[] data = Files.readAllBytes(((File)f2).toPath());
            this.addMatchingFile(pathName, packagePath, recursive, data);
        }
    }

    public void addPackage(String packageName, boolean recursive) throws IOException, ClassNotFoundException, NoClassDefFoundError {
        String[] paths = this.loader.getPaths();
        String packagePath = packageName != null && packageName.length() > 0 ? packageName.replace('.', '/') + "/" : packageName;
        int prevSize = this.classes.size();
        for (String p2 : paths) {
            File file = new File(p2);
            if (file.isDirectory()) {
                this.addMatchingDir(null, file, packagePath, recursive);
                continue;
            }
            try (JarFile jarFile = new JarFile(file);){
                Enumeration<JarEntry> entries = jarFile.entries();
                while (entries.hasMoreElements()) {
                    JarEntry entry = entries.nextElement();
                    String entryName = entry.getName();
                    long entrySize = entry.getSize();
                    long entryTimestamp = entry.getTime();
                    if (entrySize <= 0L) continue;
                    InputStream is2 = jarFile.getInputStream(entry);
                    Throwable throwable = null;
                    try {
                        int n2;
                        byte[] data = new byte[(int)entrySize];
                        for (int i2 = 0; i2 < data.length && (n2 = is2.read(data, i2, data.length - i2)) >= 0; i2 += n2) {
                        }
                        this.addMatchingFile(entryName, packagePath, recursive, data);
                    }
                    catch (Throwable throwable2) {
                        throwable = throwable2;
                        throw throwable2;
                    }
                    finally {
                        if (is2 == null) continue;
                        if (throwable != null) {
                            try {
                                is2.close();
                            }
                            catch (Throwable throwable3) {
                                throwable.addSuppressed(throwable3);
                            }
                            continue;
                        }
                        is2.close();
                    }
                }
            }
        }
        if (this.classes.size() == 0 && (packageName == null || packageName.length() == 0)) {
            this.logger.warn("No classes found in the unnamed package");
        } else if (prevSize == this.classes.size() && packageName != null) {
            this.logger.warn("No classes found in package " + packageName);
        }
    }

    public void addClassOrPackage(String name) throws IOException, ClassNotFoundException, NoClassDefFoundError {
        if (name == null) {
            return;
        }
        if ((name = name.replace('/', '.')).endsWith(".**")) {
            this.addPackage(name.substring(0, name.length() - 3), true);
        } else if (name.endsWith(".*")) {
            this.addPackage(name.substring(0, name.length() - 2), false);
        } else {
            this.addClass(name);
        }
    }
}

