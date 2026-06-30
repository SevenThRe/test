/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.modlauncher.api.IEnvironment
 *  cpw.mods.modlauncher.api.ITransformationService
 *  cpw.mods.modlauncher.api.ITransformer
 *  cpw.mods.modlauncher.api.IncompatibleEnvironmentException
 *  org.apache.logging.log4j.LogManager
 *  org.apache.logging.log4j.Logger
 */
package optifine;

import cpw.mods.modlauncher.api.IEnvironment;
import cpw.mods.modlauncher.api.ITransformationService;
import cpw.mods.modlauncher.api.ITransformer;
import cpw.mods.modlauncher.api.IncompatibleEnvironmentException;
import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URL;
import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import optifine.OptiFineResourceLocator;
import optifine.OptiFineTransformer;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class OptiFineTransformationService
implements ITransformationService {
    private static final Logger LOGGER = LogManager.getLogger();
    private static URL ofZipFileUrl;
    private static ZipFile ofZipFile;
    private static OptiFineTransformer transformer;

    public String name() {
        return "OptiFine";
    }

    public void initialize(IEnvironment environment) {
        LOGGER.info("OptiFineTransformationService.initialize");
    }

    public void beginScanning(IEnvironment environment) {
    }

    public void onLoad(IEnvironment env, Set<String> otherServices) throws IncompatibleEnvironmentException {
        LOGGER.info("OptiFineTransformationService.onLoad");
        ofZipFileUrl = OptiFineTransformer.class.getProtectionDomain().getCodeSource().getLocation();
        try {
            URI uri = ofZipFileUrl.toURI();
            File file = new File(uri);
            ofZipFile = new ZipFile(file);
            LOGGER.info("OptiFine ZIP file: " + file);
            transformer = new OptiFineTransformer(ofZipFile);
            OptiFineResourceLocator.setResourceLocator(transformer);
        }
        catch (Exception e) {
            LOGGER.error("Error loading OptiFine ZIP file: " + ofZipFileUrl, (Throwable)e);
            throw new IncompatibleEnvironmentException("Error loading OptiFine ZIP file: " + ofZipFileUrl);
        }
    }

    public Map.Entry<Set<String>, Supplier<Function<String, Optional<URL>>>> additionalResourcesLocator() {
        return super.additionalResourcesLocator();
    }

    public Map.Entry<Set<String>, Supplier<Function<String, Optional<URL>>>> additionalClassesLocator() {
        HashSet<String> key = new HashSet<String>();
        key.add("net.optifine.");
        key.add("optifine.");
        Supplier<Function> value = () -> this::getResourceUrl;
        AbstractMap.SimpleEntry<Set<String>, Supplier<Function<String, Optional<URL>>>> entry = new AbstractMap.SimpleEntry<Set<String>, Supplier<Function<String, Optional<URL>>>>(key, value);
        LOGGER.info("additionalClassesLocator: " + key);
        return entry;
    }

    public Optional<URL> getResourceUrl(String name) {
        if (name.endsWith(".class") && !name.startsWith("optifine/")) {
            name = "srg/" + name;
        }
        if (transformer == null) {
            return Optional.empty();
        }
        ZipEntry ze = ofZipFile.getEntry(name);
        if (ze == null) {
            return Optional.empty();
        }
        try {
            String ofZipUrlStr = ofZipFileUrl.toExternalForm();
            URL urlJar = new URL("jar:" + ofZipUrlStr + "!/" + name);
            return Optional.of(urlJar);
        }
        catch (IOException e) {
            LOGGER.error((Object)e);
            return Optional.empty();
        }
    }

    public List<ITransformer> transformers() {
        LOGGER.info("OptiFineTransformationService.transformers");
        ArrayList<ITransformer> list = new ArrayList<ITransformer>();
        if (transformer != null) {
            list.add(transformer);
        }
        return list;
    }

    public static OptiFineTransformer getTransformer() {
        return transformer;
    }
}

