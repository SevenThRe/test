/*
 * Decompiled with CFR 0.152.
 */
package org.bytedeco.javacpp.tools;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class UserClassLoader
extends URLClassLoader {
    private List<String> paths = new ArrayList<String>();

    public UserClassLoader() {
        super(new URL[0]);
    }

    public UserClassLoader(ClassLoader parent) {
        super(new URL[0], parent);
    }

    public void addPaths(String ... paths) {
        if (paths == null) {
            return;
        }
        for (String path : paths) {
            File f2 = new File(path);
            if (f2.getName().equals("*")) {
                File[] files = f2.getParentFile().listFiles();
                String[] jars = new String[files.length];
                int n2 = 0;
                for (File file : files) {
                    String p2 = file.getPath();
                    if (!p2.endsWith(".jar") && !p2.endsWith(".JAR")) continue;
                    jars[n2++] = p2;
                }
                this.addPaths(Arrays.copyOf(jars, n2));
                continue;
            }
            if (this.paths.contains(path) || !f2.exists()) continue;
            this.paths.add(path);
            try {
                this.addURL(f2.toURI().toURL());
            }
            catch (MalformedURLException e2) {
                throw new RuntimeException(e2);
            }
        }
    }

    public String[] getPaths() {
        if (this.paths.isEmpty()) {
            this.addPaths(System.getProperty("user.dir"));
        }
        return this.paths.toArray(new String[this.paths.size()]);
    }

    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        if (this.paths.isEmpty()) {
            this.addPaths(System.getProperty("user.dir"));
        }
        return super.findClass(name);
    }
}

