/*
 * Decompiled with CFR 0.152.
 */
package org.bytedeco.javacpp;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.bytedeco.javacpp.LoadEnabled;
import org.bytedeco.javacpp.Loader;
import org.bytedeco.javacpp.Pointer;
import org.bytedeco.javacpp.annotation.Platform;
import org.bytedeco.javacpp.annotation.Properties;
import org.bytedeco.javacpp.tools.Logger;

public class ClassProperties
extends HashMap<String, List<String>> {
    private static final Logger logger = Logger.create(ClassProperties.class);
    String[] defaultNames = new String[0];
    String platform;
    String platformExtension;
    String platformRoot;
    String pathSeparator;
    List<Class> inheritedClasses = null;
    List<Class> effectiveClasses = null;
    boolean loaded = false;

    public ClassProperties() {
    }

    public ClassProperties(java.util.Properties properties) {
        this.platform = properties.getProperty("platform");
        this.platformExtension = properties.getProperty("platform.extension");
        this.platformRoot = properties.getProperty("platform.root");
        this.pathSeparator = properties.getProperty("platform.path.separator");
        if (this.platformRoot == null || this.platformRoot.length() == 0) {
            this.platformRoot = ".";
        }
        if (!this.platformRoot.endsWith(File.separator)) {
            this.platformRoot = this.platformRoot + File.separator;
        }
        for (Map.Entry<Object, Object> e2 : properties.entrySet()) {
            String k2 = (String)e2.getKey();
            String v2 = (String)e2.getValue();
            if (v2 == null || v2.length() == 0) continue;
            if (k2.equals("platform.includepath") || k2.equals("platform.includeresource") || k2.equals("platform.include") || k2.equals("platform.linkpath") || k2.equals("platform.linkresource") || k2.equals("platform.link") || k2.equals("platform.preloadpath") || k2.equals("platform.preloadresource") || k2.equals("platform.preload") || k2.equals("platform.resourcepath") || k2.equals("platform.resource") || k2.equals("platform.frameworkpath") || k2.equals("platform.framework") || k2.equals("platform.executablepath") || k2.equals("platform.executable") || k2.equals("platform.compiler.*") || k2.equals("platform.library.suffix") || k2.equals("platform.extension")) {
                this.addAll(k2, v2.split(this.pathSeparator));
                continue;
            }
            this.setProperty(k2, v2);
        }
    }

    public List<String> get(String key) {
        ArrayList list = (ArrayList)super.get(key);
        if (list == null) {
            list = new ArrayList();
            this.put(key, list);
        }
        return list;
    }

    public void addAll(String key, String ... values) {
        if (values != null) {
            this.addAll(key, Arrays.asList(values));
        }
    }

    public void addAll(String key, Collection<String> values) {
        if (values != null) {
            String root = null;
            if (key.equals("platform.compiler") || key.equals("platform.sysroot") || key.equals("platform.toolchain") || key.equals("platform.includepath") || key.equals("platform.linkpath")) {
                root = this.platformRoot;
            }
            List<String> values2 = this.get(key);
            for (String value : values) {
                if (value == null) continue;
                if (root != null && !new File(value).isAbsolute() && new File(root + value).exists()) {
                    value = root + value;
                }
                if (values2.contains(value)) continue;
                values2.add(value);
            }
        }
    }

    public String getProperty(String key) {
        return this.getProperty(key, null);
    }

    public String getProperty(String key, String defaultValue) {
        List<String> values = this.get(key);
        return values.isEmpty() ? defaultValue : values.get(0);
    }

    public String setProperty(String key, String value) {
        List<String> values = this.get(key);
        String oldValue = values.isEmpty() ? null : values.get(0);
        values.clear();
        this.addAll(key, value);
        return oldValue;
    }

    public void load(Class cls, boolean inherit) {
        int i2;
        Class c2 = Loader.getEnclosingClass(cls);
        ArrayList<Class> classList = new ArrayList<Class>();
        classList.add(0, c2);
        while (!c2.isAnnotationPresent(Properties.class) && !c2.isAnnotationPresent(Platform.class) && c2.getSuperclass() != null && c2.getSuperclass() != Object.class && c2.getSuperclass() != Pointer.class) {
            c2 = c2.getSuperclass();
            classList.add(0, c2);
        }
        if (this.effectiveClasses == null) {
            this.effectiveClasses = classList;
        }
        Properties classProperties = c2.getAnnotation(Properties.class);
        Platform classPlatform = c2.getAnnotation(Platform.class);
        Platform[] platforms = null;
        String ourTarget = null;
        if (classProperties != null) {
            String[] names;
            String helper;
            Class[] classes = classProperties.inherit();
            if (inherit && classes != null) {
                if (this.inheritedClasses == null) {
                    this.inheritedClasses = new ArrayList<Class>();
                }
                for (Class c22 : classes) {
                    this.load(c22, inherit);
                    if (this.inheritedClasses.contains(c22)) continue;
                    this.inheritedClasses.add(c22);
                }
            }
            String target = classProperties.target();
            String global = classProperties.global();
            if (global.length() == 0) {
                global = target;
            } else if (target.length() == 0) {
                target = global;
            } else if (target.length() > 0 && !global.contains(".")) {
                global = target + "." + global;
            }
            ourTarget = global;
            if (target.length() > 0) {
                this.addAll("target", target);
            }
            if (global.length() > 0) {
                this.addAll("global", global);
            }
            if ((helper = classProperties.helper()).length() > 0 && !helper.contains(".")) {
                helper = target + "." + helper;
            }
            if (helper.length() > 0) {
                this.addAll("helper", helper);
            }
            if ((names = classProperties.names()).length > 0) {
                this.defaultNames = names;
            }
            platforms = classProperties.value();
        }
        if (classPlatform != null) {
            if (platforms == null) {
                platforms = new Platform[]{classPlatform};
            } else {
                platforms = (Platform[])Arrays.copyOf(platforms, platforms.length + 1);
                platforms[platforms.length - 1] = classPlatform;
            }
        }
        boolean hasPlatformProperties = platforms != null && platforms.length > (classProperties != null && classPlatform != null ? 1 : 0);
        String[] pragma = new String[]{};
        String[] define = new String[]{};
        String[] exclude = new String[]{};
        String[] include = new String[]{};
        String[] cinclude = new String[]{};
        String[] includepath = new String[]{};
        String[] includeresource = new String[]{};
        String[] compiler = new String[]{};
        String[] linkpath = new String[]{};
        String[] linkresource = new String[]{};
        String[] link = new String[]{};
        String[] frameworkpath = new String[]{};
        String[] framework = new String[]{};
        String[] preloadpath = new String[]{};
        String[] preloadresource = new String[]{};
        String[] preload = new String[]{};
        String[] resourcepath = new String[]{};
        String[] resource = new String[]{};
        String[] extension = new String[]{};
        String[] executablepath = new String[]{};
        String[] executable = new String[]{};
        String library = "jni" + c2.getSimpleName();
        if (hasPlatformProperties) {
            if (ourTarget != null && ourTarget.length() > 0) {
                library = "jni" + ourTarget.substring(ourTarget.lastIndexOf(46) + 1);
            }
        } else {
            Platform[] targets = this.get("global");
            if (targets != null && targets.size() > 0) {
                String target = (String)targets.get(targets.size() - 1);
                library = "jni" + target.substring(target.lastIndexOf(46) + 1);
            }
        }
        for (Platform p2 : platforms != null ? platforms : new Platform[]{}) {
            String[][] names = new String[][]{p2.value().length > 0 ? p2.value() : this.defaultNames, p2.not(), p2.pattern()};
            boolean[] matches = new boolean[]{false, false, false};
            block5: for (int i3 = 0; i3 < names.length; ++i3) {
                for (String s2 : names[i3]) {
                    if ((i3 >= 2 || !this.platform.startsWith(s2)) && (s2.length() <= 0 || !this.platform.matches(s2))) continue;
                    matches[i3] = true;
                    continue block5;
                }
            }
            if (names[0].length != 0 && !matches[0] || names[1].length != 0 && matches[1] || names[2].length != 0 && !matches[2]) continue;
            boolean match = p2.extension().length == 0 || Loader.isLoadLibraries() && this.platformExtension == null;
            for (String s2 : p2.extension()) {
                if (this.platformExtension == null || this.platformExtension.length() <= 0 || !this.platformExtension.endsWith(s2)) continue;
                match = true;
                break;
            }
            if (!match) continue;
            if (p2.pragma().length > 0) {
                pragma = p2.pragma();
            }
            if (p2.define().length > 0) {
                define = p2.define();
            }
            if (p2.exclude().length > 0) {
                exclude = p2.exclude();
            }
            if (p2.include().length > 0) {
                include = p2.include();
            }
            if (p2.cinclude().length > 0) {
                cinclude = p2.cinclude();
            }
            if (p2.includepath().length > 0) {
                includepath = p2.includepath();
            }
            if (p2.includeresource().length > 0) {
                includeresource = p2.includeresource();
            }
            if (p2.compiler().length > 0) {
                compiler = p2.compiler();
            }
            if (p2.linkpath().length > 0) {
                linkpath = p2.linkpath();
            }
            if (p2.linkresource().length > 0) {
                linkresource = p2.linkresource();
            }
            if (p2.link().length > 0) {
                link = p2.link();
            }
            if (p2.frameworkpath().length > 0) {
                frameworkpath = p2.frameworkpath();
            }
            if (p2.framework().length > 0) {
                framework = p2.framework();
            }
            if (p2.preloadresource().length > 0) {
                preloadresource = p2.preloadresource();
            }
            if (p2.preloadpath().length > 0) {
                preloadpath = p2.preloadpath();
            }
            if (p2.preload().length > 0) {
                preload = p2.preload();
            }
            if (p2.resourcepath().length > 0) {
                resourcepath = p2.resourcepath();
            }
            if (p2.resource().length > 0) {
                resource = p2.resource();
            }
            if (p2.extension().length > 0) {
                extension = p2.extension();
            }
            if (p2.executablepath().length > 0) {
                executablepath = p2.executablepath();
            }
            if (p2.executable().length > 0) {
                executable = p2.executable();
            }
            if (p2.library().length() <= 0) continue;
            library = p2.library();
        }
        for (i2 = 0; i2 < includeresource.length; ++i2) {
            String name = includeresource[i2];
            if (name.startsWith("/")) continue;
            String s3 = cls.getName().replace('.', '/');
            int n2 = s3.lastIndexOf(47);
            if (n2 >= 0) {
                name = s3.substring(0, n2 + 1) + name;
            }
            includeresource[i2] = "/" + name;
        }
        for (i2 = 0; i2 < linkresource.length; ++i2) {
            String name = linkresource[i2];
            if (name.startsWith("/")) continue;
            String s4 = cls.getName().replace('.', '/');
            int n3 = s4.lastIndexOf(47);
            if (n3 >= 0) {
                name = s4.substring(0, n3 + 1) + name;
            }
            linkresource[i2] = "/" + name;
        }
        this.addAll("platform.pragma", pragma);
        this.addAll("platform.define", define);
        this.addAll("platform.exclude", exclude);
        this.addAll("platform.include", include);
        this.addAll("platform.cinclude", cinclude);
        this.addAll("platform.includepath", includepath);
        this.addAll("platform.includeresource", includeresource);
        this.addAll("platform.compiler.*", compiler);
        this.addAll("platform.linkpath", linkpath);
        this.addAll("platform.linkresource", linkresource);
        this.addAll("platform.link", link);
        this.addAll("platform.frameworkpath", frameworkpath);
        this.addAll("platform.framework", framework);
        this.addAll("platform.preloadresource", preloadresource);
        this.addAll("platform.preloadpath", preloadpath);
        this.addAll("platform.preload", preload);
        this.addAll("platform.resourcepath", resourcepath);
        this.addAll("platform.resource", resource);
        if (this.platformExtension == null || this.platformExtension.length() == 0) {
            this.addAll("platform.extension", extension);
        }
        this.addAll("platform.executablepath", executablepath);
        this.addAll("platform.executable", executable);
        this.setProperty("platform.library", library);
        if (LoadEnabled.class.isAssignableFrom(c2)) {
            try {
                ((LoadEnabled)c2.newInstance()).init(this);
            }
            catch (ClassCastException | IllegalAccessException | InstantiationException e2) {
                logger.warn("Could not create an instance of " + c2 + ": " + e2);
            }
        }
        this.loaded |= hasPlatformProperties;
    }

    public List<Class> getInheritedClasses() {
        return this.inheritedClasses;
    }

    public List<Class> getEffectiveClasses() {
        return this.effectiveClasses;
    }

    public boolean isLoaded() {
        return this.loaded;
    }
}

