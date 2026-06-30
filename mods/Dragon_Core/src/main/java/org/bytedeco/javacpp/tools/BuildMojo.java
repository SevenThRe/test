/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.apache.maven.artifact.Artifact
 *  org.apache.maven.plugin.AbstractMojo
 *  org.apache.maven.plugin.MojoExecutionException
 *  org.apache.maven.plugin.descriptor.PluginDescriptor
 *  org.apache.maven.plugin.logging.Log
 *  org.apache.maven.plugins.annotations.LifecyclePhase
 *  org.apache.maven.plugins.annotations.Mojo
 *  org.apache.maven.plugins.annotations.Parameter
 *  org.apache.maven.project.MavenProject
 */
package org.bytedeco.javacpp.tools;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.Map;
import java.util.Properties;
import org.apache.maven.artifact.Artifact;
import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.descriptor.PluginDescriptor;
import org.apache.maven.plugin.logging.Log;
import org.apache.maven.plugins.annotations.LifecyclePhase;
import org.apache.maven.plugins.annotations.Mojo;
import org.apache.maven.plugins.annotations.Parameter;
import org.apache.maven.project.MavenProject;
import org.bytedeco.javacpp.Loader;
import org.bytedeco.javacpp.tools.Builder;
import org.bytedeco.javacpp.tools.Logger;
import org.bytedeco.javacpp.tools.ParserException;

@Mojo(name="build", defaultPhase=LifecyclePhase.PROCESS_CLASSES, threadSafe=true)
public class BuildMojo
extends AbstractMojo {
    @Parameter(property="javacpp.classPath", defaultValue="${project.build.outputDirectory}")
    String classPath = null;
    @Parameter(property="javacpp.classPaths")
    String[] classPaths = null;
    @Parameter(property="javacpp.includePath")
    String includePath = null;
    @Parameter(property="javacpp.includePaths")
    String[] includePaths = null;
    @Parameter(property="javacpp.includeResource")
    String includeResource = null;
    @Parameter(property="javacpp.includeResources")
    String[] includeResources = null;
    @Parameter(property="javacpp.buildPath")
    String buildPath = null;
    @Parameter(property="javacpp.buildPaths")
    String[] buildPaths = null;
    @Parameter(property="javacpp.buildResource")
    String buildResource = null;
    @Parameter(property="javacpp.buildResources")
    String[] buildResources = null;
    @Parameter(property="javacpp.linkPath")
    String linkPath = null;
    @Parameter(property="javacpp.linkPaths")
    String[] linkPaths = null;
    @Parameter(property="javacpp.linkResource")
    String linkResource = null;
    @Parameter(property="javacpp.linkResources")
    String[] linkResources = null;
    @Parameter(property="javacpp.preloadPath")
    String preloadPath = null;
    @Parameter(property="javacpp.preloadPaths")
    String[] preloadPaths = null;
    @Parameter(property="javacpp.preloadResource")
    String preloadResource = null;
    @Parameter(property="javacpp.preloadResources")
    String[] preloadResources = null;
    @Parameter(property="javacpp.resourcePath")
    String resourcePath = null;
    @Parameter(property="javacpp.resourcePaths")
    String[] resourcePaths = null;
    @Parameter(property="javacpp.executablePath")
    String executablePath = null;
    @Parameter(property="javacpp.executablePaths")
    String[] executablePaths = null;
    @Parameter(property="javacpp.encoding")
    String encoding = null;
    @Parameter(property="javacpp.outputDirectory")
    File outputDirectory = null;
    @Parameter(property="javacpp.outputName")
    String outputName = null;
    @Parameter(property="javacpp.clean", defaultValue="false")
    boolean clean = false;
    @Parameter(property="javacpp.generate", defaultValue="true")
    boolean generate = true;
    @Parameter(property="javacpp.compile", defaultValue="true")
    boolean compile = true;
    @Parameter(property="javacpp.deleteJniFiles", defaultValue="true")
    boolean deleteJniFiles = true;
    @Parameter(property="javacpp.header", defaultValue="false")
    boolean header = false;
    @Parameter(property="javacpp.copyLibs", defaultValue="false")
    boolean copyLibs = false;
    @Parameter(property="javacpp.copyResources", defaultValue="false")
    boolean copyResources = false;
    @Parameter(property="javacpp.configDirectory")
    String configDirectory = null;
    @Parameter(property="javacpp.jarPrefix")
    String jarPrefix = null;
    @Parameter(property="javacpp.properties")
    String properties = null;
    @Parameter(property="javacpp.propertyFile")
    File propertyFile = null;
    @Parameter(property="javacpp.propertyKeysAndValues")
    Properties propertyKeysAndValues = null;
    @Parameter(property="javacpp.classOrPackageName")
    String classOrPackageName = null;
    @Parameter(property="javacpp.classOrPackageNames")
    String[] classOrPackageNames = null;
    @Parameter(property="javacpp.buildCommand")
    String[] buildCommand = null;
    @Parameter(property="javacpp.targetDirectory")
    String targetDirectory = null;
    @Parameter(property="javacpp.targetDirectories")
    String[] targetDirectories = null;
    @Parameter(property="javacpp.workingDirectory")
    File workingDirectory = null;
    @Parameter(property="javacpp.environmentVariables")
    Map<String, String> environmentVariables = null;
    @Parameter(property="javacpp.compilerOptions")
    String[] compilerOptions = null;
    @Parameter(property="javacpp.skip", defaultValue="false")
    boolean skip = false;
    @Parameter(defaultValue="${project}", required=true, readonly=true)
    MavenProject project;
    @Parameter(defaultValue="${plugin}", required=true, readonly=true)
    PluginDescriptor plugin;

    String[] merge(String[] ss2, String s2) {
        if (ss2 != null && s2 != null) {
            ss2 = Arrays.copyOf(ss2, ss2.length + 1);
            ss2[ss2.length - 1] = s2;
        } else if (s2 != null) {
            ss2 = new String[]{s2};
        }
        return ss2 != null ? ss2 : new String[]{};
    }

    public void execute() throws MojoExecutionException {
        final Log log = this.getLog();
        try {
            String v2;
            if (log.isDebugEnabled()) {
                log.debug((CharSequence)("classPath: " + this.classPath));
                log.debug((CharSequence)("classPaths: " + Arrays.deepToString(this.classPaths)));
                log.debug((CharSequence)("buildPath: " + this.buildPath));
                log.debug((CharSequence)("buildPaths: " + Arrays.deepToString(this.buildPaths)));
                log.debug((CharSequence)("buildResource: " + this.buildResource));
                log.debug((CharSequence)("buildResources: " + Arrays.deepToString(this.buildResources)));
                log.debug((CharSequence)("includePath: " + this.includePath));
                log.debug((CharSequence)("includePaths: " + Arrays.deepToString(this.includePaths)));
                log.debug((CharSequence)("includeResource: " + this.includeResource));
                log.debug((CharSequence)("includeResources: " + Arrays.deepToString(this.includeResources)));
                log.debug((CharSequence)("linkPath: " + this.linkPath));
                log.debug((CharSequence)("linkPaths: " + Arrays.deepToString(this.linkPaths)));
                log.debug((CharSequence)("linkResource: " + this.linkResource));
                log.debug((CharSequence)("linkResources: " + Arrays.deepToString(this.linkResources)));
                log.debug((CharSequence)("preloadPath: " + this.preloadPath));
                log.debug((CharSequence)("preloadPaths: " + Arrays.deepToString(this.preloadPaths)));
                log.debug((CharSequence)("preloadResource: " + this.preloadResource));
                log.debug((CharSequence)("preloadResources: " + Arrays.deepToString(this.preloadResources)));
                log.debug((CharSequence)("resourcePath: " + this.resourcePath));
                log.debug((CharSequence)("resourcePaths: " + Arrays.deepToString(this.resourcePaths)));
                log.debug((CharSequence)("executablePath: " + this.executablePath));
                log.debug((CharSequence)("executablePaths: " + Arrays.deepToString(this.executablePaths)));
                log.debug((CharSequence)("encoding: " + this.encoding));
                log.debug((CharSequence)("outputDirectory: " + this.outputDirectory));
                log.debug((CharSequence)("outputName: " + this.outputName));
                log.debug((CharSequence)("clean: " + this.clean));
                log.debug((CharSequence)("generate: " + this.generate));
                log.debug((CharSequence)("compile: " + this.compile));
                log.debug((CharSequence)("deleteJniFiles: " + this.deleteJniFiles));
                log.debug((CharSequence)("header: " + this.header));
                log.debug((CharSequence)("copyLibs: " + this.copyLibs));
                log.debug((CharSequence)("copyResources: " + this.copyResources));
                log.debug((CharSequence)("configDirectory: " + this.configDirectory));
                log.debug((CharSequence)("jarPrefix: " + this.jarPrefix));
                log.debug((CharSequence)("properties: " + this.properties));
                log.debug((CharSequence)("propertyFile: " + this.propertyFile));
                log.debug((CharSequence)("propertyKeysAndValues: " + this.propertyKeysAndValues));
                log.debug((CharSequence)("classOrPackageName: " + this.classOrPackageName));
                log.debug((CharSequence)("classOrPackageNames: " + Arrays.deepToString(this.classOrPackageNames)));
                log.debug((CharSequence)("buildCommand: " + Arrays.deepToString(this.buildCommand)));
                log.debug((CharSequence)("targetDirectory: " + Arrays.deepToString(this.buildCommand)));
                log.debug((CharSequence)("workingDirectory: " + this.workingDirectory));
                log.debug((CharSequence)("environmentVariables: " + this.environmentVariables));
                log.debug((CharSequence)("compilerOptions: " + Arrays.deepToString(this.compilerOptions)));
                log.debug((CharSequence)("skip: " + this.skip));
            }
            if (this.targetDirectory != null) {
                this.project.addCompileSourceRoot(this.targetDirectory);
            }
            if (this.targetDirectories != null) {
                for (String targetDirectory : this.targetDirectories) {
                    this.project.addCompileSourceRoot(targetDirectory);
                }
            }
            if (this.skip) {
                log.info((CharSequence)"Skipping execution of JavaCPP Builder");
                return;
            }
            this.classPaths = this.merge(this.classPaths, this.classPath);
            this.classOrPackageNames = this.merge(this.classOrPackageNames, this.classOrPackageName);
            Logger logger = new Logger(){

                @Override
                public void debug(String s2) {
                    log.debug((CharSequence)s2);
                }

                @Override
                public void info(String s2) {
                    log.info((CharSequence)s2);
                }

                @Override
                public void warn(String s2) {
                    log.warn((CharSequence)s2);
                }

                @Override
                public void error(String s2) {
                    log.error((CharSequence)s2);
                }
            };
            Builder builder = new Builder(logger).classPaths(this.classPaths).encoding(this.encoding).outputDirectory(this.outputDirectory).outputName(this.outputName).clean(this.clean).generate(this.generate).compile(this.compile).deleteJniFiles(this.deleteJniFiles).header(this.header).copyLibs(this.copyLibs).copyResources(this.copyResources).configDirectory(this.configDirectory).jarPrefix(this.jarPrefix).properties(this.properties).propertyFile(this.propertyFile).properties(this.propertyKeysAndValues).classesOrPackages(this.classOrPackageNames).buildCommand(this.buildCommand).workingDirectory(this.workingDirectory).environmentVariables(this.environmentVariables).compilerOptions(this.compilerOptions);
            Properties properties = builder.properties;
            String extension = properties.getProperty("platform.extension");
            log.info((CharSequence)("Detected platform \"" + Loader.getPlatform() + "\""));
            log.info((CharSequence)("Building platform \"" + properties.get("platform") + "\"" + (extension != null && extension.length() > 0 ? " with extension \"" + extension + "\"" : "")));
            properties.setProperty("platform.host", Loader.getPlatform());
            String module = properties.get("platform") + (extension != null ? extension : "");
            properties.setProperty("platform.module", module.replace('-', '.'));
            String separator = properties.getProperty("platform.path.separator");
            for (String s2 : this.merge(this.buildPaths, this.buildPath)) {
                v2 = properties.getProperty("platform.buildpath", "");
                properties.setProperty("platform.buildpath", v2.length() == 0 || v2.endsWith(separator) ? v2 + s2 : v2 + separator + s2);
            }
            for (String s2 : this.merge(this.buildResources, this.buildResource)) {
                v2 = properties.getProperty("platform.buildresource", "");
                properties.setProperty("platform.buildresource", v2.length() == 0 || v2.endsWith(separator) ? v2 + s2 : v2 + separator + s2);
            }
            for (String s2 : this.merge(this.includePaths, this.includePath)) {
                v2 = properties.getProperty("platform.includepath", "");
                properties.setProperty("platform.includepath", v2.length() == 0 || v2.endsWith(separator) ? v2 + s2 : v2 + separator + s2);
            }
            for (String s2 : this.merge(this.includeResources, this.includeResource)) {
                v2 = properties.getProperty("platform.includeresource", "");
                properties.setProperty("platform.includeresource", v2.length() == 0 || v2.endsWith(separator) ? v2 + s2 : v2 + separator + s2);
            }
            for (String s2 : this.merge(this.linkPaths, this.linkPath)) {
                v2 = properties.getProperty("platform.linkpath", "");
                properties.setProperty("platform.linkpath", v2.length() == 0 || v2.endsWith(separator) ? v2 + s2 : v2 + separator + s2);
            }
            for (String s2 : this.merge(this.linkResources, this.linkResource)) {
                v2 = properties.getProperty("platform.linkresource", "");
                properties.setProperty("platform.linkresource", v2.length() == 0 || v2.endsWith(separator) ? v2 + s2 : v2 + separator + s2);
            }
            for (String s2 : this.merge(this.preloadPaths, this.preloadPath)) {
                v2 = properties.getProperty("platform.preloadpath", "");
                properties.setProperty("platform.preloadpath", v2.length() == 0 || v2.endsWith(separator) ? v2 + s2 : v2 + separator + s2);
            }
            for (String s2 : this.merge(this.preloadResources, this.preloadResource)) {
                v2 = properties.getProperty("platform.preloadresource", "");
                properties.setProperty("platform.preloadresource", v2.length() == 0 || v2.endsWith(separator) ? v2 + s2 : v2 + separator + s2);
            }
            for (String s2 : this.merge(this.resourcePaths, this.resourcePath)) {
                v2 = properties.getProperty("platform.resourcepath", "");
                properties.setProperty("platform.resourcepath", v2.length() == 0 || v2.endsWith(separator) ? v2 + s2 : v2 + separator + s2);
            }
            for (String s2 : this.merge(this.executablePaths, this.executablePath)) {
                v2 = properties.getProperty("platform.executablepath", "");
                properties.setProperty("platform.executablepath", v2.length() == 0 || v2.endsWith(separator) ? v2 + s2 : v2 + separator + s2);
            }
            properties.setProperty("platform.artifacts", this.project.getBuild().getOutputDirectory());
            for (Artifact a2 : this.plugin.getArtifacts()) {
                String s3 = a2.getFile().getCanonicalPath();
                String v3 = properties.getProperty("platform.artifacts", "");
                properties.setProperty("platform.artifacts", v3.length() == 0 || v3.endsWith(separator) ? v3 + s3 : v3 + separator + s3);
            }
            Properties projectProperties = this.project.getProperties();
            for (String key : properties.stringPropertyNames()) {
                projectProperties.setProperty("javacpp." + key, properties.getProperty(key));
            }
            Object[] outputFiles = builder.build();
            if (log.isDebugEnabled()) {
                log.debug((CharSequence)("outputFiles: " + Arrays.deepToString(outputFiles)));
            }
        }
        catch (IOException | ClassNotFoundException | InterruptedException | NoClassDefFoundError | ParserException e2) {
            log.error((CharSequence)("Failed to execute JavaCPP Builder: " + e2.getMessage()));
            throw new MojoExecutionException("Failed to execute JavaCPP Builder", e2);
        }
    }
}

